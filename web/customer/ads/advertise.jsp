
<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/customer/incl/header.jsp" %>
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
                location.href =<%=request.getContextPath()%>"/customer/ads/manager.html?kind=" + val;
            }
            $(document).ready(function() {
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
        </script>
    </head>
    <body>
        <%
            String keyword = Tool.validStringRequest(request.getParameter("keyword"));
            String startTime = Tool.validStringRequest(request.getParameter("startTime"));
            String endTime = Tool.validStringRequest(request.getParameter("endTime"));
            int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
            int accId = adminInfo.getAccID();
            int kind = Tool.string2Integer(request.getParameter("kind"), Advertise.KIND.IMAGE_TEXT.getValue());
            int status = Tool.string2Integer(request.getParameter("status"), Advertise.STATUS.SHOW_ALL.getValue());
            Advertise gDao = new Advertise();
            int totalPage = 0;
            int totalRow = gDao.countAllAdsByCustomer(accId, kind, keyword, startTime, endTime, status);
            totalPage = (int) totalRow / Constants.ROW_PER_PAGE;
            if (totalRow % Constants.ROW_PER_PAGE != 0) {
                totalPage++;
            }
            ArrayList<Advertise> all = gDao.getAllAdsByCustomer(currentPage, accId, kind, keyword, startTime, endTime, status);
        %>
        <div id="main_container">
            <%@include file="/customer/incl/checkLogin.jsp" %>
            <div class="main_content">
                <%@include file="/customer/incl/menu.jsp" %>
                <div class="center_content">
                    <div class="right_content">
                        <!-- Tìm kiếm-->
                        <form action="<%=request.getContextPath() + "/customer/ads/manager.html"%>" method="post">
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
                                                <option value="<%=Advertise.STATUS.SHOW_ALL.getValue()%>">Tất cả</option>
                                                <option value="<%=Advertise.STATUS.ACTIVE.getValue()%>">Đã duyệt</option>
                                                <option value="<%=Advertise.STATUS.WAIT_ACTIVE.getValue()%>">Chờ duyệt</option>
                                            </select>
                                            &nbsp;&nbsp;&nbsp;
                                            Loại Quảng Cáo&nbsp;&nbsp;
                                            <select onchange="changeKind(this.value);" name="kind">
                                                <option <%=(kind == Advertise.KIND.IMAGE_TEXT.getValue() ? "selected='selected'" : "")%> value="<%=Advertise.KIND.IMAGE_TEXT.getValue()%>">Quảng cáo ảnh và text</option>
                                                <option <%=(kind == Advertise.KIND.IMAGE_PRICE.getValue() ? "selected='selected'" : "")%> value="<%=Advertise.KIND.IMAGE_PRICE.getValue()%>">Quảng cáo ảnh và Giá</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr align="center">
                                        <td colspan="3">
                                            <input type="submit" name="submit" value="Tìm kiếm"/>
                                            <a href="<%=request.getContextPath() + "/customer/ads/create.html"%>">
                                                <img style="vertical-align: middle" border="0"  src="<%= request.getContextPath()%>/resource/images/add_1.gif"/>                                                
                                            </a>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>      
                        <!--End tim kiếm-->
                        <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                            <%
                                if (session.getAttribute("mess") != null) {
                                    out.print(session.getAttribute("mess"));
                                    session.removeAttribute("mess");
                                }
                            %>
                        </div>
                        <!--Content-->
                        <%@include file="/customer/incl/page.jsp" %>
                        <div class="main3">
                            <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company">Mã</th>
                                        <th scope="col" class="rounded">Ảnh mẫu</th>
                                        <th scope="col" class="rounded">Tiêu đề</th>
                                        <th scope="col" class="rounded">Mô tả</th>
                                        <th scope="col" class="rounded">Loại</th>
                                        <th scope="col" class="rounded">Trạng thái</th>
                                        <th scope="col" class="rounded">Ngày kết thúc</th>                                        
                                        <th scope="col" class="rounded">Xem truoc</th>
                                        <th scope="col" class="rounded">Edit</th>
                                        <th scope="col" class="rounded-q4">Delete</th>
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
                                            <img width="90px" height="90px" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneADV.getFilePath()%>"/>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%=oneADV.getTitle_top()%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%= oneADV.getDesc()%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%=Advertise.KIND.getName(oneADV.getKind())%>
                                        </td>
                                        <td id="status<%=oneADV.getAdvID()%>" style="border-right: solid 1px #00ccff" align="center">
                                            <%
                                                if (oneADV.getStatus() == 1) {
                                                    out.print("<img width=\"20\" src=\"" + request.getContextPath() + "/resource/images/active.png\"/>");
                                                } else if (oneADV.getStatus() == 0) {
                                            %>
                                            <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
                                                <%
                                                } else if (oneADV.getStatus() == Advertise.STATUS.WAIT_ACTIVE.getValue()) {
                                                    out.print("<img width=\"45\" src=\"" + request.getContextPath() + "/resource/images/wait.gif\"/>");
                                                } else {
                                                %>
                                            <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>')" src="<%= request.getContextPath()%>/resource/images/Recyclebin.png"/></a>
                                                <%}
                                                %>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%=DateProc.Timestamp2DDMMYYYY(oneADV.getEndTime())%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff"><a target="_blank" href="<%="/advertise/single/preview_" + oneADV.getAdvID() + ".link"%>" >Xem truoc</a></td>
                                        <td style="border-right: solid 1px #00ccff"><a href="<%=request.getContextPath() + "/customer/ads/edit/" + oneADV.getAdvID()%>" ><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                        <td><a href="<%=(oneADV.getStatus() == Advertise.STATUS.DELETE.getValue()) ? "/customer/ads/del/ever-" + oneADV.getAdvID() : "/customer/ads/del/" + oneADV.getAdvID()%>" class="ask"><img src="<%= request.getContextPath()%>/resource/images/trash.png" alt="" title="" border="0" /></a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table></div>
                            <%@include file="/customer/incl/page.jsp" %>
                    </div><!-- end of right content-->
                </div>   <!--end of center content -->
                <div class="clear"></div>
            </div> <!--end of main content-->
            <%@include file="/customer/incl/footer.jsp" %>
        </div>
    </body>
</html>