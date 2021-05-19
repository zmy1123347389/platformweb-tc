package com.behere.portal.dao;


import java.util.List;
import java.util.Map;

import com.behere.portal.domain.LawAppIntegralInfo;


public interface LawAppIntegralInfoDao {

    /**
     * 	添加积分
     * @param record
     * @return
     */
    int insertIntegral(LawAppIntegralInfo record);

    /**
     * 	积分排名
     * @param map
     * @return
     */
    List<Map<String, Object>> selectRanking(Map<String, Object> map);

    /**
     *	 个人积分总数
     * @param userId
     * @return
     */
    LawAppIntegralInfo selectIntegralNum(String userId);


	List<Map<String, Object>> qryJfpmPages(Map<String, Object> map);
	
	List<Map<String, Object>> qryJfpmPagesByPro(Map<String, Object> map);
	
	
	List<Map<String, Object>> qryByUserId(LawAppIntegralInfo record);

}