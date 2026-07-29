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
public class SLIDE_2_FRAME_A3_T implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
