package MainApp.services;

import MainApp.dto.Enemy.*;
import MainApp.model.Enemy;
import MainApp.repository.EnemyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnemyServiceImpl implements EnemyService {
    private final EnemyRepository enemyRepository;

    public EnemyServiceImpl(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }


    @Override
    public CreateEnemyResponse insert(CreateEnemyRequest request) {
        final var enemy = new Enemy();
        enemy.setName(request.name());
        enemy.setLevel(request.level());

        final var inserted = enemyRepository.insert(enemy);
        final var status = inserted.isEmpty() ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        final var reason = inserted.isEmpty() ? "Enemy created!" : inserted;

        return new CreateEnemyResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public GetEnemyResponse exists(GetEnemyRequest request) {
        final var enemy = new Enemy();
        enemy.setName(request.name());

        final var result = enemyRepository.exists(enemy);
        HttpStatus status = result == null ? HttpStatus.NOT_FOUND : HttpStatus.FOUND;
        String reason = result == null ? "Invalid Enemy!" : result.toString();

        return new GetEnemyResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public DeleteEnemyResponse delete(DeleteEnemyRequest request) {
        final var enemy = new Enemy();
        enemy.setName(request.name());

        final var result = enemyRepository.delete(enemy);
        HttpStatus status = result ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        String reason = result ? "Enemy deleted!" : "Invalid enemy!";

        return new DeleteEnemyResponse(ResponseEntity.status(status).body(reason));
    }

    @Override
    public UpdateEnemyResponse update(UpdateEnemyRequest request) {
        final var oldEnemy = new Enemy();
        oldEnemy.setName(request.name());

        final var newEnemy = new Enemy();
        newEnemy.setName(request.name());
        newEnemy.setLevel(request.level());

        final var result = enemyRepository.update(oldEnemy, newEnemy);
        return new UpdateEnemyResponse(ResponseEntity.status(HttpStatus.OK).body(result));
    }
}