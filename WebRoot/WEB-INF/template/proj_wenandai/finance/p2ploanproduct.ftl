<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 我要借款</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我要借款,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要借款,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="我要借款",m2="",m3="";</script>
 <script type="text/javascript">
		$(function(){
			$(".showOrhidde").click(function(){				
				if($(this).parents(".info-list").find(".ui-list-disc").is(":visible")){
					$(this).parents(".info-list").find(".ui-list-disc").hide();
					$(this).text("点击加载可选材料说明").css("background","","color","#fff");
					$(this).parents(".worm").css("display","none");
				}else{	
					$(this).parents(".info-list").find(".ui-list-disc").show();
					$(this).text("点击收回>>>").css("background","","color","#fff").css("background","#CECECE","color","#fff");
				}
				
			});	
			
			$(".show").click(function(){				
				if($(this).parents(".info-list").find(".ui-list-disc").is(":visible")){
					$(this).parents(".info-list").find(".ui-list-disc").hide();
					$("#showOrhidde").text("点击加载可选材料说明").css("background","");
					$(this).parents(".worm").css("display","none");
				}else{	
					$(this).parents(".info-list").find(".ui-list-disc").show();
				}
				
			});	
		
		}); 
    </script>

</head>
<body>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<!-- 头部结束 -->
<!--start mian-->
<div style="background:#f2f2f2; width:100%;">
<div class="main">
<!--start 网商贷-->

	<div class="grid1">
	<#list productList as list>
    	 <h1 class="tit">${list.productName}<span class="big">(${list.userScope})</span></h1>
         <div class="info-list-condition">
             <h2 class="mb15">申请条件</h2>
             <ul class="ui-list-disc">
             	<#list list.conditionList as conditionList>
     	 				<li class="normal black"><i></i>${conditionList.conditionContent}</li>
     	 		</#list>
              </ul>
              <div class="btn">
              <a href="${base}/loan/creditInfoP2pLoanProduct.do?productId=${list.productId}" class="btnbg btnbg1">申请借款</a>	
			</div>
    	</div>
     </#list>	
         <div class="info-list-material">
			<h2 class="mb15">必要申请资料</h2>
			<div id="attestSlide">
            <ul class="ui-list-disc">  
            <#list productList as list>
            	<#list list.conditionMaterialList as conditionMaterialList>
            		<#if conditionMaterialList.conditionState==1>
			            	<li>
			 					<h5><span style="color:red">*</span>. ${conditionMaterialList.conditionContent}</h5>
			                    <div class="idcard-div">
			                    	
			                        <span data="1" class="card-icon" tabindex="-1">                                        
			                		 <a  href="${base}/${conditionMaterialList.imgUrl1}">
			                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png"  title="${conditionMaterialList.conditionContent}" alt="${conditionMaterialList.conditionContent}"/></a><br />
			                        
			                        <a  href="${base}/${conditionMaterialList.imgUrl2}">
			                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png" style="display:none"  title="${conditionMaterialList.conditionContent}" alt="${conditionMaterialList.conditionContent}"/></a>
			                        
			                        <b class="qxs">点击图标查看样例</b>
			                        </span>
			                       
			                        <span class="line" ></span>
			                      	<span data="2" class="card-icon1" tabindex="-1">                
				                        <b class="qxs">${conditionMaterialList.remark}</b>  
			                        </span>
			                        <!--  <span class="bg">同时提供</span> -->
			           	      	</div>
			                </li>
		               </#if>
		          </#list>
             </#list>
       		 </ul>

       		 <div class="worm-tips">
	           <span class="txt">温馨提示：除进行上述认证外，建议您进行房产、购车认证，有助于提高您的审核通过率与信用额度。</span>
	     	 </div>

          <div class="info-list-material info-list">
			<h2 class="mb15">可选认证资料</h2>         
       		 <div class="worm showOrhidde">
	           <a href="javascript:void(0);">点击加载可选材料说明</a>
	     	 </div>
	     	   <ul class="ui-list-disc"  style="display:none;">
	     	  <#list productList as list>
	     	  		<#list list.conditionMaterialList as conditionMaterialList>
	     	  			<#if conditionMaterialList.conditionState==2>
			            	<li>
			            		<h5><span style="color:blue">*</span>. ${conditionMaterialList.conditionContent}</h5>
			 					
			                    <div class="idcard-div idcard-div1">
			                    
			                    <span data="1" class="card-icon" tabindex="-1">                                        
		                		 <a  href="${base}/${conditionMaterialList.imgUrl1}">
		                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png"  title="${conditionMaterialList.conditionContent}" alt="${conditionMaterialList.conditionContent}"/></a><br />
		                        
		                        <a  href="${base}/${conditionMaterialList.imgUrl2}">
		                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png" style="display:none"  title="${conditionMaterialList.conditionContent}" alt="${conditionMaterialList.conditionContent}"/></a>
		                        
		                        <b class="qxs">点击图标查看样例</b>
		                        </span>
			                        
			                        <span class="line" ></span>
			                      	<span data="2" class="card-icon1" tabindex="-1">                
				                        <b class="qxs">${conditionMaterialList.remark}</b>  
			                        </span>
			                       <!--  <span class="bg">二选其一</span>-->
			           	      	</div>
			                </li>
			            </#if>
					</#list>
			  </#list>    
			  <!-- 
               <div class="worm1 show">
	           <a href="javascript:void(0);">点击收回>>></a>
	     	 </div>         
	     	-->
       		 </ul>
       		 
       		</div> 
         </div>       
    </div>
    
 <!--end 网商贷-->
 
 <!--start 借款方式-->
 
   	 <div class="info-list-way">
        <h2 class="mb15">借款方式1</h2>
        <#list productList as list>
	        <ul class="ui-list">
	             <li><span>借&nbsp;款&nbsp;额&nbsp;度&nbsp;</span>${list.loanStartMoney?string(",##0")}-${list.loanEndMoney?string(",##0")}&nbsp;&nbsp;元</li>
	             <li><span>借款年利率</span> <#list list.loanRateList?sort_by("yearAccrualRate") as loanRateList>
	             	<#if loanRateList_index==0>${loanRateList.yearAccrualRate}%-</#if><#if loanRateList_index+1==list.listSize>${loanRateList.yearAccrualRate}%</#if></#list></li>
	             <li><span>借&nbsp;款&nbsp;期&nbsp;限</span><#list list.loanRateList?sort_by("loanTime") as loanRateList>${loanRateList.loanTime}<#if loanRateList_index+1!=list.listSize>、</#if></#list> 个月</li>
	             <li><span>审&nbsp;核&nbsp;时&nbsp;间</span>${list.approveStartTime}-${list.approveEndTime}个工作日</li>
	             <li><span>还&nbsp;款&nbsp;方&nbsp;式</span>
	             		<#if list.accrualtype=="sameprincipalandInterest">等额本息</#if>
	             		<#if list.accrualtype=="sameprincipal">等额本金</#if>
	             		<#if list.accrualtype=="sameprincipalsameInterest">等本等息</#if>
	             		<#if list.accrualtype=="singleInterest">按期付息,到期还本</#if>
	             		<#if list.accrualtype=="otherMothod">其他还款方式</#if>
	             </li>
	             
	        </ul>
        </#list>
    </div>
    
 <!--end 借款方式-->
 
 
