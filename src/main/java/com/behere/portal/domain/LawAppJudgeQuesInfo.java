package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 判断题信息表
 */
public class LawAppJudgeQuesInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8749750259405322301L;

	private String judgeId;

    private String judgeName;

    private String judgeAnsw;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private String quesType;

    private String choExam; //考点
    private String choAnalysis;//解析
    
    private  String quesId;//试题类型
    

    public String getQuesId() {
		return quesId;
	}

	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}

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

    public String getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(String judgeId) {
        this.judgeId = judgeId == null ? null : judgeId.trim();
    }

    public String getJudgeName() {
        return judgeName;
    }

    public void setJudgeName(String judgeName) {
        this.judgeName = judgeName == null ? null : judgeName.trim();
    }

    public String getJudgeAnsw() {
        return judgeAnsw;
    }

    public void setJudgeAnsw(String judgeAnsw) {
        this.judgeAnsw = judgeAnsw == null ? null : judgeAnsw.trim();
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