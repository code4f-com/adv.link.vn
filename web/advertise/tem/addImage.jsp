<%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %>
<form name="frmImage_text" action="<%=request.getContextPath() + "/sys-admin/advertise-manager/add-new"%>" method="post" enctype="multipart/form-data">
    <input id="kind-image" type="hidden" name="kind" value="<%=Advertise.KIND.IMAGE_SLIDE.getValue()%>"/>
    <script>
        $(function() {
            $("#image-choice-kind").change(function() {
                var element = $(this).find('option:selected');
                var val = element.val();
                if (val == 0) {
                    // nomal
                    $("#kind-image").val(<%=Advertise.KIND.IMAGE.getValue()%>);
                    $(".for-slide-ads").hide();
                    $("#zoom-for-slide-ads").val(0);
                    $(".for-nomal-ads").show();
                }
                else {
                    // slide
                    $("#kind-image").val(<%=Advertise.KIND.IMAGE_SLIDE.getValue()%>);
                    $(".for-slide-ads").show();
                    $("#zoom-for-slide-ads").val(1);
                    $(".for-nomal-ads").hide();
                }
            });
        });
    </script>
    <table align="center" id="rounded-corner">
        <thead>
            <tr><th scope="col" class="rounded-company"></th>
                <th  colspan="3" style="font-weight: bold;" scope="col" class="rounded-q4 redBoldUp">Thêm mới quảng cáo Ảnh</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td align="left">Chọn loại Ảnh QC: </td>
                <td colspan="2">
                    <select id="image-choice-kind">
                        <option selected="selected" value="slide">QC ảnh trong slide</option>
                        <option value="0">QC ảnh độc lập</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Tên Quảng Cáo: </td>
                <td colspan="2">
                    <input size="75" id="titleTop" name="titleTop" type="text" >
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">URL Đích: </td>
                <td colspan="2"><input size="75" type="text" name="destinationURL"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Mô Tả:(Dùng làm từ khóa QC) </td>
                <td colspan="2"><textarea rows="3" cols="55" name="desc"></textarea></td>
            </tr>
            <tr class="for-slide-ads">
                <td></td>
                <td align="left">Giá SP (=0 hiển hị SALE =-1 hiển thị HOT): </td>
                <td colspan="2"><input size="75" type="text" name="priceSell"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ảnh <span class="for-slide-ads redBold"> 1 Vuông(Cho slide Min 300x300): </span></td>
                <td colspan="2"><input id="id_imamge_1x1" size="75" type="file" name="image"/></td>
            </tr>
            <tr class="for-slide-ads">
                <td></td>
                <td align="left">Ảnh  <span class="redBold"> 2 (Tỷ lệ:1x2 Min 150x300): <span></td>
                <td colspan="2"><input id="id_imamge_1x2" size="75" type="file" name="imageslide"/></td>
            </tr>
        <input  id="zoom-for-slide-ads" size="75" type="hidden" name="zoom" value="1" />
        <script>
            var _URL = window.URL || window.webkitURL;
            $("#id_imamge_1x1").change(function(e) {
                var isSlide = $("#image-choice-kind option:selected").val();
                var file, img;
                if ((file = this.files[0])) {
                    img = new Image();
                    img.onload = function() {
                        var rate = this.width / this.height; 
                        if ((this.width < 300 || rate != 1) && (isSlide!= <%=Advertise.KIND.IMAGE.getValue()%> ) )
                            alert("Ảnh phải co kích thước nhỏ nhát là 300x300 và tỷ lệ 1x1");
                    };
                    img.src = _URL.createObjectURL(file);
                }
            });
            $("#id_imamge_1x2").change(function(e) {
                var file, img;
                if ((file = this.files[0])) {
                    img = new Image();
                    img.onload = function() {
                        var rate = this.width / this.height;
//                        alert(rate+"|"+this.width+"|"+this.height);
                        if (this.width < 150 || rate != 0.5)
                            alert("Ảnh phải co kích thước nhỏ nhát là 150x300 và tỷ lệ 1x2");
                    };
                    img.src = _URL.createObjectURL(file);
                }
            });
        </script>
        <tr class="for-nomal-ads" style="display: none">
            <td></td>
            <td align="left">Chiều Rộng (pixel): </td>
            <td colspan="2"> <input type="text" name="width"/>
                &nbsp;&nbsp;&nbsp;Chiều cao (pixel): <input type="text" name="height"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>Ngày bắt đầu:</td>
            <td>
                <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp())%>" name="startTime"/>
                &nbsp;&nbsp;&nbsp;
                Ngày kết thúc: <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.getNextDateN(DateProc.createTimestamp(), 30))%>" name="endTime"/> &nbsp;&nbsp;&nbsp;(30 Ngày)
            </td>
        </tr>  
        <tr>
            <td></td>
            <td align="left">Trạng thái: </td>
            <td colspan="2">
                <select name="status">
                    <option value="1">Kích hoạt</option>
                    <option value="0">Khóa</option>
                </select>
            </td>
        </tr>                                    
        <tr>
            <td colspan="4" align="center">
                <input type="submit" name="submit" value="Thêm mới"/>
                <input onclick="window.location.href = '<%=request.getContextPath() + "/sys-admin/advertise-manager/show.html"%>'" type="reset" name="reset" value="Hủy"/>
            </td>
        </tr>
        </tbody>
    </table>
</form>