package com.behere.portal.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 选择题表
 */
public class LawAppChoiceQuesInfo implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 2297103572699346156L;

    private String choId;

    private String choName;

    private String choOpa;

    private String choOpb;

    private String choOpc;

    private String choOpd;

    private String choOpe;

    private String choOpf;

    private String choAnsw;

    private String createUser;

    private Date createTime;

    private String modifyUser;

    private Date modifyTime;

    private String quesType;
    
    private String choExam; //考点
    
    private String choAnalysis;//解析
    
    private String quesId;//题库id
    
    

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

    public String getChoId() {
        return choId;
    }

    public void setChoId(String choId) {
        this.choId = choId == null ? null : choId.trim();
    }

    public String getChoName() {
        return choName;
    }

    public void setChoName(String choName) {
        this.choName = choName == null ? null : choName.trim();
    }

    public String getChoOpa() {
        return choOpa;
    }

    public void setChoOpa(String choOpa) {
        this.choOpa = choOpa == null ? null : choOpa.trim();
    }

    public String getChoOpb() {
        return choOpb;
    }

    public void setChoOpb(String choOpb) {
        this.choOpb = choOpb == null ? null : choOpb.trim();
    }

    public String getChoOpc() {
        return choOpc;
    }

    public void setChoOpc(String choOpc) {
        this.choOpc = choOpc == null ? null : choOpc.trim();
    }

    public String getChoOpd() {
        return choOpd;
    }

    public void setChoOpd(String choOpd) {
        this.choOpd = choOpd == null ? null : choOpd.trim();
    }

    public String getChoOpe() {
        return choOpe;
    }

    public void setChoOpe(String choOpe) {
        this.choOpe = choOpe == null ? null : choOpe.trim();
    }

    public String getChoOpf() {
        return choOpf;
    }

    public void setChoOpf(String choOpf) {
        this.choOpf = choOpf == null ? null : choOpf.trim();
    }

    public String getChoAnsw() {
        return choAnsw;
    }

    public void setChoAnsw(String choAnsw) {
        this.choAnsw = choAnsw == null ? null : choAnsw.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}