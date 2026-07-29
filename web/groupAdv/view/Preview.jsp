<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="java.util.ArrayList"%><%@page import="gk.adv.linnk.vn.utils.Md5"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%>
<%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page contentType="text/html; charset=utf-8" %>
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/advzone.css")%>" />
<html><body style="background-color: #D7D7D7">
        <%
            MapGroup gMap = (MapGroup) session.getAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME);
            if (gMap == null || gMap.getGroupID() == 0) {
                out.print("<h1 align='center'>Không có Quảng cáo nào được chọn<br/>"
                        + "Ấn vào đây để <a href='/groupAdv/group-manager.html'>quay lại</a></h1>");
            } else {
                GroupAdv gAdsDao = new GroupAdv();
                gAdsDao = gAdsDao.getByID(gMap.getGroupID());
                if (gAdsDao == null) {
                    out.print("<h1 align='center'>Yêu cầu không hợp lệ<br/>"
                            + "Ấn vào đây để <a href='/groupAdv/group-manager.html'>quay lại</a></h1>");
                } else {
                    if (gAdsDao.getKind() == GroupAdv.KIND.FLASH.getValue()) {
                        Advertise adsDao = new Advertise();
                        // Chi cho 1 phan tu
                        int adsID = Tool.string2Integer(gMap.getAdsID().get(0));
                        adsDao = adsDao.getAdvertise(adsID);
                        if (adsDao == null) {
                            out.print("<h1 align='center'>Yêu cầu không hợp lệ<br/>"
                                    + "Ấn vào đây để <a href='/groupAdv/group-manager.html'>quay lại</a></h1>");
                        } else {
        %>
        <div class="sbox" style="padding:0; border:none">
            <embed width="<%=adsDao.getWidth()%>" height="<%=adsDao.getHeight()%>" name="plugin" src="<%= BuildCache.DOMAIN + request.getContextPath()%>/adv-res/flash<%=adsDao.getFilePath()%>" type="application/x-shockwave-flash">
        </div>
        <%                }
        } else if (gAdsDao.getKind() == GroupAdv.KIND.IMAGE.getValue()) {
            Advertise adsDao = new Advertise();
            // Chi cho 1 phan tu
            int adsID = Tool.string2Integer(gMap.getAdsID().get(0));
            adsDao = adsDao.getAdvertise(adsID);
            if (adsDao == null) {
                out.print("<h1 align='center'>Yêu cầu không hợp lệ<br/>"
                        + "Ấn vào đây để <a href='/groupAdv/group-manager.html'>quay lại</a></h1>");
            } else {
        %>
        <div style="margin-top:3px" id="ads_zone256_slot3"><div class="banner0" id="ads_zone256_banner220123"><a target="_blank" href="<%= BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + adsDao.getAdvID() + "&g_main=" + gMap.getGroupID() + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><img border="0" height="<%=(adsDao.getHeight() > 0 ? adsDao.getHeight() : "100%")%>" width="<%=(adsDao.getWidth() > 0 ? adsDao.getWidth() : "100%")%>" src="<%= BuildCache.DOMAIN + request.getContextPath()%>/adv-res/image/<%=adsDao.getFilePath()%>" id="ads_zone256_banner220123"></a></div></div>
                    <%
                        }
                    } else if (gAdsDao.getKind() == GroupAdv.KIND.IMAGE_TEXT.getValue()) {
                        Advertise adsDao = new Advertise();
                        // Chi cho 1 phan tu
                        ArrayList<Advertise> all = adsDao.getAllAdsByArrID(gMap.getAdsID());
                        if (adsDao == null) {
                            out.print("<h1 align='center'>Yêu cầu không hợp lệ<br/>"
                                    + "Ấn vào đây để <a href='/groupAdv/group-manager.html'>quay lại</a></h1>");
                        } else {
                    %>
        <div style="width:300px; margin-bottom:10px" id="ads_zone227_slot4">
            <div class="banner0" id="ads_link_vn_zone_<%=gMap.getGroupID()%>">
                <div id="ssvzone_2266">
                    <div class="ssvzTop"><div class="ssvzRight"><div class="ssvzMid"><span></span></div></div></div>
                    <div class="ssvHeaderBr">
                        <div class="ssvzHeader">
                            <a target="_blank" href="<%=BuildCache.DOMAIN %>" title="Mua quảng cáo"><div style="float:left; width:140px; height:21px;" class="ssvzLogo"></div></a>
                            <a target="_blank" href="<%=BuildCache.DOMAIN %>" title="Mua quảng cáo"><div style="float:right; width:140px; height:21px;" class="ssvzBuy"></div></a>
                        </div>
                    </div>
                    <div class="ssvzContent">
                        <div class="ssvzRight">
                            <div class="ssvzMid">
                                <div id="ssvzone_1129_items">
                                    <%for (Advertise oneAds : all) {%>
                                    <div id="adv_item" class="adv_items">
                                        <div style="height:0px;width:0px;overflow:hidden;" id="1129_222779"><span></span></div>
                                        <div class="ssvzTitle"><a title="<%=oneAds.getTitle_top()%>" target="_blank" href="<%= BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + adsDao.getAdvID() + "&g_main=" + gMap.getGroupID() + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=oneAds.getTitle_top()%></a></div>
                                        <div class="itemmc">
                                            <a title="<%=Tool.getDomainName(oneAds.getDestinationUrl())%>" target="_blank" href="<%= BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + adsDao.getAdvID() + "&g_main=" + gMap.getGroupID() + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=Tool.getDomainName(oneAds.getDestinationUrl())%></a>
                                        </div>
                                        <div class="ssvzimage">
                                            <a title="<%=oneAds.getTitle_top()%>" target="_blank" href="<%= BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + adsDao.getAdvID() + "&g_main=" + gMap.getGroupID() + "&key=" + Md5.encryptMD5(Math.random() + "")%>">
                                                <img hspace="0" border="0" align="left" vspace="0" alt="<%=oneAds.getTitle_top()%>" src="<%=  BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneAds.getFilePath()%>" style="width:90px;height:90px;"></a>
                                            <div class="price">
                                                <a title="<%=oneAds.getTitle_top()%>" target="_blank" href="<%= BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + adsDao.getAdvID() + "&g_main=" + gMap.getGroupID() + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=oneAds.getDesc()%></a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="ssvzBorder"><span></span></div>
                                            <%}%>
                                </div>
                                <div class="ssvzclear"><span></span></div>
                            </div>
                        </div>
                    </div>
                    <div class="ssvzBottom"><div class="ssvzRight"><div class="ssvzMid"><span></span></div></div></div>
                </div>
            </div>
        </div>
        <%
                        }
                    }
                }
            }
        %>
    </body>
</html>