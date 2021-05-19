package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 试卷题目关系表
 */
public class LawAppPapersQuesrelInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3906274393278095812L;

	private String paperId;

    private String quesType;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo;

    private LawAppEssayQuesInfo lawAppEssayQuesInfo;

    private List<LawAppFillQuesInfo> lawAppFillQuesInfo;

    private List<LawAppJudgeQuesInfo> lawAppJudgeQuesInfo;

    public List<LawAppChoiceQuesInfo> getLawAppChoiceQuesInfo() {
        return lawAppChoiceQuesInfo;
    }

    public void setLawAppChoiceQuesInfo(List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo) {
        this.lawAppChoiceQuesInfo = lawAppChoiceQuesInfo;
    }

    public LawAppEssayQuesInfo getLawAppEssayQuesInfo() {
        return lawAppEssayQuesInfo;
    }

    public void setLawAppEssayQuesInfo(LawAppEssayQuesInfo lawAppEssayQuesInfo) {
        this.lawAppEssayQuesInfo = lawAppEssayQuesInfo;
    }

    public List<LawAppFillQuesInfo> getLawAppFillQuesInfo() {
        return lawAppFillQuesInfo;
    }

    public void setLawAppFillQuesInfo(List<LawAppFillQuesInfo> lawAppFillQuesInfo) {
        this.lawAppFillQuesInfo = lawAppFillQuesInfo;
    }

    public List<LawAppJudgeQuesInfo> getLawAppJudgeQuesInfo() {
        return lawAppJudgeQuesInfo;
    }

    public void setLawAppJudgeQuesInfo(List<LawAppJudgeQuesInfo> lawAppJudgeQuesInfo) {
        this.lawAppJudgeQuesInfo = lawAppJudgeQuesInfo;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getQuesType() {
        return quesType;
    }

    public void setQuesType(String quesType) {
        this.quesType = quesType == null ? null : quesType.trim();
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