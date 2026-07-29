<%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.admin.Modules"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
    </head>
    <body>
        <%if (!adminInfo.checkView(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            String module = RequestTool.getString(request, "module");
            Tool.Debug(module);
            ArrayList all = null;
            Modules dao = new Modules();
            all = dao.listAllModule();
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
                            <span style="font-weight: bold;color: blueviolet;margin-right: 10px">QUẢN LÝ MODULE HỆ THỐNG</span>
                            <a href="<%=request.getContextPath() + "/sys-admin/module-manager/add.html"%>"><img border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/></a>
                        </div>
                        <!--Content-->
                        <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company">STT</th>
                                    <th scope="col" class="rounded">Module Name</th>
                                    <th scope="col" class="rounded">Module Resource</th>
                                    <th scope="col" class="rounded">Trạng thái</th>                                    
                                    <th scope="col" class="rounded">Edit</th>
                                    <th scope="col" class="rounded-q4">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int count = 1; //Bien dung de dem so dong
                                    for (Iterator<Modules> iter = all.iterator(); iter.hasNext();) {
                                        Modules oneModule = iter.next();
                                %>
                                <tr>
                                    <td class="boder_right"><%=count++%></td>
                                    <td  class="boder_right" align="left">
                                        <%=oneModule.getName()%>
                                    </td>
                                    <td class="boder_right" align="left">
                                        <%= oneModule.getResource()%>
                                    </td>
                                    <td class="boder_right" align="center"><%
                                        if (oneModule.getStatus() == 1) {
                                        %>
                                        <img src="<%= request.getContextPath()%>/resource/images/active.png"/>
                                        <%
                                        } else {
                                        %>
                                        <img src="<%= request.getContextPath()%>/resource/images/key_lock.png"/>
                                        <%
                                            }
                                        %>
                                    </td>                                    
                                    <td class="boder_right"><a href="<%="/sys-admin/module-manager/edit-" + oneModule.getModulID() + ".html"%>"><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                    <td><a href="<%="/sys-admin/module-manager/del-" + oneModule.getModulID() + ".html"%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>