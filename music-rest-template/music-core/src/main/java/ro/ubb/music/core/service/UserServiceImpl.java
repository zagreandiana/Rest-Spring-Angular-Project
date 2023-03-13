package ro.ubb.music.core.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.music.core.exceptions.ServiceException;
import ro.ubb.music.core.model.User;
import ro.ubb.music.core.repository.UserRepository;
import ro.ubb.music.core.utils.ExceptionMessages;
import ro.ubb.music.core.utils.UserRoles;
import ro.ubb.music.core.utils.UserStatuses;

import java.util.List;
import java.util.Optional;

@Slf4j
// @Slf4j is used for logs (lombok)
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    // @Autowired allows Spring to resolve and inject collaborating beans into our bean
    // after enabling annotation injection, we can use autowiring on properties, setters, and constructors

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

        // if an Optional object contains a value => is present
        // if it doesn't contain a value => is empty
        // in this case if a user is empty I throw my own exception with a specific message

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

    // the transactional annotation itself defines the scope of a single database transaction
    // the database transaction happens inside the scope of a persistence context
    // transaction propagation are handled automatically
    // unfortunately is hard to debug
    // save in db (after update)

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

        // here I used Optional because the user that I wanna delete
        // may or may not be present
        // thus, just in case if the user is present I will delete it
        // in addition, calling the "orElseThrow" method helps me manage exceptions

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
