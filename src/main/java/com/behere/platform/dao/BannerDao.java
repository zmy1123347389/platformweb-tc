package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.platform.domain.BannerDO;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface BannerDao {

    List<BannerDO> queryBanners();

    BannerDO queryBanner(@Param("bannerId") int bannerId);

    int updateBanner(BannerDO bannerDO);

    int deleteBanner(@Param("bannerId") int bannerId);

    int saveBanner(BannerDO bannerDO);
}
