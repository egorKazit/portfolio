package com.yk.protfolio.springportfolio.services;

import com.yk.protfolio.springportfolio.configuration.BotRegistration;
import com.yk.protfolio.springportfolio.persistence.GeneralValueDAO;
import com.yk.schema.GeneralValue;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@SpringBootTest
@Log4j2
class GenericValuesServiceImpTest {

    @Mock
    GeneralValueDAO generalValueDAO;

    @InjectMocks
    GenericValuesServiceImp genericValuesServiceImp;

    @MockitoBean
    BotRegistration botRegistration;

    @Test
    void testAllMethodsReturnsNotNull() {
        AtomicBoolean isFailed = new AtomicBoolean(false);
        when(generalValueDAO.getGeneralValues()).thenReturn(List.of());
        Arrays.stream(GenericValuesService.class.getDeclaredMethods())
                .forEach(method -> {
                    try {
                        Object result = method.invoke(genericValuesServiceImp);
                        if (result == null) {
                            log.atError().log("Object is nullable for method {}", method.getName());
                            isFailed.set(true);
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        log.atError().log("Error during invocation of method {} due error {}", method.getName(), e.getMessage());
                        isFailed.set(true);
                    }
                });
        assertFalse(isFailed.get());
    }

    @Test
    void testGetElementByName() {
        when(generalValueDAO.getGeneralValues()).thenReturn(List.of(
                GeneralValue.builder().id(0).internalName("Test").externalName("TestExt").build()));
        assertEquals("TestExt", genericValuesServiceImp.getElementByName("TEST", "TestExtDefault"));
        assertEquals("TestExtDefault", genericValuesServiceImp.getElementByName("TEST_OTHER", "TestExtDefault"));
    }

}