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
public class SiteGroup {

    static Logger logger = Logger.getLogger(SiteGroup.class);
    public static ArrayList<SiteGroup> CACHE = new ArrayList<>();

    static {
        CACHE = getAll();
    }

    public static void reload() {
        CACHE = getAll();
    }

    public static SiteGroup getCatsById(int cid) {
        SiteGroup one = null;
        for (SiteGroup oneVDcat : CACHE) {
            if (oneVDcat.getId() == cid) {
                one = oneVDcat;
                break;
            }
        }
        return one;
    }

    public static String getCatNamebyId(int cid) {
        String str = null;
        for (SiteGroup oneVDcat : CACHE) {
            if (oneVDcat.getId() == cid) {
                str = oneVDcat.getName();
                break;
            }
        }
        return str;
    }

    public boolean add(SiteGroup one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO group_site(PARENTID,NAME,G_DESC,PRICE,CREATE_DATE,CREATE_BY,STATUS)"
                + "   VALUES(?,?,?,?,now(),?,?)";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, one.getParentid());
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getDesc());
            pstm.setDouble(i++, one.getPrice());
            pstm.setInt(i++, one.getCreateBy());
            pstm.setInt(i++, one.getStatus());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean edit(SiteGroup one) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE group_site set PARENTID=?,NAME = ?,G_DESC = ? ,PRICE = ?,UPDATE_DATE = now(),UPDATE_BY = ?,STATUS = ? WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, one.getParentid());
            pstm.setString(i++, one.getName());
            pstm.setString(i++, one.getDesc());
            pstm.setDouble(i++, one.getPrice());
            pstm.setInt(i++, one.getUpdateBy());
            pstm.setInt(i++, one.getStatus());
            pstm.setInt(i++, one.getId());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
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
        String sql = "UPDATE group_site STATUS = 404 WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
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

    public boolean delEver(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM group_site WHERE ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    private static ArrayList<SiteGroup> getAll() {
        ArrayList<SiteGroup> all = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM GROUP_SITE WHERE STATUS != 404";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                SiteGroup one = new SiteGroup();
                one.setId(rs.getInt("ID"));
                one.setParentid(rs.getInt("PARENTID"));
                one.setName(rs.getString("NAME"));
                one.setDesc(rs.getString("G_DESC"));
                one.setPrice(rs.getDouble("PRICE"));
                one.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                one.setCreateBy(rs.getInt("CREATE_BY"));
                one.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                one.setCreateBy(rs.getInt("UPDATE_BY"));
                one.setStatus(rs.getInt("STATUS"));
                one.setSite();
                all.add(one);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public static boolean checkHaveChild(int cid) {
        boolean exist = false;
        for (SiteGroup oneVDcat : CACHE) {
            if (cid != 0 && oneVDcat.getParentid() == cid) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public static ArrayList<SiteGroup> getChildGroupByParentId(long parentID) {
        ArrayList<SiteGroup> allChildCat = new ArrayList<>();
        for (SiteGroup oneVDcat : CACHE) {
            if (oneVDcat.getParentid() == parentID) {
                allChildCat.add(oneVDcat);
            }
        }
        return allChildCat;
    }

    public static String getTree(String sType) {
        String sXML = "";//<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n
        try {
            int iLevel = 0;
            int iCurrentLevel = 0;
            int iMaxLevel = 1;

            sXML += "<tree id='0'>\n";
            sXML += "<item text='" + sType + "' id='" + sType.toLowerCase() + "' "
                    + "im0='iconWrite1.gif' open='1'  call='1' select='1'>\n";
            ArrayList<SiteGroup> tree = buildTree(CACHE);
            while (!tree.isEmpty()) {
                SiteGroup onecat = tree.remove(0);

                iCurrentLevel = onecat.getLevel();
                if (iCurrentLevel == iLevel) {
                    for (int j = 0; j < iCurrentLevel; j++) {
                        sXML += "   ";
                    }
                    sXML += "</item>\n";
                } else if (iCurrentLevel > iLevel) {
                    iMaxLevel = iCurrentLevel;
                } else if (iCurrentLevel < iLevel) {
                    sXML += "</item>\n";
                    for (int i = iCurrentLevel; i < iLevel; i++) {
                        for (int j = 0; j < iCurrentLevel; j++) {
                            sXML += "   ";
                        }
                        sXML += "</item>\n";
                        iMaxLevel--;
                    }
                }
                if (iCurrentLevel == 1) {
                    for (int i = 1; i < iMaxLevel; i++) {
                        sXML += "</item>\n";
                    }
                    iMaxLevel = 1;
                }
                for (int j = 0; j < iCurrentLevel; j++) {
                    sXML += "   ";
                }
                String stxt = Tool.stringToHTMLString(onecat.getName());
                stxt = stxt.replaceAll("<font size=2>", "");
                stxt = stxt.replaceAll("</font>", "");
                stxt = stxt.replaceAll("'", "");
                stxt = stxt.replaceAll("&nbsp;", "");
//                stxt += "(Pos:" + onecat.getPos() + "-" + (onecat.getStatus() ? "Kích hoạt" : "Khóa") + ",ID:" + onecat.getId() + ")";
                stxt += "(" + (onecat.getStatus() == 1 ? "Kích hoạt" : "Khóa") + ",ID:" + onecat.getId() + ")";
                sXML += "<item text='" + stxt + "' id ='" + onecat.getId() + "'>\n";
                iLevel = iCurrentLevel;
            }
            for (int i = 1; i <= iLevel; i++) {
                sXML += "</item>\n";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            MySqlDBPool.releaseAllWapPool(rs, pstm, conn);
        }
        sXML += "</item>\n</tree>";
        return sXML;
    }

    public static ArrayList<SiteGroup> buildTree(ArrayList<SiteGroup> allCat) {
        ArrayList<SiteGroup> catTree = new ArrayList<>();
        try {
            for (SiteGroup oneParent : allCat) {
                if (oneParent.getParentid() == 0) {
                    // Them vao 1 cha
                    oneParent.setLevel(1);
                    catTree.add(oneParent);
                    // Kiem tra xem co con hay ko?
                    if (checkHaveChild(oneParent.getId())) {
                        addChild2Tree(catTree, oneParent.getId(), 1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catTree;
    }

    private static void addChild2Tree(ArrayList<SiteGroup> catTree, int parentId, int level) {
        ArrayList<SiteGroup> allChild = getChildGroupByParentId(parentId);
        for (SiteGroup oneChild : allChild) {
            int childLevel = level + 1;
            oneChild.setLevel(childLevel);
            catTree.add(oneChild);
            if (checkHaveChild(oneChild.getId())) {
                addChild2Tree(catTree, oneChild.getId(), childLevel);
            }
        }
    }
    //--
    private int id;
    private int parentid;
    private String name;
    private String desc;
    private double price;
    private Timestamp createDate;
    private int createBy;
    private Timestamp updateDate;
    private int updateBy;
    private int status;
    ArrayList<SiteManager> site;
    //
    int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<SiteManager> getSite() {
        return site;
    }

    public void setSite(ArrayList<SiteManager> site) {
        this.site = site;
    }

    public void setSite() {
        this.site = SiteManager.getSiteByCat(id);
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
