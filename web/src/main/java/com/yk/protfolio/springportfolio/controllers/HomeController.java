package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.services.SkillService;
import com.yk.protfolio.springportfolio.services.SlideService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle Contract requests
 */
@Controller
@AllArgsConstructor
public class HomeController {

    private SlideService slideService;
    private AboutService aboutService;
    private ProjectService projectService;
    private SkillService skillService;

    /**
     * handles get requests
     *
     * @param model model
     * @return template name
     */
    @GetMapping(value = {"", "/", "/index.html"})
    public String getHome(Model model) {
        model.addAttribute(Attributes.SLIDES, slideService.getSlides());
        model.addAttribute(Attributes.ABOUT, aboutService.getGeneralAbout());
        model.addAttribute(Attributes.PROJECTS, projectService.getProjects());
        model.addAttribute(Attributes.SKILLS, skillService.getSkills());
        return ControllerUtils.getPage(Page.INDEX, model);
    }

}
