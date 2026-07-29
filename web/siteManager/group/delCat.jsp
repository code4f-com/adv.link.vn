<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập hệ thống");
        response.sendRedirect(request.getContextPath() + "/sys/login");
        return;
    }
    int cid = RequestTool.getInt(request, "cid");
    SiteGroup catDao = new SiteGroup();
    if (catDao.delEver(cid)) {
        out.println("<script type='text/javascript'>parent.menu.location.reload();</script>");
        return;
    } else {
        out.println("<script type='text/javascript'>alert('Xóa dữ liệu thất bại');</script>");
    }
%>
