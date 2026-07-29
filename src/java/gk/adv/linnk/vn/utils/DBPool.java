/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import config.ResourceMagnager;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.jconfig.Configuration;
import snaq.db.ConnectionPool;

/**
 *
 * @author TUANPLA
 */
public class DBPool {

    static Logger logger = Logger.getLogger(DBPool.class);
    static ConnectionPool pool;
    //--
    public static int MAX_CONNECTIONS;
    public static int INI_CONNECTIONS;
    public static int TIME_OUT;
    private static String driverString = "com.mysql.jdbc.Driver";
    private static String user = "adv.link.vn";
    private static String pass = "adv.link.vn";
    private static String url_db = "jdbc:mysql://localhost:3306/advertise_link_vn?useUnicode=true&characterEncoding=UTF-8";

    static {
        try {
            Configuration config = ResourceMagnager.config;
            driverString = ResourceMagnager.getString("driver", "com.mysql.jdbc.Driver", "DBPool");
            url_db = ResourceMagnager.getString("url", "jdbc:mysql://localhost:3306/advertise_link_vn?useUnicode=true&characterEncoding=UTF-8", "DBPool");
            user = ResourceMagnager.getString("user", "root", "DBPool");
            pass = ResourceMagnager.getString("pass", "38643235", "DBPool");
            //--
            INI_CONNECTIONS = Integer.valueOf(config.getProperty("init_connection", "10", "DBPool"));
            MAX_CONNECTIONS = Integer.valueOf(config.getProperty("max_connection", "50", "DBPool"));
            TIME_OUT = Integer.valueOf(config.getProperty("time_out", "10", "DBPool"));
            //******
            Class c = Class.forName(driverString);
            Driver driver = (Driver) c.newInstance();
            DriverManager.registerDriver(driver);

            CreatePool();
            Connection conn = getConnection();
            if (conn.isClosed()) {
                logger.error("INIT CONNECTUON FALSE");
                System.exit(1);
            } else {
                conn.close();
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }

    }

    private static void CreatePool() {
        pool = new ConnectionPool("local",
                INI_CONNECTIONS, /*min pool*/
                MAX_CONNECTIONS, /*Max Pool*/
                MAX_CONNECTIONS, /*Max size*/
                TIME_OUT, /*Second*/
                url_db,
                user,
                pass);
        pool.setCaching(false, true, true);
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = pool.getConnection();
//            System.out.println("Connection Mode:"+conn.getAutoCommit());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void release() {
        pool.release();
    }

    public static void freeConn(ResultSet rs, PreparedStatement pstm, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }
    }

    public static void releadRsPstm(ResultSet rs, PreparedStatement pstm) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
        } catch (Exception e) {
        }
    }

    public static int size() {
        return pool.getSize();
    }
}
