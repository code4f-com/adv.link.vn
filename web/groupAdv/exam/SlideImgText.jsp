<% //á %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Layout</title>
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
        #ads_linkvn_zone_28_slot10 #linkvn_slide28Holder{
            position: relative;
            width: 1500px;
            height: 598px;
            text-align:left;           
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_left,#ads_linkvn_zone_28_slot10 #linkvn_zone_center{
            float: left;
            border-right: 2px solid #3597bf;
            width: 98px;
            height: 598px
        }
        #ads_linkvn_zone_28_slot10 #linkvn_zone_right{
            float: right;
            width: 98px;
            height: 598px
        }
        #ads_linkvn_zone_28_slot10 .img_item_200{
            overflow: hidden;
            width: 98px;
            height: 198px;
            float: left;
            position: relative;
        }
        #ads_linkvn_zone_28_slot10 .img_item_100{
            overflow: hidden;
            width: 98px;
            height: 98px;
            float: left;
        }
        #linkvn_zone_left div.border_bottom,#linkvn_zone_right div.border_bottom,#linkvn_zone_center div.border_bottom{
            border-bottom: 2px solid #3597bf;
            text-align: center;

        }
        #linkvn_zone_left div.border_bottom img,#linkvn_zone_right div.border_bottom img,#linkvn_zone_center div.border_bottom img{
            width: 98px
        }
        #ads_linkvn_zone_28_slot10 .linkvn_slotOne,#ads_linkvn_zone_28_slot10 .linkvn_slotTwo{
            padding: 0;
            overflow: hidden;
            width: 298px;
            height: 598px;
            float: left;
        }
        #ssvzone_10 .ssvzContent {
            clear: both;
            overflow: hidden;
        }
        #ssvzone_10 #ssvzone_10_items {
            clear: both;
            height: 598px;
            margin: 0;
            padding: 0;
        }
        #ssvzone_10_items .ssvzBorder {
            background: none repeat scroll 0 0 #DEDEDE;
            height: 1px;
        }
        #ssvzone_10_items .adv_items {
            clear: both;
            height: 148px;
            overflow: hidden;
        }
        #ssvzone_10_items .adv_items .ssvzimage {
            clear: both;
            padding: 0 12px;
        }
        #ssvzone_10_items .adv_items .ssvzimage img {
            border: 0 none rgba(0, 0, 0, 0);
            float: left;
            height: 90px;
            margin-right: 5px;
            width: 90px;
        }
        #ssvzone_10_items .adv_items .ssvzTitle {
            clear: both;
            float: left;
            height: auto;
            line-height: 14px;
            overflow: hidden;
            padding: 8px 12px 0;
            text-align: left;
        }
        #ssvzone_10_items .adv_items .ssvzTitle a:link, #ssvzone_10_items .adv_items .ssvzTitle a:visited {
            color: #333333;
            font-family: tahoma,arial,verdana;
            font-size: 11px;
            font-weight: 700 !important;
            padding-top: 0;
            text-align: center;
            text-decoration: none;
        }
        #ssvzone_10_items .itemmc {
            clear: both;
            height: 17px;
            line-height: 12px;
            margin-bottom: 3px;
            overflow: hidden;
            padding-left: 12px;
            padding-right: 10px;
            padding-top: 2px;
            text-align: left;
        }
        #ssvzone_10_items .itemmc, #ssvzone_10_items .itemmc a:visited, #ssvzone_10_items .itemmc a:active, #ssvzone_10_items .itemmc a:link {
            color: #666666;
            font-weight: normal;
            margin-bottom: 2px;
            text-decoration: none;
        }
        #ssvzone_10_items .adv_items .price {
            height: 97px;
            line-height: 16px;
            overflow: hidden;
            padding-left: 5px;
            text-align: left;
        }
        #ssvzone_10_items .adv_items .price a:link, #ssvzone_10_items .adv_items .price a:visited {
            color: #333333;
            font-family: Tahoma;
            font-size: 11px;
            font-weight: 400;
            text-decoration: none;
        }
        #ads_linkvn_zone_28_slot10 .price_item_200{
            border: 1px solid #3597bf;
            font-size: 16px;
            color: fuchsia;font-weight: bold;width: 100px;overflow: hidden;margin-bottom: 3px;position: absolute;
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
                <div id="linkvn_slide28Holder" style="margin-left: 0px">
                    <div class="linkvn_slotOne">
                        <div id="linkvn_zone_left">
                            <div class="img_item_200 border_bottom"><div class="price_item_200 price_img">1000.000 Đ</div><a href="hot.vn" title=""><img alt="" src="/test/img/200.png"></a></div>
                            <div class="img_item_200 border_bottom"><div class="price_item_200 price_sale">SALE</div><a href="hot.vn" title=""><img alt="" src="/test/img/2001.png"></a></div>
                            <div class="img_item_200"><div class="price_item_200 price_hot">HOT</div><a href="hot.vn" title=""><img width="98" alt="" src="/test/img/2002.png"></a></div>
                        </div>
                        <div id="linkvn_zone_center">
                            <div class="img_item_100 border_bottom"><a href="hot.vn" title=""><img alt="" src="/test/img/101.jpg"></a></div>
                            <div class="img_item_200 border_bottom"><a href="hot.vn" title=""><img alt="" src="/test/img/2002.png"></a></div>
                            <div class="img_item_200 border_bottom"><a href="hot.vn" title=""><img alt="" src="/test/img/200.png"></a></div>
                            <div class="img_item_100"><a href="hot.vn" title=""><img alt="" src="/test/img/100.jpg"></a></div>
                        </div>
                        <div id="linkvn_zone_right">
                            <div class="img_item_200 border_bottom"><a href="hot.vn" title=""><img alt="" src="/test/img/2002.png"></a></div>
                            <div class="img_item_200 border_bottom"><a href="hot.vn" title=""><img alt="" src="/test/img/2001.png"></a></div>
                            <div class="img_item_200"><a href="hot.vn" title=""><img alt="" src="/test/img/200.png"></a></div>
                        </div>
                    </div>
                    <div class="linkvn_slotTwo">
                        <div id="ssvzone_10" style="display: block;">
                            <div class="ssvzContent">
                                <div id="ssvzone_10_items"> 
                                    <div class="adv_items"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?token=97223997d0a037b1a694eea53213f5ac2905215cdc98e6fe2d7bd55bfcd4e84c938d51fa79b5f70de5aa814a7b6a8c729bc27bb82d2760afe3f1fb89a14a72563975b8c936a8bfad0d89b13f576321f4626cc82ae2054c539358fdb5176f386c974135c277425d4af52e7be67d27a951b5f34a3b2d0c94e09fcf738eee4ff33343ab57a2be7fba533371dc1f9a866956d24516a5bbbf75ac432ace4679e115bc37275719cb24296c5b1d7e31fee3d8c14ce916480adbce8354b117f724cef273dcc7dfaf524efc9e38ef41ab77cf00ef3a5df20db3890d2b2e3c9f812ae051faab8d753ad1dfe947f17925ee3384c04c0f21b7102ae457c9a99e36814116c829&amp;key=d9257b6a17565bde89aae458975d2a94&amp;refer=http://vietnamnet.vn/" target="_blank" title="Áo len vặn thừng lót lông form dài JH018">Áo len vặn thừng lót lông form dài JH018</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?token=97223997d0a037b1a694eea53213f5ac2905215cdc98e6fe2d7bd55bfcd4e84c938d51fa79b5f70de5aa814a7b6a8c729bc27bb82d2760afe3f1fb89a14a72563975b8c936a8bfad0d89b13f576321f4626cc82ae2054c539358fdb5176f386c974135c277425d4af52e7be67d27a951b5f34a3b2d0c94e09fcf738eee4ff33343ab57a2be7fba533371dc1f9a866956d24516a5bbbf75ac432ace4679e115bc37275719cb24296c5b1d7e31fee3d8c14ce916480adbce8354b117f724cef273dcc7dfaf524efc9e38ef41ab77cf00ef3a5df20db3890d2b2e3c9f812ae051faab8d753ad1dfe947f17925ee3384c04c0f21b7102ae457c9a99e36814116c829&amp;key=d9257b6a17565bde89aae458975d2a94&amp;refer=http://vietnamnet.vn/" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?token=97223997d0a037b1a694eea53213f5ac2905215cdc98e6fe2d7bd55bfcd4e84c938d51fa79b5f70de5aa814a7b6a8c729bc27bb82d2760afe3f1fb89a14a72563975b8c936a8bfad0d89b13f576321f4626cc82ae2054c539358fdb5176f386c974135c277425d4af52e7be67d27a951b5f34a3b2d0c94e09fcf738eee4ff33343ab57a2be7fba533371dc1f9a866956d24516a5bbbf75ac432ace4679e115bc37275719cb24296c5b1d7e31fee3d8c14ce916480adbce8354b117f724cef273dcc7dfaf524efc9e38ef41ab77cf00ef3a5df20db3890d2b2e3c9f812ae051faab8d753ad1dfe947f17925ee3384c04c0f21b7102ae457c9a99e36814116c829&amp;key=d9257b6a17565bde89aae458975d2a94&amp;refer=http://vietnamnet.vn/" target="_blank" title="Áo len vặn thừng lót lông form dài JH018"><img hspace="0" vspace="0" border="0" align="left" style="width:90px;height:90px;" src="http://ads.link.vn/adv-res/image/171.gif" alt="Áo len vặn thừng lót lông form dài JH018"></a> <div class="price"><a href="http://ads.link.vn/ads_tracker.link?token=97223997d0a037b1a694eea53213f5ac2905215cdc98e6fe2d7bd55bfcd4e84c938d51fa79b5f70de5aa814a7b6a8c729bc27bb82d2760afe3f1fb89a14a72563975b8c936a8bfad0d89b13f576321f4626cc82ae2054c539358fdb5176f386c974135c277425d4af52e7be67d27a951b5f34a3b2d0c94e09fcf738eee4ff33343ab57a2be7fba533371dc1f9a866956d24516a5bbbf75ac432ace4679e115bc37275719cb24296c5b1d7e31fee3d8c14ce916480adbce8354b117f724cef273dcc7dfaf524efc9e38ef41ab77cf00ef3a5df20db3890d2b2e3c9f812ae051faab8d753ad1dfe947f17925ee3384c04c0f21b7102ae457c9a99e36814116c829&amp;key=d9257b6a17565bde89aae458975d2a94&amp;refer=http://vietnamnet.vn/" target="_blank" title="Áo len vặn thừng lót lông form dài JH018">Ấm áp và thời trang là ưu điểm nổi bật có nhiều màu để bạn lựa chọn. Giá chỉ <span style="color:#FF0000"><strong>395.000đ.</strong></span></a> </div></div></div> 
                                    <div class="ssvzBorder"><span></span></div> 
                                    <div class="adv_items"><div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?token=8a467d8e305645ed35daf48de9618d9e42e6a2d950966249cf965c1b3def194903856411f10fb2d80bb4161f575b4303f72a492743f4e03eea6804bf684aac4588b10f88049613dd25e7c3df8e196bfd8f547df3257ddf65633e195276c53a82dc854f8a92f825b2b49559848b85c34fe332884b23c3795da56f64ad83f0cfd6afd60d47c39e766db9730a5ebcaaf63d35011f0e9d2e75b6245750c3b91e76b9d0f71095b53d1da4a23f23c81929f850634a3626dee1a2bab984e7bb6f1dfca9773ede5e2ff806e3ce6c4d7b751be4cfe9f2609a4bad0482696f85db2da93645de35b248747a9a5a2f755eb6ef866f49a921892eb65d7b56371d3f1a4496ebda&amp;key=ce1543024cdaabafa60d08bf60acd342&amp;refer=http://vietnamnet.vn/" target="_blank" title="Tiện lợi khi dùng giá nhà bếp đa năng">Tiện lợi khi dùng giá nhà bếp đa năng</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?token=8a467d8e305645ed35daf48de9618d9e42e6a2d950966249cf965c1b3def194903856411f10fb2d80bb4161f575b4303f72a492743f4e03eea6804bf684aac4588b10f88049613dd25e7c3df8e196bfd8f547df3257ddf65633e195276c53a82dc854f8a92f825b2b49559848b85c34fe332884b23c3795da56f64ad83f0cfd6afd60d47c39e766db9730a5ebcaaf63d35011f0e9d2e75b6245750c3b91e76b9d0f71095b53d1da4a23f23c81929f850634a3626dee1a2bab984e7bb6f1dfca9773ede5e2ff806e3ce6c4d7b751be4cfe9f2609a4bad0482696f85db2da93645de35b248747a9a5a2f755eb6ef866f49a921892eb65d7b56371d3f1a4496ebda&amp;key=ce1543024cdaabafa60d08bf60acd342&amp;refer=http://vietnamnet.vn/" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?token=8a467d8e305645ed35daf48de9618d9e42e6a2d950966249cf965c1b3def194903856411f10fb2d80bb4161f575b4303f72a492743f4e03eea6804bf684aac4588b10f88049613dd25e7c3df8e196bfd8f547df3257ddf65633e195276c53a82dc854f8a92f825b2b49559848b85c34fe332884b23c3795da56f64ad83f0cfd6afd60d47c39e766db9730a5ebcaaf63d35011f0e9d2e75b6245750c3b91e76b9d0f71095b53d1da4a23f23c81929f850634a3626dee1a2bab984e7bb6f1dfca9773ede5e2ff806e3ce6c4d7b751be4cfe9f2609a4bad0482696f85db2da93645de35b248747a9a5a2f755eb6ef866f49a921892eb65d7b56371d3f1a4496ebda&amp;key=ce1543024cdaabafa60d08bf60acd342&amp;refer=http://vietnamnet.vn/" target="_blank" title="Tiện lợi khi dùng giá nhà bếp đa năng"><img hspace="0" vspace="0" border="0" align="left" style="width:90px;height:90px;" src="http://ads.link.vn/adv-res/image/64.jpg" alt="Tiện lợi khi dùng giá nhà bếp đa năng"></a> <div class="price"><a href="http://ads.link.vn/ads_tracker.link?token=8a467d8e305645ed35daf48de9618d9e42e6a2d950966249cf965c1b3def194903856411f10fb2d80bb4161f575b4303f72a492743f4e03eea6804bf684aac4588b10f88049613dd25e7c3df8e196bfd8f547df3257ddf65633e195276c53a82dc854f8a92f825b2b49559848b85c34fe332884b23c3795da56f64ad83f0cfd6afd60d47c39e766db9730a5ebcaaf63d35011f0e9d2e75b6245750c3b91e76b9d0f71095b53d1da4a23f23c81929f850634a3626dee1a2bab984e7bb6f1dfca9773ede5e2ff806e3ce6c4d7b751be4cfe9f2609a4bad0482696f85db2da93645de35b248747a9a5a2f755eb6ef866f49a921892eb65d7b56371d3f1a4496ebda&amp;key=ce1543024cdaabafa60d08bf60acd342&amp;refer=http://vietnamnet.vn/" target="_blank" title="Tiện lợi khi dùng giá nhà bếp đa năng">Kiểu dáng hiện đại, nhỏ gọn, đa chức năng, chất liệu hợp kim nhôm giá chỉ <span style="color:#FF0000"><strong>195.000đ.</strong></span></a> </div></div></div> 
                                    <div class="ssvzBorder"><span></span></div> 
                                    <div class="adv_items"> <div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?token=43e15cccd780bed74657e0cc2f731578455769f10975cdf00615d603fc6942843ba239d39d732fd8526e4a060cdd7e49d1562a1ea9299f9ae5d02d1e14067d49929178b664422aa3859b613cffa84d619a9db3bcab1232f4324625827b7fbcc5e761555bad9ea82b8f99958649a30d5cb86acd6911a5bd8dab46e9dc5be945ebf97e4a3a92b349aa89bb6ba72d2e61926f5934920df5cfac0751129e2cb89421c3310fce20d202aedbb05b21c6c48b61227da952088dda6db1e629673a09d32aa131302a85812324a4f1e698058b8ee063f4e3982d70cc8fe31cb399a5bb606ab00761668339c279656140dac23d78c69f1f273ed709e919dc8286c435c7db7a&amp;key=8871839b5ff180ccd57b79609b6818d3&amp;refer=http://vietnamnet.vn/" target="_blank" title="Đồng hồ nữ Casio SHE-4023DP.">Đồng hồ nữ Casio SHE-4023DP.</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?token=43e15cccd780bed74657e0cc2f731578455769f10975cdf00615d603fc6942843ba239d39d732fd8526e4a060cdd7e49d1562a1ea9299f9ae5d02d1e14067d49929178b664422aa3859b613cffa84d619a9db3bcab1232f4324625827b7fbcc5e761555bad9ea82b8f99958649a30d5cb86acd6911a5bd8dab46e9dc5be945ebf97e4a3a92b349aa89bb6ba72d2e61926f5934920df5cfac0751129e2cb89421c3310fce20d202aedbb05b21c6c48b61227da952088dda6db1e629673a09d32aa131302a85812324a4f1e698058b8ee063f4e3982d70cc8fe31cb399a5bb606ab00761668339c279656140dac23d78c69f1f273ed709e919dc8286c435c7db7a&amp;key=8871839b5ff180ccd57b79609b6818d3&amp;refer=http://vietnamnet.vn/" target="_blank" title="dongho.vn">dongho.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?token=43e15cccd780bed74657e0cc2f731578455769f10975cdf00615d603fc6942843ba239d39d732fd8526e4a060cdd7e49d1562a1ea9299f9ae5d02d1e14067d49929178b664422aa3859b613cffa84d619a9db3bcab1232f4324625827b7fbcc5e761555bad9ea82b8f99958649a30d5cb86acd6911a5bd8dab46e9dc5be945ebf97e4a3a92b349aa89bb6ba72d2e61926f5934920df5cfac0751129e2cb89421c3310fce20d202aedbb05b21c6c48b61227da952088dda6db1e629673a09d32aa131302a85812324a4f1e698058b8ee063f4e3982d70cc8fe31cb399a5bb606ab00761668339c279656140dac23d78c69f1f273ed709e919dc8286c435c7db7a&amp;key=8871839b5ff180ccd57b79609b6818d3&amp;refer=http://vietnamnet.vn/" target="_blank" title="Đồng hồ nữ Casio SHE-4023DP."><img hspace="0" vspace="0" border="0" align="left" style="width:90px;height:90px;" src="http://ads.link.vn/adv-res/image/94.jpg" alt="Đồng hồ nữ Casio SHE-4023DP."></a> <div class="price"><a href="http://ads.link.vn/ads_tracker.link?token=43e15cccd780bed74657e0cc2f731578455769f10975cdf00615d603fc6942843ba239d39d732fd8526e4a060cdd7e49d1562a1ea9299f9ae5d02d1e14067d49929178b664422aa3859b613cffa84d619a9db3bcab1232f4324625827b7fbcc5e761555bad9ea82b8f99958649a30d5cb86acd6911a5bd8dab46e9dc5be945ebf97e4a3a92b349aa89bb6ba72d2e61926f5934920df5cfac0751129e2cb89421c3310fce20d202aedbb05b21c6c48b61227da952088dda6db1e629673a09d32aa131302a85812324a4f1e698058b8ee063f4e3982d70cc8fe31cb399a5bb606ab00761668339c279656140dac23d78c69f1f273ed709e919dc8286c435c7db7a&amp;key=8871839b5ff180ccd57b79609b6818d3&amp;refer=http://vietnamnet.vn/" target="_blank" title="Đồng hồ nữ Casio SHE-4023DP."><span style="font-family:arial; font-size:10pt"><span style="font-size:10pt">Quà tặng dành cho bạn gài nhân ngày 8/3 giá chỉ </span></span><span style="color:#FF0000"><strong>990.000đ</strong></span>.</a> </div></div></div> 
                                    <div class="ssvzBorder"><span></span></div> 
                                    <div class="adv_items"> <div class="ssvzTitle"><a href="http://ads.link.vn/ads_tracker.link?token=303635a4ab32843001560f67892c64cee044e98d4b4aaba321566923f4292b3645e819152e5991feae835713ff2d189bc7fc21678efc2366a1296da738e9f18904a0f020b9366437faaa49b9b499e76df1b86c823a28c97ffaece63ea8ecd08c588202764c5093b1a6c41434f6d90cea67365676c0a9c52bc2f865a9af82e77cccc69a6777824386d4a8a2659289a7cf02c5abe0187dff51fe4a737f4caeae3e8c4c0c0d3a99b7d91a9f816a2aa736639bb3bed6f2fc950b0bf7ee3a295ee0a61a2a022a009f7d014e07a5a87ead91d0b8052f772c6d2a0ed424bf892fe9f78f894c5dc5d255652fe5baa258721bbf9fb01769dc837f747b3a8f28f5ebbb9274&amp;key=7aec9bdb883c6dd6036faa082db7bda9&amp;refer=http://vietnamnet.vn/" target="_blank" title="Yên tâm với khóa báo động đa năng 325">Yên tâm với khóa báo động đa năng 325</a></div><div class="itemmc"><a href="http://ads.link.vn/ads_tracker.link?token=303635a4ab32843001560f67892c64cee044e98d4b4aaba321566923f4292b3645e819152e5991feae835713ff2d189bc7fc21678efc2366a1296da738e9f18904a0f020b9366437faaa49b9b499e76df1b86c823a28c97ffaece63ea8ecd08c588202764c5093b1a6c41434f6d90cea67365676c0a9c52bc2f865a9af82e77cccc69a6777824386d4a8a2659289a7cf02c5abe0187dff51fe4a737f4caeae3e8c4c0c0d3a99b7d91a9f816a2aa736639bb3bed6f2fc950b0bf7ee3a295ee0a61a2a022a009f7d014e07a5a87ead91d0b8052f772c6d2a0ed424bf892fe9f78f894c5dc5d255652fe5baa258721bbf9fb01769dc837f747b3a8f28f5ebbb9274&amp;key=7aec9bdb883c6dd6036faa082db7bda9&amp;refer=http://vietnamnet.vn/" target="_blank" title="hot.vn">hot.vn</a></div><div class="ssvzimage"><a href="http://ads.link.vn/ads_tracker.link?token=303635a4ab32843001560f67892c64cee044e98d4b4aaba321566923f4292b3645e819152e5991feae835713ff2d189bc7fc21678efc2366a1296da738e9f18904a0f020b9366437faaa49b9b499e76df1b86c823a28c97ffaece63ea8ecd08c588202764c5093b1a6c41434f6d90cea67365676c0a9c52bc2f865a9af82e77cccc69a6777824386d4a8a2659289a7cf02c5abe0187dff51fe4a737f4caeae3e8c4c0c0d3a99b7d91a9f816a2aa736639bb3bed6f2fc950b0bf7ee3a295ee0a61a2a022a009f7d014e07a5a87ead91d0b8052f772c6d2a0ed424bf892fe9f78f894c5dc5d255652fe5baa258721bbf9fb01769dc837f747b3a8f28f5ebbb9274&amp;key=7aec9bdb883c6dd6036faa082db7bda9&amp;refer=http://vietnamnet.vn/" target="_blank" title="Yên tâm với khóa báo động đa năng 325"><img hspace="0" vspace="0" border="0" align="left" style="width:90px;height:90px;" src="http://ads.link.vn/adv-res/image/61.jpg" alt="Yên tâm với khóa báo động đa năng 325"></a> <div class="price"><a href="http://ads.link.vn/ads_tracker.link?token=303635a4ab32843001560f67892c64cee044e98d4b4aaba321566923f4292b3645e819152e5991feae835713ff2d189bc7fc21678efc2366a1296da738e9f18904a0f020b9366437faaa49b9b499e76df1b86c823a28c97ffaece63ea8ecd08c588202764c5093b1a6c41434f6d90cea67365676c0a9c52bc2f865a9af82e77cccc69a6777824386d4a8a2659289a7cf02c5abe0187dff51fe4a737f4caeae3e8c4c0c0d3a99b7d91a9f816a2aa736639bb3bed6f2fc950b0bf7ee3a295ee0a61a2a022a009f7d014e07a5a87ead91d0b8052f772c6d2a0ed424bf892fe9f78f894c5dc5d255652fe5baa258721bbf9fb01769dc837f747b3a8f28f5ebbb9274&amp;key=7aec9bdb883c6dd6036faa082db7bda9&amp;refer=http://vietnamnet.vn/" target="_blank" title="Yên tâm với khóa báo động đa năng 325">&nbsp;Khóa báo động tiện lợi với nhiều tác dụng giúp bạn yên tâm chỉ với giá <span style="color:#FF0000"><strong>140.000đ.</strong></span></a> </div></div></div>
                                </div>
                            </div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function LinkslideIt(stp) {
                var es = document.getElementById("linkvn_slide28Holder");
                var ml = parseInt(es.style.marginLeft);
                if (stp == null || stp == 'undefined') {
                    stp = -300;
                }
                if (stp < 0) {
                    if (ml > stp) {
                        es.style.marginLeft = ml - 1 + "px";
                        if (ml - 1 == stp) {
                            return 0;
                        } else {
                            return  1;
                        }
                    }
                } else {
                    if (ml < stp) {
                        es.style.marginLeft = ml + 1 + "px";
                        if (ml + 1 == stp) {
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
                    rtl = LinkslideIt(-300);
                    if (tmp == rtl) {
                        setTimeout(function() {
                            linkvn_initSlide(rtl);
                        }, 100);
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
                        }, 100);
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
