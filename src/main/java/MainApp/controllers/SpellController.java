package MainApp.controllers;

import MainApp.dto.Spell.*;
import MainApp.services.ItemService;
import MainApp.services.SpellService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.util.annotation.NonNull;

@RequestMapping("/spells")
@RestController
@Validated
public class SpellController {
    private SpellService spellService;

    public SpellController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GetMapping
    private ResponseEntity<GetSpellResponse> addItem(@NonNull @RequestBody GetSpellRequest request) {
        final var result = spellService.exists(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PostMapping
    private ResponseEntity<CreateSpellResponse> newItem(@NonNull @RequestBody CreateSpellRequest request) {
        final var result = spellService.insert(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @PatchMapping
    private ResponseEntity<UpdateSpellResponse> updateItem(@NonNull @RequestBody UpdateSpellRequest request) {
        final var result = spellService.update(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }

    @DeleteMapping
    private ResponseEntity<DeleteSpellResponse> deleteItem(@NonNull @RequestBody DeleteSpellRequest request) {
        final var result = spellService.delete(request);
        return ResponseEntity.status(result.response().getStatusCodeValue()).body(result);
    }
}