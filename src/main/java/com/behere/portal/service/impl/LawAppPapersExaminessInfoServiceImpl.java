package com.behere.portal.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.behere.portal.dao.LawAppPapersExaminessInfoDao;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.domain.LawAppPapersExaminessInfo;
import com.behere.portal.service.LawAppIntegralInfoService;
import com.behere.portal.service.LawAppPapersExaminessInfoService;
import com.behere.system.util.UUIDUtil;

/**
 * @ClassName：LawAppPapersExaminessInfoServiceImpl
 * @Auther: 京京
 * @Date: 2020/3/23 18:05
 * @Description:
 */
@Service
public class LawAppPapersExaminessInfoServiceImpl implements LawAppPapersExaminessInfoService {
	
	 private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private LawAppPapersExaminessInfoDao lawAppPapersExaminessInfoMapper;
    
    @Autowired
    private LawAppIntegralInfoService lawAppIntegralInfoService;

    /**
     * 保存考试结果
     * @param record
     * @return
     */
    @Override
    public int saveExamResult(LawAppPapersExaminessInfo record) {
        return lawAppPapersExaminessInfoMapper.saveExamResult(record);
    }

    /**
     * 添加考试结果并返回结果
     *
     * @param examsId
     * @return
     */
    @Override
    public LawAppPapersExaminessInfo getResult(LawAppPapersExaminessInfo record) {

        //查询考试结果
        return lawAppPapersExaminessInfoMapper.getResult(record);
    }
    /**
     * 考试排名
     *
     * @param paperId
     * @return
     */
    @Override
    public List<LawAppPapersExaminessInfo> testRanking(LawAppPapersExaminessInfo examinessInfo) {
        return lawAppPapersExaminessInfoMapper.testRanking(examinessInfo);
    }

	@Override
	public int saveExamResultByPaper(LawAppPapersExaminessInfo lawAppPapersExaminessInfo) {
		
		int result =  0;
		try {
			if(log.isDebugEnabled()) {
				log.debug("增加考试请求参数："+JSONObject.toJSONString(lawAppPapersExaminessInfo));
			}
			lawAppPapersExaminessInfo.setCreatedBy(lawAppPapersExaminessInfo.getUserId());
			lawAppPapersExaminessInfo.setCreatedTime(new Date());
			int  paperRScores = lawAppPapersExaminessInfo.getScqRScores() + lawAppPapersExaminessInfo.getMcqRScores() + lawAppPapersExaminessInfo.getJqRScores();
	        lawAppPapersExaminessInfo.setPaperRScores(paperRScores);
	        result =  lawAppPapersExaminessInfoMapper.saveExamResult(lawAppPapersExaminessInfo);
	        if (result>0){
	        	LawAppIntegralInfo lawAppIntegralInfo = new LawAppIntegralInfo();
	        	lawAppIntegralInfo.setUserId(lawAppPapersExaminessInfo.getUserId());
	        	if (("6").equals(lawAppPapersExaminessInfo.getScqType()) ) {
	        		lawAppIntegralInfo.setIntegralType(lawAppPapersExaminessInfo.getScqType());
	        		if(!StringUtils.isEmpty(lawAppPapersExaminessInfo.getApptype()) && lawAppPapersExaminessInfo.getApptype().equals("tc")) {
	        			/*
						 *	 铜川- 1.在线考试不少于50道题， 并正确率达到60% 并加10积分
						 */
	        			if(lawAppPapersExaminessInfo.getCounttl() >= 50 ) {
	        				BigDecimal b1 = new BigDecimal(60);  
	    					BigDecimal b2 = new BigDecimal(100);  
	    					Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue()*lawAppPapersExaminessInfo.getCounttl();
	    					if(lawAppPapersExaminessInfo.getZqtl() >= rslt.longValue()) {
	        					lawAppIntegralInfo.setIntegralNumber(10);
	        					result += lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
	        				}
	        			}
	        		}else {
	        			//在线考试成绩90-100：可获得20分
	    	            //在线考试成绩60-89：可获得5分
	    	        	if(log.isDebugEnabled()) {
	    					log.debug("增加考试积分："+JSONObject.toJSONString(lawAppIntegralInfo));
	    				}
	        			if ((89 < paperRScores)) {
	        				lawAppIntegralInfo.setIntegralNumber(20);
	        			} else if ((59 < paperRScores) & (paperRScores < 90)) {
	        				lawAppIntegralInfo.setIntegralNumber(5);
	        			}
	        			//大于60添加积分
	        			if(paperRScores >59) {
	        				result += lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
	        			}
	        		}
	        		
	        	} else if(("12").equals(lawAppPapersExaminessInfo.getScqType())) {
	        		/*
					 *	 铜川-2.模拟考试不少于20道题， 并正确率达到60% 并加3积分
					 */
	        		lawAppIntegralInfo.setIntegralType(lawAppPapersExaminessInfo.getScqType());
	        		if(lawAppPapersExaminessInfo.getCounttl() >= 20 ) {
    					BigDecimal b1 = new BigDecimal(60);  
    					BigDecimal b2 = new BigDecimal(100);  
    					Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue()*lawAppPapersExaminessInfo.getCounttl();
    					if(lawAppPapersExaminessInfo.getZqtl() >= rslt.longValue()) {
    						lawAppIntegralInfo.setIntegralNumber(3);
    						result += lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
    					}
    				}
	        	}else if (("8").equals(lawAppPapersExaminessInfo.getScqType())) {
	        		lawAppIntegralInfo.setIntegralType(lawAppPapersExaminessInfo.getScqType());
	        		if(!StringUtils.isEmpty(lawAppPapersExaminessInfo.getApptype()) && lawAppPapersExaminessInfo.getApptype().equals("tc")) {
	        			if(lawAppPapersExaminessInfo.getCounttl() >= 10 ) {
	        				BigDecimal b1 = new BigDecimal(60);  
	    					BigDecimal b2 = new BigDecimal(100);  
	    					Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue()*lawAppPapersExaminessInfo.getCounttl();
	    					if(lawAppPapersExaminessInfo.getZqtl() >= rslt.longValue()) {
	        					lawAppIntegralInfo.setIntegralNumber(4);
	        					result += lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
	        				}
	        			}
	        		}else {
	        			lawAppIntegralInfo.setIntegralNumber(paperRScores);
	        			result += lawAppIntegralInfoService.insertIntegral(lawAppIntegralInfo);
	        		}
	        	}
	        }
			
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("增加考试异常："+e.getMessage());
			}
		}
		
