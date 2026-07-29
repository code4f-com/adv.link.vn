/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.thread;

import config.ListionContext;
import gk.adv.linnk.vn.object.StatisticClick;
import gk.adv.linnk.vn.utils.Tool;
import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class ProcesClick extends Thread {

    static Logger logger = Logger.getLogger(ProcesClick.class);

    @Override
    public void run() {
        while (ListionContext.isRuning) {
            StatisticClick click;
            try {
                click = (StatisticClick) ListionContext.queueClick.dequeue();
                if (click != null) {
                    Tool.Debug("ProcesClick Name:" + ListionContext.queueClick.getName() + " | Size =" + ListionContext.queueClick.size()+" | "+"Refer Site:"+click.getDomainRequest());
                    
                    click.addNewStatistic(click);
                    sleep(1 * 1000);
                } else {
                    logger.error("DeQueue ProcesClick Null ");
                }
            } catch (Exception e) {
                logger.error(Tool.getLogMessage(e));
            }
        }
    }
}
