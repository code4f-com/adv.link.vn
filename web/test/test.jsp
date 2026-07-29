<%-- 
    Document   : test
    Created on : Jul 28, 2013, 11:45:53 PM
    Author     : TUANPLA
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        /*    out.println("getContextPath:" + request.getContextPath());
            out.print("<br/>");
            out.println("getHeader - host:" + request.getHeader("host"));
            out.print("<br/>");
            out.println("getMethod:" + request.getMethod());
            out.print("<br/>");
            out.println("getPathInfo:" + request.getPathInfo());
            out.print("<br/>");
            out.println("getLocalAddr:" + request.getLocalAddr());
            out.print("<br/>");
            out.println("getLocalName:" + request.getLocalName());
            out.print("<br/>");
            out.println("getLocalPort:" + request.getLocalPort());
            out.print("<br/>");
            out.println("getLocale:" + request.getLocale());
            out.print("<br/>");
            out.println("getLocales:" + request.getLocales());
            out.print("<br/>");
            out.println("getProtocol:" + request.getProtocol());
            out.print("<br/>");
            out.println("getQueryString:" + request.getQueryString());
            out.print("<br/>");
            out.println("getRemoteAddr:" + request.getRemoteAddr());
            out.print("<br/>");
            out.println("getRemoteHost:" + request.getRemoteHost());
            out.print("<br/>");
            out.println("getRemoteUser:" + request.getRemoteUser());
            out.print("<br/>");
            out.println("getRequestURI:" + request.getRequestURI());
            out.print("<br/>");
            out.println("getRequestedSessionId:" + request.getRequestedSessionId());
            out.print("<br/>");
            out.println("getScheme:" + request.getScheme());
            out.print("<br/>");
            out.println("getServerName:" + request.getServerName());
            out.print("<br/>");
            out.println("getCookies:" + request.getCookies());
            out.print("<br/>");
            out.println("getRequestURL:" + request.getRequestURL());
            out.print("<br/>");
            out.println("getSession:" + request.getSession()); */
        %>

        <%
            // GOOGLE 
            Enumeration em = request.getHeaderNames();
            while (em.hasMoreElements()) {
                String header = (String) em.nextElement();
                if (header == null) {
                    continue;
                }
//                System.out.println(header + "-->" + request.getHeader(header));
                out.println(header + "-->" + request.getHeader(header)+"<br/>");
            }
        %>
    </body>
</html>
