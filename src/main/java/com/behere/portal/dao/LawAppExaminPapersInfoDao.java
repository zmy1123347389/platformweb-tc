package com.behere.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppExaminPapersInfo;

public interface LawAppExaminPapersInfoDao {
    /**
     * 提取单选题
     *
     * @param scqType
     * @param quesName
     * @param scqNum
     * @return
     */
    List<LawAppExaminPapersInfo> selectScq(@Param("scqType") String scqType, @Param("quesName") String quesName, @Param("scqNum") Integer scqNum);

    /**
     * 提取多选题
     *
     * @param scqType
     * @param quesName
     * @param aqNum
     * @return
     */
    List<LawAppExaminPapersInfo> selectAqNum(@Param("scqType") String scqType, @Param("quesName") String quesName, @Param("aqNum") Integer aqNum);

    /**
     * 提取判断题
     *
     * @param scqType
     * @param quesName
     * @param jqNum
     * @return
     */
    List<LawAppExaminPapersInfo> selectJqNum(@Param("scqType") String scqType, @Param("quesName") String quesName, @Param("jqNum") Integer jqNum);


    /**
     * 提取问答题
     *
     * @param scqType
     * @param quesName
     * @param askNum
     * @return
     */
    List<LawAppExaminPapersInfo> selectAskName(@Param("scqType") String scqType, @Param("quesName") String quesName, @Param("askNum") Integer askNum);

    /**
     * 提取填空题
     *
     * @param scqType
     * @param quesName
     * @param fbNum
     * @return
     */
    List<LawAppExaminPapersInfo> selectFbName(@Param("scqType") String scqType, @Param("quesName") String quesName, @Param("fbNum") Integer fbNum);

    /**
     * 获取所有试卷列表及信息
     *
     * @param
     * @return
     */
    List<LawAppExaminPapersInfo> selectPapersInfo(Map<String, Object> map);
  

    /**
     * 根据试卷id查询试卷信息
     *
     * @param paperId
     * @return
     */
    LawAppExaminPapersInfo getPapersInfo(LawAppExaminPapersInfo appExaminPapersInfo);

    /**
     * 答题次数
     *
     * @param userId
     * @return
     */
    String getExaminNum(@Param("userId") String userId);

    /**
     * 保存试卷和题目相关信息
     *
     * @param map
     * @return
     */
    int savePapersQues(Map<String, Object> map);

    /**
     * 保存试卷信息
     *
     * @param examinPapersInfo
     * @return
     */
    int insert(LawAppExaminPapersInfo examinPapersInfo);
    
    
    
    
    /**
     * 随机获取获取所有试卷列表及信息
     *
     * @param
     * @return
     */
    List<Map<String, Object>> selectQuesByRand(Integer jqNum);

	List<Map<String, Object>> pagesByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo);

	int delExamPapersInfo(LawAppExaminPapersInfo examinPapersInfo);
	
	int delExamPapersInfo(String paperId);
	
	int updateByPrimaryKey(LawAppExaminPapersInfo examinPapersInfo);

	List<Map<String, Object>> listByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo);

	List<Map<String, Object>> qryAllByPaperId(Map<String, Object> map);
	
	int delPapersQuesrelByid(String id);
	
	/**
	 * 	随机获取试卷试题
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryRoundQuesAll(Map<String, Object> map);
}