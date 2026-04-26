package com.demo.pipeline.procedure;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.entity.DomainDataSource;
import com.demo.entity.DomainEvent;
import com.demo.model.BinLogModel;
import com.demo.model.EventModel;
import com.demo.parser.EventParser;
import com.demo.parser.EventParserFactory;
import com.demo.pipeline.Procedure;
import com.demo.repository.DomainDataSourceRepository;
import com.demo.repository.DomainEventRepository;
import com.demo.utils.BinLogModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class BinlogNormalizeProcedure implements Procedure {

    @Autowired
    private DomainEventRepository domainEventRepository;

    public Object handleOne(Object obj) {

        if (!(obj instanceof CanalEntry.Entry)) {
            return null;
        }
        CanalEntry.Entry entry = (CanalEntry.Entry) obj;
        if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) {
            return null;
        }

        String tableName = entry.getHeader().getTableName();
        String databaseName = entry.getHeader().getSchemaName();

        List<DomainEvent> domainEventList = domainEventRepository.getEventByDbTable(databaseName, tableName);

        if (CollectionUtils.isEmpty(domainEventList)) {
            return null;
        }

        return obj;

    }
}
