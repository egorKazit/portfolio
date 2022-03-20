package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.schema.About;
import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to handle About requests
 */
@Controller
public class AboutController {

    @Autowired
    private AboutService aboutService;

    /**
     * handles get requests
     *
     * @param id    about id
     * @param model model
     * @return template name
     */
    @GetMapping("/about.html")
    public String getAbout(@RequestParam("id") Optional<Integer> id, Model model) {
        if (id.isPresent()) {
            About about = aboutService.getDetailedAbout(id.get());
            model.addAttribute(Attributes.ABOUTS, List.of(about));
        } else {
            model.addAttribute(Attributes.ABOUTS, aboutService.getAboutList());
        }
        return ControllerUtils.getPage(Page.ABOUT, model);
    }

}
