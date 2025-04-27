package com.yk.protfolio.springportfolio.services;

import com.google.gson.Gson;
import com.yk.processor.UpdateEntityStatus;
import com.yk.protfolio.springportfolio.persistence.ContactDAO;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.schema.Contact;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class to work on contract
 */
@Service
@AllArgsConstructor
public class ContactServiceImp implements ContactService {

    private ContactDAO contactDAO;

    /**
     * posts contract
     *
     * @param contact contract
     * @return operation result
     */
    @Override
    public OperationResult<Contact> postContact(Contact contact) {
        OperationResult.OperationResultBuilder<Contact> result = OperationResult.builder();
        result.isSuccess(true);
        UpdateEntityStatus<Contact> contactUpdateEntityStatus = contactDAO.postContact(contact);
        if (contactUpdateEntityStatus.getStatus().equals(UpdateEntityStatus.FAILED)) {
            result.isSuccess(false);
            result.messages(contactUpdateEntityStatus.getMessages());
        } else {
            result.entity(contactUpdateEntityStatus.getEntity());
        }
        return result.build();
    }


}
