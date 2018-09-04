$(function(){

    /*登录注册start*/


    //手机验证11位数字
    function checkTel(str){
        var  re = /^\d{11}$/;
        return re.test(str);
    }
    //输入电话后下一步按钮状态改变
    $('.tel').keyup(function(){
    	if(checkTel($(this).val())){
            $('#btn1').addClass('btn_l');
		}else{
            $('#btn1').removeClass('btn_l');
		}
	});


	
	//点击确认ajax请求判断是否用户已存在
	$('#btn1').click(function(){
		if(!$(this).hasClass('btn_l')){
			return;
		}else{
		    //ajax请求页面跳转加载中图片显示
        }
	});
	
	//注册密码格式验证
	function checkPwd(str){
        //var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
        var reg=/[A-Za-z0-9]{6,20}/;
		return reg.test(str);
	}
    //登录密码格式验证
    function checkPwdlg(str){
        //var reg=/(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,20}/;
        var reg=/[A-Za-z0-9]{6,20}/;
        return reg.test(str);
    }
	//输入登录密码时登录按钮状态改变
	$('#log_pwd').keyup(function(){
	    var val=$(this).val();
	    if(checkPwdlg(val)){
            $('#btn_log').addClass('btn_l');
        }else{
	        $('#btn_log').removeClass('btn_l');
        }
    });

	//注册页跟重置密码页————短信验证
    $('.code_box .code').focus(function(){
        $('.plceh').hide();
    });
    $('.code_box').click(function(){
        var isNull=true;
        $('.code_box .code').each(function(i,input){
            if($(input).val()){
                isNull=false;
            }
        });
        if(isNull){
            $('.code_box .code').eq(0).focus();
        }
    });
    $(document).click(function(e){
        //(!$(e.target).closest('.code_box').hasClass('code'))&&(!$(e.target).hasClass('plceh'))
        if($(e.target).closest('.code_box').length==0){
            var isNull=true;
            $('.code_box .code').each(function(i,input){
                if($(input).val()){
                    isNull=false;
                }
            });
            if(isNull){
                $('.plceh').show();
            }
        }
    });




    $('.plceh').click(function(){
        $('.code_box .code:first-child').focus();
    });



    //输入注册密码时注册按钮状态改变
    $('#reg_pwd').keyup(function(){
        var val=$(this).val();
        if(checkPwd(val)&&$('#check').hasClass('checked')){
            $('#btn_reg').addClass('btn_l');
        }else{
            $('#btn_reg').removeClass('btn_l');
        }
    });
    //注册协议是否选中
    $('#check').click(function(){
        $(this).toggleClass('checked');
        var val=$('#reg_pwd').val();
        if(checkPwd(val)&&$(this).hasClass('checked')){
            $('#btn_reg').addClass('btn_l');
        }else{
            $('#btn_reg').removeClass('btn_l');
        }
    });



    //重置密码中再次输入密码时确认按钮状态
    // $('#f_pwd1').keyup(function(){
    //     var val=$('#f_pwd').val();
    //     if(checkPwd(val)&&$(this).val()==val){
    //         $('#fpwd_btn').addClass('btn_l');
    //     }else{
    //         $('#fpwd_btn').removeClass('btn_l');
    //     }
    // });



    //忘记密码
    $('#f_pwd1').keyup(function(){
        var val=$('#f_pwd').val();
        if(val){
            $('#fpwd_btn').addClass('btn_l');
        }else{
            $('#fpwd_btn').removeClass('btn_l');
        }


    })



    /*登录注册end*/



    /*我的银行卡start*/
    // $('.jb').click(function(){
    //     //如果无余额跳转解绑页面否则弹框提示
    //     $('.my_bankCard .modal-box').show();
    // });
    // $('.my_bankCard .cancel').click(function(){
    //     $('.my_bankCard .modal-box').hide();
    // });
    /*我的银行卡end*/


    /*更换手机号start*/
    $('#chTel_code').keyup(function(){
        var val=$(this).val();
        if(val.length==6){
            $('#btn_chTel').addClass('btn_l');
        }else{
            $('#btn_chTel').removeClass('btn_l');
        }
    });

    $('#chTel1_code').keyup(function(){
        var tel=$('#chTel1_tel').val();
        var val=$(this).val();
        if(val.length==6&&checkTel(tel)){
            $('#btn_chTel1').addClass('btn_l');
        }else{
            $('#btn_chTel1').removeClass('btn_l');
        }
    });

    $('#chTel1_tel').keyup(function(){
        var val=$('#chTel1_code').val();
        var tel=$(this).val();
        if(val.length==6&&checkTel(tel)){
            $('#btn_chTel1').addClass('btn_l');
        }else{
            $('#btn_chTel1').removeClass('btn_l');
        }
    });
    /*更换手机号end*/


    /*邀请注册start*/
    $('.reg4_con .xy span').click(function(){
        $(this).toggleClass('select');
    });
    /*邀请注册end*/
});
