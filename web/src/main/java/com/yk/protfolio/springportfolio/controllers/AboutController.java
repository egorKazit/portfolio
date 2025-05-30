package com.yk.protfolio.springportfolio.controllers;

import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.CVNameResolveService;
import com.yk.protfolio.springportfolio.services.GenericValuesService;
import com.yk.protfolio.springportfolio.utilities.Attributes;
import com.yk.protfolio.springportfolio.utilities.Page;
import com.yk.schema.About;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Controller to handle About requests
 */
@Controller
@Log4j2
@AllArgsConstructor
public class AboutController {

    private AboutService aboutService;
    private CVNameResolveService cvNameResolveService;
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
        model.addAttribute(Attributes.CV_NAME, cvNameResolveService.resolveCVName(genericValuesService.getCVPath()));
        return ControllerUtils.getPage(Page.ABOUT, model);
    }

    @GetMapping("/download/{cvName}")
    public void getCV(HttpServletResponse response, @PathVariable String cvName) {
        try (InputStream fileInputStream = new FileInputStream(genericValuesService.getCVPath())) {
            IOUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException exception) {
            log.error("Error writing file to output stream. Filename was '{}'", genericValuesService.getCVPath(), exception);
        }

    }

}
