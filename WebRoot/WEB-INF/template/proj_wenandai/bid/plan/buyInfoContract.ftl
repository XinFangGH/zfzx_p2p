<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0083)http://www.renrendai.com/financeplan/getDemoFinancePlanContract!getUplanDemo.action -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>“升升投”系列服务计划协议书</title>
<style type="text/css">
html,body{ margin:0; padding:0; font-family: "微软雅黑"; font-size:12px; }
.clearfix::after { height: 0px; clear: both; display: block; visibility: hidden; content: ""; }
.clearfix { zoom: 1; }
.xywarp{ width:960px; margin:0 auto;}
.xywarp .info{ font-weight:bold; background:#dff2fc; line-height:1.5; padding:10px 40px; font-size:14px;}
.xywarp .info img{ text-align:right;}
.info p{ text-indent:30px;}
.xywarp .xycon{ border-color: #dfe6ea; border-style: solid; border-width: 0 1px;}
#content p { line-height: 18px; }
</style>
</head>
<body>
<div class="xywarp">
	<div class="xyhead">
		<div class="info clearfix">
			<div style="margin-right:10px;margin-bottom:30px;text-align: right;">
			</div>
			<div class="tit">尊敬的客户：</div>
			<p>
				升升投商务顾问（北京）有限公司（以下称"升升投"）"升升投系列服务计划"（以下称"升升投计划"）仅面向升升投的新老注册用户。“升升投”系列服务旨在为升升投的新老注册用户提供效率更高、退出更加灵活的服务，以便更好地提高出借人的资金使用效率。
			</p>
		</div>
	</div>
	<div class="xycon">
		<div id="content" style="padding: 5px 12px; font-size:12px">
			<br><br>
			<p align="center">
				<strong>升升投“升升投”D系列服务计划协议书</strong>
			</p>
			<p style="width=98%;text-align:right"> 协议编号：<span style="text-decoration:underline">${pbi.contractNo}</span></p>

            <p>甲方：<span style="text-decoration:underline">${bpCustMember.truename}</span></p>
            <p>身份证件号码：<span style="text-decoration:underline">${bpCustMember.cardcode}</span></p>
            <p>联系电话：<span style="text-decoration:underline">${bpCustMember.telphone}</span></p>

			<br>
			<p>乙方：升升投商务顾问（北京）有限公司</p>
			<#if systemConfig.companyAddress>
        		<p>${systemConfig.companyAddress}</p>
        	<#else>
            	<p>地址：北京市朝阳区来广营北苑东路中国铁建广场B座12层1205室</p>
			</#if>
			<#if systemConfig.consumerHotline>
        		<p>咨询电话：${systemConfig.consumerHotline}</p>
        	<#else>
                <p>咨询电话：4000-903-910</p>
			</#if>
			<br>
			<p>
				<strong>根据《中华人民共和国合同法》等相关法律法规的规定，甲、乙双方经友好协商，本着平等自愿、诚实信用的原则，达成如下协议：</strong>
			</p>
			<br>
			<p style="margin-left:21pt; font-size:14px; ">
				<strong>第一条：服务计划意向</strong>
			</p>
			<p style="margin-left:30pt;">
				1.1 乙方推出“出借人自动投标自动债权买卖及到开放期按相关规则退出”的服务计划，为加入服务计划的出借人提供更加贴心、便捷的服务，并将尽最大努力维护出借人的合法利益。
			</p>
			<p style="margin-left:30pt;">
				1.2 乙方作为一家在合法成立并有效存续的有限责任公司，拥有www.hurongsoft.com 网站（下称“升升投网站”或“升升投平台”）的经营权，主要通过升升投平台为互联网环境下自然人之间的借贷交易提供信息中介和居间撮合服务。
			</p>
            <p style="margin-left:30pt;">
				1.3 甲方同意加入本期服务计划，并自愿遵守升升投网站现有的规则。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第二条：服务计划金额</strong>
			</p>
			<p style="margin-left:30pt;">
				2.1 甲方以下列自有合法资金加入本期服务计划
			</p>
			<table border="1" style="margin: 0px auto; font-size:12px; border-collapse: collapse; border: 1px solid #000; width: 60%; ">
				<tbody>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px; font-weight:bold; "> 名称 </td>
						<td style="padding: 4px 10px; font-weight:bold; ">${pbi.mmName}</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">预期年化收益率</td>
						<td style="padding: 4px 10px;">${pbi.promisYearRate}%</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">申请加入时间</td>
						<td style="padding: 4px 10px;">${pbi.buyDatetime}</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">加入本金数额（单位人民币元）</td>
						<td style="padding: 4px 10px;">${pbi.buyMoney}</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">加入费率</td>
						<td style="padding: 4px 10px;"><#if plan.joinRate gt 0>${plan.joinRate}<#else>0</#if>%</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">加入费用（单位人民币元）</td>
						<td style="padding: 4px 10px;"><#if pbi.joinMoney gt 0>${pbi.joinMoney}<#else>0</#if></td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">锁定期</td>
						<td style="padding: 4px 10px;"><#if plan.lockingLimit gt 0>${plan.lockingLimit}月<#else>无</#if></td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">收益处理方式</td>
						<td style="padding: 4px 10px;"><#if pbi.investType==1>收益再投资<#else>提取主账户</#if></td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">锁定期开始日</td>
						<td style="padding: 4px 10px;">${plan.startinInterestTime}</td>
					</tr>
					<tr height="25px">
						<td width="40%" style="padding: 4px 10px;">锁定期结束日</td>
						<td style="padding: 4px 10px;">${plan.lockingEndDate}</td>
					</tr>
				</tbody>
			</table>
			<p style="margin-left:30pt;">
				2.2甲方加入本期服务计划后，乙方将按照甲方首次加入计划的时间先后顺序，将甲方加入计划的金额在升升投平台安排进入自动投标及债权买卖。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第三条：本期服务计划的具体内容和要求</strong>
			</p>
			<p style="margin-left:30pt;">
				3.1名称：“升升投”服务计划${pbi.mmName}（上下文均称“本期服务计划”）。
			</p>
			<p style="margin-left:30pt;">
				3.2描述：甲方加入本期服务计划后，在锁定期内，加入服务计划的金额将被安排自动投标及债权买卖。锁定期结束后，甲方可以自由选择继续参加或整体退出本期服务计划。
			</p>
			<p style="margin-left:30pt;">
				3.3自动投标范围：升升投平台上开放的借款标以及受托托管账户内的债权。
			</p>
			<p style="margin-left:30pt;">
				3.4加入金额要求：最低加入金额为人民币${plan.startMoney}元，并以人民币${plan.riseMoney}元整数倍递增，最高不超过人民币 ${plan.limitMoney}元
			</p>
			<p style="margin-left:30pt;">
				3.5申请期：申请期内，允许甲方及其他升升投用户加入本期计划。
			</p>
            <p style="margin-left:30pt;">
				3.6锁定期：锁定期内，甲方加入的服务计划金额不能转让或提现。
			</p>
             <p style="margin-left:30pt;">
				3.7开放期：自锁定期结束次日开始。
			</p>
             <p style="margin-left:30pt;">
				3.8 退出情形：锁定期内甲方不可将本期服务计划金额提前退出。开放期内，甲方可以选择随时按照升升投网站相关规则将加入本期服务计划的全部金额及收益全部退出；未选择退出的，可以继续享有本期服务计划所提供的服务。退出后，甲方将不再享有本期服务计划所提供的服务。
			</p>
             <p style="margin-left:30pt;">
				3.9退出方式：甲方选择退出本期服务计划的，将以转让服务计划下相对应债权的方式实现。
			</p>
             <p style="margin-left:30pt;">
				3.10债权转让的定价规则：债权转让价格为该笔债权转让日的剩余未还本金金额与当日该笔债权的应计利息之和。应计利息采用“实际天数法”规则来计算；<br/>
                即：应计利息=（转让日-上期还款日）*当期利息金额/（下期还款日-上期还款日）
			</p>
             <p style="margin-left:30pt;">
				3.11甲方收益及支付：甲方的收益将按本期服务计划所投标的对应的《借款协议》分别确认。选择退出计划后，转让本期计划项下所有债权后的收益，将提取至甲方在升升投的账户。
			</p>
             <p style="margin-left:30pt;">
				3.12托管账户：甲方加入资金进入升升投托管账户或升升投授权的托管账户。
			</p>
            
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第四条：授权</strong>
			</p>
			<p style="margin-left:30pt;">
				4.1 <strong>甲方在此无条件且不可撤销地同意并确认：</strong>自甲方加入本期”升升投”服务计划起，甲方的资金即可通过系统在本协议项下认可的投资范围内进行优先自动投标记债权买卖，并通过升升投网站系统以甲方名义自动签署相关借款协议、债权转让协议；甲方对此等自动投标和自动签署相关借款协议、债权转让协议之安排已充分知悉并理解；该等自动签署的借款协议、债权转让协议均视为甲方真实意思的表示，甲方对该等法律文件的效力均予以认可且无任何异议，并无条件接受该等自动签署的借款协议、债权转让协议之约束。
			</p>
			<p style="margin-left:30pt;">
				4.2 <strong>甲方在此无条件且不可撤销地同意并确认：</strong>甲方通过系统自动投标而签署之借款协议、债权转让协议等法律文件或其中的相关条款生效后，乙方即可根据该等协议和本协议相关约定，对相关款项进行划扣、支付、冻结以及行使其他权利，甲方对此均予以接受和认可。
			</p>
            <p style="margin-left:30pt;">
				4.3 <strong>利息收入处理：</strong>甲方加入资金通过自动优先投标出借或购买已有债权后，各笔借款每期偿还的利息在合并计提当期管理费用后的剩余部分将作为”升升投”服务计划的可支配收益（下称“收益”）支付给甲方；借款人每期偿还的本金则将继续由乙方提供自动投标服务，并适用本协议各项约定。（计提管理费用的详细计算过程参见注1）。
			</p>
            <p style="margin-left:30pt;">
				4.4 <strong>收益处理方式：</strong>提取至升升投账户：收益将提取至甲方升升投账户，甲方可自行支配。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第五条：服务计划保障</strong>
			</p>
			<p style="margin-left:30pt;">
				5.1 为降低甲方因投资标的过于集中所带来的信用违约风险，乙方将对甲方加入本期服务计划金额安排分散化的自动投标。）
			</p>
			<p style="margin-left:30pt;">
				5.2 为保证本期服务计划的及时性，在甲方加入本期服务计划并且债权充足的情况下，即刻开始为甲方加入的本期服务计划金额安排自动投标。
			</p>			
			<p style="margin-left:30pt;">
				5.3 为增加投标的优先性，乙方将按照甲方加入本期服务计划的时间顺序安排自动投标。
			</p>			
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第六条：意外事件</strong>
			</p>
			<p style="margin-left:30pt;">
				如因司法机关或行政机关对甲方采取强制措施导致其本期服务计划金额对应的资金被全部或部分扣划，视为甲方就全部本金进行了提前支取，本协议自动终止。本协议因此而自动终止的，甲方将不再享有本期服务计划的相应收益。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第七条：税费</strong>
			</p>
			<p style="margin-left:30pt;">
				甲方加入本期服务计划所获的收益，应自行申报由此所带来的可能税费。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第八条：甲方声明和保证</strong>
			</p>
			<p style="margin-left:30pt;">
				8.1 甲方声明：在签署本协议书时，甲方已认真阅读本协议有关条款，对有关条款不存在任何疑问或异议，并对协议双方的权利、义务、责任与风险有清楚和准确的理解。确的理解。
			</p>
			<p style="margin-left:30pt;">
				8.2 甲方保证所使用的资金为合法取得，且具有排他性的支配权。
			</p>
			<p style="margin-left:30pt;">
				8.3 甲方保证为履行本协议而向乙方提供的全部资料均真实、有效。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				<strong>第九条：其他</strong>
			</p>
			<p style="margin-left:30pt;">
				9.1 本期服务计划不提供纸质账单。甲方可以通过升升投网站或客服人员等方式了解相关信息。<strong>如未及时查询，或由于通讯故障、系统故障以及其他不可抗力等因素影响使甲方无法及时了解相关信息，由此产生的责任和风险由甲方自行承担，与乙方无关。</strong>
			</p>
			<p style="margin-left:30pt;">
				9.2 本期服务计划并非独立于甲方在注册、使用升升投网站时需要遵守的相应规则和订立的相关协议。甲方必须遵守升升投的相关规则和签署的相关协议，如有违背，甲方将自行承担相应后果，与乙方无关。
			</p>
			<p style="margin-left:30pt;">
				9.3 由于地震、火灾、战争等不可抗力导致的交易中断、延误的，甲乙双方互不追究责任。但在条件允许的情况下，应采取一切必要的补救措施以减小不可抗力造成的损失。
			</p>
			<p style="margin-left:30pt;">
				9.4 本期服务计划内，如甲方追加服务计划金额， 则仅存在一份服务计划协议书，甲乙双方具有相同的权利及义务。
			</p>
			<p style="margin-left:30pt;">
				9.5  本协议项下产生的纠纷，双方先行协商解决，协商不成的，任何一方均可向乙方所在地人民法院提起诉讼。
			</p>
			<p style="margin-left:30pt;">
				9.6 本协议采用电子文本形式制成，并在乙方为此设立的专用服务器上保存5年，对甲乙双方均具有法律约束力。
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				注1
			</p>
			<p style="margin-left:30pt;">
				各笔借款每期偿还的总利息中超过比照注2中计算的当期目标值的部分作为升升投计划当期管理费用计提。(可以合并或分期计提)
			</p>
			<p style="margin-left:21pt; font-size:14px;">
				注2
			</p>
			<p style="margin-left:30pt;">
				目标值=P x Y/12 x M + P x Y/360 x D
			</p>
			<p style="margin-left:30pt;">
				P:甲方加入资金
			</p>
			<p style="margin-left:30pt;">
				Y:预期年化收益率
			</p>
			<p style="margin-left:30pt;">
				M:锁定期开始日（含）至甲方退出本期升升投计划之日（含）之间足月部分的月数。
			</p>
			<p style="margin-left:30pt;">
				D: 锁定期开始日（含）至甲方退出本期升升投计划之日（含）之间足月部分以外的剩余天数。
			</p>
		</div>
		<div style="text-align: center">
			<form action="#" method="POST" id="downloadForm">
      	<input type="hidden" value="" name="mainId">
      	<input type="hidden" value="41" name="templateId">
				<input type="hidden" value="" name="content" id="htmlContent">
			</form>
			<input type="button" value="关 闭" align="center" onclick="javascript:window.close()">
		</div>
	</div>
</div>

</body></html>