/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import static gk.adv.linnk.vn.cache.BuildCache.DOMAIN;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import static gk.adv.linnk.vn.cache.BuildCache.buildURLClick;
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
public class SLIDE_A2_FRAME_1IMG implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitImgAndImgSlide(all);
                ArrayList<Advertise> imgNomal = GroupAdv.ranDomFromCache(_2group.get(0), 1);
                //--
                ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(1), 8);
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                         //----
                        + "                    <div class=\"linkvn_slotOne\" id=\"linkvn_slotOne\">"
                        + "                        <div class=\"midden_300x300 link-border-bottom\">"
                        + "                            <div class=\"item_150x300 link-border-right\">"
                        + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(0), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + gImageSlide.get(0).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                            <div class=\"item_150x300 link-border-left\">"
                        + "                                <div class=\"item_150x150 link-border-bottom\">"
                        + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(1), groupID, refer) + "\">"
                        + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + gImageSlide.get(1).getFilePath() + "\"/>"
                        + "                                    </a>"
                        + "                                </div>"
                        + "                                <div class=\"item_150x150 link-border-top\">"
                        + "                                    <div class=\"item_75x150 link-border-right\">"
                        + "                                        <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(2), groupID, refer) + "\">"
                        + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + gImageSlide.get(2).getFilePath() + "\"/>"
                        + "                                        </a>"
                        + "                                    </div>"
                        + "                                    <div class=\"item_75x150 link-border-left\">"
                        + "                                        <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(3), groupID, refer) + "\">"
                        + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + gImageSlide.get(3).getFilePath() + "\"/>"
                        + "                                        </a>"
                        + "                                    </div>"
                        + "                                </div>"
                        + "                            </div>"
                        + "                        </div>"
                        + "                        <div class=\"midden_300x300 link-border-top\">"
                        + "                            <div class=\"item_150x300 link-border-right\">"
                        + "                                <div class=\"item_150x150 link-border-bottom\">"
                        + "                                    <div class=\"item_75x150 link-border-right\">"
                        + "                                        <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(4), groupID, refer) + "\">"
                        + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + gImageSlide.get(4).getFilePath() + "\"/>"
                        + "                                        </a>"
                        + "                                    </div>"
                        + "                                    <div class=\"item_75x150 link-border-left\">"
                        + "                                        <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(5), groupID, refer) + "\">"
                        + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + gImageSlide.get(5).getFilePath() + "\"/>"
                        + "                                        </a>"
                        + "                                    </div>"
                        + "                                </div>"
                        + "                                <div class=\"item_150x150 link-border-top\">"
                        + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(6), groupID, refer) + "\">"
                        + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + gImageSlide.get(6).getFilePath() + "\"/>"
                        + "                                    </a>"
                        + "                                </div>"
                        + "                            </div>"
                        + "                            <div class=\"item_150x300 link-border-left\">"
                        + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(7), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + gImageSlide.get(7).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                        </div>"
                        + "                    </div>"
                        //----
                        + "                    <div class=\"linkvn_slotTwo\"  id=\"linkvn_slotTwo\">"
                        + "                        <div class=\"linkvn_one_img\">"
                        + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + imgNomal.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                        + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + imgNomal.get(0).getFilePath() + "\"/>"
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
            e.printStackTrace();
        }
        return str;
    }

}
