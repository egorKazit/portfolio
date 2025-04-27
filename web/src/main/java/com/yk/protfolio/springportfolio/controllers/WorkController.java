package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle Work requests
 */
@Controller
@AllArgsConstructor
public class WorkController {

    private ProjectService projectService;

    /**
     * handles get requests
     *
     * @param model model
     * @return template name
     */
    @GetMapping("/work.html")
    public String getWork(Model model) {
        model.addAttribute(Attributes.PROJECTS, projectService.getProjects());
        return ControllerUtils.getPage(Page.WORK, model);
    }

}
