package ro.ubb.music.core.repository;


import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.utils.UserRoles;
import ro.ubb.catalog.core.utils.UserStatuses;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {

    List<User> findAllByRole(UserRoles role);

    List<User> findAllByStatus(UserStatuses status);
}
