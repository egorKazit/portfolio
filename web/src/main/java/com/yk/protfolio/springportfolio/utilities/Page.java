package com.yk.protfolio.springportfolio.utilities;

import lombok.Getter;

public enum Page {
    INDEX("index"), ABOUT("about"), WORK("work"), PROJECT("project"), CONTACT("contact"), DETAILS("details"), ERROR("error");

    Page(String name) {
        this.name = name;
    }

    @Getter
    private final String name;

}
