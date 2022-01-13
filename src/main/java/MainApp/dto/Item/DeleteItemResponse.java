package MainApp.dto.Item;

import org.springframework.http.ResponseEntity;

public record DeleteItemResponse(ResponseEntity<String> response) {
}
