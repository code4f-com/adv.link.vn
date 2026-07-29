<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page contentType="text/html; charset=utf-8" %>
<style>    
    #ssvzone_7385 div, #ssvzone_7385 a:link, #ssvzone_7385 a:visited, #ssvzone_7385 a:active, #ssvzone_7385 span, #ssvzone_7385 p {
        -moz-font-feature-settings: normal;
        -moz-font-language-override: normal;
        -x-system-font: none;
        font-family: tahoma;
        font-size: 11px;
        font-size-adjust: none;
        font-stretch: normal;
        font-style: normal;
        font-variant: normal;
        font-weight: normal;
        line-height: normal;
    }
    #ssvzone_7385 {
        background: #FFFFFF none repeat scroll;
        border: #D7D7D7 2px solid; 
        height: 110px;
        width: 924px;
    }
    #ssvzone_7385 .adv_items {
        float: left;
        height: 110px;
        text-align: left;
        width: 288px;
    }
    #ssvzone_7385 .adv_items .image {
        float: left;
        padding-left: 5px;
        padding-top: 10px;
    }
    #ssvzone_7385 .adv_items .image img {
        float: left;
        height: 90px;
        width: 90px;
    }
    #ssvzone_7385 .adv_items .title {
        float: left;
        height: 27px;
        overflow: hidden;
        padding-left: 5px;
        padding-top: 8px;
        width: 170px;
    }
    #ssvzone_7385 #adv_item {
        float: left;
    }
    #ssvzone_7385 .adv_items .title a:link, #ssvzone_7385 .adv_items .title a:visited {
        text-decoration: none;
        color: #666666;
        float: left;
        font-family: Tahoma;
        font-size: 11px;
        font-weight: bold;
        text-align: left;
    }
    #ssvzone_7385 .itemmc {
        float: left;
        height: 14px;
        line-height: 14px;
        overflow: hidden;
        padding-left: 5px;
        text-align: left;
        width: 170px;
    }
    #ssvzone_7385 .div .adv_items .itemmc, #ssvzone_7385 .adv_items .itemmc a:visited, #ssvzone_7385 .adv_items .itemmc a:active, #ssvzone_7385 .adv_items .itemmc a:link {
        text-decoration: none;
        color: #B9B9B9;
        float: left;
    }
    #ssvzone_7385 .price {
        float: left;
        height: 59px;
        overflow: hidden;
        padding-left: 5px;
        text-align: left;
        width: 170px;
    }
    #ssvzone_7385 .adv_items .price a:link, #ssvzone_7385 .adv_items .price a:visited {
        text-decoration: none;
        color: #666666;
        font-family: tahoma;
        font-size: 11px;
        font-weight: normal;
    }
    #ssvzone_7385 #ssvzone_7385_items {
        float: left;
        overflow: hidden;
        width: 874px;
    }
    #ssvzone_7385 .border {
        background: url("http://admicro2.vcmedia.vn/adt/cpc/zoneimages/muachung/980_110/bglineh.jpg") repeat-y scroll ;
        float: left;
        height: 110px;
        width: 4px;
    }
    #ssvzone_7385 .left {
        background: url("<%=BuildCache.DOMAIN %>/resource/images/mlink_left.png") no-repeat scroll;
        border-right: #000099 1px dotted;        
        float: left;
        height: 108px;
        width: 21px;
    }
    #ssvzone_7385 .right {
        background: url("<%=BuildCache.DOMAIN %>/resource/images/mlink_right.png") no-repeat scroll;
        border-left: #cccc00 1px dotted;
        float: right;
        height: 108px;
        width: 21px;
    }
    #ssvzone_7385 .ads_link_item {
        height: 106px;
        margin-left: 21px;
        width: 901px;
    }

