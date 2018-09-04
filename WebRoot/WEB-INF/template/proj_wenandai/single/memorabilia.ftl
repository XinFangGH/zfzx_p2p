<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 大事记</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="大事记";</script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_common.css" />
</head>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<style>


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
        <div class="abouts-border" style="padding:30px 40px;min-height:736px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">发展历程</span>
            </div>

            <div class="history" style="display: none">
                <div>2018</div>
                <div></div>
                <div></div>
                <div><ul><li>03月</li><li>荣获“中国3.15诚信品牌企业”</li></ul></div>
                <div></div>
                <div></div>
                <div>
                    <ul>
                        <li>01月</li>
                        <li>荣获“2017-2018年度中国互联网金融安全示范单位”</li>
                        <li>荣获“2017-2018年度中国互联网金融十大绿色创新企业”</li>
                    </ul>
                </div>
                <div>2017</div>
                <div></div>
                <div></div>
                <div><ul><li>10月</li><li>公司线上平台升升投正式搭建</li></ul></div>
                <div></div>
                <div></div>
                <div><ul><li>08月</li><li>与富滇银行达成线上存管合作</li></ul></div>
                <div></div>
                <div></div>
                <div><ul><li>07月</li><li>划分全国三大业务区域</li></ul></div>
                <div></div>
                <div></div>
                <div><ul><li>01月</li><li>获工信部ICP备案号</li></ul></div>
                <div>2016</div>
                <div></div>
                <div></div>
                <div><ul><li>10月</li><li>荣获“金融行业3A级诚信单位”</li></ul></div>
                <div></div>
                <div></div>
                <div><ul><li>08月</li><li>布局全国市场</li></ul></div>
                <div></div>
                <div></div>
                <div><ul><li>04月</li><li>展信成立</li></ul></div>
            </div>
            <div class="history_pic">

            </div>


           <#-- <div class="fzlc">
                <div class="lc_con">
                    <div class="pos_line"></div>
                    <p class="tit"><i></i>2018</p>
                    <ul>
                        <li class="clearfix">
                            <div class="dl"><i></i>3月</div>
                            <div class="dr">
                                <p>荣获“中国3.15诚信品牌企业”</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>1月</div>
                            <div class="dr">
                                <p>荣获“2017-2018年度中国互联网金融安全示范单位”</p>
                                <p>荣获“2017-2018年度中国互联网金融十大绿色创新企业”</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="lc_con">
                    <div class="pos_line line2"></div>
                    <p class="tit"><i></i>2017</p>
                    <ul>
                        <li class="clearfix">
                            <div class="dl"><i></i>10月</div>
                            <div class="dr">
                                <p>公司线上平台升升投正式搭建</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>8月</div>
                            <div class="dr">
                                <p>与富滇银行达成线上存管合作</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>7月</div>
                            <div class="dr">
                                <p>划分全国三大业务区域</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>1月</div>
                            <div class="dr">
                                <p>获工信部ICP备案号</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="lc_con">
                    <div class="pos_line line3"></div>
                    <p class="tit"><i></i>2016</p>
                    <ul>
                        <li class="clearfix">
                            <div class="dl"><i></i>10月</div>
                            <div class="dr">
                                <p>荣获“金融行业3A级诚信单位”</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>8月</div>
                            <div class="dr">
                                <p>布局全国市场</p>
                            </div>
                        </li>
                        <li class="clearfix">
                            <div class="dl"><i></i>4月</div>
                            <div class="dr">
                                <p>展信成立</p>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>-->
        </div>
    </div>
</div>

    <!--end: Container -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
    <script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
    <link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" />
    <script type="text/javascript" src="${base}/js/page/jquery.page.js"></script>
    <!--<script type="text/javascript" src="${base}/js/wad_common.js"></script>-->
    <script>
        $(".tcdPageCode").createPage({
            pageCount:2,
            current:1,
            backFn:function(p){
                $('.memorabilia_list ul').eq(p-1).show().siblings('ul').hide();
            }
        });
    </script>
<script>
    $(function(){
        $(".memorabilia_list ul li").click(function(){
            var _this = $(this).find(".pic img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });

        $("#fork").click(function(){//再次点击淡出消失弹出层
            $(outerdiv).fadeOut("slow");
        });

        function imgShow(outerdiv, innerdiv, bigimg, _this){
            var src = _this.attr("data-src");
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
        }
    });

</script>
</body>
</html>