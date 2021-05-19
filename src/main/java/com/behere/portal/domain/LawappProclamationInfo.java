package com.behere.portal.domain;

import java.io.Serializable;

public class LawappProclamationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1448324396958738962L;
	
	/**
	*id
	*/
	private String id;
	/**
	*
	*/
	private String imgName;
	/**
	*
	*/
	private String imgUrl;
	/**
	*公告名称
	*/
	private String noticeName;
	/**
	*公告内容
	*/
	private String noticeContent;
	/**
	*1是学法公告,0是学法动态,2每日更新
	*/
	private String noticeType;
	/**
	*创建人
	*/
	private String createUser;
	/**
	*创建时间
	*/
	private String createTime;
	/**
	*修改人
	*/
	private String modifyUser;
	/**
	*修改时间
	*/
	private String modifyTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getNoticeName() {
		return noticeName;
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

}
