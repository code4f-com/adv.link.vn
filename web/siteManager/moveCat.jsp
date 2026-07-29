<%@page import="gk.adv.linnk.vn.object.SiteManager"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.admin.Account"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập hệ thống");
        response.sendRedirect(request.getContextPath() + "/sys/login");
        return;
    }
    int catID = Tool.string2Integer(request.getParameter("cid"));
    try {
        String[] sID = request.getParameterValues("chkmove");
        if (sID == null || sID.length <= 0) {
%>
<script type="text/javascript">
    alert("Bạn cần chọn ít nhất một nội dung để chuyển chuyên mục!");
    location.href = "sitemanager.jsp?cid=<%=catID%>";
</script>
<%
            return;
        }
        String strAllid = "";
        int[] allID = new int[sID.length];
        for (int i = 0; i < sID.length; i++) {
            allID[i] = Integer.parseInt(sID[i]);
            strAllid += allID[i] + ",";
        }
        //------------LOG -------------------
        SiteManager newsDao = new SiteManager();
        if (newsDao.moveSite(catID, allID)) {
            session.setAttribute("mess", "Di chuyển dữ liệu thành công!");
            out.print("<script>parent.location.href='/sys-admin/site-manager/show.html?cid=" + catID + "'</script>");
        } else {
            session.setAttribute("mess", "Di chuyển dữ liệu thất bại!");
            out.print("<script>parent.location.href='/sys-admin/site-manager/show.html?cid=" + catID + "'</script>");
        }
    } catch (Exception ex) {
        session.setAttribute("mess", "Di chuyển dữ liệu thất bại!");
        out.print("<script>parent.location.href='/sys-admin/site-manager/show.html?cid=" + catID + "'</script>");
    }
%>