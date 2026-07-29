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
public class SLIDE_A1_HOVER implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        try {
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                //--
                ArrayList<Advertise> imgSlide = GroupAdv.ranDomFromCache(all, 15);
                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                        + "                    <div class=\"linkvn_slotOne\"  id=\"linkvn_slotOne\">"
                        + "                        <div class=\"top_300x100 link-border-bottom\">"
                        + "                            <div class=\"item_100x100 link-border-right\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(0), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(0).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                            <div class=\"item_100x100 link-border-left link-border-right\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(1), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(1).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                            <div class=\"item_100x100 link-border-left\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(2), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(2).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                        </div>"
                        + "                        <div class=\"midden_300x300 link-border-top link-border-bottom\">"
                        + "                            <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(3), groupID, refer) + "\">"
                        + "                                <img class=\"img_item_300x300\" src=\"" + URL_IMAGE + "/adv-res/image/300x300" + imgSlide.get(3).getFilePath() + "\"/>"
                        + "                            </a>"
                        + "                        </div>"
                        + "                        <div class=\"bottom_300x200 link-border-top\">"
                        + "                            <div class=\"item_100x200 link-border-right\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(4), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imgSlide.get(4).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                            <div class=\"item_100x200 link-border-left link-border-right\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(5), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imgSlide.get(5).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>"
                        + "                            <div class=\"item_100x200 link-border-left\">"
                        + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imgSlide.get(6), groupID, refer) + "\">"
                        + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imgSlide.get(6).getFilePath() + "\"/>"
                        + "                                </a>"
                        + "                            </div>                            "
                        + "                        </div>"
                        + "                    </div>"
                        //-- Slot 2 --------------------------------------- 7-15
                        + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                        + "                         <div class=\"banners\">"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(7), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"202\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(7).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(7).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\">"
                        + "                                        <price>" + (imgSlide.get(7).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(7).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>"+ imgSlide.get(7).getTitle_top() +"</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(7).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(8), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"200\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(8).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(8).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(8).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(8).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(8).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(8).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(9), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"204\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(9).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(9).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(9).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(9).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(9).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(9).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(10), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"197\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(10).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(10).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(10).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(10).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(10).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(10).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(11), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"202\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(11).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(11).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\">"
                        + "                                        <price>" + (imgSlide.get(11).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(11).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(11).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(11).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(12), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"200\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(12).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(12).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(12).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(12).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(12).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(12).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(13), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"204\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(13).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(13).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(13).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(13).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(13).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(13).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgSlide.get(14), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"197\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgSlide.get(14).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgSlide.get(14).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgSlide.get(14).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgSlide.get(14).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgSlide.get(14).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgSlide.get(14).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                        </div> "
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
