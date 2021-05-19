package com.behere.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppPapersExaminessInfo;

public interface LawAppPapersExaminessInfoDao {
    /**
     * 考试结果
     *
     * @param record
     * @return
     */
    int saveExamResult(LawAppPapersExaminessInfo record);

    /**
     * 查询试卷结果
     *
     * @param examsId
     * @param examsId
     * @return
     */
    LawAppPapersExaminessInfo getResult(LawAppPapersExaminessInfo record);

    /**
     * 判断是否已经答过题
     *
     * @param userId
     * @param paperId
     * @param scqType
     * @return
     */

    String getState(@Param("userId") String userId, @Param("paperId") String paperId, @Param("scqType") String scqType);

    /**
     * 考试排名
     *
     * @param examinessInfo
     * @return
     */
    List<LawAppPapersExaminessInfo> testRanking(LawAppPapersExaminessInfo examinessInfo);
    
    
    int saveUserExamQues(@Param("exams") List<Map<String, Object>> exams);

	List<Map<String, Object>> qryScoresCounte();
	
	List<Map<String, Object>> qryProvinceScoresCount(@Param("unitId")String unitId,@Param("paperId")String paperId);
	
	List<Map<String, Object>> qryUnitIdByParentId(@Param("parentId") String parentId);
	
	List<Map<String, Object>> qryUnitInfo();
	
	Map<String, Object> qryAllCityPercentageForMoble(@Param("parentId")String parentId);
	
	Map<String, Object> qryAllCityPercentageForPC(@Param("parentId")String parentId);
	
	List<Map<String, Object>> qryScoresCounteAll();

	Map<String, Object> qryAllCityPercentageForCityPC(String parentId);

	List<Map<String, Object>> qryWrongCount(Map<String, Object> map);

	List<Map<String, Object>> qryExcellentRate(Map<String, Object> map);
	
	

	Map<String, Object> qryAllCityCount(Map<String, Object> map);

	List<Map<String, Object>> usersExamPapaerPages(Map<String, Object> map);
}