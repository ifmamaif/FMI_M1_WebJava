package MainApp.repository;

import MainApp.model.Inventory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public InventoryRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(Inventory inventory) {
        try {
            jdbcTemplate.update("insert into inventory values (?, ?)",
                    inventory.getId(), inventory.getItems());
        } catch (DuplicateKeyException e) {
            return "An inventory with this id already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public Inventory exists(Inventory inventory) {
        try {
            final var foundId = (Inventory) jdbcTemplate.queryForObject("select * from inventory where id = ?",
                    new Object[]{inventory.getId()},
                    new BeanPropertyRowMapper<Inventory>(Inventory.class));
            return foundId;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Inventory inventory) {
        if (exists(inventory) == null) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from inventory where id = ?", inventory.getId());
        return true;
    }

    @Override
    public String update(Inventory oldInventory, Inventory newInventory) {
        if (oldInventory.getId() != newInventory.getId()) {
            return "Something went wrongs";
        }

        if (exists(oldInventory) == null) {
            return "Invalid inventory with the id\"" + oldInventory.getId() + "\"";
        }

        try {
            final var sqlCommand = "update inventory set items = ? where id = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newInventory.getItems(), newInventory.getId()});
            return foundId > 0 ? "Inventory updated" : "Couldn't update the inventory";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}