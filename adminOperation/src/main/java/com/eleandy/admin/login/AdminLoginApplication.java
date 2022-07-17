package com.eleandy.admin.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.eleandy.admin.login.Mapper")
@EnableDiscoveryClient
public class AdminLoginApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminLoginApplication.class,args);
    }
}
