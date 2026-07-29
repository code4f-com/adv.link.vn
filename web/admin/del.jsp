<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
    if (!adminInfo.checkDel(request)) {
                session.setAttribute("mess", "Bạn không có quyền thêm module này!");
                response.sendRedirect(request.getContextPath() + "/sys-admin/module-manager/show.html");
                return;
            }
    String urlLog = Tool.getCurrentURL(request);
    String ip = request.getRemoteAddr();
    int id = Tool.string2Integer(request.getParameter("id"));
    Account adminDao = new Account();
    try {
        //--------------------
        if (adminDao.delete(id)) {
            session.setAttribute("mess", "Xóa Admin thành công");
            response.sendRedirect(request.getContextPath()+"/sys-admin/account-manager/show.html");
        } else {
            session.setAttribute("mess", "Xóa Admin thật bại");
            response.sendRedirect(request.getContextPath()+"/sys-admin/account-manager/show.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa thật bại");
        response.sendRedirect(request.getContextPath()+"/sys-admin/account-manager/show.html");
    }
%>