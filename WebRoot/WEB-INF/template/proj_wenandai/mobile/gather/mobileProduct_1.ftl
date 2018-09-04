<form action="../mobileProduct/saveApplyUser_PDAction.do" method="post" onsubmit="return check()">
<input type="hidden" name="financeApplyUser.loanId" value="${(financeApplyUser.loanId)!}">
<input type="hidden" name="financeApplyUser.productName" value="${(financeApplyUser.productName)!}">
<input type="hidden" name="financeApplyUser.productId" value="${(financeApplyUser.productId)!}" id="prid">
<input type="hidden" name="financeApplyUser.flownodes" value="${(financeApplyUser.flowNode)!}">
<input type="hidden" name="financeApplyUser.currentnode" value="${(financeApplyUser.currnodeid)!}">
<input type="hidden" name="financeApplyUser.finishStatus" value="${(financeApplyUser.finishState)!}">
<input type="hidden" name="financeApplyUser.monthlyInterest" id="monthlyInterest" value="">
<input type="hidden" name="financeApplyUser.monthlyCharge" id="monthlyCharge" value="">
<input type="hidden"  name="financeApplyUser.expectAccrual" id="expectAccrual" value="">
<input type="hidden" name="financeApplyUser.state" value="0">
 <ul>
    <li>
        <div>
            <label>贷款金额:</label>
            <input type="text" id="loanMoney" value="" onblur="manageMoney()"  name="financeApplyUser.loanMoney" placeholder="请填写贷款金额0~1万">
        </div>
    </li>
    <li>
        <div>
            <label>贷款用途:</label>
            <select class="selectcolor" name="financeApplyUser.loanUse" id="loanUse">
            	 <option value="1">---请选择---</option>
              	 <option value="708">流动资金周转</option>
		  		 <option value="709">固定资产投资贷款</option>
		 		 <option value="710">项目融资贷款</option>
		 		 <option value="724">其他用途</option>
            </select>
        </div>
    </li>
    <li>
        <div>
            <label>贷款期限:</label>
            <select class="selectcolor" name="financeApplyUser.loanTimeLen" id="deadline">
             	  <option value="1">---请选择---</option>
               	  <option value="3" >3</option>
				  <option value="6" >6</option>
				  <option value="9" >9</option>
				  <option value="12" >12</option>
				  <option value="12" >15</option>
				  <option value="12" >18</option>
				  <option value="12" >24</option>
            </select>
        </div>
    </li>
    <li class="textareawrap">
        <div>
            <label>贷款描述:</label>
            <input type="hidden" name="financeApplyUser.remark" value="" placeholder="请补充说明您贷款用途、工作情况等其它情况">
            <div class="textarea selectcolor" contentEditable="true">请补充说明您贷款用途、工作情况等其它情况</div>
        </div>
    </li>
    <li class="spec specmore">款项结果（根据您上面填写的信息自动计算的还款结果）</li>
    <li>
        <div>
            <label>贷款年利率:</label>
            <input type="text" id="autoRate" readonly="true" placeholder="" >
        </div>
    </li>
    <li>
        <div>
            <label>&nbsp;&nbsp;&nbsp;还款方式:</label>
            <input type="text" readonly="true" placeholder="" value="等额本息">
        </div>
    </li>
    <li>
        <div>
            <label>本金和利息:</label>
            <input type="text" id="moneyAndInt" readonly="true" placeholder="" value="元/月(支付给理财人)">
        </div>
    </li>
    <li>
        <div>
            <label>借款管理费:</label>
            <input type="text" id="moneyManger" readonly="true" placeholder="" value="元/月(由升升投平台收取)">
        </div>
    </li>
    <li>
        <div>
            <label>期初服务费:</label>
            <input type="text" readonly="true" placeholder="" value="50元~680元">
    </li>
</ul>

