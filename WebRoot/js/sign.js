var bidName;
var bidId;
var bidtype;
$(document).ready(function() {
	
  bidName=$("#bidName").val();
  bidId=$("#bidId").val();
   bidtype=$("#bidType").val();
   
/*****************投标详细信息**********************/
if(bidtype !="plmm"){/*   
$.ajax({
        type: "POST",
        url: basepath+"creditFlow/financingAgency/ajaxBidInfoPlBidPlan.do",
        data: { //发送给数据库的数据
               "bidId":bidId
               },
        dataType: 'json',
        success:function(responseText, statusText) {
                if (statusText == "success") {
                	var responseText = $.parseJSON(responseText);
                	$("#money").empty();
                	if(responseText.afterMoney!=null){
                		$("#money").append(responseText.afterMoney);
                	}else{
                		$("#money").append("0.00元");
                	}
                	$("#addProgressBar").empty();
                	$("#addProgressBar").append("<span class='progressBar' id='progress'>"+ responseText.progress+"%</span>" );
                    $("#progress").progressBar();
                	$("#persionNum").empty();
                	$("#persionNum").append(responseText.persionNum);
                	$("#hiddenMoney").empty();
                	
                	$("#hiddenMoney").val(responseText.afterMoney);
                	if(responseText.afterMoney==0||responseText.progress==100){
                		$("#notFull").remove();
                		$("#full").css('display','block'); 
                	}else{
                		intDiff = parseInt(responseText.afterTime);//倒计时总秒数量	
		                timer(intDiff);
                	}
                } 
            },
          error:function(responseText){
          }  
    });
*//*****************投标客户列表**********************//*
    findUserBidInfo(0,0,10,bidId);
    *//*****************投标客户列表**********************//*
*/}else{

   $("#addProgressBar").progressBar();
   var afterTime=$("#planafterTime").val();
  intDiff = parseInt(afterTime);//倒计时总秒数量	
  timer(intDiff);  
} 


 var myState = $("#myState").text();
 if(myState=="10"){
 	$("#li_10").addClass("active");
 	$("#li_0").removeClass("active");
 }
 if(myState=="1"){
 	$("#li_1").addClass("active");
 	$("#li_0").removeClass("active");
 }
 if(myState=="-1"){
 	$("#li_-1").addClass("active");
 	$("#li_0").removeClass("active");
 }
 
 //投标可用优惠券显示隐藏
 $("#onSubNav").click(function(){
	$("#addCoupons").toggle(200);	
})
 
}); 

