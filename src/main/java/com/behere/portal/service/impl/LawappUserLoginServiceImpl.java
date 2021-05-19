package com.behere.portal.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.LawAppExaminessNumInfoDao;
import com.behere.portal.dao.LawAppIntegralInfoDao;
import com.behere.portal.dao.LawAppPoliceInfoDao;
import com.behere.portal.domain.LawAppExaminessNumInfo;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.domain.LawAppPoliceInfo;
import com.behere.portal.service.LawAppPoliceInfoService;

@Service
public class LawappUserLoginServiceImpl implements LawAppPoliceInfoService {
    @Autowired
    private LawAppPoliceInfoDao lawAppPoliceInfoMapper;
    @Autowired
    private LawAppExaminessNumInfoDao lawAppExaminessNumInfoMapper;
    @Autowired
    private LawAppIntegralInfoDao lawAppIntegralInfoMapper;

    @Override
    public Map<String, String> mobileUserLoginQuery(String userId, String pwd, String signature, String timestamp) {

        Map<String, String> loginMap = new HashMap<String, String>();
        
//        if (StringUtils.isEmpty(signature)) {
//            loginMap.put("messageCode", Constants.FAILURE.getCode());
//            loginMap.put("message", "参数signature为空");
//        } else if (StringUtils.isEmpty(timestamp)) {
//            loginMap.put("messageCode", Constants.FAILURE.getCode());
//            loginMap.put("message", "参数timestamp为空");
//        } else if (StringUtils.isEmpty(userId)) {
//            loginMap.put("messageCode", Constants.FAILURE.getCode());
//            loginMap.put("message", "参数userId为空");
//        } else if (StringUtils.isEmpty(pwd)) {
//            loginMap.put("messageCode", Constants.FAILURE.getCode());
//            loginMap.put("message", "参数pwd为空");
//        } else {
//            String newSignature = ShaEncryptUtil.convertSignature(timestamp, userId);
//            if (!signature.equals(newSignature)) {
//                loginMap.put("messageCode", Constants.FAILURE.getCode());
//                loginMap.put("message", "参数signature后台比对不一致");
//            } else {
//            	LawAppPoliceInfo resultUserBean = lawAppPoliceInfoMapper.selectById(userId);
//                if(resultUserBean == null) {
//                	loginMap.put("messageCode", "-1");
//                    loginMap.put("message","账号不存在或重新注册");
//                }else {
//                	String newPwd = EncryptUtil.MD5Encode(pwd).substring(8, 24);
//                	resultUserBean = lawAppPoliceInfoMapper.userLogin(userId, newPwd);
//                	//   resultUserBean.setPwd(substring);
//                	if (null == resultUserBean) {
//                		loginMap.put("messageCode", Constants.MESSAGE_1010.getCode());
//                		loginMap.put("message", Constants.MESSAGE_1010.getDesc());
//                	} else {
//                		loginMap.put("messageCode", Constants.SUCCESS.getCode());
//                		loginMap.put("message", Constants.SUCCESS.getDesc());
//                		loginMap.put("primaryId", resultUserBean.getPrimaryId());
//                		loginMap.put("userId", resultUserBean.getUserId());
//                		loginMap.put("deptId", resultUserBean.getDeptId());
//                		loginMap.put("userName", resultUserBean.getUserName());
//                		loginMap.put("jobLevel", resultUserBean.getJobLevel());
//                		ValidateMobileAccessToken.addLoginAccessToken(resultUserBean.getUserId(), signature);
//                		
//                	}
//                }
//            }
//        }
        return loginMap;
    }

