package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.common.utils.Query;
import com.behere.platform.domain.AccusationDO;
import com.behere.platform.domain.AccusationPic;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface AccusationDao {

    /**
     * 举报信息列表
     * @param query
     * @return
     */
    List<AccusationDO> queryAccusations(Query query);

    int count(Query query);

    AccusationDO queryAccusationById(@Param("id") String id);

    int updateAccusationDealStatus(@Param("deleted") int deleted, @Param("id") String id);

    List<AccusationPic> queryAccusationPics(@Param("reportId") String reportId);

    String queryReportContentByAccusationId(@Param("accusationId") int accusationId);
}
