package com.yk.protfolio.springportfolio.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ErrorController.class)
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getError() throws Exception {
        mockMvc.perform(get("/error")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("404"))).andExpect(content().string(containsString("Page Not Found")));
    }
}