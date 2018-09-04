<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${systemConfig.metaTitle}</title>
    <meta property="qc:admins" content="1451334700445346756164116375" />
    <meta property="wb:webmaster" content="2516fb302e734849" />
    <meta name="baidu-site-verification" content="fAfDTP2TOh" />
    <meta name="description" content="${systemConfig.metaTitle} ,专业的互联网投融资平台,中发展信（北京）投资管理有限公司">
    <meta name="keywords" content="${systemConfig.metaTitle} ,投资 融资 债权转让,中发展信（北京）投资管理有限公司">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/index.css" />
    <script type="text/javascript" src="${base}/js/user/login.js"></script>
    <script type="text/javascript" src="${base}/js/user/check.js"></script>
    <script type="text/javascript" src="${base}/js/sign.js"></script>
    <script type="text/javascript" src="${base}/js/index.js"></script>

    <script type="text/javascript">var m1="首页",m2="",m3="";</script>



    <script type="text/javascript">

        $(document).ready(
                //进度条触发
                function(){
				<#------
                    <#list pager.list as list0 >
                    //$("#pb${list0.bidId}").progressBar();
                    $('#plan${list0.bidId}').css("background-position","${(list0.progress)*-54}px 0");
                    </#list>
                       --->
                    $("#bybMoneyPlanoneprogress").progressBar();
                    $("#bybMoneyPlantwoprogress").progressBar();
                    $("#bybMoneyPlantreeprogress").progressBar();
				<#if bybMoneyPlanone !=null>
                    var afterTime=$("#bybMoneyPlanoneafterTime").val();
                    intDiff = parseInt(afterTime);//倒计时总秒数量
                    timer(intDiff);
				</#if>
				<#if bybMoneyPlantwo !=null>
                    var afterTime1=$("#bybMoneyPlantwoafterTime").val();
                    intDiff1 = parseInt(afterTime1);//倒计时总秒数量
                    timer1(intDiff1);
				</#if>
				<#if bybMoneyPlantree !=null>
                    var afterTime2=$("#bybMoneyPlantreeafterTime").val();
                    intDiff2 = parseInt(afterTime2);//倒计时总秒数量
                    timer2(intDiff2);
				</#if>

                }

        );
        $(function(){

            $('a[rel*=leanModal]').leanModal({ top : 200 });
        });
        $(function() {
            // 首页广告图切换
            changeBannerSimple();

            // 合作伙伴图标水平循环滚动
            scrollPartner();
            // 理财排行榜tab切换
            $("#rightTitls li").click(function(){
                $(this).addClass("current").siblings().removeClass("current");
                $("#J_conbox ul").eq($(this).index()).addClass("current").siblings().removeClass();

            });

        });
        function scroll(){
            $("#announcment").append($("#announcment>a:first"));
            $("#announcment>a").css("display","none");
            $("#announcment>a:first").css("display","block");
        }
        setInterval("scroll()",5000)
    </script>
    <script type="text/javascript">
        $(document).ready(
                //进度条触发
                function() {
				<#if pager??>
					<#if pager.list??>
						<#list pager.list as list0 >
                            $("#pb${list0.bidId}").progressBar();
						</#list>
						<#list listMoneyPlan as list1 >
                            $("#pb${list1.mmplanId}").progressBar();
						</#list>
					</#if>
				</#if>
                }


        );


    </script>
    <style>
    *{ font-style:normal}
    .progress span {
    display: inline-block;
    height: 8px;
    float: left;
    background: #49c9c4;
    border-radius: 7px;
    }
    </style>

</head>
<body>
<#--<script type='text/javascript' src='//trusted.shuidi.cn/trusted.js?id=2081&jump=0&style=1'></script>-->
<#--<script type="text/javascript" src="//trusted.shuidi.cn/trusted.js?id=2081&jump=0&style=1"></script>-->
 <!--topbar -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/newtopbar.ftl">
 <!--header -->
<#include "/WEB-INF/template/${systemConfig.theme}/layout/newlogobar.ftl">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/banner.ftl">

