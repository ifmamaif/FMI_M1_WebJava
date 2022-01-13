package MainApp.services;

import MainApp.dto.Inventory.*;

public interface InventoryService {
    CreateInventoryResponse insert(CreateInventoryRequest request);

    GetInventoryResponse exists(GetInventoryRequest request);

    DeleteInventoryResponse delete(DeleteInventoryRequest request);

    UpdateInventoryResponse update(UpdateInventoryRequest request);
}