package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.behere.portal.domain.LawAppExaminPapersInfo;

/**
 * @ClassName：GetExamTopicService
 * @Auther: 京京
 * @Date: 2020/3/22 01:20
 * @Description:
 */
public interface LawAppExaminPapersInfoService {
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
     * 查询试卷列表
     * selectPapersInfo
     *
     * @param scqType
     * @return
     */
    List<LawAppExaminPapersInfo> selectPapersInfo(Map<String, Object> map);
    

    JSONObject saveExamPapers(LawAppExaminPapersInfo examinPapersInfo, JSONObject reqPar);

	/**	
	 * 随机获取题型信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> selectQuesByRand(Map<String, String> map);


    /**
     * 根据试卷id查询试卷信息
     *
     * @param paperId
     * @return
     */
    LawAppExaminPapersInfo getPapersInfo(LawAppExaminPapersInfo papersExaminessInfo);

	/**
	 * 	获取所有试卷信息
	 * @param page
	 * @param papersExaminessInfo
	 * @return
	 */
	List<Map<String, Object>> pagesByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo);

	/**
	 * 	增加试卷
	 * @param examinPapersInfo
	 * @return
	 */
	int saveExamPapersInfo(LawAppExaminPapersInfo examinPapersInfo);

	/**
	 * 	删除试卷
	 * @param examinPapersInfo
	 * @return
	 */
	int delExamPapersInfo(LawAppExaminPapersInfo examinPapersInfo);

	/**
	 * 	修改试卷信息
	 * @param examinPapersInfo
	 * @return
	 */
	int updateByPrimaryKey(LawAppExaminPapersInfo examinPapersInfo);

	List<Map<String, Object>> listByExaminPapersInfo(LawAppExaminPapersInfo papersExaminessInfo);

	/**
	 * 	根据试卷获取所有的试题
	 * @param map
	 * @return
	 */

	List<Map<String, Object>> qryAllByPaperId(Map<String, Object> map);

	/**	删除试题中的题目
	 * @param id
	 * @return
	 */
	int delPapersQuesrelById(String id);

	/**
	 * 	随机获取试卷题目信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryRoundQuesAll(Map<String, Object> map);

	/**
	 * 	app端添加试题库
	 * @param map
	 * @return
	 */
	int savePapersQues(Map<String, Object> map);

	/**
	 * 	定时任务增加每周一答
	 * @param examinPapersInfo
	 * @param reqPar
	 * @return
	 */
	JSONObject saveExamPapersByQuartz(LawAppExaminPapersInfo examinPapersInfo, JSONObject reqPar);


}
