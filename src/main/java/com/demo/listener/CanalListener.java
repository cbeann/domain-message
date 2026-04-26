package com.demo.listener;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.demo.mock.MockService;
import com.demo.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author chaird
 * @create 2025-03-15 23:43
 */
@Component
public class CanalListener implements CommandLineRunner {


    @Autowired
    private Pipeline pipeline;


    private CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("101.200.123.220", 11111),
            "example", // 这里的 "example" 是 Canal 实例名，对应 instance.properties 中的配置
            "",
            "");

    ExecutorService executorService = new ThreadPoolExecutor(1, 1, 2L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());





    @Override
    public void run(String... args) throws Exception {


        executorService.submit(()->{


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
                                pipeline.execute(entry);
//                                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
//                                CanalEntry.EventType eventType = rowChange.getEventType();
//                                System.out.println("Event Type: " + eventType);
//                                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
//                                    if (eventType == CanalEntry.EventType.INSERT) {
//                                        printColumns(rowData.getAfterColumnsList());
//                                    } else if (eventType == CanalEntry.EventType.UPDATE) {
//                                        System.out.println("--- Before Update ---");
//                                        printColumns(rowData.getBeforeColumnsList());
//                                        System.out.println("--- After Update ---");
//                                        printColumns(rowData.getAfterColumnsList());
//                                    } else if (eventType == CanalEntry.EventType.DELETE) {
//                                        printColumns(rowData.getBeforeColumnsList());
//                                    }
//                                }
                            } catch (Exception e) {
                                throw new RuntimeException("Error parsing row change", e);
                            }
                        }
                    }
                }
            }


        });


    }
    private static void printColumns(List<CanalEntry.Column> columns) {
        for (CanalEntry.Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + " update=" + column.getUpdated());
        }
    }
}
