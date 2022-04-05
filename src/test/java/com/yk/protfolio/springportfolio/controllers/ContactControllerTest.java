package com.yk.protfolio.springportfolio.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.components.SocialWrapper;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.schema.Social;
import com.yk.protfolio.springportfolio.services.ContactService;
import com.yk.protfolio.springportfolio.services.GenericValuesService;
import com.yk.protfolio.springportfolio.services.SocialService;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ContactService contactService;

    @MockBean
    GenericValuesService genericValuesService;

    @MockBean
    CustomProperties customProperties;

    @MockBean
    SocialService socialService;

    @Test
    void getContacts() throws Exception {
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
        mockMvc.perform(get("/contact.html")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void sendRequestSuccess() throws Exception {
        mockAll(true);
        mockMvc.perform(post("/contact").content(
                new JSONObject().toString()
        )).andDo(print()).andExpect(status().is(201)).andExpect(content().string(containsString("1010")));
    }

    @Test
    void sendRequestFailed() throws Exception {
        mockAll(false);
        mockMvc.perform(post("/contact").content(
                new JSONObject().toString()
        )).andDo(print()).andExpect(status().is(400)).andExpect(content().string(containsString("Error One")));
    }

    private void mockAll(boolean isSuccess) throws NoSuchFieldException, IllegalAccessException {
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
        when(contactService.postContact(any())).thenAnswer(invocationOnMock -> {
            OperationResult.OperationResultBuilder<Contact> contactOperationResultBuilder = OperationResult.<Contact>builder().isSuccess(isSuccess);
            if (isSuccess) {
                return contactOperationResultBuilder.entity(Contact.builder().id(1010).build()).build();
            } else {
                return contactOperationResultBuilder.messages(List.of("Error One")).build();
            }
        });
    }

}