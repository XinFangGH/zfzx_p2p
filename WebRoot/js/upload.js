$(function(){
	$(".file").change(function() {
		var filepath=$("input[name='upfile']").val(); 
		var extStart=filepath.lastIndexOf("."); 
		var ext=filepath.substring(extStart,filepath.length).toUpperCase(); 
		if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){ 
		     //检测允许的上传文件类型
		     ymPrompt.alert({title:'系统提示信息',message:'图片限于bmp,png,gif,jpeg,jpg格式',width:300,height:150});
		     return false; 
		}
		 var file_size = this.files[0].size;
         var size = file_size / 1024;
         if (size > 1536) {
         	 ymPrompt.alert({title:'系统提示信息',message:'上传的图片大小不能超过1.5M',width:300,height:150});
            return false;        
         } 
      
		var uploadWay = $("#uploadWay").text();
		var conditionId= $(this).next().text();
		var form = $("<form  enctype='multipart/form-data' method='post'></form>");
		form.attr('action', basepath + 'loan/saveHeadImageP2pLoanProduct.do?uploadWay='+uploadWay+'&conditionId='+conditionId);
			var my_fileInput = $(this);//将当前的这个文本域添加到动态创建的form中
			form.append(my_fileInput); // 附加到Form   
			form.appendTo(document.body);//追加到浏览器中
			form.ajaxSubmit( {
				clearForm : false,
				dataType : "JSON",
				success : function(responseText, statusText) {
					if (statusText == "success"){
						if (responseText.result == true) {
							 location.reload() 
							//$("#imgNone").css("display","none");
							//$("#imgJpg").append("<img src="+$("#myBase").text()+"/"+""+responseText.Msg+"  width=100 height=80>");
						} else {
							 location.reload() 
						}
					} else {
							 location.reload() 
					}
				},
				error : function() {
							 location.reload() 
						}
			});
		})
		
		//浏览图片
		function wad_gytupian() {
		    $("a[rel=group1]").fancybox({ 
		        'titlePosition' : 'over', 
		        openEffect: 'elastic',
		        'cyclic'        : true, 
		        'centerOnScroll': true,
		        'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) { 
		                    return '<span id="fancybox-title-over">' + (currentIndex + 1) + 
		                    ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>'; 
		        },
		        onStart: function () {
		            $('body').css('overflow','hidden');
		        },
		        onClosed: function () {
		            $('body').css('overflow','auto');
		        }
		        
		    }); 
		    $("a[rel=group2]").fancybox({ 
		        'titlePosition' : 'over', 
		        openEffect: 'elastic',
		        'cyclic'        : true, 
		        'centerOnScroll': true,
		        'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) { 
		                    return '<span id="fancybox-title-over">' + (currentIndex + 1) + 
		                    ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>'; 
		        },
		        onStart: function () {
		            $('body').css('overflow','hidden');
		        },
		        onClosed: function () {
		            $('body').css('overflow','auto');
		        }
		        
		    }); 
		    $("a[rel=group3]").fancybox({ 
		        'titlePosition' : 'over', 
		        openEffect: 'elastic',
		        'cyclic'        : true, 
		        'centerOnScroll': true,
		        'titleFormat'   : function(title, currentArray, currentIndex, currentOpts) { 
		                    return '<span id="fancybox-title-over">' + (currentIndex + 1) + 
		                    ' / ' + currentArray.length + (title.length ? '   ' + title : '') + '</span>'; 
		        },
		        onStart: function () {
		            $('body').css('overflow','hidden');
		        },
		        onClosed: function () {
		            $('body').css('overflow','auto');
		        }
		        
		    });
		}
	    wad_gytupian();
})

function deleteImg(id){
		$.ajax({
			url: basepath + 'loan/deleteMaterialImgP2pLoanProduct.do?id='+id,
			dataType:"JSON",
			success:function(responseText, statusText){
				if (statusText == "success"){
					if (responseText.result == true) {
						 location.reload() 
					}else{
						alert("删除失败");
					}
				}else{
					alert("删除失败");
				}
			}
		})
}

function closeDiag(){
		window.parent.location.reload();
	}