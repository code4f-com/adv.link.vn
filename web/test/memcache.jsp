<%-- 
    Document   : memcache
    Created on : May 7, 2014, 10:28:57 AM
    Author     : TUANPLA
--%>

<%@page import="gk.adv.linnk.vn.utils.DateProc"%>
<%@page import="gk.adv.linnk.vn.utils.Tool"%>
<%@page import="gk.adv.linnk.vn.memcache.MyMemCache"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test Memcache</title>
    </head>
    <body>
        <h1>Test Memcache!</h1>
        <%
            if (MyMemCache.CACHE_ONE_DAY.get("dev.key") == null) {
                System.out.println("----PUT TO CACHE....."+DateProc.createTimestamp());
                MyMemCache.CACHE_ONE_DAY.set("dev.key", "Test Memcache Forever");
                //--
            } else {
                out.println("-------------VALUE IN CACHE: ");
                out.print(MyMemCache.CACHE_ONE_DAY.get("dev.key").toString());
            }
        %>
    </body>
</html>
