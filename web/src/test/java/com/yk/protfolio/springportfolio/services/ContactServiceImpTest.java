package com.yk.protfolio.springportfolio.services;

import com.google.gson.Gson;
import com.yk.processor.UpdateEntityStatus;
import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.protfolio.springportfolio.persistence.ContactDAO;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.schema.Contact;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContactServiceImpTest {

    @InjectMocks
    ContactServiceImp contactServiceImp;

    @Mock
    ContactDAO contactDAO;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void postContactSuccess() {
        Contact contact = Contact.builder().id(0).name("Test").email("test@email.com").number("+111010").message("Test Message").build();
        when(contactDAO.postContact(any())).thenReturn(UpdateEntityStatus.<Contact>builder().status(UpdateEntityStatus.UPDATED).entity(contact).build());
        OperationResult<Contact> contactUpdateEntityStatus = contactServiceImp.postContact(contact);
        verify(contactDAO, times(1)).postContact(any());
        assertTrue(contactUpdateEntityStatus.isSuccess());
        assertEquals(contact, contactUpdateEntityStatus.getEntity());
        assertNull(contactUpdateEntityStatus.getMessages());
    }

    @Test
    void postContactFailed() {
        Contact contact = Contact.builder().id(0).name("Test").email("test@email.com").number("+111010").message("Test Message").build();
        when(contactDAO.postContact(any())).thenReturn(UpdateEntityStatus.<Contact>builder().status(UpdateEntityStatus.FAILED)
                .messages(List.of("Message 1"))
                .build());
        OperationResult<Contact> contactUpdateEntityStatus = contactServiceImp.postContact(contact);
        verify(contactDAO, times(1)).postContact(any());
        assertFalse(contactUpdateEntityStatus.isSuccess());
        assertNull(contactUpdateEntityStatus.getEntity());
        assertEquals("[\"Message 1\"]", contactUpdateEntityStatus.getMessages());
    }

}