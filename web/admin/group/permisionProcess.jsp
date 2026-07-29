
<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%><%@page contentType="text/html; charset=utf-8" %>
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
    int gid = RequestTool.getInt(request, "gid");
    String[] arrSpecial = request.getParameterValues("special");
    String[] arrView = request.getParameterValues("view");
    String[] arrAdd = request.getParameterValues("add");
    String[] arrEdit = request.getParameterValues("edit");
    String[] arrDel = request.getParameterValues("del");
    Permission perDao = new Permission();
    // Special
    perDao.cleanRoleGroup(gid, Permission.PER.SPECIAL.val);
    perDao.mapRoleGroup(arrSpecial, Permission.PER.SPECIAL.val);
    // Xem
    perDao.cleanRoleGroup(gid, Permission.PER.VIEW.val);
    perDao.mapRoleGroup(arrView, Permission.PER.VIEW.val);
    // Them
    perDao.cleanRoleGroup(gid, Permission.PER.ADD.val);
    perDao.mapRoleGroup(arrAdd, Permission.PER.ADD.val);
    // Sua
    perDao.cleanRoleGroup(gid, Permission.PER.EDIT.val);
    perDao.mapRoleGroup(arrEdit, Permission.PER.EDIT.val);
    // Xoa
    perDao.cleanRoleGroup(gid, Permission.PER.DEL.val);
    perDao.mapRoleGroup(arrDel, Permission.PER.DEL.val);
    // Update User Role Of Group
    Permission.updateRoleUserOfGroup(gid);
    session.setAttribute("mess", "Phâm quyền cho nhóm thành công");
    response.sendRedirect(request.getContextPath() + "/sys-admin/group-role/" + gid + "-show.html");
%>