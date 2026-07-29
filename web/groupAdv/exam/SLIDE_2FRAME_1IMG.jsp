<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>1 Frame 1 Image - 1 Frame Text</title>
        <script type="text/javascript" src="<%= request.getContextPath()%>/resource/js/jquery-1.7.1.min.js"></script>
    </head>
    <%int groupID = 28;%>
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
        .slide_boder{border-left: 1px solid #E91E23;border-right: 1px solid #E91E23;}
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder{
            position: relative;
            width: 1500px;
            height: 598px;
            text-align:left;           
        }
        #ads_linkvn_zone_28_slot10 #linkvn_slide_28_Holder .linkvn_one_img{
            width: 298px;height: 598px;display: block;overflow: hidden;text-align: center;
        }

        #ads_linkvn_zone_28_slot10 .linkvn_slotOne,#ads_linkvn_zone_28_slot10 .linkvn_slotTwo{
            padding: 0;
            overflow: hidden;
            width: 298px;
            height: 598px;
            float: left;
        }
    </style>
    <body>
        <div align="center">
            <div id="ads_linkvn_zone_28_slot10">
                <div id="linkvn_slide_28_Holder" style="margin-left: 0px">
                    <div class="linkvn_slotOne">
                        <div class="linkvn_one_img">
                            <a href="">
                                <img src="/test/img/test1img.png"/>
                            </a>
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
