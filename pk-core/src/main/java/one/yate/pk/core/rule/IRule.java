/**
 * IRule.java
 */
package one.yate.pk.core.rule;

import one.yate.pk.core.rule.strategy.IRuleStrategy;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public interface IRule<S extends IRuleStrategy> {

    IdReader getProvider();

    void setProvider(IdReader p);

    String genPrivateKey(S s) throws Exception;
}
