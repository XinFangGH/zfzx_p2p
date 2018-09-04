Ext.define('hrmobile.public.myhome.invitation.myinvitation', {
    extend: 'Ext.form.Panel',
    name: 'myinvitation',
    constructor: function (config) {
    	config = config || {};
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>邀请码</font>",
    		width:"100%",
		    height:"100%",
            style:"margin-top:42px;",
		    fullscreen: true,
		    cls:'hd',
		    scrollable:{
		    	direction: 'vertical'
		    },
                items: [{
                	xtype: 'container',
                    html: 
                    	'<div class="fill my_fill">'+
            			/*'<div style="font-size:17px;color:#000;"><div style="padding: 10px;border-bottom: 1px solid #eee;">推荐人<div style="float:right;">'+(null==curUserInfo.recommandPerson?"":curUserInfo.recommandPerson)+'</div></div></div>'+
            			'<div style="font-size:17px;color:#000;"><div style="padding: 10px;border-bottom: 1px solid #eee;">推荐码<div style="float:right;" id="my_code">'+(null==curUserInfo.plainpassword?"":curUserInfo.plainpassword)+'</div></div></div>'+
					    '<div style="font-size:17px;color:#000;"><div style="padding: 10px;border-bottom: 1px solid #eee;">推荐链接</div></div>'+
					    '<div style="font-size:17px;color:#000;"><div style="padding: 10px;border-bottom: 1px solid #eee;">'+__ctxPath+'/htmlreg.do?recommand='+(null==curUserInfo.plainpassword?"":curUserInfo.plainpassword)+'</div></div>'+
					    '<div style="font-size:17px;color:#000;"><div style=" margin:10px">我的二维码</div></div>'+*/
            			'<div class="dt"><span></span>让朋友用手机扫描下方的二维码即可</div>'+
					    '<nav class="assets-nav assets1-top">'+
					        	/*'<a id="output_plainpassword" style="width: 100%;text-align: center;padding-left:12%;"></a>'+*/
					        	'<a id="output_plainpassword" style="display:block;width:256px;height:256px;margin:0 auto;"></a>'+
					    '</nav>'+
						/*'<div class="dt"><span>方式二：</span>告诉朋友您的推荐码，让他在注册时输入</div>'+
						'<div class="db">推荐码：<span>'+(null==curUserInfo.telphone?"":curUserInfo.telphone)+'</span></div>'+*/
					'</div>'
                   
//					'<ul id="accordion" class="accordion">'+
//						'<li class="open">'+
//							'<div class="link"><i class="fa fa-globe"></i>推荐人<i class="fa fa-chevron-down"></i></div>'+
//							'<ul class="submenu" style="display: block;">'+
//								'<li><a>'+(null==curUserInfo.recommandPerson?"":curUserInfo.recommandPerson)+'</a></li>'+
//							'</ul>'+
//						'</li>'+
//						'<li class="open">'+
//							'<div class="link"><i class="fa fa-globe"></i>推荐码<i class="fa fa-chevron-down"></i></div>'+
//							'<ul class="submenu" style="display: block;">'+
//								'<li><a>'+(null==curUserInfo.plainpassword?"":curUserInfo.plainpassword)+'</a></li>'+
//							'</ul>'+
//						'</li>'+
//						'<li class="open">'+
//							'<div class="link"><i class="fa fa-globe"></i>推荐链接<i class="fa fa-chevron-down"></i></div>'+
//							'<ul class="submenu" style="display: block;">'+
//								'<li><a>'+__ctxPath+'/htmlreg.do?recommand='+(null==curUserInfo.plainpassword?"":curUserInfo.plainpassword)+'</a></li>'+
//							'</ul>'+
//						'</li>'+
//						'<li class="open"><div class="link"><i class="fa fa-globe"></i>我的二维码<i class="fa fa-chevron-down"></i></div>'+
//							'<ul class="submenu" style="display: block;">'+
//								'<li><a id="output_plainpassword" style="width: 100%;text-align: center;padding-left: 0px;"></a></li>'+
//							'</ul>'+
//						'</li>'+
//					'</ul>'
                }],
                listeners : {
//                	resize :function(){
//						var Accordion = function(el, multiple) {
//						this.el = el || {};
//						this.multiple = multiple || false;
//				
//						// Variables privadas
//						var links = this.el.find('.link');
//						// Evento
//						links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
//					}
//				
//					Accordion.prototype.dropdown = function(e) {
//						var $el = e.data.el;
//							$this = $(this),
//							$next = $this.next();
//				
//						$next.slideToggle();
//						$this.parent().toggleClass('open');
//				
//						if (!e.data.multiple) {
//							$el.find('.submenu').not($next).slideUp().parent().removeClass('open');
//						};
//					}	
//				
//					var accordion = new Accordion($('#accordion'), false);
//				},
				 show :function(){
			    	jQuery('#output_plainpassword').qrcode(__ctxPath+"/htmlreg.do?backpath=hrmobile.public.myhome.register1&recommand="+curUserInfo.plainpassword)
			    	// jQuery('#output_plainpassword').qrcode("http://192.168.0.129:8080/htmlreg.do?backpath=hrmobile.public.myhome.register1&recommand="+curUserInfo.plainpassword)
			   	 },
				painted:function(){
					$('#navbar').css({"position":"fixed","width":"100%","z-index":"9"});
				}

				}
		          
    	});


    	this.callParent([config]);
    	
    }
});
