package com.yk.protfolio.springportfolio.persistence;

import com.yk.dao.DAOEntityManager;
import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.schema.GeneralValue;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GeneralValueDAOImpTest {

    @Mock
    DAOEntityManager daoEntityManager;

    @InjectMocks
    private GeneralValueDAOImp generalValueDAOImp;

    @MockitoBean
    BotRegistration botRegistration;

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