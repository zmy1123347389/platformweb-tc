package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.behere.common.constant.Constant;
import com.behere.common.push.Demo;
import com.behere.common.push.PushUtil;
import com.behere.common.utils.PageUtils;
import com.behere.common.utils.Query;
import com.behere.common.utils.R;
import com.behere.platform.domain.User;
import com.behere.platform.domain.UserAuth;
import com.behere.platform.service.PlatformUserService;

import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "platform/user")
public class PlatformUserController {

    private String prefix = "platform/user";

    @Autowired
    private PlatformUserService platformUserService;

    @GetMapping()
    String user() {
        return prefix + "/user";
    }

    @GetMapping("/listAuth")
    String listAuth() {
        return prefix + "/user_auth";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<User> users = platformUserService.list(query);
        int total = platformUserService.count(query);
        PageUtils pageUtil = new PageUtils(users, total);
        return pageUtil;
    }

    @GetMapping("/listUserAuth")
    @ResponseBody
    PageUtils listUserAuth(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<UserAuth> userAuths = platformUserService.listUserAuthInformation(query);
        int total = platformUserService.countUserAuth(query);
        PageUtils pageUtil = new PageUtils(userAuths, total);
        return pageUtil;
    }

    @PostMapping("/dealUserAuth")
    @ResponseBody
    public R dealUserAuth(long id, int status, long userId) {
        try {
//            PushUtil android = new PushUtil(Constant.ANDROID_PUSH_APP_KEY, Constant.ANDROID_PUSH_Master_SECRET);
//            PushUtil ios = new PushUtil(Constant.IOS_PUSH_APP_KEY, Constant.IOS_PUSH_Master_SECRET);
            platformUserService.dealSuccessUserAuth(status, id, userId);
//            String content = Constant.AUTH_SUCCESS;
//            if (status != 1) {
//                content = Constant.AUTH_FAIL;
//            }
//            android.sendAndroidCustomizedcast(String.valueOf(userId),"IM",Constant.AUTH_TITLE,content);
//            ios.sendIOSCustomizedcast(String.valueOf(userId),"IM",Constant.AUTH_TITLE,content);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }

    @PostMapping("/deal")
    @ResponseBody
    public R deal(int status, long userId) {
        try {
            platformUserService.updateUserDeleted(status, userId);
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }
}