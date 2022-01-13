package MainApp.services;

import MainApp.dto.Character.*;

public interface CharacterService {
    CreateCharacterResponse insert(CreateCharacterRequest request);

    GetCharacterResponse exists(GetCharacterRequest request);

    DeleteCharacterResponse delete(DeleteCharacterRequest request);

    UpdateCharacterResponse update(UpdateCharacterRequest request);
}