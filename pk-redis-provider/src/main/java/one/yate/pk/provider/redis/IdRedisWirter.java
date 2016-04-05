/**
 * IdRedisWirter.java
 */
package one.yate.pk.provider.redis;

import one.yate.pk.core.rule.IdWirter;
import redis.clients.jedis.JedisPool;

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public interface IdRedisWirter extends IdWirter {

    public String getKey();

    public JedisPool getJedisPool();
}
