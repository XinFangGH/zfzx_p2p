<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 我要融资</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">

<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
<script type="text/javascript" src="${base}/js/user/authentication.js"></script>
<script type="text/javascript" src="${base}/js/Calendar3.js"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="";</script>
<script type="text/javascript">
var objesele = "";
$(function(){
	objesele = $.divselect(".divselectall");
	$("#relation").html();
	if($("input[name='bpCustMember.marry']:checked").val()==317){
		sele();
	}
	$("#317").click(function(){
		sele();
	});
	$("#318,#819,#820").click(function(){
		unsele();
	});
	
	 $("#idcard").fancybox();
	 $("#WebShop").fancybox();
	 $("#CompanyPlace").fancybox();
	 $("#Income").fancybox();
	// $("#CreditRecord").fancybox();
	$("a[rel=group]").fancybox();
	 $("#Teacher").fancybox();
	$("#Student").fancybox();
	/*function wad_gytupian() {
		    $("#idcard","#WebShop","#CompanyPlace","#Income","#CreditRecord","#Teacher").fancybox({ 
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
	    wad_gytupian();*/
	
	
	

});
function sele(){
	var chinese = '<span>配偶</span>	<input name="bpCustMember.relDirType" type="hidden" value="954" id="relDirType">'
	var marry = $("input[name='bpCustMember.marry']:checked").val();
	document.getElementById('relation').innerHTML='<li><a class="selt" href="javascript:;" selectid="954">配偶</a></li>';
	document.getElementById('relationChinese').innerHTML=chinese;
}

function unsele(){
var len = document.getElementById('relation').innerHTML=' <li><a class="selt" href="javascript:;" selectid="952">父母</a></li>'+
 														'<li><a class="selt" href="javascript:;" selectid="954">配偶</a></li>'+
														' <li><a class="selt" href="javascript:;" selectid="953">子女</a></li>';
}

function viwe(){
  	 jQuery.fancybox.open({
  				href: '../theme/proj_wenandai/images/samples/idcardpic.png',
                type: 'iframe',
                padding: 5,
                scrolling: 'no',
                fitToView: true,
                width: 610,
                height: 300,
                autoSize: false,
                closeClick: false});
	};
</script>

</head>
<body >

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<#import "/WEB-INF/template/${systemConfig.theme}/uploadMaterial/authentication.ftl" as authen>

<div style="width:100%; background:#eff3f3; overflow:hidden;">
<input type="hidden"  id="bpCustMemberId" value="${(bpCustMember.id)!}" />
<div class="main">
	<div style="margin-bottom:10px;"><span style="width:100px; height:30px; line-height:30px;background:#595757;padding:6px 10px; font-size:16px;color:#fff;text-align:center; margin-right:20px;">个人借款</span><span style="font-size:15px; color:#555;">${(financeApplyUser.productName)!}</span></div>
    <div class="img"><img src="${base}/theme/${systemConfig.theme}/images/process-bg.png" /></div>
    <div class="content">
    	<div class="leftsidebar">
        	<ul class="ui-list">
               <#list listApplyUser as menu>
               <#if (menu_index<3)>
              		<#if  financeApplyUser.currentnode==menu.nodeEng><li class="colr enable2"> <a href="javascript:void(0);">${(menu.nodeChina)!}</a> </li><#elseif menu.nodeEnable=='1'> <li class="colr enable${(menu.nodeEnable)!}"> <a href="javascript:void(0);">${(menu.nodeChina)!}</a> </li><#else><li class="colr enable${(menu.nodeEnable)!}">${(menu.nodeChina)!}</li></#if>
 				  	<#if menu.nodeEng!='nodeUpload'><li class="icon enable${(menu.nodeEnable)!}"></li></#if>
 				</#if>
               </#list>   
               <li class="colr enable2"> <a href="javascript:void(0);">补充材料</a> </li>
               <li class="icon enable1"></li>
               <li class="colr enable0"> <a href="javascript:void(0);">提交审核</a> </li>
            </ul>
        </div>
        <div class="rightlist">
        	<h2>${(financeApplyUser.productName)!}<span> 温馨提示：请完成最后一个步骤，进入发标流程。</span></h2>
      	<input type="hidden" id="currentnode" value="${(financeApplyUser.currentnode)!}">
		 
                  <div class="loanappinfo" id="div_credit">
           	 <form id="updateBpCustMemberFormupload" action="" method="post">
            <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
            <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <#--<input type="hidden" name="supplement" id="supplement" value="7">-->
                <div class="loanboder">          
                
                    <dl class="dl-list">
                     	<dd>                                                         
	         					<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="MobilePhone">
                        					<span class="CreditRecord updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">补充材料（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype />
		                        				<a rel="group" id="CreditRecord" class="veribtn1"title="联通" href="${base}/theme/${systemConfig.theme}/images/samples/UNICOM.png">查看样例</a>
		                        				<a rel="group" title="移动" href="${base}/theme/${systemConfig.theme}/images/samples/Mobile.png"></a>
		                        				<a rel="group" title="电信" href="${base}/theme/${systemConfig.theme}/images/samples/Telecom.jpg"></a>
		                        				<a rel="group" title="无导出解决方案" href="${base}/theme/${systemConfig.theme}/images/samples/solv.png"></a>
		                        				<br />补充材料报告需15日内开具
		                        			</span>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl> 
                    <div class="dl-list wad-dl-list">
						    <a href="${base}/financePurchase/downimgFinancePurchase.do?imgname=Mobile.png">移动样例下载</a>
						    <a href="${base}/financePurchase/downimgFinancePurchase.do?imgname=UNICOM.png">联通样例下载</a>
						    <a href="${base}/financePurchase/downimgFinancePurchase.do?imgname=Telecom.jpg">电信样例下载</a>
						    <a href="${base}/financePurchase/downimgFinancePurchase.do?imgname=solv.png">无导出解决方案下载</a>
					</div>
            <div class="btn-submit-div1">
            	<input id="checkUpload" type="button" class="ui-btn" value="提交审核"/>
            </div>
            </form>
            </div>
           
    	</div>
    </div>
</div>
</div>

<link rel="stylesheet" type="text/css" href="../theme/proj_wenandai/css/wad_fancybox.css" /> 
<script type="text/javascript" src="../js/jquery.fancybox-1.3.1.pack.js"></script>
<script>
function viwe(){
  	 $("#showdiv").fancybox({
			});
	};
</script>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

</body>
</html>