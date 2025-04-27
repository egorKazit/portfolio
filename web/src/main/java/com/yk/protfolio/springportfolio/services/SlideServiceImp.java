package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SlideDAOImp;
import com.yk.schema.Slide;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to work on slide
 */
@Service
@AllArgsConstructor
public class SlideServiceImp implements SlideService {

    private SlideDAOImp slideDAOImp;
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
