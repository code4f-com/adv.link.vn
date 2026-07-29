<%@page import="gk.adv.linnk.vn.admin.AccSiteManager"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head><%@include file="/includes/header.jsp" %></head>
    <body>
        <%  int uid = RequestTool.getInt(request, "id");
            Account oneAdmin = new Account();
            oneAdmin = oneAdmin.getByID(uid);
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }%>
                        </div>
                        <!--Content-->
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">Tên đăng nhập</th>
                                    <th scope="col" class="rounded">Tên</th>
                                    <th scope="col" class="rounded">Quyền</th>
                                    <th scope="col" class="rounded">Email</th>
                                    <th scope="col" class="rounded">User Type</th>
                                    <th scope="col" class="rounded">Trạng thái</th>
                                    <th scope="col" class="rounded">Edit</th>
                                    <th scope="col" class="rounded-q4">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1;
                                    //Bien dung de dem so dong
                                %>
                                <tr>
                                    <td><%=count++%></td>
                                    <td align="center"><%=oneAdmin.getUserName()%></td>
                                    <td align="center"><%= oneAdmin.getFullName()%></td>
                                    <td class="redBold" align="center">(<%=Account.getTypeName(oneAdmin.getUserType())%>)</td>
                                    <td><%=oneAdmin.getEmail()%></td>
                                    <td><%=oneAdmin.getUserType() == Account.TYPE.ADMIN.val ? "Quản tri hệ thống" : "Người dùng"%></td>
                                    <td align="center">
                                        <%
                                            if (oneAdmin.getStatus() == 1) {
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
                                    <td><a href="/sys-admin/customer-user/edit-<%=oneAdmin.getAccID()%>.html"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                    <td><a href="/sys-admin/customer-user/del-<%=oneAdmin.getAccID()%>.html" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
                                </tr>
                                <tr align="center">
                                    <td colspan="10">
                                        <%
                                            ArrayList<AccSiteManager> all = oneAdmin.getSiteManager();
                                            for (AccSiteManager one : all) {
                                        %>
                                        <%=one.getDomain()%><input onclick="return false;" type="checkbox" name="domain" value="1" checked="checked"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <%
                                            }
                                        %>
                                    </td>
                                </tr>
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