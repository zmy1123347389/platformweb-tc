package com.behere.portal.domain;

/**
 * @ClassName：LawAppExaminessNumInfo
 * @Auther: 京京
 * @Date: 2020/3/28 22:07
 * @Description:
 */
public class LawAppExaminessNumInfo {
    private String examsId;
    private Integer examinNum; //答题次数

    public String getExamsId() {
        return examsId;
    }

    public void setExamsId(String examsId) {
        this.examsId = examsId;
    }

    public Integer getExaminNum() {
        return examinNum;
    }

    public void setExaminNum(Integer examinNum) {
        this.examinNum = examinNum;
    }
}
