package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Contact;
import com.yk.processor.UpdateEntityStatus;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private ContactDAOImp contactDAOImp;

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