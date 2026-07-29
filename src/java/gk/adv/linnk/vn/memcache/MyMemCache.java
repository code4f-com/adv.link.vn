/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.memcache;

/**
 *
 * @author TUANPLA
 */
public class MyMemCache {

//    private static final String[] MEMCACHE_SERVER = {"127.0.0.1:11211"};
//
//    private static final String NAME_CACHE_2_MINUTE = "NAME_CACHE_2_MINUTE";
//    private static final String NAME_CACHE_ONE_DAY = "NAME_CACHE_ONE_DAY";
//
//    public static MemcachedClient CACHE_2_MINUTE = null;
//    public static MemcachedClient CACHE_ONE_DAY = null;
//
//    public static void init() {
//        try {
//            CACHE_2_MINUTE = createMenCache(NAME_CACHE_2_MINUTE, 120);
//            CACHE_ONE_DAY = createMenCache(NAME_CACHE_ONE_DAY, 24 * 60 * 60);
//            System.out.println("-----------INIT MEMCACHE OK --------------");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void shutDown() {
//        CACHE_2_MINUTE.flushAll();
//        CACHE_ONE_DAY.flushAll();
//    }
//
//    public static MemcachedClient createMenCache(String name, int timeout) throws Exception {
//        String[] servers = {"127.0.0.1:11211"};
//        SockIOPool pool = SockIOPool.getInstance(name);
//        pool.setServers(servers);
//        pool.setFailover(true);
//        pool.setInitConn(10);
//        pool.setMinConn(5);
//        pool.setMaxConn(250);
//        pool.setMaintSleep(60);
//        pool.setNagle(false);
//        pool.setSocketTO(3000);
//        pool.setAliveCheck(true);
//        pool.initialize();
//        //Get the Memcached Client from SockIOPool named Test1
//        MemcachedClient mcc = new MemcachedClient(name);
//        return mcc;
//    }

}
