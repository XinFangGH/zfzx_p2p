$(function () {
    //limitInt($(".number"));
    try {

        //获取计算参数
        var money = window.opener.oMoney; //申购金额
        var interestRate = window.opener.oInterestRate; //年利率
        var publisherRate = window.opener.oPublisherRate; //借款人奖励
        var tuandaiRate = window.opener.oTuandaiRate; //紫薇金融奖励
        var deadline = window.opener.oDeadline; //借款期限
        var repaymentType = window.opener.oRepaymentType; //还款方式
        //判断是否加载时计算结果
        if (money != null && interestRate != null && deadline != null && repaymentType != null) {
            $("#amount").val(money);
            $("#apr").val(interestRate);
            $("#mons").val(deadline);
            if (repaymentType == 1) {
                $("#select").val(2)
            }
            if (repaymentType == 2) {
                $("#select").val(1)
            }
            if (repaymentType == 3) {
                $("#select").val(0)
            }
            if (repaymentType == 4) {
                $("#select").val(4)
            }
            if (publisherRate != null) {
                $("#txtBid").val(publisherRate);
            }
            if (tuandaiRate != null) {
                $("#txtTender").val(tuandaiRate);
            }
            //执行计算
            calculate();
        }
    } catch (e) {

    }
    $(".tool_content").keypress(
        function (e) {
            if (e.keyCode == "13") {
                calculate();
            }
        });

    //计算
    $("#btnCalculate").click(function () {
        calculate();
    });

    //重置
    $("#btnReset").click(function () {
        $('#newdiv').hide();
        $('#gain_initial').show();

        $("#newdiv").html("");
        span1.innerHTML = "￥0.00";
        span2.innerHTML = "￥0.00";
        span3.innerHTML = "￥0.00";
        span4.innerHTML = "￥0.00";
        span5.innerHTML = "￥0.00";
        span6.innerHTML = "￥0.00";
        $("[name=lblRepay1]").click();
        $("#amount").val("");
        $("#apr").val("");
        $("#mons").val("");
        $("#txtBid").val("");
        $("#txtTender").val("");

        $("#tipAmount").hide();
        $("#tipApr").hide();
        $("#tipMons").hide();
        $("#tipBid").hide();
        $("#tipTender").hide();
    });

});





