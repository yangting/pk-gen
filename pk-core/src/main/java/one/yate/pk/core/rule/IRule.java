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
public interface IRule {

    IdReader getProvider();

    void setProvider(IdReader p);

    IRuleStrategy getRuleStrategy();

    String genPrivateKey() throws Exception;
}
