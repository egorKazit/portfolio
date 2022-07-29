package com.yk.protfolio.springportfolio.controllers;

import com.yk.schema.About;
import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.GenericValuesService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to handle About requests
 */
@Controller
@Log4j2
public class AboutController {

    @Autowired
    private AboutService aboutService;

    @Autowired
    private GenericValuesService genericValuesService;

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

    @GetMapping("cv.doc")
    public void getCV(HttpServletResponse response) {
        try (InputStream fileInputStream = new FileInputStream(genericValuesService.getCVPath())) {
            IOUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException exception) {
            log.error("Error writing file to output stream. Filename was '{}'", genericValuesService.getCVPath(), exception);
        }

    }

}
