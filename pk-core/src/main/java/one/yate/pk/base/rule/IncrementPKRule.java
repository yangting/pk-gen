/**
 * IncrementPKRule.java
 */
package one.yate.pk.base.rule;

import one.yate.pk.base.rule.strategy.IncrementStrategy;
import one.yate.pk.core.rule.IRule;
import one.yate.pk.core.rule.IdProvider;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class IncrementPKRule implements IRule<IncrementStrategy> {

    protected IdProvider p;
    protected final Long init;

    public IncrementPKRule(IdProvider p, Long init) {
        this.p = p;
        this.init = init;
    }

    public IdProvider getProvider() {
        return p;
    }

    public void setProvider(IdProvider p) {
        this.p = p;
    }

    public String genPrivateKey(IncrementStrategy s) throws Exception{
        return s.build(p.getId());
    }

}
