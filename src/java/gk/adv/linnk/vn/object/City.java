/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import gk.adv.linnk.vn.cache.CacheUtil;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class City {

    static final Logger logger = Logger.getLogger(City.class);
    private static final String KEY_ALL = "City.cacheAll";

    static {
        try {
            HashMap<String, City> CACHE = new HashMap<>();
            City ctDao = new City();
            List<City> all = ctDao.getAllCity();
            for (City one : all) {
                CACHE.put(one.getMyCode(), one);
            }
            CacheUtil.cache12h.put(new Element(KEY_ALL, CACHE));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }

    }

    private static HashMap<String, City> reCache() {
        HashMap<String, City> CACHE = new HashMap<>();
        City ctDao = new City();
        List<City> all = ctDao.getAllCity();
        for (City one : all) {
            CACHE.put(one.getMyCode(), one);
        }
        CacheUtil.cache12h.put(new Element(KEY_ALL, CACHE));
        return CACHE;
    }

    private static HashMap<String, City> getCache() {
        HashMap<String, City> result;
        try {
            // Lay tu memcache
            result = (HashMap<String, City>) CacheUtil.cache12h.get(KEY_ALL).getObjectKey();
            if (result == null) {
                // Cache lai
                result = reCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(Tool.getLogMessage(e));
            result = null;
        }
        return result;
    }

    public static ArrayList<City> getAll() {
        ArrayList<City> all = new ArrayList();
        //--
        HashMap<String, City> CACHE = getCache();
        if (CACHE != null) {
            Collection<City> coll = CACHE.values();
            all.addAll(coll);
        }
        return all;
    }

    public static ArrayList<City> getMB() {
        ArrayList<City> mb = new ArrayList();
        //--
        HashMap<String, City> CACHE = getCache();
        if (CACHE != null) {
            Collection<City> coll = CACHE.values();
            for (City one : coll) {
                if (one.getRegion().equalsIgnoreCase("MB")) {
                    mb.add(one);
                }
            }
        }
        return mb;
    }

    public static ArrayList<City> getMT() {
        ArrayList<City> mt = new ArrayList();
        //--
        HashMap<String, City> CACHE = getCache();
        if (CACHE != null) {
            Collection<City> coll = CACHE.values();
            for (City one : coll) {
                if (one.getRegion().equalsIgnoreCase("MT")) {
                    mt.add(one);
                }
            }
        }
        return mt;
    }

    public static ArrayList<City> getMN() {
        ArrayList<City> mn = new ArrayList();
        //--
        HashMap<String, City> CACHE = getCache();
        if (CACHE != null) {
            Collection<City> coll = CACHE.values();
            for (City one : coll) {
                if (one.getRegion().equalsIgnoreCase("MN")) {
                    mn.add(one);
                }
            }
        }
        return mn;
    }

    public static String buildRegion(ArrayList<City> all) {
        String str = "";
        for (City one : all) {
            if (!Tool.checkNull(one.getGgCode())) {
                str += one.getGgCode() + ",";
            }
        }
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public List<City> getAllCity() {
        ArrayList<City> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CITIES WHERE 1=1";

        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            rs = pstm.executeQuery();
            while (rs.next()) {
                City one = new City();
                one.setId(rs.getInt("ID"));
                one.setMyCode(rs.getString("MY_CODE"));
                one.setMyname(rs.getString("MYNAME"));
                one.setRegion(rs.getString("REGION"));
                one.setGgCode(rs.getString("GG_CODE"));
                one.setGgName(rs.getString("GG_NAME"));
                result.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    public ArrayList<City> getAll(int page, String key) {
        ArrayList<City> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CITIES WHERE 1=1";
        if (!Tool.checkNull(key)) {
            sql += " AND ( MY_CODE like ? OR MYNAME like ? OR GG_CODE like ? OR GG_NAME like ?)";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                City one = new City();
                one.setId(rs.getInt("ID"));
                one.setMyCode(rs.getString("MY_CODE"));
                one.setMyname(rs.getString("MYNAME"));
                one.setRegion(rs.getString("REGION"));
                one.setGgCode(rs.getString("GG_CODE"));
                one.setGgName(rs.getString("GG_NAME"));
                result.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    public int countAll(int page, String key) {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM CITIES WHERE 1=1";
        if (!Tool.checkNull(key)) {
            sql += " AND ( MY_CODE like ? OR MYNAME like ? OR GG_CODE like ? OR GG_NAME like ?)";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    public City getById(int id) {
        City one = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM CITIES WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                one = new City();
                one.setId(rs.getInt("ID"));
                one.setMyCode(rs.getString("MY_CODE"));
                one.setMyname(rs.getString("MYNAME"));
                one.setRegion(rs.getString("REGION"));
                one.setGgCode(rs.getString("GG_CODE"));
                one.setGgName(rs.getString("GG_NAME"));
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return one;
    }

    public boolean addNew(City one) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO CITIES(MY_CODE,MYNAME,REGION,GG_CODE,GG_NAME) VALUES(?,?,?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getMyCode());
            pstm.setString(i++, one.getMyname());
            pstm.setString(i++, one.getRegion());
            pstm.setString(i++, one.getGgCode());
            pstm.setString(i++, one.getGgName());
            if (pstm.executeUpdate() == 1) {
                result = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    public boolean update(City one) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE CITIES SET MY_CODE = ?,MYNAME = ?,REGION = ?,GG_CODE = ?,GG_NAME = ? WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getMyCode());
            pstm.setString(i++, one.getMyname());
            pstm.setString(i++, one.getRegion());
            pstm.setString(i++, one.getGgCode());
            pstm.setString(i++, one.getGgName());
            pstm.setInt(i++, one.getId());
            if (pstm.executeUpdate() == 1) {
                result = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    public boolean del(int id) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM CITIES WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            if (pstm.executeUpdate() == 1) {
                result = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return result;
    }

    int id;
    String myCode;
    String myname;
    String region;     // Vung mien MB MT MN
    String ggCode;
    String ggName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMyCode() {
        return myCode;
    }

    public void setMyCode(String myCode) {
        this.myCode = myCode;
    }

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    public String getGgCode() {
        return ggCode;
    }

    public void setGgCode(String ggCode) {
        this.ggCode = ggCode;
    }

    public String getGgName() {
        return ggName;
    }

    public void setGgName(String ggName) {
        this.ggName = ggName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
