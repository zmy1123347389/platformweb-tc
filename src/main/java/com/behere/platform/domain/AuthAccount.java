package com.behere.platform.domain;

/**
 * @author: Behere
 */
public class AuthAccount {

    private long userId;

    private String nickName;

    private double flower;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getFlower() {
        return flower;
    }

    public void setFlower(double flower) {
        this.flower = flower;
    }
}