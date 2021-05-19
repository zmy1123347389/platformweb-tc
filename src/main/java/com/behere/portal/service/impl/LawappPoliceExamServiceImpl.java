package com.behere.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.behere.portal.dao.LawAppPapersExaminessInfoDao;
import com.behere.portal.dao.LawappPoliceExamDao;
import com.behere.portal.domain.LawAppPapersExaminessInfo;
import com.behere.portal.domain.LawAppPoliceExam;
import com.behere.portal.service.LawappPoliceExamService;

@Service
public class LawappPoliceExamServiceImpl implements LawappPoliceExamService {
	
	private Logger log = LoggerFactory.getLogger(LawappPoliceExamServiceImpl.class);
	
	@Autowired
	private LawappPoliceExamDao examMapper;
	

    @Autowired
    private LawAppPapersExaminessInfoDao examinessInfoMapper;

	@Override
	public int saveExamUsers(Map<String, String> map) {
		if(log.isDebugEnabled()) {
			log.debug("保存参考人员权限信息请求信息...."+JSONObject.toJSONString(map));
		}
		
		int len = 0;
		//  根据登录人的信息，获取用户名
//		BaseUserEntity userEntity =  iPrivilegeService.getCurrentUser();
		Object userEntity = null;
		//当用户不为空时,根据用户插入，当用户为空时，则根据部门插入
		String userid = map.get("userId");
		List<Map<String, Object>> exams = new ArrayList<Map<String,Object>>();
      	if(!StringUtils.isEmpty(userid)) {
      		//根据用户增加考试权限
      		String uids [] = userid.split(",");
      		for (int i = 0; i < uids.length; i++) {
      			Map<String, Object> record = new HashMap<String, Object>();
      			record.put("paperId",map.get("paperId"));
      			record.put("userId",uids[i]);
      			record.put("deptId",map.get("deptId"));
      			List<Map<String, Object>> list = examMapper.qryByExamRole(record);
      			if(list != null && list.size()>0) {
      				continue ;
      			}else {
      				if(userEntity !=null) {
//      					record.put("createUser",userEntity.getUserId());
//      					record.put("modifyUser",userEntity.getUserId());
      				}else {
      					record.put("createUser","admin");
      					record.put("modifyUser","admin");
      				}
      				record.put("createTime",new Date());
      				record.put("modifyTime",new Date());
      				exams.add(record);
      			}
      		}
      	}else {
      		//根据部门增加考试权限
      		Map<String, Object> record = new HashMap<String, Object>();
  			record.put("paperId",map.get("paperId"));
  			record.put("deptId",map.get("deptId"));
  			List<Map<String, Object>> list = examMapper.qryByExamRole(record);
  			if(list != null && list.size()>0) {
  				return -2;
  			}
  			
  			if(userEntity !=null) {
//  				record.put("createUser",userEntity.getUserId());
//  				record.put("modifyUser",userEntity.getUserId());
  			}else {
  				record.put("createUser","admin");
  				record.put("modifyUser","admin");
  			}
  			record.put("createTime",new Date());
  			record.put("modifyTime",new Date());
  			exams.add(record);
      	}
      	if(exams != null && exams.size()>0) {
      		len += examMapper.saveExamRole(exams);
      	}else {
      		len = 1;
      	}
		return len;
	}


    @Override
    public int saveExamUsers(String userid, String papersId) {
        if (StringUtils.isEmpty(userid)) {
            log.debug("保存参考人员信息请求为空....参考用户" + userid + "试卷信息：" + papersId);
            return -1;
        }
        String uids[] = userid.split(",");
        int len = 0;
        //  根据登录人的信息，获取用户名
        for (int i = 0; i < uids.length; i++) {

            LawAppPapersExaminessInfo record = new LawAppPapersExaminessInfo();
            record.setPaperId(papersId);
            record.setUserId(uids[i]);

//            if (userEntity != null) {
//                record.setCreatedBy(userEntity.getUserId());
//            } else {
//                record.setCreatedBy("admin");
//            }
            record.setCreatedTime(new Date());

            len += examinessInfoMapper.saveExamResult(record);
        }
        return len;
    }

   

    @Override
    public int saveUsers(LawAppPoliceExam appPoliceInfo) {
        if (log.isDebugEnabled()) {
            log.debug("警务信息注册请求参数。。。" + JSONObject.toJSONString(appPoliceInfo));
        }

        //appPoliceInfo.setId(IDGenerator.getId());
//        appPoliceInfo.setId(appPoliceInfo.getUserId());
//        //将身份证信息修改为大写
//        appPoliceInfo.setGmsfhm(appPoliceInfo.getGmsfhm().toUpperCase());
//        //密码加密
//        String newPwd = EncryptUtil.MD5Encode(appPoliceInfo.getPwd()).substring(8, 24);
//        appPoliceInfo.setPwd(newPwd);
//        appPoliceInfo.setSource("2");
//
//        //  根据登录人的信息，获取用户名
//        BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//        if (userEntity != null) {
//            appPoliceInfo.setCreateBy(userEntity.getUserId());
//            appPoliceInfo.setUpdatedBy(userEntity.getUserId());
//        } else {
//            appPoliceInfo.setCreateBy("admin");
//            appPoliceInfo.setUpdatedBy("admin");
//        }
//        appPoliceInfo.setCreatedTime(new Date());
//        appPoliceInfo.setUpdatedTime(new Date());
//        
//        appPoliceInfo.setInteralnum(0);

        return examMapper.insert(appPoliceInfo);
    }

    @Override
    public int modifyUserById(LawAppPoliceExam appPoliceInfo) {
        if (log.isDebugEnabled()) {
            log.debug("警务信息修改用户密码请求参数。。。" + JSONObject.toJSONString(appPoliceInfo));
        }
        //密码加密
//        if (!StringUtils.isEmpty(appPoliceInfo.getPwd())) {
//            String newPwd = EncryptUtil.MD5Encode(appPoliceInfo.getPwd()).substring(8, 24);
//            appPoliceInfo.setPwd(newPwd);
//        }
//        //  根据登录人的信息，获取用户名
//        BaseUserEntity userEntity = iPrivilegeService.getCurrentUser();
//        if (userEntity != null) {
//            appPoliceInfo.setUpdatedBy(userEntity.getUserId());
//        } 
//        appPoliceInfo.setUpdatedTime(new Date());

        return examMapper.updateById(appPoliceInfo);
    }


	@Override
    public List<LawAppPoliceExam> queryListPoliceExam(LawAppPoliceExam policeExam) {
        return examMapper.queryListPoliceExam(policeExam);
    }


	@Override
	public List<Map<String, Object>> qryUnitList(Map<String, Object> map) {
		
		return examMapper.qryUnitList(map);
	}


	@Override
	public int delUserById(LawAppPoliceExam appPoliceInfo) {
		return examMapper.delUserById(appPoliceInfo);
	}


	@Override
	public List<Map<String, Object>> getRoleExam(Map<String, Object> record) {
		
		return examMapper.getRoleExam(record);
	}
	
	@Override
	public List<Map<String, Object>> qryByExamRole(Map<String, Object> record) {
		return examMapper.qryByExamRole(record);
	}
	
	
	@Override
	public LawAppPoliceExam selectById(LawAppPoliceExam record) {
		return examMapper.selectById(record);
	}


	@Override
	public List<Map<String, Object>> qryDict(Map<String, Object> map) {
		return examMapper.qryDict(map);
	}
	

}
