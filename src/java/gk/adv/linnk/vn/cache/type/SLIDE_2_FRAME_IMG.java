/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import static gk.adv.linnk.vn.cache.BuildCache.DOMAIN;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import static gk.adv.linnk.vn.cache.BuildCache.buildText;
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
public class SLIDE_2_FRAME_IMG implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
                // Slide 2 Fram Image
            // 210x480 tren 2 sao
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitImg_Slide(all);
                ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 17);
                //--
                ArrayList<Advertise> gImageLogo = _2group.get(1);
                if (gImageSlide != null && gImageSlide.size() > 12) {
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                            + "                    <div class=\"linkvn_slotOne\">"
                            + "                        <div id=\"linkvn_zone_horizontal_top\">"
                            + "                            <div class=\"item70x70 link-border-right\"><a class=\"img70x70\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x70" + gImageSlide.get(0).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"item70x70 link-border-left link-border-right\"><a class=\"img70x70\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(1).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x70" + gImageSlide.get(1).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"item70x70 link-border-left\"><a class=\"img70x70\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(2).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x70" + gImageSlide.get(2).getFilePath() + "\"></a></div>"
                            + "                        </div>"
                            + "                        <div id=\"linkvn_zone_square_210\">"
                            + "                            <div class=\"\"><div class=\"price_item_210 price_hot\">" + buildText(gImageSlide.get(9).getPriceSell()) + "</div><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(3).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/210x210" + gImageSlide.get(3).getFilePath() + "\"></a></div>"
                            + "                        </div>"
                            + "                        <div id=\"linkvn_zone_vertical_mti_item_140\">"
                            + "                            <div class=\"img70x140 link-border-right\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(4).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x140/" + gImageSlide.get(4).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"img70x140 link-border-right link-border-left\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(5).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x140" + gImageSlide.get(5).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"img70x140 link-border-left\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(6).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x140" + gImageSlide.get(6).getFilePath() + "\"></a></div>"
                            + "                        </div>";
                    if (gImageLogo != null && gImageLogo.size() == 1) {
                        // co 1 logo thi ca 2 thang dung chung
                        str += "                        <div id=\"linkvn_logo_mebe\"  class=\"link-border-top\">"
                                + "                            <div><a href=\"" + (gImageLogo.get(0).getDestinationUrl()) + "\"><img height=\"58\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageLogo.get(0).getFilePath() + "\"></a></div>"
                                + "                        </div>"
                                + "                    </div>"
                                + "                    <div class=\"linkvn_slotTwo\">"
                                + "                        <div id=\"linkvn_logo_mebe\" class=\"link-border-bottom\">"
                                + "                            <div><a href=\"" + (gImageLogo.get(0).getDestinationUrl()) + "\"><img height=\"58\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageLogo.get(0).getFilePath() + "\"></a></div>"
                                + "                        </div>";
                        //
                    } else {
                        str += "                        <div id=\"linkvn_logo_mebe\"  class=\"link-border-top\">"
                                + "                            <div><a href=\"" + (gImageLogo.get(0).getDestinationUrl()) + "\"><img height=\"58\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageLogo.get(0).getFilePath() + "\"></a></div>"
                                + "                        </div>"
                                + "                    </div>"
                                + "                    <div class=\"linkvn_slotTwo\">"
                                + "                        <div id=\"linkvn_logo_mebe\" class=\"link-border-bottom\">"
                                + "                            <div><a href=\"" + (gImageLogo.get(1).getDestinationUrl()) + "\"><img height=\"58\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageLogo.get(1).getFilePath() + "\"></a></div>"
                                + "                        </div>";
                        //
                    }
                    str += "                        <div id=\"linkvn_zone_vertical_mti_item_140\" class=\"link-border-top\">"
                            + "                            <div class=\"img140x140 link-border-right\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(7).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img width=\"140\" src=\"" + URL_IMAGE + "/adv-res/image/210x210" + gImageSlide.get(7).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"img70x140 link-border-left\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(8).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x140" + gImageSlide.get(8).getFilePath() + "\"></a></div>"
                            + "                        </div>"
                            + "                        <div id=\"linkvn_zone_vertical_210x280\" class=\"link-border-top\">"
                            + "                            <div class=\"zone_left_70\">"
                            + "                                <div class=\"item70x70 link-border-bottom\"><a class=\"img70x70\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(9).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x70" + gImageSlide.get(9).getFilePath() + "\"></a></div>"
                            + "                                <div class=\"img70x140 link-border-top link-border-bottom\"><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(10).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x140" + gImageSlide.get(10).getFilePath() + "\"></a></div>"
                            + "                                <div class=\"item70x70 link-border-top\"><a class=\"img70x70\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(11).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/70x70" + gImageSlide.get(11).getFilePath() + "\"></a></div>"
                            + "                            </div>"
                            + "                            <div class=\"zone_right_140\">"
                            + "                                <div><a href=\"hot.vn\"><div class=\"price_item_140 price_sale\">" + buildText(gImageSlide.get(12).getPriceSell()) + "</div><a href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(12).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" ><img src=\"" + URL_IMAGE + "/adv-res/image/140x280" + gImageSlide.get(12).getFilePath() + "\"></a></div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            + "                </div>"
                            + "            </div>";
                    str = Tool.validStringJs(str);
                } else {
                    // Khong du San Pham de hien thi
                }
                CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
            } else {
                str = element_js_Cache.getObjectValue().toString();
            }
        } catch (Exception e) {
        }
        return str;
    }

}
