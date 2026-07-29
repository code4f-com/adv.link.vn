
<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page contentType="text/html; charset=utf-8" %>
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/includes/checkLogin.jsp" %>
<%
    if (!adminInfo.checkRight("/sys-admin/group-adv-manager/", Permission.PER.EDIT.val)) {
        session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    }
    int adsId = RequestTool.getInt(request, "adsid");
    int groupid = RequestTool.getInt(request, "gid");
    int type = RequestTool.getInt(request, "type");
    MapGroup mapGroupDao = new MapGroup();
    if (type == 1) { // Xoa tat
        if (mapGroupDao.removeAllAds(groupid)) {
            session.setAttribute("mess", "Xóa Quảng Trong nhom =" + groupid + " Thành Công!");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/item-" + groupid + ".html");
            return;
        } else {
            session.setAttribute("mess", "Xóa Quảng Trong nhom =" + groupid + " Thất bại!");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/item-" + groupid + ".html");
        }
    } else {
        if (mapGroupDao.removeAds(groupid, adsId)) {
            session.setAttribute("mess", "Xóa Quảng Cáo ID =" + adsId + " Thành Công!");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/item-" + groupid + ".html");
            return;
        } else {
            session.setAttribute("mess", "Xóa Quảng Cáo ID =" + adsId + " Thất bại!");
            response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/item-" + groupid + ".html");
        }
    }
%>
