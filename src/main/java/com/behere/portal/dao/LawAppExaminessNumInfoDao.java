package com.behere.portal.dao;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domain.LawAppExaminessNumInfo;


/**
 * @ClassName：LawAppPapersExaminessInfoMapper
 * @Auther: 京京
 * @Date: 2020/3/28 21:58
 * @Description:
 */
public interface LawAppExaminessNumInfoDao {

    /**
     * 获取答题次数
     *
     * @param userId
     * @return
     */
    LawAppExaminessNumInfo getExaminNum(@Param("userId") String userId);
}
