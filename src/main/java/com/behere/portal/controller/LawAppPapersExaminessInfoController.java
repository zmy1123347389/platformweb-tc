package com.behere.portal.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.controller.BaseController;
import com.behere.portal.domain.LawAppExaminPapersInfo;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.domain.LawAppPapersExaminessInfo;
import com.behere.portal.service.LawAppExaminPapersInfoService;
import com.behere.portal.service.LawAppIntegralInfoService;
import com.behere.portal.service.LawAppPapersExaminessInfoService;
import com.behere.portal.service.LawappPoliceExamService;
import com.behere.system.util.RespJson;
import com.behere.system.util.UUIDUtil;

/**
 * @param <V>
 * @ClassName：LawAppPapersExaminessInfoController
 * @Auther: 京京
 * @Date: 2020/3/23 18:28
 * @Description:
 */
@Controller
@RequestMapping(value = "exam")
public class LawAppPapersExaminessInfoController extends BaseController{
    @Autowired
    private LawAppPapersExaminessInfoService lawAppPapersExaminessInfoService;
    @Autowired
    private LawAppExaminPapersInfoService lawAppExaminPapersInfoService;
    @Autowired
    private LawAppIntegralInfoService lawAppIntegralInfoService;
    
    @Autowired
   	private LawappPoliceExamService policeExamI; 
    
    
    /**
     * 添加考试结果并返回结果
     *
     * @param paperId
     * @param paperTimed
     * @param scqNum
     * @param mcqNum
     * @param jqNum
     * @return
     * @throws ParseException 
     */
    @ResponseBody
    @RequestMapping("/getResult")
    public Object getResult(String paperId, String paperTimed, Integer scqNum, Integer mcqNum, Integer jqNum, String scqType) throws ParseException {
    	String userId = getUsername();
    	int scq = 0;
        int aq = 0;
        int jq = 0;
        LawAppExaminPapersInfo papersInfo = new LawAppExaminPapersInfo();
        papersInfo.setPaperId(paperId);
        papersInfo = lawAppExaminPapersInfoService.getPapersInfo(papersInfo);
        LawAppPapersExaminessInfo lawAppPapersExaminessInfo = new LawAppPapersExaminessInfo();
        LawAppIntegralInfo lawAppIntegralInfo = new LawAppIntegralInfo();
//        lawAppPapersExaminessInfo.setExamsId(IDGenerator.getId());
        String examsId = lawAppPapersExaminessInfo.getExamsId();
        //获取题型对应成绩
        Integer scqSScores = papersInfo.getScqSScores();
        Integer aqSScores = papersInfo.getAqSScores();
        Integer jqSScores = papersInfo.getJqSScores();
        
        if (scqNum == 0 || scqSScores == 0) {
            lawAppPapersExaminessInfo.setScqRScores(0);
            lawAppPapersExaminessInfo.setIntegralNumber(0);
        } else {
            //单选题分数
            scq = scqSScores * scqNum;
            lawAppPapersExaminessInfo.setScqRScores(scq);
        }
        if (mcqNum == 0 || aqSScores == 0) {
            lawAppPapersExaminessInfo.setMcqRScores(0);
            lawAppPapersExaminessInfo.setIntegralNumber(0);
        } else {
            //多选题分数
            aq = aqSScores * mcqNum;
            lawAppPapersExaminessInfo.setMcqRScores(aq);
        }
        if (jqNum == 0 || jqSScores == 0) {
            lawAppPapersExaminessInfo.setJqRScores(0);
            lawAppPapersExaminessInfo.setIntegralNumber(0);
        } else {
            //判断题分数
            jq = jqSScores * jqNum;
            lawAppPapersExaminessInfo.setJqRScores(jq);
        }
        //总得分
       int paperRScores;
        if(jqNum == 0 & mcqNum == 0 & scqNum == 0 ){
            paperRScores =0;
        }
        paperRScores=scq + aq + jq;
        lawAppPapersExaminessInfo.setPaperRScores(paperRScores);
        int scqRScores = lawAppPapersExaminessInfo.getPaperRScores();
        // '6在线考试，7模拟答题，8每周一考'
        //添加积分
        if (("6").equals(scqType)) {
            //在线考试成绩90-100：可获得5分
            //在线考试成绩60-89：可获得2分
            if ((89 < scqRScores) & (scqRScores < 101)) {
                lawAppPapersExaminessInfo.setIntegralNumber(5);
                lawAppPapersExaminessInfo.setState("1");
            } else if ((59 < scqRScores) & (scqRScores < 90)) {
                lawAppPapersExaminessInfo.setIntegralNumber(2);
                lawAppPapersExaminessInfo.setState("1");
            } else if (scqRScores < 60) {
                lawAppPapersExaminessInfo.setIntegralNumber(0);
                lawAppPapersExaminessInfo.setState("1");
            }
        } else if (("8").equals(scqType)) {
        
            lawAppPapersExaminessInfo.setState("1");
            lawAppPapersExaminessInfo.setIntegralNumber(scqRScores);
        }
        lawAppPapersExaminessInfo.setUserId(userId);
        lawAppPapersExaminessInfo.setPaperTimed(paperTimed);
        lawAppPapersExaminessInfo.setPaperId(paperId);
        int result = lawAppPapersExaminessInfoService.saveExamResult(lawAppPapersExaminessInfo);
       if (result>0){
            String userIds = lawAppPapersExaminessInfo.getUserId();
	        if (("6").equals(scqType)) {
	            //在线考试成绩90-100：可获得20分
	            //在线考试成绩60-89：可获得5分
	            if ((89 < scqRScores) & (scqRScores < 101)) {
	                lawAppIntegralInfo.setIntegralNumber(20);
	            } else if ((59 < scqRScores) & (scqRScores < 90)) {
	                lawAppIntegralInfo.setIntegralNumber(5);
	            }
	            lawAppIntegralInfo.setIntegralType("在线考试");
	            lawAppIntegralInfo.setUserId(userIds);
	        }  else if (("8").equals(scqType)) {
	            lawAppIntegralInfo.setIntegralType("每周一考");
	            lawAppIntegralInfo.setIntegralNumber(scqRScores);
	            lawAppIntegralInfo.setUserId(userIds);
	        }
            lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
        }
       LawAppPapersExaminessInfo record = new LawAppPapersExaminessInfo();
       record.setExamsId(examsId);
       return lawAppPapersExaminessInfoService.getResult(record);
    }
    
