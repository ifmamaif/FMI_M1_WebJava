package MainApp.controllers;

import MainApp.dto.Enemy.CreateEnemyRequest;
import MainApp.dto.Enemy.DeleteEnemyRequest;
import MainApp.dto.Enemy.GetEnemyRequest;
import MainApp.dto.Enemy.UpdateEnemyRequest;
import MainApp.model.Enemy;
import MainApp.repository.EnemyRepository;
import MainApp.services.EnemyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class EnemyServiceImplTest {
    private final Enemy enemy1 = new Enemy(0, "testenemy1", 1);

    @Autowired
    private EnemyController enemyController;

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private EnemyRepository enemyRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(enemyRepository).isNotNull();
    }

    public void RegisterEnemyWithoutDelete()
    {
        final var request = new CreateEnemyRequest(enemy1.getName(), enemy1.getLevel());
        var response = enemyService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteEnemy()
    {
        final var request = new DeleteEnemyRequest(enemy1.getName());
        var response = enemyService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetEnemy(Enemy enemy)
    {
        final var request = new GetEnemyRequest(enemy.getName());
        var response = enemyService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "Enemy{name='"+ enemy.getName() +"', level=" + enemy.getLevel() + "}";
        assertEquals(expect,response.response().getBody());
    }

    @Test
    public void testCreateEnemyThatDoesntExist() throws Exception {
        RegisterEnemyWithoutDelete();
        DeleteEnemy();
    }

    @Test
    public void testCreateEnemyThatDoesExist() throws Exception {
        RegisterEnemyWithoutDelete();

        final var request = new CreateEnemyRequest(enemy1.getName(), enemy1.getLevel());
        final var response = enemyService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);
        assertSame(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);

        DeleteEnemy();
    }

    @Test
    public void testGetEnemyThatDoesExist() throws Exception {
        RegisterEnemyWithoutDelete();

        GetEnemy(enemy1);

        DeleteEnemy();
    }

    @Test
    public void testGetEnemyThatDoesntExist() throws Exception {
        final var request = new GetEnemyRequest(enemy1.getName());
        final var response = enemyService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateEnemyThatDoesExist() throws Exception {
        RegisterEnemyWithoutDelete();

        int enemy1NewLvl = 3;

        final var request = new UpdateEnemyRequest(enemy1.getName(), enemy1NewLvl);
        final var response = enemyService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Enemy updated");

        GetEnemy(new Enemy(0, enemy1.getName(),enemy1NewLvl));

        DeleteEnemy();
    }

    @Test
    public void testUpdateEnemyThatDoesntExist() throws Exception {
        final var request = new UpdateEnemyRequest(enemy1.getName(), enemy1.getLevel());
        final var response = enemyService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Invalid enemy with the name\"" + enemy1.getName()+ "\"");
    }

    @Test
    public void testDeleteEnemyThatDoesExist() throws Exception {
        RegisterEnemyWithoutDelete();
        DeleteEnemy();
    }


    @Test
    public void testDeleteEnemyThatDoesntExist() throws Exception {
        final var request = new DeleteEnemyRequest(enemy1.getName());
        var response = enemyService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
