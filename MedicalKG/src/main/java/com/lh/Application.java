package com.lh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lh.config"})//加载其他项目的包的配置类
//使用swagger http://localhost:8001/swagger-ui.html
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
