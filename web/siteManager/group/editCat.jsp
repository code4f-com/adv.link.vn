
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%
            //---------------------------------------------
            SiteGroup oneEdit = new SiteGroup();
            int catid = RequestTool.getInt(request, "cid");
            if (session.getAttribute("error") != null) {
                out.print(session.getAttribute("error"));
                session.removeAttribute("error");
            }

            if (request.getParameter("submit") != null) {
                int parentid = RequestTool.getInt(request, "parentID");
                String name = RequestTool.getString(request, "name");
                String desc = RequestTool.getString(request, "desc");
                int status = Tool.string2Integer(request.getParameter("status"), 0);
                //
                oneEdit.setId(catid);
                oneEdit.setName(name);
                oneEdit.setDesc(desc);
                oneEdit.setParentid(parentid);
                oneEdit.setStatus(status);
                oneEdit.setUpdateBy(adminInfo.getAccID());
                //------------LOG -------------------
                if (oneEdit.edit(oneEdit)) {
                    out.println("<script type='text/javascript'>parent.menu.location.reload();</script>");
                    return;
                } else {
                    session.setAttribute("mess", "Thêm mới dữ liệu lỗi");
                    out.println("<script type='text/javascript'>parent.menu.location.reload();</script>");
                    return;
                }
            } else {
                oneEdit = oneEdit.getCatsById(catid);
            }
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">  

                    <div class="right_content">            
                        <form action="" method="post">
                            <table  align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th scope="col" class="rounded-q4">
                                            <b>
                                                <%
                                                    if (catid != 0) {
                                                        out.print("Sửa chuyên mục: " + SiteGroup.getCatNamebyId(catid));
                                                    }
                                                %>
                                            </b>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td align="left">Thư mục cha: </td>
                                        <td>
                                            <select name="parentID">
                                                <option <%= oneEdit.getParentid() == 0 ? "selected='selected'" : ""%> value="0">--Thư mục gốc--</option>
                                                <%
                                                    for (Iterator<SiteGroup> it = SiteGroup.CACHE.iterator(); it.hasNext();) {
                                                        SiteGroup newsCat = it.next();
                                                %>
                                                <option <%= oneEdit.getParentid() == newsCat.getId() ? "selected='selected'" : ""%> value="<%=newsCat.getId()%>"><%=newsCat.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Chuyên mục: </td>
                                        <td><input size="100" type="text" name="name" value="<%=oneEdit.getName()%>"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Mô tả: </td>
                                        <td><textarea name="desc" cols="75" rows="3"><%=oneEdit.getDesc()%></textarea> </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Trạng thái: </td>
                                        <td>
                                            <select name="status">
                                                <option <%=oneEdit.getStatus() == 1 ? "selected='selected'" : ""%> value='1'>Kích hoạt</option>
                                                <option <%=oneEdit.getStatus() == 0 ? "selected='selected'" : ""%> value='0'>Khóa</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" align="center">
                                            <input type="hidden" name="cid" value="<%=catid%>"/>
                                            <input type="submit" name="submit" value="Cập nhật"/>
                                            <input type="reset" onclick="window.location.href = 'subindex.jsp'" name="reset" value="Hủy"/>
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