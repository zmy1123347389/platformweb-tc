package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.controller.BaseController;
import com.behere.common.utils.R;
import com.behere.platform.service.IndexService;

/**
 * @author: Behere
 */
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    @ResponseBody
    public R getIndexData(String date) {
        try {
            long newUser = indexService.countUserByCreateDate(date);
            long onlineUser = indexService.countOnlineUser();
        } catch (Exception e) {
            return R.error();
        }
        return R.ok();
    }
}
