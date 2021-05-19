package com.behere.portal.domain;

import java.io.Serializable;

public class LawAppPoliceExam implements Serializable {


    private static final long serialVersionUID = 7695062990468864000L;

    private String userName;    //用户姓名

    private String policeGender;//性别 1是男，2是女

    private String gmsfhm;//身份证号

    private String dwdm;//所在单位

    private String params;//用户参数

    private String jzlb;//警种类型

    private String pwd;//登陆密码

    private String unitAddress;//单位地址

    private String yhxmpy;//用户姓名拼音

    private String description;//用户描述

    private String deptId;//单位编号

    private String userId;//警号  登陆账号

    private String policePosition; //职位

    private String jobLevel;//职务级别

    private String fszwjb;//附属职务级别

    private String yhdwjb;//用户管辖级别

    private String gzgw;//工作岗位

    private String policePhone;//联系电话

    private String msginit;//消息初始化窗体

    private String userIp;//用户IP

    private String yxbs;//有效标识

    private String isadmin;//是否管理员

    private String yhxlh;//用户序列号

    private String czsj;//用户操作时间

    private String czdw;//用户操作单位

    private String czr;//用户操作人

    private String sfdg;//是否多岗

    private String email;//电子邮箱

    private String gzzt;//工作状态

    private String ywqxjb;//业务权限级别

    private String glylb;//管理员类别

    private String zyjd;//资源节点

    private String fjh;//房间号

    private String bgswx;//办公室外线

    private String gazx;//公安专线

    private String sjxh;//手机小号

    private String sth;//手台号

    private String xxzt;//消息状态

    private String wxh;//微信号

    private String yhbs;//用户标识

    private String khhmc;//开户行名称

    private String khhdz;//银行卡开户行地址

    private String yhkh;//银行卡号

    private String jwthm;//警务通号码

    private String gaemail;//公安网邮箱

    private String sfjdfzy;//是否监督法制员

    private String jwsbm;//警务室编码

    //private String timestamp;//时间戳

    private String signature;//加密串

    private String deviceId;//设备编号

    private Integer revision;//乐观锁


    private Integer interalnum;//积分总数


    private Integer newexaminnum;//积分总数


    private byte[] xp; //用户相片
    
    private String source;//来源
    
    private String age;//年龄
    
    private String education;//学历
    
    private String mz;//民族
    
    
    private String unitName;//单位名称
    


