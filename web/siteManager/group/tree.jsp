
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page contentType="text/xml; charset=utf-8" %>
<% try {
        SiteGroup menuDao = new SiteGroup();
        String sType = request.getParameter("type");
        if (sType == null || sType.length() == 0) {
            sType = "Tin tức";
        }
        out.print(menuDao.getTree(sType));
        return;
    } catch (Exception ex) {
        ex.printStackTrace();
    }%>
