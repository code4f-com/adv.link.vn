/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import static gk.adv.linnk.vn.cache.BuildCache.DOMAIN;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import gk.adv.linnk.vn.object.Advertise;
import gk.adv.linnk.vn.object.GroupAdv;
import gk.adv.linnk.vn.utils.Md5;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;

/**
 *
 * @author TUANPLA
 */
public class RAN_FRAME_IMG_TEXT implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
                // TODO 
            // Thu Khong Cache
//                String js_key_cache = "GroupAdv." + groupID + ".js";
//                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
//                if (element_js_Cache == null) {
            ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
//                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 9);
            //--
            ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 8);
            str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">\n"
                    + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">\n"
                    + "                    <div id=\"2frame_linkvn_slotOne\">\n"
                    + "                        <div id=\"ssvzone_1" + groupID + "\" style=\"display: block;\">\n"
                    + "                            <div class=\"ssvzContent\">\n"
                    + "                                <div id=\"ssvzone_" + groupID + "_items\"> \n";
            {
                int k = 1;
                for (int tmp = 0; tmp < 4; tmp++) {
                    Advertise oneAds = gImageText.get(tmp);
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
//                            + "                                    <div class=\"ssvzBorder\"><span></span></div> ";
                    if (k != 4) {
                        str += "                                <div class=\"ssvzBorder\"><span></span></div>";
                    }
                    k++;
                }
            }
            str += "                                </div>\n"
                    + "                            </div>  \n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    <div id=\"2frame_linkvn_slotTwo\">\n"
                    + "                        <div id=\"ssvzone_2" + groupID + "\" style=\"display: block;\">\n"
                    + "                            <div class=\"ssvzContent\">\n"
                    + "                                <div id=\"ssvzone_" + groupID + "_items\"> \n";
            {
                int k = 1;
                for (int tmp = 4; tmp < 8; tmp++) {
                    Advertise oneAds = gImageText.get(tmp);
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
//                            + "                                    <div class=\"ssvzBorder\"><span></span></div> ";
                    if (k != 8) {
                        str += "                                <div class=\"ssvzBorder\"><span></span></div>";
                    }
                    k++;
                }
            }
            str += "                                </div>\n"
                    + "                            </div>  \n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "            </div>";
            str = Tool.validStringJs(str);
//                    CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
//                } else {
//                    str = element_js_Cache.getObjectValue().toString();
//                }
        } catch (Exception e) {
        }
        return str;
    }

}
