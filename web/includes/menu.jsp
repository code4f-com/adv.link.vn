<%@page contentType="text/html; charset=utf-8" %>
<div class="menu">
    <ul>
        <li><a target="_parent" class="current" href="<%=request.getContextPath()%>/sys"><b>Trang chủ</b></a></li>
        <li><a href="#"><b>Quản lý nội dung</b><!--[if IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/advertise-manager/show.html" title="">Quản lý Quảng Cáo (Adv)</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/group-adv-manager/show.html" title="">Quản lý Group Adv</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/site-manager/show.html" title="">Quản lý Trang hiển thị QC</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/admin/city/index.jsp" title="">Quản lý tỉnh thành</a></li>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>

        <li><a href="#"><b>Quản lý hệ thống</b><!--[if IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>                
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/customer-user/show.html" title="">Tài khoản Người dùng</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/account-manager/show.html" title="">Tài khoản Quản trị</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/group-manager/show.html" title="">Quản trị nhóm quyền</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/module-manager/show.html" title="">Quản trị Module</a></li>
                <li><a target="_blank" href="<%=request.getContextPath()%>/monitoring" title="">Monitoring</a></li>
                    <%--
                    <li><a class="sub1" href="" title="">Quản lý CP<!--[if IE 7]><!--></a><!--<![endif]-->
                        <!--[if lte IE 6]><table><tr><td><![endif]-->
                        <ul>
                            <li><a target="_parent" href="<%=request.getContextPath()%>/quan-tri-cp/danh-sach-cp" title="">Tài khoản CP</a></li>
                            <li><a target="_parent" href="<%=request.getContextPath()%>/quan-tri-cp/cp/them-moi-dich-vu" title="">Cấp mã dịch vụ CP</a></li>
                            <li><a target="_parent" href="<%=request.getContextPath()%>/quan-tri-cp/ma-dich-vu-cp-quan-ly/cpid0/danh-sach" title="">Mã CP Tự Quản Lý</a></li>
                            <li><a target="_parent" href="<%=request.getContextPath()%>/quan-tri-cp/dich-vu/danh-sach" title="">Danh sách dịch vụ</a></li>
                        </ul>
                        <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                    </li>
                    --%>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
        <li><a><b>Thống kê</b><!--[if IE 7]><!--></a><!--<![endif]-->
            <!--[if lte IE 6]><table><tr><td><![endif]-->
            <ul>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/statistic/click.html" title="">Thống kê Click</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/statistic/show/forProduct.html" title="">Tổng Click Theo SP</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/statistic/show/bydomain.html" title="">Theo tên miền</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/statistic/city/click.html" title="">Click Theo Tỉnh</a></li>
                <li><a target="_parent" href="<%=request.getContextPath()%>/sys-admin/statistic/show/byads.html" title="">Thống kê Hiển Thị</a></li>

                <%--  <li><a class="sub1" title="" href="<%=request.getContextPath()%>/item_manager/sys">Quản Lý ITEM<!--[if IE 7]><!--></a><!--<![endif]-->
                      <!--[if lte IE 6]><table><tr><td><![endif]-->
                      <ul>
                          <li><a target="_parent" href="<%=request.getContextPath()%>/item_manager/sys">aaaa</a></li>
                          <li><a target="_parent" href="<%=request.getContextPath()%>/thong-ke-sms/thong-ke-mt-doi-soat">Thống kê MT</a></li>
                          <li><a target="_parent" href="<%=request.getContextPath()%>/thong-ke-sms/thong-ke-cdr-doi-soat" title="">Thống kê CDR</a></li>
                      </ul>
                      <!--[if lte IE 6]></td></tr></table></a><![endif]-->
                  </li> --%>
            </ul>
            <!--[if lte IE 6]></td></tr></table></a><![endif]-->
        </li>
    </ul>
</div>

