/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import config.ListionContext;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class StatisticShow {

    static Logger logger = Logger.getLogger(StatisticShow.class);
    GroupAdv _grShow;
    String _refer;
    String _domain;
    String _usergent;
    String _ip;

    public StatisticShow() {
    }

    public StatisticShow(GroupAdv gshow, String refer, String domain, String userAgent, String ip) {
        this._grShow = gshow;
        this._domain = domain;
        this._refer = refer;
        this._usergent = userAgent;
        this._ip = ip;
        try {
            if (_grShow != null) {
                ArrayList<Advertise> all = _grShow.getAds();
                for (Advertise oneAds : all) {
                    StatisticShow oneStatic = new StatisticShow();
                    oneStatic.setAdvID(oneAds.getAdvID());
                    oneStatic.setGroupID(_grShow.getGroupID());
                    oneStatic.setAdsCreateID(oneAds.getCreateBy());
                    oneStatic.setDomainRequest(_domain);
                    oneStatic.setRequestURL(_refer);
                    oneStatic.setDestinationURL(oneAds.getDestinationUrl());
                    oneStatic.setIpShow(_ip);
                    oneStatic.setUserAgent(_usergent);
                    oneStatic.setTimeLong(System.currentTimeMillis());
                    oneStatic.setLocationID(0);
//                     Day vao Queue
                    ListionContext.queueShow.enqueue(oneStatic);
//                      addNewStatistic(oneStatic);
                }
            }
        } catch (Exception e) {
            Tool.Debug(Tool.getLogMessage(e));
            logger.error(Tool.getLogMessage(e));
        }
    }

    //************** MINI STATISTIC SHOW
    public void CreateOrUpdateShow(StatisticShow one) {
        Connection conn = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        String sql = "{ call ProcessShow_UpdateOrInsert(?,?,?,?) }";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareCall(sql);
            int i = 1;
            pstm.setInt(i++, one.getAdvID());
            pstm.setInt(i++, one.getGroupID());
            pstm.setString(i++, one.getDomainRequest());
            pstm.setInt(i++, one.getAdsCreateID());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }

    public void CreateOrUpdateShow(ArrayList<Object> all) {
        Connection conn = null;
        CallableStatement pstm = null;
        ResultSet rs = null;
        String sql = "{ call ProcessShow_UpdateOrInsert(?,?,?,?) }";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareCall(sql);
            for (Iterator it = all.iterator(); it.hasNext();) {
                StatisticShow one = (StatisticShow) it.next();
                int i = 1;
                pstm.setInt(i++, one.getAdvID());
                pstm.setInt(i++, one.getGroupID());
                pstm.setString(i++, one.getDomainRequest());
                pstm.setInt(i++, one.getAdsCreateID());
                pstm.executeUpdate();
                pstm.clearParameters();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }
    //********************************

    public boolean addNewStatistic_Detail(StatisticShow one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO statistic_display_detail(ADV_ID,GROUP_ID,ADV_CREATE_ID,REQUEST_DOMAIN,REQUEST_URL,DESTINATION_URL,IP_SHOW,USER_AGENT,TIME_SHOW,TIME_LONG,LOCATION)"
                + "                        VALUES(  ?   ,   ?    ,     ?       ,      ?       ,      ?    ,    ?          ,     ?  ,    ?     ,    NOW() ,    ?    ,   ?    )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, one.getAdvID());
            pstm.setInt(i++, one.getGroupID());
            pstm.setInt(i++, one.getAdsCreateID());
            pstm.setString(i++, one.getDomainRequest());
            pstm.setString(i++, one.getRequestURL());
            pstm.setString(i++, one.getDestinationURL());
            pstm.setString(i++, one.getIpShow());
            pstm.setString(i++, one.getUserAgent());
            pstm.setLong(i++, one.getTimeLong());
            pstm.setInt(i++, one.getLocationID());
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }return flag;
    }

    public boolean addOrUpdate(StatisticShow one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sqlCheck = "SELECT count(*) FROM statistic_display where ADV_ID = ? AND GROUP_ID = ? AND REQUEST_DOMAIN = ?  "
                + "AND DATE_FORMAT(TO_DAY,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')";
        String sqlupDate = "UPDATE statistic_display set CLICK = CLICK + 1 where ADV_ID = ? AND GROUP_ID = ? AND REQUEST_DOMAIN =?  "
                + "AND DATE_FORMAT(TO_DAY,'%d/%m/%Y') = DATE_FORMAT(NOW(),'%d/%m/%Y')";
        String sqlInsert = "INSERT INTO statistic_display(ADV_ID,GROUP_ID,ADV_CREATE_ID,REQUEST_DOMAIN,TO_DAY)"
                + "                          VALUES(  ?   ,   ?    ,     ?       ,      ?       , NOW())";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sqlCheck);
            int i = 1;
            pstm.setInt(i++, one.getAdvID());
            pstm.setInt(i++, one.getGroupID());
            pstm.setString(i++, one.getDomainRequest());
            rs = pstm.executeQuery();
            if (rs.next()) {
                int tem = rs.getInt(1);
                if (tem == 0) {
                    // Insert 
                    rs.close();
                    pstm.clearParameters();
                    pstm.close();
                    pstm = conn.prepareStatement(sqlInsert);
                    i = 1;
                    pstm.setInt(i++, one.getAdvID());
                    pstm.setInt(i++, one.getGroupID());
                    pstm.setString(i++, one.getDomainRequest());
                    pstm.setInt(i++, one.getAdsCreateID());
                    if (pstm.executeUpdate() == 1) {
                        flag = true;
                    } else {
                        logger.error("INSERT NEW STATISTIC SHOW FALSE :" + one.getAdvID() + "|" + one.getGroupID() + "|" + one.getDomainRequest());
                    }
                } else {
                    // Update
                    rs.close();
                    pstm.clearParameters();
                    pstm.close();
                    pstm = conn.prepareStatement(sqlupDate);
                    i = 1;
                    pstm.setInt(i++, one.getAdvID());
                    pstm.setInt(i++, one.getGroupID());
                    pstm.setString(i++, one.getDomainRequest());
                    if (pstm.executeUpdate() == 1) {
                        flag = true;
                    } else {
                        logger.error("UPDATE NEW STATISTIC SHOW FALSE :" + one.getAdvID() + "|" + one.getGroupID() + "|" + one.getDomainRequest());
                    }
                }
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }return flag;
    }
    //----
    private int idShow;
    private int advID;
    private int groupID;
    private int adsCreateID;
    private String domainRequest;
    private String requestURL;
    private String destinationURL;
    private String ipShow;
    private String userAgent;
    private Timestamp timeShow;
    private long timeLong;
    private int locationID;

    public int getIdShow() {
        return idShow;
    }

    public void setIdShow(int idShow) {
        this.idShow = idShow;
    }

    public int getAdvID() {
        return advID;
    }

    public void setAdvID(int advID) {
        this.advID = advID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getAdsCreateID() {
        return adsCreateID;
    }

    public void setAdsCreateID(int adsCreateID) {
        this.adsCreateID = adsCreateID;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getDestinationURL() {
        return destinationURL;
    }

    public void setDestinationURL(String destinationURL) {
        this.destinationURL = destinationURL;
    }

    public String getIpShow() {
        return ipShow;
    }

    public void setIpShow(String ipShow) {
        this.ipShow = ipShow;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Timestamp getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(Timestamp timeShow) {
        this.timeShow = timeShow;
    }

    public String getDomainRequest() {
        return domainRequest;
    }

    public void setDomainRequest(String domainRequest) {
        this.domainRequest = domainRequest;
    }

    public long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(long timeLong) {
        this.timeLong = timeLong;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
}
