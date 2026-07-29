function txtstrike(arrEId, storeId, strikeClass) {
    if ($.trim($(storeId).val()) == '1') {
        $(storeId).val('0');
        jQuery.each(arrEId, function (index) {
            $(arrEId[index]).removeClass(strikeClass);
        });
    }
    else {
        $(storeId).val('1');
        jQuery.each(arrEId, function (index) {
            $(arrEId[index]).addClass(strikeClass);
        });
    }
}
//Edit the counter/limiter value as your wish
function prlabel_limiter(countLimit, eIdCheck, eIdCount, arrReviewId, errEId, e) {
    var chk = true;
    var hasEvt = false;

    var tmp = $(eIdCheck).val();
    try {
        if (detectEnc(tmp) > 1) {
            tmp = convert2Unicode(tmp);
            $(eIdCheck).val(tmp);
        }
    }
    catch (err) {
        //Handle errors here
    }

    if (typeof(e) == 'object') {
        hasEvt = true;
        var keyCode = (e.keyCode ? e.keyCode : e.which);
        if (!(keyCode == 8 || keyCode == 32 || (keyCode >= 41 && keyCode <= 127))) {
            chk = false;
        }
    }

    if (chk) {
        var tex = $(eIdCheck).val();
        if (!hasEvt) {
            if (Encoder.hasEncoded(tex)) {
                tex = Encoder.htmlDecode(tex);
            }
            if (chkHtmlTags(tex)) {
                tex = stripHtmlTags(tex);
                $(eIdCheck).val(tex);
            }
        }
        var len = tex.length;
        if (len > countLimit) {
            tex = tex.substring(0, countLimit);
            $(eIdCheck).val(tex);
            var num = arrReviewId.length;
            jQuery.each(arrReviewId, function (index) {
                $(arrReviewId[index]).html(tex + ':').show();
            });
        }
        else {
            $(eIdCount).html(countLimit - len);
            jQuery.each(arrReviewId, function (index) {
                if ($.trim(tex) == '') {
                    $(arrReviewId[index]).html('').hide();
                }
                else {
                    $(arrReviewId[index]).html(tex + ':').show();
                }
            });

            if (errEId != '') {
                if ($.trim(tex) == '') {
                    showError(true, eIdCheck, errEId);
                }
                else {
                    showError(false, eIdCheck, errEId);
                }
            }
        }
    }
}

function price_limiter(countLimit, eIdCheck, eIdCount, arrReviewId, arrChkReview, eVndId, errEId, e) {
    var chk = true;
    var hasEvt = false;
    var vnd = $.trim($(eVndId).val());
    vnd = (vnd.toLowerCase() == 'đ' || vnd.toLowerCase() == '%') ? vnd : 'đ';
    var tmp = $(eIdCheck).val();
    try {
        if (detectEnc(tmp) > 1) {
            tmp = convert2Unicode(tmp);
            $(eIdCheck).val(tmp);
        }
    }
    catch (err) {
        //Handle errors here
    }

    if (typeof(e) == 'object') {
        hasEvt = true;
        var keyCode = (e.keyCode ? e.keyCode : e.which);
        if (!(keyCode == 8 || keyCode == 32 || (keyCode >= 41 && keyCode <= 127))) {
            chk = false;
        }
    }
    if (chk) {
        var tex = $(eIdCheck).val();
        if (!hasEvt) {
            if (Encoder.hasEncoded(tex)) {
                tex = Encoder.htmlDecode(tex);
            }
            if (chkHtmlTags(tex)) {
                tex = stripHtmlTags(tex);
                $(eIdCheck).val(tex);
            }
        }
        var len = tex.length;
        var tmptex = '';
        for (var i = 0; i < len; i++) {
            if (tex[i] == '0' || tex[i] == '1' || tex[i] == '2' || tex[i] == '3' || tex[i] == '4' || tex[i] == '5' || tex[i] == '6' || tex[i] == '7' || tex[i] == '8' || tex[i] == '9') {
                if (i > 0) {
                    if (tmptex != '0') {
                        tmptex += tex[i];
                    }
                    else {
                        tmptex = tex[i];
                    }
                }
                else {
                    tmptex += tex[i];
                }
            }
        }

        tex = tmptex;
        var len = tex.length;
        if (len > countLimit) {
            tex = tex.substring(0, countLimit);
            tex = addCommas(tex);
            $(eIdCheck).val(tex);
            jQuery.each(arrReviewId, function (index) {
                $(arrReviewId[index]).html(tex + vnd);
            });
        }
        else {
            $(eIdCount).html(countLimit - len);
            tex = addCommas(tex);
            $(eIdCheck).val(tex);
            jQuery.each(arrReviewId, function (index) {
                if ($.trim(tex) == '') {
                    $(arrReviewId[index]).html('');
                }
                else {
                    $(arrReviewId[index]).html(tex + vnd);
                }
            });

            jQuery.each(arrChkReview, function (index) {
                if ($.trim(tex) == '') {
                    $(arrChkReview[index]).hide();
                }
                else {
                    $(arrChkReview[index]).show();
                }
            });

            if (errEId != '') {
                if ($.trim(tex) == '') {
                    showError(true, eIdCheck, errEId);
                }
                else {
                    showError(false, eIdCheck, errEId);
                }
            }
        }
        chkPriceReview();
    }
}

function slcPrTypeChange(ePrId, arrReviewId, obj) {
    var vnd = $.trim($(obj).val());
    vnd = (vnd.toLowerCase() == 'đ' || vnd.toLowerCase() == '%' || vnd.toLowerCase() == '') ? vnd : 'đ';
    var price = $.trim($(ePrId).val());
    jQuery.each(arrReviewId, function (index) {
        if (price != '') {
            $(arrReviewId[index]).html(price + vnd).show();
        }
    });
}

function setp1_limiter(countLimit, eIdCheck, eIdCount, arrReviewId, errEId, arrChkReview, e, chkNum, isCallBack) {
    var isShow = (typeof(arrReviewId) === 'undefined' || arrReviewId === '') ? false : true;
    var isChkReviewShow = (typeof(arrChkReview) === 'undefined' || arrChkReview === '') ? false : true;
    var isCallBack = (typeof(isCallBack) === 'undefined' || isCallBack === '') ? false : true;

    errEId = (typeof(errEId) === 'undefined' || errEId === '') ? '' : errEId;
    countLimit = (typeof(countLimit) === 'undefined' || countLimit === '') ? 0 : countLimit;
    chkNum = (typeof(chkNum) === 'undefined' || chkNum === '') ? false : chkNum;
    var chk = true;
    var hasEvt = false;

    var tmp = $(eIdCheck).val();
    try {
        if (detectEnc(tmp) > 1) {
            tmp = convert2Unicode(tmp);
            $(eIdCheck).val(tmp);
        }
    }
    catch (err) {
        //Handle errors here
    }

    if (typeof(e) == 'object') {
        hasEvt = true;
        var keyCode = (e.keyCode ? e.keyCode : e.which);
        if (!(keyCode == 8 || keyCode == 32 || (keyCode >= 41 && keyCode <= 127))) {
            chk = false;
        }
    }
    if (chk) {
        var tex = $(eIdCheck).val();
        if (chkNum) {
            tex = tex.replace(/,/gi, '');
        }
        if (!hasEvt) {
            if (Encoder.hasEncoded(tex)) {
                tex = Encoder.htmlDecode(tex);
            }
            if (chkHtmlTags(tex)) {
                tex = stripHtmlTags(tex);
                $(eIdCheck).val(tex);
            }
        }
        var len = tex.length;
        if (countLimit > 0) {
            if (len > countLimit) {
                tex = tex.substring(0, countLimit);
                if (chkNum) {
                    tex = tex != '' ? addCommas(tex) : tex;
                }
                $(eIdCheck).val(tex);
                if (isShow) {
                    var num = arrReviewId.length;
                    for (var i = 0; i < num; i++) {
                        $(arrReviewId[i]).html(tex);
                    }
                }
                //return false;
            }
            else {
                if (chkNum) {
                    tex = tex != '' ? addCommas(tex) : tex;
                    $(eIdCheck).val(tex);
                }
                $(eIdCount).html(countLimit - len);
                if (isShow) {
                    var num = arrReviewId.length;
                    for (var i = 0; i < num; i++) {
                        $(arrReviewId[i]).html(tex);
                    }
                }
                if (errEId != '') {
                    if ($.trim(tex) == '') {
                        showError(true, eIdCheck, errEId);
                    }
                    else {
                        showError(false, eIdCheck, errEId);
                    }
                }
            }

            if (isChkReviewShow) {
                var num = arrChkReview.length;
                if ($.trim(tex) == '') {
                    for (var i = 0; i < num; i++) {
                        $(arrChkReview[i]).hide();
                    }
                }
                else {
                    for (var i = 0; i < num; i++) {
                        $(arrChkReview[i]).show();
                    }
                }
            }

            if (isCallBack) {
                chkPriceReview();
            }
        }
        else {
            if (errEId != '') {
                if ($.trim(tex) == '') {
                    showError(true, eIdCheck, errEId);
                }
                else {
                    showError(false, eIdCheck, errEId);
                }
            }

            if (isChkReviewShow) {
                var num = arrChkReview.length;
                if ($.trim(tex) == '') {
                    for (var i = 0; i < num; i++) {

                        $(arrChkReview[i]).hide();
                    }
                }
                else {
                    for (var i = 0; i < num; i++) {
                        $(arrChkReview[i]).show();
                    }
                }
            }
            if (chkNum) {
                tex = tex != '' ? addCommas(tex) : tex;
                $(eIdCheck).val(tex);
            }
            if (isShow) {
                var num = arrReviewId.length;
                for (var i = 0; i < num; i++) {
                    $(arrReviewId[i]).html(tex);
                }
            }
            if (isCallBack) {
                chkPriceReview();
            }

        }
    }
}

function chkPriceReview() {
    var priceLabel = $.trim($('#pricelabel').val());
    var dprice = $.trim($('#txtdprice').val());
    var rprice = $.trim($('#txtrprice').val());

    if (priceLabel == '') {
        $('#review_prlabel').hide();
    }
    else {
        $('#review_prlabel').show();
    }

    if (dprice == '') {
        $('#review_dprice').hide();
    }
    else {
        $('#review_dprice').show();
    }

    if (rprice == '') {
        $('#review_rprice_box').hide();
    }
    else {
        $('#review_rprice_box').show();
    }
    if (dprice == '' && rprice == '') {
        $('#divReviewPrice').hide();
    }
    else {
        $('#divReviewPrice').show();
    }
}

function chkMaxWord(str, numMax) {
    var arr = str.split(' ');
    var num = arr.length;
    for (var i = 0; i < num; i++) {
        arr[i] = $.trim(arr[i]);
        if (arr[i].length > numMax) {
            return false;
        }
    }
    return true;
}

function step1_desUrlKeyUp(eId, errEId) {
    var tex = $.trim($(eId).val());
    if (tex == '') {
        showError(true, eId, errEId);
        $('.s1ReviewBuy1').attr('href', 'javascript:void(0);');
        $('#review_linkimage').attr('href', 'javascript:void(0);');
        $('#review_buy').attr('href', 'javascript:void(0);');
    }
    else {
        if (!isUrl(tex)) {
            showError(true, eId, errEId);
            $('.s1ReviewBuy1').attr('href', 'javascript:void(0);');
            $('#review_linkimage').attr('href', 'javascript:void(0);');
            $('#review_buy').attr('href', 'javascript:void(0);');
        }
        else {
            showError(false, eId, errEId);
            var url = $.trim($('#desUrl').val());
            $('.s1ReviewBuy1').attr('href', url);
            $('#review_linkimage').attr('href', url);
            $('#review_buy').attr('href', url);
        }
    }
}

function move2Step(stepId, time) {
    time = (typeof(time) == 'undefined' || time == '') ? 1000 : time;
    $('html,body').animate({scrollTop: $(stepId).position().top}, time);
}

function showError(isShow, tagId, errorId) {
    if (isShow) {
        if (tagId) {
            $(tagId).addClass('error-border');
        }
        if (errorId) {
            $(errorId).show();
        }
    }
    else {
        if (tagId) {
            $(tagId).removeClass('error-border');
        }
        if (errorId) {
            $(errorId).hide();
        }
    }
}

function step1_continue_click() {
    var strAlert = '';
    var chkUrl = true;
    var chkCat = true;
    var chkImg = true;
    var chkBtext = true;
    var chkDprice = true;
    var chkRprice = true;
    var chkBtnName = true;
    var chkBuyer = true;
    var chkOptConversion = true;

    var desUrl = $.trim($('#desUrl').val());
//    var desUrlDefault = $.trim($('#desUrlDefault').val());
    if (!isUrl(desUrl) || desUrl == '') {
        showError(true, '#desUrl', '#errorDesUrl');
        checkBodyText = false;
    }
    else {
        showError(false, '#desUrl', '#errorDesUrl');
        checkBodyText = true;
    }
    // thuong hieu
    var cat = $.trim($('#txtCat').val());
//    var catDefault = $.trim($('#txtCatDefault').val());
    if (cat == '') {
        showError(true, '#txtCat', '#errCatTitle');
        chkCat = false;
    }
    else {
        showError(false, '#txtCat', '#errCatTitle');
        chkCat = true;
    }

    // tieu de
    var title = $.trim($('#title').val());
//    var titleDefault = $.trim($('#titleDefault').val());
    if (title == '') {
        showError(true, '#title', '#errorTitle');
        chkTitle = false;
    }
    else {
        showError(false, '#title', '#errorTitle');
        chkTitle = true;
    }


    // image
    var imageFile = $.trim($('#hddFileName').val());
    if (imageFile == '') {
        showError(true, '', '#errorStep1Image');
        chkImg = false;
    }
    else {
        showError(false, '', '#errorStep1Image');
        chkImg = true;
    }

    // body text
    var bodyText = $.trim($('#step1BodyText').val());
//    var bodyTextDefault = $.trim($('#step1BodyTextDefault').val());
    if (bodyText == '') {
        showError(true, '#step1BodyText', '#errorBodyText');
        chkBtext = false;
    }
    else {
        showError(false, '#step1BodyText', '#errorBodyText');
        chkBtext = true;
    }

    // dis price
    var dprice = $.trim($('#txtdprice').val());
    if (dprice != '') {
        if (dprice != '0') {
            dprice = dprice.replace(/,/gi, '');
            dprice = parseFloat(dprice);
            if (dprice == 0 || dprice == '') {
                showError(true, '#txtdprice', '#errdprice');
                chkDprice = false;
            }
            else {
                showError(false, '#txtdprice', '#errdprice');
                chkDprice = true;
            }
        }
    }

    // real price
    var rprice = $.trim($('#txtrprice').val());
    if (rprice != '') {
        rprice = rprice.replace(/,/gi, '');
        rprice = parseFloat(rprice);
        if (rprice == 0 || rprice == '') {
            showError(true, '#txtrprice', '#errrprice');
            chkRprice = false;
        }
        else {
            showError(false, '#txtrprice', '#errrprice');
            chkRprice = true;
        }
    }

    // button name
    var btnName = $.trim($('#txtbutton').val());
    if (btnName != '') {
        if (btnName.length > home_lang['max_button']) {
            showError(true, '#txtbutton', '#errrprice');
            chkBtnName = false;
        }
        else {
            showError(false, '#txtbutton', '#errrprice');
            chkBtnName = true;
        }
    }

    // buyer number
    var buyer = $.trim($('#txtnumber').val());
    if (buyer != '') {
        buyer = buyer.replace(/,/gi, '');
        buyer = parseFloat(buyer);
        if (buyer == 0 || buyer == '' || buyer.length > home_lang['max_buyer']) {
            showError(true, '#txtnumber', '#errBuyer');
            chkBuyer = false;
        }
        else {
            showError(false, '#txtnumber', '#errBuyer');
            chkBuyer = true;
        }
    }

    if ($('#optimize_conversion_page').is(":checked")) {
        if ($('#tracking_cpa_type').val() == '') {
            showError(true, '#tracking_cpa_type', '#errorTrkConversion');
            chkOptConversion = false;
        }
        else {
            showError(false, '#tracking_cpa_type', '#errorTrkConversion');
            chkOptConversion = true;
        }
    }

    strAlert = '';
    if (strAlert != '') {
        if (confirm(strAlert)) {
            if (chkUrl && chkCat && chkImg && chkBtext && chkRprice && chkDprice && chkBtnName && chkBuyer && chkOptConversion) {
                $('#step1_continue').hide();
                updateLocationReview();
                $('#divStep2').show();
                if ($('#optimize_conversion_page').is(":checked")) {
                    $('#divTarSite').hide();
                    $('#divEstimateSite').hide();
                } else {
                    $('#divTarSite').show();
                    $('#divEstimateSite').show();
                }
                showDivEstimate();
                move2Step('#divStep2', 1000);
            }
        }
    }
    else {
        if (chkUrl && chkCat && chkImg && chkBtext && chkRprice && chkDprice && chkBtnName && chkBuyer) {
            $('#step1_continue').hide();
            updateLocationReview();
            $('#divStep2').show();
            if ($('#optimize_conversion_page').is(":checked")) {
                $('#divTarSite').hide();
                $('#divEstimateSite').hide();
            } else {
                $('#divTarSite').show();
                $('#divEstimateSite').show();
            }
            showDivEstimate();
            move2Step('#divStep2', 1000);
        }
    }
}

function step2_continue_click() {

    var bac = ($('#chkBac').attr('checked')) ? '1' : '0';
    var trung = ($('#chkTrung').attr('checked')) ? '2' : '0';
    var nam = ($('#chkNam').attr('checked')) ? '3' : '0';

    var chkCity = ($.trim($('#txtTargetLocationCity').val()) == '') ? false : true;
    var chkChannel = $.trim($('#hddListTargetSiteChannelId').val()) == '' ? false : true;

    if ((bac != '0' || trung != '0' || nam != '0' || chkCity) && chkChannel) {
        $('#step2_continue').hide();
        $('#divStep3').show();
        move2Step('#divStep3', 1000);
    }
    else {
        step2ShowLocationErr();
        step2ShowSiteChannelError();
    }
    return;
}

function step2ShowLocationErr() {
    var bac = ($('#chkBac').attr('checked')) ? '1' : '0';
    var trung = ($('#chkTrung').attr('checked')) ? '2' : '0';
    var nam = ($('#chkNam').attr('checked')) ? '3' : '0';

    var chkCity = ($.trim($('#txtTargetLocationCity').val()) == '') ? false : true;

    if (bac == '0' && trung == '0' && nam == '0' && !chkCity) {
        $('#errorLocation').show();
    }
    else {
        $('#errorLocation').hide();
    }

    return;
}

function step2ShowSiteChannelError() {

    var chkChannel = $.trim($('#hddListTargetSiteChannelId').val()) == '' ? false : true;
    if (!chkChannel) {
        $('#errorSiteChannel').show();
    }
    else {
        $('#errorSiteChannel').hide();
    }

    return;
}

function step3_showDivCampOption(opt) {
    if (opt == 'create') {
        $('#endDate').val($('#campEndDate').val());
        $('#startDate').val($('#campStartDate').val());
        $('#divChooseCamp').hide();
        $('#divCreateCamp').show();
        $('#hddCampType').val('create');

        $('#hddCampDBanner').val('0');


        updateCreateCampainReview();
    }
    else if (opt == 'choose') {
        $('#divChooseCamp').show();
        $('#divCreateCamp').hide();
        $('#hddCampType').val('choose');

        var campid = $('#step3SlcChooseCamp').val();
        chooseCampChange(campid);
    }
}

