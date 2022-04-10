package com.yk.protfolio.springportfolio.configuration;

import com.yk.protfolio.springportfolio.persistence.ExternalProcessService;
import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.DatabaseStartupValidator;

@Configuration
public class DockerConfiguration {

    @Autowired
    private ExternalProcessService externalProcessService;

    @Bean
    public static BeanFactoryPostProcessor dependsOnPostProcessor() {
        return bf -> {
            String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
            Stream.of(jpa)
                    .map(bf::getBeanDefinition)
                    .forEach(it -> it.setDependsOn("databaseStartupValidator"));
        };
    }

    @Bean
    public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
        var dsv = new DatabaseStartupValidator();
        dsv.setDataSource(dataSource);
        return dsv;
    }

    @PostConstruct
    public void loadDocker() throws IOException, InterruptedException {
        URL dockerComposeYml = DockerConfiguration.class.getClassLoader().getResource("docker-compose.yml");
        assert dockerComposeYml != null;

        externalProcessService.init().setContext("docker-compose")
                .setContext("-f")
                .setContext(dockerComposeYml.getPath())
                .setContext("up")
                .setContext("--build")
                .setFinishMessage("/usr/sbin/mysqld: ready for connections")
                .load();
    }

    @PreDestroy
    public void teardown() {
        externalProcessService.waitForTeardown();
    }

}
