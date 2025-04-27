package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.protfolio.springportfolio.persistence.SlideDAOImp;
import com.yk.schema.Slide;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class SlideServiceImpTest {

    @Mock
    SlideDAOImp slideDAOImp;

    @Mock
    ImageManagerImp imageManagerImp;

    @InjectMocks
    SlideServiceImp slideServiceImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void getSlides() {
        List<Slide> slides = List.of(new Slide(), new Slide());
        when(slideDAOImp.getSlides()).thenReturn(slides);
        doNothing().when(imageManagerImp).uploadImage(any(), any(), any());
        assertEquals(slides, slideServiceImp.getSlides());
    }
}