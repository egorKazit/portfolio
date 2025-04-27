package com.yk.protfolio.springportfolio.utilities;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONArray;

import java.util.List;

@Builder
@Log4j2
public class OperationResult<Entity> {
    @Getter
    boolean isSuccess;
    List<String> messages;
    @Getter
    Entity entity;

    public String getMessages() {
        JSONArray resultArray = new JSONArray();
        if (messages == null)
            return null;
        messages.forEach(resultArray::put);
        return resultArray.toString();
    }
}
