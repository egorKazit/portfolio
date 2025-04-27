package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.processor.UpdateEntityStatus;
import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.schema.Contact;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ContactDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private ContactDAOImp contactDAOImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void postContact() {
        Contact contact = Contact.builder().build();
        UpdateEntityStatus<Contact> updateEntityStatus
                = UpdateEntityStatus.<Contact>builder()
                .status("")
                .entity(contact)
                .build();
        when(daoEntityManager.updateEntity(contact))
                .thenReturn(updateEntityStatus);
        assertEquals(updateEntityStatus, contactDAOImp.postContact(contact));
    }
}