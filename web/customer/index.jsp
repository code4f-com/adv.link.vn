<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <%@include file="/customer/incl/header.jsp" %>
    </head>
    <body>
        <div id="main_container">
            <div class="header">
                <div class="logo"><a href="#"><img src="<%= request.getContextPath()%>/resource/images/logo.png" alt="" title="" border="0" /></a></div>
                <div class="right_header">Welcome: <a href="<%= request.getContextPath()%>/thay-doi-mat-khau"><b><%=(adminInfo.getUserName() != null) ? adminInfo.getUserName() : ""%></b></a> | <a href="<%= request.getContextPath()%>/customer/logout" class="logout">Thoát</a></div>
                <div id="clock_a"></div>
            </div>
            <div class="main_content">
                <%@include file="/customer/incl/menu.jsp" %>
                <div class="clear"></div>
                <div align="center" style="height: 20px;margin-top: 50px;color: red;font-weight: bold">
                    <%
                        if (session.getAttribute("mess") != null) {
                            out.print(session.getAttribute("mess"));
                            session.removeAttribute("mess");
                        }%>
                </div>
            </div><!--end of main content-->
            <%@include file="/customer/incl/footer.jsp" %>
        </div>
    </body>
</html>