package gk.adv.linnk.vn.cache;

import config.ListionContext;
import gk.adv.linnk.vn.utils.Tool;
import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.log4j.Logger;

/**
 * Utilitary class for Ehcache.
 *
 * @author huseyin
 *
 */
public class CacheUtil {

    static Logger logger = Logger.getLogger(CacheUtil.class);
    private static final Map<String, String> CACHE_CSS = new HashMap<>();
    public static CacheManager cacheMgr = null;
    public static Ehcache myCache20m = null;
    public static Ehcache cacheAds2m = null;
    public static Ehcache cacheCss60m = null;
    public static Ehcache cache12h = null;

    static {
        myCache20m = getCache("myCache20m");
        cacheAds2m = getCache("cacheAds");
        cacheCss60m = getCache("cacheCss60m");
        cache12h = getCache("cache12h");
    }

    public static void endCache() {
        if (cacheMgr != null) {
            cacheMgr.clearAll();
            cacheMgr.shutdown();
        }
    }

    // Phai Reload Neu Can

    public static void cacheCss(String key, String val) {
        CACHE_CSS.put(key, val);
    }

    public static String getCss(String key) {
        return CACHE_CSS.get(key);
    }

    /**
     * Get the cache instance of Ehcache. This method could be synchronized.
     *
     * @param cacheName
     * @return
     */
    public static Ehcache getCache(String cacheName) {
        if (cacheMgr == null) {
            // We could use an environment or a VM variable
            cacheMgr = getCacheMng(ListionContext.configDir);
        }
        Ehcache cache = null;
        if (cacheMgr != null) {
            //cache = cacheMgr.addCacheIfAbsent(name);
            cache = cacheMgr.getEhcache(cacheName);
            //It is possible to override the parameters from ehcache.xml
			/*cache.getCacheConfiguration().setTimeToIdleSeconds(1);
             cache.getCacheConfiguration().setTimeToLiveSeconds(2);
             */
        }
        return cache;
    }

    /**
     * **
     * CreteCache
     *
     * @param configDir
     */
    public static void CreteCache(String configDir) {
        try {
            configDir = configDir.replaceAll("\\\\", "/");
            cacheMgr = CacheManager.create(configDir + "/ehcache.xml");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }
    }

    /**
     *
     * @param configDir
     * @return
     */
    private static CacheManager getCacheMng(String configDir) {
        CacheManager _cacheMgr = null;
        try {
            configDir = configDir.replaceAll("\\\\", "/");
            _cacheMgr = CacheManager.create(configDir + "/ehcache.xml");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(Tool.getLogMessage(e));
        }
        return _cacheMgr;
    }
}
