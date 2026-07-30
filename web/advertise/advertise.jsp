
<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-ui.popup.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/popup.css" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
        <script>
            function yourChangeState(divid, type, value, id) {
                var url = "<%=webPath%>/advertise/changestate.jsp?type=" + type + "&vl=" + value + "&id=" + id;
                url += "&sID=" + Math.floor(Math.random() * 10000);
                eventAction(url, divid);
            }
            function changeKind(val) {
                location.href =<%=request.getContextPath()%>"/sys-admin/advertise-manager/show.html?kind=" + val;
            }
            $(document).ready(function () {
                $('.main3').find('div.div1').hide();
                $('input[rel*=manhungPopup]').showPopup({
                    top: 200, //khoảng cách popup cách so với phía trên
                    closeButton: ".close_popup", //khai báo nút close cho popup
                    scroll: false//, //cho phép scroll khi mở popup, mặc định là không cho phép
                            //onClose:function(){            	
                            //sự kiện cho phép gọi sau khi đóng popup, cho phép chúng ta gọi 1 số sự kiện khi đóng popup, bạn có thể để null ở đây
                            // }
                });
                //-- TAB
                // Sự kiện khi nhấn vào các tab của menu
                $("a.tab").click(function () {
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
        </script>
        <script>
//            window.onload = function() {
//                var img = document.getElementById('my_image');
//                // Original
//                var width, height;
//                // Display
//                var d_width = img.width;
//                var d_height = img.height;
//
//                var updateDetail = function() {
//                    document.getElementById('display_size')
//                            .innerHTML = 'Display Size: ' + d_width + ' x ' + d_height;
//                    document.getElementById('native_size')
//                            .innerHTML = 'Original Size: ' + width + ' x ' + height;
//                };
//                var width=image.offsetWidth;
//                var height = image.offsetHeight;
//                // Using naturalWidth/Height
//                if (img.naturalWidth) {
//                    width = img.naturalWidth;
//                    height = img.naturalHeight;
//                    updateDetail();
//                } else {
//                    // Using an Image Object
//                    img = new Image();
//                    img.onload = function() {
//                        width = this.width;
//                        height = this.height;
//                        updateDetail();
//                    };
////                    img.src = 'http://lorempixel.com/output/nature-q-c-640-480-3.jpg';
//                }
//            }

            function getImgSize(imgSrc, id) {
                var newImg = new Image();
                newImg.src = imgSrc;
                var height = newImg.height;
                var width = newImg.width;
                document.getElementById("display_size_" + id).innerHTML = "Width " + width + " - Height " + height;
            }
        </script>
    </head>
    <body>
        <%
            if (!adminInfo.checkView(request)) {
                session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
                response.sendRedirect(request.getContextPath() + "/sys");
                return;
            }
            String keyword = Tool.validStringRequest(request.getParameter("keyword"));
            String startTime = Tool.validStringRequest(request.getParameter("startTime"));
            String endTime = Tool.validStringRequest(request.getParameter("endTime"));
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            int kind = Tool.string2Integer(request.getParameter("kind"), Advertise.KIND.IMAGE_TEXT.getValue());
            int status = Tool.string2Integer(request.getParameter("status"), Advertise.STATUS.SHOW_ALL.getValue());
            String checkExp = Tool.validStringRequest(request.getParameter("checkExp"));
            Tool.Debug("checkExp:" + checkExp);
            Advertise advDao = new Advertise();
            int totalPage = 0;
            int adsBy = Advertise.ADSBY.SYSTEM.getValue();
            if (kind == -1199) {
                adsBy = Advertise.ADSBY.CUSTOMER.getValue();
                kind = -2;
            }

            int totalRow = advDao.countAll(kind, keyword, startTime, endTime, status, adsBy, checkExp);
            totalPage = (int) totalRow / Constants.ROW_PER_PAGE;
            if (totalRow % Constants.ROW_PER_PAGE != 0) {
                totalPage++;
            }
            ArrayList<Advertise> all = advDao.getAllAdv(currentPage, kind, keyword, startTime, endTime, status, adsBy, checkExp);
        %>
        <div id="main_container">
            <%@include file="/includes/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/includes/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <!-- Tìm kiếm-->
                        <form action="<%=request.getContextPath() + "/sys-admin/advertise-manager/show.html"%>" method="post">
                            <input type="hidden" value="<%=kind%>" name="kind"/>
                            <table id="rounded-corner" align="center">
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company"></th>
                                        <th scope="col" class="rounded"></th>
                                        <th scope="col" class="rounded-q4 redBoldUp">Tìm kiếm Quảng Cáo</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                        <td>Mã Quảng cáo:</td>
                                        <td><input size="75" type="text" name="code"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>Tiêu đề</td>
                                        <td><input size="75" type="text" name="keyword"/></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>Trạng thái:</td>
                                        <td>
                                            <select name="status">
                                                <%
                                                    for (Advertise.STATUS one : Advertise.STATUS.values()) {
                                                %>
                                                <option value="<%=one.getValue()%>"><%=one.getName()%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                            &nbsp;&nbsp;&nbsp;
                                            Loại Quảng Cáo&nbsp;&nbsp;
                                            <select onchange="changeKind(this.value);" name="kind">
                                                <%
                                                    for (Advertise.KIND one : Advertise.KIND.values()) {
                                                %>
                                                <option <%=(kind == one.getValue() ? "selected='selected'" : "")%> value="<%=one.getValue()%>"><%=one.getName()%></option>
                                                <%
                                                    }
                                                %>
                                                <option <%=(adsBy == Advertise.ADSBY.CUSTOMER.getValue() ? "selected='selected'" : "")%> value="-1199">QC tạo bởi người dùng</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td></td>                                        
                                        <td colspan="2">Tìm Quảng Cáo Hết Hạn&nbsp;&nbsp;
                                            <input type="checkbox" name="checkExp" />
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td colspan="3">
                                            <input type="submit" name="submit" value="Tìm kiếm"/>
                                            <%if (adminInfo.checkAdd(request)) {%>
                                            <a href="<%=request.getContextPath() + "/sys-admin/advertise-manager/add-new"%>">
                                                <img style="vertical-align: middle" border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/>                                                
                                            </a><%}%>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>      
                        <!--End tim kiếm-->
                        <div align="center" style="height: 40px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                                int countExprice = advDao.countAllExpire();
                                if (countExprice > 0) {
                                    out.println("<br/>Có [" + countExprice + "] QUẢNG CÁO BỊ HẾT HẠN<br/>");
                                }
                            %>
                        </div>
                        <!--Content-->
                        <%@include file="/includes/page.jsp" %>
                        <div class="main3">
                            <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company">Mã</th>
                                        <th scope="col" class="rounded">Ảnh mẫu</th>
                                        <th scope="col" class="rounded">Tiêu đề</th>
                                        <th scope="col" class="rounded">Mô tả</th>
                                        <th scope="col" class="rounded">Giá</th>
                                        <th scope="col" class="rounded">Loại</th>
                                        <th scope="col" class="rounded">Trạng thái</th>
                                        <th scope="col" class="rounded">Ngày kết thúc</th>                                        
                                        <th scope="col" class="rounded">Mã nhúng</th>
                                        <th scope="col" class="rounded">Xem truoc</th>
                                        <%if (adminInfo.checkEdit(request)) {%><th scope="col" class="rounded">Edit</th><%}%>
                                        <%if (adminInfo.checkDel(request)) {%><th scope="col" class="rounded-q4">Delete</th><%}%>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 1; //Bien dung de dem so dong
                                        for (Iterator<Advertise> iter = all.iterator(); iter.hasNext();) {
                                            Advertise oneADV = iter.next();
                                    %>
                                    <tr>
                                        <td align="center" style="border-right: solid 1px #00ccff"><%=oneADV.getAdvID()%></td>
                                        <td align="center" style="border-right: solid 1px #00ccff">
                                            <span id="display_size_<%=oneADV.getAdvID()%>"></span>
                                            <%
                                                if (oneADV.getKind() == Advertise.KIND.FLASH.getValue()) {
                                            %>
                                <embed width="90px" name="plugin" src="<%= BuildCache.DOMAIN + request.getContextPath()%>/adv-res/flash<%=oneADV.getFilePath()%>" type="application/x-shockwave-flash"></embed>
                                    <%
                                    } else {
                                    %>
                                <img id="my_image" width="90" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneADV.getFilePath()%>"/>
                                <script>getImgSize('<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneADV.getFilePath()%>', '<%=oneADV.getAdvID()%>')</script>
                                <%
                                    }
                                %>

                                </td>
                                <td style="border-right: solid 1px #00ccff" align="center"><%=oneADV.getTitle_top()%></td>
                                <td style="border-right: solid 1px #00ccff" align="center"><%= oneADV.getDesc()%></td>
                                <td style="border-right: solid 1px #00ccff" align="center">
                                    Giá mua: <%=oneADV.getPrice_root()%><br/>
                                    Giá bán: <%=oneADV.getPriceSell()%>
                                </td>
                                <td style="border-right: solid 1px #00ccff" align="center">
                                    <%=Advertise.KIND.getName(oneADV.getKind())%>
                                </td>
                                <td id="status<%=oneADV.getAdvID()%>" style="border-right: solid 1px #00ccff" align="center">
                                    <%
                                        if (oneADV.getStatus() == 1) {
                                            if (adminInfo.checkEdit(request)) {
                                    %>
                                    <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>')" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
                                        <%} else {
                                                out.print("<img width=\"20\" src=\"" + request.getContextPath() + "/resource/images/active.png\"/>");
                                            }
                                        } else if (oneADV.getStatus() == 0) {
                                            if (adminInfo.checkEdit(request)) {
                                        %>
                                    <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
                                        <%} else {
                                                out.print("<img width=\"20\" src=\"" + request.getContextPath() + "/resource/images/lock.png\"/>");
                                            }
                                        } else {
                                        %>
                                    <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>')" src="<%= request.getContextPath()%>/resource/images/Recyclebin.png"/></a>
                                        <%}
                                        %>
                                </td>
                                <td style="border-right: solid 1px #00ccff" align="center">
                                    <%=DateProc.Timestamp2DDMMYYYY(oneADV.getEndTime())%>
                                </td>
                                <td align="center" style="border-right: solid 1px #00ccff">
                                    <input type="button" class="button-popup" id="open_popup_<%=oneADV.getAdvID()%>" name="open_popup" rel="manhungPopup" href="#popup_content_<%=oneADV.getAdvID()%>" value="Mã Nhúng"/>
                                    <div id="popup_content_<%=oneADV.getAdvID()%>" class="popup">
                                        <ul class="tabs">
                                            <li><a href="#" tabval="tab2_<%=oneADV.getAdvID()%>" title="Code URL" class="tab active">JAVASCRIP</a></li>
                                            <li><a href="#" tabval="tab1_<%=oneADV.getAdvID()%>" title="Code HTML" class="tab">IFRAME</a></li>
                                            <!--<li><a href="#" tabval="tab3_<%=oneADV.getAdvID()%>" title="Code URL" class="tab">JAVASCRIP</a></li>-->
                                        </ul>
                                        <div style="border: 0px;display: none" id="tab1_<%=oneADV.getAdvID()%>" class="content tab1">
                                            <div style="color: red;margin-left: 10px"> Đặt mã cho plugin của bạn bất cứ nơi nào bạn muốn plugin để xuất hiện trên trang của bạn .</div>
                                            <textarea cols="65" rows="10"><iframe src="<%=BuildCache.DOMAIN + request.getContextPath()%>/advertise/view-frame/iframe_<%=oneADV.getAdvID()%>.link" scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:100%; height:100%" allowTransparency="true"></iframe></textarea>
                                            <div class="info_popup">
                                            </div>
                                        </div>
                                        <div  style="border: 0px" id="tab2_<%=oneADV.getAdvID()%>" class="content tab2">
                                            <div style="color: red;margin-left: 10px"> Ngày tao: <%=DateProc.Timestamp2DDMMYYYY(oneADV.getCreateDate())%></div>
                                            <textarea cols="65" rows="10"><script type="text/javascript" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/advertise/js_core/single_" + oneADV.getAdvID() + ".link"%>" ></script></textarea>
                                                    <div class="info_popup">
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff"><a target="_blank" href="<%=request.getContextPath() + "/advertise/single/preview_" + oneADV.getAdvID() + ".link"%>" >Xem truoc</a></td>
                                <%if (adminInfo.checkEdit(request)) {%><td style="border-right: solid 1px #00ccff"><a href="<%=request.getContextPath() + "/sys-admin/advertise-manager/edit/" + oneADV.getAdvID() + "&kind=" + oneADV.getKind()%>" ><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td><%}%>
                                <%if (adminInfo.checkDel(request)) {%><td><a href="<%=(oneADV.getStatus() == Advertise.STATUS.DELETE.getValue()) ? "/sys-admin/advertise-manager/del/ever-" + oneADV.getAdvID() : "/sys-admin/advertise-manager/del/" + oneADV.getAdvID()%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td><%}%>
                                    </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table></div>
                            <%@include file="/includes/page.jsp" %>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/includes/footer.jsp" %>
        </div>
    </body>
</html>