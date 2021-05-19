package com.behere.blog.service;

import java.util.List;
import java.util.Map;

import com.behere.blog.domain.ContentDO;

/**
 * 文章内容
 */
public interface ContentService {
	
	ContentDO get(Long cid);
	
	List<ContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContentDO bContent);
	
	int update(ContentDO bContent);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}
