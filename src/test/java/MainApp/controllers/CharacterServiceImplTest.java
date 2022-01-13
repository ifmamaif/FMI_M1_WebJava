package MainApp.controllers;

import MainApp.dto.Character.CreateCharacterRequest;
import MainApp.dto.Character.DeleteCharacterRequest;
import MainApp.dto.Character.GetCharacterRequest;
import MainApp.dto.Character.UpdateCharacterRequest;
import MainApp.model.Character;
import MainApp.repository.CharacterRepository;
import MainApp.services.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class CharacterServiceImplTest {
    private final Character character1 = new Character(0, "testcharacter1", 1, 4, 4, 1);

    @Autowired
    private CharacterController characterController;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(characterRepository).isNotNull();
    }

    public void RegisterCharacterWithoutDelete() {
        final var request = new CreateCharacterRequest(character1.getId(), character1.getNickname(), character1.getLevel(), character1.getInventoryId(), character1.getUserId(), character1.getWorldObjectId());
        var response = characterService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteCharacter() {
        final var request = new DeleteCharacterRequest(character1.getNickname());
        var response = characterService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetCharacter(Character character) {
        final var request = new GetCharacterRequest(character.getNickname());
        var response = characterService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "Character{nickname='" + character.getNickname() +
                "', level=" + character.getLevel() +
                ", inventoryId=" + character.getInventoryId() +
                ", userId=" + character.getUserId() +
                ", worldObjectId=" + character.getWorldObjectId() + "}";

        assertEquals(expect, response.response().getBody());
    }

    @Test
    public void testCreateCharacterThatDoesntExist() throws Exception {
        try {
            RegisterCharacterWithoutDelete();
        } finally {
            DeleteCharacter();
        }
    }

    @Test
    public void testCreateCharacterThatDoesExist() throws Exception {
        try {
            RegisterCharacterWithoutDelete();
            final var request = new CreateCharacterRequest(character1.getId(), character1.getNickname(), character1.getLevel(), character1.getInventoryId(), character1.getUserId(), character1.getWorldObjectId());
            final var response = characterService.insert(request);


            // assert
            assertEquals(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);
            assertSame(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);

        } finally {
            DeleteCharacter();
        }
    }

    @Test
    public void testGetCharacterThatDoesExist() throws Exception {
        try {

            RegisterCharacterWithoutDelete();
            GetCharacter(character1);
        } finally {
            DeleteCharacter();
        }
    }

    @Test
    public void testGetCharacterThatDoesntExist() throws Exception {
        final var request = new GetCharacterRequest(character1.getNickname());
        final var response = characterService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateCharacterThatDoesExist() throws Exception {
        try {
            RegisterCharacterWithoutDelete();

            int character1NewLvl = 3;
            int newInv = 5;
            int newUser = 5;
            int newWorldObject = 1;

            final var request = new UpdateCharacterRequest(character1.getNickname(), character1NewLvl, newInv, newUser, newWorldObject);
            final var response = characterService.update(request);

            // assert
            assertEquals(response.response().getBody(), "Character updated");

            GetCharacter(new Character(0, character1.getNickname(), character1NewLvl, newInv, newUser, newWorldObject));
        } finally {
            DeleteCharacter();

        }

    }

    @Test
    public void testUpdateCharacterThatDoesntExist() throws Exception {
        final var request = new UpdateCharacterRequest(character1.getNickname(), character1.getLevel(), character1.getInventoryId(), character1.getUserId(), character1.getWorldObjectId());
        final var response = characterService.update(request);

        // assert
        assertEquals(response.response().getBody(), "Invalid Character with the nickname\"" + character1.getNickname() + "\"");
    }

    @Test
    public void testDeleteCharacterThatDoesExist() throws Exception {
        try {
            RegisterCharacterWithoutDelete();
        } finally {
            DeleteCharacter();
        }
    }


    @Test
    public void testDeleteCharacterThatDoesntExist() throws Exception {
        final var request = new DeleteCharacterRequest(character1.getNickname());
        var response = characterService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}