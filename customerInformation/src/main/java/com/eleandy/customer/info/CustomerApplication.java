package com.eleandy.customer.info;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//主要服务于对普通用户进行查询等操作
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("com.eleandy.customer.info.Mapper")
@EnableDiscoveryClient
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
