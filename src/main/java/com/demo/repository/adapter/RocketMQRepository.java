package com.demo.repository.adapter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;

/**
 * @author chaird
 * @create 2025-03-22 3:26
 */
@Service
public class RocketMQRepository {

    private static final String NAMESRV_ADDR = "127.0.0.1:9876"; // RocketMQ Name Server地址
    private static final String PRODUCER_GROUP = "my-producer-group";


    private boolean init = false;

    DefaultMQProducer producer = null;


    public String sendMessage(String topic, Map<String, String> attibutes, String data) throws Exception{

        if (!init){
            DefaultMQProducer producer = new DefaultMQProducer(PRODUCER_GROUP);
            producer.setNamesrvAddr(NAMESRV_ADDR);
            // 启动Producer实例
            producer.start();
            init = true;
        }


        Message message = new Message();
        message.setTopic(topic);
        if (null != attibutes){
            for (Map.Entry<String, String> stringStringEntry : attibutes.entrySet()) {
                message.putUserProperty(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }

        message.setBody(data.getBytes());

        SendResult send = producer.send(message);

        return send.getMsgId();


    }





}
