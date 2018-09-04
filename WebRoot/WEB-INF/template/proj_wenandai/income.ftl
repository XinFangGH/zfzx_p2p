<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${systemConfig.metaTitle} - 收益计算器</title>
    <meta name="description" content="${systemConfig.metaTitle} - 收益计算器,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 收益计算器,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="",m2="",m3="",m4="";</script>
<link rel="stylesheet" type="text/css" href="${base}/js/computer/public2014.8.8.css">
<script type="text/javascript" src="${base}/js/computer/common.js"></script>
<link href="${base}/theme/${systemConfig.theme}/css/tool_style.css" rel="stylesheet" type="text/css">
<script src="${base}/js/computer/bd2.js" type="text/javascript"></script>
<script src="${base}/js/computer/GainCalc.js" type="text/javascript"></script>
</head>
<body >
 
  <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
<div class="main">
	<div id="container" sessionid="xkkdk3o20kks12mhjpwplvmh">
        
    <div class="detail_logo_bg">
    </div>
    <div class="position">您当前所在的位置： <a href="${base}"></a> 收益计算器</div>
    <div class="tool_content">
        <div class="tool_arrow arrow1">
        </div>
        <dl class="tool_introduce clearfloat">
            <dt><b class="calc_max"></b></dt>
            <dd>
                <h2>
                    收益计算器</h2>
                <span class="tool_detail">只要根据提示填写相关信息，即可一键计算，您在升升投的投资收益，助您高效理财。</span></dd>
        </dl>
        <div class="tool_box clearfloat">
            <div class="tool_left clearfloat">
                <h3 style="padding-bottom: 26px;">
                    选择参数</h3>
                <div class="tool_item">
                    <span>投资金额</span>
                    <div class="tool_form">
                        <input type="text" maxlength="9" class="tool_txt" name="amount" id="amount" value="${borrowamount}"></div>
                    <div class="tool_unit">
                        元</div>
                </div>
                <div class="tool_prompt" id="tipAmount" style="display: none;">
                    请输入投资金额，只能为大于100的正整数</div>
                <div class="tool_item">
                    <span>年化利率</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="apr" id="apr" value="${indexapr}"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipApr" style="display: none;">
                    请输入年化利率，只能大于0的数字</div>
                <div class="tool_item">
                    <span>借款期限</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="mons" id="mons" value="${repaytime}"></div>
                    <div class="tool_unit">
                        个月</div>
                </div>
                <div class="tool_prompt" id="tipMons" style="display: none;">
                    请输入借款期限，1-36个月</div>
                <div class="tool_item" style="display: none;">
                    <span>借款人奖励</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="txtBid" id="txtBid"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipBid" style="display: none;">
                    借款人奖励格式不正确，只能为正数</div>
                <div class="tool_item" style="display: none;">
                    <span>利宝金融奖励</span>
                    <div class="tool_form">
                        <input type="text" class="tool_txt" name="txtTender" id="txtTender"></div>
                    <div class="tool_unit">
                        %</div>
                </div>
                <div class="tool_prompt" id="tipTender" style="display: none;">
                    奖励格式不正确，只能为正数</div>
                <div class="tool_item">
                    <span></span>
                    <div class="tool_form" style="width: 320px;">
                        <div id="rd">
                            <span style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay1" value="每月付息" ></span>
                            <label for="repay1" id="repay1" name="lblRepay1" <#if borrowpay=='1'>class="checked"</#if>>
                               	 每月付息</label>
                            
                            <span style="display: none">
                            	
                                <input type="radio" name="Repayment" id="lblRepay3" value="一次性"></span><label for="repay3" name="lblRepay3" id="repay3" <#if borrowpay=='2'>class="checked"</#if>>一次性</label>
                            <span style="display: none">
                            	
                                <input type="radio" name="Repayment" id="lblRepay4" value="先息后本"></span><label for="repay4" name="lblRepay4" id="repay4" <#if borrowpay=='4'>class="checked"</#if>>先息后本</label>
                               
                            <span style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay5" value="等额本息"></span><label for="repay5" name="lblRepay5" id="repay5" <#if borrowpay=='0'>class="checked"</#if>>等额本息</label>
                            <span style="display: none">
                                <input type="radio" name="Repayment" id="lblRepay6" value="等本等息"></span><label for="repay6" name="lblRepay6" id="repay6" <#if borrowpay=='3'>class="checked"</#if>>等本等息</label>
                        </div>
                    </div>
                </div>
                <div class="tool_item_btn">
                    <input type="submit" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnCalculate" value="计算">
                    <input type="reset" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnReset" value="重置" style="margin-left: 20px;"></div>
            </div>
            <div class="tool_right">
                <dl class="tool_result clearfloat">
                    <dt>计算结果</dt>
                    <dd>
                        <table width="100%" border="0" class="tool_table">
                            <tbody><tr>
                                <td>
                                    本息合计(奖)：<strong><span id="span1">￥ 0</span></strong>
                                </td>
                                <td>
                                    利息收入共：<strong><span id="span2">￥ 0</span></strong>
                                </td>
                                <td>
                                    每月收款：<strong><span id="span3">￥ 0</span></strong>
                                </td>
                            </tr><!-- 
                            <tr style="display: none;">
                                <td>
                                    奖励总金额：<strong><span id="span4">￥ 0</span></strong>
                                </td>
                                <td>
                                    借款人奖励：<strong><span id="span5">￥ 0</span></strong>
                                </td>
                                <td>
                                    国贷网奖励：<strong><span id="span6">￥ 0</span></strong>
                                </td>
                            </tr>
                         --></tbody></table>
                    </dd>
                </dl>
                <dl class="tool_result clearfloat">
                    <dt>本息收款时间表</dt>
                    <dd id="newdiv" style="display: none;">
                    </dd>
                    <dd id="gain_initial">
                        <p class="tool_initial">
                            <img src="${base}/theme/${systemConfig.theme}/images/gain_contrast.gif"><br>
                            算一算吧，不会让你失望的</p>
                    </dd>
                </dl>
            </div>
        </div>
    </div>

    </div>
    

</div>

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
  <#--<#include "/WEB-INF/template/${systemConfig.theme}/layout/popup.ftl">-->
	</body>
</html>