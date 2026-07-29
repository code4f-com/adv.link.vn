<%@page contentType="text/html; charset=utf-8" %>
<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page autoFlush="true" import="gk.adv.linnk.vn.admin.Account"%>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
    if (!adminInfo.checkRight("/sys-admin/account-manager/",Permission.PER.EDIT.val)) {
        session.setAttribute("mess", "Bạn không có quyền thêm module này!");
        response.sendRedirect(request.getContextPath() + "/sys-admin/module-manager/show.html");
        return;
    }
    int accId = RequestTool.getInt(request, "uid");
    String[] arrSpecial = request.getParameterValues("special");
    String[] arrView = request.getParameterValues("view");
    String[] arrAdd = request.getParameterValues("add");
    String[] arrEdit = request.getParameterValues("edit");
    String[] arrDel = request.getParameterValues("del");
    Permission perDao = new Permission();
    // Special
    if (arrSpecial != null && arrSpecial.length >= 0) {
        perDao.cleanUserRole(accId, Permission.PER.SPECIAL.val);
        perDao.mapUsertRole(arrSpecial, Permission.PER.SPECIAL.val);
    } else {
        perDao.cleanUserRole(accId, Permission.PER.SPECIAL.val);
    }
    // Xem
    if (arrView != null && arrView.length >= 0) {
        perDao.cleanUserRole(accId, Permission.PER.VIEW.val);
        perDao.mapUsertRole(arrView, Permission.PER.VIEW.val);
    } else {
        perDao.cleanUserRole(accId, Permission.PER.VIEW.val);
    }
    // Them
    if (arrAdd != null && arrAdd.length >= 0) {
        perDao.cleanUserRole(accId, Permission.PER.ADD.val);
        perDao.mapUsertRole(arrAdd, Permission.PER.ADD.val);
    } else {
        perDao.cleanUserRole(accId, Permission.PER.ADD.val);
    }
    // Sua
    if (arrEdit != null && arrEdit.length >= 0) {
        perDao.cleanUserRole(accId, Permission.PER.EDIT.val);
        perDao.mapUsertRole(arrEdit, Permission.PER.EDIT.val);
    } else {
        perDao.cleanUserRole(accId, Permission.PER.EDIT.val);
    }
    // Xoa
    if (arrDel != null && arrDel.length >= 0) {
        perDao.cleanUserRole(accId, Permission.PER.DEL.val);
        perDao.mapUsertRole(arrDel, Permission.PER.DEL.val);
    } else {
        perDao.cleanUserRole(accId, Permission.PER.DEL.val);
    }
    session.setAttribute("mess", "Cấp quyền cho user thành công!");
    response.sendRedirect(request.getContextPath() + "/sys-admin/account-manager/role-" + accId + ".html");
%>