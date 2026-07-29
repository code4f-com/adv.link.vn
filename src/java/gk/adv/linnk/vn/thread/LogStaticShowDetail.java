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
public class LogStaticShowDetail implements Runnable {

    static Logger logger = Logger.getLogger(ProcesShowQueue.class);
    StatisticShow data;

    public LogStaticShowDetail(StatisticShow _data) {
        this.data = _data;
    }

    @Override
    public void run() {
        try {
            data.addNewStatistic_Detail(data);
        } catch (Exception e) {
            logger.error(Tool.getLogMessage(e));
        }
    }
}
