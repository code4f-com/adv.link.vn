/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import gk.adv.linnk.vn.object.MyLocation;
import java.util.HashMap;

/**
 *
 * @author TUANPLA
 */
public class Constants {

    public static HashMap<String, MyLocation> CACHE_LOCAT = new HashMap();
    //-- EMAIL
    static String SMTP_MAIL;
    static String SMTP_PASS;
    static String MAIL_HOST;
    static String FROM_NAME;
    static String MAIL_DEBUG;
    static int SEND_MAIL_FALSE = 1;
    //***********
    public static String PATH_IMAGE = "/adv-res/image";
    public static String PATH_FLASH = "/adv-res/flash";
    //-------Define Exception
    public static int SUCCESS = 0;
    public static int PROCESS_FAIL = 1;
    public static int UNKNOW_EXCEPTION = 99;
    public static int BAD_REQUEST = 400;
    public static int STATUS_DELETE = 404;
    //------------
    public static int ROW_PER_PAGE = 200;
    //---
    public static final int TYPE_CHANGE_STATUS = 1;
    public static final int TYPE_CHANGE_TOP = 2;
    public static final int TYPE_CHANGE_HOT = 3;
    //--
    // Noi cache Anh cua Content va Content
    public static int WIDTH_IAMGE_ADV = 542;
    public static int WIDTH_IAMGE_IN_CONTENT_NEW = 720;
    //------------STATUS CONTENT------------------------------
    static long MAX_FILE_SIZE = 10 * 1024 * 1024;
    //************** SESSIOIN NAME CONFIG ************
    public static int MAX_WIDTH = 110;
    public static String ADD_ADS_TO_GROUP_SESS_NAME = "addGroup";
}
