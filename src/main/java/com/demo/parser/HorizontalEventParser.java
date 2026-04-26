package com.demo.parser;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.entity.DomainEvent;
import com.demo.entity.DomainEventFilterSetting;
import com.demo.model.BinLogModel;
import com.demo.model.EventModel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chaird
 * @create 2025-03-16 0:32
 * 横表
 */
@Component
public class HorizontalEventParser extends EventParser {





    @Override
    public  EventModel insertLog2Event(BinLogModel binLogModel, DomainEvent domainEvent){

        if (!domainEvent.getEventType().equals(binLogModel.getOperateType())){
            return null;
        }
        EventModel eventModel = log2Event(binLogModel, domainEvent);
        return eventModel;

    }

    @Override
    public EventModel deleteLog2Event(BinLogModel binLogModel, DomainEvent domainEvent) {

        if (!domainEvent.getEventType().equals(binLogModel.getOperateType())){
            return null;
        }

        EventModel eventModel = log2Event(binLogModel, domainEvent);
        return eventModel;
    }

    @Override
    public EventModel updateLog2Event(BinLogModel binLogModel, DomainEvent domainEvent) {



        EventModel eventModel = log2Event(binLogModel, domainEvent);
        return eventModel;
    }


}
