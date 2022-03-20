package com.yk.protfolio.springportfolio.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.schema.About;
import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.GenericValuesService;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AboutController.class)
class AboutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AboutService aboutService;

    @MockBean
    GenericValuesService genericValuesService;

    @Test
    void getWithId() throws Exception {
        Arrays.stream(GenericValuesService.class.getDeclaredMethods())
                .forEach(method -> {
                    try {
                        when(method.invoke(genericValuesService)).thenReturn("");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        Field genericValuesServiceField = GenericValuesWrapper.class.getDeclaredField("genericValuesService");
        genericValuesServiceField.setAccessible(true);
        genericValuesServiceField.set(GenericValuesWrapper.class, genericValuesService);
        genericValuesServiceField.setAccessible(false);
        when(aboutService.getDetailedAbout(0)).thenReturn(About.builder()
                .id(0)
                .title("")
                .picture("")
                .reference("")
                .text("TestTwo")
                .isHidden(false).build());
        mockMvc.perform(get("/about.html").param("id", "0")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestTwo")));
    }

    @Test
    void getWithoutId() throws Exception {
        Arrays.stream(GenericValuesService.class.getDeclaredMethods())
                .forEach(method -> {
                    try {
                        when(method.invoke(genericValuesService)).thenReturn("");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        Field genericValuesServiceField = GenericValuesWrapper.class.getDeclaredField("genericValuesService");
        genericValuesServiceField.setAccessible(true);
        genericValuesServiceField.set(GenericValuesWrapper.class, genericValuesService);
        genericValuesServiceField.setAccessible(false);
        when(aboutService.getAboutList()).thenReturn(List.of(About.builder().id(0).text("TestOne").build()));
        mockMvc.perform(get("/about.html")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestOne")));
    }

}