package MainApp.repository;

import MainApp.model.Inventory;

public interface InventoryRepository {
    String insert(Inventory inventory);

    Inventory exists(Inventory inventory);

    boolean delete(Inventory inventory);

    String update(Inventory oldInventory, Inventory newInventory);
}