function chooseCampChange(value, editValue) {
    editValue = (typeof(editValue) == 'undefined' || editValue == '') ? '' : editValue;
    if ($.trim(value) == '' || $.trim(value) == '0') {
        showDivChooseCampErr(true);
    }
    else {
        showDivChooseCampErr(false);
    }

    if (editValue == '' || editValue != value) {
        var onsSuccess = function (data) {
            $('#imgChangeCampLoading').hide();
            var objData = jQuery.parseJSON(data);
            var isperday = $.trim(objData.isperday);
            var maxValudDate = $.trim(objData.maxValueDay);
            var maxValueDateVnd = $.trim(objData.maxValueDayVnd);// maxValueDayVnd
            maxValudDate += ' ' + home_lang['vnd'] + ' ' + ( isperday == '0' ? home_lang['life_time'] : home_lang['per_day']);
            var endDate = $.trim(objData.endDate);
            var arrEndDate = endDate.split('-');
            var startDate = $.trim(objData.startDate);
            var arrStartDate = startDate.split('-');

            var isCampFromDay = $.trim(objData.isCampFromDay);
            if (isCampFromDay == '1') {
                var currDate = new Date();
                var currMonth = currDate.getMonth() + 1;
                var currDay = currDate.getDate();
                var currYear = currDate.getFullYear();
                currMonth = (currMonth < 10) ? '0' + currMonth : currMonth;
                var strCurrDate = currDay + '-' + currMonth + '-' + currYear;

                $('#rvcmpfromday').html(common_lang['camp_run_from_day'] + ' <b>' + strCurrDate + '</b>').show();
                $('#rvcmpduration').hide();
            }
            else {
                var currDate = new Date();
                var currMonth = currDate.getMonth() + 1;
                var currDay = currDate.getDate();
                var currYear = currDate.getFullYear();
                currMonth = (currMonth < 10) ? '0' + currMonth : currMonth;
                var strCurrDate = currDay + '-' + currMonth + '-' + currYear;
                var currDate = new Date(currYear + '/' + currMonth + '/' + currDay);

                var endDateObj = new Date(arrEndDate[2] + '/' + arrEndDate[1] + '/' + arrEndDate[0]);
                var startDateObj = new Date(arrStartDate[2] + '/' + arrStartDate[1] + '/' + arrStartDate[0]);

                var numCampDay = 0;
                if (startDateObj > currDate) {
                    $('#startDate').val(startDate);
                    numCampDay = ((endDateObj - startDateObj) / 86400000) + 1;
                    $('#review_camp_start').html(startDate);
                }
                else {
                    $('#startDate').val(strCurrDate);
                    numCampDay = ((endDateObj - currDate) / 86400000) + 1;
                    $('#review_camp_start').html(strCurrDate);
                }
                $('#rvcmpduration').show();
                $('#rvcmpfromday').html('').hide();
            }

            $('#step3_ChooseCampBudget').html(maxValudDate + ' &asymp; ' + maxValueDateVnd + ' click ' + ( isperday == '0' ? home_lang['life_time'] : home_lang['per_day']));
            $('#endDate').val(endDate);

            $('#review_num_camp_day').html(numCampDay);
            $('#review_camp_end').html(endDate);

            $('#hddChooseCampEndDate').val(endDate);
            $('#review_daily_budget').html(maxValudDate);
            $('#review_daily_budget_vnd').html(maxValueDateVnd + ' click ' + ( isperday == '0' ? home_lang['life_time'] : home_lang['per_day']));
            $('#review_camp_name').html($.trim($("#step3SlcChooseCamp option[value=" + $("#step3SlcChooseCamp").val() + "]").text()));

            $('#review_camp_hdcode').html(objData.contract);
            $('#review_camp_hd_guest').html(objData.label);

            if (objData.contract != '' || objData.label != '') {
                $('#tr_review_hdcode').show();
                $('#tr_review_hd_guest').show();
            }
            else {
                $('#tr_review_hdcode').hide();
                $('#tr_review_hd_guest').hide();
            }

        };
        var onError = function () {
            $('#imgChangeCampLoading').hide();
        };
        $('#imgChangeCampLoading').show();
        getAjax(makeSiteUrl('createad/get_campaign/') + value, '', '', '', '', false, onsSuccess, onError);
    }
}

function showDivCreateCampNameErr(isShow) {
    if (isShow) {
        $('#txtCampName').addClass('error-border');
        $('#errorCreateCampName').show();
    }
    else {
        $('#txtCampName').removeClass('error-border');
        $('#errorCreateCampName').hide();
    }
}

function showDivCreateCampBudgetErr(isShow) {
    if (isShow) {
        $('#txtCampBudget').addClass('error-border');
        $('#errorCreateCampBudget').show();
    }
    else {
        $('#txtCampBudget').removeClass('error-border');
        $('#errorCreateCampBudget').hide();
    }
}

function showDivCreateCampTimeErr(isShow) {
    if (isShow) {
        $('#errorCreateCampTime').show();
    }
    else {
        $('#errorCreateCampTime').hide();
    }
}

function showDivChooseCampErr(isShow) {
    if (isShow) {
        $('#errorChooseCampName').show();
    }
    else {
        $('#errorChooseCampName').hide();
    }
}

function step3_create_camp_name_keyup() {
    var tex = $.trim($('#txtCampName').val());
    if (tex != '') {
        showDivCreateCampNameErr(false);
    }
    else {
        showDivCreateCampNameErr(true);
    }
}

function step3_continue_click() {
    var chooseCampType = $('#hddCampType').val();
    // check if add new campaign
    if (chooseCampType == 'create') {
        var chkTime = true;
        var chkName = true;
        var chkBudget = true;
        var chkFromDay = $('#chkRunToday').attr('checked');
        if (!chkFromDay) {
            var arrStartDate = ($('#campStartDate').val()).split('-');
            var arrEndDate = ($('#campEndDate').val()).split('-');

            var startDate = new Date(arrStartDate[2] + '/' + arrStartDate[1] + '/' + arrStartDate[0]);
            var endDate = new Date(arrEndDate[2] + '/' + arrEndDate[1] + '/' + arrEndDate[0]);

            if (endDate < startDate) {
                showDivCreateCampTimeErr(true);
                chkTime = false;
            }
            else {
                chkTime = true;
                showDivCreateCampTimeErr(false);
            }
        }

        var campName = $.trim($('#txtCampName').val());
        if (campName == '') {
            showDivCreateCampNameErr(true);
            chkName = false;
        }
        else {
            chkName = true;
            showDivCreateCampNameErr(false);
        }

        var campBudget = $('#txtCampBudget').val();
        campBudget = campBudget.replace(/,/gi, '');
        campBudget = parseFloat(campBudget);

        var budType = $('#slcCampBudgetType').val();

        if (budType == 'perday') {
            var numCampDay = ((endDate - startDate) / 86400000) + 1;
            if ((campBudget * numCampDay) < 400000) {
                showDivCreateCampBudgetErr(true);
                chkBudget = false;
            }
            else {
                chkBudget = true;
                showDivCreateCampBudgetErr(false);
            }
        }
        else if (budType == 'lifetime') {
            if (campBudget < 400000) {
                showDivCreateCampBudgetErr(true);
                chkBudget = false;
            }
            else {
                chkBudget = true;
                showDivCreateCampBudgetErr(false);
            }
        }

        //end check if add new campaign
        if (chkBudget && chkTime && chkName) {
            build_review();
            $('#step3_continue').hide();
            $('#divStep4').show();
            move2Step('#divStep4', 1000);
        }
    }
    else {
        build_review();
        $('#step3_continue').hide();
        $('#divStep4').show();
        move2Step('#divStep4', 1000);
    }

    return;
}

function build_review() {
    // update ad review
    updateStep1Review();

    // update location review
    updateLocationReview();

    // update city reivew
    updateCityReview();

    // update site review
    listSiteSort(false, false);

    // fill campaign name
    var chooseCampType = $('#hddCampType').val();
    if (chooseCampType == 'create') {
        $('#review_camp_name').html($.trim($('#txtCampName').val()) + ' (' + home_lang['new_campaign'] + ')');

        var arrStartDate = ($('#campStartDate').val()).split('-');
        var arrEndDate = ($('#campEndDate').val()).split('-');

        var startDate = new Date(arrStartDate[2] + '/' + arrStartDate[1] + '/' + arrStartDate[0]);
        var endDate = new Date(arrEndDate[2] + '/' + arrEndDate[1] + '/' + arrEndDate[0]);

        var campBudget = $('#txtCampBudget').val();
        campBudget = campBudget.replace(/,/gi, '');
        campBudget = parseFloat(campBudget);

        var budType = $('#slcCampBudgetType').val();

        var numCampDay = ((endDate - startDate) / 86400000) + 1;

        if (budType == 'perday') {
            $('#review_daily_budget').html(addCommas(campBudget.toString()) + ' ' + home_lang['vnd'] + ' ' + home_lang['per_day']);
            var chkFromDay = $('#chkRunToday').attr('checked');
            if (chkFromDay) {
                $('#rvcmpduration').hide();
                var currTime = new Date();
                var currYear = currTime.getFullYear();
                var currMonth = currTime.getMonth();
                currMonth = parseInt(currMonth) < 10 ? '0' + (currMonth + 1) : (currMonth + 1);
                var currDay = currTime.getDate();
                $('#rvcmpfromday').html(common_lang['camp_run_from_day'] + ' <b>' + currDay + '/' + currMonth + '/' + currYear + '</b>').show();
                $('#campStartDate').val(currDay + '-' + currMonth + '-' + currYear);
                $('#campEndDate').val(currDay + '-' + currMonth + '-' + currYear);
            }
            else {
                $('#rvcmpduration').show();
                $('#rvcmpfromday').html('').hide();
            }
        }
        else if (budType == 'lifetime') {
            $('#chkRunToday').attr('disabled', 'disabled');
            $('#chkRunToday').attr('checked', false);
            $('#tdChkFday').addClass('text-666');
            $('#campStartDate').attr('disabled', false).css('background-color', '#fff');
            $('#campEndDate').attr('disabled', false).css('background-color', '#fff');

            $('#review_daily_budget').html(addCommas(campBudget.toString()) + ' ' + home_lang['vnd'] + ' ' + home_lang['life_time']);

            $('#rvcmpduration').show();
            $('#rvcmpfromday').html('').hide();
        }

        //review duration
        $('#review_num_camp_day').html(numCampDay);
        $('#review_camp_start').html($('#campStartDate').val());
        $('#review_camp_end').html($('#campEndDate').val());
    }
    else {
        $('#review_camp_name').html($.trim($("#step3SlcChooseCamp option[value=" + $("#step3SlcChooseCamp").val() + "]").text()));
        //$('#review_daily_budget').html($.trim($('#step3_ChooseCampBudget').html()));
    }
}


function step4_continue(type) {
    type = (typeof(type) === 'undefined' || type === '') ? 'create' : type;

    // check des url
    var desUrl = $.trim($('#desUrl').val());
    if (desUrl == '' || !isUrl(desUrl)) {
        showError(true, '#desUrl', '#errorDesUrl');
        $('#desUrl').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        showError(false, '#desUrl', '#errorDesUrl');
    }

    // thuong hieu
    var cat = $.trim($('#txtCat').val());
    if (cat == '') {
        showError(true, '#txtCat', '#errCatTitle');
        $('#txtCat').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        showError(false, '#txtCat', '#errCatTitle');
    }

    // check title
    var title = $.trim($('#title').val());
    if (title != '') {
        if (title.length > home_lang['max_title2']) {
            showError(true, '#title', '#errorTitle');
            $('#title').focus();
            move2Step('#divStep1', 1000);
            return;
        }
    }
    else {
        showError(false, '#title', '#errorTitle');
    }

    // check body text
    if ($.trim($('#step1BodyText').val()) == '') {
        showError(true, '#step1BodyText', '#errorBodyText');
        $('#step1BodyText').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        showError(false, '#step1BodyText', '#errorBodyText');
    }

    // check image
    if ($.trim($('#hddFileName').val()) == '') {
        showError(true, '', '#errorStep1Image');
        //Tắt tạm check ảnh
//		move2Step('#divStep1', 1000);
//		return;
    }
    else {
        showError(false, '', '#errorStep1Image');
    }

    // price label
    var prLabel = $.trim($('#pricelabel').val());
    if (prLabel != '') {
        if (prLabel.length > home_lang['max_price_label']) {
            showError(true, '#pricelabel', '#errPriceLabel');
            move2Step('#divStep1', 1000);
            return;
        }
        else {
            showError(false, '#pricelabel', '#errPriceLabel');
        }
    }
    else {
        showError(false, '#pricelabel', '#errPriceLabel');
    }
    // dis price
    var dprice = $.trim($('#txtdprice').val());
    if (dprice != '') {
        if (dprice != '0') {
            dprice = dprice.replace(/,/gi, '');
            dprclen = dprice.length;
            dprice = parseFloat(dprice);
            if (dprice == 0 || dprice == '' || dprclen > home_lang['max_dprice']) {
                showError(true, '#txtdprice', '#errdprice');
                $('#txtdprice').focus();
                move2Step('#divStep1', 1000);
                return;
            }
            else {
                showError(false, '#txtdprice', '#errdprice');
            }
        }
    }
    // real price
    var rprice = $('#txtrprice').val();
    if (rprice != '') {
        rprice = rprice.replace(/,/gi, '');
        rprclen = rprice.length;
        rprice = parseFloat(rprice);
        if (rprice == 0 || rprice == '' || rprclen > home_lang['max_rprice']) {
            showError(true, '#txtrprice', '#errrprice');
            $('#txtrprice').focus();
            move2Step('#divStep1', 1000);
            return;
        }
        else {
            showError(false, '#txtrprice', '#errrprice');
        }
    }

    // button name
    var btnName = $.trim($('#txtbutton').val());
    if (btnName != '') {
        if (btnName.length > home_lang['max_button']) {
            showError(true, '#txtbutton', '#errBtnName');
            $('#txtbutton').focus();
            move2Step('#divStep1', 1000);
            return;
        }
        else {
            showError(false, '#txtbutton', '#errBtnName');
        }
    }

    // buyer number
    var buyer = $.trim($('#txtnumber').val());
    if (buyer != '') {
        buyer = buyer.replace(/,/gi, '');
        buyer = parseFloat(buyer);
        if (buyer == 0 || buyer == '' || buyer.length > home_lang['max_buyer']) {
            showError(true, '#txtnumber', '#errBuyer');
            $('#txtnumber').focus();
            move2Step('#divStep1', 1000);
            return;
        }
        else {
            showError(false, '#txtnumber', '#errBuyer');
        }
    }

    // check target location
    var bac = ($('#chkBac').attr('checked')) ? '1' : '0';
    var trung = ($('#chkTrung').attr('checked')) ? '2' : '0';
    var nam = ($('#chkNam').attr('checked')) ? '3' : '0';

    var chkCity = $.trim($('#txtTargetLocationCity').val()) == '' ? false : true;
    if (bac == '0' && trung == '0' && nam == '0' && !chkCity) {
        step2ShowLocationErr();
        move2Step('#divStep2', 1000);
        return;
    }
    else {
        step2ShowLocationErr();
    }

    // check site channel
    var chkSite = $.trim($('#hddListTargetSiteChannelId').val());
    if (!chkSite) {
        step2ShowSiteChannelError();
        move2Step('#divStep2', 1000);
        return;
    }
    else {
        step2ShowSiteChannelError();
    }

    var chooseCampType = $('#hddCampType').val();
    // check if add new campaign
    if (chooseCampType == 'create') {
        var chkFromDay = $('#chkRunToday').attr('checked');
        if (!chkFromDay) {
            var arrStartDate = ($('#campStartDate').val()).split('-');
            var arrEndDate = ($('#campEndDate').val()).split('-');

            var startDate = new Date(arrStartDate[2] + '/' + arrStartDate[1] + '/' + arrStartDate[0]);
            var endDate = new Date(arrEndDate[2] + '/' + arrEndDate[1] + '/' + arrEndDate[0]);

            if (endDate < startDate) {
                showDivCreateCampTimeErr(true);
                $('#campEndDate').focus();
                move2Step('#divStep3', 1000);
                return;
            }
            else {
                showDivCreateCampTimeErr(false);
            }
        }

        var campName = $.trim($('#txtCampName').val());
        if (campName == '') {
            showDivCreateCampNameErr(true);
            $('#txtCampName').focus();
            move2Step('#divStep3', 1000);
            return;
        }
        else {
            showDivCreateCampNameErr(false);
        }

        var campBudget = $('#txtCampBudget').val();
        campBudget = campBudget.replace(/,/gi, '');
        campBudget = parseFloat(campBudget);

        var budType = $('#slcCampBudgetType').val();

        if (budType == 'perday') {
            var numCampDay = ((endDate - startDate) / 86400000) + 1;

            if ((campBudget * numCampDay) < 400000) {
                showDivCreateCampBudgetErr(true);
                $('#txtCampBudget').focus();
                move2Step('#divStep3', 1000);
                return;
            }
            else {
                showDivCreateCampBudgetErr(false);
            }
        }
        else if (budType == 'lifetime') {
            if (campBudget < 400000) {
                showDivCreateCampBudgetErr(true);
                $('#txtCampBudget').focus();
                move2Step('#divStep3', 1000);
                return;
            }
            else {
                showDivCreateCampBudgetErr(false);
            }
        }
    }
    //end check if add new campaign
    else if (chooseCampType == 'choose') {
        var sclCampIdChk = $.trim($('#step3SlcChooseCamp').val());
        if (sclCampIdChk == '' || !isIntNumber(sclCampIdChk) || sclCampIdChk == '0') {
            showDivChooseCampErr(true);
            move2Step('#divStep3', 1000);
            return;
        }
        else {
            showDivChooseCampErr(false);
        }
    }

    if (type == 'create') {
        //if(confirm('Are you sure create ad?'))
        //{
        loadAjaxLoadingPopup('#imgLoadingPopup', '#ajaxLoadingPopupBackground');
        $('#frmCreateAd').submit();


        //}
    }
    else if (type == 'edit') {
        //if(confirm('Are you sure update ad?'))
        //{
        loadAjaxLoadingPopup('#imgLoadingPopup', '#ajaxLoadingPopupBackground');
        $('#frmCreateAd').submit();
        //}
    }
}

function initCreateAdFormField() {
    // step 1
    if ($('#slcBanner') != null) {
        $('#slcBanner').val(0);
    }

    // for captain
    //$('#s1Captain').attr('class', '').addClass($('#slcCaptain').val());

    $('#desUrl').val(home_lang['df_des_url']);
    $('#txtCat').val(home_lang['df_cat_title']);
    $('#title').val(home_lang['df_title']);
    $('#step1BodyText').val(home_lang['df_desc']);
    $('#hddFileName').val('');
    $('#txtdprice').val(home_lang['df_dprice']);
    $('#txtrprice').val(home_lang['df_rprice']);
    $('#txtbutton').val('');
    $('#txtnumber').val('');
    $('#hddStrike').val('1');
    $('#slcdpricetype').val('đ');
    $('#slcrpricetype').val('đ');

    // step 2
    $('#chkLocationAll').attr('checked', 'checked');
    $('#chkBac').attr('checked', 'checked');
    $('#chkTrung').attr('checked', 'checked');
    $('#chkNam').attr('checked', 'checked');

    $('#chkkAllSiteChannel').attr('checked', false);

    // get estimate number
    updateEstimate();

    // step 3
    $('#txtCampName').val('');
    $('#txtCampBudget').val('500000');
    showDivCreateCampBudgetErr(false);
    $('#slcCampBudgetType').val('perday');

    var campid = $('#step3SlcChooseCamp').val();
    campid = campid == '' ? '0' : campid;

    //var price = $.trim($('#hddCpcPrice').val());
    //price = price.replace(/,/gi,'');
    var moneyUnitValue = $.trim($('#hddMoneyUnitValue').val());
    moneyUnitValue = moneyUnitValue.replace(/,/gi, '');

    //price = parseInt(price);
    moneyUnitValue = parseInt(moneyUnitValue);

    $('#approximate_click').html(addCommas(parseInt((300 * moneyUnitValue) / 2200)) + '->' + addCommas(parseInt((300 * moneyUnitValue) / 1210)));
    if (parseInt(campid) > 0) {
        chooseCampChange(campid);
    }
    else {
        var currDate = new Date();
        var currMonth = currDate.getMonth() + 1;
        currMonth = (currMonth < 10) ? '0' + currMonth : currMonth;
        var strCurrDate = currDate.getDate() + '-' + currMonth + '-' + currDate.getFullYear();
        $('#campStartDate').val(strCurrDate);
        $('#campEndDate').val(strCurrDate);
        $('#startDate').val(strCurrDate);
        $('#endDate').val(strCurrDate);
        $('#chkRunToday').attr('checked', false);
        $('#rvcmpduration').show();
        $('#rvcmpfromday').html('').show();
    }
}

