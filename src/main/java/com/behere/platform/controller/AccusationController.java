package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.behere.common.constant.MsgConstant;
import com.behere.common.constants.Constant;
import com.behere.common.controller.BaseController;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.PageUtils;
import com.behere.common.utils.Query;
import com.behere.common.utils.R;
import com.behere.platform.domain.AccusationDO;
import com.behere.platform.domain.AccusationPic;
import com.behere.platform.domain.User;
import com.behere.platform.service.AccusationService;
import com.behere.platform.service.PlatformUserService;

import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 *
 * 举报管理
 *
 */
@Controller
@RequestMapping(value = "/platform/accusation")
public class AccusationController extends BaseController {

    private String prefix = "platform/accusation";

    @Autowired
    private AccusationService accusationService;

    @Autowired
    private PlatformUserService platformUserService;

    @GetMapping("/accusation")
    public String accusation(Model model) {
        return prefix + "/accusation";
    }

    @GetMapping("/accusationList")
    @ResponseBody
    PageUtils accusationList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<AccusationDO> accusations = accusationService.queryAccusations(query);
        for (AccusationDO accusationDO : accusations) {
            List<AccusationPic> pics = accusationService.queryAccusationPics(accusationDO.getId());
            accusationDO.setPics(pics);
        }
        int total = accusationService.count(query);
        PageUtils pageUtil = new PageUtils(accusations, total);
        return pageUtil;
    }

    @PostMapping("/deal")
    @ResponseBody
    public R deal(String id, int status) {
        try {
            AccusationDO accusationDO = accusationService.queryAccusationById(id);
            if (accusationDO != null) {
                if (status == 1) {
                    platformUserService.updateUserDeleted(Constant.DELETED, accusationDO.getReportedUserId());
                    accusationService.updateAccusationDealStatus(Constant.DELETED, id);
                    User user = platformUserService.queryUserById(accusationDO.getReportedUserId());
                    String reportContent = accusationService.queryReportContentByAccusationId(accusationDO.getAccusationId());
                   // String msg = "您举报" + user.getNickName() + "有“" + reportContent + "”行为，小颜已处理，感谢您对颜语的支持。";
                    String msg = NeteaseUtils.setMsgExtMap(user.getId(), null, user.getNickName(), null, null, MsgConstant.ACCUSATION_SUCCESS, reportContent);
                    NeteaseUtils.sendNetease(msg, accusationDO.getReportUserId());
                } else {
                    accusationService.updateAccusationDealStatus(2, id);
                }
            }
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}