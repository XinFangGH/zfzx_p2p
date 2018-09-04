<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 积分记录</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<script type="text/javascript">var m1="个人中心", m2="我的积分", m3="";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
    	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
    	</div>
    	 <div  class="user-cont-fr fr">
	   		<div class="user-cont-right">
	   			<h2 class="big-tit">积分记录</h2>
            <div class="cont-list">
                <table width="100%" class="tab_css_one" style="margin:0 auto;" border="0" align="center" cellpadding="0" cellspacing="0">
	                
                    <tr>
                      <th class="top-line" align="center" width="15%">时间</th>
                      <th class="top-line" align="center"  width="15%">积分</th>
                      <th class="top-line" align="center"  width="15%">详情</th>
                    </tr>
                    <#list pager.list as list>
                    <tr class="ff">
                     <td align="center">${list.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                      <td align="center"><#if list.recordDirector==1>+${list.recordNumber}<#else><font color="red">-${list.recordNumber}</font></#if></td>
                      <td align="center">${list.bonusDescription}</td>
                    </tr>
                    </#list>
              	</table>
            </div>
            <div>	 <#import "/WEB-INF/template/common/pager.ftl" as p>
  			<#assign parameterMap = {} />
  			<@p.pager pager = pager baseUrl = "/bonusSystem/pageListWebBonusRecord.do"  parameterMap = parameterMap />
  			</div>
           </div>
    	</div>
	</div>
</div>
<!--end: main -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

	
	</body>
</html>