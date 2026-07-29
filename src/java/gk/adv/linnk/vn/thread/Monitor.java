/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.thread;

import config.ListionContext;
import gk.adv.linnk.vn.utils.Today;
import gk.adv.linnk.vn.utils.Tool;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class Monitor extends Thread {

    static Logger logger = Logger.getLogger(Monitor.class);

    public Monitor() {
        this.setName("Monitor[" + this.hashCode() + "]");
    }

    @Override
    public void run() {
        while (ListionContext.isRuning) {
            try {
                int ms = Today.getInstance().getMinute();
                if (ms % 2 == 0) {
                    Tool.Debug(" Queue Show:" + ListionContext.queueShow.size());
                }
                Thread.sleep(10 * 1000);
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }

        }
    }
}
