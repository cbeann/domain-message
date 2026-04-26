package com.demo.model;

import java.util.Map;

/**
 * @author chaird
 * @create 2025-03-16 0:04
 */
public class EventModel {


    private String topic;


    private Map<String,String> oldData;


    private Map<String,String> newData;


    private String operateType;

    private String databaseName;


    private String tableName;

    private String eventName;

    private String msgKey;

    public EventModel(){

    }

    public EventModel(String databaseName, String tableName, Map<String,String> oldData, Map<String,String> newData){
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.oldData = oldData;
        this.newData = newData;

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<String, String> getOldData() {
        return oldData;
    }

    public void setOldData(Map<String, String> oldData) {
        this.oldData = oldData;
    }

    public Map<String, String> getNewData() {
        return newData;
    }

    public void setNewData(Map<String, String> newData) {
        this.newData = newData;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getMsgKey() {
        return msgKey;
    }
}
