package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Contact;
import com.yk.processor.UpdateEntityStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO class to work on Contact Entity
 */
@Repository
public class ContactDAOImp implements ContactDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    /**
     * posts contract
     *
     * @param contact contact
     * @return update entity status
     */
    @Override
    public UpdateEntityStatus<Contact> postContact(Contact contact) {
        return daoEntityManager.updateEntity(contact);
    }
}
