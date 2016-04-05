/**
 * RederIdListener.java
 */
package one.yate.pk.provider.redis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import one.yate.pk.core.rule.IdListener;
import one.yate.pk.core.rule.IdWirter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public class RedisIdListener implements IdListener<Void> {

    protected final IdWirter w;
    protected final JedisPool redisPool;
    protected final String key;
    protected final int triggerValue;
    protected ExecutorService es = Executors.newSingleThreadExecutor();

    public RedisIdListener(int triggerValue, IdRedisWirter w) {
        this.w = w;
        this.redisPool = w.getJedisPool();
        this.key = w.getKey();
        this.triggerValue = triggerValue;
        es.submit(this);
    }

    public Void call() throws Exception {
        Jedis client = null;

        while (true) {
            client = this.redisPool.getResource();
            if (client == null || !client.isConnected())
                throw new Exception("RederIdListener get redis conn is null");

            try {
                Long len = client.llen(key);
                if (len == null || this.triggerValue >= len.intValue()) {
                    this.w.write();
                }

            } finally {
                if (client != null && client.isConnected()) {
                    client.close();
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            }

        }
    }
}
