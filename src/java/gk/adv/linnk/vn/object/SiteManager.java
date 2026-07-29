/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import config.ListionContext;
import gk.adv.linnk.vn.multipart.request.MultipartFile;
import gk.adv.linnk.vn.utils.Constants;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.FileUtils;
import gk.adv.linnk.vn.utils.Tool;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class SiteManager {

    static Logger logger = Logger.getLogger(SiteManager.class);
    public static ArrayList<SiteManager> CACHE_ALL = new ArrayList<>();
    public static final String PATH_ICON = "/resource/images/domain-icon";

    static {
        CACHE_ALL = getAllCache();
    }

    private void reloadCache() {
        CACHE_ALL = getAllCache();
        SiteGroup.reload();
    }

    public static ArrayList<SiteManager> getAllCache() {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM site_manager WHERE STATUS = 1";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SiteManager site = new SiteManager();
                site.setId(rs.getInt("SITE_ID"));
                site.setGid(rs.getInt("G_ID"));
                site.setIconPath(rs.getString("ICON_PATH"));
                site.setDomain(rs.getString("DOMAIN"));
                site.setPriceClick(rs.getDouble("PRICE_CLICK"));
                site.setIsMySite(rs.getInt("IS_MYSITE"));
                site.setCreateBy(rs.getInt("CREATE_BY"));
                site.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                site.setUpdateBy(rs.getInt("UPDATE_BY"));
                site.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                site.setStatus(rs.getInt("STATUS"));
                all.add(site);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public static ArrayList<SiteManager> getSiteByCat(int gid) {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM site_manager WHERE G_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, gid);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SiteManager site = new SiteManager();
                site.setId(rs.getInt("SITE_ID"));
                site.setGid(rs.getInt("G_ID"));
                site.setIconPath(rs.getString("ICON_PATH"));
                site.setDomain(rs.getString("DOMAIN"));
                site.setPriceClick(rs.getDouble("PRICE_CLICK"));
                site.setIsMySite(rs.getInt("IS_MYSITE"));
                site.setCreateBy(rs.getInt("CREATE_BY"));
                site.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                site.setUpdateBy(rs.getInt("UPDATE_BY"));
                site.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                site.setStatus(rs.getInt("STATUS"));
                all.add(site);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public boolean moveSite(int catid, int[] allid) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DBPool.getConnection();
            String sql = "UPDATE site_manager set G_ID = ?, UPDATE_DATE = now()  where 1=0 ";
            for (int i = 0; i < allid.length; i++) {
                sql += " or SITE_ID = ?";
            }
            pstm = conn.prepareStatement(sql);
            int j = 1;
            pstm.setLong(j++, catid);
            for (int k = 0; k < allid.length; k++) {
                pstm.setLong(j++, allid[k]);
            }
            int result = pstm.executeUpdate();
            if (result > 0) {
                flag = true;
            }
            reloadCache();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return flag;

    }

    public ArrayList<SiteManager> getAllSite(int currentPage, int gid, String key, int status) {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM site_manager WHERE 1=1 ";
        if (gid != 0) {
            sql += " AND G_ID = ? ";
        }
        if (!Tool.checkNull(key)) {
            sql += " AND DOMAIN like ?";
        }
        if (status != STATUS.SHOW_ALL.value) {
            sql += " AND STATUS = ?";
        }
        sql += " ORDER BY SITE_ID DESC LIMIT ?,? ";
        int start = (currentPage - 1) * Constants.ROW_PER_PAGE;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (gid != 0) {
                pstm.setInt(i++, gid);
            }
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
            }
            if (status != STATUS.SHOW_ALL.value) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, Constants.ROW_PER_PAGE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SiteManager site = new SiteManager();
                site.setId(rs.getInt("SITE_ID"));
                site.setGid(rs.getInt("G_ID"));
                site.setDomain(rs.getString("DOMAIN"));
                site.setIconPath(rs.getString("ICON_PATH"));
                site.setPriceClick(rs.getDouble("PRICE_CLICK"));
                site.setIsMySite(rs.getInt("IS_MYSITE"));
                site.setCreateBy(rs.getInt("CREATE_BY"));
                site.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                site.setUpdateBy(rs.getInt("UPDATE_BY"));
                site.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                site.setStatus(rs.getInt("STATUS"));
                all.add(site);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int countAll(String key, int gid, int status) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM site_manager WHERE 1=1";
        if (gid != 0) {
            sql += " AND G_ID = ? ";
        }
        if (!Tool.checkNull(key)) {
            sql += " AND DOMAIN like ?";
        }
        if (status != STATUS.SHOW_ALL.value) {
            sql += " AND STATUS = ?";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (gid != 0) {
                pstm.setInt(i++, gid);
            }
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
            }
            if (status != STATUS.SHOW_ALL.value) {
                pstm.setInt(i++, status);
            }
            rs = pstm.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return count;
    }

    public SiteManager getByID(int id) {
        SiteManager site = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM site_manager WHERE SITE_ID = ? ";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                site = new SiteManager();
                site.setId(rs.getInt("SITE_ID"));
                site.setGid(rs.getInt("G_ID"));
                site.setIconPath(rs.getString("ICON_PATH"));
                site.setDomain(rs.getString("DOMAIN"));
                site.setPriceClick(rs.getDouble("PRICE_CLICK"));
                site.setIsMySite(rs.getInt("IS_MYSITE"));
                site.setCreateBy(rs.getInt("CREATE_BY"));
                site.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                site.setUpdateBy(rs.getInt("UPDATE_BY"));
                site.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                site.setStatus(rs.getInt("STATUS"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return site;
    }

    public boolean addNew(SiteManager site, MultipartFile icon) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO site_manager(G_ID,DOMAIN,PRICE_CLICK,IS_MYSITE,CREATE_BY,CREATE_DATE,STATUS)"
                + "                     VALUES( ?  ,?     ,     ?     ,        ?,   ?     ,    NOW()  , ?    )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, site.getGid());
            pstm.setString(i++, site.getDomain());
            pstm.setDouble(i++, site.getPriceClick());
            pstm.setInt(i++, site.getIsMySite());
            pstm.setInt(i++, site.getCreateBy());
            pstm.setInt(i++, site.getStatus());
            if (pstm.executeUpdate() == 1) {
                pstm.close();
                if (icon != null) {
                    pstm = conn.prepareStatement("SELECT @@IDENTITY AS 'Identity';");
                    rs = pstm.executeQuery();
                    if (rs.next()) {
                        int siteID = rs.getInt(1);
                        String fileName = siteID + "." + icon.getExtentsion();
                        String url_real = ListionContext.ROOT_DIR + PATH_ICON + "/" + fileName;
                        FileUtils.resizeWriteImg(icon.getInputStream(), 32, url_real, icon.getExtentsion());
                        pstm.clearParameters();
                        pstm.close();
                        sql = "UPDATE site_manager SET ICON_PATH = ? WHERE SITE_ID = ?";
                        pstm = conn.prepareStatement(sql);
                        pstm.setString(1, fileName);
                        pstm.setLong(2, siteID);
                        pstm.execute();
                    }
                }
                flag = true;
                reloadCache();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean update(SiteManager oneSite, MultipartFile icon) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE  site_manager SET G_ID = ?,ICON_PATH = ?, DOMAIN = ?,PRICE_CLICK = ?,IS_MYSITE = ?,UPDATE_BY= ?,UPDATE_DATE = NOW(),STATUS = ? WHERE SITE_ID = ?";
        try {
            String fileName = "";
            if (icon != null) {
                int siteID = oneSite.getId();
                fileName = siteID + "." + icon.getExtentsion();
                String url_real = ListionContext.ROOT_DIR + PATH_ICON + "/" + fileName;
                Tool.Debug("co anh dai dien ma khong ghi duoc a:" + url_real);
                FileUtils.resizeWriteImg(icon.getInputStream(), 32, url_real, icon.getExtentsion());
            } else {
                fileName = oneSite.getIconPath();
            }
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, oneSite.getGid());
            pstm.setString(i++, fileName);
            pstm.setString(i++, oneSite.getDomain());
            pstm.setDouble(i++, oneSite.getPriceClick());
            pstm.setInt(i++, oneSite.getIsMySite());
            pstm.setInt(i++, oneSite.getUpdateBy());
            pstm.setInt(i++, oneSite.getStatus());
            pstm.setInt(i++, oneSite.getId());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reloadCache();
            } else {
                Tool.Debug("UPDATE KO THANH CONG ???:" + oneSite.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean del404(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE  site_manager SET STATUS=? WHERE SITE_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, SiteManager.STATUS.DELETE.value);
            pstm.setInt(i++, id);
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reloadCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean delever(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM  site_manager WHERE SITE_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            if (pstm.executeUpdate() == 1) {
                // TODO DEL RELATION
                flag = true;
                reloadCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean updateStatus(long id, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean flag = false;
        if (status == 1) {
            status = 0;
        } else {
            status = 1;
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement("UPDATE site_manager set STATUS = ? where ID = ?");
            pstm.setInt(1, status);
            pstm.setLong(2, id);
            int result = pstm.executeUpdate();
            if (result == 1) {
                flag = true;
                reloadCache();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(Tool.getLogMessage(ex));
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return flag;
    }

    //************
    public static enum ISMYSITE {

        ISMYSITE(0, "Trang Hệ Thống"),
        PARTNER_SITE(1, "Trang đối tác");
        private final int value;
        private final String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public static String getName(int searchVal) {
            String _name = "";
            for (ISMYSITE one : ISMYSITE.values()) {
                if (one.getValue() == searchVal) {
                    _name = one.getName();
                    break;
                }
            }
            return _name;
        }

        private ISMYSITE(int value, String name) {
            this.value = value;
            this.name = name;
        }
    }

    public static enum STATUS {

        SHOW_ALL(-2, "Tất cả"),
        BLOCK(0, "QC bị khóa"),
        ACTIVE(1, "QC kích hoạt"),
        DELETE(404, "QC bị xóa");
        private int value;
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
    int id;
    int gid;
    String domain;
    String iconPath;
    double priceClick;
    int isMySite;
    int createBy;
    Timestamp createDate;
    int updateBy;
    Timestamp updateDate;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public double getPriceClick() {
        return priceClick;
    }

    public void setPriceClick(double priceClick) {
        this.priceClick = priceClick;
    }

    public int getIsMySite() {
        return isMySite;
    }

    public void setIsMySite(int isMySite) {
        this.isMySite = isMySite;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getDisplayPath() {
        return PATH_ICON + "/" + iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
