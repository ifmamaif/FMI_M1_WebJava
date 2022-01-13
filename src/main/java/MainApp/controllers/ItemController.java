package MainApp.controllers;

import MainApp.dto.Item.*;
import MainApp.services.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/items")
@RestController
@Validated
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    private ResponseEntity<GetItemResponse> addItem(@NonNull @RequestBody GetItemRequest request) {
        final var result = itemService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateItemResponse> newItem(@NonNull @RequestBody CreateItemRequest request) {
        final var result = itemService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateItemResponse> updateItem(@NonNull @RequestBody UpdateItemRequest request) {
        final var result = itemService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteItemResponse> deleteItem(@NonNull @RequestBody DeleteItemRequest request) {
        final var result = itemService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}