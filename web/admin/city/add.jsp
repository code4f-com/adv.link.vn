<%@page import="gk.adv.linnk.vn.object.City"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><%@include file="/includes/header.jsp" %></head>
    <body>
        <%            City oneCity = null;
            if (request.getParameter("submit") != null) {
                //---------------------------
                String name = Tool.validStringRequest(request.getParameter("name"));
                String mycode = Tool.validStringRequest(request.getParameter("mycode"));
                String ggcode = Tool.validStringRequest(request.getParameter("ggcode"));
                String ggName = Tool.validStringRequest(request.getParameter("ggName"));

                //---
                oneCity = new City();
                oneCity.setMyCode(mycode);
                oneCity.setMyname(name);
                oneCity.setGgCode(ggcode);
                oneCity.setGgName(ggName);
                //------------
                if (oneCity.addNew(oneCity)) {
                    session.setAttribute("mess", "Thêm mới dữ liệu thành công!");
                    response.sendRedirect(request.getContextPath() + "/admin/city/index.jsp");
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
                        <form action="" method="post">
                            <table  align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th style="font-weight: bold" scope="col" class="rounded redBoldUp">Thêm mới Tỉnh Thành</th>
                                        <th scope="col" class="rounded-q4"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td align="left">Mã Quy Định: </td>
                                        <td colspan="2"><input size="75" type="text" name="mycode"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Vùng miền: </td>
                                        <td colspan="2">
                                            <select name="region">
                                                <option value="HOME">Toàn quốc</option>                                                
                                                <option value="MB">Miền bắc</option>                                                
                                                <option value="MT">Miền Trung</option>                                                
                                                <option value="MN">Miền Nam</option>                                                
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Hiển thị: </td>
                                        <td colspan="2"><input size="75" type="text" name="name"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Mã Google: </td>
                                        <td colspan="2"><input size="75" type="text" name="ggcode"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Google </td>
                                        <td colspan="2"><input size="75" type="text" name="ggName"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4" align="center">
                                            <input type="submit" name="submit" value="Thêm mới"/>
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