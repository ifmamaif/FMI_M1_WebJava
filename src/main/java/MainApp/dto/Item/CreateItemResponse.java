package MainApp.dto.Item;

import org.springframework.http.ResponseEntity;

public record CreateItemResponse(ResponseEntity<String> response) {
}
