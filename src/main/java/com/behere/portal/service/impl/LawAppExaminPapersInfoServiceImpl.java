package com.behere.portal.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.behere.portal.dao.LawAppExaminPapersInfoDao;
import com.behere.portal.dao.LawAppPapersQuesrelInfoDao;
import com.behere.portal.domain.LawAppChoiceQuesInfo;
import com.behere.portal.domain.LawAppEssayQuesInfo;
import com.behere.portal.domain.LawAppExaminPapersInfo;
import com.behere.portal.domain.LawAppFillQuesInfo;
import com.behere.portal.domain.LawAppJudgeQuesInfo;
import com.behere.portal.service.LawAppExaminPapersInfoService;

/**
 * @ClassName：GetExamTopicServiceImpl
 * @Auther: 京京
 * @Date: 2020/3/22 01:20
 * @Description:
 */
@Service
public class LawAppExaminPapersInfoServiceImpl implements LawAppExaminPapersInfoService {

    @Autowired
    private LawAppExaminPapersInfoDao lawAppExaminPapersInfoMapper;
    @Autowired
    private LawAppPapersQuesrelInfoDao lawAppPapersQuesrelInfoMapper;

    @Override
    public List<LawAppExaminPapersInfo> selectScq(String scqType, String quesName, Integer scqNum) {
        // scqType'6在线考试，7模拟答题，8每周一考'
        //quesName  试卷名称
        //scqNum=单选题数量
        List<LawAppExaminPapersInfo> list = lawAppExaminPapersInfoMapper.selectScq(scqType, quesName, scqNum);
        ;
        for (LawAppExaminPapersInfo lawAppExaminPapersInfo : list) {
            String paperId = lawAppExaminPapersInfo.getPaperId();
            List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo = lawAppExaminPapersInfo.getLawAppChoiceQuesInfo();
            for (LawAppChoiceQuesInfo newLawAppChoiceQuesInfo : lawAppChoiceQuesInfo) {
                String choId = newLawAppChoiceQuesInfo.getChoId();
                lawAppPapersQuesrelInfoMapper.savePaper(paperId, choId, "管理员");
            }
        }
        return list;
    }

    @Override
    public List<LawAppExaminPapersInfo> selectAqNum(String scqType, String quesName, Integer aqNum) {
        List<LawAppExaminPapersInfo> list = lawAppExaminPapersInfoMapper.selectAqNum(scqType, quesName, aqNum);
        for (LawAppExaminPapersInfo lawAppExaminPapersInfo : list) {
            String paperId = lawAppExaminPapersInfo.getPaperId();
            List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo = lawAppExaminPapersInfo.getLawAppChoiceQuesInfo();
            for (LawAppChoiceQuesInfo newLawAppChoiceQuesInfo : lawAppChoiceQuesInfo) {
                String choId = newLawAppChoiceQuesInfo.getChoId();
                lawAppPapersQuesrelInfoMapper.savePaper(paperId, choId, "管理员");
            }
        }
        return list;
    }

    @Override
    public List<LawAppExaminPapersInfo> selectJqNum(String scqType, String quesName, Integer jqNum) {
        List<LawAppExaminPapersInfo> list = lawAppExaminPapersInfoMapper.selectAqNum(scqType, quesName, jqNum);
        for (LawAppExaminPapersInfo lawAppExaminPapersInfo : list) {
            String paperId = lawAppExaminPapersInfo.getPaperId();
            List<LawAppJudgeQuesInfo> lawAppJudgeQuesInfo = lawAppExaminPapersInfo.getLawAppJudgeQuesInfo();
            for (LawAppJudgeQuesInfo newLawAppJudgeQuesInfo : lawAppJudgeQuesInfo) {
                String JudgeId = newLawAppJudgeQuesInfo.getJudgeId();
                lawAppPapersQuesrelInfoMapper.savePaper(paperId, JudgeId, "管理员");
            }
        }
        return list;
    }