    public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMz() {
		return mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Integer getNewexaminnum() {
        return newexaminnum;
    }

    public void setNewexaminnum(Integer newexaminnum) {
        this.newexaminnum = newexaminnum;
    }

    public Integer getInteralnum() {
        return interalnum;
    }

    public void setInteralnum(Integer interalnum) {
        this.interalnum = interalnum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPoliceGender() {
        return policeGender;
    }

    public void setPoliceGender(String policeGender) {
        this.policeGender = policeGender == null ? null : policeGender.trim();
    }

    public String getGmsfhm() {
        return gmsfhm;
    }

    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm == null ? null : dwdm.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getJzlb() {
        return jzlb;
    }

    public void setJzlb(String jzlb) {
        this.jzlb = jzlb == null ? null : jzlb.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getUnitAddress() {
        return unitAddress;
    }

    public void setUnitAddress(String unitAddress) {
        this.unitAddress = unitAddress == null ? null : unitAddress.trim();
    }

    public String getYhxmpy() {
        return yhxmpy;
    }

    public void setYhxmpy(String yhxmpy) {
        this.yhxmpy = yhxmpy == null ? null : yhxmpy.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPolicePosition() {
        return policePosition;
    }

    public void setPolicePosition(String policePosition) {
        this.policePosition = policePosition == null ? null : policePosition.trim();
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel == null ? null : jobLevel.trim();
    }

    public String getFszwjb() {
        return fszwjb;
    }

    public void setFszwjb(String fszwjb) {
        this.fszwjb = fszwjb == null ? null : fszwjb.trim();
    }

    public String getYhdwjb() {
        return yhdwjb;
    }

    public void setYhdwjb(String yhdwjb) {
        this.yhdwjb = yhdwjb == null ? null : yhdwjb.trim();
    }

    public String getGzgw() {
        return gzgw;
    }

    public void setGzgw(String gzgw) {
        this.gzgw = gzgw == null ? null : gzgw.trim();
    }

    public String getPolicePhone() {
        return policePhone;
    }

    public void setPolicePhone(String policePhone) {
        this.policePhone = policePhone;
    }

    public String getMsginit() {
        return msginit;
    }

    public void setMsginit(String msginit) {
        this.msginit = msginit == null ? null : msginit.trim();
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    public String getYxbs() {
        return yxbs;
    }

    public void setYxbs(String yxbs) {
        this.yxbs = yxbs == null ? null : yxbs.trim();
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin == null ? null : isadmin.trim();
    }

    public String getYhxlh() {
        return yhxlh;
    }

    public void setYhxlh(String yhxlh) {
        this.yhxlh = yhxlh == null ? null : yhxlh.trim();
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj == null ? null : czsj.trim();
    }

    public String getCzdw() {
        return czdw;
    }

    public void setCzdw(String czdw) {
        this.czdw = czdw == null ? null : czdw.trim();
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr == null ? null : czr.trim();
    }

    public String getSfdg() {
        return sfdg;
    }

    public void setSfdg(String sfdg) {
        this.sfdg = sfdg == null ? null : sfdg.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getGzzt() {
        return gzzt;
    }

    public void setGzzt(String gzzt) {
        this.gzzt = gzzt == null ? null : gzzt.trim();
    }

    public String getYwqxjb() {
        return ywqxjb;
    }

    public void setYwqxjb(String ywqxjb) {
        this.ywqxjb = ywqxjb == null ? null : ywqxjb.trim();
    }

    public String getGlylb() {
        return glylb;
    }

    public void setGlylb(String glylb) {
        this.glylb = glylb == null ? null : glylb.trim();
    }

    public String getZyjd() {
        return zyjd;
    }

    public void setZyjd(String zyjd) {
        this.zyjd = zyjd == null ? null : zyjd.trim();
    }

    public String getFjh() {
        return fjh;
    }

    public void setFjh(String fjh) {
        this.fjh = fjh == null ? null : fjh.trim();
    }

    public String getBgswx() {
        return bgswx;
    }

    public void setBgswx(String bgswx) {
        this.bgswx = bgswx == null ? null : bgswx.trim();
    }

    public String getGazx() {
        return gazx;
    }

    public void setGazx(String gazx) {
        this.gazx = gazx == null ? null : gazx.trim();
    }

    public String getSjxh() {
        return sjxh;
    }

    public void setSjxh(String sjxh) {
        this.sjxh = sjxh == null ? null : sjxh.trim();
    }

    public String getSth() {
        return sth;
    }

    public void setSth(String sth) {
        this.sth = sth == null ? null : sth.trim();
    }

    public String getXxzt() {
        return xxzt;
    }

    public void setXxzt(String xxzt) {
        this.xxzt = xxzt == null ? null : xxzt.trim();
    }

    public String getWxh() {
        return wxh;
    }

    public void setWxh(String wxh) {
        this.wxh = wxh == null ? null : wxh.trim();
    }

    public String getYhbs() {
        return yhbs;
    }

    public void setYhbs(String yhbs) {
        this.yhbs = yhbs == null ? null : yhbs.trim();
    }

    public String getKhhmc() {
        return khhmc;
    }

    public void setKhhmc(String khhmc) {
        this.khhmc = khhmc == null ? null : khhmc.trim();
    }

    public String getKhhdz() {
        return khhdz;
    }

    public void setKhhdz(String khhdz) {
        this.khhdz = khhdz == null ? null : khhdz.trim();
    }

    public String getYhkh() {
        return yhkh;
    }

    public void setYhkh(String yhkh) {
        this.yhkh = yhkh == null ? null : yhkh.trim();
    }

    public String getJwthm() {
        return jwthm;
    }

    public void setJwthm(String jwthm) {
        this.jwthm = jwthm == null ? null : jwthm.trim();
    }

    public String getGaemail() {
        return gaemail;
    }

    public void setGaemail(String gaemail) {
        this.gaemail = gaemail == null ? null : gaemail.trim();
    }

    public String getSfjdfzy() {
        return sfjdfzy;
    }

    public void setSfjdfzy(String sfjdfzy) {
        this.sfjdfzy = sfjdfzy == null ? null : sfjdfzy.trim();
    }

    public String getJwsbm() {
        return jwsbm;
    }

    public void setJwsbm(String jwsbm) {
        this.jwsbm = jwsbm == null ? null : jwsbm.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public Integer getRevision() {
        return revision;
    }

    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    public byte[] getXp() {
        return xp;
    }

    public void setXp(byte[] xp) {
        this.xp = xp;
    }
}