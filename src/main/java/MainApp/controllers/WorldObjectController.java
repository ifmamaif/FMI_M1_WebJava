package MainApp.controllers;


import MainApp.dto.WorldObject.*;
import MainApp.services.WorldObjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/worldobjects")
@RestController
@Validated
public class WorldObjectController {
    private WorldObjectService worldObjectService;

    public WorldObjectController(WorldObjectService worldObjectService) {
        this.worldObjectService = worldObjectService;
    }

    @GetMapping
    private ResponseEntity<GetWorldObjectResponse> addItem(@NonNull @RequestBody GetWorldObjectRequest request) {
        final var result = worldObjectService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateWorldObjectResponse> newItem(@NonNull @RequestBody CreateWorldObjectRequest request) {
        final var result = worldObjectService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateWorldObjectResponse> updateItem(@NonNull @RequestBody UpdateWorldObjectRequest request) {
        final var result = worldObjectService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteWorldObjectResponse> deleteItem(@NonNull @RequestBody DeleteWorldObjectRequest request) {
        final var result = worldObjectService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}