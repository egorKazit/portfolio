package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.Social;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SocialDAOImpTest {
    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private SocialDAOImp socialDAOImp;

    @Test
    void getSlides() {
        List<Social> socials = List.of(new Social(), new Social());
        when(daoEntityManager.getListOfEntities(Social.class)).thenReturn(socials);
        assertEquals(socials, socialDAOImp.getSocials());
    }
}