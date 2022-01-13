package MainApp.services;

import MainApp.dto.WorldObject.*;
import MainApp.model.WorldObject;
import MainApp.repository.WorldObjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WorldObjectServiceImpl implements WorldObjectService {
    private final WorldObjectRepository worldObjectRepository;

    public WorldObjectServiceImpl(WorldObjectRepository worldObjectRepository) {
        this.worldObjectRepository = worldObjectRepository;
    }

    @Override
    public CreateWorldObjectResponse insert(CreateWorldObjectRequest request) {
        final var worldObject = new WorldObject();
        worldObject.setName(request.name());
        worldObject.setPosition(request.position());
        worldObject.setDescription(request.description());
        worldObject.setWhatDo(request.whatDo());

        final var inserted = worldObjectRepository.insert(worldObject);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "WorldObject created!" : inserted;

        return new CreateWorldObjectResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetWorldObjectResponse exists(GetWorldObjectRequest request) {
        final var worldObject = new WorldObject();
        worldObject.setName(request.name());

        final var result = worldObjectRepository.exists(worldObject);
        HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result == null ? "Invalid WorldObject!" : result.toString();

        return new GetWorldObjectResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteWorldObjectResponse delete(DeleteWorldObjectRequest request) {
        final var worldObject = new WorldObject();
        worldObject.setName(request.name());

        final var result = worldObjectRepository.delete(worldObject);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "WorldObject deleted!" : "Invalid worldObject!";

        return new DeleteWorldObjectResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateWorldObjectResponse update(UpdateWorldObjectRequest request) {
        final var oldWorldObject = new WorldObject();
        oldWorldObject.setName(request.name());

        final var newWorldObject = new WorldObject();
        newWorldObject.setName(request.name());
        newWorldObject.setDescription(request.description());
        newWorldObject.setWhatDo(request.whatDo());
        newWorldObject.setPosition(request.position());

        final var result = worldObjectRepository.update(oldWorldObject, newWorldObject);
        return new UpdateWorldObjectResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}