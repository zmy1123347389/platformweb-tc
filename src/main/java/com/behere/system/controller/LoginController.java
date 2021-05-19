package com.behere.system.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.annotation.Log;
import com.behere.common.config.Constant;
import com.behere.common.controller.BaseController;
import com.behere.common.domain.FileDO;
import com.behere.common.domain.Tree;
import com.behere.common.service.FileService;
import com.behere.common.utils.MD5Utils;
import com.behere.common.utils.R;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.ShiroUtils;
import com.behere.system.domain.MenuDO;
import com.behere.system.domain.UserDO;
import com.behere.system.service.MenuService;
import com.behere.system.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserService userService;

	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/login";
	}

	@Log("请求访问WEB主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		RedisUtil redis = new RedisUtil();
		if(getUser() == null) {
			return "redirect:/login";
		}
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/logo.png");
			}
		}else {
			model.addAttribute("picUrl","/img/logo.png");
		}
		model.addAttribute("username", getUser().getUsername());
		return "portal/index";
	}
	
	@Log("请求访问后台主页")
	@GetMapping({ "/indexHome" })
	String indexHome(Model model) {
		RedisUtil redis = new RedisUtil();
		if(getUser() == null) {
			return "redirect:/login";
		}
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/logo.png");
			}
		}else {
			model.addAttribute("picUrl","/img/logo.png");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}
	
	@GetMapping("/register")
	String register() {
		return "register";
	}
	
	@Log("保存用户")
	@PostMapping("/registerSave")
	@ResponseBody
	R save(UserDO user) {
		List<Long> roleIds = new ArrayList<Long>();
		roleIds.add((long) 59);
		user.setRoleIds(roleIds);
		user.setStatus(1);
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return R.ok();
		}
		return R.error();
	}


	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {

		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
