package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.Contact;
import com.yk.processor.UpdateEntityStatus;

/**
 * DAO interface to work on Contact Entity
 */
public interface ContactDAO {
    /**
     * posts contract
     *
     * @param contact contact
     * @return update entity status
     */
    UpdateEntityStatus<Contact> postContact(Contact contact);
}
