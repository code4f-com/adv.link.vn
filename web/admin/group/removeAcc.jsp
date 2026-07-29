<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.admin.GroupAccDetail"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
    if (!adminInfo.checkEdit(request)) {
        session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    }
    int uid = RequestTool.getInt(request, "uid");
    int gid = RequestTool.getInt(request, "gid");
    GroupAccDetail gDetail = new GroupAccDetail();
    if (gDetail.removeAcc(gid, uid)) {
        Permission.removeUserRole(gid, uid);
        session.setAttribute("mess", "Xóa Tài khoản khỏi nhóm thành Công!");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/map-acc-" + gid + ".html");
        return;
    } else {
        session.setAttribute("mess", "Xóa Tài khoản khỏi nhóm thất bại!");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/map-acc-" + gid + ".html");
    }
%>
