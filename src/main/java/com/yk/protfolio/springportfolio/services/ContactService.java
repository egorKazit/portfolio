package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import org.hibernate.validator.internal.util.Contracts;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface ContactService {
    OperationResult<Contact> postContact(JSONObject contact) throws JSONException;
}
