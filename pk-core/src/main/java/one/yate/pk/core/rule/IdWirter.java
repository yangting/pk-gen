/**
 * IdWirter.java
 */
package one.yate.pk.core.rule;

import one.yate.pk.core.exception.IdGenException;
import one.yate.pk.core.loader.ILoader;

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public interface IdWirter {

    public void setLoader(ILoader loader);

    public void write() throws IdGenException;
}
