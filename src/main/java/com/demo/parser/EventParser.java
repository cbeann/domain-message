package com.demo.parser;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.constant.EventType;
import com.demo.entity.DomainDataSource;
import com.demo.entity.DomainEvent;
import com.demo.entity.DomainEventFilterSetting;
import com.demo.model.BinLogModel;
import com.demo.model.EventModel;
import com.demo.repository.DomainDataSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class EventParser {

    @Autowired
    protected DomainDataSourceRepository domainDataSourceRepository;

    public EventModel binlogToEventModel(BinLogModel binLogModel, DomainEvent domainEvent) {

        String operateType = binLogModel.getOperateType();

        if (EventType.INSERT.equals(operateType)) {
            return insertLog2Event(binLogModel, domainEvent);
        }
        if (EventType.UPDATE.equals(operateType)) {
            return updateLog2Event(binLogModel, domainEvent);
        }
        if (EventType.DELETE.equals(operateType)) {
            return deleteLog2Event(binLogModel, domainEvent);
        }

        return null;
    }

    public EventModel log2Event(BinLogModel binLogModel, DomainEvent domainEvent) {

        if (!domainEvent.getEventType().equals(binLogModel.getOperateType())) {
            return null;
        }

        List<DomainEventFilterSetting> domainEventFilterSettingList = domainEvent.getDomainEventFilterSettingList();
        if (!CollectionUtils.isEmpty(domainEventFilterSettingList)) {
            for (DomainEventFilterSetting domainEventFilterSetting : domainEventFilterSettingList) {
                String key = domainEventFilterSetting.getKey();
                String value = domainEventFilterSetting.getValue();

                String afterValue = binLogModel.getAfterData().get(key);
                String beforeValue = binLogModel.getBeforeData().get(key);

                if (null != afterValue && afterValue.equals(value) || null != beforeValue && beforeValue.equals(value)) {
                    //true
                } else {
                    return null;
                }
            }
        }

        EventModel eventModel = new EventModel();
        eventModel.setOperateType(domainEvent.getEventType());
        eventModel.setNewData(binLogModel.getAfterData());
        eventModel.setOldData(binLogModel.getBeforeData());
        eventModel.setDatabaseName(binLogModel.getDatabaseName());
        eventModel.setTableName(binLogModel.getTableName());
        eventModel.setTopic(domainEvent.getOutputTopic());
        eventModel.setEventName(domainEvent.getEventName());

        DomainDataSource dataSource = domainDataSourceRepository.getById(domainEvent.getDatasourceId());
        String entityIdNameMapTableField = dataSource.getEntityIdNameMapTableField();
        if (null != binLogModel.getAfterData().get(entityIdNameMapTableField)) {
            eventModel.setMsgKey(binLogModel.getAfterData().get(entityIdNameMapTableField));
        } else {
            eventModel.setMsgKey(binLogModel.getBeforeData().get(entityIdNameMapTableField));
        }

        return eventModel;

    }

    public abstract EventModel insertLog2Event(BinLogModel binLogModel, DomainEvent domainEvent);

    public abstract EventModel deleteLog2Event(BinLogModel binLogModel, DomainEvent domainEvent);

    public abstract EventModel updateLog2Event(BinLogModel binLogModel, DomainEvent domainEvent);

}
