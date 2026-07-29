/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import config.ListionContext;
import gk.adv.linnk.vn.cache.Queue;
import gk.adv.linnk.vn.utils.Constants;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.DateProc;
import gk.adv.linnk.vn.utils.Tool;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author TUANPLA
 */
public class MyLocation extends Thread {

    public static final Queue location = new Queue();

    @Override
    public void run() {
        int count = 1;
        try {
            System.out.println("MyLocation Thread Start========>" + DateProc.createTimestamp());
            while (ListionContext.isRuning) {
                System.out.println("location Queue:" + location.size());
                if (count == 700) {
                    count = 1;
                    sleep(1 * 60 * 60 * 1000);
                    // Giai Phong Queeu
                    location.release();
                }
                if (location.size() > 1000) {
                    location.release();
                    System.out.println("Release location Queue:" + location.size());
                }
                String _ip = (String) location.dequeue();
                MyLocation lct = getLocation(_ip);
                count++;
                if (lct.getCountry_code().equalsIgnoreCase("VN") && !Tool.checkNull(lct.getRegion_code())) {
                    LogLocation(lct);
                }

            }
        } catch (Exception e) {
        }
    }

    public static MyLocation getLocation(String ip) {
        MyLocation local = new MyLocation();
        try {
            URL url = new URL("http://freegeoip.net/json/" + ip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            int status = connection.getResponseCode();
            if (status == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String json = "";
                for (String line; (line = reader.readLine()) != null;) {
                    json += line;
                }
                JSONObject jobj = (JSONObject) JSONSerializer.toJSON(json);
                local.setIp(ip);
                local.setCity(jobj.getString("city"));
                local.setCountry_code(jobj.getString("country_code"));
                local.setCountry_name(jobj.getString("country_name"));
                local.setRegion_code(jobj.getString("region_code"));
                local.setRegion_name(jobj.getString("region_name"));
                Constants.CACHE_LOCAT.put(local.getCity(), local);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return local;
    }

    public static MyLocation fromJson(String json) {
        MyLocation local = new MyLocation();
        JSONObject jobj = (JSONObject) JSONSerializer.toJSON(json);
        local.setIp(jobj.getString("ip"));
        local.setCity(jobj.getString("city"));
        local.setCountry_code(jobj.getString("country_code"));
        local.setCountry_name(jobj.getString("country_name"));
        local.setRegion_code(jobj.getString("region_code"));
        local.setRegion_name(jobj.getString("region_name"));
        return local;
    }

    public static void LogLocation(MyLocation one) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT IGNORE INTO _GEO_IP(IP,COUNTRY_CODE,COUNTRY_NAME,REGION_CODE,REGION_NAME,CITY,CHOICE_CITY)"
                + "                       VALUES(? ,     ?      ,     ?      ,    ?      ,    ?      ,  ? ,     ?     )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, one.getIp());
            pstm.setString(i++, one.getCountry_code());
            pstm.setString(i++, one.getCountry_name());
            pstm.setString(i++, one.getRegion_code());
            pstm.setString(i++, one.getRegion_name());
            pstm.setString(i++, one.getCity());
            pstm.setString(i++, one.getChoiceCity());
            pstm.execute();
        } catch (Exception e) {
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }
    String ip;
    String country_code;
    String country_name;
    String region_code;
    String region_name;
    String city;
    String choiceCity;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChoiceCity() {
        return choiceCity;
    }

    public void setChoiceCity(String choiceCity) {
        this.choiceCity = choiceCity;
    }

}
