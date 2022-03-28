package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;

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