/*****************投标客户列表**********************/
function findUserBidInfo(itemid,start,limit,bidId){
	
	$.ajax({
        type: "POST",
        url: basepath+"creditFlow/financingAgency/newBidListPlBidInfo.do?bidtype="+bidtype+"&bidId="+bidId,
        data: { //发送给数据库的数据
               start:start,
               limit:limit
               },
        dataType: 'json',
        beforeSend:function(){
           $("#load").append('<img src="'+themepath+'images/loading.gif"/><span>加载中请等待...</span>');
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                var list=eval(responseText.result);

                if(itemid==0){
               $("#userBidList").append("<tr id='title'><th class=\"time\">时间</th><th>投资人</th><th>投资金额</th></tr>");
                }
				
                $.each(list,function(idx,item){ 
                 
                //循环
                	start=start+1;
                	if(bidtype=="plmm"){
                		if(itemid==0){
                	           $("#title").after("<tr id='"+item.orderId+"'><td class=\"time\">"+item.buyDatetime+"</td> <td>"+item.investPersonName.substr(0, 2)+"****"+item.investPersonName.substring(item.investPersonName.length-2)+"</td><td>￥"+fmoney(item.buyMoney,2)+"元</td></tr>");
                	  }else{
                		       $("#"+itemid).after("<tr id='"+item.orderId+"'><td class=\"time\">"+item.buyDatetime+"</td> <td>"+item.investPersonName.substr(0, 2)+"****"+item.investPersonName.substring(item.investPersonName.length-2)+"</td><td>￥"+fmoney(item.buyMoney,2)+"元</td></tr>");
                			              	     }
                       itemid=item.orderId;
                	}else{
                	if(itemid==0){
                	           $("#title").after("<tr id='"+item.id+"'><td class=\"time\">"+item.bidtime+"</td> <td>"+item.userName.substr(0, 2)+"****"+item.userName.substring(item.userName.length-2)+"</td><td>￥"+fmoney(item.userMoney,2)+"元</td></tr>");
                	  }else{
                			   //循环
                			   $("#"+itemid).after("<tr id='"+item.id+"'><td class=\"time\">"+item.bidtime+"</td> <td>"+item.userName.substr(0, 2)+"****"+item.userName.substring(item.userName.length-2)+"</td><td>￥"+fmoney(item.userMoney,2)+"元</td></tr>");
                	     }
                       itemid=item.id;
                	
                	}
                	
                });
               
                if(list.length>=10){
                	$("#more").remove();
                	//alert(itemid);
                $("#"+itemid).after("<tr id='more'><td colspan=3 style=\"text-align: center;\"><a href='javascript:void(0);' onClick='findUserBidInfo("+itemid+","+start+",10,"+bidId+")'>查看更多</a></td></tr>");
                }else{
                	$("#more").remove();
                }
                
                } 
            },
            complete:function(XMLHttpRequest,textStatus){
             $("#load").empty();
           },
            error: function() {
                  $("#load").append("内容加载错误...");
            }
    });
}
function addBpCoupons(){
	   var mycouponsType = $("#mycouponsType").text();
	   //标的最大优惠券使用金额
	   var planMaxCouponMoney = $("#planMaxCouponMoney").val();
		$.ajax({
    		type:"POST",
    		dataType:"JSON",
    		url:basepath+"coupon/listBpCoupons.do?couponStatus=5&mycouponsType="+mycouponsType,
    		cache:false,
    		success:function(responseText,statusText){
    		   $("#addCoupons").empty();
    		   var addTr="<tr><td width='15px;'></td><th  align='center' width='80px;' valign='middle'>面值1</th><th align='center' width='100px;' valign='middle'>过期日期</th></tr>";
    			var list = JSON.parse(responseText.result);
    			var sycoupon = $("#sycoupon").text();
    			var sumMoney=0;
    			var sumNum=0;
    			var str="";
    			if(mycouponsType=="4"){
    			 	str="%";
    			}else{
    				str="元";
    			}
    			for(var i=0;i<list.length;i++){
    				if(sycoupon=="否"){
    				 addTr+=" <tr> <td align='left'  valign='middle'><input  type='checkbox' disabled='true' id='couponId_"+list[i].couponId+"' onClick='clickcoupon("+list[i].couponValue+","+list[i].couponId+")' name='couponName' value='"+list[i].couponId+"'/></td>" +
	    				 		" <td align='center' valign='middle' class='bgdisable'><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+list[i].couponValue+"</em>"+str+"</td>" +
	    				 		" <td align='center' valign='middle'>"+list[i].couponEndDate+"</td> </tr>";
    				}else{	
    					//可用优惠券的标才计算优惠个数和总金额，不能超过最大使用金额
    					if(list[i].couponValue<=planMaxCouponMoney){
    						sumNum=sumNum+1;
    						sumMoney=sumMoney+list[i].couponValue;
    						 addTr+=" <tr> <td align='left'  valign='middle'><input  type='checkbox' id='couponId_"+list[i].couponId+"' onClick='clickcoupon("+list[i].couponValue+","+list[i].couponId+")' name='couponName' value='"+list[i].couponId+"'/></td>" +
	    				 		" <td align='center' valign='middle' class='bg'><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+list[i].couponValue+"</em>"+str+"</td>" +
	    				 		" <td align='center' valign='middle'>"+list[i].couponEndDate+"</td> </tr>";
    					}else if(mycouponsType=="4"){
    						addTr+=" <tr> <td align='left'  valign='middle'><input  type='checkbox' id='couponId_"+list[i].couponId+"' onClick='clickcoupon("+list[i].couponValue+","+list[i].couponId+")' name='couponName' value='"+list[i].couponId+"'/></td>" +
	    				 		" <td align='center' valign='middle' class='bg'><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+list[i].couponValue+"</em>"+str+"</td>" +
	    				 		" <td align='center' valign='middle'>"+list[i].couponEndDate+"</td> </tr>";
    					}else{
    					addTr+=" <tr> <td align='left'  valign='middle'><input  type='checkbox' id='couponId_"+list[i].couponId+"' onClick='clickcoupon("+list[i].couponValue+","+list[i].couponId+")' name='couponName' value='"+list[i].couponId+"'/></td>" +
	    				 		" <td align='center' valign='middle' class='bgdisable'><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+list[i].couponValue+"</em>"+str+"</td>" +
	    				 		" <td align='center' valign='middle'>"+list[i].couponEndDate+"</td> </tr>";
    					}
    					
    				}
	    				
    			}
    				$("#addCoupons").append(addTr);
    				$("#sumMoney").text(sumMoney);
    				$("#sumNum").text(sumNum);
    		},
    		error:function(request){
    		
    		}
    	})
}