    @Override
    public int updatePoliceInfo(String userId, String policePosition, String dwdm, String policePhone, String policeGender, String userName, String xp) {
        LawAppPoliceInfo lawAppPoliceInfo = lawAppPoliceInfoMapper.selectById(userId);
        if (lawAppPoliceInfo == null) {
            return -1;
        }
        lawAppPoliceInfo.setPolicePosition(policePosition);
        //lawAppPoliceInfo.setDwdm(dwdm);
        lawAppPoliceInfo.setUnitAddress(dwdm);
        if(!StringUtils.isEmpty(dwdm)) {
			String unitAddress[] = dwdm.split("-");
			if(unitAddress.length == 4) {
				lawAppPoliceInfo.setDeptId(unitAddress[3]);
				lawAppPoliceInfo.setDwdm(unitAddress[3]);
			}else if(unitAddress.length == 3) {
				lawAppPoliceInfo.setDeptId(unitAddress[2]);
				lawAppPoliceInfo.setDwdm(unitAddress[2]);
			}else {
				lawAppPoliceInfo.setDeptId(unitAddress[1]);
				lawAppPoliceInfo.setDwdm(unitAddress[1]);
			}
		}
        lawAppPoliceInfo.setUserId(userId);
        lawAppPoliceInfo.setPolicePhone(policePhone);
        lawAppPoliceInfo.setPoliceGender(policeGender);
        lawAppPoliceInfo.setUserName(userName);
        lawAppPoliceInfo.setXp(xp);
        return lawAppPoliceInfoMapper.updatePoliceInfo(lawAppPoliceInfo);
    }

    @Override
    public LawAppPoliceInfo selectById(String userId) {
        LawAppExaminessNumInfo examinNum = lawAppExaminessNumInfoMapper.getExaminNum(userId);
        Integer newExaminNum = examinNum.getExaminNum();
        LawAppIntegralInfo lawAppIntegralInfo = lawAppIntegralInfoMapper.selectIntegralNum(userId);
        Integer integralNumber = lawAppIntegralInfo.getIntegralSum();
        LawAppPoliceInfo lawAppPoliceInfo = new LawAppPoliceInfo();
        //获取总积分
        lawAppPoliceInfo.setIntegralNum(integralNumber);
        //获取总答题数
        lawAppPoliceInfo.setNewExaminNum(newExaminNum);
        lawAppPoliceInfo.setUserId(userId);
        lawAppPoliceInfoMapper.updateUserInfo(lawAppPoliceInfo);
        return lawAppPoliceInfoMapper.selectById(userId);
    }

	@Override
	public int saveUsers(LawAppPoliceInfo appPoliceInfo) {
		try {
			appPoliceInfo.setPrimaryId(appPoliceInfo.getUserId());
			appPoliceInfo.setCreateBy(appPoliceInfo.getUserName());
			appPoliceInfo.setCreatedTime(new Date());
			appPoliceInfo.setSource("1");
			//密码加密
//			String newPwd = EncryptUtil.MD5Encode(appPoliceInfo.getPwd()).substring(8, 24);
			String newPwd = null;
			appPoliceInfo.setPwd(newPwd);
			if(!StringUtils.isEmpty(appPoliceInfo.getUnitAddress())) {
				String unitAddress[] = appPoliceInfo.getUnitAddress().split("-");
				if(unitAddress.length == 4) {
					appPoliceInfo.setDeptId(unitAddress[3]);
					appPoliceInfo.setDwdm(unitAddress[3]);
				}else if(unitAddress.length == 3) {
					appPoliceInfo.setDeptId(unitAddress[2]);
					appPoliceInfo.setDwdm(unitAddress[2]);
				}else {
					appPoliceInfo.setDeptId(unitAddress[1]);
					appPoliceInfo.setDwdm(unitAddress[1]);
				}
			}
			return lawAppPoliceInfoMapper.insert(appPoliceInfo);
		} catch (Exception e) {
			e.getLocalizedMessage();
			e.getMessage();
			return -1;
		}
		
	}

	@Override
	public List<Map<String, Object>> qryUserExamPages(Map<String, Object> map) {
		return lawAppPoliceInfoMapper.selectByUserExamQues(map);
	}

	@Override
	public List<Map<String, Object>> qryUnitList(Map<String, Object> exams) {
		// TODO Auto-generated method stub
		return lawAppPoliceInfoMapper.qryUnitList(exams);
	}

	@Override
	public List<Map<String, Object>> qryAppNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return lawAppPoliceInfoMapper.qryAppNo(map);
	}

	@Override
	public List<Map<String, Object>> qryDict(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return lawAppPoliceInfoMapper.qryDict(map);
	}
}