<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


    <title>${systemConfig.metaTitle} - 自动投标</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/register.js"></script>
    <script type="text/javascript" src="${base}/js/user/check.js"></script>
    <script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
    <script type="text/javascript" src="${base}/js/user/financeCheck.js"></script>
    <script type="text/javascript" src="${base}/js/user/authentication.js"></script>
    <script type="text/javascript">var m1 = "个人中心", m2 = "账户总览", m3 = "个人资料";</script>
    <style type="text/css">
        textarea {
            border-style: solid;
            border-width: 1px;
            border-color: #a8a8a8 #d8d8d8 #d8d8d8 #a8a8a8;
            border-radius: 3px;
            background-color: #f4f4f4;
            padding: 5px;
            overflow: auto;
            resize: none;
        }
    </style>

</head>
<body>
<!--整体布局
<div class="docment docment-711-234">-->

<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<div class="main">
    <div class="user-cont">
        <!--左侧三级导航条 -->
        <div class="user-name-nav fl">
        <#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_user.ftl">
	   	<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_usernav.ftl">
        </div>
        <div class="user-cont-fr fr">
            <div class="user-cont-right">
                <h2 class="big-tit">自动投标</h2>
                <div class="cont-list">
                    <div class="user-infor">
                        <form action="${base}/user/saveAutoBidInfoBpCustMember.do" method="post" id="autobidForm">
                            <ul>
			<#if bidAuto==null>
                <li class="none" style="display:none;">
                    <span style="color:#f29b76">新注册的用户，才能显示自动投标！</span>
                </li>
            <#else>
			<li class="none" style="display:none;">
            <#--<span style="text-align:center;">账户可用余额：<#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>0.00<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if><#else>0.00</#if>元  &nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span  style="text-align:center;"><span>每次投标金额：</span><#if bidAuto!=null><#if bidAuto.bidMoney==0><span id="new_bidMoney">0.00</span><#else><span id="new_bidMoney">${bidAuto.bidMoney?string(',###.00')}</span></#if><#else><span id="new_bidMoney">0.00</span></#if>元</span></br>
				<span style="text-align:center;"><span>利息范围：</span><span id="new_interest">${bidAuto.interestStart!}%-${bidAuto.interestEnd!}%</span>  &nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span style="text-align:center;"><span>借款期限：</span><span id="new_period">${bidAuto.periodStart!}月-${bidAuto.periodEnd!}月</span></span></br>
				<span style="text-align:center;"><span>信用等级范围：</span><span id="new_rate">${bidAuto.rateStartShow!}-${bidAuto.rateEndShow!} </span> &nbsp;&nbsp;&nbsp;&nbsp;</span>
				<span style="text-align:center;"><span>账户保留金额：</span><#if bidAuto!=null><#if bidAuto.keepMoney==0><span id="new_keepMoney">0.00</span><#else><span id="new_keepMoney">${bidAuto.keepMoney?string(',###.00')}</span></#if><#else><span id="new_keepMoney">0.00</span></#if>元</span>-->
            </li>
			<li class="none">
                <span>当前自动投标状态：<font color="red"><#if bidAuto.isOpen==0>关闭<#else>开放</#if></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				<#if bidAuto.isOpen==0>
				<a href="#signup" rel="leanModal" name="signup"><span id="autobidopen"
                                                                      style="background:#fd7754; border-radius:3px;color:#fff; text-decoration:none; font-size:12px;padding:2px 8px;">开启自动投标</span></a>
                <#else>
				<a href="${base}/user/closeBidAutoBpCustMember.do"
                   style="background:#55b14a; color:#fff; text-decoration:none; font-size:12px;padding:2px 8px;">关闭自动投标</a></#if>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='red'>注：自动投标不会使用账户中的优惠券</font>
            </li>
			<li class="none">
                <span class="accredi" style="background:none; color:#555;">账户可用余额：</span>
                <span class="accredi-tit"
                      style="text-align:left;width:130px;"><#if bpCustMember!=null><#if bpCustMember.availableInvestMoney==0>
                    0.00<#else><#if bpCustMember.availableInvestMoney lt 1000>${bpCustMember.availableInvestMoney}<#else>${bpCustMember.availableInvestMoney?string(',###.00')}</#if></#if><#else>
                    0.00</#if>元</span>
                <span class="accredi-con" id="totalMoney">（大于等于50才可以开启自动投标工具）</span>
                <span class="accredi-open" style="border:0;"><font
                        color="red"><#if backMessge??><#if backMessge.get('availableInvestMoney')??>${backMessge.get('availableInvestMoney')}</#if></#if></font></span>
            </li>
			 <li class="none">
                 <span class="accredi" style="padding-top:5px;background:none; color:#555;">每次投标金额：</span>
                 <span class="accredi-tit" style="text-align:left;width:95px;"><input type="text" maxlength="7"
                                                                                      name="bidMoney"
                                                                                      id="bidAuto.bidMoney"
                                                                                      class="colorful1"
                                                                                      style="width:50px;height:20px;"
                                                                                      value="${bidAuto.bidMoney!}"> 元</span>
                 <span class="accredi-con" style="padding-top:5px;" id="bidMoney"></span>
                 <span class="accredi-open" style="border:0;"><font
                         color="red"><#if backMessge??><#if backMessge.get('bidMoney')??>${backMessge.get('bidMoney')}</#if></#if></font></span>
             </li>
			<li class="none">
                <span class="accredi" style="padding-top:5px;background:none; color:#555;">利率范围：</span>
                <span class="accredi-tit" style="text-align:left;">
				<input type="text" id="min_lilv" name="interestStart" class="colorful1" style="width:30px;height:20px;"
                       maxlength="4" value="${bidAuto.interestStart!}" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" > %-
				<input type="text" id="max_lilv" name="interestEnd" class="colorful1" style="width:30px;height:20px;"
                       maxlength="4" value="${bidAuto.interestEnd!}" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" > %</span>
                <span id="interest_rate" style="padding-top: 5px;background: none;color: rgb(255, 0, 0);margin-left:20px;">
					利率范围应该在${bidAuto.interestStart!}%-${bidAuto.interestEnd!}%之间，填写的数值必须是正整数。
				</span>
                <span class="accredi-con" style="padding-top:5px;" id="interest"></span>
                <span class="accredi-open" style="border:0;"><font
                        color="red"><#if backMessge??><#if backMessge.get('interestStartAndEnd')??>${backMessge.get('interestStartAndEnd')}</#if></#if></font></span>
            </li>
			<li>
                <span class="accredi" style="background:none; color:#555;">借款期限：</span>
                <span class="accredi-tit" style="width:350px;">
				<div class="divselectall" style="width:78px;float:left;">
					<cite class="bg"><span>${bidAuto.periodStart!}</span><!--已经在js里面写好了，只需给name赋值就可以-->
			  		<input name="periodStart" type="hidden" value="0"/></cite>
					<ul class="ul-list">
					<#list listPeriod as period>
					<li><a class="selt" href="javascript:;" selectid="${period}"><span
                            id="periods1">${period}</span></a></li>
                    </#list>
                    </ul>
				</div>
				<em style="display:inline-block; float:left;width:30px;font-size:16px; height:20px;font-style:normal;">月-</em>
				<div class="divselectall" style="width:78px;float:left">
					<cite class="bg"><span>${bidAuto.periodEnd!}</span><!--已经在js里面写好了，只需给name赋值就可以-->
			  		 <input name="periodEnd" type="hidden" value="36"/></cite>
					<ul class="ul-list">
					<#list listPeriod as period>
					<li><a class="selt" href="javascript:;" selectid="${period}"><span
                            id="periods2">${period}</span></a></li>
                    </#list>

                    </ul>
				</div>
                <#--	<em style="display:inline-block; float:left;width:30px; padding-top:10px;font-size:16px;height:20px; font-style:normal;">月</em>
                    <select  name="bidAuto.periodStart" >
                        <#list listPeriod as period>
                            <option value="${period}"  selected="<#if (bidAuto.periodStart==period)>selected</#if>">${period}</option>
                        </#list>
                    </select>月 -
                    <select  name="bidAuto.periodEnd" >
                        <#list listPeriod as period>
                            <option value="${period}"  selected="<#if (bidAuto.periodEnd==period)>selected</#if>">${period}</option>
                        </#list>
                    </select>月</span> -->
				<span class="accredi-open" style="border:0;"><font
                        color="red"><#if backMessge??><#if backMessge.get('periodStartAndEnd')??>${backMessge.get('periodStartAndEnd')}</#if></#if></font></span>
				<span id="period"></span>
            </li>
			<li class="none">
                <span class="accredi" style="background:none; color:#555;">信用等级范围：</span>
                <span class="accredi-tit" style="width:350px;">
				<div class="divselectall" style="width:78px;float:left;">
					<cite class="bg"><span><#if  bidAuto.rateEndShow==null>HR<#else>${bidAuto.rateStartShow}</#if></span>
                        <!--已经在js里面写好了，只需给name赋值就可以-->
			  		<input id="rateStart" name="rateStart" type="hidden" value="${bidAuto.rateStart!}"/></cite>
					<ul class="ul-list1">
					<#list plKeepCreditlevel as list>
					<li><a class="selt" href="javascript:;" selectid="${list.creditLevelId}" value="1">${list.name}</a></li>
                    </#list>
                    </ul>
				</div>
				<em style="display:inline-block; float:left;width:30px;font-size:16px; height:20px;padding-top:10px;font-style:normal;">-</em>
				<div class="divselectall" style="width:78px;float:left">
					<cite class="bg"><span><#if  bidAuto.rateEndShow==null>AA<#else>${bidAuto.rateEndShow}</#if></span>
                        <!--已经在js里面写好了，只需给name赋值就可以-->
			  		 <input id="rateEnd" name="rateEnd" type="hidden" value="${bidAuto.rateEnd!}"/></cite>
					<ul class="ul-list1">
					<#list plKeepCreditlevel as list>
					<li><a class="selt" href="javascript:;" selectid="${list.creditLevelId}" value="7">${list.name}</a></li>
                    </#list>
                    </ul>
				</div> 
				</span>
                </span>
                <span class="accredi-con" id="rate" style="border:0;"></span>
            </li>
			<li>
                <span class="accredi" style="padding-top:5px; background:none; color:#555;">账户保留金额：</span>
                <span class="accredi-tit" style="text-align:left;width:90px;"><input type="text" name="keepMoney"
                                                                                     class="colorful1"
                                                                                     style="width:30px;height:20px;"
                                                                                     maxlength="8"
                                                                                     value="${bidAuto.keepMoney!}"> 元</span>
                <span class="accredi-con" style="padding-top:5px;" id="keepMoney"></span>
                <span class="accredi-open" style="border:0;"><font
                        color="red"><#if backMessge??><#if backMessge.get('keepMoney')??>${backMessge.get('keepMoney')}</#if></#if></font></span>
            </li>
			<li class="none">
                <span class="accredi1" style="margin-left:100px;"><input type="submit" maxlength="8"
                                                                         class="buttonorange" style="width:100px;"
                                                                         value="保存"></span>
                <span class="accredi-tit"></span>
                <span class="accredi-con" id="saveLoad" style="border:0;"></span>
            </li>
            </#if>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--end: Container -->

      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

