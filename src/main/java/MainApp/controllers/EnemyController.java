package MainApp.controllers;

import MainApp.dto.Enemy.*;
import MainApp.model.Enemy;
import MainApp.services.CharacterService;
import MainApp.services.EnemyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/enemies")
@RestController
@Validated
public class EnemyController {
    private EnemyService enemyService;

    public EnemyController(EnemyService enemyService) {
        this.enemyService = enemyService;
    }

    @GetMapping
    private ResponseEntity<GetEnemyResponse> addCharacter(@NonNull @RequestBody GetEnemyRequest request) {
        final var result = enemyService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateEnemyResponse> newCharacter(@NonNull @RequestBody CreateEnemyRequest request) {
        final var result = enemyService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateEnemyResponse> updateCharacter(@NonNull @RequestBody UpdateEnemyRequest request) {
        final var result = enemyService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteEnemyResponse> deleteCharacter(@NonNull @RequestBody DeleteEnemyRequest request) {
        final var result = enemyService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}