package MainApp.dto.Inventory;

import org.springframework.http.ResponseEntity;

public record DeleteInventoryResponse(ResponseEntity<String> response) {
}
