<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
//    Account adminInfo = (Account) session.getAttribute("adminInfo");
//    if (adminInfo == null) {
//        out.print("<H1>BAN KHONG CO QUYEN HIEN THI TRANG NAY<H1>");
//        System.out.println("PREVIEW:" + request.getHeader("referer"));
//        return;
//    } else {
    int groupID = RequestTool.getInt(request, "gid");
    System.out.println("PREVIEW:" + groupID + "|" + request.getHeader("referer"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <header>
        <title>Preview</title>
    </header>
    <body>
        <div style="font-weight: bold" align="center">
            <script type="text/javascript" src="/link_add_code/core_script_<%=groupID%>.linkvn?w=0&h=0"></script>
        </div>
    </body>
</html>
<%
//    }

%>


