package MainApp.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, String> inventoryMap = null;

    private ObjectMapper mapper = new ObjectMapper();
    private TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
    };

    public void Deserialize(String serializedInventory) throws JsonProcessingException {
        if (serializedInventory == null || serializedInventory.equals("")) {
            inventoryMap = new HashMap<>();
        } else {
            inventoryMap = mapper.readValue(serializedInventory, typeRef);
        }
    }

    public String Serialize() throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(inventoryMap);
    }

    public void Add(String item, String countToAddString) {
        String countString = inventoryMap.get(item);
        int count = Integer.parseInt(countToAddString);
        if (count < 1) {
            return;
        }

        if (countString != null) {
            count += Integer.parseInt(countString);
        }
        inventoryMap.put(item, String.valueOf(count));
    }

    public void Remove(String item, String countToRemoveString) {
        String currentCountString = inventoryMap.get(item);
        if (currentCountString == null) {
            return;
        }

        int countToRemove = Integer.parseInt(countToRemoveString);
        if (countToRemove < 1) {
            return;
        }

        int newCount = Integer.parseInt(currentCountString) - countToRemove;
        if (newCount < 1) {
            inventoryMap.remove(item);
            return;
        }

        inventoryMap.put(item, String.valueOf(newCount));
    }
}