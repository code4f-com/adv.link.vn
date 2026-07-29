<%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.object.City"%><%@page import="java.util.ArrayList"%><%@page import="java.util.Map"%><%@page import="java.util.HashMap"%><%@page contentType="text/html; charset=utf-8" %><%@page import="net.sf.json.JSONArray"%><%@page import="net.sf.json.JSONObject"%><%@page import="java.util.List"%><%
    String key = RequestTool.getString(request, "q");
    City ctimpl = new City();
    List<City> allcity = ctimpl.getAll(10, key);
    List m = new ArrayList<City>();
    for (City one : allcity) {
        Map oneMap = new HashMap();
        oneMap.put("id", one.getGgCode());
        oneMap.put("name", one.getMyname());
        m.add(oneMap);
    }
    JSONArray jobj = JSONArray.fromObject(m);
    out.print(jobj.toString());
%>