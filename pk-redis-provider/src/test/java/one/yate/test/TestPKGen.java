package one.yate.test;

import one.yate.pk.base.loader.JvmMemLoader;
import one.yate.pk.core.rule.IdReader;
import one.yate.pk.provider.redis.RederIdListener;
import one.yate.pk.provider.redis.RedisReader;
import one.yate.pk.provider.redis.RedisWirter;

import org.junit.Test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * TestPKGen.java
 */

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class TestPKGen {

    @Test
    public void X1() {
        // JedisPoolConfig redisConf = new JedisPoolConfig();
        // redisConf.setMinIdle(10);
        // redisConf.setMaxIdle(10);
        // redisConf.setMaxTotal(1000);
        // redisConf.setMaxWaitMillis(30000);
        // redisConf.setTestOnBorrow(true);
        // redisConf.setTestOnReturn(true);
        // redisConf.setTestWhileIdle(true);
        //
        // JedisPool p = new JedisPool(redisConf, "192.168.56.100", 6379);
        //
        // RedisReader pv = new RedisReader(p, "yate.id.gen.test");
        //
        // DatePKRule drule = new DatePKRule(pv);
        //
        // DateStrategy ds = new DateStrategy("yyyyMMdd");
        //
        // for (int i = 0; i < 100; i++) {
        // try {
        // String v = drule.genPrivateKey(ds);
        //
        // System.out.println(v);
        //
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
    }

    @Test
    public void X2() {
        JedisPoolConfig redisConf = new JedisPoolConfig();
        redisConf.setMinIdle(10);
        redisConf.setMaxIdle(10);
        redisConf.setMaxTotal(1000);
        redisConf.setMaxWaitMillis(30000);
        redisConf.setTestOnBorrow(true);
        redisConf.setTestOnReturn(true);
        redisConf.setTestWhileIdle(true);

        JedisPool p = new JedisPool(redisConf, "192.168.7.85", 6379);

        JvmMemLoader loader = new JvmMemLoader("redis", "test.redis.support");

        new RederIdListener(30,
                new RedisWirter(p, "test.redis.support", loader));

        IdReader read = new RedisReader(p, "test.redis.support");

        for (int i = 0; i < 1000; i++) {
            try {
                System.out.println(read.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
