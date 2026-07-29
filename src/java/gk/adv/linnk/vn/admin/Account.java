package gk.adv.linnk.vn.admin;

import gk.adv.linnk.vn.object.SiteManager;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.Md5;
import gk.adv.linnk.vn.utils.Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

public class Account {

    public static boolean checkSiteRight(SiteManager oneSite, ArrayList<AccSiteManager> accSite) {
        boolean flag = false;
        for (AccSiteManager one : accSite) {
            if (one.getDomain().equalsIgnoreCase(oneSite.getDomain()) && one.getSiteStatus() == 1 && one.getManagerStatus() == 1) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean addNew(Account oneAcc, String[] arrSiteId) {
        boolean ok = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "INSERT INTO ACCOUNTS(USERNAME,PASSWORD,FULL_NAME,DESCRIPTION,ADDRESS,PHONE,EMAIL,CREATE_DATE,CREATE_BY,UPDATE_DATE,UPDATE_BY,USER_TYPE,STATUS) "
                + "VALUES(    ?   ,    ?   ,    ?    ,     ?     ,   ?   ,  ?  ,  ?  ,     NOW() ,    ?    ,     NOW() ,    ?    ,   ?     ,   ?  )";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, oneAcc.getUserName());
            pstm.setString(i++, Md5.encryptMD5(oneAcc.getPassWord()));
            pstm.setString(i++, oneAcc.getFullName());
            pstm.setString(i++, oneAcc.getDescription());
            pstm.setString(i++, oneAcc.getAddress());
            pstm.setString(i++, oneAcc.getPhone());
            pstm.setString(i++, oneAcc.getEmail());
            pstm.setString(i++, oneAcc.getCreateBy());
            pstm.setString(i++, oneAcc.getUpdateBy());
            pstm.setInt(i++, oneAcc.getUserType());
            pstm.setInt(i++, oneAcc.getStatus());
            if (pstm.executeUpdate() == 1) {
                ok = true;
                pstm.clearParameters();
                pstm.close();
                pstm = conn.prepareStatement("SELECT @@IDENTITY AS 'Identity'");
                rs = pstm.executeQuery();
                if (rs.next()) {
                    int accIDNew = rs.getInt(1);
                    if (arrSiteId != null && arrSiteId.length > 0) {
                        new AccSiteManager().mapSiteManager(arrSiteId, accIDNew);
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return ok;
    }

    public boolean update(Account accUpdate) {
        boolean flag = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "UPDATE ACCOUNTS SET USERNAME = ?,";
        if (!Tool.checkNull(accUpdate.getPassWord())) {
            sql += "PASSWORD = ?,";
        }
        sql += "FULL_NAME = ?,DESCRIPTION = ?,ADDRESS = ?,PHONE = ?,EMAIL = ?,UPDATE_DATE = NOW(),UPDATE_BY = ?,USER_TYPE = ?,STATUS=? where ACC_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setString(i++, accUpdate.getUserName());
            if (!Tool.checkNull(accUpdate.getPassWord())) {
                pstm.setString(i++, Md5.encryptMD5(accUpdate.getPassWord()));
            }
            pstm.setString(i++, accUpdate.getFullName());
            pstm.setString(i++, accUpdate.getDescription());
            pstm.setString(i++, accUpdate.getAddress());
            pstm.setString(i++, accUpdate.getPhone());
            pstm.setString(i++, accUpdate.getEmail());
            pstm.setString(i++, accUpdate.getUpdateBy());
            pstm.setInt(i++, accUpdate.getUserType());
            pstm.setInt(i++, accUpdate.getStatus());
            pstm.setInt(i++, accUpdate.getAccID());
            if (pstm.executeUpdate() == 1) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return flag;
    }

    public boolean updatePass(int id, String newpass) {
        boolean ok = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "update ACCOUNTS set PASSWORD = ? WHERE ACC_ID = ?";
        newpass = Md5.encryptMD5(newpass);
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, newpass);
            pstm.setInt(2, id);
            if (pstm.executeUpdate() == 1) {
                ok = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return ok;
    }

    public boolean delete(int accID) {
        boolean ok = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        String sql = "DELETE FROM  ACCOUNTS WHERE ACC_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, accID);
            if (pstm.executeUpdate() == 1) {
                ok = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(null, pstm, conn);
        }
        return ok;
    }

    public static ArrayList listAll(int type, String key) {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNTS WHERE USER_TYPE = ? ";
        if (!Tool.checkNull(key)) {
            sql += " AND( USERNAME like ? or FULL_NAME like ?) ";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            pstm.setInt(i++, type);
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccID(rs.getInt("ACC_ID"));
                acc.setUserName(rs.getString("USERNAME"));
                acc.setPassWord(rs.getString("PASSWORD"));
                acc.setFullName(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                acc.setCreateBy(rs.getString("CREATE_BY"));
                acc.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdateBy(rs.getString("UPDATE_BY"));
                acc.setUserType(rs.getInt("USER_TYPE"));
                acc.setStatus(rs.getInt("STATUS"));
                all.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public static ArrayList listAllUser(String key) {
        ArrayList all = new ArrayList();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNTS WHERE USER_TYPE != 1 ";
        if (!Tool.checkNull(key)) {
            sql += " AND( USERNAME like ? or FULL_NAME like ?) ";
        }
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            int i = 1;
            if (!Tool.checkNull(key)) {
                pstm.setString(i++, "%" + key + "%");
                pstm.setString(i++, "%" + key + "%");
            }
            rs = pstm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccID(rs.getInt("ACC_ID"));
                acc.setUserName(rs.getString("USERNAME"));
                acc.setPassWord(rs.getString("PASSWORD"));
                acc.setFullName(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                acc.setCreateBy(rs.getString("CREATE_BY"));
                acc.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdateBy(rs.getString("UPDATE_BY"));
                acc.setUserType(rs.getInt("USER_TYPE"));
                acc.setStatus(rs.getInt("STATUS"));
                all.add(acc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return all;
    }

    public Account checkLogin(String userName, String password) {
        Account acc = null;
        String pass = Md5.encryptMD5(password);
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNTS  WHERE upper(USERNAME) = upper(?) AND PASSWORD = ? AND STATUS = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, userName);
            pstm.setString(2, pass);
            pstm.setInt(3, STATUS.ACTIVE.val);
            rs = pstm.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setAccID(rs.getInt("ACC_ID"));
                acc.setUserName(rs.getString("USERNAME"));
                acc.setPassWord(rs.getString("PASSWORD"));
                acc.setFullName(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                acc.setCreateBy(rs.getString("CREATE_BY"));
                acc.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdateBy(rs.getString("UPDATE_BY"));
                acc.setUserType(rs.getInt("USER_TYPE"));
                acc.setStatus(rs.getInt("STATUS"));
                acc.setPermission();
                acc.setSiteManager();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return acc;
    }

    public boolean checkRight(String resource, int per) {
        boolean right = false;
        // Lay ra modul dang truy xuat
        int moduleId = Modules.getModuleID(resource);
        for (Permission one : permission) {
            // Duyet qua tat ca cac Quyen cua nguoi dung
            if (one.getModelId() == moduleId) {
                // Lay duoc module khop
                if (one.isSpecial()) {
                    return one.isSpecial();
                } else if (per == Permission.PER.VIEW.val) {
                    return one.isView();
                } else if (per == Permission.PER.ADD.val) {
                    return one.isAdd();
                } else if (per == Permission.PER.EDIT.val) {
                    return one.isEdit();
                } else if (per == Permission.PER.DEL.val) {
                    return one.isDel();
                }
                break;
            }
        }
        return right;
    }

    public boolean checkView(HttpServletRequest request) {
        boolean right = false;
        String resource = request.getParameter("module");
        // Lay ra modul dang truy xuat
        int moduleId = Modules.getModuleID(resource);
        for (Permission one : permission) {
            // Duyet qua tat ca cac Quyen cua nguoi dung
            if (one.getModelId() == moduleId) {
                // Lay duoc module khop
                if (one.isSpecial()) {
                    return one.isSpecial();
                } else {
                    return one.isView();
                }
            }
        }
        return right;
    }

    public boolean checkAdd(HttpServletRequest request) {
        boolean right = false;
        String resource = request.getParameter("module");
        // Lay ra modul dang truy xuat
        int moduleId = Modules.getModuleID(resource);
        for (Permission one : permission) {
            // Duyet qua tat ca cac Quyen cua nguoi dung
            if (one.getModelId() == moduleId) {
                // Lay duoc module khop
                if (one.isSpecial()) {
                    return one.isSpecial();
                } else {
                    return one.isAdd();
                }
            }
        }
        return right;
    }

    public boolean checkEdit(HttpServletRequest request) {
        boolean right = false;
        String resource = request.getParameter("module");
        // Lay ra modul dang truy xuat
        int moduleId = Modules.getModuleID(resource);
        for (Permission one : permission) {
            // Duyet qua tat ca cac Quyen cua nguoi dung
            if (one.getModelId() == moduleId) {
                // Lay duoc module khop
                if (one.isSpecial()) {
                    return one.isSpecial();
                } else {
                    return one.isEdit();
                }
            }
        }
        return right;
    }

    public boolean checkDel(HttpServletRequest request) {
        boolean right = false;
        String resource = request.getParameter("module");
        // Lay ra modul dang truy xuat
        int moduleId = Modules.getModuleID(resource);
        for (Permission one : permission) {
            // Duyet qua tat ca cac Quyen cua nguoi dung
            if (one.getModelId() == moduleId) {
                // Lay duoc module khop
                if (one.isSpecial()) {
                    return one.isSpecial();
                } else {
                    return one.isDel();
                }
            }
        }
        return right;
    }

    public Account getByID(int accID) {
        Account acc = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM ACCOUNTS  WHERE ACC_ID = ?";
        try {
            conn = DBPool.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, accID);
            rs = pstm.executeQuery();
            if (rs.next()) {
                acc = new Account();
                acc.setAccID(rs.getInt("ACC_ID"));
                acc.setUserName(rs.getString("USERNAME"));
                acc.setPassWord(rs.getString("PASSWORD"));
                acc.setFullName(rs.getString("FULL_NAME"));
                acc.setDescription(rs.getString("DESCRIPTION"));
                acc.setAddress(rs.getString("ADDRESS"));
                acc.setPhone(rs.getString("PHONE"));
                acc.setEmail(rs.getString("EMAIL"));
                acc.setCreateDate(rs.getTimestamp("CREATE_DATE"));
                acc.setCreateBy(rs.getString("CREATE_BY"));
                acc.setUpdateDate(rs.getTimestamp("UPDATE_DATE"));
                acc.setUpdateBy(rs.getString("UPDATE_BY"));
                acc.setUserType(rs.getInt("USER_TYPE"));
                acc.setStatus(rs.getInt("STATUS"));
                acc.setSiteManager();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBPool.freeConn(rs, pstm, conn);
        }
        return acc;
    }
    public Logger logger = Logger.getLogger(Account.class);
    private int accID;
    private String userName;
    private String passWord;
    private String fullName;
    private String description;
    private String address;
    private String phone;
    private String email;
    private Timestamp createDate;
    private String createBy;
    private Timestamp updateDate;
    private String updateBy;
    private int userType;
    private int status;
    private ArrayList<Permission> permission;
    private ArrayList<AccSiteManager> siteManager;

    public static enum STATUS {

        ACTIVE(1),
        LOCK(0);
        public int val;

        private STATUS(int val) {
            this.val = val;
        }
    }

    public static enum TYPE {

        USER(0, "Quyền người dùng"), // Create Ads - Manager allow Createby Id                  USER
        ADMIN(1, "Quyền quản trị"), // ADMIN
        NOROLE(2, "Không có quyền"),
        MANAGER_ADS(3, "CP đặt quảng cáo"); // Quan ly Ads - by domain              USER
//        MANAGER_ALL(4, "User Quản lý QC & TK Domain");                     // Quan ly Ads - by domain              USER
        public int val;
        public String name;

        private TYPE(int val, String name) {
            this.val = val;
            this.name = name;
        }
    }

    public static String getTypeName(int type) {
        String name = "Ko có quyền";
        if (type == TYPE.USER.val) {
            name = TYPE.USER.name;
        }
        if (type == TYPE.ADMIN.val) {
            name = TYPE.ADMIN.name;
        }
        if (type == TYPE.NOROLE.val) {
            name = TYPE.NOROLE.name;
        }
        if (type == TYPE.MANAGER_ADS.val) {
            name = TYPE.MANAGER_ADS.name;
        }
        return name;
    }

    public ArrayList<AccSiteManager> getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(ArrayList<AccSiteManager> siteManager) {
        this.siteManager = siteManager;
    }

    public void setSiteManager() {
        this.siteManager = AccSiteManager.getAccSiteManager(accID);
    }

    public ArrayList<Permission> getPermission() {
        return permission;
    }

    public void setPermission(ArrayList<Permission> permission) {
        this.permission = permission;
    }

    public void setPermission() {
        this.permission = new Permission().getRoleAccModule(accID);
    }

    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
