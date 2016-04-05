package one.yate.test;

import one.yate.pk.core.rule.IdReader;
import one.yate.pk.persistent.MySqlLoader;
import one.yate.pk.persistent.StoreBaseLoader;
import one.yate.pk.provider.redis.RedisIdListener;
import one.yate.pk.provider.redis.RedisReader;
import one.yate.pk.provider.redis.RedisWirter;

import org.junit.Test;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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
        // JedisPoolConfig redisConf = new JedisPoolConfig();
        // redisConf.setMinIdle(10);
        // redisConf.setMaxIdle(10);
        // redisConf.setMaxTotal(1000);
        // redisConf.setMaxWaitMillis(30000);
        // redisConf.setTestOnBorrow(true);
        // redisConf.setTestOnReturn(true);
        // redisConf.setTestWhileIdle(true);
        //
        // JedisPool p = new JedisPool(redisConf, "192.168.7.85", 6379);
        //
        // JvmMemLoader loader = new JvmMemLoader("redis",
        // "test.redis.support");
        //
        // new RedisIdListener(30,
        // new RedisWirter(p, "test.redis.support", loader));
        //
        // IdReader read = new RedisReader(p, "test.redis.support");
        //
        // for (int i = 0; i < 1000; i++) {
        // try {
        // System.out.println(read.getId());
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // }
    }

    @Test
    public void X3() {
        JedisPoolConfig redisConf = new JedisPoolConfig();
        redisConf.setMinIdle(10);
        redisConf.setMaxIdle(10);
        redisConf.setMaxTotal(1000);
        redisConf.setMaxWaitMillis(30000);
        redisConf.setTestOnBorrow(true);
        redisConf.setTestOnReturn(true);
        redisConf.setTestWhileIdle(true);

        JedisPool p = new JedisPool(redisConf, "192.168.7.85", 6379);

        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl("jdbc:mysql://192.168.7.154:3306/pkgen?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&autoReconnectForPools=true&zeroDateTimeBehavior=convertToNull");
        ds.setDatabaseName("pkgen");
        ds.setUser("wechatadmin");
        ds.setPassword("portal!(!)cp");

        StoreBaseLoader loader = new MySqlLoader("test.mysql.yate",
                "test.redis.support", ds);

        new RedisIdListener(30,
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
