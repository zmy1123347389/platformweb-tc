package com.behere.platform.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import com.behere.common.utils.Query;
import com.behere.platform.domain.*;

import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Mapper
public interface AccountDao {

    /**
     * 获取提现申请列表
     * @return
     */ 
    List<WithdrawalCash> queryWithdrawalCash(Query query);

    int count(Query query);

    /**
     * 修改提现申请状态
     * @param id
     * @param type
     * @return
     */
    int updateWithdrawalStatus(@Param("id") long id, @Param("type") int type);

    /**
     * 为用户表加锁
     * @param userId
     * @return
     */
    User queryUserByIdForUpdate(@Param("userId") long userId);

    /**
     * 修改用户钻石
     * @param flower
     * @param userId
     * @return
     */
    int addFlower(@Param("flower") int flower, @Param("userId") long userId);

    /**
     * 通过提现申请ID获取申请信息
     * @param id
     * @return
     */
    RechargeRecord queryRechargeRecordById(@Param("id") String id);

    /**
     * 查询提现详情
     * @param id
     * @return
     */
    WithdrawalCash queryWithdrawalById(@Param("id") long id);

    /**
     * 统计充值数量
     * @param query
     * @return
     */
    int countRecharges(Query query);

    /**
     * 充值列表
     * @param query
     * @return
     */
    List<RechargeDO> queryRechargeList(Query query);

    List<AuthAccount> queryAuthAccount(Query query);

    int countAuthAccount(Query query);
}
