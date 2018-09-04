<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 我要融资</title>
    <meta name="description" content="${systemConfig.metaTitle} - 我要融资,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 我要融资,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

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
	$("#819,#820").click(function(){
		unsele();
	});
	$("#318").click(function(){
		unsele318();
	});
	
	
	
	 $("#idcard").fancybox();
	 $("#WebShop").fancybox();
	 $("#CompanyPlace").fancybox();
	 $("#Income").fancybox();
	 $("#CreditRecord").fancybox();
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
var len = document.getElementById('relation').innerHTML='<li><a class="selt" href="javascript:;" selectid="952">父亲</a></li>'+
														'<li><a class="selt" href="javascript:;" selectid="980">母亲</a></li>'+
														'<li><a class="selt" href="javascript:;" selectid="953">子女</a></li>';
}
function unsele318(){
var len = document.getElementById('relation').innerHTML='<li><a class="selt" href="javascript:;" selectid="952">父亲</a></li>'+
														'<li><a class="selt" href="javascript:;" selectid="980">母亲</a></li>';
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
<#--以下逻辑超神，请深呼吸后以平静的心态阅读；阅读过程中如有不适症状请及时就医。-->
<div style="width:100%; background:#eff3f3; overflow:hidden;">
<input type="hidden"  id="bpCustMemberId" value="${(bpCustMember.id)!}" />
<div class="main">
	<div style="margin-bottom:10px;"><span style="width:100px; height:30px; line-height:30px;background:#595757;padding:6px 10px; font-size:16px;color:#fff;text-align:center; margin-right:20px;">个人借款</span><span style="font-size:15px; color:#555;">${(financeApplyUser.productName)!}</span></div>
    <div class="img"><img src="${base}/theme/${systemConfig.theme}/images/process-bg.png" /></div>
    <div class="content">
    	<div class="leftsidebar">
        	<ul class="ui-list">
               <#list listApplyUser as menu>
              		<#if  financeApplyUser.currentnode==menu.nodeEng><li class="colr enable2"> <a href="${base}/user/getNodeMemBpCustMember.do?menuNode=${(menu.nodeEng)!}&loadid=${financeApplyUser.loanId}">${(menu.nodeChina)!}</a> </li><#elseif menu.nodeEnable=='1'> <li class="colr enable${(menu.nodeEnable)!}"> <a href="${base}/user/getNodeMemBpCustMember.do?menuNode=${(menu.nodeEng)!}&loadid=${financeApplyUser.loanId}">${(menu.nodeChina)!}</a> </li><#else><li class="colr enable${(menu.nodeEnable)!}">${(menu.nodeChina)!}</li></#if>
 				  	<#if menu.nodeEng!='nodeUpload'><li class="enable${(menu.nodeEnable)!}"></li></#if>
               </#list>   
            </ul>
        </div>
        <div class="rightlist">
        	<h2>${(financeApplyUser.productName)!}<span> 温馨提示：我们将在您的必要认证资料上传齐全后为您提交审核。</span></h2>
      	<input type="hidden" id="currentnode" value="${(financeApplyUser.currentnode)!}">

       <#if (financeApplyUser.currentnode=='nodePerson')>
        <div class="loanappinfo" id="div_personal_information">
             <form id="updateBpCustMemberFormPeople" action="${base}/user/getNodeMemBpCustMember.do" method="post" onsubmit="return peopleCheck()">
              <input type="hidden" name="bpCustMember.id" value="${(bpCustMember.id)!}" />
               <input type="hidden" name="nowNode" value="${financeApplyUser.currentnode}" />
              <#--<input type="hidden" name="cardType" id="show" value="IDCard" />-->
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <div class="loanboder">
            		<dl class="dl-list">
                    	<dt><span class="star">*</span>真实姓名</dt>
                        <dd>${(bpCustMember.truename)!}</dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>身份证号${(webFinanceApplyUploads.state)!}</dt>
                        <dd><#if bpCustMember.cardcode!=null>${bpCustMember.cardcode?substring(0,4)}****${bpCustMember.cardcode?substring(bpCustMember.cardcode?length-4)}<#else></#if></dd>
                        <dd>                     
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="IDCard">
                        					<span class="IDCard updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">身份认证（必要认证）<br />
		                        				<@authen.material state=list.status  materialstype=list.materialstype />
		                        				<a id="idcard" class="veribtn1"title="身份证正反面" href="${base}/theme/${systemConfig.theme}/images/samples/idcardpic.png">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
	                        	
                        </dd>    
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>手机号码</dt>
                        <dd><#if bpCustMember.telphone!=null>${bpCustMember.telphone?substring(0,4)}****${bpCustMember.telphone?substring(7)}<#else></#if></dd>
                    </dl>
                    <#if financeApplyUser.productId==16><#else> 
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>出生日期</dt>
                        <dd><#if bpCustMember.birthday??>${(bpCustMember.birthday)!}</#if></dd>
                    </dl>
                    </#if>
                   <#if financeApplyUser.productId==16><#else> 
                   
                   <dl class="dl-list">
                    	<dt><span class="star">*</span>最高学历</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listDgree??><#list listDgree as list><#if bpCustMember.collegeDegree==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.collegeDegree" type="hidden" value="${bpCustMember.collegeDegree!}" id="collegeDegree"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listDgree??>
						      	<#list listDgree as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								</#list></#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl></#if>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>入学年份</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span>${(bpCustMember.collegeYear)!}年</span>
			  				<input name="bpCustMember.collegeYear" type="hidden" value="${bpCustMember.collegeYear}"  id="collegeYear"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#list listDate as list>
						      		<li><a class="selt" href="javascript:;" selectid="${list}">${list}</a></li>
						      	</#list>
								 
						      </ul>
						      <#else></#if>
						  	</div>
						  
						  <#--<input type="text" id="purchase_time_start" style="width:80px" name="bpCustMember.collegeYear" class="colorful1" readonly="readonly" value="${bpCustMember.collegeYear!}" onclick="new Calendar().show(this);"/>-->
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span><#if financeApplyUser.productId==16>就读院校<#else>毕业学院</#if></dt>
                        <dd>
                        	<input type="text" name="bpCustMember.collegename" <#if isPass==null><#else>disabled="true"</#if> id="collegename" value="${(bpCustMember.collegename)!}"  maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                  <#if financeApplyUser.productId==16><#else>  <dl class="dl-list">
                    	<dt><span class="star">*</span>籍贯</dt>
                        <dd>
                        	<div class="divselectall" style="float:left; width:120px; margin-right:15px;" >
						      <cite class="select-bg"><span><#if csDicAreaNativePlaceProvice??>${csDicAreaNativePlaceProvice.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input type="hidden" name="bpCustMember.nativePlaceProvice" value="<#if csDicAreaNativePlaceProvice??>${csDicAreaNativePlaceProvice.id}</#if>" /></cite>
			  					<#if isPass==null>
						      <ul style="width:94px;">
								<#if listArea6591??>
						      	<#list listArea6591 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                            <div class="divselectall" style="float:left; width:120px;" >
						      <cite class="select-bg"><span><#if csDicAreaNativePlaceCity??>${csDicAreaNativePlaceCity.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.nativePlaceCity" type="hidden" value="<#if csDicAreaNativePlaceCity??>${csDicAreaNativePlaceCity.id}</#if>"  id="nativePlaceCity"/></cite>
			  					<#if isPass==null>
						      <ul style="width:191px;">
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl></#if>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>户口所在地</dt>
                        <dd>
                        	<div class="divselectall" style="float:left; width:120px; margin-right:15px;" >
						      <cite class="select-bg"><span><#if csDicAreaLiveProvice??>${csDicAreaLiveProvice.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input  name="bpCustMember.liveProvice" type="hidden" value="<#if csDicAreaLiveProvice??>${csDicAreaLiveProvice.id}</#if>" /></cite>
			  				<#if isPass==null>
						      <ul style="width:94px;">
						      	<#if listArea6591??>
						      	<#list listArea6591 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                            <div class="divselectall" style="float:left; width:120px;" >
						      <cite class="select-bg"><span><#if csDicAreaLiveCity??>${csDicAreaLiveCity.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.liveCity" type="hidden" value="<#if csDicAreaLiveCity??>${csDicAreaLiveCity.id}</#if>" id="liveCity"/></cite>
			  				<#if isPass==null>
						      <ul style="width:191px;">
								
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>现居住地址</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.relationAddress" <#if isPass==null><#else>disabled="true"</#if> id="relationAddress" value="${(bpCustMember.relationAddress)!}" maxlength="30" size="32" class="colorful1" placeholder="例如，北一区3号楼317室"/>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>现居住地邮编</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.postCode" id="postCode" <#if isPass==null><#else>disabled="true"</#if>  value="${(bpCustMember.postCode)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt>居住地电话</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.homePhonePrefix" <#if isPass==null><#else>disabled="true"</#if> id="home_PhonePrefix" value="${bpCustMember.homePhonePrefix}" maxlength="30" size="32" class="colorful1" style="width:50px;" /> -
                            <input type="text" name="bpCustMember.homePhoneSuffix" <#if isPass==null><#else>disabled="true"</#if> id="home_PhoneSuffix" value="${bpCustMember.homePhoneSuffix}" maxlength="30" size="32" class="colorful1" style="width:134px;" />
                        </dd>
                    </dl>	
           		</div>
            <div class="btn-submit-div1">
            	<input id="updateBpCustMemberPeople" type="submit"  class="ui-btn" value="保存并继续"/>
            </div>
            </form>
          </div>
		</#if>	

		 <#if (financeApplyUser.currentnode=='nodeFamily')>
			<div class="loanappinfo" id="div_Family">
	      	   <form id="updateBpCustMemberFormfamily" action="${base}/user/getNodeMemBpCustMember.do" method="post" onsubmit="return familyCheck()">
	      	      
	      	  	<input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <div class="loanboder">
            	<#if financeApplyUser.productId==16||financeApplyUser.productName=='青春贷'><#else>	<dl class="dl-list">
                    	<dt><span class="star">*</span>婚姻状况</dt>
                        <dd>
                        <#if listMarry??>
                        <#list listMarry as list>
                        <span class="check"><input <#if isPass==null><#else>disabled="true"</#if> type="radio" name="bpCustMember.marry"  <#if bpCustMember.marry==list.dicId> checked="checked"</#if>  value="${list.dicId}" id="${list.dicId}" <#if list.dicId=='317'>checked="checked"</#if> /> ${list.itemValue}</span>
                       	</#list>
                        </#if>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>有无子女</dt>
                        <dd>
                        <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havechildren"  <#if bpCustMember.havechildren=='1'> checked="checked"</#if> value="1" checked="checked"/> 有</span>
                        <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havechildren" <#if bpCustMember.havechildren=='0'> checked="checked"</#if> value="0"/> 无</span>

                        </dd>
                    </dl>
                   
                    
                    </#if>
            	</div>
            	<div class="loanboder">
            	<h4><#if financeApplyUser.productId==16>父母<#else>直系亲属</#if></h4>
                	<dl class="dl-list">
                    	<dt><span class="star">*</span>姓名</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.relDirName" <#if isPass==null><#else>disabled="true"</#if> id="relDirName" value="${(bpCustMember.relDirName)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                 	<dl class="dl-list">
                    	<dt><span class="star">*</span>关系</dt>
                        <dd>
                        	<div class="divselectall" >
						      <#--<cite><span><#if listGxrgx??><#list listGxrgx as list><#if bpCustMember.relDirType==list.dicId>${list.itemValue}</#if></#list></#if></span>
			  				<input name="bpCustMember.relDirType" type="hidden" value="${bpCustMember.relDirType!}"  id="relDirType"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listGxrgx??>
						      	<#list listGxrgx as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								</#if>
						      </ul>
						      <#else></#if>-->
						     <cite id="relationChinese">
						     <span>
							     <#if financeApplyUser.productId==16>
								     <#if bpCustMember.relDirType=='952'>父亲</#if>
								     <#if bpCustMember.relDirType=='980'>母亲</#if>
							     <#else>
								     <#if bpCustMember.relDirType=='952'>父亲</#if>
								     <#if bpCustMember.relDirType=='980'>母亲</#if>
								     <#if bpCustMember.relDirType=='953'>子女</#if>
								     <#if bpCustMember.relDirType=='954'>配偶</#if>
							     </#if>
						     </span>
			  				<#if bpCustMember.relDirType==null>
			  				<input name="bpCustMember.relDirType" type="hidden" value="952"  id="relDirType"/></cite>
			  				<#else>
			  				<input name="bpCustMember.relDirType" type="hidden" value="${bpCustMember.relDirType!}"  id="relDirType"/></cite>
			  				</#if>
			  				
			  				<#if isPass==null>
			  				<#if financeApplyUser.productId==16>
			  				  <ul id="relation">
								 <li><a class="selt" href="javascript:;" selectid="952">父亲</a></li>
								 <li><a class="selt" href="javascript:;" selectid="980">母亲</a></li>
						      </ul>
			  				<#else>
						      <ul id="relation">
								 <li><a class="selt" href="javascript:;" selectid="952">父亲</a></li>
								 <li><a class="selt" href="javascript:;" selectid="980">母亲</a></li>
								 <li><a class="selt" href="javascript:;" selectid="954">配偶</a></li>
								 <li><a class="selt" href="javascript:;" selectid="953">子女</a></li>
						      </ul>
						      </#if>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>手机</dt>
                        <dd>
                        	<input type="text"  name="bpCustMember.relDirPhone" <#if isPass==null><#else>disabled="true"</#if>  id="relDirPhone" value="${(bpCustMember.relDirPhone)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
           		</div>
                <div class="loanboder">
            	<h4>其他亲属</h4>
                <dl class="dl-list">
                    	<dt><span class="star">*</span>姓名</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.relOtherName" <#if isPass==null><#else>disabled="true"</#if> id="relOtherName" value="${(bpCustMember.relOtherName)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                 <dl class="dl-list">
                    	<dt><span class="star">*</span>关系</dt>
                        
                        <#if financeApplyUser.productId!=16>
                        <dd>
                        	<div class="divselectall" >
                        	<span>亲属</span>
                        	<input name="bpCustMember.relOtherType" type="hidden" value="575"/>
						    
						  </div>
                        </dd>
                        
                        <#else>
                        
                        <dd>
                        	<div class="divselectall" >
						      <#--<cite><span><#if listGxrgx??><#list listGxrgx as list><#if bpCustMember.relFriendType==list.dicId>${list.itemValue}</#if></#list></#if></span>
			  				<input name="bpCustMember.relFriendType" type="hidden" value="${bpCustMember.relFriendType!}"  id="relDirType"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listGxrgx??>
						      	<#list listGxrgx as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								</#if>
						      </ul>
						      <#else></#if>-->
						      <cite><span><#if bpCustMember.relOtherType=='952'>父亲</#if><#if bpCustMember.relOtherType=='980'>母亲</#if><#if bpCustMember.relOtherType=='575'>其他亲属</#if></span>
			  				<input name="bpCustMember.relOtherType" type="hidden" value="${bpCustMember.relOtherType!}"  id="relFriendType"/></cite>
			  				<#if isPass==null>
						      <ul>
								 <li><a class="selt" href="javascript:;" selectid="952">父亲</a></li>
								 <li><a class="selt" href="javascript:;" selectid="980">母亲</a></li>
								 <li><a class="selt" href="javascript:;" selectid="575">其他亲属（单亲家庭请选此项）</a></li>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                        
                        </#if>
                        
                        
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>手机</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.relOtherPhone" <#if isPass==null><#else>disabled="true"</#if> id="relOtherPhone" value="${(bpCustMember.relOtherPhone)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
           		</div>
                <div class="loanboder">
            	<h4>其他联系人<span class="other">可以为朋友、同学、同事等联系人</span></h4>
                <dl class="dl-list">
                    	<dt><span class="star">*</span>姓名</dt>
                        <dd>
                        	<input type="text" name="bpCustMember.relFriendName" <#if isPass==null><#else>disabled="true"</#if> id="relFriendName" value="${(bpCustMember.relFriendName)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                 <dl class="dl-list">
                    	<dt><span class="star">*</span>关系</dt>
                        <dd>
                        	<div class="divselectall" >
						      <#--<cite><span><#if listGxrgx??><#list listGxrgx as list><#if bpCustMember.relFriendType==list.dicId>${list.itemValue}</#if></#list></#if></span>
			  				<input name="bpCustMember.relFriendType" type="hidden" value="${bpCustMember.relFriendType!}"  id="relDirType"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listGxrgx??>
						      	<#list listGxrgx as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								</#if>
						      </ul>
						      <#else></#if>-->
						      <cite><span><#if bpCustMember.relFriendType=='577'>同事</#if><#if bpCustMember.relFriendType=='955'>同学</#if><#if bpCustMember.relFriendType=='576'>朋友</#if></span>
			  				<input name="bpCustMember.relFriendType" type="hidden" value="${bpCustMember.relFriendType!}"  id="relFriendType"/></cite>
			  				<#if isPass==null>
						      <ul>
								 <li><a class="selt" href="javascript:;" selectid="577">同事</a></li>
								 <li><a class="selt" href="javascript:;" selectid="955">同学</a></li>
								 <li><a class="selt" href="javascript:;" selectid="576">朋友</a></li>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>手机</dt>
                        <dd>
                        	<input type="text"  name="bpCustMember.relFriendPhone" <#if isPass==null><#else>disabled="true"</#if> id="relFriendPhone" value="${(bpCustMember.relFriendPhone)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                    
                     <!-- 流程修改begin  -->
                    <#if financeApplyUser.productId==16||financeApplyUser.productName=='青春贷'><#else>
                    <dl class="dl-list">
                            <dt><span class="star">*</span>是否有房</dt>
                            <dd>
                           	 <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse"  <#if bpCustMember.havehouse=='1'> checked="checked" </#if>  value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse" <#if bpCustMember.havehouse=='0'> checked="checked" </#if>  value="0" />无</span>
                            </dd>
                        </dl>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>有无房贷</dt>
                            <dd>

                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouseloan"  <#if bpCustMember.havehouseloan=='1'> checked="checked" </#if>  value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouseloan" <#if bpCustMember.havehouseloan=='0'> checked="checked" </#if>  value="0" />无</span>
                            </dd>
                        </dl>
                    
                    
                    <dl class="dl-list">
                            <dt><span class="star">*</span>是否有车</dt>
                            <dd>
                             <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecar"  <#if bpCustMember.havecar=='1'> checked="checked" </#if> value="1" checked="checked"/> 有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecar"  <#if bpCustMember.havecar=='0'> checked="checked" </#if> value="0" /> 无</span>
                            </dd>
                        </dl>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>有无车贷</dt>
                            <dd>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecarloan"  <#if bpCustMember.havecarloan=='1'> checked="checked" </#if> value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecarloan"  <#if bpCustMember.havecarloan=='0'> checked="checked" </#if> value="0" /> 无</span>
                            </dd>
                        </dl>
                    </#if>
                   <!-- 流程修改end  -->
           		</div>
            <div class="btn-submit-div1">
            	<input id="updateBpCustMemberfamily" type="submit" class="ui-btn" value="保存并继续"/>
            </div>
            </form>
            </div>
			</#if>

		 <#if financeApplyUser.currentnode=='nodeWebshop'>
			 <div class="loanappinfo" id="div_TheNetwork">
	            <form id="updateBpCustMemberFormshop" action="${base}/user/getNodeMemBpCustMember.do" method="post"  onsubmit="return theNetWorkCheck()">
	           	
	            <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <div class="loanboder">
            		<dl class="dl-list">
                    	<dt><span class="star">*</span>职业状态</dt>
                        <dd>网商</dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>网店店铺名称</dt>
                        <dd><input type="text" name="bpCustMember.webshopName" <#if isPass==null><#else>disabled="true"</#if> id="webshopName"  value="${(bpCustMember.webshopName)!}" placeholder="请输入淘宝网店名称" maxlength="30" size="32" class="colorful1" /></dd>
                        <dd>                
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="WebShop">
                        					<span class="WebShop updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">网店认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="WebShop" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/WebShop.png">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl>
                    
                   <#--  <dl class="dl-list">
                    	<dt><span class="star">*</span>月收入</dt>
                        <dd><input type="text"  name="bpCustMember.webshopMonthlyincome" id="webshopMonthlyincome"  value="${(bpCustMember.webshopMonthlyincome)!}" maxlength="30" size="32" class="colorful1" /></dd>
                    </dl>-->
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>工作邮箱</dt>
                        <dd><input type="text"  name="bpCustMember.webshopEmail" <#if isPass==null><#else>disabled="true"</#if> id="webshopEmail"  value="${(bpCustMember.webshopEmail)!}" maxlength="30" size="32" class="colorful1" /></dd>
                    </dl>
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司城市</dt>
                        <dd>
                        	<div class="divselectall" style="float:left; width:120px; margin-right:15px;" >
						      <cite class="select-bg"><span><#if csDicAreaHireProvince??>${csDicAreaHireProvince.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireProvince" type="hidden" value="<#if csDicAreaHireProvince??>${csDicAreaHireProvince.id}</#if>" /></cite>
			  				<#if isPass==null>
						      <ul style="width:150px;">
								 <#if listArea6591??>
						      	<#list listArea6591 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								 </#if>
						       
						      </ul>
						      <#else></#if>
						  </div>
                            <div class="divselectall" style="float:left; width:120px;" >
						      <cite class="select-bg"><span><#if csDicAreaHireCity??>${csDicAreaHireCity.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCity" type="hidden" value="<#if csDicAreaHireCity??>${csDicAreaHireCity.id}</#if>" /></cite>
			  				<#if isPass==null>
						      <ul style="width:150px;">
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>网店店铺地址</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.webshopAddress" id="webshopAddress"  value="${(bpCustMember.webshopAddress)!}" maxlength="30"  placeholder="请输入淘宝网店地址"size="32" class="colorful1" />
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>网站经营年限</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span>${(bpCustMember.webshopStartyear)!}年</span>
			  				<input name="bpCustMember.webshopStartyear" type="hidden" value="${(bpCustMember.webshopStartyear)!}" id="webshopStartyear"/></cite>
			  				<#if isPass==null>
						      <ul>
								 
						      		<li><a class="selt" href="javascript:;" selectid="1年">1年</a></li>
						      		<li><a class="selt" href="javascript:;" selectid="2年">2年</a></li>
						      		<li><a class="selt" href="javascript:;" selectid="3年">3年</a></li>
						      		<li><a class="selt" href="javascript:;" selectid="4年">4年</a></li>
						      		<li><a class="selt" href="javascript:;" selectid="5年">5年</a></li>
						      		<li><a class="selt" href="javascript:;" selectid="5年以上">5年以上</a></li>
						      	
						      </ul>
						      <#else></#if>
						 	 </div>
                        </dd>
                    </dl>
                    
                   
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>网店电话</dt>
                        <dd>
                        	<#-- <#if (bpCustMember!=null)><#if (bpCustMember.webshopPhone!=null)>${bpCustMember.webshopPhone?substring(0,3)}<#else></#if></#if>-->
                        	<#-- <#if (bpCustMember!=null)><#if (bpCustMember.webshopPhone!=null)>${bpCustMember.webshopPhone?substring(3,bpCustMember.webshopPhone?length)}<#else></#if></#if>-->
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.webshopPhonePrefix" id="webshop_PhonePrefix" value="${(bpCustMember.webshopPhonePrefix)!}" maxlength="30" size="32" class="colorful1" style="width:50px;" /> -
                            <input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.webshopPhoneSuffix" id="webshop_PhoneSuffix" value="${(bpCustMember.webshopPhoneSuffix)!}"  maxlength="30" size="32" class="colorful1" style="width:148px;" />
                        </dd>
                    </dl>
            				
           		</div>
            <div class="btn-submit-div1">
            	<input id="updateBpCustMembershop" type="submit" class="ui-btn" value="保存并继续"/>
            </div>
            </form>
            </div>
	</#if>

	 <#if (financeApplyUser.currentnode=='nodeCareer')>	
			<div class="loanappinfo" id="div_Work">
	           <form id="updateBpCustMemberFormwork" action="${base}/user/getNodeMemBpCustMember.do" method="post"  onsubmit="return workCheck()">
	          	 
	          	  <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <div class="loanboder">
            		<dl class="dl-list">
                    	<dt><span class="star">*</span>职业状态</dt>
                        <dd>私营企业主</dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司名称</dt>
                        <dd><input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossCompanyname" id="hireCompanyname"  value="${(bpCustMember.bossCompanyname)!}" maxlength="30" size="32" class="colorful1" /></dd>
                        <dd>                                          
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="CompanyPlace">
                        					<span class="CompanyPlace updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">经营场所认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="CompanyPlace" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/businesslisence.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl>
                    <dl class="dl-list">

                    	<dt><span class="star">*</span>职位</dt>
                        <dd>
                        <div class="divselectall" >
						      <cite><span><#if listJob??><#list listJob as list><#if bpCustMember.bossPosition==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossPosition" type="hidden" value="${bpCustMember.bossPosition!}" id="hirePosition"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listJob??>
						      	<#list listJob as list>
								  <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>月收入</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossMonthlyincome" id="hireMonthlyincome"  value="${(bpCustMember.bossMonthlyincome)!}" maxlength="30" size="32" class="colorful1" chkLoanMoney(this);funFormat(this)/>
                        </dd>
                        <dd>                                                                      
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Income">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">收入认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="Income" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/bankwater.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>   
                    </dl>
                    
                    <!--流程变更 begin-->
                    
                    <dl class="dl-list">
                     	<dd>                                                         
	         					<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="CreditRecord">
                        					<span class="CreditRecord updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">信用认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype />
		                        				<a id="CreditRecord" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/creditreport.jpg">查看样例</a>
		                        				<br />个人信用报告需15日内开具
		                        			</span>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl> 
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>工作邮箱</dt>
                        <dd><input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossEmail" id="hireEmail"  value="${(bpCustMember.bossEmail)!}" maxlength="30" size="32" class="colorful1" /></dd>
                    </dl>
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>工作11城市</dt>
                        <dd>
                        	<div class="divselectall" style="float:left; width:120px; margin-right:15px;" >
						      <cite class="select-bg"><span><#if csDicArea??>${csDicArea.parentTitle}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input  type="hidden" value="<#if csDicArea??>${csDicArea.parentTitleId}</#if>" /></cite>
			  				<#if isPass==null>
						      <ul style="width:94px;">
								 <#if listArea6591??>
						      	<#list listArea6591 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                            <div class="divselectall" style="float:left; width:120px;" >
						      <cite class="select-bg"><span><#if csDicArea??>${csDicArea.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossCity" type="hidden" value="${bpCustMember.bossCity!}" /></cite>
			  				<#if isPass==null>
						      <ul style="width:191px;">
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司地址</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossAddress" id="hireAddress"  value="${(bpCustMember.bossAddress)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司类别123</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listUnitp??><#list listUnitp as list><#if bpCustMember.bossCompanytype==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossCompanytype" type="hidden" value="${bpCustMember.bossCompanytype!}" id="hireCompanytype"/></cite>
			  				<#if isPass==null>
						      <ul>
								 <#if listUnitp??>
						      	<#list listUnitp as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>公司行业</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listArea10092??><#list listArea10092 as area><#if bpCustMember.bossCompanycategory==area.id>${area.title}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossCompanycategory" type="hidden" value="${bpCustMember.bossCompanycategory!}" id="hireCompanycategory"/></cite>
			  				<#if isPass==null>
						      <ul>
								 <#if listArea10092??>
						      	<#list listArea10092 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								</#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>公司规模</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listCompanysize??><#list listCompanysize as comsize><#if bpCustMember.bossCompanysize==comsize.dicId>${comsize.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossCompanysize" type="hidden" value="${bpCustMember.bossCompanysize!}" id="hireCompanysize"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listCompanysize??>
						      	<#list listCompanysize as comsize>
								 <li><a class="selt" href="javascript:;" selectid="${comsize.dicId}">${comsize.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
						</dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>工作起始日期</dt>
                        <dd>
                        	<input type="text"  id="purchase_time_start" <#if isPass==null><#else>disabled="true"</#if> style="width:80px" name="bpCustMember.bossStartyear" class="colorful1" readonly="readonly" value="${bpCustMember.bossStartyear!}" onclick="new Calendar().show(this);"/>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司电话</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name ="bpCustMember.hireCompanyphonePrefix" id="hireCompanyphone_Prefix"  value="${bpCustMember.hireCompanyphonePrefix}" maxlength="30" size="32" class="colorful1" style="width:50px;" /> -
                            <input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireCompanyphoneSuffix" id="hireCompanyphone_Suffix"  value="${bpCustMember.hireCompanyphoneSuffix}"  maxlength="30" size="32" class="colorful1" style="width:134px;" />
                        </dd>
                    </dl>		
           		</div>
            <div class="btn-submit-div1">
            	<input id="updateBpCustMemberwork" type="submit" class="ui-btn" value="保存并继续"/>
            </div>
            </form>
            </div>
		</#if>

	<#if (financeApplyUser.currentnode=='nodeCompany')>	
		<div class="loanappinfo" id="div_Company">
	           <form id="updateBpCustMemberFormwork" action="${base}/user/getNodeMemBpCustMember.do" method="post"  onsubmit="return workCheck()">
	          	  
	          	  <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                <div class="loanboder">
            		<dl class="dl-list">
                    	<dt><span class="star">*</span>职业状态</dt>
                        <dd><#if financeApplyUser.productId=='15'>教师及相关单位<#else>工薪阶层</#if></dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>单位名称</dt>
                        <dd>
                        	<#if financeApplyUser.productId!='15'>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireCompanyname" id="hireCompanyname"  value="${(bpCustMember.hireCompanyname)!}" maxlength="30" size="32" class="colorful1" />
                       		<#else>
                       			<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherCompanyname" id="hireCompanyname"  value="${(bpCustMember.teacherCompanyname)!}" maxlength="30" size="32" class="colorful1" />
                       		</#if>
                        </dd>
                    </dl>
                    <dl class="dl-list">

                    	<dt><span class="star">*</span>职位</dt>
                        <dd>
                        	<#if financeApplyUser.productId!='15'>
	                        	 <div class="divselectall" >
							      <cite><span><#if listJob??><#list listJob as list><#if bpCustMember.hirePosition==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
				  				<input name="bpCustMember.hirePosition" type="hidden" value="${bpCustMember.hirePosition!}" id="hirePosition"/></cite>
				  				<#if isPass==null>
							      <ul>
							      	<#if listJob??>
							      	<#list listJob as list>
									  <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
									 </#list>
									 </#if>
							      </ul>
							      <#else></#if>
							  	</div>
						  </#if>
						  <#if financeApplyUser.productId=='15'>
	                        	 <div class="divselectall" >
							      <cite><span><#if bpCustMember.teacherPosition=='956'>一线老师</#if><#if bpCustMember.teacherPosition=='957'>二线工勤</#if><#if bpCustMember.teacherPosition=='958'>管理研究</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
				  				<input name="bpCustMember.teacherPosition" type="hidden" value="${bpCustMember.teacherPosition!}" id="hirePosition"/></cite>
							      <ul>
									  <li><a class="selt" href="javascript:;" selectid="956">一线老师</a></li>
									  <li><a class="selt" href="javascript:;" selectid="957">二线工勤</a></li>
									   <li><a class="selt" href="javascript:;" selectid="958">管理研究</a></li>
							      </ul>
							  	</div>
						  </#if>
                        </dd>
                    	<#if financeApplyUser.productId=='15'>
                    		<dd>                                                                      
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Teacher">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">教师资格认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="Teacher" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/teacher1.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        	</dd> 
                       </#if>  
                    </dl>
                    
                    
                    
                    <dl class="dl-list">
                     	<dd>                                                         
	         					<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="CreditRecord">
                        					<span class="CreditRecord updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">信用认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype />
		                        				<a id="CreditRecord" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/creditreport.jpg">查看样例</a>
		                        				<br />个人信用报告需15日内开具
		                        			</span>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl> 
                    
                    
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>月收入</dt>
                        <dd>
                        	<#if financeApplyUser.productId=='15'>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherMonthlyincome" id="hireMonthlyincome"  value="${(bpCustMember.teacherMonthlyincome)!}" maxlength="30" size="32" class="colorful1" chkLoanMoney(this);funFormat(this)/>
                        	<#else>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireMonthlyincome" id="hireMonthlyincome"  value="${(bpCustMember.hireMonthlyincome)!}" maxlength="30" size="32" class="colorful1" chkLoanMoney(this);funFormat(this)/>
                        	</#if>
                        	
                        </dd>
                        <dd>                                                                      
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Income">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">收入认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="Income" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/bankwater.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>   
                    </dl>
                    
                    <!--薪资贷流程变更-->
                    
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>工作邮箱</dt>
                        <dd>
                        	<#if financeApplyUser.productId=='15'>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherEmail" id="hireEmail"  value="${(bpCustMember.teacherEmail)!}" maxlength="30" size="32" class="colorful1" />
                        	<#else>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireEmail" id="hireEmail"  value="${(bpCustMember.hireEmail)!}" maxlength="30" size="32" class="colorful1" />
                       		</#if>
                        </dd>
                    </dl>
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>工作城市</dt>
                        <dd>
                        	<div class="divselectall"  style="float:left; width:120px; margin-right:15px;" >
						      <cite class="select-bg" ><span><#if csDicAreaHireProvince??>${csDicAreaHireProvince.title}</#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<#if financeApplyUser.productId=='15'>
			  				 <input name="bpCustMember.hireProvince" type="hidden"  value="<#if csDicAreaHireProvince??>${csDicAreaHireProvince.id}</#if>" />
			  				<#else>
			  				 <input name="bpCustMember.hireProvince" type="hidden"  value="<#if csDicAreaHireProvince??>${csDicAreaHireProvince.id}</#if>" />
			  				</#if>
			  				</cite>
			  				<#if isPass==null>
						      <ul style="width:94px;" >
						      	<#if listArea6591??>
						      	<#list listArea6591 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                            <div class="divselectall"  style="float:left; width:120px;" >
						      <cite class="select-bg"><span><#if financeApplyUser.productId=='15'><#if csDicAreaHireCity??>${csDicAreaHireCity.title}</#if><#else><#if csDicAreaHireCity??>${csDicAreaHireCity.title}</#if></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  					<#if financeApplyUser.productId=='15'><!-- bpCustMember.teacherCity-->
			  						<input name="bpCustMember.hireCity" type="hidden"  value="<#if csDicAreaHireCity??>${csDicAreaHireCity.id}</#if>" />
			  					<#else>
			  						<input name="bpCustMember.hireCity" type="hidden"  value="<#if csDicAreaHireCity??>${csDicAreaHireCity.id}</#if>" />
			  					</#if>
			  				  </cite>
			  				  <#if isPass==null>
						      <ul style="width:191px;"></ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span><#if financeApplyUser.productId=='15'>单位地址 <#else>公司地址 </#if></dt>
                        <dd>
                        	<#if financeApplyUser.productId!='15'>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireAddress" id="hireAddress"  value="${(bpCustMember.hireAddress)!}" maxlength="30" size="32" class="colorful1" />
                        	<#else>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherAddress" id="hireAddress"  value="${(bpCustMember.teacherAddress)!}" maxlength="30" size="32" class="colorful1" />
                        	</#if>
                        </dd>
                    </dl>
                    <#if financeApplyUser.productId!=15>
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司类别</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listUnitp??><#list listUnitp as list><#if bpCustMember.hireCompanytype==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCompanytype" type="hidden" value="${bpCustMember.hireCompanytype!}" /></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listUnitp??>
						      	<#list listUnitp as list>
								 <li><a class="selt" href="javascript:;" selectid="${list.dicId}">${list.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>公司行业</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listArea10092??><#list listArea10092 as area><#if bpCustMember.hireCompanycategory==area.id>${area.title}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCompanycategory" type="hidden" value="${bpCustMember.hireCompanycategory!}" /></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listArea10092??>
						      	<#list listArea10092 as area>
								 <li><a class="selt" href="javascript:;" selectid="${area.id}">${area.title}</a></li>
								 </#list>
								</#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>公司规模</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listCompanysize??><#list listCompanysize as comsize><#if bpCustMember.hireCompanysize==comsize.dicId>${comsize.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCompanysize" type="hidden" value="${bpCustMember.hireCompanysize!}" /></cite>
			  				<#if isPass==null>
						      <ul>
						      	<#if listCompanysize??>
						      	<#list listCompanysize as comsize>
								 <li><a class="selt" href="javascript:;" selectid="${comsize.dicId}">${comsize.itemValue}</a></li>
								 </#list>
								 </#if>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    </#if>
                  
                    	<dl class="dl-list">
                    	  <#if financeApplyUser.productId==15>
                    		<dt><span class="star">*</span>工作日期</dt>
	                        <dd>
							  	<input type="text" <#if isPass==null><#else>disabled="true"</#if> id="purchase_time_start" style="width:80px" name="bpCustMember.teacherStartyear" class="colorful1" readonly="readonly" value="${bpCustMember.teacherStartyear!}" onclick="new Calendar().show(this);"/>
	                        </dd>
	                      	<#else>
	                        <dt><span class="star">*</span>工作日期</dt>
                        	<dd>
                        	<input type="text"  id="purchase_time_start" <#if isPass==null><#else>disabled="true"</#if> style="width:80px" name="bpCustMember.hireStartyear" class="colorful1" readonly="readonly" value="${bpCustMember.hireStartyear!}" onclick="new Calendar().show(this);"/>
                       		 </dd>
                       	</#if>
                    	</dl>
                  
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>公司(单位)电话</if></dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name ="bpCustMember.hireCompanyphonePrefix" id="hireCompanyphone_Prefix"  value="${bpCustMember.hireCompanyphonePrefix}" maxlength="30" size="32" class="colorful1" style="width:50px;" /> -
                            <input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireCompanyphoneSuffix" id="hireCompanyphone_Suffix"  value="${bpCustMember.hireCompanyphoneSuffix}"  maxlength="30" size="32" class="colorful1" style="width:134px;" />
                        </dd>
                    </dl>		
           		</div>
            <div class="btn-submit-div1">
            	<input id="updateBpCustMemberwork" type="submit" class="ui-btn" value="保存并继续"/>
            </div>
            </form>
            </div>
	</#if>



			 
            

            <#if (financeApplyUser.currentnode=='nodeAssets')>
            <div class="loanappinfo" id="div_Assets">
	            <form id="updateBpCustMemberFormassets" action="${base}/user/getNodeMemBpCustMember.do" method="post">
	            
	            <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
                    <div class="loanboder">
                        <h4>房产信息</h4>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>是否有房</dt>
                            <dd>
                           	 <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse"  <#if bpCustMember.havehouse=='1'> checked="checked" </#if>  value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouse" <#if bpCustMember.havehouse=='0'> checked="checked" </#if>  value="0" />无</span>
                            </dd>
                        </dl>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>有无房贷</dt>
                            <dd>

                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouseloan"  <#if bpCustMember.havehouseloan=='1'> checked="checked" </#if>  value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havehouseloan" <#if bpCustMember.havehouseloan=='0'> checked="checked" </#if>  value="0" />无</span>
                            </dd>
                        </dl>
                    
                    </div>
                    <div class="loanboder">
                        <h4>车产信息</h4>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>是否有车</dt>
                            <dd>
                             <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecar"  <#if bpCustMember.havecar=='1'> checked="checked" </#if> value="1" checked="checked"/> 有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecar"  <#if bpCustMember.havecar=='0'> checked="checked" </#if> value="0" /> 无</span>
                            </dd>
                        </dl>
                        <dl class="dl-list">
                            <dt><span class="star">*</span>有无车贷</dt>
                            <dd>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecarloan"  <#if bpCustMember.havecarloan=='1'> checked="checked" </#if> value="1" checked="checked"/>有</span>
                            <span class="check"><input type="radio" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.havecarloan"  <#if bpCustMember.havecarloan=='0'> checked="checked" </#if> value="0" /> 无</span>
                            </dd>
                        </dl>
                    
                    </div>
                <div class="btn-submit-div1">
                   <input id="updateBpCustMemberassets" type="submit" class="ui-btn" value="保存并继续"/>
                </div>
                </form>
                </div>
	</#if>
               
            <#if (financeApplyUser.currentnode=='nodeCredit')> 
            <div class="loanappinfo" id="div_credit">
           	<form id="updateBpCustMemberFormcredit" action="${base}/user/getNodeMemBpCustMember.do" method="post" onsubmit="return youthNodeCredit()">
           	 	 
           	 	<input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
                <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
           		<#--<input type="hidden" name="credit" id="credit" value="credit" />-->
                <div class="loanboder">          
                <#if financeApplyUser.productId==16>
                <#--${(nodeHtml)!}-->
               <#include "/WEB-INF/template/proj_wenandai/finance/nodeHtml/youth_nodeCredit.ftl">
                <#else>
                
                
                  		
                  <#if financeApplyUser.productId==16>
                  
                   <dl class="dl-list">
                    	
                        <dd>                                                                      
	                        	<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="Income">
                        					<span class="Income updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">收入认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype  />
		                        				<a id="Income" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/bankwater.jpg">查看样例</a>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>   
                    </dl>
                    
                    
                    <dl class="dl-list">
                        <dt><span>校园一卡通认证</span></dt>
                        <dd><span data="0" class="CompanyPlace">
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Teacher">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as teacherStatus>
	                        		<#if teacherStatus.materialstype=="Teacher">
	                        			<#if teacherStatus.status!=3>	                        			
	                        			  <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriTeacher()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
		                    
		                        	
                         </dd>
                     </dl>
                    
                    
                    
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>所在院系</dt>
                        <dd>
                        	<#if financeApplyUser.productId!='15'>
                        		<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.hireCompanyname" id="hireCompanyname"  value="${(bpCustMember.hireCompanyname)!}" maxlength="30" size="32" class="colorful1" />
                       		<#else>
                       			<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.teacherCompanyname" id="hireCompanyname"  value="${(bpCustMember.teacherCompanyname)!}" maxlength="30" size="32" class="colorful1" />
                       		</#if>
                        </dd>
                    </dl>
                    
                    
                    
                     <dl class="dl-list">

                    	<dt><span class="star">*</span>年级</dt>
                        <dd>
                        <div class="divselectall" >
						      <cite><span><#if listJob??><#list listJob as list><#if bpCustMember.bossPosition==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.bossPosition" type="hidden" value="${bpCustMember.bossPosition!}" id="hirePosition"/></cite>
			  				<#if isPass==null>
						      <ul>
						      	
								  <li><a class="selt" href="javascript:;" selectid="1">大一</a></li>
								 <li><a class="selt" href="javascript:;" selectid="2">大二</a></li>
								 <li><a class="selt" href="javascript:;" selectid="3">大三</a></li>
								 <li><a class="selt" href="javascript:;" selectid="4">大四</a></li>
								 <li><a class="selt" href="javascript:;" selectid="5">专一</a></li>
								 <li><a class="selt" href="javascript:;" selectid="6">专二</a></li>
								 <li><a class="selt" href="javascript:;" selectid="7">专三</a></li>
								 <li><a class="selt" href="javascript:;" selectid="8">研一</a></li>
								 <li><a class="selt" href="javascript:;" selectid="9">研二</a></li>
								 <li><a class="selt" href="javascript:;" selectid="10">研三</a></li>
								 <li><a class="selt" href="javascript:;" selectid="11">博一</a></li>
								 <li><a class="selt" href="javascript:;" selectid="12">博二</a></li>
								 <li><a class="selt" href="javascript:;" selectid="13">博三</a></li>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>每月生活费</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossMonthlyincome" id="hireMonthlyincome"  value="${(bpCustMember.bossMonthlyincome)!}" maxlength="30" size="32" class="colorful1" chkLoanMoney(this);funFormat(this)/>
                        </dd>
                    </dl>
                     <dl class="dl-list">
                    	<dt><span class="star">*</span>QQ或微信</dt>
                        <dd><input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossEmail" id="hireEmail"  value="${(bpCustMember.bossEmail)!}" maxlength="30" size="32" class="colorful1" /></dd>
                    </dl>
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>学校（校区）</dt>
                        <dd>
                        	<input type="text" <#if isPass==null><#else>disabled="true"</#if> name="bpCustMember.bossAddress" id="hireAddress"  value="${(bpCustMember.bossAddress)!}" maxlength="30" size="32" class="colorful1" />
                        </dd>
                    </dl>
                    
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>有无兼职</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listUnitp??><#list listUnitp as list><#if bpCustMember.hireCompanytype==list.dicId>${list.itemValue}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCompanytype" type="hidden" value="${bpCustMember.hireCompanytype!}" /></cite>
			  				<#if isPass==null>
						      <ul>
								 <li><a class="selt" href="javascript:;" selectid="">有</a></li>
								 <li><a class="selt" href="javascript:;" selectid="">无</a></li>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                    
                    
                    
                    <dl class="dl-list">
                    	<dt><span class="star">*</span>兼职收入</dt>
                        <dd>
                        	<div class="divselectall" >
						      <cite><span><#if listCompanysize??><#list listCompanysize as comsize><#if bpCustMember.hireCompanysize==comsize.itemValue>${bpCustMember.hireCompanysize}</#if></#list></#if></span><!--已经在js里面写好了，只需给name赋值就可以-->
			  				<input name="bpCustMember.hireCompanysize" type="hidden" value="${bpCustMember.hireCompanysize!}" /></cite>
			  				<#if isPass==null>
						      <ul>   
								 <li><a class="selt" href="javascript:;" selectid="">100-300</a></li>
								 <li><a class="selt" href="javascript:;" selectid="">300-500</a></li>
								 <li><a class="selt" href="javascript:;" selectid="">500-1000</a></li>
								 <li><a class="selt" href="javascript:;" selectid="">1000以上</a></li>
						      </ul>
						      <#else></#if>
						  </div>
                        </dd>
                    </dl>
                  
                  <#else>
                  <dl class="dl-list">
                     	<dd>                                                         
	         					<#if  webFinanceApplylist!=null>
		                        	<#list webFinanceApplylist as list>
		                        		<#if list.materialstype=="CreditRecord">
                        					<span class="CreditRecord updd">
		                        				<@authen.authentication state=list.status  />
                        					</span>
                            				<span class="sfrz">信用认证（必要认证）<br />
		                        				<@authen.material state=list.status materialstype=list.materialstype />
		                        				<a id="CreditRecord" class="veribtn1"title="" href="${base}/theme/${systemConfig.theme}/images/samples/creditreport.jpg">查看样例</a>
		                        				<br />个人信用报告需15日内开具
		                        			</span>
		                        		</#if>
		                        	</#list>
	                        	</#if>
                        </dd>    
                    </dl>  
                    
                    </#if>
                    </#if>
           		</div>
           		
           		
           		
            <div class="btn-submit-div1">
            	<input id="updateBpCustMembercredit" type="submit" class="ui-btn" value="保存并继续"/>
            </div>
            </form>
            </div>
            </#if>

        <#if (financeApplyUser.currentnode=='nodeUpload')> 
        <div class="loanappinfo" id="div_upload">
          <form id="updateBpCustMemberFormupload" action="" method="post">
            <input type="hidden" name="bpCustMember.id" id="custMemId" value="${(bpCustMember.id)!}" />
            <input type="hidden" name="loadid" id="loadid" value="${(financeApplyUser.loanId)!}"/>
            <#list webFinanceApplylist as listsign>
            	
            </#list>
<div class="loanboder">
  <h4>驳回材料说明</h4>
  
  	
	
	
  <#list webFinanceApplylist as listsign>
	  <#if listsign.status=='2'>
	  <div class="loanboder1">
	    <dl class="ver-bg-icon-ul" style="height: auto;border: 1px solid #ccc;padding: 13px;border-radius: 2px;margin-bottom: 20px;">
	        <dt style="text-align: left;"><span><@authen.key2name key = listsign.materialstype product = financeApplyUser.productName /></span></dt>
	        <dd>
	        	<span data="0" class="${listsign.materialstype}">
	            	<@authen.authentication state = listsign.status />
				</span>
			</dd>
	        <dd style="margin-top: 70px;margin-left: -57px;color: #0078b4;">
	           <a class="auth" href="javascript:void(0);" 
	           <#if isPass==null>
	           <#--特例：这要命的特例老子要疯了-->
	           <#if financeApplyUser.productName='青春贷'>
	           		<#if listsign.materialstype=="Income">
	           			onClick="veriStudent()"
	           		<#elseif listsign.materialstype=='CreditRecord'>
	           			onClick="veriWechat()"
	           		<#else>
	           			onClick="veri${listsign.materialstype}()"
	           		</#if>
	           <#else>
	           		onClick="veri${listsign.materialstype}()"
	           </#if>
	           <#else> 
	           		style="color:#CCCCCC;"
	           </#if> 
	           target="_self">上传资料</a>	
	        </dd>
	        <dd style="float: none;padding-left: 90px;color: #c40000;text-align: left;width:450px">${listsign.rejectReason}</dd>
	    </dl>
	  </div>
	 </#if>
	 
	<#if listsign.materialstype=='IDCard'><#assign IDCard=listsign.status>
	<#elseif listsign.materialstype=='CreditRecord'><#assign CreditRecord=listsign.status>
	<#elseif listsign.materialstype=='Income'><#assign Income=listsign.status>
	<#elseif listsign.materialstype=='WebShop'><#assign WebShop=listsign.status>
	<#elseif listsign.materialstype=='House'><#assign House=listsign.status>
	<#elseif listsign.materialstype=='Vehicle'><#assign Vehicle=listsign.status>
	<#elseif listsign.materialstype=='Marriage'><#assign Marriage=listsign.status>
	<#elseif listsign.materialstype=='Education'><#assign Education=listsign.status>
	<#elseif listsign.materialstype=='Career'><#assign Career=listsign.status>
	<#elseif listsign.materialstype=='JobTitle'><#assign JobTitle=listsign.status>
	<#elseif listsign.materialstype=='MobilePhone'><#assign MobilePhone=listsign.status>
	<#elseif listsign.materialstype=='MicroBlog'><#assign MicroBlog=listsign.status>
	<#elseif listsign.materialstype=='Residence'><#assign Residence=listsign.status>
	<#elseif listsign.materialstype=='CompanyPlace'><#assign CompanyPlace=listsign.status>
	<#elseif listsign.materialstype=='CompanyRevenue'><#assign CompanyRevenue=listsign.status>
	</#if>
 </#list>
</div>
            
            
            <div class="loanboder">
            	<h4>必要上传资料</h4>
                 <div class="loanboder1">
               <#if (financeApplyUser.productId=='16')||financeApplyUser.productName=='青春贷'>
                 <#include "/WEB-INF/template/proj_wenandai/finance/nodeHtml/youth_nodeUpload.ftl">
               <#else>
               
               
               <#if IDCard!='2'>
                  <dl class="ver-bg-icon-ul">
                        <dt><span>身份认证</dt>
         				<dd><span data="0" class="IDCard">
         					 <#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		 <#if list.materialstype=="IDCard">
	                        			<@authen.authentication state=list.status />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="IDCard">
	                        			<@authen.authentication state=list.status />
	                        		</#if>
	                        	</#list>
       
         				</span>
	        			</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as idCardState>
	                        	<#if idCardState.materialstype=="IDCard">
	                        		<#if idCardState.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriIDCard()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        		</#if>
                         		</#if>
	                        </#list>
                         </dd>
                    </dl>
                   
                    </#if>
                     <#if (financeApplyUser.productId=='16')>
                     <#if Income!='2'>
                      <dl class="ver-bg-icon-ul">
                        <dt><span>学生认证</span></dt>
                        <dd><span data="0" class="Income">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                        	<#list webFinanceApplylist as incomeStatus>
	                        		<#if incomeStatus.materialstype=="Income">
	                        			<#if incomeStatus.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriIncome()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                        
	                        			
                         </dd>
                     </dl>
                     
                     </#if>
                     <#if CompanyPlace!='2'>
                     <dl class="ver-bg-icon-ul">
                        <dt><span>校园一卡通认证</span></dt>
                        <dd><span data="0" class="CompanyPlace">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Teacher">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Teacher">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as teacherStatus>
	                        		<#if teacherStatus.materialstype=="Teacher">
	                        			<#if teacherStatus.status!=3>	                        			
	                        			  <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriTeacher()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         </dd>
                     </dl>
                     </#if>
                     <#else>
                     
                     
                     
                    
                    <#if (financeApplyUser.productId!='14')>
                     <#if (financeApplyUser.productId!='15')>
                      <#if CreditRecord!='2'>
                     <dl class="ver-bg-icon-ul">
                        <dt><span>信用认证</span></dt>
                        <dd><span data="0" class="CreditRecord">
                        	 <#--<#if webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="CreditRecord">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
                        		<#if list.materialstype=="CreditRecord">
	                        			<@authen.authentication state=list.status  />
	                        	</#if>
	                        </#list>
                        </span>
	       				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as recordState>
	                        		<#if recordState.materialstype=="CreditRecord">
	                        			<#if recordState.status!='3'>
	                        				<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriCreditRecord()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         	
                         </dd>
                        </dl>
                        </#if>
                        </#if>
                         <#if Income!='2'>
                         <dl class="ver-bg-icon-ul">
                        <dt><span>收入认证</span></dt>
                        <dd><span data="0" class="Income">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                        	<#list webFinanceApplylist as incomeStatus>
	                        		<#if incomeStatus.materialstype=="Income">
	                        			<#if incomeStatus.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriIncome()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                        
	                        			
                         </dd>
                     </dl>
                     </#if>
                     <#if (financeApplyUser.productId)='15'>
                      <#if CompanyPlace!='2'>
                     <dl class="ver-bg-icon-ul">
                        <dt><span>教师资格证</span></dt>
                        <dd><span data="0" class="CompanyPlace">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Teacher">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Teacher">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as teacherStatus>
	                        		<#if teacherStatus.materialstype=="Teacher">
	                        			<#if teacherStatus.status!=3>	                        			
	                        			  <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriTeacher()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
		                    
		                        	
                         </dd>
                     </dl>
                     </#if>
                     	</#if>
                     </#if>
                      <#if (financeApplyUser.productId=='13')>
                       <#if CompanyPlace!='2'>
                      <dl class="ver-bg-icon-ul">
                        <dt><span>经营场所认证</span></dt>
                        <dd><span data="0" class="CompanyPlace">
                       		<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="CompanyPlace">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="CompanyPlace">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as placeStatus>
	                        		<#if placeStatus.materialstype=="CompanyPlace">
	                        			<#if placeStatus.status!=3>
	                        			 <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriCompanyPlace()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                      
                         </dd>
                     </dl>
                       </#if>
                     </#if>
                      <#if (financeApplyUser.productId=='14')>
                       <#if WebShop!='2'>
                     </dl> <dl class="ver-bg-icon-ul">
                        <dt><span>网店认证</span></dt>
                        <dd><span data="0" class="WebShop">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="WebShop">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="WebShop">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         	<#list webFinanceApplylist as shopStatus>
	                        		<#if shopStatus.materialstype=="WebShop">
	                        			<#if shopStatus.status!=3>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriWebShop()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>			
	                        			</#if>
	                        		</#if>
	                        	</#list>
                     		
                         </dd>
                     </dl> 
                      </#if>
                      </#if>
                      </#if>
                     </div> 
                     
                     
                     
                     
                     </#if>
            	</div>
            	
            	<div class="loanboder">
            	<h4>可选上传资料</h4>
                	<div class=" loanboder1">
                <#if (financeApplyUser.productId=='16')||financeApplyUser.productName=='青春贷'>
                 <#include "/WEB-INF/template/proj_wenandai/finance/nodeHtml/youth_nodeUpload2.ftl">
               <#else>
                	<#if financeApplyUser.productId=='14' || financeApplyUser.productId=='15'>
                	<#if CreditRecord!='2'>
                	<dl class="ver-bg-icon-ul">
                        <dt><span>信用认证</span></dt>
                        <dd>
                        <span data="0" class="CreditRecord">
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="CreditRecord">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>
	       				</dd>
	       				<br/>
                         <dd>
                         		<#list webFinanceApplylist as recordState2>
	                        		<#if recordState2.materialstype=="CreditRecord">
	                        			<#if recordState2.status!=3>
	                        			
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriCreditRecord()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>		
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         </dd>
                     </dl>
                   </#if>
                   </#if>
                   <#if (financeApplyUser.productId=='14')>
                   <#if Income!='2'>
                     <dl class="ver-bg-icon-ul">
                        <dt><span>收入认证</span></dt>
                        <dd><span data="0" class="Income">
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Income">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>   
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as incomeStatus2>
	                        		<#if incomeStatus2.materialstype=="Income">
	                        			<#if incomeStatus2.status!='3'>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriIncome()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         	</dd>
                     </dl>
                     </#if>
                      </#if>
                     <#if financeApplyUser.productId=='16'><#else>
                      <#if House!='2'>
               		 <dl class="ver-bg-icon-ul">
                 	
                        <dt><span>房产认证</span> </dt> 
                        <dd><span data="0" class="House">
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="House">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span> 
						</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as houseStatus>
	                        		<#if houseStatus.materialstype=="House">
	                        			<#if houseStatus.status!=3>
	                        			 <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriHouse()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                       
                         </dd>
                        </dl>
                        </#if>
                        </#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if Vehicle!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>购车认证</span></dt>
                         <dd> <span data="0" class="Vehicle">
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Vehicle">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                         </span>  
	        			</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as vechicleStatus>
	                        		<#if vechicleStatus.materialstype=="Vehicle">
	                        			<#if vechicleStatus.status!=3>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriVehicle()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                    	
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                          <#if Marriage!='2'>
                         <dl class="ver-bg-icon-ul">
                        <dt><span>结婚认证</span></dt>
                        <dd> <span data="0" class="Marriage">
                        		<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Marriage">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        </span>   
	       				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as marriageStatus>
	                        		<#if marriageStatus.materialstype=="Marriage">
	                        			<#if marriageStatus.status!=3>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriMarriage()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         	
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if Education!='2'>
                         <dl class="ver-bg-icon-ul">
                        <dt><span>学历认证</span></dt>
                        <dd><span data="0" class="Education">
                        	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Education">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Education">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        </span>
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as educationStatus>
	                        		<#if educationStatus.materialstype=="Education">
	                        			<#if educationStatus.status!=3>
	                        			
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriEducation()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        			</#if>
	                        		</#if>
	                        	
	                        	</#list>
                        	
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if Career!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>工作认证</span><dt>
                         <dd><span data="0" class="Career"> 
                         	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Career">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Career">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                         </span>
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as carrerStatus>
	                        		<#if carrerStatus.materialstype=="Career">
	                        			<#if carrerStatus.status!=3>
	                        				<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriCareer()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>		
	                        			</#if>
	                        		</#if>
	                        	</#list>
                         	
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if JobTitle!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>职称认证</span></dt>
                         <dd>
                          <span data="0" class="JobTitle"> 
                          	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="JobTitle">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="JobTitle">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                          </span>
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as jobTitleStatus>
	                        		<#if jobTitleStatus.materialstype=="JobTitle">
	                        			<#if jobTitleStatus.status!=3>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriJobTitle()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
         					
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if MobilePhone!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>手机认证</span></dt>
                         <dd><span data="0" class="MobilePhone">
                         	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="MobilePhone">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="MobilePhone">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                         </span>
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as phoneStatus>
	                        		<#if phoneStatus.materialstype=="MobilePhone">
	                        			<#if phoneStatus.status!=3>
	                        			 <a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriMobilePhone()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                       
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                         <#if MicroBlog!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>微博认证</span></dt>
                         <dd><span data="0" class="MicroBlog">
                         <#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="MicroBlog">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="MicroBlog">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                         </span>
	     				</dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as blogStatus>
	                        		<#if blogStatus.materialstype=="MicroBlog">
	                        			<#if blogStatus.status!=3>
	                        			<a class="auth" href="javascript:void(0);" <#if isPass==null>onClick="veriMicroBlog()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                        	
                         </dd>
                        </dl></#if></#if>
                         <#if financeApplyUser.productId=='16'><#else>
                          <#if Residence!='2'>
                         <dl class="ver-bg-icon-ul">
                         <dt><span>居住认证</span></dt>
                         <dd><span data="0" class="Residence">
                         	<#--<#if  webFinanceApplylist!=null>
	                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Residence">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        	</#list>
                        	</#if>-->
                        	<#list webFinanceApplylist as list>
	                        		<#if list.materialstype=="Residence">
	                        			<@authen.authentication state=list.status  />
	                        		</#if>
	                        </#list>
                        	
                         </span></dd><br/>
                         <dd>
                         		<#list webFinanceApplylist as residenceStatus>
	                        		<#if residenceStatus.materialstype=="Residence">
	                        			<#if residenceStatus.status!=3>
	                        			 <a class="auth" href="javascript:void(0);"  <#if isPass==null>onClick="veriResidence()"<#else> style="color:#CCCCCC;" </#if> target="_self">上传资料</a>	
	                        			</#if>
	                        		</#if>
	                        	</#list>
	                       
                         </dd>
                     </dl>
                     </#if></#if>
                     </#if>
                      </div>     
           		</div>
            	<div class="btn-submit-div">
            	<#--<input id="updateBpCustMemberupload" type="button" class="ui-btn" value="提交审核" onclick="javascript:if(confirm('提示：确定要提交吗?')){window.location.href='${base}/user/fileUploadBpCustMember.do?loadid=${(financeApplyUser.loanId)!}'}"/>-->
            	<input id="checkUpload" type="button" class="ui-btn" value="提交审核"/>
            	</div>
            </form>
            </div>
            </#if>
           
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