<%@page import="gk.adv.linnk.vn.object.SiteManager"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập hệ thống");
        response.sendRedirect(request.getContextPath() + "/sys/login");
        return;
    }
    int type = Tool.string2Integer(request.getParameter("type"));
    SiteManager siteDao = new SiteManager();
    if (type == Constants.TYPE_CHANGE_STATUS) { // STATUS
        System.out.println("vao 1");
        long id = Tool.string2Long(request.getParameter("id"));
        int value = Tool.string2Integer(request.getParameter("vl"));
        if ((value == -1 || value == 0) && siteDao.updateStatus(id, value)) {
            System.out.println("vao 2");
%>
<a href="" onclick="return false"><img onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '1', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/active.png"/></a>
    <%
        }
        if (value == 1 && siteDao.updateStatus(id, value)) {
            System.out.println("vao 3");
    %>
<a href="" onclick="return false"><img onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '0', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/key_lock.png"/></a>
    <%
            }
        } else {
            out.print("error");
            return;
        }
    %>
