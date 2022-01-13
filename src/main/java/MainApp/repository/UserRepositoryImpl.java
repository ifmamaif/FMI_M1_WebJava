package MainApp.repository;

import MainApp.model.User;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(User user) {
        try {
            jdbcTemplate.update("insert into user values (?, ?, ?)",
                    user.getId(), user.getUsername(), user.getPass());
        } catch (DuplicateKeyException e) {
            return "An user with this username already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public boolean exists(User user) {
        try {
            final var foundId = jdbcTemplate.queryForObject("select id from user where username = ? and password = ?", String.class, user.getUsername(), user.getPass());

            return foundId != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(User user) {
        if (!exists(user)) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from user where username = ?", user.getUsername());
        return true;
    }

    @Override
    public String update(User oldUser, User newUser) {
        if (!oldUser.getUsername().equals(newUser.getUsername())) {
            return "Something went wrongs";
        }

        if (!exists(oldUser)) {
            return "Invalid credentials with username \"" + oldUser.getUsername() + "\" and password \"" + oldUser.getPass() + "\"";
        }

        try {
            final var sqlCommand = "update user set password = ? where username = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newUser.getPass(), newUser.getUsername()});
            return foundId > 0 ? "Password changed" : "Couldn't update the user";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}