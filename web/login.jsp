<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.admin.Account"%><%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page contentType="text/html; charset=utf-8" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>IN ADMIN PANEL</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/style.css" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
        <link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath()%>/resource/css/niceforms-default.css" />
        <script type = "text/javascript" >
            $(document).keypress(function(event) {
                var keycode = (event.keyCode ? event.keyCode : event.which);
                if (keycode == '13') {
                    $("#submit").click();
                }
            });
        </script>
    </head>
    <%
        //
        if (request.getParameter("submit") != null) {
            String username = Tool.validStringRequest(request.getParameter("user"));
            String pass = Tool.validStringRequest(request.getParameter("pass"));
            Account admin = new Account();
            admin = admin.checkLogin(username, pass);
            if (admin != null) {
                session.setAttribute("adminInfo", admin);
                
                if (admin.getUserType() == Account.TYPE.ADMIN.val) {
                    response.sendRedirect(request.getContextPath() + "/sys");
                    return;
                } else {
                    response.sendRedirect(request.getContextPath() + "/customer");
                    return;
                }

            } else {
                session.setAttribute("error", "Tài khoản hoặc mật khẩu không đúng");
            }
        }
    %>
    <body>
        <div id="main_container">
            <div class="header_login">
                <div class="logo" style="margin-left: 200px"><a href="#"><img src="<%= request.getContextPath()%>/resource/images/logo.png" alt="" title="" border="0" /></a></div>
            </div>
            <div class="login_form">
                <form action="" method="post" class="niceform">
                    <table style="margin-left: 0px;width: 100%" align="center">
                        <tr>
                            <td colspan="2" style="text-align: center;font-weight: bold;font-size: large" >Đăng nhập hệ thống</td>
                        </tr>
                        <tr>
                            <td style="color: red;text-align: center" colspan="2">
                                <%=(session.getAttribute("error") != null) ? "" + session.getAttribute("error") : ""%><%session.removeAttribute("error");%>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <th style="text-align: center;width: 170px;margin-left: 15px">Tên đăng nhập:</th>
                            <td><input style="padding: 4px;border-radius: 3px 3px  3px  3px" type="text" value="" name="user" id="" size="54" /></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <th style="text-align: center;width: 170px; ">Mật khẩu:</th>
                            <td><input style="padding: 4px;border-radius: 3px 3px  3px  3px" type="password" value="" name="pass" id="" size="54" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" name="submit" id="submit" value="Đăng nhập" />
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </body>
</html>