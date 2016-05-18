package com.infosea.examApp.enums;

/**
 * Created by infosea on 2016/5/18.
 */
public enum UserEnum {
    ADMIN("管理员"),;
    private String role;

    UserEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public static UserEnum roleOf(String role) {
        for (UserEnum state : values()) {
            if (state.getRole() == role) {
                return state;
            }
        }
        return null;
    }
}
