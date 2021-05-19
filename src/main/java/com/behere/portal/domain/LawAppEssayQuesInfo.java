package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 问答题表
 */
public class LawAppEssayQuesInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2115281908052348698L;

	private String essayId;

    private String essayName;

    private String essayAnsw;

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

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId == null ? null : essayId.trim();
    }

    public String getEssayName() {
        return essayName;
    }

    public void setEssayName(String essayName) {
        this.essayName = essayName == null ? null : essayName.trim();
    }

    public String getEssayAnsw() {
        return essayAnsw;
    }

    public void setEssayAnsw(String essayAnsw) {
        this.essayAnsw = essayAnsw == null ? null : essayAnsw.trim();
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