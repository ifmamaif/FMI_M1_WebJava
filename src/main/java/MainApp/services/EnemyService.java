package MainApp.services;

import MainApp.dto.Enemy.*;

public interface EnemyService {
    CreateEnemyResponse insert(CreateEnemyRequest request);

    GetEnemyResponse exists(GetEnemyRequest request);

    DeleteEnemyResponse delete(DeleteEnemyRequest request);

    UpdateEnemyResponse update(UpdateEnemyRequest request);
}