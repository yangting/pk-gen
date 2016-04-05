/**
 * JvmMemLoader.java
 */
package one.yate.pk.base.loader;

import java.util.ArrayList;
import java.util.List;

import one.yate.pk.core.loader.ILoader;

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public class JvmMemLoader implements ILoader {

    protected final String nameSpace;
    protected final String keyName;
    protected final String strFormat = "";
    protected volatile long currentValue;

    protected int nextStep = 100;

    public JvmMemLoader(String ns, String key) {
        this.nameSpace = ns;
        this.keyName = key;
        currentValue = this.getCurrent();
    }

    public Long init() {
        return 0L;
    }

    public List<String> nextBatch(Long v) {
        List<String> x = new ArrayList<String>(nextStep);
        for (long i = currentValue + nextStep; i > currentValue; currentValue++) {
            if (strFormat == null || strFormat.trim().equals(""))
                x.add(currentValue + "");
            else {
                x.add(String.format(strFormat, currentValue));
            }
        }
        return x;
    }

    public Long getCurrent() {
        return this.currentValue;
    }

}
