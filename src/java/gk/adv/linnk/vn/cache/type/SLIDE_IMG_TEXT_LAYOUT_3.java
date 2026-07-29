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
public class SLIDE_IMG_TEXT_LAYOUT_3 implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {

                // Lay out 3
            // Chia la 2 nhom
            // 1 nhóm ảnh 2 ảnh Vuông 300x300
            // 1 nhóm ảnh Text
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                Tool.Debug("gImageSlide Size:" + _2group.get(0).size());
                ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 2);
                //--
                ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                        + "                   <div class=\"linkvn_slotOne\">"
                        + "                        <div class=\"linkvn_square_300\" style=\"border-bottom: 1px solid #E91E23\">"
                        + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                        + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(0).getFilePath() + "\"/>"
                        + "                            </a>"
                        + "                        </div>"
                        + "                        <div class=\"linkvn_square_300\">"
                        + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(1).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                        + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getFilePath() + "\"/>"
                        + "                            </a>"
                        + "                        </div>"
                        + "                   </div>"
                        + // Slide 2
                        "                    <div class=\"linkvn_slotTwo\">"
                        + "                        <div id=\"ssvzone_10\" style=\"display: block;\">"
                        + "                            <div class=\"ssvzContent\">"
                        + "                                <div id=\"ssvzone_10_items\"> ";
                int k = 1;
                for (Advertise oneAds : gImageText) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
                    if (k != gImageText.size()) {
                        str += "                                <div class=\"ssvzBorder\"><span></span></div>";
                    }
                    k++;
                }
                str += "                                </div>"
                        + "                            </div>  "
                        + "                        </div>"
                        + "                    </div>"
                        + "                </div>"
                        + "            </div>";
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
