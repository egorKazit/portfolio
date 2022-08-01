package com.yk.protfolio.springportfolio.services;

import com.yk.schema.About;
import java.util.List;

/**
 * Service interface to work on about
 */
public interface AboutService {
    /**
     * gets general about information
     *
     * @return general information
     */
    About getGeneralAbout();

    /**
     * gets about information by id
     *
     * @return about information
     */
    About getDetailedAbout(int id);

    /**
     * gets list of about
     *
     * @return list of about
     */
    List<About> getAboutList();
}
