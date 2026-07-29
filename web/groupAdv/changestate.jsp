<%@page import="gk.adv.linnk.vn.object.GroupAdv"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
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
    int type = Tool.string2Integer(request.getParameter("type"));
    GroupAdv groupDao = new GroupAdv();
    if (type == Constants.TYPE_CHANGE_STATUS) { // STATUS
        long id = Tool.string2Long(request.getParameter("id"));
        int value = Tool.string2Integer(request.getParameter("vl"));
        if ((value == -1 || value == 0) && groupDao.updateStatus(id, value)) {
%>
<a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '1', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
    <%
        }
        if (value == 1 && groupDao.updateStatus(id, value)) {
    %>
<a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=id%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '0', '<%=id%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
    <%
            }
        } else {
            out.print("error");
            return;
        }
    %>
