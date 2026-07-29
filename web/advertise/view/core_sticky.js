var strDomain = document.domain, checkStickyLoad = !1, checkStickyCount = !1, random1 = 0, return_link = "", admTimeSticky = 0, admChrome_chk_match = (window.navigator.userAgent + "").match(/chrome/i), admChrome_chk = admChrome_chk_match ? !0 : !1, t_showFooterPopup, t_showFooterPopup1, _admStickyHeight = 0, _admStickyFooter = 0, admStickyWide = !1, admchkStickyWide = !1;
if ("undefined" == typeof admStickyHide)
    var admStickyHide = !1;
if (typeof(_admStickyFooterEnd) == 'undefined') {
    var _admStickyFooterEnd = 0;
}
function getElementsByPrefix(a, c) {
    var e = [];
    if ("undefined" != typeof c.firstChild)
        for (var d = c.firstChild; null != d; )
            "undefined" != typeof d.firstChild && (e = e.concat(this.getElementsByPrefix(a, d))), "undefined" != typeof d.id && d.id.match(RegExp("^" + a + ".*")) && e.push(d), d = d.nextSibling;
    return e
}
var windowPrototype = {wdHeight: function() {
        var a;
        "number" == typeof window.innerWidth ? a = window.innerHeight : document.documentElement && document.documentElement.clientHeight ? a = document.documentElement.clientHeight : document.body && document.body.clientHeight && (a = document.body.clientHeight);
        return a
    }, wdWidth: function() {
        var a;
        "number" == typeof window.innerWidth ? a = window.innerWidth : document.documentElement && document.documentElement.clientWidth ? a = document.documentElement.clientWidth : document.body && document.body.clientWidth &&
                (a = document.body.clientWidth);
        return a
    }}, Browser = {Version: function() {
        var a = 999;
        -1 != navigator.appVersion.indexOf("MSIE") && (a = parseFloat(navigator.appVersion.split("MSIE")[1]));
        return a
    }}, browserVersion = Browser.Version(), admwdHeight = parseInt(windowPrototype.wdHeight());
