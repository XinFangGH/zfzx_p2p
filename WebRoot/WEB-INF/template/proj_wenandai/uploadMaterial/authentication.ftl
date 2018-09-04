<#-- 材料认证的判断-->
<#-- 
	参数说明：
	state   当前材料的状态:0未上传，1待审查和补充材料，2已驳回，3已认证
 -->
<#macro authentication state>
	<#if state!=null>
		<#if (state==0)>
		<#elseif (state==1)><span class="veristatus1"></span>
		<#elseif (state==2)><span class="veristatus2"></span>
		<#elseif (state==3)><span class="veristatus3"></span>
		</#if>
	<#else>
		<!-- 为未上传-->
	</#if>
</#macro>





<#macro key2name key product>
	<#if key=='IDCard'>身份认证
	<#elseif key=='CreditRecord'><#if product='青春贷'>微信认证<#else>信用认证</#if>
	<#elseif key=='Income'><#if product='青春贷'>学生认证<#else>收入认证</#if>
	<#elseif key=='WebShop'>网店认证
	<#elseif key=='House'>房产认证
	<#elseif key=='Vehicle'>购车认证
	<#elseif key=='Marriage'>结婚认证
	<#elseif key=='Education'>学历认证
	<#elseif key=='Career'>工作认证
	<#elseif key=='JobTitle'>职称认证
	<#elseif key=='MobilePhone'>手机认证
	<#elseif key=='MicroBlog'>微博认证
	<#elseif key=='Residence'>居住认证
	<#elseif key=='CompanyPlace'>经营场所认证
	<#elseif key=='CompanyRevenue'>
	</#if>
</#macro>



<#-- 材料认证的按钮状态-->
<#macro material state materialstype>
	<#if state!=null>
		<#if (state==0)>
			<a href="javascript:void(0);" onClick="veri${materialstype}()" class="veribtn1">立即认证</a>
		<#elseif (state==1)>
			<a href="javascript:void(0);" onClick="veri${materialstype}()" class="veribtn2">补充资料</a>
		<#elseif (state==2)>
			<a href="javascript:void(0);" onClick="veri${materialstype}()" class="veribtn3">重新认证</a>
		<#elseif (state==3)>
			
		</#if>
		<#else>
		<!-- 为未上传-->
		<a href="javascript:void(0);" onClick="veriidcard()" class="veribtn1">立即认证</a>
	</#if>
</#macro>



