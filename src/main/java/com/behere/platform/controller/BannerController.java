package com.behere.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.behere.common.annotation.Log;
import com.behere.common.config.FTPConfig;
import com.behere.common.controller.BaseController;
import com.behere.common.utils.*;
import com.behere.platform.domain.BannerDO;
import com.behere.platform.service.BannerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Behere
 */
@Controller
@RequestMapping("/platform/banner")
public class BannerController extends BaseController {
    private String prefix = "platform/banner";
    @Autowired
    private FTPConfig ftpConfig;

    @Autowired
    private BannerService bannerService;

    @GetMapping()
    String banner() {
        return prefix + "/banner";
    }

    @GetMapping("/list")
    @ResponseBody()
    public List<BannerDO> list() {
        List<BannerDO> banners = bannerService.queryBanners();
        return banners;
    }

    @Log("编辑Banner")
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") int id, Model model) {
        BannerDO bannerDO = bannerService.queryBanner(id);
        model.addAttribute("banner", bannerDO);
        return prefix + "/edit";
    }

    @Log("新增Banner")
    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @Log("删除banner")
    @PostMapping("/remove")
    @ResponseBody()
    R save(int id) {
        if (bannerService.deleteBanner(id) > 0) {
            return R.ok();
        } else {
            return R.error(1, "删除失败");
        }
    }

    @ResponseBody
    @PostMapping("/update")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            BannerDO bannerDO = new BannerDO();
            bannerDO.setImage(ImageUtils.setFileToModel(ftpConfig, file));
            bannerDO.setId(Integer.parseInt(request.getParameter("bannerId")));
            bannerDO.setName(request.getParameter("name"));
            bannerDO.setUrl(request.getParameter("url"));
            bannerService.updateBanner(bannerDO);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            BannerDO bannerDO = new BannerDO();
            bannerDO.setImage(ImageUtils.setFileToModel(ftpConfig, file));
            bannerDO.setUrl(request.getParameter("url"));
            bannerDO.setName(request.getParameter("name"));
            bannerService.saveBanner(bannerDO);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}