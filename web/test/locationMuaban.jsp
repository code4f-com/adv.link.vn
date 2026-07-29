<%@page import="gk.adv.linnk.vn.object.MyLocation"%>
<%
    String lct = request.getParameter("lct");
    String json = request.getParameter("json");
    MyLocation local = MyLocation.fromJson(json);
    local.setChoiceCity(lct);
    MyLocation.LogLocation(local);
    out.print("Nhan Data OK - Thanks!");
%>