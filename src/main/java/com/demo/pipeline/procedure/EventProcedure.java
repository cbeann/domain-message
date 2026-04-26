package com.demo.pipeline.procedure;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.entity.DomainDataSource;
import com.demo.entity.DomainEvent;
import com.demo.model.BinLogModel;
import com.demo.model.EventModel;
import com.demo.parser.EventParser;
import com.demo.parser.EventParserFactory;
import com.demo.parser.HorizontalEventParser;
import com.demo.parser.VerticalEventParser;
import com.demo.pipeline.Procedure;
import com.demo.repository.DomainDataSourceRepository;
import com.demo.repository.DomainEventRepository;
import com.demo.utils.BinLogModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventProcedure implements Procedure {
    @Autowired
    private DomainDataSourceRepository domainDataSourceRepository;

    @Autowired
    private DomainEventRepository domainEventRepository;

    @Autowired
    private HorizontalEventParser horizontalEventParser;

    @Autowired
    private VerticalEventParser verticalEventParser;


    @Override
    public Object handleOne(Object obj) {

        if (!(obj instanceof CanalEntry.Entry)) {
            return null;
        }

        CanalEntry.Entry entry = (CanalEntry.Entry) obj;

        List<BinLogModel> binLogModelList = BinLogModelUtils.convert2BinLogModel(entry);
        if (CollectionUtils.isEmpty(binLogModelList)) {
            return null;
        }

        String tableName = entry.getHeader().getTableName();
        String databaseName = entry.getHeader().getSchemaName();
        List<DomainEvent> domainEventList = domainEventRepository.getEventByDbTable(databaseName, tableName);

        if (CollectionUtils.isEmpty(domainEventList)) {
            return null;
        }

        List<EventModel> eventModelList = new ArrayList<>();

        for (DomainEvent domainEvent : domainEventList) {

            Long datasourceId = domainEvent.getDatasourceId();
            DomainDataSource dataSource = domainDataSourceRepository.getById(datasourceId);
            EventParser eventParser = null;
            if (Integer.valueOf(1).equals(dataSource.getTableType())) {
                eventParser = horizontalEventParser;
            } else {
                eventParser = verticalEventParser;
            }

            for (BinLogModel binLogModel : binLogModelList) {
                EventModel tempEventModel = eventParser.binlogToEventModel(binLogModel, domainEvent);
                if (null != tempEventModel) {
                    eventModelList.add(tempEventModel);
                }
            }

        }

        return eventModelList;

    }
}
