jQuery.divselect = function(divselectid) {
	
	var inputselect =null;
	$(divselectid+" cite").click(function(e){
		var thisObj=$(this);
		inputselect=thisObj.find("input");
		var ul = thisObj.next("ul");
		if(ul.css("display")=="none"){
			ul.slideDown("fast");
		}else{
			ul.slideUp("fast");
		}
	});
	$(divselectid+" ul li .selt").live('click',(function(){
		var thisObj=$(this);
		var txt = thisObj.text();
		thisObj.parent("li").parent("ul").prev("cite").find("span").html(txt);
		var value = $(this).attr("selectid");
		inputselect.val(value);
		$(divselectid+" ul").hide();
		var nextDiv = thisObj.parent("li").parent("ul").parent("div").next();
		if((typeof nextDiv.html())!="undefined"){//当存在下一级时，才执行
			var ulText = nextDiv.children("ul").html();
			if(ulText!=""){
				nextDiv.children("ul").children("li").remove();//若不等于空，先清空
			}
			$.ajax({
				type : "POST",
				dataType : "JSON",
				async:false,
				url : basepath+"financePurchase/getNextLevelapplyUser.do?parentId="+ value,
				success : function(responseText, statusText) {
					
					if(statusText == "success"){
						var list=eval(responseText.result);
						$.each(list,function(index,item){
							nextDiv.children("ul").append(" <li><a class='selt' href='javascript:;' selectid="+item.id+">"+item.title+"</a></li>");
						});
						nextDiv.find('span').html(list[0].title);
						nextDiv.find('input').val(list[0].id);
					}else{
						alert("额，没有对应的数据哦！");
					}
				},
				error : function(request) {
					alert("处理数据时出现异常！");
				}
			});
		}
		
		
		
	}));
	
};







