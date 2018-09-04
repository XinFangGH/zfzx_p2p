
		var upload1, upload2;
		
		//上传类型
		//var requestType = window.location.href.split("?");
		
		var requestType = window.location.href;
		var arrayStr = new Array(); 
		arrayStr = requestType.split("&");
		//alert("arrayStr[3]==="+arrayStr[3]);
		//var type = document.getElementById("type").value;
		var strType=arrayStr[0].split("=");
		window.onload = function() {
			upload1 = new SWFUpload({
				// Backend Settings
				//upload_url: basepath+"FileUploadServlet?saveDataId=saveDataId1&"+requestType[1],
				upload_url: basepath+"FileUploadServlet?type="+strType[1]+"&saveDataId=saveDataId1&"+arrayStr[3],
				//post_params: {"PHPSESSID" : "<?php echo session_id(); ?>"},

				// File Upload Settings
				file_size_limit : "1.5 MB",
				file_types : ".gif,.jpg,.jpeg,.bmp,.png",
				file_types_description : "All Files",
				file_upload_limit : 10,
				file_queue_limit : 0,

				// Event Handler Settings (all my handlers are in the Handler.js file)
				swfupload_preload_handler : preLoad,
				swfupload_load_failed_handler : loadFailed,
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : basepath+"/js/swfupload/scripts/swfupload/images/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder1",
				button_width: 61,
				button_height: 22,
				
				// Flash Settings
				flash_url : "../js/swfupload/scripts/swfupload/swfupload.swf",
				flash9_url : "../js/swfupload/scripts/swfupload/swfupload_fp9.swf",
			

				custom_settings : {
					progressTarget : "fsUploadProgress1",
					cancelButtonId : "btnCancel1"
				},
				
				// Debug Settings
				debug: false
			});
			/*upload2 = new SWFUpload({
				// Backend Settings
				upload_url: basepath+"FileUploadServlet?saveDataId=saveDataId2",
				//post_params: {"PHPSESSID" : "<?php echo session_id(); ?>"},

				// File Upload Settings
				file_size_limit : "200",	// 200 kb
				file_types : "*.jpg;*.gif;*.png",
				file_types_description : "Image Files",
				file_upload_limit : "10",
				file_queue_limit : "5",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				swfupload_preload_handler : preLoad,
				swfupload_load_failed_handler : loadFailed,
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueError,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : basepath+"/js/swfupload/scripts/swfupload/images/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder2",
				button_width: 61,
				button_height: 22,
				
				flash_url : basepath+"/js/swfupload/scripts/swfupload/swfupload.swf",
				flash9_url : basepath+"/js/swfupload/scripts/swfupload/swfupload_fp9.swf",

				swfupload_element_id : "flashUI2",		// Setting from graceful degradation plugin
				degraded_element_id : "degradedUI2",	// Setting from graceful degradation plugin

				custom_settings : {
					progressTarget : "fsUploadProgress2",
					cancelButtonId : "btnCancel2"
				},

				// Debug Settings
				debug: false
			});*/
	     }
