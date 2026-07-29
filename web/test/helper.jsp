<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Helper</title>
    </head>
    <body>
        <h1 align="center">Cám ơn sự giúp đỡ của bạn!</h1>
        <%
            String ip = Tool.getClientIpAddr(request);
        %>
        <div align="center">
            <form name="abc" action="" method="post">
                <table>
                    <tr>
                        <td>Chọn tỉnh bạn đang ở </td>
                        <td>
                            <select name="cytiChoice">
                                <option>----Vui lòng chọn----</option>
                            </select>
                            &nbsp; hoặc nhập
                            <input type="text" name="inputcity" value="nhập" onfocus="this.value=''">
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="3"><input type="submit" name="submit" value="Gửi cho tôi" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
