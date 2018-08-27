package com.redis.demo.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {


    private static final long serialVersionUID = 445028251601971984L;
    private String tel;

    private String nickName;


    private String pwd;

    public UserInfo() {
    }

    public UserInfo(String tel, String nickName, String pwd) {
        this.tel = tel;
        this.nickName = nickName;
        this.pwd = pwd;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
