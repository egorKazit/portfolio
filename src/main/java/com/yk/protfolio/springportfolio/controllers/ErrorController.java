package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.utilities.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle errors
 */
@Controller
public class ErrorController {
    @GetMapping("/error")
    public String getError(Model model) {
        return ControllerUtils.getPage(Page.ERROR, model);
    }
}
