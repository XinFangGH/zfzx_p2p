<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle} - 风控管理</title>
    <meta name="description" content="${systemConfig.metaTitle} - 关于我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 关于我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="风控管理";</script>
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
        <div class="abouts-border" style="padding:30px 40px;">
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">银行监管</span>
            </div>
            <div class="windControl_blank"></div>
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">交易资金</span>
            </div>
            <div  class="windControl_transaction">
                <ul>
                    <li>
                        <span   class="windControl_division"></span>
                        <div  class="windControl_div">
                            <p  class="windControl_p_one">系统分账监管</p>
                            <p  class="windControl_p_two">接入富滇银行存管后，将由富滇银行对平台用户账户资金及平台自有运营资金进行分账监管，二者完全独立且相互隔离，平台无法接触用户账户资金。</p>
                        </div>
                    </li>
                    <li>
                        <span   class="windControl_jianguan"></span>
                        <div  class="windControl_div">
                            <p  class="windControl_p_one">用户资金监管</p>
                            <p  class="windControl_p_two">用户进行充值、绑卡、提现等每一笔与资金有关的操作，均需要通过富滇银行资金存管账户，由富滇银行对用户资金信息进行管理，避免资金被挪用。</p>
                        </div>
                    </li>
                    <li>
                        <span   class="windControl_chaozuo"></span>
                        <div  class="windControl_div">
                            <p  class="windControl_p_one">用户授权操作</p>
                            <p  class="windControl_p_two">用户需要开通富滇银行存管账户，并单独设立交易密码。在充值投资操作时，系统会验证密码，在得到用户的授权后，由银行根据交易指令进行。</p>
                        </div>
                    </li>
                    <li>
                        <span   class="windControl_liucheng"></span>
                        <div  class="windControl_div">
                            <p  class="windControl_p_one">银行监管交易流程</p>
                            <p  class="windControl_p_two">富滇银行对交易流程进行管理，并对所有的资金流水存档记录，确保借贷双方的资金流转和债权关系清晰明确。</p>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">风控流程</span>
            </div>
            <div  class="company_process">
                <ul>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
            <div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">风控体系</span>
            </div>
            <div  class="company_tixi">
                <div class="company_liucheng">
                    <ul>
                        <li class="encryption">
                            <span class="liucheng_encryption"></span>
                            <p style="">全程加密</p>
                        </li>
                        <li class="quarantine" style="margin-left:165px;">
                            <span class="liucheng_quarantine" style="width:82px;height: 82px;"></span>
                            <p>系统隔离</p>
                        </li>
                        <li class="testing" style="margin-left: 165px">
                            <span class="liucheng_testing"></span>
                           <p style="">异常检测</p>
                        </li>
                    </ul>
                </div>
                <div class="company_ahead"></div>
                <div class="company_pre"></div>
                <div class="company_after"></div>
            </div>




            <#--<div class="company_title">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">公司简介</span>
            </div>
            <div class="company_atr">
                <p>升升投由中发展信（北京）投资管理有限公司倾力打造线上金融服务平台，公司于2016年4月在北京成立，企业实际缴纳注册资本金为1亿元。自监管细则出台以来，银行存管成为业内最为关注的话题。银行存管能有效的将用户的资金与平台自身运营资金进行隔离，避免出现资金池，同时需授权交易，银行全程监管，保障投资者的资金更安全。与中发展信（北京）投资管理有限公司对接资金存管的银行是云南的一家商业银行“富滇银行”。
                </p>
                <p>
                    富滇银行成立于2007年12月30日，是经中国银监会批准的，在对昆明市商业银行进行增资扩股和处置不良资产的基础上成立的 云南省第一家省级地方性股份制商业银行。2016年中国前100家银行排名在59名，富滇银行资金存管审核更为严格。目前与该行接触的平台有200多家，只有30家平台获得了准入，目前有9家平台已经正常上线。
                </p>
                <p>升升投一直秉承”以人为本、高效专业“的核心理念，在互联网金融信息服务领域深耕细作，整合行业优势资源，基于互联网和大数据风控技术为用户提供简单便捷的金融信息服务。 未来，升升投将以为投资人提供多样化的投资产品、便捷的操作工具及优质的服务作为努力的方向，使投资人在获得资产稳健增值的同时享受到卓越的互联网金融信息服务所带来的优质体验，并将为全面构建、维护、推动中国金融创新与发展不遗余力！</p>
                <p> 升升投，您身边的财富好管家</p>

            </div>
            <div class="company_gro"></div>
            <div class="company_title company_zheng">
                <span class="company_title_span"></span>
                <span  class="company_title_JJ">公司证照</span>
            </div>
            <div class="company_zheng_bottom">
                <div  class="company_zheng_bottom_art">
                    <p>名称： 中发展信（北京）投资管理有限公司</p>
                    <p>类型： 有限责任公司（自然人投资或控股）</p>
                    <p>住所： 北京市朝阳区酒仙桥路甲16号9层9603</p>
                    <p>法定代表人： 李作林</p>
                    <p>注册资本： 10000万元</p>
                    <p>成立日期： 2016年04月13日</p>
                    <p>营业期限： 2016年04月13日 至 2036年04月12日</p>
                    <div class="company_zheng_bottom_du">
                        <div  style="width:70px;height:100px;line-height: 20px;">
                            经营范围：
                        </div>
                        <div  style="width:650px;height:100px;line-height: 20px;">
                            投资管理；项目投资；资产管理；经济贸易咨询；（“1、未经有关部门批准，不得已公开方式募集资金；不得公开开展证券类产品和金融衍生品交易活动；3、不得发放贷款；4、不得对所投资企业以外的其他企业提供担保；5、不得向投资者承诺投资本金不受损失或者承诺最低收益”；企业依法自主选择经营项目开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）

                        </div>
                    </div>
                    <div  class="company_zheng_bottom_pic"></div>
                </div>
            </div>-->






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
   /* $(".about-navbar").css("height","1513px");*/
</script>

</body>
</html>