<%-- 
    Document   : newLayout
    Created on : Mar 3, 2014, 4:56:30 PM
    Author     : TUANPLA
--%>
<% //á %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>1 Frame 1 Image - 1 Frame Text</title>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
    </head>
    <style>
        #ads_linkvn_zone_28_slot10{
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
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder{
            position: relative;
            width: 1500px;
            height: 598px;
            text-align:left;           
        }
        #ads_linkvn_zone_28_slot10 .linkvn_slotOne,
        #ads_linkvn_zone_28_slot10 .linkvn_slotTwo,
        #ads_linkvn_zone_28_slot10 .linkvn_slotThree{
            padding: 0;
            margin: 0;
            overflow: hidden;
            width: 298px;
            height: 598px;
            float: left;
        }
        .slide_boder{border-left: 1px solid #E91E23;border-right: 1px solid #E91E23;}
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .top_300x100{
            width: 298px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .midden_300x300{
            width: 298px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .bottom_300x200{
            width: 298px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_100x100{
            width: 98px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_100x200{
            width: 98px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_100x100{
            width: 98px;height: 98px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_300x300{
            width: 298px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_100x200{
            width: 98px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_150x300{
            width: 148px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_75x150{
            width: 73px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_150x150{
            width: 148px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_150x300{
            width: 148px;height: 298px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_75x150{
            width: 73px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_150x150{
            width: 148px;height: 148px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .all_300x200{
            width: 298px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .item_200x200{
            width: 198px;height: 198px;display: block;overflow: hidden;text-align: center;float: left;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .img_item_200x200{
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
        /*TEXT*/
        #ads_linkvn_zone_28_slot10 .price_item_300x300{
            border: 1px solid #3597bf;
            font-size: 16px;
            color: fuchsia;font-weight: bold;width: 300px;overflow: hidden;margin-bottom: 3px;position: absolute;
            transform:rotate(-17deg);
            -ms-transform:rotate(-17deg); 
            -webkit-transform:rotate(-17deg); 
        }
        #ads_linkvn_zone_28_slot10 .price_hot{
            opacity: 0.8;
            margin-top: 150px;font-size: 28px;color: #cd0a0a;text-align: center;
        }
    </style>
    <body>
        <div align="center">
            <div id="ads_linkvn_zone_28_slot10">
                <div id="linkvn_slide_28_Holder" style="margin-left: 0">
                    <div class="linkvn_slotOne">
                        <div class="top_300x100 link-border-bottom">
                            <div class="item_100x100 link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                </a>
                            </div>
                            <div class="item_100x100 link-border-left link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                </a>
                            </div>
                            <div class="item_100x100 link-border-left">
                                <a target="_blank" href="">
                                    <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                </a>
                            </div>
                        </div>
                        <div class="midden_300x300 link-border-top link-border-bottom">
                            <div class="price_item_300x300 price_hot">HOT</div>
                            <a target="_blank" href="">
                                <img class="img_item_300x300" src="/test/img/vong210.png"/>
                            </a>
                        </div>
                        <div class="bottom_300x200 link-border-top">
                            <div class="item_100x200 link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>
                            <div class="item_100x200 link-border-left link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>
                            <div class="item_100x200 link-border-left">
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>                            
                        </div>
                    </div>
                    <!--Frame 2-->
                    <div class="linkvn_slotTwo" id="linkvn_slotTwo">
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
                    <!--Frame 3-->
                    <div class="linkvn_slotThree">
                        <div class="all_300x200 link-border-bottom">
                            <div class="item_200x200 link-border-right">
                                <a target="_blank" href="">
                                    <img class="img_item_200x200" src="/test/img/vong210.png"/>
                                </a>
                            </div>
                            <div class="item_100x200 link-border-left">
                                <div class="img_item_100x100 link-border-bottom"> 
                                    <a target="_blank" href="">
                                        <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                    </a>
                                </div>
                                <div class="img_item_100x100 link-border-top"> 
                                    <a target="_blank" href="">
                                        <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="all_300x200 link-border-bottom link-border-top">
                            <div class="img_item_100x200 link-border-right"> 
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>
                            <div class="img_item_100x200 link-border-left link-border-right"> 
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>
                            <div class="img_item_100x200 link-border-left"> 
                                <a target="_blank" href="">
                                    <img class="img_item_100x200" src="/test/img/70140.png"/>
                                </a>
                            </div>
                        </div>
                        <div class="all_300x200 link-border-top">
                            <div class="item_100x200 link-border-right">
                                <div class="img_item_100x100 link-border-bottom"> 
                                    <a target="_blank" href="">
                                        <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                    </a>
                                </div>
                                <div class="img_item_100x100 link-border-top"> 
                                    <a target="_blank" href="">
                                        <img class="img_item_100x100" src="/test/img/100.jpg"/>
                                    </a>
                                </div>
                            </div>
                            <div class="item_200x200 link-border-left">
                                <a target="_blank" href="">
                                    <img class="img_item_200x200" src="/test/img/vong210.png"/>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function LinkslideIt(stp) {
                var es = document.getElementById("linkvn_slide_28_Holder");
                var ml = parseInt(es.style.marginLeft);
                var rtl;
                if (stp < 0) {// right to left
                    if (ml > stp) {
                        es.style.marginLeft = ml - 2 + "px";
                        if (ml - 2 == stp) {
                            rtl = 0;
                        } else {
                            rtl = 1;
                        }
                        if ((ml - 2) % 298 == 0) {
                            setTimeout(function() {
                                linkvn_initSlide(rtl);
                            }, 5000);
                            var it2 = document.getElementById("linkvn_slotTwo");
                            it2.className = it2.className.replace("slide_boder", "");
                        } else {
                            setTimeout(function() {
                                linkvn_initSlide(rtl);
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
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 5000);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        it2.className = it2.className.replace("slide_boder", "");
                    } else {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 15);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        if (it2.className.indexOf("slide_boder") == -1) {
                            it2.className = it2.className + " slide_boder";
                        }
                    }
                }
            }
            function linkvn_initSlide(rtl) {
                if (rtl == 1) {
                    rtl = LinkslideIt(-596);
                } else {
                    rtl = LinkslideIt(0);
                }
            }
            setTimeout(function() {
                linkvn_initSlide(1);
            }, 5000);
        </script>
    </body>
</html>
