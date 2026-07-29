/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.object;

import org.apache.log4j.Logger;

/**
 *
 * @author TUANPLA
 */
public class HeaderRequest {

    static Logger logger = Logger.getLogger(HeaderRequest.class);
    String host;
    String userAgent;
    String accept;
    String acceptLanguage;
    String acceptEncoding;
    String xForwardedFor;
    String xForwardedHost;
    String xForwardedServer;
    String connection;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getxForwardedFor() {
        return xForwardedFor;
    }

    public void setxForwardedFor(String xForwardedFor) {
        this.xForwardedFor = xForwardedFor;
    }

    public String getxForwardedHost() {
        return xForwardedHost;
    }

    public void setxForwardedHost(String xForwardedHost) {
        this.xForwardedHost = xForwardedHost;
    }

    public String getxForwardedServer() {
        return xForwardedServer;
    }

    public void setxForwardedServer(String xForwardedServer) {
        this.xForwardedServer = xForwardedServer;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
