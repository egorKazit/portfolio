package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Project;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjectDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private ProjectDAOImp projectDAOImp;

    @Test
    void getProjects() {
        List<Project> projects = List.of(new Project(), new Project());
        when(daoEntityManager.getListOfEntities(Project.class)).thenReturn(projects);
        assertEquals(projects, projectDAOImp.getProjects());
    }

    @Test
    void getProject() {
        Project project = new Project();
        when(daoEntityManager.getEntityByKeys(any(), any())).thenReturn(project);
        assertEquals(project, projectDAOImp.getProject(1));
    }
}