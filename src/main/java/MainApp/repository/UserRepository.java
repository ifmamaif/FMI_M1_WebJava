package MainApp.repository;

import MainApp.model.User;

public interface UserRepository {
    String insert(User user);

    boolean exists(User user);

    boolean delete(User user);

    String update(User oldUser, User newUser);
}