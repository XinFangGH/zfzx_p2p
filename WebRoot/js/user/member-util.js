$(function(){
		$("#innerNav li.myHome").addClass("current").append("<em class=\"curr\">&nbsp;</em>");
		//上传用户头像图片
		uploadFile("user_image","user_image_name",false,"yes",125,125,uploadOnComplete,'img');
		var src = "";
		if ("" != ""){
			src = "/upload/";
		}else{
			src = "/images/user/morentouxiang.jpg";
		}
		$("#user_image_img").attr("src", src);

	});

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
			$("#user_image_type").val("U");

			//更新数据库
			$.ajax({
				url:"/user/updateUserHead.html?user_image_name=" + data.datas + "&user_image_type=U",
				dataType:"text",
				async:false,
				success:function(data){
					if (data == "error"){
						showUploadTip("user_image", "头像更新失败，请重新上传",'https://user.jubao51.com');
					}else{
						showUploadTip("user_image", "头像更新成功",'https://user.jubao51.com');
					}
				}
			});
			
			$(eventObj).parents(".avatar-wrap").find("img").attr("src", "/upload/pic/member/head/" + data.datas);
		  }
		}
	}