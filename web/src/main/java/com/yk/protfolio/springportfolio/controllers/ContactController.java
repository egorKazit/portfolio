package com.yk.protfolio.springportfolio.controllers;

import com.google.gson.Gson;
import com.yk.protfolio.springportfolio.services.ContactService;
import com.yk.protfolio.springportfolio.utilities.OperationResult;
import com.yk.protfolio.springportfolio.utilities.Page;
import com.yk.schema.Contact;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Controller to handle Contract requests
 */
@Controller
@Log4j2
@AllArgsConstructor
public class ContactController {

    private ContactService contactService;
    private Gson gson;

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
     * @throws IOException exception on read
     */
    @PostMapping("/contact")
    public ResponseEntity<String> sendRequest(HttpServletRequest request) throws IOException {
        OperationResult<Contact> result = contactService.postContact(gson.fromJson(request.getReader(), Contact.class));
        return ResponseEntity.status(result.isSuccess() ? 201 : 400)
                .body(result.isSuccess() ? result.getEntity().toString() : result.getMessages());
    }

}
