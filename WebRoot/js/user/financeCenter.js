$(document).ready(function() {
		$('#innerNav').on('click', 'li > a', function() {
			var $cur = $('<em class="curr">&nbsp;</em>');
			$(this).parent().addClass('current').siblings('li:not(".title")').removeClass('current');
			$(this).after($cur).parent().siblings('li:not(".title")').find('em').remove();

			var i = $(this).parent('li:not(".title")').index();
			$('.main').eq(i).show().siblings('div').hide();
		});
	});

	
	
	$(function(){
		var path = $("#path").val();
		$('.submit-btn').on('click', function() {
		window.location.href = path+"/financePurchase/applyFinancePurchase.do?financ_type=" + $(this).attr("financType");
	});
});

function background(state){
	if(state==1){
		$("#person").css("background","#005AB5");
		$("#enterprise").css("background","#9D9D9D");
		$("#enterpriseDiv").css("display","none");
		$("#enterpriseContDiv").css("display","none");
		$("#personContDiv").css("display","block");
		$("#personDiv").css("display","block");
	}else if(state==2){
		$("#enterprise").css("background","#005AB5");
		$("#person").css("background","#9D9D9D");
		$("#enterpriseDiv").css("display","block");
		$("#personDiv").css("display","none");
		$("#enterpriseContDiv").css("display","block");
		$("#personContDiv").css("display","none");
	}
}
