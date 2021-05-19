package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 填空题信息表
 */
public class LawAppFillQuesInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7879195207234034231L;

	private String fillId;

    private String fillName;

    private String fillAnsw;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;
    private String quesType;
    private String choExam; //考点
    private String choAnalysis;//解析

    public String getChoExam() {
        return choExam;
    }

    public void setChoExam(String choExam) {
        this.choExam = choExam;
    }

    public String getChoAnalysis() {
        return choAnalysis;
    }

    public void setChoAnalysis(String choAnalysis) {
        this.choAnalysis = choAnalysis;
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType;
    }

    public String getFillId() {
        return fillId;
    }

    public void setFillId(String fillId) {
        this.fillId = fillId == null ? null : fillId.trim();
    }

    public String getFillName() {
        return fillName;
    }

    public void setFillName(String fillName) {
        this.fillName = fillName == null ? null : fillName.trim();
    }

    public String getFillAnsw() {
        return fillAnsw;
    }

    public void setFillAnsw(String fillAnsw) {
        this.fillAnsw = fillAnsw == null ? null : fillAnsw.trim();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}