    @Override
    public List<LawAppExaminPapersInfo> selectAskName(String scqType, String quesName, Integer askNum) {
        List<LawAppExaminPapersInfo> list = lawAppExaminPapersInfoMapper.selectAqNum(scqType, quesName, askNum);
        for (LawAppExaminPapersInfo lawAppExaminPapersInfo : list) {
            String paperId = lawAppExaminPapersInfo.getPaperId();
            List<LawAppEssayQuesInfo> lawAppEssayQuesInfo = lawAppExaminPapersInfo.getLawAppEssayQuesInfo();
            for (LawAppEssayQuesInfo newLawAppEssayQuesInfo : lawAppEssayQuesInfo) {
                String EssayId = newLawAppEssayQuesInfo.getEssayId();
                lawAppPapersQuesrelInfoMapper.savePaper(paperId, EssayId, "管理员");
            }
        }
        return list;
    }

    @Override
    public List<LawAppExaminPapersInfo> selectFbName(String scqType, String quesName, Integer fbNum) {
        List<LawAppExaminPapersInfo> list = lawAppExaminPapersInfoMapper.selectAqNum(scqType, quesName, fbNum);
        for (LawAppExaminPapersInfo lawAppExaminPapersInfo : list) {
            String paperId = lawAppExaminPapersInfo.getPaperId();
            List<LawAppFillQuesInfo> lawAppFillQuesInfo = lawAppExaminPapersInfo.getLawAppFillQuesInfo();
            for (LawAppFillQuesInfo newLawAppFillQuesInfo : lawAppFillQuesInfo) {
                String fillId = newLawAppFillQuesInfo.getFillId();
                lawAppPapersQuesrelInfoMapper.savePaper(paperId, fillId, "管理员");
            }
        }
        return list;
    }

    @Override
    public List<LawAppExaminPapersInfo> selectPapersInfo(Map<String, Object> map) {
        return lawAppExaminPapersInfoMapper.selectPapersInfo(map);
    }


