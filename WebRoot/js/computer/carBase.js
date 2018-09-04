var webSiteBaseUrl = "http://car.bitauto.com/";
function InitControl() {
    InitVehicleAndVesselTaxControl("divLoanVehicleAndVesselTaxMessage");

    //初始化下拉组件(品牌)文字颜色
    var spanTagMaster = $("#master4").children("span").children("span");
    spanTagMaster[0].style.color = "black";

    //初始化下拉组件(车系)文字颜色
    var spanTagSerial = $("#serial4").children("span").children("span");
    if (spanTagSerial.html() != "请选择系列") {
        spanTagSerial[0].style.color = "black";
    }

    //初始化下拉组件(车款)文字颜色
    var spanTagCarType = $("#cartype4").children("span").children("span");
    if (spanTagCarType.html() != "请选择车款") {
        spanTagCarType[0].style.color = "black";
    }
}

function initAutoLoanData() {
var hidCarPrice = $("#hidCarPrice").val();
$('#txtMoney').val(hidCarPrice);
var carId = document.getElementById("hidCarID").value;
var flag = parseInt(hidCarPrice) > 0; //可能从其他页面传递价格
if (!flag) {
    resetPrice(carId, webSiteBaseUrl);
} else {
    setCalcToolUrlByPrice(hidCarPrice);
    var downpaymentRatioInt = "0";
    switch (downpaymentRatioInt) {
        case "30":
            $("#r1").prop("checked", true);
            break;
        case "40":
            $("#r2").prop("checked", true);
            break;
        case "50":
            $("#r3").prop("checked", true);
            break;
        case "60":
            $("#r4").prop("checked", true);
            break;
    }
    var repaymentYearsInt = "0";
    switch (repaymentYearsInt) {
        case "1":
            $("#y1").prop("checked", true);
            break;
        case "2":
            $("#y2").prop("checked", true);
            break;
        case "3":
            $("#y3").prop("checked", true);
            break;
        case "4":
            $("#y4").prop("checked", true);
            break;
        case "5":
            $("#y5").prop("checked", true);
                break;
        }
    }
    calcYearRate();
}
//贷款购车总花费 首付款+贷款所花总钱数
//贷款所花总钱数=月付款×还款年限×12
//比全款购车多花费=贷款所花总钱数+首付金额-裸车价格。
function calcTotalNew() {
    var moneyMonthPayments = calcMonthPayments();
    var periods = parseInt(jQuery('#yueGongQi').html());
    var moneySaved = formatCurrency(parseFloat(moneyMonthPayments * periods) + parseFloat($('#txtDownPayments').val()) - parseFloat($('#txtMoney').val()));
    if (isNaN($('#loanRate').val()) || $('#loanRate').val().length == 0 || $('#loanRate').val() == 0) {
        moneySaved = 0;
    }
    SetSpanValueByBrowerType('lblDuoHuaFei', moneySaved);
    var moneyTotal = calcFirstDownPayments() + parseInt(moneyMonthPayments * periods);
    SetSpanValueByBrowerType('lblTopTotal', formatCurrency(moneyTotal)); //购车总花费
}

function checkMoneyValidationNew() {
    if ($('#txtMoney').val() == "0") {
        $('#liDispaly1')[0].style.display = '';
        $('#liDispaly2')[0].style.display = 'none';
    }
    else {
        $('#liDispaly1')[0].style.display = 'none';
        $('#liDispaly2')[0].style.display = '';
    }
    return true;
}

//计算年利率
function calcYearRate() {
    var rdoPaymentYears = document.getElementsByName("rdoPaymentYears");
    for (i = 0; i < rdoPaymentYears.length; i++) {
        if (rdoPaymentYears[i].checked) {
            switch (rdoPaymentYears[i].value) {
                case "1":
                    jQuery("#loanRate").val(6.31);
                    break;
                case "2":
                    jQuery("#loanRate").val(6.40);
                    break;
                case "3":
                    jQuery("#loanRate").val(6.40);
                    break;
                case "4":
                    jQuery("#loanRate").val(6.65);
                    break;
                case "5":
                    jQuery("#loanRate").val(6.65);
                    break;
            }
        }
    }
}