<!--start 费用说明-->
    <div class="info-list-fee">
                        <h2 class="mb15">费用说明</h2>
                        <ul class="ui-list">
                            <li class="range">
                               <h4>期初服务费<span style="display:inline-block;margin-left:23px;"><em class="wad_dknamemoney">贷款本金的
                               		<#list productList as list>
                               				<#list list.loanRateList?sort_by("yearFinanceServiceOfRate") as loanRateList>
                               					<#if loanRateList_index==0>${loanRateList.yearFinanceServiceOfRate}%-</#if>
                               					<#if loanRateList_index+1==list.listSize>${loanRateList.yearFinanceServiceOfRate}%</#if>
                               				</#list>
                               		</#list></em>（由升升投平台收取）</span></h4>
                                <!-- <h5>由升升投平台收取</h5> -->
                                <table width="70%" class="table" border="0" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr>
                                        <th>信用等级</th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s AA">AA</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s A">A</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s B">B</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s C">C</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s D">D</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s E">E</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s HR">HR</i></th>
                                    </tr>
                                    </thead>
                                </table>
                                <p class="wad_dengjitishi">等级越高,有助于您快速获得理财人的资金支持<a href="${base}/article/helpcontentArticle.do?catId=264">如何提升信用等级？</a></p>
                            </li>
                            <li class="info">
                                <h4>每月还款额</h4>
                                <ul>
                                    <li><span>本金及利息</span>支付给理财人</li>
                                    <li><span>借款管理费</span>由升升投平台收取，借款本金的<em>
                                    	<#list productList as list>
                                    		<#list list.loanRateList?sort_by("yearFinanceServiceOfRate") as loanRateList>
                                    			<#if loanRateList_index==0>${loanRateList.yearFinanceServiceOfRate}%-</#if>
                                    			<#if loanRateList_index+1==list.listSize>${loanRateList.yearFinanceServiceOfRate}%</#if>
                                    		</#list>
                                    	</#list></em></li>

                                </ul>
                            </li>
                        </ul>                        
                        <div class="review-button">
                        <#--<#if bpCustMember!=''>
                           <a href="${base}/loan/creditInfoP2pLoanProduct.do?productId=${list.productId}&flag=register" class="btnbg btnbg1">借款需求登记</a>	
                         </#if>-->
                        <#list productList as list>
                          <a href="${base}/loan/creditInfoP2pLoanProduct.do?productId=${list.productId}" class="ui-button">申请借款</a>
                         </#list>
                        </div>
                    </div>
                </div>
            </div>
      </div> 
 
<!--end 费用说明-->  
</div>
</div>
 
<!--start mian-->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
<div id="blueimp-gallery" class="blueimp-gallery" style="display: none;">
        <div class="slides" style="width: 5464px;"></div>
        <h3 class="title"></h3>
        <a class="prev">‹</a>
        <a class="next">›</a>
        <a class="close">×</a>
        <a class="play-pause"></a>
        <ol class="indicator"></ol>
    </div>
    
	<script type="text/javascript">
		var attestSlide = document.getElementById('attestSlide');
		attestSlide.onclick = function (event) {
            event = event || window.event;
            
            var target = event.target || event.srcElement,
                link = target.src ? target.parentNode : target,
                options = {index: link, event: event},
                links = this.getElementsByTagName('a');
                if(target.id!="weibo"&&target.id!="shouji"){
                	if(target.tagName=='IMG'){
		            	blueimp.Gallery(links, options);
		            }
                }
               
        };
	</script>
</body>
</html>