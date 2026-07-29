<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.MyLocation"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.object.StatisticClick"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page contentType="text/html; charset=utf-8" %>
<%
    StatisticClick oneClick = new StatisticClick();
    int advid = RequestTool.getInt(request, "ads_id");
    int groupId = RequestTool.getInt(request, "g_main");
    Advertise oneAds = new Advertise();
    oneAds = oneAds.getAdvertise(advid);
    if (oneAds != null) {
        // Refer
        String refer = RequestTool.getString(request, "refer");
        if (Tool.checkNull(refer)) {
            refer = request.getHeader("referer");
        }
        oneClick.setDomainRequest(Tool.getDomainName(refer));
        // IP
        String ip = Tool.getClientIpAddr(request);
        String[] arrIP = ip.split(",");
        if (arrIP != null && arrIP.length > 0) {
            ip = arrIP[arrIP.length - 1];
            if (ip != null && !ip.equals("")) {
                ip = ip.trim();
            }
        }
        oneClick.setIpCLick(ip);
        oneClick.setRequestURL(refer);
        oneClick.setUserAgent(request.getHeader("user-agent"));
        oneClick.setGroupID(groupId);
        oneClick.setAdvID(advid);       // Quen ko Set Ads ID
        oneClick.setTimeLong(System.currentTimeMillis());
        oneClick.setDestinationURL(oneAds.getDestinationUrl());
        //---
        MyLocation local = MyLocation.getLocation(oneClick.getIpCLick());
        if (local != null) {
            oneClick.setCountry_code(local.getCountry_code());
            oneClick.setCountry_name(local.getCountry_name());
            oneClick.setCity(local.getCity());
            oneClick.setRegion_code(local.getRegion_code());
            oneClick.setRegion_name(local.getRegion_name());
            ListionContext.queueClick.enqueue(oneClick);
            if (local.getCountry_code()!=null && local.getCountry_code().equalsIgnoreCase("VN") && !Tool.checkNull(local.getRegion_code())) {
                MyLocation.LogLocation(local);
            }
        }
        if (oneAds != null) {
            response.sendRedirect(oneAds.getDestinationUrl());
            return;
        } else {
            response.sendRedirect("http://hot.vn");
            return;
        }
    } else {
        response.sendRedirect("http://hot.vn");
    }
%>