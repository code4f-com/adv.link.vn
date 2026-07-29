<%@page import="java.io.InputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script language="JavaScript" src="http://j.maxmind.com/app/geoip.js"></script>
    </head>
    <body>
        <h1>Hello World!</h1>

        <br>Country Code:
        <script language="JavaScript">document.write(geoip_country_code());</script>
        <br>Country Name:
        <script language="JavaScript">document.write(geoip_country_name());</script>
        <br>City:
        <script language="JavaScript">document.write(geoip_city());</script>
        <br>Region:
        <script language="JavaScript">document.write(geoip_region());</script>
        <br>Region Name:
        <script language="JavaScript">document.write(geoip_region_name());</script>
        <br>Latitude:
        <script language="JavaScript">document.write(geoip_latitude());</script>
        <br>Longitude:
        <script language="JavaScript">document.write(geoip_longitude());</script>
        <br>Postal Code:
        <script language="JavaScript">document.write(geoip_postal_code());</script>

        <hr/>
        <%
//            String IP = "113.22.9.112";
//            URL link = new URL("http://www.geobytes.com/IpLocator.htm?GetLocation&template=php3.txt&IpAddress=" + IP);
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(link.openStream()));
//            String inputLine;
//
//            while ((inputLine = in.readLine()) != null) {
//                out.println(inputLine);
//            }
//            out.print("****************");
//            in.close();
            //********************
            String ip = "113.22.9.112";
            URL url = new URL("http://freegeoip.net/json/" + ip);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream is = connection.getInputStream();

            int status = connection.getResponseCode();
            if (status != 200) {
                // return null;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            for (String line; (line = reader.readLine()) != null;) {
                out.print(line);
                
                //this API call will return something like:
//                "2.51.255.200"
//            
//        ,"AE","United Arab Emirates","03","Dubai","Dubai","","x-coord","y-coord","",""
    // you can extract whatever you want from it
}
        %>
    </body>
</html>