function slcBannerChange(bid) {
    if (parseInt(bid) > 0) {
        var onSuccess = function (data) {
            $('#imgCopyAdLoading').css('display', 'none');

            var objData = jQuery.parseJSON(data);

            if ($.trim(objData.hasData) == '1') {
                var title = $.trim(stripHtmlTags(objData.title));
                var btext = $.trim(stripHtmlTags(objData.desc));
                var mark = $.trim(stripHtmlTags(objData.mark));
                var dprice = $.trim(stripHtmlTags(objData.dprice));
                var rprice = $.trim(stripHtmlTags(objData.rprice));
                var button = $.trim(stripHtmlTags(objData.button));
                var buyer = $.trim(stripHtmlTags(objData.buyer));
                var prlabel = $.trim(stripHtmlTags(objData.price_label));
                var rprice_strike = $.trim(stripHtmlTags(objData.rprice_strike));
                var track_conversion = $.trim(stripHtmlTags(objData.trckconver));
                var cpatrk = $.trim(stripHtmlTags(objData.cpatrk));
                var url = $.trim(objData.url);
                var filePath = $.trim(objData.img);
                var fileName = $.trim(objData.filename);
                $('#desUrl').val(url);
                if (url != '') {
                    showError(false, '#desUrl', '#errorDesUrl');
                }
                else {
                    showError(true, '#desUrl', '#errorDesUrl');
                }
                $('#txtCat').val(mark);
                $('.s1ReviewCat1').html(mark);
                if (mark != '') {
                    showError(false, '#txtCat', '#errCatTitle');
                }
                else {
                    showError(true, '#txtCat', '#errCatTitle');
                }

                $('#pricelabel').val(prlabel);
                $('.s1RvPriceLabel').html((prlabel != '') ? prlabel + ':' : '');
                if (prlabel == '') {
                    $('.s1RvPriceLabel').hide();
                }
                else {
                    $('.s1RvPriceLabel').show();
                }
                $('.s1ReviewPrice1').html((dprice != '') ? dprice + objData.dpriceType : '');
                $('#txtdprice').val(dprice);
                if (dprice != '') {
                    if (dprice != '0') {
                        dprice = dprice.replace(/,/gi, '');
                        dprice = parseFloat(dprice);
                        if (dprice == 0 || dprice == '') {
                            showError(true, '#txtdprice', '#errdprice');
                        }
                        else {
                            showError(false, '#txtdprice', '#errdprice');
                        }
                    }
                }
                $('#slcdpricetype').val(objData.dpriceType);


                $('.s1ReviewRprice1').html((rprice != '') ? rprice + objData.rpriceType : '');
                $('#txtrprice').val(rprice);
                if (rprice != '') {
                    rprice = rprice.replace(/,/gi, '');
                    rprice = parseFloat(rprice);
                    if (rprice == 0 || rprice == '') {
                        showError(true, '#txtrprice', '#errrprice');
                    }
                    else {
                        showError(false, '#txtrprice', '#errrprice');
                    }
                }
                $('#slcrpricetype').val(objData.rpriceType);

                $('#txtbutton').val(button);
                $('.s1ReviewBuy1').html(button);
                if (button != '') {
                    $('.s1ReviewBuy1').attr('href', url);
                }

                $('#txtnumber').val(buyer);
                $('.s1Review861').html(buyer);

                $('.s1ReviewTitle1').html(title);
                $('#title').val(title);
                if (title != '') {
                    showError(false, '#title', '#errorTitle');
                }
                else {
                    showError(true, '#title', '#errorTitle');
                }

                $('.s1ReviewDesc1').html(btext);
                $('#step1BodyText').val(btext);
                if (btext != '') {
                    showError(false, '#step1BodyText', '#errorBodyText');
                }
                else {
                    showError(true, '#step1BodyText', '#errorBodyText');
                }

                $('#hddStrike').val(rprice_strike);
                if (rprice_strike == '1') {

                    $('.s1ReviewRprice1').addClass('strike-text');
                    $('#review_rprice').addClass('strike-text');
                }
                else {
                    $('.s1ReviewRprice1').removeClass('strike-text');
                    $('#review_rprice').removeClass('strike-text');
                }
                if (filePath != '') {
                    $('#imgS1Review1').attr('src', filePath).show();
                    $('#imgS1Review2').attr('src', filePath).show();
                    $('#review_image').attr('src', filePath).show();
                    $('#hddFileName').val(fileName);
                    $('#errorStep1Image').hide();
                }
                if (track_conversion == 1) {
                    $('#tracking_conversion_page').attr("checked", "checked");
                }
                else {
                    $('#tracking_conversion_page').attr("checked", "");
                }
                if ((cpatrk != '') && (cpatrk != 0)) {
                    $('#optimize_conversion_page').attr("checked", "checked");
                    $('#tracking_cpa').show();
                    $('#tracking_cpa_type').val(cpatrk);
                    $("#divEstimateSite").hide();
                    $("#trReviewWebsite").hide();
                    $("#divTarSite").hide();
                } else {
                    $('#optimize_conversion_page').attr("checked", "");
                    $('#tracking_cpa').hide();
                    $('#tracking_cpa_type').val();
                    $("#divEstimateSite").show();
                    $("#trReviewWebsite").show();
                    $("#divTarSite").show();
                }
                builTextCount('#txtCat', '#markLimit', objData.max_mark);
                builTextCount('#title', '#titleLimit', objData.max_title);
                builTextCount('#step1BodyText', '#bodyTextLimit', objData.max_body);
                builTextCount('#txtdprice', '#dpriceLimit', objData.max_dprice, true);
                builTextCount('#txtrprice', '#rpriceLimit', objData.max_rprice, true);
                builTextCount('#txtbutton', '#butonLimit', objData.max_button);
                builTextCount('#txtnumber', '#buyerLimit', objData.max_buyer, true);
                builTextCount('#pricelabel', '#plabelLimit', objData.max_price_label, false);

                updateStep1Review();
            }
        };

        var onError = function (jqXHR, textStatus, errorThrown) {
            $('#imgCopyAdLoading').css('display', 'none');

        };

        var onComplete = function (jqXHR, textStatus) {
            $('#imgCopyAdLoading').css('display', 'none');
        };

        $('#imgCopyAdLoading').css('display', '');
        getAjax(makeSiteUrl('createad/suggest_banner/') + bid, '', '', '', '', false, onSuccess, onError, onComplete);
    }
}

function updateStep1Review() {
    var mark = $.trim($('#txtCat').val());
    $('#review_cat_title').html(mark);
    var title = $.trim($('#title').val());
    $('#review_title').html(title);
    $('#review_adname').html((title == '' ? mark : mark + ' - ' + title));
    $('#review_bodytext').html($.trim($('#step1BodyText').val()));
    var prLabel = $.trim($('#pricelabel').val());
    $('#review_prlabel').html((prLabel != '') ? prLabel + ':' : '');
    var dprice = $.trim($('#txtdprice').val());
    var dprType = $.trim($('#slcdpricetype').val());
    var rprice = $.trim($('#txtrprice').val());
    var rprType = $.trim($('#slcrpricetype').val());

    $('#review_dprice').html((dprice != '') ? dprice + dprType : '');
    $('#review_rprice').html((rprice != '') ? rprice + rprType : '');

    var rprStrike = $.trim($('#hddStrike').val());
    if (rprStrike == '0') {
        $('.s1ReviewRprice1').removeClass('strike-text');
        $('#review_rprice').removeClass('strike-text');
    }
    else {
        $('.s1ReviewRprice1').addClass('strike-text');
        $('#review_rprice').addClass('strike-text');
    }

    chkPriceReview();

    var url = $.trim($('#desUrl').val());
    var button = $.trim($('#txtbutton').val());
    if (button != '') {
        $('#review_buy').html(button).attr('href', url).show();
    }

    $('#review_linkimage').attr('href', url);
    $('#review_image').attr('src', $.trim($('#imgS1Review1').attr('src')));

    var buyer = $.trim($('#txtnumber').val());
    if (buyer != '') {
        $('#review_86').html(buyer);
        $('#review_86box').show();
    }
}

function chkLocationAllClick() {

    var checked = $('#chkLocationAll').attr('checked') ? 'checked' : false;
    $('.chkLocation').attr('checked', checked);
}

function chLocationItemClick() {
    var arrLocaItem = $('.chkLocation');
    var chkall = true;
    for (var i = 0; i < arrLocaItem.length; i++) {
        if (!$(arrLocaItem[i]).attr('checked')) {
            chkall = false;
            break;
        }
    }

    if (chkall) {
        $('#chkLocationAll').attr('checked', 'checked');
    }
    else {
        var chk = true;
        for (var i = 0; i < arrLocaItem.length; i++) {
            if ($(arrLocaItem[i]).attr('checked')) {
                chk = false;
                break;
            }
        }

        $('#chkLocationAll').attr('checked', false);
    }
}

function updateLocationReview() {
    var strLocation = '';
    if ($('#chkBac').attr('checked')) {
        strLocation += '<li>' + $.trim($('#bacName').html()) + '</li>';
    }
    if ($('#chkTrung').attr('checked')) {
        strLocation += '<li>' + $.trim($('#trungName').html()) + '</li>';
    }
    if ($('#chkNam').attr('checked')) {
        strLocation += '<li>' + $.trim($('#namName').html()) + '</li>';
    }
    $('#review_location').html(strLocation);
    $('#estimate-location').html(strLocation);

    if (strLocation != '') {
        $('#divEstimateLocation').css('display', '');
        $('#trReviewLocation').css('display', '');
    }
    else {
        $('#divEstimateLocation').css('display', 'none');
        $('#trReviewLocation').css('display', 'none');
    }
}

function updateCityReview() {
    var arrSelectedTag = $('#txtTargetLocationCity').parent('.superblyTagInputItem').parent('.superblyTagItems').children(".superblyTagItem");
    var numSelectedTag = arrSelectedTag.length;

    var strReviewCity = '';
    var strEsitmateCity = '';
    var numSiteCheck = 0;
    for (var i = 0; i < numSelectedTag; i++) {
        var name = ($(arrSelectedTag[i]).find('span').html());
        var style = (i < 5) ? '' : 'display:none;';
        strReviewCity += '<li class="liCityReview" style="' + style + '">' + name + '</li>';
        strEsitmateCity += '<li class="liCityEstimate" style="' + style + '">' + name + '</li>';
        numSiteCheck++;
    }

    if (numSiteCheck > 5) {
        strReviewCity += '<li class="expand" id="liCityReviewExpand" onclick="cityReviewExpand();" >' + home_lang['expand'] + '</li>';
        strEsitmateCity += '<li class="expand" id="liCityEstimateExpand" onclick="cityEstimateExpand();" >' + home_lang['expand'] + '</li>';
    }

    $('#review_city').html(strReviewCity);
    $('#estimate-location-city').html(strEsitmateCity);

    if (numSiteCheck > 0) {
        $('#divEstimateCity').css('display', '');
        $('#trReviewCity').css('display', '');
    }
    else {
        $('#divEstimateCity').css('display', 'none');
        $('#trReviewCity').css('display', 'none');
    }
}

function cityEstimateExpand() {
    if ($('#liCityEstimateExpand').hasClass('expand')) {
        var arr = $('#estimate-location-city li.liCityEstimate');
        var num = arr.length;
        if (num > 4) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).show();
            }
        }

        $('#liCityEstimateExpand').html(home_lang['collapse']);
        $('#liCityEstimateExpand').removeClass('expand');
        $('#liCityEstimateExpand').addClass('collaspe');
    }
    else if ($('#liCityEstimateExpand').hasClass('collaspe')) {
        var arr = $('#estimate-location-city li.liCityEstimate');
        var num = arr.length;
        if (num > 4) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).hide();
            }
        }

        $('#liCityEstimateExpand').html(home_lang['expand']);
        $('#liCityEstimateExpand').removeClass('collaspe');
        $('#liCityEstimateExpand').addClass('expand');
    }
    scrollEstimateBox();
}

function cityReviewExpand() {
    if ($('#liCityReviewExpand').hasClass('expand')) {
        var arr = $('#review_city li.liCityReview');
        var num = arr.length;
        if (num > 4) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).show(250);
            }
        }
        $('#liCityReviewExpand').html(home_lang['collapse']);
        $('#liCityReviewExpand').removeClass('expand');
        $('#liCityReviewExpand').addClass('collaspe');
    }
    else if ($('#liCityReviewExpand').hasClass('collaspe')) {
        var arr = $('#review_city li.liCityReview');
        var num = arr.length;
        if (num > 4) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).hide(250);
            }
        }
        $('#liCityReviewExpand').html(home_lang['expand']);
        $('#liCityReviewExpand').removeClass('collaspe');
        $('#liCityReviewExpand').addClass('expand');
    }
    scrollEstimateBox();
}

function scrollEstimateBox() {
    var footerPos = $('#footer-inner').position();
    var estimatePos = $('#divEstimate').position();
    var estimateHeight = $('#divEstimate').height();
    var top = estimatePos.top;

    if (estimatePos.top + estimateHeight > footerPos.top) {
        top = (footerPos.top - estimateHeight);
        //top = (top < 0) ? 0 : top;
        top = top - 5;
        $("#divEstimate").stop().animate({"top": top + "px"}, 500);
    }
    else {
        var tmpStep2Pos = $('#divStep2').position();
        if ($(window).scrollTop() <= tmpStep2Pos.top) {
            top = tmpStep2Pos.top;
        }
        else {
            top = $(window).scrollTop();
        }
        if (top + estimateHeight <= footerPos.top) {
            $("#divEstimate").stop().animate({"top": top + "px"}, 500);
        }
        else {
            top = top - (top + estimateHeight - footerPos.top);
            $("#divEstimate").stop().animate({"top": top + "px"}, 500);
        }
    }
}


function siteEstimateExpand() {
    if ($('#liSiteEstimateExpand').hasClass('expand')) {
        var arr = $('#estimate-site li.liSiteEstimate');
        var num = arr.length;
        if (num > 6) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).show();
            }
        }
        $('#liSiteEstimateExpand').html(home_lang['collapse']);
        $('#liSiteEstimateExpand').removeClass('expand');
        $('#liSiteEstimateExpand').addClass('collaspe');
    }
    else if ($('#liSiteEstimateExpand').hasClass('collaspe')) {
        var arr = $('#estimate-site li.liSiteEstimate');
        var num = arr.length;
        if (num > 6) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).hide();
            }
        }
        $('#liSiteEstimateExpand').html(home_lang['expand']);
        $('#liSiteEstimateExpand').removeClass('collaspe');
        $('#liSiteEstimateExpand').addClass('expand');
    }
    scrollEstimateBox();
}

function siteReviewExpand() {
    if ($('#liSiteReviewExpand').hasClass('expand')) {
        var arr = $('#review_site li.liSiteReview');
        var num = arr.length;
        if (num > 6) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).show(250);
            }
        }
        $('#liSiteReviewExpand').html(home_lang['collapse']);
        $('#liSiteReviewExpand').removeClass('expand');
        $('#liSiteReviewExpand').addClass('collaspe');
    }
    else if ($('#liSiteReviewExpand').hasClass('collaspe')) {
        var arr = $('#review_site li.liSiteReview');
        var num = arr.length;
        if (num > 6) {
            for (var i = 5; i < num; i++) {
                $(arr[i]).hide(250);
            }
        }
        $('#liSiteReviewExpand').html(home_lang['expand']);
        $('#liSiteReviewExpand').removeClass('collaspe');
        $('#liSiteReviewExpand').addClass('expand');
    }
    scrollEstimateBox();
}

function btnEditAdClick() {
    //$('#step1_continue').show();
    //$('#step2_continue').show();
    //$('#step3_continue').show();
    move2Step('#divStep1', 2000);
}

function updateCreateCampainReview() {
    var price = $.trim($('#hddCpcPrice').val());
    price = price.replace(/,/gi, '');
    price = parseInt(price, 10);
    priceVat = price + price * vat;
    var moneyUnitValue = $.trim($('#hddMoneyUnitValue').val());
    moneyUnitValue = moneyUnitValue.replace(/,/gi, '');

    //price = parseInt(price);
    moneyUnitValue = parseInt(moneyUnitValue);

    $('#review_camp_name').html($.trim($('#txtCampName').val()) + ' (' + home_lang['new_campaign'] + ')');
    $('#review_camp_hdcode').html($.trim($('#txtHdCode').val()));
    $('#review_camp_hd_guest').html($.trim($('#txtHdGuest').val()));

    if ($.trim($('#txtHdCode').val()) != '' || $.trim($('#txtHdGuest').val()) != '') {
        $('#tr_review_hdcode').show();
        $('#tr_review_hd_guest').show();
    }
    else {
        $('#tr_review_hdcode').hide();
        $('#tr_review_hd_guest').hide();
    }

    var strStartDate = $('#campStartDate').val();
    var strEndDate = $('#campEndDate').val();
    var arrStartDate = strStartDate.split('-');
    var arrEndDate = strEndDate.split('-');

    var startDate = new Date(arrStartDate[2] + '/' + arrStartDate[1] + '/' + arrStartDate[0]);
    var endDate = new Date(arrEndDate[2] + '/' + arrEndDate[1] + '/' + arrEndDate[0]);

    var campBudget = $('#txtCampBudget').val();
    campBudget = campBudget.replace(/,/gi, '');
    campBudget = parseFloat(campBudget);
    $('#txtCampBudget').val(addCommas(campBudget));
    //var strapp_click = addCommas(Math.round(campBudget/maxprice)) + '->' + addCommas(Math.round(campBudget/minprice));
    var strapp_click = addCommas(Math.round(campBudget / priceVat));
    $('#approximate_click').html(strapp_click);

    if (campBudget < 400000) {
        $('#errorCreateCampBudget').show();
    }
    else {
        $('#errorCreateCampBudget').hide();
    }

    var budType = $('#slcCampBudgetType').val();
    var chkFromDay = $('#chkRunToday').attr('checked');
    var numCampDay = ((endDate - startDate) / 86400000) + 1;

    if (budType == 'perday') {
        $('#review_daily_budget').html(addCommas(campBudget.toString()) + ' ' + home_lang['vnd'] + ' ' + home_lang['per_day']);
        $('#review_daily_budget_vnd').html(strapp_click + ' click ' + home_lang['per_day']);

        if (chkFromDay) {
            $('#rvcmpduration').hide();
            var currTime = new Date();
            var currYear = currTime.getFullYear();
            var currMonth = currTime.getMonth();
            currMonth = parseInt(currMonth) < 10 ? '0' + (currMonth + 1) : (currMonth + 1);
            var currDay = currTime.getDate();
            currDay = parseInt(currDay) < 10 ? '0' + currDay : currDay;
            $('#rvcmpfromday').html(common_lang['camp_run_from_day'] + ' <b>' + currDay + '-' + currMonth + '-' + currYear + '</b>').show();
            $('#campStartDate').val(currDay + '-' + currMonth + '-' + currYear);
            $('#campEndDate').val(currDay + '-' + currMonth + '-' + currYear);
        }
        else {
            $('#rvcmpduration').show();
            $('#rvcmpfromday').html('').hide();
        }

        $('#chkRunToday').attr('disabled', false);
        $('#tdChkFday').removeClass('text-666');

        $('#hddCampDBanner').val(campBudget);

    }
    else if (budType == 'lifetime') {
        $('#chkRunToday').attr('disabled', 'disabled');
        $('#chkRunToday').attr('checked', false);
        $('#tdChkFday').addClass('text-666');
        $('#campStartDate').attr('disabled', false).css('background-color', '#fff');
        $('#campEndDate').attr('disabled', false).css('background-color', '#fff');
        //var tmp = Math.round(campBudget / numCampDay);
        $('#review_daily_budget').html(addCommas(campBudget.toString()) + ' ' + home_lang['vnd'] + ' ' + home_lang['life_time']);

        $('#review_daily_budget_vnd').html(strapp_click + ' click ' + home_lang['life_time']);

        $('#rvcmpduration').show();
        $('#rvcmpfromday').html('').hide();

        $('#hddCampDBanner').val(Math.round(campBudget / numCampDay));
    }

    $('#review_num_camp_day').html(numCampDay);
    $('#review_camp_start').html(strStartDate);
    $('#review_camp_end').html(strEndDate);
}

