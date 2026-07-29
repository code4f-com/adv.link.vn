<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MANY_HORIZONTAL_RANDOM</title>
        <style>
            #ssvzone_57 {
                border: 1px solid #dedede;
                height: 184px;
                overflow: hidden;
                text-align: left;
                width: 980px;
            }
            #ssvzone_57 * {
                font-family: tahoma,arial !important;
                font-size: 11px;
                margin: 0;
                padding: 0;
                text-decoration: none;
                text-transform: none !important;
            }
            #ssvzone_57 .ssvzTop, #ssvzone_57 .ssvzBottom {
                clear: both;
                height: 3px;
                overflow: hidden;
            }
            #ssvzone_57 .ssvzTop {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") no-repeat scroll 0 0 transparent;
            }
            #ssvzone_57 .ssvzTop .ssvzRight {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") no-repeat scroll 100% -3px transparent;
            }
            #ssvzone_57 .ssvzTop .ssvzMid {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") repeat-x scroll 0 -12px transparent;
                height: 3px;
                margin: 0 3px;
            }
            #ssvzone_57 .ssvzBottom {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") no-repeat scroll 0 -9px transparent;
            }
            #ssvzone_57 .ssvzBottom .ssvzRight {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") no-repeat scroll 100% -6px transparent;
            }
            #ssvzone_57 .ssvzBottom .ssvzMid {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") repeat-x scroll 0 -15px transparent;
                height: 3px;
                margin: 0 3px;
            }
            #ssvzone_57 .ssvzContent {
                background: url("http://ads.link.vn/resource/images/ssvtop.png") repeat-y scroll 0 0 transparent;
                clear: both;
                overflow: hidden;
            }
            #ssvzone_57 .ssvzContent .ssvzRight {
                background: url("http://ads.link.vn/resource/images/ssvtop.png") repeat-y scroll 100% 0 transparent;
            }
            #ssvzone_57 .ssvzContent .ssvzMid {
                background: none repeat scroll 0 0 #ffffff;
                margin: 0 3px;
            }
            #ssvzone_57 .ssvzHeader {
                background: url("http://ads.link.vn/resource/images/ssvborder.png") repeat-x scroll 5px -18px transparent;
                height: 21px;
                line-height: 21px;
            }
            #ssvzone_57 .ssvHeaderBr {
                background: url("http://ads.link.vn/resource/images/ssvtop.png") repeat-y scroll 100% 0 transparent;
            }
            #ssvzone_57 .ssvzLogo {
                background: url("http://ads.link.vn/resource/images/ssvtop.png") no-repeat scroll -3px 0 transparent;
                border: 0 none;
                height: 21px;
                line-height: 21px;
                margin: 0;
                padding: 0;
                width: 149px;
            }
            #ssvzone_57 .ssvzBuy {
                background: url("http://ads.link.vn/resource/images/ssvtop.png") no-repeat scroll -3px -21px transparent;
                border-bottom-style: none;
                border-bottom-width: 0;
                border-image: none;
                border-top-style: none;
                border-top-width: 0;
                float: right;
                height: 21px;
                width: 149px;
            }
            #ssvzone_57 .ssvzclear {
                clear: both;
            }
            #ssvzone_57 #ssvzone_57_items {
                height: 157px;
                margin: 0;
                overflow: hidden;
                padding: 0;
            }
            #ssvzone_57_items .adv_items {
                float: left;
                height: 150px;
                margin: 5px 10px;
                width: 222px;
            }
            #ssvzone_57_items .adv_items .ssvzimage {
                clear: both;
            }
            #ssvzone_57_items .adv_items .ssvzimage img {
                border-bottom-style: none;
                border-bottom-width: 0;
                border-image: none;
                border-top-style: none;
                border-top-width: 0;
                float: left;
                height: 90px;
                margin-right: 5px;
                width: 90px;
            }
            #ssvzone_57_items .adv_items .ssvzTitle {
                clear: both;
                height: 32px;
                line-height: 15px;
                text-align: left;
            }
            #ssvzone_57_items .adv_items .ssvzTitle a:link, #ssvzone_57_items .adv_items .ssvzTitle a:visited {
                color: #333333;
                font-family: tahoma,arial,verdana;
                font-size: 11px;
                font-weight: 700 !important;
                padding-top: 0;
                text-align: center;
                text-decoration: none;
            }
            #ssvzone_57_items .itemmc {
                clear: both;
                float: left;
                height: 17px;
                line-height: 12px;
                margin-bottom: 3px;
                overflow: hidden;
                padding-top: 2px;
                text-align: left;
            }
            #ssvzone_57_items .itemmc, #ssvzone_57_items .itemmc a:visited, #ssvzone_57_items .itemmc a:active, #ssvzone_57_items .itemmc a:link {
                color: #666666;
                margin-bottom: 2px;
                text-decoration: none;
            }
            #ssvzone_57_items .adv_items .price {
                height: 87px;
                line-height: 16px;
                padding-left: 5px;
                text-align: left;
            }
            #ssvzone_57_items .adv_items .price a:link, #ssvzone_57_items .adv_items .price a:visited {
                color: #333333;
                font-family: Tahoma;
                font-size: 11px;
                font-weight: 400;
                text-decoration: none;
            }
            #ssvzone_57_items .ssvzBorder {
                background: none repeat scroll 0 0 #e5e5e5;
                float: left;
                height: 157px;
                width: 1px;
            }

        </style>
    </head>
    <body>
            <div id="ssvzone_57"><div class="ssvzContent"><div class="ssvzRight"><div class="ssvzMid"><div id="ssvzone_57_items"><div class="adv_items" id="adv_item"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?ads_id=196&amp;g_main=57&amp;key=10628cdf5775a14909742be8cb106d40&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Quạt USB lồng sắt 2014.">Quạt USB lồng sắt 2014.</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?ads_id=196&amp;g_main=57&amp;key=10628cdf5775a14909742be8cb106d40&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?ads_id=196&amp;g_main=57&amp;key=10628cdf5775a14909742be8cb106d40&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Quạt USB lồng sắt 2014."><img vspace="0" hspace="0" border="0" align="left" src="http://ads.link.vn/adv-res/image/196.png" style="width:90px;height:90px;" alt="Quạt-USB-lồng-sắt-2014"></a><div class="price"><a href="http://ads.link.vn/ads_tracker.link?ads_id=196&amp;g_main=57&amp;key=10628cdf5775a14909742be8cb106d40&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Quạt USB lồng sắt 2014."><span style="font-family:arial; font-size:10pt">Kiểu dáng xinh xắn, thời trang, sử dụng cổng USB đầy tiện lợi</span>. Giá chỉ <span style="color:#FF0000"><strong>75.000đ.</strong></span></a></div></div></div><div class="ssvzBorder"><span></span></div><div class="adv_items" id="adv_item"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?ads_id=203&amp;g_main=57&amp;key=ecba317ebc7c93eb072fbf01ab0c6f1a&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Túi xách nữ thời trang Sheng Dilu 8982">Túi xách nữ thời trang Sheng Dilu 8982</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?ads_id=203&amp;g_main=57&amp;key=ecba317ebc7c93eb072fbf01ab0c6f1a&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?ads_id=203&amp;g_main=57&amp;key=ecba317ebc7c93eb072fbf01ab0c6f1a&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Túi xách nữ thời trang Sheng Dilu 8982"><img vspace="0" hspace="0" border="0" align="left" src="http://ads.link.vn/adv-res/image/203.png" style="width:90px;height:90px;" alt="Túi-xách-nữ-thời-trang-Sheng-Dilu-8982"></a><div class="price"><a href="http://ads.link.vn/ads_tracker.link?ads_id=203&amp;g_main=57&amp;key=ecba317ebc7c93eb072fbf01ab0c6f1a&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Túi xách nữ thời trang Sheng Dilu 8982"><span style="font-family:arial; font-size:10pt">Chất liệu da thật cao cấp với những đường gân tự nhiên bền &amp; đẹp</span>. Giá chỉ <span style="color:#FF0000"><strong>840.000đ.</strong></span></a></div></div></div><div class="ssvzBorder"><span></span></div><div class="adv_items" id="adv_item"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?ads_id=208&amp;g_main=57&amp;key=ea173eb9bab4bda338e57677a447d398&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Giờ vàng vòi tắm công nghệ Nhật Bản.">Giờ vàng vòi tắm công nghệ Nhật Bản.</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?ads_id=208&amp;g_main=57&amp;key=ea173eb9bab4bda338e57677a447d398&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?ads_id=208&amp;g_main=57&amp;key=ea173eb9bab4bda338e57677a447d398&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Giờ vàng vòi tắm công nghệ Nhật Bản."><img vspace="0" hspace="0" border="0" align="left" src="http://ads.link.vn/adv-res/image/208.png" style="width:90px;height:90px;" alt="Giờ-vàng-vòi-tắm-công-nghệ-Nhật-Bản"></a><div class="price"><a href="http://ads.link.vn/ads_tracker.link?ads_id=208&amp;g_main=57&amp;key=ea173eb9bab4bda338e57677a447d398&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Giờ vàng vòi tắm công nghệ Nhật Bản."><span style="font-size:12px"><span style="font-family:tahoma">Tác dụng massage tạo cảm giác thư thái thoải mái. Giá sốc chỉ &nbsp;</span><strong><span style="color:#FF0000">99.000đ.</span></strong></span></a></div></div></div><div class="ssvzBorder"><span></span></div><div class="adv_items" id="adv_item"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=3cbdead69df46a878b38b528a4e38b7b&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Combo 4 giá dán tường đa năng BL-1127">Combo 4 giá dán tường đa năng BL-1127</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=3cbdead69df46a878b38b528a4e38b7b&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=3cbdead69df46a878b38b528a4e38b7b&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Combo 4 giá dán tường đa năng BL-1127"><img vspace="0" hspace="0" border="0" align="left" src="http://ads.link.vn/adv-res/image/202.png" style="width:90px;height:90px;" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"></a><div class="price"><a href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=3cbdead69df46a878b38b528a4e38b7b&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" title="Combo 4 giá dán tường đa năng BL-1127"><span style="font-size:12px">Công nghệ mới khả năng chịu lực lên tới 5kg không cần khoan đục. Giá chỉ <span style="color:#FF0000"><strong>88.000đ</strong></span>.</span></a></div></div></div></div><div class="ssvzclear"><span></span></div></div></div></div></div>
    </body>
</html>
