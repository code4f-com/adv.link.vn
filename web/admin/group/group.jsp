<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.admin.Groups"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%
            if (!adminInfo.checkView(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            ArrayList all = null;
            Groups dao = new Groups();
            all = dao.listAllGroups();
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <form name="search" method="post" action="/sys-admin/group-manager/show.html">
                            <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th colspan="2" scope="col" class="rounded-q4"><span style="text-align: center;font-weight: bold;color: blueviolet;margin-right: 10px">QUẢN LÝ NHÓM QUẢN TRỊ</span></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>Tên nhóm</td>
                                        <td><input size="70" type="text" value="" name="name"/></td>
                                    </tr>
                                    <tr>
                                        <td align="center" colspan="3">
                                            <input type="submit" name="submit" value="Tìm kiếm"/>
                                            <a href="<%=request.getContextPath() + "/sys-admin/group-manager/add.html"%>"><img border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/></a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                                boolean rightDel = adminInfo.checkDel(request);
                                boolean rightEdit = adminInfo.checkEdit(request);
                            %>
                        </div>
                        <!--Content-->
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">Group Name</th>
                                    <th scope="col" class="rounded">Group Desc</th>
                                    <th scope="col" class="rounded">Trạng thái</th>
                                    <th scope="col" class="rounded">Users</th>
                                    <th scope="col" class="rounded">Role</th>
                                    <%if (rightEdit) {%><th scope="col" class="rounded">Edit</th><%}%>
                                    <%if (rightDel) {%><th scope="col" class="rounded-q4">Delete</th><%}%>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1; //Bien dung de dem so dong
                                    for (Iterator<Groups> iter = all.iterator(); iter.hasNext();) {
                                        Groups oneGroup = iter.next();
                                %>
                                <tr>
                                    <td><%=count++%></td>
                                    <td align="center">
                                        <%=oneGroup.getName()%>
                                    </td>
                                    <td align="center">
                                        <%= oneGroup.getDescription()%>
                                    </td>
                                    <td align="center"><%
                                        if (oneGroup.getStatus()) {
                                        %>
                                        <img src="<%= request.getContextPath()%>/resource/images/active.png"/>
                                        <%
                                        } else {
                                        %>
                                        <img src="<%= request.getContextPath()%>/resource/images/key_lock.png"/>
                                        <%
                                            }
                                        %>
                                    </td>
                                    <td><a href="<%="/sys-admin/group-manager/map-acc-" + oneGroup.getGroupID() + ".html"%>">User</a></td>
                                    <td><a href="<%="/sys-admin/group-role/" + oneGroup.getGroupID() + "-show.html"%>">Quyền</a></td>
                                    <%if (rightEdit) {%><td><a href="<%="/sys-admin/group-manager/edit-" + oneGroup.getGroupID() + ".html"%>"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td><%}%>
                                    <%if (rightDel) {%><td><a href="<%="/sys-admin/group-manager/del-" + oneGroup.getGroupID() + ".html"%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td><%}%>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>