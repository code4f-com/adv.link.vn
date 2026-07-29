/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import gk.adv.linnk.vn.object.Advertise;
import java.util.ArrayList;

/**
 *
 * @author TUANPLA
 */
public interface ServiceType {

    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer);
}
