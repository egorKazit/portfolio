package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import org.json.JSONException;
import org.json.JSONObject;

public interface ContactService {
    OperationResult<Contact> postContact(JSONObject contact) throws JSONException;
}
