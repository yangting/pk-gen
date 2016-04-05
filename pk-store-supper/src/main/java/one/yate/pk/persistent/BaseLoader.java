/**
 * MySqlLoader.java
 */
package one.yate.pk.persistent;

import javax.sql.DataSource;

import one.yate.pk.core.loader.ILoader;

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public abstract class BaseLoader implements ILoader {

    protected final DataSource ds;

    protected final String nameSpace;
    protected final String keyName;
    protected final String strFormat = "";
    protected volatile long currentValue;

    protected int nextStep = 5000;

    public BaseLoader(String ns, String key, DataSource ds) {
        this.nameSpace = ns;
        this.keyName = key;
        this.ds = ds;
        currentValue = this.getCurrent();
    }

}
