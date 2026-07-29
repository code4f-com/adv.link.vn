<% //á %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SLIDE_A2_HOVER</title>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
    </head> <%int groupID = 28;%>
    <style>
        #ads_linkvn_zone_<%=groupID%>_slot10{
            clear: both;
            width: 298px;
            height: 598px;
            margin: 0 auto 0 auto;
            padding: 0;
            overflow: hidden;
            text-align: left;
            border: 1px solid #E91E23;
            text-decoration: none;
            text-transform: none !important;
            font-family: tahoma,arial !important;
            font-size:11px;

        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder{
            position: relative;
            width: 1500px;
            height: 598px;
            text-align:left;           
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_zone_left,#ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_zone_center{
            float: left;
            border-right: 2px solid #3597bf;
            width: 98px;
            height: 598px
        }
        .slide_boder{border-left: 1px solid #E91E23;border-right: 1px solid #E91E23;}

        #ads_linkvn_zone_<%=groupID%>_slot10 .img_item_200{
            overflow: hidden;
            width: 98px;
            height: 198px;
            float: left;
            position: relative;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 .img_item_100{
            overflow: hidden;
            width: 98px;
            height: 98px;
            float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 .linkvn_slotOne,#ads_linkvn_zone_<%=groupID%>_slot10 .linkvn_slotTwo{
            padding: 0;
            overflow: hidden;
            width: 298px;
            height: 598px;
            float: left;
        }
        .zone-floating {
            display: block;
            position: fixed;
        }
        #linkvn_slide_<%=groupID%>_Holder {
            overflow: hidden;
            position: relative;
        }
        #linkvn_slide_<%=groupID%>_Holder * {
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
        #linkvn_slide_<%=groupID%>_Holder a, #linkvn_slide_<%=groupID%>_Holder a:hover, #linkvn_slide_<%=groupID%>_Holder a:visited, #linkvn_slide_<%=groupID%>_Holder a:active {
            text-decoration: none !important;
        }
        #linkvn_slide_<%=groupID%>_Holder a img {
            -moz-border-bottom-colors: none;
            -moz-border-left-colors: none;
            -moz-border-right-colors: none;
            -moz-border-top-colors: none;
            border-color: -moz-use-text-color;
            border-image: none 1 1 1 1;
            border-style: none;
            border-width: 0;
        }
        #linkvn_slide_<%=groupID%>_Holder span {
            display: block;
        }
        #linkvn_slide_<%=groupID%>_Holder {
            background: none repeat scroll 0 center #ffffff;
            border: 1px solid #dcdcdc;
            overflow: hidden;
        }
        #linkvn_slide_<%=groupID%>_Holder * {
            font-size: 12px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: 700;
            line-height: 16px;
        }
        #linkvn_slide_<%=groupID%>_Holder .banners:before, #linkvn_slide_<%=groupID%>_Holder .banners:after {
            display: table;
        }
        #linkvn_slide_<%=groupID%>_Holder .banners:after {
            clear: both;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block {
            display: block;
            float: left;
            height: 140px;
            margin: 4.5px;
            overflow: hidden;
            position: relative;
            width: 140px;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block:before, #linkvn_slide_<%=groupID%>_Holder .one_block:after {
            display: table;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block:after {
            clear: both;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front {
            position: relative;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front img {
            float: left;
            height: 110px;
            padding: 5px 15px;
            width: 110px;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span {
            background: none repeat scroll 0 0 #d2e7f0;
            height: 140px;
            left: 0;
            opacity: 0.9;
            padding: 3px 10px;
            position: absolute;
            top: 120px;
            transition: top 350ms ease-in 0s;
            z-index: 999;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span strong, #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span em {
            color: #3e3e3f;
            display: block;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span em {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: #797878;
            font-size: 11px;
            font-weight: 500;
            line-height: 21px;
            overflow: hidden;
            width: 120px;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span price {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: #e91e23;
            font-size: 14px;
            font-weight: bold;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span del {
            background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
            color: black;
            font-size: 11px;
            font-weight: 500;
            text-align: left !important;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front span .discount {
            background: none repeat scroll 0 0 rgba(255, 0, 0, 0.7);
            float: right;
            height: 37px;
            width: 37px;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block .banner-face-front:hover span {
            background: none repeat scroll 0 0 #81dafc;
            top: 0 !important;
            z-index: 1002;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block em {
            font-weight: bold;
            text-align: right !important;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block.banner-widget price {
            text-align: right !important;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block.banner-widget price {
            line-height: 12px;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block.banner-widget strong {
            margin: 10px 0;
            text-align: right !important;
        }
        #linkvn_slide_<%=groupID%>_Holder .one_block.banner-context .banner-face-front span strong {
            font-family: arial;
            font-size: 11px;
            font-size-adjust: none;
            font-stretch: normal;
            font-style: normal;
            font-variant: normal;
            font-weight: 700;
            line-height: 16px;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .top_300x100{
            width: 298px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .midden_300x300{
            width: 298px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .bottom_300x200{
            width: 298px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_100x100{
            width: 98px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_100x200{
            width: 98px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_100x100{
            width: 98px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_300x300{
            width: 298px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_100x200{
            width: 98px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_150x300{
            width: 148px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_75x150{
            width: 73px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_150x150{
            width: 148px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_150x300{
            width: 148px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_75x150{
            width: 73px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_150x150{
            width: 148px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .all_300x200{
            width: 298px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .item_200x200{
            width: 198px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .img_item_200x200{
            width: 198px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        .link-border-top{
            border-top:  solid 1px #3597bf;
        }
        .link-border-right{
            border-right: solid 1px #3597bf;
        }
        .link-border-bottom{
            border-bottom:  solid 1px #3597bf;
        }
        .link-border-left{
            border-left:  solid 1px #3597bf;
        }
        .slide_boder{border: 1px solid #E91E23}
    </style>
    <body>
        <div align="center">
            <div id="ads_linkvn_zone_<%=groupID%>_slot10">
                <div id="linkvn_slide_<%=groupID%>_Holder" style="margin-left: 0px">
                    <div class="linkvn_slotOne" id="linkvn_slotOne">
                        <div class="midden_300x300 link-border-bottom">
                            <div class="item_150x300 link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_150x300" src="/test/img/70140.png"/>
                                </a>
                            </div>
                            <div class="item_150x300 link-border-left">
                                <div class="item_150x150 link-border-bottom">
                                    <a target="_blank" href="">
                                        <img class="img_item_150x150" src="/test/img/vong210.png"/>
                                    </a>
                                </div>
                                <div class="item_150x150 link-border-top">
                                    <div class="item_75x150 link-border-right">
                                        <a target="_blank" href="">
                                            <img class="img_item_75x150" src="/test/img/vong210.png"/>
                                        </a>
                                    </div>
                                    <div class="item_75x150 link-border-left">
                                        <a target="_blank" href="">
                                            <img class="img_item_75x150" src="/test/img/vong210.png"/>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="midden_300x300 link-border-top">
                            <div class="item_150x300 link-border-right">
                                <div class="item_150x150 link-border-bottom">
                                    <div class="item_75x150 link-border-right">
                                        <a target="_blank" href="">
                                            <img class="img_item_75x150" src="/test/img/vong210.png"/>
                                        </a>
                                    </div>
                                    <div class="item_75x150 link-border-left">
                                        <a target="_blank" href="">
                                            <img class="img_item_75x150" src="/test/img/vong210.png"/>
                                        </a>
                                    </div>
                                </div>
                                <div class="item_150x150 link-border-top">
                                    <a target="_blank" href="">
                                        <img class="img_item_150x150" src="/test/img/vong210.png"/>
                                    </a>
                                </div>
                            </div>
                            <div class="item_150x300 link-border-left">
                                <a target="_blank" href="">
                                    <img class="img_item_150x300" src="/test/img/70140.png"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="linkvn_slotTwo" id="linkvn_slotTwo">
                        <div class="banners"> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=904ab35c69fb277d87b6a60aab911171&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="202"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/202.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                    <span class="banner-content">
                                        <price>88,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Công nghệ mới khả năng chịu lực lên tới 5kg không cần khoan đục. Giá chỉ
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=200&amp;g_main=57&amp;key=ebe41e9271b60d760dc5c79703692dd4&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="200"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/200.png" alt="Giày-da-cho-bé-gái-Income-12025"> 
                                    <span class="banner-content"><price>295,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Chất liệu da thật thoáng khí tạo cho bé sự thoải mái khi sử dụng
                                        </strong>
                                        <em>hot.vn</em></span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=204&amp;g_main=57&amp;key=72d05b315f943143765d38d119d9988d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="204"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/204.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                    <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            thông minh
                                            giúp treo được nhiều vật dụng khác nhau. Giá chỉ&nbsp;
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=197&amp;g_main=57&amp;key=119055a44cefd972a411499f6569909d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="197">
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/197.png" alt="Combo-2-bông-lau-nhà-360-độ">
                                    <span class="banner-content"><price>52,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Bông lau nhà làm bằng chất liệu sợi microfiber mềm mại, dễ thấm nước... Giá chỉ 
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span>
                            </a>
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=202&amp;g_main=57&amp;key=904ab35c69fb277d87b6a60aab911171&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="202"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/202.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                    <span class="banner-content">
                                        <price>88,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Công nghệ mới khả năng chịu lực lên tới 5kg không cần khoan đục. Giá chỉ
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=200&amp;g_main=57&amp;key=ebe41e9271b60d760dc5c79703692dd4&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="200"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/200.png" alt="Giày-da-cho-bé-gái-Income-12025"> 
                                    <span class="banner-content"><price>295,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Chất liệu da thật thoáng khí tạo cho bé sự thoải mái khi sử dụng
                                        </strong>
                                        <em>hot.vn</em></span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=204&amp;g_main=57&amp;key=72d05b315f943143765d38d119d9988d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="204"> 
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/204.png" alt="Combo-4-giá-dán-tường-đa-năng-BL-1127"> 
                                    <span class="banner-content"><price>88,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            thông minh
                                            giúp treo được nhiều vật dụng khác nhau. Giá chỉ&nbsp;
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span> 
                            </a> 
                            <a class="banner-widget one_block banner-first-row" href="http://ads.link.vn/ads_tracker.link?ads_id=197&amp;g_main=57&amp;key=119055a44cefd972a411499f6569909d&amp;refer=http://link.vn:8080/groupAdv/view/view-ads-57.html" target="_blank" data-banner-id="197">
                                <span class="banner-face banner-face-front"> 
                                    <img src="http://ads.link.vn/adv-res/image/197.png" alt="Combo-2-bông-lau-nhà-360-độ">
                                    <span class="banner-content"><price>52,000</price>&nbsp;&nbsp;<del></del>
                                        <strong>
                                            Bông lau nhà làm bằng chất liệu sợi microfiber mềm mại, dễ thấm nước... Giá chỉ 
                                        </strong>
                                        <em>hot.vn</em>
                                    </span> 
                                </span>
                            </a>
                        </div> 
                    </div>
                </div>
            </div>
        </div>
        <script>
            function LinkslideIt_<%=groupID%>(stp) {
                var es = document.getElementById("linkvn_slide_<%=groupID%>_Holder");
                var ml = parseInt(es.style.marginLeft);
                var rtl;
                if (stp < 0) { /* right to left*/
                    if (ml > stp) {
                        es.style.marginLeft = ml - 2 + "px";
                        if (ml - 2 == stp) {
                            rtl = 0;
                        } else {
                            rtl = 1;
                        }
                        if ((ml - 2) % 298 == 0) {
                            setTimeout(function () {
                                linkvn_initSlide_<%=groupID%>(rtl);
                            }, 5000);
                            var it2 = document.getElementById("linkvn_slotTwo");
                            it2.className = it2.className.replace("slide_boder", "");
                        } else {
                            setTimeout(function () {
                                linkvn_initSlide_<%=groupID%>(rtl);
                            }, 15);
                            var it2 = document.getElementById("linkvn_slotTwo");
                            if (it2.className.indexOf("slide_boder") == -1) {
                                it2.className = it2.className + " slide_boder";
                            }
                        }
                    }
                } else {
                    if (ml < stp) {
                        es.style.marginLeft = ml + 2 + "px";
                        if (ml + 2 == stp) {
                            rtl = 1;
                        }
                        else {
                            rtl = 0;
                        }
                    }
                    if ((ml + 2) % 298 == 0) {
                        setTimeout(function () {
                            linkvn_initSlide_<%=groupID%>(rtl);
                        }, 5000);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        it2.className = it2.className.replace("slide_boder", "");
                    } else {
                        setTimeout(function () {
                            linkvn_initSlide_<%=groupID%>(rtl);
                        }, 15);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        if (it2.className.indexOf("slide_boder") == -1) {
                            it2.className = it2.className + " slide_boder";
                        }
                    }
                }
            }
            function linkvn_initSlide_<%=groupID%>(rtl) {
                if (rtl == 1) {
                    rtl = LinkslideIt_<%=groupID%>(-298);
                } else {
                    rtl = LinkslideIt_<%=groupID%>(0);
                }
            }
            setTimeout(function () {
                linkvn_initSlide_<%=groupID%>(1);
            }, 5000);
        </script>
    </body>
</html>
