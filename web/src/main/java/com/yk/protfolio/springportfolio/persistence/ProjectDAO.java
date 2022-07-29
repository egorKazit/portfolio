package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.Project;
import java.util.List;

/**
 * DAO interface to work on Project Entity
 */
public interface ProjectDAO {
    /**
     * gets list of project entities
     *
     * @return list of project entities
     */
    List<Project> getProjects();

    /**
     * gets project entity by id
     *
     * @return project entity
     */
    Project getProject(int id);
}
