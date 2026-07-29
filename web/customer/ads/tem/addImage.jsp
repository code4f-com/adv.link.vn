<%@page import="gk.adv.linnk.vn.utils.DateProc"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %>
<form name="frmImage_text" action="<%=request.getContextPath()+"/customer/ads/create.html" %>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="kind" value="<%=Advertise.KIND.IMAGE.getValue()%>"/>
    <table align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th colspan="3" style="font-weight: bold" scope="col" class="rounded-q4 redBoldUp">Thêm mới quảng cáo Ảnh</th>
            </tr>
        </thead>
        <tbody>
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
                <td align="left">Mô Tả: </td>
                <td colspan="2"><textarea rows="3" cols="55" name="desc"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Hình ảnh: </td>
                <td colspan="2"><input size="75" type="file" name="iamge"/></td>
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
                    <input onclick="window.location.href = '<%=request.getContextPath()+"/customer/ads/manager.html" %>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>