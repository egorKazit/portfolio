package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.persistence.SocialDAOImp;
import com.yk.schema.Social;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class SocialServiceImpTest {
    @Mock
    SocialDAOImp socialDAOImp;

    @Mock
    ImageManagerImp imageManagerImp;

    @InjectMocks
    SocialServiceImp socialServiceImp;

    @Test
    void getSlides() {
        List<Social> socials = List.of(new Social(), new Social());
        when(socialDAOImp.getSocials()).thenReturn(socials);
        doNothing().when(imageManagerImp).uploadImage(any(), any(), any());
        assertEquals(socials, socialServiceImp.getSocials());
    }
}