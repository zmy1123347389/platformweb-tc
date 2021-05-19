package com.behere.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.platform.dao.GiftDao;
import com.behere.platform.domain.Gift;
import com.behere.platform.service.GiftService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftDao giftDao;

    @Override
    public List<Gift> list() {
        return giftDao.list();
    }

    @Override
    public Gift queryGiftById(int id) {
        return giftDao.queryGiftById(id);
    }

    @Override
    public int update(Gift gift) {
        return giftDao.update(gift);
    }

    @Override
    public int save(Gift gift) {
        return giftDao.save(gift);
    }

    @Override
    public int delete(int id) {
        return giftDao.delete(id);
    }
}
