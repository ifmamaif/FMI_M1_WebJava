package MainApp.repository;

import MainApp.model.Character;

public interface CharacterRepository {
    String insert(Character character);

    Character exists(Character character);

    boolean delete(Character character);

    String update(Character oldcharacter, Character newcharacter);
}