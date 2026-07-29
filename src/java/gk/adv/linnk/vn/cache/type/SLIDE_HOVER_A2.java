/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import gk.adv.linnk.vn.cache.BuildCache;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import static gk.adv.linnk.vn.cache.BuildCache.buildURLClick;
import gk.adv.linnk.vn.cache.CacheUtil;
import gk.adv.linnk.vn.object.Advertise;
import gk.adv.linnk.vn.object.GroupAdv;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;
import net.sf.ehcache.Element;

/**
 *
 * @author TUANPLA
 */
public class SLIDE_HOVER_A2 implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        try {
            String js_key_cache = "GroupAdv." + groupID + ".js";
            Element element_js_Cache = CacheUtil.cacheAds2m.get(js_key_cache);
            if (element_js_Cache == null) {
                ArrayList<ArrayList<Advertise>> _2group = Advertise.splitImgAndImgSlideHover(all);
                //--
                ArrayList<Advertise> gImageSlide = GroupAdv.ranDomFromCache(_2group.get(1), 8);
                //--
                ArrayList<Advertise> imgHover = GroupAdv.ranDomFromCache(_2group.get(0), 8);

                str = "<div id=\"ads_linkvn_zone_" + groupID + "_slot10\">"
                        + "                <div id=\"linkvn_slide_" + groupID + "_Holder\" style=\"margin-left: 0px\">"
                        //---- A2 --------------
                        + "                    <div class=\"linkvn_slotOne\" id=\"linkvn_slotOne\">"
                        + "                         <div class=\"banners\">"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(0), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"202\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(0).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(0).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\">"
                        + "                                        <price>" + (imgHover.get(0).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(0).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(0).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(0).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(1), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"200\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(1).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(1).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(1).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(1).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(1).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(1).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(2), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"204\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(2).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(2).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(2).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(2).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(2).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(2).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(3), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"197\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(3).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(3).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(3).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(3).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(3).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(3).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(4), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"202\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(4).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(4).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\">"
                        + "                                        <price>" + (imgHover.get(4).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(4).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(4).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(4).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(5), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"200\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(5).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(5).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(5).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(5).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(5).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(5).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(6), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"204\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(6).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(6).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(6).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(6).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(6).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(6).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                            <a class=\"banner-widget one_block banner-first-row\" href=\"" + BuildCache.buildURLClick(imgHover.get(7), groupID, refer) + "\" target=\"_blank\" data-banner-id=\"197\">"
                        + "                                <span class=\"banner-face banner-face-front\">"
                        + "                                    <img src=\"" + URL_IMAGE + "/adv-res/image" + imgHover.get(7).getFilePath() + "\" alt=\"" + Tool.getStringAlt(imgHover.get(7).getTitle_top()) + "\">"
                        + "                                    <span class=\"banner-content\"><price>" + (imgHover.get(7).getPriceSell() > 0 ? Tool.priceToString(Tool.string2Double(imgHover.get(7).getPriceSell() + "")) : "") + "</price>&nbsp;&nbsp;<del></del>"
                        + "                                        <strong>" + imgHover.get(7).getTitle_top() + "</strong>"
                        + "                                        <em>" + Tool.getDomainName(imgHover.get(7).getDestinationUrl()) + "</em>"
                        + "                                    </span>"
                        + "                                </span>"
                        + "                            </a>"
                        + "                        </div> "
                        + "                    </div>"
                        //---- HOVER
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
                        + "                    </div>"
                        //--
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
