/**
 * IncrementPKRule.java
 */
package one.yate.pk.base.rule;

import one.yate.pk.base.rule.strategy.IncrementStrategy;
import one.yate.pk.core.rule.IRule;
import one.yate.pk.core.rule.IdReader;
import one.yate.pk.core.rule.strategy.IRuleStrategy;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class IncrementPKRule implements IRule {

    protected IdReader p;
    protected IncrementStrategy s;

    public IncrementPKRule(IdReader p, IncrementStrategy s) {
        this.p = p;
        this.s = s;
    }

    public IRuleStrategy getRuleStrategy() {
        return this.s;
    }

    /*
     * (non-Javadoc)
     * 
     * @see one.yate.pk.core.rule.IRule#getProvider()
     */
    @Override
    public IdReader getProvider() {
        return this.p;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * one.yate.pk.core.rule.IRule#setProvider(one.yate.pk.core.rule.IdReader)
     */
    @Override
    public void setProvider(IdReader p) {
        this.p = p;
    }

    /*
     * (non-Javadoc)
     * 
     * @see one.yate.pk.core.rule.IRule#genPrivateKey()
     */
    @Override
    public String genPrivateKey() throws Exception {
        return s.build(p.getId());
    }

}
