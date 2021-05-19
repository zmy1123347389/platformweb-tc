package com.behere.platform.service;

import java.util.List;

import com.behere.platform.domain.Gift;

/**
 * @author: Behere
 */
public interface GiftService {

    List<Gift> list();

    Gift queryGiftById(int id);

    int update(Gift gift);

    int save(Gift gift);

    int delete(int id);
}
