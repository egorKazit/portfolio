package com.yk.protfolio.springportfolio.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yk.protfolio.springportfolio.configuration.CustomProperties;
import com.yk.protfolio.springportfolio.schema.Project;
import com.yk.protfolio.springportfolio.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProjectService projectService;

    @MockBean
    CustomProperties customProperties;

    @Test
    void getProject() throws Exception {
        when(customProperties.getStaticImageFolder()).thenReturn("");
        Project project = Project.builder().id(0).title("TestProject").text("TestProjectText").picture("pic1").build();
        when(projectService.getProject(0)).thenReturn(project);
        mockMvc.perform(get("/project-0.html")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("TestProject")))
                .andExpect(content().string(containsString("TestProjectText")))
                .andExpect(content().string(containsString("pic1")));
    }
}