var  yearlilv=25;
var  mixqixianvar =37;

Ext.define('hrmobile.public.myhome.aiplan.aubiddingupdate', {
    extend: 'Ext.Container',
    name: 'aubiddingupdate',
     constructor:function(config){
    	var this1= this;
    	var bidauto =config.bidauto;
		Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>自动投标</font>",
			name:'menu',
			scrollable: {
    	        direction: 'vertical'
    	    },
			items:[
		       {
		    	   xtype:'panel',
		    	   html:'<div class="m-user-addrAdd">'+
		    			'<div class="m-user-bar" style="font-size: 11px !important;">'+
						'<div class="w-inputBar w-bar">'+
							'<div class="w-bar-label">'+
								'每次投标金额:'+
							'</div>'+
							'<span class="w-bar-input-clear" style="display: none;">×</span>'+
							'<div class="w-bar-control">'+
								'<input style="font-size: 16px;" class="w-bar-input" type="number" name="" value="'+bidauto.bidMoney+'" id="aubidmoney">'+
							'</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'利息范围:'+
							'</div>'+
							'<div class="w-bar-control">'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="minlixi" onchange="javascript:economize()">'+
									'<option value="-1">请选择</option>'+
								'</select>～'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="mixlixi">'+
									'<option value="-1">请选择</option>'+
								'</select>'+
							'</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'借款期限'+
							'</div>'+
							'<div class="w-bar-control">'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="minqixian" onchange="javascript:market()">'+
									'<option value="-1">请选择</option>'+
								'</select>～'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="mixqixian" >'+
									'<option value="-1">请选择</option>'+
								'</select>'+
							'</div>'+
						'</div>'+
						'<div class="w-selectBar w-bar">'+
							'<div class="w-bar-label">'+
								'信用等级范围'+
							'</div>'+
							'<div class="w-bar-control">'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="minxinyong">'+
									'<option value="-1">请选择</option>'+
								'</select>～'+
								'<select style="font-size: 16px;" class="w-bar-input" name="" id="mixxinyong">'+
									'<option value="-1">请选择</option>'+
								'</select>'+
							'</div>'+
						'</div>'+
						'<div class="w-inputBar w-bar">'+
							'<div class="w-bar-label">'+
								'账户保留金额:'+
							'</div>'+
							'<span class="w-bar-input-clear" style="display: none;">×</span>'+
							'<div class="w-bar-control">'+
								'<input style="font-size: 16px;" class="w-bar-input" type="text" name="" value="'+bidauto.keepMoney+'" id="aubidfunmoney">'+
							'</div>'+
						'</div>'+
					'</div>'+
				'</div>'
    		   },
    		    {
                    	style:"margin: 20px 10px;height:44px;background:"+themeColor+";font-color:white",
                    	xtype: 'button',
                        text:"<font color=white>保存</font>",
                        handler:this.saveaubid
                }
			],
			listeners: {
				show : function(newActiveItem, this1, oldActiveItem, eOpts ) {
				    		var minlixi = document.getElementById("minlixi");
				    		for(var i=8;i<yearlilv;i++){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+i+"";
				    		     if(bidauto.interestStart!=null && bidauto.interestStart==i){
				    		    	Option.selected=true;
				    		     }
				    		     minlixi.options.add(Option);
				    		}
				    		var mixlixi = document.getElementById("mixlixi");
				    		for(var i=8;i<yearlilv;i++){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+i+"";
				    		     if(bidauto.interestEnd!=null && bidauto.interestEnd==i){
				    		    	Option.selected=true;
				    		     }
				    		     mixlixi.options.add(Option);
				    		}
				    		var minqixian = document.getElementById("minqixian");
				    		for(var i=1;i<mixqixianvar;i++){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+i+"";
				    		     if(bidauto.periodStart!=null && bidauto.periodStart==i){
				    		    	Option.selected=true;
				    		     }
				    		     minqixian.options.add(Option);
				    		}
				    		var mixqixian = document.getElementById("mixqixian");
				    		for(var i=1;i<mixqixianvar;i++){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+i+"";
				    		     if(bidauto.periodEnd!=null && bidauto.periodEnd==i){
				    		    	Option.selected=true;
				    		     }
				    		     mixqixian.options.add(Option);
				    		}
				    		var minxinyong = document.getElementById("minxinyong");
				    		for(var i=7;i>0;i--){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+xyarrayObj[i]+"";
				    			if(bidauto.rateStart!=null && bidauto.rateStart==i){
				    		    	Option.selected=true;
				    		     }
				    		     minxinyong.options.add(Option);
				    		}
				    		var mixxinyong = document.getElementById("mixxinyong");
				    		for(var i=7;i>0;i--){
				    			 var Option=document.createElement("OPTION");
				    		     Option.value=""+i+"";
				    		     Option.text=""+xyarrayObj[i]+"";
				    			if(bidauto.rateEnd!=null && bidauto.rateEnd==i){
				    		    	Option.selected=true;
				    		     }
				    		     mixxinyong.options.add(Option);
				    		}
		    	}
			}
		});
		
		this.callParent([config]);
		

		economize = function() {
			var province = document.getElementById("minlixi");
			var ParentCode=province.options[province.options.selectedIndex].value;
		    		var province = document.getElementById("mixlixi");
		    		province.length = 1;
		    		for(var i=ParentCode;i<yearlilv;i++){
		    			var Option=document.createElement("OPTION");
		    		     Option.value=""+i+"";
				   		 Option.text=""+i+"";
		    		     province.options.add(Option);
		  			  }
		};
		
		market = function() {
			var minqixian = document.getElementById("minqixian");
			var ParentCode=minqixian.options[minqixian.options.selectedIndex].value;
		    		var minqixian = document.getElementById("mixqixian");
		    		minqixian.length = 1;
		    		for(var i=ParentCode;i<mixqixianvar;i++){
		    			var Option=document.createElement("OPTION");
		    		     Option.value=""+i+"";
				   		 Option.text=""+i+"";
		    		     minqixian.options.add(Option);
		  			  }
		};
		
		

    },
