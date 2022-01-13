package MainApp.controllers;

import MainApp.dto.Spell.CreateSpellRequest;
import MainApp.dto.Spell.DeleteSpellRequest;
import MainApp.dto.Spell.GetSpellRequest;
import MainApp.dto.Spell.UpdateSpellRequest;
import MainApp.model.Spell;
import MainApp.repository.SpellRepository;
import MainApp.services.SpellService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class SpellServiceImplTest {
    private final Spell spell1 = new Spell(0, "testspell1","testdesc","nothing","da","da");

    @Autowired
    private SpellController spellController;

    @Autowired
    private SpellService spellService;

    @Autowired
    private SpellRepository spellRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(spellRepository).isNotNull();
    }

    public void RegisterSpellWithoutDelete()
    {
        final var request = new CreateSpellRequest(spell1.getId(), spell1.getName(),spell1.getDescription(),spell1.getWhatDo(),spell1.getSourceId(),spell1.getTargetId());
        var response = spellService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteSpell()
    {
        final var request = new DeleteSpellRequest(spell1.getName());
        var response = spellService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetSpell(Spell spell)
    {
        final var request = new GetSpellRequest(spell.getName());
        var response = spellService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "Spell{name='"+ spell.getName() +"', description='" + spell.getDescription() + "', whatDo='"+ spell.getWhatDo()+"'}";
        assertEquals(expect,response.response().getBody());
    }

    @Test
    public void testCreateSpellThatDoesExist() throws Exception {
        try {
            RegisterSpellWithoutDelete();
        }
        finally {
        DeleteSpell();
        }
    }

    @Test
    public void testGetSpellThatDoesExist() throws Exception {
        try {
            RegisterSpellWithoutDelete();
            GetSpell(spell1);
        } finally {
            DeleteSpell();
        }

    }

    @Test
    public void testGetSpellThatDoesntExist() throws Exception {
        final var request = new GetSpellRequest(spell1.getName());
        final var response = spellService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateSpellThatDoesExist() throws Exception {
        RegisterSpellWithoutDelete();

        String spellNewDesc = "new";
        String spellNewWhatDo = "new";
        String spellNewSource = "new";
        String spellNewTarget = "new";

        final var request = new UpdateSpellRequest(spell1.getName(), spellNewDesc,spellNewWhatDo,spellNewSource,spellNewTarget);
        final var response = spellService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Spell updated");

        GetSpell(new Spell(spell1.getId(),spell1.getName(),spellNewDesc,spellNewWhatDo,spellNewSource,spellNewTarget));

        DeleteSpell();
    }

    @Test
    public void testUpdateSpellThatDoesntExist() throws Exception {
        final var request = new UpdateSpellRequest(spell1.getName(),spell1.getDescription(),spell1.getWhatDo(),spell1.getSourceId(),spell1.getTargetId());
        final var response = spellService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Invalid spell \"" + spell1.getName()+ "\"");
    }

    @Test
    public void testDeleteSpellThatDoesExist() throws Exception {
        RegisterSpellWithoutDelete();
        DeleteSpell();
    }


    @Test
    public void testDeleteSpellThatDoesntExist() throws Exception {
        final var request = new DeleteSpellRequest(spell1.getName());
        var response = spellService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
