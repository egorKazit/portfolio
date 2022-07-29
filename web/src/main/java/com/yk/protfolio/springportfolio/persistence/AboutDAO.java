package com.yk.protfolio.springportfolio.persistence;

import com.yk.schema.About;
import java.util.List;

/**
 * DAO interface to work on About Entity
 */
public interface AboutDAO {
    /**
     * gets list of about entities
     *
     * @return list of about entities
     */
    List<About> getAbouts();

    /**
     * gets about entity by id
     *
     * @return about entity
     */
    About getAbout(int id);
}
