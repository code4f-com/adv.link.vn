/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.admin;

import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author TUANPLA
 */
public class AccSiteManager {

    public static String[] buidSite(ArrayList<AccSiteManager> all) {
        String[] domain = null;
        if (all != null && !all.isEmpty()) {
            domain = new String[all.size()];
            for (int i = 0; i < all.size(); i++) {
                domain[i] = all.get(i).getDomain();
            }
        }
        return domain;
    }

    public void mapSiteManager(String[] arrSiteId, int _accid) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM acc_site_manager WHERE ACC_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, _accid);
            pstm.executeUpdate();
            for (String oneArr : arrSiteId) {
                int _siteId = Tool.string2Integer(oneArr);
                // Xoa Het Roi Insert Lai
                pstm = conn.prepareStatement("INSERT INTO acc_site_manager(ACC_ID,SITE_ID,STATUS) VALUES(?,?,1) ");
                pstm.setInt(1, _accid);
                pstm.setInt(2, _siteId);
                pstm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }

    private void clearRole(int accId) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE acc_site_manager SET STATUS = 0 WHERE ACC_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, accId);
            pstm.executeUpdate();
        } catch (Exception e) {
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
    }

    public static ArrayList<AccSiteManager> getAccSiteManager(int accId) {
        ArrayList<AccSiteManager> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT S.ACC_ID,A.SITE_ID,A.G_ID,A.DOMAIN,A.PRICE_CLICK,A.IS_MYSITE,A.STATUS AS SITE_STATUS,"
                + " S.STATUS AS MANAGER_STATUS"
                + " from SITE_MANAGER A LEFT JOIN ACC_SITE_MANAGER S on A.SITE_ID = S.SITE_ID WHERE S.ACC_ID = ? ORDER BY A.G_ID";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, accId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                AccSiteManager one = new AccSiteManager();
                one.setAccId(rs.getInt("ACC_ID"));
                one.setSiteId(rs.getInt("SITE_ID"));
                one.setSiteStatus(rs.getInt("SITE_STATUS"));
                one.setManagerStatus(rs.getInt("MANAGER_STATUS"));
                one.setgId(rs.getInt("G_ID"));
                one.setDomain(rs.getString("DOMAIN"));
                one.setPriceClick(rs.getInt("PRICE_CLICK"));
                one.setIsMySite(rs.getInt("IS_MYSITE"));
                all.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }
    //
    private int accId;
    private int siteId;
    private int siteStatus;
    private int managerStatus;
    private int gId;
    private String domain;
    private int priceClick;
    private int isMySite;

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(int siteStatus) {
        this.siteStatus = siteStatus;
    }

    public int getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(int managerStatus) {
        this.managerStatus = managerStatus;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPriceClick() {
        return priceClick;
    }

    public void setPriceClick(int priceClick) {
        this.priceClick = priceClick;
    }

    public int getIsMySite() {
        return isMySite;
    }

    public void setIsMySite(int isMySite) {
        this.isMySite = isMySite;
    }

}