//计算
function calculate() {

    var amount = $("#amount"); //获取借款金额	
    var apr = $("#apr"); //获取年利率
    var mons = $("#mons"); //获取借款月数
    var bid = parseFloat($("#txtBid").val()); //发标奖励
    var tender = parseFloat($("#txtTender").val()); //投标奖励

    if (!valid(amount.val(), mons.val(), apr.val(), bid, tender))
        return;

    $('#gain_initial').hide();
    $('#newdiv').show();

    var text = $('input[name="Repayment"]:checked').val();  //$("#select").val(); //获取下拉框的值

    var principal = parseFloat(amount.val()); //返回一个浮点数
    var interest = parseFloat(apr.val()) / 100 / 12; //每月利率
    var payments = parseInt(mons.val()); //投资期限


    var bidmoney = 0, tendermoney = 0;
    if (bid && bid > 0)
        bidmoney = principal * bid / 100;
    if (tender && tender > 0)
        tendermoney = principal * tender / 100;

    var monthly = (principal + principal * payments * interest) / payments; //每月还款本息

    var newdiv = $("#newdiv");
    //自定义创建一个表格
    var str = "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tool_table2\">";
    //表头
    str += "<tr><th width=\"10%\">期数</th><th width=\"25%\">每期回购本息</th><th width=\"25%\">每期回购本金</th><th width=\"18%\">利息</th><th width=\"22%\">未回购余额</th></tr>";
    if (text == "每月付息") {
        var mon = 0;
        for (var j = 1; j < parseInt(payments) + 1; j++) {
            if (j == parseInt(payments)) {
                mon = principal;
            }
            if (j % 2 > 0) {
                str += "<tr class=\"tool_bai\">";
                str += "<td>" + (j) + "</ td>"//期数
                str += "<td>" + "￥" + Round2(principal * interest + mon) + "</ td>"; //每期还款本息
                str += "<td>" + "￥" + Round2(mon) + "</ td>"; //每期还款本金
                str += "<td>" + "￥" + Round2(principal * interest) + "</ td>"; //利息
                str += "<td>" + "￥" + Round2(principal + principal * interest * payments - principal * interest * parseInt(j) - mon) + "</ td>"; //余额
                str += "</tr>";
            }
            else {
                str += "<tr >";
                str += "<td>" + (j) + "</ td>"//期数
                str += "<td>" + "￥" + Round2(principal * interest + mon) + "</ td>"; //每期还款本息
                str += "<td>" + "￥" + Round2(mon) + "</ td>"; //每期还款本金
                str += "<td>" + "￥" + Round2(principal * interest) + "</ td>"; //利息
                str += "<td>" + "￥" + Round2(principal + principal * interest * payments - principal * interest * parseInt(j) - mon) + "</ td>"; //余额
                str += "</tr>";
            }

        }
        str += "</table>";
        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + Round2((principal * interest * payments) + principal + bidmoney + tendermoney); //还款本息总额        
        span2.innerHTML = "￥" + Round2(principal * interest * payments); //利息
        span3.innerHTML = "￥" + Round2(principal * interest); //月利率
        span4.innerHTML = "￥" + Round2(bidmoney + tendermoney);
        span5.innerHTML = "￥" + Round2(bidmoney);
        span6.innerHTML = "￥" + Round2(tendermoney);
    }
    if (text == "每月还本息") {
        for (var i = 1; i < parseInt(payments) + 1; i++) {
            str += "<tr  class=\"tool_bai\">";
            str += "<td>" + (i) + "</ td>"//期数	
            str += "<td>" + "￥" + Round2(monthly) + "</ td>"; //	每期还款本息
            str += "<td>" + "￥" + Round2(principal / payments) + "</ td>"; //每期还款本金
            str += "<td>" + "￥" + Round2(principal * interest) + "</ td>"; //利息
            str += "<td>" + "￥" + Round2(monthly * payments - monthly * parseInt(i)) + "</ td>"; //余额
            str += "</tr>";
        }
        str += "</table>";
        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + Round2((monthly * payments) + principal + bidmoney + tendermoney); //还款本息总额        
        span2.innerHTML = "￥" + Round2(monthly * payments); //利息
        span3.innerHTML = "￥" + Round2(monthly); //月利率
        span4.innerHTML = "￥" + Round2(bidmoney + tendermoney);
        span5.innerHTML = "￥" + Round2(bidmoney);
        span6.innerHTML = "￥" + Round2(tendermoney);
    }
    if (text == "一次性") {
        str += "<tr  class=\"tool_bai\">";
        str += "<td>" + 1 + "</ td>"//期数	
        str += "<td>" + "￥" + Round2((principal * interest * payments) + principal) + "</ td>"; //	每期还款本息
        str += "<td>" + "	￥" + Round2(principal) + "</ td>"; //每期还款本金
        str += "<td>" + "￥" + Round2(principal * interest * payments) + "</ td>"; //利息
        str += "<td>" + "￥0.00</ td>"; //余额
        str += "</tr></table>";
        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + Round2((principal * interest * payments) + principal + bidmoney + tendermoney); //还款本息总额        
        span2.innerHTML = "￥" + Round2(principal * interest * payments); //利息
        span3.innerHTML = "￥" + Round2((principal * interest * payments) + principal + bidmoney + tendermoney);   //月利率
        span4.innerHTML = "￥" + Round2(bidmoney + tendermoney);
        span5.innerHTML = "￥" + Round2(bidmoney);
        span6.innerHTML = "￥" + Round2(tendermoney);
    }
    if (text == "先息后本") {
        str += "<tr class=\"tool_bai\"><td>申购时<br/>付息</ td>"//期数
        str += "<td>" + "￥" + Round2(principal * interest * payments) + "</ td>"; //	先还利息
        str += "<td>" + "	￥" + 0.00 + "</ td>"; //每期还款本金
        str += "<td>" + "￥" + Round2(principal * interest * payments) + "</ td>"; //利息
        str += "<td>" + "￥" + Round2(principal) + "</ td>"; //余额
        str += "</tr>";

        str += "<tr ><td>1</ td>"//期数
        str += "<td>" + "￥" + Round2(principal) + "</ td>"; //	到期还款本
        str += "<td>" + "	￥" + Round2(principal) + "</ td>"; //到期还款本
        str += "<td>" + "￥" + 0.00 + "</ td>"; //利息
        str += "<td>" + "￥" + 0.00 + "</ td>"; //余额
        str += "</tr></table>";

        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + Round2((principal * interest * payments) + principal + bidmoney + tendermoney); //还款本息总额        
        span2.innerHTML = "￥" + Round2(principal * interest * payments); //利息
        span3.innerHTML = "￥" + Round2((principal * interest * payments) + principal + bidmoney + tendermoney);   //月利率
        span4.innerHTML = "￥" + Round2(bidmoney + tendermoney);
        span5.innerHTML = "￥" + Round2(bidmoney);
        span6.innerHTML = "￥" + Round2(tendermoney);
    }
    if (text == "等额本息") {

        var monthRate = interest;
        var tempAmount = 0.00, tempInterestAmount = 0.00, tempAmountAndInterest = 0.00, totalInterest = 0.00, totalAmount = 0.00; interestAmount = 0.00;
        var index = 1;
        interestAmount = GetInterest(principal, payments, apr.val());
        while (index <= payments) {
            tempAmount = Number(principal * monthRate * Math.pow(1 + monthRate, index - 1) / (Math.pow(1 + monthRate, payments) - 1)).toFixed(4);
            tempAmountAndInterest = Number(principal * monthRate * Math.pow(1 + monthRate, payments) / (Math.pow(1 + monthRate, payments) - 1)).toFixed(4);
            tempInterestAmount = Number(tempAmountAndInterest - tempAmount).toFixed(4);
            if (index == payments) {
                tempAmount = Number(principal) - Number(totalAmount);
                tempInterestAmount = Number(tempAmountAndInterest - tempAmount);
            }
            totalInterest += Number(tempInterestAmount);
            totalAmount += Number(tempAmount);

            str += "<tr  class=\"tool_bai\">";
            str += "<td>" + (index) + "</ td>"//期数	
            str += "<td>" + "￥" + (Number(tempAmountAndInterest)).toFixed(2) + "</ td>"; //	每期还款本息
            str += "<td>" + "￥" + (Number(tempAmount)).toFixed(2) + "</ td>"; //每期还款本金
            str += "<td>" + "￥" + (Number(tempInterestAmount)).toFixed(2) + "</ td>"; //利息
            str += "<td>" + "￥" + Round2(interestAmount + principal - Number(tempAmountAndInterest * index).toFixed(2)) + "</ td>"; //余额
            str += "</tr>";

            index++;
        }
        interestAmount = ((Number(totalInterest) * 100) / 100).toFixed(2); //总利息
        str += "</table>";
        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + (Number(interestAmount) + principal + bidmoney + tendermoney); //还款本息总额
        span2.innerHTML = "￥" + interestAmount;  //总利息   
        span3.innerHTML = "￥" + (Number(tempAmountAndInterest)).toFixed(2); //每月收款

    }
    if (text == "等本等息") {
        for (var j = 1; j < parseInt(payments) + 1; j++) {
            if (j % 2 > 0) {
                str += "<tr class=\"tool_bai\">";
                str += "<td>" + (j) + "</ td>"//期数
                str += "<td>" + "￥" + Round2(principal / payments + principal * interest) + "</ td>"; //每期还款本息
                str += "<td>" + "￥" + Round2(principal / payments) + "</ td>"; //每期还款本金
                str += "<td>" + "￥" + Round2(principal * interest) + "</ td>"; //利息
                str += "<td>" + "￥" + Round2(principal + principal * interest * payments - (principal / payments + principal * interest) * parseInt(j)) + "</ td>"; //余额
                str += "</tr>";
            }
            else {
                str += "<tr >";
                str += "<td>" + (j) + "</ td>"//期数
                str += "<td>" + "￥" + Round2(principal / payments + principal * interest) + "</ td>"; //每期还款本息
                str += "<td>" + "￥" + Round2(principal / payments) + "</ td>"; //每期还款本金
                str += "<td>" + "￥" + Round2(principal * interest) + "</ td>"; //利息
                str += "<td>" + "￥" + Round2(principal + principal * interest * payments - (principal / payments + principal * interest) * parseInt(j)) + "</ td>"; //余额
                str += "</tr>";
            }

        }
        str += "</table>";
        newdiv.html(str); //将表格的内容填充到div中
        span1.innerHTML = "￥" + Round2(principal + principal * interest * payments); //还款本息总额        
        span2.innerHTML = "￥" + Round2(principal * interest * payments); //利息
        span3.innerHTML = "￥" + Round2(principal * interest); //月利率
        
    }
}

