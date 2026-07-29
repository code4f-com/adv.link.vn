<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.multipart.request.MultipartFile"%><%@page import="gk.adv.linnk.vn.utils.RequestTool"%><%@page import="gk.adv.linnk.vn.multipart.request.HttpServletMultipartRequest"%>
<%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@include file="/customer/incl/header.jsp" %>        <%@include file="/includes/datePicker.jsp" %>
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/custom.js"></script>
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

            function validForm()
            {
                var title = $("#titleTop").val();
                if (title.length == 0 || title.length > 40) {
                    jAlert("Bạn cần nhập tiêu đề quảng cáo hợp lệ</br>Tiêu đề là bắt buộc và phải ít hơn 40 ký tự", "Thông báo", null);
                    return false;
                }
            }
            function checkLength(maxLen, itemId) {
                var currentLength = $("#" + itemId).val().length;
                var displayLength = maxLen - currentLength;
                if (displayLength > 0) {
                    $("#" + itemId + "_warning").html(displayLength);
                } else {
                    $("#" + itemId + "_warning").html(0);
                    $("#" + itemId).val($("#" + itemId).val().substring(0, maxLen));
                }
            }
            function checkLengEditer(maxLen, itemId)
            {
                var editorcontent = CKEDITOR.instances['contentEdt'].getData().replace(/<[^>]*>/gi, '');
                if (editorcontent.length) {
                    return true;
                }
                else {
                    // alert (you'll want to use jQuery to make this nice!)
                    alert('What no copy?');
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%
            HttpServletMultipartRequest req = new HttpServletMultipartRequest(request);
            if (req.getParameter("submit") != null) {
                Advertise advDao = new Advertise();
                int kind = RequestTool.getInt(req, "kind");
                if (kind == Advertise.KIND.FLASH.getValue()) {
                    String title = RequestTool.getString(req, "title");
                    String desc = RequestTool.getString(req, "desc");
                    String startTime = RequestTool.getString(req, "startTime");
                    String endTime = RequestTool.getString(req, "endTime");
                    int width = RequestTool.getInt(req, "width");
                    int height = RequestTool.getInt(req, "height");
                    MultipartFile file = req.getFileParameter("flash");
                    //--
                    advDao.setTitle_top(title);
                    advDao.setDesc(desc);
                    advDao.setStartTime(DateProc.String2Timestamp(startTime));
                    advDao.setEndTime(DateProc.String2Timestamp(endTime));
                    advDao.setKind(kind);
                    advDao.setWidth(width);
                    advDao.setHeight(height);
                    advDao.setStatus(Advertise.STATUS.WAIT_ACTIVE.getValue());
                    advDao.setAdsBy(Advertise.ADSBY.CUSTOMER.getValue());
                    advDao.setCreateBy(adminInfo.getAccID());
                    if (advDao.addNewFlash(advDao, file)) {
                        session.setAttribute("mess", "Thêm mới dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/customer/ads/manager.html'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Thêm mới dữ liệu lỗi!");
                    }
                } else if (kind == Advertise.KIND.IMAGE.getValue()) {
                    // IMAGE
                    String title = RequestTool.getString(req, "titleTop");
                    String desc = RequestTool.getString(req, "desc");
                    String destinationURL = RequestTool.getString(req, "destinationURL");
                    String startTime = RequestTool.getString(req, "startTime");
                    String endTime = RequestTool.getString(req, "endTime");
                    int width = RequestTool.getInt(req, "width");
                    int height = RequestTool.getInt(req, "height");
                    MultipartFile file = req.getFileParameter("iamge");
                    //--
                    advDao.setTitle_top(title);
                    advDao.setDesc(desc);
                    advDao.setDestinationUrl(destinationURL);
                    advDao.setWidth(width);
                    advDao.setHeight(height);
                    advDao.setStartTime(DateProc.String2Timestamp(startTime));
                    advDao.setEndTime(DateProc.String2Timestamp(endTime));
                    advDao.setKind(kind);
                    advDao.setStatus(Advertise.STATUS.WAIT_ACTIVE.getValue());
                    advDao.setAdsBy(Advertise.ADSBY.CUSTOMER.getValue());
                    advDao.setCreateBy(adminInfo.getAccID());
                    if (advDao.addNewImage(advDao, file)) {
                        session.setAttribute("mess", "Thêm mới dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/customer/ads/manager.html'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Thêm mới dữ liệu lỗi!");
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
                    String endTime = RequestTool.getString(req, "endtime");
                    MultipartFile file = req.getFileParameter("image");
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
                    advDao.setStatus(Advertise.STATUS.WAIT_ACTIVE.getValue());
                    advDao.setAdsBy(Advertise.ADSBY.CUSTOMER.getValue());
                    advDao.setCreateBy(adminInfo.getAccID());
                    if (advDao.addNewImageText(advDao, file)) {
                        session.setAttribute("mess", "Thêm mới dữ liệu thành công!");
                        out.print("<script>location.href = '" + request.getContextPath() + "/customer/ads/manager.html'</script>");
                        return;
                    } else {
                        session.setAttribute("mess", "Thêm mới dữ liệu lỗi!");
                    }
                } else {
                    Tool.Debug("Customer add Ads -- Chang thuoc cai the loai boi nào ??:" + kind);
                }
                //------------
            }
        %>
        <div id="main_container">
            <%@include file="/customer/incl/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/customer/incl/menu.jsp" %>
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
                        <div class="tabbed_area">
                            <ul class="tabs">
                                <li><a href="#" tabval="tab1" title="Thêm QC Ảnh Có Text" class="tab active">Thêm QC Ảnh Có Text</a></li>
                                <li><a href="#" tabval="tab4" title="Thêm QC Ảnh Và giá" class="tab">Thêm QC Ảnh Và Giá</a></li>
                                    <%--
                                    <li><a href="#" tabval="tab2" title="Thêm QC Chỉ có Ảnh" class="tab">Thêm QC Chỉ có Ảnh</a></li>
                                     <li><a href="#" tabval="tab3" title="Thêm QC Flash" class="tab">Thêm QC Flash</a></li> --%>
                            </ul>
                            <div id="tab1" class="content">
                                <%@include file="/customer/ads/tem/addImageAndText.jsp" %>
                            </div>
                            <%--
                            <div id="tab2" class="content">
                                <%@include file="/advertise/tem/addImage.jsp" %>
                            </div>
                            
                            <div id="tab3" class="content">
                                <%@include file="/advertise/tem/addFlash.jsp" %>
                            </div>
                            --%>
                            <div id="tab4" class="content">
                                <%@include file="/customer/ads/tem/addImageAndprices.jsp" %>
                            </div>
                        </div>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/customer/incl/footer.jsp" %>
        </div>
    </body>
</html>