<%@page import="gk.adv.linnk.vn.object.City"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.multipart.request.MultipartFile"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.multipart.request.HttpServletMultipartRequest"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@include file="/includes/header.jsp" %>
        <%@include file="/includes/datePicker.jsp" %>
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/custom.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/select2.css" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/select2.js"></script>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/select2_locale_vi.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                // Sự kiện khi nhấn vào các tab của menu
                $("a.tab").click(function() {
                    // tắt tất cả các tab
                    $(".active").removeClass("active");
                    // bật tab đang click lên
                    $(this).addClass("active");
                    // tạo hiệu ứng trượt lên trên cho nội dung của tab đang click
                    $(".content").slideUp();
                    // Nếu là tab đầu tiên thì set hiệu ứng là trượt xuống dưới
                    var content_show = $(this).attr("tabval");
                    $("#" + content_show).slideDown();
                });
            });
            function checkLength(maxLen, itemId) {
                var currentLength = $("#" + itemId).val().length;
                var displayLength = maxLen - currentLength;
                if (displayLength > 0) {
                    $("#" + itemId + "_warning").html(displayLength);
                } else {
                    $("#" + itemId + "_warning").innerHTML = 0;
                    $("#" + itemId).val($("#" + itemId).val().substring(0, maxLen));
                }
            }
            //----
            $(document).ready(function() {
                $("#region").select2({
                    placeholder: "Nhập tỉnh thành phố để lựa chọn",
                    minimumInputLength: 2,
                    maximumSelectionSize: 30,
                    tokenSeparators: [","],
                    multiple: true,
                    allowClear: true,
                    matcher: function(term, text, opt) {
                        return text.toUpperCase().indexOf(term.toUpperCase()) >= 0
                                || opt.attr("alt").toUpperCase().indexOf(term.toUpperCase()) >= 0;
                    },
                    ajax: {// instead of writing the function to execute the request we use Select2's convenient helper
                        url: "/advertise/location.jsp",
                        dataType: 'json',
                        quietMillis: 500,
                        data: function(term, page) {
                            return {
                                q: term, // search term
                                page_limit: 10
                            };
                        },
                        results: function(data, page) {
                            return {results: data};
                        }
                    },
                    formatResult: function(item) {
                        return ('<div>' + item.name + '</div>');
                    },
                    formatSelection: function(item) {
                        return (item.name);
                    },
                    dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
                    escapeMarkup: function(m) {
                        return m;
                    }
                });
            });
        </script>
    </head>
    <body>
        <%
            if (!adminInfo.checkEdit(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            HttpServletMultipartRequest req = new HttpServletMultipartRequest(request);
            Advertise advDao = new Advertise();
            int advID = 0, kind = 0;
            if (req.getParameter("submit") != null) {
                advID = RequestTool.getInt(req, "id");
                kind = RequestTool.getInt(req, "kind");
                advDao = advDao.getAdvertise(advID);
                if (kind == Advertise.KIND.FLASH.getValue()) {
                    String title = RequestTool.getString(req, "title");
                    String desc = RequestTool.getString(req, "desc");
                    String startTime = RequestTool.getString(req, "startTime");
                    String endTime = RequestTool.getString(req, "endTime");
                    int width = RequestTool.getInt(req, "width");
                    int height = RequestTool.getInt(req, "height");
                    int status = RequestTool.getInt(req, "status");
                    MultipartFile file = req.getFileParameter("flash");
                    //--
                    advDao.setKind(kind);
                    advDao.setTitle_top(title);
                    advDao.setDesc(desc);
                    advDao.setWidth(width);
                    advDao.setHeight(height);
                    advDao.setStartTime(DateProc.String2Timestamp(startTime));
                    advDao.setEndTime(DateProc.String2Timestamp(endTime));
                    advDao.setUpdateBy(adminInfo.getAccID());
                    advDao.setStatus(status);
                    if (advDao.updateFlash(advDao, file)) {
                        session.setAttribute("mess", "Cập nhật dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/sys-admin/advertise-manager/show.html'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Cập nhật dữ liệu lỗi!");
                    }
                } else if (kind == Advertise.KIND.IMAGE.getValue()
                        || kind == Advertise.KIND.IMAGE_SLIDE.getValue()) {
                    // IMAGE
                    String titleTop = RequestTool.getString(req, "titleTop");
                    String desc = RequestTool.getString(req, "desc");
                    String destinationURL = RequestTool.getString(req, "destinationURL");
                    String startTime = RequestTool.getString(req, "startTime");
                    String endTime = RequestTool.getString(req, "endTime");
                    int priceSell = RequestTool.getInt(req, "priceSell");
                    int zoom = RequestTool.getInt(req, "zoom");
                    Tool.Debug("Zoom" + zoom);
                    int width = RequestTool.getInt(req, "width");
                    int height = RequestTool.getInt(req, "height");
                    int status = RequestTool.getInt(req, "status");
                    MultipartFile file = req.getFileParameter("image");
                    MultipartFile file1x2 = req.getFileParameter("imageslide");
                    //--
                    advDao.setTitle_top(titleTop);
                    advDao.setDesc(desc);
                    advDao.setDestinationUrl(destinationURL);
                    advDao.setWidth(width);
                    advDao.setHeight(height);
                    advDao.setStartTime(DateProc.String2Timestamp(startTime));
                    advDao.setEndTime(DateProc.String2Timestamp(endTime));
                    advDao.setPriceSell(priceSell);
                    advDao.setKind(kind);
                    advDao.setStatus(status);
                    advDao.setUpdateBy(adminInfo.getAccID());
                    if (advDao.updateImage(advDao, file, file1x2, zoom)) {
                        session.setAttribute("mess", "Cập nhật dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/sys-admin/advertise-manager/show.html?kind=" + kind + "'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Cập nhật dữ liệu lỗi!");
                    }
                } else if (kind == Advertise.KIND.IMAGE_TEXT.getValue() || kind == Advertise.KIND.IMAGE_PRICE.getValue()) {
                    //  IMAGE_AND TEXT
                    String titleTop = RequestTool.getString(req, "titleTop");
                    String destinationURL = RequestTool.getString(req, "destinationURL");
                    String desc = RequestTool.getString(req, "desc");
                    String titleShort = RequestTool.getString(req, "titleShort");
                    String titlePrice = RequestTool.getString(req, "titlePrice");
                    int priceSell = RequestTool.getInt(req, "priceSell");
                    int priceRoot = RequestTool.getInt(req, "priceRoot");
                    String startTime = RequestTool.getString(req, "startTime");
                    String endTime = RequestTool.getString(req, "endTime");
                    int status = RequestTool.getInt(req, "status");
                    MultipartFile file = req.getFileParameter("image");
                    String chkLocationAll = RequestTool.getString(req, "chkLocationAll");
                    String chkBac = RequestTool.getString(req, "chkBac");
                    String chkTrung = RequestTool.getString(req, "chkTrung");
                    String chkNam = RequestTool.getString(req, "chkNam");
//                    Tool.Debug("chkLocationAll: " + chkLocationAll);
//                    Tool.Debug("chkBac: " + chkBac);
//                    Tool.Debug("chkTrung: " + chkTrung);
//                    Tool.Debug("chkNam: " + chkNam);
                    String region = "";
                    if (chkLocationAll.equalsIgnoreCase("on")) {
                        region = City.buildRegion(City.getAll());
                    } else {
                        if (chkBac.equalsIgnoreCase("on")) {
                            region = City.buildRegion(City.getMB());
                        }
                        if (chkTrung.equalsIgnoreCase("on")) {
                            region += "," + City.buildRegion(City.getMT());
                        }
                        if (chkNam.equalsIgnoreCase("on")) {
                            region += "," + City.buildRegion(City.getMN());
                        }
                    }
                    Tool.Debug("region: " + region);
                    //---
                    advDao.setKind(kind);
                    advDao.setTitle_top(titleTop);
                    advDao.setDestinationUrl(destinationURL);
                    advDao.setDesc(desc);
                    advDao.setTitle_II(titleShort);
                    advDao.setTitle_price(titlePrice);
                    advDao.setPriceSell(priceSell);
                    advDao.setPrice_root(priceRoot);
                    advDao.setStartTime(DateProc.String2Timestamp(startTime));
                    advDao.setEndTime(DateProc.String2Timestamp(endTime));
                    advDao.setStatus(status);
                    advDao.setRegion(region);
                    advDao.setUpdateBy(adminInfo.getAccID());
                    if (advDao.updateImageText(advDao, file)) {
                        session.setAttribute("mess", "Cập nhật dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/sys-admin/advertise-manager/show.html?kind=" + kind + "'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Cập nhật dữ liệu lỗi!");
                    }
                    session.setAttribute("mess", "Test Cập nhật dữ liệu!");
                } else {
                    System.out.println("Edit Ads khong thuoc kieu gi");
                }
                //------------
            } else {
                advID = RequestTool.getInt(request, "id");
                kind = RequestTool.getInt(request, "kind");
                advDao = advDao.getAdvertise(advID);
                if (request.getParameter("submit") == null && advDao == null) {
                    session.setAttribute("mess", "Yêu cầu không hợp lệ");
                    out.print("<script>location.href = '" + request.getContextPath() + "/sys-admin/advertise-manager/show.html?kind=" + kind + "'</script>");
                    return;
                }
            }
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%                        
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                            %>
                        </div>
                        <%if (kind == Advertise.KIND.IMAGE_TEXT.getValue()) {
                        %>
                        <%@include file="/advertise/tem/editImageText.jsp" %>
                        <%} else if (kind == Advertise.KIND.IMAGE.getValue() || kind == Advertise.KIND.IMAGE_SLIDE.getValue()) {
                        %>
                        <%@include file="/advertise/tem/editImage.jsp" %>
                        <%                        } else if (kind == Advertise.KIND.FLASH.getValue()) {
                        %>
                        <%@include file="/advertise/tem/editFlash.jsp" %>
                        <%                        } else if (kind == Advertise.KIND.IMAGE_PRICE.getValue()) {
                        %>
                        <%@include file="/advertise/tem/editImagePrice.jsp" %>
                        <%                            }
                        %>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>