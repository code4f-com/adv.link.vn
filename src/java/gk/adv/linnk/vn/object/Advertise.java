/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import gk.adv.linnk.vn.multipart.request.MultipartFile;
import gk.adv.linnk.vn.utils.Constants;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.DataConvert;
import gk.adv.linnk.vn.utils.FileUtils;
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
public class Advertise {

    static Logger logger = Logger.getLogger(Advertise.class);

    public ArrayList<Advertise> getRanDomAds() {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ADVERTISE WHERE STATUS = 1 ";

        sql += " ORDER BY ADV_ID DESC";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
//                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setFilePath(rs.getString("FILE_PATH"));
//                one.setTitle_price(rs.getString("TITLE_PRICE"));
//                one.setPriceSell(rs.getInt("PRICE_SELL"));
//                one.setPrice_root(rs.getInt("PRICE_ROOT"));
//                one.setTitle_button(rs.getString("TITLE_BUTTON"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
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
    /***
     * Chia các ảnh cho nhom Anh slide - anh banner (Anh Nhieu kich thuoc - anh banner)
     * <br/> Anh Va Noi dung
     * @param all
     * @return 
     */
    public static ArrayList<ArrayList<Advertise>> splitAdsGroup(ArrayList<Advertise> all) {
        ArrayList<ArrayList<Advertise>> result = new ArrayList();
        ArrayList<Advertise> gImageSlide = new ArrayList<>();
        ArrayList<Advertise> gimgText = new ArrayList<>();
        for (Advertise one : all) {
//            System.out.println("Ads id [" + one.getAdvID() + "]:kind:" + one.getKind());
            if (one.getKind() == KIND.IMAGE_SLIDE.getValue() || one.getKind() == KIND.IMAGE.getValue()) {
                // La anh nho co nhieu kich thuoc - Chi co 1 Anh Banner
                gImageSlide.add(one);
            }
            if (one.getKind() == KIND.IMAGE_TEXT.getValue()) {  
                // Anh va noi dung san pham
                gimgText.add(one);
            }
        }
        result.add(gImageSlide);
        result.add(gimgText);
        return result;
    }

    public static ArrayList<ArrayList<Advertise>> splitImgAndImgSlide(ArrayList<Advertise> all) {
        ArrayList<ArrayList<Advertise>> result = new ArrayList();
        ArrayList<Advertise> imgNomal = new ArrayList<>();
        ArrayList<Advertise> imgSlide = new ArrayList<>();
        for (Advertise one : all) {
//            System.out.println("Ads id [" + one.getAdvID() + "]:kind:" + one.getKind());
            if (one.getKind() == KIND.IMAGE.getValue()) {
                imgNomal.add(one);
            }
            if (one.getKind() == KIND.IMAGE_SLIDE.getValue()) {
                // La anh nho co nhieu kich thuoc
                imgSlide.add(one);
            }
        }
        result.add(imgNomal);
        result.add(imgSlide);
        return result;
    }
    
     public static ArrayList<ArrayList<Advertise>> splitImgAndImgSlideHover(ArrayList<Advertise> all) {
        ArrayList<ArrayList<Advertise>> result = new ArrayList();
        ArrayList<Advertise> imgHover = new ArrayList<>();
        ArrayList<Advertise> imgSlide = new ArrayList<>();
        for (Advertise one : all) {
//            System.out.println("Ads id [" + one.getAdvID() + "]:kind:" + one.getKind());
            if (one.getKind() == KIND.IMAGE_PRICE.getValue()) {
                // Anh va gia dung trong Hover
                imgHover.add(one);
            }
            if (one.getKind() == KIND.IMAGE_SLIDE.getValue()) {
                imgSlide.add(one);
            }
        }
        result.add(imgHover);
        result.add(imgSlide);
        return result;
    }
     
     public static ArrayList<ArrayList<Advertise>> splitImgAndBannerHover(ArrayList<Advertise> all) {
        ArrayList<ArrayList<Advertise>> result = new ArrayList();
        ArrayList<Advertise> imgHover = new ArrayList<>();
        ArrayList<Advertise> imgSlide = new ArrayList<>();
        for (Advertise one : all) {
//            System.out.println("Ads id [" + one.getAdvID() + "]:kind:" + one.getKind());
            if (one.getKind() == KIND.IMAGE_PRICE.getValue()) {
                // Anh va gia dung trong Hover
                imgHover.add(one);
            }
            if (one.getKind() == KIND.IMAGE.getValue()) {
                imgSlide.add(one);
            }
        }
        result.add(imgHover);
        result.add(imgSlide);
        return result;
    }

    public static ArrayList<ArrayList<Advertise>> splitImg_Slide(ArrayList<Advertise> all) {
        ArrayList<ArrayList<Advertise>> result = new ArrayList();
        ArrayList<Advertise> gImageSlide = new ArrayList<>();
        ArrayList<Advertise> gImageLogo = new ArrayList<>();
        for (Advertise one : all) {
//            Tool.Debug("Kind:" + one.getKind());
            if (one.getKind() == KIND.IMAGE_SLIDE.getValue()) {
                // La anh nho co nhieu kich thuoc - Chi co 1 Anh Banner
                gImageSlide.add(one);
            }
            if (one.getKind() == KIND.IMAGE.getValue()) {
                // Image Banner
                gImageLogo.add(one);
            }
        }
        result.add(gImageSlide);
        result.add(gImageLogo);
        return result;
    }

    public ArrayList<Advertise> getAllAdsByArrID(ArrayList<String> arrID) {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ADVERTISE WHERE STATUS = 1 ";
        if (!arrID.isEmpty()) {
            int k = 1;
            sql += " AND (";
            for (String one : arrID) {
                sql += "ADV_ID = ?";
                if (k != arrID.size()) {
                    sql += " OR ";
                }
                k++;
            }
            sql += ")";
        }
        sql += " ORDER BY ADV_ID DESC";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            for (String one : arrID) {
                pstm.setString(i++, one);
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setTitle_price(rs.getString("TITLE_PRICE"));
                one.setPriceSell(rs.getInt("PRICE_SELL"));
                one.setPrice_root(rs.getInt("PRICE_ROOT"));
                one.setTitle_button(rs.getString("PRICE_ROOT"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
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

    public ArrayList<Advertise> searForGroup(int gid, int currentPage, int kind, String keyword, String startTime, String endTime) {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
//        String sql = "SELECT A.* FROM ADVERTISE A WHERE STATUS = 1 ";
        String sql = " SELECT A.*,(SELECT `STATUS` FROM group_adv_detail G WHERE G.ADV_ID = A.ADV_ID AND G.GROUP_ID = ?) GSTATUS "
                + " FROM ADVERTISE A WHERE A.STATUS = 1";
        if (kind != -2) {
            sql += " AND A.KIND = ?";
        }
        if (!Tool.checkNull(keyword)) {
            sql += " AND (A.TITLE_TOP like ? OR A.ADV_DESC like ? OR A.TITLE_II like ? OR A.ADV_ID = ?)";
        }
        if (!Tool.checkNull(startTime)) {
            sql += " AND A.START_TIME >= STR_TO_DATE(?,'%d,%m,%Y 00:00:00')";
        }
        if (!Tool.checkNull(endTime)) {
            sql += " AND A.END_TIME <= STR_TO_DATE(?,'%d,%m,%Y 23:59:59')";
        }
        sql += " ORDER BY A.ADV_ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, gid);
            if (kind != -2) {
                pstm.setInt(i++, kind);
            }
            if (!Tool.checkNull(keyword)) {
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, keyword);
            }
            if (!Tool.checkNull(startTime)) {
                pstm.setString(i++, startTime);
            }
            if (!Tool.checkNull(endTime)) {
                pstm.setString(i++, endTime);
            }

            pstm.setInt(i++, (currentPage - 1) * Constants.ROW_PER_PAGE);
            pstm.setInt(i++, Constants.ROW_PER_PAGE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setTitle_price(rs.getString("TITLE_PRICE"));
                one.setPriceSell(rs.getInt("PRICE_SELL"));
                one.setPrice_root(rs.getInt("PRICE_ROOT"));
                one.setTitle_button(rs.getString("PRICE_ROOT"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
                one.setGstatus(rs.getInt("GSTATUS"));
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
    // Get list Ads SYSTEM

    public ArrayList<Advertise> getAllAdv(int currentPage, int kind, String keyword, String startTime, String endTime, int status, int adsBy, String checkExp) {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ADVERTISE WHERE ADS_BY = ? ";
        if (checkExp.equalsIgnoreCase("on")) {
            sql += " AND END_TIME < NOW()";
        }
        if (kind != -2) {
            sql += " AND KIND = ?";
        }
        if (!Tool.checkNull(keyword)) {
            sql += " AND (TITLE_TOP like ? OR ADV_DESC like ? OR TITLE_II like ?)";
        }
        if (!Tool.checkNull(startTime)) {
            sql += " AND START_TIME >= STR_TO_DATE(?,'%d,%m,%Y 00:00:00')";
        }
        if (!Tool.checkNull(endTime)) {
            sql += " AND END_TIME <= STR_TO_DATE(?,'%d,%m,%Y 23:59:59')";
        }
        if (status != -2) {
            sql += " AND STATUS = ?";
        } else {
            sql += " and STATUS != 404 ";
        }
        sql += " ORDER BY ADV_ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, adsBy);
            if (kind != -2) {
                pstm.setInt(i++, kind);
            }
            if (!Tool.checkNull(keyword)) {
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
            }
            if (!Tool.checkNull(startTime)) {
                pstm.setString(i++, startTime);
            }
            if (!Tool.checkNull(endTime)) {
                pstm.setString(i++, endTime);
            }
            if (status != -2) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, (currentPage - 1) * Constants.ROW_PER_PAGE);
            pstm.setInt(i++, Constants.ROW_PER_PAGE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setWidth(rs.getInt("WIDTH"));
                one.setHeight(rs.getInt("HEIGHT"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setTitle_price(rs.getString("TITLE_PRICE"));
                one.setPriceSell(rs.getInt("PRICE_SELL"));
                one.setPrice_root(rs.getInt("PRICE_ROOT"));
                one.setTitle_button(rs.getString("PRICE_ROOT"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
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

    public int countAll(int kind, String keyword, String startTime, String endTime, int status, int adsBy, String checkExp) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM ADVERTISE WHERE ADS_BY = ? ";
        if (checkExp.equalsIgnoreCase("on")) {
            sql += " AND END_TIME < NOW()";
        }
        if (kind != -2) {
            sql += " AND KIND = ?";
        }
        if (!Tool.checkNull(keyword)) {
            sql += " AND (TITLE_TOP like ? OR ADV_DESC like ? OR TITLE_II like ?)";
        }
        if (!Tool.checkNull(startTime)) {
            sql += " AND START_TIME >= STR_TO_DATE(?,'%d,%m,%Y 00:00:00')";
        }
        if (!Tool.checkNull(endTime)) {
            sql += " AND END_TIME <= STR_TO_DATE(?,'%d,%m,%Y 23:59:59')";
        }
        if (status != -2) {
            sql += " AND STATUS = ?";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, adsBy);
            if (kind != -2) {
                pstm.setInt(i++, kind);
            }
            if (!Tool.checkNull(keyword)) {
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
            }
            if (!Tool.checkNull(startTime)) {
                pstm.setString(i++, startTime);
            }
            if (!Tool.checkNull(endTime)) {
                pstm.setString(i++, endTime);
            }
            if (status != -2) {
                pstm.setInt(i++, status);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public int countAllExpire() {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM ADVERTISE WHERE END_TIME < NOW() ";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public Advertise getAdvertise(int id) {
        Advertise one = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ADVERTISE WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setWidth(rs.getInt("WIDTH"));
                one.setHeight(rs.getInt("HEIGHT"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setRate(rs.getString("RATE"));
                one.setTitle_price(rs.getString("TITLE_PRICE"));
                one.setPriceSell(rs.getInt("PRICE_SELL"));
                one.setPrice_root(rs.getInt("PRICE_ROOT"));
                one.setTitle_button(rs.getString("PRICE_ROOT"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
                one.setAdsBy(rs.getInt("ADS_BY"));
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return one;
    }
    // Flash

    public boolean addNewFlash(Advertise oneAdv, MultipartFile file) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO ADVERTISE(KIND,TITLE_TOP,ADV_DESC,DESTINATION_URL,WIDTH,HEIGHT,START_TIME,END_TIME,CREATE_DATE,CREATE_BY,STATUS,ADS_BY)"
                + "                  VALUES(?   ,    ?    ,    ?   ,      ?        ,   ? ,  ?   ,     ?    ,    ?   , now()     ,    ?    ,  ?   ,   ?  )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setString(i++, oneAdv.getDestinationUrl());
            pstm.setInt(i++, oneAdv.getWidth());
            pstm.setInt(i++, oneAdv.getHeight());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getCreateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            if (pstm.executeUpdate() == 1) {
                pstm.clearParameters();
                pstm.close();
                sql = "SELECT @@IDENTITY AS 'Identity';";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    long advid = rs.getLong(1);
                    if (file != null) {
                        String pathSave = "/" + advid + "." + file.getExtentsion();
//                        String url_real = Constants.PATH_FLASH + "/" + pathSave;
                        String url_real = Constants.PATH_FLASH + pathSave;
                        if (FileUtils.writeNomalFile(file.getByteFromFile(), url_real)) {
                            pstm.clearParameters();
                            pstm.close();
                            sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                            pstm = conn.prepareStatement(sql);
                            pstm.setString(1, pathSave);
                            pstm.setLong(2, advid);
                            pstm.execute();
                            flag = true;
                        }
                    }
                } else {
                    Tool.Debug("Insert duoc nhung lai ko lay dc inretment");
                }
            } else {
                Tool.Debug("Khong insert duoc DB");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean updateFlash(Advertise oneAdv, MultipartFile file) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE ADVERTISE SET KIND = ?,TITLE_TOP = ?,ADV_DESC = ?,WIDTH = ?,HEIGHT = ?,START_TIME = ?,END_TIME = ?,"
                + "UPDATE_DATE = NOW(),UPDATE_BY = ?,STATUS = ?,ADS_BY = ? WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setInt(i++, oneAdv.getWidth());
            pstm.setInt(i++, oneAdv.getHeight());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getUpdateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            pstm.setInt(i++, oneAdv.getAdvID());
            if (pstm.executeUpdate() == 1) {
                if (file != null) {
                    String pathSave = "/" + oneAdv.getAdvID() + "." + file.getExtentsion();
                    String url_real = Constants.PATH_FLASH + pathSave;
                    if (FileUtils.writeNomalFile(file.getByteFromFile(), url_real)) {
                        pstm.clearParameters();
                        pstm.close();
                        sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                        pstm = conn.prepareStatement(sql);
                        pstm.setString(1, pathSave);
                        pstm.setLong(2, oneAdv.getAdvID());
                        pstm.execute();
                        flag = true;
                    }
                }

            } else {
                Tool.Debug("Khong insert duoc DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    // Image

    public boolean addNewImage(Advertise oneAdv, MultipartFile file1x1, MultipartFile file1x2, int zoom) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO ADVERTISE(KIND,TITLE_TOP,ADV_DESC,DESTINATION_URL,PRICE_SELL,WIDTH,HEIGHT,START_TIME,END_TIME,CREATE_DATE,CREATE_BY,STATUS,ADS_BY)"
                + "                  VALUES(?   ,    ?    ,    ?   ,     ?         ,    ?     ,  ?  ,  ?   ,    ?     ,    ?   , now()     ,    ?    ,  ?   ,   ?  )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setString(i++, oneAdv.getDestinationUrl());
            pstm.setInt(i++, oneAdv.getPriceSell());
            pstm.setInt(i++, oneAdv.getWidth());
            pstm.setInt(i++, oneAdv.getHeight());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getCreateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            if (pstm.executeUpdate() == 1) {
                pstm.clearParameters();
                pstm.close();
                sql = "SELECT @@IDENTITY AS 'Identity';";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    long advid = rs.getLong(1);
                    if (file1x1 != null) {
                        // ảnh gốc 100x100 lưu DB pathSave
                        String pathSave = "/" + advid + "." + file1x1.getExtentsion();
                        String url_real = Constants.PATH_IMAGE + pathSave;
                        if (zoom == 1) {
                            // Zoom
                            // 70x70
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 70, Constants.PATH_IMAGE + "/70x70/" + pathSave, file1x1.getExtentsion());
                            // 100x100 -- Default
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 100, url_real, file1x1.getExtentsion());
                            // 150x150
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 150, Constants.PATH_IMAGE + "/150x150/" + pathSave, file1x1.getExtentsion());
                            // 200x200
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 200, Constants.PATH_IMAGE + "/200x200/" + pathSave, file1x1.getExtentsion());
                            // 210x210
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 210, Constants.PATH_IMAGE + "/210x210/" + pathSave, file1x1.getExtentsion());
                            // 300x300
                            FileUtils.resizeWriteImg(file1x1.getInputStream(), 300, Constants.PATH_IMAGE + "/300x300/" + pathSave, file1x1.getExtentsion());

                            pstm.clearParameters();
                            pstm.close();
                            sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                            pstm = conn.prepareStatement(sql);
                            pstm.setString(1, pathSave);
                            pstm.setLong(2, advid);
                            pstm.execute();
                            flag = true;
                            if (file1x2 != null) {
                                // 70x140
                                FileUtils.resizeWriteImg(file1x2.getInputStream(), 70, Constants.PATH_IMAGE + "/70x140/" + pathSave, file1x2.getExtentsion());
                                // 75x150
                                FileUtils.resizeWriteImg(file1x2.getInputStream(), 75, Constants.PATH_IMAGE + "/75x150/" + pathSave, file1x2.getExtentsion());
                                // 100x200
                                FileUtils.resizeWriteImg(file1x2.getInputStream(), 100, Constants.PATH_IMAGE + "/100x200/" + pathSave, file1x2.getExtentsion());
                                // 140x280
                                FileUtils.resizeWriteImg(file1x2.getInputStream(), 140, Constants.PATH_IMAGE + "/140x280/" + pathSave, file1x2.getExtentsion());
                                // 150x300
                                FileUtils.resizeWriteImg(file1x2.getInputStream(), 150, Constants.PATH_IMAGE + "/150x300/" + pathSave, file1x2.getExtentsion());
                            }
                        } else {
//                            if (FileUtils.writeFileToDisk(file.getByteFromFile(), url_real)) {
                            byte[] data = DataConvert.InputStream2Bytes(file1x1.getInputStream());
//                            if (FileUtils.writeImg(file1x1.getInputStream(), url_real, file1x1.getExtentsion())) {
                            if (FileUtils.writeNomalFile(data, url_real)) {
                                Tool.Debug("Ghi file anh ko zoom ok");
                                pstm.clearParameters();
                                pstm.close();
                                sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                                pstm = conn.prepareStatement(sql);
                                pstm.setString(1, pathSave);
                                pstm.setLong(2, advid);
                                pstm.execute();
                                flag = true;
                            } else {
                                Tool.Debug("Write file  To disk Error");
                            }
                        }
                    } else {
                        Tool.Debug("File 1x1 null cmnr");
                    }
                } else {
                    Tool.Debug("Insert duoc nhung lai ko lay dc inretment");
                }
            } else {
                Tool.Debug("Khong insert duoc DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean updateImage(Advertise oneAdv, MultipartFile file1x1, MultipartFile file1x2, int zoom) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE ADVERTISE SET KIND =?,TITLE_TOP =?,ADV_DESC =?,PRICE_SELL=?,DESTINATION_URL =?,WIDTH = ?, HEIGHT = ?,START_TIME =?,END_TIME =?,"
                + "UPDATE_DATE = NOW(),UPDATE_BY = ?,STATUS =?,ADS_BY = ? WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setInt(i++, oneAdv.getPriceSell());
            pstm.setString(i++, oneAdv.getDestinationUrl());
            pstm.setInt(i++, oneAdv.getWidth());
            pstm.setInt(i++, oneAdv.getHeight());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getUpdateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            pstm.setInt(i++, oneAdv.getAdvID());
            if (pstm.executeUpdate() == 1) {
                if (file1x1 != null) {
                    String pathSave = "/" + oneAdv.getAdvID() + "." + file1x1.getExtentsion();
                    String url_real = Constants.PATH_IMAGE + pathSave;
                    if (zoom == 1) {
                        // Zoom
                        // 70x70
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 70, Constants.PATH_IMAGE + "/70x70/" + pathSave, file1x1.getExtentsion());
                        // 100x100 -- Default
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 100, url_real, file1x1.getExtentsion());
                        // 150x150
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 150, Constants.PATH_IMAGE + "/150x150/" + pathSave, file1x1.getExtentsion());
                        // 200x200
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 200, Constants.PATH_IMAGE + "/200x200/" + pathSave, file1x1.getExtentsion());
                        // 210x210
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 210, Constants.PATH_IMAGE + "/210x210/" + pathSave, file1x1.getExtentsion());
                        // 300x300
                        FileUtils.resizeWriteImg(file1x1.getInputStream(), 300, Constants.PATH_IMAGE + "/300x300/" + pathSave, file1x1.getExtentsion());

                        pstm.clearParameters();
                        pstm.close();
                        sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                        pstm = conn.prepareStatement(sql);
                        pstm.setString(1, pathSave);
                        pstm.setLong(2, oneAdv.getAdvID());
                        pstm.execute();
                        flag = true;
                        if (file1x2 != null) {
                            // 70x140
                            FileUtils.resizeWriteImg(file1x2.getInputStream(), 70, Constants.PATH_IMAGE + "/70x140/" + pathSave, file1x2.getExtentsion());
                            // 75x150
                            FileUtils.resizeWriteImg(file1x2.getInputStream(), 75, Constants.PATH_IMAGE + "/75x150/" + pathSave, file1x2.getExtentsion());
                            // 100x200
                            FileUtils.resizeWriteImg(file1x2.getInputStream(), 100, Constants.PATH_IMAGE + "/100x200/" + pathSave, file1x2.getExtentsion());
                            // 140x280
                            FileUtils.resizeWriteImg(file1x2.getInputStream(), 140, Constants.PATH_IMAGE + "/140x280/" + pathSave, file1x2.getExtentsion());
                            // 150x300
                            FileUtils.resizeWriteImg(file1x2.getInputStream(), 150, Constants.PATH_IMAGE + "/150x300/" + pathSave, file1x2.getExtentsion());
                        }
                    } else {
                        byte[] data = DataConvert.InputStream2Bytes(file1x1.getInputStream());
//                            if (FileUtils.writeImg(file1x1.getInputStream(), url_real, file1x1.getExtentsion())) {
                        if (FileUtils.writeNomalFile(data, url_real)) {
                            pstm.clearParameters();
                            pstm.close();
                            sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                            pstm = conn.prepareStatement(sql);
                            pstm.setString(1, pathSave);
                            pstm.setLong(2, oneAdv.getAdvID());
                            pstm.execute();
                            flag = true;
                        } else {
                            Tool.Debug("Write file  To disk Error");
                        }
                    }
                } else {
                    Tool.Debug("Khong update Anh 1 Thi se check co Anh 2 hay ko?");
                    if (file1x2 != null) {
                        // Bat buoc 2 ảnh phải lưu cùng Extention thì luc lay ra mơi lay được
                        // Con extention de lưu xuống thì kệ mẹ nó theo đúng ảnh up lên
                        String pathSave = "/" + oneAdv.getFilePath();
                        // 70x140
                        FileUtils.resizeWriteImg(file1x2.getInputStream(), 70, Constants.PATH_IMAGE + "/70x140/" + pathSave, file1x2.getExtentsion());
                        // 75x150
                        FileUtils.resizeWriteImg(file1x2.getInputStream(), 75, Constants.PATH_IMAGE + "/75x150/" + pathSave, file1x2.getExtentsion());
                        // 100x200
                        FileUtils.resizeWriteImg(file1x2.getInputStream(), 100, Constants.PATH_IMAGE + "/100x200/" + pathSave, file1x2.getExtentsion());
                        // 140x280
                        FileUtils.resizeWriteImg(file1x2.getInputStream(), 140, Constants.PATH_IMAGE + "/140x280/" + pathSave, file1x2.getExtentsion());
                        // 150x300
                        FileUtils.resizeWriteImg(file1x2.getInputStream(), 150, Constants.PATH_IMAGE + "/150x300/" + pathSave, file1x2.getExtentsion());
                    }
                    flag = true;

                }
            } else {
                Tool.Debug("Khong insert duoc DB");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    // ImageText

    public boolean addNewImageText(Advertise oneAdv, MultipartFile file) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO ADVERTISE(KIND,TITLE_TOP,TITLE_II,ADV_DESC,DESTINATION_URL,TITLE_PRICE,PRICE_SELL,PRICE_ROOT,START_TIME,END_TIME,CREATE_DATE,CREATE_BY,STATUS,ADS_BY,REGION)"
                + "                  VALUES(?   ,    ?    ,   ?    ,    ?   ,     ?         ,      ?    ,     ?    ,     ?    , ?        ,    ?   , now()     ,    ?    ,  ?   ,   ?  ,  ?   )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getTitle_II());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setString(i++, oneAdv.getDestinationUrl());
            pstm.setString(i++, oneAdv.getTitle_price());
            pstm.setInt(i++, oneAdv.getPriceSell());
            pstm.setInt(i++, oneAdv.getPrice_root());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getCreateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            pstm.setString(i++, oneAdv.getRegion());
            if (pstm.executeUpdate() == 1) {
                pstm.clearParameters();
                pstm.close();
                sql = "SELECT @@IDENTITY AS 'Identity';";
                pstm = conn.prepareStatement(sql);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    long advid = rs.getLong(1);
                    if (file != null) {
                        String pathSave = "/" + advid + "." + file.getExtentsion();
                        String url_real = Constants.PATH_IMAGE + pathSave;
//                        Tool.Debug(url_real);
                        FileUtils.resizeMaxWithWriteImg(file.getInputStream(), Constants.MAX_WIDTH, url_real, file.getExtentsion());
                        pstm.clearParameters();
                        pstm.close();
                        sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                        pstm = conn.prepareStatement(sql);
                        pstm.setString(1, pathSave);
                        pstm.setLong(2, advid);
                        pstm.execute();
                        flag = true;
                    } else {
                        Tool.Debug("Khong up File Anh!");
                        flag = true;
                    }
                } else {
                    Tool.Debug("Insert duoc nhung lai ko lay dc inretment");
                }
            } else {
                Tool.Debug("Khong insert duoc DB");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean updateImageText(Advertise oneAdv, MultipartFile file) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE ADVERTISE SET KIND = ?,TITLE_TOP = ?,TITLE_II = ?,ADV_DESC = ?,DESTINATION_URL = ?,TITLE_PRICE = ?,PRICE_SELL = ?,"
                + "PRICE_ROOT = ?,START_TIME = ?,END_TIME = ?,UPDATE_DATE = now(),UPDATE_BY = ?,STATUS = ?,ADS_BY=?,REGION = ? WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneAdv.getKind());
            pstm.setString(i++, oneAdv.getTitle_top());
            pstm.setString(i++, oneAdv.getTitle_II());
            pstm.setString(i++, oneAdv.getDesc());
            pstm.setString(i++, oneAdv.getDestinationUrl());
            pstm.setString(i++, oneAdv.getTitle_price());
            pstm.setInt(i++, oneAdv.getPriceSell());
            pstm.setInt(i++, oneAdv.getPrice_root());
            pstm.setTimestamp(i++, oneAdv.getStartTime());
            pstm.setTimestamp(i++, oneAdv.getEndTime());
            pstm.setInt(i++, oneAdv.getUpdateBy());
            pstm.setInt(i++, oneAdv.getStatus());
            pstm.setInt(i++, oneAdv.getAdsBy());
            pstm.setString(i++, oneAdv.getRegion());
            pstm.setInt(i++, oneAdv.getAdvID());
            if (pstm.executeUpdate() == 1) {
                if (file != null) {
                    String pathSave = "/" + oneAdv.getAdvID() + "." + file.getExtentsion();
//                    String url_real = Constants.PATH_IMAGE + "/" + pathSave;
                    String url_real = Constants.PATH_IMAGE + pathSave;
                    FileUtils.resizeMaxWithWriteImg(file.getInputStream(), Constants.MAX_WIDTH, url_real, file.getExtentsion());
                    //
                    pstm.clearParameters();
                    pstm.close();
                    sql = "UPDATE advertise SET FILE_PATH = ? WHERE ADV_ID = ?";
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, pathSave);
                    pstm.setLong(2, oneAdv.getAdvID());
                    pstm.execute();
                    flag = true;

                } else {
                    flag = true;
                }
            } else {
                Tool.Debug("Khong UPDATE duoc DB Update image and Text");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean delete404(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE ADVERTISE SET STATUS = ? WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, STATUS.DELETE.getValue());
            pstm.setInt(2, id);
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean delete(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM ADVERTISE WHERE ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean updateStatus(long id, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean flag = true;
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(
                    "UPDATE ADVERTISE set STATUS = ?,UPDATE_DATE = now() where ADV_ID = ?");
            pstm.setInt(1, status);
            pstm.setLong(2, id);
            int result = pstm.executeUpdate();
            if (result <= 0) {
                flag = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return flag;
    }

    public boolean updateItemStatusInGroup(long advId, int gid, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean flag = true;
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(
                    "UPDATE GROUP_ADV_DETAIL set STATUS = ? where ADV_ID = ? AND GROUP_ID = ?");
            pstm.setInt(1, status);
            pstm.setLong(2, advId);
            pstm.setLong(3, gid);
            int result = pstm.executeUpdate();
            if (result <= 0) {
                flag = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return flag;
    }

    //*********** CUSTOMER
    public ArrayList<Advertise> getAllAdsByCustomer(int currentPage, int accId, int kind, String keyword, String startTime, String endTime, int status) {
        ArrayList<Advertise> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ADVERTISE WHERE ADS_BY = ? ";
        if (accId != 0) {
            sql += " AND CREATE_BY = ?";
        }
        if (kind != -2) {
            sql += " AND KIND = ?";
        }
        if (!Tool.checkNull(keyword)) {
            sql += " AND (TITLE_TOP like ? OR ADV_DESC like ? OR TITLE_II like ?)";
        }
        if (!Tool.checkNull(startTime)) {
            sql += " AND START_TIME >= STR_TO_DATE(?,'%d,%m,%Y 00:00:00')";
        }
        if (!Tool.checkNull(endTime)) {
            sql += " AND END_TIME <= STR_TO_DATE(?,'%d,%m,%Y 23:59:59')";
        }
        if (status != -2) {
            sql += " AND STATUS = ?";
        } else {
            sql += " and STATUS != 404 ";
        }
        sql += " ORDER BY ADV_ID DESC LIMIT ?,?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, ADSBY.CUSTOMER.value);
            if (accId != 0) {
                pstm.setInt(i++, accId);
            }
            if (kind != -2) {
                pstm.setInt(i++, kind);
            }
            if (!Tool.checkNull(keyword)) {
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
            }
            if (!Tool.checkNull(startTime)) {
                pstm.setString(i++, startTime);
            }
            if (!Tool.checkNull(endTime)) {
                pstm.setString(i++, endTime);
            }
            if (status != -2) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, (currentPage - 1) * Constants.ROW_PER_PAGE);
            pstm.setInt(i++, Constants.ROW_PER_PAGE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Advertise one = new Advertise();
                one.setAdvID(rs.getInt("ADV_ID"));
                one.setKind(rs.getInt("KIND"));
                one.setTitle_top(rs.getString("TITLE_TOP"));
                one.setTitle_II(rs.getString("TITLE_II"));
                one.setDesc(rs.getString("ADV_DESC"));
                one.setDestinationUrl(rs.getString("DESTINATION_URL"));
                one.setFilePath(rs.getString("FILE_PATH"));
                one.setTitle_price(rs.getString("TITLE_PRICE"));
                one.setPriceSell(rs.getInt("PRICE_SELL"));
                one.setPrice_root(rs.getInt("PRICE_ROOT"));
                one.setTitle_button(rs.getString("PRICE_ROOT"));
                one.setStartTime(rs.getTimestamp("START_TIME"));
                one.setEndTime(rs.getTimestamp("END_TIME"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setUpdateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
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

    public int countAllAdsByCustomer(int accId, int kind, String keyword, String startTime, String endTime, int status) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM ADVERTISE WHERE ADS_BY = ? ";
        if (accId != 0) {
            sql += " AND CREATE_BY = ?";
        }
        if (kind != -2) {
            sql += " AND KIND = ?";
        }
        if (!Tool.checkNull(keyword)) {
            sql += " AND (TITLE_TOP like ? OR ADV_DESC like ? OR TITLE_II like ?)";
        }
        if (!Tool.checkNull(startTime)) {
            sql += " AND START_TIME >= STR_TO_DATE(?,'%d,%m,%Y 00:00:00')";
        }
        if (!Tool.checkNull(endTime)) {
            sql += " AND END_TIME <= STR_TO_DATE(?,'%d,%m,%Y 23:59:59')";
        }
        if (status != -2) {
            sql += " AND STATUS = ?";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, ADSBY.CUSTOMER.value);
            if (accId != 0) {
                pstm.setInt(i++, accId);
            }
            if (kind != -2) {
                pstm.setInt(i++, kind);
            }
            if (!Tool.checkNull(keyword)) {
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
                pstm.setString(i++, "%" + keyword + "%");
            }
            if (!Tool.checkNull(startTime)) {
                pstm.setString(i++, startTime);
            }
            if (!Tool.checkNull(endTime)) {
                pstm.setString(i++, endTime);
            }
            if (status != -2) {
                pstm.setInt(i++, status);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }
    //****************

    public static enum STATUS {

        SHOW_ALL(-2, "Tất cả"),
        WAIT_ACTIVE(-1, "QC Chờ duyệt"),
        BLOCK(0, "QC bị khóa"), // Tam Dung (Tự dừng - hết tiền ..)
        ACTIVE(1, "QC kích hoạt"), // Dang chay
        COMPLETED(2, "Hoàn thành"), // Kế hoạch quảng cáo
        REJECT(-9, "Từ chối"),
        DELETE(404, "QC bị xóa");
        private final int value;
        private final String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        private STATUS(int value, String name) {
            this.value = value;
            this.name = name;
        }
    };

    public static enum KIND {

        // Anh va Text
        IMAGE_TEXT(1, "QC Ảnh và Text"),
        // Anh va gia Hover
        IMAGE_PRICE(3, "QC Ảnh và Giá"),
        // Anh Slide
        IMAGE_SLIDE(4, "QC Ảnh-Trong Slide"),
        // Chi co Anh
        IMAGE(0, "QC Ảnh"),
        // Flash one Item
        FLASH(2, "QC Flash");
        private final int value;
        private final String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        private KIND(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getName(int searchVal) {
            String _name = "";
            for (KIND one : KIND.values()) {
                if (one.getValue() == searchVal) {
                    _name = one.getName();
                    break;
                }
            }
            return _name;
        }
    };

    public static enum ADSBY {

        SYSTEM(0),
        CUSTOMER(1);
        private int value;

        public int getValue() {
            return value;
        }

        private ADSBY(int value) {
            this.value = value;
        }
    };

    public static enum RATE {

        RATE_1x1("1:1"),
        RATE_3x2("3:2"),
        RATE_2_3("2:3");
        private String value;

        public String getValue() {
            return value;
        }

        private RATE(String value) {
            this.value = value;
        }
    };
    //---
    private int advID;
    private int kind;
    private String title_top;
    private String title_II;
    private String desc;
    private String destinationUrl;
    private int width;
    private int height;
    private String filePath;
    private String rate;
    private String title_price;
    private int priceSell;
    private int price_root;
    private String title_button;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createDate;
    private int createBy;
    private Timestamp updateDate;
    private int updateBy;
    private int click;
    private int status;
    private int adsBy;
    private String region;
    //--
    private int gstatus;            // Group Status

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getDestinationUrl() {
        return destinationUrl;
    }

    public void setDestinationUrl(String destinationUrl) {
        this.destinationUrl = destinationUrl;
    }

    public int getAdvID() {
        return advID;
    }

    public void setAdvID(int advID) {
        this.advID = advID;
    }

    public String getTitle_top() {
        if (title_top == null) {
            return "";
        } else {
            return title_top;
        }
    }

    public void setTitle_top(String title_top) {
        this.title_top = title_top;
    }

    public String getTitle_II() {
        if (title_II == null) {
            return "";
        }
        return title_II;
    }

    public void setTitle_II(String title_II) {
        this.title_II = title_II;
    }

    public String getDesc() {
        if (desc == null) {
            return "";
        }
        return desc;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFilePath() {
        if (filePath == null) {
            return "";
        }
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getimg100x200() {
        return "/100x200" + filePath;
    }

    public String getTitle_price() {
        if (title_price == null) {
            return "";
        }
        return title_price;
    }

    public void setTitle_price(String title_price) {
        this.title_price = title_price;
    }

    public int getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(int priceSell) {
        this.priceSell = priceSell;
    }

    public int getPrice_root() {
        return price_root;
    }

    public void setPrice_root(int price_root) {
        this.price_root = price_root;
    }

    public String getTitle_button() {
        if (title_button == null) {
            return "";
        }
        return title_button;
    }

    public void setTitle_button(String title_button) {
        this.title_button = title_button;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getAdsBy() {
        return adsBy;
    }

    public void setAdsBy(int adsBy) {
        this.adsBy = adsBy;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getGstatus() {
        return gstatus;
    }

    public void setGstatus(int gstatus) {
        this.gstatus = gstatus;
    }

}