function addExperienceBpCoupons(){
		$.ajax({
    		type:"POST",
    		dataType:"JSON",
    		url:basepath+"coupon/listByExperienceBpCoupons.do?couponStatus=5&couponType=3",
    		cache:false,
    		success:function(responseText,statusText){
    		   $("#addExperienceBpCoupons").empty();
    		    var addTr="<tr><td width='15px;'></td><th  align='center' width='80px;' valign='middle'>面值2</th><th align='center' width='100px;' valign='middle'>过期日期</th></tr>";
    			var list = JSON.parse(responseText.result);
    			var sumMoney=0;
    			var sumNum=0;
    			for(var i=0;i<list.length;i++){
    				sumNum=list.length;
    				sumMoney=sumMoney+list[i].couponValue;
				    addTr+=" <tr> <td align='left'  valign='middle'><input  type='checkbox' id='couponId_"+list[i].couponId+"' onClick='clicExperienceKcoupon("+list[i].couponValue+","+list[i].couponId+")' name='couponName' value='"+list[i].couponId+"'/></td>" +
				 		" <td align='center' valign='middle' class='bg'><em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+list[i].couponValue+"</em>元</td>" +
				 		" <td align='center' valign='middle'>"+list[i].couponEndDate+"</td> </tr>";
	    				
    			}
				$("#addExperienceBpCoupons").append(addTr);
				$("#sumExperienceMoney").text(sumMoney);
				$("#sumExperienceNum").text(sumNum);
				
				$("#expectMoney").text("0");//每次点击投标，没有选择优惠券时，预期收益为0
    		},
    		error:function(request){
    		
    		}
    	})
}

