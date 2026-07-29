<%@page import="gk.adv.linnk.vn.cache.BuildCache"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                padding: 0;
                outline: medium;
                outline-color: -moz-use-text-color;
                outline-style: none;
                outline-width: medium;
                text-align: left;
                text-shadow: none;
                width: auto;
            }
            .linkvn-zone a, .linkvn-zone a:hover, .linkvn-zone a:visited, .linkvn-zone a:active {
                text-decoration: none !important;
            }
            .linkvn-zone a img {
                border: 0 none;
                border-image: none 1 stretch;
            }

            .linkvn-zone span {
                display: block;
            }
            .linkvn-zone.linkvn-zone-metro {
                border: 1px solid #DCDCDC;
                background: #FFFFFF none repeat 0;
                border-image: none stretch 100% ;
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
                background: url("<%=BuildCache.DOMAIN %>/resource/images/ads_logo_tiny.png") no-repeat scroll;
                display: block;
                height: 17px;
                position: absolute;
                width: 19px !important;
                z-index: 1000;
            }
            .linkvn-zone.linkvn-zone-metro .header .logo abbr {
                background-color: #FFFFFF;
                background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAAKCAYAAADhNJ24AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAbBJREFUeNrEVe1twjAQNRH/mw3qTlA6AWECYALoBJAJokpt/9JOkDIB6QTABMAEpBt4g9YnPUtPJzu/UnHSyfHXu693zuD17d1Acq8jr2evzsSl8HqgudyxuNOXBD8c4bId7QOLxdiGhYw2l15rjCnZq7k4sukxOLF99Vp53cEfbafquL+AmliAsjHXB6hyeQewhRNcAd6ziTuFmksQE+gD1nTCX1S1U76JDzZTpT0TDQLAyesUGTUJoA0yW4Nae8LcqICZLVOcFTszr1+K7hOsxVg0gm9jjDaC74ZYWFFgLap4gFFZLwFwjQToUHmDfTHcAOMTmHN1pwKmoyrddfR+TFaopiTgSAGugfPEAc4QWEXZEQfuqWHbhKFWfecITCr+g2Bj9HxUrLGoqG4Nm7At61t8h0dnTFjih8sA4oj7gRaS1QvRtejov1y9wuElloR9J5JyRAUuONvAxlr1pOlIbKA+P44HJLgOj8yCMhFkCwo0cPaEeUp26I2SaLZF0LEKzuH8HrgtJVn8+YXNJtKDQUrqYavOfQS6Dug/2LcsQZlnc0MZ/mNwFSpyU/kTYAB6Y3brWms9TQAAAABJRU5ErkJggg==");
                background-position: left 2px;
                background-repeat: no-repeat;
                border-bottom-left-radius: 0;
                border-bottom-right-radius: 2px;
                border-top-left-radius: 0;
                border-top-right-radius: 0;
                display: block;
                height: 14px;
                margin-left: -60px;
                padding-right: 2px;
                text-indent: -9999px;
                transition-delay: 0s;
                transition-duration: 300ms;
                transition-property: margin;
                transition-timing-function: linear;
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
            .linkvn-zone.linkvn-zone-metro .banner {
                display: block;
                float: left;
                height: 140px;
                margin-bottom: 4.5px;
                margin-left: 4.5px;
                margin-right: 4.5px;
                margin-top: 4.5px;
                overflow: hidden;
                position: relative;
                width: 140px;
            }
            .linkvn-zone.linkvn-zone-metro .banner:before, .linkvn-zone.linkvn-zone-metro .banner:after {
                content: "";
                display: table;
            }
            .linkvn-zone.linkvn-zone-metro .banner:after {
                clear: both;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front {
                position: relative;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front img {
                float: left;
                height: 110px;
                padding-bottom: 5px;
                padding-left: 15px;
                padding-right: 15px;
                padding-top: 5px;
                width: 110px;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span {
                background: #00ccff none repeat scroll;
                height: 140px;
                left: 0;
                padding: 6px 10px 6px 10px;
                position: absolute;
                top: 120px;
                transition-delay: 0s;
                transition-duration: 350ms;
                transition-property: top;
                transition-timing-function: ease-in;
                filter: alpha(opacity=90);
                opacity: 0.9;
                z-index: 999;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span strong, .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span em {
                color: #3E3E3F;
                display: block;
            }

            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span em {
                background: rgba(0, 0, 0, 0) none repeat scroll;
                color: #797878;
                font-size: 11px;
                font-weight: 500;
                line-height: 21px;
                overflow: hidden;
                width: 120px;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span price {
                background: rgba(0, 0, 0, 0) none repeat;
                color: #E91E23;
                font-weight: bold; 
                font-size: 14px;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span del {
                background: rgba(0, 0, 0, 0) none repeat 0 0;
                text-align: left !important;
                color: black;
                font-size: 11px;
                font-weight: 500;
            }

            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front span .discount {
                background-attachment: scroll;
                background-clip: border-box;
                background-color: rgba(255, 0, 0, 0.7);
                background-image: none;
                background-origin: padding-box;
                background-position: 0 0;
                background-repeat: repeat;
                background-size: auto auto;
                float: right;
                height: 37px;
                width: 37px;
            }
            .linkvn-zone.linkvn-zone-metro .banner .banner-face-front:hover span {
                background-attachment: scroll;
                background-clip: border-box;
                background-color: #81dafc;
                background-image: none;
                background-origin: padding-box;
                background-position: 0 0;
                background-repeat: repeat;
                background-size: auto auto;
                top: 0 !important;
                z-index: 1002;
            }
            .linkvn-zone.linkvn-zone-metro .banner em {
                text-align: right !important;
            }
            .linkvn-zone.linkvn-zone-metro .banner.banner-widget price
            {
                text-align: right !important;
            }
            .linkvn-zone.linkvn-zone-metro .banner.banner-widget price {
                line-height: 12px;
            }
            .linkvn-zone.linkvn-zone-metro .banner.banner-widget strong {
                height: 60px;
                margin-bottom: 10px;
                margin-left: 0;
                margin-right: 0;
                margin-top: 10px;
                text-align: right !important;
            }
            .linkvn-zone.linkvn-zone-metro .banner.banner-context .banner-face-front span strong {
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
    </head>
    <body>
        <div style="margin:0 0 0px 0; padding:0;width:300px;float:left">
            <div data-zone-id="473" class="link_ads_zone" data-rendered="true">
                <div class="linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4">
                    <a target="_blank" href="http://hot.vn" class="header">
                        <span class="logo"><i></i><abbr>ads by link.vn</abbr></span>
                    </a>
                    <div class="block_ads">
                        <a data-banner-id="21927" target="_blank" href="#" class="banner-widget banner banner-first-row">
                            <span class="banner-face banner-face-front">
                                <img alt="Đầm dạo phố ren." src="http://st.eclick.vn/d5/uploads/thumb/2013/09/06/d90dd503d30cf48166410fec97a1dc5d.png">
                                <span class="banner-content"><del>1.200.000</del><price>1000.000</price>
                                    <!--<ins>645.000</ins>-->
                                    <strong>Đầm dạo phố ren.</strong><em>sendo.vn</em></span>
                            </span>
                        </a>
                        <a data-banner-id="21926" target="_blank" href="#" class="banner-widget banner banner-first-row">
                            <span class="banner-face banner-face-front">
                                <img alt="[FREE SHIP] Áo thun polo công sở" src="http://st.eclick.vn/d5/uploads/thumb/2013/09/06/3b7cbce1f5ca0b31a90f989880588846.png">
                                <span class="banner-content"><price>1000.000</price>&nbsp;&nbsp;<del>1.200.000</del>
                                    <!--<ins>260.000</ins>-->
                                    <strong>[FREE SHIP] Áo thun polo công sở</strong><em>sendo.vn</em></span>
                            </span>
                        </a>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
