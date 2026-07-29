/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.thread;

import gk.adv.linnk.vn.object.StatisticShow;
import gk.adv.linnk.vn.utils.Tool;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class LogStaticShowShort implements Runnable {

    static Logger logger = Logger.getLogger(ProcesShowQueue.class);
    StatisticShow data;

    public LogStaticShowShort(StatisticShow _data) {
        this.data = _data;
    }

    @Override
    public void run() {
        try {
            data.CreateOrUpdateShow(data);
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
    }
}