<div class="login_submit"><input type="submit" value="同意并继续"></div>
</form>
 <script type="text/javascript">
   $("#deadline").change(function(){
		dead();
		});
		
		function dead(){
		
		
		var objS = document.getElementById("deadline");
	    var rate = objS.options[objS.selectedIndex].value;
		var money=$("#loanMoney").val(); //借款本金
		var loanTimeLen='';//还款月数
		if(rate=='1'){
			loanTimeLen = $("#selectid").val();
		}else{
			loanTimeLen = rate;
		}
	if(money.match(/^[0-9]*$/) && loanTimeLen!=''){
		var yearRate='';
		//var productId=$("#productid").val();
		
		if(loanTimeLen=='3'){
				yearRate='9';
		}else if(loanTimeLen=='6'){
				yearRate='10';
		}else if(loanTimeLen=='9'){
				yearRate='11'
		}else if(loanTimeLen=='12'){
				yearRate='12'
		}else if(loanTimeLen>='15'){
				yearRate='13'
		}
		
		var monthRate=yearRate*0.01/12;//月利率
		var comNum=Math.pow((1+monthRate),loanTimeLen);//(1+月化利率)^月数
		var fenmu=monthRate*comNum;
		var fenzi=comNum-1;
		var totalMoney=fenmu/fenzi*money+"";
		var point=totalMoney.indexOf('.');
		var newMoney;
		if(point==-1){
			newMoney=totalMoney;
		}else{
			newMoney=totalMoney.substr(0,point+3);
		}
		$("#moneyAndInt").val(newMoney);
		$("#monthlyInterest").val(newMoney);
		$("#autoRate").val(yearRate+"%");
		$("#expectAccrual").val(yearRate);
	}
		
		}
		
		
		
		
		function manageMoney(){
		var money = $("#loanMoney");
		if(!money.val().match(/^[0-9]*$/)){
			$('.tips').text("借款金额必须有数字组成");
            $(".tipswrap").show();
			return false;
		}else if(parseInt(money.val())<parseInt('100')){
			$('.tips').text("借款金额至少为100元");
            $(".tipswrap").show();
			return false;
		}else if(parseInt(money.val())>parseInt('10000')){
			$('.tips').text("借款金额最多为10000元");
            $(".tipswrap").show();
			return false;
		}else{
			serviceFree();//期初服务费
			var manageMon = money.val()*0.003+"";
			var point = manageMon.indexOf('.');
			var newManageMon;
			if(point == -1){
				newManageMon = manageMon;
			}else{
				newManageMon = manageMon.substr(0,point+3);
			}
			$("#moneyManger").val(newManageMon);
			$("#monthlyCharge").val(newManageMon);
			dead();
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//期初服务费
function serviceFree(){
	var money=$("#loanMoney").val();
	$("#serCharge1").html(0);
	$("#serCharge2").html(checkPoint(money*0.01));
	$("#serCharge3").html(checkPoint(money*0.015));
	$("#serCharge4").html(checkPoint(money*0.02));
	$("#serCharge5").html(checkPoint(money*0.025));
	$("#serCharge6").html(checkPoint(money*0.03));
	$("#serCharge7").html(checkPoint(money*0.05));
}
function checkPoint(tm){
		var totalMoney=tm+"";
		var point=totalMoney.indexOf('.');
		var newMoney;
		if(point=='-1'){
			newMoney=totalMoney;
		}else{
			newMoney=totalMoney.substr(0,point+3);
		}
		return newMoney;
}
	
	
	function check(){
		var loanMoney = $("#loanMoney").val();//借款标题
		var loanUse = $("#loanUse").find('option:selected').val();//借款用途
		var deadline = $("#deadline").find('option:selected').val();//借款期限
		if(loanMoney==""||loanMoney==null){
			$('.tips').text('贷款金额不能为空！');
            $(".tipswrap").show();
			return false;
		}
		if(loanUse=="1"||loanUse==null){
			$('.tips').text('请选择借款用途');
            $(".tipswrap").show();
			return false;
		}
		if(deadline=="1"||deadline==null){
			$('.tips').text('请选择借款期限');
            $(".tipswrap").show();
			return false;
		}
		return true;
	}
	
	
	
</script>