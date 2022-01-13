package MainApp.repository;

import MainApp.model.WorldObject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorldObjectRepositoryImpl implements WorldObjectRepository {
    private final JdbcTemplate jdbcTemplate;

    public WorldObjectRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(WorldObject worldObject) {
        try {
            jdbcTemplate.update("insert into worldobject values (?, ?, ?, ?, ?)",
                    worldObject.getId(), worldObject.getName(), worldObject.getPosition(), worldObject.getDescription(), worldObject.getWhatDo());
        } catch (DuplicateKeyException e) {
            return "A worldObject with this name already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public WorldObject exists(WorldObject worldObject) {
        try {
            final var foundId = (WorldObject) jdbcTemplate.queryForObject("select * from worldobject where name = ?", new Object[]{worldObject.getName()}, new BeanPropertyRowMapper<>(WorldObject.class));
            return foundId;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(WorldObject worldObject) {
        if (exists(worldObject) == null) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from worldobject where name = ?", worldObject.getName());
        return true;
    }

    @Override
    public String update(WorldObject oldWorldObject, WorldObject newWorldObject) {
        if (!oldWorldObject.getName().equals(newWorldObject.getName())) {
            return "Something went wrongs";
        }

        if (exists(oldWorldObject) == null) {
            return "Invalid worldObject \"" + oldWorldObject.getName() + "\"";
        }

        try {
            final var sqlCommand = "update worldobject set position = ?, description = ?, whatDo = ? where name = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newWorldObject.getPosition(), newWorldObject.getDescription(), newWorldObject.getWhatDo(), newWorldObject.getName()});
            return foundId > 0 ? "WorldObject updated" : "Couldn't update the worldObject";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}