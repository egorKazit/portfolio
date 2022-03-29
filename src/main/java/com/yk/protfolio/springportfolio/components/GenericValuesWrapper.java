package com.yk.protfolio.springportfolio.components;

import com.yk.protfolio.springportfolio.services.GenericValuesService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Generic value wrapper to keep GenericValuesService as singleton
 */
@Component
public class GenericValuesWrapper {

    @Getter
    private static GenericValuesService genericValuesService;

    /**
     * Constructor with initialization GenericValuesService
     *
     * @param genericValuesService GenericValuesService instance
     */
    @Autowired
    private GenericValuesWrapper(GenericValuesService genericValuesService) {
        GenericValuesWrapper.genericValuesService = genericValuesService;
    }

}
