/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author PLATUAN
 */
public class Tool {

    static Logger logger = Logger.getLogger(Tool.class);
//    public static String TOKEN_STRING = "#@#";

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String StringOneLine(String input) {
        if (input == null) {
            return "";
        } else {
            input = input.trim();
            String NL = System.getProperty("line.separator");
            input = input.replaceAll("\n", "");
            input = input.replaceAll("\r", "");
            input = input.replaceAll(NL, "");
            return input;
        }
    }

    public static String validStringJs(String input) {
        if (input == null) {
            return "";
        } else {
            input = input.replaceAll("/", "\\\\/");
            input = input.replaceAll("\"", "\\\\\"");
            input = input.replaceAll("  ", " ");
            input = input.replaceAll("  ", " ");
            input = input.replaceAll("\n", "");
            input = input.replaceAll("\r", "");
            return input;
        }
    }

    /**
     *
     * @param advid is String md5
     * @param destinationURL is domain site
     * @param groupId
     * @param adsCreateID
     * @return
     */
//    public static String builRSAToken(int advid, String destinationURL, int groupId, int adsCreateID) {
//        String str = "";
//        try {
//            str = advid + TOKEN_STRING + destinationURL + TOKEN_STRING + groupId + TOKEN_STRING + adsCreateID;
//            str = RSA.encript(RSA.RSA_PUBLIC_KEY, str);
//        } catch (Exception e) {
//            logger.error(e.getStackTrace());
//        }
//        return str;
//    }

//    public static StatisticClick deRSAToken(String token) {
//        StatisticClick click = null;
//        try {
//            String _token = RSA.deEncript(RSA.RSA_PRIVATE_KEY, token);
//            if (!Tool.checkNull(token)) {
////                Tool.Debug("Token DeEncrypt:" + _token);
//                String[] arr = _token.split(TOKEN_STRING);
////                if (arr != null) {
////                    Tool.Debug("Token arr leng:" + arr.length);
////                }
//                if (arr != null && arr.length == 4) {
//                    click = new StatisticClick();
//                    click.setAdvID(string2Integer(arr[0]));
//                    click.setDestinationURL(arr[1]);
//                    click.setGroupID(string2Integer(arr[2]));
//                    click.setAdsCreateID(string2Integer(arr[3]));
//                } else {
//                    Tool.Debug(" is null or Empty");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e.getStackTrace().toString());
//        }
//        return click;
//    }

    public static String getDomainName(String url) throws MalformedURLException {
        String host = "";
        try {
            if (!url.startsWith("http") && !url.startsWith("https")) {
                url = "http://" + url;
            }
            URL netUrl = new URL(url);
            host = netUrl.getHost();
            if (host.startsWith("www")) {
                host = host.substring("www".length() + 1);
            }
        } catch (Exception e) {
            logger.error("[ERROR getDomainName] ==> " + url);
        }
        return host;
    }

    public static void Debug(String bug) {
        System.out.println("adv.link.vn: " + bug);
    }

    public static void Debug(int bug) {
        System.out.println("adv.link.vn: " + bug);
    }

    public static void sleepSecond(int second) {
        long start = System.currentTimeMillis();
        while (true) {
            long runTime = System.currentTimeMillis();
            long distance = runTime - start;
            if (distance > second * 1000) {
                break;
            }
        }
    }

    private static final Random RANDOM = new SecureRandom();
    // Get Random String

