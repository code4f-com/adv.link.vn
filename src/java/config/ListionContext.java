/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import gk.adv.linnk.vn.cache.CacheUtil;
import gk.adv.linnk.vn.cache.Queue;
import gk.adv.linnk.vn.object.GroupAdv;
import gk.adv.linnk.vn.object.MyLocation;
import gk.adv.linnk.vn.thread.Monitor;
import gk.adv.linnk.vn.thread.ProcesClick;
import gk.adv.linnk.vn.thread.ProcesShowQueue;
import gk.adv.linnk.vn.utils.Constants;
import gk.adv.linnk.vn.utils.DBPool;
import gk.adv.linnk.vn.utils.RSA;
import gk.adv.linnk.vn.utils.Tool;
import java.io.File;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jconfig.Configuration;
import org.jconfig.ConfigurationManager;
import org.jconfig.handler.XMLFileHandler;

/**
 *
 * @author TUANPLA
 */
public class ListionContext implements ServletContextListener {

    static Logger logger = Logger.getLogger(ListionContext.class);
    public static String configDir;
    private static int nThread;
    //---
    public static String ROOT_DIR;
    public static boolean isRuning = false;
    public static Queue queueClick = new Queue("Queue_Click");
    public static Queue queueShow = new Queue("Queue_Show");
    ProcesClick pClick;
    // ----
    Monitor m;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        isRuning = true;
        //--
        ROOT_DIR = sc.getRealPath("/");
        configDir = sc.getInitParameter("config");
        configDir = ROOT_DIR + configDir;

        //Load Log4J
        LoadLog4j(configDir + "/Log4j.properties");
        ResourceMagnager.config = getConfig("config.xml");
        nThread = ResourceMagnager.getInt("nThread", 5, "General");
        //---------------
        Constants.PATH_IMAGE = sc.getRealPath(Constants.PATH_IMAGE);
        Constants.PATH_IMAGE = Constants.PATH_IMAGE.replaceAll("\\\\", "/");
        //------->
        Constants.PATH_FLASH = sc.getRealPath(Constants.PATH_FLASH);
        Constants.PATH_FLASH = Constants.PATH_FLASH.replaceAll("\\\\", "/");
        //------------Generate RSA Key
        RSA.createRSA();
        //------ CREATE CACHE
        CacheUtil.CreteCache(configDir);
        // CACHE IP

        // Regis Service
        GroupAdv.regisService();
        // INIT MEMCACHE 
//        MyMemCache.init();
        // Xu Ly Click
        pClick = new ProcesClick();
        pClick.setPriority(Thread.NORM_PRIORITY);
        pClick.start();
        //-- Xu Ly Show Count
        ProcesShowQueue show = new ProcesShowQueue();
        show.setPriority(Thread.MIN_PRIORITY);
        show.start();
        //--
        MyLocation lct = new MyLocation();
        lct.start();
        m = new Monitor();
        m.setPriority(Thread.MIN_PRIORITY);
        m.start();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void contextDestroyed(ServletContextEvent sce) {
        isRuning = false;
        // Cache
        CacheUtil.endCache();
//        MyMemCache.shutDown();
        try {
            queueClick.ShutDown();
            queueShow.ShutDown();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }
        DBPool.release();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("adv.link.vn -- Deregis Driver:" + driver.toString());
                logger.log(Level.INFO, String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.log(Level.ERROR, String.format("Error deregistering driver %s", driver), e);
            }
        }
        System.out.println("Link.vn contextDestroyed ............");
    }

    public static Configuration getConfig(String aliasPath) {
        Configuration _config = null;
        String configPath = configDir + "/" + aliasPath;
        configPath = configPath.replaceAll("\\\\", "/");
        File file = new File(configPath);
        logger.info(file.getName());
        XMLFileHandler handler = new XMLFileHandler();
        handler.setFile(file);
        try {
            logger.debug("trying to load file config");
            ConfigurationManager cm = ConfigurationManager.getInstance();
            cm.load(handler, "engineConfig");
            logger.info("file config successfully processed");
            logger.info("get Config From ConfigurationManager");
            _config = ConfigurationManager.getConfiguration("engineConfig");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _config;
    }

    private static void LoadLog4j(String log4jPath) {
        if (log4jPath == null) {
            System.err.println("=====> No adv.link.vn- log4j-properties-location init param, so initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        } else {
            File yoMamaYesThisSaysYoMama = new File(log4jPath);
            if (yoMamaYesThisSaysYoMama.exists()) {
                System.out.println("====>Initializing Log4j adv.link.vn:" + log4jPath);
                PropertyConfigurator.configure(log4jPath);
            } else {
                System.err.println("=====> adv.link.vn *** " + log4jPath + " file not found, so initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
    }
}
