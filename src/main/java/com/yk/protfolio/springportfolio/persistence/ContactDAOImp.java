package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDAOImp implements ContactDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    @Override
    public UpdateEntityStatus<Contact> postContact(Contact contact) {
        return daoEntityManager.updateEntity(contact);
    }
}
