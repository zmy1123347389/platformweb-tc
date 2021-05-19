package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 考试结果基本信息表
 */
public class LawAppPapersExaminessInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8233281220582481514L;

	private String examsId;

    private String paperId;

    private String userId;

    private Integer paperRScores;

    private String paperTimed;

    private Integer scqRScores;

    private Integer mcqRScores;

    private Integer jqRScores;

    private Integer aqRScores;

    private Integer fbRScores;

    private String createdBy;

    private Date createdTime;

    private String updatedBy;

    private Date updatedTime;

    private String scqType;

    private String state;

    private Integer integralNumber;

    private List<LawAppPoliceInfo> lawAppPoliceInfos;
    
    private String rank;//增加排名
    
    private String apptype;//apptype=tc/ya
    
    private int zqtl;//答正确题量
    
    
    private int counttl;//总题量
    

    
    
    public int getCounttl() {
		return counttl;
	}

	public void setCounttl(int counttl) {
		this.counttl = counttl;
	}

	public int getZqtl() {
		return zqtl;
	}

	public void setZqtl(int zqtl) {
		this.zqtl = zqtl;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public List<LawAppPoliceInfo> getLawAppPoliceInfos() {
        return lawAppPoliceInfos;
    }

    public void setLawAppPoliceInfos(List<LawAppPoliceInfo> lawAppPoliceInfos) {
        this.lawAppPoliceInfos = lawAppPoliceInfos;
    }

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public Integer getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(Integer integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getScqType() {
        return scqType;
    }

    public void setScqType(String scqType) {
        this.scqType = scqType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId == null ? null : paperId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPaperRScores() {
        return paperRScores;
    }

    public void setPaperRScores(Integer paperRScores) {
        this.paperRScores = paperRScores;
    }

    public String getPaperTimed() {
        return paperTimed;
    }

    public void setPaperTimed(String paperTimed) {
        this.paperTimed = paperTimed == null ? null : paperTimed.trim();
    }

    public Integer getScqRScores() {
        return scqRScores;
    }

    public void setScqRScores(Integer scqRScores) {
        this.scqRScores = scqRScores;
    }

    public Integer getMcqRScores() {
        return mcqRScores;
    }

    public void setMcqRScores(Integer mcqRScores) {
        this.mcqRScores = mcqRScores;
    }

    public Integer getJqRScores() {
        return jqRScores;
    }

    public void setJqRScores(Integer jqRScores) {
        this.jqRScores = jqRScores;
    }

    public Integer getAqRScores() {
        return aqRScores;
    }

    public void setAqRScores(Integer aqRScores) {
        this.aqRScores = aqRScores;
    }

    public Integer getFbRScores() {
        return fbRScores;
    }

    public void setFbRScores(Integer fbRScores) {
        this.fbRScores = fbRScores;
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