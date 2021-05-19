package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.platform.domain.Gift;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface GiftDao {

    List<Gift> list();

    Gift queryGiftById(@Param("id") int id);

    int update(Gift gift);

    int save(Gift gift);

    int delete(@Param("id") int id);

}
