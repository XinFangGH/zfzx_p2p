$(function(){
	
	$(".noUpload").mouseover(function(){
		$(".noUpload").css("display","none");
		$(".btnUpload").css("display","block");
	})
	$(".btnUpload").mouseout(function(){
		$(".noUpload").css("display","block");
		$(".btnUpload").css("display","none");
	})
    		$("#innerNav li.userInfo").addClass("current").append("<em class=\"curr\">&nbsp;</em>");
    		//修改按钮
    		$("#updateUserInfo").click(function(){
				$("#updateUserInfoForm").ajaxSubmit({
					beforeSubmit:checkUserInfoForm,
					clearForm:false,
					dataType:"JSON",
					success:function(responseText, statusText){
						if (statusText == "success"){
							if (responseText.result == 1){
								location.href = basepath+"/user/updateSuccessBpCustMember.do";
							}else{
								showCommonTip(".common-tip",responseText.errMsg);
							}
						}else{
							showCommonTip(".common-tip","基本资料修改失败");
						}
					},
					error:function(){
						showCommonTip(".common-tip","基本资料修改失败");
					}
				});
    		});

    		//修改按钮
    		$("#updateBaseUserInfo").click(function(){
    			if ($("#truename").val() != ''){
    				$("#user_real_name").val($("#truename").val());
    			}
    			else
    			{
    				$("#user_real_name").val("");
    			}
				$("#updateBaseUserInfoForm").ajaxSubmit({
					beforeSubmit:checkBaseUserInfoForm,
					clearForm:false,
					dataType:"JSON",
					success:function(responseText, statusText){
						if (statusText == "success"){
							if (responseText.result == 1){
								location.href = basepath+"/user/updateSuccessBpCustMember.do";
							}else{
								showCommonTip(".common-tip",responseText.errMsg);
							}
						}else{
							showCommonTip(".common-tip","详细资料修改失败");
						}
					},
					error:function(){
						showCommonTip(".common-tip","详细资料修改失败");
					}
				});
    		});
    		$('#updateBaseUserInfoForm').on('keyup', 'input', function(e){
    			if (e.keyCode == 13) {
    				$("#updateBaseUserInfo").trigger('click');
    			};
    		});

    		//省份列表
    		var provinceList = $.ajax({async:false,url:"getListArea.do"}).responseText;
    		//居住城市
    		var liveProvice = $("#liveProvice1").val();
    		var liveCity = $("#liveCity1").val();
    		
    		/* 基本信息 */
    		//性别
    		$(":radio[name=sex][value=]").attr("checked","checked");
    		//省份
    		$("#liveProvice").change(function(){
    			$("#liveCity option").remove();
    			$("#liveCity").append("<option value=''>请选择</option>");
    			if (!isEmpty($(this).val())){
					$.ajax({
						url:"getListArea.do?ParentCode=" + $(this).val(),
						dataType:"text",
						async:false,
						success:function(data){
							
							if (data){
								$("#liveCity").append(data);
								if(liveCity!=null){
					    			$("#liveCity").val(liveCity);
					    		}else{
					    			$("#liveCity").val();
					    		}

							}
						}
					});
    			}
    		});

    		$("#liveProvice").append(provinceList);
    		if(liveProvice!=null){
    			$("#liveProvice").val(liveProvice);
    		}else{
    			$("#liveProvice").val();
    		}

    		//城市
    		$("#liveProvice").trigger("change");

    		/* 详细信息 */
    		//婚姻状态
    		$(":radio[name=user_marital_status][value=]").attr("checked","checked");

    		//籍贯
    		var nativePlaceProvice = $("#nativePlaceProvice1").val();
    		var nativePlaceCity = $("#nativePlaceCity1").val();
    		//省份
    		$("#nativePlaceProvice").change(function(){
    			$("#nativePlaceCity option").remove();
    			$("#nativePlaceCity").append("<option value=''>请选择</option>");
    			if (!isEmpty($(this).val())){
					$.ajax({
						url:"getListArea.do?ParentCode=" + $(this).val(),
						dataType:"text",
						async:false,
						success:function(data){
							if (data){
								$("#nativePlaceCity").append(data);
								if(nativePlaceCity!=null){
									$("#nativePlaceCity").val(nativePlaceCity);
								}else{
									$("#nativePlaceCity").val();
								}
							}
						}
					});
    			}
    		});

    		$("#nativePlaceProvice").append(provinceList);
    		if(nativePlaceProvice!=null){
				$("#nativePlaceProvice").val(nativePlaceProvice);
			}else{
				$("#nativePlaceProvice").val();
			}

    		//城市
    		$("#nativePlaceProvice").trigger("change");

    		//用户头像
    		/*if ("" != ""){
    			var src = "/upload/";
    		}else{
    			var src = "/upload/";
    		}
    		$("#").attr("src", src);*/
    		/*
    		//日历控件
    		$( "#user_birthday" ).datepicker({
    		 	defaultDate: "+1w",
    		 	dateFormat: "yy-mm-dd",
    		 	changeMonth: true,
  				changeYear: true,
  				yearRange: "1900: "
    		});*/
    	});

    	//校验表单
    	function checkUserInfoForm(){
    		//校验性别
    		if ($(":radio[name=sex][checked]").length <= 0){
    			showTip("sex","请选择性别");
   	          	return false;
    		}
    		
    		//校验所在省份
    		if (isEmpty($("#liveProvice").val())){
    			showTip("liveProvice","请选择省份");
   	          	return false;
    		}

    		//校验所在城市
    		if (isEmpty($("#liveCity").val())){
    			showTip("liveCity","请选择城市");
   	          	return false;
    		}

    		return true;
    	}
    	
    	//校验表单
    	function checkBaseUserInfoForm(){
    		//校验真实姓名
    		if (isEmpty($("#truename").val())){
    			showTip("truename","真实姓名不能为空");
   	          	return false;
    		} else {
    			if ($("#truename").val() != ''){
    				if ($("#truename").val().match(/^[\u4E00-\u9FA5]+$/)) {
	   	          		if ($("#truename").val().length > 5) {
	   	          			showTip("truename", "中文姓名不能超过五个字");
	   	          			return false;
	   	          		}
	   	          	} else {
	   	          		showTip("truename", "请使用中文姓名");
	   	          		return false;
	   	          	}
    				
    			}
    			
    		}
    		
    		//校验固定电话
    		if (!isEmpty($("#homePhone").val())){
    			if (!$("#homePhone").val().match(/^([0-9]|-)+$/)){
    				showTip("homePhone","固定电话格式不正确");
	   	          	return false;
    			} 
    		}
    		
    		//校验邮政编码
    		if (!isEmpty($("#postCode").val())){
    			if (!$("#postCode").val().match(/[1-9]\d{5}(?!\d)/)){
    				showTip("postCode","邮政编码格式不正确");
	   	          	return false;
    			}
    		}
    		
    		//校验传真
    		if (!isEmpty($("#fax").val())){
    			if (!$("#fax").val().match(/^([0-9]|-)+$/)){
    				showTip("fax","传真格式不正确");
	   	          	return false;
    			}
    		}
    		
    		//校验籍贯省份
    		if (isEmpty($("#nativePlaceProvice").val())){
    			showTip("nativePlaceProvice","请选择省份");
   	          	return false;
    		}

    		//校验籍贯城市
    		if (isEmpty($("#nativePlaceCity").val())){
    			showTip("nativePlaceCity","请选择城市");
   	          	return false;
    		}
    		
    		//校验地址
    		/*if (isEmpty($("#relationAddress").val())){
    			showTip("relationAddress","地址不能为空");
   	          	return false;
    		}*/

    		return true;
    	}
    	
    	$(function(){
    		

    
});
//条件查询积分
function sel(){
	var sel = $("#sel").val();
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : basepath + "/bonusSystem/listWebBonusRecord.do?sel="+sel,
		cache : false,
		success : function(responseText, statusText) {
		   $("#addTr").empty();
		   var addTr="<tr><td align='center'>时间</td><td align='center'>积分</td><td align='center'>详情</td></tr>";
			var list = JSON.parse(responseText.result);
			for(var i=0;i<list.length;i++){
				var recordDirector=list[i].recordDirector;
				var recordNumber="";
				if(recordDirector==1){
					recordNumber="<td align='center'>+"+list[i].recordNumber+"</td>";
				}else{
					recordNumber="<td align='center' style='color:red'>-"+list[i].recordNumber+"</td>";
				}
				 addTr+="<tr><td align='center'>"+list[i].createTime+"</td>"+recordNumber+"<td align='center'>"+list[i].bonusIntention+"</td></tr>";
				 $("#useRecordNumber").text(list[i].useRecordNumber);
				 $("#totalRecordNumber").text(list[i].totalRecordNumber);
				 $("#usableRecordNumber").text(list[i].usableRecordNumber);
			}
			$("#addTr").append(addTr);
		},
		error : function(request) {
		}
	});

}
//积分兑换
function activitybutton(activityId,needIntegral){
 var usableRecordNumber=$("#usableRecordNumber1").val();
	if(usableRecordNumber<needIntegral){
		layer.alert('可用积分不足,不能兑换', 8);
	}else{
		
		$.layer({
		    shade: [0],
		    area: ['auto','auto'],
		    dialog: {
		        msg: '是否兑换该积分活动？',
		        btns: 2,                    
		        type: 4,
		        btn: ['兑换','取消'],
		        yes: function(){
		            $.ajax({
						type:"POST",
						dataType:"JSON",
						url:basepath+"activity/exchangeBpActivityManage.do?activityId="+activityId,
						cache:false,
						success : function(responseText, statusText) {
							if(responseText.success){
								layer.alert(responseText.result, 1);
								myRecord();
							}else{
								layer.alert(responseText.result, 8);
							}
							
						},
						error :function(request){
							layer.alert('积分兑换失败', 8);
						}
					})
		        }, no: function(){
		            layer.msg('积分兑换活动正在火热进行中...',2,9);
		        }
		    }
		});
	}
}
//保存推荐码
function saveRecommand(){
	var recommandValue=$("#recommand").val();
	var show = $("#showRec").val();
	if(show!=null && show!="" && show!='undefined'){
		document.getElementById("recommandDiv").style.display="none";
		$("#put_b").attr("disabled","true");
		$(".accredi-open1").hide();
		setTimeout(function(){
			$('#recommandPerson_span').text("不能重复录入推荐码");
		},1000);
		return false;
	}
	$.ajax({
		type : "POST",
		dataType : "JSON",
		url : basepath + "/user/updateRecommandBpCustMember.do?recommandPerson="+recommandValue,
		cache : false,
		success : function(responseText, statusText) {
			if (responseText.success) {
				document.getElementById("recommandDiv").style.display="none";
				$('#recommandPerson_span').text(recommandValue);
				$("#put_b").attr("disabled","true");
				$(".accredi-open1").hide();
			}
		},
		error : function(request) {
		}
	});
}

