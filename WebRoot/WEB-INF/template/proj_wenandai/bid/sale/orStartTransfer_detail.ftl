
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="description" content="${systemConfig.metaTitle} ,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} ,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/layer/layer.min.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/bidOrSale.js"></script>
<script type="text/javascript">var m1="债权转让",m2="",m3="";</script>

</head>
<body >
<form id="transfer_Form"  method="post" action="${base}/creditFlow/financingAgency/saveorStartTransferPlBidSale.do" >
<input name="bidInfoId" type="hidden" id="bidInfoId" value=${bidInfoId} />

<div><span>债权的名称：</span><input name="bidProName" type="text"   value="${bidProName}" readonly style='background:#ffff00;width:400px;'/>
</div><br>
<div><span>债权年化收益率：</span><input name="yearAccrualRate" type="text" value="${yearAccrualRate}"  readonly style='background:#ffff00;'>   
<span>到期日：</span><input name="intentDate" type="text"   value="${intentDate}" readonly style='background:#ffff00;'>    
<span>当前可交易金额：</span><input name="saleMoney" type="text"   value="${saleMoney}" readonly style='background:#ffff00;'>    
 </div> <br>
 <div><input type="radio" name="changeMoneyType" value="1" checked="checked" style='margin-left:100px;' />减价
      <input type="radio" name="changeMoneyType" value="0" style='margin-left:100px;' />加价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 <span>千分之：<input id="changeMoneyRate" name="changeMoneyRate" type="text"     style='width:100px;'/>&nbsp;&nbsp;
              <input id="changeMoney" name="changeMoney" type="text"    style='width:100px;' />元</span>
 </div>	
 <hr> <br>
 <div style='font-weight:bold;'> 结算清单:</div><br>
 <div style='margin-left:60px;font-weight:bold;'> 结算总金额:</div><br>
<br>
<div ><span style='margin-left:120px;'> 出让本金：${saleMoney}元</span> <span style='margin-left:400px;'>折价率：</span></div>  <br>
<div><span style='margin-left:120px;'> 折让金：</span> <span style='margin-left:400px;'> 年化利率：${yearAccrualRate}%</span></div><br>
<div ><span style='margin-left:120px;'>结算利息：</span> <span style='margin-left:400px;'>预期罚息：</span></div>  <br>
<div ><span style='margin-left:120px;'>债权到期日：${intentDate}</span>  <span style='margin-left:400px;'></span></div><br>

<br>	
 <hr> <br>
 <div>交易费用</div>
  <div>转让服务费：5.00元</div>
   <div>交易金额的0.5% ，挂牌时，由出让人缴付。</div>
 <br>	
 <hr> <br>
 <div><a id="getVerifySms"  href="javascript:void(0);">获取验证码</a></div>
 <div><input id="telphone" name="telphone"  type="text" value=${telephone} readonly style='width:100px;background:#ffff00;'> &nbsp;&nbsp;	
 <span style=" float:left;"><input type="text"  id="verify_sms" tabindex="6"  name="verify_sms" placeholder="请输入短信验证码"  size="30" /></span>
 <span  class="black middle" id="showMsg"></span>
 </div>
 
 <div>
 <input id="transferFormPhone" type="button" value="挂牌"  style='margin-left:400px;' > <!-- onClick="javascript:transfer_Form.submit();"-->

 </div>
</form>
	</body>
</html>