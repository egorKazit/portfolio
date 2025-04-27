package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.AboutDAOImp;
import com.yk.schema.About;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void getGeneralAboutWithNull() {
        doNothing().when(imageManager).uploadImage(any(), any(), any());
        when(aboutDAOImp.getAbout(0)).thenReturn(null);
        assertNull(aboutServiceImp.getGeneralAbout());
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