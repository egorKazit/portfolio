package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SlideDAOImp;
import com.yk.protfolio.springportfolio.schema.Slide;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service to work on slide
 */
@Service
public class SlideServiceImp implements SlideService {

    @Autowired
    private SlideDAOImp slideDAOImp;

    @Autowired
    private ImageManager imageService;

    /**
     * gets list of slides
     *
     * @return list of slides
     */
    @Override
    public List<Slide> getSlides() {
        List<Slide> slides = slideDAOImp.getSlides();
        imageService.uploadImage(slides, Slide::getPicture, Slide::getImage);
        return slides;
    }

}
