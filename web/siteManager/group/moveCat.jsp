<%@page import="gk.media.taichinhinfo.com.article.ArticleCats"%>
<%@page import="gk.media.taichinhinfo.com.utils.Tool"%>
<%@page import="gk.media.taichinhinfo.com.admin.Rights"%>
<%@page import="gk.media.taichinhinfo.com.admin.Admins"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    Admins adminInfo = (Admins) session.getAttribute("adminInfo");
    ArrayList sysRights = new ArrayList();
    sysRights.add(Rights.MANAGER_ARTICLE_VALUE);
    sysRights.add(Rights.ADMINISTRATOR_VALUE);
%>
<%@include file="/admin/includes/checkLogin.jsp" %>
<%
    int catID = Tool.string2Integer(request.getParameter("catid"));
    try {
        String[] sID = request.getParameterValues("chkmove");
        if (sID == null || sID.length <= 0) {
%>
<script type="text/javascript">
    alert("Bạn cần chọn ít nhất một nội dung để chuyển chuyên mục!");
    location.href="subindex.jsp?cid=<%=catID%>";
</script>
<%
            return;
        }
        int[] allID = new int[sID.length];
        for (int i = 0; i < sID.length; i++) {
            allID[i] = Integer.parseInt(sID[i]);
        }
        ArticleCats newsDao = new ArticleCats();
        if (newsDao.moveItemToCat(catID, allID)) {
            session.setAttribute("mess", "Di chuyển dữ liệu thành công!");
            response.sendRedirect(request.getContextPath() + "/news/subindex.jsp?cid=" + catID);
            return;
        } else {
            session.setAttribute("mess", "Di chuyển dữ liệu thất bại!");
            response.sendRedirect(request.getContextPath() + "/news/subindex.jsp?cid=" + catID);
            return;
        }
    } catch (Exception ex) {
        session.setAttribute("mess", "Di chuyển dữ liệu thất bại!");
        response.sendRedirect(request.getContextPath() + "/news/subindex.jsp?cid=" + catID);
        return;
    }
%>