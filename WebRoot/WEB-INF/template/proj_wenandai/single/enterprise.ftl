<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 企业资质</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="企业资质";</script>
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
        <div class="abouts-border" style="padding:30px 40px;min-height: 735px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">荣誉资质</span>
            </div>
            <div class="memorabilia_list">
                <ul  style="display:block;">
                <#--<li>
                    <div class="pic">
                        <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/01.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/01.jpg" alt="">
                    </div>
                    <div class="mask"></div>
                    <span class="search"></span>
                    <p class="title">营业执照</p>
                </li>-->
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/02.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/02.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">营业执照（副本）</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/03.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/03.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">验资报告</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/04.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/04.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">验资报告</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/05.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/05.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">验资事项说明</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/06.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/06.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">验资报告</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/07.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/07.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">电子回单</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/08.png"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/08.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">电子回单</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/15.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/15.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">计算机软件著作权</p>
                    </li>
                </ul>
                <ul class="row"  style="display:none;">
                    <li>
                        <div class="pic">
                            <img style="width: 170px;height: 243px;" src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/16.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/16.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">增值电信业务经营许可证</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img style="width: 170px;height: 243px;"  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/17.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/17.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">增值电信业务经营许可证</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img class="sec_img"  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/09.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/09.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">开户许可证</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/10.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/10.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">注册资本明细表</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/11.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/11.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">会员证书</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/12.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/12.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">证书</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/13.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/13.jpg" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">认证联盟</p>
                    </li>
                    <li>
                        <div class="pic">
                            <img  src="${base}/theme/${systemConfig.theme}/images/newHonor/newMin/14.jpg"  data-src="${base}/theme/${systemConfig.theme}/images/newHonor/newMax/14.png" alt="">
                        </div>
                        <div class="mask"></div>
                        <span class="search"></span>
                        <p class="title">信息系统安全备案证明</p>
                    </li>
                </ul>
            </div>
            <div class="tcdPageCode"></div>
        <#--  <p class="tit"><i></i>荣誉资质</p>-->
        <#--  <div class="ryzz_box">
                <ul class="ryzz" style="display:block;">
                    <li>
                        <div class="pic"><img data-src="${base}/theme/${systemConfig.theme}/images/mypic/zs_big01.jpg" src="${base}/theme/${systemConfig.theme}/images/mypic/zs_01.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li>
                        <div class="pic"><img data-src="${base}/theme/${systemConfig.theme}/images/mypic/zs_big02.jpg" src="${base}/theme/${systemConfig.theme}/images/mypic/zs_02.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li class="li3">
                        <div class="pic"><img data-src="${base}/theme/${systemConfig.theme}/images/mypic/zs_big03.jpg" src="${base}/theme/${systemConfig.theme}/images/mypic/zs_03.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                </ul>
                <ul class="ryzz">
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/11.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/11.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/12.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/12.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li class="li3">
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/03.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/03.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                </ul>
                <ul class="ryzz">
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/04.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/04.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/05.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/05.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li class="li3">
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/06.jpg"  src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/06.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                </ul>
                <ul class="ryzz">
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/07.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/07.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li>
                        <div class="pic"><img class="hpic" data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/08.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/08.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                    <li class="li3">
                        <div class="pic"><img data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/09.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/09.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                </ul>
                <ul class="ryzz">
                    <li>
                        <div class="pic"><img data-src="${base}/theme/${systemConfig.theme}/images/Qualification/bigImg/10.jpg" src="${base}/theme/${systemConfig.theme}/images/Qualification/minImg/10.jpg" alt=""></div>
                        <div class="mask"></div>
                        <img src="${base}/theme/${systemConfig.theme}/images/mypic/sea_icon.png" class="search" alt="">
                    </li>
                </ul>
                <div class="tcdPageCode"></div>
            </div>-->
        <#--图片点击放大start-->
            <div id="outerdiv" style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:4;width:100%;height:100%;display:none;">
                <div id="innerdiv" style="position:absolute;">
                    <i id="fork"></i>
                    <img id="bigimg" style="border:5px solid #fff;" src="" />
                </div>
            </div>
        <#--图片点击放大end-->
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