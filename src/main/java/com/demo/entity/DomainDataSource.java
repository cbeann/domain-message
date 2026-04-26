package com.demo.entity;

import com.demo.entity.common.CommonDomain;
import lombok.Data;

/**
 * @author chaird
 * @create 2025-03-15 22:58
 */
@Data
public class DomainDataSource extends CommonDomain {


    private Long domainId;


    private Long domainEntityId;

    private String databaseName;

    private String tableName;

    private Integer tableType;

    private String entityIdNameMapTableField;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDomainEntityId(Long domainEntityId) {
        this.domainEntityId = domainEntityId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityIdNameMapTableField() {
        return entityIdNameMapTableField;
    }

    public void setEntityIdNameMapTableField(String entityIdNameMapTableField) {
        this.entityIdNameMapTableField = entityIdNameMapTableField;
    }

    public Long getDomainEntityId() {
        return domainEntityId;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public Integer getTableType() {
        return tableType;
    }


    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }
}
