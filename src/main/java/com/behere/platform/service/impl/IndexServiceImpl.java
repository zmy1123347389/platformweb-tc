package com.behere.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.platform.dao.IndexDao;
import com.behere.platform.service.IndexService;

/**
 * @author: Behere
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public long countUserByCreateDate(String date) {
        return indexDao.countUserByCreateDate(date);
    }

    @Override
    public long countOnlineUser() {
        return indexDao.countOnlineUser();
    }


    @Override
    public long countPayFlower(String date, int type) {
        return indexDao.countPayFlower(date, type);
    }

    @Override
    public long countRecharge(String date) {
        return indexDao.countRecharge(date);
    }

    @Override
    public long countBestFriend() {
        return indexDao.countBestFriend();
    }

    @Override
    public long countWithdrawal(String date) {
        return indexDao.countWithdrawal(date);
    }

    @Override
    public long countInvitation() {
        return indexDao.countInvitation();
    }
}