function calcAutoLoanAll() {
    if ($('#txtMoney').val().length == 0 || $('#txtMoney').val() == 0) {
        $('#rdoDownPaymentsOfSelf').attr("disabled", true);
        $('#txtDownPayments').attr("disabled", true);
    } else {
        $('#rdoDownPaymentsOfSelf').attr("disabled", false);
        if ($('#rdoDownPaymentsOfSelf').prop("checked"))
            $('#txtDownPayments').attr("disabled", false);
        else
            $('#txtDownPayments').attr("disabled", true);
    }

    if (!checkMoneyValidationNew()) {
        return false;
    }
    if ($('#txtMoney').val() == 0) {
        $('#txtDownPayments').val(0);
        jQuery('#lblYueGong').html('0');
        jQuery('#lblShouFu').html('0'); //首付
        jQuery('#lblAcquisitionTax').html('0'); //购置税
        $('#txtVehicleTax').val(0); //车船使用税
        jQuery('#lblCompulsory').html('0'); //交强险
        jQuery('#lblCommonTotal').html('0'); //必要花费
        jQuery('#lblTPL').html('0'); //第三方责任险
        jQuery('#lblCarDamage').html('0'); //车辆损失险
        jQuery('#lblCarTheft').html('0'); //全车盗抢险
        jQuery('#lblBreakageOfGlass').html('0'); //玻璃破碎险
        jQuery('#lblSelfignite').html('0'); //自燃险
        jQuery('#lblAbatement').html('0'); //不计免赔特约险
        jQuery('#lblCarDamageDW').html('0'); //车身划痕险
        jQuery('#lblLimitOfPassenger').html('0'); //乘客坐位责任险
        jQuery('#lblLimitOfDriver').html('0'); //司机坐位责任险
        jQuery('#engineDamage').html('0'); //发动机特别损失险
        $('#txtChePai').val(0); //上牌费用
        jQuery('#essentialCost').html('0'); //必要花费
        //全款购车
        calcTotalNew();
        return;
    }

    //首付金额
    calcDownPayments();
    //月付款
    calcMonthPayments();
    //------------------------
    //购置税
    calcAcquisitionTax();
    //交强险
    calcCompulsory();
    //------------------------
    //第三方责任险
    calcTPL();
    //车辆损失险
    calcCarDamage();
    //全车盗抢险
    calcCarTheft();
    //玻璃单独破碎险
    calcBreakageOfGlass();
    //自燃损失险
    calcSelfignite();
    //不计免赔特约险
    calcAbatement();
    //乘客责任险
    calcLimitofPassenger();
    //司机责任险
    calcLimitofDriver();
    //车身划痕险
    calcCarDamageDW();
    //发动机特别损失险
    calcCarEngineDamage();
    //------------------------
    //必要花费
    calcEssentialCost();
    //常规保险合计
    calcBusinessTotal();
    //首付款总额=首付款+必要花费+商业保险
    calcFirstDownPayments();

    calcTotalNew();
}

//----------------------------
//首付金额
function calcDownPayments() {
    var rdoDownPayments = document.getElementsByName("rdoDownPayments");
    if (!rdoDownPayments[4].checked) {
        for (i = 0; i < rdoDownPayments.length; i++) {
            if (rdoDownPayments[i].checked) {
                $('#txtDownPayments').val(Math.round($('#txtMoney').val() * rdoDownPayments[i].value));
                break;
            }
        }
    }
}

function calcMonthPayments() {
    var loanMonths = 12;
    var rdoPaymentYears = document.getElementsByName("rdoPaymentYears");
    for (i = 0; i < rdoPaymentYears.length; i++) {
        if (rdoPaymentYears[i].checked) {
            loanMonths = rdoPaymentYears[i].value * 12;
            break;
        }
    }
    if (isNaN($('#loanRate').val()) || $('#loanRate').val().length == 0 || $('#loanRate').val() == 0) {
        var r = Math.round(calcLoanValue() / loanMonths);
        SetSpanValueByBrowerType('lblYueGong', formatCurrency(r));
        return r;
    } else {
        var yearRate = parseFloat($("#loanRate").val()) / 100;
        var monthPercent = yearRate / 12;
        jQuery('#yueGongQi').html(loanMonths);
        jQuery('#daikuanQi').html(loanMonths);
        var fenzi = calcLoanValue() * monthPercent * Math.pow((1 + monthPercent), loanMonths);
        var fenmu = (Math.pow((1 + monthPercent), loanMonths) - 1);
        var result = 0;
        if (fenmu != 0) {
            result = Math.round(fenzi / fenmu);
        }
        SetSpanValueByBrowerType('lblYueGong', formatCurrency(result));
        return result;
    }

}

