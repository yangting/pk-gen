/**
 * IncrementStrategy.java
 */
package one.yate.pk.base.rule.strategy;

import one.yate.pk.core.rule.strategy.IRuleStrategy;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class IncrementStrategy implements IRuleStrategy {

    public String build(Long id) {
        return id.toString();
    }

}
