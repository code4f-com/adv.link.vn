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
                outline: medium none;
                padding: 0;
                text-align: right;
                text-shadow: none;
                width: auto;
            }
            .linkvn-zone a, .linkvn-zone a:hover, .linkvn-zone a:visited, .linkvn-zone a:active {
                text-decoration: none !important;
            }
            .linkvn-zone a img {
                border-style: none;
                border-width: 0;
            }
            .linkvn-zone span {
                display: block;
            }
            .linkvn-zone.linkvn-zone-metro {
                background: none repeat scroll 0 center #FFFFFF;
                border: 1px solid #DCDCDC;
                overflow: hidden;
            }
            .linkvn-zone.linkvn-zone-metro * {
                font-family: "tahoma","Segoe UI","open sans","arial","sans-serif";
                font-size: 10px;
                font-size-adjust: none;
                font-stretch: normal;
                font-style: normal;
                font-variant: normal;
                font-weight: bold;
                line-height: 12px;
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
                height: 124px;
                margin: 3.5px 0 3.5px 0;
                overflow: hidden;
                position: relative;
                width: 124px;
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
                padding: 4px 7px;
                height: 110px;
                width: 110px;
            }
            .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span {
                background: none repeat scroll 0 0 #00CCFF;
                height: 124px;
                left: 0;
                filter: alpha(opacity=90);
                opacity: 0.9;
                padding: 3px 6px;
                position: absolute;
                top: 104px;
                transition: top 350ms ease-in 0s;
                z-index: 999;
            }
            .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span strong, .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span em {
                color: #3E3E3F;
                display: block;
            }
            .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span em {
                background: none repeat scroll 0 0 transparent;
                color: #797878;
                font-size: 11px;
                font-weight: 500;
                line-height: 21px;
                overflow: hidden;
                width: 110px;
            }
            .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front span price {
                background: none repeat scroll 0 0 transparent;
                color: #E91E23;
                font-size: 14px;
                font-weight: bold;
            }
            .linkvn-zone.linkvn-zone-metro .one_block .banner-face-front:hover span {
                background: none repeat scroll 0 0 #81DAFC;
                top: 0 !important;
                z-index: 1002;
            }
            .linkvn-zone.linkvn-zone-metro .one_block domain {
                font-weight: bold;
                text-align: right !important;
            }
            .linkvn-zone.linkvn-zone-metro .one_block.banner-widget price {
                text-align: right !important;
                margin-right: 7px;
            }
            .linkvn-zone.linkvn-zone-metro .one_block.banner-widget price {
                line-height: 12px;
            }
            .linkvn-zone.linkvn-zone-metro .one_block.banner-widget strong {
                height: 50px;
                margin: 5px 0;
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
    </head>
    <body>
        <div style="margin:0; padding:0;width:124px;float:left"> 
            <div data-rendered="true" class="link_ads_zone" data-zone-id="473"> 
                <div class="linkvn-zone vertical linkvn-zone-metro linkvn-zone-blue metro-2-4"> 
                    <div class="banners"> 
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=2cf1f22a9b047d00968ec0638b55b8b574f34bbd2096e818281732b25948ab32f85ab53efde6770f0f2b33e5585fe68b8fbe06dd9323af185ab1dfac448d7fcc009031020f4a91ee9fe9d55a18ed03de0cca0c0bb30d8839ccfed623ffa78bb4dc4f72a7514c064a7c8d6d7dd36b197e8f22489ed0cb31f749b6858a88b517a0&amp;key=9bf45f260038e9e7be9a25b1a2f00915&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="102"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/102.jpg" alt=""> 
                                <span class="banner-content"><price>205,000</price><strong>Quần bầu kaki HC 532</strong><domain>mebe.vn</domain></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=1b151020095f2bdb1ff952fcebc5295adbd098a77e635c760fd69dfcc31a3b31c5db699a9ad97bca10b62193da7a01e93fdf7f3e7a276bc350c101b132f888bc0876a2f3bddd7547cd1ce0aecebbc1f3253214be2e622528b049171af53cf8b225a3c30f9682f31e3b95437c89c1586f2609780b6c545fbe589a2c2dd9edd85b&amp;key=f56187286406ae94daf9fbc96b416e5c&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="85"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/85.jpg" alt=""> 
                                <span class="banner-content"><price>199,000</price><strong>Váy yếm bầu thời trang HC-625</strong><domain>mebe.vn</domain></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=947865bfda9f6bb133da7fc2450525bd33ec82eb1248bc8650e477b24adf02718365367b833849477b6c08bf4d6e5cc27cbd5efcb1a36297cf9df3c0847838a604591a21b7c368f97f1093039ac5463756035a828ead7ccf647eed739b3a26e7c0a90a904f91b6a606a7d37b6d6d390c19113e9d2926b3514ad88621c33b5090&amp;key=b0dfae4c5836b9b205f9cb39e7d0a9e7&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="103"> 
                            <span class="banner-face banner-face-front"> 
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/103.jpg" alt=""> 
                                <span class="banner-content"><price>269,000</price><strong>Quần yếm bầu denim HC 517.</strong><domain>mebe.vn</domain></span> 
                            </span> 
                        </a> 
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=0baaf4e28701488744e1951f5cd23f6ee69a0c41ec2e785094ea51ea7cda368ee2591082fc192b4983fc4581e1a768be2c3fb840805744f6f6d9cce9c03dc69f3611c6b14dc4aeea0ff360390416924910f50ddd8c29c630e6308f3269ef6fc36a525d9f47fd752e9192dfcd5020d7b3264dfd097792c5a2b98453be72b2918d&amp;key=879216f9647f6449719747d2fbf9d8da&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="113">
                            <span class="banner-face banner-face-front"> 
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/113.jpg" alt="">
                                <span class="banner-content"><price>175,000</price><strong>Áo cotton nữ kẻ ngang PT 8805</strong><domain>hot.vn</domain></span>
                            </span>
                        </a>
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=6efcc63e82336f9dfa0d90316a30eb96d683d11e785e40138803a6ceac49db8718839ea71efebe9377f4b1216535402f1fcaaf22f6c46f584ae65bf6b72f14f44090ed13c2352364f5a153a8ebc1780ae3187fb4e5ad98e5bb9e5af0e80e63908438c5b1d58afda708b705801a11625be018d1e58b72645784c5b0133d7589bb&amp;key=68d8554b7444e8302d2811d52141b9ff&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="110"> <span class="banner-face banner-face-front"> <img src="<%=BuildCache.DOMAIN %>/adv-res/image/110.jpg" alt=""> 
                                <span class="banner-content"><price></price><strong>Áo vest ren thời trang QJ 388</strong><domain>hot.vn</domain></span> </span> </a>
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=6369ac46f7fda77a5bf646a118b2e282c2fbb9d7c0e1340c3520372bbeed8c77e3ad0efd9debba0ba661d7615b600c3cc517559701b59c33b34a5026884e37a3ca0db76f001e66d6c6b1d3dc961bda012200f5b039c45cf303cd06a02d4753af37ffa16443d20d731529901323300ca874542b984eb11bd2ce1a3365b56cdccc&amp;key=0dcdff33a90ec490147f8eb9924bcde1&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="78">
                            <span class="banner-face banner-face-front">
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/78.jpg" alt=""> 
                                <span class="banner-content"><price>180,000</price><strong>Áo sơ mi voan cổ tim thanh lịch WH 6007.</strong><domain>hot.vn</domain></span> </span> </a>
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=4972ad8ede922f04c099c1eaf17eb5e2b32989a7881049b05e7ba89218b98dfb97c48792618aaa1a90fbdf867a4db9e238e070865e35ac934cf1e0f3f7b853b6693664ab33185cbe2ad170ce60c28b7b7caab0fee85ee1e368fb9ba82c680873b42ad1973a550ec5c2f25e06b0c0f491d224ec6921bfd82116a5f56dfc30b8c3&amp;key=6cb54169fdc5d2f4e2d1c6bc917d5f4f&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="105">
                            <span class="banner-face banner-face-front"> 
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/105.jpg" alt=""> 
                                <span class="banner-content"><price>179,000</price><strong>Bộ đồ bầu MB 559-9</strong><domain>mebe.vn</domain></span> </span>
                        </a>
                        <a class="banner-widget one_block banner-first-row" href="<%=BuildCache.DOMAIN %>/ads_tracker.link?token=ae1fe075dc4f8854564fa4252a376d96d7941be904a934e0bc4654647587bbff1286181e3c2fac9b43d265535f813eb32848619513226d6263b58564d07c42b93e6716ebc46f4275f32ed86d2b1768191e531e2067858541f69bd7f12f6d0c741cec9fe7413ba8a51785c47e5caf79ae86b6f41053a897922b9ee350708aa0b9&amp;key=a0e7358e47e9bf2c0af3a50540a8f893&amp;refer=http://link.vn/groupAdv/view/view-ads-25.html" target="_blank" data-banner-id="90">
                            <span class="banner-face banner-face-front">
                                <img src="<%=BuildCache.DOMAIN %>/adv-res/image/90.jpg" alt=""> 
                                <span class="banner-content"><price>285,000</price><strong>Giày da Income 2205</strong><domain>giay.vn</domain></span> 
                            </span> 
                        </a>  
                    </div> 
                </div> 
            </div> 
        </div>
    </body>
</html>
