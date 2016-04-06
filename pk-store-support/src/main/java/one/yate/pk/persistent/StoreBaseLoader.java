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
public abstract class StoreBaseLoader implements ILoader {

    protected final DataSource ds;

    protected final String nameSpace;
    protected final String keyName;
    protected String strFormat = "";
    protected volatile long currentValue;

    protected int nextStep = 100;

    public StoreBaseLoader(String ns, String key, DataSource ds) {
        this.nameSpace = ns;
        this.keyName = key;
        this.ds = ds;
        currentValue = this.getCurrent();
    }

    public void setStrFormat(String strFormat) {
        this.strFormat = strFormat;
    }

    public String getStrFormat() {
        return strFormat;
    }

}
