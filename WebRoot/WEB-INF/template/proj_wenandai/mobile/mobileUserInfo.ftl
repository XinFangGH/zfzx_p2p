<!DOCTYPE html>
<html>
<head> 
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="telephone=no" name="format-detection">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="format-detection" content="telephone=no">
    <title>升升投 - 用户信息情况</title>
        <link rel="stylesheet"  media="all" type="text/css" href="${base}/theme/${systemConfig.theme}/css/mobile.css" />
</head>
<body>
    <div id="wrap">
        <!-- 顶部   -->
        <div class="wad_header">
            <div class="wad_topbar">
                <a href="#" class="wad_bak"></a>
                <em>用户信息情况</em>
                <span class="wad_allnav"></span>
            </div>

               <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/navbar.ftl">
        </div>
        <!-- 用户信息 -->
        <div class="wad_xinyongxinxi">
            <ul>
                <li class="wad_bottomtop"><div class="wad_item">基本信息</div></li>
                <li>
                    <div class="wad_item">
                        <label>用户名：</label>
                        <span>${planPro.persionName?substring(0,1)}****${planPro.persionName?substring(planPro.persionName?length-1)}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>公司行业：</label>
                        <span><#if planPro.companyIndustry=="null">未填写<#else>${planPro.companyIndustry}</#if></span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>年龄：</label>
                        <span>${planPro.age!}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>职位：</label>
                        <span>${planPro.position!}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>学历：</label>
                        <span>${planPro.education!}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>学校：</label>
                        <span>${custMem.collegename}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>婚姻：</label>
                        <span>${planPro.marriage!}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>城市：</label>
                        <span>${planPro.workCity!}</span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>月收入：</label>
                        <span><#if plan.monthIncome==0>未提供<#else>${plan.monthIncome!}元</#if></span>
                    </div>
                </li>
            </ul>
            <ul class="wad_jkmiaoshu">
                <li class="wad_bottomtop wad_itemspec"><div class="wad_item">借款描述</div></li>
                <li class="wad_lispec">
                    <div class="wad_item">
                        <span>${(planKeep.proDes)!}</span>
                    </div>
                </li>
            </ul>
         <!--   <ul>
                <li class="wad_bottomtop wad_itemspec"><div class="wad_item">资产信息</div></li>
                <li>
                    <div class="wad_item">
                        <label>车：</label>
                        <span>
                        	<#if planPro.vehicleProperty=='0'>
                        		有
                        	<#elseif planPro.vehicleProperty=='1'>
                        		无
                        	<#else>
                        		未填写
                        	</#if>
                        </span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>车贷：</label>
                        <span>
                        	<#if planPro.vehicleLoan=='1'>
                        		无
                        	<#elseif planPro.vehicleProperty=='0'>
                        		有
                        	<#else>
                        		未填写
                        	</#if>
                        </span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>房：</label>
                        <span>
                        	<#if planPro.houseProperty=='0'>
                        		有
                        	<#elseif planPro.houseProperty=='1'>
                        		无
                        	<#else>
                        		未填写
                        	</#if>
                        </span>
                    </div>
                </li>
                <li>
                    <div class="wad_item">
                        <label>房贷：</label>
                        <span>
                        	<#if planPro.houseLoan=='1'>
                        		无
                        	<#elseif planPro.houseLoan=='0'>
                        		有
                        	<#else>
                        		未填写
                        	</#if>
                        </span>
                    </div>
                </li>
            </ul>-->
            <ul>
                <li class="wad_bottomtop wad_itemspec"><div class="wad_item">认证信息</div></li>
                 <#list applyUploadList as upload>
                <li>
                    <div class="wad_item">
                        <label>
                        	<#if upload.materialstype=='IDCard'>
                        	身份验证：
                        	<#elseif upload.materialstype=='CreditRecord'>
                        	收入验证：
                        	<#elseif upload.materialstype=='Income'>
                        	收入验证：
                        	<#elseif upload.materialstype=='WebShop'>
                        	网店验证：
                        	<#elseif upload.materialstype=='House'>
                        	房产验证：
                        	<#elseif upload.materialstype=='Vehicle'>
                        	车辆验证：
                        	<#elseif upload.materialstype=='Marriage'>
                        	结婚验证：
                        	<#elseif upload.materialstype=='Education'>
                        	教育验证：
                        	<#elseif upload.materialstype=='Career'>
                        	工作验证：
                        	<#elseif upload.materialstype=='JobTitle'>
                        	职称验证：
                        	<#elseif upload.materialstype=='MobilePhone'>
                        	手机验证：
                        	<#elseif upload.materialstype=='MicroBlog'>
                        	微博验证：
                        	<#elseif upload.materialstype=='Residence'>
                        	居住验证：
                        	<#elseif upload.materialstype=='CompanyPlace'>
                        	经营场所验证：
                        	<#elseif upload.materialstype=='CompanyRevenue'>
                        	经营收入验证：
                        	<#elseif upload.materialstype=='Teacher'>
                        	教师验证：
                        	</#if>
                        </label>
                        <span>
	                        	<#if upload.status=='0'>
	                        		未上传
	                        	<#elseif upload.status=='1'>
	                        		待审查
	                        	<#elseif upload.status=='2'>
	                        		已驳回
	                        	<#else>
	                        		已认证
	                        	</#if>
                        </span>
                    </div>
                </li>
                 </#list>
            </ul>
           
        </div>
        <!--star footer  -->
          <#include "/WEB-INF/template/${systemConfig.theme}/mobilelayout/footerbar.ftl">
        <!-- end footer  -->
    </div>
    <script src='${base}/js/mobile/zepto.min.js'></script>
    <script type="text/javascript">
        $(".wad_allnav").click(function(){
            $(".wad_slidernav").toggle();
        });
    </script>
</body>
</html>
