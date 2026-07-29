<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Layout</title>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
    </head>
    <style>
        /*210x480*/
        #ads_linkvn_zone_28_slot10{
            clear: both;
            width: 208px;
            height: 478px;
            margin: 0 auto 0 auto;
            padding: 0;
            overflow: hidden;
            text-align: center;
            border: 1px solid #E91E23;
            text-decoration: none;
            text-transform: none !important;
            font-family: tahoma,arial !important;
            font-size:11px;

        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder{
            position: relative;
            width: 1500px;
            height: 478px;
            text-align:center;           
        }   
        #ads_linkvn_zone_28_slot10 .linkvn_slotOne,#ads_linkvn_zone_28_slot10 .linkvn_slotTwo{
            padding: 0;
            overflow: hidden;
            width: 208px;
            height: 478px;
            float: left;
            border-right:  1px solid #E91E23;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_horizontal_top{
            float: left;
            width: 208px;
            height: 68px;
            border-bottom:  solid 1px #3597bf;
            overflow: hidden;               
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_horizontal_top .img70x70,
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280 .img70x70{
            float: left;
            width: 68px;
            height: 68px;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_horizontal_top .item70x70,
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280 .item70x70{
            float: left;
            overflow: hidden;
            width: 68px;
            height: 68px; 
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_square_210{
            float: left;
            width: 208px;
            height: 208px;
            overflow: hidden;
            position: relative;
            border-top:  solid 1px #3597bf;
            border-bottom:  solid 1px #3597bf;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_mti_item_140{
            float: left;
            width: 208px;
            height: 138px;
            overflow: hidden;
            border-top:  solid 1px #3597bf;
            border-bottom:  solid 1px #3597bf;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_mti_item_140 .img70x140,
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280 .img70x140{
            float: left;
            width: 68px;
            height: 138px;
            overflow: hidden;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_mti_item_140 .img140x140{
            float: left;
            width: 138px;
            height: 138px;
            overflow: hidden;
        }

        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280{
            float: left;
            width: 208px;
            height: 280px;
            overflow: hidden;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280 .zone_left_70{
            float: left;
            height: 280px;
            width: 68px;
            overflow: hidden;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_vertical_210x280 .zone_right_140{
            float: left;
            height: 280px;
            width: 138px;
            overflow: hidden;
            border-left:  solid 2px #3597bf;
        }
        #ads_linkvn_zone_28_slot10 #linkvn_logo_mebe{
            float: left;
            width: 208px;
            height: 58px;
            overflow: hidden;
        }
        .link-border-right{
            border-right: solid 1px #3597bf;
        }
        .link-border-left{
            border-left:  solid 1px #3597bf;
        }
        .link-border-bottom{
            border-bottom:  solid 1px #3597bf;
        }
        .link-border-top{
            border-top:  solid 1px #3597bf;
        }
        #ads_linkvn_zone_28_slot10 .price_item_210{
            border: 1px solid #3597bf;
            font-size: 16px;
            color: fuchsia;font-weight: bold;width: 210px;overflow: hidden;margin-bottom: 3px;position: absolute;
            transform:rotate(-17deg);
            -ms-transform:rotate(-17deg); 
            -webkit-transform:rotate(-17deg); 
        }
        #ads_linkvn_zone_28_slot10 .price_item_140{
            border: 1px solid #3597bf;
            font-size: 16px;
            color: fuchsia;font-weight: bold;width: 140px;overflow: hidden;margin-bottom: 3px;position: absolute;
            transform:rotate(-17deg);
            -ms-transform:rotate(-17deg); 
            -webkit-transform:rotate(-17deg); 
        }
        #ads_linkvn_zone_28_slot10 .price_img{
            margin-top: 160px;
        }
        #ads_linkvn_zone_28_slot10 .price_sale{
            opacity: 0.8;
            margin-top: 80px;font-size: 28px;color: #cd0a0a
        }
        #ads_linkvn_zone_28_slot10 .price_hot{
            opacity: 0.8;
            margin-top: 150px;font-size: 28px;color: #cd0a0a;text-align: center;
        }        
    </style>
    <body>
        <div align="center">
            <div id="ads_linkvn_zone_28_slot10">
                <div id="linkvn_slide_28_Holder" style="margin-left: -0px">
                    <div class="linkvn_slotOne">
                        <div id="linkvn_zone_horizontal_top">
                            <div class="item70x70 link-border-right"><a href="hot.vn" ><img class="img70x70" src="/test/img/70x70.png"></a></div>
                            <div class="item70x70 link-border-left link-border-right"><a href="hot.vn" ><img class="img70x70" src="/test/img/70x70.png"></a></div>
                            <div class="item70x70 link-border-left"><a href="hot.vn" ><img class="img70x70" width="98" src="/test/img/70x70.png"></a></div>
                        </div>
                        <div id="linkvn_zone_square_210">
                            <div class=""><div class="price_item_210 price_hot">HOT</div><a href="hot.vn" ><img src="/test/img/vong210.png"></a></div>
                        </div>
                        <div id="linkvn_zone_vertical_mti_item_140">
                            <div class="img70x140 link-border-right"><a href="hot.vn" ><img src="/test/img/70140.png"></a></div>
                            <div class="img70x140 link-border-right link-border-left"><a href="hot.vn" ><img src="/test/img/70140.png"></a></div>
                            <div class="img70x140 link-border-left"><a href="hot.vn" ><img src="/test/img/70140.png"></a></div>
                        </div>
                        <div id="linkvn_logo_mebe" class="link-border-top">
                            <div><a href="mebe.vn"><img height="58" src="/test/img/logomebe.png"></a></div>
                        </div>
                    </div>
                    <!--Frame 2-->                     
                    <div class="linkvn_slotTwo">
                        <div id="linkvn_logo_mebe" class="link-border-bottom">
                            <div><a href="mebe.vn"><img height="58" src="/test/img/logomebe.png"></a></div>
                        </div>
                        <div id="linkvn_zone_vertical_mti_item_140" class="link-border-top">
                            <div class="img140x140 link-border-right"><a href="hot.vn" ><img src="/test/img/vong210.png"></a></div>
                            <div class="img70x140 link-border-left"><a href="hot.vn" ><img src="/test/img/70140.png"></a></div>
                        </div>
                        <div id="linkvn_zone_vertical_210x280" class="link-border-top">
                            <div class="zone_left_70">
                                <div class="item70x70 link-border-bottom"><a class="img70x70" href="hot.vn" ><img src="/test/img/70x70.png"></a></div>
                                <div class="img70x140 link-border-top link-border-bottom"><a href="hot.vn" ><a href="hot.vn" ><img src="/test/img/70140.png"></a></div>
                                <div class="item70x70 link-border-top"><a class="img70x70" href="hot.vn" ><img src="/test/img/70x70.png"></a></div>
                            </div>
                            <div class="zone_right_140">
                                <div><a href="hot.vn"><div class="price_item_140 price_sale">SALE</div><img width="140" src="/test/img/70140.png"></a></div>
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
                if (stp == null || stp == 'undefined') {
                    stp = -208;
                }
                if (stp < 0) {
                    if (ml > stp) {
                        es.style.marginLeft = ml - 2 + "px";
                        if (ml - 2 == stp) {
                            return 0;
                        } else {
                            return  1;
                        }
                    }
                } else {
                    if (ml < stp) {
                        es.style.marginLeft = ml + 2 + "px";
                        if (ml + 2 == stp) {
                            return 1;
                        }
                        else {
                            return 0;
                        }
                    }
                }
            }
            function linkvn_initSlide(rtl) {
                var tmp = rtl;
                if (rtl == 1) {
                    rtl = LinkslideIt(-208);
                    if (tmp == rtl) {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 10);
                    } else {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 5000);
                    }
                }
                else {
                    rtl = LinkslideIt(0);
                    if (tmp == rtl) {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 10);
                    } else {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 5000);
                    }
                }
            }
            setTimeout(function() {
                linkvn_initSlide(1);
            }, 5000);
        </script>
    </body>
</html>
