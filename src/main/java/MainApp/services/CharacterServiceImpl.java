package MainApp.services;

import MainApp.dto.Character.*;
import MainApp.model.Character;
import MainApp.repository.CharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public CreateCharacterResponse insert(CreateCharacterRequest request) {
        final var character = new Character();
        character.setId(request.id());
        character.setNickname(request.nickname());
        character.setLevel(request.level());
        character.setInventoryId(request.idInventory());
        character.setUserId(request.idUser());
        character.setWorldObjectId(request.idWorldObject());

        final var inserted = characterRepository.insert(character);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "Character created!" : inserted;

        return new CreateCharacterResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetCharacterResponse exists(GetCharacterRequest request) {
        final var character = new Character();
        character.setNickname(request.nickname());

        final var result = characterRepository.exists(character);
        HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result == null ? "Invalid Character!" : result.toString();

        return new GetCharacterResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteCharacterResponse delete(DeleteCharacterRequest request) {
        final var character = new Character();
        character.setNickname(request.nickname());

        final var result = characterRepository.delete(character);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "Character deleted!" : "Invalid Character!";

        return new DeleteCharacterResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateCharacterResponse update(UpdateCharacterRequest request) {
        final var oldCharacter = new Character();
        oldCharacter.setNickname(request.nickname());

        final var newCharacter = new Character();
        newCharacter.setNickname(request.nickname());
        newCharacter.setLevel(request.level());
        newCharacter.setInventoryId(request.idInventory());
        newCharacter.setUserId(request.idUser());
        newCharacter.setWorldObjectId(request.idWorldObject());

        final var result = characterRepository.update(oldCharacter, newCharacter);
        return new UpdateCharacterResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}