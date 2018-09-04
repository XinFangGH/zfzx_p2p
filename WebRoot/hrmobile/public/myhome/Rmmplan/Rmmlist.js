var data
var listPlBid1
var bondListBid1
Ext.define('hrmobile.public.myhome.Rmmplan.Rmmlist', {
	
	extend : 'Ext.Container',
    name: 'Rmmlist',
     constructor: function (config) {
     	console.log(config);
    	config = config || {};
    	listMaterials1=config.listPlBid;
    	var dateStr = "";
   		function date(date){
			if(date != undefined) {
				dateStr = "";
				var date = new Date(date);
				var nowDate = date;
				var year = nowDate.getFullYear();
				var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
				var day = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
				return dateStr = year + "-" + month + "-" + day;
			}
   		}
    	Ext.apply(config, {
			width : "100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>投标记录</font>",
			height : "100%",
			fullscreen : true,
			scrollable : {
				direction : 'vertical'
			},
			items : [ {
				xtype : 'panel',
				margin: '-10px 0 0 0',
				html : ""
			} ]
		});
    	this.callParent([ config ]);
    	var this1 = this;
		var addTr = '<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
                  '<th width="18%">序号</th>'+
                  '<th width="18%">投标人</th>'+//用户名
                  '<th width="18%">年利率</th>'+
                  '<th width="18%">投标金额</th>'+
                  '<th>投标时间</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>';
		if(listMaterials1!=null&&listMaterials1!=""){
				for ( var i = 0; i < listMaterials1.length; i++) {
//								var TypeValue = "通过";
//								var Value = "";
								addTr +=
								'<div class="record-table" style="padding:0 !important;">'+
					            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
					             '<tbody style="padding:0 !important;">' +
					                '<tr style="padding:0 !important;">'+
					                  '<td style="padding:0 !important; "width="18%"><span class="font14">'+Number(i+1)+'</span></td>'+
					                  '<td style="padding:0 !important; "width="18%"><span class="font14">'+this.listxinghao(listMaterials1[i].userName)+'</span></td>'+
					                  '<td style="padding:0 !important; "width="18%"><span class="font14">'+listMaterials1[i].plBidPlan.yearInterestRate+'%</span></td>'+
					                  '<td style="padding:0 !important; "width="18%"><span class="mColor">'+listMaterials1[i].userMoney+'元</span></td>'+
					                  '<td style="padding:0 !important;  ">'+date(listMaterials1[i].bidtime)+'</td>'+
					                '</tr>'+
					             '</tbody></table>'+
					           '</div>'
						}
          }	
				if(addTr==""){
					addTr="<div style='text-align: center;margin-top: 50%;" +
							"color: rgb(132, 131, 131);font-size: 1em;'>暂无数据</div>";
				}
				var obj = this1.items.items[0];
				obj.setHtml(addTr);
    }, 
	listxinghao: function(userName){
		    	var userName =userName;
		    	if(userName.length>4){
                var content2=userName.toString().substring(userName.length-2,userName.length);
			    var content1=userName.toString().substring(0,2);
				userName= content1+"****"+content2;
				return userName;
	 		}
         }
	});
