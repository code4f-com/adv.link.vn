<%@page import="gk.adv.linnk.vn.multipart.request.MultipartFile"%>
<%@page import="gk.adv.linnk.vn.multipart.request.HttpServletMultipartRequest"%>
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%><%@page import="gk.adv.linnk.vn.object.SiteManager"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><%@include file="/includes/header.jsp" %></head>
    <body>
        <%            HttpServletMultipartRequest req = new HttpServletMultipartRequest(request);
            int siteID = Tool.string2Integer(req.getParameter("id"));
            SiteManager oneSite = new SiteManager();
            oneSite = oneSite.getByID(siteID);
            if (oneSite == null) {
                session.setAttribute("mess", "Yêu cầu không hợp lệ");
                out.print("<script>top.location.href = '" + req.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
                return;
            }
            if (req.getParameter("submit") != null) {
                //---------------------------
                String domain = Tool.validStringRequest(req.getParameter("domain"));
                double priceClick = Tool.string2Double(req.getParameter("priceClick"));
                int gid = Tool.string2Integer(req.getParameter("gid"));
                int ismysite = Tool.string2Integer(req.getParameter("ismysite"));
                int status = Tool.string2Integer(req.getParameter("status"));
                MultipartFile file = req.getFileParameter("icon");
                //---
                oneSite.setGid(gid);
                oneSite.setDomain(domain);
                oneSite.setPriceClick(priceClick);
                oneSite.setIsMySite(ismysite);
                oneSite.setStatus(status);
                oneSite.setUpdateBy(adminInfo.getAccID());
                //------------
                if (oneSite.update(oneSite, file)) {
                    session.setAttribute("mess", "Cập nhật dữ liệu thành công!");
                    out.print("<script>top.location.href = '" + req.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
                    return;
                } else {
                    session.setAttribute("mess", "Cập nhật dữ liệu lỗi!");
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
                        <form action="" method="post" enctype="multipart/form-data">
                            <input type="hidden" value="<%=siteID%>" name="id"/>
                            <table  align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th style="font-weight: bold" scope="col" class="rounded redBoldUp">Cập nhật Trang Quảng cáo</th>
                                        <th scope="col" class="rounded-q4"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td align="left">Thư mục cha: </td>
                                        <td class="2">
                                            <select name="gid">
                                                <option value="0">--Thư mục gốc--</option>
                                                <%
                                                    for (Iterator<SiteGroup> it = SiteGroup.CACHE.iterator(); it.hasNext();) {
                                                        SiteGroup newcat = it.next();
                                                %>
                                                <option <%= newcat.getId() == oneSite.getId() ? "selected='selected'" : ""%> value="<%=newcat.getId()%>"><%=newcat.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Icon của Domain: <img src="<%=oneSite.getDisplayPath()%>"></td>
                                        <td colspan="2"><input size="75" type="file" name="icon"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Domain: </td>
                                        <td colspan="2"><input size="75" value="<%=oneSite.getDomain()%>" type="text" name="domain"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Giá/1Click: </td>
                                        <td colspan="2"><input  value="<%=oneSite.getPriceClick()%>"  size="75" type="text" name="priceClick" </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">LOẠI TRANG: </td>
                                        <td colspan="2">
                                            <select name="ismysite">
                                                <%
                                                    for (SiteManager.ISMYSITE one : SiteManager.ISMYSITE.values()) {
                                                %>
                                                <option <%=oneSite.getIsMySite()==one.getValue()?"selected='selected'":""%> value="<%=one.getValue()%>"><%=one.getName()%></option>
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
                                                <option <%=oneSite.getStatus() == 1 ? "selected='selected'" : ""%> value="1">Kích hoạt</option>
                                                <option <%=oneSite.getStatus() == 0 ? "selected='selected'" : ""%> value="0">Khóa</option>
                                            </select>
                                        </td>
                                    </tr>                                    
                                    <tr>
                                        <td colspan="4" align="center">
                                            <input type="submit" name="submit" value="Cập nhật"/>
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