function resetTxtDownPayments() {
    window.setTimeout(function () { $('#txtDownPayments').focus(); }, 0);
    $('#txtDownPayments').attr("disabled", false);
}

//首付金额
function checkTxtDownPayments() {
    if (parseInt($('#txtDownPayments').val()) > parseInt($('#txtMoney').val())) {
        $('#txtDownPayments').val($('#txtMoney').val());
        window.setTimeout(function () { $('#txtDownPayments').focus(); }, 0);
    }
    //月付款
    calcMonthPayments();
    //首付额
    calcFirstDownPayments();
    calcTotalNew();
}

//贷款额=车辆购置价格-首付金额  注意首付金额同首付款的区别
function calcLoanValue() {
    var downPayments = $('#txtDownPayments').val() == "" ? "0" : $('#txtDownPayments').val();
    var loanValue = parseInt($('#txtMoney').val()) - parseInt(downPayments);
    return loanValue;
}

//首付款=首付金额+必要花费+商业保险
function calcFirstDownPayments() {
    var downPayments = $('#txtDownPayments').val() == "" ? "0" : $('#txtDownPayments').val();
    var firstDownPayments = parseInt(downPayments) +
            parseInt(calcEssentialCost()) +
            parseInt(calcBusinessTotal());
    SetSpanValueByBrowerType('lblShouFu', formatCurrency(firstDownPayments));
    return firstDownPayments;
}

//----------------------------

//在未选择车型的情况下初始化上牌费用及车船使用税
//        function InitPaiAndChuan(v) {
//            $("#txtMoney").val(v.replace(/(\D)/g, ''));
//            $("#txtChePai").val(500);
//            var cheChuan = 420 * (12 - new Date().getMonth()) / 12;
//            $("#txtVehicleTax").val(Math.ceil(cheChuan));
//            setCalcToolUrl(-1);
//            calcAutoLoanAll();
//        }

function InitPaiAndChuan(v) {
    $("#txtMoney").val(v.replace(/(\D)/g, ''));
    if (parseInt($("#hidCarID").val()) <= 0) {
        if ($("#txtMoney").val() != "" || parseInt($("#txtMoney").val()) > 0) {
            if ($("#txtChePai").val() == "" || $("#txtChePai").val() == "0") {
                $("#txtChePai").val(500);
            }

            if ($("#txtVehicleTax").val() == "" || $("#txtVehicleTax").val() == "0") {
                var cheChuan = 420 * (12 - new Date().getMonth()) / 12;
                $("#txtVehicleTax").val(Math.ceil(cheChuan));
            }
        }
    } else {
        //                if ($("#txtMoney").val() == "" && $("#txtMoney").val() == "0") {
        //                    $("#txtChePai").val(500);
        //                }
        //                if ($("#txtVehicleTax").val() == "" && $("#txtVehicleTax").val() == "0") {
        //                    var cheChuan = 420 * (12 - new Date().getMonth()) / 12;
        //                    $("#txtVehicleTax").val(Math.ceil(cheChuan));
        //                }    
    }
    setCalcToolUrl(-1);
    calcAutoLoanAll();
}

//--------------------------------------------------
function ResetToDefault() {
    InitControl();
    initAutoLoanData();
    InitCheChuanValue();
    calcAutoLoanAll();
}

$(function () {
    $(".close").click(function (e) {
        e = e || window.event;
        e.preventDefault();
        e.stopPropagation();
        $(this).closest(".yiwenicon.z30").removeAttr("style");
        $(this).closest(".tc.tc-jsq").hide();
    });
    $(document).click(function (e) {
        e = e || window.event;
        var target = e.srcElement || e.target;
        if ($(target).closest(".tc.tc-jsq").length <= 0)
            $(".yiwenicon.z30").removeAttr("style");
            $(".tc.tc-jsq").hide();
    });
});
