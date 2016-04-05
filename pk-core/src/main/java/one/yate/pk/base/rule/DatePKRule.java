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
    protected final DateStrategy s;

    public DatePKRule(IdReader p, DateStrategy s) {
        this.p = p;
        this.s = s;
    }

    public String genPrivateKey() throws Exception {
        return s.build(p.getId());
    }

    public IdReader getProvider() {
        return p;
    }

    public void setProvider(IdReader p) {
        this.p = p;
    }

    public IRuleStrategy getCurrentStrategy() {
        return this.s;
    }

}
