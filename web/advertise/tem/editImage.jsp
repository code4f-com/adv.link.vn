<%--@page import="gk.adv.linnk.vn.object.Advertise"--%>
<%@page contentType="text/html; charset=utf-8" %>
<%// Advertise advDao = new Advertise(); %>
<form name="frmImage" action="" method="post" enctype="multipart/form-data">
    <input id="kind-image" type="hidden" name="kind" value="<%=advDao.getKind()%>"/>
    <input type="hidden" name="id" value="<%=advDao.getAdvID()%>"/>
    <table align="center" id="rounded-corner">
        <thead>
            <tr>
                <th scope="col" class="rounded-company"></th>
                <th colspan="3" style="font-weight: bold" scope="col" class="rounded-q4 redBoldUp">Sửa quảng cáo chỉ có Ảnh</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td></td>
                <td align="left">Tên Quảng Cáo: </td>
                <td colspan="2">
                    <input value="<%=advDao.getTitle_top()%>" size="75" id="titleTop" name="titleTop" type="text" >
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">URL Đích: </td>
                <td colspan="2"><input value="<%=advDao.getDestinationUrl()%>" size="75" type="text" name="destinationURL"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Mô Tả: </td>
                <td colspan="2"><textarea rows="3" cols="55" name="desc"><%=advDao.getDesc()%></textarea></td>
            </tr>
            <%
                if (advDao.getKind() == Advertise.KIND.IMAGE_SLIDE.getValue()) {
            %>
            <tr>
                <td></td>
                <td align="left">Ảnh cũ: </td>
                <td colspan="2">
                    Anh 1x1
                    <img width="100" src="/adv-res/image/<%=advDao.getFilePath()%>"/>
                    Ảnh 1x2
                    <img width="100" src="/adv-res/image/<%=advDao.getimg100x200()%>"/>
                </td>
            </tr>
             <tr>
                <td></td>
                <td align="left">Giá SP (=0 hiển hị SALE =-1 hiển thị HOT): </td>
                <td colspan="2"><input size="75" type="text" value="<%=advDao.getPriceSell()%>" name="priceSell"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ảnh 1 Vuông 1x1 (Min 300x300): </td>
                <td colspan="2"><input size="75" type="file" name="image"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ảnh 2  (Tỷ lệ:1x2-cho QC Slide 150x300): </td>
                <td colspan="2"><input size="75" type="file" name="imageslide"/></td>
            </tr>
            <input size="75" type="hidden" name="zoom" value="1" />
            <%
            } else {
            %>
            <tr>
                <td></td>
                <td align="left">Ảnh cũ: </td>
                <td colspan="2">
                    <img src="/adv-res/image/<%=advDao.getFilePath()%>"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Ảnh</td>
                <td colspan="2"><input size="75" type="file" name="image"/></td>
            </tr>
            <tr>
                <td></td>
                <td align="left">Chiều Rộng: </td>
                <td colspan="2"> <input value="<%=advDao.getWidth()%>" type="text" name="width"/>
                    &nbsp;&nbsp;&nbsp;Chiều cao: <input value="<%=advDao.getHeight()%>" type="text" name="height"/>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td></td>
                <td>Ngày bắt đầu:</td>
                <td>
                    <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(advDao.getStartTime())%>" name="startTime"/>
                    &nbsp;&nbsp;&nbsp;
                    Ngày kết thúc: <input class="dateproc" type="text" value="<%=DateProc.Timestamp2DDMMYYYY(advDao.getEndTime())%>" name="endTime"/>
                </td>
            </tr>  
            <tr>
                <td></td>
                <td align="left">Trạng thái: </td>
                <td colspan="2">
                    <select name="status">
                        <option <%=advDao.getStatus() == 1 ? "selected='selected'" : ""%> value="1">Kích hoạt</option>
                        <option <%=advDao.getStatus() == 0 ? "selected='selected'" : ""%> value="0">Khóa</option>
                    </select>
                </td>
            </tr>                                    
            <tr>
                <td colspan="4" align="center">
                    <input type="submit" name="submit" value="Cập nhật"/>
                    <input onclick="window.location.href = '<%=request.getContextPath() + "/sys-admin/advertise-manager/show.html"%>'" type="reset" name="reset" value="Hủy"/>
                </td>
            </tr>
        </tbody>
    </table>
</form>