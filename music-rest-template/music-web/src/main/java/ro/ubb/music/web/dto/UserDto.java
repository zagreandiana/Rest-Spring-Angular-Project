package ro.ubb.music.web.dto;

import lombok.*;
import ro.ubb.music.web.utils.UserRoles;
import ro.ubb.music.web.utils.UserStatuses;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRoles role;
    private UserStatuses status;


}
