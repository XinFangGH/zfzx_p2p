<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - CPI跟踪器</title>
    <meta name="description" content="${systemConfig.metaTitle} - CPI跟踪器,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - CPI跟踪器,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="",m2="",m3="",m4="";</script>
<link rel="stylesheet" type="text/css" href="${base}/js/computer/public2014.8.8.css">
<link href="${base}/theme/${systemConfig.theme}/css/tool_style.css" rel="stylesheet" type="text/css">
<script src="${base}/js/computer/common.js" type="text/javascript"></script>
<script src="${base}/js/computer/highcharts.src.js" type="text/javascript"></script>
<script src="${base}/js/computer/selectbox.js" type="text/javascript"></script>
<script src="${base}/js/computer/CPI.js" type="text/javascript"></script>
<script type="text/javascript">$(document).ready(function () { $('#TimeLong').selectbox(); });</script>
</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/registerCenter/layout/topall.ftl">
<div class="main">
	<div id="container" sessionid="ixseixuapcxjzm1czcqeol5m">
       
        <div style="background: #fff; width: 100%; height: auto; ">
            
        
    <div class="detail_logo_bg">
    </div>
    <div class="position">您当前所在的位置： <a href="${base}">紫薇金融</a> &gt; 工具箱 &gt; CPI 跟踪器</div>
    
    <div class="tool_content">
      <div class="tool_arrow arrow4"></div>
      <dl class="tool_introduce clearfloat">
        <dt><b class="cpi_max"></b></dt>
        <dd><h2>CPI跟踪器</h2><span class="tool_detail">CPI跟踪器在展示历史CPI数据的同时，还会根据CPI数据迅速计算您的当前财富在未来若干年后的贬值情况，以及投资紫薇金融的增值幅度。</span></dd>
      </dl>
      <div class="tool_box clearfloat">
        <div class="tool_left clearfloat">
          <h3>选择参数</h3>
          <div class="tool_item">
            <span>当前财富</span>
            <div class="tool_form"><input type="text" maxlength="9" class="tool_txt" name="money" id="money"></div>
            <div class="tool_unit">元</div>
          </div>
          <div class="tool_prompt" id="tipMoney"></div>
          <div class="tool_item">
            <span>计算时长</span>
            <div class="tool_form">
                <div class="selectbox_tool">
                <div class="left">
                  <div id="TimeLong_container" class="selectbox-wrapper" style="display: none; width: 260px;"><ul><li id="TimeLong_input_1" class="selected">1</li><li id="TimeLong_input_2">2</li><li id="TimeLong_input_5">5</li><li id="TimeLong_input_10">10</li><li id="TimeLong_input_20">20</li><li id="TimeLong_input_30">30</li><li id="TimeLong_input_50">50</li></ul></div>
                  <select style="display: none;" name="TimeLong" id="TimeLong">
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="5">5</option>
                      <option value="10">10</option>
                      <option value="20">20</option>
                      <option value="30">30</option>
                      <option value="50">50</option>
                  </select>
                </div>
                </div>
            </div>
            <div class="tool_unit">年</div>
          </div>
          <div class="tool_item_btn"><input type="submit" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnCalculate" value="计算">
                                <input type="reset" name="button" class="tool_btn" onmouseover="this.className=&#39;tool_btn_mouseover test&#39;" onmouseout="this.className=&#39;tool_btn_mouseout test&#39;" id="btnReset" value="重置" style="margin-left:20px;"></div>
        </div>
        <div class="tool_right">
          <dl class="tool_result clearfloat">
            <dt>计算结果</dt>
            <dd>
              <div class="result_cpi" style="display:none;" id="ResultShow">
                <ul>
                <li><h4>CPI计算结果</h4><div id="CPIResults"></div></li>
                <li><h4>您可以选择投资紫薇金融</h4><div id="TDResults"></div></li>
                </ul>
              </div>
              <div class="result_cpi" id="cpi_initial">
                <ul>
                <li><h4>CPI计算结果</h4><div class="cpi_jg"><span class="initial_hui">你可以跑不赢刘翔，但一定要跑赢CPI</span><br></div>根据当前CPI指数推算，数据仅供参考</li>
                <li><h4>您可以选择投资紫薇金融</h4><div class="cpi_jg"><span class="initial_hui">算一算吧，紫薇金融不会让您失望的！</span><br></div>根据当前紫薇金融指数推算，数据仅供参考</li>
                </ul>
              </div>
            </dd>
          </dl>
          <dl class="tool_result clearfloat">
            <dt>2014年各季度CPI同比涨幅</dt>
            <dd>
              <div class="hgjhb_c" id="divCPIReport" style="height: 200px;"><div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 500px; height: 200px; text-align: left; line-height: normal; z-index: 0; font-family: &#39;Lucida Grande&#39;, &#39;Lucida Sans Unicode&#39;, Verdana, Arial, Helvetica, sans-serif; font-size: 12px;"><svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="500" height="200"><defs><clippath id="highcharts-1"><rect fill="none" x="0" y="0" width="480" height="131"></rect></clippath></defs><rect rx="5" ry="5" fill="#FFFFFF" x="0" y="0" width="500" height="200" stroke-width="0.000001"></rect><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path fill="none" d="M 10 106.5 L 490 106.5" stroke="#C0C0C0" stroke-width="1" zIndex="1"></path><path fill="none" d="M 10 40.5 L 490 40.5" stroke="#C0C0C0" stroke-width="1" zIndex="1"></path><path fill="none" d="M 10 171.5 L 490 171.5" stroke="#C0C0C0" stroke-width="1" zIndex="1"></path></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 249.5 171 L 249.5 176" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 369.5 171 L 369.5 176" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 489.5 171 L 489.5 176" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 129.5 171 L 129.5 176" stroke="#C0D0E0" stroke-width="1"></path><path fill="none" d="M 10 171.5 L 490 171.5" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-axis" zIndex="2"></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series" visibility="visible" zIndex="0.1" transform="translate(10,40)" clip-path="url(#highcharts-1)"><path fill="none" d="M 60 52.4 L 180 52.4 L 300 39.3 L 420 36" stroke="black" stroke-width="5" zIndex="1" isShadow="true" stroke-opacity="0.049999999999999996" transform="translate(1, 1)"></path><path fill="none" d="M 60 52.4 L 180 52.4 L 300 39.3 L 420 36" stroke="black" stroke-width="3" zIndex="1" isShadow="true" stroke-opacity="0.09999999999999999" transform="translate(1, 1)"></path><path fill="none" d="M 60 52.4 L 180 52.4 L 300 39.3 L 420 36" stroke="black" stroke-width="1" zIndex="1" isShadow="true" stroke-opacity="0.15" transform="translate(1, 1)"></path><path fill="none" d="M 60 52.4 L 180 52.4 L 300 39.3 L 420 36" stroke="#ffc502 " stroke-width="2" zIndex="1"></path></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(10,40)" clip-path="none"><path fill="#ffc502 " d="M 420 32 C 425.328 32 425.328 40 420 40 C 414.672 40 414.672 32 420 32 Z" stroke="#FFFFFF" stroke-width="0.000001"></path><path fill="#ffc502 " d="M 300 35.3 C 305.328 35.3 305.328 43.3 300 43.3 C 294.672 43.3 294.672 35.3 300 35.3 Z" stroke="#FFFFFF" stroke-width="0.000001"></path><path fill="#ffc502 " d="M 180 48.4 C 185.328 48.4 185.328 56.4 180 56.4 C 174.672 56.4 174.672 48.4 180 48.4 Z" stroke="#FFFFFF" stroke-width="0.000001"></path><path fill="#ffc502 " d="M 60 48.4 C 65.328 48.4 65.328 56.4 60 56.4 C 54.672 56.4 54.672 48.4 60 48.4 Z" stroke="#FFFFFF" stroke-width="0.000001"></path></g></g><text x="250" y="25" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:16px;color:#3E576F;fill:#3E576F;" text-anchor="middle" class="highcharts-title" zIndex="4"><tspan x="250">单位(%)</tspan></text><g class="highcharts-data-labels" visibility="visible" zIndex="6" transform="translate(10,40)"><g zIndex="1" style="" transform="translate(48,28)" visibility="inherit"><rect rx="0" ry="0" fill="none" x="0" y="0" width="24" height="24" stroke-width="0.000001"></rect><text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;line-height:14px;fill:#666;" zIndex="1"><tspan x="3">2.4</tspan></text></g><g zIndex="1" style="" transform="translate(168,28)" visibility="inherit"><rect rx="0" ry="0" fill="none" x="0" y="0" width="24" height="24" stroke-width="0.000001"></rect><text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;line-height:14px;fill:#666;" zIndex="1"><tspan x="3">2.4</tspan></text></g><g zIndex="1" style="" transform="translate(288,15)" visibility="inherit"><rect rx="0" ry="0" fill="none" x="0" y="0" width="24" height="24" stroke-width="0.000001"></rect><text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;line-height:14px;fill:#666;" zIndex="1"><tspan x="3">2.8</tspan></text></g><g zIndex="1" style="" transform="translate(408,12)" visibility="inherit"><rect rx="0" ry="0" fill="none" x="0" y="0" width="24" height="24" stroke-width="0.000001"></rect><text x="3" y="15" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;color:#666;line-height:14px;fill:#666;" zIndex="1"><tspan x="3">2.9</tspan></text></g></g><g class="highcharts-axis-labels" zIndex="7"><text x="70" y="185" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:100px;color:#666;line-height:14px;font:normal 12px Arial;fill:#666;" text-anchor="middle" visibility="visible"><tspan x="70">第一季度</tspan></text><text x="190" y="185" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:100px;color:#666;line-height:14px;font:normal 12px Arial;fill:#666;" text-anchor="middle" visibility="visible"><tspan x="190">第二季度</tspan></text><text x="310" y="185" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:100px;color:#666;line-height:14px;font:normal 12px Arial;fill:#666;" text-anchor="middle" visibility="visible"><tspan x="310">第三季度</tspan></text><text x="430" y="185" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Verdana, Arial, Helvetica, sans-serif;font-size:11px;width:100px;color:#666;line-height:14px;font:normal 12px Arial;fill:#666;" text-anchor="middle" visibility="visible"><tspan x="430">第四季度</tspan></text></g><g class="highcharts-axis-labels" zIndex="7"></g><g class="highcharts-tracker" zIndex="9"><g visibility="visible" zIndex="1" transform="translate(10,40)"><path fill="none" d="M 0 0" isTracker="true" stroke-linejoin="bevel" visibility="visible" stroke-opacity="0.000001" stroke="rgb(192,192,192)" stroke-width="NaN" style=""></path></g></g></svg></div></div>
            </dd>
          </dl>
        </div>
      </div>
    </div>

    </div>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
	</body>
</html>