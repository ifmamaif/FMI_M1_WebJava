package MainApp.services;

import MainApp.dto.Item.*;
import MainApp.model.Item;
import MainApp.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository userRepository) {
        this.itemRepository = userRepository;
    }

    @Override
    public CreateItemResponse insert(CreateItemRequest request) {
        final var item = new Item();
        item.setName(request.name());
        item.setDescription(request.description());
        item.setWhatDo(request.whatDo());

        final var inserted = itemRepository.insert(item);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "Item created!" : inserted;

        return new CreateItemResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetItemResponse exists(GetItemRequest request) {
        final var item = new Item();
        item.setName(request.name());

        final var result = itemRepository.exists(item);
        HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result == null ? "Invalid Item!" : result.toString();

        return new GetItemResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteItemResponse delete(DeleteItemRequest request) {
        final var item = new Item();
        item.setName(request.name());

        final var result = itemRepository.delete(item);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "Item deleted!" : "Invalid item!";

        return new DeleteItemResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateItemResponse update(UpdateItemRequest request) {
        final var oldItem = new Item();
        oldItem.setName(request.name());

        final var newItem = new Item();
        newItem.setName(request.name());
        newItem.setDescription(request.description());
        newItem.setWhatDo(request.whatDo());

        final var result = itemRepository.update(oldItem, newItem);
        return new UpdateItemResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}