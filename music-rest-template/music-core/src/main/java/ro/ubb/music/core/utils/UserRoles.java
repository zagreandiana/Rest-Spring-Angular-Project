package ro.ubb.music.core.utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRoles {

    USER,
    ADMIN;

    public static UserRoles of(String role) {
        try {
            return UserRoles.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserRoles.USER;
        }
    }
}
