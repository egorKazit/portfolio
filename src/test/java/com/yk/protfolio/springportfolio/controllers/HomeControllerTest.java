package com.yk.protfolio.springportfolio.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yk.protfolio.springportfolio.components.GenericValuesWrapper;
import com.yk.protfolio.springportfolio.components.SocialWrapper;
import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.schema.About;
import com.yk.protfolio.springportfolio.schema.Project;
import com.yk.protfolio.springportfolio.schema.Skill;
import com.yk.protfolio.springportfolio.schema.Slide;
import com.yk.protfolio.springportfolio.schema.Social;
import com.yk.protfolio.springportfolio.services.AboutService;
import com.yk.protfolio.springportfolio.services.GenericValuesService;
import com.yk.protfolio.springportfolio.services.ProjectService;
import com.yk.protfolio.springportfolio.services.SkillService;
import com.yk.protfolio.springportfolio.services.SlideService;
import com.yk.protfolio.springportfolio.services.SocialService;
import java.util.List;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SlideService slideService;

    @MockBean
    AboutService aboutService;

    @MockBean
    ProjectService projectService;

    @MockBean
    SkillService skillService;

    @MockBean
    GenericValuesService genericValuesService;

    @MockBean
    SocialService socialService;

    @MockBean
    CustomProperties customProperties;

    @Test
    void getHomeAsIndex() throws Exception {
        mockedAll();
        mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("SlideDescr")))
                .andExpect(content().string(containsString("SlideGenWord")))
                .andExpect(content().string(containsString("SlidePic")))
                .andExpect(content().string(containsString("AboutTitle")))
                .andExpect(content().string(containsString("AboutPic")))
                .andExpect(content().string(containsString("ProjectPic")))
                .andExpect(content().string(containsString("SkillDescr")))
                .andExpect(content().string(containsString("SocialReference")));

    }

    @Test
    void getHomeAsSlash() throws Exception {
        mockedAll();
        mockMvc.perform(get("/index.html")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("SlideDescr")))
                .andExpect(content().string(containsString("SlideGenWord")))
                .andExpect(content().string(containsString("SlidePic")))
                .andExpect(content().string(containsString("AboutTitle")))
                .andExpect(content().string(containsString("AboutPic")))
                .andExpect(content().string(containsString("ProjectPic")))
                .andExpect(content().string(containsString("SkillDescr")))
                .andExpect(content().string(containsString("SocialReference")));

    }

    @Test
    void getHomeAsEmpty() throws Exception {
        mockedAll();
        mockMvc.perform(get("https://127.0.0.1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("SlideDescr")))
                .andExpect(content().string(containsString("SlideGenWord")))
                .andExpect(content().string(containsString("SlidePic")))
                .andExpect(content().string(containsString("AboutTitle")))
                .andExpect(content().string(containsString("AboutPic")))
                .andExpect(content().string(containsString("ProjectPic")))
                .andExpect(content().string(containsString("SkillDescr")))
                .andExpect(content().string(containsString("SocialReference")));

    }

    private void mockedAll() throws IllegalAccessException {
        when(customProperties.getStaticImageFolder()).thenReturn("");
        when(slideService.getSlides()).thenReturn(List.of(Slide.builder()
                .id(0)
                .description("SlideDescr")
                .generalWord("SlideGenWord")
                .picture("SlidePic")
                .build()));
        when(aboutService.getGeneralAbout()).thenReturn(About.builder()
                .id(0)
                .title("AboutTitle")
                .picture("AboutPic")
                .text("")
                .isHidden(false).build());
        when(projectService.getProjects()).thenReturn(List.of(Project.builder()
                .id(0)
                .title("")
                .text("")
                .picture("ProjectPic").build()));
        when(skillService.getSkills()).thenReturn(List.of(Skill.builder()
                .id(0)
                .description("SkillDescr")
                .build()));
        when(genericValuesService.getAbout()).thenReturn("About");
        when(genericValuesService.getProjects()).thenReturn("Projects");
        when(genericValuesService.getHome()).thenReturn("Home");
        when(genericValuesService.getFollows()).thenReturn("Follows");
        when(genericValuesService.getPortfolio()).thenReturn("Portfolio");
        when(genericValuesService.getSkills()).thenReturn("Skills");
        when(genericValuesService.getContact()).thenReturn("Contact");
        when(genericValuesService.getRead()).thenReturn("Read");
        when(genericValuesService.getWork()).thenReturn("Work");
        FieldUtils.writeStaticField(GenericValuesWrapper.class, "genericValuesService", genericValuesService, true);
        when(socialService.getSocials()).thenReturn(List.of(Social.builder()
                .id(0)
                .reference("SocialReference")
                .picture("SocialPicture")
                .image(new byte[]{})
                .build()));
        FieldUtils.writeStaticField(SocialWrapper.class, "socialService", socialService, true);
    }
}