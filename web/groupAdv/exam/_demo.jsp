<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="gk.adv.linnk.vn.object.GroupAdv"%>
<%@page import="gk.adv.linnk.vn.utils.RequestTool"%>
<%@page contentType="text/html; charset=utf-8" autoFlush="true" %>
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/advzone.css")%>" />
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/groupAdv/exam/cssdocto.css")%>" />
<link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/groupAdv/exam/cssngang.css")%>" />
<%
    int type = RequestTool.getInt(request, "type");
%>
<%
    if (type == GroupAdv.TYPE.MANY_HORIZONTAL_RANDOM.getValue()) {
%>
<%@include file="/groupAdv/exam/MANY_HORIZONTAL_RANDOM.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.MANY_VERTICAL_RANDOM.getValue()) {
%>
<%@include file="/groupAdv/exam/MANY_VERTICAL_RANDOM.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM_124.getValue()) {
%>
<%@include file="/groupAdv/exam/IMAGE_HOVER_DOC_RANDOM_124.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM.getValue()) {
%>
<jsp:include page="/groupAdv/exam/IMAGE_HOVER_DOC_RANDOM.jsp"/>
<%
    }
    if (type == GroupAdv.TYPE.DOC_RAN_MIN_200.getValue()) {
%>
<jsp:include page="/groupAdv/exam/DOC_RAN_MIN_200.jsp"/>
<%
    }
    if (type == GroupAdv.TYPE.IMAGE_HOVER_DOC_RANDOM_210.getValue()) {
%>
<%@include file="/groupAdv/exam/IMAGE_HOVER_DOC_RANDOM_210.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.MEDIUM_DOC_RANDOM.getValue()) {
%>
<%@include file="/groupAdv/exam/MEDIUM_DOC_RANDOM.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.MEDIUM_NGANG_RANDOM.getValue()) {
%>
<%@include file="/groupAdv/exam/MEDIUM_NGANG_RANDOM.jsp" %>
<%
    }
    if (type == GroupAdv.TYPE.SLIDE_IMG_TEXT.getValue()) {
%>
<%@include file="/groupAdv/exam/SlideImgText.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2_FRAME_IMG.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide2Frameimg.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_IMG_TEXT_LAYOUT_2.getValue()) {
%>
<%@include file="/groupAdv/exam/SLIDE_IMG_TEXT_LAYOUT_2.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_IMG_TEXT_LAYOUT_3.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide_layout3.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_1.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3Frameimg.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_2.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3FrameA2-T-A3.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_3FRAME_LAYOUT_3.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3FrameT-A3-T.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2_FRAME_T_A2.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3FrameT-A2.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2_FRAME_A3_T.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3FrameA3-T.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2_FRAME_T_A3.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide3FrameT-A3.jsp" %>
<%
} else if (type == GroupAdv.TYPE.RAN_FRAME_IMG_TEXT.getValue()) {
%>
<%@include file="/groupAdv/exam/RAN_FRAME_IMG_TEXT.jsp" %>
<%
} else if (type == GroupAdv.TYPE.ROTATE_FRAME_IMG_TEXT.getValue()) {
%>
<%@include file="/groupAdv/exam/Rotate2FrameText.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_FRAME_IMG_TEXT.getValue()) {
%>
<%@include file="/groupAdv/exam/Slide2FrameText.jsp" %>
<%
} else if (type == GroupAdv.TYPE.SLIDE_FRAME_1IMG_A1.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_FRAME_1IMG_A1.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A1_FRAME_1IMG.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A1_FRAME_1IMG.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_FRAME_1IMG_A2.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_FRAME_1IMG_A2.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A2_FRAME_1IMG.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A2_FRAME_1IMG.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_FRAME_1IMG_A3.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_FRAME_1IMG_A3.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A3_FRAME_1IMG.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A3_FRAME_1IMG.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2FRAME_1IMG.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_2FRAME_1IMG.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_2FRAME_IMAGE_HOVER.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_2FRAME_IMAGE_HOVER.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A1_HOVER.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A1_HOVER.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_HOVER_A1.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_HOVER_A1.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A2_HOVER.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A2_HOVER.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_HOVER_A2.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_HOVER_A2.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_HOVER_A3.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_HOVER_A3.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_A3_HOVER.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_A3_HOVER.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_1IMG_HOVER.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_1-IMAGE_HOVER.jsp"/>
<%
} else if (type == GroupAdv.TYPE.SLIDE_HOVER_1IMG.getValue()) {
%>
<jsp:include page="/groupAdv/exam/SLIDE_HOVER__1-IMAGE.jsp"/>
<%
    }
%>
<%!
    /**
     * The code of method _jspService(HttpServletRequest, HttpServletResponse)
     * is exceeding the 65535 bytes Byte limit Exceed problem when reloading a
     * jsp page?
     *
     *
     * We "fixed" this here by setting mappedfile to false for JspServlet in our
     * Tomcat-Config. Go to %TOMCAT_HOME%/conf/web.xml and add the following
     * init-param to the JspServlet:
     *
     * <init-param>
     * <param-name>mappedfile</param-name>
     * <param-value>false</param-value>
     * </init-param>
     *
     * This does not solve the 64 KiB limit but helps in that way that it occurs
     * much later because the generated code is shorter then.
     *
     * //------------ Move some of the logic out of your JSP pages and into
     * dedicated beans.
     *
     * The limit of 65k bytes per Java method is insanely high and only very,
     * very long methods exceed it.
     *
     * Note also that the length of any strong constants is not included in that
     * method, so you simply have some absurd amount of logic in that single
     * method (note: JSPs are compiled into Servlets, wher the _jspService
     * method holds the main bulk of the content of the JSP).
     *
     * So you simply have too much logic. You shouldn't have any logic in your
     * JSP at all (only output rendering).
     *
     * Also note that <%@ include and <jsp:include are simply two different ways
     * to do the same thing in this case, so that won't make a difference.
     *
     */
%>