package com.behere.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppPoliceExam;

public interface LawappPoliceExamDao {


    List<LawAppPoliceExam> queryListPoliceExam(LawAppPoliceExam policeExam);

    int insert(LawAppPoliceExam appPoliceInfo);

    int updateById(LawAppPoliceExam appPoliceInfo);

	
	int saveExamRole(@Param("exams") List<Map<String, Object>> exams);
	
	
	List<Map<String, Object>> qryUnitList(Map<String, Object> exams);

	int delUserById(LawAppPoliceExam appPoliceInfo);
	
	List<Map<String, Object>> qryByExamRole(Map<String, Object> record);


	List<Map<String, Object>> getRoleExam(Map<String, Object> record);
	
	LawAppPoliceExam selectById(LawAppPoliceExam policeExam);

	List<Map<String, Object>> qryDict(Map<String, Object> map);
	
}
