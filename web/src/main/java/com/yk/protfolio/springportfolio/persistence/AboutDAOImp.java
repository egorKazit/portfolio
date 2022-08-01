package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.About;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO class to work on About Entity
 */
@Repository
public class AboutDAOImp implements AboutDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    /**
     * gets list of about entities
     *
     * @return list of about entities
     */
    @Override
    public List<About> getAbouts() {
        return daoEntityManager.getListOfEntities(About.class);
    }

    /**
     * gets about entity by id
     *
     * @return about entity
     */
    @Override
    public About getAbout(int id) {
        return daoEntityManager.getEntityByKeys(About.class, Map.of("id", id));
    }
}
