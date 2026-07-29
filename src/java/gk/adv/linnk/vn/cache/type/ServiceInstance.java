/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import gk.adv.linnk.vn.utils.Tool;
import java.util.HashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class ServiceInstance {

    static Logger logger = Logger.getLogger(ServiceInstance.class);
    public static HashMap<Integer, Class> svMap = new HashMap<>();

    public ServiceInstance() {
        logger.setLevel(Level.ALL);
    }

    public ServiceType getInstance(int serviceType) {
        ServiceType serviceIns = null;
        try {
            Class serviceClass;
            serviceClass = (Class) svMap.get(serviceType);
            if (serviceClass != null) {
                serviceIns = (ServiceType) serviceClass.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(Tool.getLogMessage(e));
            logger.error("Ma dich vu [" + serviceType + "] chua duoc dang ky.");
        }
        return serviceIns;
    }

    public static void register(int serviceType, Class service) {
        svMap.put(serviceType, service);
    }
}
