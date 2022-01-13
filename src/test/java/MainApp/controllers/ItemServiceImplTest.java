package MainApp.controllers;

import MainApp.dto.Item.CreateItemRequest;
import MainApp.dto.Item.DeleteItemRequest;
import MainApp.dto.Item.GetItemRequest;
import MainApp.dto.Item.UpdateItemRequest;
import MainApp.model.Item;
import MainApp.repository.ItemRepository;
import MainApp.services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
public class ItemServiceImplTest {
    private final Item item1 = new Item(0, "testitem1", "testitem1","nothing");

    @Autowired
    private ItemController itemController;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testLoad() throws Exception {
        assertThat(itemRepository).isNotNull();
    }

    public void RegisterItemWithoutDelete()
    {
        final var request = new CreateItemRequest(item1.getName(), item1.getDescription(), item1.getWhatDo());
        var response = itemService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void DeleteItem()
    {
        final var request = new DeleteItemRequest(item1.getName());
        var response = itemService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.OK);
        assertSame(response.response().getStatusCode(), HttpStatus.OK);
    }

    public void GetItem(Item item)
    {
        final var request = new GetItemRequest(item.getName());
        var response = itemService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.FOUND);

        String expect = "Item{name='"+ item.getName() +"', description='" + item.getDescription() + "', whatDo='" + item.getWhatDo()+ "'}";
        assertEquals(expect,response.response().getBody());
    }

    @Test
    public void testCreateItemThatDoesExist() throws Exception {
        RegisterItemWithoutDelete();
        DeleteItem();
    }

    @Test
    public void testCreateItemThatDoesntExist() throws Exception {
        RegisterItemWithoutDelete();

        final var request = new CreateItemRequest(item1.getName(), item1.getDescription(), item1.getWhatDo());
        final var response = itemService.insert(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);
        assertSame(response.response().getStatusCode(), HttpStatus.EXPECTATION_FAILED);

        DeleteItem();
    }

    @Test
    public void testGetItemThatDoesExist() throws Exception {
        RegisterItemWithoutDelete();

        GetItem(item1);

        DeleteItem();
    }

    @Test
    public void testGetItemThatDoesntExist() throws Exception {
        final var request = new GetItemRequest(item1.getName());
        final var response = itemService.exists(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testUpdateItemThatDoesExist() throws Exception {
        RegisterItemWithoutDelete();

        String item1NewDesc = "new desc";
        String item1NewWhatDo = "new what do";

        final var request = new UpdateItemRequest(item1.getName(), item1NewDesc, item1NewWhatDo);
        final var response = itemService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Item updated");

        GetItem(new Item(0, item1.getName(),item1NewDesc,item1NewWhatDo));

        DeleteItem();
    }

    @Test
    public void testUpdateItemThatDoesntExist() throws Exception {
        final var request = new UpdateItemRequest(item1.getName(), item1.getDescription(), item1.getWhatDo());
        final var response = itemService.update(request);

        // assert
        assertEquals(response.response().getBody(),  "Invalid item with the name\"" + item1.getName()+ "\"");
    }

    @Test
    public void testDeleteItemThatDoesExist() throws Exception {
       RegisterItemWithoutDelete();
       DeleteItem();
    }


    @Test
    public void testDeleteItemThatDoesntExist() throws Exception {
        final var request = new DeleteItemRequest(item1.getName());
        var response = itemService.delete(request);

        // assert
        assertEquals(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
        assertSame(response.response().getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
