<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 站内信</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript">var m1="个人中心", m2="消息", m3="";</script>
</head>
<body >
 <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
	<div class="user-cont">
	<!--start: 左侧页面-->
	 	<div class="user-name-nav fl">
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
	 		<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
			<#--<#if bpCustMember.entCompanyType==1> 
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_guarantee.ftl">
			<#elseif bpCustMember.perCompanyType==1 >
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_financial.ftl"> 
			<#else>
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">  
			</#if>-->
        <!-- 左侧三级导航结束 end-->
 		</div>
    <!-- 左侧页面 end -->
    	<!-- 我的个人信息 start -->
     <div class="user-cont-fr fr">
	   	<div class="user-cont-right"  style="min-height:500px;">
	   		<div class="head-num">
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-five fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">未读消息(个)</p>
	   						<p class="middle"><em>0</em>个</p>
	   					</div>
	   				</div>
	   				<div class="num-icon fl">
	   					<div class="icon-img icon-img-six fl"></div>
	   					<div class="num-data fl">
	   						<p class="normal">已读消息(个)</p>
	   						<p class="middle"><em>0</em>个</p>
	   					</div>
	   				</div>
	   			</div>
	   		<h4 class="big">消息管理</h4>
        	<div class="cont-list">
            	 <p class="ched-btn">
            	 	<span>
	            	  	<a href="#" onclick="updateMessage(1)" class="middle current" >标记为已读</a>
	            	  	<a href="#" onclick="updateMessage(2)" class="middle">批量删除</a>
            	 	</span>
            	 </p>
                <table width="100%" class="tab_css_one" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                     <!-- <th class="top-line left-line" align="center"width="20%" ><input type="checkbox" onClick="this.value=check(this.form.ids)"/></th>-->
                     <th align="center" style="width:80px;"><input type="checkbox" name="all" id="all"/></th>
                     <th align="center">是否阅读</th>
                      <th align="center">消息类型</th>
                      <th align="center">消息标题</th>
                      <th align="center">收件时间</th>
                      <th align="center">操作</th>
                    </tr>
                    <#list pager.list as list>
                    <tr>
                     <td align="center">
                     <input type="checkbox" name="checkbox" id="check" value="${list.id}"/></td>
                       <td align="center"><#if list.readStatus!=null><#if list.readStatus==1>
                       <img src="${base}/theme/${systemConfig.theme}/images/mail/yidu.gif" width="30px"><#else>
                       <img src="${base}/theme/${systemConfig.theme}/images/mail/weidu1.gif" width="25px"></#if><#else>
                       <img src="${base}/theme/${systemConfig.theme}/images/mail/weidu1.gif" width="25px"></#if></td>
                      <td align="center"><#if list.typename!=null>${list.typename}<#else>&nbsp;</#if></td>
                      <td align="center"><#if list.title!=null><#if list.title?length gt 10>${list.title?substring(0,10)}...<#else>${list.title}</#if><#else>&nbsp;</#if></td>
                      <td align="center"><#if list.sendTime!=null>${list.sendTime}<#else>&nbsp;</#if></td>
                      <td align="center" >
                      	<a href="${base}/message/getOaNewsMessage.do?id=${list.id}"><span class="blue middle">查看详细</span></a>
		            	&nbsp;&nbsp;&nbsp;
		            	<a href="javascript:;" onclick="deleteMessage(${list.id})<#import "/WEB-INF/template/common/pager.ftl" as p>"><span class="blue middle">删除</span></a>
                      </td>
                    </tr>
                    </#list>
              	</table>
              	
              	 <#import "/WEB-INF/template/common/pager.ftl" as p>
      			<#assign parameterMap = {}/>
      			<@p.pager pager = pager baseUrl = "/message/getUserAllOaNewsMessage.do" parameterMap = parameterMap />
      			
            </div>
			</div>		
		</div>			
	</div>
</div>
<!--end: main -->
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>
<script type="text/javascript" src="${base}/js/user/message.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>

<#--js-render引用js文件-->
<script type="text/javascript" src="${base}/js/page/jquery.pagination-1.2.3.js"></script>
<script type="text/javascript" src="${base}/js/jsrender/jsrender.min.js"></script>

<script id="temp" type="text/x-jsrender">
	<div class="num-icon fl">
		<div {{if notReadNums==0}}  class="icon-img icon-img-six fl" {{else}} class="icon-img icon-img-five fl"{{/if}}></div>
		<div class="num-data fl">
			<p class="normal">未读消息(个)</p>
			<p class="middle"><em>{{:notReadNums}}</em>个</p>
		</div>
	</div>
	<div class="num-icon fl">
		<div class="icon-img icon-img-six fl"></div>
		<div class="num-data fl">
			<p class="normal">已读消息(个)</p>
			<p class="middle"><em>{{:readNums}}</em>个</p>
		</div>
	</div>
</script>

<script type="text/javascript">
	$(function () {
		showStatistic();
	});
    //查询统计数据	
	function showStatistic(){
	    var new_map0={age:2,start:0,limit:10};
	    ajaxFunction("${base}/message/showMessageNumsOaNewsMessage.do",new_map0,callback0_0);
	}	
    //获取我的借款的统计数据
	 function callback0_0(data){
	 	var result=data.result;
	 	$(".head-num").empty().html($("#temp").render(result));//累计赚取收益
	 }
</script>