function getScrollTop() {
    var a = document.body.scrollTop;
    0 == a && (a = window.pageYOffset ? window.pageYOffset : document.body.parentElement ? document.body.parentElement.scrollTop : 0);
    return a
}
function getElementTop(a) {
    if (document.getElementById)
        var c = document.getElementById(a);
    else
        document.all && (c = document.all[a]);
    if (null != c) {
        yPos = c.offsetTop;
        for (tempEl = c.offsetParent; null != tempEl; )
            yPos += tempEl.offsetTop, tempEl = tempEl.offsetParent;
        return yPos
    }
    return 0
}
function getElementLeft(a) {
    var c;
    document.getElementById ? c = document.getElementById(a) : document.all && (c = document.all[a]);
    if (null == c || "undefined" == typeof c)
        return 0;
    a = c.offsetLeft;
    for (c = c.offsetParent; null != c; )
        a += c.offsetLeft, c = c.offsetParent;
    return a
}
function getElementWidth(a) {
    return document.getElementById(a).clientWidth
}
function getElementHeight(a) {
    return document.getElementById(a).clientHeight
}
var stickyCheck = 0;
function stickyLoaded(a, c, e) {
    advScroll(a, c, e)
}
var admBox2Status = 0;
function advScroll(a, c, e) {
    _admStickyFooterEnd = e;
    "Sticky" == a && 0 != _admStickyHeight && (c = _admStickyHeight);
    var d = "advZone" + a, b = document, f = Math.max(Math.max(b.body.scrollHeight, b.documentElement.scrollHeight), Math.max(b.body.offsetHeight, b.documentElement.offsetHeight), Math.max(b.body.clientHeight, b.documentElement.clientHeight)), j = getScrollTop(), k = getElementTop(d + "Top"), b = b.getElementById(d), m = getElementLeft(d + "Top"), l = 0, h = navigator.userAgent, n = getElementHeight(d);
    n > c && (c = n);
    0 == admBox2Status && -1 == h.indexOf("MSIE") && (h = b.innerHTML,
            -1 != h.indexOf("display:none") && (b.innerHTML = h.replace("display:none", "")), admBox2Status = 1);
    var g;
    if ("string" == typeof e)
        g = f - getElementTop(e);
    else if ("object" == typeof e) {
        h = 0;
        for (n = e.length; h < n; h++)
            if ("string" == typeof e[h]) {
                if (document.getElementById(e[h])) {
                    var p = getElementTop(e[h]);
                    if (0 != p) {
                        g = f - p;
                        break
                    }
                }
            } else {
                g = e;
                break
            }
    } else
        g = e;
    50 > admTimeSticky && admTimeSticky++;
    h = Math.floor((f - (k + g)) / 2);
    880 <= admwdHeight && (admStickyWide && !admchkStickyWide && 1200 <= h) && (admchkStickyWide = !0);
    _admFSticky = g;
    if (f - k > c + g && 110 <=
            k)
        if (b.style.display = "block", a = getElementWidth(d + "Top"), e = getElementWidth(d), h = -1 != strDomain.indexOf("kenh14.vn") ? 1.08 : 1.2, f - k - g > h * c) {
            a > e && (l = Math.round((a - e) / 2));
            if (f - j > c + g)
                if (7 > browserVersion)
                    b.style.position = "absolute", b.style.left = m + l + "px", b.style.top = j + "px", a = getElementTop(d) - j, 0 != a && (b.style.top = j - a + "px");
                else if ((-1 != strDomain.indexOf("tratu.vn") || -1 != strDomain.indexOf("socvui.com") || -1 != strDomain.indexOf("kenh14.vn")) && 9 >= browserVersion && 8 <= browserVersion)
                    b.style.position = "absolute", b.style.left =
                            m + l + "px", b.style.top = j + "px", a = getElementTop(d) - j, 0 != a && (b.style.top = j - a + "px");
                else {
                    c = document.domain;
                    f = 0;
                    if (-1 != c.indexOf("muare.vn") || -1 != c.indexOf("muare.todo.vn"))
                        f = 35;
                    b.style.left = m + l + "px";
                    b.style.top = f + "px";
                    b.style.bottom = "auto";
                    b.style.position = "fixed"
                }
            else
                admChrome_chk ? -1 != document.domain.indexOf("afamily.vn") || -1 != document.domain.indexOf("giacaphe.com") || -1 != document.domain.indexOf("quantri.com.vn") ? (b.style.position = "absolute", b.style.left = m + l + "px", a = f - (j + windowPrototype.wdHeight()), b.style.top =
                        f - (c + g) - 300 + "px") : (b.style.position = "fixed", b.style.left = m + l + "px", a = f - (j + windowPrototype.wdHeight()), b.style.bottom = g - a + "px", b.style.top = "auto") : (b.style.position = "absolute", b.style.left = m + l + "px", b.style.top = -1 != document.domain.indexOf("afamily.vn") || -1 != document.domain.indexOf("giacaphe.com") || -1 != document.domain.indexOf("quantri.com.vn") ? f - (c + g) - 300 + "px" : f - (c + g) + "px"), a = f - getElementTop(d), c + g > a && (b.style.top = f - 2 * (c + g) + a + "px");
            j < k && (b.style.top = k + "px", b.style.position = "");
            d = getElementLeft(d) - (l +
                    m);
            0 != d && (7 == browserVersion ? 0 < d && (b.style.position = "", b.style.top = "0px", b.style.left = "0px") : b.style.left = l + m - d + "px")
        } else
            b.style.top = k + "px", b.style.position = "";
    else
        f - k - g > 0.85 * c ? b.style.display = "block" : 272 < f - k - g && -1 == a.indexOf("No") ? (a += "No", d = document.getElementById("advZone" + a), c = 320, d && ("Sticky" == a ? clearTimeout(t_showFooterPopup) : clearTimeout(t_showFooterPopup1), advScroll(a, c, e), b.style.display = "none")) : !1 === admStickyHide ? (b.style.display = "block", b.style.position = "") : b.style.display = "none"
}
;