package com.laborotoryproject.weather.parser.entity.enums;

public enum Permissions {
    WEATHER_GET("get:weather"),
    WEATHER_DELETE("delete:weather"),;

    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
