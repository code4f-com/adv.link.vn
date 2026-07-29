<%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.object.AnalyticsAll"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
        <%@include file="/includes/datePicker.jsp" %>        
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-ui.popup.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/popup.css" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
    </head>
    <body>
        <%
            String startTime = Tool.validStringRequest(request.getParameter("startTime"));
            if (Tool.checkNull(startTime)) {
                startTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            String endTime = Tool.validStringRequest(request.getParameter("endTime"));
            if (Tool.checkNull(endTime)) {
                endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            int rowPerPage = RequestTool.getInt(request, "rowPerPage", 100);
            int locationID = Tool.string2Integer(request.getParameter("location"), 0);
            int adsId = Tool.string2Integer(request.getParameter("adsId"), 0);
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            AnalyticsAll analyDao = new AnalyticsAll();
            int totalPage = 0;
            int totalRow = analyDao.getCountClickForProduct(startTime, endTime, locationID, adsId);
            totalPage = (int) totalRow / rowPerPage;
            if (totalRow % rowPerPage != 0) {
                totalPage++;
            }
            ArrayList<AnalyticsAll> all = analyDao.getClickForProduct(currentPage, startTime, endTime, locationID, adsId, rowPerPage);
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <!-- Tìm kiếm-->
                        <form action="" method="post">
                            <table id="rounded-corner" align="center">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th scope="col" class="rounded-q4 redBoldUp">Thống KÊ từ ngày <%=startTime%> đến ngày <%=endTime%></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>Mã Quảng cáo:</td>
                                        <td><input size="75" type="text" name="adsId"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Từ ngày: </td>
                                        <td colspan="2">
                                            <input value="<%=startTime%>" class="dateproc" size="30" type="text" name="startTime"/>
                                            &nbsp;&nbsp;&nbsp;
                                            Đến ngày
                                            <input value="<%=endTime%>" class="dateproc" size="30" type="text" name="endTime"/>
                                        </td>
                                    </tr>
                                <script>
                                    function changeRowView(val) {
                                        var pageView = location.href;
                                        location.href = "<%=request.getContextPath()%>/sys-admin/statistic/show/forProduct.html?rowPerPage=" + val;
                                    }
                                </script>
                                <tr align="center">
                                    <td colspan="3">
                                        <input type="submit" name="submit" value="Lọc Thống Kê"/>
                                        &nbsp;&nbsp;&nbsp;Hiển thị
                                        <select name="rowPerPage" onchange="changeRowView(this.value)">
                                            <option <%=rowPerPage == 20 ? "selected='selected'" : ""%> value="20">20</option>
                                            <option <%=rowPerPage == 30 ? "selected='selected'" : ""%> value="30">30</option>
                                            <option <%=rowPerPage == 50 ? "selected='selected'" : ""%> value="50">50</option>
                                            <option <%=rowPerPage == 100 ? "selected='selected'" : ""%> value="100">100</option>
                                        </select>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>      
                        <!--End tim kiếm-->
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }%>
                        </div>
                        <!--Content-->
                        <%@include file="/includes/page.jsp" %>
                        <table align="center" id="rounded-corner" summary="Gk Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">Mã QC</th>
                                    <th scope="col" class="rounded">Ảnh mẫu</th>
                                    <th scope="col" class="rounded">QC Của</th>
                                    <th scope="col" class="rounded">Tiêu đề</th>
                                    <th scope="col" class="rounded">Giá</th>
                                    <th scope="col" class="rounded">Lượt Click</th>
                                    <th scope="col" class="rounded-q4">Chi tiết</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int stt = 1;
                                    int countTotal = 0;
                                    for (Iterator<AnalyticsAll> iter = all.iterator(); iter.hasNext();) {
                                        AnalyticsAll oneAnalytic = iter.next();
                                        Advertise oneAds = oneAnalytic.getAds();
                                %>
                                <tr>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=stt++%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getAdvID()%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff">
                                        <img width="90px" height="90px" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneAds.getFilePath()%>"/>
                                    </td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=Tool.getDomainName(oneAds.getDestinationUrl()) %></td>
                                    <td style="border-right: solid 1px #00ccff" align="center">
                                        <%=oneAds.getTitle_top()%>
                                    </td>
                                    <td style="border-right: solid 1px #00ccff" align="center">
                                        Giá mua: <%=oneAds.getPrice_root()%><br/>
                                        Giá bán: <%=oneAds.getPriceSell()%>
                                    </td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getClick()%></td>
                                    <td ><a href="<%="/sys-admin/statistic/click/detail-"+oneAnalytic.getAdvID()+".html"%>"> Chi tiết</td>
                                </tr>
                                <%
                                        countTotal = countTotal + oneAnalytic.getClick();
                                    }
                                    endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
                                %>
                                <tr>
                                    <td colspan="6" class="redBold">Tổng Click:</td>
                                    <td align="center" class="redBold"><%=countTotal%></td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>
                        <%@include file="/includes/page.jsp" %>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>