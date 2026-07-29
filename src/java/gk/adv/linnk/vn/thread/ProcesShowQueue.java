/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.thread;

import config.ListionContext;
import gk.adv.linnk.vn.object.StatisticShow;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class ProcesShowQueue extends Thread {

    static Logger logger = Logger.getLogger(ProcesShowQueue.class);

    public ProcesShowQueue() {
        this.setName("ProcesShowQueue [" + this.hashCode() + "]");
    }

    @Override
    public void run() {
        StatisticShow staticDao = new StatisticShow();
        while (ListionContext.isRuning) {
            ArrayList<Object> all = null;
            try {
                all = ListionContext.queueShow.dequeue(20);
                if (all != null && !all.isEmpty()) {
                    staticDao.CreateOrUpdateShow(all);
//                    show.addNewStatistic_Detail(show);
                }
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
    }
//    @Override
//    public void run() {
//        while (ListionContext.isRuning) {
//            StatisticShow show = null;
//            try {
//                show = (StatisticShow) ListionContext.queueShow.dequeue();
//                if (show != null) {
//                    show.CreateOrUpdateShow(show);
////                    show.addNewStatistic_Detail(show);
//                }
//            } catch (Exception e) {
//                logger.error(Tool.getLogMessage(e));
//            }
//        }
//    }
}
