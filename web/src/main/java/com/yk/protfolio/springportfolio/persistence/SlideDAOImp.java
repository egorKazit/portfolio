package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Slide;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * DAO class to work on Slide Entity
 */
@Repository
public class SlideDAOImp implements SlideDAO {

    @Autowired
    private DAOEntityManager daoEntityManager;

    /**
     * gets list of slide entities
     *
     * @return list of slide entities
     */
    @Override
    public List<Slide> getSlides() {
        return daoEntityManager.getListOfEntities(Slide.class);
    }
}
