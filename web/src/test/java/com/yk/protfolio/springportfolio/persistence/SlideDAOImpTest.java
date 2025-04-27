package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Slide;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SlideDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private SlideDAOImp slideDAOImp;

    @Test
    void getSlides() {
        List<Slide> slides = List.of(new Slide(), new Slide());
        when(daoEntityManager.getListOfEntities(Slide.class)).thenReturn(slides);
        assertEquals(slides, slideDAOImp.getSlides());
    }
}