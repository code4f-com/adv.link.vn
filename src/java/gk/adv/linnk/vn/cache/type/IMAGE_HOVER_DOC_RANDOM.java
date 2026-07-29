/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import static gk.adv.linnk.vn.cache.BuildCache.DOMAIN;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
import gk.adv.linnk.vn.object.Advertise;
import gk.adv.linnk.vn.utils.Md5;
import gk.adv.linnk.vn.utils.Tool;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TUANPLA
 */
public class IMAGE_HOVER_DOC_RANDOM implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
