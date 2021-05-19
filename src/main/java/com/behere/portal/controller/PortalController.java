package com.behere.portal.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.controller.BaseController;
import com.behere.common.utils.PageUtils;
import com.behere.common.utils.Query;
import com.behere.platform.domain.AuthAccount;
import com.behere.platform.domain.RechargeDO;
import com.behere.platform.service.AccountService;
import com.behere.portal.domain.LawappProclamationInfo;
import com.behere.portal.service.LawappProclamationInfoService;

/**
 * 门户网站
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalController extends BaseController {

    private String prefix = "portal";
    
    @Autowired
    private LawappProclamationInfoService lawappProclamationInfoService;

    @Autowired
    private AccountService accountService;

    /**
     * 跳转头条通知详细内容
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/detail")
    String authAccount(Model model,String id) {
        return prefix + "/detail";
    }
    
    /**
     * 获取头条通知列表
     * @param lawappProclamationInfo
     * @param params
     * @return
     */
    @RequestMapping("/lawappProclamationInfoList")
    @ResponseBody
    PageUtils lawappProclamationInfoList(LawappProclamationInfo lawappProclamationInfo,@RequestParam Map<String, Object> params) {
        // 查询列表数据
        List<LawappProclamationInfo> rechargeList = lawappProclamationInfoService.selectByEntityList(lawappProclamationInfo, params);
        PageUtils pageUtil = new PageUtils(rechargeList, 10);
        return pageUtil;
    }
    
    /**
     * 获取头条通知详细内容
     * @param id
     * @return
     */
    @RequestMapping("/lawappProclamationInfoById")
    @ResponseBody
    Object lawappProclamationInfoById(String id) {
        LawappProclamationInfo lawappProclamationInfo = lawappProclamationInfoService.selectById(id);
        return lawappProclamationInfo;
    }
    
    /**
     * 正式考试列表
     * @param model
     * @return
     */
    @GetMapping("/examinationList")
    String examinationList(Model model) {
        return prefix + "/examination/examinationList";
    }
    
    /**
     * 正式考试试题详情
     * @param model
     * @return
     */
    @GetMapping("/examinationDetail")
    String examinationDetail(Model model) {
        return prefix + "/examination/examinationDetail";
    }
    
    /**
     * 正式考试结束信息
     * @param model
     * @return
     */
    @GetMapping("/examinationMsg")
    String examinationMsg(Model model) {
        return prefix + "/examination/examinationMsg";
    }
    
    /**
     * 模拟考试列表
     * @param model
     * @return
     */
    @GetMapping("/examinationmockList")
    String examinationmockList(Model model) {
        return prefix + "/examinationmock/examinationmockList";
    }
    
    /**
     * 模拟试题详情
     * @param model
     * @return
     */
    @GetMapping("/examinationmockDetail")
    String examinationmockDetail(Model model) {
        return prefix + "/examinationmock/examinationmockDetail";
    }
    
    /**
     * 模拟结束信息
     * @param model
     * @return
     */
    @GetMapping("/examinationmockMsg")
    String examinationmockMsg(Model model) {
        return prefix + "/examinationmock/examinationmockMsg";
    }
    
    
    /**
     * 每周必练考试列表
     * @param model
     * @return
     */
    @GetMapping("/examinationweekList")
    String examinationweekList(Model model) {
        return prefix + "/examinationweek/examinationweekList";
    }
    
    /**
     * 每周必练试题详情
     * @param model
     * @return
     */
    @GetMapping("/examinationweekDetail")
    String examinationweekDetail(Model model) {
        return prefix + "/examinationweek/examinationweekDetail";
    }
    
    /**
     * 每周必练结束信息
     * @param model
     * @return
     */
    @GetMapping("/examinationweekMsg")
    String examinationweekMsg(Model model) {
        return prefix + "/examinationweek/examinationweekMsg";
    }
    
    /**
     * 自测自练试题详情
     * @param model
     * @return
     */
    @GetMapping("/selfexaminationDetail")
    String selfexaminationDetail(Model model) {
        return prefix + "/selfexamination/selfexaminationDetail";
    }
    
    /**
     * 自测自练结束信息
     * @param model
     * @return
     */
    @GetMapping("/selfexaminationMsg")
    String selfexaminationMsg(Model model) {
        return prefix + "/selfexamination/selfexaminationMsg";
    }
    

    /**
     * 闯关答题试题详情
     * @param model
     * @return
     */
    @GetMapping("/answerQuestionsDetail")
    String answerQuestionsDetail(Model model) {
        return prefix + "/answerquestions/answerQuestionsDetail";
    }
    
    /**
     * 闯关答题结束信息
     * @param model
     * @return
     */
    @GetMapping("/answerQuestionsMsg")
    String answerQuestionsMsg(Model model) {
        return prefix + "/answerquestions/answerQuestionsMsg";
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