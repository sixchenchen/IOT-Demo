package com.moyan;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.moyan.mapper")  // 确保扫描到你的 Mapper 接口
public class IotProjectApp {
    public static void main(String[] args) {
        SpringApplication.run(IotProjectApp.class, args);
    }
}
