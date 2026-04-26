package com.demo.dto;

/**
 * @author chaird
 * @create 2025-03-22 2:18
 */
public class ResultDTO {


    public Integer code;


    public Object data;


    public static ResultDTO SUCCESS(Object data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.code = 200;
        resultDTO.data = data;
        return resultDTO;
    }
}
