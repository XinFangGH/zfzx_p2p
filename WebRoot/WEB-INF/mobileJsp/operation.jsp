<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 运营报告</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/information.css"/>
    <style>
        html {
            font-size: 100px;
        }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        运营报告
    </div>
</div>
<div class="operationReport">
    <div class="titleNav" style="margin-top: 0.6rem">
        <span class="history"></span>
        <span class="title">平台运营概况</span>
    </div>
    <ul class="survey">
        <li><p>累计借贷金额（元）</p><span>${operate.sumLoanMoney}</span></li>
        <li><p>累计借贷笔数（笔）</p><span>${operate.sumLoanCount}</span></li>
        <li><p>借贷余额（元）</p><span>${operate.balanceLoanMoney}</span></li>
        <li><p>借贷余额总笔数（笔）</p><span>${operate.balanceLoanCount}</span></li>
    </ul>
    <div class="segmentingLine" style="margin:0.6rem auto 0.7rem; "></div>
    <div class="titleNav" style="margin-top: 0.6rem">
        <span class="loan"></span>
        <span class="title">借贷数据</span>
    </div>
    <ul class="survey  survey_loan">
        <li><p>累计出借人数量（人）</p><span>${operate.sumPayPeople}</span></li>
        <li><p>当前出借人数量（人）</p><span>${operate.payPeople}</span></li>
        <li><p>累计借款人数量（人）</p><span>${operate.sumIncomePeople}</span></li>
        <li><p>当前借款人数量（人）</p><span>${operate.incomePeople}</span></li>
        <li><p>关联关系借款余额（元）</p><span>0</span></li>
        <li><p>关联关系借款笔数（笔）</p><span>0</span></li>
    </ul>
    <div class="Percentage">
        <div class="loading-circle" id="loadingCircle">
            <p><span id="loadedNum">0</span>%</p>
            <canvas class="mask" id="loadingProgress" width="220" height="220"></canvas>
            <canvas class="bg" width="220" height="220"></canvas>
        </div>
        <p>前十大借款人待还金额占比</p>
        <div class="loading-circle loading-circleOne  " id="loadingCircle1">
            <p class="One"><span id="loadedNum1">0</span>%</p>
            <canvas class="mask" id="loadingProgress1" width="220" height="220"></canvas>
            <canvas class="bg  bgOne" width="220" height="220"></canvas>
        </div>
        <p>最大单一借款人待还金额占比</p>
    </div>
    <div class="segmentingLine" style="margin:0.6rem auto 0.7rem; "></div>
    <div class="titleNav" style="margin-top: 0.6rem">
        <span class="overdue"></span>
        <span class="title">逾期数据</span>
    </div>
    <ul class="survey">
        <li><p>逾期金额（元）</p><span>0</span></li>
        <li><p>逾期笔数（笔）</p><span>0</span></li>
        <li><p>逾期90天以上金额（元）</p><span>0</span></li>
        <li><p>逾期90天以上笔数（笔）</p><span>0</span></li>
    </ul>
    <ul class="Accumulative">
        <li>
            <div class="money"></div>
            <div><span>累计代偿金额（元）</span><p>0</p></div>
        </li>
        <li>
            <div class="pens"></div>
            <div><span>累计代偿笔数（笔）</span><p>0</p></div>
        </li>
    </ul>
    <div class="segmentingLine" style="margin:0.6rem auto 0.7rem; "></div>
    <div class="titleNav" style="margin-top: 0.6rem">
        <span class="charge"></span>
        <span class="title">收费标准</span>
    </div>
    <div class="charge_list">
        <ul>
            <li><span>业务类型</span><span>资费标准</span></li>
            <li><span>用户注册</span><span>免费</span></li>
            <li><span>身份认证</span><span>免费</span></li>
            <li><span>开通存管账户</span><span>免费</span></li>
            <li><span>投资</span><span>免费</span></li>
            <li><span>充值</span><span>免费</span></li>
            <li><span>提现</span><span>单笔提现收取一元手续费</span></li>
        </ul>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script>
    var slots={},c=document.getElementById('loadingProgress'),ctx=c.getContext('2d');
    window.hasLoaded = 0;
    window.loading = false;
    window.ulp = ulp;
    function ulp(percent){
        window.loading = true;
        var i = 0, draw = null;
        draw = setInterval(function(){
            if (window.hasLoaded > 100) {
                window.loading = false;
                clearInterval(draw);
                draw = null;
                return true;
            }

            if (i<percent) {
                d();
                i++;
                window.hasLoaded += 1;
            } else {
                clearInterval(draw);
                draw = null;
            }
        }, 100);
    }

    function d(){
        var lp = document.getElementById('loadedNum');
        lp.innerHTML = window.hasLoaded;

        var loaded = window.hasLoaded * 2 / 100 * Math.PI, cw = 220, hcw = 110;
        ctx.clearRect (0,0,cw,cw);
        ctx.beginPath();
        ctx.arc(hcw,hcw,hcw-8, 0, loaded, false);
        ctx.lineWidth = 16;
        ctx.strokeStyle = '#F56923';
        ctx.stroke();
    }
    ulp(${operate.tenIncomeMoneyProporion}*100+1);
</script>
<script>
    var slots1={},c1=document.getElementById('loadingProgress1'),ctx1=c1.getContext('2d');
    window.hasLoaded1 = 0;
    window.loading1 = false;
    window.ulp1 = ulp1;
    function ulp1(percent){
        window.loading1 = true;
        var i1 = 0, draw1 = null;
        draw1 = setInterval(function(){
            if (window.hasLoaded1 > 100) {
                window.loading1 = false;
                clearInterval(draw1);
                draw1 = null;
                return true;
            }

            if (i1<percent) {
                d1();
                i1++;
                window.hasLoaded1 += 1;
            } else {
                clearInterval(draw1);
                draw1 = null;
            }
        }, 100);
    }

    function d1(){
        var lp1 = document.getElementById('loadedNum1');
        lp1.innerHTML = window.hasLoaded1;

        var loaded1 = window.hasLoaded1* 2 / 100 * Math.PI, cw = 220, hcw = 110;
        ctx1.clearRect (0,0,cw,cw);
        ctx1.beginPath();
        ctx1.arc(hcw,hcw,hcw-8, 0, loaded1, false);
        ctx1.lineWidth = 16;
        ctx1.strokeStyle = '#8C79F7';
        ctx1.stroke();
    }
    ulp1(${operate.maxIncomeMoneyProporion}*100+1);
</script>
</body>
</html>