/**
 * RedisIdProvider.java
 */
package one.yate.pk.provider.redis;

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
public class RedisReader implements IdReader {
    
    protected final String key;
    protected final JedisPool redisPool;

    public RedisReader(JedisPool jedisPool, String key) {
        this.redisPool = jedisPool;
        this.key = key;
    }

    public String getId() throws IdGenException {
        Jedis client = redisPool.getResource();
        if (client == null) {
            throw new IdGenException("Get redis client fail");
        }

        try {
            if (client.exists(this.key)) {
                String x = client.rpop(key);
                if (x == null)
                    throw new IdGenException(
                            String.format("Read key list value is empty"));
                return x;
            } else {
                throw new IdGenException(String.format("Read key={} not found",
                        this.key));
            }
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }
    }

}
