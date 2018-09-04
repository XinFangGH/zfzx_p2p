<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 房贷计算器</title>
    <meta name="description" content="${systemConfig.metaTitle} - 房贷计算器,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 房贷计算器,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<link rev="stylesheet" href="${base}/js/computer/main20100906.css" type="text/css" rel="stylesheet">
<link rev="stylesheet" href="${base}/js/computer/jquery-ui-1.8.custom.css" type="text/css" rel="stylesheet">
<script src="${base}/js/computer/houseBase.js" type="text/javascript" charset="utf-8"></script>
<script src="${base}/js/computer/makeone.js" type="text/javascript" charset="utf-8"></script>
<script src="${base}/js/computer/house_loan.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">var m1="我要投资",m2="",m3="",m4="";</script>
</head>
<body >
  <#include "/WEB-INF/template/${systemConfig.theme}/registerCenter/layout/topall.ftl">
<div class="main">
	<div id="BAIDU_DUP_fp_wrapper" style="position: absolute; left: -1px; bottom: -1px; z-index: 0; width: 0px; height: 0px; overflow: hidden; visibility: hidden; display: none;"><iframe id="BAIDU_DUP_fp_iframe" src="./房贷计算器_2014最新贷款利率_files/o.htm" style="width: 0px; height: 0px; visibility: hidden; display: none;"></iframe></div>
<div class="wrap">

<div class="zcpart1 clearfix">
<div class="contant">
<div id="center">
<div class="position">您当前所在的位置： <a href="${base}">紫薇金融</a> &gt; 工具箱 &gt; 房贷计算器</div>
<div id="detailct">
说明：本计算器提供等额本息、等额本金两种房贷还款方式的计算参考。在贷款期限方面，支持非整年的期数。贷款利率除提供常用备选利率外，还支持自定义输入。<br>
无忧房网友情提示：本贷款计算器按2014年11月21日最新利率调整，计算结果仅供参考！ </div>
<div id="types" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
<div id="tabs-1">
<form class="verify" id="houseLoan">
<div class="input">
<table cellspacing="0" cellpadding="0">
  <tbody>
  <tr>
    <td class="frontface">贷款类型 </td>
    <td>
      <div id="loadType" class="ui-buttonset">
        <input id="radio3" type="radio" checked="" value="1" name="radio" class="ui-helper-hidden-accessible">
        <label for="radio3" class="ui-state-active ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left" aria-pressed="true" role="button" aria-disabled="false">
          <span class="">商业贷款</span>
        </label> 
        <input id="radio5" type="radio" value="2" name="radio" class="ui-helper-hidden-accessible">
        <label for="radio5" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only" role="button" aria-disabled="false">
        <span class="">公积金</span>
        </label> 
        <input id="radio6" type="radio" value="3" name="radio" class="ui-helper-hidden-accessible">
        <label for="radio6" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-right" role="button" aria-disabled="false">
        <span class="">组合型</span>
        </label> 
       </div>
       </td>
  </tr>
  <tr class="buzzTr">
    <td class="frontface">商业贷款金额 </td>
    <td><input class="uinumber require" id="loadBuzzAmt"><span class="unit">万元</span> </td></tr>
  <tr class="counTr" style="display: none;">
    <td class="frontface">公积金贷款金额 </td>
    <td><input class="uinumber require" id="loadCounAmt" value="0"><span class="unit">万元</span> </td></tr>
  <tr>
    <td class="frontface">贷款期限 </td>
    <td><input class="uinumber require" id="years" value="20"><span class="unit">年</span> <select id="month"> <option value="0" selected="">0</option> <option value="1">1</option> <option value="2">2</option> <option value="3">3</option> <option value="4">4</option> 
        <option value="5">5</option> <option value="6">6</option> <option value="7">7</option> <option value="8">8</option> <option value="9">9</option> 
        <option value="10">10</option> <option value="11">11</option></select><span class="unit">月 (共<span id="term">240</span>期)</span> </td></tr>
  <tr class="buzzTr">
    <td class="frontface">商业贷款利率 </td>
    <td><select id="buzzRateSel"> <option value="-1" selected="">手动输入利率值</option><option value="1">14年11月21日利率下限(7折)</option><option value="2">14年11月21日利率下限(8折)</option><option value="3">14年11月21日利率下限(85折)</option><option value="4">14年11月21日利率下限(9折)</option><option value="5">14年11月21日利率下限(95折)</option><option value="6">14年11月21日基准利率</option><option value="7">14年11月21日利率上限(1.1倍)</option><option value="8">15年3月1日利率下限(7折)</option><option value="9">15年3月1日利率下限(8折)</option><option value="10">15年3月1日利率下限(85折)</option><option value="11">15年3月1日利率下限(9折)</option><option value="12">15年3月1日利率下限(95折)</option><option value="13">15年3月1日基准利率</option><option value="14">15年3月1日利率上限(1.1倍)</option></select> <input class="uinumber require" id="buzzRate" style="WIDTH: 50px" value="0.00"><span class="unit">%</span> 
