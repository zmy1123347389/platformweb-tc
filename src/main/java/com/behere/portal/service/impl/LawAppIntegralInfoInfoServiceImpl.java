package com.behere.portal.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.LawAppIntegralInfoDao;
import com.behere.portal.domain.LawAppIntegralInfo;
import com.behere.portal.service.LawAppIntegralInfoService;

/**
 * @ClassName：IntegralServiceImpl
 * @Auther: 京京
 * @Date: 2020/3/17 01:31
 * @Description:
 */
@Service
public class LawAppIntegralInfoInfoServiceImpl implements LawAppIntegralInfoService {

    private static Date lastTime;
    private final SimpleDateFormat shortSdf;
    private final SimpleDateFormat longSdf;
    @Autowired
    private LawAppIntegralInfoDao lawAppIntegralInfoMapper;

    public LawAppIntegralInfoInfoServiceImpl() {
        this.shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        this.longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public List<Map<String, Object>> selectRanking(Map<String, Object> map) {
        //1周2月3年
        Calendar c = Calendar.getInstance();
        String timeType = map.get("timeType").toString();
        //周排名
        try {
        	if (timeType.equals("1")) {
        		int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
        		c.add(Calendar.DATE, -weekday);
        		c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
        		lastTime = c.getTime();
        	}
        	//月排名
        	else if (timeType.equals("2")) {
        		c.set(Calendar.DATE, 1);
        		lastTime = shortSdf.parse(shortSdf.format(c.getTime()));
        	}
        	//年排名
        	else if (timeType.equals("3")) {
        		c.set(Calendar.MONTH, 0);
        		c.set(Calendar.DATE, 1);
        		lastTime = shortSdf.parse(shortSdf.format(c.getTime()));
        	}
        	map.put("lastTime", lastTime);
        } catch (ParseException e) {
        	e.printStackTrace();
        }

        return lawAppIntegralInfoMapper.selectRanking(map);
    }

    @Override
    public int insertIntegral(LawAppIntegralInfo record) {
    	record.setCreatedTime(new Date());
        return lawAppIntegralInfoMapper.insertIntegral(record);
    }

    @Override
    public int insertIntegral(Map<String, String> map) {
        LawAppIntegralInfo lawAppIntegralInfo = new LawAppIntegralInfo();
        lawAppIntegralInfo.setUserId(map.get("userId"));
        String jfType = map.get("integralType");
        lawAppIntegralInfo.setIntegralType(jfType);
        if (jfType.equals("1")) {
        	if(!StringUtils.isEmpty(map.get("apptype")) && map.get("apptype").equals("tc") ) {
        		lawAppIntegralInfo.setIntegralNumber(1);
        	}else {
        		lawAppIntegralInfo.setIntegralNumber(2);
        	}
        } else if (jfType.equals("2")) {
        	if(!StringUtils.isEmpty(map.get("apptype")) && map.get("apptype").equals("tc")) {
        		lawAppIntegralInfo.setIntegralNumber(1);
        	}else {
        		lawAppIntegralInfo.setIntegralNumber(2);
        	}
        } else if (jfType.equals("3")) {
        	
            lawAppIntegralInfo.setIntegralNumber(2);
            
        } else if (jfType.equals("4")) {
            lawAppIntegralInfo.setIntegralNumber(2);
        } else if (jfType.equals("5")) {
        	if(!StringUtils.isEmpty(map.get("apptype")) && map.get("apptype").equals("tc")) {
        		lawAppIntegralInfo.setIntegralNumber(1);
        	}else {
        		lawAppIntegralInfo.setIntegralNumber(2);
        	}
        } else if (jfType.equals("9") || jfType.equals("13") || jfType.equals("14") || jfType.equals("15") || jfType.equals("16") ) {
        	if(!StringUtils.isEmpty(map.get("apptype")) && map.get("apptype").equals("tc")) {
        		lawAppIntegralInfo.setIntegralNumber(1);
        	}else {
        		lawAppIntegralInfo.setIntegralNumber(2);
        	}
        } else if (jfType.equals("7")) {
        	//模拟答题：根据每一道题的正确性加积分;
        	// 60/100*总量
        	if(!StringUtils.isEmpty(map.get("apptype")) && map.get("apptype").equals("tc")) {
        		lawAppIntegralInfo.setIntegralNumber(2);
        	}else {
        		if(StringUtils.isEmpty(map.get("nums"))) {
        			map.put("nums","0");
        		}
        		lawAppIntegralInfo.setIntegralNumber(Integer.parseInt(map.get("nums")));
        	}
        } else if(jfType.equals("8")){
        	
        }else if(jfType.equals("17")){
        	lawAppIntegralInfo.setIntegralNumber(1);
        }else{
        	lawAppIntegralInfo.setIntegralNumber(0);
        }
        return lawAppIntegralInfoMapper.insertIntegral(lawAppIntegralInfo);
    }

	@Override
	public List<Map<String, Object>> qryJfpmPagesByPro(Map<String, Object> map) {
		return lawAppIntegralInfoMapper.qryJfpmPagesByPro(map);
	}

	@Override
	public List<Map<String, Object>> qryJfpmPages(Map<String, Object> map) {
		return lawAppIntegralInfoMapper.qryJfpmPages(map);
	}

	@Override
	public List<Map<String, Object>> qryJfByuserId(LawAppIntegralInfo appIntegralInfo) {
		return lawAppIntegralInfoMapper.qryByUserId(appIntegralInfo);
	}


}
