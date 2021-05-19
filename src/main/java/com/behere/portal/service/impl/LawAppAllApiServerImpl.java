package com.behere.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.LawAppAllApiDao;
import com.behere.portal.service.LawAppAllApiServer;


@Service
public class LawAppAllApiServerImpl implements LawAppAllApiServer {
	
	@Autowired
	private LawAppAllApiDao lawAppAllApiMapper;

	@Override
	public List<Map<String, Object>> qryAudioPages(Map<String, Object> map) {
		return lawAppAllApiMapper.qryAudioPages(map);
	}

	@Override
	public List<Map<String, Object>> qryNoticePages(Map<String, Object> map) {
		return lawAppAllApiMapper.qryNoticePages(map);
	}

	@Override
	public List<Map<String, Object>> qryCheckPass(Map<String, Object> map) {
		return lawAppAllApiMapper.qryCheckPass(map);
	}

	@Override
	public List<Map<String, Object>> qryImggeTextPages(Map<String, Object> map) {
		return lawAppAllApiMapper.qryImggeTextPages(map);
	}

	@Override
	public Map<String, Object> qryImggeTextById(Map<String, Object> map) {
		return lawAppAllApiMapper.qryImggeTextById(map);
	}

	@Override
	public int saveExamPass(Map<String, Object> map) {
		return lawAppAllApiMapper.saveExamPass(map);
	}

	@Override
	public Map<String, Object> qryCgDt(Map<String, Object> map) {
		return lawAppAllApiMapper.qryCgdtCount(map);
	}

	@Override
	public List<Map<String, Object>> qryLawappKjPages(Map<String, Object> map) {
		return lawAppAllApiMapper.qryLawappKjPages(map);
	}

	@Override
	public List<Map<String, Object>> qryJpkjImg(Map<String, Object> map) {
		return lawAppAllApiMapper.selectByPid(map);
	}

	@Override
	public List<Map<String, Object>> qryNewLawPages(Map<String, Object> map) {
		return lawAppAllApiMapper.qryNewLawPages(map);
	}

	@Override
	public Map<String, Object> qryNewLawById(Map<String, Object> map) {
		return lawAppAllApiMapper.qryNewLawById(map);
	}

}
