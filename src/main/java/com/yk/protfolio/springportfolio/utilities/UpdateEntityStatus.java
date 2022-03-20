package com.yk.protfolio.springportfolio.utilities;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PUBLIC,toBuilder = true)
@Getter
public class UpdateEntityStatus<Entity> {
    public static final String UPDATED = "UPDATED";
    public static final String FAILED = "FAILED";
    String status;
    List<String> messages;
    Entity entity;
}
