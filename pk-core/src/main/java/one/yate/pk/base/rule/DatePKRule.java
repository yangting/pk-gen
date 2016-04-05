/**
 * DatePKRule.java
 */
package one.yate.pk.base.rule;

import one.yate.pk.base.rule.strategy.DateStrategy;
import one.yate.pk.core.rule.IRule;
import one.yate.pk.core.rule.IdReader;
import one.yate.pk.core.rule.strategy.IRuleStrategy;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class DatePKRule implements IRule {

    protected IdReader p;
    protected DateStrategy s;

    public DatePKRule(IdReader p, DateStrategy s) {
        this.p = p;
        this.s = s;
    }

    public void setProvider(IdReader p) {
        this.p = p;
    }

    /*
     * (non-Javadoc)
     * 
     * @see one.yate.pk.core.rule.IRule#getRuleStrategy()
     */
    @Override
    public IRuleStrategy getRuleStrategy() {
        return this.s;
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

    /*
     * (non-Javadoc)
     * 
     * @see one.yate.pk.core.rule.IRule#getProvider()
     */
    @Override
    public IdReader getProvider() {
        return this.p;
    }

}
