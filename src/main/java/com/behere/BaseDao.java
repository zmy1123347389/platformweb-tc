package com.behere;

import java.util.List;

/**
 * 基础Dao
 */
public interface BaseDao<T> {

	T selectById(String id);

	T selectByEntity(T t);

	List<T> selectByEntityList(T t);

	List<T> selectByIdList(String[] idList);

	int create(T t);

	int update(T t);

	int deleteById(String id);

	int deleteByEntity(T t);

	int deleteByIdList(String[] idList);

}
