var flag1=false;
Ext.define('hrmobile.public.myhome.changeTelephone1', {
    extend: 'Ext.Container',
    name: 'changeTelephone1',
    constructor: function (config) {
    	config = config || {};
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
                                    '<label for="">新手机号</label>'+
                                    '<input type="text" id="tel1" placeholder="请输入您的新手机号">'+
                                '</li>'+
                                '<li>' +
                                    '<label for="">验证码</label>'+
                                    '<input type="text" id="verify_sms1" placeholder="请输入短信验证码">'+
                                    '<a href="javascript:;" id="code1">获取验证码</a>'+
                                '</li>'+
                            '</ul>'+
                            '<a href="javascript:;" class="btn" id="btn1">下一步</a>'+
                        '</form>'
                }

                ],
		    	listeners :{
					painted:function(){
                        //$('#ext-button-3').addClass('wyjk_select').removeClass('wyjk');
                       // $('#ext-button-1').removeClass('home_select').addClass('home');

                        // 获取验证码
                        $('#code1').click(function(){
                            var  telphone = $('#tel1').val();
                            if(Ext.isEmpty(telphone)){
                                //  提示框
                                Ext.Msg.alert('提示',"手机号不能为空");
                                return;
                            }
                            if(!isMobile(telphone)){
                                //  提示框
                                Ext.Msg.alert('提示',"手机号格式不正确");
                                return;
                            }
                            var existPhone = false;
                            Ext.Ajax.request({
                                url : __ctxPath + "/user/isTelphoneBpCustMember.do?telphone=" + telphone,
                                async : false,
                                success : function(response) {
                                    var responseText = Ext.util.JSON.decode(response.responseText);
                                    if (responseText.success) {
                                        Ext.Msg.alert('提示',responseText.errMsg);
                                        existPhone = true;
                                        return;
                                    }
                                }
                            });
                            if(existPhone) {
                                return;
                            }




                            var seconds = 120;
                            var temp = 120;;

                            var getVerifySmsObj = this;
                            var text1 = $(getVerifySmsObj).html();
                            if(flag1== false && !existPhone){
                                flag1=true;
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
                                                    flag1=false;
                                                } else {
                                                    $(getVerifySmsObj).html(text1 + "(" + seconds + ")");
                                                    //getVerifySmsObj.setText(seconds);
                                                }
                                            }, 1000);
                                        } else {
                                            //  提示框
                                            Ext.Msg.alert('提示',responseText.remark);
                                            flag1=false;
                                            $(getVerifySmsObj).html(text1);
                                        }

                                    }
                                });

                            }
                        });

                        //下一步提交
                        $('#btn1').click(function(){
                            var  telphone = $('#tel1').val();
                            if(!telphone){
                                Ext.Msg.alert('提示',"手机号不能为空");
                                return false;
                            }
                            var verify_sms =$('#verify_sms1').val();
                            if(curUserInfo.isCheckCard==1){
                                window.open(__ctxPath + "/user/updatePhoneMOBpCustMember.do?isMobile=1&verify_sms="+verify_sms + "&telphone=" + telphone+'&backpath=hrmobile.public.myhome.main');
                            }else{
                                Ext.Ajax.request({
                                    url :__ctxPath + "/user/updatePhoneMOBpCustMember.do?isMobile=1&verify_sms="+verify_sms + "&telphone=" + telphone,
                                    success : function(response) {
                                        var responseText = Ext.util.JSON.decode(response.responseText);
                                        if(responseText.success){
                                            Ext.Msg.alert('提示',responseText.remark);
                                            mobileNavi.reset();
                                            mobileNavi.push(Ext.create('hrmobile.public.myhome.main', {}));
                                        }else{
                                            Ext.Msg.alert('提示',responseText.remark);
                                        }

                                    }
                                });
                            }
                        });
					}
		    
		    	}
	    })

    	this.callParent([config]);
    }
});
