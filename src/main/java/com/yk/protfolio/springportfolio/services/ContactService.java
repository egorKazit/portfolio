package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Service interface to work on contract
 */
public interface ContactService {
    /**
     * posts contract
     *
     * @param contact contract
     * @return operation result
     * @throws JSONException error on json parsing
     */
    OperationResult<Contact> postContact(JSONObject contact) throws JSONException;
}
