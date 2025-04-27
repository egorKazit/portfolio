package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ErrorController.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CustomProperties customProperties;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void getError() throws Exception {
        when(customProperties.getStaticImageFolder()).thenReturn("");
        mockMvc.perform(get("/error")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("404"))).andExpect(content().string(containsString("Page Not Found")));
    }
}