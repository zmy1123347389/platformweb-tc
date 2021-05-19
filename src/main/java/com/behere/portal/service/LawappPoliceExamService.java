package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import com.behere.portal.domain.LawAppPoliceExam;

public interface LawappPoliceExamService {

	/**
	 * 	增加参考人员信息
	 * @param userid
	 * @return
	 */
	int saveExamUsers(Map<String, String> map);
    /**
     * 增加参考人员信息
     *
     * @param userid
     * @return
     */
    int saveExamUsers(String userId, String papersId);

    /**
     * 查询警务人员信息
     *
     * @param page
     * @param policeExam
     * @return
     */
    List<LawAppPoliceExam> queryListPoliceExam(LawAppPoliceExam policeExam);

    /**
     * 增加警务人员信息
     *
     * @param appPoliceInfo
     * @return
     */
    int saveUsers(LawAppPoliceExam appPoliceInfo);

    /**
     * 根据用户信息(ID)修改用户密码
     *
     * @param appPoliceInfo
     * @return
     */

    int modifyUserById(LawAppPoliceExam appPoliceInfo);
    
    
	/**
	 * 	获取单位信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryUnitList(Map<String, Object> map);
	
	
	/**
	 * 	删除用户
	 * @param appPoliceInfo
	 * @return
	 */
	int delUserById(LawAppPoliceExam appPoliceInfo);
	
	
	/**
	 * 
	 * 	根据用户部门是否允许考试
	 * @param appPoliceInfo
	 * @return
	 */
	List<Map<String, Object>> getRoleExam(Map<String, Object> record);
	
	
	/**
	 * 	获取考试人员权限
	 * @param record
	 * @return
	 */
	List<Map<String, Object>> qryByExamRole(Map<String, Object> record);
	
	
	/**
	 * 	根据警号获取用户信息
	 * @param record
	 * @return
	 */
	LawAppPoliceExam selectById(LawAppPoliceExam record);
	
	
	/**
	 * 	获取字典信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryDict(Map<String, Object> map);


}
