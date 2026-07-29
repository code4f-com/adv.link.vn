<%@page import="gk.adv.linnk.vn.admin.Permission"%>
<%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="gk.adv.linnk.vn.utils.Tool"%><%@page import="java.util.Enumeration"%><%@page import="org.apache.log4j.Logger"%><%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<html >
    <head>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery.alerts.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/jquery.alerts.css")%>" />
    </head>
    <body>
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
            Logger logger = Logger.getLogger(MapGroup.class);
            int groupID = Tool.string2Integer(request.getParameter("gid"), 0);
            String adsID = Tool.validStringRequest(request.getParameter("aid"));
            if (adsID.equals("0")) {
                out.print("<script type='text/javascript'>jAlert('Quảng cáo bạn chọn không hợp lệ!','Thông báo',function(r){"
                        + "if(r){location.href='" + request.getContextPath() + "/sys-admin/group-adv-manager/item-" + groupID + ".html'}"
                        + "});"
                        + "</script>");
            }
            GroupAdv addGroup = new GroupAdv();
            addGroup = addGroup.getByID(groupID);
            if (addGroup == null) {
                out.print("<script type='text/javascript'>jAlert('Bạn chưa chọn nhóm để thêm QC<br/>Hoặc QC bạn chọn không hợp lệ!','Thông báo',function(r){"
                        + "if(r){location.href='" + request.getContextPath() + "/sys-admin/group-adv-manager/show.html'}"
                        + "});"
                        + "</script>");
            } else {
                try {
                    MapGroup map = (MapGroup) session.getAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME);
                    if (map != null) {
                        // Da co Trong session So sanh voi GrooupID hien tai de xu ly
                        if (map.getGroupID() == groupID) {
                            // Dang xu ly Dung session
                            // Check xem advertise co trong Group nay chua
                            if (!checkExit(adsID, map)) {
                                // Khong co thi add Vao
                                ArrayList<String> item = map.getAdsID();
                                item.add(adsID);
                                map.setAdsID(item);
                                session.setAttribute("mess", "Thêm Quảng cáo vào Nhóm: " + map.getGroupName() + " thành công");
                                response.sendRedirect("/sys-admin/group-adv-manager/item-" + groupID + ".html");
                                return;
                            } else {
                                response.sendRedirect("/sys-admin/group-adv-manager/item-" + groupID + ".html");
                                return;
                            }
                        } else {
                            // Xu ly khac Session
                            out.print("<script type='text/javascript'>"
                                    + "jConfirm('Bạn đang thêm quảng cáo vào nhóm: <b>" + Tool.stringToHTMLString(addGroup.getName()) + "</b>"
                                    + "<br/>Nhóm cũ đang thao tác: <b style=\"color:red\">" + Tool.stringToHTMLString(map.getGroupName()) + "</b>"
                                    + "<br/>Bạn có chắc chắn bỏ nhóm cũ không?"
                                    + "','Thông báo',function(r){"
                                    + "if(r){ /*OK THI REMOVE THOI*/}"
                                    + "else{location.href='" + request.getContextPath() + "/sys-admin/group-adv-manager/show.html'}"
                                    + "});"
                                    + "</script>");

                        }
                    } else {
                        // Chua co Khoi Tao moi
                        map = new MapGroup();
                        map.setGroupID(groupID);
                        map.setGroupName(addGroup.getName());
                        ArrayList item = new ArrayList();
                        item.add(adsID);
                        map.setAdsID(item);
                        // Put vao Session
                        session.setAttribute("mess", "Thêm Quảng cáo vào Nhóm: " + map.getGroupName() + " thành công");
                        session.setAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME, map);
                        response.sendRedirect("/sys-admin/group-adv-manager/item-" + groupID + ".html");
                        return;
                    }
                } catch (Exception ex) {
                    logger.error(ex.getStackTrace());
                }
            }
        %>
        <%!
            private static boolean checkExit(String adsID, MapGroup map) {
                boolean flag = false;
                if (map != null) {
                    ArrayList<String> listAds = map.getAdsID();
                    if (listAds != null) {
                        for (String one : listAds) {
                            if (one.equalsIgnoreCase(adsID)) {
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                return flag;
            }
        %>
    </body>