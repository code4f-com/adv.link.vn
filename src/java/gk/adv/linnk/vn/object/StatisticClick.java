/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import java.sql.Timestamp;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class StatisticClick {

    static Logger logger = Logger.getLogger(StatisticClick.class);

    public StatisticClick() {
    }

    public boolean addNewStatistic(StatisticClick one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO statistic_click(ADV_ID,GROUP_ID,ADV_CREATE_ID,REQUEST_DOMAIN,REQUEST_URL,DESTINATION_URL,IP_CLICK,USER_AGENT,TIME_CLICK,TIME_LONG,country_code,country_name,city,region_code,region_name)"
                + "                        VALUES(  ?   ,   ?    ,     ?       ,      ?       ,      ?    ,    ?          ,     ?  ,    ?     ,    NOW() ,    ?    ,   ?    ,    ?       ,?   ,?          ,?          )";
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
            pstm.setString(i++, one.getIpCLick());
            pstm.setString(i++, one.getUserAgent());
            pstm.setLong(i++, one.getTimeLong());
            pstm.setString(i++, one.getCountry_code());
            pstm.setString(i++, one.getCountry_name());
            pstm.setString(i++, one.getCity());
            pstm.setString(i++, one.getRegion_code());
            pstm.setString(i++, one.getRegion_name());
            if (pstm.executeUpdate() == 1) {
                // Cap nhat vao DB
                DBPool.releadRsPstm(rs, pstm);
                pstm = conn.prepareStatement("UPDATE advertise SET CLICK = CLICK + 1 WHERE ADV_ID = ? ");
                pstm.setInt(1, one.getAdvID());
                pstm.execute();
                flag = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
            return flag;
        }
    }
    //----
    private int id;
    private int advID;
    private int groupID;
    private int adsCreateID;            // Create By ?
    private String domainRequest;       // Destination Domain
    private String requestURL;          // Source dislay Ads
    private String destinationURL;      // Destination URL
    private String ipCLick;
    private String userAgent;
    private Timestamp timeClick;
    private long timeLong;
    // For IP Location
    private String country_code;     // Country Code 
    private String country_name;    // Country_name
    private String city;
    private String region_code;
    private String region_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIpCLick() {
        return ipCLick;
    }

    public void setIpCLick(String ipCLick) {
        this.ipCLick = ipCLick;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Timestamp getTimeClick() {
        return timeClick;
    }

    public void setTimeClick(Timestamp timeClick) {
        this.timeClick = timeClick;
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
