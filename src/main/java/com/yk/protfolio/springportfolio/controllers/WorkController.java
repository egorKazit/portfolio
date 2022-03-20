package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/work.html")
    public String getWork(Model model) {
        model.addAttribute(Attributes.PROJECTS, projectService.getProjects());
        return ControllerUtils.getPage(Page.WORK, model);
    }

}
