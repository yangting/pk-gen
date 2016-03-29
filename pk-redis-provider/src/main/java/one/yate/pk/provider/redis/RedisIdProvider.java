/**
 * RedisIdProvider.java
 */
package one.yate.pk.provider.redis;

import one.yate.pk.core.rule.IdProvider;
import one.yate.pk.provider.redis.exception.IdGenException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class RedisIdProvider implements IdProvider {

    protected final String key;
    protected final JedisPool redisPool;

    public RedisIdProvider(JedisPool jedisPool, String key) {
        this.redisPool = jedisPool;
        this.key = key;
    }

    public Long getId() throws IdGenException {
        Jedis client = redisPool.getResource();
        if (client == null) {
            throw new IdGenException("Get redis client fail");
        }

        try {
            if (client.exists(this.key)) {
                // String v = client.incr(key);
                Long v = client.incr(key);
                if (v == null) {
                    throw new IdGenException(String.format(
                            "Read key={} value={} fail", this.key, v));
                }
                return v;
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
