<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%>
<%
    int groupID = Tool.string2Integer(request.getParameter("gid"), 0);
    session.removeAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME);
    response.sendRedirect("/sys-admin/group-adv-manager/item-" + groupID + ".html");
%>