<!-------------------------实时数据----------------------------->
<div class="showcase">
    <div class="warpcase">
		<div class="caseblok title">
			<span><a href="javascript:;" <#--style="cursor:auto;"-->></a>实时数据播报</span>
			<#--<span><a href="${base}/creditFlow/financingAgency/bidSaleContentPlBidSale.do">实时数据播报</a></span>-->
		</div>
		
		<div class="caseblok" style="border-left: 1px solid #ddd;">
			<p><span>注册用户</span></p>
			<p><em><span id="indexCount" style="color:#f65541;font-size:24px;font-weight: bold;">${listcount}</span></em>人</p>
		</div>
		
		<div class="caseblok">
			<p><span>已撮合融资</span></p>
			<p><em><span id="totalDealMoney" style="color:#f65541;font-size:24px;font-weight: bold;"><#if (b8>=10000)>${(b8/10000)?string(",##0.00#")}</span></em>万元<#else>${b8}</span></em>元</#if></p>
		</div>
		
		<div class="caseblok">
			<p><span>为客户赚取收益</span></p>
			<p><em><span id="totalUserInMoney" style="color:#f65541;font-size:24px;font-weight: bold;"><#if (b3>=10000)>${(b3/10000)?string(",##0.00#")}</span></em>万元<#else>${b3}</span></em>元</#if></p>
		</div>
		<div class="caseblok">
		<p><span>平台安全运营</span></p>
		<p><em><span style="color:#f65541;font-size:24px;font-weight: bold;">${day}</span></em>天</p>
			<#--<p><span>累计访问次数</span></p>
			<p><em><span id="visitorCount">0</span></em>次</p>-->
		</div>
	</div>
</div>

<div class="mainbg">
<div class="guarantee">
	<div class="box">
		<dl>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/xy-index/advantage1.png"></dt>
			<dd>
				<b>严选项目</b>
				合作机构对每个借款项目进行实地<br>考察核实后，升升投专业风控再次审核
			</dd>
		</dl>
		<dl>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/xy-index/advantage2.png"></dt>
			<dd>
				<b>风险分散</b>
				各资产类别风险不同，<br>借款标的额度小，风险总体较低
			</dd>
		</dl>
		<dl>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/xy-index/advantage3.png"></dt>
			<dd>
				<b>合规保障</b>
				推动借款标的公示信息，<br>杜绝虚假，拒绝自融，拒绝非法集资
			</dd>
		</dl>
		<dl>
			<dt><img src="${base}/theme/${systemConfig.theme}/images/xy-index/advantage4.png"></dt>
			<dd>
				<b>资金安全</b>
				资金全程由富滇银行管理，<br>用户资金与平台自有资金严格隔离
			</dd>
		</dl>
	</div>
</div>
      <div <#--class="main"--><#-- style="margin:10px auto;"-->>

    <!-------------------------宣传图------------------------------->
    <!--<div class="feature">
		 <a class="fea1" href="javascript:void(0)">
			<i></i>
			<h3>理财省心</h3>
			<span>运用技术分散投资帮助<br>投资人进入保障理财计划</span>
		</a> 
		 <a class="fea1" href="javascript:void(0)">
			<i></i>
			<h3>收益放心</h3>
			<span>历史收益可达9%-18%<br>祝您轻松获盈利</span>
		</a> 
		 <a class="fea1" href="javascript:void(0)">
			<i></i>
			<h3>简单贷款</h3>
			<span>专业的咨询，全面贴心<br>的服务贷款，就这么简单</span>
		</a> 
		 <a class="fea1" href="javascript:void(0)">
			<i></i>
			<h3>快捷办理</h3>
			<span>一个电话，专业的客户经<br>理为您量身定制贷款方案</span>
		</a> 
	</div>-->
     <!--start:理财跟理财排行榜--->
     <!---首页改版start---->
     
      <div class="box Novice my-Novice" style="height:255px;">
        <div class="floatL fl new">
           <#-- <h1>新手专区</h1>
            <p style="margin-bottom:0;">新手专享·高收益</p>-->
            <!--<a target="_blank" href="${base}/creditFlow/financingAgency/listPlBidPlan.do" class="new" onclick="">查看更多»</a>-->
        </div>
        <div class="floatR fr"  style="width:931px;height:255px;border-right:0;">
        	<#list newcomerList as list>
        	<!-- currList-->
	            <div class="NoviceBox" style="width:414px;"">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                   <#-- <em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
								<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                                </em>
<#--<i>-->
				   <#--<#if list.novice==1><font class="font-one">新</font></#if>-->
		           <#--<#if list.addRate!=0&&list.addRate!=""><font class="font-two">息</font></#if>-->
		           <#--<#if list.coupon==1><font class="font-three">券</font></#if>-->
