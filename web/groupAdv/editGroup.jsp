<%@page import="gk.adv.linnk.vn.object.GroupAdv"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head><%@include file="/includes/header.jsp" %></head>
    <script>
        function choiceKind() {
            var kind = $("#kind").val();
            if (kind ==<%=GroupAdv.KIND.FLASH.getValue()%>) {
                $("#show_item").hide();
                $("#max_item").hide();
                $("#type_show").hide();
                getdata("/groupAdv/exam/flash.html", "demo_view");
                //--
            } else if (kind ==<%=GroupAdv.KIND.IMAGE.getValue()%>) {
                $("#show_item").hide();
                $("#max_item").hide();
                $("#type_show").hide();
                getdata("/groupAdv/exam/image.html", "demo_view");
            } else {
                $("#show_item").show();
                $("#max_item").show();
                $("#type_show").show();
                var type_choice = $("#type_choice").val();
                //--
                getdata("/groupAdv/exam/_demo.jsp?type=" + type_choice, "demo_view")
            }
//            var adsWidth = $("#demo_view").outerWidth(), mgl;
//            if (adsWidth < 344){
//                 mgl = (344 - adsWidth) / 2;
//            }
//            if (adsWidth >= 344 & adsWidth < 1024) {
//                mgl = (1024 - adsWidth) / 2;
//            }
//            $("#demo_view").css({'margin-left': mgl});
        }
        function getdata(url, div) {
            $.get(url, div, function AllStateChanged(responseText) {
                if (responseText != "error" && responseText != "") {
                    $("#" + div).html(responseText);
                }
            });
        }
    </script>
    <body style="width: ">
        <%
            if (!adminInfo.checkEdit(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            int groupID = Tool.string2Integer(request.getParameter("id"));
            GroupAdv oneGroup = new GroupAdv();
            oneGroup = oneGroup.getByID(groupID);
            if (oneGroup == null) {
                session.setAttribute("mess", "Yêu cầu không hợp lệ");
                response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
                return;
            }
            if (request.getParameter("submit") != null) {
                //---------------------------
                String name = Tool.validStringRequest(request.getParameter("name"));
                String desc = Tool.validStringRequest(request.getParameter("desc"));
                int kind = Tool.string2Integer(request.getParameter("kind"));
                int type = Tool.string2Integer(request.getParameter("type"));
                int max_item = Tool.string2Integer(request.getParameter("max_item"));
                int show_item = Tool.string2Integer(request.getParameter("show_item"));
                int status = Tool.string2Integer(request.getParameter("status"));
                //---
                oneGroup.setName(name);
                oneGroup.setDesc(desc);
                oneGroup.setKind(kind);
                oneGroup.setType(type);
                oneGroup.setMaxItem(max_item);
                oneGroup.setShowItem(show_item);
                oneGroup.setStatus(status);
                //------------
                if (oneGroup.update(oneGroup)) {
                    session.setAttribute("mess", "Cập nhật dữ liệu thành công!");
                    response.sendRedirect(request.getContextPath() + "/sys-admin/group-adv-manager/show.html");
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
                            DEMO
                        </div>
                        <div style="float: left">
                            <form action="" method="post">
                                <input type="hidden" value="<%=groupID%>" name="id"/>
                                <table  align="center" id="rounded-corner">
                                    <thead>
                                        <tr>
                                            <th scope="col" class="rounded-company"></th>
                                            <th scope="col" class="rounded"></th>
                                            <th style="font-weight: bold" scope="col" class="rounded redBoldUp">Thêm mới Nhóm quảng cáo</th>
                                            <th scope="col" class="rounded-q4"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td></td>
                                            <td align="left">Tên nhóm: </td>
                                            <td colspan="2"><input value="<%=oneGroup.getName()%>" size="75" type="text" name="name"/></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td align="left">Mô tả: </td>
                                            <td colspan="2"><textarea cols="55" name="desc"><%=oneGroup.getDesc()%></textarea></td>
                                        </tr>
                                        <tr id="max_item">
                                            <td></td>
                                            <td align="left">Số quảng cáo tối da: </td>
                                            <td colspan="2"><input size="75" type="text" value="<%=oneGroup.getMaxItem()%>" name="max_item"></td>
                                        </tr>
                                        <tr id="show_item">
                                            <td></td>
                                            <td align="left">Số quảng cáo Hiển thị: </td>
                                            <td colspan="2"><input id="show_item_val" size="75" type="text" value="<%=oneGroup.getShowItem()%>" name="show_item"></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td align="left">LOẠI GROUP: </td>
                                            <td colspan="2">
                                                <select name="kind">
                                                    <%
                                                        for (GroupAdv.KIND one : GroupAdv.KIND.values()) {
                                                    %>
                                                    <option  <%=oneGroup.getKind() == one.getValue() ? "selected='selected'" : ""%> value="<%=one.getValue()%>"><%=one.getName()%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </td>
                                        </tr>   
                                        <tr id="type_show">
                                            <td></td>
                                            <td align="left">KIỂU HIỂN HỊ: </td>
                                            <td colspan="2">
                                                <select onchange="choiceKind()" name="type" id="type_choice">
                                                    <%
                                                        for (GroupAdv.TYPE one : GroupAdv.TYPE.values()) {
                                                    %>
                                                    <option <%=oneGroup.getType() == one.getValue() ? "selected='selected'" : ""%> value="<%=one.getValue()%>"><%=one.getName()%></option>
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
                                                    <option <%=oneGroup.getStatus() == 1 ? "selected='selected'" : ""%> value="1">Kích hoạt</option>
                                                    <option <%=oneGroup.getStatus() == 0 ? "selected='selected'" : ""%> value="0">Khóa</option>
                                                </select>
                                            </td>
                                        </tr>                                    
                                        <tr>
                                            <td colspan="4" align="center">
                                                <input type="submit" name="submit" value="Cập nhật"/>
                                                <input onclick="window.location.href = '<%=request.getContextPath() + "/sys-admin/group-adv-manager/show.html"%>'" type="reset" name="reset" value="Hủy"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div style="margin-left: 20px;float: left;margin-top: 10px" id="demo_view"></div>
                        <script>choiceKind();</script>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>