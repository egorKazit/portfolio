package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.services.SkillService;
import com.yk.protfolio.springportfolio.services.SlideService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SlideService slideService;
    @Autowired
    private AboutService aboutService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SkillService skillService;

    @GetMapping("/index.html")
    public String getHome(Model model) {
        model.addAttribute(Attributes.SLIDES, slideService.getSlides());
        model.addAttribute(Attributes.ABOUT, aboutService.getGeneralAbout());
        model.addAttribute(Attributes.PROJECTS, projectService.getProjects());
        model.addAttribute(Attributes.SKILLS, skillService.getSkills());
        return ControllerUtils.getPage(Page.INDEX, model);
    }

}
