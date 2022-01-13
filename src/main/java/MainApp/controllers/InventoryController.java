package MainApp.controllers;

import MainApp.dto.Inventory.*;
import MainApp.services.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inventories")
@RestController
@Validated
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    private ResponseEntity<GetInventoryResponse> addItem(@RequestBody GetInventoryRequest request) {
        final var result = inventoryService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateInventoryResponse> newItem(@RequestBody CreateInventoryRequest request) {
        final var result = inventoryService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateInventoryResponse> updateItem(@RequestBody UpdateInventoryRequest request) {
        final var result = inventoryService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteInventoryResponse> deleteItem(@RequestBody DeleteInventoryRequest request) {
        final var result = inventoryService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}