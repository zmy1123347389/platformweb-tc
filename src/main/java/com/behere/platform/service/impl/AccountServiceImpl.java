package com.behere.platform.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.behere.common.constant.MsgConstant;
import com.behere.common.constants.Constant;
import com.behere.common.constants.PayConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.Query;
import com.behere.common.utils.StringUtils;
import com.behere.platform.dao.AccountDao;
import com.behere.platform.domain.*;
import com.behere.platform.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<WithdrawalCash> queryWithdrawalCash(Query query) {
        return accountDao.queryWithdrawalCash(query);
    }

    @Override
    public int count(Query query) {
        return accountDao.count(query);
    }

    @Override
    public int updateWithdrawalStatus(long id, int type) {
        return accountDao.updateWithdrawalStatus(id, type);
    }

    @Override
    public User queryUserByIdForUpdate(long userId) {
        return accountDao.queryUserByIdForUpdate(userId);
    }

    @Override
    public int addFlower(int balance, long userId) {
        return accountDao.addFlower(balance, userId);
    }

    @Override
    public RechargeRecord queryRechargeRecordById(String id) {
        return accountDao.queryRechargeRecordById(id);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int dealRefuseWithdrawalApply(long id) {
        try {
            WithdrawalCash withdrawalCash = queryWithdrawalById(id);
            User user = queryUserByIdForUpdate(withdrawalCash.getUserId());
            addFlower(withdrawalCash.getMoney() * Constant.CNY_TO_FLOWER, user.getId());
            updateWithdrawalStatus(id, 2);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return 1;
    }

    @Override
    public WithdrawalCash queryWithdrawalById(long id) {
        return accountDao.queryWithdrawalById(id);
    }

    @Override
    public void acceptWithdrawal(long id) {
        WithdrawalCash withdrawalCash = queryWithdrawalById(id);
        AlipayClient alipayClient = new DefaultAlipayClient(PayConstant.ALIPAY_API,
                PayConstant.ALIPAY_APP_ID,
                PayConstant.ALIPAY_PRIVATE_KEY, "json", "utf-8",
                PayConstant.ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "    \"out_biz_no\":\"" + StringUtils.getUUID() + " \"," +  //自己生成的唯一id
                "    \"payee_type\":\"ALIPAY_LOGONID\"," +  //有两个选择
                "    \"payee_account\":\""+ withdrawalCash.getAccountName() +"\"," +
                "    \"amount\":\""+withdrawalCash.getMoney()+"\"," +
                "    \"payer_show_name\":\"颜语提现\"," +
                "    \"payee_real_name\":\""+withdrawalCash.getUsername()+"\"," +
                "    \"remark\":\"转账备注\"," +
                "  }");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
        }
        try {
            //String msg = "您已成功提现" + withdrawalCash.getMoney() + "元";
            String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, null, MsgConstant.WITHDRAWAL_SUCCESS, String.valueOf(withdrawalCash.getMoney()));
            NeteaseUtils.sendNetease(msg, withdrawalCash.getUserId());
        } catch (Exception e) {

        }
    }

    @Override
    public List<RechargeDO> queryRechargeList(Query query) {
        return accountDao.queryRechargeList(query);
    }

    @Override
    public int countRecharges(Query query) {
        return accountDao.countRecharges(query);
    }

    @Override
    public List<AuthAccount> queryAuthAccount(Query query) {
        return accountDao.queryAuthAccount(query);
    }

    @Override
    public int countAuthAccount(Query query) {
        return accountDao.countAuthAccount(query);
    }
}