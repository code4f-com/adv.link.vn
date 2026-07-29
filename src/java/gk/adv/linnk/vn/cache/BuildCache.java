/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache;

import gk.adv.linnk.vn.cache.type.ServiceInstance;
import gk.adv.linnk.vn.cache.type.ServiceType;
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
public class BuildCache {

    public static String DOMAIN = "http://ads.link.vn";
    public static String URL_IMAGE = "http://ads.link.vn";
    private static final ServiceInstance serviceFactory = new ServiceInstance();

    public static String buildCache(ArrayList<Advertise> all, int groupID, int groupType, int width, String refer) {
        String str = "";
        try {
            ServiceType service = serviceFactory.getInstance(groupType);
            String urlClick = "";
            if (service != null) {
                str = service.buildCache(all, groupID, width, refer);
                return str;
            } else {
                Tool.Debug("Service is null: "+groupType);
            }

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
            }
            /*
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
            //            else if (groupType == GroupAdv.TYPE.MEDIUM_DOC.getValue()) {
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
            //            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC.getValue()) {
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
            //            else if (groupType == GroupAdv.TYPE.IMAGE_HOVER_DOC_210.getValue()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String buildURLClick(Advertise oneAds, int groupID, String refer) {
        String urlClick = DOMAIN + "/ads_tracker.link?ads_id=" + oneAds.getAdvID() + "&g_main=" + groupID + "&key=" + Md5.encryptMD5(Math.random() + "") + "&refer=" + refer;
        return urlClick;
    }

    public static String buildText(int price) {
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

    public static String buildCss(int price) {
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
