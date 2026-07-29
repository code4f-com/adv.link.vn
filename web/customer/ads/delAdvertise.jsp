
<%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
%>
<%@include file="/customer/incl/checkLogin.jsp" %>
<%
    String urlLog = Tool.getCurrentURL(request);
    String ip = request.getRemoteAddr();
    int id = Tool.string2Integer(request.getParameter("id"));
    Advertise advDao = new Advertise();
    try {
        //--------------------
        if (advDao.delete404(id)) {
            session.setAttribute("mess", "Xóa QC Thành Công");
            response.sendRedirect(request.getContextPath() + "/customer/ads/manager.html");
        } else {
            session.setAttribute("mess", "Xóa QC thật bại");
            response.sendRedirect(request.getContextPath() + "/customer/ads/manager.html");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa thật bại");
        response.sendRedirect(request.getContextPath() + "/customer/ads/manager.html");
    }
%>