function updateEstimate() {
    var strLocationId = '';
    if ($('#chkBac').attr('checked')) {
        strLocationId += (strLocationId == '') ? '1' : ',1';
    }
    if ($('#chkTrung').attr('checked')) {
        strLocationId += (strLocationId == '') ? '2' : ',2';
    }
    if ($('#chkNam').attr('checked')) {
        strLocationId += (strLocationId == '') ? '3' : ',3';
    }

    var strCityId = $('#txtTargetLocationCity').val();
    var strChannelId = $('#hddListTargetSiteChannelId').val();

    var onSuccess = function (data) {
        var objData = jQuery.parseJSON(data);
        var estimateNumber = $.trim(objData.estimate);
        $('#estimate-number').html(estimateNumber);
        $('#estimateLoading').css('display', 'none');

    };
    $('#estimateLoading').css('display', '');
    getAjax(makeSiteUrl('createad/estimate/?locat=' + strLocationId + '&city=' + strCityId + '&channel=' + strChannelId), '', '', '', '', false, onSuccess);

}

function showDivEstimate() {
    var divStep2Pos = $('#divStep2').position();
    $("#divEstimate").css({top: divStep2Pos.top + 'px', left: (divStep2Pos.left + 753) + 'px' });
    $('#divEstimate').show();
}


// for new
function showTargetByCity(obj, eId) {
    if ($(obj).attr('checked')) {
        $(eId).show();
    }
    else {
        $(eId).hide();
    }
}

function gcatChkAllChn(divGcatId, chkClassName, divSiteBoxClass, obj) {
    var strCurrChalId = $.trim($('#hddListTargetSiteChannelId').val());
    var arrCurrChalId = new Array();
    if (strCurrChalId != '') {
        arrCurrChalId = strCurrChalId.split(',');
    }

    if ($(obj).hasClass('gchkall')) {
        var arrChal = $(divGcatId + ' ' + chkClassName);
        var numChal = arrChal.length;
        for (var i = 0; i < numChal; i++) {
            var id = $(arrChal[i]).val();
            if (jQuery.inArray(id, arrCurrChalId) == -1) {
                arrCurrChalId.push(id);
            }
            $(arrChal[i]).attr('checked', 'checked');
        }
        $(obj).parent().parent().find(divSiteBoxClass + ' a.chkSiteName').removeClass('chkall');
        $(obj).parent().parent().find(divSiteBoxClass + ' a.chkSiteName').addClass('chkunall');

        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
        $(obj).removeClass('gchkall');
        $(obj).addClass('gchkunall');
        $(divGcatId + ' ' + divSiteBoxClass).addClass('trg-site-name-box-slc');

    }
    else if ($(obj).hasClass('gchkunall')) {
        var arrChal = $(divGcatId + ' ' + chkClassName);
        var numChal = arrChal.length;
        for (var i = 0; i < numChal; i++) {
            var id = $(arrChal[i]).val();
            var index = jQuery.inArray(id, arrCurrChalId);
            if (index > -1) {
                arrCurrChalId.splice(index, 1);
            }
            $(arrChal[i]).attr('checked', false);
        }
        $(obj).parent().parent().find(divSiteBoxClass + ' a.chkSiteName').removeClass('chkunall');
        $(obj).parent().parent().find(divSiteBoxClass + ' a.chkSiteName').addClass('chkall');
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));

        $(obj).removeClass('gchkunall');
        $(obj).addClass('gchkall');
        $(divGcatId + ' ' + divSiteBoxClass).removeClass('trg-site-name-box-slc');
    }
    if (checkChannelIsCheckAll()) {
        $('#chkkAllSiteChannel').attr('checked', 'checked');
    }
    else {
        $('#chkkAllSiteChannel').attr('checked', false);
    }

    updateEstimate();
    listSiteSort(false, false, true);
    step2ShowSiteChannelError();
}

function expandSiteChannel(divOuterId, classname, obj) {
    var className = $.trim($(obj).attr('class'));
    className = className.toLowerCase();
    if (className == 'img-site-expand') {
        $(divOuterId + ' .' + classname).slideDown(300);
        $(obj).attr('class', 'img-site-collapse');
    }
    else if (className == 'img-site-collapse') {
        var arr = $(divOuterId + ' .' + classname);
        var num = arr.length;

        $(divOuterId + ' .' + classname).slideUp(300);
        $(obj).attr('class', 'img-site-expand');
    }
}

