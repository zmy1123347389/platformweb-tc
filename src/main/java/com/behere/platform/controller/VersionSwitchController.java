package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.behere.common.controller.BaseController;
import com.behere.common.utils.PageUtils;
import com.behere.common.utils.Query;
import com.behere.common.utils.R;
import com.behere.platform.domain.Version;
import com.behere.platform.domain.VersionSwitch;
import com.behere.platform.service.VersionService;

import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/platform/versionSwitch")
public class VersionSwitchController extends BaseController {
    private String prefix = "platform/version";

    @Autowired
    private VersionService versionService;


    @GetMapping()
    String versionSwitch(Model model) {
        return prefix + "/version_switch";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<VersionSwitch> versionSwitches = versionService.queryVersionSwitch(query);
        int total = versionService.count();
        PageUtils pageUtil = new PageUtils(versionSwitches, total);
        return pageUtil;
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") int id, Model model) {
        VersionSwitch versionSwitch = versionService.queryVersionSwitchById(id);
        model.addAttribute("versionSwitch", versionSwitch);
        return prefix + "/edit_switch";
    }

    @ResponseBody
    @PostMapping("/update")
    public R upload(VersionSwitch versionSwitch) {
        try {
            versionService.updateVersionSwitchOnOff(versionSwitch.getId(), versionSwitch.getOnOff());
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/add_switch";
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(VersionSwitch versionSwitch) {
        try {
            versionService.save(versionSwitch);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}