    public static String getRandomString(int length) {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";

        String pw = "";
        for (int i = 0; i < length; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
    }
    // GetlogMessage for log4j

    public static String getCurrentURL(HttpServletRequest request) {
        String currentURL = null;
        if (request.getAttribute("javax.servlet.forward.request_uri") != null) {
            currentURL = (String) request.getAttribute("javax.servlet.forward.request_uri");
        } else {
            currentURL = request.getRequestURI();
        }
        if (currentURL != null && request.getAttribute("javax.servlet.include.query_string") != null) {
            currentURL += "?" + request.getQueryString();
        }
        return currentURL;
    }

    public static String getLogMessage(Exception ex) {
        StackTraceElement[] trace = ex.getStackTrace();
        String str = DateProc.createTimestamp() + "||" + ex.getMessage() + "||";
        for (int i = 0; i < trace.length; i++) {
            str += trace[i] + "\t\n";
        }
        return str;
    }

    public static boolean checkNull(String input) {
        if (input == null || input.equalsIgnoreCase("null") || input.equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Chuyển có dấu sang không dấu. Nếu = Null trả ra ""
     *
     * @param org
     * @return
     */
    public static String convert2NoSign(String org) {
        if (org == null) {
            org = "";
            return org;
        }
        char arrChar[] = org.toCharArray();
        char result[] = new char[arrChar.length];
        for (int i = 0; i < arrChar.length; i++) {
            switch (arrChar[i]) {
                case '\u00E1':
                case '\u00E0':
                case '\u1EA3':
                case '\u00E3':
                case '\u1EA1':
                case '\u0103':
                case '\u1EAF':
                case '\u1EB1':
                case '\u1EB3':
                case '\u1EB5':
                case '\u1EB7':
                case '\u00E2':
                case '\u1EA5':
                case '\u1EA7':
                case '\u1EA9':
                case '\u1EAB':
                case '\u1EAD':
                case '\u0203':
                case '\u01CE': {
                    result[i] = 'a';
                    break;
                }
                case '\u00E9':
                case '\u00E8':
                case '\u1EBB':
                case '\u1EBD':
                case '\u1EB9':
                case '\u00EA':
                case '\u1EBF':
                case '\u1EC1':
                case '\u1EC3':
                case '\u1EC5':
                case '\u1EC7':
                case '\u0207': {
                    result[i] = 'e';
                    break;
                }
                case '\u00ED':
                case '\u00EC':
                case '\u1EC9':
                case '\u0129':
                case '\u1ECB': {
                    result[i] = 'i';
                    break;
                }
                case '\u00F3':
                case '\u00F2':
                case '\u1ECF':
                case '\u00F5':
                case '\u1ECD':
                case '\u00F4':
                case '\u1ED1':
                case '\u1ED3':
                case '\u1ED5':
                case '\u1ED7':
                case '\u1ED9':
                case '\u01A1':
                case '\u1EDB':
                case '\u1EDD':
                case '\u1EDF':
                case '\u1EE1':
                case '\u1EE3':
                case '\u020F': {
                    result[i] = 'o';
                    break;
                }
                case '\u00FA':
                case '\u00F9':
                case '\u1EE7':
                case '\u0169':
                case '\u1EE5':
                case '\u01B0':
                case '\u1EE9':
                case '\u1EEB':
                case '\u1EED':
                case '\u1EEF':
                case '\u1EF1': {
                    result[i] = 'u';
                    break;
                }
                case '\u00FD':
                case '\u1EF3':
                case '\u1EF7':
                case '\u1EF9':
                case '\u1EF5': {
                    result[i] = 'y';
                    break;
                }
                case '\u0111': {
                    result[i] = 'd';
                    break;
                }
                case '\u00C1':
                case '\u00C0':
                case '\u1EA2':
                case '\u00C3':
                case '\u1EA0':
                case '\u0102':
                case '\u1EAE':
                case '\u1EB0':
                case '\u1EB2':
                case '\u1EB4':
                case '\u1EB6':
                case '\u00C2':
                case '\u1EA4':
                case '\u1EA6':
                case '\u1EA8':
                case '\u1EAA':
                case '\u1EAC':
                case '\u0202':
                case '\u01CD': {
                    result[i] = 'A';
                    break;
                }
                case '\u00C9':
                case '\u00C8':
                case '\u1EBA':
                case '\u1EBC':
                case '\u1EB8':
                case '\u00CA':
                case '\u1EBE':
                case '\u1EC0':
                case '\u1EC2':
                case '\u1EC4':
                case '\u1EC6':
                case '\u0206': {
                    result[i] = 'E';
                    break;
                }
                case '\u00CD':
                case '\u00CC':
                case '\u1EC8':
                case '\u0128':
                case '\u1ECA': {
                    result[i] = 'I';
                    break;
                }
                case '\u00D3':
                case '\u00D2':
                case '\u1ECE':
                case '\u00D5':
                case '\u1ECC':
                case '\u00D4':
                case '\u1ED0':
                case '\u1ED2':
                case '\u1ED4':
                case '\u1ED6':
                case '\u1ED8':
                case '\u01A0':
                case '\u1EDA':
                case '\u1EDC':
                case '\u1EDE':
                case '\u1EE0':
                case '\u1EE2':
                case '\u020E': {
                    result[i] = 'O';
                    break;
                }
                case '\u00DA':
                case '\u00D9':
                case '\u1EE6':
                case '\u0168':
                case '\u1EE4':
                case '\u01AF':
                case '\u1EE8':
                case '\u1EEA':
                case '\u1EEC':
                case '\u1EEE':
                case '\u1EF0': {
                    result[i] = 'U';
                    break;
                }

                case '\u00DD':
                case '\u1EF2':
                case '\u1EF6':
                case '\u1EF8':
                case '\u1EF4': {
                    result[i] = 'Y';
                    break;
                }
                case '\u0110':
                case '\u00D0':
                case '\u0089': {
                    result[i] = 'D';
                    break;
                }
                case (char) 160: {
                    result[i] = ' ';
                    break;
                }
                default:
                    result[i] = arrChar[i];
            }
        }
        return new String(result);
    }

    /**
     * Loai bỏ các ky tự đặc biệt ra khỏi String
     *
     * @param input
     * @return
     */
    public static String convertTitle(String input) {
        if (input == null) {
            return null;
        }
        input = Tool.convert2NoSign(input);
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    public static String[] tag2Nosign(String[] tag) {
        if (tag == null || tag.length == 0) {
            return null;
        } else {
            for (int i = 0; i < tag.length; i++) {
                tag[i] = convertTitle(tag[i]);
            }
        }
        return tag;
    }

    /**
     * Tag String[] to abc,def,ghe
     *
     * @param tag
     * @return
     */
    public static String tag2String(String[] tag) {
        String tem = "";
        for (String one : tag) {
            tem += one + ",";
        }
        if (tem.endsWith(",")) {
            tem = tem.substring(0, tem.length() - 1);
        }
        return tem;
    }

    public static boolean getBooleanStatus(String status) {
        if (status != null && (status.equals("1") || status.equals("true"))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean getBoolean(int status) {
        if (status == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String validStringRequest(String input) {
        if (input != null) {
            input = input.trim();
        } else {
            input = "";
        }
        return input;
    }

    /**
     * string2Integer
     *
     * @param input
     * @return Default return 0 neu String = 0 or notvalid
     */
    public static int string2Integer(String input) {
        int tem;
        try {
            tem = Integer.parseInt(input.trim());
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static int string2Integer(String input, int defaultVal) {
        int tem;
        try {
            tem = Integer.parseInt(input.trim());
        } catch (Exception e) {
            tem = defaultVal;
        }
        return tem;
    }

    /**
     * string2Integer
     *
     * @param input
     * @return Default return 0 neu String = 0 or notvalid
     */
    public static long string2Long(String input) {
        long tem;
        try {
            tem = Long.parseLong(input);
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static double string2Double(String input) {
        double tem;
        try {
            tem = Double.parseDouble(input);
        } catch (Exception e) {
            tem = 0;
        }
        return tem;
    }

    public static String stringToHTMLString(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        // true if last char was blank
        boolean lastWasBlankChar = false;
        int len = string.length();
        char c;

        for (int i = 0; i < len; i++) {
            c = string.charAt(i);
            if (c == ' ') {
                // blank gets extra work,
                // this solves the problem you get if you replace all
                // blanks with &nbsp;, if you do that you loss
                // word breaking
                if (lastWasBlankChar) {
                    lastWasBlankChar = false;
                    sb.append("&nbsp;");
                } else {
                    lastWasBlankChar = true;
                    sb.append(' ');
                }
            } else {
                lastWasBlankChar = false;
                //
                // HTML Special Chars
                if (c == '"') {
                    sb.append("&quot;");
                } else if (c == '&') {
                    sb.append("&amp;");
                } else if (c == '<') {
                    sb.append("&lt;");
                } else if (c == '>') {
                    sb.append("&gt;");
                } else if (c == '\n') // Handle Newline
                {
                    sb.append("&lt;br/&gt;");
                } else {
                    int ci = 0xffff & c;
                    if (ci < 160) // nothing special only 7 Bit
                    {
                        sb.append(c);
                    } else {
                        // Not 7 Bit use the unicode system
                        sb.append("&#");
                        sb.append(ci);
                        sb.append(';');
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param sStr
     * @param oldStr
     * @param newStr
     * @return
     */
    public static String replaceString(String sStr, String oldStr, String newStr) {
        sStr = (sStr == null ? "" : sStr);
        String strVar = sStr;
        String tmpStr = "";
        String finalStr = "";
        int stpos = 0, endpos = 0, strLen = 0;
        while (true) {
            strLen = strVar.length();
            stpos = 0;
            endpos = strVar.indexOf(oldStr, stpos);
            if (endpos == -1) {
                break;
            }
            tmpStr = strVar.substring(stpos, endpos);
            tmpStr = tmpStr.concat(newStr);
            strVar = strVar.substring(endpos + oldStr.length() > sStr.length() ? endpos : endpos + oldStr.length(), strLen);
            finalStr = finalStr.concat(tmpStr);
            stpos = endpos;
        }
        finalStr = finalStr.concat(strVar);
        return finalStr;
    }

    /**
     *
     * @param number
     * @return
     */
    public static String formatNumberPhone(String number) {
        if (number == null) {
            number = "";
            return number;
        }
        number = number.replaceAll("o", "0");
        if (number.startsWith("84")) {
            return number;
        } else if (number.startsWith("0")) {
            number = "84" + number.substring(1);
        } else if (number.startsWith("+84")) {
            number = number.substring(1);
        } else {
            number = "84" + number;
        }
        return number;
    }

    /**
     *
     * @param sStr
     * @return
     */
    public static String validStringUserInput(String sStr) {

        if (sStr == null) {
            sStr = "";
        }
        sStr = sStr.replaceAll("&", "&amp;");
        sStr = sStr.replaceAll("\"", "&quot;");
        sStr = sStr.replaceAll("'", "&apos;");
        sStr = sStr.replaceAll("<", "&lt;");
        sStr = sStr.replaceAll(">", "&gt;");
        sStr = sStr.replaceAll("&lt;br&gt;", "<br/>");
        sStr = sStr.replaceAll("&lt;br/&gt;", "<br/>");
        sStr = sStr.replaceAll("&lt;p&gt;", "<p>");
        sStr = sStr.replaceAll("&lt;/p&gt;", "</p>");
        sStr = sStr.replaceAll("&lt;hr&gt;", "<hr/>");
        sStr = sStr.replaceAll("&lt;hr/&gt;", "<hr/>");
        sStr = sStr.replaceAll("\n", "<br/>");
        return sStr;
    }

    /**
     * Xử lý các ly tự đặc biệt html trong title
     *
     * @param str
     * @return
     */
    public static String validTitle(String str) {
        if (str == null) {
            str = "";
        }
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("'", "&apos;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        return str.trim();
    }

    public static String trimString(String input, int lenght) {
        String SPECIAL_CHARACTOR = "[ .-/]";
        String str = "";
        if (input == null) {
            return str;
        } else {
            StringTokenizer tokenSpace = new StringTokenizer(input, SPECIAL_CHARACTOR);
            while (tokenSpace.hasMoreTokens()) {
                String tempStr = tokenSpace.nextToken();
                if ((str + tempStr).length() < lenght) {
                    str += tempStr + " ";
                } else {
                    str += "...";
                    break;
                }
            }

            return str.trim();
        }
    }

    public static boolean cacheImageRequest(String urlImage, String realPath, boolean resize, int width_max) {
        boolean flag = false;
        try {
            URL urlimg = new URL(urlImage);
            URLConnection ucconnimg = urlimg.openConnection();
//            InputStream ipstimg = ucconnimg.getInputStream();
            //**************
            String extention = "jpg";
            ImageInputStream iis = ImageIO.createImageInputStream(ucconnimg.getInputStream());
            Iterator iter = ImageIO.getImageReaders(iis);
            if (iter.hasNext()) {
                ImageReader reader = (ImageReader) iter.next();
                reader.setInput(iis);
                String formatName = reader.getFormatName();
                // Gan lai Extention by Final Image Type
                extention = formatName;
            }
            //**************
            if (resize) {
                // Thay doi Kich Thuoc Anh Lay Ve                
                FileUtils.resizeMaxWithWriteImg(urlimg, width_max, realPath, extention);
            } else {
                FileUtils.writeImg(urlimg, realPath, realPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static String getUrlFromImageTag(String imageTag) {
        try {
            imageTag = imageTag.replaceAll(" ", "");
            imageTag = imageTag.substring(imageTag.indexOf("src=\"") + 5);
            imageTag = imageTag.substring(0, imageTag.indexOf("\""));
        } catch (Exception e) {
        }
        return imageTag;
    }

    public static String getLongTimeString() {
        String str = "";
        long time = new Date().getTime();
        str += time;
        return str;
    }

    public static String getStringURL(String input) {
        if (input == null || input.equals("")) {
            return "";
        }
        input = input.trim();
        input = convert2NoSign(input);
        input = input.replaceAll("&amp;", "");
        input = input.replaceAll("amp;", "");
        input = input.replaceAll("&quot;", "");
        input = input.replaceAll("quot;", "");
        input = input.replaceAll("&apos;", "");
        input = input.replaceAll("apos;", "");
        input = input.replaceAll("&lt;", "");
        input = input.replaceAll("lt;", "");
        input = input.replaceAll("&gt;", "");
        input = input.replaceAll("gt;", "");
        input = input.replaceAll("&amp;", "");
        input = input.replaceAll("&AMP;", "");
        input = input.replaceAll("'", "");
        input = input.replaceAll(":", "");
        input = input.replaceAll(",", "");
        input = input.replaceAll("\\.", "");
        input = input.replaceAll("‘", "");
        input = input.replaceAll("“", "");
        input = input.replaceAll("”", "");
        input = input.replaceAll("\\?", "");
        input = input.replaceAll("~", "");
        input = input.replaceAll("!", "");
        input = input.replaceAll("@", "");
        input = input.replaceAll("#", "");
        input = input.replaceAll("$", "");
        input = input.replaceAll("%", "");
        input = input.replaceAll("^", "");
        input = input.replaceAll("&", "");
        input = input.replaceAll("…", "");
        input = input.replaceAll("\\*", "");
        input = input.replaceAll("\\(", "");
        input = input.replaceAll("\\)", "");
        input = input.replaceAll("\"", "");
        input = input.replaceAll("\'", "");
        input = input.replaceAll(" ", "-");
        input = input.replaceAll("/", "-");
        while (input.indexOf("--") > -1) {
            input = input.replaceAll("--", "-");
        }
        input = input.toLowerCase();
        return input;
    }

    public static String getStringAlt(String input) {
        if (input == null || input.equals("")) {
            return "";
        }
        input = input.trim();
        input = input.replaceAll("&amp;", "");
        input = input.replaceAll("'", "");
        input = input.replaceAll(":", "");
        input = input.replaceAll(",", "");
        input = input.replaceAll("\\.", "");
        input = input.replaceAll("‘", "");
        input = input.replaceAll("“", "");
        input = input.replaceAll("”", "");
        input = input.replaceAll("\\?", "");
        input = input.replaceAll("~", "");
        input = input.replaceAll("!", "");
        input = input.replaceAll("@", "");
        input = input.replaceAll("#", "");
        input = input.replaceAll("$", "");
        input = input.replaceAll("%", "");
        input = input.replaceAll("^", "");
        input = input.replaceAll("&", "");
        input = input.replaceAll("\\*", "");
        input = input.replaceAll("\\(", "");
        input = input.replaceAll("\\)", "");
        input = input.replaceAll("\"", "");
        input = input.replaceAll(" ", "-");
        while (input.indexOf("--") > -1) {
            input = input.replaceAll("--", "-");
        }
        return input;
    }

    public static String readFileText(String path) {
        String Content = "";
        String sContent = "";
        try {
            FileInputStream fstream = new FileInputStream(path);
            try (DataInputStream in = new DataInputStream(fstream)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                while ((Content = br.readLine()) != null) {
                    sContent += Content + "\n";
                }
            }
        } catch (Exception e) {
            System.err.println("Tool : Error: ReadFile >> " + e.getMessage());
        }
        return sContent;
    }

    public static boolean writeFileText(String content, String path) {
        boolean flag = false;
        try {
            try (Writer outw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"))) {
                outw2.write(content);
                flag = true;
            } catch (Exception e) {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    public static String priceWithDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal(Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal(String price) {
        double dprice = string2Double(price);
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(dprice);
    }

    public static String priceWithoutDecimal(int price) {
        double dprice = string2Double(price + "");
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(dprice);
    }

    public static String priceToString(Double price) {
        String toShow = priceWithoutDecimal(price);
        if (toShow.indexOf(".") > 0) {
            return priceWithDecimal(price);
        } else {
            return priceWithoutDecimal(price);
        }
    }
}
