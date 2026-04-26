package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chaird
 * @create 2020-12-25 21:28
 */
@SpringBootApplication
@MapperScan("com.demo.mapper")
public class App {

  /**
   * 核心入口：CanalListener
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
