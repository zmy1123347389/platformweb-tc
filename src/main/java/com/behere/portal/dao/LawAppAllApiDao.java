package com.behere.portal.dao;

import java.util.List;
import java.util.Map;

public interface LawAppAllApiDao {

	public List<Map<String, Object>> qryAudioPages(Map<String, Object> map);

	public List<Map<String, Object>> qryNoticePages(Map<String, Object> map);

	public List<Map<String, Object>> qryCheckPass(Map<String, Object> map);

	public List<Map<String, Object>> qryImggeTextPages(Map<String, Object> map);

	public Map<String, Object> qryImggeTextById(Map<String, Object> map);

	public int saveExamPass(Map<String, Object> map);

	public Map<String, Object> qryCgdtCount(Map<String, Object> map);

	public List<Map<String, Object>> qryLawappKjPages(Map<String, Object> map);
	
	List<Map<String, Object>> selectByPid (Map<String, Object> record);

	public List<Map<String, Object>> qryNewLawPages(Map<String, Object> map);

	public Map<String, Object> qryNewLawById(Map<String, Object> map);
}
