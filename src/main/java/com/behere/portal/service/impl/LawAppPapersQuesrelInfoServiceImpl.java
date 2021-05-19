package com.behere.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.LawAppPapersQuesrelInfoDao;
import com.behere.portal.domain.LawAppPapersQuesrelInfo;
import com.behere.portal.domain.LawAppPoliceExam;
import com.behere.portal.service.LawAppPapersQuesrelInfoService;

/**
 * @ClassName：LawAppPapersQuesrelInfoServiceImpl
 * @Auther: 京京
 * @Date: 2020/3/22 13:39
 * @Description:
 */
@Service
public class LawAppPapersQuesrelInfoServiceImpl implements LawAppPapersQuesrelInfoService {

    @Autowired
    private LawAppPapersQuesrelInfoDao lawAppPapersQuesrelInfoMapper;
    
    
    @Override
    public Map<String, List<LawAppPapersQuesrelInfo>> getAll(String userId, String paperId, String scqType) {
        Map<String, List<LawAppPapersQuesrelInfo>> getAllMap = new HashMap<String, List<LawAppPapersQuesrelInfo>>();
        /**
         * 得到单选题
         * @param paperId
         * @return
         */
        List<LawAppPapersQuesrelInfo> scqQuesName = lawAppPapersQuesrelInfoMapper.getScqChoName(paperId);
        /**
         * 得到多选题
         * @param paperId
         * @return
         */
        List<LawAppPapersQuesrelInfo> aqQuesName = lawAppPapersQuesrelInfoMapper.getAqChoName(paperId);
        /**
         * 获取判断题
         * @param paperId
         * @return
         */
        List<LawAppPapersQuesrelInfo> jqQuesName = lawAppPapersQuesrelInfoMapper.getJqQuesName(paperId);
        /**
         * 获取问答题
         * @param paperId
         * @return
         */
        List<LawAppPapersQuesrelInfo> askQuesName = lawAppPapersQuesrelInfoMapper.getAskQuesName(paperId);
        /**
         * 获取填空题
         * @param paperId
         * @return
         */
        List<LawAppPapersQuesrelInfo> fbQuesName = lawAppPapersQuesrelInfoMapper.getFbQuesName(paperId);
        getAllMap.put("scqQuesName", scqQuesName);
        getAllMap.put("aqQuesName", aqQuesName);
        getAllMap.put("jqQuesName", jqQuesName);
        getAllMap.put("askQuesName", askQuesName);
        getAllMap.put("fbQuesName", fbQuesName);

        return getAllMap;

    }

	@Override
	public List<LawAppPoliceExam> qryPaperAll(Map<String, Object> map) {
		
		return lawAppPapersQuesrelInfoMapper.qryPaperAll(map);
	}

	@Override
	public List<Map<String, Object>> getAllList(Map<String, Object> map) {
		
		return lawAppPapersQuesrelInfoMapper.getAllList(map);
	}
}
