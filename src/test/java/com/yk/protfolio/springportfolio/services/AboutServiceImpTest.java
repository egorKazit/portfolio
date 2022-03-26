package com.yk.protfolio.springportfolio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.persistence.AboutDAOImp;
import com.yk.protfolio.springportfolio.schema.About;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AboutServiceImpTest {

    @Mock
    AboutDAOImp aboutDAOImp;

    @Mock
    ImageManager imageManager;

    @InjectMocks
    AboutServiceImp aboutServiceImp;

    @Test
    void getGeneralAbout() {
        About about = new About();
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(aboutDAOImp.getAbout(0)).thenReturn(about);
        assertEquals(about, aboutServiceImp.getGeneralAbout());
        assertFalse(aboutServiceImp.getGeneralAbout().isHidden());
    }

    @Test
    void getDetailedAbout() {
        About about = new About();
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(aboutDAOImp.getAbout(1)).thenReturn(about);
        assertEquals(about, aboutServiceImp.getDetailedAbout(1));
        assertTrue(aboutServiceImp.getDetailedAbout(1).isHidden());
        when(aboutDAOImp.getAbout(2)).thenReturn(null);
        assertNull(aboutServiceImp.getDetailedAbout(2));
    }

    @Test
    void getAboutList() {
        List<About> abouts = List.of(new About(), new About());
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(aboutDAOImp.getAbouts()).thenReturn(abouts);
        assertEquals(abouts, aboutServiceImp.getAboutList());
    }

}