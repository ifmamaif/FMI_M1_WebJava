package MainApp.repository;

import MainApp.model.Item;

public interface ItemRepository {
    String insert(Item item);

    Item exists(Item item);

    boolean delete(Item item);

    String update(Item oldItem, Item newItem);
}