<%@page import="gk.adv.linnk.vn.admin.AccSiteManager"%>
<%@page import="gk.adv.linnk.vn.object.SiteManager"%>
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%            int id = Tool.string2Integer(request.getParameter("id"));
            Account admDao = new Account();
            Account oneAdmin = null;
            oneAdmin = admDao.getByID(id);
            if (request.getParameter("submit") != null) {
                //----------Log--------------
                String account = Tool.validStringRequest(request.getParameter("name"));
                String pass = Tool.validStringRequest(request.getParameter("pass"));
                String fullname = Tool.validStringRequest(request.getParameter("fullname"));
                String phone = Tool.validStringRequest(request.getParameter("phone"));
                String email = Tool.validStringRequest(request.getParameter("email"));
                String desc = Tool.validStringRequest(request.getParameter("desc"));
                String address = Tool.validStringRequest(request.getParameter("address"));
                String[] arrSiteId = request.getParameterValues("siteId");
                int status = Tool.string2Integer(request.getParameter("status"));
                int userType = Tool.string2Integer(request.getParameter("type"), Account.TYPE.NOROLE.val);
                //---
                oneAdmin.setUserName(account);
                oneAdmin.setPassWord(pass);
                oneAdmin.setFullName(fullname);
                oneAdmin.setPhone(phone);
                oneAdmin.setEmail(email);
                oneAdmin.setDescription(desc);
                oneAdmin.setAddress(address);
                oneAdmin.setUpdateBy(adminInfo.getUserName());
                oneAdmin.setUserType(userType);
                oneAdmin.setStatus(status);
                if (admDao.update(oneAdmin)) {
                    if (arrSiteId != null && arrSiteId.length > 0) {
                        new AccSiteManager().mapSiteManager(arrSiteId, oneAdmin.getAccID());
                    }
                    session.setAttribute("mess", "Sửa dữ liệu thành công");
                    response.sendRedirect(request.getContextPath() + "/sys-admin/customer-user/show.html");
                    return;
                } else {
                    session.setAttribute("mess", "Sửa dữ liệu lỗi");
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
                            <%if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }%>
                        </div>
                        <form action="" method="post">
                            <table align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th style="font-weight: bold"  scope="col" class="rounded redBoldUp">Thêm mới quản trị</th>
                                        <th scope="col" class="rounded-q4"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td align="left"></td>
                                        <td align="left">Tên đăng nhập: </td>
                                        <td colspan="2"><input size="75" value="<%=oneAdmin.getUserName()%>" type="text" name="name"/></td>
                                    </tr>
                                    <tr>
                                        <td align="left"></td>
                                        <td align="left">Mật khẩu: </td>
                                        <td colspan="2"><input size="75" type="password" name="pass"/></td>
                                    </tr>
                                    <tr>
                                        <td align="left"></td>
                                        <td align="left">Tên thật: </td>
                                        <td colspan="2"><input size="75" value="<%=oneAdmin.getFullName()%>" type="text" name="fullname"/></td>
                                    </tr>
                                    <tr>
                                        <td align="left"></td>
                                        <td align="left">Mobile: </td>
                                        <td colspan="2"><input size="75" value="<%=oneAdmin.getPhone()%>" type="text" name="phone"/></td>
                                    </tr>
                                    <tr><td align="left"></td>
                                        <td align="left">Email </td>
                                        <td colspan="2"><input size="75" value="<%=oneAdmin.getEmail()%>" type="text" name="email"/></td>
                                    </tr>
                                    <tr><td align="left"></td>
                                        <td align="left">Mô tả </td>
                                        <td colspan="2"><textarea cols="55" name="desc"><%=oneAdmin.getDescription()%></textarea></td>
                                    </tr>
                                    <tr><td align="left"></td>
                                        <td align="left">Trạng thái: </td>
                                        <td colspan="2">
                                            <select name="status">
                                                <option <%=oneAdmin.getStatus() == 1 ? "selected='selected'" : ""%> value="1">Kích hoạt</option>
                                                <option <%=oneAdmin.getStatus() == 0 ? "selected='selected'" : ""%> value="0">Khóa</option>
                                            </select>
                                            &nbsp;&nbsp;&nbsp;
                                            <select name="type">
                                                <%
                                                    for (Account.TYPE one : Account.TYPE.values()) {
                                                %>
                                                <option <%= one.val == oneAdmin.getUserType() ? "selected='selected'" : ""%> value="<%=one.val%>"><%=Account.getTypeName(one.val)%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <th colspan="4">Quản lý thống kê trên các trang</th>
                                    </tr>
                                    <%for (SiteGroup one : SiteGroup.CACHE) {%>
                                    <tr>
                                        <td colspan="4">
                                            <span class="redBold">Nhóm: <%=one.getName()%></span><br/>
                                            <%
                                                ArrayList<SiteManager> oneGroup = one.getSite();
                                                for (SiteManager oneSite : oneGroup) {
                                                    out.print(oneSite.getDomain());
                                                    out.print("<input type=\"checkbox\" name=\"siteId\" value=\"" + oneSite.getId() + "\" " + (Account.checkSiteRight(oneSite, oneAdmin.getSiteManager()) ? "checked=\"checked\"" : "") + "  />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                                                }
                                            %>
                                        </td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                        <td colspan="4" align="center">
                                            <input type="submit" name="submit" value="Cập nhật"/>
                                            <input  onclick="window.location.href = '<%=request.getContextPath() + "/sys-admin/customer-user/show.html"%>'" type="reset" name="reset" value="Hủy"/>
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