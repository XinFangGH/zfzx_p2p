function gbcount(message, total) { 
		var max; 
		max = total.value; 
		if (message.value.length > max) { 
			message.value = message.value.substring(0, max); 
			showTip("financ_detail", "详情不能超过1333个字!");
			return false; 
		} 
	} 
	$(function(){

		$('#financ_money').on('keyup', function() {
				var val = $('#financ_money').val();
				setIntLen(val);
				var qfwVal = add_Qfw(getoff_Qfw(val));
				$('#financ_money').val(qfwVal);
				//shortCalc(capital);			
		});
	
		$("#innerNav li.accountSafety").addClass("current").append("<em class=\"curr\">&nbsp;</em>");

		//确定按钮
		$("#applyFinance").click(function(){
		    if($("#financ_detail").val().length>1333)
			{	
				showTip("financ_detail", "详细不能超过1333字符！!");
		        return false;
			}
			$("#applyFinanceForm").ajaxSubmit({
				beforeSubmit:checkForm,
				clearForm:false,
				dataType:"JSON",
				success:function(responseText, statusText){
					if (statusText == "success"){
						if (responseText.result == 1){
							location.href = "/finance/uploadFinanceAttachment.html";
						}else{
							showCommonTip(".common-tip",responseText.errMsg);
							removeGray("applyFinance");
						}
					}else{
						showCommonTip(".common-tip","融资申请提交失败");
						removeGray("applyFinance");
					}
				},
				error:function(){
					showCommonTip(".common-tip","融资申请提交失败");
					removeGray("applyFinance");
				}
			});
		});

		//省份
		$("#financ_province").change(function(){
			$("#financ_city option").remove();
			$("#financ_city").append("<option value=''>请选择</option>");
			if (!isEmpty($(this).val())){
				$.ajax({
					url:"getListArea.do?ParentCode=" + $(this).val(),
					dataType:"text",
					success:function(data){
						if (data){
							$("#financ_city").append(data);
						}
					}
				});
			}
		});

		$("#financ_province").one("mouseover",function(){
				$(this).append($.ajax({async:false,url:"getListArea.do"}).responseText);
			}
		);
	});

	//校验表单
	function checkForm(){
		//校验期限
		if (isEmpty($("#financ_limited_number").val())){
			showTip("financ_limited_number","期限不能为空");
			return false;
		}
		if (!checkNUM($("#financ_limited_number").val())){
			showTip("financ_limited_number","期限应为正整数");
			return false;
		}
		
		if (parseInt($("#financ_limited_number").val()) <= 0){
			showTip("financ_limited_number","期限应大于0");
			return false;
		}
		
		if ($("#financ_limited_number").val().substring(0, 1) == "0"){
			showTip("financ_limited_number","期限不能以0开头");
			return false;
		}
		
		//校验类别
		if (isEmpty($("#financ_method").val())){
			showTip("financ_method","请选择类别");
			return false;
		}
		
		//校验还款方式
		if (isEmpty($("#financ_repayment_type").val())){
			showTip("financ_repayment_type","请选择还款方式");
			return false;
		}
		
		//校验省
		if (isEmpty($("#financ_province").val())){
			showTip("financ_province","请选择省份");
			return false;
		}

		//校验市
		if (isEmpty($("#financ_city").val())){
			showTip("financ_city","请选择城市");
			return false;
		}
		
		//校验金额
		if (isEmpty($("#financ_money").val())){
			showTip("amountShow","金额不能为空");
			return false;
		}
		
		if (!checkNUM($("#financ_money").val().replace(/,/g,""))){
			showTip("amountShow","金额应为正整数");
			return false;
		}
		
		if (parseInt($("#financ_money").val().replace(/,/g,"")) <= 0){
			showTip("amountShow","金额应大于0");
			return false;
		}
		
		if ($("#financ_money").val().substring(0, 1) == "0"){
			showTip("amountShow","金额不能以0开头");
			return false;
		}
		
		if (isAmountErr($("#financ_money").val())){
			showTip("amountShow","金额格式不正确");
			return false;
		}
		
		
		if (parseInt($("#financ_money").val().replace(/,/g,"")) > 999999999){
			showTip("amountShow","金额不能超过999999999");
			return false;
		}
		
		//校验行业
		if (isEmpty($("#financ_trade").val())){
			showTip("financ_trade","请选择行业");
			return false;
		}
		
		//校验标题
		if (isEmpty($("#financ_name").val())){
			showTip("financ_name","标题不能为空");
			return false;
		}
		
		//校验详情
		if (isEmpty($("#financ_detail").val())){
			showTip("financ_detail","详情不能为空");
			return false;
		}
		activeGray("applyFinance");
		return true;
	}
	//校验数字长度，限制9位
	function checkLength(obj)
	{
		var src = obj.value;
		var target = src.replace(new RegExp(",","gm"),""); 
		if(target.length>9)
		{
			document.getElementById("amountShow").value=target.substr(0,9);
		}
	}

	
	/**
	 *
	 * @param fileId  文件元素标识

	 * @param resultId  上传文件结果保存元素id（<input type='hidden' id="uploadFiles" name="uploadFiles"）;
	 *
	 * @param multi  是否支持多文件上传
	 *
	 * @param comFlag 是否生成缩略图
	 *
	 * @param s_width 生成缩略图宽度
	 *
	 * @param s_height 生成缩略图高度
	 *
	 * @param onComplete 上传完成后处理函数
	 *
	 * @param type 文件类型: img、file
	 */
	function uploadFile(fileId,resultId,multi,comFlag,s_width,s_height,onComplete,type){
		if (!onComplete){
			onComplete = defaultUploadOnComplete;
		}
		var fileExt = '';//允许上传的文件后缀
		var fileDesc = '';//在浏览窗口底部的文件类型下拉菜单中显示的文本
		if(!type){
			type = "img";
		}
		
		if (type == 'img'){
			fileExt = '*.jpg;*.gif;*.png;*.jpeg;*.bmp';
			fileDesc = 'jpg、gif、png、bmp格式的图片';
		}else{
			fileExt = '*.txt;*.xls';
			fileDesc = 'txt、xls格式的文件';
		}
		
		$(document).ready(function() {
			$('#'+fileId+'').uploadify({
				'uploader'     : '/man/js/uploadify/uploadify.swf?var=' + (new Date()).getTime(),
				'script'       : '/servlet/upload',
				'cancelImg'    : '/man/js/uploadify/cancel.png',
				'buttonImg'    : '/man/js/uploadify/button.jpg',
				'width'        : 112 ,
				'height'       : 32,
				'auto'         : true ,
				'multi'        : multi,
				'sizeLimit'    : 10 * 1024 * 1024, 
				'scriptData'   : {'comFlag':comFlag,'smallWidth':s_width,'smallHeight':s_height,'type':type},
				'fileExt': fileExt,
				'fileDesc': fileDesc, 
				'onAllComplete':function(event,data){	} ,
				'onComplete':function(event, queueId, fileObj, response, data){
			   		var result = eval('(' + response + ')');
				   	onComplete(event,result,resultId,multi);
				},
				onError : function (event, queueId, fileObj, errorObj){
					var obj = event.target || event.srcElement;
					$(obj).parent().find(".percentage").html("&nbsp;&nbsp;<span class='font-red'>" + (errorObj.type == "File Size" ? "图片不能超过10MB！" : "<span style='display:none;'>" + errorObj.type + "</span>系统繁忙，请稍候再试！") + "</span>");
					return false;
				}
			});
		});
	}

	function defaultUploadOnComplete(event,data,objId,multi){
	    if (data.error==1){
	       alert(data.datas);
	    }else if (data.error == 0 ){
	      if (multi){
		      var oldVal = $("#"+objId+"").val();
		      if (oldVal != null && oldVal != "" ){
		        oldVal = oldVal+",";
		      }
	      	$("#"+objId+"").val(oldVal+data.datas);
	      }else{
	      	$("#"+objId+"").val(data.datas);
	      }
	    }
  }
