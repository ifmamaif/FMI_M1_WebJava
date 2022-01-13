package MainApp.repository;

import MainApp.model.Spell;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpellRepositoryImpl implements SpellRepository {
    private final JdbcTemplate jdbcTemplate;

    public SpellRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(Spell spell) {
        try {
            jdbcTemplate.update("insert into spell values (?, ?, ?, ?, ?, ?)",
                    spell.getId(), spell.getName(), spell.getDescription(), spell.getWhatDo(), spell.getSourceId(), spell.getTargetId());
        } catch (DuplicateKeyException e) {
            return "A spell with this name already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public String exists(Spell spell) {
        try {
            final var foundId = (Spell) jdbcTemplate.queryForObject("select * from spell where name = ?", new Object[]{spell.getName()}, new BeanPropertyRowMapper<>(Spell.class));
            return foundId.toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean delete(Spell spell) {
        if (exists(spell).isEmpty()) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from spell where name = ?", spell.getName());
        return true;
    }

    @Override
    public String update(Spell oldSpell, Spell newSpell) {
        if (!oldSpell.getName().equals(newSpell.getName())) {
            return "Something went wrongs";
        }

        if (exists(oldSpell).isEmpty()) {
            return "Invalid spell \"" + oldSpell.getName() + "\"";
        }

        try {
            final var sqlCommand = "update spell set description = ?, whatDo = ?, sourceId = ?, targetId = ?  where name = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newSpell.getDescription(), newSpell.getWhatDo(), newSpell.getSourceId(), newSpell.getTargetId(), newSpell.getName()});
            return foundId > 0 ? "Spell updated" : "Couldn't update the spell";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}