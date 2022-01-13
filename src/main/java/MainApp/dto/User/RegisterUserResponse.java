package MainApp.dto.User;

import org.springframework.http.ResponseEntity;

public record RegisterUserResponse(ResponseEntity<String> response) {
}
