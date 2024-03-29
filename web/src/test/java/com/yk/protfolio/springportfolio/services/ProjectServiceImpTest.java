package com.yk.protfolio.springportfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.persistence.ProjectDAOImp;
import com.yk.schema.Project;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectServiceImpTest {

    @Mock
    ProjectDAOImp projectDAOImp;

    @Mock
    ImageManager imageManager;

    @InjectMocks
    ProjectServiceImp projectServiceImp;

    @Test
    void getProjects() {
        List<Project> projects = List.of(new Project(), new Project());
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(projectDAOImp.getProjects()).thenReturn(projects);
        assertEquals(projects, projectServiceImp.getProjects());
    }

    @Test
    void getProject() {
        Project project = new Project();
        when(projectDAOImp.getProject(0)).thenReturn(project);
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        assertEquals(project, projectServiceImp.getProject(0));
    }
}