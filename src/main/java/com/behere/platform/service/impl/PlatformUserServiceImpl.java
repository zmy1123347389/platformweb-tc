package com.behere.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.MsgConstant;
import com.behere.common.constants.Constant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.Query;
import com.behere.platform.dao.PlatformUserDao;
import com.behere.platform.domain.User;
import com.behere.platform.domain.UserAuth;
import com.behere.platform.service.PlatformUserService;

/**
 * @author: Behere
 */
@Service
public class PlatformUserServiceImpl implements PlatformUserService {

    @Autowired
    private PlatformUserDao platformUserDao;

    @Override
    public List<User> list(Query query) {
        return platformUserDao.list(query);
    }

    @Override
    public int count(Query query) {
        return platformUserDao.count(query);
    }

    @Override
    public int countUserAuth(Query query) {
        return platformUserDao.countUserAuth(query);
    }

    @Override
    public List<UserAuth> listUserAuthInformation(Query query) {
        return platformUserDao.listUserAuthInformation(query);
    }

    @Override
    public int updateUserAuth(int userAuth, long userId) {
        return platformUserDao.updateUserAuth(userAuth, userId);
    }

    @Override
    public int updateUserAuthInformation(int status, long id) {
        return platformUserDao.updateUserAuthInformation(status, id);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int dealSuccessUserAuth(int status, long authId, long userId) {
        try {
            updateUserAuth(status, userId);
            updateUserAuthInformation(status, authId);
            int type = MsgConstant.AUTH_FAIL;
            if (status == 1) {
                type = MsgConstant.AUTH_SUCCESS;
            }
            String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, null, type, null);
            NeteaseUtils.sendNetease(msg, userId);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return Constant.SUCCESS;
    }

    @Override
    public int updateUserDeleted(int deleted, long userId) {
        return platformUserDao.updateUserDeleted(deleted, userId);
    }

    @Override
    public User queryUserById(long userId) {
        return platformUserDao.queryUserById(userId);
    }
}