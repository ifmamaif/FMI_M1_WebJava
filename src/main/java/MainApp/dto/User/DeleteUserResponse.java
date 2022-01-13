package MainApp.dto.User;

import org.springframework.http.ResponseEntity;

public record DeleteUserResponse(ResponseEntity<String> response) {
}
