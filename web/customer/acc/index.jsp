<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%            ArrayList all = null;
            Account dao = new Account();
            all = dao.listAllUser(null);
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                            %>
                        </div>
                        <div align="center" style="height: 20px;margin-bottom: 12px;margin-top: 10px">
                            <a href="<%=request.getContextPath() + "/sys-admin/customer-user/add.html"%>"><img border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/></a>
                        </div>
                        <!--Content-->
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">Tên đăng nhập</th>
                                    <th scope="col" class="rounded">Họ tên</th>
                                    <th scope="col" class="rounded">Điện thoại</th>
                                    <th scope="col" class="rounded">Email</th>
                                    <th scope="col" class="rounded">User Type</th>
                                    <th scope="col" class="rounded">Trạng thái</th>
                                    <th scope="col" class="rounded">Quyền</th>
                                    <th scope="col" class="rounded">Edit</th>
                                    <th scope="col" class="rounded-q4">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1; //Bien dung de dem so dong
                                    for (Iterator<Account> iter = all.iterator(); iter.hasNext();) {
                                        Account oneAdmin = iter.next();
                                %>
                                <tr>
                                    <td><%=count++%></td>
                                    <td align="center">
                                        <%=oneAdmin.getUserName()%>
                                    </td>
                                    <td align="center">
                                        <%= oneAdmin.getFullName()%>
                                    </td>
                                    <td align="center">
                                        <%=oneAdmin.getPhone()%>
                                    </td>
                                    <td><%=oneAdmin.getEmail()%></td>
                                    <td><%=Account.getTypeName(oneAdmin.getUserType())%></td>
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
                                    <td><a href="/sys-admin/customer-user/role-<%=oneAdmin.getAccID()%>.html">Quyền</a></td>
                                    <td><a href="/sys-admin/customer-user/edit-<%=oneAdmin.getAccID()%>.html"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                    <td><a href="/sys-admin/customer-user/del-<%=oneAdmin.getAccID()%>.html" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
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