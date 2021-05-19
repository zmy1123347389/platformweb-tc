package com.behere.portal.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppPapersQuesrelInfo;
import com.behere.portal.domain.LawAppPoliceExam;

public interface LawAppPapersQuesrelInfoDao {
    /**
     * 添加试卷
     *
     * @param paperId
     * @param quesId
     * @return
     */
    int savePaper(@Param("paperId") String paperId, @Param("quesId") String quesId, @Param("createdBy") String createdBy);

    /**
     * 得到单选题
     *
     * @param paperId
     * @return
     */
    List<LawAppPapersQuesrelInfo> getScqChoName(@Param("paperId") String paperId);

    /**
     * 得到多选题
     *
     * @param paperId
     * @return
     */
    List<LawAppPapersQuesrelInfo> getAqChoName(@Param("paperId") String paperId);

    /**
     * 得到问答题
     *
     * @param paperId
     * @return
     */
    List<LawAppPapersQuesrelInfo> getAskQuesName(@Param("paperId") String paperId);

    /**
     * 得到填空题
     *
     * @param paperId
     * @return
     */
    List<LawAppPapersQuesrelInfo> getFbQuesName(@Param("paperId") String paperId);

    /**
     * 得到判断题
     *
     * @param paperId
     * @return
     */
    List<LawAppPapersQuesrelInfo> getJqQuesName(@Param("paperId") String paperId);

    /**
     * 根据题目获取信息试卷信息
     *
     * @param map
     * @return
     */
    List<Object> selectByQuesId(Map<String, Object> map);

	List<LawAppPoliceExam> qryPaperAll(Map<String, Object> map);


	List<Map<String, Object>> getAllList(Map<String, Object> map);
	
	
	Map<String, Object> qryCountPapersQuesrel(Map<String, Object> map);

}