<#--</i>-->
                        </span>
                        <span class="xydj"><span>${list.keepCreditlevelName!}</span></span>
	                </p>
	                <dl>
	                    <dt>
	 							<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
									${list.yearInterestRate}<span>%</span>
								</#if>
	 							<font><em>${list.loanLife}</em></font>
	 							<i>预期年化收益率</i>
							   <b>项目期限</b>
	 					</dt>
						<dd>

						</dd>
                        <#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
                        </dd>-->
						<#-- <dd style="margin-top:10px;margin-left: 10px;">
					   <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
						   <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>

					   </dd>-->
						<dd style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
	                    <dd  style="width:392.39px;margin-top:20px;" class="progressBar">
	                        <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
	                        <span></span>
	                    </dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <!--<div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>-->
	            </div>
	         </#list>
        </div>

        <div class="clear"></div>
    </div>
	  <div class="box Novice  my-Novice">
        <div class="floatL fl short">
          <#--  <h1>短期灵活</h1>
            <p>投资短期·门槛低</p>-->
            <a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank" class="short" onclick="">查看更多»</a>
        </div>
        <div class="floatR fr" >
           <#list shortList as list>
           		<#if list_index < 2>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                    <#--<em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
						<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                        </em>
                        </span>
                    <sapn class="xydj"><span>${list.keepCreditlevelName}</span></sapn>


	                </p>
	                <dl>
	                    <dt>
							<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
								${list.yearInterestRate}<span>%</span>
							</#if>  <font><em>${list.loanLife}</em></font>
                            <i>预期年化收益率</i>
                            <b>项目期限</b>
	 					</dt>
						<#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借aa</span>
						</dd>-->
                        <#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
                        </dd>-->
                        <dd style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
                        </dd>
						<dd style="width:90%;margin-top:20px;" class="progressBar">
                            <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
                            <span></span>
						</dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	             <#--   <div class="label">
	                    &lt;#&ndash;  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  &ndash;&gt;
	                </div>-->
	            </div>
	            </#if>
	         </#list>
        </div>
        <div class="floatR fr"  style="border-right:0;">
            <#list shortList as list>
            	<#if (list_index>=2 && list_index<5)>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                    <#--<em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
						<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                        </em>
                        </span>
                    <sapn class="xydj"><span>${list.keepCreditlevelName!}</span></sapn>


	                </p>
	                <dl>
	                    <dt>
								<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
									${list.yearInterestRate}<span>%</span>
								</#if>    <font><em>${list.loanLife}</em></font>
                            <i>预期年化收益率</i>
                            <b>项目期限</b>
	 					</dt>
						<#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
						</dd>-->
                       <#-- <dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
						</dd>-->
						<dd style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
						<dd  style="width:90%;margin-top:20px;" class="progressBar">
                            <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
                            <span></span>
						</dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>
	            </div>
	            </#if>
	         </#list>
        </div>
        <div class="clear"></div>
    </div>
      <div class="box Novice my-Novice">
        <div class="floatL fl middle">
            <#--<h1>中期稳赚</h1>
            <p>科学理财·低风险</p>-->
            <a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank" class="middle" onclick="">查看更多»</a>
        </div>
        <div class="floatR fr" >
            <#list currList as list>
              <#if list_index < 2>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                   <#-- <em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
						<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                        </em>
                        </span>
                    <sapn class="xydj"><span>${list.keepCreditlevelName!}</span></sapn>
	                </p>
	                <dl>
	                    <dt>
								<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
									${list.yearInterestRate}<span>%</span>
								</#if>   <font><em>${list.loanLife}</em></font>
                            <i>预期年化收益率</i>
                            <b>项目期限</b>
	 					</dt>
						<#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
						</dd>-->
                        <#--<dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
						</dd>-->
						<dd  style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
						<dd  style="width:90%;margin-top:20px;" class="progressBar">
                            <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
                            <span></span>
						</dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>
	            </div>
	           </#if>
	         </#list>
        </div>
        <div class="floatR fr"  style="border-right:0;">
            <#list currList as list>
           	 <#if (list_index>=2 && list_index<5)>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                   <#-- <em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
						<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                        </em>
                        </span>
					<sapn class="xydj"><span>${list.keepCreditlevelName!}</span></sapn>
	                </p>
	                <dl>
	                    <dt>
							<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
								${list.yearInterestRate}<span>%</span>
							</#if>		<font><em>${list.loanLife}</em></font>
	 							<i>预期年化收益率</i><b>项目期限</b>
	 					</dt>
                       <#-- <dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
                        </dd>-->
                       <#-- <dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
                        </dd>-->
						<dd  style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
	                    <dd  style="width:90%;margin-top:20px;" class="progressBar">
	                        <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
	                        <span></span>

	                    </dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>
	            </div>
	           </#if>
	         </#list>
        </div>
        <div class="clear"></div>
    </div>
      <div class="box Novice my-Novice">
        <div class="floatL fl long">
           <#-- <h1>长期优选</h1>
            <p>长期投资·稳增值</p>-->
            <a href="${base}/creditFlow/financingAgency/listPlBidPlan.do" target="_blank" class="long">查看更多»</a>
        </div>
        <div class="floatR fr"  >
           <#list longList as list>
             <#if list_index < 2>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                    <#--<em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
							<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
							</em>
                        </span>
                    <sapn class="xydj"><span>${list.keepCreditlevelName!}</span></sapn>
	                </p>
	                <dl>
	                    <dt>
								<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
									${list.yearInterestRate}<span>%</span>
								</#if> <font><em>${list.loanLife}</em></font>
                            <i>预期年化收益率</i><b>项目期限</b>
	 					</dt>
                       <#-- <dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
                        </dd>-->
                      <#--  <dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
                        </dd>-->
						<dd  style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
	                    <dd  style="width:90%;margin-top:20px;" class="progressBar">
	                        <i><b style="" class="progressBar_pending"></b></i><em>${list.progress}%</em>
	                        <span></span>
	                    </dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>
	            </div>
	           </#if>
	         </#list>
        </div>
        <div class="floatR fr"  style="border-right:0;">
            <#list longList as list>
              <#if (list_index>=2 && list_index<5)>
	            <div class="NoviceBox">
	                <p><a href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" target="_blank" onclick="">${list.bidProName}</a>
	                   <#-- <em class="projectCodeClass">&nbsp;${list.bidProNumber}</em>-->
                        <span class="sd">
						<em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                        </em>
						</span>
                    <sapn class="xydj"><span>${list.keepCreditlevelName!}</span></sapn>
	                </p>
	                <dl>
	                    <dt>
								<#if list.showRate !=null>${list.yearInterestRate - list.showRate }<span>%</span><span style="font-size: 20px">+</span>${list.showRate}<span>%<#--<b class="addinterest"></b>--></span><#else>
									${list.yearInterestRate}<span>%</span>
								</#if> <font><em>${list.loanLife}</em></font>
                            <i>预期年化收益率</i><b>项目期限</b>
	 					</dt>
                       <#-- <dd style="margin-top:10px;    margin-left: 10px;">
                            <span style="display:inline-block; width: 100%;text-align: center;font-size:18px;color:#333333">${list.bidMoney}元</span>
                            <span style="display:inline-block; width: 100%;margin-top: 22px;text-align: center;color:#333;font-weight: 100;font-style: normal;">剩余可出借</span>
                        </dd>-->
                      <#--  <dd style="margin-top:10px;    margin-left: 10px;">
                            <p class="xydj"><span>${list.keepCreditlevelName!}</span></p>
                            <span style="display:inline-block; width: 100%;margin-top: 25px;text-align: center;color:#333;font-weight: 100;font-style: normal;">信用等级</span>
                        </dd>-->
						<dd  style="margin-top:10px;margin-left:23px;">
                            <a class="invesAlink " target="_blank" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}" >
							  <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
							  <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
							  <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
							  <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
							  <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
							  <#else>
								  <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
	                                 <span class="btn_red  f_white">立即出借</span>
								  <#else> <span class="btn_yushou f_white">预售中</span></#if>
							  </#if>
                            </a>
						</dd>
	                    <dd  style="width:90%;margin-top:20px;" class="progressBar">
	                        <i><b style=""  class="progressBar_pending"></b></i><em>${list.progress}%</em>
	                        <span></span>
	                    </dd>
	                    <div class="clear"></div>
	                </dl>
                    <div class="sykcj">
                        <span style="font-size:14px;color:#808080;">剩余金额</span>
                        <span style="font-size:14px;color:#333333;margin-left:10px;">${list.afterMoney?string(',##0.00')!}元</span>
                    </div>
	                <div class="label">
	                    <#--  <a href="" target="_blank" class="aFirst"><img src="">已有29675人投资该品牌</a>  -->
	                </div>
	            </div>
	           </#if>
	         </#list>   
        </div>
        <div class="clear"></div>
    </div>
  <#--  <div class="box indexTop">
        <h2>
      		投资排行榜
      		<a class="iwant" target="_blank" href="">我要上榜!</a>
      		&lt;#&ndash; <font><span class="hover"><a href="rankingList" target="_blank">更多»</a></span></font> &ndash;&gt;
      	</h2>
        <div class="list">
            <div class="title">投资总额排行榜</div>
            <div class="content">
                <#list listall as list>
                <#if (list_index<5) >
                <div class="order">
                    <div class="number">
                        <span class="highlight">${list_index+1}</span>
                        <span>
  							<a target="_blank" href="">
  								${list.userName?substring(0,1)}***</a>
  						</span>
                    </div>
                    <span class="bold">${list.userMoney}</span>
                </div>
                </#if>
                 </#list>
            </div>
        </div>
        <div class="list">
            <div class="title">近30天投资排行榜</div>
            <div class="content">
                <#list listmonth as list>
                <#if (list_index<5) >
                <div class="order">
                    <div class="number">
                        <span class="highlight">${list_index+1}</span>
                        <span>
  							<a target="_blank" href="">
  								${list.userName?substring(0,1)}***</a>
  						</span>
                    </div>
                    <span class="bold">${list.userMoney}</span>
                </div>
                </#if>
                 </#list>
            </div>
        </div>
        <div class="list">
            <div class="title">周投资排行榜</div>
            <div class="content">
                <#list listweek as list>
                <#if (list_index<5) >
                <div class="order">
                    <div class="number">
                        <span class="highlight">${list_index+1}</span>
                        <span>
  							<a target="_blank" href="">
  								${list.userName?substring(0,1)}***</a>
  						</span>
                    </div>
                    <span class="bold">${list.userMoney}</span>
                </div>
                </#if>
                 </#list>
            </div>
        </div>
        </div>-->
       
    <div class="box last_box" style="margin-bottom:0px;width: 100%;height:270px;background-color: #ffffff;padding-top:2px;">
       <div style="width:1160px;height: 100%;margin:0 auto;">
           <div class="noticeList floatL fl">
               <div class="notceLtit">媒体报道<a target="_blank" href="${base}/article/newslistArticle.do?lid=34" >更多&nbsp;<i class="news_detail_icon"></i>&nbsp;</a></div>
               <div class="notceLcont" id="notceLcont">
                   <ul>
                	<#list listArticle as list>
						 <#if list_index<5>
		                    <li><a target="_blank" href="${base}/article/${list.id}.html" title="【媒体报道】${list.title}"><i></i> &nbsp;${list.title}</a><span>${list.createDate}</span></li>
						 </#if>
					</#list>
                   </ul>
               </div>
           </div>
           <div class="noticeList floatR fr"  style="border-right:0;">
               <div class="notceLtit">网站公告<a target="_blank" href="${base}/article/newslistArticle.do?lid=30" >更多&nbsp;<i class="news_detail_icon"></i>&nbsp;</a></div>
               <div class="notceLcont" id="notceLcont">
                   <ul>
                	<#list listArticle30 as list>
						 <#if list_index<5>
		                    <li><a target="_blank" href="${base}/article/${list.id}.html" title="【网站公告】${list.title}"><i></i> &nbsp;${list.title}</a><span>${list.createDate}</span></li>
						 </#if>
					</#list>
                   </ul>
               </div>
           </div>
           <div class="clear"></div>
	   </div>
    </div>
     <!----首页改版end---> 
    
       
   
   
   <!-------------------------我要贷款---------------------------->
     <div class="amaldar" style="height:0;padding:0;border:0;margin-bottom:0;">
		<#--<div class="ama_loan">
			<div class="title">
				<p><a href="${base}/loan/listP2pLoanProduct.do"><span>我要贷款</span></a>简单贷款，快速办理</p>
			</div>
		  <div class="ama_form">
			<form action="${base}/loan/loanApplyP2pLoanProduct.do" method="post">
				<ul>
					<li>
						<span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
						<input type="text" name="applyname">
					</li>
					<li>
						<span>贷款金额：</span>
						<input type="text" name="applymoney">
					</li>
					<li>
						<span>贷款类型：</span>
						<select name="applytype">  
						  <option value="1">车辆抵押贷</option>  
						  <option value="2">房屋抵押贷</option>  
						  <option value="3">企业经营贷</option>  
						  <option value="4">个人信用贷</option>  
						</select>
					</li>
					<li>
						<span>联系方式：</span>
						<input type="text" name="applyphone">
					</li>
					<li class="submit" style="position:relative;">
						<input type="submit" value="开始申请" style="position:absolute;left:0;cursor:pointer;" class="bdr3"> 
					</li>
				</ul>
				</form>
			</div>
		</div>-->
		<div class="newinvest hide" style="width:730px; height:229px;border:0; padding:0;">
		    <#list currList as list>
            <div class="listblok" style="margin-top:0;">
               <!--新手专享的时候显示这个 需要判断一下-->
                <div class="xinshouico">
                   <#if list.novice==1>
                  <img src="${base}/theme/${systemConfig.theme}/images/xy-index/xinshouico.png" />
                  </#if >
                </div>
                <!---->
                <div class="listtit clearfix">
                     <a href="${base}/creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId=${list.bidId}">
                       <!--<em class="bg"></em>-->
                       <span style="padding-left:20px;">${list.bidProName}</span>
                     </a>
                    <em>
								<#if (list.proKeepType)??>
								<#if list.proKeepType=="信用审核标">信用
								<#elseif list.proKeepType=="实地核查标">实地
								<#elseif  list.proKeepType="机构担保标">担保
								<#elseif list.proKeepType="福利标">福利
								<#else>信用
								</#if>
								<#else>信用
								</#if>
                    </em>
                </div>
                <div class="information">
                    <div class="listcon">
                    <span>金额:
                     <#if list.accMoney lt 10000>
                        <strong>${list.bidMoney}</strong>元
                        <#else>
                        <#assign tm = list.accMoney/10000>
						<#if tm=null><strong>0</strong><#else><strong>${tm}万</strong></#if>元
						</#if>
                        </span>
                    </div>
                    <div class="listcon">
                        <span>出借期限:<em>${list.loanLife}</em></span>
                    </div>
                    <div class="listcon" style=" margin-left:22px; ">
                        <span><#---年化利率:---><em style="font-size:30px;color:#ff9d19;!important">${list.yearInterestRate}%</em></span>
                    </div>
                </div>
                <div class="listcona f_r">
                   <div class="progressvar">
                       <div class="progress">
                         <span class="pr bdr3" title="${list.progress}%" style="width:${list.progress}%;">
                         </span>
                      </div>
                      <div style="line-height:15px;background:none;margin-left:2px;  margin-top: 26px;float:left;">${list.progress}%</div>
                    </div>
                    <a class="listbtn" href="${base}/creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId=${list.bidId}">
                      <#if list.state==2 || list.state==5|| list.state==6> <span class="btn_yellow f_white">已满标</span>
                                <#elseif list.state==3> <span class="btn_gray f_white">已流标</span>
                                <#elseif list.state==4> <span class="btn_gray f_white">已过期</span>
                                <#elseif list.state==10> <span class="btn_gray f_white">已还清</span>
                                <#elseif list.state==7> <span class="btn_gray f_white">还款中</span>
                                <#else>
                                    <#if list.preSaleTimeStr?date("yyyy-MM-dd HH:mm:ss") lt list.nowTimeStr?date("yyyy-MM-dd HH:mm:ss") >
                                     <span class="btn_red  f_white">投标中</span>
                                    <#else> <span class="btn_yushou f_white">预售中</span></#if>
                                </#if>
                    </a>
                </div>
            
            </div>
            </#list>
            </div>
	<!---------------------------->	
		<div class="consultant hide" style="width:auto; ">
			<ul>
				<!--<li>
					<div class="avatar">
						<img src="${base}/theme/${systemConfig.theme}/images/xy-index/foot01.png">
					</div>
					<p class="text_name">王经理</p>
					<p class="text_merit"><img src="${base}/theme/${systemConfig.theme}/images/xy-index/011.png">房贷通</p>
					<p class="text_motto">一站式服务，省时，省心。</p>
					<p class="text_phone"><em></em>400-6251-123&nbsp;&nbsp;转8101</p>
				</li>
				<li>
					<div class="avatar">
						<img src="${base}/theme/${systemConfig.theme}/images/xy-index/foot02.png">
					</div>
					<p class="text_name">魏经理</p>
					<p class="text_merit"><img src="${base}/theme/${systemConfig.theme}/images/xy-index/021.png">车贷通</p>
					<p class="text_motto">手续简单，费用低廉。</p>
					<p class="text_phone"><em></em>400-6251-123&nbsp;&nbsp;转8103</p>
				</li>-->
				<li>
					<div class="avatar">
						<img src="${base}/theme/${systemConfig.theme}/images/xy-index/03.png">
					</div>
					<p class="text_name">王经理</p>
					<p class="text_merit"><img src="${base}/theme/${systemConfig.theme}/images/xy-index/031.png">企贷通</p>

					<p class="text_motto">审核快速，放款及时。</p>

					<p class="text_phone"><em></em>400-6251-123&nbsp;&nbsp;转8106</p>
				</li>
				
			</ul>
		</div>
	</div>
	
	 <!-------------------------公告---------------------------->
    <#--
    	<div class="r-block selct counter">
		<div class="gray_title clearfix" style="height:44px;">
           <div rel="1" class="f_l f16 b  "><a>公司动态</a></div>
			<div rel="2" class="f_l f16 b foucs"><a>媒体报道</a></div>
			<div rel="3" class="f_l f16 b  "><a>网站公告</a></div>
			
			<div class="f_r more-box">
				<div class="f_r pr10 hide " rel="1">
	                <span style="font-weight: normal;">
                    <a href="${base}/article/newslistArticle.do?lid=40" class="f12" title="更多">更多&gt;&gt;</a>
	                </span>
		    	</div>
				<div class="f_r pr10 " rel="2">
	                <span style="font-weight: normal;">
                    <a href="${base}/article/newslistArticle.do?lid=34" class="f12" title="更多">更多&gt;&gt;</a>
	                </span>
		    	</div>
		    	<div class="f_r pr10 hide " rel="3">
	                <span style="font-weight: normal;">
                    <a href="${base}/article/newslistArticle.do?lid=30" class="f12" title="更多">更多&gt;&gt;</a>
	                </span>
		    	</div>
		    
			</div>
		</div>
		
        
		<div rel="1" class="nicai bt0 pl10 pr10 index_article hide" id="article40">
           <ul>
			<#list listArticle33 as list>
				 <#if list_index<5>
				   <li><span class="f_r">${list.createDate}</span><a href="${base}/article/newscontentArticle.do?catId=${list.id}" title="">${list.title}</a></li>
				 </#if>
			</#list>
		</ul> 
		</div>
        
		<div rel="2" class="nicai bt0 pl10 pr10 index_article " id="article34">
		<ul>
			<#list listArticle34 as list>
				 <#if list_index<5>
				   <li><span class="f_r">${list.createDate}</span><a href="${base}/article/newscontentArticle.do?catId=${list.id}" title="">${list.title}</a></li>
				 </#if>
			</#list>
		</ul>
        
		</div>
		
		<div rel="3" class="nicai bt0 pl10 pr10 index_article hide" id="article30">
		 <ul>
		  <#list listArticle30 as list>
			<#if list_index<5>
			 <li><span class="f_r">${list.createDate}</span><a href="${base}/article/newscontentArticle.do?catId=${list.id}" title="">${list.title}</a></li>
			</#if>
		</#list>
		 </ul>
		</div>
		
		
	</div>
	-->



	<#--首页弹框遮罩start-->
      <style type="text/css">
          #modal-box .text-r{text-align:right;}
          #modal-box .modal{position:absolute;left:0px;top:0px;width:100%;height:100%;background:rgba(0,0,0,0.3);z-index:99;}
          #modal-box .dialog{
              width:526px;height:620px;position:fixed;left:50%;top:50%;margin-left:-263px;margin-top:-310px;z-index:100;
          }
          #modal-box .pic{width:526px;height:620px;}
          #modal-box .close{position:absolute;right:-20px;top:-20px;color:#ccc;width:32px;height:32px;cursor:pointer;z-index:100;}
          #modal-box .a_close{position:absolute;right:28px;top:38px;width:42px;height:42px;background:rgba(0,0,0,0);cursor:pointer;}
          #modal-box .a_btn{position:absolute;left:184px;bottom:88px;width:166px;height:51px;background:rgba(0,0,0,0);cursor:pointer;}
      </style>
      <div id="modal-box" style="display:none;">
          <div class="modal"></div>
          <div class="dialog">
              <#--<span class="close">X</span>-->
			  <#--<img class="close" src="${base}/theme/proj_wenandai/images/mypic/0412icon2.png">-->
              <img class="pic" src="${base}/theme/proj_wenandai/images/mypic/jx_zhongqiu.png">
              <a href="javascript:;" class="a_close"></a>
              <a href="https://www.zxzbol.com/creditFlow/financingAgency/listPlBidPlan.do" class="a_btn" target="xiaohuang"></a>
          </div>
      </div>
      <script type="text/javascript">
          $(function(){
              $('#modal-box .modal').css("height",($(document).height()-90));


              var key="t1",value=1;
              if(sessionStorage.getItem(key)){
                  //console.log(sessionStorage.getItem(key));
                  $('#modal-box').css('display','none');
              }else{
                  //console.log(sessionStorage.getItem(key));
                  $('#modal-box').css('display','block');
                  $('.a_close').on('click',function(){
                      $('#modal-box').css('display','none');
                  });
                  $('#modal-box .a_btn').on('click',function(){
                      $('#modal-box').css('display','none');
                  });
                 /* var timer=setTimeout(function(){
                      $('#modal-box').css('display','none');
                  },3000);*/
                  sessionStorage.setItem(key,value);
			  }

          });
      </script>
	<#--首页弹框遮罩end-->


	<#--首页加息活动start-->
		<style>
			.jx_active{
				position:fixed;
				width:161px;
				height:148px;
				right:0px;
				top:69%;
				z-index:500;
			}
			.jx_active .jx_pic{
				width:100%;
				height:100%;
			}
			.time_con span{
				position:absolute;
				left:26px;
				top:118px;
				color:#ff5c49;
				font-size:18px;
				font-weight:bold;
			}
            .time_con span.jx_h{
				left:63px;
			}
            .time_con span.jx_m{
                left:101px;
            }
		</style>
		<div class="jx_active">
            <input type="hidden" id="jx_time" value=" ${time!}">
			<div class="time_con">
                <span class="jx_d">00</span>
                <span class="jx_h">00</span>
                <span class="jx_m">00</span>
			</div>
            <img src="${base}/theme/proj_wenandai/images/mypic/jx_time0.png" class="jx_pic" alt="">
		</div>
		<script>
            $(function(){
                //加息时间倒计时
                var time=parseInt($('#jx_time').val());
                var timeFn=function () {
                    if(time>0){
                        var day = parseInt(time / 1000 / 60 / 60 / 24);
                        var hour = parseInt(time / 1000 / 60 / 60 % 24);
                        var minute = parseInt(time / 1000 / 60 % 60);
                        //var seconds = parseInt(time / 1000 % 60);
                        if(day<10){
                            day='0'+day;
                        }
                        if(hour<10){
                            hour='0'+hour;
                        }
                        if(minute<10){
                            minute='0'+minute;
                        }
                        $('.jx_active .jx_d').html(day);
                        $('.jx_active .jx_h').html(hour);
                        $('.jx_active .jx_m').html(minute);
                        //$('#time').html(day + "天" + hour + "时" + minute + "分" + seconds + "秒");
                        time-=60000;
                    }
                }
                timeFn();
                var t=setInterval(timeFn, 60000);
            });
		</script>
	<#--首页加息活动end-->



	<script type="text/javascript">
		var leftTimeActInv = null;
		jQuery(function(){
			$(".selct .gray_title .b a").click(function(){
					$(".selct .gray_title .b a").parent().removeClass("foucs");
					$(this).parent().addClass("foucs");
					var rel=$(this).parent().attr("rel");
					
					$(".selct .gray_title .more-box .f_r").addClass("hide");
					$(".selct .gray_title .more-box .f_r[rel='"+rel+"']").removeClass("hide");
					
					$(".selct .nicai").addClass("hide");
					$(".selct  .nicai[rel='"+rel+"']").removeClass("hide");
				});
		});
	</script>
	
