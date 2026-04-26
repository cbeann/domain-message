package com.demo.utils;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.constant.EventType;
import com.demo.model.BinLogModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chaird
 * @create 2025-03-22 18:33
 */
public class BinLogModelUtils {


      public  static List<BinLogModel> convert2BinLogModel(CanalEntry.Entry entry){
        List<BinLogModel> binLogModelList = new ArrayList<>();
        if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA){
            return null;
        }

        String tableName = entry.getHeader().getTableName();
        String databaseName  = entry.getHeader().getSchemaName();


        CanalEntry.RowChange rowChange = null;
        try {
            rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
        } catch (Exception e) {
            e.printStackTrace();
        }



        CanalEntry.EventType eventType = entry.getHeader().getEventType();
        if (CanalEntry.EventType.INSERT.equals(eventType)){


            // 遍历每一行数据
            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {

                BinLogModel binLogModel = new BinLogModel();
                binLogModel.setTableName(tableName);
                binLogModel.setDatabaseName(databaseName);
                binLogModel.setOperateType(EventType.INSERT);
                Map<String, String> afterData = new HashMap<>();
                binLogModel.setAfterData(afterData);
                binLogModelList.add(binLogModel);

                // 对于新增操作，我们只关心 afterColumnsList
                List<CanalEntry.Column> columns = rowData.getAfterColumnsList();
                for (CanalEntry.Column column : columns) {
                    //System.out.println("字段名: " + column.getName() + ", 新增的值: " + column.getValue());
                    afterData.put(column.getName(), column.getValue());
                }
            }


        }else if (CanalEntry.EventType.DELETE.equals(eventType)){

            // 遍历每一行数据
            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {

                BinLogModel binLogModel = new BinLogModel();
                binLogModel.setTableName(tableName);
                binLogModel.setDatabaseName(databaseName);
                binLogModel.setOperateType(EventType.DELETE);
                Map<String, String> beforeData = new HashMap<>();
                binLogModel.setBeforeData(beforeData);
                binLogModelList.add(binLogModel);


                // 对于删除操作，我们只关心 beforeColumnsList
                List<CanalEntry.Column> columns = rowData.getBeforeColumnsList();
                for (CanalEntry.Column column : columns) {
                    //System.out.println("字段名: " + column.getName() + ", 删除前的值: " + column.getValue());
                    beforeData.put(column.getName(), column.getValue());
                }
            }

        }else if (CanalEntry.EventType.UPDATE.equals(eventType)){

            // 遍历每一行数据
            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                //System.out.println("受影响的行数据如下：");

                BinLogModel binLogModel = new BinLogModel();
                binLogModel.setTableName(tableName);
                binLogModel.setDatabaseName(databaseName);
                binLogModel.setOperateType(EventType.UPDATE);
                Map<String, String> beforeData = new HashMap<>();
                Map<String, String> afterData = new HashMap<>();
                List<String> changeFieldList = new ArrayList<>();
                binLogModel.setBeforeData(beforeData);
                binLogModel.setAfterData(afterData);
                binLogModel.setChangeFieldList(changeFieldList);
                binLogModelList.add(binLogModel);

                // 比较 beforeColumnsList 和 afterColumnsList 来找出变化的字段
                List<CanalEntry.Column> beforeColumns = rowData.getBeforeColumnsList();
                List<CanalEntry.Column> afterColumns = rowData.getAfterColumnsList();

                for (int i = 0; i < beforeColumns.size(); i++) {
                    CanalEntry.Column beforeColumn = beforeColumns.get(i);
                    beforeData.put(beforeColumn.getName(), beforeColumn.getValue());

                    CanalEntry.Column afterColumn = afterColumns.get(i);
                    afterData.put(afterColumn.getName(), afterColumn.getValue());

                    // 如果前后值不相同，则表示该字段被更新了
                    if (!beforeColumn.getValue().equals(afterColumn.getValue())) {
                        //System.out.println(String.format("字段名: %s, 更新前的值: %s, 更新后的值: %s",
                                //beforeColumn.getName(), beforeColumn.getValue(), afterColumn.getValue()));
                        changeFieldList.add(beforeColumn.getName());
                    }
                }
            }


        }else {
            return null;
        }







        return binLogModelList;

    }
}
