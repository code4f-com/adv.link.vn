<%@page import="gk.adv.linnk.vn.admin.Modules"%>
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
        session.setAttribute("mess", "Bạn không có quyền xóa modul này!");
        response.sendRedirect(request.getContextPath() + "/sys-admin/module-manager/show.html");
        return;
    }
    int id = Tool.string2Integer(request.getParameter("id"));
    Modules gDao = new Modules();
    try {
        //--------------------
        if (gDao.del(id)) {
            session.setAttribute("mess", "Xóa Module thành công");
            response.sendRedirect("/sys-admin/module-manager/show.html");
        } else {
            session.setAttribute("mess", "Xóa Module thật bại");
            response.sendRedirect("/sys-admin/module-manager/show.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa Module thật bại");
        response.sendRedirect("/sys-admin/module-manager/show.html");
    }
%>