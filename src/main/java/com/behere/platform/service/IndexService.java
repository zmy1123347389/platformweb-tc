package com.behere.platform.service;

/**
 * @author: Behere
 */
public interface IndexService {

    /**
     * 新增用户
     * @param date
     * @return
     */
    long countUserByCreateDate(String date);

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
    long countPayFlower(String date, int type);

    /**
     * 用户充值
     * @param date
     * @return
     */
    long countRecharge(String date);

    /**
     * 最佳男女友
     * @return
     */
    long countBestFriend();

    /**
     * 提现
     * @return
     */
    long countWithdrawal(String date);

    /**
     * 邀请
     * @return
     */
    long countInvitation();
}
