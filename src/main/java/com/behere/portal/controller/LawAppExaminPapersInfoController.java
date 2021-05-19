package com.behere.portal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.controller.BaseController;
import com.behere.portal.domain.LawAppExaminPapersInfo;
import com.behere.portal.domain.LawAppPapersExaminessInfo;
import com.behere.portal.service.LawAppExaminPapersInfoService;
import com.behere.portal.service.LawAppPapersExaminessInfoService;
import com.behere.system.util.RespJson;

/**
 * @ClassName：GetExamTopicController
 * @Auther: 京京
 * @Date: 2020/3/22 02:00
 * @Description:得到试卷
 */
@Controller
@RequestMapping(value = "exam")
public class LawAppExaminPapersInfoController extends BaseController{
	
	 private Logger log = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private LawAppExaminPapersInfoService papersInfoService;
    
    @Autowired
    private LawAppPapersExaminessInfoService lawAppPapersExaminessInfoService;
    

    /**
     * 提取单选题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectScq")
    public List<LawAppExaminPapersInfo> selectScq(String scqType, String quesName, Integer scqNum) {
        return papersInfoService.selectScq(scqType, quesName, scqNum);
    }

    /**
     * 提取多选题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAqNum")
    public List<LawAppExaminPapersInfo> selectAqNum(String scqType, String quesName, Integer aqNum) {
        return papersInfoService.selectAqNum(scqType, quesName, aqNum);
    }

    /**
     * 提取判断题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectJqNum")
    public List<LawAppExaminPapersInfo> selectJqNum(String scqType, String quesName, Integer jqNum) {
        return papersInfoService.selectJqNum(scqType, quesName, jqNum);
    }

    /**
     * 提取问答题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAskName")
    public List<LawAppExaminPapersInfo> selectAskName(String scqType, String quesName, Integer askNum) {
        return papersInfoService.selectAskName(scqType, quesName, askNum);
    }

    /**
     * 提取填空题
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectFbName")
    public List<LawAppExaminPapersInfo> selectFbName(String scqType, String quesName, Integer fbNum) {
        return papersInfoService.selectFbName(scqType, quesName, fbNum);
    }

    /**
     * 查询试卷列表
     *
     * @param scqType
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectPapersInfo")
    public Object selectPapersInfo(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	String scqType =  request.getParameter("scqType");
    	String paperId =  request.getParameter("paperId");
    	if(StringUtils.isEmpty(scqType)) {
    		return new RespJson("-1","未上送请求参数");
    	}
    	String userId =  getUsername();
    	if(!StringUtils.isEmpty(userId)) {
    		map.put("userId", userId);
    	}
    	map.put("paperId", paperId);
    	map.put("scqType", scqType);
    	
        return papersInfoService.selectPapersInfo(map);
    }


    /********************************** taven.tao start  *************************/

