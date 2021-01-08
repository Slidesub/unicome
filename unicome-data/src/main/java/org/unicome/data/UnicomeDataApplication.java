package org.unicome.data;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"org.unicome.data.domain.mysql"})
@EnableMongoRepositories(basePackages = {"org.unicome.data.domain.mongo"})
public class UnicomeDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnicomeDataApplication.class, args);
    }
}
