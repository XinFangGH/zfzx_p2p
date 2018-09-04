var biddata,
	listMaterials,
	enterPrise,
	proDes,
	pltype,
	buystyle,
	disabled,
	formtoken,
	buttontext,
	showRate,
	bidId,
	flag=true;
var	time,t;
Ext.define('hrmobile.public.myhome.bidDetail', {
	extend: 'Ext.Panel',
    name: 'bidDetail',
    constructor: function (config) {
    	// 获取data数据
    	biddata = config.data;
    	formtoken = config.formtoken;
    	// 获取bidId
    	bidId = config.bidId;
    	
    	if(biddata.state==2||biddata.state==5||biddata.state==6){
			buttontext = "已满标";
		}else if(biddata.state==3){
			buttontext = "已流标";
		}else if(biddata.state==4){
			buttontext = "已过期";
		}else if(biddata.state==10){
			buttontext = "已还清";
		}else if(biddata.state==7){
			buttontext = "还款中";
		}else{
			buttontext = "立即出借";
		}
    	if(biddata.state==1){
    		disabled=false;
    		buystyle="width:75%;margin:20px auto;height:36px;background:#d75d2d;font-color:white;font-size:18px;";
    	}else{
    		disabled=true;
    		buystyle="width:75%;margin:20px auto;height:36px;background:"+nobtncolor+";font-color:white;font-size:18px;";
    	}
    	if(biddata.showRate!=null&&biddata.showRate!=""){
    		/*addRate = '<span style="font-size:20px;">+'+ biddata.addRate+'%<span style="background:#EA0000;color:#FFF;">息</span></span>';*/
            showRate = '+<span>'+ biddata.showRate+'</span>%';
    		interestRate= (biddata.interestRate-biddata.showRate).toFixed(1);
    	}else{
            showRate = "";
            interestRate= biddata.interestRate;
    	}
    	
    	enterPrise=config.enterPrise;
    	listMaterials=config.listMaterials;
    	proDes=config.proDes;
    	pltype=config.pltype;
	    var html1='<div class="container my_container" style="margin-top:42px;">'+
        '<div class="topCon"  style="background-color:#D75D2D;opacity: 0.77;margin-top: 10px;margin-bottom: 10px">'+
            '<div class="year">'+
				'<div class="dl">' +
					'<div class="pt"><span>'+interestRate+'</span> %'+showRate+'</div>'+
					/*'<div class="pt"><span>'+biddata.interestRate+'</span> %</div>'+*/
            		'<p class="pb">年化利率</p>'+
				'</div>'+
				'<div class="dr">' +
					'<p class="pt">期限<span>'+biddata.loanLife+'</span></p>'+
					'<div class="pb">总额<span>'+biddata.bidMoney/10000+'万元</span></div>'+
				'</div>'+
                '<p class="pos_line"></p>'+
            '</div>'+
            '<div class="progress">'+
                '<div>'+
//                    '<b><img src="images/tip.png" alt=""></b>'+
                '</div>'+
                '<p><span style="width:'+biddata.progress+'%"></span><i class="pos_per" style="left:'+biddata.progress+'%">'+biddata.progress+'%</i></p>'+
                '<ul class="lf" >'+
					'<input id="timestart" type="hidden" value="'+biddata.remainingTime+'">'+
					'<input id="timestartopen" type="hidden" value="'+ new Date().getTime()+'">'+
                    '<li>剩余募集时间：<span id="time">00<i>天</i>00<i>小时</i>00<i>秒</i></span></li>'+
                '</ul>'+
                '<ul class="rg">'+
                    '<li>剩余<span>'+biddata.afterMoney/10000+'</span>万元</li>'+
                '</ul>'+
            '</div>'+
        '</div>'+
        '<div class="bottCon clear" style="background-color:#FFF;">'+
            '<ul>'+
            	'<li><a >产品类型</a><span>'+biddata.proKeepType +'</span></li>'+
				'<li><a >起息时间</a><span id="qx_time">'+(null==biddata.startIntentDate?"满标日期":biddata.startIntentDate)+'</span></li>'+
               /* '<li><a >项目编号</a><span>'+biddata.bidProNumber+'</span></li>'+*/
                '<li><a >截止日期</a><span>'+biddata.bidEndTime+'</span></li>'+
                '<li><a >还款方式</a><span>'+biddata.theWayBack+'</span></li>'+
                '<li><a >信用等级</a><span>'+biddata.keepCreditlevelName +'</span></li>'+
                '<li onclick="javascript:bidplan(1);" class="hasArrow"><a>标的详情</a></li>'+
               /* '<li onclick="javascript:bidplan(2);" class="hasArrow"><a>相关材料</a></li>'+*/
                '<li onclick="javascript:bidplan(3);" class="hasArrow"><a>投标记录</a></li>'+
               /* '<li onclick="javascript:bidplan(4);" class="hasArrow"><a>风险控制</a></li>'+*/
            '</ul>'+
        '</div>'+
    '</div>';
		config = config || {};
	    Ext.apply(config,{
        	title:"<font color=#ffffff style='font-size:17px;'>"+biddata.bidProName+"</font>",
            width:"100%",
			height:"100%",
		    listPaging: true,
			scrollable:{
			direction: 'vertical'
			},
					    
		   items: [{
			   baseCls:"baseClass",
			   items:[
			   {
				   html:html1
            	},{
            		style:buystyle,
            		xtype: 'button',
            		text:"<font color=white>"+buttontext+"</font>",
            		disabled:disabled,
            		handler:this.buy
            	}]
		   }],
            listeners:{
        		painted:function(){

        			//返回按钮显示
                    mobileNavi.getNavigationBar().getBackButton().hide();
                    mobileNavi.getNavigationBar().getBackButton().show();
                    //剩余募集时间倒计时
                    var opentime=parseInt($('#timestartopen').val());
                    var offsettime=new Date()-opentime;
                    time=parseInt($('#timestart').val())*1000;
                    if(offsettime>=1000){
                   	   time-=offsettime;
				    }
					clearInterval(t);
					t=setInterval(function () {
						if(time>0){
							var day = parseInt(time / 1000 / 60 / 60 / 24);
							var hour = parseInt(time / 1000 / 60 / 60 % 24);
							var minute = parseInt(time / 1000 / 60 % 60);
							var seconds = parseInt(time / 1000 % 60);
							$('#time').html(day + "<i>天</i>" + hour + "<i>时</i>" + minute + "<i>分</i>" + seconds + "<i>秒</i>");
							time-=1000;
						}else{
							$('#time').html("募集期结束");
							$('#time').css('font-weight','normal')
							clearTimeout(t);
						}
					}, 1000);
                    $('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
        		}
				// }
			}
        });
        this.callParent([config]);
        
        
        // 列表相关
        bidplan = function (data) {
        	// 获取变量里不同的参数进行判断
        	switch (data) {
        		// 项目信息
        		case 1:
        			var obj = {
        				data:biddata,
        				enterPrise:enterPrise,
        				proDes:proDes,
        				proType:pltype
	        		};
	    			mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.biddetail',{
	    				data: obj
	    				}
	    			));
        			break;
        		// 相关资料
        		case 2:
        			mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.bidrelated',{
	        					listMaterials:listMaterials
	        				}
        				)
        			);
        			break;
        		// 投标记录
        		case 3:
                    if (Ext.isEmpty(curUserInfo)) {
                        mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
                    } else {
                        mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.biddinglist', {
                                data: biddata
                            }
                            )
                        );
                    }
                    break;
        		// 风险控制
        		case 4:
        			// 判断是否登录
        			if (Ext.isEmpty(curUserInfo)) {
        				mobileNavi.push(Ext.create('hrmobile.public.myhome.login', {}));
        			} else {
        				mobileNavi.push(Ext.create('hrmobile.public.myhome.scatteredMark.RiskManagement',{
        						data:biddata,
        						bidId: bidId
        					}
        				)
        			);
        			}
        			break;
        		default :
        			
        			break;
        	}
        	
        };
	},
	buy:function(){
			if(flag){
				flag=false;
				setTimeout(function(){
					flag=true;
				},2000);
                if(Ext.isEmpty(curUserInfo)){
                    mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
                }else{
                    Ext.Ajax.request({
                        url: __ctxPath+'/user/isSessinonValidBpCustMember.do',
                        method: 'POST',
                        success : function(response) {
                            var obj = Ext.util.JSON.decode(response.responseText);
                            if(obj.success==true){
                                curUserInfo=obj.data;
                                isCheckEmail=(null!=curUserInfo&&curUserInfo.isCheckEmail==1)?true:false;
                                isCheckPhone=(null!=curUserInfo&&curUserInfo.isCheckPhone==1)?true:false;
                                isCheckCard=(null!=curUserInfo&&curUserInfo.isCheckCard==1)?true:false;
                                isthirdPayFlagId=(null!=curUserInfo&&Ext.isEmpty(curUserInfo.thirdPayFlagId))?false:true;
                                if(isthirdPayFlagId){
                                    mobileNavi.push(Ext.create('hrmobile.public.myhome.planbid.buyplanbid',{data:biddata,formtoken:formtoken}));
                                }else{
                                    Ext.Msg.alert('','您还未进行第三方认证!',function () {
                                        mobileNavi.push(Ext.create('hrmobile.public.myhome.binding',{}));
                                    });

                                }
                            }else{
                                mobileNavi.push(Ext.create('hrmobile.public.myhome.login',{}));
                            }
                        }
                    });

                }
			}
	}
 
});

