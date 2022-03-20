package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.ProjectDAO;
import com.yk.protfolio.springportfolio.schema.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work on project
 */
@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    /**
     * gets list of projects
     *
     * @return list of projects
     */
    @Override
    public List<Project> getProjects() {
        return projectDAO.getProjects();
    }

    /**
     * gets project by id
     *
     * @return project
     */
    @Override
    public Project getProject(int id) {
        return projectDAO.getProject(id);
    }
}
