package MainApp.dto.Character;

import org.springframework.http.ResponseEntity;

public record GetCharacterResponse(ResponseEntity<String> response) {
}
