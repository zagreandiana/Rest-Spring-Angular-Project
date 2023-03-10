package ro.ubb.music.core.repository;


import ro.ubb.music.core.model.User;
import ro.ubb.music.core.utils.UserRoles;
import ro.ubb.music.core.utils.UserStatuses;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAllByRole(UserRoles role);

    List<User> findAllByStatus(UserStatuses status);
}
