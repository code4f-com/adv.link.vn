<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.utils.DateProc"%>
<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="java.util.ArrayList"%>
<%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page contentType="text/html; charset=utf-8" %>
<%!
    private ArrayList<String> ArrayToList(String[] all) {
        ArrayList list = new ArrayList();
        if (all == null || all.length <= 0) {
            Tool.Debug("Ma San Pham Da chon null or empty");
        } else {
            for (String one : all) {
                list.add(one);
            }
        }
        return list;
    }
%>
<%
    Account adminInfo = (Account) session.getAttribute("adminInfo");
    if (adminInfo == null) {
        session.setAttribute("error", "Bạn cần đăng nhập để truy cập hệ thống");
        out.print("<script>top.location='" + request.getContextPath() + "/sys/login';</script>");
        return;
    } else {
        System.out.println("Admin [Adv Link.vn] user: " + adminInfo.getUserName() + "---" + DateProc.createTimestamp());
    }
    if (!adminInfo.checkRight("/sys-admin/group-adv-manager/", Permission.PER.EDIT.val)) {
        session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
        response.sendRedirect(request.getContextPath() + "/sys");
        return;
    }
    String[] allVal = request.getParameterValues("chkmove");
    int gid = RequestTool.getInt(request, "gid");
    MapGroup map = new MapGroup();
    map.setGroupID(gid);
    ArrayList<String> adsId = ArrayToList(allVal);
    map.setAdsID(adsId);
    MapGroup mapDao = new MapGroup();
    if (mapDao.mapGroup(map)) {
        session.setAttribute("mess", "Thêm sản phầm vào Group Thành Công!");
        session.removeAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME);
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
        return;
    } else {
        session.setAttribute("mess", "Thêm sản phầm vào Group Thất bại!");
        response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
    }
%>
