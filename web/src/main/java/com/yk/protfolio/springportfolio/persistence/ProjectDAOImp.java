package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * DAO class to work on Project Entity
 */
@Repository
@AllArgsConstructor
public class ProjectDAOImp implements ProjectDAO {

    private DAOEntityManager daoEntityManager;

    /**
     * gets list of project entities
     *
     * @return list of project entities
     */
    @Override
    public List<Project> getProjects() {
        return daoEntityManager.getListOfEntities(Project.class);
    }

    /**
     * gets project entity by id
     *
     * @return project entity
     */
    @Override
    public Project getProject(int id) {
        return daoEntityManager.getEntityByKeys(Project.class, Map.of("id", id));
    }
}
