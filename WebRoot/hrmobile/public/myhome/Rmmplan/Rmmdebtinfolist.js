var data
var listPlBid1
var bondListBid1
Ext.define('hrmobile.public.myhome.Rmmplan.Rmmdebtinfolist', {
	
	extend : 'Ext.Container',
    name: 'Rmmdebtinfolist',
     constructor: function (config) {
     	console.log(config);
    	config = config || {};
    	bondListBid1=config.bondListBid;
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
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>债权信息</font>",
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
                  '<th width="33.3%">序号</th>'+
                  '<th width="33.3%">债权人</th>'+//用户名
                  '<th width="33.3%">持有金额</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>';
		if(bondListBid1!=null&&bondListBid1!=""){
				for ( var i = 0; i < bondListBid1.length; i++) {
//								var TypeValue = "通过";
//								var Value = "";
								addTr +=
								'<div class="record-table" style="padding:0 !important;">'+
					            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
					             '<tbody style="padding:0 !important;">' +
					                '<tr style="padding:0 !important;">'+
					                  '<td style="padding:0 !important; "width="33.3%"><span class="font14">'+Number(i+1)+'</span></td>'+
					                  '<td style="padding:0 !important; "width="33.3%"><span class="font14">'+this.listxinghao(bondListBid1[i].userName)+'</span></td>'+
					                  '<td style="padding:0 !important; "width="33.3%"><span class="mColor">'+bondListBid1[i].bondTotelMoney+'元</span></td>'+
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