/*****************投标客户列表**********************/
//操作标的js
//校验js 检查用户是否登录 金额 是否充足
function check(bidid,afterMoney,yearInterestRate,flag){
	$("#money_span").text("");
	var memberId=$("#memberId").val();
	var grade=$("#grade").val();
	var from = $("#personCenter").val();
	if(grade){
		$("#mySignMoney").val($("#sign_Money").val());
		addBpCoupons();
		addExperienceBpCoupons();
		bidId=bidid;
		from=from;
	}else if(memberId && flag){
		if(from!=null&&from!=''&&from!='undefined'){
			location.href=basepath+"user/toAccessBpCustMember.do?from=1";
		}else{
			location.href=basepath+"user/toAccessBpCustMember.do?bidId="+bidId+"&bidType="+bidtype
		}
	}
	//第一检查是否登录
	checkUserIsLogin(bidId,afterMoney,yearInterestRate);
}
//投标
function bidHandler(){
	var sycoupon = $("#sycoupon").text();
	var couponId="";
	var myCoupon="";
	//是否选中服务协议（体验标不用选中协议）
	if($("#readAgreement").attr("checked")=="checked" || bidtype=="experience"){
	}else{
		document.getElementById("readAgreement_msg").style.color = "red";
		$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#readAgreement_msg").html("请选中协议");
		return false;
	}
	var object = $("#mySignMoney");
	var currM = $('#currM').val();
 	if(parseInt(object.val())>parseInt(currM)){
		$("#money_span").text("您输入的投资金额大于账户可用余额。");
		return false;
	}
	$('input[name="couponName"]:checked').each(function(i, v){
		if(i==0){
			couponId=$(this).val();//得到当前选择的优惠券id
		}else{
			myCoupon="myCoupon";
			 $("#money_span").html("每次只能选择一张优惠券哦!");
		}
	});
	if(myCoupon=="myCoupon"){
		return false;
	}
	if(couponId!=""&&sycoupon=="否"){
		 $("#money_span").html("该项目不支持使用优惠券!");
		 return false;
	}
	var typeid = $("#typeid").val();
/*	if(typeid=='10'){
		var a_plan = $("#A_plan").val();
		if(a_plan!='0'){
			$("#mymess").attr("style","display:none");
			$("#mymessage").attr("style","display:none");
			$("#mymessage2").attr("style","height: 64px;color: #000;font-weight: 400;font-size: 16px;text-align: center;");
			return false;
		}
	}*/
	var userMoney=$("#mySignMoney").val();//购买标金额
	var plmmMoney=$("#sign_Money").val();//理财购买金额
	var afterMoney=$("#afterM").val();
	var bidId=$("#bidId").val();
	var radioType=$(':radio[name=investType]');//U计划需要radio对象
	if(bidtype=="plmm"){
		radioType.each(function(){
			var $thisCurr=$(this);
			if($thisCurr.attr('checked')=="checked"){
				$("#investType").val($thisCurr.val());
			}
		});
		ajaxPlMM(bidId,bidName,plmmMoney,couponId,'mmplan');
	}else if("uplmm"==bidtype){//U计划
		radioType.each(function(){
			var $thisCurr=$(this);
			if($thisCurr.attr('checked')=="checked"){
				$("#investType").val($thisCurr.val());
			}
		});
		ajaxPlMM(bidId,bidName,plmmMoney,couponId,'UPlan');
	}else if(bidtype=="experience"){
	    ajaxPlMMExperience(bidId,bidName,userName,userId,couponId);
	}else {
		if(chkMoney('mySignMoney',afterMoney,'currM')){
			ajaxBid(bidId,bidName,userMoney,userName,userId,couponId);
		}
	}
}
//投标金额千分位显示
function ckMoney(){
	var input = $("#signMoney");
   	 input.val() =input.val().replace(/\d+?(?=(?:\d{3})+$)/g, function(s){
                return s +',';
     });
      input.onfocus =function(){
           input.val() =input.val().replace(/,/g, '');
      }
}
 
