package MainApp.controllers;

import MainApp.dto.WorldObject.CreateWorldObjectRequest;
import MainApp.dto.WorldObject.DeleteWorldObjectRequest;
import MainApp.dto.WorldObject.GetWorldObjectRequest;
import MainApp.dto.WorldObject.UpdateWorldObjectRequest;
import MainApp.model.WorldObject;
import MainApp.repository.WorldObjectRepository;
import MainApp.services.WorldObjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class WorldObjectServiceImplTest {
    private final WorldObject worldObject1 = new WorldObject(0, "testworldObject1", "testposition","testdescription","nothing");

    @Autowired
    private WorldObjectController worldObjectController;

    @Autowired
    private WorldObjectService worldObjectService;

    @Autowired
    private WorldObjectRepository worldObjectRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(worldObjectRepository).isNotNull();
    }

    public void RegisterWorldObjectWithoutDelete()
    {
        final var request = new CreateWorldObjectRequest(worldObject1.getName(), worldObject1.getPosition(), worldObject1.getDescription(), worldObject1.getWhatDo());
        var response = worldObjectService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteWorldObject()
    {
        final var request = new DeleteWorldObjectRequest(worldObject1.getName());
        var response = worldObjectService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetWorldObject(WorldObject worldObject) {
        final var request = new GetWorldObjectRequest(worldObject.getName());
        var response = worldObjectService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "WorldObject{name='" + worldObject.getName() +
                "', position='" + worldObject.getPosition() +
                "', description='" + worldObject.getDescription() +
                "', whatDo='" + worldObject.getWhatDo() + "'}";
        assertEquals(expect, response.response().getBody());
    }

    @Test
    public void testCreateWorldObjectThatDoesntExist() throws Exception {
        RegisterWorldObjectWithoutDelete();
        DeleteWorldObject();
    }

    @Test
    public void testCreateWorldObjectThatDoesExist() throws Exception {
        RegisterWorldObjectWithoutDelete();

        final var request = new CreateWorldObjectRequest(worldObject1.getName(), worldObject1.getPosition(),worldObject1.getDescription(),worldObject1.getWhatDo());
        final var response = worldObjectService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);
        assertSame(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);

        DeleteWorldObject();
    }

    @Test
    public void testGetWorldObjectThatDoesExist() throws Exception {
        RegisterWorldObjectWithoutDelete();

        GetWorldObject(worldObject1);

        DeleteWorldObject();
    }

    @Test
    public void testGetWorldObjectThatDoesntExist() throws Exception {
        final var request = new GetWorldObjectRequest(worldObject1.getName());
        final var response = worldObjectService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateWorldObjectThatDoesExist() throws Exception {
        RegisterWorldObjectWithoutDelete();

        String newPos ="newpos";
        String newDesc = "newdesc";
        String newWhatDo = "new what";

        final var request = new UpdateWorldObjectRequest(worldObject1.getName(), newPos,newDesc,newWhatDo);
        final var response = worldObjectService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "WorldObject updated");

        GetWorldObject(new WorldObject(0, worldObject1.getName(),newPos,newDesc,newWhatDo));

        DeleteWorldObject();
    }

    @Test
    public void testUpdateWorldObjectThatDoesntExist() throws Exception {
        final var request = new UpdateWorldObjectRequest(worldObject1.getName(), worldObject1.getPosition(),worldObject1.getDescription(),worldObject1.getWhatDo());
        final var response = worldObjectService.update(request);

        // assert
        assertEquals("Invalid worldObject \"" + worldObject1.getName()+ "\"",response.response().getBody());
    }

    @Test
    public void testDeleteWorldObjectThatDoesExist() throws Exception {
        RegisterWorldObjectWithoutDelete();
        DeleteWorldObject();
    }


    @Test
    public void testDeleteWorldObjectThatDoesntExist() throws Exception {
        final var request = new DeleteWorldObjectRequest(worldObject1.getName());
        var response = worldObjectService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
