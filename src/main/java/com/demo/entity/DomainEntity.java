package com.demo.entity;

import com.demo.entity.common.CommonDomain;

public class DomainEntity extends CommonDomain {

    private Long domainId;

    private String entityName;

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityIdName() {
        return entityIdName;
    }

    public void setEntityIdName(String entityIdName) {
        this.entityIdName = entityIdName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String entityIdName;

    private String description;




}