    @ResponseBody
    @RequestMapping("/savePaper")
    public Object savePaperResult(HttpServletRequest request,LawAppPapersExaminessInfo lawAppPapersExaminessInfo ){
    	//String userId, String paperId, String paperTimed, Integer scqNum, Integer mcqNum, Integer jqNum, String scqType
    	String userId = getUsername();
    	lawAppPapersExaminessInfo.setUserId(userId);
    	try {
    		if(StringUtils.isEmpty(lawAppPapersExaminessInfo.getScqType())) {
    			return new RespJson("-1", "考试类型未上送");
    		}
    		if(StringUtils.isEmpty(lawAppPapersExaminessInfo.getUserId())) {
    			return new RespJson("-1", "警号未上送");
    		}
    		if(StringUtils.isEmpty(lawAppPapersExaminessInfo.getPaperId())) {
    			return new RespJson("-1", "试卷未上送");
    		}
    		if(StringUtils.isEmpty(lawAppPapersExaminessInfo.getPaperTimed())) {
    			return new RespJson("-1", "考试用时未上送");
    		}
    		LawAppExaminPapersInfo papersInfo = new LawAppExaminPapersInfo();
    	    papersInfo.setPaperId(lawAppPapersExaminessInfo.getPaperId());
    	    papersInfo = lawAppExaminPapersInfoService.getPapersInfo(papersInfo);
    	    String scqNum = request.getParameter("scqNum");
    	    if(StringUtils.isEmpty(scqNum)) {
    	    	scqNum = "0";
    	    }
    	    int scpCount = 0;
    	    if(Integer.parseInt(scqNum) > 0) {
    	    	//单选题分数
    	    	scpCount  = papersInfo.getScqSScores() * Integer.parseInt(scqNum);
    	    }
    	    lawAppPapersExaminessInfo.setScqRScores(scpCount);
    	    
    	    String mcqNum = request.getParameter("mcqNum");
    	    if(StringUtils.isEmpty(mcqNum)) {
    	    	mcqNum = "0";
    	    }
    	    int mcpCount = 0;
    	    if(Integer.parseInt(mcqNum) > 0) {
    	    	//单选题分数
    	    	mcpCount  = papersInfo.getAqSScores() * Integer.parseInt(mcqNum);
    	    }
            lawAppPapersExaminessInfo.setMcqRScores(mcpCount);
            
            String jqNum = request.getParameter("jqNum");
    	    if(StringUtils.isEmpty(jqNum)) {
    	    	jqNum = "0";
    	    }
    	    int jpCount = 0;
    	    if(Integer.parseInt(jqNum) > 0) {
    	    	//单选题分数
    	    	jpCount  = papersInfo.getJqSScores() * Integer.parseInt(jqNum);
    	    }
    	    //答正确题量
    	    int zqtl = Integer.parseInt(scqNum)+Integer.parseInt(mcqNum)+Integer.parseInt(jqNum);
    	    lawAppPapersExaminessInfo.setZqtl(zqtl);
    	    //总题量
    	    int cnum = papersInfo.getScqNum()+papersInfo.getJqNum()+papersInfo.getAqNum();
    	    lawAppPapersExaminessInfo.setCounttl(cnum);
            lawAppPapersExaminessInfo.setJqRScores(jpCount);
            String examsId = UUIDUtil.randomUUID();
            lawAppPapersExaminessInfo.setExamsId(examsId);
            
            int result = lawAppPapersExaminessInfoService.saveExamResultByPaper(lawAppPapersExaminessInfo);
            if(result < 0) {
            	return new RespJson("-1", "操作失败!");
            }
            
            if(lawAppPapersExaminessInfo.getScqType().equals("6") || lawAppPapersExaminessInfo.getScqType().equals("12")) {
            	//
            	String scqPaper = request.getParameter("scqPaper");
            	String acqPaper = request.getParameter("acqPaper");
            	String jcqPaper = request.getParameter("jcqPaper");
            	JSONObject reqJson = new JSONObject();
            	//单选
            	reqJson.put("scqPaper", scqPaper);
            	//多选
            	reqJson.put("acqPaper", acqPaper);
            	//判断
            	reqJson.put("jcqPaper", jcqPaper);
            	
            	reqJson.put("scqType", lawAppPapersExaminessInfo.getScqType());
            	reqJson.put("userId", lawAppPapersExaminessInfo.getUserId());
            	reqJson.put("paperId", lawAppPapersExaminessInfo.getPaperId());
            	//增加考试流水
            	result = lawAppPapersExaminessInfoService.saveExamResultByList(reqJson);
            }
            LawAppPapersExaminessInfo record = new LawAppPapersExaminessInfo();
            record.setExamsId(examsId);
            return lawAppPapersExaminessInfoService.getResult(record);
		} catch (Exception e) {
			e.getMessage();
			return new RespJson("-1", "考试成绩失败");
		}
    
    }