function channelItemCheck(obj, siteBoxId, chkClass, divSiteId) {
    var strCurrChalId = $.trim($('#hddListTargetSiteChannelId').val());
    var arrCurrChalId = new Array();
    if (strCurrChalId != '') {
        arrCurrChalId = strCurrChalId.split(',');
    }
    var id = $(obj).val();

    if ($(obj).attr('checked')) {
        if (jQuery.inArray(id, arrCurrChalId) == -1) {
            arrCurrChalId.push(id);
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
    }
    else {
        var index = jQuery.inArray(id, arrCurrChalId);
        if (index > -1) {
            arrCurrChalId.splice(index, 1);
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
    }

    if (checkChannelIsCheckAll()) {
        $('#chkkAllSiteChannel').attr('checked', 'checked');
        $('#divTargetSiteBox a.target-site-cat-btab').removeClass('gchkall').addClass('gchkunall');
    }
    else {
        chkGcatChkAll('#' + $(siteBoxId).parent().parent().attr('id'), true);
        $('#chkkAllSiteChannel').attr('checked', false);
    }
    step2ShowSiteChannelError();
    listSiteSort(false, false, false);
    updateEstimate();
}

function channelItemNameCheck(chkId, siteBoxId, chkClass, divSiteId) {
    var strCurrChalId = $.trim($('#hddListTargetSiteChannelId').val());
    var arrCurrChalId = new Array();
    if (strCurrChalId != '') {
        arrCurrChalId = strCurrChalId.split(',');
    }
    var id = $(chkId).val();

    if ($(chkId).attr('checked')) {
        var index = jQuery.inArray(id, arrCurrChalId);
        if (index > -1) {
            arrCurrChalId.splice(index, 1);
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
        $(chkId).attr('checked', false);
    }
    else {
        if (jQuery.inArray(id, arrCurrChalId) == -1) {
            arrCurrChalId.push(id);
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
        $(chkId).attr('checked', 'checked');

    }

    if (checkChannelIsCheckAll()) {
        $('#chkkAllSiteChannel').attr('checked', 'checked');
        $('#divTargetSiteBox a.target-site-cat-btab').removeClass('gchkall').addClass('gchkunall');
    }
    else {
        chkGcatChkAll('#' + $(siteBoxId).parent().parent().attr('id'), true);
        $('#chkkAllSiteChannel').attr('checked', false);
    }
    step2ShowSiteChannelError();
    listSiteSort(false, false, false);
    updateEstimate();
}

function checkAllSiteChannel(divOuterId, classname, divSiteId, obj) {
    var strCurrChalId = $.trim($('#hddListTargetSiteChannelId').val());
    var arrCurrChalId = new Array();
    if (strCurrChalId != '') {
        arrCurrChalId = strCurrChalId.split(',');
    }

    if ($(obj).hasClass('chkall')) {
        var arrChal = $(divOuterId + ' .' + classname);
        var numChal = arrChal.length;
        for (var i = 0; i < numChal; i++) {
            var id = $(arrChal[i]).val();
            if (jQuery.inArray(id, arrCurrChalId) == -1) {
                arrCurrChalId.push(id);
            }
            $(arrChal[i]).attr('checked', 'checked');
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
        $(obj).removeClass('chkall');
        $(obj).addClass('chkunall');
        $(divSiteId).addClass('trg-site-name-box-slc');

    }
    else if ($(obj).hasClass('chkunall')) {
        var arrChal = $(divOuterId + ' .' + classname);
        var numChal = arrChal.length;
        for (var i = 0; i < numChal; i++) {
            var id = $(arrChal[i]).val();
            var index = jQuery.inArray(id, arrCurrChalId);
            if (index > -1) {
                arrCurrChalId.splice(index, 1);
            }
            $(arrChal[i]).attr('checked', false);
        }
        $('#hddListTargetSiteChannelId').val(arrCurrChalId.join(','));
        $(obj).removeClass('chkunall');
        $(obj).addClass('chkall');
        $(divSiteId).removeClass('trg-site-name-box-slc');
    }

    if (checkChannelIsCheckAll()) {
        $('#chkkAllSiteChannel').attr('checked', 'checked');
        $('#divTargetSiteBox a.target-site-cat-btab').removeClass('gchkall').addClass('gchkunall');
    }
    else {
        chkGcatChkAll('#' + $(divOuterId).parent().parent().attr('id'), true);
        $('#chkkAllSiteChannel').attr('checked', false);
    }

    updateEstimate();
    listSiteSort(false, false, false);
    step2ShowSiteChannelError();
}

function chkGcatChkAll(gcatId, isSetClass) {
    var isSetClass = (typeof(isSetClass) === 'undefined' || isSetClass === '') ? true : isSetClass;
    var arrChal = $(gcatId + ' .chksitechannel');
    var numChal = arrChal.length;
    var chkAll = true;
    for (var i = 0; i < numChal; i++) {
        if (!$(arrChal[i]).attr('checked')) {
            chkAll = false;
            break;
        }
    }
    if (isSetClass) {
        if (chkAll) {
            $(gcatId + ' a.target-site-cat-btab').removeClass('gchkall').addClass('gchkunall');
        }
        else {
            $(gcatId + ' a.target-site-cat-btab').removeClass('gchkunall').addClass('gchkall');
        }
    }
}

function checkAllChannel(obj) {
    if ($(obj).attr('checked')) {
        $('#divTargetSiteBox .target-site-cat-box a.target-site-cat-btab').removeClass('gchkall');
        $('#divTargetSiteBox .target-site-cat-box a.target-site-cat-btab').addClass('gchkunall');

        $('#divTargetSiteBox .target-site-cat-box a.chkSiteName').removeClass('chkall');
        $('#divTargetSiteBox .target-site-cat-box a.chkSiteName').addClass('chkunall');

        var arrChannel = $('#divTargetSiteBox .chksitechannel');
        var numChannel = arrChannel.length;
        var arrChannelId = new Array();
        for (var i = 0; i < numChannel; i++) {
            arrChannelId.push($(arrChannel[i]).val());
            $(arrChannel[i]).attr('checked', 'checked');
        }
        $('#hddListTargetSiteChannelId').val(arrChannelId.join(','));
        updateEstimate();
    }
    else {
        $('#divTargetSiteBox .target-site-cat-box a.target-site-cat-btab').removeClass('gchkunall');
        $('#divTargetSiteBox .target-site-cat-box a.target-site-cat-btab').addClass('gchkall');

        $('#divTargetSiteBox .target-site-cat-box a.chkSiteName').removeClass('chkunall');
        $('#divTargetSiteBox .target-site-cat-box a.chkSiteName').addClass('chkall');

        $('#divTargetSiteBox .chksitechannel').attr('checked', false);
        $('#hddListTargetSiteChannelId').val('');

        updateEstimate();
    }

    listSiteSort(false, false, true);
    step2ShowSiteChannelError();
}

function checkChannelIsCheckAll() {
    var arrChannel = $('#divTargetSiteBox .chksitechannel');
    var num = arrChannel.length;
    var chk = true;
    for (var i = 0; i < num; i++) {
        if (!$(arrChannel[i]).attr('checked')) {
            chk = false;
            break;
        }
    }
    return chk;
}

function expandSite(boxId, expId, colId, classname, chClassName, type) {
    if (type == 1) {
        $(boxId + ' ' + classname + ' li').slideDown(300);
        //$(boxId + ' ' + chClassName).slideDown(300);
        //$(boxId + ' a.trg-site-expand img').attr('class', 'img-site-collapse');
        $(expId).hide();
        $(colId).show();
        scrollEstimateBox();
    }
    else if (type == 2) {
        $(expId).show();
        $(colId).hide();
        $(boxId + ' a.trg-site-expand img').attr('class', 'img-site-expand');
        var arrSite = $(boxId + ' ' + classname + ' li');
        var numS = arrSite.length;
        var nShow = 0;
        var arrShow = new Array();
        var arrHide = new Array();
        for (var i = 0; i < numS; i++) {
            var arrChn = $(arrSite[i]).find('p' + chClassName);
            var numTmp = arrChn.length;
            var chk = false;
            for (var j = 0; j < numTmp; j++) {
                var arrChk = $(arrChn[j]).find('input');
                if ($(arrChk[0]).attr('checked')) {
                    chk = true;
                    break;
                }
            }
            if (chk && nShow < 5) {
                arrShow.push(arrSite[i]);
                nShow++;
            }
            else {
                arrHide.push(arrSite[i]);
            }
        }
        if (nShow == 0 || nShow < 5) {
            var nHide = arrHide.length;
            var rmHideIndex = new Array();
            var tmpArrHide = new Array();
            for (var i = 0; i < nHide; i++) {
                if (nShow < 5) {
                    arrShow.push(arrHide[i]);
                    nShow++;
                }
                else {
                    tmpArrHide.push(arrHide[i]);
                }
            }
            arrHide = null;
            arrHide = tmpArrHide;
        }
        jQuery.each(arrHide, function () {
            $(this).slideUp(300);
            $(this).find(chClassName).slideUp(300);
        });
        jQuery.each(arrShow, function () {
            $(this).find(chClassName).slideUp(300);
            $(this).slideDown(300);
        });

        $('html,body').animate({scrollTop: $(boxId).position().top - 30}, 500);
    }
}

function listSiteSort(isReturnListChannelId, isSort, isCollapse) {
    isReturnListChannelId = (typeof(isReturnListChannelId) === 'undefined' || isReturnListChannelId === '') ? false : isReturnListChannelId;
    isSort = (typeof(isSort) === 'undefined' || isSort === '') ? true : isSort;
    isCollapse = (typeof(isCollapse) === 'undefined' || isCollapse === '') ? false : isCollapse;
    var arrSiteBox = $('#divTargetSiteBox .target-site-cat-box');
    var num = arrSiteBox.length;
    var arrValueChecked = new Array();
    var arrSiteName = new Array();

    for (var i = 0; i < num; i++) {
        var arrTmpSite = $(arrSiteBox[i]).find('.liSiteCl');
        var nSite = arrTmpSite.length;
        var arrSiteCheck = new Array();
        var arrSiteNotCheck = new Array();

        for (var m = 0; m < nSite; m++) {
            var arrChk = $(arrTmpSite[m]).find('input.chksitechannel');
            var numCheck = arrChk.length;
            var chkItem = false;
            for (var j = 0; j < numCheck; j++) {
                if ($(arrChk[j]).attr('checked')) {
                    chkItem = true;
                    break;
                }
            }
            if (chkItem) {
                $(arrTmpSite[m]).find('div.trg-site-name-box').addClass('trg-site-name-box-slc');
                $(arrTmpSite[m]).find('div.trg-site-name-box .trg-site-name a.chkSiteName').addClass('chkunall').removeClass('chkall');
                arrSiteCheck.push(arrTmpSite[m]);
                var arrChalItem = $(arrTmpSite[m]).find('.target-site-channel');
                arrSiteName.push($(arrTmpSite[m]).find('.trg-site-name').children('a').html());
                jQuery.each(arrChalItem, function () {
                    if ($(this).children('.chksitechannel').attr('checked')) {
                        arrValueChecked.push($(this).children('.chksitechannel').val());
                    }
                });
            }
            else {
                $(arrTmpSite[m]).find('div.trg-site-name-box').removeClass('trg-site-name-box-slc');
                $(arrTmpSite[m]).find('div.trg-site-name-box .trg-site-name a.chkSiteName').removeClass('chkunall').addClass('chkall');
                arrSiteNotCheck.push(arrTmpSite[m]);
            }
        }

        var numSiteBoxHasSelected = arrSiteCheck.length;
        var numSiteBoxNotSelected = arrSiteNotCheck.length;
        var i_temp = 0;
        for (var n = 0; n < numSiteBoxHasSelected; n++) {
            var chkExpand = ($(arrSiteCheck[n]).find('.trg-site-expand').children('img').attr('class') == 'img-site-expand' && $(arrSiteCheck[n]).parent().parent('.target-site-cat-box').find('.target-site-more-exp').css('display') != 'none' ) ? false : true;
            if (!chkExpand) {
                $(arrSiteCheck[n]).find('.target-site-channel').css('display', 'none');
                if (i_temp < 5) {
                    $(arrSiteCheck[n]).css('display', '');
                    i_temp++;
                }
                else {
                    $(arrSiteCheck[n]).css('display', 'none');
                }
            }
            else {
                i_temp++;
            }
        }

        for (var n = 0; n < numSiteBoxNotSelected; n++) {
            var chkExpand = ($(arrSiteNotCheck[n]).find('.trg-site-expand').children('img').attr('class') == 'img-site-expand' && $(arrSiteNotCheck[n]).parent().parent('.target-site-cat-box').find('.target-site-more-exp').css('display') != 'none') ? false : true;
            if (!chkExpand) {
                $(arrSiteNotCheck[n]).find('.target-site-channel').css('display', 'none');
                $(arrSiteNotCheck[n]).find('.trg-site-expand').children('img').attr('class', 'img-site-expand');
                if (i_temp < 5) {
                    $(arrSiteNotCheck[n]).css('display', '');
                    i_temp++;
                }
                else {
                    $(arrSiteNotCheck[n]).css('display', 'none');
                }

            }
            else {
                i_temp++;
            }
        }
    }

    // update estimate site + update step 4 review site
    var numSlcSite = arrSiteName.length;
    var numSiteCheck = 0;
    var strSiteReview = '';
    var strSiteEstimate = '';
    for (var i = 0; i < numSlcSite; i++) {
        var style = (i < 5) ? '' : 'display:none;';
        strSiteReview += '<li class="liSiteReview" style="' + style + '">' + arrSiteName[i] + '</li>';
        strSiteEstimate += '<li class="liSiteEstimate" style="' + style + '">' + arrSiteName[i] + '</li>';
        numSiteCheck++;
    }

    if (numSiteCheck > 5) {
        strSiteReview += '<li class="expand" id="liSiteReviewExpand" onclick="siteReviewExpand();" >' + home_lang['expand'] + '</li>';

        strSiteEstimate += '<li class="expand" id="liSiteEstimateExpand" onclick="siteEstimateExpand();" >' + home_lang['expand'] + '</li>';
    }

    $('#review_site').html(strSiteReview);
    $('#estimate-site').html(strSiteEstimate);

    if (numSiteCheck > 0) {
        if ($('#optimize_conversion_page').is(":checked")) {
            $('#divEstimateSite').css('display', 'none');
            $('#trReviewWebsite').css('display', 'none');
        } else {
            $('#divEstimateSite').css('display', '');
            $('#trReviewWebsite').css('display', '');
        }
    }
    else {
        $('#divEstimateWebsite').css('display', 'none');
        $('#trReviewWebsite').css('display', 'none');
    }

    if (isReturnListChannelId) {

        if (arrValueChecked.length > 0) {
            return arrValueChecked.join(',');
        }
        else {
            return '';
        }
    }
}

function suggCatCheckChannel(catid, type, isUpdateEstimate) {
    type = (typeof(type) === 'undefined' || type === '') ? 1 : type;
    isUpdateEstimate = (typeof(isUpdateEstimate) === 'undefined' || isUpdateEstimate === '') ? true : isUpdateEstimate;
    if (type == 1) // add cat
    {
        var onSuccess = function (data) {
            var objData = jQuery.parseJSON(data);
            var num = objData.length;
            for (var i = 0; i < num; i++) {
                $('#site-channel-' + objData[i]).attr('checked', 'checked');
            }
            $('#hddListTargetSiteChannelId').val(listSiteSort(true, true, true));
            step2ShowSiteChannelError();
            if (isUpdateEstimate) {
                updateEstimate();
            }
        };
        getAjax(makeSiteUrl('home/get_site_channel_by_cat'), 'catid=' + catid, '', '', '', false, onSuccess);
    }
    else if (type == 2) // remove cat
    {
        var onSuccess = function (data) {
            var objData = jQuery.parseJSON(data);
            var num = objData.length;
            for (var i = 0; i < num; i++) {
                $('#site-channel-' + objData[i]).attr('checked', false);
            }
            $('#hddListTargetSiteChannelId').val(listSiteSort(true, true, true));
            step2ShowSiteChannelError();
            if (isUpdateEstimate) {
                updateEstimate();
            }
        };
        getAjax(makeSiteUrl('home/get_site_channel_by_cat'), 'catid=' + catid, '', '', '', false, onSuccess);
    }

}

function chkRunFromDay(obj) {
    if ($(obj).attr('checked')) {
        $('#campStartDate').attr('disabled', 'disabled').css('background-color', '#f3f3f3');
        $('#campEndDate').attr('disabled', 'disabled').css('background-color', '#f3f3f3');

        $('#slcCampBudgetType').find('option[value=\"perday\"]').remove();
        $('#slcCampBudgetType').find('option[value=\"lifetime\"]').remove();
        $('#slcCampBudgetType').append('<option value="perday">' + common_lang['camp_budget_perday'] + '</option>');

        updateCreateCampainReview();
    }
    else {
        $('#campStartDate').attr('disabled', false).css('background-color', '#fff');
        $('#campEndDate').attr('disabled', false).css('background-color', '#fff');

        $('#slcCampBudgetType').find('option[value=\"perday\"]').remove();
        $('#slcCampBudgetType').find('option[value=\"lifetime\"]').remove();
        $('#slcCampBudgetType').append('<option value="perday">' + common_lang['camp_budget_perday'] + '</option>');
        $('#slcCampBudgetType').append('<option value="lifetime">' + common_lang['camp_budget_lifetime'] + '</option>');

        updateCreateCampainReview();
    }
}

function builEditLocation() {
    var arrLocaItem = $('.chkLocation');
    var chkall = true;
    for (var i = 0; i < arrLocaItem.length; i++) {
        if (!$(arrLocaItem[i]).attr('checked')) {
            chkall = false;
            break;
        }
    }
    if (chkall) {
        $('#chkLocationAll').attr('checked', 'checked');
    }
    else {
        $('#chkLocationAll').attr('checked', false);
    }
}

function builTextCount(txtId, countId, limit, chkNum) {
    chkNum = (typeof(chkNum) === 'undefined' || chkNum === '') ? false : chkNum;
    var tex = $(txtId).val();
    if (chkNum) {
        tex = tex.replace(/,/gi, '');
    }
    var len = tex.length;
    $(countId).html(limit - len);
}

function builStyleCount(countId, numCurr, limit) {
    $(countId).html(limit - numCurr);
}
// end for new


// payment
function payment_checkout() {
    var price = $.trim($('#txtPrice').val());

    price = price.replace(/,/g, '');

    var chkPrice = true;
    if (!isIntNumber(price) || (parseInt(price) < 50000)) {
        $('#txtPrice').next('span').html(payment_lang['msg_50000']);
        chkPrice = false;
    }
    else {
        $('#txtPrice').next('span').html('');
        chkPrice = true;
    }

    if (chkPrice) {
        $('#frmCheckOut').submit();
    }
}
// end payment


// create account
function account_checkuname() {
    var uname = $.trim($('#username').val());
    var onSuccess = function (data) {
        var objData = jQuery.parseJSON(data);
        var chk = $.trim(objData.chk);
        if (chk.length > 0) {
            $('#uname_check').show();
            $('#errorUsername').show();
            $('#errorUsername').html(chk);
            $('#username').addClass('error-border');
        }
        else {
            $('#errorUsername').hide();
            $('#uname_check').hide();
            $('#username').removeClass('error-border');
        }
    };

    getAjax(makeSiteUrl('/account/checkuname/?uname=' + uname), '', '', '', '', false, onSuccess);
}

function account_checkemail() {
    var email = $.trim($('#email').val());
    var onSuccess = function (data) {
        var objData = jQuery.parseJSON(data);
        var chk = $.trim(objData.chk);
        if (chk == 0) {
            $('#errorEmail').show();
            $('#errorEmail').html('Email nÃ y Ä‘Ã£ tá»“n táº¡i trÃªn há»‡ thá»‘ng!');
            $('#email').addClass('error-border');
        }
        else {
            $('#errorEmail').hide();
            $('#email').removeClass('error-border');
        }
    };

    getAjax(makeSiteUrl('/account/checkemail/?email=' + email), '', '', '', '', false, onSuccess);
}

function phonenumber_validate(str) {
    if (!isNaN(str)) return true;
    return false;
}

function signup_check() {
    //account_checkuname(); account_checkemail();
    var uname = $.trim($('#username').val());
    var pass = $.trim($('#password').val());
    var repass = $.trim($('#repassword').val());

    var fullname = $.trim($('#fullname').val());

    var email = $.trim($('#email').val());

    var phone = $.trim($('#phone').val());
    var address = $.trim($('#address').val());

    var uname_error = validateUsername(uname);
    var pass_error = validatePassword(pass);
    var email_error = validateEmail(email);

    var captcha = $.trim($('#aCaptcha').val());
    var term = $('#cbPolicy:checked').val();

    if (uname_error == '') uname_error = $.trim($('#errorUsername').val());
    if (email_error == '') email_error = $.trim($('#errorEmail').val());

    var chkUserName = true;
    if (uname_error != '') {
        $('#errorUsername').html(uname_error);
        $('#errorUsername').show();
        $('#username').addClass('error-border');
        chkUserName = false;
    }
    else {
        $('#errorUsername').hide();
        $('#username').removeClass('error-border');
        chkUserName = true;
    }

    var chkPass = true;
    if (pass == '' || repass == '') {
        $('#errorPassword').html(account_lang['enter_pass']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        chkPass = false;
    }
    else if (pass != repass) {
        $('#errorPassword').html(account_lang['pass_not_match']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        $('#repassword').addClass('error-border');
        chkPass = false;
    }
    else if (pass.length < 6 || pass.length > 64) {
        $('#errorPassword').html(account_lang['pass_short']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        chkPass = false;
    }
    else {
        $('#errorPassword').hide();
        $('#password').removeClass('error-border');
        $('#repassword').removeClass('error-border');
        chkPass = true;
    }

    var chkEmail = true;
    if (email_error != '') {
        $('#errorEmail').html(email_error);
        $('#errorEmail').show();
        $('#email').addClass('error-border');
        chkEmail = false;
    }
    else {
        $('#errorEmail').hide();
        $('#email').removeClass('error-border');
        chkEmail = true;
    }

    var chkFullName = true;
    if (fullname == '') {
        $('#errorFullname').html(account_lang['enter_fullname']);
        $('#errorFullname').show();
        $('#fullname').addClass('error-border');
        chkFullName = false;
    }
    else {
        $('#errorFullname').hide();
        $('#fullname').removeClass('error-border');
        chkFullName = true;
    }

    var chkPhone = true;
    if (phone == '') {
        $('#errorPhone').html(account_lang['enter_phone']);
        $('#errorPhone').show();
        $('#phone').addClass('error-border');
        chkPhone = false;
    }
    else if (!phonenumber_validate(phone)) {
        $('#errorPhone').html(account_lang['phone_not_type']);
        $('#errorPhone').show();
        $('#phone').addClass('error-border');
        chkPhone = false;
    }
    else {
        $('#errorPhone').hide();
        $('#phone').removeClass('error-border');
        chkPhone = true;
    }

    //check captcha
    var chkCaptcha = true;
    if (captcha == '') {
        $('#errorCaptcha').html(account_lang['enter_captcha']);
        $('#errorCaptcha').show();
        $('#aCaptcha').addClass('error-border');
        chkCaptcha = false;
    }
    else {
        $('#errorCaptcha').hide();
        $('#aCaptcha').removeClass('error-border');
        chkCaptcha = true;
    }

    //Check term
    var chkTerm = true;
    if (term != '1') {
        $('#errorTerm').html(account_lang['enter_terms']);
        $('#errorTerm').show();
        chkTerm = false;
    }
    else {
        $('#errorTerm').hide();
        chkTerm = true;
    }

    if (chkUserName && chkPass && chkEmail && chkFullName && chkPhone && chkCaptcha && chkTerm) {
        $('#frmSignUp').submit();
        return;
        // use ajax
        var dataString = 'username=' + uname + '&password=' + pass + '&repassword=' + repass + '&fullname=' + fullname + '&email=' + email + '&phone=' + phone + '&address=' + address + '&aCaptcha=' + captcha + '&callback=1';
        var onSuccess = function (data) {
            //Show popup
            if (data != '') {
                var objData = jQuery.parseJSON(data);
                //alert(objData.captcha);
                if (!objData.captcha) {
                    $('#errorCaptcha').html(objData.captcha_msg);
                    $('#errorCaptcha').show();
                    $('#aCaptcha').addClass('error-border');
                    return false;
                }

                var chk = $.trim(objData.chk);
                $('body').append(chk + '<div id="fade"></div>');
                $('#fade').css({'filter': 'alpha(opacity=80)'}).fadeIn();

                $(document).ready(function () {
                    $('#fade').click(function () {
                        $('#fade').fadeOut();
                        return false;
                    });
                });
            }
        };
        account_checkuname();
        account_checkemail();
        getAjax(makeSiteUrl('/account/signup'), dataString, '', 'POST', '', false, onSuccess);
    }
}

function account_update_check() {
    var pass = $.trim($('#newpassword').val());
    var repass = $.trim($('#newrepassword').val());

    var fullname = $.trim($('#fullname').val());

    var email = $.trim($('#email').val());
    var email_error = validateEmail(email);

    var chkPass = true;
    if (pass != '') {
        var pass_error = validatePassword(pass);
        if (pass != repass) {
            $('#errorPassword').html('The passwords you entered do not match. Please try again.');
            $('#errorPassword').show();
            $('#newpassword').addClass('error-border');
            $('#newrepassword').addClass('error-border');
            chkPass = false;
        }
        else if (pass.length < 6 || pass.length > 64) {
            $('#errorPassword').html("The password is the wrong length.");
            $('#errorPassword').show();
            $('#newpassword').addClass('error-border');
            chkPass = false;
        }
        else {
            $('#errorPassword').hide();
            $('#newpassword').removeClass('error-border');
            $('#newrepassword').removeClass('error-border');
            chkPass = true;
        }
    }

    var chkEmail = true;
    if (email_error != '') {
        $('#errorEmail').html(email_error);
        $('#errorEmail').show();
        $('#email').addClass('error-border');
        chkEmail = false;
    }
    else {
        $('#errorEmail').hide();
        $('#email').removeClass('error-border');
        chkEmail = true;
    }

    var chkFullName = true;
    if (fullname == '') {
        $('#errorFullname').html('Please enter your fullname.');
        $('#errorFullname').show();
        $('#fullname').addClass('error-border');
        chkFullName = false;
    }
    else {
        $('#errorFullname').hide();
        $('#fullname').removeClass('error-border');
        chkFullName = true;
    }

    if (chkPass && chkEmail && chkFullName) {
        $('#frmChangeInfo').submit();
    }
}

function account_lostpass() {
    var email = $.trim($('#email').val());
    var email_error = validateEmail(email);

    var chkEmail = true;
    if (email_error != '') {
        $('#errorEmail').html(email_error);
        $('#errorEmail').show();
        $('#email').addClass('error-border');
        chkEmail = false;
    }
    else {
        $('#errorEmail').hide();
        $('#email').removeClass('error-border');
        chkEmail = true;
    }
    if (chkEmail) {
        $('#frmLostPass').submit();
    }
}
// end creae account


/* tool tip */
function toannh_tooltip(e, strTipTitle, strTipBody, width, xOffset, yOffset) {
    width = (typeof(width) === 'undefined' || width === '') ? 200 : width;

    xOffset = (typeof(xOffset) === 'undefined' || xOffset === '') ? 40 : xOffset;

    yOffset = (typeof(yOffset) === 'undefined' || yOffset === '') ? 0 : yOffset;

    if (strTipTitle != '' || strTipBody != '') {
        $('#toannh-tooltip').width(width);

        $("#toannh-tooltip-title").html(strTipTitle);
        $("#toannh-tooltip-body").html(strTipBody);

        var ttipW = $('#toannh-tooltip').width();
        var ttipH = $('#toannh-tooltip').height();

        if ((ttipW / ttipH) < 3) {
            ttipW = ttipH * 3;
            ttipW = (ttipW > 450) ? 450 : ttipW;
            $('#toannh-tooltip').width(ttipW);
        }

        //alert($('#toannh-tooltip-inner').html());
        var pos = $(e).offset();
        var eWidth = $(e).outerWidth();
        var eHeight = $(e).outerHeight();

        $("#toannh-tooltip").css('z-index', '-1000000001');
        $("#toannh-tooltip").show();

        var ttop = pos.top + yOffset + eHeight;

        var tleft = pos.left - xOffset;
        var arrowPosLeft = xOffset - ($('#toannh-tooltip-arrow').outerWidth() / 2) + (eWidth / 2);

        var wheight = $(window).height();
        var wWidth = $(window).width();

        //var wScrollTop = $(window).scrollTop();

        var tooltipHeight = $('#toannh-tooltip').outerHeight();
        var tooltipWidth = $('#toannh-tooltip').outerWidth();

        if (tleft + tooltipWidth > wWidth) {
            var tmp = tleft + tooltipWidth - wWidth;
            tleft -= tmp;
            arrowPosLeft = arrowPosLeft + tmp;
        }
        /*
         if(ttop + tooltipHeight > wheight + wScrollTop)
         {
         var tmp = ttop + tooltipHeight - wheight - wScrollTop;
         ttop -= tmp;
         $("#toannh-tooltip-arrow").css("left", (tmp - 3) + "px" );
         }
         else
         {
         $("#toannh-tooltip").css("background-position","left top");
         }
         */
        $('#toannh-tooltip-arrow').css("left", arrowPosLeft + "px");
        $("#toannh-tooltip").css("top", ttop + "px");
        $("#toannh-tooltip").css("left", tleft + "px");
        $("#toannh-tooltip").css('z-index', '1000000001');
    }
}

function removeToannh_Tooltip() {
    $("#toannh-tooltip").css('display', 'none');
    $("#toannh-tooltip-title").html('');
    $("#toannh-tooltip-body").html('');
}

function toannh_tooltip2(e, strTipTitle, strTipBody, width, xOffset, yOffset, isFixWidth) {
    width = (typeof(width) === 'undefined' || width === '') ? 200 : width;

    xOffset = (typeof(xOffset) === 'undefined' || xOffset === '') ? 40 : xOffset;

    yOffset = (typeof(yOffset) === 'undefined' || yOffset === '') ? 0 : yOffset;

    isFixWidth = (typeof(yOffset) === 'isFixWidth' || yOffset === '') ? false : isFixWidth;

    if (strTipTitle != '' || strTipBody != '') {
        $('#toannh-tooltip2').width(width);

        $("#toannh-tooltip-title2").html(strTipTitle);
        $("#toannh-tooltip-body2").html(strTipBody);

        var ttipW = $('#toannh-tooltip2').width();
        var ttipH = $('#toannh-tooltip2').height();

        if (!isFixWidth) {
            if ((ttipW / ttipH) < 3) {
                ttipW = ttipH * 3;
                ttipW = (ttipW > 450) ? 450 : ttipW;
                $('#toannh-tooltip2').width(ttipW);
            }
        }
        //alert($('#toannh-tooltip-inner').html());
        var pos = $(e).offset();
        var eWidth = $(e).outerWidth();
        var eHeight = $(e).outerHeight();

        $("#toannh-tooltip2").css('z-index', '-1000000001');
        $("#toannh-tooltip2").show();

        var tooltipHeight = $('#toannh-tooltip2').outerHeight();
        var tooltipWidth = $('#toannh-tooltip2').outerWidth();

        var ttop = pos.top - yOffset - tooltipHeight;

        var tleft = pos.left - xOffset;
        var arrowPosLeft = xOffset - ($('#toannh-tooltip-arrow-down').outerWidth() / 2) + (eWidth / 2);

        var wheight = $(window).height();
        var wWidth = $(window).width();

        //var wScrollTop = $(window).scrollTop();


        if (tleft + tooltipWidth > wWidth) {
            var tmp = tleft + tooltipWidth - wWidth;
            tleft -= tmp;
            arrowPosLeft = arrowPosLeft + tmp;
        }
        /*
         if(ttop + tooltipHeight > wheight + wScrollTop)
         {
         var tmp = ttop + tooltipHeight - wheight - wScrollTop;
         ttop -= tmp;
         $("#toannh-tooltip-arrow").css("left", (tmp - 3) + "px" );
         }
         else
         {
         $("#toannh-tooltip").css("background-position","left top");
         }
         */
        $('#toannh-tooltip-arrow-down').css("left", arrowPosLeft + "px");
        $("#toannh-tooltip2").css("top", ttop + "px");
        $("#toannh-tooltip2").css("left", tleft + "px");
        $("#toannh-tooltip2").css('z-index', '1000000001');
    }
}

function removeToannh_Tooltip2() {
    $("#toannh-tooltip2").css('display', 'none');
    $("#toannh-tooltip-title2").html('');
    $("#toannh-tooltip-body2").html('');
}

function tooltip_preview(e, boxId, xOffset, yOffset) {
    $('.ttip-ad-preview').hide();
    xOffset = (typeof(xOffset) === 'undefined' || xOffset === '') ? 40 : xOffset;
    yOffset = (typeof(yOffset) === 'undefined' || yOffset === '') ? 0 : yOffset;

    var ttipW = $(boxId).width();
    var ttipH = $(boxId).height();


    var pos = $(e).offset();
    var eWidth = $(e).outerWidth();
    var eHeight = $(e).outerHeight();

    $(boxId).css('z-index', '-1000010');
    $(boxId).show();

    var ttop = pos.top + yOffset + eHeight;

    var tleft = pos.left - xOffset;
    var arrowWidth = $(boxId).children('.ttip-ad-preview-arrow').outerWidth();
    var arrowPosLeft = xOffset - (arrowWidth / 2) + (eWidth / 2);

    var wheight = $(window).height();
    var wWidth = $(window).width();

    var tooltipHeight = $(boxId).outerHeight();
    var tooltipWidth = $(boxId).outerWidth();

    if (tleft + tooltipWidth > wWidth) {
        var tmp = tleft + tooltipWidth - wWidth;
        tleft -= tmp;
        arrowPosLeft = arrowPosLeft + tmp;
    }

    $(boxId).find('img.ttip-ad-preview-arrow').css("left", arrowPosLeft + "px");
    $(boxId).css("top", ttop + "px");
    $(boxId).css("left", tleft + "px");
    $(boxId).css('z-index', '1000010');
}

function tooltip_preview_remove(boxId) {
    $(boxId).css('display', 'none');
}

function bubble_tip(eId, strContent, posType, width, arrowUrl, xOffset, yOffset, xArrowOffset, yArrowOffset) {
    if (posType != 'top' && posType != 'right' && posType != 'bottom' && posType != 'left') {
        return;
    }
    if ($(eId).css('display') == 'none') {
        bubble_tip_close();
        return;
    }

    var bubbleId = '#bubble-tip';
    var bbInnerId = '#bubble-inner';
    var bbarrowId = '#bubble-arrow';
    var bubbleboxId = '#bubble-box';
    var bbcloseId = '#bubble-close';

    $(bubbleboxId).css({'margin-top': '0px'});

    var divCont = $('<div/>');
    $(divCont).html(strContent);

    width = (typeof(width) === 'undefined' || width === '') ? 350 : width;
    width = (width < 900) ? width : 900;
    width = (width < 150) ? 150 : width;

    xOffset = (typeof(xOffset) === 'undefined' || xOffset === '') ? 0 : xOffset;
    yOffset = (typeof(yOffset) === 'undefined' || yOffset === '') ? 0 : yOffset;

    xArrowOffset = (typeof(xArrowOffset) === 'undefined' || xArrowOffset === '') ? 0 : xArrowOffset;
    yArrowOffset = (typeof(yArrowOffset) === 'undefined' || yArrowOffset === '') ? 0 : yArrowOffset;

    if (arrowUrl != '') {
        $(bbarrowId).attr('src', arrowUrl);
    }

    $(bubbleId).width(width);
    $(divCont).css({'width': (width - 32) + 'px'});
    $(bbInnerId).html(divCont);
    $(bubbleId).width(width);

    posType = (typeof(posType) === 'undefined' || posType === '') ? 'bottom' : posType;
    arrowUrl = (typeof(arrowUrl) === 'undefined' || arrowUrl === '') ? '' : arrowUrl;

    var pos = $(eId).offset();
    pos.top = Math.round(pos.top);
    pos.left = Math.round(pos.left);
    var eH = $(eId).outerHeight();
    var eW = $(eId).outerWidth();

    var boxW = width;
    var boxH = $(bubbleId).outerHeight();

    var wHeight = $(window).height();
    var wWidth = $(window).width();

    switch (posType) {
        case 'top':
            var arrowH = 26;
            var arrowW = 32;
            var ttop = pos.top + eH + yOffset;
            var tleft = pos.left + xOffset;
            var arrowPosLeft = xArrowOffset + 10;
            var arrowPosTop = arrowH - 1 + yArrowOffset;
            if (tleft + boxW > wWidth) {
                var tmp = tleft + boxW - wWidth;
                tleft -= tmp;
                arrowPosLeft = arrowPosLeft + tmp;
            }

            $(bubbleboxId).css({'margin-top': arrowH + 'px', 'width': width + 'px'});
            $(bbInnerId).css({'width': (width - 32) + 'px'});
            $(bbarrowId).css({'top': '-' + arrowPosTop + 'px', "left": arrowPosLeft + "px" });
            $(bbcloseId).css({'left': (width - 10) + 'px', 'top': '-6px'});

            $(bubbleId).css({'top': ttop + 'px', 'left': tleft + 'px'});
            $(bubbleId).fadeIn(300);
            break;
        case 'right':
            var arrowH = 32;
            var arrowW = 26;
            var ttop = pos.top - arrowH + yOffset;
            var tleft = pos.left - width - arrowW + xOffset;
            var arrowPosTop = 10 + yArrowOffset;
            var arrowPosLeft = width - 1 + xArrowOffset;
            if (ttop + boxH > wHeight) {
                var tmp = ttop + boxH - wHeight;
                ttop -= tmp;
                arrowPosTop = arrowPosTop + tmp;
            }

            $(bubbleboxId).css({'width': width + 'px', 'margin-top': '0'});
            $(bbInnerId).css({'width': (width - 32) + 'px'});
            $(bbarrowId).css({'top': arrowPosTop + 'px', "left": arrowPosLeft + "px" });
            $(bbcloseId).css({'left': '-8px', 'top': '-6px'});

            $(bubbleId).css({'left': tleft + 'px', 'top': ttop + 'px'});
            $(bubbleId).fadeIn(300);

            break;
        case 'bottom':
            var arrowH = 26;
            var arrowW = 32;
            var ttop = pos.top - arrowH - boxH + yOffset;
            var tleft = pos.left + xOffset;
            var arrowPosTop = boxH - 1 + yArrowOffset;
            var arrowPosLeft = 5 + xArrowOffset;
            if (tleft + boxW > wWidth) {
                var tmp = tleft + boxW - wWidth;
                tleft -= tmp;
                arrowPosLeft = arrowPosLeft + tmp;
            }
            $(bubbleboxId).css({'width': width + 'px', 'margin-top': '0'});
            $(bbInnerId).css({'width': (width - 32) + 'px'});
            $(bbarrowId).css({'top': arrowPosTop + 'px', "left": arrowPosLeft + "px" });
            $(bbcloseId).css({'left': (width - 10) + 'px', 'top': '-6px'});

            $(bubbleId).css({'left': tleft + 'px', 'top': ttop + 'px'});
            $(bubbleId).fadeIn(300);
            break;
        case 'left':
            var arrowH = 32;
            var arrowW = 26;
            var ttop = pos.top + 10 - Math.round(arrowH / 2) + yOffset + 10;
            var tleft = pos.left + eW + arrowW + xOffset;
            var arrowPosTop = yArrowOffset + 10;
            var arrowPosLeft = arrowW - 1 + xArrowOffset;
            if (ttop + boxH > wHeight) {
                var tmp = ttop + boxH - wHeight;
                ttop -= tmp;
                arrowPosTop = arrowPosTop + tmp;
            }

            $(bubbleboxId).css({'width': width + 'px', 'margin-top': '0'});
            $(bbInnerId).css({'width': (width - 32) + 'px'});
            $(bbarrowId).css({'top': arrowPosTop + 'px', "left": '-' + arrowPosLeft + "px" });
            $(bbcloseId).css({'left': ( width - 10) + 'px', 'top': '-6px'});

            $(bubbleId).css({'left': tleft + 'px', 'top': ttop + 'px'});
            $(bubbleId).fadeIn(300);
            break;
        default:
            break;
    }
}

function bubble_tip_close() {
    var bubbleId = '#bubble-tip';
    var bbInnerId = '#bubble-inner';
    var bbarrowId = '#bubble-arrow';
    var bubbleboxId = '#bubble-box';
    var bbcloseId = '#bubble-close';
    $(bubbleId).fadeOut(300);
    $(bubbleId).css({'left': '0', 'top': '0'});
    $(bbcloseId).css({'left': '0px', 'top': '0'});
    $(bbarrowId).css({'left': '0', 'top': '0'});
    $(bbInnerId).css({'width': 'auto'}).html('');
    $(bubbleboxId).css({'margin-top': +'0', 'top': '0', 'left': '0'});
}
/* end tool tip */


/* for create ad box mua chung thuong hieu */
function mc_step1_continue_click() {
    var desUrl = $.trim($('#desUrl').val());
    var chkUrl = true;
    if (!isUrl(desUrl) || desUrl == '') {
        step1ErrorDesUrl(true);
        checkBodyText = false;
    }
    else {
        step1ErrorDesUrl(false);
        checkBodyText = true;
    }

    var title = $.trim($('#title').val());
    var chkTitle = true;
    if (title == '') {
        step1ErrorTitle(true);
        chkTitle = false;
    }
    else {
        step1ErrorTitle(false);
        chkTitle = true;
    }

    var bodyText = $.trim($('#step1BodyText').val());
    var chkBtext = true;
    if (bodyText == '') {
        step1ErrorBodyText(true);
        chkBtext = false;
    }
    else {
        step1ErrorBodyText(false);
        chkBtext = true;
    }

    var imageFile = $.trim($('#hddFileName').val());
    var imgSrc = $.trim($('#step1_suggest_image').attr('src'));
    var chkImg = true;
    if (imageFile == '' || imgSrc == '') {
        step1ErrorImage(true);
        chkImg = false;
    }
    else {
        step1ErrorImage(false);
        chkImg = true;
    }

    if (chkUrl && chkBtext && chkImg && chkTitle) {
        $('#step1_continue').hide();
        move2Step('#divStep2', 800);
    }
}

function mc_step4_continue(type) {
    type = (typeof(type) === 'undefined' || type === '') ? 'create' : type;
    // check title
    if ($.trim($('#hddTitle').val()) == '') {
        step1ErrorTitle(true);
        $('#title').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        step1ErrorTitle(false);
    }

    // check des url
    var desUrl = $.trim($('#desUrl').val());
    if (desUrl == '' || !isUrl(desUrl)) {
        step1ErrorDesUrl(true);
        $('#desUrl').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        step1ErrorDesUrl(false);
    }

    // check body text
    if ($.trim($('#hddBodyText').val()) == '') {
        step1ErrorBodyText(true);
        $('#step1BodyText').focus();
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        step1ErrorBodyText(false);
    }

    // check image
    if ($.trim($('#hddFileName').val()) == '') {
        step1ErrorImage(true);
        move2Step('#divStep1', 1000);
        return;
    }
    else {
        step1ErrorImage(false);
    }

    // check target location
    var bac = ($('#chkBac').attr('checked')) ? '1' : '0';
    var trung = ($('#chkTrung').attr('checked')) ? '2' : '0';
    var nam = ($('#chkNam').attr('checked')) ? '3' : '0';

    var chkCity = $.trim($('#txtTargetLocationCity').val()) == '' ? false : true;
    if (bac == '0' && trung == '0' && nam == '0' && !chkCity) {
        step2ShowLocationErr();
        move2Step('#divStep2', 1000);
        return;
    }
    else {
        step2ShowLocationErr();
    }


    //end check if add new campaign
    if (type == 'create') {
        //if(confirm('Are you sure create ad?'))
        //{
        loadAjaxLoadingPopup('#imgLoadingPopup', '#ajaxLoadingPopupBackground');
        $('#frmCreateAd').submit();
        //}
    }
    else if (type == 'edit') {
        //if(confirm('Are you sure update ad?'))
        //{
        loadAjaxLoadingPopup('#imgLoadingPopup', '#ajaxLoadingPopupBackground');
        $('#frmCreateAd').submit();
        //}
    }
}
function initCreateAdMcFormField() {
    // step 1
    if ($('#slcBanner') != null) {
        $('#slcBanner').val(0);
    }

    $('#desUrl').val('');
    $('#title').val('');
    $('#hddTitle').val('');
    $('#step1BodyText').val('');
    $('#hddBodyText').val('');
    $('#hddFileName').val('');

    // step 2
    $('#chkLocationAll').attr('checked', 'checked');
    $('#chkBac').attr('checked', 'checked');
    $('#chkTrung').attr('checked', 'checked');
    $('#chkNam').attr('checked', 'checked');

}
/* end for create ad box mua chung thuong hieu */
function notice_tooltip(e, strTipTitle, noticeId, width, xOffset, yOffset, val, pos) {
    width = (typeof(width) === 'undefined' || width === '') ? 200 : width;
    xOffset = (typeof(xOffset) === 'undefined' || xOffset === '') ? 40 : xOffset;
    yOffset = (typeof(yOffset) === 'undefined' || yOffset === '') ? 0 : yOffset;
    if (strTipTitle != '') {
        $('#toannh-tooltip3').width(width);
        $("#toannh-tooltip-title3").html(strTipTitle);
        $("#toannh-tooltip-body3").html($(noticeId).html());
        var ttipW = $('#toannh-tooltip3').width();
        var ttipH = $('#toannh-tooltip3').height();
        if ((ttipW / ttipH) < 3) {
            ttipW = ttipH * 3;
            ttipW = (ttipW > 450) ? 450 : ttipW;
            $('#toannh-tooltip3').width(ttipW);
        }
        var pos = $(e).offset();
        var eWidth = $(e).outerWidth();
        var eHeight = $(e).outerHeight();
        $("#toannh-tooltip3").css('z-index', '-1000000001');
        $("#toannh-tooltip3").show();
        var tooltipHeight = $('#toannh-tooltip3').outerHeight();
        var tooltipWidth = $('#toannh-tooltip3').outerWidth();

        var ttop = pos.top + yOffset + eHeight;
        var tleft = pos.left - xOffset;

        var arrowPosLeft = xOffset - ($('#toannh-tooltip-arrow-down3').outerWidth() / 2) + (eWidth / 2);
        var wheight = $(window).height();
        var wWidth = $(window).width();
        if (tleft + tooltipWidth > wWidth) {
            var tmp = tleft + tooltipWidth - wWidth;
            tleft -= tmp;
            arrowPosLeft = arrowPosLeft + tmp;
        }
        $('#toannh-tooltip-arrow-down3').css("left", arrowPosLeft + "px");
        $("#toannh-tooltip3").css("top", ttop + "px");
        $("#toannh-tooltip3").css("left", tleft + "px");
        $("#toannh-tooltip3").css('z-index', '1000000001');
    }
    $('#noticetooltip').attr('href', makeSiteUrl('home/editAd/' + val, true));
}
function removeNotice_Tooltip() {
    $("#toannh-tooltip3").css('display', 'none');
    $("#toannh-tooltip-title3").html('');
    $("#toannh-tooltip-body3").html('');
}
function edit_check() {
    //account_checkuname(); account_checkemail();
    var uname = $.trim($('#username').val());
    var pass = $.trim($('#password').val());
    var repass = $.trim($('#repassword').val());

    var fullname = $.trim($('#fullname').val());

    var email = $.trim($('#email').val());

    var phone = $.trim($('#phone').val());
    var address = $.trim($('#address').val());

    var uname_error = validateUsername(uname);
    var pass_error = validatePassword(pass);
    var email_error = validateEmail(email);

    var captcha = $.trim($('#aCaptcha').val());
    var term = $('#cbPolicy:checked').val();

    if (uname_error == '') uname_error = $.trim($('#errorUsername').val());
    if (email_error == '') email_error = $.trim($('#errorEmail').val());


    var chkPass = true;
    if (pass != '' && repass == '') {
        $('#errorPassword').html(account_lang['enter_pass']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        chkPass = false;
    }
    else if (pass != repass) {
        $('#errorPassword').html(account_lang['pass_not_match']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        $('#repassword').addClass('error-border');
        chkPass = false;
    }
    else if (pass.length < 6 || pass.length > 64) {
        $('#errorPassword').html(account_lang['pass_short']);
        $('#errorPassword').show();
        $('#password').addClass('error-border');
        chkPass = false;
    }
    else {
        $('#errorPassword').hide();
        $('#password').removeClass('error-border');
        $('#repassword').removeClass('error-border');
        chkPass = true;
    }

    var chkEmail = true;
    if (email_error != '') {
        $('#errorEmail').html(email_error);
        $('#errorEmail').show();
        $('#email').addClass('error-border');
        chkEmail = false;
    }
    else {
        $('#errorEmail').hide();
        $('#email').removeClass('error-border');
        chkEmail = true;
    }

    var chkFullName = true;
    if (fullname == '') {
        $('#errorFullname').html(account_lang['enter_fullname']);
        $('#errorFullname').show();
        $('#fullname').addClass('error-border');
        chkFullName = false;
    }
    else {
        $('#errorFullname').hide();
        $('#fullname').removeClass('error-border');
        chkFullName = true;
    }

    var chkPhone = true;
    if (phone == '') {
        $('#errorPhone').html(account_lang['enter_phone']);
        $('#errorPhone').show();
        $('#phone').addClass('error-border');
        chkPhone = false;
    }
    else if (!phonenumber_validate(phone)) {
        $('#errorPhone').html(account_lang['phone_not_type']);
        $('#errorPhone').show();
        $('#phone').addClass('error-border');
        chkPhone = false;
    }
    else {
        $('#errorPhone').hide();
        $('#phone').removeClass('error-border');
        chkPhone = true;
    }

    //check captcha
    var chkCaptcha = true;
    if (captcha == '') {
        $('#errorCaptcha').html(account_lang['enter_captcha']);
        $('#errorCaptcha').show();
        $('#aCaptcha').addClass('error-border');
        chkCaptcha = false;
    }
    else {
        $('#errorCaptcha').hide();
        $('#aCaptcha').removeClass('error-border');
        chkCaptcha = true;
    }

    //Check term
    var chkTerm = true;
    if (term != '1') {
        $('#errorTerm').html(account_lang['enter_terms']);
        $('#errorTerm').show();
        chkTerm = false;
    }
    else {
        $('#errorTerm').hide();
        chkTerm = true;
    }

    if (chkUserName && chkPass && chkEmail && chkFullName && chkPhone && chkCaptcha && chkTerm) {
        $('#frmSignUp').submit();
        return;
        // use ajax
        var dataString = 'username=' + uname + '&password=' + pass + '&repassword=' + repass + '&fullname=' + fullname + '&email=' + email + '&phone=' + phone + '&address=' + address + '&aCaptcha=' + captcha + '&callback=1';
        var onSuccess = function (data) {
            //Show popup
            if (data != '') {
                var objData = jQuery.parseJSON(data);
                //alert(objData.captcha);
                if (!objData.captcha) {
                    $('#errorCaptcha').html(objData.captcha_msg);
                    $('#errorCaptcha').show();
                    $('#aCaptcha').addClass('error-border');
                    return false;
                }

                var chk = $.trim(objData.chk);
                $('body').append(chk + '<div id="fade"></div>');
                $('#fade').css({'filter': 'alpha(opacity=80)'}).fadeIn();

                $(document).ready(function () {
                    $('#fade').click(function () {
                        $('#fade').fadeOut();
                        return false;
                    });
                });
            }
        };
        account_checkuname();
        account_checkemail();
        getAjax(makeSiteUrl('/account/signup'), dataString, '', 'POST', '', false, onSuccess);
    }
}

/* switch user */
function showSuser(obj, eId) {
    var pos = $(obj).offset();
    var eW = $(eId).outerWidth();
    var tleft = pos.left - eW + 12;
    var ttop = pos.top + 10;

    $(eId).css("top", ttop + "px");
    $(eId).css("left", tleft + "px");
    $(eId).slideToggle(500);
}

function suser(uname, imdLoading, divLoadingBg) {
    var u = $.trim(uname);
    if (u == '') {
        alert(common_lang['switch_user_err']);
        return;
    }

    var onSuccess = function (data) {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
        var objData = jQuery.parseJSON(data);
        if (objData.r == '1') {
            window.location = makeSiteUrl('');
            return;
        }
        else {
            if (objData.r == '0') {
                window.location.reload(true);
                return;
            }
            if (objData.r == '-3') {
                alert(common_lang['switch_user_not_found']);
                return;
            }
            else if (objData.r == '-1' || objData.r == '-2') {
                alert(common_lang['switch_user_not_right']);
                return;
            }
        }
    };
    var onError = function () {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
    };
    loadAjaxLoadingPopup(imdLoading, divLoadingBg);
    getAjax(base_url + 'suser/change', 'u=' + u, '', 'post', '', false, onSuccess, onError);
}

function viewas_suser(url, uname, imdLoading, divLoadingBg) {
    var u = $.trim(uname);
    if (u == '') {
        alert(common_lang['switch_user_err']);
        return;
    }

    var onSuccess = function (data) {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
        var objData = jQuery.parseJSON(data);
        if (objData.r == '1') {
            window.location = makeSiteUrl('banner');
            return;
        }
        else {
            if (objData.r == '0') {
                window.location.reload(true);
                return;
            }
            if (objData.r == '-3') {
                alert(common_lang['switch_user_not_found']);
                return;
            }
            else if (objData.r == '-1' || objData.r == '-2') {
                alert(common_lang['switch_user_not_right']);
                return;
            }
        }
    };
    var onError = function () {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
    };
    loadAjaxLoadingPopup(imdLoading, divLoadingBg);
    getAjax(url, 'u=' + u, '', 'post', '', false, onSuccess, onError);
}

function suser_pager(cPage, groupId, eId, imdLoading, divLoadingBg) {
    var onSuccess = function (data) {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
        if ($.trim(data) == 'login') {
            window.location.reload(true);
            return;
        }
        else {
            if ($.trim(data) == 'notright') {
                alert(common_lang['switch_user_not_right']);
                return;
            }
            else {
                $(eId).html(data);
                return;
            }
        }
    };
    var onError = function () {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
    };
    loadAjaxLoadingPopup(imdLoading, divLoadingBg);
    getAjax(makeSiteUrl('suser/view_paging/' + cPage + '/' + groupId), '', '', '', '', false, onSuccess, onError);
}

function suser_filter(keyId, stypeEId, groupId, eId, imdLoading, divLoadingBg) {
    var keyword = $.trim($(keyId).val());
    var stype = $.trim($(stypeEId).val());
    var isSearch = (keyword != '') ? true : false;
    if (keyword.length < 3 && keyword.length > 0) {
        alert(common_lang['switch_user_snumword_error']);
        $(keyId).focus();
        return;
    }
    var onSuccess = function (data) {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
        if ($.trim(data) == 'login') {
            window.location.reload(true);
            return;
        }
        else {
            if ($.trim(data) == 'notright') {
                alert(common_lang['switch_user_not_right']);
                return;
            }
            else {
                $(eId).html(data);
                return;
            }
        }
    };
    var onError = function () {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
    };
    loadAjaxLoadingPopup(imdLoading, divLoadingBg);
    var url = (isSearch) ? base_url + 'suser/search/' : base_url + 'suser/view_paging/1/' + groupId;
    var param = (isSearch) ? 'stype=' + stype + '&g=' + groupId + '&cpage=1&keyword=' + UrlEncode.encode(keyword) : '';
    getAjax(url, param, '', '', '', false, onSuccess, onError);
}

function suser_search_pager(keyId, stypeEId, cPage, groupEId, eId, imdLoading, divLoadingBg) {
    var keyword = $.trim($(keyId).val());
    var stype = $.trim($(stypeEId).val());
    var groupId = '-1';
    if ($(groupEId).length) {
        groupId = $.trim($(groupEId).val());
    }

    if (keyword.length < 3) {
        alert(common_lang['switch_user_snumword_error']);
        $(keyId).focus();
        return;
    }

    var onSuccess = function (data) {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
        if ($.trim(data) == 'login') {
            window.location.reload(true);
            return;
        }
        else {
            if ($.trim(data) == 'notright') {
                alert(common_lang['switch_user_not_right']);
                return;
            }
            else {
                $(eId).html(data);
                return;
            }
        }
    };
    var onError = function () {
        disableAjaxLoadingPopup(imdLoading, divLoadingBg);
    };
    loadAjaxLoadingPopup(imdLoading, divLoadingBg);

    getAjax(base_url + 'suser/search/', 'stype=' + stype + '&g=' + groupId + '&cpage=' + cPage + '&keyword=' + UrlEncode.encode(keyword), '', '', '', false, onSuccess, onError);
}
/* end switch user */

/* ub multi */
function bchkAll(obj, divId, chkItemClass, hddId) {
    var strId = $.trim($(hddId).val());
    var arrId = new Array();
    if (strId != '') {
        arrId = strId.split(',');
    }
    if ($(obj).attr('checked')) {
        var arr = $(divId + ' input.' + chkItemClass);
        jQuery.each(arr, function () {
            var id = $.trim($(this).val());
            if (jQuery.inArray(id, arrId) == -1) {
                arrId.push(id);
            }
            $(this).attr('checked', 'checked');
        });
        $(hddId).val(arrId.join(','));
    }
    else {
        var arr = $(divId + ' input.' + chkItemClass);
        jQuery.each(arr, function () {
            var id = $.trim($(this).val());
            var index = jQuery.inArray(id, arrId);
            if (index > -1) {
                arrId[index] = '';
            }
            $(this).attr('checked', false);
        });
        var str = '';
        $.each(arrId, function (idx, val) {
            if ($.trim(arrId[idx]) != '') {
                str += (str != '') ? ',' + val : val;
            }
        });

        $(hddId).val(str);
    }
}

function bchkItem(obj, divId, chkAllId, chkItemClass, hddId) {
    var strId = $.trim($(hddId).val());
    var arrId = new Array();
    if (strId != '') {
        arrId = strId.split(',');
    }

    var arr = $(divId + ' input.' + chkItemClass);
    var chkAll = true;
    var num = arr.length;
    for (var i = 0; i < num; i++) {
        var id = $.trim($(arr[i]).val());
        if ($(arr[i]).attr('checked')) {
            if (jQuery.inArray(id, arrId) == -1) {
                arrId.push(id);
            }
        }
        else {
            chkAll = false;
            var index = jQuery.inArray(id, arrId);
            if (index > -1) {
                arrId[index] = '';
            }
        }
    }
    if (chkAll) {
        $(chkAllId).attr('checked', 'checked');
    }
    else {
        $(chkAllId).attr('checked', false);
    }
    var str = '';
    $.each(arrId, function (idx, val) {
        if ($.trim(arrId[idx]) != '') {
            str += (str != '') ? ',' + val : val;
        }
    });
    $(hddId).val(str);
}

function ubmulti(action, hddEmaId, imgLoadId, divBgLoadId, page, isReload) {
    isReload = (typeof(isReload) === 'undefined' || isReload === '') ? false : isReload;
    var str = $.trim($(hddEmaId).val());
    var _onSuccess = function (data) {
        if (isReload) {
            window.location.reload(true);
        }
        else {
            dbLoadPage(page, imgLoadId, divBgLoadId);
        }
    };

    var _onError = function () {
        disableAjaxLoadingPopup(imgLoadId, divBgLoadId);
    };
    if (str != '') {
        loadAjaxLoadingPopup(imgLoadId, divBgLoadId);
        getAjax(makeSiteUrl('banner/emulti'), 'ac=' + action + '&lsb=' + str, '', 'GET', '', false, _onSuccess, _onError);
    }
}

function emainit(divId, chkAllId, chkItemClass, hddId, itemIdPrefix) {
    var strId = $.trim($(hddId).val());
    if (strId != '') {
        var arr = strId.split(',');
        var num = arr.length;
        for (var i = 0; i < num; i++) {
            if ($.trim(arr[i]) != '') {
                $(itemIdPrefix + arr[i]).attr('checked', 'checked');
            }
        }
    }

    var arr = $(divId + ' input.' + chkItemClass);
    var chkAll = true;
    var num = arr.length;
    for (var i = 0; i < num; i++) {
        if (!$(arr[i]).attr('checked')) {
            chkAll = false;
            break;
        }
    }
    if (chkAll && num > 0) {
        $(chkAllId).attr('checked', 'checked');
    }
    else {
        $(chkAllId).attr('checked', false);
    }
}
/* end ub multi */

/* for promotion website */
function sp_tooltip(obj, content, eId, eInnerId) {
    $(eInnerId).html(content);
    $(eInnerId).removeClass('pad10').addClass('pad5');
    var objParent = $(obj).parent().parent();
    var pos = $(objParent).offset();
    var tleft = pos.left;
    var w = $(objParent).outerWidth();
    var explH = $(eId).outerHeight();
    var ttop = pos.top - explH;

    $(eId).css("top", ttop + "px");
    $(eId).css("left", tleft + "px");
    $(eId).css("width", w + "px");
    $(eId).fadeIn(300);
}
function sp_tooltip_out(eId, eInnerId) {
    $(eId).fadeOut(300);
    $(eInnerId).html('');
    $(eInnerId).removeClass('pad5').addClass('pad10');
}
/* end for promotion website */


/* for plus */
function initCrop(imgId, tposid, lposid, wposid) {
    var arr = $('#divCrop .jcrop-holder');
    if (arr.length == 0) {
        $(imgId).Jcrop({
            onSelect: function (c) {
                $(lposid).val(c.x);
                $(tposid).val(c.y);
                $(wposid).val(c.w);
            },
            onChange: function (c) {
                $(lposid).val(c.x);
                $(tposid).val(c.y);
                $(wposid).val(c.w);
            },
            minSize: [158, 158],
            bgColor: 'black',
            bgOpacity: .4,
            aspectRatio: 1
        }, function () {
            cropObj = this;
        });
    }
    else {
        cropObj.destroy();
        $(imgId).Jcrop({
            onSelect: function (c) {
                $(lposid).val(c.x);
                $(tposid).val(c.y);
                $(wposid).val(c.w);
            },
            onChange: function (c) {
                $(lposid).val(c.x);
                $(tposid).val(c.y);
                $(wposid).val(c.w);
            },
            minSize: [158, 158],
            bgColor: 'black',
            bgOpacity: .4,
            aspectRatio: 1
        }, function () {
            cropObj = this;
        });
    }
}

function reinitCrop(w, h) {
    w = parseInt(w, 10);
    h = parseInt(h, 10);
    var src = $('#cropimg').attr('src');
    //$('#divCrop .jcrop-holder').find('img').attr('src', '').attr('src', src).css({'width': w + 'px', 'height': h + 'px'});
    //$('#divCrop .jcrop-holder').css({'width': w+ 'px', 'height': h + 'px'});
    //$('#divCrop .jcrop-tracker').css({'width': (w + 4) + 'px', 'height': (h + 4) + 'px'});
}

function applyCrop(filecropId, tid, lid, wid, imgloadingid, errorId) {
    var f = $(filecropId).val();
    var t = $(tid).val();
    var l = $(lid).val();
    var w = $(wid).val();
    if (confirm(home_lang['crop_confirm'])) {
        var _onOk = function (data) {
            var objData = jQuery.parseJSON(data);
            if ($.trim(objData.errors) == '') {
                $(errorId).hide();
                $(errorId).find('li').html('');
                $('#hddFileName').val(objData.filename);
                $('#imgS1Review1').attr('src', objData.image_path);
                $('#imgS1Review2').attr('src', objData.image_path);
                $('#review_image').attr('src', objData.image_path);
            }
            else {
                $(errorId).find('li').html(objData.errors);
                $(errorId).show();
            }
            $('#imgcropLoading').hide();
            closeCrop('#divCrop', '#ajaxLoadingPopupBackground');
        };

        var _onError = function () {
            $('#imgcropLoading').hide();
            closeCrop('#divCrop', '#ajaxLoadingPopupBackground');
            $(errorId).find('li').html('error');
            $(errorId).show();
        };
        $('#imgcropLoading').show();
        getAjax(makeSiteUrl('createad/apply_crop'), 'f=' + f + '&w=' + w + '&t=' + t + '&l=' + l, '', '', '', false, _onOk, _onError);
    }
}

function showCrop(divId, cropimgId, divCropInnerId, divBgId, imgCropW, imgCropH) {
    initCrop(cropimgId, '#hddCropLeft', '#hddCropTop', '#hddCropWidth');
    reinitCrop(imgCropW, imgCropH);

    //request data for centering
    var windowWidth = document.documentElement.clientWidth;
    var windowHeight = document.documentElement.clientHeight;
    var bodywidth = $('body').innerWidth();
    var bodyheight = $('body').innerHeight();
    var popupHeight = $(divId).height();
    var popupWidth = 0;
    var imgW = parseInt(imgCropW, 10) + 4;
    if (imgW < (windowWidth - 12)) {
        $(divId).css({'width': imgW + 'px'});
        popupWidth = imgW;
    }
    else {
        popupWidth = windowWidth - 20;
        $(divId).css({'width': popupWidth + 'px'});
    }
    $(divCropInnerId).css({'width': popupWidth + 'px'});

    var wpos = (bodywidth > windowWidth) ? bodywidth : windowWidth;
    var hpos = (bodyheight > windowHeight) ? bodyheight : windowHeight;
    var scrollWindow = $(window).scrollTop();
    var top = windowHeight / 2 - ((popupHeight / 3) * 2) + scrollWindow;
    var left = windowWidth / 2 - popupWidth / 2;

    //centering
    $(divId).css({
        "position": "absolute",
        "top": top,
        "left": left
    });
    //only need force for IE6
    $(divBgId).css({
        "height": hpos,
        "width": wpos
    });

    $(window).scroll(function () {
        if ($(divId).css('display') != 'none') {
            $(divId).stop();
            var scroll = $(window).scrollTop();
            var scrollPos = windowHeight / 2 - ((popupHeight / 3) * 2) + scroll;
            $(divId).animate({top: scrollPos}, 'slow');
        }
    });

    $(divBgId).css({
        "opacity": "0.5"
    });
    $(divBgId).fadeIn("fast");
    $(divId).fadeIn("fast");
}

function closeCrop(divId, divBgId) {
    $(divBgId).fadeOut();
    $(divBgId).hide();
    $(divId).hide();
}

function startCrop(isOk, imgCropW, imgCropH, imgsrctip) {
    imgsrctip = (typeof(imgsrctip) === 'undefined' || imgsrctip === '') ? '' : imgsrctip;
    if (isOk) {
        $('#imgS1Review1').addClass('cursor-pointer');
        $('#imgS1Review2').addClass('cursor-pointer');
        $('#imgS1Review1, #imgS1Review2').click(function () {
            showCrop('#divCrop', '#cropimg', '#divCropInner', '#ajaxLoadingPopupBackground', imgCropW, imgCropH);
        });
        if (imgsrctip != '') {
            bubble_tip('#imgS1Review1', home_lang['crop_image_tip'], 'top', 300, imgsrctip, 0, 0, 15);
        }
    }
    else {
        $('#imgS1Review1').removeClass('cursor-pointer');
        $('#imgS1Review2').removeClass('cursor-pointer');
        $('#imgS1Review1, #imgS1Review2').click(function () {
            return;
        });
        closeCrop('#divCrop', '#ajaxLoadingPopupBackground');
    }
}
function slccaptain(val) {
    $('#s1Captain').attr('class', '').addClass(val).css({left: '0', top: '0'});
}

function captainChange(id, max, maxId) {
    var x = $.trim($(id).val());
    if (x.length > 12) {
        x = $.trim(x.substring(0, 12));
    }
    //x = x.replace(/ /gi, '&nbsp;');
    $(maxId).html(max - x.length);
    $(id).val(x);
    $('#s1Captain').html(x).css({left: '0', top: '0'});
}

function captainColor(clor) {
    $('#s1Captain').css({color: clor});
}
/* end for plus */


// function for test select style
function fstyle_init() {
    var arrItem = $('#fstyle-slider .fstyle-60-box');
    var numItem = arrItem.length;
    $('#fstyle-slider').css({width: (numItem * 80) + 'px', left: '0px'});
    $('#fstyle-pre').addClass('fstyle-next-pre-hidden');
    if (numItem <= 6) {
        $('#fstyle-next').addClass('fstyle-next-pre-hidden');
    }
}
function fstyle_next() {
    var l = $('#fstyle-slider').css('left').toLowerCase().replace('px', '');
    l = parseInt(l);
    var w = $('#fstyle-slider').width();
    var pW = $('#fstyle-slider').parent().width();
    var chk = w + l - pW;
    if (chk > 0) {
        $('#fstyle-next').attr('onclick', '');
        $('#fstyle-pre').removeClass('fstyle-next-pre-hidden');
        $('#fstyle-slider').animate({left: '-=480'}, 1000, function () {
            $('#fstyle-next').attr('onclick', 'fstyle_next();');
        });
    }
    if (chk <= 480) {
        $('#fstyle-next').addClass('fstyle-next-pre-hidden');
    }
}

function fstyle_pre() {
    var l = $('#fstyle-slider').css('left').toLowerCase().replace('px', '');
    l = parseInt(l);
    if (l < 0) {
        $('#fstyle-pre').attr('onclick', '');
        $('#fstyle-next').removeClass('fstyle-next-pre-hidden');
        $('#fstyle-slider').animate({left: '+=480'}, 1000, function () {
            $('#fstyle-pre').attr('onclick', 'fstyle_pre();');
        });
    }
    if (l >= -480) {
        $('#fstyle-pre').addClass('fstyle-next-pre-hidden');
    }
}

function style_hover(obj, styleClass, styleColor) {
    if (!$(obj).hasClass('selected')) {
        $(obj).find('span.fstyle-60-box-over').css({'z-index': '-1'});
        $('#fstyle-bubble-text').attr('class', '').addClass(styleColor);
        $('#fstyle-bubble-table-text').attr('class', '').addClass(styleClass);
    }
    $('#fstyle-bubble-text').html($(obj).find('table td').html());
    var boxPos = $('#fstyle-box-content').position();
    var pos = $(obj).position();
    var l = $('#fstyle-slider').css('left').toLowerCase().replace('px', '');
    l = parseInt(l);
    var tmp = (pos.left + l);
    var index = (tmp - (tmp % 80)) / 80;
    var addLeft = (80 * index) - 25;
    $('#fstyle-bubble-tip').css({left: (boxPos.left + addLeft) + 'px'}).show();
}

function style_out(obj) {
    if (!$(obj).hasClass('selected')) {
        $(obj).find('span.fstyle-60-box-over').css({'z-index': '10'});
    }
    $('#fstyle-bubble-tip').hide();
}

function style_click(obj, styleCssClass, colorCssClass) {
    var captain = $.trim($('#txtcaptain').val());
    captain
    $('#fstyle-slider .fstyle-60-box').removeClass('selected');
    $('#fstyle-slider .fstyle-60-box').find('span.fstyle-60-box-over').css({'z-index': '10'});
    $('#s1Captain').attr('class', '').addClass(colorCssClass);
    $('#tblCaptain').attr('class', styleCssClass).show();
    $(obj).addClass('selected').find('span.fstyle-60-box-over').css({'z-index': '-1'});

    $('#hddCaptainStyle').val(styleCssClass);
    $('#hddCaptainColor').val(colorCssClass);

    if ($('#s1Captain').width() > 10) {
        $('#s1Captain').html(captain);
    }
    else {
        var tmp = '';
        var num = captain.length;
        for (var i = 0; i < num; i++) {
            if (captain[i] != '') {
                tmp += captain[i] + ' ';
            }
        }
        tmp = $.trim(tmp);
        if (tmp.length > 12) {
            tmp = tmp.substring(0, 12);
        }
        tmp = $.trim(tmp);
        $('#s1Captain').html(tmp);
    }
}
// end function for test select style
// for gender + hobby
function chkAllGender(chkAll, chkMale, maleName, chkFemale, femaleName, estId, estContentId, revId, revContentId, isUpdateEst) {
    var isUpdateEst = (typeof(isUpdateEst) === 'undefined' || isUpdateEst === '') ? true : isUpdateEst;
    if ($(chkAll).attr('checked')) {
        $(chkMale).attr('checked', 'checked');
        $(chkFemale).attr('checked', 'checked');
    }
    else {
        $(chkMale).attr('checked', false);
        $(chkFemale).attr('checked', false);
    }
    chkGender(chkAll, chkMale, maleName, chkFemale, femaleName, estId, estContentId, revId, revContentId, isUpdateEst);
}
function chkGender(chkAll, chkMale, maleName, chkFemale, femaleName, estId, estContentId, revId, revContentId, isUpdateEst) {
    var isUpdateEst = (typeof(isUpdateEst) === 'undefined' || isUpdateEst === '') ? true : isUpdateEst;
    var m = $(chkMale).attr('checked');
    var mname = $.trim($(maleName).html());
    var f = $(chkFemale).attr('checked');
    var fname = $.trim($(femaleName).html());
    if (m || f) {
        if (m && f) {
            $(chkAll).attr('checked', 'checked');
        }
        else {
            $(chkAll).attr('checked', false);
        }
        var strEst = '';
        var strReview = '';
        if (m) {
            strReview += '<li class="liGenRev">' + mname + '</li>';
            strEst += '<li class="liGenEst">' + mname + '</li>';
        }

        if (f) {
            strReview += '<li class="liGenRev">' + fname + '</li>';
            strEst += '<li class="liGenEst">' + fname + '</li>';
        }

        $(estContentId).html(strEst);
        $(revContentId).html(strReview);
        $(estId).show();
        $(revId).show();
    }
    else {
        $(estId).hide();
        $(revId).hide();
    }
    if (isUpdateEst) {
        updateEstimate();
    }
}

function chkHobby(chkId, boxId, chkClass, nameClass, divEstId, ulEstId, liEstClass, maxEstItem, divRevId, ulRevId, liRevClass, maxRevItem, expEstId, expRevId) {
    var strCurrId = $.trim($('#hddHobby').val());
    var arrCurr = new Array();
    if (strCurrId != '') {
        arrCurr = strCurrId.split(',');
    }
    var id = $(chkId).val();

    if ($(chkId).attr('checked')) {
        if (jQuery.inArray(id, arrCurr) == -1) {
            arrCurr.push(id);
        }
        $('#hddHobby').val(arrCurr.join(','));
    }
    else {
        var index = jQuery.inArray(id, arrCurr);
        if (index > -1) {
            arrCurr.splice(index, 1);
        }
        $('#hddHobby').val(arrCurr.join(','));
    }
    updateTrgReview(boxId, chkClass, nameClass, divEstId, ulEstId, liEstClass, maxEstItem, divRevId, ulRevId, liRevClass, maxRevItem, expEstId, expRevId);
    updateEstimate();
}

function chkAge(chkId, boxId, chkClass, nameClass, divEstId, ulEstId, liEstClass, maxEstItem, divRevId, ulRevId, liRevClass) {
    var strCurrId = $.trim($('#hddAge').val());
    var arrCurr = new Array();
    if (strCurrId != '') {
        arrCurr = strCurrId.split(',');
    }
    var id = $(chkId).val();
    if ($(chkId).attr('checked')) {
        if (jQuery.inArray(id, arrCurr) == -1) {
            arrCurr.push(id);
        }
        arrCurr.sort();
        $('#hddAge').val(arrCurr.join(','));
    }
    else {
        var index = jQuery.inArray(id, arrCurr);
        if (index > -1) {
            arrCurr.splice(index, 1);
        }
        arrCurr.sort();
        $('#hddAge').val(arrCurr.join(','));
    }
    var arrChk = $(boxId + ' .' + chkClass);
    var arrName = $(boxId + ' .' + nameClass);
    var num = arrChk.length;
    var strRev = '';
    var strEst = '';
    var numSiteCheck = 0;
    chkAll = true;
    for (var i = 0; i < num; i++) {
        if ($(arrChk[i]).attr('checked')) {
            var name = $.trim($(arrName[i]).html());
            strRev += '<li class="' + liRevClass + '">' + name + '</li>';
            strEst += '<li class="' + liEstClass + '">' + name + '</li>';
            numSiteCheck++;
            if (jQuery.inArray($(arrChk[i]).val(), arrCurr) == -1) {
                arrCurr.push($(arrChk[i]).val());
            }
        } else {
            var index = jQuery.inArray($(arrChk[i]).val(), arrCurr);
            if (index > -1) {
                arrCurr.splice(index, 1);
            }
            chkAll = false;
        }
    }
    arrCurr.sort();
    $('#hddAge').val(arrCurr.join(','));
    if (ulRevId != '') {
        $(ulRevId).html(strRev);
    }
    if (ulEstId != '') {
        $(ulEstId).html(strEst);
    }
    if (numSiteCheck > 0) {
        if (divRevId != '') {
            $(divRevId).css('display', '');
        }
        if (divEstId != '') {
            $(divEstId).css('display', '');
        }
    }
    else {
        if (divRevId != '') {
            $(divRevId).css('display', 'none');
        }
        if (divEstId != '') {
            $(divEstId).css('display', 'none');
        }
    }
    if (chkAll == false) {
        $('#chkAgeGame_0').attr('checked', '');
    } else {
        $('#chkAgeGame_0').attr('checked', 'checked');
    }
    updateEstimate();
}

function chkAllAge() {
    var checked = $('#chkAgeGame_0').attr('checked') ? 'checked' : '';
    $('.chkAge').attr('checked', checked);

    var arrChk = $('#divTrgAge .chkAge');
    var arrName = $('#divTrgAge .agename');
    var num = arrChk.length;
    var strRev = '';
    var strEst = '';
    var numSiteCheck = 0;

    if (checked == '') {
        $('#hddAge').val('');
    }
    var strCurrId = $.trim($('#hddAge').val());
    var arrCurr = new Array();
    if (strCurrId != '') {
        arrCurr = strCurrId.split(',');
    }
    for (var i = 0; i < num; i++) {
        if ($(arrChk[i]).attr('checked')) {
            var name = $.trim($(arrName[i]).html());
            strRev += '<li class="liEstAge">' + name + '</li>';
            strEst += '<li class="liEstAge">' + name + '</li>';
            numSiteCheck++;
            if (jQuery.inArray($(arrChk[i]).val(), arrCurr) == -1) {
                arrCurr.push($(arrChk[i]).val());
            }
        } else {
            var index = jQuery.inArray($(arrChk[i]).val(), arrCurr);
            if (index > -1) {
                arrCurr.splice(index, 1);
            }
        }
    }
    arrCurr.sort();
    $('#hddAge').val(arrCurr.join(','));

    $('#estAge').html(strRev);
    $('#review_age').html(strRev);
    if (numSiteCheck > 0) {
        if ($('#trReviewAge') != '') {
            $('#trReviewAge').css('display', '');
        }
        if ($('#divEstAge') != '') {
            $('#divEstAge').css('display', '');
        }
    }
    else {
        if ($('#trReviewAge') != '') {
            $('#trReviewAge').css('display', 'none');
        }
        if ($('#divEstAge') != '') {
            $('#divEstAge').css('display', 'none');
        }
    }
    updateEstimate();
}

function trgExpand(iconId, ulId, liClass, maxItem) {
    if ($(iconId).hasClass('expand')) {
        var arr = $(ulId + 'li.' + liClass);
        var num = arr.length;
        if (num > (maxItem + 1)) {
            for (var i = maxItem; i < num; i++) {
                $(arr[i]).show();
            }
        }
        $(iconId).html(home_lang['collapse']);
        $(iconId).removeClass('expand');
        $(iconId).addClass('collaspe');
    }
    else if ($(iconId).hasClass('collaspe')) {
        var arr = $(ulId + 'li.' + liClass);
        var num = arr.length;
        if (num > (maxItem + 1)) {
            for (var i = maxItem; i < num; i++) {
                $(arr[i]).hide();
            }
        }
        $(iconId).html(home_lang['expand']);
        $(iconId).removeClass('collaspe');
        $(iconId).addClass('expand');
    }
    scrollEstimateBox();
}

function updateTrgReview(boxId, chkClass, nameClass, divEstId, ulEstId, liEstClass, maxEstItem, divRevId, ulRevId, liRevClass, maxRevItem, expEstId, expRevId) {
    var arrChk = $(boxId + ' .' + chkClass);
    var arrName = $(boxId + ' .' + nameClass);
    var num = arrChk.length;
    var strRev = '';
    var strEst = '';
    var numSiteCheck = 0;
    for (var i = 0; i < num; i++) {
        if ($(arrChk[i]).attr('checked')) {
            var name = $.trim($(arrName[i]).html());
            var styleEst = (i < maxEstItem) ? '' : 'display:none;';
            var styleRev = (i < maxRevItem) ? '' : 'display:none;';
            strRev += '<li class="' + liRevClass + '" style="' + styleRev + '">' + name + '</li>';
            strEst += '<li class="' + liEstClass + '" style="' + styleEst + '">' + name + '</li>';
            numSiteCheck++;
        }
    }
    if (numSiteCheck > maxEstItem) {
        strEst += '<li id="' + expEstId + '" class="expand" onclick="trgExpand(\'' + expEstId + '\',\'' + ulEstId + '\',\'' + liEstClass + '\', \'' + maxEstItem + '\');" >' + home_lang['expand'] + '</li>';
    }
    if (numSiteCheck > maxRevItem) {
        strRev += '<li id="' + expRevId + '" class="expand" onclick="trgExpand(\'' + expRevId + '\',\'' + ulRevId + '\', \'' + liRevClass + '\',\'' + maxRevItem + '\');" >' + home_lang['expand'] + '</li>';
    }
    if (ulRevId != '') {
        $(ulRevId).html(strRev);
    }
    if (ulEstId != '') {
        $(ulEstId).html(strEst);
    }

    if (numSiteCheck > 0) {
        if (divRevId != '') {
            $(divRevId).css('display', '');
        }
        if (divEstId != '') {
            $(divEstId).css('display', '');
        }
    }
    else {
        if (divRevId != '') {
            $(divRevId).css('display', 'none');
        }
        if (divEstId != '') {
            $(divEstId).css('display', 'none');
        }
    }
}
function updateAgeReview() {
    var arrChk = $('#divTrgAge .chkAge');
    var arrName = $('#divTrgAge .agename');
    var num = arrChk.length;
    var strRev = '';
    var strEst = '';
    var numSiteCheck = 0;

    var strCurrId = $.trim($('#hddAge').val());
    var arrCurr = new Array();
    if (strCurrId != '') {
        arrCurr = strCurrId.split(',');
    }
    for (var i = 0; i < num; i++) {
        if ($(arrChk[i]).attr('checked')) {
            var name = $.trim($(arrName[i]).html());
            strRev += '<li class="liEstAge">' + name + '</li>';
            strEst += '<li class="liEstAge">' + name + '</li>';
            numSiteCheck++;
            if (jQuery.inArray($(arrChk[i]).val(), arrCurr) == -1) {
                arrCurr.push($(arrChk[i]).val());
            }
        } else {
            var index = jQuery.inArray($(arrChk[i]).val(), arrCurr);
            if (index > -1) {
                arrCurr.splice(index, 1);
            }
        }
    }
    arrCurr.sort();
    $('#hddAge').val(arrCurr.join(','));

    $('#estAge').html(strEst);
    $('#review_age').html(strRev);
    if (numSiteCheck > 0) {
        if ($('#trReviewAge') != '') {
            $('#trReviewAge').css('display', '');
        }
        if ($('#divEstAge') != '') {
            $('#divEstAge').css('display', '');
        }
    }
    else {
        if ($('#trReviewAge') != '') {
            $('#trReviewAge').css('display', 'none');
        }
        if ($('#divEstAge') != '') {
            $('#divEstAge').css('display', 'none');
        }
    }
}
// end for gender + hobby


function loadsorter (){
    //sorter,pager
    jQuery.tablesorter.addParser({
        id: "fancyNumber",
        is: function(s) {
            return false;
        },
        format: function(s) {
            return parseFloat(s.replace(/,/g,'')) ;
        },
        type: "numeric"
    });

    var myBannerDetailTextExtraction = function(node)
    {
        var cellIndex = node.cellIndex;
        var rel = '';
        switch(cellIndex)
        {
            case 4:
                var arrA = $(node).find('a');

                if(arrA.length > 0)
                {
                    rel = $.trim($(arrA[0]).html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                else
                {
                    rel = $.trim($(node).find('p').html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                break;
            case 5:
                var arrA = $(node).find('a');

                if(arrA.length > 0)
                {
                    rel = $.trim($(arrA[0]).html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                else
                {
                    rel = $.trim($(node).find('p').html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                break;
            case 6:
                var arrA = $(node).find('a');

                if(arrA.length > 0)
                {
                    rel = $.trim($(arrA[0]).html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                else
                {
                    rel = $.trim($(node).find('p').html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                break;
            case 7:
                var arrA = $(node).find('a');

                if(arrA.length > 0)
                {
                    rel = $.trim($(arrA[0]).html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                else
                {
                    rel = $.trim($(node).find('p').html());
                    rel = rel.replace(/,/g,'');
                    rel = (rel == 'N/A') ? '-1' : rel;
                }
                break;
        }
        return rel;
    };

    $('#table').tablesorter(
        {
            textExtraction: myBannerDetailTextExtraction,
            headers:
            {
                4: {sorter:'fancyNumber'},
                5: {sorter:'fancyNumber'},
                6: {sorter:'fancyNumber'},
                7: {sorter:'fancyNumber'}
            }
        });

}


function ajax_sync_data(url, fdate, tdate, numitem, showtooltip, havemoney) {
    var arrId = $('.cpid');
    var total = arrId.length;
    var page = 1;
    if(havemoney != true) havemoney = false;
    var start = (page - 1) * numitem;
    var end = ((start + numitem) <= total) ? (start + numitem) : total;
    var strid = '';
    for (var i = start; i < end; i++) {
        strid += (strid == '') ? $.trim($(arrId[i]).val()) : ',' + $.trim($(arrId[i]).val());
    }
    var _onSuccess = function (data) {
        data = $.trim(data);
        if (data == 'unlogin' || data == 'notright') {
            window.location.reload(true);
            return;
        }
        else if (data == 'empty') {
            if (end < total) {
                ajax_sync_data(url, fdate, tdate);
            }
            else {
                for (var i = start; i < end; i++) {
                    var bannerid1 = $.trim($(arrId[i]).val());
                    var na = 'N/A';
                    $('#imp_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#cl_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#uv_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#ctr_' + bannerid1).html(na).removeClass('text-center').addClass('text-right');
                    if(havemoney) $('#money_' + bannerid1).html(na).removeClass('text-center').addClass('text-right');

                }
                return;
            }
        }
        else {
            var obj = jQuery.parseJSON(data);
            for (var i = start; i < end; i++) {
                var bannerid1 = $.trim($(arrId[i]).val());
                var na = 'N/A';
                $('#imp_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#cl_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#uv_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#ctr_' + bannerid1).html(na).removeClass('text-center').addClass('text-right');
                if(havemoney) $('#money_' + bannerid1).html(na).removeClass('text-center').addClass('text-right');
            }
            jQuery.each(obj, function () {
                var pid = this.id;
                var status = $('#' + pid).val();
                if (showtooltip) {
                    $('#imp_' + pid).html('<a href="javascript:void(0);" onclick="ajax_tooltip2(' + pid + ',this,' + status + ')">' + this.imp + '</a>').removeClass('text-center').addClass('text-right');
                    $('#cl_' + pid).html('<a href="javascript:void(0);" onclick="ajax_tooltip2(' + pid + ',this,' + status + ')">' + this.cl + '</a>').removeClass('text-center').addClass('text-right');
                } else {
                    $('#imp_' + pid).html(this.uv).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#cl_' + pid).html(this.ctr).removeClass('text-center').addClass('text-right').addClass('pad5');
                }
                $('#uv_' + pid).html(this.uv).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#ctr_' + pid).html(this.ctr).removeClass('text-center').addClass('text-right');
                if(havemoney) $('#money_' + pid).html(this.money).removeClass('text-center').addClass('text-right');
            });

            loadsorter();
            if (end < total) {
                ajax_sync_data(url, fdate, tdate);
            }
            else {
                return;
            }

        }
    };
    var param = {'ajax': '1', 'id': strid, 'fdate': fdate, 'tdate': tdate};
    getAjax(url, param, '', 'POST', '', false, _onSuccess);
}

function ajax_sync_data_campaign(url, fdate, tdate,numitem,showtooltip)
{
    var arrId = $('.cpid');
    var total = arrId.length;
    var page = 1;
    var start = (page - 1) * numitem;
    var end = ((start + numitem) <= total) ? (start + numitem) : total;

    var strid = '';
    for(var i = start; i < end; i++)
    {
        strid += (strid == '') ? $.trim($(arrId[i]).val()) : ',' + $.trim($(arrId[i]).val());
    }
    var _onSuccess = function(data)
    {
        data = $.trim(data);
        if(data == 'unlogin' || data == 'notright')
        {
            window.location.reload(true);
            return;
        }
        else if(data == 'empty')
        {
            if(end < total)
            {
                ajax_sync_data_campaign(url, fdate, tdate);
            }
            else
            {
                for(var i = start; i < end; i++)
                {
                    var bannerid1 = $.trim($(arrId[i]).val());
                    var na='N/A';
                    $('#imp_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#cl_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#uv_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#ctr_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#money_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                }
                return;
            }
        }
        else
        {
            var obj = jQuery.parseJSON(data);
            for(var i = start; i < end; i++)
            {
                var bannerid1 = $.trim($(arrId[i]).val());
                var na='N/A';
                $('#imp_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#cl_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#uv_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#ctr_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#money_' + bannerid1).html(na).removeClass('text-center').addClass('text-right').addClass('pad5');
            }
            jQuery.each(obj, function()
            {
                var pid = this.id;
                var status = $('#'+pid).val();
                if(showtooltip)
                {
                    $('#imp_' + pid).html('<a href="javascript:void(0);" onclick="ajax_tooltip2('+pid+',this,0,'+status+')">'+this.imp+'</a>').removeClass('text-center').addClass('text-right');
                    $('#cl_' + pid).html('<a href="javascript:void(0);" onclick="ajax_tooltip2('+pid+',this,0,'+status+')">'+this.cl+'</a>').removeClass('text-center').addClass('text-right');
                }else{
                    $('#imp_' + pid).html(this.uv).removeClass('text-center').addClass('text-right').addClass('pad5');
                    $('#cl_' + pid).html(this.ctr).removeClass('text-center').addClass('text-right').addClass('pad5');
                }
                $('#uv_' + pid).html(this.uv).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#ctr_' + pid).html(this.ctr).removeClass('text-center').addClass('text-right').addClass('pad5');
                $('#money_' + pid).html(this.money).removeClass('text-center').addClass('text-right').addClass('pad5');
            });
            if(end < total)
            {
                ajax_sync_data_campaign(url, fdate, tdate);
            }
            else
            {
                return;
            }
        }
    };
    var param = {'ajax' : '1', 'id': strid, 'fdate':fdate, 'tdate':tdate};
    getAjax(url, param, '', 'POST', '', false, _onSuccess);
}

function check_max_length(maxLengh, id)
{
    var inputString = $('#'+id);
    var inputStringVal = inputString.val();
    if(parseInt(inputStringVal.length) > parseInt(maxLengh)){
        var shortenedString = inputStringVal.substr(0,(inputStringVal.length -1));
        inputString.val(shortenedString);
        check_max_length(maxLengh, id);
    }
}