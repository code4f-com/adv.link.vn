<%@page import="gk.adv.linnk.vn.utils.Constants"%>
<%@page import="gk.adv.linnk.vn.object.Advertise"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/customer/incl/checkLogin.jsp" %>
<%
    if (!adminInfo.checkEdit(request)) {
        session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    }
    int type = Tool.string2Integer(request.getParameter("type"));
    Advertise advDao = new Advertise();
%>
<%--
if (type == Constants.TYPE_CHANGE_HOT) { // IS HOT
    long id = Tool.string2Long(request.getParameter("id"));
    boolean value = Tool.getBooleanStatus(request.getParameter("vl"));
    advDao.updateHotArticles(id, value);
    if (!value) {
%>
<a href="" onclick="return false"><img onclick="yourChangeState('hot<%=id%>','<%= Constants.TYPE_CHANGE_HOT%>','true','<%=id%>')" src="<%= request.getContextPath()%>/admin/resource/images/active.png"/></a>
<%
} else {
%>
<a href="" onclick="return false"><img onclick="yourChangeState('hot<%=id%>','<%= Constants.TYPE_CHANGE_HOT%>','false','<%=id%>')" src="<%= request.getContextPath()%>/admin/resource/images/lock.png"/></a>
<%
    }
}

else if (type == Constants.TYPE_CHANGE_TOP) {
    long id = Tool.string2Long(request.getParameter("id"));
    boolean value = Tool.getBooleanStatus(request.getParameter("vl"));
    advDao.updateTOPArticles(id, value);
    if (!value) {
%>
<a href="" onclick="return false"><img onclick="yourChangeState('top<%=id%>','<%= Constants.TYPE_CHANGE_TOP%>','true','<%=id%>')" src="<%= request.getContextPath()%>/admin/resource/images/active.png"/></a>
<%
} else {
%>
<a href="" onclick="return false"><img onclick="yourChangeState('top<%=id%>','<%= Constants.TYPE_CHANGE_TOP%>','false','<%=id%>')" src="<%= request.getContextPath()%>/admin/resource/images/lock.png"/></a>
<%
    }
} else 
--%>
<%
    if (type == Constants.TYPE_CHANGE_STATUS) { // STATUS
        long id = Tool.string2Long(request.getParameter("id"));
        int value = Tool.string2Integer(request.getParameter("vl"));
        if ((value == -1 || value == 0) && advDao.updateStatus(id, value)) {
%>
<a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '1', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
    <%
        }
        if (value == 1 && advDao.updateStatus(id, value)) {
    %>
<a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '0', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
    <%
            }
        } else {
            out.print("error");
            return;
        }
    %>