saveaubid :function() {
			var typejs="add";
			var aubidmoney = document.getElementById("aubidmoney").value;//投标金额
			var aubidfunmoney = document.getElementById("aubidfunmoney").value;//保留金额
			
			var minlixi = document.getElementById("minlixi");//最小利息
			var index1=minlixi.selectedIndex;
			var minlixivalue = minlixi.options[index1].value;
			var minlixitext = minlixi.options[index1].text;
			
			var mixlixi = document.getElementById("mixlixi");//最大利息
			var index2=mixlixi.selectedIndex;
			var mixlixivalue = mixlixi.options[index2].value;
			var mixlixitext = mixlixi.options[index2].text;
			
			var minqixian = document.getElementById("minqixian");//最小月
			var index3=minqixian.selectedIndex;
			var minqixianvalue = minqixian.options[index3].value;
			var minqixiantext = minqixian.options[index3].text;
			
			var mixqixian = document.getElementById("mixqixian");//最大月
			var index4=mixqixian.selectedIndex;
			var mixqixianvalue = mixqixian.options[index4].value;
			var mixqixiantext = mixqixian.options[index4].text;
			
			var minxinyong = document.getElementById("minxinyong");//最小信用
			var index5=minxinyong.selectedIndex;
			var minxinyongvalue = minxinyong.options[index5].value;
			var minxinyongtext = minxinyong.options[index5].text;
			
			var mixxinyong = document.getElementById("mixxinyong");//最大信用
			var index6=mixxinyong.selectedIndex;
			var mixxinyongvalue = mixxinyong.options[index6].value;
			var mixxinyongtext = mixxinyong.options[index6].text;
//			var isDefault=this1.parent.getCmpByName('isaubid').getValue();//是否默认地址
			if(isEmpty(aubidmoney)){
				Ext.Msg.alert('', '每次投标金额不能为空');
        		return;
			}
			
			if(isEmpty(aubidfunmoney)){
				Ext.Msg.alert('', '保留金额不能为空');
        		return;
			}
			if(minlixivalue == -1){
				Ext.Msg.alert('', '请选择最小利息');
        		return ;
			}
			if(mixlixivalue == -1){
				Ext.Msg.alert('', '请选择最大利息');
        		return ;
			}
			
			if(minqixianvalue == -1){
				Ext.Msg.alert('', '请选择最小月');
        		return ;
			}
			if(mixqixianvalue == -1){
				Ext.Msg.alert('', '请选择最大月');
        		return ;
			}
			if(parseInt(minlixivalue)>parseInt(mixlixivalue)){
				Ext.Msg.alert('', '最小利息不可大于最大利息');
        		return ;
			}
			if(parseInt(minqixianvalue)>parseInt(mixqixianvalue)){
				Ext.Msg.alert('', '最小利息不可大于最大利息');
        		return ;
			}
			if(minxinyongvalue == -1){
				Ext.Msg.alert('', '请选择最小信用');
        		return ;
			}
			if(mixxinyongvalue == -1){
				Ext.Msg.alert('', '请选择最大信用');
        		return ;
			}
			if(minxinyongvalue>mixxinyongvalue){
				Ext.Msg.alert('', '信用等级范围不能低于下限');
        		return ;
			}
//			if(isDefault=="1"){
					Ext.Ajax.request({
	    			url : __ctxPath+'/user/saveAutoBidInfoBpCustMember.do',
	    			params:{
						bidMoney:aubidmoney,
						interestStart:minlixivalue,
						interestEnd:mixlixivalue,
						periodStart:minqixiantext,
						periodEnd:mixqixiantext,
						rateStart:minxinyongvalue,
						rateEnd:mixxinyongvalue,
						keepMoney:aubidfunmoney
	    			},
	    		   	success : function(response) {
	    		    	var responseText = Ext.util.JSON.decode(response.responseText);
	    		    	if(responseText.success==true){
	    		    		Ext.Msg.alert('提示', '保存成功');
	    		    		mobileNavi.pop();	
	    		    	}else{
	    		    		Ext.Msg.alert('提示',responseText.rate);
	    		    	}
	    		    	
	    		   }
	        	}); 
        		
//			}
//			if(isDefault=="0"){
//					Ext.Ajax.request({
//	    			url : __ctxPath + '/user/closeBidAutoBpCustMember.do',
//	    			params:{
//	    				isMobile:"1"
//	    			},
//	    		   	success : function(response) {
//	    		    	var responseText = Ext.util.JSON.decode(response.responseText);
//	    		    	Ext.Msg.alert('', responseText.prompt);
//	    		    	mobileNavi.pop();
//	    		   }
//	        	}); 
//			}
	  		
		}
});