    /**
     * 创建试卷信息
     *
     * @param request
     * @param examinPapersInfo
     * @return
     */
    @RequestMapping("/paperSave")
    @ResponseBody
    public RespJson saveExamPapers(HttpServletRequest request, LawAppExaminPapersInfo examinPapersInfo) {
        RespJson retJson = new RespJson();
        try {
            if (StringUtils.isEmpty(examinPapersInfo.getPaperId())) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("未选择【试卷】");
                return retJson;
            }
           
            examinPapersInfo = papersInfoService.getPapersInfo(examinPapersInfo);
    		if(examinPapersInfo != null) {
            	Date date=new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String dateString = examinPapersInfo.getStartTime();
            	if(!StringUtils.isEmpty(dateString)) {
            		Date date2 = sdf.parse(dateString);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已开始，不允许操作");
            			return retJson;
            		}
            	}
            	String dateEnd = examinPapersInfo.getEndTime();
            	if(!StringUtils.isEmpty(dateEnd)) {
            		Date date2 = sdf.parse(dateEnd);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已结束，不允许操作");
            			return retJson;
            		}
            	}
            }
            String queids = request.getParameter("queIds");
            if (StringUtils.isEmpty(queids)) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("请选择试卷");
                return retJson;
            }
            
            //TODO
            String queidsV [] = queids.split(",");
            StringBuilder queSId0 = new StringBuilder();
            StringBuilder queSId1 = new StringBuilder();
            StringBuilder queSId2 = new StringBuilder();
            StringBuilder queSId3 = new StringBuilder();
            StringBuilder queSId4 = new StringBuilder();
            for(int i = 0; i< queidsV.length;i++) {
            	String type [] = queidsV[i].split("-");
            	if(type[1].equals("0")) {
            		queSId0.append(type[0]).append(",");
            	}else if(type[1].equals("1")) {
            		queSId1.append(type[0]).append(",");
            	}else if(type[1].equals("2")) {
            		queSId2.append(type[0]).append(",");
            	}else if(type[1].equals("3")) {
            		queSId3.append(type[0]).append(",");
            	}else if(type[1].equals("4")) {
            		queSId4.append(type[0]).append(",");
            	}
            }
            
			/*
			 * int scqCount = 0; int cAqCount = 0; int cFbCount = 0; int askCount = 0; int
			 * cJqCount = 0;
			 */

            JSONObject reqPar = new JSONObject();
            //0选择试题的编号ID,以英文【,】隔开
            String queSId = queSId0.toString();
            if (!StringUtils.isEmpty(queSId)) {
            	if(queSId.lastIndexOf(",") > 0) {
            		queSId = queSId.substring(0, queSId.length()-1);
            	}
                reqPar.put("queSId", queSId);
                
				/*
				 * String queSIds[] = queSId.split(","); //只计算每道题的数量
				 * examinPapersInfo.setScqNum(queSIds.length); if
				 * (examinPapersInfo.getScqSScores() != null && examinPapersInfo.getScqSScores()
				 * > 0) { scqCount = examinPapersInfo.getScqSScores() * queSIds.length; }
				 */
            }
            //1多选择题
            String quesSId = queSId1.toString();
            if (!StringUtils.isEmpty(quesSId)) {
            	if(quesSId.lastIndexOf(",") > 0) {
            		quesSId = quesSId.substring(0, quesSId.length()-1);
            	}
                reqPar.put("quesSId", quesSId);
				/*
				 * String quesSIds[] = quesSId.split(","); //只计算每道题的数量
				 * examinPapersInfo.setAqNum(quesSIds.length); if
				 * (examinPapersInfo.getAqSScores() != null && examinPapersInfo.getAqSScores() >
				 * 0) { cAqCount = examinPapersInfo.getAqSScores() * quesSIds.length; }
				 */
            }
            //4填空题
            String queFId = queSId4.toString();
            if (!StringUtils.isEmpty(queFId)) {
            	if(queFId.lastIndexOf(",") > 0) {
            		queFId = queFId.substring(0, queFId.length()-1);
            	}
                reqPar.put("queFId", queFId);
				/*
				 * String queFIds[] = queFId.split(","); //只计算每道题的数量
				 * examinPapersInfo.setFbNum(queFIds.length); if
				 * (examinPapersInfo.getFbSScores() != null && examinPapersInfo.getFbSScores() >
				 * 0) { cAqCount = examinPapersInfo.getFbSScores() * queFIds.length; }
				 */
            }
            //3问答题
            String queEId = queSId3.toString();
            if (!StringUtils.isEmpty(queEId)) {
            	if(queEId.lastIndexOf(",") > 0) {
            		queEId = queEId.substring(0, queEId.length()-1);
            	}
                reqPar.put("queEId", queEId);
				/*
				 * String queEIds[] = queEId.split(","); //只计算每道题的数量
				 * examinPapersInfo.setAskNum(queEIds.length); if
				 * (examinPapersInfo.getAskScores() != null && examinPapersInfo.getAskScores() >
				 * 0) { cAqCount = examinPapersInfo.getAskScores() * queEIds.length; }
				 */
            }

            //2判断题
            String queJId = queSId2.toString();
            if (!StringUtils.isEmpty(queJId)) {
            	if(queJId.lastIndexOf(",") > 0) {
            		queJId = queJId.substring(0, queJId.length()-1);
            	}
                reqPar.put("queJId", queJId);
				/*
				 * String queEIds[] = queEId.split(","); //只计算每道题的数量
				 * examinPapersInfo.setJqNum(queEIds.length); if
				 * (examinPapersInfo.getJqSScores() != null && examinPapersInfo.getJqSScores() >
				 * 0) { cAqCount = examinPapersInfo.getJqSScores() * queEIds.length; }
				 */
            }
            //总分数
			/*
			 * int tScores = scqCount + cAqCount + cFbCount + askCount + cJqCount;
			 * examinPapersInfo.setPaperTScores(tScores);
			 */
            
            if(log.isDebugEnabled()) {
            	log.debug("创建试卷请求参数"+JSONObject.toJSONString(examinPapersInfo)+"==="+JSONObject.toJSONString(reqPar));
            }
            JSONObject object = papersInfoService.saveExamPapers(examinPapersInfo, reqPar);
            retJson.setRespCode(object.getString("code"));
            retJson.setRespDesc(object.getString("desc"));
        } catch (Exception e) {
            e.getMessage();
            if(log.isDebugEnabled()) {
            	log.debug("创建试卷失败:"+e.getMessage());
            }
            retJson.setRespCode("-1");
            retJson.setRespDesc("创建试卷失败！");
            return retJson;
        }
        return retJson;
    }
    
    
    /**
     *  随机模拟答题
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/qryQuesByrand")
    public Object qryQuesByrand(HttpServletRequest request) {
    	RespJson retJson = new RespJson();
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	try {
    		String num =  request.getParameter("num");
    		if(StringUtils.isEmpty(num)) {
    			num = "20";
    		}
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("num", num);
    		list = papersInfoService.selectQuesByRand(map);
		} catch (Exception e) {
			e.getMessage();
			retJson.setRespCode("-1");
            retJson.setRespDesc("获取随机题型异常,请联系管理员！");
            return retJson;
		}
        return list;
    }
    
    
    
    @RequestMapping("/qryExaminPapersPages")
    @ResponseBody
    public List<Map<String, Object>> pagesByExaminPapersInfo (HttpServletRequest request, LawAppExaminPapersInfo papersExaminessInfo) {
        List<Map<String, Object>> obj = null;
        try {
        	String curPages = request.getParameter("curPage");
        	if(StringUtils.isEmpty(curPages)) {
        		curPages = "1";
        	}
        	
			/*
			 * int curPage = Integer.parseInt(curPages); if(curPage > 1) {
			 * page.setStart((curPage-1) * page.getLength()); }else { page.setStart(0); }
			 */
    		
