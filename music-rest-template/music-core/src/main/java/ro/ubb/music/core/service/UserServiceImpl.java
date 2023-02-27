package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.catalog.core.exceptions.ServiceException;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.repository.UserRepository;
import ro.ubb.catalog.core.utils.ExceptionMessages;
import ro.ubb.catalog.core.utils.UserRoles;
import ro.ubb.catalog.core.utils.UserStatuses;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        log.debug("### Entering create user method.");

        try {
            User savedUser = userRepository.save(user);
            log.debug("### Exiting create user method.");

            return savedUser;
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_EMAIL_ALREADY_EXISTS.message);
        }
    }

    @Override
    public User readOne(Long id) {
        log.debug("### Entering read user method.");
        Optional<User> optional = userRepository.findById(id);

        User user = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));
        log.debug("### Exiting read user method.");

        return user;
    }

    @Override
    public List<User> readAll() {
        log.debug("### Entering read users method.");
        List<User> users = userRepository.findAll();
        log.debug("### Exiting read users method.");

        return users;
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        log.debug("### Entering update users method.");
        Optional<User> optional = userRepository.findById(id);

        User userToBeUpdated = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        userToBeUpdated.setFirstName(user.getFirstName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setEmail(user.getEmail());
        userToBeUpdated.setPassword(user.getPassword());
        userToBeUpdated.setRole(user.getRole());
        userToBeUpdated.setStatus(user.getStatus());
        log.debug("### Exiting update users method.");

        return userToBeUpdated;
    }

    @Override
    public User delete(Long id) {
        log.debug("### Entering delete user method.");
        Optional<User> optional = userRepository.findById(id);

        User userToBeDeleted = optional.orElseThrow(
                () -> new ServiceException(ExceptionMessages.ENTITY_WITH_GIVEN_ID_DOES_NOT_EXIST.message));

        userRepository.deleteById(userToBeDeleted.getId());
        log.debug("### Exiting delete user method.");

        return userToBeDeleted;
    }

    @Override
    public List<User> readAll(UserStatuses status) {
        return userRepository.findAllByStatus(status);
    }

    @Override
    public List<User> readAll(UserRoles role) {
        return userRepository.findAllByRole(role);
    }
}
