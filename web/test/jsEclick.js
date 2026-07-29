(function() {
    function ja(b) {
        var a = document.createElement("link");
        a.setAttribute("rel", "stylesheet");
        a.setAttribute("type", "text/css");
        a.setAttribute("href", b);
        document.getElementsByTagName("head")[0].appendChild(a)
    }
    function T(b, a) {
        var c = !1, e = !0, d = b.document, f = d.documentElement, g = d.addEventListener ? "addEventListener" : "attachEvent", h = d.addEventListener ? "removeEventListener" : "detachEvent", k = d.addEventListener ? "" : "on", l = function(e) {
            if ("readystatechange" != e.type || "complete" == d.readyState)
                ("load" == e.type ?
                        b : d)[h](k + e.type, l, !1), !c && (c = !0) && a.call(b, e.type || e)
        }, q = function() {
            try {
                f.doScroll("left")
            } catch (a) {
                setTimeout(q, 50);
                return
            }
            l("poll")
        };
        if ("complete" == d.readyState)
            a.call(b, "lazy");
        else {
            if (d.createEventObject && f.doScroll) {
                try {
                    e = !b.frameElement
                } catch (n) {
                }
                e && q()
            }
            d[g](k + "DOMContentLoaded", l, !1);
            d[g](k + "readystatechange", l, !1);
            b[g](k + "load", l, !1)
        }
    }
    function N(b, a, c) {
        b.addEventListener ? b.addEventListener(a, c, !1) : b.attachEvent ? b.attachEvent("on" + a, c) : b["on" + a] = c
    }
    function ka() {
        var b = document.body, a = document.createElement("div");
        a.style.paddingLeft = a.style.width = "1px";
        b.appendChild(a);
        var c = 2 == a.offsetWidth;
        b.removeChild(a);
        return c
    }
    function $(b) {
        var a = document.body, c = document.documentElement;
        b = b.getBoundingClientRect();
        var e = aa();
        return{top: b.top + e.top - (c.clientTop || a.clientTop || 0), left: b.left + e.left - (c.clientLeft || a.clientLeft || 0)}
    }
    function aa() {
        return{left: void 0 !== window.pageXOffset ? window.pageXOffset : (document.documentElement || document.body.parentNode || document.body).scrollLeft, top: void 0 !== window.pageYOffset ? window.pageYOffset :
                    (document.documentElement || document.body.parentNode || document.body).scrollTop}
    }
    function F(b, a) {
        0 <= (" " + b.className + " ").indexOf(" " + a + " ") || (b.className += " " + a)
    }
    function H(b, a) {
        b.className = (" " + b.className + " ").split(" " + a + " ").join(" ").replace(/\s{2,}/g, " ")
    }
    function C(b) {
    }
    function la(b) {
        var a = new Date(b.getFullYear(), 0, 1);
        return Math.ceil(((b - a) / 864E5 + a.getDay() + 1) / 7)
    }
    function I(b, a) {
        var c = new Image(1, 1);
        c.onload = a;
        c.src = b;
        C("log sent", b)
    }
    window.Modernizr = function(b, a, c) {
        function e(a, b) {
            for (var d in a) {
                var e =
                        a[d];
                if (!~("" + e).indexOf("-") && k[e] !== c)
                    return"pfx" == b ? e : !0
            }
            return!1
        }
        function d(a, b, d) {
            var f = a.charAt(0).toUpperCase() + a.slice(1), g = (a + " " + l.join(f + " ") + f).split(" ");
            if ("string" === typeof b || "undefined" === typeof b)
                b = e(g, b);
            else
                a:{
                    g = (a + " " + q.join(f + " ") + f).split(" "), a = g;
                    for (var k in a)
                        if (f = b[a[k]], f !== c) {
                            b = !1 === d ? a[k] : "function" === typeof f ? f.bind(d || b) : f;
                            break a
                        }
                    b = !1
                }
            return b
        }
        var f = {}, g = a.documentElement, h = a.createElement("modernizr"), k = h.style, l = ["Webkit", "Moz", "O", "ms"], q = ["webkit", "moz", "o", "ms"],
                h = {};
        b = [];
        var n = b.slice, E, u = function(c, b, d, e) {
            var f, k, h, q, l = a.createElement("div"), n = a.body, t = n || a.createElement("body");
            if (parseInt(d, 10))
                for (; d--; )
                    h = a.createElement("div"), h.id = e ? e[d] : "modernizr" + (d + 1), l.appendChild(h);
            return f = ['&#173;<style id="smodernizr">', c, "</style>"].join(""), l.id = "modernizr", (n ? l : t).innerHTML += f, t.appendChild(l), n || (t.style.background = "", t.style.overflow = "hidden", q = g.style.overflow, g.style.overflow = "hidden", g.appendChild(t)), k = b(l, c), n ? l.parentNode.removeChild(l) : (t.parentNode.removeChild(t),
                    g.style.overflow = q), !!k
        }, z = {}.hasOwnProperty, s;
        "undefined" === typeof z || "undefined" === typeof z.call ? s = function(a, c) {
            return c in a && "undefined" === typeof a.constructor.prototype[c]
        } : s = function(a, c) {
            return z.call(a, c)
        };
        Function.prototype.bind || (Function.prototype.bind = function(a) {
            var c = this;
            if ("function" != typeof c)
                throw new TypeError;
            var b = n.call(arguments, 1), d = function() {
                if (this instanceof d) {
                    var e = function() {
                    };
                    e.prototype = c.prototype;
                    var e = new e, f = c.apply(e, b.concat(n.call(arguments)));
                    return Object(f) ===
                            f ? f : e
                }
                return c.apply(a, b.concat(n.call(arguments)))
            };
            return d
        });
        h.csstransforms = function() {
            return!!d("transform")
        };
        h.csstransforms3d = function() {
            var a = !!d("perspective");
            return a && "webkitPerspective"in g.style && u("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}", function(c, b) {
                a = 9 === c.offsetLeft && 3 === c.offsetHeight
            }), a
        };
        h.csstransitions = function() {
            return d("transition")
        };
        for (var p in h)
            s(h, p) && (E = p.toLowerCase(), f[E] = h[p](), b.push((f[E] ? "" : "no-") + E));
        f.addTest = function(a, b) {
            if ("object" == typeof a)
                for (var d in a)
                    s(a, d) && f.addTest(d, a[d]);
            else {
                a = a.toLowerCase();
                if (f[a] !== c)
                    return f;
                b = "function" == typeof b ? b() : b;
                g.className += " " + (b ? "" : "no-") + a;
                f[a] = b
            }
            return f
        };
        k.cssText = "";
        return h = null, function(a, c) {
            function b() {
                var a = n.elements;
                return"string" == typeof a ? a.split(" ") : a
            }
            function d(a) {
                var c = ba[a[J]];
                return c || (c = {}, y++, a[J] = y, ba[y] = c), c
            }
            function e(a, b, f) {
                b || (b = c);
                if (q)
                    return b.createElement(a);
                f || (f = d(b));
                var y;
                return f.cache[a] ? y = f.cache[a].cloneNode() :
                        l.test(a) ? y = (f.cache[a] = f.createElem(a)).cloneNode() : y = f.createElem(a), y.canHaveChildren && !h.test(a) ? f.frag.appendChild(y) : y
            }
            function f(a, c) {
                c.cache || (c.cache = {}, c.createElem = a.createElement, c.createFrag = a.createDocumentFragment, c.frag = c.createFrag());
                a.createElement = function(b) {
                    return n.shivMethods ? e(b, a, c) : c.createElem(b)
                };
                a.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + b().join().replace(/\w+/g, function(a) {
                    return c.createElem(a),
                            c.frag.createElement(a), 'c("' + a + '")'
                }) + ");return n}")(n, c.frag)
            }
            function g(a) {
                a || (a = c);
                var b = d(a);
                if (n.shivCSS && !t && !b.hasCSS) {
                    var e, y = a;
                    e = y.createElement("p");
                    y = y.getElementsByTagName("head")[0] || y.documentElement;
                    e = (e.innerHTML = "x<style>article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}</style>", y.insertBefore(e.lastChild, y.firstChild));
                    b.hasCSS = !!e
                }
                return q || f(a, b), a
            }
            var k = a.html5 || {}, h = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,
                    l = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i, t, J = "_html5shiv", y = 0, ba = {}, q;
            (function() {
                try {
                    var a = c.createElement("a");
                    a.innerHTML = "<xyz></xyz>";
                    t = "hidden"in a;
                    var b;
                    if (!(b = 1 == a.childNodes.length)) {
                        c.createElement("a");
                        var d = c.createDocumentFragment();
                        b = "undefined" == typeof d.cloneNode || "undefined" == typeof d.createDocumentFragment || "undefined" == typeof d.createElement
                    }
                    q = b
                } catch (e) {
                    q = t = !0
                }
            })();
            var n = {elements: k.elements || "abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",
                shivCSS: !1 !== k.shivCSS, supportsUnknownElements: q, shivMethods: !1 !== k.shivMethods, type: "default", shivDocument: g, createElement: e, createDocumentFragment: function(a, e) {
                    a || (a = c);
                    if (q)
                        return a.createDocumentFragment();
                    e = e || d(a);
                    for (var f = e.frag.cloneNode(), y = 0, g = b(), k = g.length; y < k; y++)
                        f.createElement(g[y]);
                    return f
                }};
            a.html5 = n;
            g(c)
        }(this, a), f._version = "2.6.2", f._prefixes = " -webkit- -moz- -o- -ms- ".split(" "), f._domPrefixes = q, f._cssomPrefixes = l, f.testProp = function(a) {
            return e([a])
        }, f.testAllProps = d, f.testStyles =
                u, g.className = g.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (" js " + b.join(" ")), f
    }(this, this.document);
    (function(b, a, c) {
        function e(a) {
            return"[object Function]" == z.call(a)
        }
        function d(a) {
            return"string" == typeof a
        }
        function f() {
        }
        function g(a) {
            return!a || "loaded" == a || "complete" == a || "uninitialized" == a
        }
        function h() {
            var a = s.shift();
            p = 1;
            a ? a.t ? E(function() {
                ("c" == a.t ? t.injectCss : t.injectJs)(a.s, 0, a.a, a.x, a.e, 1)
            }, 0) : (a(), h()) : p = 0
        }
        function k(c, b, d, e, f, k, q) {
            function x(a) {
                if (!n && g(l.readyState) && (D.r = n = 1, !p && h(),
                        l.onload = l.onreadystatechange = null, a)) {
                    "img" != c && E(function() {
                        r.removeChild(l)
                    }, 50);
                    for (var d in v[b])
                        v[b].hasOwnProperty(d) && v[b][d].onload()
                }
            }
            q = q || t.errorTimeout;
            var l = a.createElement(c), n = 0, m = 0, D = {t: d, s: b, e: f, a: k, x: q};
            1 === v[b] && (m = 1, v[b] = []);
            "object" == c ? l.data = b : (l.src = b, l.type = c);
            l.width = l.height = "0";
            l.onerror = l.onload = l.onreadystatechange = function() {
                x.call(this, m)
            };
            s.splice(e, 0, D);
            "img" != c && (m || 2 === v[b] ? (r.insertBefore(l, G ? null : u), E(x, q)) : v[b].push(l))
        }
        function l(a, c, b, e, f) {
            return p = 0, c = c || "j",
                    d(a) ? k("c" == c ? A : m, a, c, this.i++, b, e, f) : (s.splice(this.i++, 0, a), 1 == s.length && h()), this
        }
        function q() {
            var a = t;
            return a.loader = {load: l, i: 0}, a
        }
        var n = a.documentElement, E = b.setTimeout, u = a.getElementsByTagName("script")[0], z = {}.toString, s = [], p = 0, D = "MozAppearance"in n.style, G = D && !!a.createRange().compareNode, r = G ? n : u.parentNode, n = b.opera && "[object Opera]" == z.call(b.opera), n = !!a.attachEvent && !n, m = D ? "object" : n ? "script" : "img", A = n ? "script" : m, w = Array.isArray || function(a) {
            return"[object Array]" == z.call(a)
        }, U = [], v =
                {}, B = {timeout: function(a, c) {
                return c.length && (a.timeout = c[0]), a
            }}, C, t;
        t = function(a) {
            function b(a) {
                a = a.split("!");
                var c = U.length, d = a.pop(), e = a.length, d = {url: d, origUrl: d, prefixes: a}, f, g, y;
                for (g = 0; g < e; g++)
                    y = a[g].split("="), (f = B[y.shift()]) && (d = f(d, y));
                for (g = 0; g < c; g++)
                    d = U[g](d);
                return d
            }
            function g(a, d, f, k, t) {
                var h = b(a), l = h.autoCallback;
                h.url.split(".").pop().split("?").shift();
                h.bypass || (d && (d = e(d) ? d : d[a] || d[k] || d[a.split("/").pop().split("?")[0]]), h.instead ? h.instead(a, d, f, k, t) : (v[h.url] ? h.noexec = !0 :
                        v[h.url] = 1, f.load(h.url, h.forceCSS || !h.forceJS && "css" == h.url.split(".").pop().split("?").shift() ? "c" : c, h.noexec, h.attrs, h.timeout), (e(d) || e(l)) && f.load(function() {
                    q();
                    d && d(h.origUrl, t, k);
                    l && l(h.origUrl, t, k);
                    v[h.url] = 2
                })))
            }
            function k(a, c) {
                function b(a, f) {
                    if (a)
                        if (d(a))
                            f || (t = function() {
                                var a = [].slice.call(arguments);
                                l.apply(this, a);
                                q()
                            }), g(a, t, c, 0, y);
                        else {
                            if (Object(a) === a)
                                for (n in J = function() {
                                    var c = 0, b;
                                    for (b in a)
                                        a.hasOwnProperty(b) && c++;
                                    return c
                                }(), a)
                                    a.hasOwnProperty(n) && (!f && !--J && (e(t) ? t = function() {
                                        var a =
                                        [].slice.call(arguments);
                                        l.apply(this, a);
                                        q()
                                    } : t[n] = function(a) {
                                        return function() {
                                            var c = [].slice.call(arguments);
                                            a && a.apply(this, c);
                                            q()
                                        }
                                    }(l[n])), g(a[n], t, c, n, y))
                        }
                    else
                        !f && q()
                }
                var y = !!a.test, h = a.load || a.both, t = a.callback || f, l = t, q = a.complete || f, J, n;
                b(y ? a.yep : a.nope, !!h);
                h && b(h)
            }
            var h, l, n = this.yepnope.loader;
            if (d(a))
                g(a, 0, n, 0);
            else if (w(a))
                for (h = 0; h < a.length; h++)
                    l = a[h], d(l) ? g(l, 0, n, 0) : w(l) ? t(l) : Object(l) === l && k(l, n);
            else
                Object(a) === a && k(a, n)
        };
        t.addPrefix = function(a, c) {
            B[a] = c
        };
        t.addFilter = function(a) {
            U.push(a)
        };
        t.errorTimeout = 1E4;
        null == a.readyState && a.addEventListener && (a.readyState = "loading", a.addEventListener("DOMContentLoaded", C = function() {
            a.removeEventListener("DOMContentLoaded", C, 0);
            a.readyState = "complete"
        }, 0));
        b.yepnope = q();
        b.yepnope.executeStack = h;
        b.yepnope.injectJs = function(c, b, d, e, k, l) {
            var q = a.createElement("script"), n, m;
            e = e || t.errorTimeout;
            q.src = c;
            for (m in d)
                q.setAttribute(m, d[m]);
            b = l ? h : b || f;
            q.onreadystatechange = q.onload = function() {
                !n && g(q.readyState) && (n = 1, b(), q.onload = q.onreadystatechange = null)
            };
            E(function() {
                n || (n = 1, b(1))
            }, e);
            k ? q.onload() : u.parentNode.insertBefore(q, u)
        };
        b.yepnope.injectCss = function(c, b, d, e, g, k) {
            e = a.createElement("link");
            var t;
            b = k ? h : b || f;
            e.href = c;
            e.rel = "stylesheet";
            e.type = "text/css";
            for (t in d)
                e.setAttribute(t, d[t]);
            g || (u.parentNode.insertBefore(e, u), E(b, 0))
        }
    })(this, document);
    Modernizr.load = function() {
        yepnope.apply(window, [].slice.call(arguments, 0))
    };
    (function() {
        function b() {
            var a = {"&": "&#38;", "<": "&#60;", ">": "&#62;", '"': "&#34;", "'": "&#39;", "/": "&#47;"}, c = /&(?!#?\w+;)|<|>|"|'|\//g;
            return function(b) {
                return b ? b.toString().replace(c, function(c) {
                    return a[c] || c
                }) : b
            }
        }
        function a(c, b, d) {
            return("string" === typeof b ? b : b.toString()).replace(c.define || h, function(a, c, b, e) {
                0 === c.indexOf("def.") && (c = c.substring(4));
                c in d || (":" === b ? d[c] = e : eval("def['" + c + "']=" + e));
                return""
            }).replace(c.use || h, function(b, e) {
                var f = eval(e);
                return f ? a(c, f, d) : f
            })
        }
        function c(a) {
            return a.replace(/\\('|\\)/g, "$1").replace(/[\r\t\n]/g, " ")
        }
        var e = {version: "0.2.0", templateSettings: {evaluate: /\{\{([\s\S]+?)\}\}/g, interpolate: /\{\{=([\s\S]+?)\}\}/g,
                encode: /\{\{!([\s\S]+?)\}\}/g, use: /\{\{#([\s\S]+?)\}\}/g, define: /\{\{##\s*([\w\.$]+)\s*(\:|=)([\s\S]+?)#\}\}/g, conditional: /\{\{\?(\?)?\s*([\s\S]*?)\s*\}\}/g, iterate: /\{\{~\s*(?:\}\}|([\s\S]+?)\s*\:\s*([\w$]+)\s*(?:\:\s*([\w$]+))?\s*\}\})/g, varname: "it", strip: !0, append: !0, selfcontained: !1}, template: void 0, compile: void 0}, d = function() {
            return this || (0, eval)("this")
        }();
        "undefined" !== typeof module && module.exports ? module.exports = e : "function" === typeof define && define.amd ? define(function() {
            return e
        }) : d.doT =
                e;
        d.encodeHTML = b();
        var f = {start: "'+(", end: ")+'", startencode: "'+encodeHTML("}, g = {start: "';out+=(", end: ");out+='", startencode: "';out+=encodeHTML("}, h = /$^/;
        e.template = function(k, l, q) {
            l = l || e.templateSettings;
            var n = l.append ? f : g, E, u = 0, z;
            if (l.use || l.define) {
                var s = d.def;
                d.def = q || {};
                k = a(l, k, d.def);
                d.def = s
            }
            k = ("var out='" + (l.strip ? k.replace(/(^|\r|\n)\t* +| +\t*(\r|\n|$)/g, " ").replace(/\r|\n|\t|\/\*[\s\S]*?\*\//g, "") : k).replace(/'|\\/g, "\\$&").replace(l.interpolate || h, function(a, b) {
                return n.start + c(b) + n.end
            }).replace(l.encode ||
                    h, function(a, b) {
                E = !0;
                return n.startencode + c(b) + n.end
            }).replace(l.conditional || h, function(a, b, d) {
                return b ? d ? "';}else if(" + c(d) + "){out+='" : "';}else{out+='" : d ? "';if(" + c(d) + "){out+='" : "';}out+='"
            }).replace(l.iterate || h, function(a, b, d, e) {
                if (!b)
                    return"';} } out+='";
                u += 1;
                z = e || "i" + u;
                b = c(b);
                return"';var arr" + u + "=" + b + ";if(arr" + u + "){var " + d + "," + z + "=-1,l" + u + "=arr" + u + ".length-1;while(" + z + "<l" + u + "){" + d + "=arr" + u + "[" + z + "+=1];out+='"
            }).replace(l.evaluate || h, function(a, b) {
                return"';" + c(b) + "out+='"
            }) + "';return out;").replace(/\n/g,
                    "\\n").replace(/\t/g, "\\t").replace(/\r/g, "\\r").replace(/(\s|;|}|^|{)out\+='';/g, "$1").replace(/\+''/g, "").replace(/(\s|;|}|^|{)out\+=''\+/g, "$1out+=");
            E && l.selfcontained && (k = "var encodeHTML=(" + b.toString() + "());" + k);
            try {
                return new Function(l.varname, k)
            } catch (p) {
                throw"undefined" !== typeof console && console.log("Could not create a template function: " + k), p;
            }
        };
        e.compile = function(a, c) {
            return e.template(a, null, c)
        }
    })();
    (function() {
        function b(b) {
            var f, g, h, l = "json" == b;
            if (l || "json-stringify" == b || "json-parse" ==
                    b) {
                if ("json-stringify" == b || l) {
                    if (f = "function" == typeof k.stringify && w) {
                        (h = function() {
                            return 1
                        }).toJSON = h;
                        try {
                            f = "0" === k.stringify(0) && "0" === k.stringify(new Number) && '""' == k.stringify(new String) && k.stringify(d) === a && k.stringify(a) === a && k.stringify() === a && "1" === k.stringify(h) && "[1]" == k.stringify([h]) && "[null]" == k.stringify([a]) && "null" == k.stringify(e) && "[null,null,null]" == k.stringify([a, d, e]) && '{"A":[1,true,false,null,"\\u0000\\b\\n\\f\\r\\t"]}' == k.stringify({A: [h, c, !1, e, "\x00\b\n\f\r\t"]}) && "1" === k.stringify(e,
                                    h) && "[\n 1,\n 2\n]" == k.stringify([1, 2], e, 1) && '"-271821-04-20T00:00:00.000Z"' == k.stringify(new Date(-864E13)) && '"+275760-09-13T00:00:00.000Z"' == k.stringify(new Date(864E13)) && '"-000001-01-01T00:00:00.000Z"' == k.stringify(new Date(-621987552E5)) && '"1969-12-31T23:59:59.999Z"' == k.stringify(new Date(-1))
                        } catch (q) {
                            f = !1
                        }
                    }
                    if (!l)
                        return f
                }
                if ("json-parse" == b || l) {
                    if ("function" == typeof k.parse)
                        try {
                            if (0 === k.parse("0") && !k.parse(!1) && (h = k.parse('{"A":[1,true,false,null,"\\u0000\\b\\n\\f\\r\\t"]}'), g = 5 == h.a.length &&
                                    1 == h.a[0])) {
                                try {
                                    g = !k.parse('"\t"')
                                } catch (n) {
                                }
                                if (g)
                                    try {
                                        g = 1 != k.parse("01")
                                    } catch (m) {
                                    }
                            }
                        } catch (s) {
                            g = !1
                        }
                    if (!l)
                        return g
                }
                return f && g
            }
        }
        var a = void 0, c = !0, e = null, d = {}.toString, f, g, h = "function" === typeof define && define.c, k = !h && "object" == typeof V && V;
        k || h ? "object" == typeof JSON && JSON ? h ? k = JSON : (k.stringify = JSON.stringify, k.parse = JSON.parse) : h && (k = this.JSON = {}) : k = this.JSON || (this.JSON = {});
        var l, q, n, E, u, z, s, p, D, r, v, m, A, w = new Date(-0xc782b5b800cec), B, C, F;
        try {
            w = -109252 == w.getUTCFullYear() && 0 === w.getUTCMonth() && 1 == w.getUTCDate() &&
                    10 == w.getUTCHours() && 37 == w.getUTCMinutes() && 6 == w.getUTCSeconds() && 708 == w.getUTCMilliseconds()
        } catch (H) {
        }
        b("json") || (w || (B = Math.floor, C = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334], F = function(a, c) {
            return C[c] + 365 * (a - 1970) + B((a - 1969 + (c = +(1 < c))) / 4) - B((a - 1901 + c) / 100) + B((a - 1601 + c) / 400)
        }), (f = {}.hasOwnProperty) || (f = function(a) {
            var c = {}, b;
            (c.__proto__ = e, c.__proto__ = {toString: 1}, c).toString != d ? f = function(a) {
                var c = this.__proto__;
                a = a in(this.__proto__ = e, this);
                this.__proto__ = c;
                return a
            } : (b = c.constructor, f = function(a) {
                var c =
                        (this.constructor || b).prototype;
                return a in this && !(a in c && this[a] === c[a])
            });
            c = e;
            return f.call(this, a)
        }), g = function(a, c) {
            var b = 0, g, h, k;
            (g = function() {
                this.valueOf = 0
            }).prototype.valueOf = 0;
            h = new g;
            for (k in h)
                f.call(h, k) && b++;
            g = h = e;
            b ? b = 2 == b ? function(a, c) {
                var b = {}, e = "[object Function]" == d.call(a), g;
                for (g in a)
                    e && "prototype" == g || f.call(b, g) || (b[g] = 1) && f.call(a, g) && c(g)
            } : function(a, c) {
                var b = "[object Function]" == d.call(a), e, g;
                for (e in a)
                    b && "prototype" == e || !f.call(a, e) || (g = "constructor" === e) || c(e);
                (g || f.call(a,
                        e = "constructor")) && c(e)
            } : (h = "valueOf toString toLocaleString propertyIsEnumerable isPrototypeOf hasOwnProperty constructor".split(" "), b = function(a, c) {
                var b = "[object Function]" == d.call(a), e;
                for (e in a)
                    b && "prototype" == e || !f.call(a, e) || c(e);
                for (b = h.length; e = h[--b]; f.call(a, e) && c(e))
                    ;
            });
            b(a, c)
        }, b("json-stringify") || (l = {"\\": "\\\\", '"': '\\"', "\b": "\\b", "\f": "\\f", "\n": "\\n", "\r": "\\r", "\t": "\\t"}, q = function(a, c) {
            return("000000" + (c || 0)).slice(-a)
        }, n = function(a) {
            for (var c = '"', b = 0, d; d = a.charAt(b); b++)
                c +=
                        -1 < '\\"\b\f\n\r\t'.indexOf(d) ? l[d] : l[d] = " " > d ? "\\u00" + q(2, d.charCodeAt(0).toString(16)) : d;
            return c + '"'
        }, E = function(b, h, k, l, m, s, u) {
            var p = h[b], x, D, z, r, v, w, A, C, G;
            if ("object" == typeof p && p)
                if (x = d.call(p), "[object Date]" != x || f.call(p, "toJSON"))
                    "function" == typeof p.toJSON && ("[object Number]" != x && "[object String]" != x && "[object Array]" != x || f.call(p, "toJSON")) && (p = p.toJSON(b));
                else if (p > -1 / 0 && p < 1 / 0) {
                    if (F) {
                        z = B(p / 864E5);
                        for (x = B(z / 365.2425) + 1970 - 1; F(x + 1, 0) <= z; x++)
                            ;
                        for (D = B((z - F(x, 0)) / 30.42); F(x, D + 1) <= z; D++)
                            ;
                        z =
                                1 + z - F(x, D);
                        r = (p % 864E5 + 864E5) % 864E5;
                        v = B(r / 36E5) % 24;
                        w = B(r / 6E4) % 60;
                        A = B(r / 1E3) % 60;
                        r %= 1E3
                    } else
                        x = p.getUTCFullYear(), D = p.getUTCMonth(), z = p.getUTCDate(), v = p.getUTCHours(), w = p.getUTCMinutes(), A = p.getUTCSeconds(), r = p.getUTCMilliseconds();
                    p = (0 >= x || 1E4 <= x ? (0 > x ? "-" : "+") + q(6, 0 > x ? -x : x) : q(4, x)) + "-" + q(2, D + 1) + "-" + q(2, z) + "T" + q(2, v) + ":" + q(2, w) + ":" + q(2, A) + "." + q(3, r) + "Z"
                } else
                    p = e;
            k && (p = k.call(h, b, p));
            if (p === e)
                return"null";
            x = d.call(p);
            if ("[object Boolean]" == x)
                return"" + p;
            if ("[object Number]" == x)
                return p > -1 / 0 && p < 1 / 0 ? "" +
                        p : "null";
            if ("[object String]" == x)
                return n(p);
            if ("object" == typeof p) {
                for (b = u.length; b--; )
                    if (u[b] === p)
                        throw TypeError();
                u.push(p);
                C = [];
                h = s;
                s += m;
                if ("[object Array]" == x) {
                    D = 0;
                    for (b = p.length; D < b; G || (G = c), D++)
                        x = E(D, p, k, l, m, s, u), C.push(x === a ? "null" : x);
                    b = G ? m ? "[\n" + s + C.join(",\n" + s) + "\n" + h + "]" : "[" + C.join(",") + "]" : "[]"
                } else
                    g(l || p, function(b) {
                        var d = E(b, p, k, l, m, s, u);
                        d !== a && C.push(n(b) + ":" + (m ? " " : "") + d);
                        G || (G = c)
                    }), b = G ? m ? "{\n" + s + C.join(",\n" + s) + "\n" + h + "}" : "{" + C.join(",") + "}" : "{}";
                u.pop();
                return b
            }
        }, k.stringify =
                function(a, c, b) {
                    var e, f, g, h, k, l;
                    if ("function" == typeof c || "object" == typeof c && c)
                        if ("[object Function]" == d.call(c))
                            f = c;
                        else if ("[object Array]" == d.call(c))
                            for (g = {}, h = 0, k = c.length; h < k; l = c[h++], ("[object String]" == d.call(l) || "[object Number]" == d.call(l)) && (g[l] = 1))
                                ;
                    if (b)
                        if ("[object Number]" == d.call(b)) {
                            if (0 < (b -= b % 1))
                                for (e = "", 10 < b && (b = 10); e.length < b; e += " ")
                                    ;
                        } else
                            "[object String]" == d.call(b) && (e = 10 >= b.length ? b : b.slice(0, 10));
                    return E("", (l = {}, l[""] = a, l), f, g, e, "", [])
                }), b("json-parse") || (u = String.fromCharCode,
                z = {"\\": "\\", '"': '"', "/": "/", b: "\b", t: "\t", n: "\n", f: "\f", r: "\r"}, s = function() {
            m = A = e;
            throw SyntaxError();
        }, p = function() {
            for (var a = A, b = a.length, d, f, g, h, k; m < b; )
                if (d = a.charAt(m), -1 < "\t\r\n ".indexOf(d))
                    m++;
                else {
                    if (-1 < "{}[]:,".indexOf(d))
                        return m++, d;
                    if ('"' == d) {
                        f = "@";
                        for (m++; m < b; )
                            if (d = a.charAt(m), " " > d)
                                s();
                            else if ("\\" == d)
                                if (d = a.charAt(++m), -1 < '\\"/btnfr'.indexOf(d))
                                    f += z[d], m++;
                                else if ("u" == d) {
                                    g = ++m;
                                    for (h = m + 4; m < h; m++)
                                        d = a.charAt(m), "0" <= d && "9" >= d || "a" <= d && "f" >= d || "A" <= d && "F" >= d || s();
                                    f += u("0x" + a.slice(g,
                                            m))
                                } else
                                    s();
                            else {
                                if ('"' == d)
                                    break;
                                f += d;
                                m++
                            }
                        if ('"' == a.charAt(m))
                            return m++, f
                    } else {
                        g = m;
                        "-" == d && (k = c, d = a.charAt(++m));
                        if ("0" <= d && "9" >= d) {
                            for ("0" == d && (d = a.charAt(m + 1), "0" <= d && "9" >= d) && s(); m < b && (d = a.charAt(m), "0" <= d && "9" >= d); m++)
                                ;
                            if ("." == a.charAt(m)) {
                                for (h = ++m; h < b && (d = a.charAt(h), "0" <= d && "9" >= d); h++)
                                    ;
                                h == m && s();
                                m = h
                            }
                            d = a.charAt(m);
                            if ("e" == d || "E" == d) {
                                d = a.charAt(++m);
                                "+" != d && "-" != d || m++;
                                for (h = m; h < b && (d = a.charAt(h), "0" <= d && "9" >= d); h++)
                                    ;
                                h == m && s();
                                m = h
                            }
                            return+a.slice(g, m)
                        }
                        k && s();
                        if ("true" == a.slice(m, m + 4))
                            return m +=
                                    4, c;
                        if ("false" == a.slice(m, m + 5))
                            return m += 5, !1;
                        if ("null" == a.slice(m, m + 4))
                            return m += 4, e
                    }
                    s()
                }
            return"$"
        }, D = function(a) {
            var b, d;
            "$" == a && s();
            if ("string" == typeof a) {
                if ("@" == a.charAt(0))
                    return a.slice(1);
                if ("[" == a) {
                    for (b = []; ; d || (d = c)) {
                        a = p();
                        if ("]" == a)
                            break;
                        d && ("," == a ? (a = p(), "]" == a && s()) : s());
                        "," == a && s();
                        b.push(D(a))
                    }
                    return b
                }
                if ("{" == a) {
                    for (b = {}; ; d || (d = c)) {
                        a = p();
                        if ("}" == a)
                            break;
                        d && ("," == a ? (a = p(), "}" == a && s()) : s());
                        "," != a && "string" == typeof a && "@" == a.charAt(0) && ":" == p() || s();
                        b[a.slice(1)] = D(p())
                    }
                    return b
                }
                s()
            }
            return a
        },
                v = function(b, c, d) {
            d = r(b, c, d);
            d === a ? delete b[c] : b[c] = d
        }, r = function(a, b, c) {
            var e = a[b], f;
            if ("object" == typeof e && e)
                if ("[object Array]" == d.call(e))
                    for (f = e.length; f--; )
                        v(e, f, c);
                else
                    g(e, function(a) {
                        v(e, a, c)
                    });
            return c.call(a, b, e)
        }, k.parse = function(a, b) {
            var c, f;
            m = 0;
            A = a;
            c = D(p());
            "$" != p() && s();
            m = A = e;
            return b && "[object Function]" == d.call(b) ? r((f = {}, f[""] = c, f), "", b) : c
        }));
        h && define(function() {
            return k
        })
    })();
    Date.now = Date.now || function() {
        return+new Date
    };
    var ca = Array.prototype.forEach, da = Array.prototype.map, r =
            {toArray: function(b) {
                    for (var a = [], c = 0; c < b.length; ++c)
                        a[c] = b[c];
                    return a
                }, forEach: function(b, a, c) {
                    if (ca && b.forEach === ca)
                        b.forEach(a);
                    else
                        for (var e = 0, d = b.length; e < d && !1 !== a.call(c, b[e], e, b); ++e)
                            ;
                }, map: function(b, a, c) {
                    var e = [];
                    if (null == b)
                        return e;
                    if (da && b.map === da)
                        return b.map(a, c);
                    r.forEach(b, function(b, f, g) {
                        e[e.length] = a.call(c, b, f, g)
                    });
                    return e
                }, find: function() {
                }, extend: function(b) {
                    r.forEach([].slice.call(arguments, 1), function(a) {
                        if (a)
                            for (var c in a)
                                b[c] = a[c]
                    });
                    return b
                }, isArray: Array.isArray || function(b) {
                    return"[object Array]" ==
                            {}.toString.call(b)
                }, debounce: function(b, a, c) {
                    var e, d;
                    return function() {
                        var f = this, g = arguments, h = c && !e;
                        clearTimeout(e);
                        e = setTimeout(function() {
                            e = null;
                            c || (d = b.apply(f, g))
                        }, a);
                        h && (d = b.apply(f, g));
                        return d
                    }
                }, tokenize: function(b, a, c) {
                    !a && (a = ",");
                    !c && (c = ":");
                    var e = {};
                    b = b.split(a);
                    r.forEach(b, function(a) {
                        var b = a.split(c);
                        a = b[0];
                        b = b[1];
                        a && b && (e[a] = b)
                    });
                    return e
                }}, O = function() {
        function b(a) {
            var b = document.createElement("script"), d = !1;
            b.src = a;
            b.async = !0;
            b.onload = b.onreadystatechange = function() {
                d || this.readyState &&
                        "loaded" !== this.readyState && "complete" !== this.readyState || (d = !0, b.onload = b.onreadystatechange = null, b && b.parentNode && b.parentNode.removeChild(b))
            };
            c || (c = document.getElementsByTagName("head")[0]);
            c.appendChild(b)
        }
        var a = 0, c, e, d, f = this, g = {};
        return{get: function(c, k, l, q) {
                e = -1 === (c || "").indexOf("?") ? "?" : "&";
                k = k || {};
                for (d in k)
                    k.hasOwnProperty(d) && (e += encodeURIComponent(d) + "=" + encodeURIComponent(k[d]) + "&");
                var n = "json" + ++a;
                f[n] = function(a) {
                    l(a);
                    try {
                        delete f[n]
                    } catch (b) {
                        f[n] = null
                    }
                };
                b(c + e + (q || g.callbackName ||
                        "callback") + "=" + n);
                return n
            }, script: b, init: function(a) {
                g = a
            }}
    }();
    (function(b, a, c) {
        var e = function(a, b, c) {
            return 1 === arguments.length ? e.get(a) : e.set(a, b, c)
        };
        e.get = function(a) {
            b.cookie !== e._cacheString && e._populateCache();
            return e._cache[a] || ""
        };
        e.defaults = {path: "/"};
        e.set = function(a, f, g) {
            g = {path: g && g.path || e.defaults.path, domain: g && g.domain || e.defaults.domain, expires: g && g.expires || e.defaults.expires, secure: g && g.secure !== c ? g.secure : e.defaults.secure};
            f === c && (g.expires = -1);
            switch (typeof g.expires) {
                case "number":
                    g.expires =
                            new Date((new Date).getTime() + 1E3 * g.expires);
                    break;
                case "string":
                    g.expires = new Date(g.expires)
            }
            a = encodeURIComponent(a) + "=" + (f + "").replace(/[^!#-+\--:<-[\]-~]/g, encodeURIComponent);
            a += g.path ? ";path=" + g.path : "";
            a += g.domain ? ";domain=" + g.domain : "";
            a += g.expires ? ";expires=" + g.expires.toGMTString() : "";
            a += g.secure ? ";secure" : "";
            b.cookie = a;
            return e
        };
        e.expire = function(a, b) {
            return e.set(a, c, b)
        };
        e._populateCache = function() {
            e._cache = {};
            e._cacheString = b.cookie;
            for (var a = e._cacheString.split("; "), f = 0; f < a.length; f++) {
                var g =
                        a[f].indexOf("=");
                try {
                    var h = decodeURIComponent(a[f].substr(0, g)), k = decodeURIComponent(a[f].substr(g + 1))
                } catch (l) {
                    continue
                }
                e._cache[h] === c && (e._cache[h] = k)
            }
        };
        e.enabled = function() {
            var a = "1" === e.set("cookies.js", "1").get("cookies.js");
            e.expire("cookies.js");
            return a
        }();
        return a.cookies = e
    })(document, r);
    window.console && (window.console.log && K) && (C = function() {
        var b = Error(), b = b.stack ? b.stack.split("\n")[2].replace(/^\s+/, "[eClick] log ") : "";
        console.log(b, arguments)
    });
    navigator.appVersion.indexOf("MSIE");
    navigator.appVersion.toLowerCase().indexOf("win");
    navigator.userAgent.indexOf("Opera");
    var K = !1, L = {}, ea, fa, W, ga, v, P, ha, Q, A, w, V, M, R, ia, X, Y, Z, S, B;
    B = window;
    w = document;
    Y = B.screen;
    ia = B.location;
    v = {cache: {zones: {}}, widgetZones: [], balloonZones: [], sticky: [], setVisitorId: function(b) {
            return r.cookies("fosp_aid") ? b() : O.get("http://t.d.eclick.vn/getid", {nid: "fosp_aid"}, function(a) {
                if (null != a.result)
                    return r.cookies(a.nid, a.vid, {expire: 864E4, domain: ".eclick.vn"}), b()
            })
        }, getZones: function() {
            var b, a;
            a = [];
            if (null !=
                    w.querySelectorAll)
                return b = w.querySelectorAll("[data-zone-id]"), r.forEach(b, function(b) {
                    var e;
                    if (!b.getAttribute("data-rendered"))
                        return e = b.getAttribute("data-zone-id"), a.push(new W(e, b))
                }), a;
            r.forEach(w.all, function(b) {
                var e;
                if ((e = b.getAttribute("data-zone-id")) && !b.getAttribute("data-rendered"))
                    return a.push(new W(e, b))
            });
            return a
        }, init: function() {
            var b, a, c, e;
            ja("http://e.eclick.vn/delivery/asset/275692097/eclick.css");
            A.isBoxModel = ka();
            e = v.widgetZones = v.getZones();
            if (0 !== e.length)
                return b = [function() {
                        var a,
                                b, g;
                        g = [];
                        a = 0;
                        for (b = e.length; a < b; a++)
                            c = e[a], g.push(c.id);
                        return g
                    }()], a = "http://d.eclick.vn/delivery/zone/batch.json?id=" + b.join(",") + "&url=" + encodeURIComponent(A.location()), v.setVisitorId(function() {
                    return O.get(a, {}, function(a) {
                        if (a)
                            return null != a.meta && 200 !== +a.meta.code ? C(a) : r.forEach(e, function(b) {
                                var c;
                                c = a.data[b.id];
                                if (null != c)
                                    return r.extend(b, c), b.banners = r.map(b.banners, function(a) {
                                        a = new fa(a);
                                        a.zone = b;
                                        return a
                                    }), b.render().bind().logImpression()
                            })
                    })
                }), N(B, "scroll", r.debounce(function() {
                    return r.forEach(e,
                            function(a) {
                                if (a.isInViewPort(50))
                                    return a.logTrueImpression()
                            })
                }, 1E3))
        }};
    Q = r.debounce(v.init, 100);
    R = function(b, a) {
        var c;
        c = "eclick-log-frame-" + a;
        return(w.getElementById(c) || ha(c)).setAttribute("src", b)
    };
    ha = function(b) {
        var a;
        a = w.createElement("iframe");
        a.setAttribute("id", b);
        a.setAttribute("style", "display:none");
        a.setAttribute("width", "0");
        a.setAttribute("height", "0");
        w.body.appendChild(a);
        return a
    };
    P = function(b) {
        var a;
        a = w.createElement("div");
        a.setAttribute("data-zone-id", b);
        return a
    };
    Z = function(b,
            a, c) {
        null != a[0] && (a = a[0]);
        v.sticky.push({id: b, container: a, params: c});
        var e, d, f;
        d = v.widgetZones;
        f = [];
        c = 0;
        for (e = d.length; c < e; c++)
            a = d[c], a.id === b && f.push(a);
        a = f[0];
        return null != a ? a.bindSticky() : void 0
    };
//    ga = function(b) {
//        return'<object id="' + b.id + '" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="' + b.width + '" height="' + b.height + '"><param name="movie" value="' + b.fileUrl + '"><param name="allowScriptAccess" value="always" /><param name="quality" value="high"><param name="wmode" value="transparent"><param name="flashvars" value="' +
//                b.flashVars + '"><param name="allowfullscreen" value="true"><embed id="videoplayer" name="videoplayer" flashvars="' + b.flashVars + '" type="application/x-shockwave-flash" allowfullscreen="true" allowscriptaccess="always" wmode="transparent" pluginspage="http://www.macromedia.com/go/getflashplayer" src="' + b.fileUrl + '" width="' + b.width + '" height="' + b.height + '"></object>'
//    };
    A = {isBoxModel: !1, framed: function() {
            return B.self !== B.top
        }, resolution: function() {
            return"" + Y.width + "x" + Y.height
        }, location: function() {
            return this.framed() ?
                    w.referrer : ia.href
        }, referrer: function() {
            return this.framed() ? "CANNOT_ACCESS" : w.referrer
        }, hostname: function() {
            return this.location() ? this.location().match(/\/[^\/:]+/)[0].substr(1) : "MALFORMED_HOSTNAME"
        }, localStorage: function() {
            return null != B.localStorage
        }};
    K = {"widget-float": '<div class="eclick-zone eclick-zone-classic widget-float {{=zone.direction}} widget_{{=zone.color}}" style="width:{{=zone.width}}px;height:{{=zone.height}}px"> <div class="ec-zone-head" style="{{=zone.direction == \'horizontal\' ? \'height:\' + (zone.height-4) + \'px\' :\'\' }}"> <a target="_blank" href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}"> <span class="ec-zone-logo"></span> {{? zone.direction == \'vertical\' && !zone.isBalloon }} <span class="buy">Mua qu\u1ea3ng c\u00e1o</span> {{?}} </a> </div> <div class="banners"> {{~zone.banners :banner:index}} <a class="{{= banner.firstRow ? \'banner firstRow\' : \'banner\'}}" data-banner-id="{{=banner.id}}" style="width:{{= zone.bannerWidth}}px;height:{{= zone.bannerHeight + banner.adjust}}px;" target="_blank" href="{{= banner.url}}"> <span class="banner-wrapper" style="margin:{{= zone.padding}}px 5px;"> <img src="{{= banner.image}}" alt="{{= banner.title}}"/> <span class="ec-banner-content"> <span class="banner-title"> {{= banner.title}} </span> <em class="banner-hostname">{{= banner.hostname}}</em> {{? banner.oldPrice && banner.oldPrice != banner.newPrice}} <del class="original">{{= banner.oldPrice}} VN\u0110</del> {{?}} <span class="promotion">{{= banner.newPrice}} VN\u0110</span> </span> </span> <span class="banner-back-face">eClick.vn</span> </a> {{~}} </div> <div class="bottom-bar"></div> </div>',
        "widget-center": '<div class="eclick-zone eclick-zone-classic widget-center {{=zone.direction}} widget_{{=zone.color}}" style="width:{{=zone.width}}px;height:{{=zone.height}}px"> <div class="ec-zone-head" style="{{=zone.direction == \'horizontal\' ? \'height:\' + (zone.height-4) + \'px\' :\'\' }}"> <a target="_blank" href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}"> <span class="ec-zone-logo"></span> </a> </div> <div class="banners"> {{~zone.banners :banner:index}} <a class="{{= banner.firstRow ? \'banner firstRow\' : \'banner\'}}" data-banner-id="{{=banner.id}}" style="width:{{= zone.bannerWidth}}px;height:{{= zone.bannerHeight + banner.adjust}}px;" target="_blank" href="{{= banner.url}}"> <span class="banner-wrapper" style="margin:{{= zone.padding}}px 5px;"> <span class="banner-title"> {{= banner.title}} </span> <em class="banner-hostname">{{= banner.hostname}}</em> <img src="{{= banner.image}}" alt="{{= banner.title}}"/> {{? banner.oldPrice && banner.oldPrice != banner.newPrice}} <del class="original">{{= banner.oldPrice}} VN\u0110</del> {{?}} <span class="promotion">{{= banner.newPrice}} VN\u0110</span> </span> <span class="banner-back-face">eClick.vn</span> </a> {{~}} </div> <div class="bottom-bar"></div> </div>',
        "context-center-2": '<div class="eclick-zone eclick-zone-classic context-center-2 vertical widget_{{=zone.color}} {{= zone.width < 185 ? \'context-center-thin\' : \'\'}}" style="width:{{=zone.width}}px;height:{{=zone.height}}px"> <div class="ec-zone-head"> <a target="_blank" href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}"> <span class="ec-zone-logo"></span> {{? !zone.isBalloon && zone.width >= 185}} <span class="buy">Mua qu\u1ea3ng c\u00e1o</span> {{?}} </a> </div> <div class="banners"> {{~zone.banners :banner:index}} <a class="{{= banner.firstRow ? \'banner firstRow\' : \'banner\'}}" data-banner-id="{{=banner.id}}" style="width:{{= zone.bannerWidth}}px;height:{{= zone.bannerHeight + banner.adjust}}px;" target="_blank" href="{{= banner.url}}"> <span class="banner-wrapper" style="margin:{{= zone.padding}}px 10px;"> <span class="banner-title"> {{= banner.title}} </span> <em class="banner-hostname">{{= banner.hostname}}</em> <img src="{{= banner.image}}" alt="{{= banner.title}}"/> <span class="banner-content">{{= banner.content}}</span> </span> <span class="banner-back-face">eClick.vn</span> </a> {{~}} </div> <div class="bottom-bar"></div> </div>',
        "context-float-2": '<div class="eclick-zone eclick-zone-classic context-float-2 {{= zone.direction}} widget_{{=zone.color}}" style="width:{{=zone.width}}px;height:{{=zone.height}}px"> <div class="ec-zone-head"> <a target="_blank" href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}"> <span class="ec-zone-logo"></span> {{? !zone.isBalloon && zone.width >= 185}} <span class="buy">Mua qu\u1ea3ng c\u00e1o</span> {{?}} </a> </div> <div class="banners"> {{~zone.banners :banner:index}} <a class="{{= banner.firstRow ? \'banner firstRow\' : \'banner\'}}" data-banner-id="{{=banner.id}}" style="width:{{= zone.bannerWidth}}px;height:{{= zone.bannerHeight + banner.adjust}}px;" target="_blank" href="{{= banner.url}}"> <span class="banner-wrapper" style="margin:{{= zone.padding}}px 10px;"> <img src="{{= banner.image}}" alt="{{= banner.title}}"/> <span class="ec-banner-content"> <span class="banner-title"> {{= banner.title}} </span> <em class="banner-hostname">{{= banner.hostname}}</em> <span class="banner-content">{{= banner.content}}</span> </span> </span> <span class="banner-back-face">eClick.vn</span> </a> {{~}} </div> <div class="bottom-bar"></div> </div>',
        "context-hybrid-2": '<div class="eclick-zone eclick-zone-classic context-hybrid-2 vertical widget_{{=zone.color}}" style="width:{{=zone.width}}px;height:{{=zone.height}}px"> <div class="ec-zone-head"> <a target="_blank" href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}"> <span class="ec-zone-logo"></span> {{? !zone.isBalloon}} <span class="buy">Mua qu\u1ea3ng c\u00e1o</span> {{?}} </a> </div> <div class="banners"> {{~zone.banners :banner:index}} <a class="{{= banner.firstRow ? \'banner firstRow\' : \'banner\'}}" data-banner-id="{{=banner.id}}" style="width:{{= zone.bannerWidth}}px;height:{{= zone.bannerHeight + banner.adjust}}px;" target="_blank" href="{{= banner.url}}"> <span class="banner-wrapper" style="margin:{{= zone.padding}}px 10px;"> <span class="banner-title"> {{= banner.title}} </span> <em class="banner-hostname">{{= banner.hostname}}</em> <img src="{{= banner.image}}" alt="{{= banner.title}}"/> <span class="banner-content">{{= banner.content}}</span> </span> <span class="banner-back-face">eClick.vn</span> </a> {{~}} </div> <div class="bottom-bar"></div> </div>',
        metro: '<div class="eclick-zone vertical eclick-zone-metro eclick-zone-blue {{=zone.className}}"><a class="header" href="http://eclick.vn/#!/vi/advertisers?{{=zone.utm.logo_bar}}" target="_blank"><span class="logo"><i></i><abbr>ads by eClick</abbr></span></a><div class="banners">{{~zone.banners :banner:index}}{{? banner.bannerType==14}}<a class="banner-widget {{= banner.firstRow ? \'banner banner-first-row\' : \'banner\'}}" href="{{=banner.url}}" target="_blank" data-banner-id="{{=banner.id}}"><span class="banner-face banner-face-front"><img src="{{=banner.image}}" alt="{{=banner.title}}"/><span class="banner-content"><del>{{? banner.oldPrice && banner.oldPrice !=banner.newPrice}}{{=banner.oldPrice}} VN\u0110{{??}}&zwnj;{{?}}</del><ins>{{=banner.newPrice || \'0 \u0111\u1ed3ng\'}}</ins><strong>{{=banner.title}}</strong><em>{{=banner.hostname}}</em></span></span></a>{{??}}<a class="banner-context {{= banner.firstRow ? \'banner banner-first-row\' : \'banner\'}}" href="{{=banner.url}}" target="_blank" data-banner-id="{{=banner.id}}"><span class="banner-face banner-face-front"><img src="{{=banner.image}}" alt="{{=banner.title}}"/><span class="banner-content"><strong>{{=banner.title}}</strong><ins>{{=banner.content}}</ins><em>{{=banner.hostname}}</em></span></span></a>{{?}}{{~}}</div></div>',
        promotion: '<div class="eclick-zone vertical eclick-zone-promotion eclick-zone-blue {{=zone.className}}"><a class=header href="http://eclick.vn/#!/vi/advertisers?{{= zone.utm.logo_bar }}" target=_blank><span class=logo><i></i><abbr>ads by eClick</abbr></span></a><div class=banners>{{~zone.banners :banner:index }} <a class="banner-promotion {{= banner.firstRow ? \'banner banner-first-row\' : \'banner\'}} {{=banner.ribbonClass }}" data-banner-id="{{=banner.id}}" href="{{=banner.url}}" target=_blank><span class="banner-face banner-face-front"><span class=eclick-banner-ribbon></span> <span class=banner-content><ins>{{= banner.percentage }}<small>%</small></ins> <strong>{{= banner.title }}</strong> <em>{{= banner.hostname }}</em></span></span></a> {{~}}</div></div>'};
    S = {};
    for (X in K)
        M = K[X], doT.templateSettings.varname = "zone", S[X] = doT.template(M);
    W = function() {
        function b(a, b) {
            this.id = a;
            this.elem = b;
            this.trueImpression = {logged: !1, time: 0};
            this.impression = {logged: !1};
            this.placement = "widget";
            v.cache.zones[a] = this
        }
        b.prototype.render = function() {
            var a, b, e, d, f, g, h, k, l, q, n, r, u;
            a = new Date;
            l = la(a);
            g = this.view;
            e = "vertical" === g.direction;
            g.banners = this.banners.slice(0, +g.total + 1 || 9E9);
            g.color = e ? "blue" : "yellow";
            g.showBuy = e && 200 <= g.width;
            f = {source: "zone-" + this.id + "-" + A.hostname(),
                medium: "logo_bar_cpc", campaign: "EC:w" + l + ":m" + (a.getMonth() + 1) + ":y" + (a.getYear() - 100)};
            g.utm = {logo_bar: function() {
                    var a;
                    a = [];
                    for (d in f)
                        k = f[d], a.push("utm_" + d + "=" + k);
                    return a
                }().join("&")};
            u = g.banners;
            e = l = 0;
            for (r = u.length; l < r; e = ++l)
                a = u[e], a.firstRow = e < g.col, a.adjust = e >= g.total - g.col ? g.adjustedHeight : 0, a.url = this.links[e];
            try {
                this.isSticky() && (b = this.getStickyParams().container, this._container_height = b.offsetHeight)
            } catch (z) {
                C(z)
            }
            this.elem.innerHTML = (0, S[g.layout])(g);
            this.rendered = !0;
            this.elem.setAttribute("data-rendered",
                    "true");
            this.renderTimestamp = Date.now();
            this.zoneDiv = null != (h = null != (n = this.elem.firstElementChild) ? n : this.elem.children[0]) ? h : {};
            b = this.features;
            h = this.elem.style;
            if (null != b.floating) {
                e = ["top", "left", "bottom", "right"];
                n = 0;
                for (a = e.length; n < a; n++)
                    if (g = e[n], k = b.floating[g])
                        this.floating = !0, h[g] = "" + k + "px";
                this.floating && (this.elem.className += " zone-floating", h.zIndex = "999999")
            }
            b.alternativeLogo && (this.zoneDiv.className += " no-logo");
            q = this;
            setTimeout(function() {
                if (q.isInViewPort(50))
                    return q.logTrueImpression()
            },
                    1E3);
            this.isSticky() && this.bindSticky();
            return this
        };
        b.prototype.isInViewPort = function(a) {
            var b;
            a = ~~a;
            b = this.elem.getBoundingClientRect();
            M = w.documentElement;
            return null != b && b.bottom >= a && b.right >= a && b.top <= M.clientHeight - a && b.left <= M.clientWidth - a
        };
        b.prototype.buildImpressionQuery = function(a, b) {
            var e, d, f, g, h;
            f = new Date;
            e = encodeURIComponent;
            e = ["" + a + "/l?action_name=" + e((null != (g = w.title) ? g : "Untitled Page").substring(0, 255)), "h=" + f.getHours(), "m=" + f.getMinutes(), "s=" + f.getSeconds(), "url=" + e(A.location()),
                "urlref=" + e(A.referrer()), "hostname=" + e(A.hostname()), "beacon=" + this.beacon, "res=" + A.resolution(), "zone_format=" + (null != (h = {metro: 2, promotion: 3}[this.view.layout]) ? h : 1)];
            for (d in b)
                g = b[d], e.push("" + d + "=" + g);
            return e.join("&")
        };
        b.prototype.logImpression = function() {
            var a, b, e, d;
            this.impression.logged || (a = this.impression, b = [], d = this.id, r.forEach(this.banners, function(a, d) {
                return b.push(a.id)
            }), e = "http://d.eclick.vn/delivery/tracking/ga.html?actionType=impression&bannerId=" + b.join(",") + "&zone=" + d, R(e,
                    "eClick-" + d), I(this.buildImpressionQuery("http://l.d.eclick.vn", {}), function() {
                return a.logged = !0
            }));
            return this
        };
        b.prototype.logTrueImpression = function() {
            var a, b, e, d, f, g;
            b = Date.now();
            d = this.trueImpression;
            if (d.impressed)
                return this;
            this.zoneDiv.className += " ecl-impressed";
            d.impressed = !0;
            d.time = b - this.renderTimestamp;
            b = null != (f = L.flip) ? f : this.features.flip;
            null != b && (a = 0 === ~~Math.random() * (null != (g = b.rate) ? g : 1));
            a && (this.zoneDiv.className += " flip");
            a = this.elem.getBoundingClientRect();
            a = this.buildImpressionQuery("http://t.d.eclick.vn",
                    {rect: {top: a.top, right: a.right, height: a.height, left: a.left, right:a.right, width: a.width}, t2t: d.time});
            I(a, function() {
                return d.logged = !0
            });
            e = [];
            f = this.id;
            r.forEach(this.banners, function(a, b) {
                return e.push(a.id)
            });
            a = "http://d.eclick.vn/delivery/tracking/ga.html?actionType=trueImp&bannerId=" + e.join(",") + "&zone=" + f;
            R(a, "eClick-" + f);
            return this
        };
        b.prototype.getBannerElems = function() {
            var a, b;
            a = this.elem;
            b = function(a) {
                var b;
                a = a.getElementsByTagName("a");
                b = [];
                r.forEach(a, function(a) {
                    var c;
                    c = String(a.className ||
                            a.getAttribute("class"));
                    if (/(^|\s)banner(\s|$)/.test(c))
                        return b.push(a)
                });
                return b
            };
            return("function" === typeof a.querySelectorAll ? a.querySelectorAll(".banner") : void 0) || ("function" === typeof a.getElementsByClassName ? a.getElementsByClassName("banner") : void 0) || b(a)
        };
        b.prototype.bind = function() {
            var a;
            a = {};
            r.forEach(this.banners, function(b) {
                return a[b.id] = b
            });
            r.forEach(this.getBannerElems(), function(b) {
                var e;
                e = b.getAttribute("data-banner-id");
                return a[e].bind(b)
            });
            return this
        };
        b.prototype.isSticky =
                function() {
                    return null != this.getStickyParams()
                };
        b.prototype.getStickyParams = function() {
            var a;
            a = this;
            r.forEach(v.sticky, function(b) {
                if (a.id === b.id)
                    return a.sticky = b
            });
            return this.sticky
        };
        b.prototype.bindSticky = function() {
            var a, b, e, d, f, g, h, k;
            a = this.getStickyParams();
            f = a.params;
            a = a.container;
            g = this.elem.style;
            if (null != this._sticky)
                return this;
            d = this._container_height = a.offsetHeight;
            g.position = "absolute";
            if (d !== a.offsetHeight)
                return this._container_height = a.offsetHeight, g.position = "static", this;
            null ==
                    f && (f = {top: 5, bottom: 5});
            null == f.top && (f.top = 5);
            null == f.bottom && (f.bottom = 5);
            b = this.elem;
            e = $(b).top - f.top;
            k = $(a).top + a.offsetHeight - b.offsetHeight;
            h = g.top;
            F(this.zoneDiv, "eclick-stick-zone");
            a = function() {
                var a;
                a = aa().top;
                if (a <= e)
                    return H(b, "eclick-sticky-active"), H(b, "eclick-sticky-overactive"), g.position = "relative", g.top = h;
                if (a <= k)
                    return F(b, "eclick-sticky-active"), H(b, "eclick-sticky-overactive"), g.position = "fixed", g.top = "" + f.top + "px";
                H(b, "eclick-sticky-active");
                F(b, "eclick-sticky-overactive");
                g.position =
                        "absolute";
                return g.top = "" + k + "px"
            };
            N(B, "scroll", a);
            a();
            return this._sticky = !0
        };
        return b
    }();
    fa = function() {
        function b(a) {
            var c;
            if (this === B)
                return new b(a);
            r.extend(this, a);
            this.click = {logger: !1};
            this.hostname = null != (c = this.hostname) ? c.replace(/^(https?:\/\/)?(www\.)?/i, "") : void 0;
            17 === +this.bannerType && null != this.ribbonType && (this.ribbonClass = "banner-promotion-" + this.ribbonType)
        }
        b.prototype.bind = function(a) {
            var b;
            b = this;
            N(a, "click", function() {
                if (!b.click.clicked)
                    return a.className += " clicked", b.click.clicked =
                            !0, b.logClick()
            });
            return b
        };
        b.prototype.buildClickQuery = function(a) {
            var b, e, d;
            e = Date.now();
            b = encodeURIComponent;
            return a + "/l?" + ["link=" + b(this.url), "ts=" + e, "url=" + b(A.location()), "urlref=" + b(A.referrer()), "hostname=" + b(A.hostname()), "beacon=" + this.zone.beacon, "t2c=" + (e - this.zone.renderTimestamp), "t2t=" + this.zone.trueImpression.time, "res=" + A.resolution(), "zone_format=" + (null != (d = {metro: 2, promotion: 3}[this.zone.view.layout]) ? d : 1)].join("&")
        };
        b.prototype.logClick = function() {
            var a, b, e, d, f;
            if (this.click.logged)
                return this;
            a = this;
            f = ["http://l.d.eclick.vn", "http://c.d.eclick.vn"];
            e = 0;
            for (d = f.length; e < d; e++)
                b = f[e], I(this.buildClickQuery(b), function() {
                    return a.click.logged = !0
                });
            return R("http://d.eclick.vn/delivery/tracking/ga.html?actionType=click&bannerId=" + a.id + "&zone=" + a.zone.id, "eClick")
        };
        return b
    }();
    ea = function() {
        function b(a) {
            r.extend(this, a);
            if (null == this.id)
                throw Error("init a zone without id");
            v.cache.zones[this.id] = this;
            this.placement = "balloon";
            this.expandDuration = 15050;
            this.trueImpressionDuration = 1E3;
            this.trueImpressionTimeout =
                    this.expandedTimeout = null;
            this.impressions = {log: !1, secondsOnExpand: 0, secondsOnHover: 0};
            this.clicks = {log: !1};
            this.timestamps = {init: Date.now()};
            this.render().expand()
        }
        b.prototype.buildLogQuery = function(a, b, e) {
            var d;
            null == e && (e = {});
            a = ["" + a + "/cpm/" + ("click" === b ? "c" : "i") + "?ts=" + Date.now() + "&ev=130606", "&url=" + A.location() + "&beacon=" + this.log[1], "&placement=2", "&res=" + A.resolution(), "&t2t=" + (this.timestamps.trueImpression - this.timestamps.rendered)];
            for (d in e)
                b = e[d], a.push("&" + d + "=" + b);
            return a.join("")
        };
        b.prototype.logImpression = function() {
            var a;
            if (this.impressions.logged)
                return this;
            a = this.impressions;
            this.timestamps.trueImpression = Date.now();
            I(this.buildLogQuery("http://t.d.eclick.vn", "impression"), function() {
                return a.logged = !0
            });
            return this
        };
        b.prototype.logClick = function() {
            var a;
            if (this.clicks.logged)
                return this;
            a = this.clicks;
            I(this.buildLogQuery("http://t.d.eclick.vn", "click", {click_beacon: this.log[0]}), function() {
                return a.logged = !0
            });
            return this
        };
        b.prototype.expand = function() {
            var a, b, e, d, f,
                    g, h, k;
            C("expanded");
            a = this.elem;
            d = this;
            this.collapsed = !1;
            H(a, "eclick-collapsed");
            F(a, "eclick-expanded");
            b = null != (e = this.banner) ? e.files.main : void 0;
            null != b && (e = null != (f = null != (g = b.dimensions) ? g.width : void 0) ? f : 300, b = null != (h = null != (k = b.dimensions) ? k.height : void 0) ? h : 300, a.style.clip = "rect(0px " + e + "px " + b + "px 0px)");
            clearTimeout(this.expandedTimeout);
            this.expandedTimeout = setTimeout(function() {
                return d.collapse()
            }, this.expandDuration);
            clearTimeout(this.trueImpressionTimeout);
            if (!this.log)
                return this;
            this.trueImpressionTimeout = setTimeout(function() {
                return d.logImpression()
            }, this.trueImpressionDuration)
        };
        b.prototype.collapse = function() {
            var a, b, e, d, f, g, h;
            C("collapsed");
            a = this.elem;
            this.collapsed = !0;
            H(a, "eclick-collapsed");
            F(a, "eclick-expanded");
            b = null != (e = this.banner) ? e.files.main : void 0;
            null != b && (e = null != (d = null != (f = b.dimensions) ? f.width : void 0) ? d : 300, b = null != (g = null != (h = b.dimensions) ? h.height : void 0) ? g : 300, a.style.clip = "rect(" + (b - 30) + "px " + e + "px " + b + "px 0px)");
            clearTimeout(this.expandedTimeout);
            clearTimeout(this.trueImpressionTimeout)
        };
        b.prototype.close = function() {
            this.collapsed = this.closed = !0;
            this.elem.style.display = "none";
            F(this.elem, "elick-closed");
            clearTimeout(this.expandedTimeout)
        };
        b.prototype.click = function() {
            C("clicked");
            this.logClick()
        };
        b.prototype.render = function() {
            var a, b, e, d;
            b = "eClick.getZone(" + this.id + ")";
            e = ["eClick_event_clicked=" + b + ".click", "eClick_event_expanded=" + b + ".expand", "eClick_event_collapsed=" + b + ".collapse", "eClick_event_closed=" + b + ".close"].join("&");
            d = this;
            a = this.elem;
            O.get("http://cpm.d.eclick.vn?url=" + A.location() + "&zoneId=" + this.id, {}, function(b) {
                var c;
                if (null == (null != b ? b.data : void 0))
                    return C("error", b.meta);
                r.extend(d, b.data);
                c = d.banner;
                b = c.files.main;
                null == b.dimensions && (b.dimensions = {width: 300, height: 300});
                c = ga({id: "eclick-zone-" + d.id, width: "" + b.dimensions.width, height: "" + b.dimensions.height, fileUrl: "" + b.url + "?clickTAG=" + c.url, flashVars: e});
                a.innerHTML = c;
                a.style.width = b.dimensions.width;
                a.style.height = b.dimensions.height;
                d.timestamps.rendered = Date.now();
                return d.trueImpressionTimeout = setTimeout(function() {
                    return d.logImpression()
                }, d.trueImpressionDuration)
            });
            return this
        };
        return b
    }();
    T(B, Q);
    V = B.eClick = {version: 275692097, appendTo: function(b, a) {
            var c;
            c = P(a);
            b.appendChild(c);
            Q();
            return c
        }, prependTo: function(b, a) {
            var c;
            c = P(a);
            b.insertBefore(c, b.firstChild);
            Q();
            return c
        }, stickyTo: Z, preview: function(b, a) {
            var c, e, d;
            d = {row: 1, col: 1, total: 1, utm: {logo_bar: "utm_source=zone-preview&utm_medium=logo_bar_cpc&utm_campaign=EC:preview"}, banners: [b]};
            c = r.extend({format: "classic"},
            a);
            b.forstRow = !0;
            b.id = b.id || "preview";
            null != b.hostname && (b.hostname = b.hostname.replace(/^(https?:\/\/)?(www\.)?/i, ""));
            17 === b.bannerType && (c.format = "promotion", null != b.ribbonType && (b.ribbonClass = "banner-promotion-" + b.ribbonType));
            c.format.match(/^metro|promotion$/g) ? (d.layout = c.format, d.width = d.height = 150, d.className = d.layout + "-1-1") : (d.layout = 14 === b.bannerType ? "widget-float" : "context-float-2", d.direction = "horizontal", d.color = "yellow", d.width = c.width || 300, d.height = c.height || 110, d.padding = 10, b.adjust =
                    d.adjustedHeight = 0, d.actualSize = {width: d.width - 18, height: d.height - 1}, d.bannerWidth = d.width - 18, d.bannerHeight = d.height - 1);
            e = S[d.layout];
            c = P(b.id);
            c.innerHTML = e(d);
            c.setAttribute("data-rendered", "true");
            return c
        }, getZone: function(b) {
            return v.cache.zones[b]
        }, balloon: function(b) {
            var a;
            if (v.cache.zones[b])
                return C("zone " + b + " already set up");
            a = w.createElement("div");
            a.className = "eclick-zone-balloon";
            a.style.position = "fixed";
            a.style.zIndex = 99999;
            a.style.bottom = 0;
            a.style.right = 0;
            w.body.appendChild(a);
            return v.balloonZones.push(new ea({id: b,
                elem: a}))
        }};
    B.jQuery && (jQuery.fn.eClickSticky = function(b, a) {
        return Z(b, this, a)
    });
    (function(b, a, c) {
        T(b, function() {
            var e = function() {
                var a = new Date;
                return(new Date(a.getFullYear(), a.getMonth(), a.getDate())).getTime()
            }, d = function() {
                var a = new Date;
                return(new Date(a.getFullYear(), a.getMonth(), a.getDate(), a.getHours())).getTime()
            }, f = function() {
            }, g = function(b) {
                var c = function(c, d, e) {
                    var g = !!c && !!d && !!e, k, l = 0, p = function(a) {
                        var c = "adFreCb" + ++l;
                        b[c] = a;
                        return c
                    };
                    g && (k = function() {
                        N(b, "message", function(a) {
                            try {
                                var c =
                                        e.parse(a.data), d = b[c.callback];
                                "function" == typeof d && d(c.data)
                            } catch (f) {
                            }
                        });
                        var c = a.createElement("iframe");
                        c.src = "http://d.eclick.vn/delivery/proxy.html";
                        c.id = "eclick-proxy-" + Math.random().toString().split(".")[1];
                        c.width = 0;
                        c.height = 0;
                        c.style.display = "none";
                        a.body.appendChild(c);
                        return c.contentWindow
                    }());
                    return g ? {isSupported: function() {
                            return g
                        }, get: function(a, b) {
                            k.postMessage(e.stringify({action: "get", key: a, callback: p(b)}), "*")
                        }, set: function(a, b) {
                            k.postMessage(e.stringify({action: "set", key: a,
                                value: e.stringify(b)}), "*")
                        }, expireOldKey: function(a, b) {
                            k.postMessage(e.stringify({action: "expireOldKey", prefix: a, time: b}), "*")
                        }, getProxy: function() {
                            return k
                        }} : {isSupported: function() {
                            return!1
                        }, get: f, set: f}
                }(b.postMessage, b.localStorage, b.JSON), g = c.isSupported();
                getDayKey = function(a) {
                    return"evfd-" + a
                };
                getHourKey = function(a) {
                    return"evfh-" + a
                };
                getLifetimeKey = function() {
                    return"evfl"
                };
                expireOldKey = function() {
                    c.expireOldKey("evfd-", e());
                    c.expireOldKey("evfh-", d())
                };
                g && T(b, expireOldKey);
                return g ? {isSupported: function() {
                        return g
                    },
                    get: function(a, b, f) {
                        var g = getDayKey(e()), h = getHourKey(d());
                        lifetime = getLifetimeKey();
                        c.get([g, h, lifetime].join(), function(c) {
                            var d, e = {};
                            if (b)
                                for (d in c)
                                    0 == d.indexOf("evfd-") ? e.day = c[d] : 0 == d.indexOf("evfh-") ? e.hour = c[d] : 0 == d.indexOf("evfl") && (e.lifetime = c[d]);
                            else
                                e = c;
                            if (f)
                                for (d in e)
                                    e[d] = null != e[d] ? JSON.stringify(e[d]) : "";
                            a(e)
                        })
                    }, set: function(a) {
                        var b = getDayKey(e()), f = getHourKey(d()), g = getLifetimeKey(), h, l;
                        c.get(b, function(d) {
                            h = d[b] || {};
                            h.hasOwnProperty(a) ? h[a] += 1 : h[a] = 1;
                            c.set(b, h)
                        });
                        c.get(f, function(b) {
                            l =
                                    b[f] || {};
                            l.hasOwnProperty(a) ? l[a] += 1 : l[a] = 1;
                            c.set(f, l)
                        });
                        c.get(g, function(b) {
                            lifetimeData = b[g] || {};
                            lifetimeData.hasOwnProperty(a) ? lifetimeData[a] += 1 : lifetimeData[a] = 1;
                            c.set(g, lifetimeData)
                        })
                    }} : {isSupported: function() {
                        return!1
                    }, get: function(a, b) {
                        if (b) {
                            var c = {};
                            c[getDayKey(e())] = c[getHourKey(d())] = c[getLifetimeKey()] = null;
                            a(c)
                        } else
                            a({day: null, hour: null, lifetime: null})
                    }, set: f}
            }(b);
            -1 < b.location.href.indexOf("FOSP_DEBUG") && (b.Frequency = g);
            b[c] = {getAd: function() {
                    var c = !1;
                    return function(d, e, f) {
                        c || (expireOldKey(),
                                c = !0);
                        g.get(function(c) {
                            O.get("http://cpm.d.eclick.vn/ar", {zone_id: d, url: b.location.href, fd: c.day, fh: c.hour, fl: c.lifetime}, function(c) {
                                var d = -1 != navigator.appName.indexOf("Microsoft") ? b[e] : a[e];
                                d && "function" == typeof d[f] && d[f](c)
                            })
                        }, !0, !0)
                    }
                }(), trackAd: function(a, b, c, d) {
                    (new Image).src = a;
                    "start" == d && g.set(b)
                }}
        })
    })(window, document, "ECLIMA");
    window.chrome && (L = function(b) {
        b = b.substring(1).split("&");
        var a = {};
        r.forEach(b, function(b) {
            b = b.split("=");
            if (b[0].match(/^ec_/)) {
                if ("true" === b[1] || "false" === b[1])
                    b[1] =
                            "true" === b[1];
                a[b[0].substring(3)] = b[1]
            }
        });
        return a
    }(window.location.search), (K = !0 === L.debug) && L.ctx_url && (A.location = function() {
        return decodeURIComponent(L.ctx_url)
    }))
})();
