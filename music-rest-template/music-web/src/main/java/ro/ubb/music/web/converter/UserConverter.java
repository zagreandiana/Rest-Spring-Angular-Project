package ro.ubb.music.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.music.core.model.User;
import ro.ubb.music.core.utils.UserRoles;
import ro.ubb.music.core.utils.UserStatuses;
import ro.ubb.music.web.dto.UserDto;

@Component
public class UserConverter extends BaseConverter<User, UserDto> {
    @Override
    public User convertDtoToModel(UserDto dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(UserRoles.of(dto.getRole().name()))
                .status(UserStatuses.of(dto.getStatus().name()))
                .build();
        user.setId(dto.getId());

        return user;
    }

    @Override
    public UserDto convertModelToDto(User user) {
        UserDto dto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(ro.ubb.music.web.utils.UserRoles.of(user.getRole().name()))
                .status(ro.ubb.music.web.utils.UserStatuses.of(user.getStatus().name()))
                .build();
        dto.setId(user.getId());

        return dto;
    }
}
