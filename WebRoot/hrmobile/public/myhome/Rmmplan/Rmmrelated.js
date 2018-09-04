var listMaterials1
var salseId
Ext.define('hrmobile.public.myhome.Rmmplan.Rmmrelated', {
	extend : 'Ext.Container',
    name: 'Rmmrelated',
     constructor: function (config) {
    	config = config || {};
    	listMaterials1=config.listMaterials;
    	salseId=config.salseId;
    	Ext.apply(config, {
			width : "100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>相关材料</font>",
			height : "100%",
			fullscreen : true,
			scrollable : {
				direction : 'vertical'
			},
			items : [ {
				xtype : 'panel',
				margin: '-10px 0 0 0',
				html : ""
			} ],
			listeners :  {
				painted : function () {
					// 监听键下事件
					$(document).bind('keypress', function(e) {
					    var evt = e || window.event;
					    var keyCode = evt.keyCode || evt.which || evt.charCode;
					    	if (keyCode == 13) {
					    		mobileNavi.setMasked(false);
								$('.x-img-image').hide();
					    	}
					})
				}
			}
		});
    	this.callParent([ config ]);
    	
    	
    	// 点击遮罩层,隐藏图片
		$('.x-dock-body').on('tap', function () { 
				mobileNavi.setMasked(false);
				$('.x-img-image').hide();
			}
		)
    	
    	var this1 = this;
		var addTr = '<div class="record-table" style="padding:10px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
                  '<th width="25%">审核项目</th>'+
                  '<th width="25%">状态</th>'+
                  '<th width="25%">通过日期</th>'+
                  '<th width="25%">详细</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>';
		if(listMaterials1!=null&&listMaterials1!=""){
				for ( var i = 0; i < listMaterials1.length; i++) {
								var TypeValue = "通过";
								var Value = "";
								addTr +=
								'<div class="record-table" style="padding:0 !important;">'+
						            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
						             '<tbody style="padding:0 !important;">' +
						                '<tr style="padding:0 !important;">'+
						                  '<td style="padding:0 !important;  width:25%;"><span class="font14"></span>'+listMaterials1[i].materialsName+'<br></td>'+
						                  '<td style="padding:0 !important;  width:25%;"><span class="green">'+TypeValue+'</span></td>'+
						                  '<td style="padding:0 !important;  width:25%;">'+listMaterials1[i].createTime+'</td>'+
										  '<td style="padding:0 !important;  width:25%;"><span class="green"  onclick="javascript:openimage({i:'+i+'});">查看</span></td>'+
						                '</tr>'+
						             '</tbody></table>'+
						    '</div>';
						}
          }	
				if(addTr==""){
					addTr="<div style='text-align: center;margin-top: 50%;" +
							"color: rgb(132, 131, 131);font-size: 1em;'>暂无数据</div>";
				}
				var obj = this1.items.items[0];
				obj.setHtml(addTr);
				openimage=function(i){
				var ii=i.i;
				var url= __ctxPath+"/"+listMaterials1[ii].imgUrl;
				localStorage.setItem("src",url);
				showImg(localStorage);
				
				// 更改图片大小和自适应
				$('.x-img-image ').css({
					'width': '90%',
					'margin': '0 auto',
					'background-size': '100% 100%'
				})
			}
    }
});
