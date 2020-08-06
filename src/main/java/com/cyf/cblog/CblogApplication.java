package com.cyf.cblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = {"com.cyf.cblog.mapper"})
@SpringBootApplication
public class CblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CblogApplication.class, args);
    }

}
