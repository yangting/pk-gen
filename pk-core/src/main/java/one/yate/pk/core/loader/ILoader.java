/**
 * ILoader.java
 */
package one.yate.pk.core.loader;

import java.util.List;

/**
 * @author Yate
 * @date Apr 1, 2016
 * @description TODO
 * @version 1.0
 */
public interface ILoader {

    Long init();

    List<String> nextBatch();

    Long getCurrent();

}
