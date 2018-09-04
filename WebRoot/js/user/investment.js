$(function() {
	
	

	var path =$('#path').val();
	
	
	
	});
		
	//显示灰色 jQuery 遮罩层 
	function showBg() { 
		var bh = $("body").height(); 
		var bw = $("body").width(); 
		$("#fullbg").css({ 
		height:bh, 
		width:bw, 
		display:"block" 
		}); 
		$("#dialog").show(); 
	} 
	//关闭灰色 jQuery 遮罩 
	function closeBg() { 
		$("#fullbg,#dialog").hide(); 
	} 
	
	
	//关闭弹出层
		$('.js_close').click(function(){
			$('#backShow').hide();
			$('.mask-layer').remove();
		    self.location='/userAccount/recharge.html';
		});


