package com.yk.protfolio.springportfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.yk.protfolio.springportfolio","com.yk.processor","com.yk.dao"})
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.yk.schema"})
@EnableScheduling
public class SpringPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPortfolioApplication.class, args);
    }

}
