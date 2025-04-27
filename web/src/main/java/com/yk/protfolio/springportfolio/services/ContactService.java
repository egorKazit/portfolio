package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.schema.Contact;

/**
 * Service interface to work on contract
 */
public interface ContactService {
    /**
     * posts contract
     *
     * @param contact contract
     * @return operation result
     */
    OperationResult<Contact> postContact(Contact contact);
}
