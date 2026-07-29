<%@page import="gk.adv.linnk.vn.multipart.request.MultipartFile"%>
<%@page import="gk.adv.linnk.vn.multipart.request.HttpServletMultipartRequest"%>
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="gk.adv.linnk.vn.object.SiteManager"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><%@include file="/includes/header.jsp" %></head>
    <script>

    </script>
    <body>
        <%            HttpServletMultipartRequest req = new HttpServletMultipartRequest(request);

            SiteManager oneSite = null;
            int cid = Tool.string2Integer(req.getParameter("cid"));
            if (req.getParameter("submit") != null) {
                //---------------------------
                String domain = Tool.validStringRequest(req.getParameter("domain"));
                double priceClick = Tool.string2Double(req.getParameter("priceClick"));
                int ismysite = Tool.string2Integer(req.getParameter("ismysite"));
                int status = Tool.string2Integer(req.getParameter("status"));
                MultipartFile file = req.getFileParameter("icon");
                //---
                oneSite = new SiteManager();
                oneSite.setGid(cid);
                oneSite = new SiteManager();
                oneSite.setDomain(domain);
                oneSite.setPriceClick(priceClick);
                oneSite.setIsMySite(ismysite);
                oneSite.setStatus(status);
                oneSite.setCreateBy(adminInfo.getAccID());
                //------------
                if (oneSite.addNew(oneSite, file)) {
                    session.setAttribute("mess", "Thêm mới dữ liệu thành công!");
                    out.print("<script>top.location.href = '" + req.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
                    return;
                } else {
                    session.setAttribute("mess", "Thêm mới dữ liệu lỗi!");
                }
            }
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
                        <form action="" method="post"  enctype="multipart/form-data">
                            <table  align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th style="font-weight: bold" scope="col" class="rounded redBoldUp">Thêm mới Trang Quảng cáo</th>
                                        <th scope="col" class="rounded-q4"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>Nhóm site</td>
                                        <td colspan="2">
                                            <select id="parentID" name="cid">
                                                <option value="0">--Chọn nhóm site--</option>
                                                <%
                                                    for (Iterator<SiteGroup> it = SiteGroup.CACHE.iterator(); it.hasNext();) {
                                                        SiteGroup gSite = it.next();
                                                %>
                                                <option <%= gSite.getId() == cid ? "selected='selected'" : ""%> value="<%=gSite.getId()%>"><%=gSite.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Icon của Domain: </td>
                                        <td colspan="2"><input size="75" type="file" name="icon"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Domain: </td>
                                        <td colspan="2"><input size="75" type="text" name="domain"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Giá/1Click: </td>
                                        <td colspan="2"><input size="75" type="text" name="priceClick" </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">LOẠI TRANG: </td>
                                        <td colspan="2">
                                            <select name="ismysite">
                                                <%
                                                    for (SiteManager.ISMYSITE one : SiteManager.ISMYSITE.values()) {
                                                %>
                                                <option value="<%=one.getValue()%>"><%=one.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>   
                                    <tr>
                                        <td></td>
                                        <td align="left">Trạng thái: </td>
                                        <td colspan="2">
                                            <select name="status">
                                                <option value="1">Kích hoạt</option>
                                                <option value="0">Khóa</option>
                                            </select>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td colspan="4" align="center">
                                            <input type="submit" name="submit" value="Thêm mới"/>
                                            <input onclick="top.location.href = '<%=req.getContextPath() + "/sys-admin/site-manager/show.html"%>'" type="reset" name="reset" value="Hủy"/>
                                        </td>
                                    </tr>
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