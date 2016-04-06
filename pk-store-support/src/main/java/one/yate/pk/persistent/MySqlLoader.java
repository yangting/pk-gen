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

/**
 * @author Yate
 * @date Apr 5, 2016
 * @description TODO
 * @version 1.0
 */
public class MySqlLoader extends StoreBaseLoader {

    protected final static String UPDATE_SQL = "update pk_gen set current_val = ?,last_opt_time = ? where namespace = ? and key_name = ? and current_val = ?";
    protected final static String QUERY_SQL = "select current_val from pk_gen where namespace = ? and key_name = ?";

    public MySqlLoader(String ns, String key, DataSource ds) {
        super(ns, key, ds);
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

    public List<String> nextBatch() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(UPDATE_SQL);
            ps.setLong(1, this.currentValue + this.nextStep);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setString(3, this.nameSpace);
            ps.setString(4, this.keyName);
            ps.setLong(5, this.currentValue);
            int x = ps.executeUpdate();
            if (x == 1) {
                long oc = this.currentValue;
                this.currentValue += nextStep;

                List<String> result = new ArrayList<String>(nextStep);
                for (; oc < currentValue; oc++) {
                    if (strFormat == null || strFormat.trim().equals(""))
                        result.add(oc + "");
                    else
                        result.add(String.format(strFormat, oc));
                }
                return result;
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            ps = conn.prepareStatement(QUERY_SQL);
            ps.setString(1, this.nameSpace);
            ps.setString(2, this.keyName);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                Long x = rs.getLong(1);
                return x == null ? 0L : x;
            } else {
                return null;
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

        return this.currentValue;
    }

}
