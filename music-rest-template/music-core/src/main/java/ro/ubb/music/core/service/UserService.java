package ro.ubb.music.core.service;


import ro.ubb.music.core.model.User;
import ro.ubb.music.core.utils.UserRoles;
import ro.ubb.music.core.utils.UserStatuses;

import java.util.List;

public interface UserService {

    User create(User user);

    User readOne(Long id);

    List<User> readAll();

    List<User> readAll(UserStatuses status);

    List<User> readAll(UserRoles role);

    User update(Long id, User user);

    User delete(Long id);
}
