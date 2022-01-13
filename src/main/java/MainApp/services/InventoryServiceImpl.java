package MainApp.services;

import MainApp.dto.Inventory.*;
import MainApp.model.Inventory;
import MainApp.repository.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public CreateInventoryResponse insert(CreateInventoryRequest request) {
        final var inventory = new Inventory();
        inventory.setId(request.id());
        inventory.setItems(request.items());

        final var inserted = inventoryRepository.insert(inventory);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "Inventory created!" : inserted;

        return new CreateInventoryResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetInventoryResponse exists(GetInventoryRequest request) {
        final var inventory = new Inventory();
        inventory.setId(request.id());

        final var result = inventoryRepository.exists(inventory);
        HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result == null ? "Invalid Inventory!" : result.toString();

        return new GetInventoryResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteInventoryResponse delete(DeleteInventoryRequest request) {
        final var inventory = new Inventory();
        inventory.setId(request.id());

        final var result = inventoryRepository.delete(inventory);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "Inventory deleted!" : "Invalid inventory!";

        return new DeleteInventoryResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateInventoryResponse update(UpdateInventoryRequest request) {
        final var oldInventory = new Inventory();
        oldInventory.setId(request.id());

        final var newInventory = new Inventory();
        newInventory.setId(request.id());
        newInventory.setItems(request.items());

        final var result = inventoryRepository.update(oldInventory, newInventory);
        return new UpdateInventoryResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}