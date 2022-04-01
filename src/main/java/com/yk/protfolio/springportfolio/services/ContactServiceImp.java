package com.yk.protfolio.springportfolio.services;

import com.google.gson.Gson;
import com.yk.protfolio.springportfolio.persistence.ContactDAO;
import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class to work on contract
 */
@Service
public class ContactServiceImp implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    /**
     * posts contract
     *
     * @param contactInJson contract
     * @return operation result
     * @throws JSONException error on json parsing
     */
    @Override
    public OperationResult<Contact> postContact(JSONObject contactInJson) throws JSONException {
        OperationResult.OperationResultBuilder<Contact> result = OperationResult.builder();
        result.isSuccess(true);
        Contact contact = new Gson().fromJson(String.valueOf(contactInJson), Contact.class);
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
