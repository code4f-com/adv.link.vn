/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache;

import gk.adv.linnk.vn.cache.type.ServiceInstance;
import gk.adv.linnk.vn.object.Advertise;
import gk.adv.linnk.vn.object.GroupAdv;
import gk.adv.linnk.vn.utils.Md5;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;
import java.util.Iterator;
import net.sf.ehcache.Element;

/**
 *
 * @author TUANPLA
 */
public class BuildCache_bak {

    public static String DOMAIN = "http://ads.link.vn";
    public static String URL_IMAGE = "http://ads.link.vn";
    private final ServiceInstance serviceFactory = new ServiceInstance();

    public static String buildCache(ArrayList<Advertise> all, int groupID, int groupType, int width, String refer) {
        String str = "";
        try {
            String urlClick = "";
//            if (groupType == GroupAdv.TYPE.MANY_VERTICAL.getValue()) {
//                str = "<div style=\"width:" + (width > 0 ? width : "300") + "px; height: " + (all.size() * 144 + 22) + "px; margin-bottom:10px\" id=\"ads_linkvn_zone_" + groupID + "_slot" + all.size() + "\">"
//                        + "<div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_linkvn_zone_" + groupID + "\">"
//                        + " <div id=\"ssvzone_" + groupID + "\">"
//                        + "<div class=\"ssvzContent\">"
//                        + " <div class=\"ssvzRight\">"
//                        + "  <div class=\"ssvzMid\">"
//                        + "   <div id=\"ssvzone_" + groupID + "_items\">";
//                int countItem = 1;
//                for (Advertise oneAds : all) {
////                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
//                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
//                    str += "<div id=\"adv_item\" class=\"adv_items\">"
//                            + "<div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\">"
//                            + "<span></span>"
//                            + "</div>"
//                            + "<div class=\"ssvzTitle\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a>"
//                            + "</div><div class=\"itemmc\">"
//                            + "<a title=\"" + Tool.getDomainName(oneAds.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a>"
//                            + "</div><div class=\"ssvzimage\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
//                            + "<img hspace=\"0\" border=\"0\" align=\"left\" vspace=\"0\" alt=\"" + oneAds.getTitle_top() + "\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" style=\"width:90px;height:90px;\"></a> <div class=\"price\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a> </div>"
//                            + "</div>"
//                            + "</div> ";
//                    if (countItem != all.size()) {
//                        str += "<div class=\"ssvzBorder\"><span></span></div>";
//                    }
//                    countItem++;
//                }
//                str += "        </div> "
//                        + "     <div class=\"ssvzclear\"><span></span></div>"
//                        + "   </div>"
//                        + "  </div>"
//                        + "</div>"
//                        + "   </div>"
//                        + "  </div>"
//                        + "</div>";
//                str = Tool.validStringJs(str);
//            } else 
//                
            if (groupType == GroupAdv.TYPE.SLIDE_IMG_TEXT.getValue()) {
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
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(0).getPriceSell()) + "\">" + buildText(gImageSlide.get(0).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(0).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(1).getPriceSell()) + "\">" + buildText(gImageSlide.get(1).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(1).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_200\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(2).getPriceSell()) + "\">" + buildText(gImageSlide.get(2).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(2).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img  width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(2).getimg100x200() + "\"></a></div>"
                            + "                        </div>"
                            + "                        <div id=\"linkvn_zone_center\">"
                            + "                            <div class=\"img_item_100 border_bottom\"><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(3).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(3).getFilePath() + "\"></a></div>"
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(4).getPriceSell()) + "\">" + buildText(gImageSlide.get(4).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(4).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(4).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(5).getPriceSell()) + "\">" + buildText(gImageSlide.get(5).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(5).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(5).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_100\"><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(6).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(6).getFilePath() + "\"></a></div>"
                            + "                        </div>"
                            + "                        <div id=\"linkvn_zone_right\">"
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(7).getPriceSell()) + "\">" + buildText(gImageSlide.get(7).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(7).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(7).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_200 border_bottom\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(8).getPriceSell()) + "\">" + buildText(gImageSlide.get(8).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(8).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(8).getimg100x200() + "\"></a></div>"
                            + "                            <div class=\"img_item_200\"><div class=\"price_item_200 " + buildCss(gImageSlide.get(9).getPriceSell()) + "\">" + buildText(gImageSlide.get(9).getPriceSell()) + "</div><a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(9).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\" title=\"\"><img width=\"98\" alt=\"\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(9).getimg100x200() + "\"></a></div>"
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
            } else if (groupType == GroupAdv.TYPE.SLIDE_IMG_TEXT_LAYOUT_2.getValue()) {
                // Lay out 2
                // Chia la 2 nhom
                // 1 Frame 1 ảnh 300x600
                // 1 nhóm ảnh Text
                // Key js_vertical_fix
                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
//                    Tool.Debug("all size:"+all.size());
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
//                    Tool.Debug("gImageSlide Size:" + _2group.get(0).size());
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 1);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                            + "                   <div class=\"linkvn_slotOne\">"
                            + "                        <div class=\"linkvn_one_img\">"
                            + "                            <a target=\"_blank\" href=\"" + DOMAIN + "/ads_tracker.link?ads_id=" + gImageSlide.get(0).getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer + "\">"
                            + "                                <img src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(0).getFilePath() + "\"/>"
                            + "                            </a>"
                            + "                        </div>"
                            + "                    </div>"
                            + // Slide 2
                            "                    <div class=\"linkvn_slotTwo\">"
                            + "                        <div id=\"ssvzone_10\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_10_items\"> ";
                    int k = 1;
                    for (Advertise oneAds : gImageText) {
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
                    CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
                } else {
                    str = element_js_Cache.getObjectValue().toString();
                }
            } else if (groupType == GroupAdv.TYPE.SLIDE_IMG_TEXT_LAYOUT_3.getValue()) {
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
            } else if (groupType == GroupAdv.TYPE.SLIDE_2_FRAME_IMG.getValue()) {
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

            } else if (groupType == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_1.getValue()) {
                // Slide 3 Fram Image
                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    // Chi lay ra San Pham Anh
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> imageAds = GroupAdv.ranDomFromCache(_2group.get(0), 24);
                    if (imageAds != null && imageAds.size() >= 24) {
                        str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                                + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                                + "                    <div class=\"linkvn_slotOne\">"
                                + "                        <div class=\"top_300x100 link-border-bottom\">"
                                + "                            <div class=\"item_100x100 link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(0), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(0).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_100x100 link-border-left link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(1), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(1).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_100x100 link-border-left\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(2), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(2).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                        </div>"
                                + "                        <div class=\"midden_300x300 link-border-top link-border-bottom\">"
                                + "                            <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(3), groupID, refer) + "\">"
                                + "                                <img class=\"img_item_300x300\" src=\"" + URL_IMAGE + "/adv-res/image/300x300" + imageAds.get(3).getFilePath() + "\"/>"
                                + "                            </a>"
                                + "                        </div>"
                                + "                        <div class=\"bottom_300x200 link-border-top\">"
                                + "                            <div class=\"item_100x200 link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(4), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(4).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_100x200 link-border-left link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(5), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(5).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_100x200 link-border-left\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(6), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(6).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>                            "
                                + "                        </div>"
                                + "                    </div>"
                                + "                    <!--Frame 2-->"
                                + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                                + "                        <div class=\"midden_300x300 link-border-bottom\">"
                                + "                            <div class=\"item_150x300 link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(7), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + imageAds.get(7).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_150x300 link-border-left\">"
                                + "                                <div class=\"item_150x150 link-border-bottom\">"
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(8), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + imageAds.get(8).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                                <div class=\"item_150x150 link-border-top\">"
                                + "                                    <div class=\"item_75x150 link-border-right\">"
                                + "                                        <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(9), groupID, refer) + "\">"
                                + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(9).getFilePath() + "\"/>"
                                + "                                        </a>"
                                + "                                    </div>"
                                + "                                    <div class=\"item_75x150 link-border-left\">"
                                + "                                        <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(10), groupID, refer) + "\">"
                                + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(10).getFilePath() + "\"/>"
                                + "                                        </a>"
                                + "                                    </div>"
                                + "                                </div>"
                                + "                            </div>"
                                + "                        </div>"
                                + "                        <div class=\"midden_300x300 link-border-top\">"
                                + "                            <div class=\"item_150x300 link-border-right\">"
                                + "                                <div class=\"item_150x150 link-border-bottom\">"
                                + "                                    <div class=\"item_75x150 link-border-right\">"
                                + "                                        <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(11), groupID, refer) + "\">"
                                + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(11).getFilePath() + "\"/>"
                                + "                                        </a>"
                                + "                                    </div>"
                                + "                                    <div class=\"item_75x150 link-border-left\">"
                                + "                                        <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(12), groupID, refer) + "\">"
                                + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(12).getFilePath() + "\"/>"
                                + "                                        </a>"
                                + "                                    </div>"
                                + "                                </div>"
                                + "                                <div class=\"item_150x150 link-border-top\">"
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(13), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + imageAds.get(13).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                            </div>"
                                + "                            <div class=\"item_150x300 link-border-left\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(14), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + imageAds.get(14).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                        </div>"
                                + "                    </div>"
                                + "                    <!--Frame 3-->"
                                + "                    <div class=\"linkvn_slotThree\">"
                                + "                        <div class=\"all_300x200 link-border-bottom\">"
                                + "                            <div class=\"item_200x200 link-border-right\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(15), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + imageAds.get(15).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"item_100x200 link-border-left\">"
                                + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(16), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(16).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                                <div class=\"img_item_100x100 link-border-top\"> "
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(17), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(17).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                            </div>"
                                + "                        </div>"
                                + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                                + "                            <div class=\"img_item_100x200 link-border-right\"> "
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(18), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(18).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(19), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(19).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                            <div class=\"img_item_100x200 link-border-left\"> "
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(20), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(20).getFilePath() + "\"/>"
                                + "                                </a>"
                                + "                            </div>"
                                + "                        </div>"
                                + "                        <div class=\"all_300x200 link-border-top\">"
                                + "                            <div class=\"item_100x200 link-border-right\">"
                                + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(21), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(21).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                                <div class=\"img_item_100x100 link-border-top\"> "
                                + "                                    <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(22), groupID, refer) + "\">"
                                + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(22).getFilePath() + "\"/>"
                                + "                                    </a>"
                                + "                                </div>"
                                + "                            </div>"
                                + "                            <div class=\"item_200x200 link-border-left\">"
                                + "                                <a target=\"_blank\" href=\"" + buildURLClick(imageAds.get(23), groupID, refer) + "\">"
                                + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + imageAds.get(23).getFilePath() + "\"/>"
                                + "                                </a>"
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
            } else if (groupType == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_2.getValue()) {
                // Slide 3 Frame Anh2 -A.Text - Anh3
                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 17);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                            + "                    <div class=\"linkvn_slotOne\">"
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
                            //  <!--Frame 2-->"
                            + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
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
                            // <!--Frame 3-->"
                            + "                    <div class=\"linkvn_slotThree\">"
                            + "                        <div class=\"all_300x200 link-border-bottom\">"
                            + "                            <div class=\"item_200x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(8), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(8).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(9), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(9).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(10), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(10).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                            + "                            <div class=\"img_item_100x200 link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(11), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(11).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(12), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(12).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(13), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(13).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(14), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(14).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(15), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(15).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_200x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(16), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(16).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            + "                </div>"
                            + "            </div>";
                    str = Tool.validStringJs(str);
                    CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
                } else {
                    str = element_js_Cache.getObjectValue().toString();
                }
            } else if (groupType == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_3.getValue()) {
                // Slide 3 Frame A.Text - Anh3 - A.Text

                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 9);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 8);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                            + "                    <!--Frame 1-->"
                            + "                    <div class=\"linkvn_slotOne\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
                    int k = 1;
                    for (int i = 0; i < 4; i++) {
                        Advertise oneAds = gImageText.get(i);
                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                        str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
                        if (k != 4) {
                            str += "                                <div class=\"ssvzBorder\"><span></span></div>";
                        }
                        k++;
                    }
                    str += "                                </div>"
                            + "                            </div>  "
                            + "                        </div>"
                            + "                    </div>"
                            // <!--Frame 2-->
                            + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                            + "                        <div class=\"all_300x200 link-border-bottom\">"
                            + "                            <div class=\"item_200x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(0), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(0).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(1), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(2), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(2).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                            + "                            <div class=\"img_item_100x200 link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(3), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(3).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(4), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(4).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(5), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(5).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(6), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(6).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(7), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(7).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_200x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(8), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(8).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            //      <!--Frame 3-->"
                            + "                    <div class=\"linkvn_slotThree\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
                    k = 1;
                    for (int i = 4; i < gImageText.size(); i++) {
                        Advertise oneAds = gImageText.get(i);
                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                        str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
                        if (k != 4) {
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
            } else if (groupType == GroupAdv.TYPE.SLIDE_2_FRAME_T_A2.getValue()) {

                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 8);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                            + "                    <div class=\"linkvn_slotOne\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
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
                            //                      <!--Frame 2-->"
                            + "                    <div class=\"linkvn_slotTwo\"  id=\"linkvn_slotTwo\">"
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
                            + "                    </div>                    "
                            + "                </div>"
                            + "            </div>";
                    str = Tool.validStringJs(str);
                    CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
                } else {
                    str = element_js_Cache.getObjectValue().toString();
                }
            } else if (groupType == GroupAdv.TYPE.SLIDE_2_FRAME_T_A3.getValue()) {

                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 9);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                            + "                    <div class=\"linkvn_slotOne\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
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
                            + "                    <!--Frame 2-->"
                            + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                            + "                        <div class=\"all_300x200 link-border-bottom\">"
                            + "                            <div class=\"item_200x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(0), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(0).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(1), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(2), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(2).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                            + "                            <div class=\"img_item_100x200 link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(3), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(3).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(4), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(4).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(5), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(5).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(6), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(6).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(7), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(7).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_200x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(8), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(8).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            + "                </div>"
                            + "            </div>";
                    str = Tool.validStringJs(str);
                    CacheUtil.cacheAds2m.put(new Element(js_key_cache, str));
                } else {
                    str = element_js_Cache.getObjectValue().toString();
                }
            } else if (groupType == GroupAdv.TYPE.SLIDE_2_FRAME_A3_T.getValue()) {

                String js_key_cache = "GroupAdv." + groupID + ".js";
                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
                if (element_js_Cache == null) {
                    ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 9);
                    //--
                    ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 4);
                    str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                            + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0\">"
                            + "                    <div class=\"linkvn_slotOne\">"
                            + "                        <div class=\"all_300x200 link-border-bottom\">"
                            + "                            <div class=\"item_200x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(0), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(0).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(1), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(1).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(2), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(2).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                            + "                            <div class=\"img_item_100x200 link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(3), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(3).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(4), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(4).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left\"> "
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(5), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + gImageSlide.get(5).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(6), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(6).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(7), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + gImageSlide.get(7).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_200x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + buildURLClick(gImageSlide.get(8), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + gImageSlide.get(8).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            //+ "                    <!--Frame 2-->"
                            + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                            + "                        <div id=\"ssvzone_" + groupID + "_linkZone\" style=\"display: block;\">"
                            + "                            <div class=\"ssvzContent\">"
                            + "                                <div id=\"ssvzone_" + groupID + "_items\"> ";
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

            } else if (groupType == GroupAdv.TYPE.RAN_FRAME_IMG_TEXT.getValue()) {
                // TODO 
                // Thu Khong Cache
//                String js_key_cache = "GroupAdv." + groupID + ".js";
//                Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
//                if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
//                    ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(0), 9);
                //--
//                    Tool.Debug("BACBACBCBCBBC: "+_2group.get(1).size());
                ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 8);
