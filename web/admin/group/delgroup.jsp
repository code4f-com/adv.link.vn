<%@page import="gk.adv.linnk.vn.admin.Groups"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
     if (!adminInfo.checkDel(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
    int id = Tool.string2Integer(request.getParameter("id"));
    Groups gDao = new Groups();
    try {
        //--------------------
        if (gDao.del(id)) {
            session.setAttribute("mess", "Xóa Group thành công");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/show.html");
        } else {
            session.setAttribute("mess", "Xóa Group thật bại");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/show.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa Group thật bại");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/show.html");
    }
%>