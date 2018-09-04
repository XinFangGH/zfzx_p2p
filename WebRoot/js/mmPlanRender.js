	$(function(){
		$(".cont-list .finlist_title li").click(function(){debugger
			$(this).addClass("active").siblings().removeClass();
			$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
			addPage($(".cont-list .finlist_hide ol").eq($(this).index())[0].id,'');
		});  	
	});
/*	$(document).ready(function(){
		var active="${show}";
		var obj=$(".cont-list .finlist_title li");
		if(active=='buyingList'){
			$(obj.get(0)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();
		}else if(active=='owningList'){
			$(obj.get(1)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(1)).addClass("rent").siblings().removeClass();
		}else if(active=='outList'){
			$(obj.get(3)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(3)).addClass("rent").siblings().removeClass();
		}else if(active=='successList'){
			$(obj.get(2)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(2)).addClass("rent").siblings().removeClass();
		}else if(active=='failureList'){
			$(obj.get(4)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(4)).addClass("rent").siblings().removeClass();
		}else{
			$(obj.get(0)).addClass("active").siblings().removeClass();
			$($(".cont-list .finlist_hide ol").get(0)).addClass("rent").siblings().removeClass();
		}
	});
*//*	$(function(){
		$(".content02 .finlist_title02 li").click(function(){
			$(this).addClass("active").siblings().removeClass();
			$(".content02 .finlist_hide02 ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		});
	});*/