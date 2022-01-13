package MainApp.controllers;

import MainApp.dto.Character.*;
import MainApp.services.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/characters")
@RestController
@Validated
public class CharacterController {
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    private ResponseEntity<GetCharacterResponse> addCharacter(@NonNull @RequestBody GetCharacterRequest request) {
        final var result = characterService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateCharacterResponse> newCharacter(@NonNull @RequestBody CreateCharacterRequest request) {
        final var result = characterService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateCharacterResponse> updateCharacter(@NonNull @RequestBody UpdateCharacterRequest request) {
        final var result = characterService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteCharacterResponse> deleteCharacter(@NonNull @RequestBody DeleteCharacterRequest request) {
        final var result = characterService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}