$(function(){
			$("#innerNav li.accountSafety").addClass("current").append("<em class=\"curr\">&nbsp;</em>");
			var path = $("#path").val();
			//确定按钮
			$("#authenticate").click(function(){
				//if ($("#truename").val() != ''){
				//	$("#user_real_name").val($("#truename").val());
				//}
				$("#authenticateForm").submit();
			});

			//上传证件正面图片
			//uploadFile("card_front","user_card_image_front",false,"yes",120,80,uploadOnComplete,'img','https://user.jubao51.com');
			//上传证件反面图片
			//uploadFile("card_back","user_card_image_back",false,"yes",120,80,uploadOnComplete,'img','https://user.jubao51.com');
			//上传用户签名图片
			//uploadFile("sign","user_sign",false,"yes",120,80,uploadOnComplete,'img','https://user.jubao51.com');

			/* if ("" != ""){
				var src = "/upload/";
				$("#user_card_image_front").val("");
				$("#card_front_img").attr("src", src);
				$("#card_front_img").parents(".upload-img-wrap").css("display", "inline-block");
			} */

			/* if ("" != ""){
				var src = "/upload/";
				$("#user_card_image_back").val("");
				$("#card_back_img").attr("src", src);
				$("#card_back_img").parents(".upload-img-wrap").css("display", "inline-block");
			} */

			/* if ("" != ""){
				var src = "/upload/";
				$("#user_sign").val("");
				$("#sign_img").attr("src", src);
				$("#sign_img").parents(".upload-img-wrap").css("display", "inline-block");
			} */

			$(".upload-img-wrap .item-close").click(function(){
				$(this).parents(".upload-img-wrap").hide();
				$(this).parents(".upload-img-wrap").prevAll(":hidden").val("");
			});
			
			//证件类型
			//$("#cardtype").append($.ajax({async:false,cache:false,url:"/member/getCardTypeList.html?card_type_id="}).responseText);
		});

		//校验表单
		function checkForm(){
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

			//校验证件类别
			if (isEmpty($("#cardtype").val())||$("#cardtype").val()<0){
				showTip("cardtype","证件类别不能为空");
				return false;
			}

			//校验证件号码
			if (isEmpty($("#cardcode").val())){
				showTip("cardcode","证件号码不能为空");
				return false;
			}else if($("#cardtype").val().toUpperCase() == "S"){
				if (!isIdCardNo($("#cardcode").val())){
					showTip("cardcode","无效的身份证号码");
					return false;
				}
			}

			//校验证件扫描正面图片
			/* if (isEmpty($("#user_card_image_front").val())){
				showTip("user_card_image_front_div","请上传证件扫描正面图片");
				return false;
			} */

			//校验证件扫描反面图片
			/* if (isEmpty($("#user_card_image_back").val())){
				showTip("user_card_image_back_div","请上传证件扫描反面图片");
				return false;
			} */

			//校验用户签名图片
			/* if (isEmpty($("#user_sign").val())){
				showTip("user_sign_div","请上传用户签名图片");
				return false;
			} */
			return true;
		}

		//图片上传回调
		function uploadOnComplete(event,data,objId,multi){
			var eventObj = event.srcElement || event.target;
			clearUploadTip(eventObj.id);
			
			if (data.error==1){
				showUploadTip(eventObj.id, data.datas);
			}else if (data.error == 0 ){
			  if (multi){
				  var oldVal = $("#"+objId+"").val();
				  if (oldVal != null && oldVal != "" ){
					oldVal = oldVal+",";
				  }
				$("#"+objId+"").val(oldVal+data.datas);
			  }else{
				$("#"+objId+"").val(data.datas);
				$(eventObj).parent().find(".upload-img-wrap img").attr("src", "/upload/Temp/s_" + data.datas);
				$(eventObj).parent().find(".upload-img-wrap").css("display", "inline-block");
			  }
			}
		}
