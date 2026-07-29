<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="com.ckeditor.CKEditorConfig"%>
<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %><%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<form onsubmit="return validForm();" name="frmImage" action="<%=request.getContextPath()+"/customer/ads/create.html" %>" method="post" enctype="multipart/form-data">
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
            </tr>
            <tr>
                <td></td>
                <td align="left">URL Đích: </td>
                <td colspan="2"><input size="70" type="text" name="destinationURL"/></td>
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
                    <input class="dateproc" size="25" type="text" name="startTime"/>
                    &nbsp;&nbsp;&nbsp;
                    Ngày kết thúc
                    <input class="dateproc" size="25" type="text" name="endtime"/>
                </td>
            </tr>  
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" name="submit" value="Thêm mới"/>
                    <input onclick="window.location.href = '<%=request.getContextPath() + "/customer/ads/manager.html"%>'" type="reset" name="reset" value="Hủy"/>
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