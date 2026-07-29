<%@page import="gk.adv.linnk.vn.object.City"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%            int id = Tool.string2Integer(request.getParameter("id"));
            City oneCity = new City();
            oneCity = oneCity.getById(id);
            if (request.getParameter("submit") != null) {
                //----------Log--------------
                String name = Tool.validStringRequest(request.getParameter("name"));
                String mycode = Tool.validStringRequest(request.getParameter("mycode"));
                String ggcode = Tool.validStringRequest(request.getParameter("ggcode"));
                String ggName = Tool.validStringRequest(request.getParameter("ggName"));

                //---
                oneCity.setMyCode(mycode);
                oneCity.setMyname(name);
                oneCity.setGgCode(ggcode);
                oneCity.setGgName(ggName);
                if (oneCity.update(oneCity)) {
                    session.setAttribute("mess", "Sửa dữ liệu thành công");
                    response.sendRedirect(request.getContextPath() + "/admin/city/index.jsp");
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
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                            %>
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
                                        <td></td>
                                        <td align="left">Mã Quy Định: </td>
                                        <td colspan="2"><input size="75" value="<%=oneCity.getMyCode()%>" type="text" name="mycode"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Vùng miền: </td>
                                        <td colspan="2">
                                            <select name="region">
                                                <option <%=oneCity.getRegion().equals("HOME")?"selected='selected'":"" %> value="HOME">Toàn quốc</option>                                                
                                                <option <%=oneCity.getRegion().equals("MB")?"selected='selected'":"" %> value="MB">Miền bắc</option>                                                
                                                <option <%=oneCity.getRegion().equals("MT")?"selected='selected'":"" %> value="MT">Miền Trung</option>                                                
                                                <option <%=oneCity.getRegion().equals("MN")?"selected='selected'":"" %> value="MN">Miền Nam</option>                                                
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Hiển thị: </td>
                                        <td colspan="2"><input size="75" value="<%=oneCity.getMyname()%>" type="text" name="name"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Mã Google: </td>
                                        <td colspan="2"><input size="75"  value="<%=oneCity.getGgCode()%>" type="text" name="ggcode"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Google </td>
                                        <td colspan="2"><input size="75" value="<%=oneCity.getGgName()%>" type="text" name="ggName"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="center">
                                            <input type="submit" name="submit" value="Cập nhật"/>
                                            <input onclick="window.location.href = '<%=request.getContextPath() + "/admin/city/index.jsp"%>'" type="reset" name="reset" value="Hủy"/>
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