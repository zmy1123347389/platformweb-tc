package com.behere.activiti.service;

import java.util.List;
import java.util.Map;

import com.behere.activiti.domain.SalaryDO;

/**
 * 审批流程测试表
 */
public interface SalaryService {
	
	SalaryDO get(String id);
	
	List<SalaryDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SalaryDO salary);
	
	int update(SalaryDO salary);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
