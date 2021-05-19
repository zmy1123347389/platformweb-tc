package com.behere.portal.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.behere.common.controller.BaseController;
import com.behere.portal.domain.LawAppPapersQuesrelInfo;
import com.behere.portal.domain.LawAppPoliceExam;
import com.behere.portal.service.LawAppPapersQuesrelInfoService;
import com.behere.system.util.RespJson;


/**
 * @ClassName：LawAppPapersQuesrelInfoController
 * @Auther: 京京
 * @Date: 2020/3/22 13:41
 * @Description:
 */
@Controller
@RequestMapping(value = "exam")
public class LawAppPapersQuesrelInfoController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private LawAppPapersQuesrelInfoService lawAppPapersQuesrelInfoService;
    
    

    /**
     * 查询试卷题目
     *
     * @param paperId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getQuesAlls")
    public Map<String, List<LawAppPapersQuesrelInfo>> getQuesAll(String paperId, String scqType) {
    	String userId = getUsername();
        return lawAppPapersQuesrelInfoService.getAll(userId, paperId, scqType);
    }
    
    
    @ResponseBody
    @RequestMapping("/getQuesAll")
    public Object getQuesAll(HttpServletRequest request) {
    	List<Map<String, Object>> list =  null;
    	try {
			String userId = getUsername();
			if(StringUtils.isEmpty(userId)) {
				return new RespJson("-1", "未上送警号");
			}
			String paperId = request.getParameter("paperId");
			if(StringUtils.isEmpty(paperId)) {
				return new RespJson("-1", "未上送试卷号");
			}
			String scqType = request.getParameter("scqType");
			if(StringUtils.isEmpty(scqType)) {
				return new RespJson("-1", "未上送试卷类型");
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("paperId", paperId);
			map.put("scqType", scqType);
			list = lawAppPapersQuesrelInfoService.getAllList(map);
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("获取考试信息异常："+e.getMessage());
			}
			e.getMessage();
		}

        return list;
    }
    
    
    /********************************** taven.tao pages  *************************/
    
    
    @RequestMapping(value = "/papersQuesrel")
    public ModelAndView papersQuesrelIndex() {
    	ModelAndView view = new ModelAndView();
//    	BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//    	if(userEntity == null) {
//    		view.setViewName("/portal/lawappLogin");
//    		return view;
//    	}
//		view.addObject("deptId", userEntity.getDeptId());
    	view.setViewName("/exam/papersquesrel");
    	view.addObject("menuId", "2");
        return view;
    }
    
    /**
     * 
     * @param request
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/getQuesAllPage")
	public Object getQuesAllPage(HttpServletRequest request){
		List<LawAppPoliceExam> obj = null;
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			String type = request.getParameter("type");
			if(!StringUtils.isEmpty(type)) {
				map.put("type", type);
			}
			obj = lawAppPapersQuesrelInfoService.qryPaperAll(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

}

