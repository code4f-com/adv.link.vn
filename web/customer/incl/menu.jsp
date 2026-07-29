<%@page import="gk.adv.linnk.vn.admin.Account"%>
<%@page contentType="text/html; charset=utf-8" %>
<% //Account adminInfo = (Account) session.getAttribute("adminInfo");%>
<div class="menu">
    <ul>
        <li><a target="_parent" class="current" href="<%=request.getContextPath()%>/customer"><b>Trang chủ</b></a></li>
            <% if (adminInfo.getUserType() == Account.TYPE.USER.val) {%>  
        <li><a href="<%=request.getContextPath()%>/customer/ads/manager.html"><b>Quản lý Quảng Cáo (Adv)</b></a></li>
        <li><a target="_parent" href="<%=request.getContextPath()%>/customer/statistic/product/click.html" title=""><b>Click Theo sản phẩm</b></a></li>            
            <%}
                if(adminInfo.getUserType() == Account.TYPE.MANAGER_ADS.val){
                %>
            <li><a target="_parent" href="<%=request.getContextPath()%>/customer/statistic/domain/click.html" title=""><b>Click Tên miền</b></a></li>
            <%
                }
            %>
        <%--
        <li><a><b>Thống kê</b></a>
            <ul>
                <li><a target="_parent" href="<%=request.getContextPath()%>/customer/statistic/click.html" title="">Click Theo sản phẩm</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/customer/statistic/city/click.html" title="">Click Tên miền</a></li>
            </ul>
        </li>
        --%>
    </ul>
</div>

