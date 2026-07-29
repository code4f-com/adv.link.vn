<%@page import="gk.adv.linnk.vn.utils.Constants"%>
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="gk.adv.linnk.vn.object.SiteManager"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <script>
        function yourChangeState(divid, type, value, id) {
            var url = "<%=webPath%>/siteManager/changestate.jsp?type=" + type + "&vl=" + value + "&id=" + id;
            url += "&sID=" + Math.floor(Math.random() * 10000);
            eventAction(url, divid);
        }
        function checkallMove() {
            var chk = document.getElementById("checkall");
            if (chk.checked) {
                var chkmove = document.getElementsByName("chkmove");
                for (var i = 0; i < chkmove.length; i++)
                    chkmove[i].checked = true;
            } else {
                var chkmove = document.getElementsByName("chkmove");
                for (var i = 0; i < chkmove.length; i++)
                    chkmove[i].checked = false;
            }
        }
        function CheckMove() {
            var chkmove = document.getElementsByName("chkmove");
            for (var i = 0; i < chkmove.length; i++) {
                if (chkmove[i].checked) {
                    return true;
                }
            }
            alert("Bạn cần chọn ít nhất một nội dung để chuyển chuyên mục");
            return false;
        }
    </script>
    <body>
        <%            int cid = Tool.string2Integer(request.getParameter("cid"));
            String key = Tool.validStringRequest(request.getParameter("key"));
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            int status = Tool.string2Integer(request.getParameter("status"), SiteManager.STATUS.SHOW_ALL.getValue());
            SiteManager gDao = new SiteManager();
            ArrayList<SiteManager> all = gDao.getAllSite(currentPage, cid, key, status);
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                            %>
                        </div>
                        <div align="center" style="height: 20px;margin-bottom: 12px;margin-top: 10px">
                            <a href="<%=request.getContextPath() + "/sys-admin/site-manager/add.html?cid=" + cid%>"><img border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/></a>
                        </div>
                        <!--Content-->
                        <form name="frmmove" action="/siteManager/moveCat.jsp" method="post" onsubmit="return CheckMove()">
                            <div align="center" style="font-weight: bold;color: blue">
                                Chuyển đến Nhóm trang:
                                <select name="cid">
                                    <option value="0">---Tất cả---</option>
                                    <%
                                        ArrayList<SiteGroup> allCat = SiteGroup.CACHE;
                                        // Duyet qua tat ca cat cha
                                        for (SiteGroup oneRoot : allCat) {
                                            // in ra 1 catcha
                                    %>
                                    <option style="font-weight: bold;color: red" value="<%=oneRoot.getId()%>" >** <%=oneRoot.getName()%> </option>
                                    <%
                                        // kiem tra cat cha nay co con hay khong ?
                                        if (SiteGroup.checkHaveChild(oneRoot.getId())) {
                                            // Neu co thi lay ra tat ca cac cat con cap 1
                                            ArrayList<SiteGroup> allChild_1 = SiteGroup.getChildGroupByParentId(oneRoot.getId());
                                            // duyet qua tung cat con 1
                                            for (SiteGroup child_1 : allChild_1) {
                                    %>
                                    <option  style="font-weight: bold;color: blue" value="<%=child_1.getId()%>" > &nbsp;&nbsp; +&nbsp;<%=child_1.getName()%> </option>
                                    <%
                                        if (SiteGroup.checkHaveChild(child_1.getId())) {
                                            ArrayList<SiteGroup> allChild_2 = SiteGroup.getChildGroupByParentId(child_1.getId());
                                            for (SiteGroup child_2 : allChild_2) {
                                    %>
                                    <option style="font-weight: bold" value="<%=child_2.getId()%>" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -&nbsp;<%=child_2.getName()%></option>
                                    <%
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    %>
                                </select><input type="submit" value="Chuyển"/>
                            </div>
                            <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company">
                                            <input type="checkbox" id="checkall" name="checkall" onclick="checkallMove()"/>
                                        </th>
                                        <th scope="col" class="rounded">STT</th>
                                        <th scope="col" class="rounded">Domain</th>
                                        <th scope="col" class="rounded">Logo</th>
                                        <th scope="col" class="rounded">Price Click</th>
                                        <th scope="col" class="rounded">Loại</th>
                                        <th scope="col" class="rounded">Ngày Sửa</th>
                                        <!--<th scope="col" class="rounded">Người Sửa</th>-->
                                        <th scope="col" class="rounded">Trạng thái</th>
                                        <th scope="col" class="rounded">Edit</th>
                                        <th scope="col" class="rounded-q4">Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 1; //Bien dung de dem so dong
                                        for (Iterator<SiteManager> iter = all.iterator(); iter.hasNext();) {
                                            SiteManager oneSite = iter.next();
                                    %>
                                    <tr>
                                        <td align="center" style="border-right: solid 1px #00ccff">
                                            <input value="<%=oneSite.getId()%>" type="checkbox" name="chkmove" />
                                        </td>
                                        <td align="center" style="border-right: solid 1px #00ccff"><%=count++%></td>
                                        <td style="border-right: solid 1px #00ccff" align="left">
                                            <%=oneSite.getDomain()%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="left">
                                            <img style="max-width: 64px" onerror="src='/resource/images/default.gif'" src="<%=oneSite.getDisplayPath()%>" />
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%= oneSite.getPriceClick()%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="left">
                                            <%=SiteManager.ISMYSITE.getName(oneSite.getIsMySite())%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff"><%=DateProc.Timestamp2DDMMYY(oneSite.getUpdateDate())%></td>
                                        <!--<td style="border-right: solid 1px #00ccff"><%=oneSite.getUpdateBy()%></td>-->
                                        <td id="status<%=oneSite.getId()%>" style="border-right: solid 1px #00ccff" align="center">
                                            <%if (oneSite.getStatus() == 1) {%>
                                            <a href="" onclick="return false;"><img onclick="yourChangeState('status<%=oneSite.getId()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneSite.getStatus()%>', '<%=oneSite.getId()%>')" src="<%= request.getContextPath()%>/resource/images/active.png"/></a>
                                                <%} else if (oneSite.getStatus() == 0) {%>
                                            <a href="" onclick="return false;"><img  onclick="yourChangeState('status<%=oneSite.getId()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneSite.getStatus()%>', '<%=oneSite.getId()%>')" src="<%= request.getContextPath()%>/resource/images/key_lock.png"/></a>
                                                <%} else {%>
                                            <a href="" onclick="return false;"><img  onclick="yourChangeState('status<%=oneSite.getId()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneSite.getStatus()%>', '<%=oneSite.getId()%>')" title="Đã xóa" src="/resource/images/Recyclebin.png"/></a>
                                                <%}%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff"><a href="<%=request.getContextPath() + "/sys-admin/site-manager/edit-" + oneSite.getId() + ".html"%>"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                        <td><a href="<%=oneSite.getStatus() == 404 ? request.getContextPath() + "/sys-admin/site-manager/delever-" + oneSite.getId() + ".html" : request.getContextPath() + "/sys-admin/site-manager/del-" + oneSite.getId() + ".html"%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </form>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>