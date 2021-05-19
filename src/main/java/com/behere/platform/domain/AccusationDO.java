package com.behere.platform.domain;

import java.util.Date;
import java.util.List;

/**
 * @author: Behere
 */
public class AccusationDO {

    private String id;

    private long reportUserId;

    private long reportedUserId;

    private String reportUserName;

    private String reportedUserName;

    private String accusation;

    private String content;

    private List<AccusationPic> pics;

    private int accusationId;

    private Date createTime;

    private int deleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public long getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(long reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public String getReportUserName() {
        return reportUserName;
    }

    public void setReportUserName(String reportUserName) {
        this.reportUserName = reportUserName;
    }

    public String getReportedUserName() {
        return reportedUserName;
    }

    public void setReportedUserName(String reportedUserName) {
        this.reportedUserName = reportedUserName;
    }

    public String getAccusation() {
        return accusation;
    }

    public void setAccusation(String accusation) {
        this.accusation = accusation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AccusationPic> getPics() {
        return pics;
    }

    public void setPics(List<AccusationPic> pics) {
        this.pics = pics;
    }

    public int getAccusationId() {
        return accusationId;
    }

    public void setAccusationId(int accusationId) {
        this.accusationId = accusationId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}