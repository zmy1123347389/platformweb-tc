package com.behere.portal.service;

import java.util.List;
import java.util.Map;

public interface LawAppAllApiServer {

	/**
	 * 	获取音频信息
	 * 
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryAudioPages(Map<String, Object> map);

	/**
	 * 
	 * 	获取 头条通知
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryNoticePages(Map<String, Object> map);

	/**
	 * 	校验用户当天是否已做闯关答题
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryCheckPass(Map<String, Object> map);

	/**	
	 * 	获取图文信息
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryImggeTextPages(Map<String, Object> map);

	/**
	 * 	 按照Id获取图文信息
	 * @param map
	 * @return
	 */
	Map<String, Object> qryImggeTextById(Map<String, Object> map);

	/**
	 * 	保存闯关答题信息
	 * @param map
	 * @return
	 */
	int saveExamPass(Map<String, Object> map);

	/**
	 * 	获取闯关地答题最高分和闯关答题次数
	 * @param map
	 * @return
	 */
	Map<String, Object> qryCgDt(Map<String, Object> map);

	/**
	 * 	精品课件信息
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryLawappKjPages(Map<String, Object> map);

	/**
	 * 	精品课件详细信息
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryJpkjImg(Map<String, Object> map);

	/**
	 * 新法速递
	 * 
	 * @param pageQuery
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qryNewLawPages(Map<String, Object> map);

	/**
	 * 	新法速递详情
	 * @param map
	 * @return
	 */
	Map<String, Object> qryNewLawById(Map<String, Object> map); }
