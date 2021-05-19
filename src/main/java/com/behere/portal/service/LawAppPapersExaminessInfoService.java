package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.behere.portal.domain.LawAppPapersExaminessInfo;

/**
 * @ClassName：LawAppPapersExaminessInfoService
 * @Auther: 京京
 * @Date: 2020/3/23 17:57
 * @Description:
 */
public interface LawAppPapersExaminessInfoService {


    /**
     * 保存考试结果
     *
     * @param record
     * @return
     */
    int saveExamResult(LawAppPapersExaminessInfo record);

    /**
     * 查询试卷结果
     * @param examsId
     * @return
     */
    LawAppPapersExaminessInfo getResult(LawAppPapersExaminessInfo record);

    /**
     * 考试排名
     *
     * @param examinessInfo
     * @return
     */
    List<LawAppPapersExaminessInfo> testRanking(LawAppPapersExaminessInfo examinessInfo);

	/**
	 * 	增加考试
	 * @param lawAppPapersExaminessInfo
	 * @return
	 */
	int saveExamResultByPaper(LawAppPapersExaminessInfo lawAppPapersExaminessInfo);

	/**
	 * 	增加考试试题信息
	 * @param reqJson
	 * @return
	 */
	int saveExamResultByList(JSONObject reqJson);

	List<Map<String, Object>> qryScoresCounte();
	
	List<Map<String, Object>> qryProvinceScoresCount(@Param("unitId")String unitId,@Param("paperId")String paperId);
	
	List<Map<String, Object>> qryUnitIdByParentId(@Param("paperId") String parentId);
	
	List<Map<String, Object>> qryUnitInfo();
	
	Map<String, Object> qryAllCityPercentageForMoble(@Param("parentId")String parentId);
	
	Map<String, Object> qryAllCityPercentageForPC(@Param("parentId")String parentId);
	
	Map<String, Object> qryAllCityPercentageForCityPC(@Param("parentId")String parentId);

	List<Map<String, Object>> qryScoresCounteAll();


	/**
	 * 	统计 每个试卷的试题错误
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryWrongCount(Map<String, Object> map);

	/**
	 * 	统计个人的优秀率
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryExcellentRate(Map<String, Object> map);


	Map<String, Object> qryAllCityCount(Map<String, Object> map);

	/**
	 *  	获取个人考试信息
	 * @param page
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> usersExamPapaerPages(Map<String, Object> map);
}