//            obj = papersInfoService.pagesByExaminPapersInfo(page, papersExaminessInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    
    @ResponseBody
    @RequestMapping("/saveExaminPapers")
    public RespJson saveExaminPapersInfo(HttpServletRequest request, LawAppExaminPapersInfo examinPapersInfo) {
        RespJson retJson = new RespJson();
        try {
            if (StringUtils.isEmpty(examinPapersInfo.getPaperName())) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("未上送参数【试卷名称】");
                return retJson;
            }
            if(log.isDebugEnabled()) {
            	log.debug("增加试卷请求参数。。。。。"+ JSONObject.toJSONString(examinPapersInfo));
            }
            LawAppExaminPapersInfo req = new LawAppExaminPapersInfo();
            req.setPaperName(examinPapersInfo.getPaperName());
            LawAppExaminPapersInfo record = papersInfoService.getPapersInfo(req);
            if (record == null) {
//            	examinPapersInfo.setPaperId(IDGenerator.getId());
            	int len = papersInfoService.saveExamPapersInfo(examinPapersInfo);
                if (len > 0) {
                    retJson.setRespCode("0");
                    retJson.setRespDesc("试卷增加成功");
                } else {
                    retJson.setRespCode("-1");
                    retJson.setRespDesc("试卷增加失败");
                }
            } else {
            	retJson.setRespCode("-1");
            	retJson.setRespDesc("试卷名称已存在");
            }
        } catch (Exception e) {
            e.getMessage();
            retJson.setRespCode("-1");
            retJson.setRespDesc("操作失败！");
            return retJson;
        }
        return retJson;
    }
    
    @ResponseBody
    @RequestMapping("/delExaminPapers")
    public RespJson delExaminPapersInfo(HttpServletRequest request, LawAppExaminPapersInfo examinPapersInfo) {
        RespJson retJson = new RespJson();
        try {
            if (StringUtils.isEmpty(examinPapersInfo.getPaperId())) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("未上送参数【试卷名称】");
                return retJson;
            }
            if(log.isDebugEnabled()) {
            	log.debug("删除试卷请求参数。。。。。"+ JSONObject.toJSONString(examinPapersInfo));
            }
            //检查试卷信息是否
            LawAppExaminPapersInfo  examinPapersInfo2 =  papersInfoService.getPapersInfo(examinPapersInfo);
            if(examinPapersInfo2 != null) {
            	LawAppPapersExaminessInfo appPapersExaminessInfo = new LawAppPapersExaminessInfo();
            	appPapersExaminessInfo.setPaperId(examinPapersInfo.getPaperId());
            	List<LawAppPapersExaminessInfo> record2 = lawAppPapersExaminessInfoService.testRanking(appPapersExaminessInfo);
            	if (record2 != null && record2.size()>0) {
            		retJson.setRespCode("-1");
            		retJson.setRespDesc("试卷已存在试题信息,不允许此操作");
            	} else {
            		int len = papersInfoService.delExamPapersInfo(examinPapersInfo);
            		if (len > 0) {
            			retJson.setRespCode("0");
            			retJson.setRespDesc("试卷删除成功");
            		} else {
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("试卷删除失败");
            		}
            	}
            } else {
        		retJson.setRespCode("-1");
        		retJson.setRespDesc("试卷不存在,不允许此操作");
        	}
            

        } catch (Exception e) {
            e.getMessage();
            retJson.setRespCode("-1");
            retJson.setRespDesc("操作失败！");
            return retJson;
        }
        return retJson;
    }
    
    
    @ResponseBody
    @RequestMapping("/modifyExaminPapers")
    public RespJson modifyExaminPapersInfo(HttpServletRequest request, LawAppExaminPapersInfo examinPapersInfo) {
        RespJson retJson = new RespJson();
        try {
            if (StringUtils.isEmpty(examinPapersInfo.getPaperId())) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("未上送参数【试卷名称】");
                return retJson;
            }
            if(log.isDebugEnabled()) {
            	log.debug("修改试卷请求参数。。。。。"+ JSONObject.toJSONString(examinPapersInfo));
            }
            //检查试卷信息是否
            LawAppPapersExaminessInfo appPapersExaminessInfo = new LawAppPapersExaminessInfo();
            appPapersExaminessInfo.setPaperId(examinPapersInfo.getPaperId());
            List<LawAppPapersExaminessInfo> record2 = lawAppPapersExaminessInfoService.testRanking(appPapersExaminessInfo);
            if (record2 != null && record2.size()>0) {
            	retJson.setRespCode("-1");
            	retJson.setRespDesc("试卷已存在试题,不允许此操作");
            } else {
            	int len = papersInfoService.updateByPrimaryKey(examinPapersInfo);
                if (len > 0) {
                    retJson.setRespCode("0");
                    retJson.setRespDesc("试卷更新成功");
                } else {
                    retJson.setRespCode("-1");
                    retJson.setRespDesc("试卷更新失败");
                }
            }

        } catch (Exception e) {
            e.getMessage();
            retJson.setRespCode("-1");
            retJson.setRespDesc("操作失败！");
            return retJson;
        }
        return retJson;
    }
    
    
    
    @ResponseBody
    @RequestMapping("/qryExaminPapers")
    public Object getExaminPapersInfo(HttpServletRequest request, LawAppExaminPapersInfo examinPapersInfo) {
        RespJson retJson = new RespJson();
        try {
            if (StringUtils.isEmpty(examinPapersInfo.getPaperId())) {
                retJson.setRespCode("-1");
                retJson.setRespDesc("未上送参数【试卷名称】");
                return retJson;
            }
            if(log.isDebugEnabled()) {
            	log.debug("根据id获取试卷信息请求参数。。。。。"+ JSONObject.toJSONString(examinPapersInfo));
            }
            examinPapersInfo = papersInfoService.getPapersInfo(examinPapersInfo);
            String xq = request.getParameter("xq");
            if(StringUtils.isNotBlank(xq) &&  xq.equals("999")) {
            	 return examinPapersInfo;
            }
            if(examinPapersInfo != null) {
            	Date date=new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	
            	String dateEnd = examinPapersInfo.getEndTime();
            	if(!StringUtils.isEmpty(dateEnd)) {
            		Date date2 = sdf.parse(dateEnd);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已结束，不允许操作");
            			return retJson;
            		}
            	}
            	String dateString = examinPapersInfo.getStartTime();
            	if(!StringUtils.isEmpty(dateString)) {
            		Date date2 = sdf.parse(dateString);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已开始，不允许操作");
            			return retJson;
            		}
            	}
            }
        } catch (Exception e) {
            e.getMessage();
            retJson.setRespCode("-1");
            retJson.setRespDesc("试卷信息获取异常！");
            return retJson;
        }
        return examinPapersInfo;
    }
    
    
    @RequestMapping("/qryExaminPapersList")
    @ResponseBody
    public Object lipstByExaminPapersInfo (HttpServletRequest request,  LawAppExaminPapersInfo papersExaminessInfo) {
        List<Map<String, Object>> obj = null;
        try {
            obj = papersInfoService.listByExaminPapersInfo(papersExaminessInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    
    @RequestMapping("/qryAllByPaperId")
    @ResponseBody
    public Object getPaperByPaperId (HttpServletRequest request) {
        List<Map<String, Object>> obj = null;
        try {
        	Map<String, Object> map = new HashMap<String, Object>();
			String paperId = request.getParameter("paperId");
			if(StringUtils.isEmpty(paperId)) {
				return new RespJson("-1", "未上送试卷号");
			}
			map.put("paperId", paperId);
            obj = papersInfoService.qryAllByPaperId(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
    
    @ResponseBody
    @RequestMapping("/delQuesrelById")
    public RespJson delPapersQuesrelById(HttpServletRequest request) {
    	RespJson retJson = new RespJson();
    	try {
    		
    		String id = request.getParameter("id");
    		if (StringUtils.isEmpty(id)) {
    			retJson.setRespCode("-1");
    			retJson.setRespDesc("不允许操作");
    			return retJson;
    		}
    		String pid = request.getParameter("pid");
    		if (StringUtils.isEmpty(pid)) {
    			retJson.setRespCode("-1");
    			retJson.setRespDesc("不允许操作");
    			return retJson;
    		}
    		if(log.isDebugEnabled()) {
    			log.debug("删除试卷中的试题请求参数。。。。。"+ id);
    		}
    		LawAppExaminPapersInfo examinPapersInfo = new LawAppExaminPapersInfo();
    		examinPapersInfo.setPaperId(pid);
    		examinPapersInfo = papersInfoService.getPapersInfo(examinPapersInfo);
    		if(examinPapersInfo != null) {
            	Date date=new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	String dateEnd = examinPapersInfo.getEndTime();
            	if(!StringUtils.isEmpty(dateEnd)) {
            		Date date2 = sdf.parse(dateEnd);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已结束，不允许操作");
            			return retJson;
            		}
            	}
            	String dateString = examinPapersInfo.getStartTime();
            	if(!StringUtils.isEmpty(dateString)) {
            		Date date2 = sdf.parse(dateString);
            		if(date.getTime() > date2.getTime()){
            			retJson.setRespCode("-1");
            			retJson.setRespDesc("考试时间已开始，不允许操作");
            			return retJson;
            		}
            	}
            }
    		//检查试卷信息是否
    		LawAppPapersExaminessInfo appPapersExaminessInfo = new LawAppPapersExaminessInfo();
            appPapersExaminessInfo.setPaperId(pid);
    		List<LawAppPapersExaminessInfo> record2 = lawAppPapersExaminessInfoService.testRanking(appPapersExaminessInfo);
    		if (record2 != null && record2.size()>0) {
    			int len = papersInfoService.delPapersQuesrelById(id);
    			if (len > 0) {
    				retJson.setRespCode("0");
    				retJson.setRespDesc("试题删除成功");
    			} else {
    				retJson.setRespCode("-1");
    				retJson.setRespDesc("试题删除失败");
    			}
    		} else {
    			retJson.setRespCode("-1");
    			retJson.setRespDesc("试卷不存在试题,不允许此操作");
    		}
    	} catch (Exception e) {
    		e.getMessage();
    		retJson.setRespCode("-1");
    		retJson.setRespDesc("操作失败！");
    		return retJson;
    	}
    	return retJson;
    }
    
    
    
    
    
    @ResponseBody
    @RequestMapping("/qryRoundQuesAll")
    public Object qryRoundQuesAll(HttpServletRequest request) {
    	List<Map<String, Object>> lists =  new ArrayList<Map<String,Object>>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			String paperId = request.getParameter("paperId");
			if(StringUtils.isEmpty(paperId)) {
				return new RespJson("-1", "未上送试卷号");
			}
			String scqType = request.getParameter("scqType");
			if(StringUtils.isEmpty(scqType)) {
				return new RespJson("-1", "未上送参数【scqType】");
			}
			String userId = getUsername();
			Map<String, Object> map = new HashMap<String, Object>(); 
			map.put("userId", userId); 
			map.put("paperId", paperId); 
			map.put("scqType", scqType);
			if(scqType.equals("6") || scqType.equals("12")) {
				LawAppExaminPapersInfo examinPapersInfo  = new LawAppExaminPapersInfo();
				examinPapersInfo.setPaperId(paperId);
				examinPapersInfo = papersInfoService.getPapersInfo(examinPapersInfo);
				if(examinPapersInfo != null) {
					Date date=new Date();
					String dateEnd = examinPapersInfo.getEndTime();
					if(!StringUtils.isEmpty(dateEnd)) {
						Date date2 = sdf.parse(dateEnd);
						if(date.getTime() > date2.getTime()){
							return new RespJson("-1","考试时间已结束，不允许操作");
						}
					}
					String dateString = examinPapersInfo.getStartTime();
					if(!StringUtils.isEmpty(dateString)) {
						Date date2 = sdf.parse(dateString);
						if(date.getTime() < date2.getTime()){
							return new RespJson("-1","考试时间未开始，不允许操作");
						}
					}
					//单选题
					if(examinPapersInfo.getScqNum() != null && examinPapersInfo.getScqNum()>0) {
						Map<String, Object> reqMap = new HashMap<String, Object>();
						reqMap.put("type", "0");
						reqMap.put("num", examinPapersInfo.getScqNum());
						List<Map<String, Object>> list = papersInfoService.qryRoundQuesAll(reqMap);
						lists.addAll(list);
					}
					//多选题
					if(examinPapersInfo.getAqNum() != null && examinPapersInfo.getAqNum()>0) {
						Map<String, Object> reqMap = new HashMap<String, Object>();
						reqMap.put("type", "1");
						reqMap.put("num", examinPapersInfo.getAqNum());
						List<Map<String, Object>> list = papersInfoService.qryRoundQuesAll(reqMap);
						lists.addAll(list);
					}
					//判断题
					if(examinPapersInfo.getJqNum() != null && examinPapersInfo.getJqNum()>0) {
						Map<String, Object> reqMap = new HashMap<String, Object>();
						reqMap.put("type", "2");
						reqMap.put("num", examinPapersInfo.getJqNum());
						List<Map<String, Object>> list = papersInfoService.qryRoundQuesAll(reqMap);
						lists.addAll(list);
					}
					
					//填空题
					if(examinPapersInfo.getFbNum() != null && examinPapersInfo.getFbNum()>0) {
						Map<String, Object> reqMap = new HashMap<String, Object>();
						reqMap.put("type", "4");
						reqMap.put("num", examinPapersInfo.getScqNum());
						List<Map<String, Object>> list = papersInfoService.qryRoundQuesAll(reqMap);
						lists.addAll(list);
					}
					//问答题
					if(examinPapersInfo.getAskNum() != null && examinPapersInfo.getAskNum()>0) {
						Map<String, Object> reqMap = new HashMap<String, Object>();
						reqMap.put("type", "3");
						reqMap.put("num", examinPapersInfo.getAskNum());
						List<Map<String, Object>> list = papersInfoService.qryRoundQuesAll(reqMap);
						lists.addAll(list);
					}
				}
			}else if(scqType.equals("7")) {
				
			}else if(scqType.equals("8")) {
				
			}
			
		} catch (Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("获取考试信息异常："+e.getMessage());
			}
			e.getMessage();
		}
        return lists;
    }
    
    
    
    @ResponseBody
    @RequestMapping("/saveRoundQues")
    public Object saveRoundQues(HttpServletRequest request) {
    	 int len = 0;
    	try {
    		String paperId = request.getParameter("paperId");
    		if(StringUtils.isEmpty(paperId)) {
    			return new RespJson("-1", "未上送试卷号");
    		}
    		String scqType = request.getParameter("scqType");
    		if(StringUtils.isEmpty(scqType)) {
    			return new RespJson("-1", "未上送参数【scqType】");
    		}
    		String userId = getUsername();
    		if(StringUtils.isEmpty(userId)) {
    			return new RespJson("-1", "未上送警号");
    		}
    		String quesType = request.getParameter("quesType");
    		if(StringUtils.isEmpty(quesType)) {
    			return new RespJson("-1", "未上送试题类型");
    		}
    		String quesId = request.getParameter("quesId");
    		if(StringUtils.isEmpty(quesId)) {
    			return new RespJson("-1", "未上送试题编号");
    		}
    		if(scqType.equals("6") || scqType.equals("12")) {
    			Map<String, Object> map = new HashMap<String, Object>();
    			map.put("paperId", paperId);
    			map.put("quesId", quesId);
    			map.put("quesType", quesType);
//    			map.put("id", IDGenerator.getId());
    			map.put("createTime", new Date());
    			map.put("modifyTime", new Date());
    			map.put("createUser", userId);
    			map.put("modifyUser", userId);
    			map.put("userId", userId); 
    			if(log.isDebugEnabled()) {
    				log.debug("app请求增加试题信息"+JSONObject.toJSONString(map));
    			}
    			len = papersInfoService.savePapersQues(map);
    		}
            if(len > 0) {
            	return  new RespJson("0", "操作成功");
            }else {
            	return  new RespJson("-1", "操作失败!");
            }
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
    }


    /********************************** taven.tao end  *************************/

    
    public static void main(String[] args) {
    	Date date=new Date();

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	String dateString="2028-09-23 23:00:45";

    	Date date2;
		try {
			date2 = sdf.parse(dateString);
			if(date.getTime()<date2.getTime()){
				System.out.println("当前日期小于"+sdf.format(date));
			}else{
				System.out.println(sdf.format(date2));
			}
		} catch (ParseException e) {
			
		}

	}
    
    /**
     * 考试列表
     * @return
     */
    @RequestMapping("/examListIndex")
	public ModelAndView examListIndex() {
		ModelAndView view = new ModelAndView();
//		BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//    	if(userEntity == null) {
//    		view.setViewName("/portal/lawappLogin");
//    		return view;
//    	}
//    	view.addObject("userId", userEntity.getUserId());
//		view.addObject("deptId", userEntity.getDeptId());
		view.setViewName("/exam/examinationList");
		view.addObject("menuId", "13");
		return view;
	}
    
    /**
     * 在线考试
     * @return
     */
	@RequestMapping("/examIndex")
	public ModelAndView examIndex() {
		ModelAndView view = new ModelAndView();
//		BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//    	if(userEntity == null) {
//    		view.setViewName("/portal/lawappLogin");
//    		return view;
//    	}
//    	view.addObject("userId", userEntity.getUserId());
//		view.addObject("deptId", userEntity.getDeptId());
		view.setViewName("/exam/examination");
		return view;
	}
	
	/**
	 * 考试结果
	 * @return
	 */
	@RequestMapping("/examMsgIndex")
	public ModelAndView examsIndex() {
		ModelAndView view = new ModelAndView();
//		BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//    	if(userEntity == null) {
//    		view.setViewName("/portal/lawappLogin");
//    		return view;
//    	}
//    	view.addObject("userId", userEntity.getUserId());
//		view.addObject("deptId", userEntity.getDeptId());
		view.setViewName("/exam/examinationMsg");
		view.addObject("menuId", "13");
		return view;
	}

}
