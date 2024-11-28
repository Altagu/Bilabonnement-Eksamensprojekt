package com.example.bilabonnementeksamensprojekt.Utility;

public enum UserType {
    ADMIN(0),
    DATA_USER(1),
    USER(2);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType fromValue(int value) {
        for (UserType userType : values()) {
            if (userType.getValue() == value) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Unexpected value: " + value);
    }
}
