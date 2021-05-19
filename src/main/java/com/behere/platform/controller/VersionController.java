package com.behere.platform.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.behere.common.controller.BaseController;
import com.behere.common.utils.R;
import com.behere.platform.domain.Version;
import com.behere.platform.service.VersionService;

import java.util.List;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/platform/version")
public class VersionController extends BaseController {
    private String prefix = "platform/version";

    @Autowired
    private VersionService versionService;


    @GetMapping()
    String version(Model model) {
        return prefix + "/version";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Version> queryVersions() {
        return versionService.queryVersions();
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") int id, Model model) {
        Version version = versionService.queryVersion(id);
        model.addAttribute("version", version);
        return prefix + "/edit";
    }

    @ResponseBody
    @PostMapping("/update")
    public R upload(Version version) {
        try {
            versionService.updateVersion(version);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}