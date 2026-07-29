<%@page import="gk.adv.linnk.vn.utils.Constants"%><%@page import="com.ckeditor.CKEditorConfig"%>
<%@page import="config.ListionContext"%><%@page import="gk.adv.linnk.vn.object.Advertise"%><%@page contentType="text/html; charset=utf-8" %><%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<form name="frmImagePrice" action="<%=request.getContextPath()+"/customer/ads/create.html" %>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="kind" value="<%=Advertise.KIND.IMAGE_PRICE.getValue()%>"/>
    <table style="margin-left: 5px" align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th scope="col" class="rounded"></th>
                <th colspan="2" scope="col" style="text-align: right" class="rounded-q4  redBoldUp">Thêm mới quảng cáo Ảnh & Text</th>
            </tr>
        </thead>
        <tbody>
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
                <td align="left">URL Đích: </td>
                <td colspan="2"><input size="70" type="text" name="destinationURL"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Nội dung QC:<br/>(Max: <span id="contenPrice_warning" class="redBold">90</span> ký tự) </td>
                <td colspan="2"><textarea
                        onchange="checkLength(90,'contenPrice');"
                        onkeydown="checkLength(90,'contenPrice');"
                        onkeyup="checkLength(90,'contenPrice');"
                        rows="3" id="contenPrice" cols="80" name="desc"></textarea></td>
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
                    <input onclick="window.location.href = '<%=request.getContextPath()+"/customer/ads/manager.html"%>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
       
    </table>
</form>