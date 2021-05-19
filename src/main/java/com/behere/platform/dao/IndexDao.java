package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

/**
 * @author: Behere
 */
@Mapper
public interface IndexDao {

    /**
     * 新增用户
     * @param date
     * @return
     */
    long countUserByCreateDate(@Param("date") String date);

    /**
     * 在线用户
     * @return
     */
    long countOnlineUser();

    /**
     * 视频通话
     * @param date
     * @return
     */
    long countPayFlower(@Param("date") String date, @Param("type") int type);

    /**
     * 用户充值
     * @param date
     * @return
     */
    long countRecharge(@Param("date") String date);

    /**
     * 最佳男女友
     * @return
     */
    long countBestFriend();

    /**
     * 提现
     * @return
     */
    long countWithdrawal(@Param("date") String date);

    /**
     * 邀请
     * @return
     */
    long countInvitation();
}
