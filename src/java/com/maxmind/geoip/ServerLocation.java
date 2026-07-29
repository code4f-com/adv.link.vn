package com.maxmind.geoip;

import gk.adv.linnk.vn.utils.DBPool;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author TUANPLA
 */
public class ServerLocation {

    public static void main(String[] args) {
        ServerLocation obj = new ServerLocation();
//        ServerLocation location = obj.getLocation("220.113.175.190");
        ServerLocation location = obj.getLocation("61.231.9.7");

        System.out.println("CountryCode:" + location.getCountryCode());
        System.out.println("CountryName:" + location.getCountryName());
        System.out.println("PostalCode:" + location.getPostalCode());
        System.out.println("Region:" + location.getRegion());
        System.out.println("RegionName:" + location.getRegionName());
        System.out.println("city:" + location.getCity());
        System.out.println("Latitude:" + location.getLatitude());
        System.out.println("Longitude:" + location.getLongitude());
    }

    public ServerLocation getLocation(String ipAddress) {

        File file = new File("D:\\_PROJECT\\adv.link.vn\\web\\test\\GeoLiteCity.dat");
        return getLocation(ipAddress, file);

    }

    public ServerLocation getLocation(String ipAddress, File file) {

        ServerLocation serverLocation = null;

        try {

            serverLocation = new ServerLocation();

            LookupService lookup = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);

            serverLocation.setCountryCode(locationServices.countryCode);
            serverLocation.setCountryName(locationServices.countryName);
            serverLocation.setRegion(locationServices.region);
            serverLocation.setRegionName(com.maxmind.geoip.regionName.regionNameByCode(
                    locationServices.countryCode, locationServices.region));
            serverLocation.setCity(locationServices.city);
            serverLocation.setPostalCode(locationServices.postalCode);
            serverLocation.setLatitude(String.valueOf(locationServices.latitude));
            serverLocation.setLongitude(String.valueOf(locationServices.longitude));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return serverLocation;

    }

    private String countryCode;
    private String countryName;
    private String region;
    private String regionName;
    private String city;
    private String postalCode;
    private String latitude;
    private String longitude;
    private String inputUser;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getInputUser() {
        return inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public static ServerLocation logInfo(ServerLocation one) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO USER_HELP(CountryCode,CountryName,PostalCode,Region,RegionName,city,Latitude,Longitude,INPUT_USER)"
                + "                  VALUES(    ?      ,    ?      ,   ?      ,  ?   ,   ?      ,  ? ,   ?    ,   ?     ,    ?     )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getCountryCode());
            pstm.setString(i++, one.getCountryName());
            pstm.setString(i++, one.getPostalCode());
            pstm.setString(i++, one.getRegion());
            pstm.setString(i++, one.getRegionName());
            pstm.setString(i++, one.getCity());
            pstm.setString(i++, one.getLatitude());
            pstm.setString(i++, one.getLongitude());
            pstm.setString(i++, one.getInputUser());
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return one;
    }

}
