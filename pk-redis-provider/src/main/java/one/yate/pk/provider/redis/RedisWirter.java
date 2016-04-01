/**
 * IRedisKeyLoader.java
 */
package one.yate.pk.provider.redis;

import java.util.List;

import one.yate.pk.core.exception.IdGenException;
import one.yate.pk.core.loader.ILoader;
import one.yate.pk.core.rule.IdWirter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class RedisWirter implements IdWirter {
    protected ILoader loader;
    protected final String key;
    protected final JedisPool redisPool;
    protected long current = -1;

    public RedisWirter(JedisPool jedisPool, String key) {
        this.redisPool = jedisPool;
        this.key = key;
    }

    public void write() throws IdGenException {
        if (this.current == -1)
            this.current = loader.getCurrent();
        List<String> x = loader.nextBatch(this.current);

        Jedis client = redisPool.getResource();
        if (client == null) {
            throw new IdGenException("Get redis client fail");
        }

        try {
            client.lpush(this.key, x.toArray(new String[0]));
        } finally {
            if (client != null && client.isConnected()) {
                client.close();
            }
        }
    }

    public void setLoader(ILoader loader) {
        this.loader = loader;
    }

}
