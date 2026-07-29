<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Account acountInfo = (Account) session.getAttribute("adminInfo");
    if (acountInfo == null) {
        out.print("<script>top.location='" + request.getContextPath() + "/customer';</script>");
        return;
    }
    // End loging
    session.removeAttribute("adminInfo");
    session.invalidate();
    out.print("<script>top.location='" + request.getContextPath() + "/customer';</script>");
%>