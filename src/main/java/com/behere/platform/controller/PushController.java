package com.behere.platform.controller;

import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.behere.common.config.ScheduleConfig;
import com.behere.common.constant.Constant;
import com.behere.common.controller.BaseController;
import com.behere.common.domain.ScheduleJob;
import com.behere.common.push.PushUtil;
import com.behere.common.quartz.utils.QuartzManager;
import com.behere.common.utils.*;
import com.behere.platform.domain.PushDO;
import com.behere.platform.service.PushService;

import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/platform/push")
public class PushController extends BaseController {

    private String prefix = "platform/push";

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private PushService pushService;

    @GetMapping("/simplePush/{timing}")
    public String simplePush(Model model, @PathVariable("timing") int timing) {
        model.addAttribute("timing", timing);
        return prefix + "/push";
    }

    @GetMapping("/pushList")
    @ResponseBody
    PageUtils pushList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<PushDO> pushList = pushService.queryPushInformations(query);
        int total = pushService.count(query);
        PageUtils pageUtil = new PageUtils(pushList, total);
        return pageUtil;
    }

    @GetMapping("/add/{timing}")
    public String add(@PathVariable("timing") int timing) {
        if (timing == 0) {
            return prefix + "/add";
        } else {
            return prefix + "/add_timing";
        }
    }

    @PostMapping("/save")
    @ResponseBody
    public R save(PushDO pushDO) {
        long[] ids = pushDO.getIds();
        try {
            pushDO.setOperatorId(getUserId());
            if (pushDO.getTiming() == 0) {
                String title = pushDO.getTitle();
                String content = pushDO.getContent();
                pushService.sendBroadcast(pushDO.getType(), ids, title, content);
            } else {
                int rule = pushDO.getRule();
                String[] timingRules = pushDO.getTimingRule().split(",");
                String timingRule = timingRules[rule];
                pushDO.setCron(CronUtil.transationDate2Cron(rule, timingRule));
                String ruleDescribe = Constant.ONEC_TIME;
                if (rule == 1) {
                    ruleDescribe = Constant.YEAR_TIME;
                } else if (rule == 2) {
                    ruleDescribe = Constant.MONTH_TIME;
                } else if (rule == 3) {
                    ruleDescribe = Constant.DAY_TIME;
                }
                ruleDescribe = ruleDescribe + timingRule;
                pushDO.setRuleDescribe(ruleDescribe);
            }
            pushService.savePushInformation(pushDO);
            pushService.savePushUser(pushDO.getId(), ids);
            if (pushDO.getTiming() == 1) {
                ScheduleJob scheduleJob = new ScheduleJob();
                scheduleJob.setCronExpression(pushDO.getCron());
                scheduleJob.setBeanClass("com.behere.platform.service.impl.PushServiceImpl");
                scheduleJob.setJobName(String.valueOf(pushDO.getId()));

                scheduleJob.setMethodName("test");

                if (pushDO.getType() == 0) {
                    scheduleJob.setJobGroup("broadcast");
                } else {
                    scheduleJob.setJobGroup("customercast");
                }
                quartzManager.addJob(scheduleJob);
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}