<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 公司资质</title>
    <meta name="description" content="${systemConfig.metaTitle} - 公司资质,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 公司资质,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="公司资质";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />

</head>
<style type="text/css">
    .article-content {
        color: #6c6c6c;
        font-size: 14px;
        line-height: 40px;
        text-indent: 28px;
    }
    strong, b {
        font-weight: normal;
    }
</style>
<body >
<!-- header --><!-- navbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">

<div class="main">
    <!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr">
        <div class="abouts-border">
            <div class="titie_h1">
                <span class="left_pic"></span><h1>公司资质</h1><span class="right_pic"></span>
            </div>
            <div class="article" style="margin-top:55px;">
                <div class="img_box">
                    <ul>
                        <li style="width: 304px;height:216px;">
                            <ul>
                                <li style="width: 304px;height: 216px;display:table-cell;vertical-align: middle;position:relative;">
                                    <img style="display: inline-block;width: 304px;height: 216px;"  class="pimg"   data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/zhengshu01.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/zhengshu01.jpg"  alt="会员证书">
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>会员证书</p></li>
                            </ul>
                        </li>
                        <li class="right_box" style="width:304px;height:216px;">
                            <ul>
                                <li  style="width: 304px;height: 216px;display:table-cell;vertical-align:middle;position:relative;">
                                    <img  style="display: inline-block;width: 304px;height: 216px;"  class="pimg"   data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/zhengshu02.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/zhengshu02.jpg" alt="证书"  >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>证书</p></li>
                            </ul>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img  style="width: 264px;height: 350px;"     class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/01.jpg"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/01.jpg"  alt="营业执照" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>营业执照</p></li>
                            </ul>
                        </li>
                        <li class="right_box" >
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img   style="width: 264px;height: 350px;"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/02.jpg"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/02.jpg"  alt="营业执照(副本)" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>营业执照(副本)</p></li>
                            </ul>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img  style="width: 264px;height: 350px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/03.jpg"    alt="验资报告"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/03.jpg" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>验资报告</p></li>
                            </ul>
                        </li>
                        <li class="right_box" >
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img   style="width: 264px;height: 350px;position:relative;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/04.jpg"   alt="验资报告"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/04.jpg" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>验资报告</p></li>
                            </ul>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img   style="width: 264px;height: 350px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/05.jpg"   alt="验资事项说明"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/05.jpg" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>验资事项说明</p></li>
                            </ul>
                        </li>
                        <li class="right_box" >
                            <ul>
                                <li style="display:table-cell;vertical-align: middle;position:relative;">
                                    <img  style="width: 264px;height: 350px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/06.jpg"    alt="验资报告"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/06.jpg" >
                                    <div class="cover"></div>
                                    <span class="span_img"></span>
                                </li>
                                <li><p>验资报告</p></li>
                            </ul>
                        </li>
                    </ul><ul>
                    <li>
                        <ul>
                            <li style="display:table-cell;vertical-align: middle;position:relative;">
                                <img  style="width: 264px;height: 350px;"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/07.jpg"  alt="电子回单"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/07.jpg" >
                                <div class="cover"></div>
                                <span class="span_img"></span>
                            </li>
                            <li><p>电子回单</p></li>
                        </ul>
                    </li>
                    <li class="right_box" >
                        <ul>
                            <li style="display:table-cell;vertical-align: middle;position:relative;">
                                <img  style="width: 264px;height: 350px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/08.jpg"   alt="电子回单"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/08.jpg" >
                                <div class="cover"></div>
                                <span class="span_img"></span>
                            </li>
                            <li><p>电子回单</p></li>
                        </ul>
                    </li>
                </ul>
                    <ul>
                    <li  style="width:304px;height:216px;">
                        <ul>
                            <li style="width: 304px;height: 216px;display:table-cell;vertical-align: middle;position:relative;">
                                <img  style="width: 304px;height: 216px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/09.jpg"    alt="开户许可证"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/09.jpg" >
                                <div class="cover"></div>
                                <span class="span_img"></span>
                            </li>
                            <li><p>开户许可证</p></li>
                        </ul>
                    </li>
                    <li class="right_box" style="width:304px;height:216px;">
                        <ul>
                            <li style="width: 304px;height: 216px;display:table-cell;vertical-align: middle;position:relative;">
                                <img  style="width: 304px;height: 216px;" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/10.jpg"    alt="注册资本实收明细表"   class="pimg"  data_src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/10.jpg" >
                                <div class="cover"></div>
                                <span class="span_img"></span>
                            </li>
                            <li><p>注册资本实收明细表</p></li>
                        </ul>
                    </li>
                </ul>
                </div>
                <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:2;width:100%;height:100%;display:none;">
                    <div id="innerdiv" style="position:absolute;">
                        <i id="fork"></i>
                        <img id="bigimg" style="border:5px solid #fff;" src="" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<script>
    /*$(".about-navbar").css("height","2373px");*/
</script>

<script>
/*    $(function(){
        $(".pimg").click(function(){
            var _this = $(this);
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
    });  */


    $(function(){
        $(".img_box>ul>li>ul>li").click(function(){
            var _this = $(this).find("img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });
    });

    function imgShow(outerdiv, innerdiv, bigimg, _this){
        var src = _this.attr("data_src");
        $(bigimg).attr("src", src);

        $("<img/>").attr("src", src).load(function(){
            var windowW = $(window).width();
            var windowH = $(window).height();
            var realWidth = this.width;
            var realHeight = this.height;
            var imgWidth, imgHeight;
            var scale = 0.8;

            if(realHeight>windowH*scale) {
                imgHeight = windowH*scale;
                imgWidth = imgHeight/realHeight*realWidth;
                if(imgWidth>windowW*scale) {
                    imgWidth = windowW*scale;
                }
            } else if(realWidth>windowW*scale) {
                imgWidth = windowW*scale;
                imgHeight = imgWidth/realWidth*realHeight;
            } else {
                imgWidth = realWidth;
                imgHeight = realHeight;
            }
            $(bigimg).css("width",imgWidth);

            var w = (windowW-imgWidth)/2;
            var h = (windowH-imgHeight)/2;
            $(innerdiv).css({"top":h, "left":w});
            $(outerdiv).fadeIn("slow");//淡入显示#outerdiv及.pimg
        });

        $("#fork").click(function(){//再次点击淡出消失弹出层
            $(outerdiv).fadeOut("slow");
        });
    }
</script>
</body>
</html>