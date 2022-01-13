package MainApp.dto.Item;

import org.springframework.http.ResponseEntity;

public record GetItemResponse(ResponseEntity<String> response) {
}
