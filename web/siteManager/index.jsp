<%@ page contentType="text/html; charset=utf-8" %>
<%@ page errorPage="/error.jsp"%>
<%@page buffer="none" autoFlush="true" %>
<html>
    <head>
        <title>ADMIN PANEL</title>
    </head>
    
    <frameset cols="15%,*" id="parent" name="parent">
        <frame src="<%= request.getContextPath()%>/siteManager/group/leftmenu.jsp?type=Nhom-Site"  scrolling="no" target="main" id="menu" name="menu">
            <frame src="<%= request.getContextPath()%>/siteManager/sitemanager.jsp" id="main" name="main">
                </frameset>
   
</html>