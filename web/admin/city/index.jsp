<%@page import="gk.adv.linnk.vn.object.City"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%            ArrayList all = null;
            City dao = new City();
            all = dao.getAll(1, null);
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
                            <a href="<%=request.getContextPath() + "/admin/city/add.jsp"%>"><img border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/></a>
                        </div>
                        <!--Content-->
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">MyCode</th>
                                    <th scope="col" class="rounded">MyName</th>
                                    <th scope="col" class="rounded">Vùng Miền</th>
                                    <th scope="col" class="rounded">Google Code</th>
                                    <th scope="col" class="rounded">Google Name</th>
                                    <th scope="col" class="rounded">Edit</th>
                                    <th scope="col" class="rounded-q4">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1; //Bien dung de dem so dong
                                    for (Iterator<City> iter = all.iterator(); iter.hasNext();) {
                                        City oneAdmin = iter.next();
                                %>
                                <tr>
                                    <td><%=count++%></td>
                                    <td align="center">
                                        <%=oneAdmin.getMyCode()%>
                                    </td>
                                    <td align="center">
                                        <%= oneAdmin.getMyname()%>
                                    </td>
                                    <td align="center">
                                        <%=buildRegion(oneAdmin.getRegion()) %>
                                    </td>
                                    <td align="center">
                                        <%=oneAdmin.getGgCode()%>
                                    </td>
                                    <td><%=oneAdmin.getGgName()%></td>
                                    <td><a href="/admin/city/edit.jsp?id=<%=oneAdmin.getId()%>"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                    <td><a href="/admin/city/del.jsp?id=<%=oneAdmin.getId()%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
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
<%!
    String buildRegion(String code) {
        String reuslt = "Toàn Quốc";
        if (code.equals("MB")) {
            reuslt = "Miền Bắc";
        }
        if (code.equals("MT")) {
            reuslt = "Miền Trung";
        }
        if (code.equals("MN")) {
            reuslt = "Miền Nam";
        }
        return reuslt;
    }
%>