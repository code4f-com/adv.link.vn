/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import static gk.adv.linnk.vn.cache.BuildCache.DOMAIN;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import gk.adv.linnk.vn.cache.CacheUtil;
import gk.adv.linnk.vn.object.Advertise;
import gk.adv.linnk.vn.object.GroupAdv;
import gk.adv.linnk.vn.utils.Md5;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;
import net.sf.ehcache.Element;

/**
 *
 * @author TUANPLA
 */
public class SLIDE_2FRAME_1IMG implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitImgAndImgSlide(all);
                ArrayList<Advertise> imgNomal = GroupAdv.ranDomFromCache(_2group.get(0), 2);
                //--
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                        + "                    <div class=\"linkvn_slotOne\">"
                        + "                        <div class=\"linkvn_one_img\">"
                        + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + imgNomal.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                        + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + imgNomal.get(0).getFilePath() + "\"/>"
                        + "                            </a>"
                        + "                        </div>"
                        + "                    </div>"
                        + "                    <div class=\"linkvn_slotTwo\"  id=\"linkvn_slotTwo\">"
                        + "                        <div class=\"linkvn_one_img\">"
                        + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + imgNomal.get(1).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                        + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + imgNomal.get(1).getFilePath() + "\"/>"
                        + "                            </a>"
                        + "                        </div>"
                        + "                    </div>"
                        + "                </div>"
                        + "</div>";
                str = Tool.validStringJs(str);
                CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
            } else {
                str = element_js_Cache.getObjectValue().toString();
            }
        } catch (Exception e) {
        }
        return str;
    }
}
