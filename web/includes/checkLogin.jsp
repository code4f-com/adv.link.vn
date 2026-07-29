<%@page contentType="text/html; charset=utf-8" %>
<%
    try {
        if (adminInfo == null) {
            session.setAttribute("error", "Bạn cần đăng nhập hệ thống");
            response.sendRedirect(request.getContextPath() + "/sys/login");
            return;
        }
    } catch (Exception ex) {
        System.out.println("Vao Exception CheckLogin:" + ex.getMessage());
        out.print("<script>top.location='" + request.getContextPath() + "/sys/login';</script>");
        return;
    }
%>
<div class="header">
    <div class="logo"><a href="#"><img src="<%= request.getContextPath()%>/resource/images/logo.png" alt="" title="" border="0" /></a></div>
    <div class="right_header">Welcome: <a href="<%= request.getContextPath()%>/thay-doi-mat-khau"><b><%=(adminInfo.getUserName() != null) ? adminInfo.getUserName() : ""%></b></a> | <a href="<%= request.getContextPath()%>/out.jsp" class="logout">Thoát</a></div>
    <div id="clock_a"></div>
</div>
