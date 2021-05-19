package com.behere;

import java.util.List;
import java.util.Map;


/**
 * 基础Service
 */
public interface BaseService<T> {
	/**
	 * 
	 * @param id
	 * @param paramMap
	 * @return
	 */
	T selectById(String id);

	/**
	 * 
	 * @param t
	 * @param paramMap
	 * @return
	 */
	T selectByEntity(T t, Map<String, Object> paramMap);

	/**
	 * 
	 * @param t
	 * @param paramMap
	 * @return
	 */
	List<T> selectByEntityList(T t, Map<String, Object> paramMap);

	/**
	 * 
	 * @param idList
	 * @param paramMap
	 * @return
	 */
	List<T> selectByIdList(String[] idList, Map<String, Object> paramMap);

	int create(T t);

	int update(T t);

	int deleteById(String id);

	int deleteByEntity(T t);

	int deleteByIdList(String[] idList);

}
