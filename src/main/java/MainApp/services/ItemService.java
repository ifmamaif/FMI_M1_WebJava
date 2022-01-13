package MainApp.services;

import MainApp.dto.Item.*;

public interface ItemService {
    CreateItemResponse insert(CreateItemRequest request);

    GetItemResponse exists(GetItemRequest request);

    DeleteItemResponse delete(DeleteItemRequest request);

    UpdateItemResponse update(UpdateItemRequest request);
}