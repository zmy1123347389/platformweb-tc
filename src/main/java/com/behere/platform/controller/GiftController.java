package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.behere.common.config.FTPConfig;
import com.behere.common.utils.ImageUtils;
import com.behere.common.utils.R;
import com.behere.common.utils.StringUtils;
import com.behere.platform.domain.Gift;
import com.behere.platform.domain.VersionSwitch;
import com.behere.platform.service.GiftService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "platform/gift")
public class GiftController {

    @Autowired
    private FTPConfig ftpConfig;

    private String prefix = "platform/gift";

    @Autowired
    private GiftService giftService;

    @GetMapping()
    String gift() {
        return prefix + "/gift";
    }

    @GetMapping("/list")
    @ResponseBody()
    public List<Gift> list() {
        List<Gift> gifts = giftService.list();
        return gifts;
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") int id, Model model) {
        Gift gift = giftService.queryGiftById(id);
        model.addAttribute("gift", gift);
        return prefix + "/edit";
    }

    @ResponseBody
    @PostMapping("/update")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            Gift gift = new Gift();
            gift.setId(Integer.parseInt(request.getParameter("id")));
            setModel(gift, file, request);
            giftService.update(gift);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(VersionSwitch versionSwitch) {
        try {

            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/remove")
    @ResponseBody()
    R save(int id) {
        if (giftService.delete(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    public void setModel(Gift gift, MultipartFile file, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
            gift.setUrl(ImageUtils.setFileToModel(ftpConfig, file));
        }
        gift.setName(request.getParameter("name"));
        gift.setFlower(Integer.parseInt(request.getParameter("flower")));
    }
}