package ro.ubb.music.web.dto;

import lombok.*;
import ro.ubb.catalog.web.utils.UserRoles;
import ro.ubb.catalog.web.utils.UserStatuses;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoles role;
    private UserStatuses status;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
