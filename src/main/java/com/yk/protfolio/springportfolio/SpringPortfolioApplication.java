package com.yk.protfolio.springportfolio;

import com.yk.protfolio.springportfolio.components.DockerComposeLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.yk.protfolio.springportfolio.schema"})
public class SpringPortfolioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPortfolioApplication.class, args);
    }

}
