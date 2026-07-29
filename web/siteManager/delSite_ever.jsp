<%@page import="gk.adv.linnk.vn.object.SiteManager"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập hệ thống");
        response.sendRedirect(request.getContextPath() + "/sys/login");
        return;
    }
    String urlLog = Tool.getCurrentURL(request);
    String ip = request.getRemoteAddr();
    int id = Tool.string2Integer(request.getParameter("id"));
    SiteManager oneSite = new SiteManager();
    try {
        //--------------------
        if (oneSite.delever(id)) {
            session.setAttribute("mess", "Xóa Site thành công");
            out.print("<script>top.location.href = '" + request.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
        } else {
            session.setAttribute("mess", "Xóa Site thật bại");
            out.print("<script>top.location.href = '" + request.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        session.setAttribute("mess", "Xóa thật bại");
        out.print("<script>top.location.href = '" + request.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
    }
%>