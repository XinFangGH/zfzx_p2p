$(function(){
	$(".cont-list .finlist_title li").click(function(){debugger;
		$(this).addClass("active").siblings().removeClass();
		$(".cont-list .finlist_hide ol").eq($(this).index()).addClass("rent").siblings().removeClass();
		$("#pager"+$(".cont-list .finlist_hide ol").eq($(this).index())[0].id).empty();
		addPage($(".cont-list .finlist_hide ol").eq($(this).index())[0].id,'');
	});  	
});


