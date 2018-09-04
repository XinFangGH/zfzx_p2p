<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>风险评估</title>
    <!-- Link Swiper's CSS -->
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/help_center.css"/>
    <link rel="stylesheet" href="${base}/mobileNew/js/test/swiper.min.css">

    <!-- Demo styles -->
    <style>
        html, body {
            position: relative;
            height: 100%;
        }
        body {
            /*  background: #eee;*/
            font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
            font-size: 14px;
            color:#000;
            margin: 0;
            padding: 0;
        }

        html {
            font-size: 100px;
        }

        .container {
            width: 100%;
            background: #FFFFFF;
            margin-top: 0.88rem;
            overflow: hidden;
        }

        .swiper-container {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<%--<div class="nav_box" >
    <div class="nav" >
        <a href="javascript:history.go(-1);" class="back"></a>
        风险评估
    </div>
</div>--%>
<div class="container" id="immediate_test">
    <div class="immediate_test_topBar">
        <span id="currentPager">1</span>/12
    </div>

    <div class="swiper-container">
        <!-- Add Pagination -->
        <div class="swiper-pagination" id="my_pro" style="margin:0 auto;width:6.7rem;height: 0.12rem;background: rgba(155,155,155,0.15);border-radius: 4px;"></div>
        <div class="swiper-wrapper">
            <div class="swiper-slide swiper-no-swiping" id="page1">
                <div class="pager pager1">
                    <h4 class="remind">您的投资经验可以被概括为：</h4>
                    <p class="active"   value="1">有限：除银行活期账户和定期存款外，我基本没有其他投资经验</p>
                    <p  value="2">一般：除银行活期账户和定期存款外，我购买过基金、保险等金融产品</p>
                    <p  value="3">较多：我在股票、基金等金融产品方面有一定的投资经验，但还需要进一步的指导</p>
                    <p  value="4">丰富：我在股票、基金等金融产品方面有丰富的投资经验，倾向于自己做出投资决策</p>
                    <p  value="5">非常丰富：我是一位非常有经验的投资者，参与过权证、期货或创业板等金融产品的交易或有5年以上股票、基金投资经验</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager2">
                    <h4 class="remind">您的金融知识可以被概括为：</h4>
                    <p  class="active"  value="2">现在或此前曾从事金融、经济或财会等与金融产品投资相关的工作超过两年；已取得金融、经济或财会等与金融产品投资相关专业学士以上学位；取得证券从业资格、期货从业资格、注册会计师证书（CPA）或注册金融分析师证（CFA）中的一项及以上</p>
                    <p  value="3">掌握一定的金融知识和投资方法</p>
                    <p  value="4">掌握丰富的金融知识和多种投资方法</p>
                    <p  value="1">不符合以上任何一项描述</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager3">
                    <h4 class="remind">您的财务状况可以被概括为:</h4>
                    <p  class="active"   value="5">有较高收入，家庭收入大于支出，没有大额负债</p>
                    <p   value="4">有稳定收入，家庭收支基本平衡，有消费信贷等短期信用债务</p>
                    <p   value="3">有稳定收入，家庭收支平衡，有住房抵押贷款等长期定额债务</p>
                    <p   value="2">有稳定收入，家庭支出大于收入，有造成生活压力的经济负担</p>
                    <p   value="1">无稳定收入，有亲朋之间借款</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager4">
                    <h4 class="remind">您可用于理财投资的资产数额（包括银行存款等金融资产和非自住用途不动产）为：</h4>
                    <p class="active"   value="1">不超过10万元人民币</p>
                    <p   value="2">10万-50万元（不含）人民币</p>
                    <p   value="3">50万-300万元（不含）人民币</p>
                    <p   value="4">300万元以上人民币</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager5">
                    <h4 class="remind">最近您家庭预计进行金融投资的资金占家庭现有总资产(不含自住、自用房产及汽车等固定资产)的比例是：</h4>
                    <p class="active"   value="1">70%以上</p>
                    <p   value="2">50%-70%</p>
                    <p   value="3">30%-50%</p>
                    <p   value="4">10%－30%</p>
                    <p   value="5">10%以下</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager6">
                    <h4 class="remind">您用于金融投资的大部分资金不会用作其它用途的时间段为：</h4>
                    <p class="active"   value="1">短期 — 0到1年</p>
                    <p   value="2">中期 — 1到5年</p>
                    <p   value="3">长期 — 5年以上</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager7">
                    <h4 class="remind">您打算重点投资于哪些种类的投资品种 (可多选)</h4>
                    <p class="active"   value="1">债券、货币市场基金、债券基金、银行理财产品等固定收益类投资品种</p>
                    <p   value="1">股票、混合型基金、偏股型基金、股票型基金等权益类投资品种</p>
                    <p   value="1">融资融券、约定购回、股票质押回购</p>
                    <p   value="1">货、期权、信托、基金专项计划</p>
                    <p   value="1">复杂或高风险金融产品、其他产品</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager8">
                    <h4 class="remind">有一位投资者一个月内做了15笔交易（同一品种买卖各一次算一笔），您认为这样的交易频率：</h4>
                    <p class="active"   value="4">太高了</p>
                    <p   value="3">偏高</p>
                    <p   value="2">正常</p>
                    <p   value="1">偏低</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager9">
                    <h4 class="remind">当您进行投资时，您的首要目标是:</h4>
                    <p class="active"   value="1">资产保值，我不愿意承担任何投资风险</p>
                    <p   value="2">尽可能保证本金安全，不在乎收益率比较低</p>
                    <p   value="3">产生较多的收益，可以承担一定的投资风险</p>
                    <p   value="4">实现资产大幅增长，愿意承担很大的投资风险</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager10">
                    <h4 class="remind">您认为自己能承受的最大投资损失是多少:</h4>
                    <p class="active"   value="1">10%以内</p>
                    <p   value="2">10%-20%</p>
                    <p   value="3">20%-30%</p>
                    <p   value="4">30%-50%</p>
                    <p   value="5">超过50%</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager11">
                    <h4 class="remind">您的最高学历是：</h4>
                    <p class="active"   value="1">高中或以下</p>
                    <p   value="2">大学专科</p>
                    <p   value="3">大学本科</p>
                    <p   value="4">硕士及以上</p>
                </div>
            </div>
            <div class="swiper-slide swiper-no-swiping">
                <div class="pager  pager12">
                    <h4 class="remind">您的年龄是:</h4>
                    <p class="active"   value="1">18-30岁</p>
                    <p   value="2">31-40岁</p>
                    <p   value="3">41-50岁</p>
                    <p   value="4">51-60岁</p>
                    <p   value="5">超过60岁</p>
                </div>
            </div>
        </div>
    </div>

    <div class="immediate_test_footer">
        <div class="swiper-button-prev">上一题</div>
        <div  class="swiper-button-next  next  Submission "><a href="javaScript:void(0);"  id="Submission" >下一题</a></div>
    </div>
</div>

<!-- Swiper -->
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<!-- Swiper JS -->
<script src="${base}/mobileNew/js/test/swiper.min.js"></script>
<!-- Initialize Swiper -->
<script>
    $("#my_pro").css({"margin":"0 auto","overflow":"hidden"});
    $("#my_pro").find("span").css({"background-image":"linear-gradient(-130deg, #69BEFF 0%, #719EFF 100%)","border-radius":"4px"});


    $(".pager7 p").click(function(){
        $(this).toggleClass("active");
    });

    $(" .pager1 p, .pager2 p, .pager3 p, .pager4 p, .pager5 p, .pager6 p, .pager8 p, .pager9 p, .pager10 p, .pager11 p , .pager12 p").click(function(){
        $(this).addClass("active").siblings("p").removeClass("active");
    });


    $(".swiper-container").height("650px");
    $(".swiper-wrapper").height("650px");
    $("#page1").height("650px");

    /* 1    2  3 7*/






    var swiper = new Swiper('.swiper-container', {
        pagination: {
            el: '.swiper-pagination',
            type: 'progressbar'
        },
        watchSlidesProgress : true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev'
        },
        on: {
            slideChangeTransitionEnd: function(){
                //  alert(this.activeIndex);//切换结束时，告诉我现在是第几个slide
                $("#currentPager").html((this.activeIndex)+1);
            },
        },
    });

    $(".swiper-button-next ,.swiper-button-prev").click(function(){
        if($(this).find("a").html()=="提交"){
            var total = null;
            var value = null;
            var lenght;
            for(var i = 1 ; i <= 12 ;i++ ){
                if(i == "7"){
                    lenght = $(".pager7  p.active").size()
                }else{
                    value = $(".pager"+i).find("p.active").attr("value");
                    total += parseInt(value);
                }
            }
            total = total + parseInt(lenght);
            console.log(total);
            window.location.href="${base}/webPhone/mobileSaveWebPhoneCustMember.do?flag=${flag}&bidId=${bidId}&total="+total;
        }
        $("#currentPager").html((swiper.activeIndex)+1);
        if((swiper.activeIndex)+1 == "12"){
            $('.swiper-button-next a').html("提交");

        }else{
            $('.swiper-button-next a').html("下一题");
        }

    });


</script>
</body>
</html>