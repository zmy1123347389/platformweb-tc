package com.behere.platform.service;

import java.util.List;

import com.behere.common.utils.Query;
import com.behere.platform.domain.AccusationDO;
import com.behere.platform.domain.AccusationPic;

/**
 * @author: Behere
 */
public interface AccusationService {

    /**
     * 举报信息列表
     * @param query
     * @return
     */
    List<AccusationDO> queryAccusations(Query query);

    int count(Query query);

    AccusationDO queryAccusationById(String id);

    int updateAccusationDealStatus(int deleted, String id);

    List<AccusationPic> queryAccusationPics(String reportId);

    String queryReportContentByAccusationId(int accusationId);
}