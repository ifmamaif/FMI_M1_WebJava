package MainApp.repository;

import MainApp.model.Enemy;

public interface EnemyRepository {
    String insert(Enemy enemy);

    Enemy exists(Enemy enemy);

    boolean delete(Enemy enemy);

    String update(Enemy oldEnemy, Enemy newEnemy);
}