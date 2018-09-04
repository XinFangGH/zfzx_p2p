/**
 * 初始化页面各种js效果
 */
$(function() {
	//根据各个页面上传入的m1,m2,m3,为网站主导行、二级导航、三级导航设定初始的选中状态
	// 设定页面初始被选中的一级菜单
	$(".xy-logo-nav li:contains('"+m1+"')").attr("class","current");
	// 设定页面初始被选中的二级菜单
	$(".topnav li:contains('"+m2+"')").attr("class","current").parent("ul").show();
	// 设定页面初始被选中的三级菜单
	$(".leftsidebar li:contains('"+m3+"')").attr("class","current");	
	// 对主导航条mainnavbar中的ul调用lavalamp切换
//	$(".mainnavbar").children("ul").lavaLamp({
//		fx: "linear", //缓动类型
//		speed: 200, //缓动时间
//		click: function(event, menuItem) {
//			return true; //单击触发事件
//		}
//	});
	// 对二级导航条subnavbar中的ul调用lavalamp
	/*$(".subnavbar").children("ul").lavaLamp({
		fx: "backout", //缓动类型
		speed: 700, //缓动时间
		click: function(event, menuItem) {
			return true; //单击触发事件
		}
	});*/



  /*
    $("#xy-logo-nav li a").click(function(){
        console.log($(this).html());
        $(this).addClass("change");
        $(this).css({"display":"inline-block","height":"100%","width":"100%","color":"#ffffff","background-color":"#4789e7","border-radius":"5px"});

    });*/

});