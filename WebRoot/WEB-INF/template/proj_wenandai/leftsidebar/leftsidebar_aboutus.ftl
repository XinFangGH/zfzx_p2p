<div class="leftsidebar about-navbar my-about-navbar ">
	<ul>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/commitmentSingle.do?lid=32">承诺函<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/complianceSingle.do">合规进程<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/companySingle.do">公司简介<em class="user-arrow"></em></a></li>
        <#--<li class="trititle">&lt;#&ndash;<i class="red_user"></i>&ndash;&gt;<a href="${base}/html/teamSingle.do">公司资质<em class="user-arrow"></em></a></li>-->
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/structureSingle.do">组织架构<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/memorabiliaSingle.do">大事记<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/enterpriseSingle.do">企业资质<em class="user-arrow"></em></a></li>

        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/operateSingle.do">运营数据<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/platformSingle.do">平台信息<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/sefitySingle.do">风控管理<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/riskcontrolSingle.do">重大风险信息<em class="user-arrow"></em></a></li>
       <#-- <li class="trititle"><i class="red_user"></i><a href="${base}/html/financialSingle.do">财务报告<em class="user-arrow"></em></a></li>-->
    <#--  <li class="trititle"><i class="red_user"></i><a href="${base}/html/regulationsSingle.do">法律法规<em class="user-arrow"></em></a></li>-->
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/article/lawListArticle.do?lid=32">出借人教育<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/article/newslistArticle.do?lid=30">网站公告<em class="user-arrow"></em></a></li>
        <#--<li class="trititle"><i class="red_user"></i><a href="${base}/html/bulletinSingle.do">网站公告<em class="user-arrow"></em></a></li>-->
        <#--
        <li class="trititle"><i class="red_user"></i><a href="${base}/article/newslistArticle.do?lid=33">最新动态<em class="user-arrow"></em></a></li>
        <li class="trititle"><i class="red_user"></i><a href="${base}/html/careersSingle.do">招贤纳士<em class="user-arrow"></em></a></li>-->
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/article/newslistArticle.do?lid=34">媒体报道<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/html/contactSingle.do">联系我们<em class="user-arrow"></em></a></li>
        <li class="trititle"><#--<i class="red_user"></i>--><a href="${base}/gonglue/">理财攻略<em class="user-arrow"></em></a></li>
            <#list  listParent as parent>
        <li class="trititle" data-i="hj" style="display:none;text-align:center;"><#--<i class="red_user"></i>--><a href="${base}/gonglue/${parent.id}/">${parent.name}</a></li>
            </#list>
	</ul>
</div>

