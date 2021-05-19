package com.behere.platform.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.behere.common.constants.PayConstant;
import com.behere.common.controller.BaseController;
import com.behere.common.utils.PageUtils;
import com.behere.common.utils.Query;
import com.behere.common.utils.R;
import com.behere.common.utils.StringUtils;
import com.behere.platform.domain.AuthAccount;
import com.behere.platform.domain.RechargeDO;
import com.behere.platform.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/platform/account")
public class AccountController extends BaseController {

    private String prefix = "platform/account";

    @Autowired
    private AccountService accountService;


    @GetMapping("/authAccount")
    String authAccount(Model model) {
        return prefix + "/auth/auth_account";
    }


    @GetMapping("/recharge")
    String recharge(Model model) {
        return prefix + "/recharge/recharge";
    }

    @GetMapping("/rechargeList")
    @ResponseBody
    PageUtils rechargeList(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<RechargeDO> rechargeList = accountService.queryRechargeList(query);
        int total = accountService.countRecharges(query);
        PageUtils pageUtil = new PageUtils(rechargeList, total);
        return pageUtil;
    }

    @RequestMapping("/listAuthAccount")
    @ResponseBody
    public PageUtils listAuthAccount(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<AuthAccount> authAccounts = accountService.queryAuthAccount(query);
        int total = accountService.countAuthAccount(query);
        PageUtils pageUtil = new PageUtils(authAccounts, total);
        return pageUtil;
    }
}