//检查用户是否登录 是否具有投资资格
function checkUserIsLogin(bidid,afterMoney,yearInterestRate){
	var bid =bidid;
	var afterMoney=afterMoney;
	$.ajax({
        type: "POST",
        url: basepath+"checkUserIsLoginlogin.do",
        dataType: 'json',
        beforeSend:function(){
             $("#bidForm").css('display','none');
             $("#bidLoad").empty();
             $("#bidLoad").append('<img src="'+themepath+'images/loading.gif"/><span>账户检查中请等待...</span>');
        },
        success: function(responseText, statusText) {
       		$("#yearInterestRate").html(yearInterestRate);
       	 	$("#plBidInfobidId").val(bidid);
       		if (statusText == "success") {
            	//用户未登录
            	if(!responseText.success){
            		$("#bidLoad").empty();
            		//url 也得改变
            		var url = $("#bidLogin").attr("href");
            		if(bidtype=="plmm"){
            			$("#bidLogin").attr("href",url+"creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId="+bid);
            		}else if(bidtype=="experience"){
            		    $("#bidLogin").attr("href",url+"creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId="+bid);
            		}else{
            			$("#bidLogin").attr("href",url+"creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId="+bid);
            		}
            		$("#bidMsg").html("<a href='"+basepath+"/thirdreg.do'>"+responseText.msg+"</a>");
            		$("#msgWin").css('display','block');
        		  	// 隐藏关闭按钮
        		  	$("#afterMoney").html(afterMoney+"元");
        		  	$("#myMoney").html("登录后显示");
            	}else{
            		userName=responseText.userName;
            		userId=responseText.userId;
            		checkUserIsSecurity();
            		$("#afterMoney").html(fmoney(afterMoney,2)+"元");
            		$("#myMoney").html(fmoney(responseText.myPmoney,2)+"元"); // myPmoney 平台账户 myTmoney 总金额  myDmoney冻结金额
            		$("#currM").val(responseText.myPmoney);
            	}
            } 
        }
    })
}
//检查用户是否具有投资资格
function checkUserIsSecurity(){
	
	$.ajax({
        type: "POST",
        url: basepath+"checkUserIsSecuritylogin.do",
        dataType: 'json',
       success: function(responseText, statusText) {
                      if (statusText == "success") {
                	//没有投资资格
                	if(!responseText.success){
                		$("#bidLogin").empty();
                		$("#li_myhome").removeAttr("style");

                		$("#bidLoad").empty();
                		 $("#bidMsg").html("<a href='"+basepath+"/thirdreg.do'>"+responseText.msg+"</a>");
                		  $("#msgWin").css('display','block');
                	}else{
                		
                		 $("#bidMsg").empty();
                		 //隐藏load 样式
                		$("#bidLoad").empty();
                		 $("#bidForm").css('display','block');
                	}
                } 
            }
    })

}
//投标开始
function ajaxBid(bidId,bidName,userMoney,userName,userId,couponId){
	  var bidFormForm = $('#bid_Form');
	  $("#plBidInfouserMoney").val(userMoney);
	   $("#plBidInfouserName").val(userName);
	  $("#plBidInfouserId").val(userId);
	  $("#couponId").val(couponId);
	  var mobile=$("#mobile").val();
	  if(mobile=="mobile"){
		  $('#bid_Form').attr("action",basepath+"creditFlow/financingAgency/bidingPlBidInfo.do?retUrl=creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId="+bidId+"&mobile=mobile");
	  }else{
	  	  $('#bid_Form').attr("action",basepath+"creditFlow/financingAgency/bidingPlBidInfo.do?retUrl=creditFlow/financingAgency/bidPlanDetailPlBidPlan.do?bidId="+bidId);
	  }
	  
	/*  $("#plBidInfobidId").val(bidId);
	  $("#plBidInfobidName").val(bidName);
	 */
       
        bidFormForm.submit();
	/*$.ajax({
        type: "POST",
        url: basepath+"creditFlow/financingAgency/bidingPlBidInfo.do",
        data:{
        	 "plBidInfo.bidId":bidId,
        	 "plBidInfo.bidName":bidName,
        	 "plBidInfo.userMoney":userMoney,
        	 "plBidInfo.userName":userName,
        	 "plBidInfo.userId":userId
        },
        dataType: 'json',
         beforeSend:function(){
             $("#bidForm").empty();
             $("#bidLoad").append('<img src="'+themepath+'images/loading.gif"/><span>系统正在帮您投标请耐心等待...</span>');
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                	//用户为未登录
                	if(responseText.success){
                		//隐藏load 样式
                		$("#bidLoad").empty();
                		//加入新的消息提示
                		 $("#bidMsg").append(responseText.msg);
                		  $("#msgWin").css('display','block');
                		 // 隐藏关闭按钮
                		 // $("#lean_overlay_close").remove();
                		    //隐藏登录按钮
                		 $("#bidLogin").remove();
                	}else{
                		 $("#bidLoad").empty();
                		 //加入新的消息提示
                		 $("#bidMsg").append(responseText.msg);
                		 $("#msgWin").css('display','block');
                		   // 隐藏关闭按钮
                		// $("#lean_overlay_close").remove();
                		 if(responseText.type==0){
                		  $("#bidLogin").remove(); 
                		  $("#bidReset").css('display','block');
                		  
                		 }
                		
                	}
                } 
            }
    })*/
}

