package com.yk.protfolio.springportfolio.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.schema.GeneralValue;
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
    }
}