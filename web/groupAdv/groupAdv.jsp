
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-ui.popup.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/popup.css" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
        <script>
            function yourChangeState(divid, type, value, id) {
                var url = "<%=webPath%>/groupAdv/changestate.jsp?type=" + type + "&vl=" + value + "&id=" + id;
                url += "&sID=" + Math.floor(Math.random() * 10000);
                eventAction(url, divid);
            }
            $(document).ready(function() {
                $('.main3').find('div.div1').hide();
                $('input[rel*=manhungPopup]').showPopup({
                    top: 200, //khoảng cách popup cách so với phía trên
                    closeButton: ".close_popup", //khai báo nút close cho popup
                    scroll: false//, //cho phép scroll khi mở popup, mặc định là không cho phép
                            //onClose:function(){            	
                            //sự kiện cho phép gọi sau khi đóng popup, cho phép chúng ta gọi 1 số sự kiện khi đóng popup, bạn có thể để null ở đây
                            // }
                });
                //-- TAB
                // Sự kiện khi nhấn vào các tab của menu
                $("a.tab").click(function() {
                    // tắt tất cả các tab
                    $(".active").removeClass("active");
                    // bật tab đang click lên
                    $(this).addClass("active");
                    // tạo hiệu ứng trượt lên trên cho nội dung của tab đang click
                    $(".content").slideUp();
                    // Nếu là tab đầu tiên thì set hiệu ứng là trượt xuống dưới
                    var content_show = $(this).attr("tabval");
                    $("#" + content_show).slideDown();
                });
            });
        </script>
    </head>
    <body>
        <%
            if (!adminInfo.checkView(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            int totalPage = 1;
            String key = Tool.validStringRequest(request.getParameter("key"));
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            int status = Tool.string2Integer(request.getParameter("status"), GroupAdv.STATUS.SHOW_ALL.getValue());
            GroupAdv gDao = new GroupAdv();
            int totalRow = gDao.countAll(key, status);
            totalPage = (int) totalRow / Constants.ROW_PER_PAGE;
            if (totalRow % Constants.ROW_PER_PAGE != 0) {
                totalPage++;
            }
            ArrayList<GroupAdv> all = gDao.getAllGroup(currentPage, key, status);
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
                                        <th scope="col" class="rounded-q4 redBoldUp">Tìm kiếm Nhóm Quảng Cáo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>Mã Quảng cáo hoặc tiêu đề:</td>
                                        <td><input size="75" type="text" name="key"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>Trạng thái:</td>
                                        <td>
                                            <select name="status">
                                                <%
                                                    for (GroupAdv.STATUS one : GroupAdv.STATUS.values()) {
                                                %>
                                                <option value="<%=one.getValue()%>"><%=one.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td colspan="3">
                                            <input type="submit" name="submit" value="Tìm kiếm"/>
                                            <%if (adminInfo.checkAdd(request)) {%>
                                            <a href="<%=request.getContextPath() + "/sys-admin/group-adv-manager/add.html"%>">
                                                <img style="vertical-align: middle" border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/>
                                            </a>
                                            <%}%>
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
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company"><input type="checkbox"></th>
                                    <th scope="col" class="rounded">Mã</th>
                                    <th scope="col" class="rounded">Tên nhóm</th>
                                    <th scope="col" class="rounded">Mô tả</th>
                                    <th scope="col" class="rounded">Loại</th>
                                    <th scope="col" class="rounded">Kiểu hiển thị</th>
                                    <th scope="col" class="rounded">Hiển thị</th>
                                    <th scope="col" class="rounded">Trạng thái</th>
                                    <th scope="col" class="rounded">Thành phần</th>
                                    <th scope="col" class="rounded">Mã Nhúng</th>
                                    <%if (adminInfo.checkEdit(request)) {%><th scope="col" class="rounded">Edit</th><%}%>
                                    <%if (adminInfo.checkDel(request)) {%><th scope="col" class="rounded-q4">Delete</th><%}%>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1; //Bien dung de dem so dong
                                    for (Iterator<GroupAdv> iter = all.iterator(); iter.hasNext();) {
                                        GroupAdv oneGroup = iter.next();
                                %>
                                <tr>
                                    <td><input type="checkbox"/></td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><%=oneGroup.getGroupID()%></td>
                                    <td style="border-right: solid 1px #00ccff" align="left">
                                        <a target="_blank" href="<%=request.getContextPath() + "/groupAdv/view/view-ads-" + oneGroup.getGroupID() + ".html"%>"><%=oneGroup.getName()%></a>
                                    </td>
                                    <td style="border-right: solid 1px #00ccff" align="center">
                                        <%= oneGroup.getDesc()%>
                                    </td>
                                    <td style="border-right: solid 1px #00ccff" align="center">
                                        <%=GroupAdv.KIND.getName(oneGroup.getKind())%>
                                    </td>
                                    <td style="border-right: solid 1px #00ccff"><%=GroupAdv.TYPE.getName(oneGroup.getType())%></td>
                                    <td style="border-right: solid 1px #00ccff"><%=oneGroup.getShowItem()%> Item</td>
                                    <td id="status<%=oneGroup.getGroupID()%>" style="border-right: solid 1px #00ccff" align="center">
                                        <%
                                            if (oneGroup.getStatus() == 1) {
                                                if (adminInfo.checkEdit(request)) {
                                        %>
                                        <a href="" onclick="return false;"><img width="32" onclick="yourChangeState('status<%=oneGroup.getGroupID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneGroup.getStatus()%>', '<%=oneGroup.getGroupID()%>')" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
                                            <%
                                                } else {
                                                    out.print("<img width=\"20\" src=\"" + request.getContextPath() + "/resource/images/active.png\"/>");
                                                }
                                            } else if (oneGroup.getStatus() == 0) {
                                                if (adminInfo.checkEdit(request)) {
                                            %>
                                        <a href="" onclick="return false;"><img width="32" onclick="yourChangeState('status<%=oneGroup.getGroupID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneGroup.getStatus()%>', '<%=oneGroup.getGroupID()%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
                                            <%
                                                } else {
                                                    out.print("<img width=\"20\" src=\"" + request.getContextPath() + "/resource/images/lock.png\"/>");
                                                }
                                            } else {
                                            %>
                                        <img width="32"  src="<%= request.getContextPath()%>/resource/images/Recyclebin.png"/>
                                            <%}
                                            %>
                                    </td>
                                    <td align="center" style="border-right: solid 1px #00ccff"><a href="/sys-admin/group-adv-manager/item-<%=oneGroup.getGroupID()%>.html"><img width="32" title="Chi tiết QC" src="/resource/images/giohang.png" ><br/>( <span class="redBold"><%=oneGroup.getTotalAds()%></span> )</a></td>
                                    <td align="center" style="border-right: solid 1px #00ccff">
                                        <input type="button" class="button-popup" id="open_popup_<%=oneGroup.getGroupID()%>" name="open_popup" rel="manhungPopup" href="#popup_content_<%=oneGroup.getGroupID()%>" value="Mã Nhúng"/>
                                        <div id="popup_content_<%=oneGroup.getGroupID()%>" class="popup">
                                            <ul class="tabs">
                                                <li><a href="#" tabval="tab1_<%=oneGroup.getGroupID()%>" title="Code URL" class="tab active">JAVASCRIP</a></li>
                                                <li><a href="#" tabval="tab2_<%=oneGroup.getGroupID()%>" title="Code HTML" class="tab">IFRAME</a></li>
                                            </ul>
                                            <div style="border: 0px;" id="tab1_<%=oneGroup.getGroupID()%>" class="content tab1">
                                                <div style="margin-left: 10px" ><span style="font-weight: bold"> Tên nhóm:</span> <span class="redBold"><%=oneGroup.getName()%></span></div>
                                                <textarea cols="65" rows="10"><script type="text/javascript" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/link_add_code/core_script_" + oneGroup.getGroupID() + ".linkvn?w=" + oneGroup.getWidth() + "&h=" + oneGroup.getHeight()%>"></script></textarea>
                                                <div class="info_popup"><%=oneGroup.getDesc()%></div>
                                            </div>
                                            <div style="border: 0px;display: none" id="tab2_<%=oneGroup.getGroupID()%>" class="content tab2">
                                                <div style="margin-left: 10px"> Đặt mã cho plugin của bạn bất cứ nơi nào bạn muốn plugin để xuất hiện trên trang của bạn .
                                                    <br/><span style="font-weight: bold"> Tên nhóm:</span> <span class="redBold"><%=oneGroup.getName()%></span>
                                                </div>
                                                <textarea cols="65" rows="10"><iframe src="<%=BuildCache.DOMAIN + request.getContextPath() + "/link_add_code/add_box_" + oneGroup.getGroupID() + ".linkvn"%>" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:100%; height:100%" allowTransparency="true"></iframe></textarea>
                                                <div class="info_popup"><%=oneGroup.getDesc()%></div>
                                            </div>
                                        </div>
                                    </td>
                                    <%if (adminInfo.checkEdit(request)) {%><td style="border-right: solid 1px #00ccff"><a href="<%="/sys-admin/group-adv-manager/edit-" + oneGroup.getGroupID() + ".html"%>"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td><%}%>
                                    <%if (adminInfo.checkDel(request)) {%><td><a href="<%=oneGroup.getStatus() != GroupAdv.STATUS.DELETE.getValue() ? "/sys-admin/group-adv-manager/del-" + oneGroup.getGroupID() + ".html" : "/sys-admin/group-adv-manager/del-ever-" + oneGroup.getGroupID() + ".html"%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td><%}%>
                                </tr>
                                <%
                                    }
                                %>
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