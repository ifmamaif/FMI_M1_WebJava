package MainApp.services;

import MainApp.dto.Spell.*;
import MainApp.model.Spell;
import MainApp.repository.SpellRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SpellServiceImpl implements SpellService {
    private final SpellRepository spellRepository;

    public SpellServiceImpl(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }


    @Override
    public CreateSpellResponse insert(CreateSpellRequest request) {
        final var spell = new Spell();
        spell.setId(request.id());
        spell.setName(request.name());
        spell.setDescription(request.description());
        spell.setWhatDo(request.whatDo());
        spell.setSourceId(request.sourceId());
        spell.setTargetId(request.targetId());

        final var inserted = spellRepository.insert(spell);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "Spell created!" : inserted;

        return new CreateSpellResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetSpellResponse exists(GetSpellRequest request) {
        final var enemy = new Spell();
        enemy.setName(request.name());

        final var result = spellRepository.exists(enemy);
        HttpStatus status = result.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result.isEmpty() ? "Invalid Spell!" : result;

        return new GetSpellResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteSpellResponse delete(DeleteSpellRequest request) {
        final var enemy = new Spell();
        enemy.setName(request.name());

        final var result = spellRepository.delete(enemy);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "Spell deleted!" : "Invalid Spell!";

        return new DeleteSpellResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateSpellResponse update(UpdateSpellRequest request) {
        final var oldEnemy = new Spell();
        oldEnemy.setName(request.name());

        final var newEnemy = new Spell();
        newEnemy.setName(request.name());
        newEnemy.setDescription(request.description());
        newEnemy.setWhatDo(request.whatDo());
        newEnemy.setSourceId(request.sourceId());
        newEnemy.setTargetId(request.targetId());

        final var result = spellRepository.update(oldEnemy, newEnemy);
        return new UpdateSpellResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}