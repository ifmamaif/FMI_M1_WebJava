package MainApp.repository;

import MainApp.model.Enemy;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EnemyRepositoryImpl implements EnemyRepository {
    private final JdbcTemplate jdbcTemplate;

    public EnemyRepositoryImpl(JdbcTemplate jbdcTemplate) {
        this.jdbcTemplate = jbdcTemplate;
    }

    @Override
    public String insert(Enemy enemy) {
        try {
            jdbcTemplate.update("insert into enemy values (?, ?, ?)",
                    enemy.getId(), enemy.getName(), enemy.getLevel());
        } catch (DuplicateKeyException e) {
            return "An enemy with this name already exist";
        } catch (Exception e) {
            return e.getMessage();
        }
        return "";
    }

    @Override
    public Enemy exists(Enemy enemy) {
        try {
            final var foundId = (Enemy) jdbcTemplate.queryForObject("select * from enemy where name = ?",
                    new Object[]{enemy.getName()},
                    new BeanPropertyRowMapper<Enemy>(Enemy.class));
            return foundId;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean delete(Enemy enemy) {
        if (exists(enemy) == null) {
            return false;
        }

        final var foundId = jdbcTemplate.update("delete from enemy where name = ?", enemy.getName());
        return true;
    }

    @Override
    public String update(Enemy oldEnemy, Enemy newEnemy) {
        if (!oldEnemy.getName().equals(newEnemy.getName())) {
            return "Something went wrongs";
        }

        if (exists(oldEnemy) == null) {
            return "Invalid enemy with the name\"" + oldEnemy.getName() + "\"";
        }

        try {
            final var sqlCommand = "update enemy set level = ? where name = ?";
            final var foundId = jdbcTemplate.update(sqlCommand, new Object[]{newEnemy.getLevel(), newEnemy.getName()});
            return foundId > 0 ? "Enemy updated" : "Couldn't update the enemy";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}