package com.yk.protfolio.springportfolio.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import com.yk.protfolio.springportfolio.persistence.GeneralValueDAO;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class GenericValuesServiceImpTest {

    @Mock
    GeneralValueDAO generalValueDAO;

    @InjectMocks
    GenericValuesServiceImp genericValuesServiceImp;

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

}