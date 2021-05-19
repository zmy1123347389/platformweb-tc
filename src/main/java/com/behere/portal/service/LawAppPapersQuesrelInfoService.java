package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import com.behere.portal.domain.LawAppPapersQuesrelInfo;
import com.behere.portal.domain.LawAppPoliceExam;

/**
 * @ClassName：LawAppPapersQuesrelInfoService
 * @Auther: 京京
 * @Date: 2020/3/22 13:38
 * @Description:
 */
public interface LawAppPapersQuesrelInfoService {


    Map<String, List<LawAppPapersQuesrelInfo>> getAll(String userId, String paperId, String scqType);

	/**
	 * 	获取所有试题信息
	 * @param page
	 * @param map
	 * @return
	 */
	List<LawAppPoliceExam> qryPaperAll(Map<String, Object> map);

	List<Map<String, Object>> getAllList(Map<String, Object> map);


}
