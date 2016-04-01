/**
 * DateStrategy.java
 */
package one.yate.pk.base.rule.strategy;

import java.text.SimpleDateFormat;
import java.util.Date;

import one.yate.pk.core.rule.strategy.IRuleStrategy;

/**
 * @author Yate
 * @date Mar 29, 2016
 * @description TODO
 * @version 1.0
 */
public class DateStrategy implements IRuleStrategy {

    protected final SimpleDateFormat sdf;

    public DateStrategy(String format) {
        sdf = new SimpleDateFormat(format);
    }

    public String build(String id) {
        return sdf.format(new Date()) + id;
    }

}
