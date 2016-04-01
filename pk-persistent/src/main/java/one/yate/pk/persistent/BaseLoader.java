/**
 * MySqlLoader.java
 */
package one.yate.pk.persistent;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public Long init() {
        // String s =
        // "update pk_gen set current_val = ?,last_opt_time = ? where namespace = ? and key_name = ? and current_val = ?";
        //
        // Connection conn = null;
        // PreparedStatement ps = null;
        // ResultSet rs = null;
        // try {
        // conn = ds.getConnection();
        // ps = conn.prepareStatement(s);
        // ps.setLong(0, this.currentValue + this.nextStep);
        // ps.setDate(1, new Date(System.currentTimeMillis()));
        // ps.setString(2, this.nameSpace);
        // ps.setString(3, this.keyName);
        // ps.setLong(4, this.currentValue);
        // int x = ps.executeUpdate();
        // if (x == 1) {
        // this.currentValue += nextStep;
        // }
        // } catch (SQLException e) {
        // e.printStackTrace();
        // } finally {
        // if (rs != null) {
        // try {
        // rs.close();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
        // if (ps != null) {
        // try {
        // ps.close();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
        // if (conn != null) {
        // try {
        // conn.close();
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        // }
        // }

        return currentValue;
    }

    public List<String> nextBatch(Long v) {

        String s = "update pk_gen set current_val = ?,last_opt_time = ? where namespace = ? and key_name = ? and current_val = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(s);
            ps.setLong(0, this.currentValue + this.nextStep);
            ps.setDate(1, new Date(System.currentTimeMillis()));
            ps.setString(2, this.nameSpace);
            ps.setString(3, this.keyName);
            ps.setLong(4, this.currentValue);
            int x = ps.executeUpdate();
            if (x == 1) {
                long oc = this.currentValue;
                this.currentValue += nextStep;

                List<String> result = new ArrayList<String>(nextStep);
                for (; oc <= currentValue; oc++) {
                    result.add(String.format(strFormat, oc));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return Collections.emptyList();
    }

    public Long getCurrent() {
        String s = "select current_val from pk_gen where namespace = ? and key_name = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(s);
            ps.setString(0, this.nameSpace);
            ps.setString(1, this.keyName);
            rs = ps.executeQuery();
            Long x = rs.getLong(0);
            return x == null ? 0L : x;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return this.currentValue;
    }

}