<!--充值弹框-->
	 <#include "/WEB-INF/template/common/user/autoBidBox.ftl">

<!--充值弹框End-->

<script type="text/javascript">
    $(function () {
        $.divselect(".divselectall");
        //异步开启自动投标功能
        $("#autobidopen").click(function () {
            $('a[rel*=leanModal]').leanModal({top: 200});
            $.ajax({
                type: "POST",
                url: "${base}/creditFlow/financingAgency/autoBidPlBidPlan.do",
                dataType: 'json',
                beforeSend: function () {
                    $("#bidLoad").append('<img src="${base}/theme/${systemConfig.theme}/images/loading.gif"/><span>检查中请等待...</span>');
                },
                success: function (responseText, statusText) {
                    if (statusText == "success") {
                        $("#bidLoad").empty();
                        var responseText = $.parseJSON(responseText);
                        $("#recharge").html(responseText.msg);
                        if (responseText.success) {
                            $("#button_div").css("display", "block");
                            $("#submitrecharge").click(function () {
                                $("#submitrecharge").unbind("click").attr("value", "   开通成功  »   ").click(function () {
                                    location.href = ""
                                });
                                $("#updatemoney").unbind("click").attr("value", "   开通失败  »   ").click(function () {
                                    location.href = ""
                                });
                                $("#lean_overlay_close").unbind("click").click(function () {
                                    location.href = ""
                                });
                                window.open('${base}/user/openBidAutoBpCustMember.do?whetherOpen=1&retUrl=user/automaticbidBpCustMember.do', '', '')
                            });
                        } else {
                            $("#recharge").html(responseText.msg);
                        }
                    } else {
                        $("#recharge").html(responseText.msg);
                    }

                },
                error: function (responseText) {
                }
            });

        });

        //异步保存数据

        $('#autobidForm').submit(function () {
            var min = $("#min_lilv").val();
            var max = $("#max_lilv").val();

            if(min >= ${bidAuto.interestStart!} && max <= ${bidAuto.interestEnd!} && parseInt(min) <= parseInt(max)){
              /*  alert("ok")*/
                debugger;
                var t = $('#rateStart').val();
                var u = $('#rateEnd').val();
                if (t == null || t == "") {
                    $('#rateStart').val(1);
                }
                if (u == null || u == "") {
                    $('#rateEnd').val(6);
                }
                var aQuery = $("#autobidForm").formSerialize();
                jQuery.ajax({
                    url: "${base}/user/saveAutoBidInfoBpCustMember.do",
                    type: "POST",
                    data: aQuery,
                    beforeSend: function () {
                        $("#saveLoad").html('<img src="${base}/theme/${systemConfig.theme}/images/loading.gif"/><span style="margin-top:9px;margin-left:5px;">检查中请等待...</span>');
                    },
                    success: function (responseText, statusText) {
                        var responseText = $.parseJSON(responseText);
                        if (responseText.success) {
                            $("#saveLoad").html('<img style="margin-left:5px;margin-top:10px" src="${base}/theme/${systemConfig.theme}/images/successicon.jpg"/>');
                            clear();
                            setData(responseText);
                        } else {
                            $("#saveLoad").html("");
                            $("#bidMoney").html(responseText.bidMoney);
                            $("#interest").html(responseText.interest);
                            $("#period").html(responseText.period);
                            $("#rate").html(responseText.rate);
                            $("#keepMoney").html(responseText.keepMoney);
                        }
                    }
                });
            }else{

            }
            $("#interest_rate").html('请在规定范围内填写利率');
            return false;
        });

    });

    //清空数据
    function clear() {
        $("#bidMoney").html("");
        $("#interest").html("");
        $("#period").html("");
        $("#rate").html("");
        $("#keepMoney").html("");
    }

    //设置成后的数据
    function setData(responseText) {
        $("#new_bidMoney").html(responseText.bidMoney);
        $("#new_interest").html(responseText.interestStart + "%-" + responseText.interestEnd + "%");
        $("#new_period").html(responseText.periodStart + "月-" + responseText.periodEnd + "月");
        $("#new_rate").html(responseText.rateStart + "-" + responseText.rateEnd + "");
        $("#keepMoney").html("");
    }




</script>
</body>
</html>