//理财计划购买开始
function ajaxPlMM(bidId,bidName,userMoney,couponId,keystr){
	var bidFormForm = $('#bid_Form');
	  $("#plBidInfobidId").val(bidId);
	  $("#plBidInfobidName").val(bidName);
	  $("#plBidInfouserMoney").val(userMoney);
	  $("#couponId").val(couponId);
	  $('#bid_Form').attr("action",basepath+"creditFlow/financingAgency/buyBidplanPlManageMoneyPlan.do?keystr="+keystr+"&retUrl=creditFlow/financingAgency/getDetailPlManageMoneyPlan.do?mmplanId="+bidId+"");
	  bidFormForm.submit();
	  
}

//体验标投标开始
function ajaxPlMMExperience(bidId,bidName,userName,userId,couponId){
	  var bidFormForm = $('#bid_Form');
	  $("#plBidInfobidId").val(bidId);
  //  $("#plBidInfobidName").val(bidName);
	  $("#couponId").val(couponId);
	  $('#bid_Form').attr("action",basepath+"creditFlow/financingAgency/buyExperienceplanPlManageMoneyPlan.do?retUrl=creditFlow/financingAgency/getExperienceDetailPlManageMoneyPlan.do?mmplanId="+bidId);
	  bidFormForm.submit();
	  
}


/**
 * 
 * @param {} afterMoney 剩余金额
 * @param {} statrM 起投金额
 * @param {} risM 递增金额
 */
function bidAll(afterMoney,statrM,risM){
	var myMoney=$("#currM").text();//当前账户余额
	if(myMoney !==""){
		myMoney = myMoney.replace(',','')
	}
	var currM=$("#currM").val();//当前账户余额
	if(currM==""&&myMoney!=""){
		currM=myMoney;
	}

	var maxMoney=$("#maxMoney").val();//投资上限
	var inputMoney;// 投资金额
	if(parseFloat(myMoney)==0){
		alert("余额不足。");
	}else if(afterMoney==0){
		alert("已满标。");
	}else if(parseInt(myMoney)<parseInt(statrM)){
		alert("账户余额小于起投金额，不能进行投标。");
	}else if(parseInt(myMoney)<=parseInt(afterMoney)){
		inputMoney=myMoney;
	}else{
		inputMoney=afterMoney;
		
	}
	
	if(inputMoney!=undefined){
		if(maxMoney!=null&&maxMoney!=undefined){
			if(parseInt(inputMoney)>parseInt(maxMoney)){
				$("#sign_Money").val(parseInt(maxMoney));
			}else{
				$("#sign_Money").val(parseInt(inputMoney));
			}
		}else{
			$("#sign_Money").val(parseInt(inputMoney));
		}
	}
}

