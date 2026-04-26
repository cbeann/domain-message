package com.demo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chaird
 * @create 2025-03-22 18:30
 */
public class BinLogModel {

    private String operateType;

    private String databaseName;

    private String tableName;


    private Map<String, String> beforeData = new HashMap<>();

    private Map<String, String> afterData = new HashMap<>();

    private List<String> changeFieldList = new ArrayList<>();



    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public void setBeforeData(Map<String, String> beforeData) {
        this.beforeData = beforeData;
    }

    public Map<String, String> getBeforeData() {
        return beforeData;
    }

    public void setAfterData(Map<String, String> afterData) {
        this.afterData = afterData;
    }

    public Map<String, String> getAfterData() {
        return afterData;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public List<String> getChangeFieldList() {
        return changeFieldList;
    }

    public void setChangeFieldList(List<String> changeFieldList) {
        this.changeFieldList = changeFieldList;
    }
}
