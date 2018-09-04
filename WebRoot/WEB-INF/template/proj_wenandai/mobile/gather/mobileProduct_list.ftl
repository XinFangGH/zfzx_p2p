<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <meta  http-equiv="x-rim-auto-match" content="none">
    <title>升升投 - 产品展示</title>
    <script type="text/javascript" src="../js/jQuery/jquery-1.8.2-min.js"></script>
    <script type="text/javascript" src="../js/user/login.js"></script>
    <link rel="stylesheet"  media="all" type="text/css" href="../theme/${systemConfig.theme}/css/mobile.css" />
     <link href="../theme/${systemConfig.theme}/css/style.css" rel="stylesheet" media="all">
    <style type="text/css">
        @charset "UTF-8";
        body,input {margin: 0;padding: 0;font-family: "Myriad Set Pro","Lucida Grande","Helvetica Neue","Helvetica","Arial","Verdana","sans-serif";font-size:12px;line-height:1.5;text-rendering: optimizeLegibility;-webkit-font-smoothing: antialiased;-moz-osx-font-smoothing: grayscale;-moz-font-feature-settings: "liga", "kern";color: #ccc;border: 0;}
        ul,li,ol,dl,dt,dd,h1,h2,h3,h4,h5,h6,em,i,p {padding: 0;margin: 0;list-style: none;font-style: normal;font-weight: normal;}
        #wrap {background: #efefef;}
        a,img {border: none;text-decoration: none;}
        img{display: block;}
        /* reset */


        /*弹出层申请表单验证&产品敬请期待提示*/
        .tipswrap {
            position: fixed;
            top: 0;
            width: 100%;
            background: rgba(0,0,0,.7);
            display: none;
            transition: opacity .5s ease-in-out;
            -webkit-transition: opacity .5s ease-in-out;
            -moz-transition: opacity .5s ease-in-out;
            overflow: hidden;
            z-index: 10;
            bottom: 0;
            opacity: 1;
        }

        .tipswrap .tipscontent {
            margin: auto;
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            max-height: 100%;
            max-width: 100%;
            box-shadow: 0 0 8px rgba(0,0,0,.3);
            box-sizing: border-box;
            -webkit-box-sizing: border-box;
            transform:scale(1,1);
            z-index: 0;
        }

        .tipswrap .tipscontent span {
            color: #000;
            font-size: 14px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -52px;
            margin-left: -35%;
            background: #fff;
            display: block;
            border-radius: 5px;
            width: 70%;
        }

        .tipswrap .tipsclose {
            display: block;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            color: #007aff;
            height: 44px;
            text-align: center;
            line-height: 44px;
            border-top: 1px solid #bdbdbd;
            font-size: 15px;
        }

        .tipswrap .box_close:before,.tipswrap .box_close:after {
            content: none;
        }

        .tipswrap em {
            display: block;
            width: 100%;
            text-align: center;
            height: 60px;
            line-height: 60px;
            font-size: 18px;
            color: #222;
        }

        /*产品列表*/
        .product dl{
            height: 80px;
            margin: 10px 4%;
            background: #fff;
            padding: 10px 3%;
            width: 86%;
            border: 1px dashed #e4eaee;
            box-shadow: 0 0 2px #ddd;
        }

        .product dt {
            height: 28px;
            line-height: 28px;
            color: #000;
            overflow: hidden;
        }
        .product dt span {
            font-size: 16px;
            width: 18%;
            float: left;
            text-align: center;
        }

        .product dt span:nth-child(2) {
            font-size: 14px;
            padding-left: 10%;
            width: 68%;
            text-align: left;
            text-overflow:ellipsis;
            white-space:nowrap;
            overflow: hidden;
        }

        .product dd {
            float: left;
            margin: 2px 0;
            width: 48%;
        }

        .product .product_index_img {
            width: 18%;
            text-align: center;
            padding-right: 5%;
        }

        .product_index_img img {
            width: 44px;
            display: block;
            margin: 2px auto;
        }

        .product dl dd:nth-child(3) {
            width: 1px;
            float: left;
            height: 32px;
            margin: 10px 0 10px -2px;
            border-left: 1px dashed #e4eaee;
        }

        .product .product_index_news {
            width: 72%;
            float: left;
            padding-left: 5%;
        }

        .product_index_news span {
            float: left;
            width:50%;
            font-size: 14px;
            color: #666;
            text-align: left;
            line-height: 24px;
        }




    </style>
</head>
<body>
   
   <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>信用借款</em>
                <span class="wad_allnav"></span>
            </div>

            <div class="wad_slidernav">
                <div class="wad_nav">
                    <p><img src="../theme/proj_wenandai/images/mobile/shouye.png"><a class="current">首页</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/touzi.png"><a class="current">投资</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/yonghu.png"><a class="current">我的</a></p>
                    <p><img src="../theme/proj_wenandai/images/mobile/guanyu.png"><a class="current">关于</a></p>
                </div>
            </div>
        </div>
   
   
   <!-- 产品推荐模块 -->
        <div class="product">
            <dl>
                <dt><span>青春贷</span><span><a href="${base}/mobileProduct/first_PDAction.do?productName=青春贷&productId=16">立即申请</a></span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/qc.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
            <dl class="else">
                <dt><span>园丁贷</span><span>教师专属理财快速通道</span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/js.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
            <dl class="else">
                <dt><span>医护贷</span><span>教师专属理财快速通道</span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/yh.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
            <dl class="else">
                <dt><span>薪资贷</span><span>上班白领的首选理财，机不可失</span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/xz.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
            <dl class="else">
                <dt><span>经营贷</span><span>私营企业主的及时雨，雪中送炭犹未晚</span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/jy.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
            <dl class="else">
                <dt><span>网商贷</span><span>淘宝掌柜们的临时输血站，快速生存</span></dt>
                <dd class="product_index_img"><img src="../theme/proj_wenandai/images/mobile/ws.png"></dd>
                <dd></dd>
                <dd class="product_index_news"><span>月费率<em>1.27%</em></span><span>放款时间<em>1天</em></span><span>额度<em>3千 - 50万</em></span><span>每月还款</span></dd>
            </dl>
        </div>


        <!-- 弹出层申请表单验证&产品敬请期待提示 -->
        <div class="tipswrap">
            <div class="tipscontent">
                <span>
                    <em class="tips">本产品尚未开放手机端申请，请前往PC官网申请</em>
                    <i><a class="tipsclose" href="#">知道了</a></i>
                </span>
            </div>
        </div>


        <!-- footer  -->
        <div class="footer">
            <div class="version"><a href="#">Phone版</a><a href="#">PAD版</a><a href="javascript:;">电脑版</a></div>
            <div class="footer_bottom">
                <p class="wad_lianxi"><a><img src="../theme/proj_wenandai/images/mobile/qq.png">4006918987</a><a href="tel:4006918987"><img src="../theme/proj_wenandai/images/mobile/phone1.png">4006-918-987</a></p>
            </div>
            <div class="footer_banquan">版权所有 升升投商务顾问（北京）有限公司<br>© 2014 </div>
        </div>
    </div>
    <script src='../js/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
        $(function () {
            $('.bottom_box_up_target').click();
        });

        $('.wad_wcont').click(function () {
            $('#tipshide').show();
        });

        $('.else').click(function () {
            $(".tipswrap").show();
        });

        $('.tipsclose').click(function () {
            $('.tipswrap').hide();
        });

        
    </script>
   
   
   
   
  <!-- <div class="list-way-ul">
        <div class="porduct one wad_porspec1">
            	<h4>园丁贷<span class="small">(适用教育工作者)</span></h4>
                
                <p><a href="${base}/mobileProduct/first_PDAction.do?productName=园丁贷&productId=15">立即申请</a></p>
            	</div>
            	<div class="porduct wad_porspec2">
            	<h4>青春贷<span class="small">(适用于大学在校生)</span></h4>
                <ul>
                	<li>申请条件：</li>
                </ul>
                <p><a href="${base}/mobileProduct/first_PDAction.do?productName=青春贷&productId=16">立即申请</a></p>
            	</div>
            	<div class="porduct wad_porspec3">
            	<h4>医护贷<span class="small">(适用于职业医师及护士)</span></h4>
                <ul>
                	<li>申请条件：</li>
                </ul>
                <p><a href="javascript:void(0)">敬请期待</a></p>
            	</div>
         		<div class="porduct wad_porspec4">
            	<h4>薪资贷<span class="small">(适用工薪阶层)</span></h4>
                <ul>
                	<li>申请条件：</li>
                </ul>
                <p><a href="${base}/mobileProduct/first_PDAction.do?id=12">立即申请</a></p>
            	</div>
         		<div class="porduct porduct1 wad_porspec5">
            	<h4>经营贷<span class="small">(适用私营企业主 )</span></h4>
                <ul>
                	<li>申请条件：</li>
                </ul>
                <p><a href="${base}mobileProduct/first_PDAction.do?id=13">立即申请</a></p>
            	</div>
            	<div class="porduct none wad_porspec6">
            	<h4>网商贷<span class="small">(适用淘宝网店商户 )</span></h4>
                <ul>
                	<li>申请条件：</li>
		      </ul>
                <p><a href="${base}/mobileProduct/first_PDAction.do?id=14">立即申请</a></p>
            	</div>
        </div>
    </div>-->
</body>
</html>