//检查推荐码是否存在
function chkRecommand(obj){
	$("#recommandMark").html("");
	document.getElementById("recommandMark").style.background = "";
	if (isEmpty(obj.value)) {
		document.getElementById("recommandMark").style.color = "red";
		$("#recommandMark").css("display","block");
		$("#recommandImg").css("display","none");
		$("#recommandMark").html("推荐码不能为空");
	}else{
		$.ajax({
			type : "POST",
			dataType : "JSON",
			url : basepath + "/user/isExistRecommandBpCustMember.do?recommandPerson="+ obj.value,
			cache : false,
			success : function(responseText, statusText) {
				$("#recommandMark").css("display","none");
				$("#recommandImg").css("display","block");
				if(responseText.success){
					$("#recommandImg").empty().append("<img src='"+themepath+"images/icon.JPG'>");
				}else{
					$("#recommandImg").empty().append("<img src='"+themepath+"images/erricon.jpg'>");	
				}
			},
			error : function(request) {
			}
		});
	}
}

$(function(){
	$('#put_b').click(function(){
		var show = $("#showRec").val();
		if(show!=null && show!="" && show!='undefined'){
			$(".user-infor ul li").last().hide();
		}else{
			$("#recommand").val("");
			document.getElementById("recommandDiv").style.display="";
		}
	});


    var clipboard = new ClipboardJS('#copy_b');
    clipboard.on('success', function(e) {
        alert("复制成功");
    });

    clipboard.on('error', function(e) {
        console.log(e);
    });
  var clipboarda = new ClipboardJS('#copy_a');
    clipboarda.on('success', function(e) {
        alert("复制成功");
    });

    clipboarda.on('error', function(e) {
        console.log(e);
    });


    //查询积分
    $("#bonusRecord").click(function(){
   		 myRecord();
    })
    
    
    //我的优惠券
    $("#clickCoupons").click(function(){
    	ajaxCoupons();
    })
    //激活代金券
    $("#activateButton").click(function(){
    	var colorText = $("#colorText").val();
    	if(colorText==""){
    		$("#resultText").text("请填写该优惠券激活码");
    	}else{
    		$.ajax({
    			type:"POST",
	    		dataType:"JSON",
	    		url:basepath+"coupon/activateCouponsBpCoupons.do?colorText="+colorText,
	    		cache:false,
	    		success:function(responseText,statusText){
	    			if(responseText.result=="ok"){
	    				$("#resultText").text("激活成功");
	    				ajaxCoupons();
	    			}else{
		    			$("#resultText").text(responseText.result);
	    			}
	    		},
	    		error:function(request){
	    			$("#resultText").text("激活失败");
	    		}
    		})
    		
    	}
    	
    })
    $("#colorText").click(function(){
    	 $("#colorText").text("");
   		 $("#resultText").text("");
    })
    $("#ysyCoupons").click(function(){
    	$("#ysyCoupons").addClass("one");
    	$("#wsyCoupons").removeClass("one");
    	$("#ygqCoupons").removeClass("one");
    	$("#addCoupons2").show();
    	$("#addCoupons").hide();
    	$("#addCoupons3").hide();
    })
    
     $("#ygqCoupons").click(function(){
    	$("#ysyCoupons").removeClass("one");
    	$("#wsyCoupons").removeClass("one");
    	$("#ygqCoupons").addClass("one");
    	$("#addCoupons2").hide();
    	$("#addCoupons").hide();
    	$("#addCoupons3").show();
    })
    
      $("#wsyCoupons").click(function(){
    	$("#ysyCoupons").removeClass("one");
    	$("#wsyCoupons").addClass("one");
    	$("#ygqCoupons").removeClass("one");
    	$("#addCoupons2").hide();
    	$("#addCoupons").show();
    	$("#addCoupons3").hide();
    })
 
});

