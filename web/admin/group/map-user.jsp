<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.admin.Account"%><%@page import="gk.adv.linnk.vn.admin.GroupAccDetail"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page contentType="text/html; charset=utf-8" %>

<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập để truy cập hệ thống");
        out.print("<script>top.location='" + request.getContextPath() + "/sys/login';</script>");
        return;
    } else {
        System.out.println("Admin [Adv Link.vn] user: " + adminInfo.getUserName() + "---" + DateProc.createTimestamp());
    }
    if (!adminInfo.checkEdit(request)) {
        session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    }
    int gid = RequestTool.getInt(request, "gid");
    int uid = RequestTool.getInt(request, "uid");
    GroupAccDetail gAdminDao = new GroupAccDetail();
    int[] tem = new int[1];
    tem[0] = gid;
    if (gAdminDao.create(uid, tem, adminInfo.getAccID())) {
        Permission.updateUserRole(gid, uid);
        session.setAttribute("mess", "Thêm user vào nhóm thành công");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/map-acc-" + gid + ".html");
        return;
    } else {
        session.setAttribute("mess", "Thêm user vào nhóm thất bại");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-manager/map-acc-" + gid + ".html");
    }
%>