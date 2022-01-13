package MainApp.dto.Character;

import org.springframework.http.ResponseEntity;

public record DeleteCharacterResponse(ResponseEntity<String> response) {
}
