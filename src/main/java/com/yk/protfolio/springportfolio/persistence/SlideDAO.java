package com.yk.protfolio.springportfolio.persistence;

import com.yk.protfolio.springportfolio.schema.Slide;
import java.util.List;

/**
 * DAO interface to work on Slide Entity
 */
public interface SlideDAO {
    /**
     * gets list of slide entities
     *
     * @return list of slide entities
     */
    List<Slide> getSlides();
}
