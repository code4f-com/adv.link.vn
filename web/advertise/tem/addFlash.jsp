<%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %>
<form name="frmFlash" action="<%=request.getContextPath()+"/sys-admin/advertise-manager/add-new" %>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="kind" value="<%=Advertise.KIND.FLASH.getValue()%>" />
    <table style="margin-left: 155px"  align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th scope="col" class="rounded"></th>
                <th colspan="2" style="font-weight: bold" scope="col" class="rounded-q4 redBoldUp">Thêm mới quảng cáo FLASH</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td class="redBold" colspan="4" align="center">Quảng cáo Flash phụ thuộc kích thước Flash bạn nhập lên: </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Tên Quảng cáo: </td>
                <td colspan="2"><input size="75" type="text" name="title"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Mô tả: </td>
                <td colspan="2"><textarea rows="3" cols="55" name="desc"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Chiều Rộng (pixel): </td>
                <td colspan="2"> <input type="text" name="width"/>
                    &nbsp;&nbsp;&nbsp;Chiều cao (pixel): <input type="text" name="height"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">File Flash: </td>
                <td colspan="2"><input size="75" type="file" name="flash"/></td>
            </tr>
            <tr>
                <td></td>
                <td>Ngày bắt đầu:</td>
                <td>
                    <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.createTimestamp())%>" name="startTime"/>
                    &nbsp;&nbsp;&nbsp;
                    Ngày kết thúc: <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(DateProc.getNextDateN(DateProc.createTimestamp(), 15))%>" name="endTime"/> &nbsp;&nbsp;&nbsp;(15 Ngày)
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
                    <input onclick="window.location.href = '<%=request.getContextPath()+"/sys-admin/advertise-manager/show.html" %>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>