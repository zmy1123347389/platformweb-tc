package com.behere.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppPoliceInfo;

/**
 * @ClassName：LawAppPoliceInfoMapper
 * @Auther: 京京
 * @Date: 2020/3/25 12:56
 * @Description:
 */
public interface LawAppPoliceInfoDao {
    /**
     * 登陆
     *
     * @param
     * @return
     */
    LawAppPoliceInfo userLogin(@Param("userId") String userId, @Param("pwd") String pwd);

    /**
     * 修改个人信息
     *
     * @param lawAppPoliceInfo
     * @return
     */
    int updatePoliceInfo(LawAppPoliceInfo lawAppPoliceInfo);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    LawAppPoliceInfo selectById(@Param("userId") String userId);

    /**
     * 更新用户答题次数和总积分
     * ge
     *
     * @param lawAppPoliceInfo
     * @return
     */
    int updateUserInfo(LawAppPoliceInfo lawAppPoliceInfo);
    
    
    /**
     * 	注册
     * @param lawAppPoliceInfo
     * @return
     */
    int insert(LawAppPoliceInfo lawAppPoliceInfo);
    
    List<Map<String, Object>> selectByUserExamQues(Map<String, Object> map);
    
    
	/**
	 * 	获取单位信息
	 * @param exams
	 * @return
	 */
	List<Map<String, Object>> qryUnitList(Map<String, Object> exams);

	/**
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryAppNo(Map<String, Object> map);

	List<Map<String, Object>> qryDict(Map<String, Object> map);

}
