package com.behere.portal.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.controller.BaseController;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.domain.LawAppPoliceInfo;
import com.behere.portal.service.LawAppIntegralInfoService;
import com.behere.portal.service.LawAppPoliceInfoService;
import com.behere.system.util.RespJson;

/**
 * @ClassName：IntegralController
 * @Auther: 京京
 * @Date: 2020/3/17 13:59
 * @Description:
 */
@Controller
@RequestMapping(value = "/integral")
public class LawAppIntegralInfoController extends BaseController{
	
    @Autowired
    private LawAppIntegralInfoService lawAppIntegralInfoService;
    
    
    @Autowired
    private LawAppPoliceInfoService lawAppPoliceInfoService;

    /**
     * 查询用户积分排名
     */
    @ResponseBody
    @RequestMapping("/selectRanking")
    public Object selectRanking(HttpServletRequest request ) {
    	List<Map<String, Object>> list = null;
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		String timeType = request.getParameter("timeType");
    		if(StringUtils.isEmpty(timeType)) {
    			return new RespJson("-1", "未上送参数");
    		}
    		String userId = getUsername();
    		if(StringUtils.isEmpty(userId)) {
    			return new RespJson("-1", "未上送警号");
    		}
    		map.put("timeType", timeType);
    		LawAppPoliceInfo  appPoliceInfo = lawAppPoliceInfoService.selectById(userId);
    		if(appPoliceInfo != null && !StringUtils.isEmpty(appPoliceInfo.getUnitAddress())) {
    			String [] utitIds = appPoliceInfo.getUnitAddress().split("-");
    			if(utitIds.length > 3) {
    				map.put("unitId", utitIds[3]);
    			}else if(utitIds.length > 2) {
    				map.put("unitId", utitIds[2]);
    			}else if(utitIds.length > 1) {
    				map.put("unitId", utitIds[1]);
    			}else {
    				map.put("unitId", utitIds[0]);
    			}
    		}else {
    			return list;
    		}
    		list = lawAppIntegralInfoService.selectRanking(map);
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
        return list;
    }


    /**
     *	 添加积分
     * 	自测自练(模拟答题)
     * @param userId
     * @param integralType == 7
     * @return
     */
    @ResponseBody
    @RequestMapping("/insertIntegral")
    public RespJson insertIntegral(HttpServletRequest request ) {
    	
    	try {
			
    		//模拟答题正确的分值
    		String nums =  request.getParameter("nums");
    		Map<String, String> map = new HashMap<String, String>();
    		
    		String userId =  getUsername();
    		if(StringUtils.isEmpty(userId)) {
    			return new RespJson("-1", "用户不能为空");
    		}
    		String type =  request.getParameter("type");
    		if(StringUtils.isEmpty(type)) {
    			return new RespJson("-1", "积分类型不能为空");
    		}
    		String apptype =  request.getParameter("apptype");
    		map.put("apptype", apptype);
    		map.put("integralType", type);
    		map.put("userId", userId);
    		map.put("nums", nums);
    		
    		int result = lawAppIntegralInfoService.insertIntegral(map);
    		if (result == -1) {
    			return new RespJson("-1", "积分添加失败！");
    		}
		} catch (Exception e) {
			e.getMessage();
			return new RespJson("-1", "系统错误,请联系管理员！");
		}
        return new RespJson("0", "积分添加成功");
    }
    
    
    @ResponseBody
    @RequestMapping("/qryJfpm")
    public Object qryJfpm(HttpServletRequest request) {
    	
    	List<Map<String, Object>> pageList =  null;
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
    		int curPage = Integer.parseInt(cur);
    		String userId =  getUsername();
    		if(!StringUtils.isEmpty(userId)) {
    			map.put("userId", userId);
    			pageList = lawAppIntegralInfoService.qryJfpmPagesByPro(map);
    			map = new HashMap<String, Object>();
    			if(pageList != null && pageList.size()>0) {
    				map = pageList.get(0);
    			}
    			return map;
    		}else {
    			//获取省的积分排名
    			pageList = lawAppIntegralInfoService.qryJfpmPagesByPro(map);
    		}
    		
		} catch (Exception e) {
			e.getMessage();
		}
        return pageList;
    }

    
    
    @ResponseBody
    @RequestMapping("/qryJfpmPages")
    public Object qryJfpmPages(HttpServletRequest request) {
    	
    	List<Map<String, Object>> pageList =  null;
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		String userId =  getUsername();
    		if(StringUtils.isEmpty(userId)) {
    			return new RespJson("-1", "未上送警号");
    		}
    		String type =  request.getParameter("type");
    		if(StringUtils.isEmpty(type)) {
    			return new RespJson("-1", "未上送参数类型");
    		}
    		LawAppPoliceInfo  appPoliceInfo = lawAppPoliceInfoService.selectById(userId);
    		if(appPoliceInfo != null && !StringUtils.isEmpty(appPoliceInfo.getUnitAddress())) {
    			String [] utitIds = appPoliceInfo.getUnitAddress().split("-");
    			if(utitIds.length > 3) {
    				 //1区(单位)2市
    				if(type.equals("1")) {
    					map.put("unitId", utitIds[2]);
    				}else if(type.equals("2")) {
    					map.put("unitId", utitIds[1]);
    				}else if(type.equals("3")) {
    					map.put("unitId", utitIds[0]);
    				}else {
        				map.put("unitId", utitIds[0]);
        			}
    				
    			}else if(utitIds.length > 2) {
    				if(type.equals("1")) {
    					map.put("unitId", utitIds[2]);
    				}else if(type.equals("2")) {
    					map.put("unitId", utitIds[1]);
    				}else if(type.equals("3")) {
    					map.put("unitId", utitIds[0]);
    				}else {
        				map.put("unitId", utitIds[0]);
        			}
    			}else if(utitIds.length > 1) {
    				if(type.equals("1")) {
    					map.put("unitId", utitIds[1]);
    				}else if(type.equals("2")) {
    					map.put("unitId", utitIds[1]);
    				}else if(type.equals("3")) {
    					map.put("unitId", utitIds[0]);
    				}else {
        				map.put("unitId", utitIds[0]);
        			}
    			}else{
    				map.put("unitId", utitIds[0]);
    			}
    		}else {
    			return pageList;
    		}
    		
    		String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
    		int curPage = Integer.parseInt(cur);
			pageList = lawAppIntegralInfoService.qryJfpmPages(map);
    		
		} catch (Exception e) {
			e.getMessage();
		}
        return pageList;
    }
    
    
    
    @ResponseBody
    @RequestMapping("/qryJfByuserId")
    public Object qryJfByuserId(HttpServletRequest request) {
    	
    	List<Map<String, Object>> appIntegralInfos = null;
    	try {
    		LawAppIntegralInfo appIntegralInfo = new LawAppIntegralInfo();
    		String userId =  getUsername();
    		if(!StringUtils.isEmpty(userId)) {
    			appIntegralInfo.setUserId(userId);
    		}
    		appIntegralInfos = lawAppIntegralInfoService.qryJfByuserId(appIntegralInfo);
    		
		} catch (Exception e) {
			e.getMessage();
		}
        return appIntegralInfos;
    }
    
}