function bidAll1(afterMoney,statrM,risM){
    var myMoney=$("#currM").text();//当前账户余额
    if(myMoney !==""){
        myMoney = myMoney.replace(',','')
    }
    /*var currM=$("#currM").val();//当前账户余额
    alert("currM:"+currM+"myMoney"+myMoney);
    if(currM==""&&myMoney!=""){
        currM=myMoney;
    }*/

    var maxMoney=$("#maxMoney").val();//投资上限
    var inputMoney;// 投资金额
    if(parseFloat(myMoney)==0){
        //alert("余额不足。");
        $("#moneyspan").html("<span style='font-size:12px;'>余额不足</span>");
    }else if(afterMoney==0){
        //alert("已满标。");
        $("#moneyspan").html("<span style='font-size:12px;'>已满标</span>");
    }else if(parseInt(myMoney)<parseInt(statrM)){
        //alert("账户余额小于起投金额，不能进行投标。");
        $("#moneyspan").html("<span style='font-size:12px;'>账户余额小于起投金额，不能进行投标</span>");
    }else if(parseInt(myMoney)<=parseInt(afterMoney)){
        inputMoney=myMoney;
    }else{
        inputMoney=afterMoney;

    }

    if(inputMoney!=undefined){
        if(maxMoney!=null&&maxMoney!=undefined){
            if(parseInt(inputMoney)>parseInt(maxMoney)){
                $("#sign_Money").val(parseInt(maxMoney));
            }else{
                $("#sign_Money").val(parseInt(inputMoney));
            }
        }else{
            $("#sign_Money").val(parseInt(inputMoney));
        }
    }
}
//优惠券选择
function clickcoupon(money,couponId){
	var couponId_Id = "couponId_"+couponId;
	if($("#"+couponId_Id).attr("checked")=="checked"){
		$(':checkbox[name=couponName]').removeAttr('checked'); 
		var maxCouponMoney =$("#maxCouponMoney").text();
		var mycouponsType =$("#mycouponsType").text();
		if(mycouponsType!="4"){
			if(Number(money)>Number(maxCouponMoney)){
				//散标提示
				$("#money_span").html("优惠券面值超过单笔最大面值："+maxCouponMoney+"元");
				//理财计划提示
				$(".xy-ui-form-item-explain").text("优惠券面值超过单笔最大面值："+maxCouponMoney+"元");
				$(".xy-ui-form-item-explain").show();
				return false;
			}else{
				$("#money_span").html("");
				$(".xy-ui-form-item-explain").text("");
				$(".xy-ui-form-item-explain").hide();
			}
		}
											
		$("#"+couponId_Id).attr('checked',true);
		$("#setMoney").text(money);
		var returnRatio = $("#returnRatio").text();
		$("#validMoney").text(Number(money)*returnRatio/100);
		/*$('input[name="couponName"]:checked').each(function(i, v){
			if(i==0){*/
				/*var mySignMoney = $("#mySignMoney").val();
				var returnRatio = $("#returnRatio").text();
				if(mySignMoney!=""){
					if(Number(mySignMoney)<Number(money)){
						$("#ratioMoney").text(Number(mySignMoney)*returnRatio/100);
					}else{
						$("#ratioMoney").text(Number(money)*returnRatio/100);
					}
				}else{
					$("#ratioMoney").text(Number(money)*returnRatio/100);
				}*/
			/*}else{
				myCoupon="myCoupon";
				
				 //$("#money_span").html("每次只能选择一张优惠券哦!");
			}
		});*/
	}else{
		$("#ratioMoney").text("0");
		$("#validMoney").text("0");
	}
}