    @Override
    public JSONObject saveExamPapers(LawAppExaminPapersInfo examinPapersInfo, JSONObject reqPar) {
        //TODO  获取当前登录人的信息
        JSONObject obj = new JSONObject();
       
        String paperId = examinPapersInfo.getPaperId();
        int len = 0;
        //单选择题
        
        String queSId = reqPar.getString("queSId");
        
        //单选
		StringBuilder scpSB = null; 
		//多选
		StringBuilder aqSB = null;
		//判断
		StringBuilder jqSB = null;
		//问答
		StringBuilder wqSB = null;
		//填空
		StringBuilder tqSB = null;
		//统计
		long tCount = 0;
		//重复
		long fCount = 0;
		//成功
		long suCount  = 0;
        
        Date d = new Date();
        if (!StringUtils.isEmpty(queSId)) {
            String queSIds[] = queSId.split(",");
            scpSB = new StringBuilder("单项选择题:【");
            String quesType = "0";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queSIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", queSIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
//                map.put("createTime", d);
//                map.put("modifyTime", d);
//                if (userEntity != null) {
//                    map.put("createUser", userEntity.getUserId());
//                    map.put("modifyUser", userEntity.getUserId());
//                } else {
//                    map.put("createUser", "admin");
//                    map.put("modifyUser", "admin");
//                }
                if(tCount < examinPapersInfo.getScqNum()) {
                	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
                	tCount =  tCount+1;
                }else {
                	len ++;
                }
            }
            scpSB.append("重复:" + fCount);
            scpSB.append(";成功:" + suCount); 
            scpSB.append(";超出:" + len); 
            scpSB.append("】" ); 
            scpSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //多选择题
        String quesSId = reqPar.getString("quesSId");
        if (!StringUtils.isEmpty(quesSId)) {
            String quesSIds[] = quesSId.split(",");
            aqSB = new StringBuilder("多项选择题:【");
            String quesType = "1";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < quesSIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", quesSIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
//                map.put("createTime", d);
//                map.put("modifyTime", d);
//                if (userEntity != null) {
//                    map.put("createUser", userEntity.getUserId());
//                    map.put("modifyUser", userEntity.getUserId());
//                } else {
//                    map.put("createUser", "admin");
//                }
                if(tCount < examinPapersInfo.getAqNum()) {
                	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
                	tCount =  tCount+1;
                }else {
                	len ++;
                }
            }
            aqSB.append("重复:" + fCount);
            aqSB.append(";成功:" + suCount); 
            aqSB.append(";超出:" + len); 
            aqSB.append("】" ); 
            aqSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //判断题
        String queJId = reqPar.getString("queJId");
        if (!StringUtils.isEmpty(queJId)) {
            String queJIds[] = queJId.split(",");
            jqSB = new StringBuilder("判断题:【");
            String quesType = "2";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queJIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", queJIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
//                map.put("createTime", d);
//                map.put("modifyTime", d);
//                if (userEntity != null) {
//                    map.put("createUser", userEntity.getUserId());
//                    map.put("modifyUser", userEntity.getUserId());
//                } else {
//                    map.put("createUser", "admin");
//                }
	            if(tCount < examinPapersInfo.getJqNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            jqSB.append("重复:" + fCount);
            jqSB.append(";成功:" + suCount); 
            jqSB.append(";超出:" + len); 
            jqSB.append("】" ); 
            jqSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //问答题
        String queEId = reqPar.getString("queEId");
        if (!StringUtils.isEmpty(queEId)) {
            String queEIds[] = queEId.split(",");
            wqSB = new StringBuilder("问答题:【");
            String quesType = "3";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queEIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesType", quesType);
                map.put("quesId", queEIds[i]);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
//                map.put("createTime", d);
//                map.put("modifyTime", d);
//                if (userEntity != null) {
//                    map.put("createUser", userEntity.getUserId());
//                    map.put("modifyUser", userEntity.getUserId());
//                } else {
//                    map.put("createUser", "admin");
//                }
	            if(tCount < examinPapersInfo.getAskNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            wqSB.append("重复:" + fCount);
            wqSB.append(";成功:" + suCount); 
            wqSB.append(";超出:" + len); 
            wqSB.append("】" ); 
	        fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //填空题
        String queFId = reqPar.getString("queFId");
        if (!StringUtils.isEmpty(queFId)) {
            String queFIds[] = queFId.split(",");
            tqSB = new StringBuilder("填空题:【");
            String quesType = "4";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queFIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesType", quesType);
                map.put("quesId", queFIds[i]);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
//                map.put("createTime", d);
//                map.put("modifyTime", d);
//                if (userEntity != null) {
//                    map.put("createUser", userEntity.getUserId());
//                    map.put("modifyUser", userEntity.getUserId());
//                } else {
//                    map.put("createUser", "admin");
//                }
                if(tCount < examinPapersInfo.getFbNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            tqSB.append("重复:" + fCount);
            tqSB.append(";成功:" + suCount); 
            tqSB.append(";超出:" + len); 
            tqSB.append("】" ); 
	        fCount = 0;
	        len = 0;
        }
        StringBuilder cSB = new StringBuilder(); 
        if(scpSB != null) {
        	cSB.append(scpSB);
        }
        if(aqSB != null) {
        	cSB.append(aqSB);
        }
        if(jqSB != null) {
        	cSB.append(jqSB);
        }
        if(wqSB != null) {
        	cSB.append(wqSB);
        }
        if(tqSB != null) {
        	cSB.append(tqSB);
        }
        obj.put("code", "0");
        obj.put("desc", cSB.toString());
        return obj;
    }

	@Override
	public List<Map<String, Object>> selectQuesByRand(Map<String, String> map) {
		int num = Integer.parseInt(map.get("num"));
		if(num == 0) {
			num = 20;
		}
		List<Map<String, Object>>  list = lawAppExaminPapersInfoMapper.selectQuesByRand(num);
		return list;
	}

    /**
     * 根据试卷id获取试卷信息
     * @param paperId
     * @return
     */
    @Override
    public LawAppExaminPapersInfo getPapersInfo(LawAppExaminPapersInfo papersExaminessInfo) {
        return lawAppExaminPapersInfoMapper.getPapersInfo(papersExaminessInfo);
    }

	@Override
	public List<Map<String, Object>> pagesByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo) {
		// TODO Auto-generated method stub
		return lawAppExaminPapersInfoMapper.pagesByExaminPapersInfo(papersExaminessInfo);
	}

	@Override
	public int saveExamPapersInfo(LawAppExaminPapersInfo examinPapersInfo) {
		
		//999是每周一答
		if(!StringUtils.isBlank(examinPapersInfo.getAuthCode()) && examinPapersInfo.getAuthCode().equals("999")) {
			examinPapersInfo.setCreatedBy("admin");
			examinPapersInfo.setUpdatedBy("admin");
			examinPapersInfo.setUpdatedTime(new Date());
			examinPapersInfo.setCreatedTime(new Date());
			examinPapersInfo.setAuthCode(null);
		}else {
//			BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//			if(userEntity != null) {
//				examinPapersInfo.setCreatedBy(userEntity.getUserId());
//				examinPapersInfo.setUpdatedBy(userEntity.getUserId());
//			}else {
//				examinPapersInfo.setCreatedBy("admin");
//				examinPapersInfo.setUpdatedBy("admin");
//			}
			examinPapersInfo.setUpdatedTime(new Date());
			examinPapersInfo.setCreatedTime(new Date());
		}
		return lawAppExaminPapersInfoMapper.insert(examinPapersInfo);
	}

	@Override
	public int delExamPapersInfo(LawAppExaminPapersInfo examinPapersInfo) {
		return lawAppExaminPapersInfoMapper.delExamPapersInfo(examinPapersInfo);
	}

	@Override
	public int updateByPrimaryKey(LawAppExaminPapersInfo examinPapersInfo) {
//		BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//		examinPapersInfo.setUpdatedBy(userEntity.getUserId());
		examinPapersInfo.setUpdatedTime(new Date());
		return lawAppExaminPapersInfoMapper.updateByPrimaryKey(examinPapersInfo);
	}
	
	@Override
	public List<Map<String, Object>> listByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo) {
		// TODO Auto-generated method stub
		return lawAppExaminPapersInfoMapper.listByExaminPapersInfo(papersExaminessInfo);
	}

	@Override
	public List<Map<String, Object>> qryAllByPaperId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return lawAppExaminPapersInfoMapper.qryAllByPaperId(map);
	}

	@Override
	public int delPapersQuesrelById(String id) {
		// TODO Auto-generated method stub
		return lawAppExaminPapersInfoMapper.delPapersQuesrelByid(id);
	}

	@Override
	public List<Map<String, Object>> qryRoundQuesAll(Map<String, Object> map) {
		return lawAppExaminPapersInfoMapper.qryRoundQuesAll(map);
	}

	@Override
	public int savePapersQues(Map<String, Object> map) {
		return lawAppExaminPapersInfoMapper.savePapersQues(map);
	}
	
	
	
	
    @Override
    public JSONObject saveExamPapersByQuartz(LawAppExaminPapersInfo examinPapersInfo, JSONObject reqPar) {
    	
        JSONObject obj = new JSONObject();
        String paperId = examinPapersInfo.getPaperId();
        int len = 0;
        //单选择题
        
        String queSId = reqPar.getString("queSId");
        
        //单选
		StringBuilder scpSB = null; 
		//多选
		StringBuilder aqSB = null;
		//判断
		StringBuilder jqSB = null;
		//问答
		StringBuilder wqSB = null;
		//填空
		StringBuilder tqSB = null;
		//统计
		long tCount = 0;
		//重复
		long fCount = 0;
		//成功
		long suCount  = 0;
        
        Date d = new Date();
        if (!StringUtils.isEmpty(queSId)) {
            String queSIds[] = queSId.split(",");
            scpSB = new StringBuilder("单项选择题:【");
            String quesType = "0";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queSIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", queSIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
                map.put("createTime", d);
                map.put("modifyTime", d);
                map.put("createUser", "admin");
                map.put("modifyUser", "admin");
                if(tCount < examinPapersInfo.getScqNum()) {
                	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
                	tCount =  tCount+1;
                }else {
                	len ++;
                }
            }
            scpSB.append("重复:" + fCount);
            scpSB.append(";成功:" + suCount); 
            scpSB.append(";超出:" + len); 
            scpSB.append("】" ); 
            scpSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //多选择题
        String quesSId = reqPar.getString("quesSId");
        if (!StringUtils.isEmpty(quesSId)) {
            String quesSIds[] = quesSId.split(",");
            aqSB = new StringBuilder("多项选择题:【");
            String quesType = "1";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < quesSIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", quesSIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
                map.put("createTime", d);
                map.put("modifyTime", d);
                map.put("createUser", "admin");
                map.put("modifyUser", "admin");
                if(tCount < examinPapersInfo.getAqNum()) {
                	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
                	tCount =  tCount+1;
                }else {
                	len ++;
                }
            }
            aqSB.append("重复:" + fCount);
            aqSB.append(";成功:" + suCount); 
            aqSB.append(";超出:" + len); 
            aqSB.append("】" ); 
            aqSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //判断题
        String queJId = reqPar.getString("queJId");
        if (!StringUtils.isEmpty(queJId)) {
            String queJIds[] = queJId.split(",");
            jqSB = new StringBuilder("判断题:【");
            String quesType = "2";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queJIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesId", queJIds[i]);
                map.put("quesType", quesType);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
                map.put("createTime", d);
                map.put("modifyTime", d);
                map.put("createUser", "admin");
                map.put("modifyUser", "admin");
	            if(tCount < examinPapersInfo.getJqNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            jqSB.append("重复:" + fCount);
            jqSB.append(";成功:" + suCount); 
            jqSB.append(";超出:" + len); 
            jqSB.append("】" ); 
            jqSB.append("<br>");
            fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //问答题
        String queEId = reqPar.getString("queEId");
        if (!StringUtils.isEmpty(queEId)) {
            String queEIds[] = queEId.split(",");
            wqSB = new StringBuilder("问答题:【");
            String quesType = "3";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queEIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesType", quesType);
                map.put("quesId", queEIds[i]);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
                map.put("createTime", d);
                map.put("modifyTime", d);
                map.put("createUser", "admin");
                map.put("modifyUser", "admin");
	            if(tCount < examinPapersInfo.getAskNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            wqSB.append("重复:" + fCount);
            wqSB.append(";成功:" + suCount); 
            wqSB.append(";超出:" + len); 
            wqSB.append("】" ); 
	        fCount = 0;
	        len = 0;
	        suCount = 0;
        }

        //填空题
        String queFId = reqPar.getString("queFId");
        if (!StringUtils.isEmpty(queFId)) {
            String queFIds[] = queFId.split(",");
            tqSB = new StringBuilder("填空题:【");
            String quesType = "4";
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("paperId", paperId);
            map2.put("quesType", quesType);
            map2 = lawAppPapersQuesrelInfoMapper.qryCountPapersQuesrel(map2);
            tCount =  (long) map2.get("pcount");
            for (int i = 0; i < queFIds.length; i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("paperId", paperId);
                map.put("quesType", quesType);
                map.put("quesId", queFIds[i]);
                List<Object> list = lawAppPapersQuesrelInfoMapper.selectByQuesId(map);
                if(list != null && list.size()>0) {
                	fCount += 1;
                	continue;
                }
//                map.put("id", IDGenerator.getId());
                map.put("createTime", d);
                map.put("modifyTime", d);
                map.put("createUser", "admin");
                map.put("modifyUser", "admin");
                if(tCount < examinPapersInfo.getFbNum()) {
	            	suCount += lawAppExaminPapersInfoMapper.savePapersQues(map);
	            	tCount =  tCount+1;
	            }else {
	            	len ++;
	            }
	        }
            tqSB.append("重复:" + fCount);
            tqSB.append(";成功:" + suCount); 
            tqSB.append(";超出:" + len); 
            tqSB.append("】" ); 
	        fCount = 0;
	        len = 0;
        }
        StringBuilder cSB = new StringBuilder(); 
        if(scpSB != null) {
        	cSB.append(scpSB);
        }
        if(aqSB != null) {
        	cSB.append(aqSB);
        }
        if(jqSB != null) {
        	cSB.append(jqSB);
        }
        if(wqSB != null) {
        	cSB.append(wqSB);
        }
        if(tqSB != null) {
        	cSB.append(tqSB);
        }
        obj.put("code", "0");
        obj.put("desc", cSB.toString());
        return obj;
    }
	
	

}
