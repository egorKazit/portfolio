package com.yk.protfolio.springportfolio.services;

import com.yk.schema.Project;

import java.util.List;

/**
 * Service interface to work on project
 */
public interface ProjectService {
    /**
     * gets list of projects
     *
     * @return list of projects
     */
    List<Project> getProjects();

    /**
     * gets project by id
     *
     * @return project
     */
    Project getProject(int id);
}
