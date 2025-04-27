package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Slide;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO class to work on Slide Entity
 */
@Repository
@AllArgsConstructor
public class SlideDAOImp implements SlideDAO {

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
