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
    protected final IncrementStrategy s;

    public IncrementPKRule(IdReader p, IncrementStrategy s) {
        this.p = p;
        this.s = s;
    }

    public IdReader getProvider() {
        return p;
    }

    public void setProvider(IdReader p) {
        this.p = p;
    }

    public String genPrivateKey() throws Exception {
        return s.build(p.getId());
    }

    public IRuleStrategy getCurrentStrategy() {
        return this.s;
    }

}
