function openUpdateAuto(){

	var bidMoney = document.getElementById("bidAuto.bidMoney").value;
	alert(bidMoney);
	if(bidMoney==""){
		showTip("bidAuto.bidMoney","投标金额不能为空");
		return false;
	}
}