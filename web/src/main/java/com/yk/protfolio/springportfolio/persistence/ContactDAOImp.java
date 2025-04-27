package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.processor.UpdateEntityStatus;
import com.yk.schema.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * DAO class to work on Contact Entity
 */
@Repository
@AllArgsConstructor
public class ContactDAOImp implements ContactDAO {

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
