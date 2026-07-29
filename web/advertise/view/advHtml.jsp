<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.utils.Md5"%><%@page import="config.ListionContext"%><%@page import="java.util.Enumeration"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page contentType="text/html; charset=utf-8" %><link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/advzone.css")%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table width="100%" >
    <tr >
        <td align="center">
            <%
                int advID = RequestTool.getInt(request, "id");
                Advertise oneAdv = new Advertise();
                oneAdv = oneAdv.getAdvertise(advID);
                if (oneAdv != null) {
                    Tool.Debug("advHtml.jsp: \nkind:" + oneAdv.getKind() + " | REFERER:" + request.getHeader("referer"));
                    if (oneAdv.getKind() == Advertise.KIND.FLASH.getValue()) {
            %><div class="sbox" style="padding:0; border:none">
                <embed width="<%=oneAdv.getWidth()%>" height="<%=oneAdv.getHeight()%>" name="plugin" src="<%= BuildCache.DOMAIN + request.getContextPath()%>/adv-res/flash<%=oneAdv.getFilePath()%>" type="application/x-shockwave-flash"></embed>
            </div>
            <%    } else if (oneAdv.getKind() == Advertise.KIND.IMAGE.getValue()) {%>
            <div style="margin-top:3px" id="ads_zone256_slot3"><div class="banner0" id="ads_zone256_banner220123"><a target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=" + 0 + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><img border="0" height="<%=(oneAdv.getHeight() > 0 ? oneAdv.getHeight() : "100%")%>" width="<%=(oneAdv.getWidth() > 0 ? oneAdv.getWidth() : "100%")%>" src="<%=BuildCache.DOMAIN + request.getContextPath()%>/adv-res/image/<%=oneAdv.getFilePath()%>" id="ads_zone256_banner220123"></a></div></div>
                        <%} else {
                            Tool.Debug(oneAdv.getDestinationUrl());
                        %>
            <div align="center" style="width:300px; margin-bottom:10px;border: 1px solid #DEDEDE;" id="ads_zone227_slot4">
                <div class="banner0" id="ads_zone227_banner65457">
                    <div id="ssvzone_1129" style="height: 146px">
                        <div class="ssvzContent">
                            <div class="ssvzRight">
                                <div class="ssvzMid"><div id="ssvzone_1129_items">
                                        <div id="adv_item" class="adv_items">
                                            <div style="height:0px;width:0px;overflow:hidden;" id="1129_222779"><span></span></div>
                                            <div class="ssvzTitle"><a title="<%=oneAdv.getTitle_top()%>" target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=" + 0 + "&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=oneAdv.getTitle_top()%></a></div>
                                            <div class="itemmc">
                                                <a title="<%=Tool.getDomainName(oneAdv.getDestinationUrl())%>" target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=Tool.getDomainName(oneAdv.getDestinationUrl())%></a>
                                            </div>
                                            <div class="ssvzimage">
                                                <a title="<%=oneAdv.getTitle_top()%>" target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "")%>">
                                                    <img hspace="0" border="0" align="left" vspace="0" alt="<%=oneAdv.getTitle_top()%>" src="<%= BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneAdv.getFilePath()%>" style="width:90px;height:90px;"></a>
                                                <div class="price">
                                                    <a title="<%=oneAdv.getTitle_top()%>" target="_blank" href="<%=BuildCache.DOMAIN + request.getContextPath() + "/ads_tracker.link?ads_id=" + oneAdv.getAdvID() + "&g_main=0&key=" + Md5.encryptMD5(Math.random() + "")%>"><%=oneAdv.getDesc()%></a>
                                                </div>
                                            </div>
                                        </div>
                                        <!--<div class="ssvzBorder"><span></span></div>-->                            
                                    </div>
                                    <!--<div class="ssvzclear"><span></span></div>-->
                                </div>
                            </div>
                        </div>
                        <div class="ssvzBottom"><div class="ssvzRight"><div class="ssvzMid"><span></span></div></div></div>
                    </div>
                </div>
            </div>
            <%        }
                }
            %>
        </td> 
    </tr>
</table>
