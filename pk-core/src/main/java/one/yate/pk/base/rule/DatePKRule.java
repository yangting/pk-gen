/**
 * DatePKRule.java
 */
package one.yate.pk.base.rule;

import one.yate.pk.base.rule.strategy.DateStrategy;
import one.yate.pk.core.rule.IRule;
import one.yate.pk.core.rule.IdProvider;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class DatePKRule implements IRule<DateStrategy> {

    protected IdProvider p;

    public DatePKRule(IdProvider p) {
        this.p = p;
    }

    public String genPrivateKey(DateStrategy s) throws Exception {
        return s.build(p.getId());
    }

    public IdProvider getProvider() {
        return p;
    }

    public void setProvider(IdProvider p) {
        this.p = p;
    }

}
