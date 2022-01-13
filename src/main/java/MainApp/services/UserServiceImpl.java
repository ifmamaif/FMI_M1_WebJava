package MainApp.services;

import MainApp.dto.User.*;
import MainApp.model.User;
import MainApp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterUserResponse insert(RegisterUserRequest request) {
        final var user = new User();
        user.setUsername(request.username());
        user.setPass(request.password());

        final var inserted = userRepository.insert(user);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "User created!" : inserted;

        return new RegisterUserResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public LoginUserResponse exists(LoginUserRequest request) {
        final var user = new User(request.username(), request.password());

        final var result = userRepository.exists(user);
        HttpStatus status;
        String reason;
        if (result) {
            status = HttpStatus.FOUND;
            reason = "The User exist!";
        } else {
            status = HttpStatus.NOT_FOUND;
            reason = "Invalid Credentials!";
        }

        return new LoginUserResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteUserResponse delete(DeleteUserRequest request) {
        final var user = new User(request.username(), request.password());

        final var result = userRepository.delete(user);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "User deleted!" : "Invalid credentials!";

        return new DeleteUserResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        final var oldUser = new User(request.username(), request.oldPassword());
        final var newUser = new User(request.username(), request.newPassword());

        final var result = userRepository.update(oldUser, newUser);
        return new UpdateUserResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}