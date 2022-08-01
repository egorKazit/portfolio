package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.dao.DAOEntityManager;
import com.yk.schema.GeneralValue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneralValueDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private GeneralValueDAOImp generalValueDAOImp;

    @Test
    void getGeneralValues() {
        GeneralValue generalValue = GeneralValue.builder()
                .id(91)
                .internalName("name")
                .externalName("Name")
                .build();
        when(daoEntityManager.getListOfEntities(GeneralValue.class)).thenReturn(List.of(generalValue));
        assertEquals(List.of(generalValue), generalValueDAOImp.getGeneralValues());
        assertEquals(91, generalValueDAOImp.getGeneralValues().get(0).getId());
        assertEquals("name", generalValueDAOImp.getGeneralValues().get(0).getInternalName());
        assertEquals("Name", generalValueDAOImp.getGeneralValues().get(0).getExternalName());
    }
}