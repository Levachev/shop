package com.example.shop.models;

public enum RoleEnum {
    MANUFACTURER("MANUFACTURER"),
    ADMIN("ADMIN"),
    USER("USER");

    private final String value;

    RoleEnum(final String value) {
        this.value = value;
    }

    public String getValueAsRole() {
        return "ROLE_"+value;
    }

}
