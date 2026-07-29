<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="java.util.Enumeration"%><%@page import="gk.adv.linnk.vn.admin.LogAction"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String webPath = request.getContextPath();
    //--------------- Admin
    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("mess", "Account not exist, Access Deny!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    } else {
        // Khong null
        if (adminInfo.getUserType() == Account.TYPE.NOROLE.val) {
            session.setAttribute("mess", "Permission not Allow, Access Deny!");
            response.sendRedirect(request.getContextPath() + "/sys");
            return;
        }
        System.out.println("Customer ["+BuildCache.DOMAIN+"] user: " + adminInfo.getUserName() + "---" + DateProc.createTimestamp());
    }
    //---------PAGE SETING----------------
    String pageURL = "";
    Enumeration paraList = null;
    pageURL = Tool.getCurrentURL(request) + "?";
    paraList = request.getParameterNames();
    while (paraList.hasMoreElements()) {
        String paraName = String.valueOf(paraList.nextElement());
        if (!paraName.equalsIgnoreCase("page") && !paraName.equalsIgnoreCase("submit") && !paraName.equalsIgnoreCase("module")) {
            pageURL += paraName + "=" + request.getParameter(paraName) + "&amp;";
        }
    }
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Customer ads.link.vn</title>
<link rel="Shortcut Icon" href="<%= request.getContextPath() + "/resource/images/logo.ico"%>" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/style.css")%>" />
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/jquery.alerts.css")%>" />
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/clockp.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/clockh.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ddaccordion.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ImageOnMouse.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/ajax.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery.alerts.js"></script>
<script type="text/javascript">
    ddaccordion.init({
        headerclass: "submenuheader", //Shared CSS class name of headers group
        contentclass: "submenu", //Shared CSS class name of contents group
        revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
        mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
        collapseprev: true, //Collapse previous content (so only one open at any time)? true/false
        defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
        onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
        animatedefault: false, //Should contents open by default be animated into view?
        persiststate: true, //persist state of opened contents within browser session?
        toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
        togglehtml: ["suffix", "<img src='<%= request.getContextPath()%>resource/images/plus.gif' class='statusicon' />", "<img src='<%= request.getContextPath()%>resource/images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
        animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
        oninit: function(headers, expandedindices) { //custom code to run when headers have initalized
            //do nothing
        },
        onopenclose: function(header, index, state, isuseractivated) { //custom code to run whenever a header is opened or closed
            //do nothing
        }
    })
</script>
<script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jconfirmaction.jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $('.ask').jConfirmAction();
    });
</script>

