package ro.ubb.music.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.service.UserService;
import ro.ubb.catalog.core.utils.UserRoles;
import ro.ubb.catalog.core.utils.UserStatuses;
import ro.ubb.catalog.web.converter.UserConverter;
import ro.ubb.catalog.web.dto.UserDto;
import ro.ubb.catalog.web.dto.UserResponse;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @GetMapping(value = "/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            User user = userService.readOne(id);
            UserResponse userResponse = new UserResponse();
            userResponse.setUsers(Set.of(userConverter.convertModelToDto(user)));

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            UserResponse userResponse = new UserResponse();
            userResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }

    @GetMapping()
    ResponseEntity<UserResponse> getUsers() {
        List<User> users = userService.readAll();
        Set<UserDto> dtoSet = userConverter.convertModelsToDtos(users);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(dtoSet);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/role={role}")
    ResponseEntity<UserResponse> getUsersByRole(@PathVariable String role) {
        UserRoles userRole = UserRoles.of(role);
        List<User> users = userService.readAll(userRole);
        Set<UserDto> dtoSet = userConverter.convertModelsToDtos(users);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(dtoSet);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/status={status}")
    ResponseEntity<UserResponse> getUsersByStatus(@PathVariable String status) {
        UserStatuses userStatus = UserStatuses.of(status);
        List<User> users = userService.readAll(userStatus);
        Set<UserDto> dtoSet = userConverter.convertModelsToDtos(users);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(dtoSet);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<UserResponse> saveUser(@RequestBody UserDto userDto) {
        User user = userConverter.convertDtoToModel(userDto);

        try {
            User savedUser = userService.create(user);
            UserDto savedDto = userConverter.convertModelToDto(savedUser);

            UserResponse userResponse = new UserResponse();
            userResponse.setUsers(Set.of(savedDto));

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            UserResponse userResponse = new UserResponse();
            userResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }


    @PutMapping(value = "/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userConverter.convertDtoToModel(userDto);

        try {
            User updatedUser = userService.update(id, user);
            UserDto updatedDto = userConverter.convertModelToDto(updatedUser);

            UserResponse userResponse = new UserResponse();
            userResponse.setUsers(Set.of(updatedDto));

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            UserResponse userResponse = new UserResponse();
            userResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<UserResponse> deleteUserById(@PathVariable Long id) {
        try {
            User deletedUser = userService.delete(id);
            UserDto deletedDto = userConverter.convertModelToDto(deletedUser);

            UserResponse userResponse = new UserResponse();
            userResponse.setUsers(Set.of(deletedDto));

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (ServiceException e) {
            UserResponse userResponse = new UserResponse();
            userResponse.setErrorMessage(e.getMessage());

            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }
    }
}
