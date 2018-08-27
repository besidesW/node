package com.redis.demo.entity;

import java.io.Serializable;

public class Dept implements Serializable {

    private static final long serialVersionUID = 8620305311084020880L;
    private Integer deptId;

    private String dname;

    private String loc;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
