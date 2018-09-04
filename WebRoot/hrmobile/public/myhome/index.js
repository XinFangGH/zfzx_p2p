var progress;
var carousel_height;
var bidproimpro,bidproimpro0,bidproimpro1,bidproimpro2,bidproimpro3;
var flag=true;
Ext.define('hrmobile.public.myhome.index', {
    extend: 'Ext.Container',
    name:'index',
    constructor:function(config){
		var bidDetailtop= Ext.create('Ext.Container',{
			/*docked:'top',*/
			items:[{
                xtype: 'carousel',
    		    height:carousel_height,
    		    items: [
                    {
                        html:" <div class='banner' onclick='javascript:hd_detail();'>" +
                        "<img style='width:100%; height:100%;' src='" +
                        __ctxPath+"/hrmobile/resources/imagesP2p/index/banner_xh.jpg'  id='top_dd' />" +
                        "</div>"
                    }
    		    ],
                /*html:'<img style="width:23px; height:20px;position:absolute;right:17px;top:12px;" src="' +
                                        __ctxPath+'/hrmobile/resources/mypic/index_news.png" />',*/
    		    listeners: {
    		    	activate : function(newActiveItem, this1, oldActiveItem, eOpts ) {
	    		    	var window_Width=window.screen.availWidth;
						var me = this;
						carousel_height=window_Width*0.53+"px"
						me.setHeight(carousel_height);  
	    		    	var cards = me.items.items; // 获得carousel中的items的个数
	    		    	var i = me.getActiveIndex();
	    		    	interval=setInterval( function() {
	    		    		me.setActiveItem(cards[(i++)% me.items.getCount()+1]);
	    		    		i = me.getActiveIndex()+1;
	    		    		if(i==me.items.getCount()-1){
	    		    			i=0;
	    		    		}
	    		    	}, 2500);
    		    	},
    		    	activeitemchange : function( this1, value, oldValue, eOpts){
    		    		clearInterval(interval);
    		    		var me = this;
	    		    	var cards = me.items.items;//获得carousel中的items的个数
	    		    	var i = me.getActiveIndex();
	    		    	interval=setInterval( function() {
	    		    		me.setActiveItem(cards[(i++)% me.items.getCount()+1]);
	    		    		i = me.getActiveIndex()+1;
	    		    		if(i==me.items.getCount()-1){
	    		    			i=0;
	    		    		}
	    		    	}, 1500);
    		    	}
    			}
		}]
        });
		Ext.apply(config,{
		    isload:true,
			style:"margin-bottom:55px",
		    scrollable:{
		    	direction: 'vertical'
		    },
    		items: [bidDetailtop,{
                xtype: 'panel',
    		    width:'100%',
    		    style:"background-color:#fff",
    		        	html:'<div class="ammount my_ammount dflex">'+
					       /*'<h2 class="ammount-ico"></h2>'+*/
							'<img class="ammount-ico" src="'+__ctxPath+'/hrmobile/resources/imagesP2p/icon_gonggao.png">'+
					       /*'<div class="ammount-txt flex" style="font-size:14px" id="title">'+
					        	'：关于中国农业银行关闭支付业务的通知！'+
					       '</div>'+*/
					       /*"<marquee color:"+themeColor+"; direction='up' align='left' height='26px' width='100%' scrollamount='2' scrolldelay='100'>" +
					       "<strong  style='color:#808080;padding-left:5px;font-size:14px;' id='title'></strong>" +
					       "</marquee>"+*/
					      '<div class="my_content"  id="my_content_liu">'+
							'<ul>' +
								'<li id="my_content_li"><!--募集期利息为投资日到满标当日所记利息--></li>' +
								'<li id="my_content_lis"><!--为平台单独发放的福利--></li>' +
							'</ul>' +
                          '</div>'+


					    '</div>'+
					    '<div class="data-list mydata-list" style="font-size:14px">'+
				            	'<div class="fl dataitem">'+
					            	'<div class="dataiteminner">'+
					            		/*'<dt><img src="'+__ctxPath+'/hrmobile/resources/images/data-icon2.png" width="28" height="28"></dt>'+*/
					            		/*'<dd>'+*/
					            			'<p class="p1">累计注册用户</p>'+
					            			'<p class="p2"><em id="userTotalIncome">15,000</em></p>'+
					            		/*'</dd>'+*/
					                '</div>'+
				            	'</div>'+
				            	'<div class="fl dataitem dataitemr">'+
					            	'<dl class="dataiteminner righthuiline">'+
					            		/*'<dt><img src="'+__ctxPath+'/hrmobile/resources/images/data-icon1.png" width="28" height="28"></dt>'+*/
					            		'<div>'+
					            			'<p class="p1">用户累计投资</p>'+
					            			'<p class="p2"><em id="userTotalInvest">15,000 </em></p>'+
					            		'</div>'+
					                '</dl>'+
				            	'</div>'+
				    		'</div>'
		},
		            {
		    			xtype:'panel',
		            	html:'<div class="product-list my-product-list">'+
					    		"<span id='progress_animation' style='display:none'>{interestRate}</span>"+
					    	 /*	'<h2 class="txtc fontN" style="font-size:17px" id="bidnameindex"></h2>'+*/
								/*'<div class="my_progress">'+
									'<img class="wq" src="'+__ctxPath+'/hrmobile/resources/images/wq.png" alt="">'+
									'<img class="nq" src="'+__ctxPath+'/hrmobile/resources/images/nq.png" alt="">'+
									'<div class="circle_box">'+
										'<div class="circle"></div>'+
										'<div class="circle_modle"></div>'+
									'</div>'+
									'<div class="text">'+
										'<p class="pt"  id="bidnameindex">融资租赁(SSP-01)</p>'+
										'<p class="pb" id="progress"></p>'+
									'</div>'+

								'</div>'+*/
					    		/*'<div class="progress txtc">'+
					    		"<div class='m-index'>"+ 
									            "<canvas id='circle-progress-custom_1' width='180' height='180'></canvas><font class='indexfont' id='progress'></font>"+
									            "<div class='left-arrow'></div>"+
									        "</div>"+
					    		'</div>'+*/
					    		'<div class="dt"><span class="sl">新手</span><span class="sc">专享</span><span class="sr"><i>打开你的财富大门</i></span></div>'+
								'<div class="dc">预期年化收益率 <p id="progress"></p></div>'+
					    		'<p class="txt-con txtc" style="font-size:13px;" id="bidmoneymix"></p>'+
					    		'<div>'+
					    			'<a onclick="javascript:expbidplan(0);" class="btn_login font18">立即出借</a>'+
					    		'</div>'+
					    	'</div>'+
							'<div class="index_list"><div class="dt"><span class="sl">理财</span><span class="sc">精选</span><span class="sr"><i>高收益&nbsp;&nbsp;放心投</i></span><!--<span class="fr more">更多</span>--></div>'+
							 	'<div class="list_con" onclick="javascript:expbidplan(1);">' +
									'<div class="pt"><span class="sl" id="proKeepType1"></span><h2 class="sc" id="bidnameindex1"></h2><p class="sr showRate1"><span加息</span></p></div>' +
									'<div class="pc"><div class="sl"><p><span id="progress1" class="f28"></span><span id="showRate1" class="f19"></span>%</p>预期年化收益率</div><div class="sr"><p><span id="loanLife1"></span><b></b><span id="theWayBack1"></span></p>剩余可投<span id="afterMoney1"></span></div><div class="line"></div></div>'+
								'</div>'+
								'<div class="list_con" onclick="javascript:expbidplan(2);">' +
									'<div class="pt"><span class="sl" id="proKeepType2"></span><h2 class="sc" id="bidnameindex2"></h2><p class="sr showRate2"><span加息</span></p></div>' +
									'<div class="pc"><div class="sl"><p><span id="progress2" class="f28"></span><span id="showRate2" class="f19"></span>%</p>预期年化收益率</div><div class="sr"><p><span id="loanLife2"></span><b></b><span id="theWayBack2"></span></p>剩余可投<span id="afterMoney2"></span></div><div class="line"></div></div>'+
								'</div>'+
								'<div class="list_con" onclick="javascript:expbidplan(3);">' +
									'<div class="pt"><span class="sl" id="proKeepType3"></span><h2 class="sc" id="bidnameindex3"></h2><p class="sr showRate3"><span加息</span></p></div>' +
									'<div class="pc"><div class="sl"><p><span id="progress3" class="f28"></span><span id="showRate3" class="f19"></span>%</p>预期年化收益率</div><div class="sr"><p><span id="loanLife3"></span><b></b><span id="theWayBack3"></span></p>剩余可投<span id="afterMoney3"></span></div><div class="line"></div></div>'+
								'</div>'+
							 '</div>'
						/*	'<div class="media_news"><div class="dl"><p>媒体</p>报道</div>' +
                                '<div class="dr swiper1">' +
                                    '<ul class="swiper-wrapper">' +
                                        '<li class="swiper-slide"><span class="sl">展信快讯</span><b></b><span class="sr">合规备案大考将至，</span><br><span class="sb">你应该你知道的那些事儿！</span></li>' +
                                        '<li class="swiper-slide"><span class="sl">展信快讯</span><b></b><span class="sr">合规备案大考将至，</span><br><span class="sb">你应该你知道的那些事儿！</span></li>' +
                                    '</ul>' +
                                '</div>'+
                            '</div>'+
                            '<div class="info_safe"><img src="'+__ctxPath+'/hrmobile/resources/mypic/index_info.png" class="info" alt=""><img src="'+__ctxPath+'/hrmobile/resources/mypic/index_safe.png" class="safe" alt=""></div>'*/
    				},{
		    		
					}
    		],
    		
    	       listeners : {
		    	painted:function(){
					if(flag){
						flag=false;
                        function scroll(){
                            $("#my_content_liu ul").animate({"margin-top":"-28px"},function(){
                                $("#my_content_liu ul li:eq(0)").appendTo($("#my_content_liu ul"));
                                $("#my_content_liu ul").css({"margin-top":0});
                            })
                        }
                        setInterval(scroll,2000)
					}

					//媒体报道

                    var prwidth = $(window).width()-72;
                    $('.media_news .dr').css("width",prwidth);
                    var swiper1 = new Swiper('.swiper1', {
//					        freeMode: true,
                            slidesPerView: 'auto',
                            freeModeSticky: true
                    });

					//点击更多打开投资列表页面
                    $('.more').click(function(){
                        mobileNavi.reset();
                        mobileNavi.push(
                            Ext.create('hrmobile.public.myhome.investManage',{}));
                        mobileNavi.getNavigationBar().getBackButton().show();
                        $('#ext-button-2').removeClass('wytz').addClass('wytz_select');
                        $('#ext-button-1').addClass('home').removeClass('home_select');
                        mobileNavi.getNavigationBar().hide();
                    });

                    //进入信息披露页面
                    $('.info').click(function(){
                       // mobileNavi.reset();
                        mobileNavi.push(
                            Ext.create('hrmobile.public.myhome.informationDisclosure',{}));
                        hidebottomBarIndex();
                    });



                    // 平台数据披露  Aajx Get读取数据
                    Ext.Ajax.request({
                        url: __ctxPath + '/platShowMobilelogin.do',
                        params: {
                            isMobile: "1"

                        },
                        method: 'GET',
                        async: true,
                        success: function (response) {
                            // 解析json串为对象
                            var responseText = Ext.util.JSON.decode(response.responseText);

                            var b8 = responseText.b8;

                            if (b8 >= 10000) {
                                b8 = Number(b8 / 10000).toFixed(0);
                                b8= b8 + '<span class="f12">万元</span>';
                            } else {
                                b8 = b8 + '<span class="f12">元</span>';
                            }


                            // 累计交易
                            $('#userTotalInvest').html(b8);

                            // 注册用户
                            $('#userTotalIncome').html(responseText.count + '<span class="f12">人</span>');
                        }
                    });


                    Ext.Ajax.request({
                        async : false,
                        url : __ctxPath + '/creditFlow/financingAgency/listPlBidPlan.do',
                        params : {
                            limit:"5",
                            page:"1",
                            isMobile : "1"
                        },
                        success : function(response) {
                            var  responseText1=response.responseText.replace(/[\n]/ig,'');
                            var	 responseText = Ext.util.JSON.decode(responseText1);
                            if(responseText.result[0]!=null){
                                //投标进度
                                bidproimpro0=responseText.result[0];
                                progress=responseText.result[0].progress;

                                //document.getElementById("bidnameindex1").innerHTML =responseText.result[1].bidProName;
                                document.getElementById("progress").innerHTML =responseText.result[0].yearInterestRate+'<span class="f12">%</span>';
                                if(responseText.result[0].theWayBack=="按期计息到期还本"){
                                    document.getElementById("bidmoneymix").innerHTML =responseText.result[0].startMoney+"元起投<span></span>项目期限"+responseText.result[0].loanLife
                                        +"<span></span>先息后本";
                                }else{
                                    document.getElementById("bidmoneymix").innerHTML =responseText.result[0].startMoney+"元起投<span></span>项目期限"+responseText.result[0].loanLife
                                        +"<span></span>"+responseText.result[0].theWayBack;
                                }


                            }
                            if(responseText.result[1]!=null){
                                bidproimpro1=responseText.result[1];
                                document.getElementById("bidnameindex1").innerHTML =responseText.result[1].bidProName;
                            	//标的类型
                                if(responseText.result[1].proKeepType=="信用审核标"){
                                	$('#proKeepType1').html("信用");
                                }else if(responseText.result[1].proKeepType=="实地核查标"){
                                    $('#proKeepType1').html("实地");
                                }else if(responseText.result[1].proKeepType=="机构担保标"){
                                    $('#proKeepType1').html("担保");
                                }else if(responseText.result[1].proKeepType=="福利标"){
                                    $('#proKeepType1').html("福利");
                                }else{
                                    $('#proKeepType1').html("信用");
                                }
								//是否加息及年化收益率
                                if(responseText.result[1].showRate!=null&&responseText.result[1].showRate!=""){
                                    document.getElementById("progress1").innerHTML =(responseText.result[1].yearInterestRate-responseText.result[1].showRate).toFixed(1);
                                    $('#showRate1').html("+"+responseText.result[1].showRate);
                                }else{
                                    document.getElementById("progress1").innerHTML =responseText.result[1].yearInterestRate;
                                    $('.showRate1').hide();
								}
								//项目期限
								$('#loanLife1').html(responseText.result[1].loanLife);
                                if(responseText.result[1].theWayBack=="按期计息到期还本"){
                                    $('#theWayBack1').html("先息后本");
								}else{
                                    $('#theWayBack1').html(responseText.result[1].theWayBack);
								}
                                if (responseText.result[1].afterMoney >= 10000) {
                                    responseText.result[1].afterMoney = Number(responseText.result[1].afterMoney / 10000).toFixed(2);
                                    responseText.result[1].afterMoney= responseText.result[1].afterMoney + '<span class="f12">万元</span>';
                                } else {
                                    responseText.result[1].afterMoney = responseText.result[1].afterMoney + '<span class="f12">元</span>';
                                }
                                $('#afterMoney1').html(responseText.result[1].afterMoney);
							}
                            if(responseText.result[2]!=null){
                                bidproimpro2=responseText.result[2];
                                document.getElementById("bidnameindex2").innerHTML =responseText.result[2].bidProName;
                                //标的类型
                                if(responseText.result[2].proKeepType=="信用审核标"){
                                    $('#proKeepType2').html("信用");
                                }else if(responseText.result[1].proKeepType=="实地核查标"){
                                    $('#proKeepType2').html("实地");
                                }else if(responseText.result[1].proKeepType=="机构担保标"){
                                    $('#proKeepType2').html("担保");
                                }else if(responseText.result[1].proKeepType=="福利标"){
                                    $('#proKeepType2').html("福利");
                                }else{
                                    $('#proKeepType2').html("信用");
                                }
                                //是否加息及年化收益率
                                if(responseText.result[2].showRate!=null&&responseText.result[2].showRate!=""){
                                    document.getElementById("progress2").innerHTML =(responseText.result[2].yearInterestRate-responseText.result[2].showRate).toFixed(1);
                                    $('#showRate2').html("+"+responseText.result[2].showRate);
                                }else{
                                    document.getElementById("progress2").innerHTML =responseText.result[2].yearInterestRate;
                                    $('.showRate2').hide();
                                }
                                //项目期限
                                $('#loanLife2').html(responseText.result[2].loanLife);
                                if(responseText.result[2].theWayBack=="按期计息到期还本"){
                                    $('#theWayBack2').html("先息后本");
                                }else{
                                    $('#theWayBack2').html(responseText.result[2].theWayBack);
                                }
                                if (responseText.result[2].afterMoney >= 10000) {
                                    responseText.result[2].afterMoney = Number(responseText.result[2].afterMoney / 10000).toFixed(2);
                                    responseText.result[2].afterMoney= responseText.result[2].afterMoney + '<span class="f12">万元</span>';
                                } else {
                                    responseText.result[2].afterMoney = responseText.result[2].afterMoney + '<span class="f12">元</span>';
                                }
                                $('#afterMoney2').html(responseText.result[2].afterMoney);
                            }
                            if(responseText.result[3]!=null){
                                bidproimpro3=responseText.result[3];
                                document.getElementById("bidnameindex3").innerHTML =responseText.result[3].bidProName;
                                //标的类型
                                if(responseText.result[3].proKeepType=="信用审核标"){
                                    $('#proKeepType3').html("信用");
                                }else if(responseText.result[3].proKeepType=="实地核查标"){
                                    $('#proKeepType3').html("实地");
                                }else if(responseText.result[3].proKeepType=="机构担保标"){
                                    $('#proKeepType3').html("担保");
                                }else if(responseText.result[3].proKeepType=="福利标"){
                                    $('#proKeepType3').html("福利");
                                }else{
                                    $('#proKeepType3').html("信用");
                                }
                                //是否加息及年化收益率
                                if(responseText.result[3].showRate!=null&&responseText.result[3].showRate!=""){
                                    document.getElementById("progress3").innerHTML =(responseText.result[3].yearInterestRate-responseText.result[3].showRate).toFixed(1);
                                    $('#showRate3').html("+"+responseText.result[3].showRate);
                                }else{
                                    document.getElementById("progress3").innerHTML =responseText.result[3].yearInterestRate;
                                    $('.showRate3').hide();
                                }
                                //项目期限
                                $('#loanLife3').html(responseText.result[3].loanLife);
                                if(responseText.result[3].theWayBack=="按期计息到期还本"){
                                    $('#theWayBack3').html("先息后本");
                                }else{
                                    $('#theWayBack3').html(responseText.result[3].theWayBack);
                                }
                                if (responseText.result[3].afterMoney >= 10000) {
                                    responseText.result[3].afterMoney = Number(responseText.result[3].afterMoney / 10000).toFixed(2);
                                    responseText.result[3].afterMoney= responseText.result[3].afterMoney + '<span class="f12">万元</span>';
                                } else {
                                    responseText.result[3].afterMoney = responseText.result[3].afterMoney + '<span class="f12">元</span>';
                                }
                                $('#afterMoney3').html(responseText.result[3].afterMoney);
                            }
                        }
                    });

				},
				resize :function(){

				circleProgress({
					    id: 'circle-progress-custom_1',
					    progress:progress, // default: 100
			            duration: 1000, // default: 1000
			            color: themeColor, // default: 'rgb(52, 145, 204)'
			            bgColor: '#ededed', // default: 'rgb(230, 230, 230)'
			            textColor: 'transparent', // default: 'black'
			            progressWidth: 0.05, // default: 0.25 (r)
			            fontScale: 0.0, // default: 0.4 (r)
			            toFixed: 0  // default: 0
					        });
					           
			}}
			}
    	);
    		

    	
    	this.callParent([config]);
    	
    	// 跳转散标详情
    	expbidplan =function(i){
    	        switch(i){
                    case 0: bidproimpro=bidproimpro0;break;
                    case 1: bidproimpro=bidproimpro1;break;
                    case 2: bidproimpro=bidproimpro2;break;
                    case 3: bidproimpro=bidproimpro3;break;
                }
         		Ext.Ajax.request({
				url: __ctxPath +"/creditFlow/financingAgency/bidPlanDetailisMobilePlBidPlan.do",
				 params : {
					isMobile : "1",
					bidId:bidproimpro.bidId
		         },
			     success : function(response) {
			    	var responseText1=response.responseText.replace(/[\n]/ig,'');
			    	var responseText = Ext.util.JSON.decode(responseText1);
			    	var data=responseText.data;
			    	var listMaterials=responseText.listMaterials;
			    	var enterPrise = responseText.enterPrise;
			    	var pltype = responseText.pltype;
			    	var proDes = responseText.proDes;
			    	hidebottomBarIndex();
			    	mobileNavi.push(Ext.create('hrmobile.public.myhome.bidDetail',{
			    				result: responseText,
			    				random: responseText.numnum,
			    				formtoken: responseText.formtoken,
					    		data:data,
					    		listMaterials:listMaterials,
					    		enterPrise:enterPrise,
					    		proDes:proDes,
					    		pltype:pltype,
					    		bidId: bidproimpro.bidId,
			    			}
			    		)
			    	);
	            }});
    	}
    	/*Ext.Ajax.request({// 累计交易金额 userTotalInvest 累计创造收益userTotalIncome
    		url: __ctxPath+'/user/getMoneyAccumulativeInvestBpCustMember.do',
    		 params: {
    			 	isMobile:'1'
                 },
		         method: 'POST',
		          success: function(response) {
		        	  var responseText = Ext.util.JSON.decode(response.responseText);
		        	  var sumMoney = responseText.result;
		        	  if(sumMoney<10000000){
		        	    document.getElementById("userTotalInvest").innerHTML= moneyFormat(sumMoney)+'元';
		        	  }else{
		        	  	sumMoney=sumMoney/10000;
		        	    document.getElementById("userTotalInvest").innerHTML= moneyFormat(sumMoney)+'万元';
		        	  }
		          }
    		
    	})
    	Ext.Ajax.request({// 累计交易金额 userTotalInvest 累计创造收益userTotalIncome
    		url: __ctxPath+'/user/getMoneyAccumulativeIncomeBpCustMember.do',
    		 params: {
    			 	isMobile:'1'
                 },
		         method: 'POST',
		          success: function(response) {
		        	  var responseText = Ext.util.JSON.decode(response.responseText);
		        	  var afterMoney = responseText.result;
		        	  if(afterMoney<10000000){
		        	    document.getElementById("userTotalIncome").innerHTML= moneyFormat(afterMoney)+'元';
		        	  }else{
		        	  	afterMoney=afterMoney/10000;
		        	    document.getElementById("userTotalIncome").innerHTML= moneyFormat(afterMoney)+'万元';
		        	  }
		          }
    		
    	})*/
    	
    	
						
    	contactWe=function(){
            mobileNavi.push(Ext.create('hrmobile.public.myhome.notice',{}));
            } 
    	Ext.Ajax.request({//ok
    		url: __ctxPath+'/article/newslistArticle.do?',
    		 params: {
    			 	isMobile:'1',
    			 	lid: '30',//网站公告
    			 	page: '1',
    			 	limit:'1'
                 },
		         method: 'POST',
		          success: function(response) {
		        	  var responseText = Ext.util.JSON.decode(response.responseText);
			          var title =responseText.result[0].title;
					 /* title =title.replace(title.substr(20),'......');*/
		        	  document.getElementById("my_content_li").innerHTML =title;//滚动新标公告
		        	  document.getElementById("my_content_lis").innerHTML =title;//滚动新标公告
					  // document.getElementById("my_content_li").innerHTML =title;//滚动新标公告
		          }
    		
    	})



        //跳转banner活动详情页
        hd_detail=function(){
            window.open('https://active.clewm.net/EeiqBp?qrurl=https%3A%2F%2Fc3.clewm.net%2FEeiqBp&gtype=1&key=747cf15cf5278a75532070afab84c3657579aff843', '_blank');
        }
	
	}
});
