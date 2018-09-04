<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 债权详情</title>

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/login.js"></script>
<script type="text/javascript" src="${base}/js/sign.js"></script>
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/flb.css" />
<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/xy-lc.css">
<script type="text/javascript" src="${base}/js/jquery.fancybox-1.3.1.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${base}/theme/proj_wenandai/css/wad_fancybox.css" /> 
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
	$(function() {
		$('a[rel*=leanModal]').leanModal({ top : 200 });		
	});
	window.onload=function() {
	$(".finlist .finlist_title li").each(function(index) { //带参数遍历各个选项卡
		$(this).click(function() { //注册每个选卡的单击事件
			$(".finlist .finlist_title li.active").removeClass("active"); //移除已选中的样式
			$(this).addClass("active"); //增加当前选中项的样式
			//显示选项卡对应的内容并隐藏未被选中的内容
			$(".finlist .finlist_hide ol:eq(" + index + ")").show()
			.siblings().hide();
		});
	});
	
	$(function(){
		$("#to").click(function(){
			var myMoney = $("#myMoney").text();
			var afterMoney = $("#afterMoney").text();
			var subMyMoney = myMoney.substring(0,myMoney.length-1);
			var subAfterMoney = afterMoney.substring(0,afterMoney.length-1);
			var strAfterMoney = "";
			var strMyMoney ="";
			for(var i=0;i<subAfterMoney.length;i++){
				if(","!=subAfterMoney[i]){
					strAfterMoney+= subAfterMoney[i];
				}
			}
			for(var i=0;i<subMyMoney.length;i++){
				if(","!=subMyMoney[i]){
					strMyMoney+= subMyMoney[i];
				}
			}
			
			if(strMyMoney > strAfterMoney){
				$("#signMoney").val(strAfterMoney);
			}else if(strMyMoney <= strAfterMoney){
				$("#signMoney").val(strMyMoney);
			}else{
				$("#signMoney").val(0);
			}
			
		});
	});
}

