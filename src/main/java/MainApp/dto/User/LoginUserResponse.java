package MainApp.dto.User;

import org.springframework.http.ResponseEntity;

public record LoginUserResponse(ResponseEntity<String> response) {
}
