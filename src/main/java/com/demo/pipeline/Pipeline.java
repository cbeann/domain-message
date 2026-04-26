package com.demo.pipeline;

import com.demo.enums.State;
import com.demo.pipeline.procedure.BinlogNormalizeProcedure;
import com.demo.pipeline.procedure.EventProcedure;
import com.demo.pipeline.procedure.MessageProdure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chaird
 * @create 2025-03-22 10:27
 */
@Service
public class Pipeline implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Pipeline.class);

    private List<Procedure> procedureList = new ArrayList<>();

    public State execute(Object input) {

        if (procedureList == null || procedureList.isEmpty()) {
            return State.ERROR;
        }

        for (Procedure procedure : procedureList) {
            try {
                input = procedure.handleOne(input);
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        return State.FINISH;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {


        BinlogNormalizeProcedure binlogNormalizeProcedure = (BinlogNormalizeProcedure)applicationContext.getBean(BinlogNormalizeProcedure.class);
        procedureList.add(binlogNormalizeProcedure);
        EventProcedure eventProcedure = (EventProcedure)applicationContext.getBean(EventProcedure.class);
        procedureList.add(eventProcedure);
        MessageProdure messageProdure = (MessageProdure)applicationContext.getBean(MessageProdure.class);
        procedureList.add(messageProdure);


    }
}