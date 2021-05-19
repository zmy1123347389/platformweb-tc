package com.behere.platform.service.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.common.constant.Constant;
import com.behere.common.push.PushUtil;
import com.behere.common.utils.Query;
import com.behere.platform.dao.PushDao;
import com.behere.platform.domain.PushDO;
import com.behere.platform.service.PushService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class PushServiceImpl implements PushService, Job {
    @Autowired
    private PushDao pushDao;

    @Override
    public List<PushDO> queryPushInformations(Query query) {
        return pushDao.queryPushInformations(query);
    }

    @Override
    public int count(Query query) {
        return pushDao.count(query);
    }

    @Override
    public int savePushInformation(PushDO pushDO) {
        return pushDao.savePushInformation(pushDO);
    }

    @Override
    public void sendPushMessage(PushDO pushDO) {
        PushUtil android = new PushUtil(com.behere.common.constant.Constant.ANDROID_PUSH_APP_KEY, com.behere.common.constant.Constant.ANDROID_PUSH_Master_SECRET);
        PushUtil ios = new PushUtil(com.behere.common.constant.Constant.IOS_PUSH_APP_KEY, com.behere.common.constant.Constant.IOS_PUSH_Master_SECRET);
        try {
            //0：手动推送  1：定时推送
            if (pushDO.getType() == 0) {
                android.sendAndroidBroadcast(pushDO.getTitle(), pushDO.getContent());
                ios.sendIOSBroadcast(pushDO.getTitle(), pushDO.getContent());
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePushUser(long pushId, long[] ids) {
        pushDao.savePushUser(pushId, ids);
    }

    @Override
    public void test() {
        System.out.println("------test-------");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        long[] ids = null;
        String[] key = context.getJobDetail().getKey().toString().split("\\.");
        String jobGroup = key[0];
        String jobName = key[1];
        PushDO pushDO = queryPushInformationById(Long.valueOf(jobName));
        if ("broadcast".equals(jobGroup)) {
            sendBroadcast(0, ids, pushDO.getTitle(), pushDO.getContent());
        } else {
            ids = queryPushUserByPushInformationId(pushDO.getId());
            sendBroadcast(1, ids, pushDO.getTitle(), pushDO.getContent());
        }
    }

    @Override
    public void sendBroadcast(int type, long[] ids, String title, String content) {
        System.out.println("-----push success------");
        PushUtil android = new PushUtil(com.behere.common.constant.Constant.ANDROID_PUSH_APP_KEY, com.behere.common.constant.Constant.ANDROID_PUSH_Master_SECRET);
        PushUtil ios = new PushUtil(com.behere.common.constant.Constant.IOS_PUSH_APP_KEY, com.behere.common.constant.Constant.IOS_PUSH_Master_SECRET);
        try {
            if (type == 0) {
                android.sendAndroidBroadcast(title, content);
                ios.sendIOSBroadcast(title, content);
            } else {
                for (int i = 0; i < ids.length; i++) {
                    android.sendAndroidCustomizedcast(String.valueOf(ids[i]), Constant.ALIAS, title, content);
                    ios.sendIOSCustomizedcast(String.valueOf(ids[i]), Constant.ALIAS, title, content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PushDO queryPushInformationById(long id) {
        return pushDao.queryPushInformationById(id);
    }

    @Override
    public long[] queryPushUserByPushInformationId(long pushId) {
        return pushDao.queryPushUserByPushInformationId(pushId);
    }
}