/**
 * 手机登录初始化
 * by cjj
 */

Ext.Loader.setConfig({
    enabled: true,
    paths: {
        'mobile': 'hrmobile'
    }
});

Ext.application({

    name: 'mobileIndex',

    launch: function() {

        Ext.Msg.defaultAllowedConfig.showAnimation = false;
        Ext.Msg.defaultAllowedConfig.hideAnimation = false;
        clientWidth = document.body.clientWidth;
        mobileView = Ext.Viewport;
        var username = localStorage.getItem("userName")!=null?localStorage.getItem("userName"):'';
        var isGesturePassword = localStorage.getItem(username+"isGesturePassword")!=null?localStorage.getItem(username+"isGesturePassword"):'';
        var errorPasswordcount=localStorage.getItem(username+"errorPassword");
        localStorage.setItem(username+"updateGesturePassword","0");//0初始化，1，修噶密码的第一步，输入原始密码2，新密码
        localStorage.setItem(username+"isupdatepassword","no");//初始化，不是修改密码
        var isOpenWeixinOpenLong= localStorage.getItem("isOpenWeixinOpenLong");


        var isReg = localStorage.getItem("isReg")!=null?localStorage.getItem("isReg"):'0';
        //如果不是app的话也即使浏览器访问，或微信访问，就要先验证session
        if(isApp==false){
            Ext.Ajax.request({
                url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
                params: {
                },
                method: 'POST',
                success : function(response) {
                    var obj = Ext.util.JSON.decode(response.responseText);
                    if(obj.success==true){
                        if(obj.backpath){
                            mobileNavi = Ext.create('mobile.View',{fullscreen: true});
                            mobileNavi.getNavigationBar().hide();
                            mobileView.add(mobileNavi);
                            //var plainpassword = localStorage.getItem("plainpassword")!=null?localStorage.getItem("plainpassword"):'';
                            curUserInfo=obj.data;
                            mobileNavi.push(
                                Ext.create(obj.backpath,{

                                })
                            );
                        }else{
                            curUserInfo=obj.data;
                            localStorage.setItem(username+"curUserInfo",curUserInfo);
                            mobileNavi = Ext.create('mobile.View',{fullscreen: true,isLoginBtn:true,isRegisterBtn:true,isLogoutBtn:false});
                            mobileNavi.getNavigationBar().hide();
                            mobileView.add(mobileNavi);
                        }
                    }else{
                        if(obj.data.backpath){
                            mobileNavi = Ext.create('mobile.View',{fullscreen: true});
                            mobileNavi.getNavigationBar().hide();
                            mobileView.add(mobileNavi);
                            //var plainpassword = localStorage.getItem("plainpassword")!=null?localStorage.getItem("plainpassword"):'';
                            //curUserInfo=obj.data;
                            mobileNavi.push(
                                Ext.create(obj.data.backpath,{
                                    recommand:obj.data.recommand
                                })
                            );
                        }else{
                            if(isOpenWeixinOpenLong=="yes" && localStorage.getItem("weixinuserName")!= "null"){
                                var weixinuserName = localStorage.getItem("weixinuserName")!=null?localStorage.getItem("weixinuserName"):'';
                                var weixinpassword = localStorage.getItem(username+"weixinpassword")!=null?localStorage.getItem(username+"weixinpassword"):'';

                                Ext.Ajax.request({
                                    url: __ctxPath+'/tologin.do',
                                    params: {
                                        loginname:weixinuserName ,
                                        password: weixinpassword,
                                        isWeixin:"1"
                                    },
                                    method: 'POST',
                                    success : function(response) {
                                        var obj = Ext.util.JSON.decode(response.responseText);
                                        if(Ext.isEmpty(obj.errMsg)){
                                            curUserInfo = obj.data;
                                            localStorage.setItem(username+"curUserInfo",curUserInfo);
                                            mobileNavi = Ext.create('mobile.View',{fullscreen: true,isLoginBtn:true,isRegisterBtn:true,isLogoutBtn:false});
                                            mobileNavi.getNavigationBar().hide();
                                            mobileView.add(mobileNavi);

                                        }else{
                                            Ext.Msg.alert('', obj.errMsg);
                                        }
                                    },
                                    failure: function(form,action,response){
                                        var obj = Ext.util.JSON.decode(response);
                                        Ext.Msg.alert('', obj.msg);
                                    }
                                });


                            } else if(isReg=="1"){
                                mobileNavi = Ext.create('mobile.View',{fullscreen: true});
                                mobileNavi.getNavigationBar().hide();
                                mobileView.add(mobileNavi);
                                var plainpassword = localStorage.getItem("plainpassword")!=null?localStorage.getItem("plainpassword"):'';

                                mobileNavi.push(
                                    Ext.create('hrmobile.public.myhome.register',{
                                        plainpassword:plainpassword
                                    })
                                );
                                localStorage.removeItem("isReg");
                                localStorage.removeItem("plainpassword");
                            }else{


                                mobileNavi = Ext.create('mobile.View',{fullscreen: true});
                                mobileNavi.getNavigationBar().hide();
                                mobileView.add(mobileNavi);

                            }
                        }
                    }
                },
                failure: function(form,action,response){
                    var obj = Ext.util.JSON.decode(response);
                    Ext.Msg.alert('', obj.msg);
                }
            });

        }else  if(isApp==true){  //如果是app

            /*if(username!=''&&isGesturePassword=='true' &&parseInt(errorPasswordcount) !=5){
                    gesturePasswordLogin = Ext.create('mobile.usermanage.GesturePasswordLogin', {fullscreen: true});
          }else{*/
            if(username!=''){
                var password = localStorage.getItem(username+"password")!=null?localStorage.getItem(username+"password"):'';
                Ext.Ajax.request({
                    url: __ctxPath+'/tologin.do?loginname='+username+'&password='+$.md5(password)+'&isMobile=1',

                    success : function(response) {
                        var obj = Ext.util.JSON.decode(response.responseText);
                        if(obj.success){
                            curUserInfo=obj.data;
                            localStorage.setItem(username+"curUserInfo",curUserInfo);
                            mobileNavi = Ext.create('mobile.View',{fullscreen: true,isLogoutBtn:false,isRegisterBtn:true,isLoginBtn:true});
                            mobileNavi.getNavigationBar().hide();
                            mobileView.add(mobileNavi);

                        }else{}
                    }
                });
            }else{
                mobileNavi = Ext.create('mobile.View',{fullscreen: true});
                mobileNavi.getNavigationBar().hide();
                mobileView.add(mobileNavi);
            }

            /*}*/


        }



    }

});


curUserInfo=null;

var quitflag = 0;

document.addEventListener( "deviceready",onDeviceReady, false);
function onDeviceReady() {
    navigator.splashscreen.hide();
    document.addEventListener( "backbutton", eventBackButton, false); //注册返回键
}
function eventBackButton(){
    if(window.plugins.toast){
        window.plugins.toast.shortshow( "再按一次返回键退出程序" );
    }

    quitflag++;
    setTimeout(function() {
        quitflag = 0;
    }, 3000);

    if (quitflag >= 2) {
        navigator.app.exitApp();
    }
}
    


