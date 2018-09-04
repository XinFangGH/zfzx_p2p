		<div class='main_content'>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<script type="text/javascript" src="../static/js/jquery.imgareaselect/scripts/jquery.min.js"></script>
			<script type="text/javascript" src="../static/js/jquery.imgareaselect/scripts/jquery.imgareaselect.pack.js"></script>
			<link rel='stylesheet' type='text/css' href='../static/js/jquery.imgareaselect/css/imgareaselect-default.css'>
			<link rel='stylesheet' type='text/css' href='../static/css/style.css'>
			<div id="image_area">
				<img id="biuuu" src="${base}/theme/${systemConfig.theme}/images/uc/urer-name-pic.png" title="求真相">
			</div>
			<div id ="upload_area">
				<form name = "form1" action ='http://127.0.0.1:8080/hurong_p2p/user/uploadAvatarBpCustMember.do' enctype = 'multipart/form-data'  method = 'POST'>
					<input type="hidden" value="${bpCustMember.id}" name="mark" />
					<input type="file" id="picpath" name="atvatar_image">
					<a href="javascript:void(0);" class="button"> 上传照片</a>
					<input type='hidden' name="path" readonly>
					<div id="submit_button">
						<a href="javascript:void(0);" class='button'>确认</a>
					</div>
			</div>
		</div>
		<script type="text/javascript" src="../static/js/index.js"></script>