//                    Tool.Debug("---------------- gImageText ___ " + gImageText.size());
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

            } else if (groupType == GroupAdv.TYPE.ROTATE_FRAME_IMG_TEXT.getValue()) {
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
                        + "                    <div id=\"linkvn_slotOne\"  class=\"linkvn_slotOne\">"
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
                        + "                    <div id=\"linkvn_slotTwo\" class=\"linkvn_slotTwo\">"
                        + "                        <div id=\"ssvzone_2" + groupID + "\" style=\"display: block;\">\n"
                        + "                            <div class=\"ssvzContent\">\n"
                        + "                                <div id=\"ssvzone_" + groupID + "_items\"> \n";
                {
                    int k = 1;
                    for (int tmp = 4; tmp < 8; tmp++) {
                        Advertise oneAds = gImageText.get(tmp);
                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                        str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
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

            } else if (groupType == GroupAdv.TYPE.SLIDE_FRAME_IMG_TEXT.getValue()) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitAdsGroup(all);
                //--
                ArrayList<Advertise> gImageText = GroupAdv.ranDomFromCache(_2group.get(1), 8);
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">\n"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">\n"
                        + "                    <div id=\"linkvn_slotOne\"  class=\"linkvn_slotOne\">"
                        + "                        <div id=\"ssvzone_1" + groupID + "\" style=\"display: block;\">\n"
                        + "                            <div class=\"ssvzContent\">\n"
                        + "                                <div id=\"ssvzone_" + groupID + "_items\"> \n";
                {
                    int k = 1;
                    for (int tmp = 0; tmp < 4; tmp++) {
                        Advertise oneAds = gImageText.get(tmp);
                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                        str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
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
                        + "                    <div id=\"linkvn_slotTwo\" class=\"linkvn_slotTwo\">"
                        + "                        <div id=\"ssvzone_2" + groupID + "\" style=\"display: block;\">\n"
                        + "                            <div class=\"ssvzContent\">\n"
                        + "                                <div id=\"ssvzone_" + groupID + "_items\"> \n";
                {
                    int k = 1;
                    for (int tmp = 4; tmp < 8; tmp++) {
                        Advertise oneAds = gImageText.get(tmp);
                        urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                        str += "                                    <div class=\"adv_items\"><div class=\"ssvzTitle\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getTitle_top() + "</a></div><div class=\"itemmc\"><a href=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div><div class=\"ssvzimage\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\"><img hspace=\"0\" vspace=\"0\" border=\"0\" align=\"left\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" alt=\"" + oneAds.getTitle_top() + "\"></a> <div class=\"price\"><a href=\"" + urlClick + "\" target=\"_blank\" title=\"" + oneAds.getTitle_top() + "\">" + oneAds.getDesc() + "</a> </div></div></div> ";
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
            } /*
             *---------
             Not Slide
             Nomal Basic
             *
             */ //            else if (groupType == GroupAdv.TYPE.MANY_HORIZONTAL.getValue()) {
            //                str = "<div id=\"ads_linkvn_zone" + groupID + "\"><div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
            //                        + "<div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_linkvn_zone" + groupID + "_banner87226\">"
            //                        + "<div id=\"ssvzone_" + groupID + "\">"
            //                        + "<div class=\"ssvzContent\">"
            //                        + " <div class=\"ssvzRight\">"
            //                        + "     <div class=\"ssvzMid\"><div id=\"ssvzone_" + groupID + "_items\">";
            //                int k = 1;
            //                for (Advertise oneAds : all) {
            //                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
            //                    str += "<div id=\"adv_item\" class=\"adv_items\"><div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\"><span></span></div>"
            //                            + "<div class=\"ssvzTitle\">"
            //                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a>"
            //                            + "</div><div class=\"itemmc\">"
            //                            + "<a title=\"" + Tool.getDomainName(oneAds.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a>"
            //                            + "</div><div class=\"ssvzimage\">"
            //                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
            //                            + "<img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\">"
            //                            + "</a><div class=\"price\"><a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
            //                            + oneAds.getDesc() + ""
            //                            + "</a></div></div></div>";
            //                    if (k != all.size()) {
            //                        str += "<div class=\"ssvzBorder\"><span></span></div>";
            //                    }
            //                    k++;
            //                }
            //                str += "        </div>"
            //                        + "     <div class=\"ssvzclear\"><span></span></div>"
            //                        + "    </div>"
            //                        + " </div>"
            //                        + "</div>"
            //                        + "</div>"
            //                        + "</div>"
            //                        + "</div>"
            //                        + "</div>";
            //                str = Tool.validStringJs(str);
            //            } 
//            else if (groupType == GroupAdv.TYPE.MANY_VERTICAL_RANDOM.getValue()) {
//                // RANDOM TRONG CACHE NEN KHONG CACHE ELEMENT NAY                
//                str = "<div style=\"width:" + (width > 0 ? width : "300") + "px; height: " + (all.size() * 144 + 22) + "px; margin-bottom:10px\" id=\"ads_linkvn_zone_" + groupID + "_slot" + all.size() + "\">"
//                        + "<div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_linkvn_zone_" + groupID + "\">"
//                        + "<div id=\"ssvzone_" + groupID + "\" style=\"display: block;\">"
//                        + "<div class=\"ssvzContent\">"
//                        + "<div class=\"ssvzRight\"><div class=\"ssvzMid\">"
//                        + "<div id=\"ssvzone_" + groupID + "_items\">";
//                int countItem = 1;
//                for (Advertise oneAds : all) {
//                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
//                    str += " <div id=\"adv_item\" class=\"adv_items\">"
//                            //                            + "<div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\"><span></span></div> "
//                            + "<div class=\"ssvzTitle\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a>"
//                            + "</div><div class=\"itemmc\">"
//                            + "<a title=\"" + Tool.getDomainName(oneAds.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a>"
//                            + "</div><div class=\"ssvzimage\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
//                            + "<img hspace=\"0\" border=\"0\" align=\"left\" vspace=\"0\" alt=\"" + oneAds.getTitle_top() + "\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" style=\"width:90px;height:90px;\"></a> <div class=\"price\">"
//                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a> </div>"
//                            + "</div></div> ";
//                    if (countItem != all.size()) {
//                        str += "<div class=\"ssvzBorder\"><span></span></div>";
//                    }
//                    countItem++;
//                }
//                str += "</div> <div class=\"ssvzclear\"><span></span></div> </div> </div> </div> "
//                        + " </div> </div> </div>";
//                str = Tool.validStringJs(str);
//            } 
            
            else if (groupType == GroupAdv.TYPE.MANY_HORIZONTAL_RANDOM.getValue()) {
                str = "<div id=\"ads_linkvn_zone" + groupID + "\"><div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
                        + "<div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_linkvn_zone" + groupID + "_banner" + (groupID + "" + groupType) + "\">"
                        + "<div id=\"ssvzone_" + groupID + "\">"
                        + "<div class=\"ssvzContent\"><div class=\"ssvzRight\"><div class=\"ssvzMid\"><div id=\"ssvzone_" + groupID + "_items\">";
                int countItem = 1;
                for (Advertise oneAds : all) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += "<div id=\"adv_item\" class=\"adv_items\">"
                            //                            + "<div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\"><span></span></div>"
                            + "<div class=\"ssvzTitle\">"
                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a>"
                            + "</div><div class=\"itemmc\">"
                            + "<a title=\"" + Tool.getDomainName(oneAds.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a>"
                            + "</div><div class=\"ssvzimage\">"
                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
                            + "<img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" style=\"width:90px;height:90px;\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\">"
                            + "</a><div class=\"price\"><a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
                            + oneAds.getDesc() + ""
                            + "</a></div></div></div>";
                    if (countItem != all.size()) {
                        str += "<div class=\"ssvzBorder\"><span></span></div>";
                    }
                    countItem++;
                }
                str += "</div><div class=\"ssvzclear\"><span></span></div>"
                        + "</div></div></div>"
                        + "</div></div></div>";
                str = Tool.validStringJs(str);
            } //            else if (groupType == GroupAdv.TYPE.MEDIUM_DOC.getValue()) {
            //                // Rộng 160px nhiều thành phần - chiều dọc
            //                str = "<div id=\"ads_zone" + groupID + "\">"
            //                        + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
            //                        + " <div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_zone" + groupID + "_banner\">"
            //                        + " <div id=\"ssvzone_" + groupID + "\">"
            //                        + " <div class=\"ssvzContent\">"
            //                        + " <div class=\"ssvzRight\">"
            //                        + " <div class=\"ssvzMid\">"
            //                        + "<div id=\"ssvzone_" + groupID + "_items\">";
            //                int countItem = 1;
            //                for (Advertise oneAds : all) {
            //                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
            //                    str += " <div id=\"adv_item\" class=\"adv_items\">"
            //                            + " <div class=\"ssvzTitle\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a></div>"
            //                            + " <div class=\"itemmc\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div>"
            //                            + " <div class=\"ssvzimage\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\"><img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\" \" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\"></a></div>"
            //                            + " <div class=\"contentAds\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a></div>"
            //                            + " </div>";
            //                    if (countItem != all.size()) {
            //                        str += " <div class=\"ssvzBorder\"><span></span></div>";
            //                    }
            //                    countItem++;
            //                }
            //                str += " </div>"
            //                        + " <div class=\"ssvzclear\"><span></span></div>"
            //                        + " </div>" + " </div>" + " </div>"
            //                        + " </div>"
            //                        + " </div>"
            //                        + " </div>"
            //                        + "</div>";
            //                str = Tool.validStringJs(str);
            //            } 
            //            else if (groupType == GroupAdv.TYPE.MEDIUM_NGANG.getValue()) {
            //                str = "<div id=\"ads_zone" + groupID + "\">"
            //                        + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
            //                        + " <div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_zone" + groupID + "_banner4\"> "
            //                        + " <div id=\"ssvzone_" + groupID + "\">"
            //                        + " <div class=\"ads_link_item\">"
            //                        + " <div id=\"ssvzone_" + groupID + "_items\">";
            //                int countItem = 1;
            //                for (Advertise oneAds : all) {
            //                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
            //                    str += " <div id=\"adv_item\" class=\"adv_items\">"
            //                            + " <div class=\"image\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\"><img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\" \" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\"></a></div>"
            //                            + " <div class=\"title\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + (oneAds.getTitle_top()) + "</a></div>"
            //                            + " <div class=\"itemmc\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div>"
            //                            + " <div class=\"price\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a></div>"
            //                            + " </div>";
            //                    if (countItem != all.size()) {
            //                        str += "<div class=\"border\"><span></span></div>";
            //                    }
            //                    countItem++;
            //                }
            //                str += " </div>"
            //                        + " </div> "
            //                        + " </div>"
            //                        + " </div>"
            //                        + " </div>"
            //                        + "</div>";
            //                str = Tool.validStringJs(str);
            //            } 
            else if (groupType == GroupAdv.TYPE.MEDIUM_DOC_RANDOM.getValue()) {
                // Rộng 160px nhiều thành phần - chiều dọc - Ngẫu Nhiên
                str = "<div id=\"ads_zone" + groupID + "\">"
                        + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
                        + " <div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_zone" + groupID + "_banner\">"
                        + " <div id=\"ssvzone_" + groupID + "\">"
                        + " <div class=\"ssvzContent\">"
                        + " <div class=\"ssvzRight\">"
                        + " <div class=\"ssvzMid\">"
                        + "<div id=\"ssvzone_" + groupID + "_items\">";
                int countItem = 1;
                for (Advertise oneAds : all) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += " <div id=\"adv_item\" class=\"adv_items\">"
                            //                            + " <div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\"><span></span></div>"
                            + " <div class=\"ssvzTitle\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a></div>"
                            + " <div class=\"itemmc\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div>"
                            + " <div class=\"ssvzimage\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\"><img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\" \" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\"></a></div>"
                            + " <div class=\"contentAds\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a></div>"
                            + " </div>";
                    if (countItem != all.size()) {
                        str += " <div class=\"ssvzBorder\"><span></span></div>";
                    }
                    countItem++;
                }
                str += " </div>"
                        + " <div class=\"ssvzclear\"><span></span></div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + "</div>";
                str = Tool.validStringJs(str);
            } else if (groupType == GroupAdv.TYPE.MEDIUM_NGANG_RANDOM.getValue()) {
                str = "<div id=\"ads_zone" + groupID + "\">"
                        + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
                        + " <div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_zone" + groupID + "_banner4\"> "
                        + " <div id=\"ssvzone_" + groupID + "\">"
                        + " <div class=\"ads_link_item\">"
                        + " <div id=\"ssvzone_" + groupID + "_items\">";
                int countItem = 1;
                for (Advertise oneAds : all) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += " <div id=\"adv_item\" class=\"adv_items\">"
                            //                            + " <div style=\"height:0px;width:0px;overflow:hidden\" id=\"" + (groupID + "_" + oneAds.getAdvID()) + "\"><span></span></div>"
                            + " <div class=\"image\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\"><img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\" \" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\"></a></div>"
                            + " <div class=\"title\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + (oneAds.getTitle_top()) + "</a></div>"
                            + " <div class=\"itemmc\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div>"
                            + " <div class=\"price\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a></div>"
                            + " </div>";
                    if (countItem != all.size()) {
                        str += "<div class=\"border\"><span></span></div>";
                    }
                    countItem++;
                }
                str += " </div>"
                        + " </div> "
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + "</div>";
                str = Tool.validStringJs(str);
            } //            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC.getValue()) {
            //                str = "<div style=\"margin:0; padding:0;width:300px;float:left\">"
            //                        + " <div data-zone-id=\"473\" class=\"link_ads_zone\" data-rendered=\"true\">"
            //                        + " <div class=\"linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4\">"
            //                        + " <a target=\"_blank\" href=\"http://hot.vn\" class=\"header\">"
            //                        + " <span class=\"logo\"><i></i><abbr>ads by link.vn</abbr></span>"
            //                        + " </a>"
            //                        + " <div class=\"banners\">";
            //                for (Iterator<Advertise> items = all.iterator(); items.hasNext();) {
            //                    Advertise oneAds = items.next();
            //                    String href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
            //                    str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
            //                            + " <span class=\"banner-face banner-face-front\">"
            //                            + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
            //                            + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del>" + (oneAds.getPrice_root() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPrice_root() + "")) : "") + "</del><strong>" + oneAds.getTitle_top() + "</strong><em>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</em></span>"
            //                            + " </span>"
            //                            + " </a>";
            //                    if (items.hasNext()) {
            //                        oneAds = items.next();
            //                        href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
            //                        str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
            //                                + " <span class=\"banner-face banner-face-front\">"
            //                                + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
            //                                + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del>" + (oneAds.getPrice_root() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPrice_root() + "")) : "") + "</del><strong>" + oneAds.getTitle_top() + "</strong><em>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</em></span>"
            //                                + " </span>"
            //                                + " </a>";
            //                    }
            //                }
            //                str += " </div>" + " </div>" + " </div>" + " </div>";
            //                str = Tool.validStringJs(str);
            //            } 
            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM.getValue()) {
                str = "<div style=\"margin:0; padding:0;width:300px;float:left\">"
                        + " <div data-zone-id=\"473\" class=\"link_ads_zone\" data-rendered=\"true\">"
                        + " <div class=\"linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4\">"
                        + " <a target=\"_blank\" href=\"http://hot.vn\" class=\"header\">"
                        + " <span class=\"logo\"><i></i><abbr>ads by link.vn</abbr></span>"
                        + " </a>"
                        + " <div class=\"banners\">";
                for (Iterator<Advertise> items = all.iterator(); items.hasNext();) {
                    Advertise oneAds = items.next();
                    String href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
                    str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
                            + " <span class=\"banner-face banner-face-front\">"
                            + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
                            + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del>" + (oneAds.getPrice_root() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPrice_root() + "")) : "") + "</del><strong>" + oneAds.getDesc() + "</strong><em>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</em></span>"
                            + " </span>"
                            + " </a>";
                    if (items.hasNext()) {
                        oneAds = items.next();
                        href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
                        str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
                                + " <span class=\"banner-face banner-face-front\">"
                                + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
                                + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del>" + (oneAds.getPrice_root() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPrice_root() + "")) : "") + "</del><strong>" + oneAds.getDesc() + "</strong><em>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</em></span>"
                                + " </span>"
                                + " </a>";
                    }
                }
                str += " "
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>";
                str = Tool.validStringJs(str);
            } //            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC_210.getValue()) {
            //                str = "<div style=\"margin:0; padding:0;width:210px;float:left\"> "
            //                        + "            <div data-rendered=\"true\" class=\"link_ads_zone\" data-zone-id=\"473\"> "
            //                        + "                <div class=\"linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4\"> "
            //                        + "                    <div class=\"banners\"> ";
            //                for (Iterator<Advertise> items = all.iterator(); items.hasNext();) {
            //                    Advertise oneAds = items.next();
            //                    String href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
            //                    str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
            //                            + " <span class=\"banner-face banner-face-front\">"
            //                            + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
            //                            + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price><strong>" + oneAds.getDesc() + "</strong><domain>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</domain></span>"
            //                            + " </span>"
            //                            + " </a>";
            //                    if (items.hasNext()) {
            //                        oneAds = items.next();
            //                        href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
            //                        str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
            //                                + " <span class=\"banner-face banner-face-front\">"
            //                                + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
            //                                + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price><strong>" + oneAds.getDesc() + "</strong><domain>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</domain></span>"
            //                                + " </span>"
            //                                + " </a>";
            //                    }
            //                }
            //                str += " </div> "
            //                        + "                </div> "
            //                        + "            </div> "
            //                        + "</div>";
            //                str = Tool.validStringJs(str);
            //            } 
            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM_210.getValue()) {
                str = "<div style=\"margin:0; padding:0;width:210px;float:left\"> "
                        + "            <div data-rendered=\"true\" class=\"link_ads_zone\" data-zone-id=\"473\"> "
                        + "                <div class=\"linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4\"> "
                        + "                    <div class=\"banners\"> ";

                for (Iterator<Advertise> items = all.iterator(); items.hasNext();) {
                    Advertise oneAds = items.next();
                    String href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
                    str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
                            + " <span class=\"banner-face banner-face-front\">"
                            + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
                            + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price><strong>" + oneAds.getDesc() + "</strong><domain>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</domain></span>"
                            + " </span>"
                            + " </a>";
                    if (items.hasNext()) {
                        oneAds = items.next();
                        href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
                        str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
                                + " <span class=\"banner-face banner-face-front\">"
                                + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
                                + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price><strong>" + oneAds.getDesc() + "</strong><domain>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</domain></span>"
                                + " </span>"
                                + " </a>";
                    }
                }
                str += " </div> "
                        + "                </div> "
                        + "            </div> "
                        + "</div>";
                str = Tool.validStringJs(str);
            } else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM_124.getValue()) {
                str = "<div style=\"margin:0; padding:0;width:124px;float:left\"> "
                        + "            <div data-rendered=\"true\" class=\"link_ads_zone\" data-zone-id=\"473\"> "
                        + "                <div class=\"linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4\"> "
                        + "                    <div class=\"banners\"> ";
                for (Advertise oneAds : all) {
                    String href = (DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer);
                    str += " <a data-banner-id=\"" + oneAds.getAdvID() + "\" target=\"_blank\" href=\"" + href + "\" class=\"banner-widget one_block banner-first-row\">"
                            + " <span class=\"banner-face banner-face-front\">"
                            + " <img alt=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" src=\"" + (URL_IMAGE + "/adv-res/image" + oneAds.getFilePath()) + "\">"
                            + " <span class=\"banner-content\"><price>" + (oneAds.getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(oneAds.getPriceSell() + "")) : "") + "</price><strong>" + oneAds.getDesc() + "</strong><domain>" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</domain></span>"
                            + " </span>"
                            + " </a>";
                }
                str += " </div> "
                        + "                </div> "
                        + "            </div> "
                        + "</div>";
                str = Tool.validStringJs(str);
            } else if (groupType == GroupAdv.TYPE.DOC_RAN_MIN_120.getValue()) {
                // Rộng min 120px  Ngẫu Nhiên không có Desc
                str = "<div id=\"ads_zone" + groupID + "\">"
                        + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
                        + " <div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_zone" + groupID + "_banner\">"
                        + " <div id=\"ssvzone_" + groupID + "\">"
                        + " <div class=\"ssvzContent\">"
                        + " <div class=\"ssvzRight\">"
                        + " <div class=\"ssvzMid\">"
                        + "<div id=\"ssvzone_" + groupID + "_items\">";
                int countItem = 1;
                for (Advertise oneAds : all) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += " <div id=\"adv_item\" class=\"adv_items\">"
                            + " <div class=\"ssvzTitle\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a></div>"
                            + " <div class=\"itemmc\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a></div>"
                            + " <div class=\"ssvzimage\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\"><img vspace=\"0\" hspace=\"0\" border=\"0\" align=\"left\" alt=\" \" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\"></a></div>"
                            + " <div class=\"contentAds\"><a title=\"" + Tool.getStringAlt(oneAds.getTitle_top()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + (oneAds.getPriceSell() > 0 ? Tool.priceWithoutDecimal(oneAds.getPriceSell() + "") + " vnđ" : "") + "</a></div>"
                            + " </div>";
                    if (countItem != all.size()) {
                        str += " <div class=\"ssvzBorder\"><span></span></div>";
                    }
                    countItem++;
                }
                str += " </div>"
                        + " <div class=\"ssvzclear\"><span></span></div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + " </div>"
                        + "</div>";
                str = Tool.validStringJs(str);
            } else if (groupType == GroupAdv.TYPE.DOC_RAN_MIN_200.getValue()) {
                // RANDOM TRONG CACHE NEN KHONG CACHE ELEMENT NAY                
                str = "<div style=\"width:" + (width > 0 ? width : "200") + "px; height: " + (all.size() * 174 + 22) + "px; margin-bottom:10px\" id=\"ads_linkvn_zone_" + groupID + "_slot" + all.size() + "\">"
                        + "<div class=\"banner" + (groupID + "" + groupType) + "\" id=\"ads_linkvn_zone_" + groupID + "\">"
                        + "<div id=\"ssvzone_" + groupID + "\" style=\"display: block;\">"
                        + "<div class=\"ssvzContent\">"
                        + "<div class=\"ssvzRight\"><div class=\"ssvzMid\">"
                        + "<div id=\"ssvzone_" + groupID + "_items\">";
                int countItem = 1;
                for (Advertise oneAds : all) {
                    urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
                    str += " <div id=\"adv_item\" class=\"adv_items\">"
                            + "<div style=\"height:0px;width:0px;overflow:hidden;\" id=\"" + groupID + "_" + oneAds.getAdvID() + "\">"
                            + "<span></span></div> <div class=\"ssvzTitle\">"
                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getTitle_top() + "</a>"
                            + "</div><div class=\"itemmc\">"
                            + "<a title=\"" + Tool.getDomainName(oneAds.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + urlClick + "\">" + Tool.getDomainName(oneAds.getDestinationUrl()) + "</a>"
                            + "</div><div class=\"ssvzimage\">"
                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">"
                            + "<img hspace=\"0\" border=\"0\" align=\"left\" vspace=\"0\" alt=\"" + oneAds.getTitle_top() + "\" src=\"" + URL_IMAGE + "/adv-res/image" + oneAds.getFilePath() + "\" style=\"width:90px;height:90px;\"></a> <div class=\"price\">"
                            + "<a title=\"" + oneAds.getTitle_top() + "\" target=\"_blank\" href=\"" + urlClick + "\">" + oneAds.getDesc() + "</a> </div>"
                            + "</div></div> ";
                    if (countItem != all.size()) {
                        str += "<div class=\"ssvzBorder\"><span></span></div>";
                    }
                    countItem++;
                }
                str += "</div> <div class=\"ssvzclear\"><span></span></div> </div> </div> </div> "
                        + " </div> </div> </div>";
                str = Tool.validStringJs(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String buildURLClick(Advertise oneAds, int groupID, String refer) {
        String urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
        return urlClick;
    }

    private static String buildText(int price) {
        String str = "HOT";
        if (price > 0) {
            str = Tool.priceToString(Tool.string2Double(price + ""));
            str += " Đ";
        }
        if (price == 0) {
            str = "SALE";
        }
        return str;
    }

    private static String buildCss(int price) {
        String css = "";
        if (price > 0) {
            css = "price_img";
        }
        if (price == 0) {
            css = "price_sale";
        }
        if (price == -1) {
            css = "price_hot";
        }
        if (price == -2) {
            css = "price_new";
        }
        return css;
    }
}
