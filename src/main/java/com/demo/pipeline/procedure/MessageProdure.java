package com.demo.pipeline.procedure;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.demo.model.EventModel;
import com.demo.pipeline.Procedure;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chaird
 * @create 2025-03-22 20:38
 */
@Component
public class MessageProdure implements Procedure {
    @Override
    public Object handleOne(Object obj) {

        if (!(obj instanceof List)){
            //只处理固定类型
            return null;
        }

        List<EventModel> eventModelList = (List<EventModel>) obj;

        for (EventModel eventModel : eventModelList) {

            //模拟发送消息
            Map<String, String> userProperties = new HashMap<>();
            userProperties.put("tableName", eventModel.getTableName());
            userProperties.put("databaseName", eventModel.getDatabaseName());
            userProperties.put("operateType", eventModel.getOperateType());
            userProperties.put("eventName", eventModel.getEventName());


            System.out.println("模拟发送消息--> 消息属性:" + userProperties
                    +" , 消息key:" + eventModel.getMsgKey()
                    + " ,消息Topic:" + eventModel.getTopic()
                    + "，消息内容：" + JSON.toJSONString(eventModel));









        }

        return null;
    }
}
