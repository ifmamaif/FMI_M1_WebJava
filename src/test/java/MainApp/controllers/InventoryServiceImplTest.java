package MainApp.controllers;

import MainApp.dto.Inventory.CreateInventoryRequest;
import MainApp.dto.Inventory.DeleteInventoryRequest;
import MainApp.dto.Inventory.GetInventoryRequest;
import MainApp.dto.Inventory.UpdateInventoryRequest;
import MainApp.model.Inventory;
import MainApp.repository.InventoryRepository;
import MainApp.services.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class InventoryServiceImplTest {
    private final Inventory inventory1 = new Inventory(10005, "testinventory1");

    @Autowired
    private InventoryController inventoryController;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(inventoryRepository).isNotNull();
    }

    public void RegisterInventoryWithoutDelete()
    {
        final var request = new CreateInventoryRequest(inventory1.getId(),inventory1.getItems());
        var response = inventoryService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteInventory()
    {
        final var request = new DeleteInventoryRequest(inventory1.getId());
        var response = inventoryService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetInventory(Inventory inventory)
    {
        final var request = new GetInventoryRequest(inventory.getId());
        var response = inventoryService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "Inventory{id="+ inventory.getId() +", items='" + inventory.getItems() + "'}";
        assertEquals(expect,response.response().getBody());
    }

    @Test
    public void testCreateInventoryThatDoesntExist() throws Exception {
        RegisterInventoryWithoutDelete();
        DeleteInventory();
    }

    @Test
    public void testGetInventoryThatDoesExist() throws Exception {
        RegisterInventoryWithoutDelete();

        GetInventory(inventory1);

        DeleteInventory();
    }

    @Test
    public void testGetInventoryThatDoesntExist() throws Exception {
        final var request = new GetInventoryRequest(-1);
        final var response = inventoryService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateInventoryThatDoesExist() throws Exception {
        RegisterInventoryWithoutDelete();

        String inventory1NewLvl = "newItems";

        final var request = new UpdateInventoryRequest(inventory1.getId(), inventory1NewLvl);
        final var response = inventoryService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Inventory updated");

        GetInventory(new Inventory(inventory1.getId(),inventory1NewLvl));

        DeleteInventory();
    }

    @Test
    public void testUpdateInventoryThatDoesntExist() throws Exception {
        final var request = new UpdateInventoryRequest(inventory1.getId(), inventory1.getItems());
        final var response = inventoryService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Invalid inventory with the id\"" + inventory1.getId()+ "\"");
    }

    @Test
    public void testDeleteInventoryThatDoesExist() throws Exception {
        RegisterInventoryWithoutDelete();
        DeleteInventory();
    }


    @Test
    public void testDeleteInventoryThatDoesntExist() throws Exception {
        final var request = new DeleteInventoryRequest(inventory1.getId());
        var response = inventoryService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
