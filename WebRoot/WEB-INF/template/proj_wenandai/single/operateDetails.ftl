<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 运营详情</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="运营数据";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<style>
    .article-content {
        font-size: 14px;
        color: #6c6c6c;
        line-height: 40px;
        text-indent: 28px;
    }
</style>

<div class="main">
    <!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->

    <div class="abouts-cont fr" >
        <div class="abouts-border" style="padding:0;/*min-height: 590px;*/">
            <#--<div class="operate_title"></div>-->
            <img  class="operate_title" src="${base}/theme/proj_wenandai/images/operate/banner/${date}.jpg" alt="">
            <div class="operate_details" style="padding:30px 40px;">
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">平台运营概况</span>
                </div>
                <ul class="operate_money">
                    <li><span>累计借贷金额（元）</span><p>${operate.sumLoanMoney}</p></li>
                    <li><span>累计借贷笔数（笔）</span><p>${operate.sumLoanCount}</p></li>
                    <li><span>借贷余额（元）</span><p>${operate.balanceLoanMoney}</p></li>
                    <li><span>借贷余额总笔数（笔）</span><p>${operate.balanceLoanCount}</p></li>
                </ul>
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">借贷数据</span>
                </div>
                <ul class="Loan_data">
                    <li><span>累计出借人数量（人）</span><p>${operate.sumPayPeople}</p></li>
                    <li><span>当前出借人数量（人）</span><p>${operate.payPeople}</p></li>
                    <li><span>累计借款人数量（人）</span><p>${operate.sumIncomePeople}</p></li>
                    <li><span>当前借款人数量（人）</span><p>${operate.incomePeople}</p></li>
                    <li><span>关联关系借款余额（元）</span><p>0</p></li>
                    <li><span>关联关系借款笔数（笔）</span><p>0</p></li>
                </ul>
                <div class="Percentage">
                    <div class="loading-circle" id="loadingCircle">
                        <p><span id="loadedNum">0</span>%</p>
                        <canvas class="mask" id="loadingProgress" width="180" height="180"></canvas>
                        <canvas class="bg" width="180" height="180"></canvas>
                    </div>
                    <div class="loading-circle loading-circleOne  " id="loadingCircle1">
                        <p class="One"><span id="loadedNum1">0</span>%</p>
                        <canvas class="mask" id="loadingProgress1" width="180" height="180"></canvas>
                        <canvas class="bg  bgOne" width="180" height="180"></canvas>
                    </div>
                    <ul>
                        <li>前十大借款人待还金额占比</li>
                        <li>最大单一借款人待还金额占比</li>
                    </ul>
                </div>
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">逾期数据</span>
                </div>
                <ul class="operate_money operate_money_1 ">
                    <li><span>逾期金额（元）</span><p>0</p></li>
                    <li><span>逾期笔数（笔）</span><p>0</p></li>
                    <li><span>逾期90天以上金额（元）</span><p>0</p></li>
                    <li><span>逾期90天以上笔数（笔）</span><p>0</p></li>
                </ul>
                <ul class="Accumulative">
                    <li>
                        <div class="money"></div>
                        <div ><span>累计代偿金额（元）</span><p>0</p></div>
                    </li>
                    <li>
                        <div class="pens"></div>
                        <div><span>累计代偿笔数（笔）</span><p>0</p></div>
                    </li>
                </ul>
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">收费标准</span>
                </div>

                <div class="standard">
                    <table>
                        <thead>
                        <tr><th>业务类型</th><th>资费标准</th></tr>
                        </thead>
                        <tbody>
                        <tr><td>用户注册</td><td>免费</td></tr>
                        <tr><td>身份认证</td><td>免费</td></tr>
                        <tr><td>开通存管账户</td><td>免费</td></tr>
                        <tr><td>投资</td><td>免费</td></tr>
                        <tr><td>充值</td><td>免费</td></tr>
                        <tr><td>提现</td><td>单笔提现收取一元手续费</td></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
<!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
<script type="text/javascript">
    $(function () {
        function wad_gytupian() {
            $("a[rel=group]").fancybox({
                'titlePosition' : 'over',
                openEffect: 'elastic',
                'cyclic'        : true,
                'centerOnScroll': true,
                'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) {
                    return '<span id="fancybox-title-over">' + (currentIndex + 1) +
                            ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>';
                },
                onStart: function () {
                    $('body').css('overflow','hidden');
                },
                onClosed: function () {
                    $('body').css('overflow','auto');
                },

            });
        }
        wad_gytupian();
    });
</script>
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

        var loaded = window.hasLoaded * 2 / 100 * Math.PI, cw = 180, hcw = 90;
        ctx.clearRect (0,0,cw,cw);
        ctx.beginPath();
        ctx.arc(hcw,hcw,hcw-6, 0, loaded, false);
        ctx.lineWidth = 12;
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

        var loaded1 = window.hasLoaded1* 2 / 100 * Math.PI, cw = 180, hcw = 90;
        ctx1.clearRect (0,0,cw,cw);
        ctx1.beginPath();
        ctx1.arc(hcw,hcw,hcw-6, 0, loaded1, false);
        ctx1.lineWidth = 12;
        ctx1.strokeStyle = '#8C79F7';
        ctx1.stroke();
    }
    ulp1(${operate.maxIncomeMoneyProporion}*100+1);
</script>
</body>
</html>