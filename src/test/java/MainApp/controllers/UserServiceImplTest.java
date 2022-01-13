package MainApp.controllers;

import MainApp.dto.User.DeleteUserRequest;
import MainApp.dto.User.LoginUserRequest;
import MainApp.dto.User.RegisterUserRequest;
import MainApp.dto.User.UpdateUserRequest;
import MainApp.model.User;
import MainApp.repository.UserRepository;
import MainApp.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class UserServiceImplTest {
    private final User user1 = new User(0, "testuser1", "testuser1");

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void testAddUser() {
        final var request = new RegisterUserRequest(user1.getUsername(), user1.getPass());
        var response = userService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);

        userRepository.delete(user1);
    }

    @Test
    void testAddUserThatExist() {
        final var request = new RegisterUserRequest(user1.getUsername(), user1.getPass());
        var response = userService.insert(request);

        var response1 = userService.insert(request);
        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);
        assertSame(response1.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);

        userRepository.delete(user1);
    }

    @Test
    void testExistUserThatExist() {
        final var request = new RegisterUserRequest(user1.getUsername(), user1.getPass());
        var response = userService.insert(request);

        final var request1 = new LoginUserRequest(user1.getUsername(), user1.getPass());
        var response1 = userService.exists(request1);
        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response1.response().getStatusCode(), HttpStatus.FOUND);

        userRepository.delete(user1);
    }

    @Test
    void testExistUserThatDoesntExist() {
        final var request1 = new LoginUserRequest(user1.getUsername(), user1.getPass());
        var response1 = userService.exists(request1);
        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response1.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void testUpdateUserThatExist() {
        final var request = new RegisterUserRequest(user1.getUsername(), user1.getPass());
        var response = userService.insert(request);

        final var newPass = "newPass";
        final var request1 = new UpdateUserRequest(user1.getUsername(), user1.getPass(), newPass);
        var response1 = userService.update(request1);

        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.OK);
        assertSame(response1.response().getStatusCode(), HttpStatus.OK);
        assertEquals(response1.response().getBody(), "Password changed");

        final var request2 = new LoginUserRequest(user1.getUsername(), newPass);
        var response2 = userService.exists(request2);
        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.OK);
        assertSame(response1.response().getStatusCode(), HttpStatus.OK);

        userRepository.delete(user1);
    }

    @Test
    void testUpdateUserThatDoesntExist() {
        final var newPass = "newPass";
        final var request1 = new UpdateUserRequest(user1.getUsername(), user1.getPass(), newPass);
        final var response1 = userService.update(request1);

        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.OK);
        assertSame(response1.response().getStatusCode(), HttpStatus.OK);
        assertEquals(response1.response().getBody(), "Invalid credentials with username \"testuser1\" and password \"testuser1\"");
    }

    @Test
    void testDeleteUserThatExist() {
        final var request = new RegisterUserRequest(user1.getUsername(), user1.getPass());
        final var response = userService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);

        final var request1 = new DeleteUserRequest(user1.getUsername(), user1.getPass());
        final var response1 = userService.delete(request1);

        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.OK);
        assertSame(response1.response().getStatusCode(), HttpStatus.OK);
    }

    @Test
    void testDeleteUserThatDoesntExist() {
        final var request1 = new DeleteUserRequest(user1.getUsername(), user1.getPass());
        final var response1 = userService.delete(request1);

        // assert
        assertEquals(response1.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response1.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}