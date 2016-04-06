/**
 * RedisIdProvider.java
 */
package one.yate.pk.provider.redis;

import java.util.List;

import one.yate.pk.core.exception.IdGenException;
import one.yate.pk.core.rule.IdReader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class RedisIdReader implements IdReader {

    protected final String key;
    protected final JedisPool redisPool;
    protected int timeOut = 5;

    public RedisIdReader(JedisPool jedisPool, String key) {
        this.redisPool = jedisPool;
        this.key = key;
    }

    public String getId() throws IdGenException {
        Jedis client = redisPool.getResource();
        if (client == null) {
            throw new IdGenException("Get redis client fail");
        }

        try {
            List<String> x = client.brpop(timeOut, key);
            if (x == null || x.isEmpty())
                throw new IdGenException(
                        String.format("Read key list value is empty"));
            return x.get(1);
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }
    }

}
