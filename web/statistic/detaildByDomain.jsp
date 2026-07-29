<%@page import="gk.adv.linnk.vn.object.Advertise"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.object.AnalyticsAll"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            String domain = Tool.validStringRequest(request.getParameter("domain"));
            if (Tool.checkNull(domain)) {
                session.setAttribute("mess", "Bạn cần chọn domain để xem chi tiết");
                response.sendRedirect("/statistic/clickByDomain.jsp");
                return;
            }
            String startTime = Tool.validStringRequest(request.getParameter("startTime"));
            if (Tool.checkNull(startTime)) {
                startTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            String endTime = Tool.validStringRequest(request.getParameter("endTime"));
            if (Tool.checkNull(endTime)) {
                endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            int rowPerPage = RequestTool.getInt(request, "rowPerPage", 100);
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            AnalyticsAll analyDao = new AnalyticsAll();
            int totalPage = 0;
            int totalRow = analyDao.getCountDetailByDomain(startTime, endTime, domain);
            totalPage = (int) totalRow / rowPerPage;
            if (totalRow % rowPerPage != 0) {
                totalPage++;
            }
            ArrayList<Advertise> all = analyDao.getDetailByDomain(currentPage,rowPerPage,startTime, endTime, domain);
            Tool.Debug(all.size());
            Tool.Debug("Domain:"+domain);
            Tool.Debug("totalRow"+totalRow);
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
                                        location.href = "/statistic/detaildByDomain.jsp?rowPerPage=" + val;
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
                                    <th scope="col" class="rounded-company"></th>
                                    <th scope="col" class="rounded">STT</th>
                                    <th scope="col" class="rounded">Mã QC</th>
                                    <th scope="col" class="rounded">Ảnh</th>
                                    <th scope="col" class="rounded">Tiêu đề</th>
                                    <th scope="col" class="rounded">Lượt Click</th>
                                    <th scope="col" class="rounded-q4"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int stt = 1;
                                    int countTotal = 0;
                                    for (Iterator<Advertise> iter = all.iterator(); iter.hasNext();) {
                                        Advertise oneAnalytic = iter.next();
                                %>
                                <tr>
                                    <td></td>
                                    <td><%=stt++%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getAdvID()%></td>
                                    <td style="border-right: solid 1px #00ccff" align="center">
                                        <img src="/adv-res/image<%= oneAnalytic.getFilePath()%>" />
                                    </td>
                                    <td style="border-right: solid 1px #00ccff" align="center"><%= oneAnalytic.getTitle_top()%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getClick()%></td>
                                    <td></td>
                                </tr>
                                <%
                                        countTotal = countTotal + oneAnalytic.getClick();
                                    }
                                    endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
                                %>
                                <tr>
                                    <td colspan="4" class="redBold">Tổng Click:</td>
                                    <td align="center" class="redBold"><%=countTotal%></td><td></td>
                                    <td ></td>
                                </tr>
                            </tbody>
                        </table>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
        <div id="dhtmltooltip" align=left style="border:1px;border-style:solid; z-index: 100; visibility:  hidden; width:  500px; position:  absolute; background-color: lavender;">
            <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/tooltip.js"></script>
    </body>
</html>