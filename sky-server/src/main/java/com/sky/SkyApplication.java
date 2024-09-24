package com.sky;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Spring Boot 这是一个启动类，它会自动配置很多东西，省去手动配置的麻烦。
@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@Slf4j
public class SkyApplication {
    public static void main(String[] args) {
        // 这一行代码启动了 Spring Boot 应用程序
        SpringApplication.run(SkyApplication.class, args);
        log.info("server started");
    }
}
