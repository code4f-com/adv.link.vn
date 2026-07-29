/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import gk.adv.linnk.vn.cache.CacheUtil;
import gk.adv.linnk.vn.cache.type.*;
import gk.adv.linnk.vn.cache.type.ServiceInstance;
import gk.adv.linnk.vn.utils.Constants;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class GroupAdv {

    static Logger logger = Logger.getLogger(GroupAdv.class);

    private boolean isRanDomTextImg(int type, int showItem) {
        return (type == TYPE.MANY_HORIZONTAL_RANDOM.value
                || type == TYPE.MANY_VERTICAL_RANDOM.value
                || type == TYPE.MEDIUM_DOC_RANDOM.value
                || type == TYPE.MEDIUM_NGANG_RANDOM.value
                || type == TYPE.IMAGE_HOVER_DOC_RANDOM.value
                || type == TYPE.IMAGE_HOVER_DOC_RANDOM_210.value
                || type == TYPE.IMAGE_HOVER_DOC_RANDOM_124.value
                || type == TYPE.DOC_RAN_MIN_120.value
                || type == TYPE.DOC_RAN_MIN_200.value
                || showItem == 0);
    }

    public boolean isSlide(int type) {
        return (type == TYPE.SLIDE_IMG_TEXT.value
                || type == TYPE.SLIDE_2_FRAME_IMG.value
                || type == TYPE.SLIDE_IMG_TEXT_LAYOUT_2.getValue()
                || type == TYPE.SLIDE_IMG_TEXT_LAYOUT_3.getValue()
                || type == TYPE.SLIDE_3FRAME_LAYOUT_1.getValue()
                || type == TYPE.SLIDE_3FRAME_LAYOUT_2.getValue()
                || type == TYPE.SLIDE_3FRAME_LAYOUT_3.getValue()
                || type == TYPE.SLIDE_2_FRAME_T_A2.getValue()
                || type == TYPE.SLIDE_2_FRAME_A3_T.getValue()
                || type == TYPE.SLIDE_2_FRAME_T_A3.getValue()
                || type == TYPE.SLIDE_FRAME_1IMG_A1.getValue() //--
                || type == TYPE.SLIDE_A1_FRAME_1IMG.getValue() //--
                || type == TYPE.SLIDE_FRAME_1IMG_A2.getValue() //--
                || type == TYPE.SLIDE_A2_FRAME_1IMG.getValue() //--
                || type == TYPE.SLIDE_FRAME_1IMG_A3.getValue() //--
                || type == TYPE.SLIDE_A3_FRAME_1IMG.getValue() //--
                || type == TYPE.SLIDE_2FRAME_1IMG.getValue() //--
                || type == TYPE.SLIDE_2FRAME_IMAGE_HOVER.getValue()
                //--
                || type == TYPE.SLIDE_A1_HOVER.getValue()
                || type == TYPE.SLIDE_HOVER_A1.getValue()
                //-
                || type == TYPE.SLIDE_A2_HOVER.getValue()
                || type == TYPE.SLIDE_HOVER_A2.getValue()
                //-
                || type == TYPE.SLIDE_A3_HOVER.getValue()
                || type == TYPE.SLIDE_HOVER_A3.getValue()
                //--
                || type == TYPE.SLIDE_1IMG_HOVER.getValue()
                || type == TYPE.SLIDE_HOVER_1IMG.getValue() //--
                );

    }

    public GroupAdv getGroupAdsPreviewById(int groupid) {
        GroupAdv group = getByID(groupid);
        if (group != null) {
            Connection conn = null;
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String sql = "SELECT * from group_adv_detail G INNER JOIN advertise A ON G.ADV_ID = a.ADV_ID "
                    + "where G.GROUP_ID = ? AND A.STATUS = 1 AND G.STATUS = 1 LIMIT 5";
            try {
                conn = DBPool.getConnection();
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, groupid);
                rs = pstm.executeQuery();
                ArrayList<Advertise> all = new ArrayList<>();
                Advertise oneAds;
                while (rs.next()) {
                    oneAds = new Advertise();
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
                    all.add(oneAds);
                }
                group.setAds(all);
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            } finally {
                DBPool.freeConn(rs, pstm, conn);
            }
        }
        return group;
    }

    public GroupAdv getGroupAdsByIdFromCache(int groupid) {
        GroupAdv gr_item_return = new GroupAdv();
        String key = "GroupAdv." + groupid;
        GroupAdv groupCache = null;
        try {
            // Cache not = null
            if (CacheUtil.cacheAds2m != null) {
                Element g12hCache = CacheUtil.cacheAds2m.get(key);
//                GroupAdv groupCache = (GroupAdv) MyMemCache.CACHE_ONE_DAY.get(key);
                if (g12hCache == null) {
                    // Khong co Trong Cache lay o DB -- Moi chi la lay Group
                    GroupAdv groupDB = getGroupAdsById(groupid);
                    if (groupDB != null) {
                        // Put vao cache
                        CacheUtil.cacheAds2m.put(new Element(key, groupDB));
                        // Set lai gia tri cua ads Show neu la ran dom
                        if (isRanDomTextImg(groupDB.getType(), groupDB.getShowItem())) {
                            // Clone Group
                            gr_item_return.setGroupID(groupDB.getGroupID());
                            gr_item_return.setName(groupDB.getName());
                            gr_item_return.setDesc(groupDB.getDesc());
                            gr_item_return.setKind(groupDB.getKind());
                            gr_item_return.setType(groupDB.getType());
                            gr_item_return.setMaxItem(groupDB.getMaxItem());
                            gr_item_return.setShowItem(groupDB.getShowItem());
                            gr_item_return.setOnsiteJson(groupDB.getOnsiteJson());
                            gr_item_return.setStatus(groupDB.getStatus());
                            // Ran dom thi moi ran dom luon truoc khi tra ra
                            ArrayList<Advertise> ranItem = ranDomFromCache(groupDB.getAds(), groupDB.getShowItem());
                            gr_item_return.setAds(ranItem);
                        } else {
                            // Neu la Slide thi lay tat roi ra ngoai xu ly sau
                            // Neu khong phai ran dom thi vo mom a
                            gr_item_return.setGroupID(groupDB.getGroupID());
                            gr_item_return.setName(groupDB.getName());
                            gr_item_return.setDesc(groupDB.getDesc());
                            gr_item_return.setKind(groupDB.getKind());
                            gr_item_return.setType(groupDB.getType());
                            gr_item_return.setMaxItem(groupDB.getMaxItem());
                            gr_item_return.setShowItem(groupDB.getShowItem());
                            gr_item_return.setOnsiteJson(groupDB.getOnsiteJson());
                            gr_item_return.setStatus(groupDB.getStatus());
                            gr_item_return.setAds(groupDB.getAds());
                        }
                    } else {
                        logger.warn("Link.vn GET DB NULL [Hoac Or Error] GroupID:" + groupid);
                    }
                } else {
                    // Co cache  => Lay tu Cache               
                    groupCache = (GroupAdv) g12hCache.getObjectValue();
                    // Set lai gia tri cua ads Show
                    if (isRanDomTextImg(groupCache.getType(), groupCache.getShowItem())) {
                        // Clone Group
                        gr_item_return.setGroupID(groupCache.getGroupID());
                        gr_item_return.setName(groupCache.getName());
                        gr_item_return.setDesc(groupCache.getDesc());
                        gr_item_return.setKind(groupCache.getKind());
                        gr_item_return.setType(groupCache.getType());
                        gr_item_return.setMaxItem(groupCache.getMaxItem());
                        gr_item_return.setShowItem(groupCache.getShowItem());
                        gr_item_return.setOnsiteJson(groupCache.getOnsiteJson());
                        gr_item_return.setStatus(groupCache.getStatus());
                        // Ran dom thi moi ran dom
                        ArrayList<Advertise> ranItem = ranDomFromCache(groupCache.getAds(), groupCache.getShowItem());
                        gr_item_return.setAds(ranItem);
                    } else {
                        // Khong Random Lay tat Roi Tinh Sau
                        gr_item_return.setGroupID(groupCache.getGroupID());
                        gr_item_return.setName(groupCache.getName());
                        gr_item_return.setDesc(groupCache.getDesc());
                        gr_item_return.setKind(groupCache.getKind());
                        gr_item_return.setType(groupCache.getType());
                        gr_item_return.setMaxItem(groupCache.getMaxItem());
                        gr_item_return.setShowItem(groupCache.getShowItem());
                        gr_item_return.setOnsiteJson(groupCache.getOnsiteJson());
                        gr_item_return.setStatus(groupCache.getStatus());
                        gr_item_return.setAds(groupCache.getAds());
                    }
                }
            } else {
//                logger.error("[Warring] CacheUtil.cache12h is null => lay o DB ???");
                Tool.Debug("[Warring] CacheUtil.cache12h is null => lay o DB ???");
                GroupAdv groupDB = getGroupAdsById(groupid);
                // Set lai gia tri cua ads Show
                if (isRanDomTextImg(groupDB.getType(), groupDB.getShowItem())) {
                    // Clone Group
                    gr_item_return.setGroupID(groupDB.getGroupID());
                    gr_item_return.setName(groupDB.getName());
                    gr_item_return.setDesc(groupDB.getDesc());
                    gr_item_return.setKind(groupDB.getKind());
                    gr_item_return.setType(groupDB.getType());
                    gr_item_return.setMaxItem(groupDB.getMaxItem());
                    gr_item_return.setShowItem(groupDB.getShowItem());
                    gr_item_return.setOnsiteJson(groupDB.getOnsiteJson());
                    gr_item_return.setStatus(groupDB.getStatus());
                    // Ran dom thi moi ran dom
                    ArrayList<Advertise> ranItem = ranDomFromCache(groupDB.getAds(), groupDB.getShowItem());
                    gr_item_return.setAds(ranItem);
                } else {
                    // Khong Random
                    gr_item_return.setGroupID(groupDB.getGroupID());
                    gr_item_return.setName(groupDB.getName());
                    gr_item_return.setDesc(groupDB.getDesc());
                    gr_item_return.setKind(groupDB.getKind());
                    gr_item_return.setType(groupDB.getType());
                    gr_item_return.setMaxItem(groupDB.getMaxItem());
                    gr_item_return.setShowItem(groupDB.getShowItem());
                    gr_item_return.setOnsiteJson(groupDB.getOnsiteJson());
                    gr_item_return.setStatus(groupDB.getStatus());
                    gr_item_return.setAds(groupDB.getAds());
                }
            }
        } catch (IllegalStateException | CacheException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return gr_item_return;
    }

    public static ArrayList<Advertise> ranDomFromCache(ArrayList<Advertise> source, int showItem) {
        ArrayList<Advertise> result = new ArrayList<>();
        try {
            if (source != null && source.size() > showItem) {
                Collections.shuffle(source);
                for (int i = 0; i < showItem; i++) {
                    result.add(source.get(i));
                }
            } else {
                return source;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }
        return result;
    }

    public GroupAdv getGroupAdsById(int groupid) {
        GroupAdv group = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sqlGroup = "SELECT * FROM group_adv WHERE GROUP_ID = ? AND STATUS = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sqlGroup);
            int i = 1;
            pstm.setInt(i++, groupid);
            pstm.setInt(i++, STATUS.ACTIVE.value);
            rs = pstm.executeQuery();
            if (rs.next()) {
                group = new GroupAdv();
                group.setGroupID(rs.getInt("GROUP_ID"));
                group.setName(rs.getString("NAME"));
                group.setDesc(rs.getString("GROUP_DESC"));
                group.setKind(rs.getInt("KIND"));
                group.setType(rs.getInt("TYPE"));
                group.setMaxItem(rs.getInt("MAX_ITEM"));
                group.setShowItem(rs.getInt("SHOW_ITEM"));
                group.setOnsiteJson(rs.getString("ON_SITE"));
                group.setStatus(rs.getInt("STATUS"));
                //---
                rs.close();
                pstm.clearParameters();
                pstm.close();
                String sql;
                if (isRanDomTextImg(group.getType(), group.getShowItem()) || isSlide(group.getType())) {
                    sql = "SELECT * from group_adv_detail G INNER JOIN advertise A ON G.ADV_ID = A.ADV_ID "
                            + "WHERE G.GROUP_ID = ? AND A.STATUS = 1 AND G.STATUS = 1 AND START_TIME <= NOW() AND END_TIME >= NOW() LIMIT ?";
                    // Lay tat roi ran dom sau
//                    Tool.Debug("Vao lay tat: max item =" + group.getMaxItem());
                } else {
                    sql = "SELECT * from group_adv_detail G INNER JOIN advertise A ON G.ADV_ID = A.ADV_ID "
                            + "WHERE G.GROUP_ID = ? AND A.STATUS = 1 AND G.STATUS = 1 AND START_TIME <= NOW() AND END_TIME >= NOW() LIMIT ?";
                }
                // Lay ra san pham QC
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, groupid);
                if (isRanDomTextImg(group.getType(), group.getShowItem()) || isSlide(group.getType())) {
                    pstm.setInt(2, group.getMaxItem());
                } else {
                    pstm.setInt(2, group.getShowItem());
                }
                rs = pstm.executeQuery();
                ArrayList<Advertise> all = new ArrayList<>();
                Advertise oneAds;
                while (rs.next()) {
                    oneAds = new Advertise();
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
                    all.add(oneAds);
                }
                group.setAds(all);
            } else {
                Tool.Debug("getGroupAdsById : Not get GroupADV");
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return group;
    }

    public static boolean checkExitAds(String adsID, GroupAdv group) {
        boolean flag = false;
        if (group != null) {
            // get List ads Id from Group
            ArrayList<String> listAds = group.getAdsID();
            if (listAds != null) {
                for (String one : listAds) {
                    if (one == null) {
                        continue;
                    }
                    if (one.equalsIgnoreCase(adsID)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    public static ArrayList<String> getAdsIdByGroupID(int groupID) {
        ArrayList<String> adsArrId = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT ADV_ID FROM GROUP_ADV_DETAIL WHERE GROUP_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, groupID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                adsArrId.add(rs.getString("ADV_ID"));
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return adsArrId;
    }

    public ArrayList<GroupAdv> getAllGroup(int currentPage, String key, int status) {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT A.*,(SELECT COUNT(*) FROM group_adv_detail GD WHERE  GD.GROUP_ID = A.GROUP_ID AND GD.STATUS = 1) AS ADS FROM group_adv A WHERE 1=1 ";
        if (!Tool.checkNull(key)) {
            sql += " AND (A.NAME like ? OR A.GROUP_ID like ?)";
        }
        if (status != STATUS.SHOW_ALL.value) {
            sql += " AND A.STATUS = ?";
        }
        sql += " ORDER BY A.GROUP_ID DESC LIMIT ?,? ";
        int start = (currentPage - 1) * Constants.ROW_PER_PAGE;
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            if (status != STATUS.SHOW_ALL.value) {
                pstm.setInt(i++, status);
            }
            pstm.setInt(i++, start);
            pstm.setInt(i++, Constants.ROW_PER_PAGE);
            rs = pstm.executeQuery();
            while (rs.next()) {
                GroupAdv group = new GroupAdv();
                group.setGroupID(rs.getInt("GROUP_ID"));
                group.setName(rs.getString("NAME"));
                group.setDesc(rs.getString("GROUP_DESC"));
                group.setKind(rs.getInt("KIND"));
                group.setType(rs.getInt("TYPE"));
                group.setMaxItem(rs.getInt("MAX_ITEM"));
                group.setShowItem(rs.getInt("SHOW_ITEM"));
                group.setOnsiteJson(rs.getString("ON_SITE"));
                group.setStatus(rs.getInt("STATUS"));
                group.setTotalAds(rs.getInt("ADS"));
                setAdsID();
                all.add(group);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public int countAll(String key, int status) {
        int count = 0;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT count(*) FROM group_adv WHERE 1=1";
        if (!Tool.checkNull(key)) {
            sql += " AND (A.NAME like ? OR A.GROUP_ID like ?)";
        }
        if (status != STATUS.SHOW_ALL.value) {
            sql += " AND STATUS = ?";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
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

    public GroupAdv getByID(int id) {
        GroupAdv group = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM group_adv WHERE GROUP_ID = ? ";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                group = new GroupAdv();
                group.setGroupID(rs.getInt("GROUP_ID"));
                group.setName(rs.getString("NAME"));
                group.setDesc(rs.getString("GROUP_DESC"));
                group.setKind(rs.getInt("KIND"));
                group.setType(rs.getInt("TYPE"));
                group.setMaxItem(rs.getInt("MAX_ITEM"));
                group.setShowItem(rs.getInt("SHOW_ITEM"));
                group.setOnsiteJson(rs.getString("ON_SITE"));
                group.setStatus(rs.getInt("STATUS"));
                group.setAdsID();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return group;
    }

    public boolean addNew(GroupAdv oneGroup) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO group_adv(NAME,GROUP_DESC,KIND,TYPE,MAX_ITEM,SHOW_ITEM,ON_SITE,STATUS)"
                + "                  VALUES(?   , ?        ,  ? ,  ? ,   ?    ,   ?     ,  ?    ,  ?   )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, oneGroup.getName());
            pstm.setString(i++, oneGroup.getDesc());
            pstm.setInt(i++, oneGroup.getKind());
            pstm.setInt(i++, oneGroup.getType());
            pstm.setInt(i++, oneGroup.getMaxItem());
            pstm.setInt(i++, oneGroup.getShowItem());
            pstm.setString(i++, oneGroup.getOnsiteJson());
            pstm.setInt(i++, oneGroup.getStatus());
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

    public boolean update(GroupAdv oneGroup) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE  group_adv SET NAME =?,GROUP_DESC=?,KIND=?,TYPE=?,MAX_ITEM=?,SHOW_ITEM=?,ON_SITE = ?,STATUS=? WHERE GROUP_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, oneGroup.getName());
            pstm.setString(i++, oneGroup.getDesc());
            pstm.setInt(i++, oneGroup.getKind());
            pstm.setInt(i++, oneGroup.getType());
            pstm.setInt(i++, oneGroup.getMaxItem());
            pstm.setInt(i++, oneGroup.getShowItem());
            pstm.setString(i++, oneGroup.getOnsiteJson());
            pstm.setInt(i++, oneGroup.getStatus());
            pstm.setInt(i++, oneGroup.getGroupID());
            if (pstm.executeUpdate() == 1) {
                flag = true;
                reload(oneGroup.getGroupID());
            }
        } catch (SQLException | IllegalStateException | CacheException | NullPointerException e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    private static void reload(int gid) {
        String key = "GroupAdv." + gid;
        Element cacheAds2m = CacheUtil.cacheAds2m.get(key);
        if (cacheAds2m != null) {
            Tool.Debug("Cache Add not null:" + gid);
            CacheUtil.cacheAds2m.removeElement(cacheAds2m);
        }
        //----
        String css_Key = "GroupAdv." + gid + ".css";
        Element elmCss = CacheUtil.cacheCss60m.get(css_Key);
        if (elmCss != null) {
            Tool.Debug("Cache css not null:" + gid);
            CacheUtil.cacheCss60m.removeElement(elmCss);
        }
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
            pstm = conn.prepareStatement("UPDATE group_adv set STATUS = ? where GROUP_ID = ?");
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

    public boolean del404(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "UPDATE  group_adv SET STATUS=? WHERE GROUP_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, GroupAdv.STATUS.DELETE.value);
            pstm.setInt(i++, id);
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

    public boolean delever(int id) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM  group_adv WHERE GROUP_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, id);
            if (pstm.executeUpdate() == 1) {
                // TODO DEL RELATION
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

    public static void regisService() {
        ServiceInstance.register(TYPE.MANY_HORIZONTAL_RANDOM.value, MANY_HORIZONTAL_RANDOM.class);
        ServiceInstance.register(TYPE.MANY_VERTICAL_RANDOM.value, MANY_VERTICAL_RANDOM.class);
        ServiceInstance.register(TYPE.MEDIUM_DOC_RANDOM.value, MEDIUM_DOC_RANDOM.class);
        ServiceInstance.register(TYPE.MEDIUM_NGANG_RANDOM.value, MEDIUM_NGANG_RANDOM.class);
        ServiceInstance.register(TYPE.IMAGE_HOVER_DOC_RANDOM.value, IMAGE_HOVER_DOC_RANDOM.class);
        ServiceInstance.register(TYPE.IMAGE_HOVER_DOC_RANDOM_210.value, IMAGE_HOVER_DOC_RANDOM_210.class);
        ServiceInstance.register(TYPE.IMAGE_HOVER_DOC_RANDOM_124.value, IMAGE_HOVER_DOC_RANDOM_124.class);
        ServiceInstance.register(TYPE.DOC_RAN_MIN_120.value, DOC_RAN_MIN_120.class);
        ServiceInstance.register(TYPE.DOC_RAN_MIN_200.value, DOC_RAN_MIN_200.class);
        ServiceInstance.register(TYPE.SLIDE_IMG_TEXT.value, SLIDE_IMG_TEXT.class);
        ServiceInstance.register(TYPE.SLIDE_2_FRAME_IMG.value, SLIDE_2_FRAME_IMG.class);
        ServiceInstance.register(TYPE.SLIDE_IMG_TEXT_LAYOUT_2.value, SLIDE_IMG_TEXT_LAYOUT_2.class);
        ServiceInstance.register(TYPE.SLIDE_IMG_TEXT_LAYOUT_3.value, SLIDE_IMG_TEXT_LAYOUT_3.class);
        ServiceInstance.register(TYPE.SLIDE_3FRAME_LAYOUT_1.value, SLIDE_3FRAME_LAYOUT_1.class);
        ServiceInstance.register(TYPE.SLIDE_3FRAME_LAYOUT_2.value, SLIDE_3FRAME_LAYOUT_2.class);
        ServiceInstance.register(TYPE.SLIDE_3FRAME_LAYOUT_3.value, SLIDE_3FRAME_LAYOUT_3.class);
        ServiceInstance.register(TYPE.SLIDE_2_FRAME_T_A2.value, SLIDE_2_FRAME_T_A2.class);
        ServiceInstance.register(TYPE.SLIDE_2_FRAME_A3_T.value, SLIDE_2_FRAME_A3_T.class);
        ServiceInstance.register(TYPE.SLIDE_2_FRAME_T_A3.value, SLIDE_2_FRAME_T_A3.class);
        ServiceInstance.register(TYPE.RAN_FRAME_IMG_TEXT.value, RAN_FRAME_IMG_TEXT.class);
        ServiceInstance.register(TYPE.ROTATE_FRAME_IMG_TEXT.value, ROTATE_FRAME_IMG_TEXT.class);
        ServiceInstance.register(TYPE.SLIDE_FRAME_IMG_TEXT.value, SLIDE_FRAME_IMG_TEXT.class);
        // New
        ServiceInstance.register(TYPE.SLIDE_FRAME_1IMG_A1.value, SLIDE_FRAME_1IMG_A1.class);
        ServiceInstance.register(TYPE.SLIDE_A1_FRAME_1IMG.value, SLIDE_A1_FRAME_1IMG.class);
        //--
        ServiceInstance.register(TYPE.SLIDE_FRAME_1IMG_A2.value, SLIDE_FRAME_1IMG_A2.class);
        ServiceInstance.register(TYPE.SLIDE_A2_FRAME_1IMG.value, SLIDE_A2_FRAME_1IMG.class);

        //
        ServiceInstance.register(TYPE.SLIDE_FRAME_1IMG_A3.value, SLIDE_FRAME_1IMG_A3.class);
        ServiceInstance.register(TYPE.SLIDE_A3_FRAME_1IMG.value, SLIDE_A3_FRAME_1IMG.class);
        ServiceInstance.register(TYPE.SLIDE_2FRAME_1IMG.value, SLIDE_2FRAME_1IMG.class);
        ServiceInstance.register(TYPE.SLIDE_2FRAME_IMAGE_HOVER.value, SLIDE_2FRAME_IMAGE_HOVER.class);
        //
        ServiceInstance.register(TYPE.SLIDE_A1_HOVER.value, SLIDE_A1_HOVER.class);
        ServiceInstance.register(TYPE.SLIDE_HOVER_A1.value, SLIDE_HOVER_A1.class);
        //
        ServiceInstance.register(TYPE.SLIDE_HOVER_A2.value, SLIDE_HOVER_A2.class);
        ServiceInstance.register(TYPE.SLIDE_HOVER_A2.value, SLIDE_HOVER_A2.class);
        //
        ServiceInstance.register(TYPE.SLIDE_HOVER_A3.value, SLIDE_HOVER_A3.class);
        ServiceInstance.register(TYPE.SLIDE_A3_HOVER.value, SLIDE_A3_HOVER.class);
        //
        ServiceInstance.register(TYPE.SLIDE_1IMG_HOVER.value, SLIDE_1IMG_HOVER.class);
        ServiceInstance.register(TYPE.SLIDE_HOVER_1IMG.value, SLIDE_HOVER_1IMG.class);
    }

    public static enum TYPE {
//        ONLY_ONE(1, "Hiển thị chỉ một thành phần"), //  for only one Object adv
//        MANY_HORIZONTAL(2, "Hiển thị nhiều - chiều ngang - Fix Max"), //  for height dimension, multi Object adv fixed max

//        MANY_VERTICAL(3, "Hiển thị nhiều - chiều dọc - Fix Max"), //  for width dimension, multi Object adv fixed max
        MANY_HORIZONTAL_RANDOM(4, "Hiển thị - chiều ngang - Ngẫu Nhiên"), //  for height dimension, random Object adv
        MANY_VERTICAL_RANDOM(5, "Hiển thị - chiều dọc - Ngẫu Nhiên(300x600)"), //  for width dimension, random Object adv
        //        MEDIUM_DOC(6, "Rộng 160px nhiều - chiều dọc - Fix Max"),
        MEDIUM_DOC_RANDOM(7, "Rộng 160px - chiều dọc - Ngẫu Nhiên"),
        //        MEDIUM_NGANG(8, "Cao 110px nhiều - chiều ngang - Fix Max"),
        MEDIUM_NGANG_RANDOM(9, "Cao 110px - chiều ngang - Ngẫu Nhiên"),
        //-- Hover
        //        IMAGE_HOVER_DOC(10, "Quảng cáo ảnh Hover Dọc Anh:110px - Fix Max"),
        IMAGE_HOVER_DOC_RANDOM(11, "Quảng cáo ảnh Hover Dọc - Ngẫu Nhiên Anh:110px"),
        //--- Hover image 90px min 210
        //        IMAGE_HOVER_DOC_210(12, "Quảng cáo ảnh Hover Dọc Anh:90px - Width: 210px"),
        IMAGE_HOVER_DOC_RANDOM_210(13, "Quảng cáo ảnh Hover Dọc - Ngẫu Nhiên Anh:90px - Width: 210px"),
        //  Hover image 110px min 124
        IMAGE_HOVER_DOC_RANDOM_124(14, "Quảng cáo ảnh Hover Dọc - Ngẫu Nhiên Anh:110px - Width: 124px"),
        //---
        DOC_RAN_MIN_120(15, "Rộng min 120px theo chiều dọc - không có mô tả RANDOM"),
        DOC_RAN_MIN_200(16, "Rộng min 200px theo chiều dọc - có mô tả RANDOM"),
        // SLIDE TYPE 1 FRame Anh - 1 Frame Anh-text
        SLIDE_IMG_TEXT(17, "300x600 - Fix. (1 Frame nhiều ảnh) - (1 Frame Anh+text)"),
        // SLIDE 2 FRAME IMG
        SLIDE_2_FRAME_IMG(18, "210x480 - Fix. 2 Frame Ảnh"),
        SLIDE_IMG_TEXT_LAYOUT_2(19, "300x600 - Fix. (1 Frame 1 Anh) - (1 Frame A.text)"),
        SLIDE_IMG_TEXT_LAYOUT_3(20, "300x600 - Fix. (1 Frame 2 Anh Vuông) - (1 Frame A.text)"),
        SLIDE_3FRAME_LAYOUT_1(21, "300x600 - Fix. (3 Frame Anh)"),
        SLIDE_3FRAME_LAYOUT_2(22, "300x600 - Fix. (Frame Anh A2 - A.Text - A3) (Min 24 SP Ảnh)"),
        SLIDE_3FRAME_LAYOUT_3(24, "300x600 - Fix. (Frame A.Text - A3 - A.Text) (Min 17 SP Ảnh)"),
        // SLIDE 2 FRAME IMG
        SLIDE_2_FRAME_T_A2(25, "300x600 - Fix. 2 Frame A.Text-A2"),
        SLIDE_2_FRAME_A3_T(26, "300x600 - Fix. 2 Frame A3 - A.Text"),
        SLIDE_2_FRAME_T_A3(27, "300x600 - Fix. 2 Frame A.Text-A3"),
        //--
        RAN_FRAME_IMG_TEXT(28, "300x600 - Fix. 2 Frame Text Dọc, Hiển thị tuần tự"),
        ROTATE_FRAME_IMG_TEXT(29, "300x600 - Fix. 2 Frame Text, Hiển thị Xoay vòng"),
        SLIDE_FRAME_IMG_TEXT(30, "300x600 - Fix. 2 Frame Text, Slide"),
        // New 09-07-2014
        SLIDE_FRAME_1IMG_A1(31, "300x600 - (1 Frame 1 Anh) - A1, Slide"),
        SLIDE_A1_FRAME_1IMG(32, "300x600  - A1 - (1 Frame 1 Anh), Slide"),
        //-
        SLIDE_FRAME_1IMG_A2(33, "300x600 - (1 Frame 1 Anh) - A2, Slide"),
        SLIDE_A2_FRAME_1IMG(34, "300x600 - A2 -  (1 Frame 1 Anh), Slide"),
        //-
        SLIDE_FRAME_1IMG_A3(35, "300x600 - (1 Frame 1 Anh) - A3, Slide"),
        SLIDE_A3_FRAME_1IMG(36, "300x600 - A3 -  (1 Frame 1 Anh), Slide"),
        // 2 anh 300x600 slide truot
        SLIDE_2FRAME_1IMG(37, "300x600 - (2 Frame 1 Anh Gif)"),
        SLIDE_2FRAME_IMAGE_HOVER(38, "300x600 - (2 Frame - Ảnh Giá Hover Slide)"),
        SLIDE_A1_HOVER(39, "300x600 - (2Frame Slide - A1 Hover)"),
        SLIDE_HOVER_A1(40, "300x600 - (2Frame Slide - Hover A1)"),
        //--
        SLIDE_HOVER_A2(41, "300x600 - (2Frame Slide - Hover A2)"), //--
        SLIDE_A2_HOVER(42, "300x600 - (2Frame Slide - A2 Hover)"),
        //--
        SLIDE_HOVER_A3(43, "300x600 - (2Frame Slide - Hover A3)"), //--
        SLIDE_A3_HOVER(44, "300x600 - (2Frame Slide - A3 Hover)"), //--
        //--
        SLIDE_1IMG_HOVER(45, "300x600 - (Slide 1Baner -  Hover)"), //--
        SLIDE_HOVER_1IMG(46, "300x600 - (Slide Hover - 1Baner)"), //--
        ;
        //--
        private final int value;
        private final String name;

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        private TYPE(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static String getName(int searVal) {
            String _name = "";
            for (TYPE one : TYPE.values()) {
                if (one.getValue() == searVal) {
                    _name = one.getName();
                    break;
                }
            }
            return _name;
        }
    };

    public static enum STATUS {

        SHOW_ALL(-2, "Tất cả"),
        BLOCK(0, "Nhóm bị khóa"),
        ACTIVE(1, "Nhóm kích hoạt"),
        DELETE(404, "Nhóm bị xóa");
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

    // Kind nay phai giong Kind cua Advertise
    public static enum KIND {

        // Anh va Text
        IMAGE_TEXT(1, "QC Ảnh và Text"),
        // Anh va gia Hover
        IMAGE_PRICE(3, "QC Ảnh và Giá"),
        // Slide 300x600 1 Frame nhiều Anh + 1 Frame Text 
        SLIDE_IMG_TEXT(4, "QC Slide (1 Frame nhiều Ảnh)- (1 Frame Anh+text)"),
        // 300x600 Anh Slide 1 Frame 1 Anh & 1 Frame IMG+ TEXT
        SLIDE_IMG_TEXT_LAYOUT_2(6, "QC Slide (1 Frame 1 Ảnh) - (1 Frame Anh+text)"),
        // Co the co nhieu Frame Anh
        SLIDE_2FRAME(5, "QC Slide 2 Frame Ảnh"),
        //------------
        SLIDE_3FRAME(7, "QC Slide 3 Frame"),
        // Chi co Anh
        IMAGE(0, "QC Ảnh"),
        // Flash one Item
        FLASH(2, "QC Flash");
        //--
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
    //--
    int groupID;
    String name;
    String desc;
    int kind;
    int type;
    int maxItem;
    int showItem;
    String onsiteJson;
    ArrayList<OnSite> onSite;
    int status;
    int totalAds;
    ArrayList<String> adsID;
    ArrayList<Advertise> ads;
    int width;
    int height;

    public int getShowItem() {
        return showItem;
    }

    public void setShowItem(int showItem) {
        this.showItem = showItem;
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

    public ArrayList<Advertise> getAds() {
        return ads;
    }

    public void setAds(ArrayList<Advertise> ads) {
        this.ads = ads;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
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

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOnsiteJson() {
        return onsiteJson;
    }

    public void setOnsiteJson(String onsiteJson) {
        this.onsiteJson = onsiteJson;
    }

    public ArrayList<OnSite> getOnSite() {
        return onSite;
    }

    public void setOnSite(ArrayList<OnSite> onSite) {
        this.onSite = onSite;

    }

    public static class OnSite {

        String domain;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }
    }

    public int getTotalAds() {
        return totalAds;
    }

    public void setTotalAds(int totalAds) {
        this.totalAds = totalAds;
    }

    public int getMaxItem() {
        return maxItem;
    }

    public void setMaxItem(int maxItem) {
        this.maxItem = maxItem;
    }

    public ArrayList<String> getAdsID() {
        return adsID;
    }

    public void setAdsID(ArrayList<String> adsID) {
        this.adsID = adsID;
    }

    public void setAdsID() {
        this.adsID = getAdsIdByGroupID(groupID);
    }
}
