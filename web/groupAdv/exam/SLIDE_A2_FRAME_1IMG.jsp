<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SLIDE_A2_FRAME_1IMG</title>
    </head>
    <style>
        <%int groupID = 28;%>
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
        #ads_linkvn_zone_<%=groupID%>_slot10 #linkvn_slide_<%=groupID%>_Holder .linkvn_one_img{
            width: 298px;height: 598px;display: block;overflow: hidden;text-align: center;
        }
        #ads_linkvn_zone_<%=groupID%>_slot10 .linkvn_slotOne,
        #ads_linkvn_zone_<%=groupID%>_slot10 .linkvn_slotTwo,
        #ads_linkvn_zone_<%=groupID%>_slot10 .linkvn_slotThree{
            padding: 0;
            margin: 0;
            overflow: hidden;
            width: 298px;
            height: 598px;
            float: left;
        }
        .slide_boder{border-left: 1px solid #E91E23;border-right: 1px solid #E91E23;}
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
                        <div class="linkvn_one_img">
                            <a href="">
                                <img src="/test/img/test1img.png"/>
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
                if (stp < 0) {
                    if (ml > stp) {
                        es.style.marginLeft = ml - 2 + "px";
                        if (ml - 2 == stp) {
                            rtl = 0;
                        } else {
                            rtl = 1;
                        }
                        if ((ml - 2) % 298 == 0) {
                            setTimeout(function() {
                                linkvn_<%=groupID%>_initSlide(rtl);
                            }, 5000);
                            var it2 = document.getElementById("linkvn_slotTwo");
                            it2.className = it2.className.replace("slide_boder", "");
                        } else {
                            setTimeout(function() {
                                linkvn_<%=groupID%>_initSlide(rtl);
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
                            linkvn_<%=groupID%>_initSlide(rtl);
                        }, 5000);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        it2.className = it2.className.replace("slide_boder", "");
                    } else {
                        setTimeout(function() {
                            linkvn_<%=groupID%>_initSlide(rtl);
                        }, 15);
                        var it2 = document.getElementById("linkvn_slotTwo");
                        if (it2.className.indexOf("slide_boder") == -1) {
                            it2.className = it2.className + " slide_boder";
                        }
                    }
                }
            }
            function linkvn_<%=groupID%>_initSlide(rtl) {
                if (rtl == 1) {
                    rtl = LinkslideIt_<%=groupID%>(-298);
                } else {
                    rtl = LinkslideIt_<%=groupID%>(0);
                }
            }
            setTimeout(function() {
                linkvn_<%=groupID%>_initSlide(1);
            }, 5000);
        </script>
    </body>
</html>
