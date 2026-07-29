/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author TUANPLA
 */
public class RequestTool {

    public static int getInt(HttpServletRequest request, String param) {
        int tem;
        try {
            tem = Integer.parseInt(request.getParameter(param).trim());
        } catch (Exception e) {
            tem = 0;
            Tool.Debug("RequestTool: getInt  => [Param:"+param+"] " + e.getMessage());
        }
        return tem;
    }

    public static int getInt(HttpServletRequest request, String param, int defaultVal) {
        int tem;
        try {
            tem = Integer.parseInt(request.getParameter(param).trim());
        } catch (Exception e) {
            Tool.Debug("RequestTool: getInt - defaultVal => [Param:"+param+"] " + e.getMessage());
            tem = defaultVal;
        }
        return tem;
    }

    /**
     * string2Integer
     *
     * @param request
     * @param param
     * @param input
     * @return Default return 0 neu String = 0 or notvalid
     */
    public static long getLong(HttpServletRequest request, String param) {
        long tem;
        try {
            tem = Long.parseLong(request.getParameter(param).trim());
        } catch (Exception e) {
            Tool.Debug("RequestTool: getLong => [Param:"+param+"] " + Tool.getCurrentURL(request));
            tem = 0;
        }
        return tem;
    }

    public static double getDouble(HttpServletRequest request, String param) {
        double tem;
        try {
            tem = Double.parseDouble(request.getParameter(param).trim());
        } catch (Exception e) {
            Tool.Debug("RequestTool: getDouble => [Param:"+param+"] " + e.getMessage());
            tem = 0;
        }
        return tem;
    }

    public static String getString(HttpServletRequest request, String param) {
        String str;
        try {
            str = request.getParameter(param).trim();
        } catch (Exception e) {
            Tool.Debug("RequestTool getstring => [Param:"+param+"] " + e.getMessage());
            str = "";
        }
        return str;
    }
}
