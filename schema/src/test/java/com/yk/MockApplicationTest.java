package com.yk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@EnableAutoConfiguration
class MockApplicationTest {
    @Test
    void contextLoads(ApplicationContext context) {
        assertNotNull(context);
    }
}