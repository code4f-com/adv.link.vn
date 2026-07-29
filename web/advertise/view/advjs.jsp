<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="gk.adv.linnk.vn.utils.Md5"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page contentType="text/javascript; charset=utf-8" %><%    int advID = RequestTool.getInt(request, "id");
    Advertise oneAdv = new Advertise();
    oneAdv = oneAdv.getAdvertise(advID);
    if (oneAdv != null) {
        Tool.Debug("advjs.jsp: kind : " + request.getHeader("referer"));
        if (oneAdv.getKind() == Advertise.KIND.FLASH.getValue()) {
%>document.write('<embed width="<%=oneAdv.getWidth() > 0 ? oneAdv.getWidth() : "100%"%>" height="<%=oneAdv.getHeight() > 0 ? oneAdv.getHeight() : "100%"%>" align="middle" quality="high" wmode="transparent" allowscriptaccess="always" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" alt="<%="http://" + BuildCache.DOMAIN + "/adv-res/image" + oneAdv.getFilePath()%>" src="<%="http://" + BuildCache.DOMAIN + "/adv-res/flash" + oneAdv.getFilePath()%>">');
<%} else if (oneAdv.getKind() == Advertise.KIND.IMAGE.getValue()) {%>document.write('<a target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "")%>"><img border="0" height="<%=(oneAdv.getHeight() > 0 ? oneAdv.getHeight() : "100%")%>" width="<%=(oneAdv.getWidth() > 0 ? oneAdv.getWidth() : "100%")%>" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneAdv.getFilePath()%>" id="ads_zone256_banner220123"></a>');
    <%} else if (oneAdv.getKind() == Advertise.KIND.IMAGE_TEXT.getValue()) {
        String ads_link_single_zone =
                "<div style=\"width:300px; margin-bottom:10px;border: 1px solid #DEDEDE\" id=\"ads_zone227_slot4\"><div class=\"banner0\" id=\"ads_zone227_banner65457\">"
                + "<div id=\"ssvzone_1129\" style=\"height: 146px\">"
                + "<div class=\"ssvzContent\">"
                + "<div class=\"ssvzRight\">"
                + "<div class=\"ssvzMid\"><div id=\"ssvzone_1129_items\">"
                + "<div id=\"adv_item\" class=\"adv_items\">"
                + "<div style=\"height:0px;width:0px;overflow:hidden;\" id=\"1129_222779\"><span></span></div>"
                + "<div class=\"ssvzTitle\"><a title=\"" + oneAdv.getTitle_top() + "\" target=\"_blank\" href=\"" + BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "") + "\">" + oneAdv.getTitle_top() + "</a></div>"
                + "<div class=\"itemmc\">"
                + "<a title=\"" + Tool.getDomainName(oneAdv.getDestinationUrl()) + "\" target=\"_blank\" href=\"" + BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "") + "\">" + Tool.getDomainName(oneAdv.getDestinationUrl()) + "</a>"
                + "</div>"
                + "<div class=\"ssvzimage\">"
                + "<a title=\"" + oneAdv.getTitle_top() + "\" target=\"_blank\" href=\"" + BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "") + "\">"
                + "<img hspace=\"0\" border=\"0\" align=\"left\" vspace=\"0\" alt=\"" + oneAdv.getTitle_top() + "\" src=\"" + BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneAdv.getFilePath() + "\" style=\"width:90px;height:90px;\"></a>"
                + "<div class=\"price\">"
                + "<a title=\"" + oneAdv.getTitle_top() + "\" target=\"_blank\" href=\"" + BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "") + "\">" + Tool.StringOneLine(oneAdv.getDesc()) + "</a>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "</div>"
                + "<div class=\"ssvzBottom\"><div class=\"ssvzRight\"><div class=\"ssvzMid\"><span></span></div></div></div>"
                + "</div>"
                + "</div>"
                + "</div>";
        ads_link_single_zone = ads_link_single_zone.replaceAll("/", "\\\\/");
        ads_link_single_zone = ads_link_single_zone.replaceAll("\"", "\\\\\"");
        ads_link_single_zone = ads_link_single_zone.replaceAll("\n", "");
        ads_link_single_zone = ads_link_single_zone.replaceAll("\r", "");
    %>
var ads_link_single_zone = "<%=ads_link_single_zone%>";
document.write(ads_link_single_zone);
<%}
    }%>
