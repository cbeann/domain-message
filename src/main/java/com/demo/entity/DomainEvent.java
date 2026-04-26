package com.demo.entity;

import com.demo.entity.common.CommonDomain;

import java.util.List;

/**
 * @author chaird
 * @create 2025-03-15 23:24
 */
public class DomainEvent extends CommonDomain {


    private Long domainId;

    private Long domainEntityId;


    private Long datasourceId;

    private String careFields;

    private String eventName;

    private String eventType;


    private String conditionFieldName;

    private String conditionFieldValue;

    private String outputTopic;

    private String description;

    /*领域实体唯一标识对应表字段名称*/
    private String entityIdNameMapTableField;

    List<DomainEventFilterSetting> domainEventFilterSettingList;


    public List<DomainEventFilterSetting> getDomainEventFilterSettingList() {
        return domainEventFilterSettingList;
    }

    public void setDomainEventFilterSettingList(List<DomainEventFilterSetting> domainEventFilterSettingList) {
        this.domainEventFilterSettingList = domainEventFilterSettingList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public Long getDomainEntityId() {
        return domainEntityId;
    }

    public void setDomainEntityId(Long domainEntityId) {
        this.domainEntityId = domainEntityId;
    }

    public Long getDatasourceId() {
        return datasourceId;
    }

    public void setDatasourceId(Long datasourceId) {
        this.datasourceId = datasourceId;
    }

    public String getCareFields() {
        return careFields;
    }

    public void setCareFields(String careFields) {
        this.careFields = careFields;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getConditionFieldName() {
        return conditionFieldName;
    }

    public void setConditionFieldName(String conditionFieldName) {
        this.conditionFieldName = conditionFieldName;
    }

    public String getConditionFieldValue() {
        return conditionFieldValue;
    }

    public void setConditionFieldValue(String conditionFieldValue) {
        this.conditionFieldValue = conditionFieldValue;
    }

    public String getOutputTopic() {
        return outputTopic;
    }

    public void setOutputTopic(String outputTopic) {
        this.outputTopic = outputTopic;
    }

    public String getEntityIdNameMapTableField() {
        return entityIdNameMapTableField;
    }

    public void setEntityIdNameMapTableField(String entityIdNameMapTableField) {
        this.entityIdNameMapTableField = entityIdNameMapTableField;
    }
}
