package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.HtmlConstants;
import com.yk.protfolio.springportfolio.utilities.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller to handle Project requests
 */
@Controller
@AllArgsConstructor
public class ProjectController {

    private ProjectService projectService;

    /**
     * handles get requests
     *
     * @param id    project id
     * @param model model
     * @return template name
     */
    @GetMapping("/project-{id}.html")
    public String getProject(@PathVariable String id, Model model) {
        String out = ControllerUtils.getPage(Page.PROJECT, model);
        model.addAttribute(Page.WORK.getName() + ControllerUtils.ACTIVE_MARKER, HtmlConstants.NAVIGATION_ITEM_ACTIVE);
        model.addAttribute(Attributes.PROJECT, projectService.getProject(Integer.parseInt(id)));
        return out;
    }
}
