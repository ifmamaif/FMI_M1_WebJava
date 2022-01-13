package MainApp.repository;

import MainApp.model.Character;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {
    private final JdbcTemplate jdbcTemplate;

    public CharacterRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insert(Character character) {
        try {
            jdbcTemplate.update("insert into characters values (?, ?, ?, ?, ?, ?)",
                    character.getId(), character.getNickname(), character.getLevel(), character.getInventoryId(), character.getUserId(), character.getWorldObjectId());
        } catch (DuplicateKeyException e) {
            return "A Character with this nickname or userId or inventoryId already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public Character exists(Character character) {
        try {
            var foundId = jdbcTemplate.query("select * from characters where nickname = ?", new CharacterMapper(), character.getNickname());
            //final var foundId = (Character) jdbcTemplate.queryForObject("select * from characters where nickname = ?", new Object[]{character.getNickname()}, new BeanPropertyRowMapper<>(Character.class));
            return foundId.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    private static final class CharacterMapper implements RowMapper<Character> {
        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
            Character character = new Character();
            character.setId(rs.getInt("id"));
            character.setNickname(rs.getString("nickname"));
            character.setLevel(rs.getInt("level"));
            character.setInventoryId(rs.getInt("idInventory"));
            character.setUserId(rs.getInt("idUser"));
            character.setWorldObjectId(rs.getInt("idWorldObject"));
            return character;
        }
    }

    @Override
    public boolean delete(Character character) {
        if (exists(character) == null) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from characters where nickname = ?", character.getNickname());
        return true;
    }

    @Override
    public String update(Character oldcharacter, Character newcharacter) {
        if (!oldcharacter.getNickname().equals(newcharacter.getNickname())) {
            return "Something went wrongs";
        }

        if (exists(oldcharacter) == null) {
            return "Invalid Character with the nickname\"" + oldcharacter.getNickname() + "\"";
        }

        try {
            final var sqlCommand = "update characters set level = ?, idInventory = ?, idUser = ?, idWorldObject = ? where nickname = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newcharacter.getLevel(), newcharacter.getInventoryId(), newcharacter.getUserId(), newcharacter.getWorldObjectId(), newcharacter.getNickname()});
            return foundId > 0 ? "Character updated" : "Couldn't update the character";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}