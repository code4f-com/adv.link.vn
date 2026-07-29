/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import gk.adv.linnk.vn.cache.BuildCache;
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
public class SLIDE_IMG_TEXT implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
            // Cache Test
            String js_key_vertical = "GroupAdv." + groupID + ".js";
            Element element_js_vertical = CacheUtil.cacheAds2m.get(js_key_vertical);
            if (element_js_vertical == null) {
                // Layout 1
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    // Chia la 2 nhom
                // 1 nhóm ảnh nhieu anh
                // 1 nhóm ảnh Text
                ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 10);
                //--
                ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide" + groupID + "Holder\" style=\"margin-left: 0px\">"
                        + "                    <div class=\"linkvn_slotOne\">"
                        + "                        <div id=\"linkvn_zone_left\">"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(0).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(0).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(0).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(1).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(1).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(1).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_200\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(2).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(2).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(2).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img  width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(2).getimg100x200() + "\"></a></div>"
                        + "                        </div>"
                        + "                        <div id=\"linkvn_zone_center\">"
                        + "                            <div class=\"img_item_100 border_bottom\"><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(3).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(3).getFilePath() + "\"></a></div>"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(4).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(4).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(4).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(4).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(5).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(5).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(5).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(5).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_100\"><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(6).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(6).getFilePath() + "\"></a></div>"
                        + "                        </div>"
                        + "                        <div id=\"linkvn_zone_right\">"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(7).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(7).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(7).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(7).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(8).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(8).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(8).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(8).getimg100x200() + "\"></a></div>"
                        + "                            <div class=\"img_item_200\"><div class=\"price_item_200 " + BuildCache.buildCss(gImageSlide.get(9).getPriceSell()) + "\">" + BuildCache.buildText(gImageSlide.get(9).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(9).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(9).getimg100x200() + "\"></a></div>"
                        + "                        </div>"
                        + "                    </div>"
                        + // Slide 2 - Anh Text
                        "                    <div class=\"linkvn_slotTwo\">"
                        + "                        <div id=\"ssvzone_" + groupID + "\" style=\"display: block;\">"
                        + "                            <div class=\"ssvzContent\">"
                        + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
                int k = 1;
                for (Advertise oneAds : gImageText) {
//                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
//                            + "                                    <div class=\"ssvzBorder\"><span></span></div> ";
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
                CacheUtil.cacheAds2m.put(new Element(js_key_vertical, str));
            } else {
                // Lay tu Cache
                str = element_js_vertical.getObjectValue().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
