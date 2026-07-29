<%@page import="gk.adv.linnk.vn.object.GroupAdv"%>
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
    String urlLog = Tool.getCurrentURL(request);
    String ip = request.getRemoteAddr();
    int id = Tool.string2Integer(request.getParameter("id"));
    GroupAdv groupDao = new GroupAdv();
    try {
        //--------------------
        if (groupDao.del404(id)) {
            session.setAttribute("mess", "Xóa Group QC thành công");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
        } else {
            session.setAttribute("mess", "Xóa Group AC thất bại");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa thật bại");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
    }
%>