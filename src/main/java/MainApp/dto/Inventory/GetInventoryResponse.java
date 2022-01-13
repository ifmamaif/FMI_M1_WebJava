package MainApp.dto.Inventory;

import org.springframework.http.ResponseEntity;

public record GetInventoryResponse(ResponseEntity<String> response) {
}
