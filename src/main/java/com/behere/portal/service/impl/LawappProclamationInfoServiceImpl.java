package com.behere.portal.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.common.utils.Query;
import com.behere.portal.dao.LawappProclamationInfoDao;
import com.behere.portal.domain.LawappProclamationInfo;
import com.behere.portal.service.LawappProclamationInfoService;

@Service
public class LawappProclamationInfoServiceImpl implements LawappProclamationInfoService {

	@Autowired
	private LawappProclamationInfoDao lawappProclamationInfoDao;

	@Override
	public LawappProclamationInfo selectById(String id) {
		return lawappProclamationInfoDao.selectById(id);
	}

	@Override
	public LawappProclamationInfo selectByEntity(LawappProclamationInfo t, Map<String, Object> paramMap) {
		return lawappProclamationInfoDao.selectByEntity(t);
	}

	@Override
	public List<LawappProclamationInfo> selectByEntityList(LawappProclamationInfo t, Map<String, Object> paramMap) {
		return lawappProclamationInfoDao.selectByEntityList(t);
	}

	@Override
	public List<LawappProclamationInfo> selectByIdList(String[] idList, Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public int create(LawappProclamationInfo t) {
		return 0;
	}

	@Override
	public int update(LawappProclamationInfo t) {
		return 0;
	}

	@Override
	public int deleteById(String id) {
		return 0;
	}

	@Override
	public int deleteByEntity(LawappProclamationInfo t) {
		return 0;
	}

	@Override
	public int deleteByIdList(String[] idList) {
		return 0;
	}

}
