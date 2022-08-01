package com.yk.bot;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class MessageBotApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        assertNotNull(context);
    }

}
