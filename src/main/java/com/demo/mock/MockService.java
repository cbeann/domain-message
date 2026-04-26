package com.demo.mock;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.springframework.util.CollectionUtils;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

public class MockService {

    public void mockList(){

        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("127.0.0.1", 11111), "example", "", "");
        Message message = connector.getWithoutAck(100);

        List<CanalEntry.Entry> entries = message.getEntries();
        if (CollectionUtils.isEmpty(entries)){
            return;
        }

        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA){
                String tableName = entry.getHeader().getTableName();
                String databaseName  = entry.getHeader().getSchemaName();

                CanalEntry.RowChange rowChange = null;
                try {
                    rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                CanalEntry.EventType eventType = rowChange.getEventType();
                if (eventType == CanalEntry.EventType.DELETE) {
                    // 打印删除前的数据
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    // 打印新增的数据
                } else if (eventType == CanalEntry.EventType.UPDATE){
                    // 打印更新后的数据
                }else {
                    //忽略
                }



            }
        }


    }

}
