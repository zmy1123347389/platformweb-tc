package com.behere.portal.service;


import java.util.List;
import java.util.Map;

import com.behere.portal.domain.LawAppIntegralInfo;

/**
 * @ClassName：IntegralService
 * @Auther: 京京
 * @Date: 2020/3/17 00:12
 * @Description:积分添加
 */

public interface LawAppIntegralInfoService {

    /**
     * 查询积分排名
     *
     * @param
     * @return
     */
    List<Map<String, Object>> selectRanking(Map<String, Object> map);

    /**添加积分
     * @param
     * @param record
     * @return
     */
    int insertIntegral(LawAppIntegralInfo record);

    /**
     * 添加积分
     * @param map
     * @return
     */
    int insertIntegral(Map<String, String> map);

	/**
	 * 	获取积分排名
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryJfpmPagesByPro(Map<String, Object> map);

	/**
	 * 	积分排名 省市区
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryJfpmPages(Map<String, Object> map);

	/**
	 * 	根据警号获取积分详情
	 * @param appIntegralInfo
	 * @return
	 */
	List<Map<String, Object>> qryJfByuserId(LawAppIntegralInfo appIntegralInfo);



}
