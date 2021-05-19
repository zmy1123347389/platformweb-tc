package com.behere.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.platform.dao.BannerDao;
import com.behere.platform.domain.BannerDO;
import com.behere.platform.service.BannerService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<BannerDO> queryBanners() {
        return bannerDao.queryBanners();
    }

    @Override
    public BannerDO queryBanner(int bannerId) {
        return bannerDao.queryBanner(bannerId);
    }

    @Override
    public int updateBanner(BannerDO bannerDO) {
        return bannerDao.updateBanner(bannerDO);
    }

    @Override
    public int deleteBanner(int bannerId) {
        return bannerDao.deleteBanner(bannerId);
    }

    @Override
    public int saveBanner(BannerDO bannerDO) {
        return bannerDao.saveBanner(bannerDO);
    }
}
