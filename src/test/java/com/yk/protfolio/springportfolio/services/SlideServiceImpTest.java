package com.yk.protfolio.springportfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.persistence.SlideDAOImp;
import com.yk.protfolio.springportfolio.schema.Slide;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SlideServiceImpTest {

    @Mock
    SlideDAOImp slideDAOImp;

    @InjectMocks
    SlideServiceImp slideServiceImp;

    @Test
    void getSlides() {
        List<Slide> slides = List.of(new Slide(), new Slide());
        when(slideServiceImp.getSlides()).thenReturn(slides);
        assertEquals(slides, slideDAOImp.getSlides());
    }
}