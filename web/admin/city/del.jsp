<%@page import="gk.adv.linnk.vn.object.City"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
    String urlLog = Tool.getCurrentURL(request);
    String ip = request.getRemoteAddr();
    int id = Tool.string2Integer(request.getParameter("id"));
    City adminDao = new City();
    try {
        //--------------------
        if (adminDao.del(id)) {
            session.setAttribute("mess", "Xóa Admin thành công");
            response.sendRedirect(request.getContextPath() + "/admin/city/index.jsp");
        } else {
            session.setAttribute("mess", "Xóa Admin thật bại");
            response.sendRedirect(request.getContextPath() + "/admin/city/index.jsp");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa thật bại");
        response.sendRedirect(request.getContextPath() + "/admin/city/index.jsp");
    }
%>