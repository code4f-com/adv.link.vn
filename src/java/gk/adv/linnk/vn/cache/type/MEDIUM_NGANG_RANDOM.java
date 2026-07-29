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
public class MEDIUM_NGANG_RANDOM implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {
            str = "<div id=\"ads_zone" + groupID + "\">"
                    + " <div id=\"ads_zone" + groupID + "_slot" + all.size() + "\">"
                    + " <div class=\"banner" + (groupID + "" + all.size()) + "\" id=\"ads_zone" + groupID + "_banner4\"> "
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
        } catch (Exception e) {
        }
        return str;
    }

}
