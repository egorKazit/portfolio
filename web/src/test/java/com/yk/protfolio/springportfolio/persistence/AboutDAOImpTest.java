package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.About;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AboutDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private AboutDAOImp aboutDAOImp;

    @Test
    void getAbouts() {
        List<About> abouts = List.of(new About(), new About());
        when(daoEntityManager.getListOfEntities(About.class)).thenReturn(abouts);
        assertEquals(abouts, aboutDAOImp.getAbouts());
    }

    @Test
    void getAbout() {
        About about = new About();
        when(daoEntityManager.getEntityByKeys(any(), any())).thenReturn(about);
        assertEquals(about, aboutDAOImp.getAbout(1));
    }
}