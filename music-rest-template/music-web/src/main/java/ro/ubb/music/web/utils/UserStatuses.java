package ro.ubb.music.web.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum UserStatuses {

    ACTIVE("active"),
    INACTIVE("inactive");

    public final String value;

    @JsonCreator
    public static UserStatuses of(String role) {
        try {
            return UserStatuses.valueOf(role.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return UserStatuses.ACTIVE;
        }
    }
}
