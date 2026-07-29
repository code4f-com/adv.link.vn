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
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class MapGroup {

    static Logger logger = Logger.getLogger(MapGroup.class);

    public boolean removeAds(int groupId, int adsId) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM group_adv_detail WHERE GROUP_ID = ? AND ADV_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, groupId);
            pstm.setInt(2, adsId);
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

    public boolean removeAllAds(int groupId) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "DELETE FROM group_adv_detail WHERE GROUP_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, groupId);
            if (pstm.executeUpdate() >= 1) {
                flag = true;
            }
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }

    public boolean mapGroup(MapGroup map) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT IGNORE INTO group_adv_detail(GROUP_ID, ADV_ID)";
        ArrayList<String> arrID = map.getAdsID();
        if (!arrID.isEmpty()) {
            sql += " VALUES";
            int k = 1;
            for (String one : arrID) {
                sql += "(?,?)";
                if (k != arrID.size()) {
                    sql += ",";
                }
                k++;
            }
        }
        Tool.Debug(sql);
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!arrID.isEmpty()) {
                for (String one : arrID) {
                    pstm.setInt(i++, map.getGroupID());
                    pstm.setInt(i++, Tool.string2Integer(one, 0));
                }
            }
            flag = pstm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return flag;
    }
    int groupID;
    String groupName;
    ArrayList<String> adsID;

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public ArrayList<String> getAdsID() {
        return adsID;
    }

    public void setAdsID(ArrayList<String> adsID) {
        this.adsID = adsID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
