/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class AnalyticsAll {

    static Logger logger = Logger.getLogger(AnalyticsAll.class);

    public ArrayList<AnalyticsAll> getAnalyticShowDomain(String startDate, String endDate) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT REQUEST_DOMAIN,SUM(`SHOW`) TOTAL_SHOW from statistic_display A"
                + "WHERE A.TO_DAY >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TO_DAY <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')"
                + "GROUP BY REQUEST_DOMAIN  ORDER BY TOTAL_SHOW DESC";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("TOTAL_SHOW"));
                all.add(one);
            }
        } catch (Exception e) {
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public ArrayList<AnalyticsAll> getAnalyticClickByCity(int currentPage, String startDate, String endDate, String domain, String cityName, int adsID, int rowperpage) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.CITY,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN like ?";
        }
        if (!Tool.checkNull(cityName)) {
            sql += " AND A.CITY like ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.CITY,A.REQUEST_DOMAIN ORDER BY CLICK DESC LIMIT ?,?";
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, "%" + domain + "%");
            }
            if (!Tool.checkNull(cityName)) {
                pstm.setString(i++, "%" + cityName + "%");
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setCity(rs.getString("CITY"));
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCountAnalyticClickByCity(String startDate, String endDate, String domain, String City, int adsID) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.CITY,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN like ?";
        }
        if (!Tool.checkNull(City)) {
            sql += " AND A.CITY like ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.CITY,A.REQUEST_DOMAIN ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, "%" + domain + "%");
            }
            if (!Tool.checkNull(City)) {
                pstm.setString(i++, "%" + City + "%");
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public ArrayList<AnalyticsAll> getAnalyticClick(int currentPage, String startDate, String endDate, String domain, int cityCode, int adsID, int rowperpage) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.ADV_ID,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID,A.REQUEST_DOMAIN ORDER BY CLICK DESC LIMIT ?,?";
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCountAnalyticClick(String startDate, String endDate, String domain, int cityCode, int adsID) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.ADV_ID,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID,A.REQUEST_DOMAIN ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    //---- Click For Product
    public ArrayList<AnalyticsAll> getClickForProduct(int currentPage, String startDate, String endDate, int cityCode, int adsID, int rowperpage) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT B.*,C.* FROM  (SELECT A.ADV_ID,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID) B,ADVERTISE C WHERE  B.ADV_ID = C.ADV_ID  ORDER BY B.CLICK DESC  LIMIT ?,?";
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setClick(rs.getInt("CLICK"));
                //--
                Advertise oneAds = new Advertise();
                oneAds.setAdvID(rs.getInt("ADV_ID"));
                oneAds.setKind(rs.getInt("KIND"));
                oneAds.setTitle_top(rs.getString("TITLE_TOP"));
                oneAds.setTitle_II(rs.getString("TITLE_II"));
                oneAds.setDesc(rs.getString("ADV_DESC"));
                oneAds.setDestinationUrl(rs.getString("DESTINATION_URL"));
                oneAds.setWidth(rs.getInt("WIDTH"));
                oneAds.setHeight(rs.getInt("HEIGHT"));
                oneAds.setFilePath(rs.getString("FILE_PATH"));
                oneAds.setRate(rs.getString("RATE"));
                oneAds.setTitle_price(rs.getString("TITLE_PRICE"));
                oneAds.setPriceSell(rs.getInt("PRICE_SELL"));
                oneAds.setPrice_root(rs.getInt("PRICE_ROOT"));
                oneAds.setTitle_button(rs.getString("PRICE_ROOT"));
                oneAds.setStartTime(rs.getTimestamp("START_TIME"));
                oneAds.setEndTime(rs.getTimestamp("END_TIME"));
                oneAds.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                oneAds.setCreateBy(rs.getInt("CREATE_BY"));
                oneAds.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                oneAds.setUpdateBy(rs.getInt("UPDATE_BY"));
                oneAds.setStatus(rs.getInt("STATUS"));
                oneAds.setAdsBy(rs.getInt("ADS_BY"));
                one.setAds(oneAds);
                all.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCountClickForProduct(String startDate, String endDate, int cityCode, int adsID) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.ADV_ID,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    //---------
    public ArrayList<AnalyticsAll> getClickDomain(int currentPage, String startDate, String endDate, String domain, int rowperpage) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        sql += " GROUP BY A.REQUEST_DOMAIN ORDER BY CLICK DESC LIMIT ?,?";
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getPageClickDomain(String startDate, String endDate, String domain) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        sql += " GROUP BY A.REQUEST_DOMAIN ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public int getCountAnalyticClickDetail(String startDate, String endDate, String domain, int cityCOde, int adsID) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.region_code,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (cityCOde != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.region_code,A.REQUEST_DOMAIN ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (cityCOde != 0) {
                pstm.setInt(i++, cityCOde);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public ArrayList<Advertise> getDetailByDomain(int page, int rowPerPage, String startDate, String endDate, String domain) {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.ADV_ID,A.TITLE_TOP,A.FILE_PATH,TMP.CLICK FROM advertise A INNER JOIN "
                + "(SELECT S.ADV_ID,COUNT(*) CLICK FROM statistic_click S "
                + " WHERE S.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND S.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') "
                + " AND S.REQUEST_DOMAIN = ? "
                + " GROUP BY S.ADV_ID) TMP ON A.ADV_ID = TMP.ADV_ID ORDER BY CLICK DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            pstm.setString(i++, domain);
            pstm.setInt(i++, (page - 1) * rowPerPage);
            pstm.setInt(i++, rowPerPage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCountDetailByDomain(String startDate, String endDate, String domain) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM "
                + "(SELECT S.ADV_ID,COUNT(*) CLICK FROM statistic_click S "
                + " WHERE S.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND S.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') "
                + " AND S.REQUEST_DOMAIN = ? GROUP BY S.ADV_ID)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            pstm.setString(i++, domain);
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public ArrayList<AnalyticsAll> getAnalyticClickDetail(int currentPage, String startDate, String endDate, String domain, int cityCode, int adsID, int rowperpage) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.ADV_ID,A.region_code,A.region_name,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM statistic_click A  "
                + " WHERE A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.region_code,A.REQUEST_DOMAIN ORDER BY CLICK DESC LIMIT ?,?";
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setRegion_name(rs.getString("region_name"));
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public ArrayList<AnalyticsAll> getAnalyticShow(int currentPage, int limit, String startDate, String endDate, String domain, int locationID, int adsID) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.ADV_ID,A.REQUEST_DOMAIN,A.SHOW,A.TO_DAY FROM statistic_display A  "
                + " WHERE A.TO_DAY >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TO_DAY <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " ORDER BY A.SHOW DESC LIMIT ?,?";
        int start = (currentPage - 1) * limit;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (locationID != 0) {
                pstm.setInt(i++, locationID);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, limit);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("SHOW"));
                one.setDateShow(rs.getTimestamp("TO_DAY"));
                all.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCountAnalyticShow(String startDate, String endDate, String domain, int locationID, int adsID) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM statistic_display A  "
                + " WHERE A.TO_DAY >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') AND A.TO_DAY <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (!Tool.checkNull(domain)) {
            sql += " AND A.REQUEST_DOMAIN = ?";
        }
        if (locationID != 0) {
            sql += " AND A.LOCATION = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (!Tool.checkNull(domain)) {
                pstm.setString(i++, domain);
            }
            if (locationID != 0) {
                pstm.setInt(i++, locationID);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    //--------- CUSTOMER
    public ArrayList<AnalyticsAll> getCustomerClick(int currentPage, String startDate, String endDate, String[] domain, int cityCode, int adsID, int rowperpage, int createBy) {
        ArrayList<AnalyticsAll> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.ADV_ID,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM STATISTIC_CLICK A,ADVERTISE B "
                + " WHERE A.ADV_ID = B.ADV_ID AND A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') "
                + " AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s') ";
        if (createBy != 0) {
            sql += " AND CREATE_BY = ? ";
        }
        if (domain != null && domain.length > 0) {
            sql += " AND (";
            for (int k = 0; k < domain.length; k++) {
                sql += " A.REQUEST_DOMAIN = ? ";
                if (k < domain.length - 1) {
                    sql += " OR ";
                }
            }
            sql += ")";
        }

        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID,A.REQUEST_DOMAIN ORDER BY CLICK DESC LIMIT ?,?";
//        System.out.println(sql);
        int start = (currentPage - 1) * rowperpage;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (createBy != 0) {
                pstm.setInt(i++, createBy);
            }
            if (domain != null && domain.length > 0) {
                for (String oneDomain : domain) {
                    pstm.setString(i++, oneDomain);
                }
            }
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, rowperpage);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AnalyticsAll one = new AnalyticsAll();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setDomain(rs.getString("REQUEST_DOMAIN"));
                one.setClick(rs.getInt("CLICK"));
                all.add(one);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int getCustomerCountClick(String startDate, String endDate, String[] domain, int cityCode, int adsID, int createBy) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT COUNT(*) FROM (SELECT A.ADV_ID,A.REQUEST_DOMAIN,COUNT(*) CLICK FROM STATISTIC_CLICK A,ADVERTISE B   "
                + " WHERE A.ADV_ID = B.ADV_ID AND A.TIME_CLICK >= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')"
                + " AND A.TIME_CLICK <= STR_TO_DATE(?,'%d/%m/%Y %H:%i:%s')";
        if (createBy != 0) {
            sql += " AND B.CREATE_BY = ? ";
        }
        if (domain != null && domain.length > 0) {
            sql += " AND (";
            for (int k = 0; k < domain.length; k++) {
                sql += " A.REQUEST_DOMAIN = ? ";
                if (k < domain.length - 1) {
                    sql += " OR ";
                }
            }
            sql += ")";
        }
        if (cityCode != 0) {
            sql += " AND A.region_code = ?";
        }
        if (adsID != 0) {
            sql += " AND A.ADV_ID = ?";
        }
        sql += " GROUP BY A.ADV_ID,A.REQUEST_DOMAIN ORDER BY CLICK DESC) B";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;

            pstm.setString(i++, startDate + " 00:00:00");
            pstm.setString(i++, endDate + " 23:59:59");
            if (createBy != 0) {
                pstm.setInt(i++, createBy);
            }
            if (domain != null && domain.length > 0) {
                for (String oneDomain : domain) {
                    pstm.setString(i++, oneDomain);
                }
            }
            if (cityCode != 0) {
                pstm.setInt(i++, cityCode);
            }
            if (adsID != 0) {
                pstm.setInt(i++, adsID);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }
    //---------
    int advID;
    String domain;
    String country_code;
    String country_name;
    String city;
    String region_code;
    String region_name;
    int click;
    Timestamp dateShow;
    //-------------
    Advertise ads;

    public Advertise getAds() {
        return ads;
    }

    public void setAds(Advertise ads) {
        this.ads = ads;
    }

    //-------------
    public Timestamp getDateShow() {
        return dateShow;
    }

    public void setDateShow(Timestamp dateShow) {
        this.dateShow = dateShow;
    }

    public int getAdvID() {
        return advID;
    }

    public void setAdvID(int advID) {
        this.advID = advID;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
