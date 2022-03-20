package com.yk.protfolio.springportfolio.utilities;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.configurationprocessor.json.JSONArray;

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
        messages.forEach(resultArray::put);
        return resultArray.toString();
    }
}
