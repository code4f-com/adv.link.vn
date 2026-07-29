<%@page contentType="text/html; charset=utf-8" %>
<form name="frmImage_text" action="" method="post" enctype="multipart/form-data">
    <input type="hidden" name="kind" value="<%=Advertise.KIND.IMAGE_TEXT.getValue()%>"/>
    <input type="hidden" name="id" value="<%=advDao.getAdvID()%>"/>
    <table align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th style="text-align: center" scope="col"  colspan="3" class="rounded-q4 redBoldUp">Sửa  quảng cáo Ảnh có Text</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td align="left">Tiêu đề TOP: (Max: <span id="titleTop_warning" class="redBold">40</span> ký tự) </td>
                <td colspan="2">
                    <input onkeypress="checkLength(40, 'titleTop');" value="<%=advDao.getTitle_top()%>" size="75" id="titleTop" name="titleTop" type="text" >
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">URL Đích: </td>
                <td colspan="2"><input value="<%=advDao.getDestinationUrl()%>" size="75" type="text" name="destinationURL"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Nội dung QC:<br/>(Max: <span id="contentEdt_warning" class="redBold">90</span> ký tự) </td>
                <td colspan="2"><textarea rows="3" id="contentEdt" cols="80" name="desc"><%=advDao.getDesc()%></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Hình ảnh Cũ: </td>
                <td colspan="2">
                    <img width="110px" src="/adv-res/image/<%=advDao.getFilePath()%>"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Hình ảnh: </td>
                <td colspan="2"><input size="75" type="file" name="image"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ngày bắt đầu: </td>
                <td colspan="2">
                    <input value="<%=DateProc.Timestamp2DDMMYYYY(advDao.getStartTime())%>" class="dateproc" size="30" type="text" name="startTime"/>
                    &nbsp;&nbsp;&nbsp;
                    Ngày kết thúc
                    <input value="<%=DateProc.Timestamp2DDMMYYYY(advDao.getEndTime())%>" class="dateproc" size="30" type="text" name="endTime"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <input type="submit" name="submit" value="Cập nhật"/>
                    <input onclick="window.location.href = '<%=request.getContextPath() + "/customer/ads/manager.html"%>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>
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