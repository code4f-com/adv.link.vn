<script type="text/javascript" language="javascript">
//<![CDATA[

    window.onload = function()
    {
        document.getElementById("d2").onclick = slideIt;
    };

    function slideIt()
    {
        var slidingDiv = document.getElementById("d1");
        var stopPosition = 50;
        if (parseInt(slidingDiv.style.left) < stopPosition)
        {
            slidingDiv.style.left = parseInt(slidingDiv.style.left) + 2 + "px";
            setTimeout(slideIt, 1000);
        }
    }

//]]>
</script>
</head>
<body>

    <div id="d2" style="border: 1px solid #000099">click here to slide the div</div>
    <div id="d1" style="position:absolute; left:-150px; top:30px">horizontally sliding div</div>

</body>
</html>