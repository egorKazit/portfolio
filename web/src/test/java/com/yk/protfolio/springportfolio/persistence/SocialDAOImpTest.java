package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.schema.Social;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SocialDAOImpTest {
    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private SocialDAOImp socialDAOImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void getSlides() {
        List<Social> socials = List.of(new Social(), new Social());
        when(daoEntityManager.getListOfEntities(Social.class)).thenReturn(socials);
        assertEquals(socials, socialDAOImp.getSocials());
    }
}