</script>
<script type="text/javascript">	
$(function(){
		//图标下拉框  提示框
	var content = $(".sidnext").html();	
	$('.icon-one').pt({
		position: 'b',
		width:300,
		content: content
	});	 
	var content = $(".sidnext1").html();	
	$('.icon-two').pt({
		position: 'b',
		width:300,
		content: content
	});	 
	var content = $(".sidnext2").html();	
	$('.icon-three').pt({
		position: 'b',
		width:300,
		content: content
	});	   
	
});
</script>
</head>
<body>
<div> 
  <!-- topbar -->
  <!-- header --><!-- navbar -->
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<!-- main --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 开始 只需要拿这个说明标签之内的部分 --> 
<!-- 个人直投标 -->
<input type="hidden" id="bidType" value="claim">
<input value="${(bpCustMember.grade)??}" type="hidden" id="grade">
<div style="width:100%;background:#eff3f3;overflow:hidden; ">
<div class="main">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/share.ftl">
	<!--<div class="porduct">
    	<a href="#">产品详情</a>
    </div>-->   
    <div class="inner">
    	<h2 class="title"><span class="pic"></span><span style="padding:0 10px;">项目名称：${(plBidPlan.bidProName)!}</span><span style="padding:0 10px;">编号：${plBidSale.saleNumber}</span></h2>
    	<div class="inner-left">        	
            <div class="condition">
            	<dl class="didt">
            	<dt>年化利率</dt>
                	<dd><i>${(planPro.yearInterestRate)!}</i>%</dd>
                </dl>
                <dl class="didt">
                	<dt>转让本金</dt>
                	<dd><#if (plBidSale.factMoney>=10000)><i>${(plBidSale.factMoney/10000)?string(",##0.00#")}</i>万元<#else><i>${plBidSale.factMoney?string(',##0')}</i>元</#if></p></dd> 
                </dl>
                <dl class="didt didt-none">
                 	<dt>剩余期限</dt>
                	<dd>${(plBidSale.days)!}天</d>  
                </dl>
            </div>
            <ul>
             
	            	<li>
	            	<em><img src="${base}/theme/proj_wenandai/images/ty-icon1.png"  alt="" /></em>
			                      原始债权金额：<b class="red" style="font-weight:normal;">${plBidSale.saleMoney}</b>
	            	</li>
	            	
	            	<li>
	            	<em><img src="${base}/theme/proj_wenandai/images/ty-icon2.png"  alt="" /></em>
			                      年化收益率：<b class="red" style="font-weight:normal;">${(planPro.yearInterestRate)!}%</b>
	            	
	            	</li>
	            	
	            	<li>
	            	<em><img src="${base}/theme/proj_wenandai/images/ty-icon3.png"  alt="" /></em>
			                      债权到期日：<b class="red" style="font-weight:normal;">${plBidSale.endIntentDate}</b>
	            	
	            	</li>
	            
            	<li>
            	<em class="icon-one"><img src="${base}/theme/proj_wenandai/images/ty-icon4.png"  alt="" /></em>
            	还款方式：${plan.theWayBack!}
                 <#if plan.payIntersetWay=='1'>
                 <div class="sidnext" style="display:none;">等额本息还款法是在还款期内，每月偿还同等数额的贷款(包括本金和利息)。借款人每月还款额中的本金比重逐月递增、利息比重逐月递减。</div>
                 </#if>
                 
                 <#if plan.payIntersetWay=='2'>
                 <div class="sidnext" style="display:none;">等额本金是在还款期内把贷款数总额等分，每月偿还同等数额的本金和剩余贷款在该月所产生的利息，每月的还款本金额固定，而利息越来越少</div>
                 </#if>
                 
                 <#if plan.payIntersetWay=='3'>
                 <div class="sidnext" style="display:none;">等本等息还款法是在还款期内，每个月平均偿还本金和利息，利息一直按总本金计算</div>
                 </#if>
                 
                  <#if plan.payIntersetWay=='4'>
                  <div class="sidnext" style="display:none;">先息后本还款法是在还款期内，每月只偿还利息，在最后一期偿还利息的同时偿还全部本金。</div>
                  </#if>
                
                </li>
                <!--<li>
                	<em><img src="${base}/theme/proj_wenandai/images/ty-icon5.png"  alt="" /></em>
                	保障方式：本金保障
                <a href="${base}/html/secureSingle.do?toWhere=projectDesc#projectDesc"><#--<span style="display:inline-block;" >[?]</span>--></a>
                </li>-->
               
            </ul>
        </div>
        
        <!-- start 已满标-->
        <#if plBidSale.saleStatus==3 > 
        <div class="inner-right1">
        	<i class="bg"></i>
        	<h3>交易中</h3>
            <div class="sumb">
            	
                    <ul>
                    	<li>交易时间：</li>
                    	<li style="font-size:20px;">${plan.bidFullTime}</li>
                    </ul>
               
            </div>
        </div>
        <!--end 已满标-->
        <!-- start还款中-->
        
        <#elseif plBidSale.saleStatus==4 || plBidSale.saleStatus==7>
         <div class="inner-right1" >
         	<i class="bg"></i>
        	<h3>交易成功</h3>
        	<ul>
                    	<li>交易成功时间：</li>
                    	<li style="font-size:20px;">${plan.bidFullTime}</li>
             </ul>
            
        </div>
        <!-- end还款中-->
        <!-- start已还清-->  
        <#elseif plBidSale.saleStatus==9>  
        <div class="inner-right1" >
        	<i class="bg"></i>
        	<h3>取消交易</h3>
            <div class="sumb">
            		
                    <ul>
                    	<li>取消交易时间：${plan.repaymentFullDate}</li>
                    </ul>
                
            </div>
        </div>
        <!-- end 已还清 -->
        <!-- start 已过期 -->
        <#elseif plBidSale.saleStatus==10>
         <div class="inner-right1" >
         	<i class="bg"></i>
        	<h3>交易过期</h3>
            <div class="sumb">
            	<ul>
                    <p style="padding:20px 0;font-size:16px;text-align:center;">活动结束！</p>
               </ul>
            </div>
        </div>
        
        <#else>
         
        <div class="inner-right1">
        	<i class="bg"></i>
        	<h3>购买中</h3>
            <div class="sumb">
            	<li style="height:14px; line-height:10px; display:none;"><span id="moneyspan"></span></li>
                    	<li>剩余金额：<input type="hidden" id="afterM" value="${plBidSale.factMoney}"/><span style="color:#fd7754">${plBidSale.factMoney?string(',##0.00')}元</span></li>

                    	 <#if Session.successHtml=='undefined'||Session.successHtml==null> <li>账户余额：登录可见</li><#else>
                        <li>账户余额：<span style="color:#333">${bpCustMember.availableInvestMoney?string(',###.00')}</span></li>                       
                    	</#if>
                    </ul>
                    <#if Session.successHtml=='undefined'||Session.successHtml==null>
                  		<a href="${base}/htmllogin.do?retUrl=" target="_self" id="bidLogin">立即登录 </a>
                   <#else>
                   		<div class="btn">
					    	<a href="javascript:void(0);" class="wad_open" onClick="clickOrStartTransferFormbuy(${plBidSale.bidInfoID},${planPro.yearInterestRate},'${plBidSale.endIntentDate}','${plBidSale.startDate}','${plBidSale.id}')">购买</a> 
                   		</div>
                   </#if>
            </div>
        </div>
        </#if>
        
    </div>
    <#--
  <#if plan.proKeepType=="福利标">
  <#include "/WEB-INF/template/proj_wenandai/bid/welfare/welfare.ftl">
  
    <#else>
    -->
    <div class="finlist">
       <ul class="finlist_title">
          <li class="active">标的详情</li> 
          <#--<li>相关材料</li>-->
          <li>投标记录</li>
          <li>还款表现</li>
          <li>债权信息</li>
          <li>转让记录</li>
       </ul>
       <div class="finlist_hide">
         <ol style="display:block">
             <div class="content">
                 <div class="contentleft">
                     <div class="contentleft1">
                         <span class="bg"></span>
                         <h5>企业基本信息</h5>
                     </div>
                     <table class="my-table">
                         <tr>
                             <td class="td1">企业名称</td>
                             <td class="td2">
                            <#if enterPrise.enterprisename=="">未填<#else>${enterPrise.enterprisename}</#if>
                             </td>
                             <td class="td1">所有制性质</td>
                             <td class="td2"><#if enterPrise.ownership=="">未填<#else>${enterPrise.ownership!}</#if></td>
                         </tr>
                         <tr>
                             <td class="td1">注册资金</td>
                             <td class="td2"><#if enterPrise.registermoney=="">未填<#else>${enterPrise.registermoney!}万元</#if></td>
                             <td class="td1">注册时间</td>
                             <td class="td2"><#if enterPrise.opendate=="">未填<#else>${enterPrise.opendate!}</#if></td>
                         </tr>
                         <tr>
                             <td class="td1">经营所在地</td>
                             <td class="td2"><#if enterPrise.managecityName=="">未填<#else>${enterPrise.managecityName!}</#if></td>
                             <td class="td1">所属行业</td>
                             <td class="td2"><#if enterPrise.hangyeName=="">未填<#else>${enterPrise.hangyeName!}</#if></td>
                         </tr>
                         <tr>
                             <td class="td1">营业范围</td>
                             <td class="td2"><#if planKeep.proBusinessScope=="">未填<#else>${planKeep.proBusinessScope!}</#if></td>
                             <td class="td1">主要债务</td>
                             <td class="td2"><#if planKeep.mainDebt=="">未填<#else>${planKeep.mainDebt!}</#if></td>
                         </tr>
                     </table>
                 </div>
             </div>
             <div class="content">
                 <div class="contentleft">
                     <div class="contentleft1">
                         <span class="bg"></span>
                         <h5 class="icon2">项目信息</h5>
                     </div>


                     <table class="my-table">
                         <tr>
                             <td class="td1">项目描述</td>
                             <td class="td3">
							 ${planKeep.proDes}
                             </td>
                         </tr>
                         <tr>
                             <td class="td1">资金用途</td>
                             <td class="td3">融资款主要用于工程机械整机采购</td>
                         </tr>
                         <tr>
                             <td class="td1">还款来源</td>
                             <td class="td3">1）租赁工程机械租金保障 2）设备残余价值保障 3）融资租赁公司其他待回款保障 4）相关产业链企业提供还款保障！</td>
                         </tr>
                     </table>
                 </div>
             </div>

		 <#--经营公示图片-->
         <p style="font-size: 18px;color:#333;text-align: left;margin-top: 30px">
            <#if fileLIst?? && fileLIst?size gt 0  >
                 <i style="display:inline-block;    margin-right: 10px;width:24px;height:20px;vertical-align:sub;background:url(${base}/theme/${systemConfig.theme}/images/03.png)no-repeat;"></i>经营公示
			<#else>  </p>
			</#if>
             <div class="htmleaf-container" style="width:100%;margin-top: 20px;">
                 <div style="width: 845px;    margin: 0 auto;">
                 <#if fileLIst?? && fileLIst?size gt 0 >
                     <ul class="vmcarousel-centered-infitine vmc-centered" style="width: 1824px">

                         <#list fileLIst?sort_by("sort") as fList >
                         <li>
                             <img src="${base}/${fList.webPath!}" alt=""   data_src="${base}/${fList.webPath!}" width="260">
                             <div class="cover"></div>
                             <span class="span_img"></span>
                             <p>${fList.setname!}</p>
                         </li>
						 </#list>
                     </ul>
				 </#if>
                 </div>
                 <div id="outerdiv"  style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:35;width:100%;height:100%;display:none;">
                     <div id="innerdiv" style="position:absolute;">
                         <i id="fork"></i>
                         <img id="bigimg" style="border:5px solid #fff;" src="" alt="">
                     </div>
                 </div>
             </div>

		 <#--设备公示-->
         <p style="font-size: 18px;color:#333;text-align: left;margin-top: 30px">
                 <#if listMaterials?? && listMaterials?size gt 0  >
                     <i style="display:inline-block;    margin-right: 10px;width:24px;height:20px;vertical-align:sub;background:url(${base}/theme/${systemConfig.theme}/images/04.png)no-repeat;"></i>设备公示</p>
					 <#else ></p>
				 </#if>
             <div class="htmleaf-container" style="width:100%x;margin-top: 20px;">
                 <div style="width: 845px;    margin: 0 auto;">
             <#if listMaterials?? && listMaterials?size gt 0 >
                 <ul class="vmcarousel-centered-infitine vmc-centered" style="width: 1692px">
                 <#list listMaterials as list>
                     <li>
                         <img src="${base}/${list.imgUrl!}" alt=""   data_src="${base}/${list.imgUrl!}" width="260">
                         <div class="cover"></div>
                         <span class="span_img"></span>
                         <p>${list.materialsName!}</p>
                     </li>
				 </#list>
                 </ul>
			 </#if>
                 </div>
                 <div id="outerdiv"  style="position:fixed;top:0;left:0;background:rgba(100, 100, 100, 0.5);z-index:35;width:100%;height:100%;display:none;">
                     <div id="innerdiv" style="position:absolute;">
                         <i id="fork"></i>
                         <img id="bigimg" style="border:5px solid #fff;" src="" alt="">
                     </div>
                 </div>
             </div>

         </ol>
       
       
       <#--	<ol>
       	 <div class="content">
       	 	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>
    	<!--借款材料 start &ndash;&gt;
            <div class="contentleft">

               <div class="tab tab1">
            	<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="150" height="40"align="center" bgcolor="#47B2D6">审核项目</th>
                        <th width="100" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="100" align="center" bgcolor="#47B2D6">通过日期</th>
                        <th width="100" align="center" bgcolor="#47B2D6">详细</th>
                      </tr>
                      <#list listMaterials as list>
	                      <tr>
	                        <td align="center" height="40" bgcolor="#FAFBFD">${list.materialsName}</td>
	                        <td align="center" bgcolor="#FAFBFD">通过</td>
	                        <td align="center" bgcolor="#FAFBFD"><#if list.createTime==null>-- --<#else>${list.createTime!}</#if></td>
	                        <td align="center" bgcolor="#FAFBFD"><a>
		                        <#if list.imgUrl !="">
			                	<a rel="group" class="spec_img" href="${base}/${list.imgUrl}" title="${list.materialsName}"><i class=""></i>
			                	<img src="${base}/theme/proj_wenandai/images/chakan.png" title="查看"></a>
			                	<#else>
			                	未上传材料
			                	</#if>

		                    	<#list list.fileFormList as filelist>
		                    	<#if filelist_index !=0>
			                	<a rel="group" class="spec_img" href="${base}/${filelist.filepath!}" title="${list.materialsName}"><i class=""></i>
						        <img alt="${list.materialsName}" style="display:none" src="${base}/${filelist.filepath!}" width="100" height="80"/></a>
			                	</#if>
			                	</#list>
	                        	</a>
	                        </td>
	                      </tr>
                      </#list>

                </table>
            </div>
            </div>
    	<!--借款材料 end &ndash;&gt;
    	     </#if>
         </div>
       </ol>-->
       
       	
       	<ol>
            <div class="content">
       	 	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
                 <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
			 <#else>
    	<!--借款材料 start -->
            	<div class="contentleft">
                    <div class="tab <#--tab1-->">
                        <table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                            <thead>
                            <tr>
                                <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标人</th>
                                <th width="50" align="center" bgcolor="#47B2D6">年利率</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标金额</th>
                                <th width="150" align="center" bgcolor="#47B2D6">投标时间</th>
                            </tr>
                            </thead>
                            <tbody  id="content">
                            <#if (listPlBid!=null)>
                           <#list listPlBid as list>
                        <tr class="data_list">
                            <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
                            <td align="center" bgcolor="#FAFBFD">${planPro.yearInterestRate}%</td>
                            <td align="center" bgcolor="#FAFBFD">${list.userMoney?string(',###.00')}</td>
                            <td align="center"
                                bgcolor="#FAFBFD"><#if (list.bidtime==null)><#else>${list.bidtime?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
                        </tr>
						   </#list>
							</#if>
                            </tbody>
                        </table>
                        <input type='hidden' id='current_page' />
                        <input type='hidden' id='show_per_page' />
                        <div id='page_navigation' class="3333333333"></div>
                    </div>
                </div>
			 </#if>
            </div>
        </ol>
       
       	 <ol>
       	 <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>
       	  <#assign sum1=0>
       	  <#assign sum2=0>
        <#list  slFundList as list>
          <#if list.afterMoney??>
            <#assign sum1=sum1 +list.afterMoney>
          </#if>
          <#if list.incomeMoney??>
            <#assign sum2=sum2 +list.incomeMoney>
          </#if>
        </#list>

            <!--<h2 class="title"><span>已还本息<#if sum1??>${sum1?string(",##0.00")}<#else>--</#if> 元</span> 待还本息<#if sum1?? && sum2??>${(sum2-sum1)?string(",##0.00")}<#else>--</#if>元</h2>-->
            <div class="tab">
            	<#--<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">-->
            	<table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                        <th width="150" height="40" align="center" bgcolor="#47B2D6">约定还款日期</th>
                        <th width="150" align="center" bgcolor="#47B2D6">状态</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还本息</th>
                        <th width="150" align="center" bgcolor="#47B2D6">应还罚息</th>
                         <th width="150" align="center" bgcolor="#47B2D6">实际还款日期</th>
                      </tr>
                      <#list slFundList as fundlist>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">${fundlist.intentDate}</td>
                        <td align="center" bgcolor="#FAFBFD">
                        	<#if (fundlist.notMoney=0)>
                        		已偿还
                        	<#else>
                        		未偿还
                        	</#if>
                        </td>
                        <td align="center" bgcolor="#FAFBFD">${fundlist.incomeMoney?string(',##0.00')}</td>
                        <td align="center" bgcolor="#FAFBFD">${fundlist.accrualMoney?string(',##0.00')}</td>
                        <td align="center" bgcolor="#FAFBFD"><#if (fundlist.factDate==null)>--<#else>${fundlist.factDate}</#if></td>
                      </tr>
                      </#list>
                </table>
            </div>
            </#if>
         </div>
       </ol>
       
      <ol>
       	 <div class="content">
       	 <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>     
          		 <h2 class="title" style="font-size: 18px;">持有债权人数<#if (bondListBid!=null)> ${bondListBid?size}<#else>0</#if>人</h2>
	            <div class="tab">
	            	<#--<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">-->
	            	<table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
	                       <tr>
	                        <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
	                        <th width="150" align="center" bgcolor="#47B2D6">债权人</th>
	                        <th width="150" align="center" bgcolor="#47B2D6">持有金额</th>
	                      </tr>
	                      <#if (bondListBid!=null)>
	                        <#list bondListBid as list>
	                        <#if (list_index<8)>
	                        <tr>
	                           <td height="40" align="center" bgcolor="#FAFBFD">${list_index+1}</td>
	                           <td align="center" bgcolor="#FAFBFD">${list.userName?substring(0,1)}****</td>
	                           <td align="center" bgcolor="#FAFBFD">${list.bondTotelMoney?string(',###.00')}</td>
	                        </tr>
	                        </#if>
	                        </#list>
	                        </#if>
	                </table>
	            </div>  
             </#if>
         </div> 
       </ol>
       
       <ol>
       	 <div class="content">    
       	  <#if Session.successHtml=='undefined'||Session.successHtml==null>
               <div style="text-align:center;">	<span><a href="${base}/htmllogin.do">登录</a>&nbsp;&nbsp;后可见</span></div>
             <#else>  
           <#-- <h2 class="title"><span>以交易总额0.00元</span> 待交易总额0.00元</h2>-->
           <#if (saleList!=null)>
            <div class="tab">
            	<#--<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">-->
            	<table width="100%" class="tab_css_one" border="0" align="center" cellpadding="0" cellspacing="0">
                       <tr>
                       <th width="81" height="40" align="center" bgcolor="#47B2D6">序号</th>
                        <th width="140" height="40" align="center" bgcolor="#47B2D6">债权买入者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">债权卖出者</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易金额</th>
                        <th width="140" align="center" bgcolor="#47B2D6">交易时间</th>
                      </tr>
                      <#list saleList as sale>
                      <#if (sale_index<8)>
                      <tr>
                        <td height="40" align="center" bgcolor="#FAFBFD">${sale_index+1}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.inCustName?substring(0,2)}****${sale.inCustName?substring(sale.inCustName?length-2)}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.outCustName?substring(0,2)}****${sale.outCustName?substring(sale.outCustName?length-2)}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.sumMoney?string(',###.00')}</td>
                        <td align="center" bgcolor="#FAFBFD">${sale.saleSuccessTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                      </tr>
                      </#if>
                      </#list>
                </table>
            </div>
            <#else>
            <p>无债权转让记录</p>  
            </#if>
            </#if>
         </div> 
       </ol>
        
	</div>
</div> <#--</#if>-->
</div>
<!-- end main --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 
<!-- 结束 只需要拿这个说明标签之内的部分 --> 

<!-- copyright -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">
</div>

 <input type="hidden" id="bidId" value="${plan.bidId}">
 <input type="hidden" id="bidName" value="${plan.bidProName}">
 <input type="hidden" id="hiddenMoney" value="${plan.afterMoney}"/>
</body>
</html>