</style>
<%
String css_ngang = "#ssvzone_7385 div, #ssvzone_7385 a:link, #ssvzone_7385 a:visited, #ssvzone_7385 a:active, #ssvzone_7385 span, #ssvzone_7385 p { -moz-font-feature-settings: normal; -moz-font-language-override: normal; -x-system-font: none; font-family: tahoma; font-size: 11px; font-size-adjust: none; font-stretch: normal; font-style: normal; font-variant: normal; font-weight: normal; line-height: normal; } #ssvzone_7385 { background: #FFFFFF none repeat scroll; border: #D7D7D7 2px solid; height: 110px; width: 924px; } #ssvzone_7385 .adv_items { float: left; height: 110px; text-align: left; width: 288px; } #ssvzone_7385 .adv_items .image { float: left; padding-left: 5px; padding-top: 10px; } #ssvzone_7385 .adv_items .image img { float: left; height: 90px; width: 90px; } #ssvzone_7385 .adv_items .title { float: left; height: 27px; overflow: hidden; padding-left: 5px; padding-top: 8px; width: 170px; } #ssvzone_7385 #adv_item { float: left; } #ssvzone_7385 .adv_items .title a:link, #ssvzone_7385 .adv_items .title a:visited { text-decoration: none; color: #666666; float: left; font-family: Tahoma; font-size: 11px; font-weight: bold; text-align: left; } #ssvzone_7385 .itemmc { float: left; height: 14px; line-height: 14px; overflow: hidden; padding-left: 5px; text-align: left; width: 170px; } #ssvzone_7385 .div .adv_items .itemmc, #ssvzone_7385 .adv_items .itemmc a:visited, #ssvzone_7385 .adv_items .itemmc a:active, #ssvzone_7385 .adv_items .itemmc a:link { text-decoration: none; color: #B9B9B9; float: left; } #ssvzone_7385 .price { float: left; height: 59px; overflow: hidden; padding-left: 5px; text-align: left; width: 170px; } #ssvzone_7385 .adv_items .price a:link, #ssvzone_7385 .adv_items .price a:visited { text-decoration: none; color: #666666; font-family: tahoma; font-size: 11px; font-weight: normal; } #ssvzone_7385 #ssvzone_7385_items { float: left; overflow: hidden; width: 874px; } #ssvzone_7385 .border { background: url(\"http://admicro2.vcmedia.vn/adt/cpc/zoneimages/muachung/980_110/bglineh.jpg\") repeat-y scroll ; float: left; height: 110px; width: 4px; } #ssvzone_7385 .left { background: url(\""+BuildCache.DOMAIN +"/resource/images/mlink_left.png\") no-repeat scroll; border-right: #000099 1px dotted; float: left; height: 108px; width: 21px; } #ssvzone_7385 .right { background: url(\""+BuildCache.DOMAIN +"/resource/images/mlink_right.png\") no-repeat scroll; border-left: #cccc00 1px dotted; float: right; height: 108px; width: 21px; } #ssvzone_7385 .ads_link_item { height: 106px; margin-left: 21px; width: 901px; }";
%>
<div id="ads_zone7386">
    <div id="ads_zone7386_slot1">
        <div class="banner0" id="ads_zone7386_banner225859"> 
            <div id="ssvzone_7385">
                <a target="_blank" href="http://hot.vn">
                    <div class="left"></div>
                </a>
                <div id="header_ssvzone_7385">
                    <div class="ads_link_item">
                        <div id="ssvzone_7385_items">
                            <div id="adv_item" class="adv_items">
                                <div style="height:0px;width:0px;overflow:hidden" id="7385_221477"><span></span></div>
                                <div class="image"><a title="" target="_blank" href="#"><img vspace="0" hspace="0" border="0" align="left" style=" background:url('http://admicro2.vcmedia.vn/adt/cpc/ssvimg/2013/07/tour--11373282282.jpg') no-repeat center  center " alt="Du lịch Sapa 2 ngày 3 đêm - Giảm 44%" src="http://admicro2.vcmedia.vn/adt/cpc/zoneimages/spacer.gif"></a></div>
                                <div class="title"><a title="" target="_blank" href="#">Du lịch Sapa 2 ngày 3 đêm - Giảm <b style="font-weight:bold;color:#fc0203;">44%</b></a></div>
                                <div class="itemmc"><a title="" target="_blank" href="#">muachung.vn</a></div>
                                <div class="price"><a title="" target="_blank" href="#">Chỉ <b style="font-weight:bold;color:#fc0203;">1.322.000đ</b> có tour Sapa 2 ngày 3 đêm khám phá thiên nhiên hoang sơ giá <b>2.380.000đ</b></a></div>
                            </div>
                            <div class="border"><span></span></div>
                        </div>
                        <div class="right"></div>
                    </div>                    
                </div>                
            </div>
        </div>
    </div>
</div>