</td></tr>
  <tr class="counTr" style="display: none;">
    <td class="frontface">公积金贷款利率 </td>
    <td><select id="counRateSel"> <option value="-1" selected="">手动输入利率值</option><option value="1">12年7月6日后</option><option value="2">14年11月21日后</option><option value="3">15年3月1日后</option></select> <input class="uinumber require" id="counRate" style="WIDTH: 50px" value="0.00"><span class="unit">%</span> 
</td></tr>
  <tr>
    <td class="frontface">还款方式 </td>
    <td>
      <div id="payType" class="ui-buttonset">
      <input id="radioPay1" type="radio" checked="" value="1" name="radioPay" class="ui-helper-hidden-accessible">
      <label for="radioPay1" class="ui-state-active ui-button ui-widget ui-state-default ui-button-text-only ui-corner-left" aria-pressed="true" role="button" aria-disabled="false">
      <span class="">等额本息</span></label> 
      <input id="radioPay2" type="radio" value="2" name="radioPay" class="ui-helper-hidden-accessible">
      <label for="radioPay2" aria-pressed="false" class="ui-button ui-widget ui-state-default ui-button-text-only ui-corner-right" role="button" aria-disabled="false">
      <span class="">等额本金</span></label> 
    </div></td></tr></tbody></table>
<center><button hidefocus="" type="submit">计算</button><button id="resetbtn" hidefocus="" type="reset">重置</button> </center></div>
<div class="output">
<div class="zch2">
<h4>计算结果</h4></div>
<style>#buzzRateSel {
	WIDTH: 200px
}
#counRateSel {
	WIDTH: 200px
}
#types TABLE TR TD#rateTd {
	PADDING-LEFT: 190px; FONT-SIZE: 12px; WIDTH: 348px; PADDING-TOP: 0px
}
.counTr {
	DISPLAY: none
}
.list {
	BACKGROUND: #ffffff; OVERFLOW-X: hidden; VERTICAL-ALIGN: top; OVERFLOW: auto; WIDTH: 100%; HEIGHT: 25px; -moz-user-select: all
}
.listhead {
	BORDER-BOTTOM: #cccccc 1px solid
}
.listhead DIV {
	FONT-SIZE: 12px; COLOR: #0152a4
}
.ct DIV {
	DISPLAY: inline-block; FLOAT: left; OVERFLOW: hidden; WIDTH: 220px; TEXT-ALIGN: center
}
.ct DD {
	DISPLAY: block; OVERFLOW: hidden; WIDTH: 100%; LINE-HEIGHT: 18px; HEIGHT: 18px
}
.ct DT {
	DISPLAY: block; OVERFLOW: hidden; WIDTH: 100%; LINE-HEIGHT: 18px; HEIGHT: 18px
}
.ct DD {
	BORDER-BOTTOM: #cccccc 1px dashed
}
</style>

<div class="ct">
<dl style="PADDING-RIGHT: 18px">
  <dt class="listhead clearfix">
  <div>期次</div>
  <div>偿还本息(元)</div>
  <div>偿还利息(元)</div>
  <div>偿还本金(元)</div>
  <div>剩余本金(元)</div></dt></dl>
<dl class="list" id="container"></dl></div>
<table cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
  <tbody>
  <tr id="BuzzIntAmtTr" style="DISPLAY: none">
    <td class="frontface">商贷支付利息</td>
    <td><input class="txtd" id="BuzzIntAmt" readonly=""><span class="unit">元</span></td></tr>
  <tr id="CounIntAmtTr" style="DISPLAY: none">
    <td class="frontface">公积金支付利息</td>
    <td><input class="txtd" id="CounIntAmt" readonly=""><span class="unit">元</span></td></tr>
  <tr>
    <td class="frontface">累计支付利息</td>
    <td><input class="txtd" id="intAmt" readonly=""><span class="unit">元</span></td></tr>
  <tr>
    <td class="frontface">累计还款总额</td>
    <td><input class="txtd" id="fullAmt" readonly=""><span class="unit">元</span></td></tr></tbody></table></div></form></div></div></div>

</div></div>
</div></div>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>