package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.schema.Contact;
import com.yk.protfolio.springportfolio.services.ContactService;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.protfolio.springportfolio.utilities.Page;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller to handle Contract requests
 */
@Controller
@Log4j2
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     * handles get requests
     *
     * @param model model
     * @return template name
     */
    @GetMapping("/contact.html")
    public String getContacts(Model model) {
        return ControllerUtils.getPage(Page.CONTACT, model);
    }

    /**
     * handles post requests
     *
     * @param request request data
     * @return template name
     * @throws IOException   exception on read
     * @throws JSONException exception on JSon deserialization
     */
    @PostMapping("/contact")
    public ResponseEntity<String> sendRequest(HttpServletRequest request) throws IOException, JSONException {
        BufferedReader reader = request.getReader();
        JSONObject contact = new JSONObject(reader.lines().collect(Collectors.joining()));
        OperationResult<Contact> result = contactService.postContact(contact);
        return ResponseEntity.status(result.isSuccess() ? 201 : 400)
                .body(result.isSuccess() ? result.getEntity().toString() : result.getMessages());
    }

}
