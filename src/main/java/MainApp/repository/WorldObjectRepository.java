package MainApp.repository;

import MainApp.model.WorldObject;

public interface WorldObjectRepository {
    String insert(WorldObject worldObject);

    WorldObject exists(WorldObject worldObject);

    boolean delete(WorldObject worldObject);

    String update(WorldObject oldWorldObject, WorldObject newWorldObject);
}