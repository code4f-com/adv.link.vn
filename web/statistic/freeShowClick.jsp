<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.object.AnalyticsAll"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@page import="java.util.Enumeration"%><%@page import="gk.adv.linnk.vn.admin.LogAction"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.admin.Account"%>
        <%@page contentType="text/html; charset=utf-8" %>
        <%
            request.setCharacterEncoding("UTF-8");
            //String webPath = request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort())) + request.getContextPath();
            String webPath = request.getContextPath();
            //--------------- Admin
            Account adminInfo = (Account) session.getAttribute("adminInfo");
            if (adminInfo == null) {
                // session.setAttribute("error", "Bạn cần đăng nhập để truy cập hệ thống");
                // out.print("<script>top.location='" + request.getContextPath() + "/sys/login';</script>");
                // return;
            } else {
                System.out.println("Admin [Adv Link.vn] user: VIETNAMNET---" + DateProc.createTimestamp());
            }
            //---------PAGE SETING----------------
            String pageURL = "";
            Enumeration paraList = null;
            pageURL = request.getRequestURI() + "?";
            paraList = request.getParameterNames();
            while (paraList.hasMoreElements()) {
                String paraName = String.valueOf(paraList.nextElement());
                if (!paraName.equalsIgnoreCase("page") && !paraName.equalsIgnoreCase("submit")) {
                    pageURL += paraName + "=" + request.getParameter(paraName) + "&amp;";
                }
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ADMIN LINK.VN</title>
        <link rel="Shortcut Icon" href="<%= request.getContextPath() + "/resource/images/logo.ico"%>" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/style.css")%>" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/jquery.alerts.css")%>" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/clockp.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/clockh.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ddaccordion.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ImageOnMouse.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ajax.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery.alerts.js"></script>
        <script type="text/javascript">
            ddaccordion.init({
                headerclass: "submenuheader", //Shared CSS class name of headers group
                contentclass: "submenu", //Shared CSS class name of contents group
                revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
                mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
                collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
                defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
                onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
                animatedefault: false, //Should contents open by default be animated into view?
                persiststate: true, //persist state of opened contents within browser session?
                toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
                togglehtml: ["suffix", "<img src='<%= request.getContextPath()%>resource/images/plus.gif' class='statusicon' />", "<img src='<%= request.getContextPath()%>resource/images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
                animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
                oninit: function(headers, expandedindices) { //custom code to run when headers have initalized
                    //do nothing
                },
                onopenclose: function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
                    //do nothing
                }
            })
        </script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jconfirmaction.jquery.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.ask').jConfirmAction();
            });
        </script>
        <%@include file="/includes/datePicker.jsp" %>        
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-ui.popup.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/popup.css" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
    </head>
    <body>
        <%
            String domain = Tool.validStringRequest(request.getParameter("domain"));
            if (Tool.checkNull(domain)) {
                domain = "tintuconline.com.vn";
            }
            String startTime = Tool.validStringRequest(request.getParameter("startTime"));
            if (Tool.checkNull(startTime)) {
                startTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            String endTime = Tool.validStringRequest(request.getParameter("endTime"));
            if (Tool.checkNull(endTime)) {
                endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
            }
            int rowPerPage = RequestTool.getInt(request, "rowPerPage", 50);
            int locationID = Tool.string2Integer(request.getParameter("location"), 0);
            int adsId = Tool.string2Integer(request.getParameter("adsId"), 0);
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            AnalyticsAll analyDao = new AnalyticsAll();
            int totalPage = 0;
            int totalRow = analyDao.getCountAnalyticClick(startTime, endTime, domain, locationID, adsId);
            totalPage = (int) totalRow / rowPerPage;
            if (totalRow % rowPerPage != 0) {
                totalPage++;
            }
            ArrayList<AnalyticsAll> all = analyDao.getAnalyticClick(currentPage, startTime, endTime, domain, locationID, adsId, rowPerPage);
        %>
        <div id="main_container">
            <%--@include file="/includes/checkLogin.jsp" --%>
            <div class="main_content">
                <div class="menu">
                    <ul>
                        <li><a target="_parent" class="current" href="#"><b>Trang chủ</b></a></li>
                        <li>
                        </li>
                    </ul>
                </div>
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
                                        <td>Tên Miền:</td>
                                        <td>
                                            <select name="domain">
                                                <option <%=domain.equalsIgnoreCase("tintuconline.com.vn") ? "selected='selected'" : ""%> value="tintuconline.com.vn">http://tintuconline.com.vn</option>
                                                <option <%=domain.equalsIgnoreCase("vietnamnet.vn") ? "selected='selected'" : ""%> value="vietnamnet.vn">http://vietnamnet.vn</option>
                                                <option <%=domain.equalsIgnoreCase("2sao.vn") ? "selected='selected'" : ""%> value="2sao.vn">http://2sao.vn</option>
                                            </select>
                                        </td>
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
                                        location.href = "/statistic/freeShowClick.jsp?rowPerPage=" + val;
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
                                    <th scope="col" class="rounded">Domain</th>
                                    <th scope="col" class="rounded">Lượt Click</th>
                                    <th scope="col" class="rounded-q4"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int stt = 1;
                                    int countTotal = 0;
                                    for (Iterator<AnalyticsAll> iter = all.iterator(); iter.hasNext();) {
                                        AnalyticsAll oneAnalytic = iter.next();
                                %>
                                <tr>
                                    <td></td>
                                    <td><%=stt++%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getAdvID()%></td>
                                    <td style="border-right: solid 1px #00ccff" align="center"><%= oneAnalytic.getDomain()%></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneAnalytic.getClick()%></td>
                                    <td></td>
                                </tr>
                                <%
                                        countTotal = countTotal + oneAnalytic.getClick();
                                    }
                                    endTime = DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp());
                                %>
                                <tr>
                                    <td colspan="3" class="redBold">Tổng Click:</td>
                                    <td align="center" class="redBold"><%=countTotal%></td><td></td>
                                    <td ></td>
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
        <div id="dhtmltooltip" align=left style="border:1px;border-style:solid; z-index: 100; visibility:  hidden; width:  500px; position:  absolute; background-color: lavender;">
            <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/tooltip.js"></script>
    </body>
</html>