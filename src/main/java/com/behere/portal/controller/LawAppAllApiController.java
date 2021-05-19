package com.behere.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.controller.BaseController;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.service.LawAppAllApiServer;
import com.behere.portal.service.LawAppIntegralInfoService;
import com.behere.system.util.RespJson;
import com.behere.system.util.UUIDUtil;

@Controller
@RequestMapping("/app")
public class LawAppAllApiController extends BaseController{
	
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LawAppAllApiServer appAllApiServer ;
	
	 @Autowired
	 private LawAppIntegralInfoService lawAppIntegralInfoService;
	
	/**
	 * 
	 * 	获取音频信息
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryAudio")
	public Object  qryAudioPages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			if(log.isDebugEnabled()) {
				log.debug("音频信息请求............");
			}
			String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
			int curPage = Integer.parseInt(cur);
			Map<String, Object> map = new HashMap<String, Object>();
			pageList = appAllApiServer.qryAudioPages(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("音频信息异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	
	
	
	/**
	 * 
	 * 	获取 头条通知
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryNotice")
	public Object  qryNoticePages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("头条通知信息请求............");
			}
			String ntype = request.getParameter("ntype");
			if(!StringUtils.isEmpty(ntype)) {
				map.put("noticeType", ntype);
			}
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)) {
				map.put("id", id);
			}
			String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
			int curPage = Integer.parseInt(cur);
			pageList = appAllApiServer.qryNoticePages(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("头条通知信息异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	
	/**
	 * 
	 * 	校验用户当天是否已做闯关答题
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryCheckPass")
	public Object  qryCheckPass(HttpServletRequest request) {
		RespJson retJson = new RespJson();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			
			//闯关答题（10题一关【（随机）一天一次】）
			String userId = getUsername();
			if(StringUtils.isEmpty(userId)) {
				retJson.setRespCode("-1");
				retJson.setRespDesc("请上传参数");
				return retJson;
			}
			map.put("userId", userId);
			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
			String nowDate = sim.format(new Date());
			map.put("startTime", nowDate+" 00:00:00");
			map.put("endTime", nowDate+" 23:59:59");
			if(log.isDebugEnabled()) {
				log.debug("检查闯关答题请求............"+JSONObject.toJSONString(map));
			}
			List<Map<String, Object>>  list =  appAllApiServer.qryCheckPass(map); 
			if(list != null && list.size()>0) {
				retJson.setRespCode("-1");
				retJson.setRespDesc("已参加");
			}else {
				retJson.setRespCode("0");
				retJson.setRespDesc("未参加");
			}
		} catch (Exception e) {
			e.getMessage();
			retJson.setRespCode("-1");
			retJson.setRespDesc("查询失败");
		}
		return retJson;
	}
	
	
	/**
	 * 
	 * 	保存闯关答题信息
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveExamPass")
	public Object  saveExamPass(HttpServletRequest request) {
		RespJson retJson = new RespJson();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("闯关答题信息请求............");
			}
			String userId = getUsername();
			if(!StringUtils.isEmpty(userId)) {
				map.put("userId", userId);
			}else {
				retJson.setRespCode("-1");
				retJson.setRespDesc("未上送警号");
				return retJson;
			}
			String level = request.getParameter("level");
			if(!StringUtils.isEmpty(level)) {
				map.put("level", Integer.parseInt(level));
			}else {
				map.put("level", 0);
			}
			map.put("id",UUIDUtil.randomUUID());
			SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowDate = sim.format(new Date());
			map.put("examTime",nowDate);
			//'类型(6在线考试，7执法资格，8专项考试,11闯关答题)'
			map.put("scqType","11");
			int flag  = appAllApiServer.saveExamPass(map);
			if(flag > 0) {
				LawAppIntegralInfo record = new LawAppIntegralInfo();;
	    		record.setIntegralType("11");
	    		record.setUserId(userId);
	    		record.setIntegralNumber(Integer.parseInt(level));
	    		lawAppIntegralInfoService.insertIntegral(record);
				
				retJson.setRespCode("0");
				retJson.setRespDesc("操作成功");
			}else {
				retJson.setRespCode("-1");
				retJson.setRespDesc("操作失败");
			}
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("闯关答题信息异常............"+e.getMessage());
			}
		}
		return retJson;
	}
	
	
	
	/**
	 * 
	 * 	获取闯关最高记录和闯关次数
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryCgDt")
	public Object  qryCgDt(HttpServletRequest request) {
		RespJson retJson = new RespJson();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(log.isDebugEnabled()) {
				log.debug("闯关答题请求............");
			}
			String userId = getUsername();
			if(!StringUtils.isEmpty(userId)) {
				map.put("userId", userId);
			}else {
				retJson.setRespCode("-1");
				retJson.setRespDesc("未上送警号");
				return retJson;
			}
			map = appAllApiServer.qryCgDt(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("闯关答题异常............"+e.getMessage());
			}
		}
		return map;
	}
	
	
	
	
	/**
	 * 	 
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryImggeText")
	public Object  qryImggeTextPages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("图文信息请求............");
			}
			// '0-法律解读,1-执法制度,2-练兵手册,3-资格考试,4-练兵手册'
			String type = request.getParameter("type");
			if(!StringUtils.isEmpty(type)) {
				map.put("lType", type);
			}
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)) {
				map.put("id", id);
			}
			String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
			int curPage = Integer.parseInt(cur);
			pageList = appAllApiServer.qryImggeTextPages(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("图文信息异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	/**
	 * 
	 * 	按照Id获取图文信息
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryImggeTextById")
	public Object  qryImggeText(HttpServletRequest request) {
		
		Map<String, Object> map2 =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("按照Id获取图文信息............");
			}
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)) {
				map.put("id", id);
			}
			map2 = appAllApiServer.qryImggeTextById(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("按照Id获取图文信息............"+e.getMessage());
			}
		}
		return map2;
	}
	
	
	
	
	/**
	 * 	 精品课件
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryJpkj")
	public Object  qryLawappKjPages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("精品课件请求............");
			}
			String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
			int curPage = Integer.parseInt(cur);
			pageList = appAllApiServer.qryLawappKjPages(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("精品课件异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	/**
	 * 	 精品课件详情
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryJpkjImg")
	public Object  qryLawappKjImgPages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)) {
				map.put("kid", id);
			}else {
				return new RespJson("-1","未上送参数");
			}
			if(log.isDebugEnabled()) {
				log.debug("精品课件请求............" +JSONObject.toJSONString(map));
			}
			pageList = appAllApiServer.qryJpkjImg(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("精品课件异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	
	
	/**
	 * 	 精品课件
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryNewLaw")
	public Object  qryNewLawPages(HttpServletRequest request) {
		
		List<Map<String, Object>> pageList =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(log.isDebugEnabled()) {
				log.debug("新法速递请求............");
			}
			String lawType = request.getParameter("lawType");
			//lawType 1表示法律法规，0表示法律解释
			if(!StringUtils.isEmpty(lawType)) {
				map.put("lawType", lawType);
			}
			String lawName = request.getParameter("lawName");
			if(!StringUtils.isEmpty(lawName)) {
				map.put("lawName", lawName);
			}
			String cur = request.getParameter("curPage");
			if(StringUtils.isEmpty(cur)) {
				cur = "1";
			}
			int curPage = Integer.parseInt(cur);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("新法速递异常............"+e.getMessage());
			}
		}
		return pageList;
	}
	
	/**
	 * 	新法速递详情
	 * @param request
	 * @param pageQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/qryNewLawById")
	public Object  qryNewLawById(HttpServletRequest request) {
		
		Map<String, Object> retMap =  null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String id = request.getParameter("id");
			if(!StringUtils.isEmpty(id)) {
				map.put("id", id);
			}else {
				return new RespJson("-1","未上送参数");
			}
			if(log.isDebugEnabled()) {
				log.debug("新法速递请求............" +JSONObject.toJSONString(map));
			}
			retMap = appAllApiServer.qryNewLawById(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("新法速递异常............"+e.getMessage());
			}
		}
		return retMap;
	}
	
}
