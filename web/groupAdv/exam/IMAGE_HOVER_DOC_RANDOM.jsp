<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMAGE_HOVER_DOC_RANDOM</title>
    </head>
    <style>

        .zone-floating {
            display: block;
            position: fixed;
        }
        .linkvn-zone {
            overflow: hidden;
            position: relative;
        }
        .linkvn-zone * {
            color: #464646;
            float: none;
            font-family: arial;
            font-size: 11px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: 500;
            line-height: 15px;
            margin: 0;
            outline: medium none;
            padding: 0;
            text-align: left;
            text-shadow: none;
            width: auto;
        }
        .linkvn-zone a, .linkvn-zone a:hover, .linkvn-zone a:visited, .linkvn-zone a:active {
            text-decoration: none !important;
        }
        .linkvn-zone a img {
            -moz-border-bottom-colors: none;
            -moz-border-left-colors: none;
            -moz-border-right-colors: none;
            -moz-border-top-colors: none;
            border-color: -moz-use-text-color;
            border-image: none 1 1 1 1;
            border-style: none;
            border-width: 0;
        }
        .linkvn-zone span {
            display: block;
        }
        .linkvn-zone.linkvn-zone-metro {
            background: none repeat scroll 0 center #ffffff;
            border: 1px solid #dcdcdc;
            overflow: hidden;
        }
        .linkvn-zone.linkvn-zone-metro * {
            font-family: "tahoma","Segoe UI","open sans","arial","sans-serif";
            font-size: 12px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: 700;
            line-height: 16px;
        }
        .linkvn-zone.linkvn-zone-metro .header {
            display: block;
            overflow: hidden;
            position: absolute;
            width: 100%;
            z-index: 1001;
        }
        .linkvn-zone.linkvn-zone-metro .header .logo {
            height: 17px;
            width: 19px;
        }
        .linkvn-zone.linkvn-zone-metro .header .logo i {
            background: url("http://ads.link.vn/resource/images/ads_logo_tiny.png") no-repeat scroll 0 0 rgba(0, 0, 0, 0);
            display: block;
            height: 17px;
            position: absolute;
            width: 19px !important;
            z-index: 1000;
        }
        .linkvn-zone.linkvn-zone-metro .header .logo abbr {
            background-color: #ffffff;
            background-image: url("data:image/png;base64,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");
            background-position: left 2px;
            background-repeat: no-repeat;
            border-radius: 0 0 2px;
            display: block;
            height: 14px;
            margin-left: -60px;
            padding-right: 2px;
            text-indent: -9999px;
            transition: margin 300ms linear 0s;
            width: 60px;
        }
        .linkvn-zone.linkvn-zone-metro .header .logo:hover abbr {
            margin-left: 20px;
        }
        .linkvn-zone.linkvn-zone-metro .banners:before, .linkvn-zone.linkvn-zone-metro .banners:after {
            content: "";
            display: table;
        }
        .linkvn-zone.linkvn-zone-metro .banners:after {
            clear: both;
        }
        .linkvn-zone.linkvn-zone-metro .one_block {
            display: block;
            float: left;
            height: 140px;
            margin: 4.5px;
            overflow: hidden;
            position: relative;
            width: 140px;
        }
        .linkvn-zone.linkvn-zone-metro .one_block:before, .linkvn-zone.linkvn-zone-metro .one_block:after {
            content: "";
            display: table;
        }
        .linkvn-zone.linkvn-zone-metro .one_block:after {
            clear: both;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front {
            position: relative;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front img {
            float: left;
            height: 110px;
            padding: 5px 15px;
            width: 110px;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span {
            background: none repeat scroll 0 0 #d2e7f0;
            height: 140px;
            left: 0;
            opacity: 0.9;
            padding: 6px 10px;
            position: absolute;
            top: 120px;
            transition: top 350ms ease-in 0s;
            z-index: 999;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span strong, .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span em {
            color: #3e3e3f;
            display: block;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span em {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: #797878;
            font-size: 11px;
            font-weight: 500;
            line-height: 21px;
            overflow: hidden;
            width: 120px;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span price {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: #e91e23;
            font-size: 14px;
            font-weight: bold;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span del {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: black;
            font-size: 11px;
            font-weight: 500;
            text-align: left !important;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span .discount {
            background: none repeat scroll 0 0 rgba(255, 0, 0, 0.7);
            float: right;
            height: 37px;
            width: 37px;
        }
        .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front:hover span {
            background: none repeat scroll 0 0 #81dafc;
            top: 0 !important;
            z-index: 1002;
        }
        .linkvn-zone.linkvn-zone-metro .one_block em {
            font-weight: bold;
            text-align: right !important;
        }
        .linkvn-zone.linkvn-zone-metro .one_block.banner-widget price {
            text-align: right !important;
        }
        .linkvn-zone.linkvn-zone-metro .one_block.banner-widget price {
            line-height: 12px;
        }
        .linkvn-zone.linkvn-zone-metro .one_block.banner-widget strong {
            height: 60px;
            margin: 10px 0;
            text-align: right !important;
        }
        .linkvn-zone.linkvn-zone-metro .one_block.banner-context .banner-face-front span strong {
            font-family: arial;
            font-size: 11px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: 700;
            line-height: 16px;
        }

    </style>
    <body>
        <div style="margin:0; padding:0;width:300px;float:left"> 
            <div data-rendered="true" class="link_ads_zone" data-zone-id="473"> 
                <div class="linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4"> 
<!--                    <a class="header" href="http://hot.vn" target="_blank"> 
                        <span class="logo"><i></i><abbr>ads by link.vn</abbr></span> 
                    </a> -->
                    <div class="banners"> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=904ab35c69fb277d87b6a60aab911171&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="202"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/202.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-size:12px">Công nghệ mới khả năng chịu lực lên tới 5kg không cần khoan đục. Giá chỉ <span style="color:#FF0000"><strong>88.000đ</strong></span>.</span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=200&amp;g_main=57&amp;key=ebe41e9271b60d760dc5c79703692dd4&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="200"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/200.png" alt="Giày-da-cho-bé-gái-Income-12025"> 
                                <span class="banner-content"><price>295,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-family:arial; font-size:10pt">Chất liệu da thật thoáng khí tạo cho bé sự thoải mái khi sử dụng</span>. Chỉ <span style="color:#FF0000"><strong>295.000đ.</strong></span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=204&amp;g_main=57&amp;key=72d05b315f943143765d38d119d9988d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="204"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/204.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-size:12px">Thiết kế <span style="color:#FF0000"><strong>thông minh</strong></span> giúp treo được nhiều vật dụng khác nhau. Giá chỉ&nbsp;<span style="color:#FF0000"><strong>88.000đ</strong></span></span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=197&amp;g_main=57&amp;key=119055a44cefd972a411499f6569909d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="197">
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/197.png" alt="Combo-2-bông-lau-nhà-360-độ">
                                <span class="banner-content"><price>52,000</price>&nbsp;&nbsp;<del></del><strong>Bông lau nhà làm bằng chất liệu sợi microfiber mềm mại, dễ thấm nước... Giá chỉ <span style="color:#FF0000"><strong>52.000đ.</strong></span></strong><em>hot.vn</em></span> 
                            </span>
                        </a>
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=904ab35c69fb277d87b6a60aab911171&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="202"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/202.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-size:12px">Công nghệ mới khả năng chịu lực lên tới 5kg không cần khoan đục. Giá chỉ <span style="color:#FF0000"><strong>88.000đ</strong></span>.</span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=200&amp;g_main=57&amp;key=ebe41e9271b60d760dc5c79703692dd4&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="200"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/200.png" alt="Giày-da-cho-bé-gái-Income-12025"> 
                                <span class="banner-content"><price>295,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-family:arial; font-size:10pt">Chất liệu da thật thoáng khí tạo cho bé sự thoải mái khi sử dụng</span>. Chỉ <span style="color:#FF0000"><strong>295.000đ.</strong></span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=204&amp;g_main=57&amp;key=72d05b315f943143765d38d119d9988d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="204"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/204.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del><strong><span style="font-size:12px">Thiết kế <span style="color:#FF0000"><strong>thông minh</strong></span> giúp treo được nhiều vật dụng khác nhau. Giá chỉ&nbsp;<span style="color:#FF0000"><strong>88.000đ</strong></span></span></strong><em>hot.vn</em></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=197&amp;g_main=57&amp;key=119055a44cefd972a411499f6569909d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="197">
                            <span class="banner-face banner-face-front"> 
                                <img src="http://ads.link.vn/adv-res/image/197.png" alt="Combo-2-bông-lau-nhà-360-độ">
                                <span class="banner-content"><price>52,000</price>&nbsp;&nbsp;<del></del><strong>Bông lau nhà làm bằng chất liệu sợi microfiber mềm mại, dễ thấm nước... Giá chỉ <span style="color:#FF0000"><strong>52.000đ.</strong></span></strong><em>hot.vn</em></span> 
                            </span>
                        </a> 
                    </div> 
                </div> 
            </div> 
        </div>
    </body>
</html>
