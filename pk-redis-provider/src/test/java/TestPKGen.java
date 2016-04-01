import one.yate.pk.base.rule.DatePKRule;
import one.yate.pk.base.rule.strategy.DateStrategy;
import one.yate.pk.provider.redis.RedisReader;

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
        // <bean id="jedisPoolConfig"
        // class="redis.clients.jedis.JedisPoolConfig">
        // <property name="maxTotal" value="${redis.max_total}" />
        // <property name="minIdle" value="${redis.min_idle}" />
        // <property name="maxIdle" value="${redis.max_idle}" />
        // <property name="maxWaitMillis" value="${redis.max_wait}" />
        // <property name="testOnBorrow" value="${redis.testOnBorrow}" />
        // <property name="testOnReturn" value="${redis.testOnReturn}" />
        // <property name="testWhileIdle" value="${redis.testWhileIdle}" />
        // </bean>
        //
        // <bean id="jedisPool" class="redis.clients.jedis.JedisPool">
        // <constructor-arg ref="jedisPoolConfig" />
        // <constructor-arg type="java.lang.String" value="${redis.ip}" />
        // <constructor-arg type="int" value="${redis.port}" />
        // <constructor-arg type="int" value="${redis.timeout}" />
        // </bean>

        // redis.min_idle=10
        // redis.max_idle=10
        // redis.max_total=1000
        // redis.max_wait=30000
        // redis.timeout=2000
        // redis.testOnBorrow=true
        // redis.testOnReturn=true
        // redis.testWhileIdle=true
        // redis.db.index=5

        JedisPoolConfig redisConf = new JedisPoolConfig();
        redisConf.setMinIdle(10);
        redisConf.setMaxIdle(10);
        redisConf.setMaxTotal(1000);
        redisConf.setMaxWaitMillis(30000);
        redisConf.setTestOnBorrow(true);
        redisConf.setTestOnReturn(true);
        redisConf.setTestWhileIdle(true);

        JedisPool p = new JedisPool(redisConf, "192.168.7.85", 6379);

        RedisReader pv = new RedisReader(p, "yate.id.gen.test");

        DatePKRule drule = new DatePKRule(pv);
        
        DateStrategy ds = new DateStrategy("yyyyMMdd");
        
        for(int i=0;i<100;i++){
            try {
                String v = drule.genPrivateKey(ds);
                
                System.out.println(v);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
}