    /**
     * 考试排名
     *
     * @param paperId
     * @return
     */
    @ResponseBody
    @RequestMapping("/testRanking")
    public List<LawAppPapersExaminessInfo> testRanking(LawAppPapersExaminessInfo examinessInfo) {
        return lawAppPapersExaminessInfoService.testRanking(examinessInfo);
    }
    
    /**
     * 考试结果/验证是否已经考试
     *
     * @param paperId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectExamnation")
    public Object selectExamnation(String paperId) {
    	String userId= getUsername();
    	JSONObject reqJson = new JSONObject();
    	LawAppPapersExaminessInfo record = new LawAppPapersExaminessInfo();
    	record.setUserId(userId);
    	record.setPaperId(paperId);
    	reqJson.put("papersExaminessInfo", lawAppPapersExaminessInfoService.getResult(record));
    	return reqJson;
    }
    
    
    
    /**
     * 	本周一答校验
     *
     * @param paperId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkWeekByUserId")
    public RespJson checkWeekByUserId(LawAppPapersExaminessInfo  record) {
    	RespJson retJson = new RespJson();
    	try {
    		//每周一答，检查用户本周是否已做答 scqType 8：每周一答 7：模拟答题 6：在线考试
        	if(StringUtils.isEmpty(record.getUserId())) {
        		return new RespJson("-1", "未上送警号信息");
        	}
        	if(StringUtils.isEmpty(record.getPaperId())) {
        		return new RespJson("-1", "未上送试卷信息");
        	}
        	//先检查试卷的有效性
        	LawAppExaminPapersInfo papersInfo = new LawAppExaminPapersInfo();
    	    papersInfo.setPaperId(record.getPaperId());
    	    papersInfo = lawAppExaminPapersInfoService.getPapersInfo(papersInfo);
        	if(record.getScqType().equals("8")) {
        		
        		/*
        		 *  2020528每周一答不做时间限制，显示已做答或未作答
        		Calendar cld = Calendar.getInstance(Locale.CHINA);
         	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd ");
        		cld.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//周一
        		String aa  = df.format(cld.getTime())+" 00:00:00";
        		record.setCreatedTime(df.parse(aa));
        	    if(papersInfo != null ) {
            		Date edate = df.parse(papersInfo.getEndTime());
            		Date ndate = new Date();
            		boolean flag = edate.after(ndate);
            		if(!flag) {
            			return new RespJson("-1", "答题时间已结束!");
            		}
        	    }else {
        	    	return new RespJson("-1", "不允许操作!");
        	    }
        		*/
        	}else if(record.getScqType().equals("7")) {
        		
        	}else if(record.getScqType().equals("6") || record.getScqType().equals("12")) {
         	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         	   if(papersInfo != null ) {
	           		Date edate = df.parse(papersInfo.getEndTime());
	           		Date ndate = new Date();
	           		boolean flag = edate.after(ndate);
	           		if(!flag) {
	           			return new RespJson("-1", "考试时间已结束!");
	           		}
	       	    }else {
	       	    	return new RespJson("-1", "不允许操作!");
	       	    }
        		//当考试验证码为空时，再去验证是否允许考试
        	    if(StringUtils.isEmpty(papersInfo.getAuthCode())) {
        	    	Map<String, Object> map = new HashMap<String, Object>();
        	    	map.put("userId", record.getUserId());
        	    	map.put("paperId", record.getPaperId());
//        	    	List<Map<String, Object>> lists = policeExamI.qryByExamRole(map);
//        	    	if(lists.isEmpty() || lists.size()<= 0 ) {
//        	    		LawAppPoliceExam appPoliceExam = new LawAppPoliceExam();
//        	    		appPoliceExam.setUserId(record.getUserId());
//        	    		appPoliceExam = policeExamI.selectById(appPoliceExam);
//        	    		if(appPoliceExam != null ) {
//        	    			Map<String, Object> record2 = new HashMap<String, Object>();
//        	    			record2.put("deptId", appPoliceExam.getDeptId());
//        	    			List<Map<String, Object>>  list2 =  policeExamI.getRoleExam(record2);
//        	    			if(list2 == null || list2.size()<0) {
//        	    				return new RespJson("-1", "请添加参考人员信息");
//        	    			}
//        	    		}
//        	    	}
        	    }
    			
        	}
        	record = lawAppPapersExaminessInfoService.getResult(record);
        	if(record != null) {
        		return new RespJson("-1", "已做答!");
        	}else {
        		retJson.setRespCode("0");
        		retJson.setRespDesc("未做答");
        	} 
		} catch (Exception e) {
			e.getMessage();
		}
        return retJson;
    }
    
    /**
     * 	
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryScoresCount")
    public Object qryScoresCount(HttpServletRequest request) {
    	
    	List<Map<String, Object>> list = null;
    	try {
    		list = lawAppPapersExaminessInfoService.qryScoresCounte();
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    
    /**
     * 	
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryScoresCountForPC")
    public Object qryScoresCountForPC(HttpServletRequest request) {
    	
    	List<Map<String, Object>> list = null;
    	try {
    		list = lawAppPapersExaminessInfoService.qryScoresCounte();
    		int flag = 0;
    		for (Map<String, Object> map : list) {
    			long value = (long) map.get("value");
    			flag +=  value;
			}
    		if(flag <= 0) {
    			list = new ArrayList<Map<String,Object>>();
    		}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    /**
     * 	统计根据试题和当前机构移动端
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryAllCityPercentageForMoble")
    public Object qryAllCityPercentageForMoble(HttpServletRequest request) {
    	
    	List<Object> list = new ArrayList<Object>();
    	try {
    		List<Map<String, Object>> qryUnitInfo = lawAppPapersExaminessInfoService.qryUnitInfo();
    		for (Map<String, Object> unitInfoMap : qryUnitInfo) {
    			Map<String,Object> hashMap = new HashMap<String, Object>();
    			
    			String parentId = (String) unitInfoMap.get("unitId");
    			String unitName = (String) unitInfoMap.get("unitName");
    			
    			Map<String, Object> percentageForMoble = lawAppPapersExaminessInfoService.qryAllCityPercentageForMoble(parentId);
    			BigDecimal excellentValue = (BigDecimal) percentageForMoble.get("excellentValue");
    			BigDecimal passValue = (BigDecimal) percentageForMoble.get("passValue");
    			BigDecimal failValue = (BigDecimal) percentageForMoble.get("failValue");
    			List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
    			Map<String, Object> excellentMap = new HashMap<String, Object>();
    			excellentMap.put("name", "优秀");
    			excellentMap.put("excellentValue",excellentValue == null ? "0" : excellentValue.toString());
    			arrayList.add(excellentMap);
    			Map<String, Object> passMap = new HashMap<String, Object>();
    			passMap.put("name", "及格");
    			passMap.put("passValue", passValue == null ? "0"  : passValue.toString());
    			arrayList.add(passMap);
    			Map<String, Object> failMap = new HashMap<String, Object>();
    			failMap.put("name", "不及格");
    			failMap.put("failValue", failValue == null ? "0"  : failValue.toString());
    			arrayList.add(failMap);
    			
    			hashMap.put("city", unitName.replace("公安局", ""));
    			hashMap.put("source", arrayList);
    			list.add(hashMap);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    /**
     * 	统计根据试题和当前机构PC
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryAllCityPercentageForPC")
    public Object qryAllCityPercentageForPC(HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	List<Object> list = new ArrayList<Object>();
    	try {
    		List<Map<String, Object>> qryUnitInfo = lawAppPapersExaminessInfoService.qryUnitInfo();
    		List<Object> excellentList = new ArrayList<>();
    		List<Object> passList = new ArrayList<>();
    		List<Object> failList = new ArrayList<>();
    		List<Object> failValueList = new ArrayList<>();
    		
    		for (Map<String, Object> unitInfoMap : qryUnitInfo) {
    			//Map<String,Object> hashMap = new HashMap<String, Object>();
    			
    			String parentId = (String) unitInfoMap.get("unitId");
    			//String unitName = (String) unitInfoMap.get("unitName");
    			
    			Map<String, Object> percentageForMoble = lawAppPapersExaminessInfoService.qryAllCityPercentageForPC(parentId);
    			BigDecimal excellentValue = (BigDecimal) percentageForMoble.get("excellentValue");
    			BigDecimal passValue = (BigDecimal) percentageForMoble.get("passValue");
    			BigDecimal failValue = (BigDecimal) percentageForMoble.get("failValue");
    			BigDecimal avgSource = (BigDecimal) percentageForMoble.get("avgSource");
    			
    			excellentList.add(excellentValue == null?0:excellentValue);
    			passList.add(passValue == null?0:passValue);
    			failList.add(failValue == null?0:failValue);
    			failValueList.add(avgSource == null?0:avgSource);
    		}
    		map.put("excellentArray",failValueList);
    		map.put("passArray",passList);
    		map.put("failArray",failList);
    		map.put("failValueArray",failValueList);
    		list.add(map);
    	} catch (Exception e) {
    		e.getMessage();
    	}
    	return map;
    	
    }
    /**
     * 	统计根据试题和当前机构城市PC
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryAllCityPercentageForCityPC")
    public Object qryAllCityPercentageForCityPC(HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	List<Object> list = new ArrayList<Object>();
    	try {
    		List<Map<String, Object>> qryUnitInfo = lawAppPapersExaminessInfoService.qryUnitInfo();
    		List<Object> excellentList = new ArrayList<>();
    		List<Object> passList = new ArrayList<>();
    		List<Object> failList = new ArrayList<>();
    		List<Object> failValueList = new ArrayList<>();
    		
    		for (Map<String, Object> unitInfoMap : qryUnitInfo) {
    			//Map<String,Object> hashMap = new HashMap<String, Object>();
    			
    			String parentId = (String) unitInfoMap.get("unitId");
    			//String unitName = (String) unitInfoMap.get("unitName");
    			
    			Map<String, Object> percentageForMoble = lawAppPapersExaminessInfoService.qryAllCityPercentageForPC(parentId);
    			BigDecimal excellentValue = (BigDecimal) percentageForMoble.get("excellentValue");
    			BigDecimal passValue = (BigDecimal) percentageForMoble.get("passValue");
    			BigDecimal failValue = (BigDecimal) percentageForMoble.get("failValue");
    			BigDecimal avgSource = (BigDecimal) percentageForMoble.get("avgSource");
    			
    			excellentList.add(excellentValue == null?0:excellentValue);
    			passList.add(passValue == null?0:passValue);
    			failList.add(failValue == null?0:failValue);
    			failValueList.add(avgSource == null?0:avgSource);
			}
    		map.put("excellentArray",failValueList);
    		map.put("passArray",passList);
    		map.put("failArray",failList);
    		map.put("failValueArray",failValueList);
    		list.add(map);
		} catch (Exception e) {
			e.getMessage();
		}
		return map;
    	
    }
    
    /**
     * 	
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryScoresCounteAll")
    public Object qryScoresCounteAll(HttpServletRequest request) {
    	
    	List<Map<String, Object>> list = null;
    	try {
    		list = lawAppPapersExaminessInfoService.qryScoresCounteAll();
    		int flag = 0;
    		for (Map<String, Object> map : list) {
    			long value = (long) map.get("value");
    			flag +=  value;
			}
    		if(flag <= 0) {
    			list = new ArrayList<Map<String,Object>>();
    		}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    
    /**
     * 	统计根据试题和当前机构
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryProvinceScoresCount")
    public Object qryProvinceScoresCount(HttpServletRequest request,String unitId,String paperId) {
    	
    	List<Map<String, Object>> list = null;
    	try {
    		list = lawAppPapersExaminessInfoService.qryProvinceScoresCount(unitId,paperId);
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    /**
     * 	统计
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryUnitIdByParentId")
    public Object qryUnitIdByParentId(HttpServletRequest request,String parentId) {
    	List<Map<String, Object>> list = null;
    	try {
    		list = lawAppPapersExaminessInfoService.qryUnitIdByParentId(parentId);
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    
    /**
     * 	统计 每个试卷的试题错误
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryWrongCount")
    public Object qryWrongCount(HttpServletRequest request) {
    	List<Map<String, Object>> list = null;
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		list = lawAppPapersExaminessInfoService.qryWrongCount(map);
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    }
    
    /**
     * 	统计 每个人的优秀率
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryExcellentRate")
    public Object qryExcellentRate(HttpServletRequest request) {
    	List<Map<String, Object>> list = null;
    	try {
    		Map<String, Object> map = new HashMap<String, Object>();
    		list = lawAppPapersExaminessInfoService.qryExcellentRate(map);
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    }
    
    
    
    /**
     * 	统计根据试题按照市区统计
     *
     * @param 
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryAllCityCount")
    public Object qryAllCityCount(HttpServletRequest request) {
    	Map<String,Object> map = new HashMap<>();
    	List<Object> list = new ArrayList<Object>();
    	try {
    		List<Map<String, Object>> qryUnitInfo = lawAppPapersExaminessInfoService.qryUnitInfo();
    		List<Object> excellentList = new ArrayList<>();
    		List<Object> userCountList = new ArrayList<>();
    		
    		for (Map<String, Object> unitInfoMap : qryUnitInfo) {
    			String parentId = (String) unitInfoMap.get("unitId");
    			//统计当前市以及下属单位
    			parentId = parentId.substring(0,4)+"%";
    			Map<String, Object>  map2 = new HashMap<String, Object>();
    			map2.put("parentId", parentId);
    			Map<String, Object> percentageForMoble = lawAppPapersExaminessInfoService.qryAllCityCount(map2);
    			String excellentValue = percentageForMoble.get("excellentValue")== null?"0":percentageForMoble.get("excellentValue").toString();
    			String userCount =  percentageForMoble.get("userCount")== null?"0":percentageForMoble.get("userCount").toString();
    			excellentList.add(excellentValue );
    			userCountList.add(userCount );
    		}
    		map.put("excellentArray",excellentList);
    		map.put("userCountArray",userCountList);
    		list.add(map);
    	} catch (Exception e) {
    		e.getMessage();
    	}
    	return map;
    }
    
    
    @ResponseBody
    @RequestMapping("/qryAllCityCountApp")
    public Object qryAllAppCityCount(HttpServletRequest request) {
    	
    	List<Object> list = new ArrayList<Object>();
    	try {
    		List<Map<String, Object>> qryUnitInfo = lawAppPapersExaminessInfoService.qryUnitInfo();
    		for (Map<String, Object> unitInfoMap : qryUnitInfo) {
    			Map<String,Object> hashMap = new HashMap<String, Object>();
    			
    			String parentId = (String) unitInfoMap.get("unitId");
    			String unitName = (String) unitInfoMap.get("unitName");
    			parentId = parentId.substring(0,4)+"%";
    			Map<String, Object>  map2 = new HashMap<String, Object>();
    			map2.put("parentId", parentId);
    			
    			Map<String, Object> percentageForMoble = lawAppPapersExaminessInfoService.qryAllCityCount(map2);
    			String excellentValue = percentageForMoble.get("excellentValue")== null?"0":percentageForMoble.get("excellentValue").toString();
    			String userCount =  percentageForMoble.get("userCount")== null?"0":percentageForMoble.get("userCount").toString();
    			List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
    			Map<String, Object> excellentMap = new HashMap<String, Object>();
    			excellentMap.put("excellentValue",excellentValue);
    			excellentMap.put("userCountValue", userCount);
    			
    			arrayList.add(excellentMap);
    			hashMap.put("city", unitName.replace("公安局", ""));
    			hashMap.put("source", arrayList);
    			list.add(hashMap);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
    	
    }
    
    
    
	/**
	 * 
	 * 	获取个人考试信息
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/usersExamInfo")
	@ResponseBody
	public Object usersExamPapaerPages(HttpServletRequest request){
		List<Map<String, Object>> obj = null;
		try{
			Map<String, Object> map = new  HashMap<String, Object>();
			String userId = getUsername();
			if(StringUtils.isEmpty(userId)) {
				return obj ;
			}
			map.put("userId", userId);
			obj = lawAppPapersExaminessInfoService.usersExamPapaerPages(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
    
    

    
    

}
