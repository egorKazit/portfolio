package com.yk.protfolio.springportfolio;

import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringPortfolioApplicationTests {

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void contextLoads(ApplicationContext context) {
        assertNotNull(context);
    }

}
