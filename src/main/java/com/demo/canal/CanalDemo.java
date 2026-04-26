package com.demo.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author chaird
 * @create 2025-03-22 3:55
 */
public class CanalDemo {




    public static void main(String[] args) throws Exception{
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("101.200.123.220", 11111),
                "example", // 这里的 "example" 是 Canal 实例名，对应 instance.properties 中的配置
                "",
                "");


        // 连接到 Canal 服务器
        connector.connect();
        // 订阅所有数据库表的变更
        connector.subscribe(".*\\..*");
        // 回滚到未消费的地方
        connector.rollback();
            while (true) {
                // 获取指定数量的变更数据
                Message message = connector.get(100);
                List<CanalEntry.Entry> entries = message.getEntries();
                if (!entries.isEmpty()) {
                    for (CanalEntry.Entry entry : entries) {
                        if (entry.getEntryType() == CanalEntry.EntryType.ROWDATA) {
                            CanalEntry.RowChange rowChange;
                            try {
                                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                                CanalEntry.EventType eventType = rowChange.getEventType();
                                System.out.println("Event Type: " + eventType);
                                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                                    if (eventType == CanalEntry.EventType.INSERT) {
                                        printColumns(rowData.getAfterColumnsList());
                                    } else if (eventType == CanalEntry.EventType.UPDATE) {
                                        System.out.println("--- Before Update ---");
                                        printColumns(rowData.getBeforeColumnsList());
                                        System.out.println("--- After Update ---");
                                        printColumns(rowData.getAfterColumnsList());
                                    } else if (eventType == CanalEntry.EventType.DELETE) {
                                        printColumns(rowData.getBeforeColumnsList());
                                    }
                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Error parsing row change", e);
                            }
                        }
                    }
                }
            }








    }
    private static void printColumns(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }

}
