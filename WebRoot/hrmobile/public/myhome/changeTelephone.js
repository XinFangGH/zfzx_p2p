var flag=false,tel;
Ext.define('hrmobile.public.myhome.changeTelephone', {
    extend: 'Ext.Container',
    name: 'changeTelephone',
    constructor: function (config) {
    	config = config || {};
        tel=config.tel;
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>更换手机号</font>",
    		width:"100%",
		    height:"100%",
            style:"margin-top:42px;",
		    fullscreen: true,
		    scrollable:{
		    	//direction: 'vertical'
		    	direction: 'false'
		    },
		    
		    items: [
                {
                    html:'<form class="changePhone">' +
                            '<ul>' +
                                '<li>' +
                                    '<label for="">手机号</label>'+
                                    '<input type="text" readonly id="tel" value="'+tel+'">'+
                                '</li>'+
                                '<li>' +
                                    '<label for="">验证码</label>'+
                                    '<input type="text" id="verify_sms" placeholder="请输入短信验证码">'+
                                    '<a href="javascript:;" id="code">获取验证码</a>'+
                                '</li>'+
                            '</ul>'+
                            '<a href="javascript:;" id="btn" class="btn">下一步</a>'+
                        '</form>'
                }

                ],
		    	listeners :{
					painted:function(){
                        //$('#ext-button-3').addClass('wyjk_select').removeClass('wyjk');
                       // $('#ext-button-1').removeClass('home_select').addClass('home');


                        // 获取验证码
						$('#code').click(function(){
                            var  telphone = $('#tel').val();
                            var seconds = 120;
                            var temp = 120;;

                            var getVerifySmsObj = this;
                            var text1 = $(getVerifySmsObj).html();
                            if(flag== false){
                                flag=true;
                                var url = __ctxPath + "/codecreate.do?isMobile=1&mobile=1&sms_code_type=bind_telphone"
                                    + "&telphone=" + telphone + '&randomCode='
                                    + (Math.random())+"&token=updatePhone";
                                Ext.Ajax.request({
                                    url : url,
                                    success : function(response) {
                                        var responseText = Ext.util.JSON.decode(response.responseText);
                                        if (responseText.status==200) {
                                            var text=$(getVerifySmsObj).html();
                                            var interval = window.setInterval(function() {
                                                seconds--;

                                                if (seconds == 0) {
                                                    seconds = temp;
                                                    window.clearInterval(interval);

                                                    $(getVerifySmsObj).html(text.indexOf("重新") == -1
                                                        ? "重新" + text
                                                        : text);
                                                    flag=false;
                                                } else {
                                                    $(getVerifySmsObj).html(text1 + "(" + seconds + ")");
                                                    //getVerifySmsObj.setText(seconds);
                                                }
                                            }, 1000);
                                        } else {
                                            //  提示框
                                            Ext.Msg.alert('提示',responseText.remark);
                                            flag=false;
                                            $(getVerifySmsObj).html(text1);
                                        }

                                    }
                                });

                            }
						});


						//下一步提交
						$('#btn').click(function(){
                            var verify_sms =$('#verify_sms').val();
                            Ext.Ajax.request({
                                url :__ctxPath + "/user/verifPhoneMoBpCustMember.do?isMobile=1&verify_sms="+verify_sms,
                                success : function(response) {
                                    var responseText = Ext.util.JSON.decode(response.responseText);
                                    if(responseText.success){
                                        mobileNavi.push(Ext.create('hrmobile.public.myhome.changeTelephone1', {}));
                                    }else{
                                        Ext.Msg.alert('提示',responseText.remark);
                                    }
                                }
                            });
						});
					}
		    
		    	}
	    })
    	this.callParent([config]);
    }
});
