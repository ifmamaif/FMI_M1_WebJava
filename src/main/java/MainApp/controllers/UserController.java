package MainApp.controllers;

import MainApp.dto.User.*;
import MainApp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/users")
@RestController
@Validated
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<LoginUserResponse> loginUser(@NonNull @RequestBody LoginUserRequest request) {
        final var result = userService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping()
    public ResponseEntity<RegisterUserResponse> registerUser(@NonNull @RequestBody RegisterUserRequest request) {
        final var result = userService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping()
    public ResponseEntity<DeleteUserResponse> deleteUser(@NonNull @RequestBody DeleteUserRequest request) {
        final var result = userService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping()
    public ResponseEntity<UpdateUserResponse> deleteUser(@NonNull @RequestBody UpdateUserRequest request) {
        final var result = userService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}