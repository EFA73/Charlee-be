package com.efa73.charleecollector.interfaces.dto.request;

import lombok.Getter;

@Getter
public enum EventType {
    ON("시동 ON"),
    CYCLE("주행중"),
    OFF("시동 OFF");

    private final String type;

    private EventType(String type) {
        this.type = type;
    }
}