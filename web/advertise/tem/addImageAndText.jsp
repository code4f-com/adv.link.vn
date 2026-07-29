<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page import="gk.adv.linnk.vn.utils.DateProc"%>
<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="com.ckeditor.CKEditorConfig"%>
<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %><%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<form onsubmit="return validForm();" name="frmImage" action="<%=request.getContextPath()+"/sys-admin/advertise-manager/add-new" %>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="kind" value="<%=Advertise.KIND.IMAGE_TEXT.getValue()%>"/>
    <table style="margin-left: 5px" align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th scope="col" class="rounded"></th>
                <th colspan="2" scope="col" style="text-align: right" class="rounded-q4  redBoldUp">Thêm mới quảng cáo Ảnh & Text</th>
                <!--<th scope="col" class="rounded-q4"></th>-->
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td align="left">Tiêu đề TOP: (Max: <span id="titleTop_warning" class="redBold">40</span> ký tự) </td>
                <td colspan="2">
                    <input size="70" id="titleTop" onkeypress ="checkLength(40, 'titleTop');" onkeydown ="checkLength(40, 'titleTop');" onkeyup="checkLength(40, 'titleTop');" name="titleTop" type="text" >
                </td>
                <!--<td style="background-color: white;border-left: 1px solid #cd0a0a;border-right:1px solid #cd0a0a; " rowspan="10">-->
                <!--                    <div class="box300 myclear">
                                        <a target="_blank" href="http://<%=BuildCache.DOMAIN%>" class="hads-300">
                                            <img src="/resource/images/hads.gif" alt="">
                                        </a>
                                        <div style="height: 199px; overflow: hidden;" class="box300-item">
                                            <p class="box188-cat-title s1ReviewCat1">Tiêu đề 1</p>
                                            <div class="myclear">
                                                <p class="img-ads-300-158">
                                                    <img width="150" height="150" src="/resource/images/adsdefault.gif" id="imgS1Review1" alt="" class="block">
                                                </p>
                                                <div class="rcontent-300-158">
                                                    <div class="rcontent-300-top-158">
                                                        <p class="ads-title-300 s1ReviewTitle1">Tiêu đề 2</p>
                                                        <p class="ads-desc s1ReviewDesc1">Nội dung quảng cáo</p>
                                                        <div class="myclear">
                                                            <p class="lprice s1RvPriceLabel">
                                                                Giá:
                                                            </p>
                                                            <p class="rprice">
                                                                <span class="block price-discount s1ReviewPrice1">80,000đ</span>
                                                                <span class="block">[<span class="price-real strike-text s1ReviewRprice1">120,000đ</span>]</span>
                                                            </p>
                                                             important. fix for ie 6. Don't remove 
                                                            [if IE 6]><br class="clearboth" /><![endif]
                                                        </div>
                                                    </div>
                                                    <p class="myclear">
                                                        <a target="_blank" class="buy-btn s1ReviewBuy1" href="javascript:void(0);"></a>
                
                                                        <span class="r86">
                                                            <span class="l86 s1Review861"></span>
                                                        </span>
                                                    </p>
                
                                                </div>
                
                                            </div>
                                        </div> end item 1
                                    </div>-->
                <!--</td>-->
            </tr>
            <tr>
                <td></td>
                <td align="left">URL Đích: </td>
                <td colspan="2"><input size="70" type="text" name="destinationURL"/></td>
            </tr>
            <tr>
                <td></td>
                <td>Khu Vực Hiện QC:</td>
                <td colspan="2">
                    <p class="myclear">
                        <input type="checkbox" class="ver-middle marright5" id="chkLocationAll" name="chkLocationAll" onclick="chkLocationAllClick();"> <span class="ver-middle" style="color: red"><b>Toàn quốc</b></span>
                        <input type="checkbox" class="marleft20 ver-middle marright5 chkLocation" id="chkBac" name="chkBac" onclick="chLocationItemClick();"> <span id="bacName" class="ver-middle">Miền Bắc</span>
                        <input type="checkbox" class="marleft20 ver-middle marright5 chkLocation" id="chkTrung" onclick="chLocationItemClick();" name="chkTrung"> <span id="trungName" class="ver-middle">Miền Trung</span>
                        <input type="checkbox" class="marleft20 ver-middle marright5 chkLocation" id="chkNam" onclick="chLocationItemClick();" name="chkNam"> <span id="namName" class="ver-middle">Miền Nam</span>
                    </p>
<!--                    <p class="padtop10">
                        <span class="ver-middle">Theo Tỉnh - Thành phố</span>
                    </p>
                    <input name="choiceLocation" id="region" type="text" style="width: 450px;" value="Nhập tên tỉnh thành phố để lựa chọn." autocomplete="false" class="superblyTagInput">
                    <img src="/resource/images/ajaxLoading.gif" class="superblyTagfieldLoading"  style="display: none;">
                    <div class="superblyTagfieldClearer"></div>-->
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Giá bán: </td>
                <td colspan="2">
                    <input size="27" type="text" name="priceSell"/>
                    &nbsp;&nbsp;&nbsp;
                    Giá Gốc
                    <input size="27" type="text" name="priceRoot"/>
                </td>
            </tr>  
            <tr>
                <td></td>
                <td align="left">Nội dung QC:<br/>(Max: <span id="contentEdt_warning" class="redBold">90</span> ký tự) </td>
                <td colspan="2"><textarea rows="3" id="contentEdt" cols="80" name="desc"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Hình ảnh: </td>
                <td colspan="2">
                    <input size="75" type="file" name="image"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ngày bắt đầu: </td>
                <td colspan="2">
                    <input class="dateproc" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp())%>" size="25" type="text" name="startTime"/>
                    &nbsp;&nbsp;&nbsp;
                    Ngày kết thúc
                    <input class="dateproc" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.getNextDateN(DateProc.createTimestamp(), 30))%>" size="25" type="text" name="endtime"/>&nbsp;&nbsp;&nbsp;(30 Ngày)
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
                <td colspan="3" align="center">
                    <input type="submit" name="submit" value="Thêm mới"/>
                    <input onclick="window.location.href = '<%=request.getContextPath() + "/sys-admin/advertise-manager/show.html"%>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
        <%
            CKEditorConfig settings = new CKEditorConfig();
            settings.addConfigValue("toolbar", "["
                    + "            { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','-','RemoveFormat' ] },"
                    + "            { name: 'document', items : [ 'Source','-','NewPage','DocProps','Preview','-','Templates' ] },            { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },"
                    + "            '/',"
                    + "            { name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },"
                    + "            { name: 'colors', items : [ 'TextColor','BGColor' ] }"
                    + "    ]");
            settings.addConfigValue("width", "520");
            settings.addConfigValue("height", "150");
            String basePath = request.getContextPath() + "/ckeditor/";
        %>
        <ckeditor:replace config="<%=settings%>" replace="contentEdt" basePath="<%=basePath%>" />
    </table>
    <script>
    var editor = CKEDITOR.instances['contentEdt']
    editor.on('change', function(e) {
        var data = editor.getData();
        data = data.replace(/<[^>]*>/gi, '');
        var currentLength = data.length;
        var displayLength = 90 - currentLength;
        if (displayLength > 0) {
            $("#contentEdt_warning").html(displayLength);
        } else {
            $("#contentEdt_warning").html(0);
            jAlert("Bạn cần nhập nội dung quảng cáo hợp lệ</br>Nội dung là bắt buộc và phải <= 90 ký tự", "Thông báo", null);
            editor.setData(data.substring(0, 90));
            return false;
        }
        return false;
    });
    </script>
</form>