//验证
function valid(amount, mons, apr, bid, tender) {
    $("#tipAmount").hide();
    $("#tipMons").hide();
    $("#tipApr").hide();
    $("#tipBid").hide();
    $("#tipTender").hide();
    var isok = true;
    if (!/^[1-9]\d*$/.test(amount)) {
        $("#tipAmount").show();
        isok = false;
    }
    if (!/^(?!0\d|[0.]+$|0$)\d+(\.\d+)?$/.test(apr)) {
        $("#tipApr").show();
        isok = false;
    }
    if (!/^[1-9]\d*$/.test(mons)) {
        $("#tipMons").show();
        isok = false;
    }
    if (parseInt(mons) > 36) {
        $("#tipMons").show();
        isok = false;
    }
    if (!isNaN(bid)) {
        if (!checkFloat(bid)) {
            $("#tipBid").show();
            isok = false;
        }
    }
    if (!isNaN(tender)) {
        if (!checkFloat(tender)) {
            $("#tipTender").show();
            isok = false;
        }
    }
    return isok;
}
function GetInterest(amount, deadline, interestRate) {
    var interestAmount = 0.00;
    if (amount == "" || amount == "0" || deadline == "" || deadline == "0" || interestRate == "" || interestRate == "0")
        return interestAmount;

    var monthRate = interestRate * 0.010000000000 / 12.00;
    var tempAmount = 0.00, tempInterestAmount = 0.00, tempAmountAndInterest = 0.00, totalInterest = 0.00, totalAmount = 0.00;
    var index = 1;
    while (index <= deadline) {
        tempAmount = Number(amount * monthRate * Math.pow(1 + monthRate, index - 1) / (Math.pow(1 + monthRate, deadline) - 1)).toFixed(4);
        tempAmountAndInterest = Number(amount * monthRate * Math.pow(1 + monthRate, deadline) / (Math.pow(1 + monthRate, deadline) - 1)).toFixed(4);
        tempInterestAmount = Number(tempAmountAndInterest - tempAmount).toFixed(4);
        if (index == deadline) {
            tempAmount = (Number(amount) - Number(totalAmount));
            tempInterestAmount = Number(tempAmountAndInterest - tempAmount);
        }
        totalInterest += Number(tempInterestAmount);
        totalAmount += Number(tempAmount);
        index++;
    }
    interestAmount = Number(((Number(totalInterest) * 100) / 100).toFixed(2));


    return interestAmount;
}