package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;

public interface ContactDAO {
    UpdateEntityStatus<Contact> postContact(Contact contact);
}
