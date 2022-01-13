package MainApp.repository;

import MainApp.model.Item;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(Item item) {
        try {
            jdbcTemplate.update("insert into item values (?, ?, ?, ?)",
                    item.getId(), item.getName(), item.getDescription(), item.getWhatDo());
        } catch (DuplicateKeyException e) {
            return "An item with this name already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public Item exists(Item item) {
        try {
            final var foundId = (Item) jdbcTemplate.queryForObject("select * from item where name = ?", new Object[]{item.getName()}, new BeanPropertyRowMapper<Item>(Item.class));
            return foundId;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Item item) {
        if (exists(item) == null) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from item where name = ?", item.getName());
        return true;
    }

    @Override
    public String update(Item oldItem, Item newItem) {
        if (!oldItem.getName().equals(newItem.getName())) {
            return "Something went wrongs";
        }

        if (exists(oldItem) == null) {
            return "Invalid item with the name\"" + oldItem.getName() + "\"";
        }

        try {
            final var sqlCommand = "update item set description = ?, whatDo = ? where name = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newItem.getDescription(), newItem.getWhatDo(), newItem.getName()});
            return foundId > 0 ? "Item updated" : "Couldn't update the item";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}