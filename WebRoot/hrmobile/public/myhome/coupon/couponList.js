Ext.define('hrmobile.public.myhome.coupon.couponList', {
	extend : 'Ext.Container',

	name : 'couponList',

	constructor : function(config) {
		this.flag = config.flag;
		Ext.apply(config, {
			width : "100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>我的优惠券</font>",
			height : "100%",
			fullscreen : true,
			scrollable : {
				direction : 'vertical'
			},
			items : [ {
				xtype : 'panel',
				html : ""
			} ]
		});

		this.callParent([ config ]);
		var flag = this.flag;
		var flag2 = this.flag2;
		var this1 = this;
		Ext.Ajax.request({
			url : __ctxPath + '/coupon/couponsAllListBpCoupons.do?isMobile=1',
			params : {
                userid:curUserInfo.id,
				couType:flag
			},
			success : function(response) {
				var responseText = Ext.util.JSON.decode(response.responseText);
				var list = responseText.result; //JSON.parse(responseText.result);
				var addTr = "";
				for ( var i = 0; i < list.length; i++) {

						var couponTypeValue = "";
						var couponValue = "";
						if (list[i].couponType == 1) {
							couponTypeValue = "返现券";
							couponValue = "￥" + list[i].couponValue;
						}
						if (list[i].couponType == 2) {
							couponTypeValue = "体验券";
							couponValue = "￥" + list[i].couponValue;
						}
						if (list[i].couponType == 3) {
							couponTypeValue = "加息券";
							couponValue = list[i].couponValue + "%";

						}
						addTr += '<div class="stamp stamp02"><div class="par"><p>席瑞杰</p><sub class="sign">￥</sub><span>1000</span><sub>优惠券</sub><p class="dingdan"></p></div><div class="copy"><p>2016-05-20<br>2016-06-30</p><a style="font-szie:14px"></a></div><i></i></div>'
				}
				if(addTr==""){
					addTr="<div style='text-align: center;margin-top: 50%;" +
							"color: rgb(132, 131, 131);font-size: 1em;background-color: #F3F3F3;'>暂无数据</div>";
				}
				var obj = this1.items.items[0];
				obj.setHtml(addTr);
			}
		});
	}
});

/*Ext.define('hrmobile.public.myhome.couponList', {

 extend: 'mobile.List',
 name: 'couponList',
 constructor: function (config) {
 config = config || {};
 recordsitoInvestlist=new Array();
 this.flag=config.flag;
 this.flag2=config.flag2;
 Ext.apply(config,{
 modeltype:"couponList",
 flex:1,
 width:"100%",
 height:"100%",
 title:"<font color=#9e2328>优惠券</font>",
 pullRefresh: true,
 listPaging: true,
 isload:true,
 loadfunction:function(this1,records){
 for (var i=records.length-1; i >= 0; i--) {  
 recordsitoInvestlist.push( records[i] );  
 }  

 },
 fields:[ {
 name : 'couponType'
 },{
 name : 'couponsDescribe' //
 },{
 name : 'couponStatus' //
 },{
 name : 'couponStartDate' //
 },{
 name : 'couponEndDate' //
 },{
 name : 'couponValue'
 }],
 url : __ctxPath + '/coupon/listBpCoupons.do',
 root:'result',
 totalProperty: 'totalCounts',
 params : {
 isMobile : "1",
 },
 pressedCls:'',
 itemCls :"listitems",
 itemTpl: new Ext.XTemplate( 
 "<tpl if='couponStatus == "+this.flag+"||couponStatus =="+this.flag2+"'>"+
 "<div class='coupon_three'> "+
 "<div class='bg'>" +
 "<div class='bg-box1'>" +
 "<tpl if='couponType == 1'>返现券</tpl>" +
 "<tpl if='couponType == 2'>体验券</tpl>" +
 "<tpl if='couponType == 3'>加息券</tpl>" +
 "</div>" +
 "<div class='bg-box2'>" +
 "<tpl if='couponType == 3'>{couponValue}%</br></tpl>" +
 "<tpl if='couponType != 3'>￥{couponValue}</br></tpl>" +
 "<span>{couponStartDate}--{couponEndDate}</span>"+
 "</div>" +
 "</div>"+
 "</div></tpl>")
 });

 this.callParent([config]);


 }
 });
 */
