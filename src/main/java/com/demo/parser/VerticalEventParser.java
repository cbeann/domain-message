package com.demo.parser;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.entity.DomainEvent;
import com.demo.model.BinLogModel;
import com.demo.model.EventModel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chaird
 * @create 2025-03-16 0:32
 */
@Component
public class VerticalEventParser extends EventParser {


    //没写

    @Override
    public EventModel insertLog2Event(BinLogModel binLogModel, DomainEvent domainEvent) {
        return null;
    }

    @Override
    public EventModel deleteLog2Event(BinLogModel binLogModel, DomainEvent domainEvent) {
        return null;
    }

    @Override
    public EventModel updateLog2Event(BinLogModel binLogModel, DomainEvent domainEvent) {
        return null;
    }
}
