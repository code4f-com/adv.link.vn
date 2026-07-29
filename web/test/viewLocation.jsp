<%@page import="gk.adv.linnk.vn.object._GeoIP"%>
<%@page import="gk.adv.linnk.vn.object.MyLocation"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%><%@page contentType="text/html; charset=utf-8" %>
<%    Collection<MyLocation> em = Constants.CACHE_LOCAT.values();
    out.print("CACHE IP SIZE: " + _GeoIP.CACHE.size());
    out.print("Location size:" + em.size());

    for (MyLocation one : em) {
        out.println("<br/>city: " + one.getCity());
        out.println("<br/>country_code: " + one.getCountry_code());
        out.println("<br/>country_name: " + one.getCountry_name());
        out.println("<br/>region_code: " + one.getRegion_code());
        out.println("<br/>region_name: " + one.getRegion_name());
        out.println("<br/>--------------------------------<br/>");
    }
%>