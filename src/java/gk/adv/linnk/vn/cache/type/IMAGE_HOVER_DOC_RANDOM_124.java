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

/**
 *
 * @author TUANPLA
 */
public class IMAGE_HOVER_DOC_RANDOM_124 implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
