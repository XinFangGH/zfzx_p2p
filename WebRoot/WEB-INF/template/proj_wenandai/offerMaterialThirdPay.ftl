<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 支付账户激活材料页面</title>
    <meta name="description" content="${systemConfig.metaTitle} - 支付账户激活材料页面,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 支付账户激活材料页面,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="个人中心",m2="",m3="";</script>
</head>
<body >
  <!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main-box">
   <div class="main">
	   <div class="tab-list" style="border:1px solid #ddd; background:#fff; padding:20px 40px; " >
	    <p style="font-size:18px;padding-bottom:10px;text-indent:2em;">目前易宝支付需要线下审核企业用户的注册信息，需要提供一下材料进行人工审核，审核通过后方可激活支付账户。</p>
	    <p style="font-size:18px;padding-bottom:10px;">操作步骤：</p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">1.平台名称+商户编码（请致电运营平台【${servicePhone}】获取平台名称和商户编码）</p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">2.支付账号（在【个人中心-->账户信息-->第三方支付】查看） </p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">2.会员类型（在【个人中心-->账户信息-->第三方支付】查看） </p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">3.完整的企业名称 </p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">4.企业五证材料（注：黑白复印件+红色公章）： </p>
	   	<p style="font-size:14px;padding-bottom:10px;text-indent:4em;">a、营业执照副本复印件加盖单位 公章；</p>
	   	<p style="font-size:14px;padding-bottom:10px;text-indent:4em;">b、税务登记证复印件加盖单位公章；</p>
	   	<p style="font-size:14px;padding-bottom:10px;text-indent:4em;">c、组织机构代码证复印件加盖单位公章；</p>
	   	<p style="font-size:14px;padding-bottom:10px;text-indent:4em;">d、银行开户许可证复印件加盖单位公章；</p>
	   	<p style="font-size:14px;padding-bottom:10px;text-indent:4em;">e、法人身份证正反面复印在一页，加盖单位公章。</p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">5. 将上述材料中1至3条邮件正文形式书写，第4条中的企业5证为以普通附件添加方式发给易宝运营。邮箱地址：ying.gao@yeepay.com。</p>
	   	<p style="font-size:16px;padding-bottom:10px;text-indent:2em;">6. 易宝收到五证后和相关材料后，会在一个工作日内完成审核。</p>
	   	<p style="font-size:16px;padding-bottom:40px;text-indent:2em;">7. 用户登录后,前往【个人中心-->账户信息-->第三方支付】查看支付账户是否被激活。如果未激活请联系运营方重新开通第三方支付账户。</p>
	   	<p style="font-size:14px;padding-left: 10px;color:red;padding-bottom:10px;text-indent:2em;">备注：</p>
	   	<p style="font-size:12px;padding-left: 10px;color:red;padding-bottom:10px;text-indent:4em;">1.非盈利组织开通企业户需要提供企业5证替换为：社会团体法人登记证书; 组织机构代码证;法定代表人有效身份证件;银行开户许可证;税务登记证。</p>
	   	<p style="font-size:12px;padding-left: 10px;color:red;padding-bottom:10px;text-indent:4em;">2.正式环境需提供以上材料给易宝运营方，等待材料审核。</p>
	    <p style="font-size:12px;padding-left: 10px;color:red;padding-bottom:10px;text-indent:4em;">3.测试环境无需提供上述材料给易宝线下运营方,请自行审核激活支付账户。</p>
	   </div>
   </div>
</div>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>