package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.ContactDAO;
import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.protfolio.springportfolio.utilities.UpdateEntityStatus;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.persistence.Id;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImp implements ContactService {

    @Autowired
    private ContactDAO contactDAO;

    @Override
    public OperationResult<Contact> postContact(JSONObject contact) throws JSONException {
        Contact.ContactBuilder contactBuilder = Contact.builder();
        OperationResult.OperationResultBuilder<Contact> result = OperationResult.builder();
        List<String> messages = new ArrayList<>();
        AtomicBoolean isSucceed = new AtomicBoolean(true);
        Arrays.stream(Contact.class.getDeclaredFields()).forEach(field -> {
            if (!field.isAnnotationPresent(Id.class)) {
                try {
                    if (!contact.has(field.getName())) {
                        messages.add(String.format("%s is not presented in response", field.getName()));
                        isSucceed.set(false);
                        return;
                    }
                    Method method = contactBuilder.getClass().getDeclaredMethod(field.getName(), field.getType());
                    method.invoke(contactBuilder, contact.get(field.getName()));
                } catch (NoSuchMethodException | JSONException | InvocationTargetException | IllegalAccessException e) {
                    messages.add(String.format("Error during insertion of field %s due %s", field.getName(), e.getMessage()));
                }
            }
        });

        if (isSucceed.get()) {
            UpdateEntityStatus<Contact> contactUpdateEntityStatus = contactDAO.postContact(contactBuilder.build());
            if (contactUpdateEntityStatus.getStatus().equals(UpdateEntityStatus.FAILED)) {
                isSucceed.set(false);
                messages.addAll(contactUpdateEntityStatus.getMessages());
            } else {
                result.entity(contactUpdateEntityStatus.getEntity());
            }
        }
        result.isSuccess(isSucceed.get());
        result.messages(messages);
        return result.build();
    }


}
