package com.yk.processor;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder(access = AccessLevel.PUBLIC, toBuilder = true)
@Getter
public class UpdateEntityStatus<E> {
    public static final String UPDATED = "UPDATED";
    public static final String FAILED = "FAILED";
    String status;
    List<String> messages;
    E entity;
}
