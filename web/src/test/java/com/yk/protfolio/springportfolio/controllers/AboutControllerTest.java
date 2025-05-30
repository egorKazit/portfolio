package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.components.SocialWrapper;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.services.*;
import com.yk.schema.About;
import com.yk.schema.Social;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AboutController.class)
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class AboutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AboutService aboutService;

    @MockitoBean
    ImageManager imageManager;

    @MockitoBean
    CustomProperties customProperties;

    @MockitoBean
    GenericValuesService genericValuesService;

    @MockitoBean
    SocialService socialService;

    @MockitoBean
    CVNameResolveService cvNameResolveService;


    @Test
    void getWithId() throws Exception {
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(customProperties.getStaticImageFolder()).thenReturn("");
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
        when(socialService.getSocials()).thenReturn(List.of(Social.builder()
                .id(0)
                .reference("SocialReference")
                .picture("SocialPicture")
                .image(new byte[]{})
                .build()));
        FieldUtils.writeStaticField(SocialWrapper.class, "socialService", socialService, true);
        when(aboutService.getDetailedAbout(0)).thenReturn(About.builder()
                .id(0)
                .title("")
                .picture("")
                .isHidden(false).build());
        mockMvc.perform(get("/about.html").param("id", "0")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getWithoutId() throws Exception {
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(customProperties.getStaticImageFolder()).thenReturn("");
        Arrays.stream(GenericValuesService.class.getDeclaredMethods())
                .forEach(method -> {
                    try {
                        when(method.invoke(genericValuesService)).thenReturn("");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
        when(socialService.getSocials()).thenReturn(List.of(Social.builder()
                .id(0)
                .reference("SocialReference")
                .picture("SocialPicture")
                .image(new byte[]{})
                .build()));
        FieldUtils.writeStaticField(SocialWrapper.class, "socialService", socialService, true);
        Field genericValuesServiceField = GenericValuesWrapper.class.getDeclaredField("genericValuesService");
        genericValuesServiceField.setAccessible(true);
        genericValuesServiceField.set(GenericValuesWrapper.class, genericValuesService);
        genericValuesServiceField.setAccessible(false);
        when(aboutService.getAboutList()).thenReturn(List.of(About.builder().id(0).text("TestOne").build()));
        mockMvc.perform(get("/about.html")).andDo(print()).andExpect(status().isOk());
    }

}