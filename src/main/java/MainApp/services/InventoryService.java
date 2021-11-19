package MainApp.services;

import MainApp.models.Inventory;
import MainApp.repo.DataBaseRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryService {
    private final DataBaseRepository dbRepository;
    //private final Inventory inventory = new Inventory();

    public InventoryService(DataBaseRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    private String getInventoryFromPersistenceDB(String userToken) {
        // we don't have a persistence DB, so we will return empty.
        return "";
    }

    private String GetInventoryRaw(String userToken) {
        String inventoryString = dbRepository.GetValueInHash(DataBaseRepository.INVENTORY_HASH_NAME, userToken);
        if (inventoryString == null) {
            return getInventoryFromPersistenceDB(userToken);
        }

        return inventoryString;
    }


    @GetMapping("/inventory")
    public String getInventory(@RequestHeader(name = "token") String userToken) {
        return GetInventoryRaw(userToken);
    }

    @PostMapping("/addToInventory")
    public boolean addItemToInventory(@RequestHeader(name = "token") String userToken, @RequestHeader(name = "itemID") String item, @RequestHeader(name = "count", defaultValue = "1") String count) throws JsonProcessingException {
        String inventoryRaw = GetInventoryRaw(userToken);
        Inventory inventory = new Inventory();
        inventory.Deserialize(inventoryRaw);

        inventory.Add(item, count);

        inventoryRaw = inventory.Serialize();
        dbRepository.AddElementInHash(DataBaseRepository.INVENTORY_HASH_NAME, userToken, inventoryRaw);

        return true;
    }

    @PostMapping("/removeFromInventory")
    public boolean removeItemToInventory(@RequestHeader(name = "token") String userToken, @RequestHeader(name = "itemID") String item, @RequestHeader(name = "count", defaultValue = "1") String count) throws JsonProcessingException {
        String inventoryRaw = GetInventoryRaw(userToken);
        Inventory inventory = new Inventory();
        inventory.Deserialize(inventoryRaw);

        inventory.Remove(item, count);

        inventoryRaw = inventory.Serialize();
        dbRepository.AddElementInHash(DataBaseRepository.INVENTORY_HASH_NAME, userToken, inventoryRaw);
        return true;
    }
}