package ro.ubb.music.web.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum UserRoles {

    USER("user"),
    ADMIN("admin");

    public final String value;

    @JsonCreator
    public static UserRoles of(String role) {
        try {
            return UserRoles.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserRoles.USER;
        }
    }
}
