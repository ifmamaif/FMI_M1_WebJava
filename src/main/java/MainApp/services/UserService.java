package MainApp.services;

import MainApp.dto.User.*;

public interface UserService {
    RegisterUserResponse insert(RegisterUserRequest request);

    LoginUserResponse exists(LoginUserRequest request);

    DeleteUserResponse delete(DeleteUserRequest request);

    UpdateUserResponse update(UpdateUserRequest request);
}