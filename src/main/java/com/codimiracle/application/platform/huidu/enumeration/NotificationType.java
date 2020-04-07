package com.codimiracle.application.platform.huidu.enumeration;

import java.util.Arrays;
import java.util.Objects;

public enum NotificationType {
    Subscribe("subscribe"),
    Message("message");
    private final String type;

    NotificationType(String type) {
        this.type = type;
    }

    public static NotificationType valueOfCode(String code) {
        return Arrays.stream(NotificationType.values()).filter((e) -> Objects.equals(e.type, code)).findFirst().orElseGet(() -> Enum.valueOf(NotificationType.class, code));
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return getType();
    }
}