function clicExperienceKcoupon(money,couponId){
	
	var couponId_Id = "couponId_"+couponId;
	if($("#"+couponId_Id).attr("checked")=="checked"){
		$(':checkbox[name=couponName]').removeAttr('checked'); 
		$("#"+couponId_Id).attr('checked',true);
		$("#setMoney").text(money);
		var dayRate = $("#yeaRate").val();
		var investlimit = $("#investlimit").val();
		$("#expectMoney").text((Number(money)*dayRate*investlimit/(100*360)).toFixed(2));
	}else{
		$("#expectMoney").text("0");
	}
}
//理财计划购买
function show(){
	var grade=$("#grade").val();
	var login = $(".loginname").text();
	if(login==""||login==null||login==undefined){
		window.location.href=basepath+"/htmllogin.do";
	}else if(grade){
		$("#sign_Money").val($("#signMoney").val());
		document.getElementById("xy-lc-box").style.display="";
	}else{
		location.href=basepath+"user/toAccessBpCustMember.do?bidId="+bidId+"&bidType="+bidtype;
	}
}
function checkShow(){
	var object = $("#sign_Money");
	var s = $("#sign_Money").val();
	var afterM = $('#afterM').val();
	var currM = $('#currM').val();
	var sx = '${plan.limitMoney}';
	var lx = $('.xy-lc-amount-value').text();
	var a_plan = $("#A_plan").val();
	if(a_plan==undefined){
		$(".xy-ui-form-item-explain").text("请您登录后购买！");
		$(".xy-ui-form-item-explain").show();
			return false;
		}
	 var reg = /(?!^0\d*$)^\d{1,16}(\.\d{1,2})?$/;
     flag = reg.test(object.val());
	if(s==''||s==null){
	$(".xy-ui-form-item-explain").text("金额不能为空");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(!flag){
	$(".xy-ui-form-item-explain").text("金额必须大于0且为整数或小数。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(!s.match(/^[0-9]*$/)){
	$(".xy-ui-form-item-explain").text("金额必须为数字");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(parseInt(object.val())> parseInt(sx)){
	$(".xy-ui-form-item-explain").text("您输入的金额大于了当前标的投资上限。");
	$(".xy-ui-form-item-explain").show();
		return false;
	
	}
	if(parseInt(object.val())>parseInt(afterM)){
	$(".xy-ui-form-item-explain").text("您输入的投资金额大于标的剩余金额。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	if(parseInt(object.val())>parseInt(currM)){
	$(".xy-ui-form-item-explain").text("您输入的投资金额大于您的账户余额。");
	$(".xy-ui-form-item-explain").show();
		return false;
	}
	//是否选中服务协议
	if($("#readAgreement").attr("checked")=="checked"){
	}else{
		document.getElementById("readAgreement_msg").style.color = "red";
		$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
		$("#readAgreement_msg").html("请选中协议");
		return false;
	}
	
	/*if(parseInt(s)%1!=0){
	$(".xy-ui-form-item-explain").text("金额必须为100的倍数");
	$(".xy-ui-form-item-explain").show();
		return false;
	}*/
	$("#signyuan").text(s);
	bidHandler();
}

$(function() {
	$("#readAgreement").click(function(){
		if($("#readAgreement").attr("checked")=="checked"){
			$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/successicon.jpg"/>');
			$("#readAgreement_msg").html("已阅读协议");
		}else{
			document.getElementById("readAgreement_msg").style.color = "red";
			$("#readAgreement_msg_img").empty().append('<img width="18px"  src="'+themepath+'images/errorimg.png"/>');
			$("#readAgreement_msg").html("请选中协议");
		}
	})
	
	
		var _move=false;//移动标记  
		var _x,_y;//鼠标离控件左上角的相对位置  
		    $(".xy-lc-box").click(function(){  
		    	
		        }).mousedown(function(e){  
		        _move=true;  
		        _x=e.pageX-parseInt($(".xy-lc-box").css("left"));  
		        _y=e.pageY-parseInt($(".xy-lc-box").css("top"));  
		        //$(".xy-lc-box").fadeTo(20, 0.5);//点击后开始拖动并透明显示  
		    	   $(this).mousemove(function(e){  
		    		   if(_move){  
		    			   var x=e.pageX-_x;//移动时根据鼠标位置计算控件左上角的绝对位置  
		    			   var y=e.pageY-_y;  
		    			   $(".xy-lc-box").css({top:y,left:x});//控件新位置  
		    		   }  
		    	   }).mouseup(function(){  
		    		   _move=false;  
		    		   $(".xy-lc-box").fadeTo("fast", 1);//松开鼠标后停止移动并恢复成不透明  
		    	   });
		    }); 
	

});