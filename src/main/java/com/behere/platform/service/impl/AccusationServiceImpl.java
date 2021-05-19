package com.behere.platform.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.behere.common.constants.Constant;
import com.behere.common.constants.PayConstant;
import com.behere.common.utils.Query;
import com.behere.common.utils.StringUtils;
import com.behere.platform.dao.AccountDao;
import com.behere.platform.dao.AccusationDao;
import com.behere.platform.domain.*;
import com.behere.platform.service.AccountService;
import com.behere.platform.service.AccusationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class AccusationServiceImpl implements AccusationService {
    @Autowired
    private AccusationDao accusationDao;

    @Override
    public List<AccusationDO> queryAccusations(Query query) {
        return accusationDao.queryAccusations(query);
    }

    @Override
    public int count(Query query) {
        return accusationDao.count(query);
    }

    @Override
    public AccusationDO queryAccusationById(String id) {
        return accusationDao.queryAccusationById(id);
    }

    @Override
    public int updateAccusationDealStatus(int deleted, String id) {
        return accusationDao.updateAccusationDealStatus(deleted, id);
    }

    @Override
    public List<AccusationPic> queryAccusationPics(String reportId) {
        return accusationDao.queryAccusationPics(reportId);
    }

    @Override
    public String queryReportContentByAccusationId(int accusationId) {
        return accusationDao.queryReportContentByAccusationId(accusationId);
    }
}