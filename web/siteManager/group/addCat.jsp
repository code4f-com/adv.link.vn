<%@page import="gk.adv.linnk.vn.object.SiteGroup"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><%@include file="/includes/header.jsp" %></head>
    <body>
        <%
            //---------------------------------------------
            int catid = RequestTool.getInt(request, "cid");

            if (session.getAttribute("error") != null) {
                out.print(session.getAttribute("error"));
                session.removeAttribute("error");
            }
            if (request.getParameter("submit") != null) {
                int parentid = RequestTool.getInt(request, "parentID");
                String name = RequestTool.getString(request, "name");
                String desc = RequestTool.getString(request, "desc");
                int status = Tool.string2Integer(request.getParameter("status"),0);

                SiteGroup oneCat = new SiteGroup();
                oneCat.setName(name);
                oneCat.setDesc(desc);
                oneCat.setParentid(parentid);
                oneCat.setStatus(status);
                oneCat.setCreateBy(adminInfo.getAccID());
                oneCat.setUpdateBy(adminInfo.getAccID());
                //------------END LOG -------------------
                if (oneCat.add(oneCat)) {
//                    out.println("<script type='text/javascript'>parent.menu.location.reload();</script>");
                    out.println("<script type='text/javascript'>top.location.href='" + request.getContextPath() + "/sys-admin/site-manager/show.html'</script>");
                    return;
                } else {
                    out.println("<script type='text/javascript'>alert('Thêm mới dữ liệu lỗi');</script>");
                    out.println("<script type='text/javascript'>parent.menu.location.reload();</script>");
                    return;
                }
            }
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">  
                    <div class="right_content">            
                        <form action="" method="post">
                            <table align="center" id="rounded-corner">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th class="rounded"></th>
                                        <th scope="col" class="rounded-q4">
                                            <b>
                                                <%
                                                    if (catid != 0) {
                                                        out.print("Thêm nhóm con của nhóm: <span class='redBold'>" + SiteGroup.getCatNamebyId(catid)+"</span>");
                                                    } else {
                                                        out.print("Thêm mới nhóm!");
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
                                                <option value="0">--Thư mục gốc--</option>
                                                <%
                                                    for (Iterator<SiteGroup> it = SiteGroup.CACHE.iterator(); it.hasNext();) {
                                                        SiteGroup newcat = it.next();
                                                %>
                                                <option <%= newcat.getId() == catid ? "selected='selected'" : ""%> value="<%=newcat.getId()%>"><%=newcat.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Tên Chuyên mục: </td>
                                        <td><input type="text" name="name" size="100"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Mô tả: </td>
                                        <td><textarea name="desc" cols="75" rows="3"></textarea> </td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td align="left">Trạng thái: </td>
                                        <td>
                                            <select name="status">
                                                <option value="1">Kích hoạt</option>
                                                <option value="0">Khóa</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" align="center">
                                            <input type="submit" name="submit" value="Thêm mới"/>
                                            <input type="reset" onclick="window.location.href = '<%=request.getContextPath()+"/sys-admin/site-manager/show.html" %>'" name="reset" value="Hủy"/>
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