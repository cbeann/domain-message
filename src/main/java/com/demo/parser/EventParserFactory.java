package com.demo.parser;

/**
 * @author chaird
 * @create 2025-03-16 0:36
 */
public class EventParserFactory {


    public static EventParser getByType(Integer type){

        if (Integer.valueOf(1).equals(type)){
            return new HorizontalEventParser();
        }
        return new VerticalEventParser();

    }
}
