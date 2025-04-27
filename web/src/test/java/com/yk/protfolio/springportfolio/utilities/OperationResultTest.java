package com.yk.protfolio.springportfolio.utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationResultTest {

    @Test
    void getMessages() {
        OperationResult<Object> objectOperationResult
                = OperationResult.builder()
                .messages(List.of("Test 1", "Test 2"))
                .build();
        assertEquals("[\"Test 1\",\"Test 2\"]", objectOperationResult.getMessages());
    }
}