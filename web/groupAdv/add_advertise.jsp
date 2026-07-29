
<%@page import="gk.adv.linnk.vn.object.MapGroup"%><%@page import="gk.adv.linnk.vn.object.GroupAdv"%><%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="java.util.Iterator"%><%@page import="java.util.ArrayList"%><%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
    <head>
        <%@include file="/includes/header.jsp" %>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-ui.popup.js"></script>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/resource/css/popup.css" />
        <link rel="stylesheet" type="text/css" href="<%=response.encodeURL(request.getContextPath() + "/resource/css/tab.css")%>" />
    </head>
    <%
        if (!adminInfo.checkEdit(request)) {
            session.setAttribute("mess", "Bạn không có quyền truy cập trang này!");
            response.sendRedirect(request.getContextPath() + "/sys");
            return;
        }
        int groupID = Tool.string2Integer(request.getParameter("gid"), 0);
        int kind = Tool.string2Integer(request.getParameter("kind"), -2);

        GroupAdv group = new GroupAdv();
        group = group.getByID(groupID);
        if (group == null) {
            out.print("<script type='text/javascript'>jAlert('Bạn chưa chọn nhóm để thêm QC<br/>Hoặc QC bạn chọn không hợp lệ!','Thông báo',function(r){"
                    + "if(r){location.href='" + request.getContextPath() + "/sys-admin/group-adv-manager/show.html'}"
                    + "});"
                    + "</script>");
            return;
        }

        String keyword = Tool.validStringRequest(request.getParameter("keyword"));
        String startTime = Tool.validStringRequest(request.getParameter("startTime"));
        String endTime = Tool.validStringRequest(request.getParameter("endTime"));
        int currentPage = Tool.string2Integer(request.getParameter("page"), 1);
        Advertise gDao = new Advertise();
        int totalPage = 0;
        //-------------

        //-------------
        int totalRow = gDao.countAll(kind, keyword, startTime, endTime, Advertise.STATUS.ACTIVE.getValue(),Advertise.ADSBY.SYSTEM.getValue(),"");
        totalPage = (int) totalRow / Constants.ROW_PER_PAGE;
        if (totalRow % Constants.ROW_PER_PAGE != 0) {
            totalPage++;
        }

        ArrayList<Advertise> all = gDao.searForGroup(groupID, currentPage, kind, keyword, startTime, endTime);
        MapGroup gMap = (MapGroup) session.getAttribute(Constants.ADD_ADS_TO_GROUP_SESS_NAME);
        if (gMap != null) {
            // Da co Trong session So sanh voi GrooupID hien tai de xu ly
            if (gMap.getGroupID() != groupID) {
                //  Group Vua chon khong giong Group dang thuc hien add
                out.print("<script type='text/javascript'>"
                        + "jConfirm('Bạn vừa chọn nhóm quảng cáo: <b>" + Tool.stringToHTMLString(group.getName()) + "</b>"
                        + "<br/>Nhóm cũ đang thao tác: <b style=\"color:red\">" + Tool.stringToHTMLString(gMap.getGroupName()) + "</b>"
                        + "<br/>Bạn có chắc chắn bỏ nhóm cũ không?"
                        + "','Thông báo',function(r){"
                        + "if(r){ /*OK THI REMOVE THOI*/ location.href='" + request.getContextPath() + "/groupAdv/refreshSession.jsp?gid=" + groupID + "'}"
                        + "else{location.href='" + request.getContextPath() + "/sys-admin/group-adv-manager/show.html'}"
                        + "});"
                        + "</script>");
            }
        }
    %>
    <script>
        function yourChangeState(divid, type, value, id, gid) {
            var url = "<%=webPath%>/groupAdv/changestateiteminGroup.jsp?type=" + type + "&vl=" + value + "&id=" + id + "&gid=" + gid;
            url += "&sID=" + Math.floor(Math.random() * 10000);
            eventAction(url, divid);
        }
        function addAdstoGroup(obj) {
            jConfirm('Bạn chắc chắn muốn thêm QC này vào Group', 'Thông báo', function(r) {
                if (r) {
                    location.href = $(obj).attr('href');
                } else {
                    return  false;
                }
            });
            return false;
        }
        function changeKind(val) {
            location.href =<%=request.getContextPath()%>"/sys-admin/group-adv-manager/item-<%=group.getGroupID()%>.html?kind=" + val;
        }
        function open_in_new_tab(url) {
            var win = window.open(url, '_blank');
            win.focus();
        }
        function checkallMove() {
            var chk = document.getElementById("checkall");
            if (chk.checked) {
                var chkmove = document.getElementsByName("chkmove");
                for (var i = 0; i < chkmove.length; i++)
                    chkmove[i].checked = true;
            }
            else {
                var chkmove = document.getElementsByName("chkmove");
                for (var i = 0; i < chkmove.length; i++)
                    chkmove[i].checked = false;
            }
        }
        function CheckMove() {
            var chkmove = document.getElementsByName("chkmove");
            for (var i = 0; i < chkmove.length; i++) {
                if (chkmove[i].checked) {
                    return true;
                }
            }
            alert("Bạn cần chọn ít nhất một nội dung để chuyển chuyên mục");
            return false;
        }
        function submitFrm() {
            document.forms['frmChoice'].submit();
        }
        function getImgSize(imgSrc, id) {
            var newImg = new Image();
            newImg.src = imgSrc;
            var height = newImg.height;
            var width = newImg.width;
            document.getElementById("display_size_" + id).innerHTML = "Rộng: " + width + "<br/>Cao: " + height;
        }
    </script>
    <body>
    <style>.fixed {border: 1px solid #660066; position: fixed;top: 0;}</style>
    <div id="main_container">
        <%@include file="/includes/checkLogin.jsp" %>
        <div class="main_content">
            <%@include file="/includes/menu.jsp" %>
            <div class="center_content">
                <div class="right_content">
                    <!-- Tìm kiếm-->
                    <form action="" method="post">
                        <table id="rounded-corner" align="center">
                            <thead>
                                <tr>
                                    <th scope="col" class="rounded-company"></th>
                                    <th scope="col" class="rounded"></th>
                                    <th scope="col" class="rounded-q4 redBoldUp">Tìm Quảng cáo để thêm vào nhóm <%=gMap != null ? gMap.getGroupName() : group.getName()%></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td>Tìm kiếm</td>
                                    <td><input size="75" type="text" name="keyword"/></td>
                                </tr>
                                <tr align="center">
                                    <td colspan="3">
                                        <input type="submit" name="submit" value="Tìm kiếm"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  colspan="3" class="redBoldUp">
                                        Có (<%=gMap != null ? gMap.getAdsID().size() : "0"%>-Quảng cáo) trong Nhóm:<span style="color: darkorange"> <%=group.getName()%></span>
                                        <br/> <input type="button" onclick="open_in_new_tab('<%=request.getContextPath()%>/groupAdv/view/preview.html');" value="Xem Trước"/>
                                        &nbsp; <input type="button" onclick="location.href = '<%=request.getContextPath()%>/groupAdv/applyGroup.jsp'" value="Hoàn thành"/>
                                    </td>
                                </tr>
                                <tr id="applyChoiceItem">
                                    <td  colspan="3" class="redBoldUp">
                                        Đưa các sản phẩm đã chọn vào nhóm: &nbsp;<span style="color: darkorange"> <%=group.getName()%></span> <input type="button" onclick="submitFrm();" value="Đồng ý"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>

                    <!--End tim kiếm-->
                    <div align="center" style="height: 20px;margin-bottom: 2px; color: red;font-weight: bold">
                        <%if (session.getAttribute("mess") != null) {
                                out.print(session.getAttribute("mess"));
                                session.removeAttribute("mess");
                            }%>
                    </div>
                    <div align="center" style="height: 20px;margin-bottom: 5px; color: red;font-weight: bold">
                        Chọn loại sản phẩm thêm vào QC: 
                        <select  onchange="changeKind(this.value);">
                            <option <%= (kind == -2) ? "selected='selected'" : ""%> value="-2">--- Tất cả các QC ----</option>
                            <option <%= (kind == Advertise.KIND.IMAGE_TEXT.getValue()) ? "selected='selected'" : ""%> value="<%=Advertise.KIND.IMAGE_TEXT.getValue()%>">QC Ảnh và Text</option>
                            <option <%= (kind == Advertise.KIND.IMAGE_SLIDE.getValue()) ? "selected='selected'" : ""%> value="<%=Advertise.KIND.IMAGE_SLIDE.getValue()%>">QC Ảnh Slide (100x100 & 100x200)</option>
                            <option <%= (kind == Advertise.KIND.IMAGE.getValue()) ? "selected='selected'" : ""%> value="<%=Advertise.KIND.IMAGE.getValue()%>">QC Ảnh thường</option>
                            <option <%= (kind == Advertise.KIND.IMAGE_PRICE.getValue()) ? "selected='selected'" : ""%> value="<%=Advertise.KIND.IMAGE_PRICE.getValue()%>">QC Ảnh Và giá</option>
                        </select>
                    </div>
                    <script>
                        function checkRemoveAll() {
                            jConfirm('Bạn chắc chắm muốn bỏ tất cả sp trong nhóm này?', 'Xác nhận xóa', function(r) {
                                if (r) {
                                    location.href = '/groupAdv/removeAds.jsp?type=1&gid=<%=groupID%>';
//                                    jAlert('Xóa đê: ' + r, 'Confirmation Results');
                                } else {
                                    return false;
//                                    jAlert('Từ từ: ' + r, 'Confirmation Results');
                                }
                            });
                        }
                    </script>
                    <div align="center" style="height: 20px;margin-bottom: 5px; color: red;font-weight: bold">
                        <input onclick="checkRemoveAll()" type="button" value="Xóa tất"/>
                        <input onclick="location.href='/sys-admin/group-adv-manager/show.html'" type="button" value="Quay về"/>
                    </div>
                    <!--Content--><%@include file="/includes/page.jsp" %>
                    <form id="frmChoice" name="frmChoice" method="post" action="<%=request.getContextPath() + "/groupAdv/applyChoiceItem.jsp"%>">
                        <input type="hidden" value="<%=groupID%>" name="gid"/>
                        <div class="main3">
                            <table align="center" id="rounded-corner" summary="Msc Joint Stock Company" >
                                <thead>
                                    <tr>
                                        <th scope="col" class="rounded-company">
                                            <input type="checkbox" id="checkall" name="checkall" onclick="checkallMove()"/>
                                        </th>
                                        <th scope="col" class="rounded boder_right">ID</th>
                                        <th scope="col" class="rounded boder_right">Ảnh mẫu</th>
                                        <th scope="col" class="rounded boder_right">Tiêu đề</th>
                                        <th scope="col" class="rounded boder_right">Mô tả</th>
                                        <th scope="col" class="rounded boder_right">Giá</th>
                                        <th scope="col" class="rounded boder_right">Ngày hết hạn</th>                                        
                                        <th scope="col" class="rounded boder_right">Loại</th>
                                        <th scope="col" class="rounded boder_right">Add 2 Group</th>
                                        <th scope="col" class="rounded boder_right">Xem truoc</th>
                                        <th scope="col" class="rounded boder_right">Trạng thái SP</th>
                                        <th scope="col" class="rounded boder_right">Trạng thái Trong Group</th>
                                        <th scope="col" class="rounded-q4">Edit</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        int count = 1; //Bien dung de dem so dong
                                        for (Iterator<Advertise> iter = all.iterator(); iter.hasNext();) {
                                            Advertise oneADV = iter.next();
                                    %>
                                    <tr>
                                        <td class="boder_right"><input value="<%=oneADV.getAdvID()%>" type="checkbox" name="chkmove" /></td>
                                        <td align="center" class="boder_right"><%=oneADV.getAdvID()%></td>
                                        <td align="center" class="boder_right">
                                            <%
                                            if(oneADV.getKind()==Advertise.KIND.FLASH.getValue())
                                            {
                                            %>
                                            <embed width="90px" name="plugin" src="<%= BuildCache.DOMAIN + request.getContextPath()%>/adv-res/flash<%=oneADV.getFilePath()%>" type="application/x-shockwave-flash"></embed>
                                            <%
                                            }
                                            else{
                                            %>
                                            <span id="display_size_<%=oneADV.getAdvID()%>"></span>
                                            <img width="90" src="<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneADV.getFilePath()%>"/>
                                            <script>getImgSize('<%=BuildCache.DOMAIN + request.getContextPath() + "/adv-res/image" + oneADV.getFilePath()%>', '<%=oneADV.getAdvID()%>')</script>
                                            <%
                                            }
                                            %>
                                        </td>
                                        <td class="boder_right" align="center"><%=oneADV.getTitle_top()%></td>
                                        <td class="boder_right" style="width: 180px;" align="center"><%= oneADV.getDesc()%></td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            Giá mua: <%=oneADV.getPrice_root()%><br/>Giá bán: <%=oneADV.getPriceSell()%>
                                        </td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%=DateProc.Timestamp2DDMMYYYY(oneADV.getEndTime())%>
                                        </td>
                                        <td class="boder_right" align="center">
                                            <%=Advertise.KIND.getName(oneADV.getKind())%>
                                        </td>
                                        <td class="boder_right" align="center">                                            
                                            <%=GroupAdv.checkExitAds(oneADV.getAdvID() + "", group) ? "<a href='/groupAdv/removeAds.jsp?adsid=" + oneADV.getAdvID() + "&gid=" + groupID + "' class='ask'><img title='Remove From Group' src='/resource/images/remove.png'/></a>" : "<a onclick=\"return addAdstoGroup(this);\" target=\"_blank\" href=\"/groupAdv/add-ads/g" + groupID + "-a" + oneADV.getAdvID() + ".link\">Thêm vào Group</a>"%>
                                        </td>
                                        <td class="boder_right"><a target="_blank" href="<%="/advertise/single/preview_" + oneADV.getAdvID() + ".link"%>" >Xem truoc</a></td>
                                        <td style="border-right: solid 1px #00ccff" align="center">
                                            <%
                                                if (oneADV.getStatus()== 1) {
                                            %>
                                            <a href="" onclick="return false"><img width="24" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
                                                <%
                                                } else if (oneADV.getStatus() == 0) {
                                                %>
                                            <a href="" onclick="return false"><img width="24" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
                                                <%
                                                } else {
                                                %>
                                            <img width="24" src="<%= request.getContextPath()%>/resource/images/outofgroup.png"/>
                                            <%}
                                            %>
                                        </td>
                                        <td id="status<%=oneADV.getAdvID()%>" style="border-right: solid 1px #00ccff" align="center">
                                            <%
                                                if (oneADV.getGstatus() == 1) {
                                            %>
                                            <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>', '<%=groupID%>')" src="<%= request.getContextPath()%>/resource/images/play.png"/></a>
                                                <%
                                                } else if (oneADV.getGstatus() == 0 && GroupAdv.checkExitAds(oneADV.getAdvID() + "", group)) {
                                                %>
                                            <a href="" onclick="return false"><img width="24" onclick="yourChangeState('status<%=oneADV.getAdvID()%>', '<%= Constants.TYPE_CHANGE_STATUS%>', '<%=oneADV.getStatus()%>', '<%=oneADV.getAdvID()%>', '<%=groupID%>')" src="<%= request.getContextPath()%>/resource/images/pause.png"/></a>
                                                <%
                                                } else {
                                                %>
                                            <img width="24" src="<%= request.getContextPath()%>/resource/images/outofgroup.png"/>
                                            <%}
                                            %>
                                        </td>
                                        <td class="boder_right"><a href="<%=request.getContextPath() + "/sys-admin/advertise-manager/edit/" + oneADV.getAdvID() + "&kind=" + oneADV.getKind()%>" ><img src="<%= request.getContextPath()%>/resource/images/user_edit.png" alt="" title="" border="0" /></a></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </form>
                    <%@include file="/includes/page.jsp" %>
                </div><!-- end of right content-->
            </div>   <!--end of center content -->
            <div class="clear"></div>
        </div> <!--end of main content-->
        <%@include file="/includes/footer.jsp" %>
    </div>
    <script>
        var $sidebar = $("#applyChoiceItem"),
                $window = $(window),
                offset = $sidebar.offset(),
                topPadding = 15;
        $window.scroll(function() {
            if ($window.scrollTop() > offset.top) {
                $sidebar.addClass('fixed');
            } else {
                $sidebar.removeClass('fixed');
            }
        });
    </script>
</body>
</html>