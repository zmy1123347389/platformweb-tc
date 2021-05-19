package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 试卷信息表
 */
public class LawAppExaminPapersInfo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6175648489441229876L;

    private String paperId;

    private String scqType;

    private String paperName;

    private String paperTime;

    private Integer paperTScores;

    private Integer scqNum;

    private Integer scqSScores;

    private Integer jqNum;

    private Integer jqSScores;

    private Integer aqNum;

    private Integer aqSScores;

    private Integer fbNum;

    private Integer fbSScores;

    private Integer askNum;

    private Integer askScores;
    private List<LawAppPapersQuesrelInfo> lawAppPapersQuesrelInfo = Lists.newArrayList();
    private List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo = Lists.newArrayList();
    private List<LawAppEssayQuesInfo> lawAppEssayQuesInfo = Lists.newArrayList();
    private List<LawAppFillQuesInfo> lawAppFillQuesInfo = Lists.newArrayList();
    private List<LawAppJudgeQuesInfo> lawAppJudgeQuesInfo = Lists.newArrayList();
    private String createdBy;
    private Date createdTime;
    private String updatedBy;
    private Date updatedTime;
    
    
    private String startTime;
    
    private String endTime;
    
    private String authCode;
    
    private String isRandom;
    
    
    private Integer isAnswered;





	public Integer getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(Integer isAnswered) {
		this.isAnswered = isAnswered;
	}

	public String getIsRandom() {
		return isRandom;
	}

	public void setIsRandom(String isRandom) {
		this.isRandom = isRandom;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getAskScores() {
        return askScores ==null ? 0 : askScores;
    }

    public void setAskScores(Integer askScores) {
        this.askScores = askScores ;
    }

    public List<LawAppPapersQuesrelInfo> getLawAppPapersQuesrelInfo() {
        return lawAppPapersQuesrelInfo;
    }

    public void setLawAppPapersQuesrelInfo(List<LawAppPapersQuesrelInfo> lawAppPapersQuesrelInfo) {
        this.lawAppPapersQuesrelInfo = lawAppPapersQuesrelInfo;
    }

    public List<LawAppChoiceQuesInfo> getLawAppChoiceQuesInfo() {
        return lawAppChoiceQuesInfo;
    }

    public void setLawAppChoiceQuesInfo(List<LawAppChoiceQuesInfo> lawAppChoiceQuesInfo) {
        this.lawAppChoiceQuesInfo = lawAppChoiceQuesInfo;
    }

    public List<LawAppEssayQuesInfo> getLawAppEssayQuesInfo() {
        return lawAppEssayQuesInfo;
    }

    public void setLawAppEssayQuesInfo(List<LawAppEssayQuesInfo> lawAppEssayQuesInfo) {
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

    public Integer getAskNum() {
        return askNum ==null ? 0 : askNum;
    }

    public void setAskNum(Integer askNum) {
        this.askNum = askNum ;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getScqType() {
        return scqType;
    }

    public void setScqType(String scqType) {
        this.scqType = scqType == null ? null : scqType.trim();
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public String getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(String paperTime) {
        this.paperTime = paperTime;
    }

    public Integer getPaperTScores() {
        return paperTScores ==null ? 0 : paperTScores;
    }

    public void setPaperTScores(Integer paperTScores) {
        this.paperTScores = paperTScores ;
    }

    public Integer getScqNum() {
        return scqNum  ==null ? 0 : scqNum;
    }

    public void setScqNum(Integer scqNum) {
        this.scqNum = scqNum;
    }

    public Integer getScqSScores() {
        return scqSScores == null ? 0 : scqSScores;
    }

    public void setScqSScores(Integer scqSScores) {
        this.scqSScores = scqSScores ;
    }

    public Integer getJqNum() {
        return jqNum  ==null ? 0 : jqNum;
    }

    public void setJqNum(Integer jqNum) {
        this.jqNum = jqNum ;
    }

    public Integer getJqSScores() {
        return jqSScores ==null ? 0 : jqSScores;
    }

    public void setJqSScores(Integer jqSScores) {
        this.jqSScores = jqSScores ;
    }

    public Integer getAqNum() {
        return aqNum  ==null ? 0 : aqNum;
    }

    public void setAqNum(Integer aqNum) {
        this.aqNum = aqNum ;
    }

    public Integer getAqSScores() {
        return aqSScores == null ? 0 : aqSScores;
    }

    public void setAqSScores(Integer aqSScores) {
        this.aqSScores = aqSScores ;
    }

    public Integer getFbNum() {
        return fbNum == null ? 0 : fbNum;
    }

    public void setFbNum(Integer fbNum) {
        this.fbNum = fbNum ;
    }

    public Integer getFbSScores() {
        return fbSScores == null ? 0 : fbSScores;
    }

    public void setFbSScores(Integer fbSScores) {
        this.fbSScores = fbSScores  ;
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

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

    
    
    
}