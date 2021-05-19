package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import com.behere.portal.domain.LawAppPoliceInfo;

/**
 * @ClassName：LawAppPoliceInfoService
 * @Auther: 京京
 * @Date: 2020/3/25 11:25
 * @Description:
 */
public interface LawAppPoliceInfoService {
    /**
     * 用户登录
     *
     * @param lawAppPoliceInfo 用户对象
     * @return
     */
    public Map<String, String> mobileUserLoginQuery(String userId, String pwd, String signature, String timestamp);

    /**
     * 修改个人信息
     *
     * @param userId
     * @param policePosition
     * @param dwdm
     * @param policePhone
     * @param policeGender
     * @param userName
     * @return
     */
    int updatePoliceInfo(String userId, String policePosition, String dwdm, String policePhone,
                         String policeGender, String userName, String xp);

    /**
     * 查询用户信息
     *
     * @param userId
     * @return
     */
    LawAppPoliceInfo selectById(String userId);

	/**	
	 * 	注册用户
	 * @param appPoliceInfo
	 * @return
	 */
	public int saveUsers(LawAppPoliceInfo appPoliceInfo);

	List<Map<String, Object>> qryUserExamPages(Map<String, Object> map);

	/**
	 * 	获取单位信息
	 * @param exams
	 * @return
	 */
	public List<Map<String, Object>> qryUnitList(Map<String, Object> exams);

	/**	
	 * 	获取app版本号
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> qryAppNo(Map<String, Object> map);

	/**
	 * 	获取字典
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> qryDict(Map<String, Object> map);
}
