package com.behere.platform.service;

import java.util.List;

import com.behere.platform.domain.BannerDO;

/**
 * @author: Behere
 */
public interface BannerService {

    List<BannerDO> queryBanners();

    BannerDO queryBanner(int bannerId);

    int updateBanner(BannerDO bannerDO);

    int deleteBanner(int bannerId);

    int saveBanner(BannerDO bannerDO);
}