package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*
积分表
 */
public class LawAppIntegralInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7475577120167423370L;

	private Integer iId;

    private String userId;

    private String integralType;

    private Integer integralNumber;

    private Integer revision;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private Integer integralSum;

    private String integralRanking;

    private List<LawAppPoliceInfo> lawAppPoliceInfos;

    public List<LawAppPoliceInfo> getLawAppPoliceInfos() {
        return lawAppPoliceInfos;
    }

    public void setLawAppPoliceInfos(List<LawAppPoliceInfo> lawAppPoliceInfos) {
        this.lawAppPoliceInfos = lawAppPoliceInfos;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getIntegralSum() {
        return integralSum;
    }

    public void setIntegralSum(Integer integralSum) {
        this.integralSum = integralSum;
    }

    public String getIntegralRanking() {
        return integralRanking;
    }

    public void setIntegralRanking(String integralRanking) {
        this.integralRanking = integralRanking;
    }

    public Integer getiId() {
        return iId;
    }

    public void setiId(Integer iId) {
        this.iId = iId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntegralType() {
        return integralType;
    }

    public void setIntegralType(String integralType) {
        this.integralType = integralType == null ? null : integralType.trim();
    }

    public Integer getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(Integer integralNumber) {
        this.integralNumber = integralNumber;
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime(String format) {
        return createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime(String format) {
        return updatedTime;
    }
}