//查询我的积分
    function myRecord(){
    	$.ajax({
    		type : "POST",
    		dataType : "JSON",
    		url : basepath + "/bonusSystem/listWebBonusRecord.do",
    		cache : false,
    		success : function(responseText, statusText) {
    		   $("#addTr").empty();
    		   var addTr="<tr><td align='center'>时间</td><td align='center'>积分</td><td align='center'>详情</td></tr>";
    			var list = JSON.parse(responseText.result);
    			for(var i=0;i<list.length;i++){
    				var recordDirector=list[i].recordDirector;
    				var recordNumber="";
    				if(recordDirector==1){
    					recordNumber="<td align='center'>+"+list[i].recordNumber+"</td>";
    				}else{
    					recordNumber="<td align='center' style='color:red'>-"+list[i].recordNumber+"</td>";
    				}
    				 addTr+="<tr><td align='center'>"+list[i].createTime+"</td>"+recordNumber+"<td align='center'>"+list[i].bonusDescription+"</td></tr>";
    				 $("#useRecordNumber").text(list[0].useRecordNumber);
    				 $("#totalRecordNumber").text(list[0].totalRecordNumber);
    				 $("#usableRecordNumber").text(list[0].usableRecordNumber);
    			}
    			$("#addTr").append(addTr);
    		},
    		error : function(request) {
    		}
    	});
    	
    	//查询积分兑换
    $.ajax({
    	type:"POST",
    	dataType:"JSON",
    	url:basepath+"activity/listBpActivityManage.do",
    	cache:false,
    	success:function(responseText,statusText){
    		$("#addli").empty();
    		var list=JSON.parse(responseText.result);
    		var addli="";
    		for(var i=0;i<list.length;i++){
    			couponName="";
    			var danwei="";
    			if(list[i].couponType==1){
    				couponName="返现券";
    				danwei="￥"+list[i].parValue;
    			}else if(list[i].couponType==2){
    				couponName="体验券";
    				danwei="￥"+list[i].parValue;
    			}else if(list[i].couponType==3){
    				couponName="加息券";
    				danwei=list[i].parValue+"%";
    			}
    			addli+="<li><h5 class='larger'>"+couponName+"</h5><p class='large'>"+danwei+"</p>" +
    					"<p class='day'>有限期：<span>"+list[i].validNumber+"天</span></p>" +
    					"<p class='jifen'>需要：<em>"+list[i].needIntegral+"</em> 积分</p>" +
    					"<p class='btn'><a href='#' onClick='activitybutton("+list[i].activityId+","+list[i].needIntegral+")'>立即兑换</a></p></li>";
    		}
    		if(list.length==0){
    			addli="<p align='center'>暂未有积分兑换活动，敬请期待！</p>";
    		}
    		$("#addli").append(addli);
    	},
    	error : function(request){
    		
    	}
    })
    
    }
    //激活成功 加载优惠券列表
    function ajaxCoupons(){
    	
    	$.ajax({
    		type:"POST",
    		dataType:"JSON",
    		url:basepath+"coupon/listBpCoupons.do?",
    		cache:false,
    		success:function(responseText,statusText){
    		   $("#addCoupons").empty();
    		   $("#addCoupons2").empty();
    		   $("#addCoupons3").empty();
    		   var addTr="<tr><td align='center'>优惠券</td><td align='center'>有效期</td><td align='center'>来源</td></tr>";
    		   var addTr2="<tr><td align='center'>优惠券</td><td align='center'>有效期</td><td align='center'>来源</td></tr>";
    		   var addTr3="<tr><td align='center'>优惠券</td><td align='center'>有效期</td><td align='center'>来源</td></tr>";
    			var list = JSON.parse(responseText.result);
    			var wysNum=0;
    			var yysNum=0;
    			var ygqNum=0;
    			for(var i=0;i<list.length;i++){
    					var couponTypeValue="";
    					var danwei="";
	    				if(list[i].couponType==1){
	    					couponTypeValue="优惠券";
	    					danwei="￥"+list[i].couponValue;
	    				}
	    				if(list[i].couponType==2){
	    					couponTypeValue="体验券";
	    					danwei="￥"+list[i].couponValue;
	    				}
	    				if(list[i].couponType==3){
	    					couponTypeValue="加息券";
	    					danwei=list[i].couponValue+"%";
	    				}
	    			/*	var couponResource="";
	    				if(list[i].couponResourceType=="couponResourceType_normal"){
	    					couponResource="普通优惠券";
	    				}else if(list[i].couponResourceType=="couponResourceType_active"){
	    					couponResource="活动优惠券";
	    				}*/
	    			var couponsDescribe="";
	    			if(list[i].couponsDescribe=="undefined"||list[i].couponsDescribe==undefined){
	    				couponsDescribe="";
	    			}else{
	    				couponsDescribe=list[i].couponsDescribe;
	    			}
    				//未使用
    				if(list[i].couponStatus=="5"){
    					wysNum=wysNum+1;
    					if(wysNum<6){
	    				 addTr+="<tr><td align='center' class='bg'><span style='float:left;width:10px;padding-left:80px; font-size:14px;'>"+couponTypeValue+"</span><span style='float:right;padding:15px 90px 0 0;'>"+danwei+"</span></td>" +
	    				 		"<td align='center'>"+list[i].couponStartDate+"<br>--<br>"+list[i].couponEndDate+"</td>" +
	    				 		"<td align='center'>"+couponsDescribe+"</td></tr>";
    					}
	    			}
	    			//已使用
	    			if(list[i].couponStatus=="10"||list[i].couponStatus=="1"){
	    				yysNum=yysNum+1;
	    				if(yysNum<6){
		    				 addTr2+="<tr><td align='center' class='bg'><span style='float:left;width:10px;padding-left:80px; font-size:14px;'>"+couponTypeValue+"</span><span style='float:right;padding:15px 90px 0 0;'>"+danwei+"</span></td>" +
		    				 		"<td align='center'>"+list[i].couponStartDate+"<br>--<br>"+list[i].couponEndDate+"</td>" +
		    				 		"<td align='center'>"+couponsDescribe+"</td></tr>";
	    				}
	    			}
	    			//已过期
	    			if(list[i].couponStatus=="4"){
	    				ygqNum=ygqNum+1;
	    				if(ygqNum<6){
	    				  addTr3+="<tr><td align='center' class='bg'><span style='float:left;width:10px;padding-left:80px; font-size:14px;'>"+couponTypeValue+"</span><span style='float:right;padding:15px 90px 0 0;'>"+danwei+"</span></td>" +
	    				 		"<td align='center'>"+list[i].couponStartDate+"<br>--<br>"+list[i].couponEndDate+"</td>" +
	    				 		"<td align='center'>"+couponsDescribe+"</td></tr>";
	    				}
	    			}
    			}
    			$("#addCoupons").append(addTr);
    			$("#addCoupons2").append(addTr2);
    			$("#addCoupons3").append(addTr3);
    			
    		},
    		error:function(request){
    		
    		}
    	})
    	
    
    }
