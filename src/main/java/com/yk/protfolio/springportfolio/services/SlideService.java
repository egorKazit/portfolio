package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.schema.Slide;
import java.util.List;

/**
 * Service interface to work on slide
 */
public interface SlideService {
    /**
     * gets list of slides
     *
     * @return list of slides
     */
    List<Slide> getSlides();

}
