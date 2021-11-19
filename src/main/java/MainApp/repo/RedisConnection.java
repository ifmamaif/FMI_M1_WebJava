package MainApp.repo;

import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

@Repository
public class RedisConnection implements DataBaseRepository {

    private Jedis redisConnection = new Jedis();

    @Override
    public void AddElementInList(String listName, String element) {
        redisConnection.sadd(listName, element);
    }

    @Override
    public Boolean ExistsElementInList(String listName, String element) {
        return redisConnection.sismember(listName, element);
    }

    @Override
    public void AddElementInHash(String key, String field, String value) {
        redisConnection.hset(key, field, value);
    }

    @Override
    public String GetValueInHash(String key, String field) {
        return redisConnection.hget(key, field);
    }
}