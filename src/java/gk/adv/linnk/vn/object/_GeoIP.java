/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import gk.adv.linnk.vn.utils.DBPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 *
 * @author TUANPLA
 */
public class _GeoIP {

    public static HashMap<String, String> CACHE = new HashMap<>();

    public static void init() {
        cacheAll();
    }

    public static boolean checkExist(String ip) {
        return CACHE.get(ip) != null;
    }

    private static void cacheAll() {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT IP,REGION_CODE FROM _GEO_IP ";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CACHE.put(rs.getString("IP"), rs.getString("REGION_CODE"));
            }
        } catch (Exception e) {
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }
    int id;
    String ip;
    String countryCode;
    String countryName;
    String regionCode;
    String regionName;
    String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
