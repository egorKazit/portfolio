package com.yk.protfolio.springportfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.yk.protfolio.springportfolio.persistence.ContactDAOImp;
import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactServiceImpTest {

    @InjectMocks
    ContactServiceImp contactServiceImp;

    @Mock
    ContactDAOImp contactDAOImp;

    @Test
    void postContactSuccess() {
        Contact contact = Contact.builder().id(0).name("Test").email("test@email.com").number("+111010").message("Test Message").build();
        JSONObject jsonObjectContact = new JSONObject(new Gson().toJson(contact));
        when(contactDAOImp.postContact(contact)).thenReturn(UpdateEntityStatus.<Contact>builder().status(UpdateEntityStatus.UPDATED).entity(contact).build());
        OperationResult<Contact> contactUpdateEntityStatus = contactServiceImp.postContact(jsonObjectContact);
        verify(contactDAOImp, times(1)).postContact(contact);
        assertTrue(contactUpdateEntityStatus.isSuccess());
        assertEquals(contact, contactUpdateEntityStatus.getEntity());
        assertNull(contactUpdateEntityStatus.getMessages());
    }

    @Test
    void postContactFailed() {
        Contact contact = Contact.builder().id(0).name("Test").email("test@email.com").number("+111010").message("Test Message").build();
        JSONObject jsonObjectContact = new JSONObject(new Gson().toJson(contact));
        when(contactDAOImp.postContact(contact)).thenReturn(UpdateEntityStatus.<Contact>builder().status(UpdateEntityStatus.FAILED)
                .messages(List.of("Message 1"))
                .build());
        OperationResult<Contact> contactUpdateEntityStatus = contactServiceImp.postContact(jsonObjectContact);
        verify(contactDAOImp, times(1)).postContact(contact);
        assertFalse(contactUpdateEntityStatus.isSuccess());
        assertNull(contactUpdateEntityStatus.getEntity());
        assertEquals("[\"Message 1\"]", contactUpdateEntityStatus.getMessages());
    }

}