<script type="text/javascript">
	//计算器页面输入的值给后台
	function calculator1(){
	$("#calculator").submit();
	}
	function formatNum(num,n){//参数说明：num 要格式化的数字 n 保留小数位
	    //num = String(num.toFixed(n));
	    var re = /(-?\d+)(\d{3})/;
	    while(re.test(num)) num = num.replace(re,"$1,$2")
	    return num;
	}
	$(function () {
		var map1 = {};
		//实时数据播报
		//ajaxFunction("${base}/user/regSumMemberBpCustMember.do", map1, indexCount);
		
		//ajaxFunction("${base}/user/totalDealMoneyBpCustMember.do", map1, totalDealMoney);//已撮合融资
		
		//ajaxFunction("${base}/user/totalUserInMoneyBpCustMember.do", map1, totalUserInMoney);
		
	});
	function indexCount(data){
		 var result=data.result;
		 $("#indexCount").empty().html(formatNum(result,2));
	 }
	function totalDealMoney(data){
		 var result=data.result;
		 $("#totalDealMoney").empty().html(formatNum(result,2));
	 }
	 function totalUserInMoney(data){
		 var result=data.result;
		 $("#totalUserInMoney").empty().html(formatNum(result,2));
	 }
	function visitorCount(data){
		 var result=data.result;
		 $("#visitorCount").empty().html(formatNum(result,2));
	 }
</script>

	 <!--end:公告-->
	
 <!--end:主体内容--->   
</div>
</div>
<p style="font-size:15px;text-align:center;background:#fff;"><i style=" font-style: normal;margin-left:20px;color:red;vertical-align: middle;">*</i> 网贷有风险，出借需谨慎。</p>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

 <script>
/*     window.addEventListener('scroll',function(){
         var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
         if(scrollTop>300){
             //判断当滚到高度大于300时候
             alert('300');
             //这里写要触发的事件
         }
     },true);*/





     var P2pProductList = {
         progressRoll:function(){
             var scrollTop = $(document).scrollTop();
             $(".progressBar").each(function () {
                 var _this = $(this);
                 var width = _this.find('em').html();
                 if (_this.offset().top <= (scrollTop + $(window).height()) && width != '0%') {
                     _this.find('.progressBar_pending').animate({"width":width},1200);
                 }
             });
             return arguments.callee;
         }
     }
     $(function () {
         P2pProductList.progressRoll();
         $(document).scroll(function (e) {
             P2pProductList.progressRoll();
         });
     });
 </script>
</body>
</html>
