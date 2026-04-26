package com.demo.entity.common;

import lombok.Data;

import java.util.Date;


public class CommonDomain {

    private Long id;

    private Date gmtCreate;

    private Date gmtModifed;


    public Date getGmtCreate() {
        return gmtCreate;
    }

    public Date getGmtModifed() {
        return gmtModifed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModifed(Date gmtModifed) {
        this.gmtModifed = gmtModifed;
    }
}
