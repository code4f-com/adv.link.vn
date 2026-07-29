/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.cache.type;

import gk.adv.linnk.vn.cache.BuildCache;
import static gk.adv.linnk.vn.cache.BuildCache.URL_IMAGE;
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
public class SLIDE_3FRAME_LAYOUT_1 implements ServiceType {

    @Override
    public String buildCache(ArrayList<Advertise> all, int groupID, int width, String refer) {
        String str = "";
        String urlClick = "";
        try {

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
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(0), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(0).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x100 link-border-left link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(1), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(1).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x100 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(2), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(2).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"midden_300x300 link-border-top link-border-bottom\">"
                            + "                            <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(3), groupID, refer) + "\">"
                            + "                                <img class=\"img_item_300x300\" src=\"" + URL_IMAGE + "/adv-res/image/300x300" + imageAds.get(3).getFilePath() + "\"/>"
                            + "                            </a>"
                            + "                        </div>"
                            + "                        <div class=\"bottom_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(4), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(4).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(5), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(5).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(6), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(6).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>                            "
                            + "                        </div>"
                            + "                    </div>"
                            + "                    <!--Frame 2-->"
                            + "                    <div class=\"linkvn_slotTwo\" id=\"linkvn_slotTwo\">"
                            + "                        <div class=\"midden_300x300 link-border-bottom\">"
                            + "                            <div class=\"item_150x300 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(7), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + imageAds.get(7).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_150x300 link-border-left\">"
                            + "                                <div class=\"item_150x150 link-border-bottom\">"
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(8), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + imageAds.get(8).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"item_150x150 link-border-top\">"
                            + "                                    <div class=\"item_75x150 link-border-right\">"
                            + "                                        <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(9), groupID, refer) + "\">"
                            + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(9).getFilePath() + "\"/>"
                            + "                                        </a>"
                            + "                                    </div>"
                            + "                                    <div class=\"item_75x150 link-border-left\">"
                            + "                                        <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(10), groupID, refer) + "\">"
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
                            + "                                        <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(11), groupID, refer) + "\">"
                            + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(11).getFilePath() + "\"/>"
                            + "                                        </a>"
                            + "                                    </div>"
                            + "                                    <div class=\"item_75x150 link-border-left\">"
                            + "                                        <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(12), groupID, refer) + "\">"
                            + "                                            <img class=\"img_item_75x150\" src=\"" + URL_IMAGE + "/adv-res/image/75x150" + imageAds.get(12).getFilePath() + "\"/>"
                            + "                                        </a>"
                            + "                                    </div>"
                            + "                                </div>"
                            + "                                <div class=\"item_150x150 link-border-top\">"
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(13), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_150x150\" src=\"" + URL_IMAGE + "/adv-res/image/150x150" + imageAds.get(13).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_150x300 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(14), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_150x300\" src=\"" + URL_IMAGE + "/adv-res/image/150x300" + imageAds.get(14).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                    </div>"
                            + "                    <!--Frame 3-->"
                            + "                    <div class=\"linkvn_slotThree\">"
                            + "                        <div class=\"all_300x200 link-border-bottom\">"
                            + "                            <div class=\"item_200x200 link-border-right\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(15), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_200x200\" src=\"" + URL_IMAGE + "/adv-res/image/200x200" + imageAds.get(15).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"item_100x200 link-border-left\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(16), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(16).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(17), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(17).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-bottom link-border-top\">"
                            + "                            <div class=\"img_item_100x200 link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(18), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(18).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left link-border-right\"> "
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(19), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(19).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                            <div class=\"img_item_100x200 link-border-left\"> "
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(20), groupID, refer) + "\">"
                            + "                                    <img class=\"img_item_100x200\" src=\"" + URL_IMAGE + "/adv-res/image/100x200" + imageAds.get(20).getFilePath() + "\"/>"
                            + "                                </a>"
                            + "                            </div>"
                            + "                        </div>"
                            + "                        <div class=\"all_300x200 link-border-top\">"
                            + "                            <div class=\"item_100x200 link-border-right\">"
                            + "                                <div class=\"img_item_100x100 link-border-bottom\"> "
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(21), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(21).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                                <div class=\"img_item_100x100 link-border-top\"> "
                            + "                                    <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(22), groupID, refer) + "\">"
                            + "                                        <img class=\"img_item_100x100\" src=\"" + URL_IMAGE + "/adv-res/image" + imageAds.get(22).getFilePath() + "\"/>"
                            + "                                    </a>"
                            + "                                </div>"
                            + "                            </div>"
                            + "                            <div class=\"item_200x200 link-border-left\">"
                            + "                                <a target=\"_blank\" href=\"" + BuildCache.buildURLClick(imageAds.get(23), groupID, refer) + "\">"
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

}
