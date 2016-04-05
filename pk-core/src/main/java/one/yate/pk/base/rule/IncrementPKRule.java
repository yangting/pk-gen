/**
 * IncrementPKRule.java
 */
package one.yate.pk.base.rule;

import one.yate.pk.base.rule.strategy.IncrementStrategy;
import one.yate.pk.core.rule.IRule;
import one.yate.pk.core.rule.IdReader;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class IncrementPKRule implements IRule<IncrementStrategy> {

    protected IdReader p;

    public IncrementPKRule(IdReader p) {
        this.p = p;
    }

    public IdReader getProvider() {
        return p;
    }

    public void setProvider(IdReader p) {
        this.p = p;
    }

    public String genPrivateKey(IncrementStrategy s) throws Exception{
        return s.build(p.getId());
    }

}