		return result;
	}

	@Override
	public int saveExamResultByList(JSONObject reqJson) {
		
		if(log.isDebugEnabled()) {
			log.debug("增加考试试题流水请求参数："+reqJson);
		}
		List<Map<String, Object>> exams = new ArrayList<Map<String,Object>>();
		String userId = reqJson.getString("userId");
		String paperId = reqJson.getString("paperId");
		//6在线考试，7执法资格，8专项考试'
		String scqType = reqJson.getString("scqType");
		String scqPaper = reqJson.getString("scqPaper");
		//题目类型  0 代表单选题  1 代表多选题  2 判断题  3 问答题  4 填空题
		if(!StringUtils.isEmpty(scqPaper)) {
			String [] scqPapers = scqPaper.split(",");
			for (int i = 0; i < scqPapers.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String [] quesIds = scqPapers[i].split("-");
				map.put("id", UUIDUtil.randomUUID());
				map.put("userId", userId);
				map.put("paperId", paperId);
				map.put("scqType", scqType);
				map.put("quesType", "0");
				map.put("createTime", new Date());
				map.put("quesId", quesIds[0]);
				map.put("isCorrect", quesIds[1]);
				exams.add(map);
			}
		}
		String acqPaper = reqJson.getString("acqPaper");
		if(!StringUtils.isEmpty(acqPaper)) {
			String [] acqPapers = acqPaper.split(",");
			for (int i = 0; i < acqPapers.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String [] quesIds = acqPapers[i].split("-");
				map.put("id", UUIDUtil.randomUUID());
				map.put("userId", userId);
				map.put("paperId", paperId);
				map.put("scqType", scqType);
				map.put("createTime", new Date());
				map.put("quesType", "1");
				map.put("quesId", quesIds[0]);
				map.put("isCorrect", quesIds[1]);
				exams.add(map);
			}
		}
		
		String jcqPaper = reqJson.getString("jcqPaper");
		if(!StringUtils.isEmpty(jcqPaper)) {
			String [] jcqPapers = jcqPaper.split(",");
			for (int i = 0; i < jcqPapers.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				String [] quesIds = jcqPapers[i].split("-");
				map.put("id", UUIDUtil.randomUUID());
				map.put("userId", userId);
				map.put("paperId", paperId);
				map.put("scqType", scqType);
				map.put("createTime", new Date());
				map.put("quesType", "2");
				map.put("quesId", quesIds[0]);
				map.put("isCorrect", quesIds[1]);
				exams.add(map);
			}
		}
		return lawAppPapersExaminessInfoMapper.saveUserExamQues(exams);
	}

	@Override
	public List<Map<String, Object>> qryScoresCounte() {
		return lawAppPapersExaminessInfoMapper.qryScoresCounte();
	}

	@Override
	public List<Map<String, Object>> qryProvinceScoresCount(String unitId,String paperId) {
		return lawAppPapersExaminessInfoMapper.qryProvinceScoresCount(unitId,paperId);
	}

	@Override
	public List<Map<String, Object>> qryUnitIdByParentId(String parentId) {
		return lawAppPapersExaminessInfoMapper.qryUnitIdByParentId(parentId);
	}

	@Override
	public List<Map<String, Object>> qryUnitInfo() {
		return lawAppPapersExaminessInfoMapper.qryUnitInfo();
	}

	@Override
	public Map<String, Object> qryAllCityPercentageForMoble(String parentId) {
		return lawAppPapersExaminessInfoMapper.qryAllCityPercentageForMoble(parentId);
	}
	
	@Override
	public Map<String, Object> qryAllCityPercentageForPC(String parentId) {
		return lawAppPapersExaminessInfoMapper.qryAllCityPercentageForPC(parentId);
	}

	@Override
	public List<Map<String, Object>> qryScoresCounteAll() {
		return lawAppPapersExaminessInfoMapper.qryScoresCounteAll();
	}

	@Override
	public Map<String, Object> qryAllCityPercentageForCityPC(String parentId) {
		return lawAppPapersExaminessInfoMapper.qryAllCityPercentageForCityPC(parentId);
	}

	@Override
	public List<Map<String, Object>> qryWrongCount(Map<String, Object> map) {
		return lawAppPapersExaminessInfoMapper.qryWrongCount(map);
	}

	@Override
	public List<Map<String, Object>> qryExcellentRate(Map<String, Object> map) {
		return lawAppPapersExaminessInfoMapper.qryExcellentRate(map);
	}

	@Override
	public Map<String, Object> qryAllCityCount(Map<String, Object> map) {
		return lawAppPapersExaminessInfoMapper.qryAllCityCount(map);
	}

	@Override
	public List<Map<String, Object>> usersExamPapaerPages(Map<String, Object> map) {
		return lawAppPapersExaminessInfoMapper.usersExamPapaerPages(map);
	}


}
