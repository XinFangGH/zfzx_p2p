var flag = false;
Ext.define('hrmobile.public.myhome.planbid.bidrelated', {
	extend : 'Ext.Container',
    name: 'bidrelated',
     constructor: function (config) {
    	config = config || {};
    	var listMaterials=config.listMaterials;
    	var bidId =config.bidId;
    	Ext.apply(config, {
			width : "100%",
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>相关文件</font>",
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
    	var this1 = this;
		var addTr = '<div class="record-table" style="padding:0px 0 0 0 !important;">'+
            '<table width="100%" border="0" cellspacing="0" cellpadding="0">'+
              '<tbody><tr>'+
                  '<th width="25%">审核项目</th>'+
                  '<th width="25%">状态</th>'+
                  '<th width="25%">通过日期</th>'+
                  '<th width="25%">详细</th>'+
                '</tr>'+
            '</tbody></table>'+
          '</div>';
		for ( var i = 0; i < listMaterials.length; i++) {
				if (flag == false) {
					flag = true;
				}
						var TypeValue = "通过";
						var Value = "";
						addTr +=
						'<div class="record-table" style="padding:0 !important;">'+
				            '<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:0 !important;">'+
				             '<tbody style="padding:0 !important;">' +
				                '<tr style="padding:0 !important;">'+
				                  '<td style="padding:0 !important;  width:25%;"><span class="font14"></span>'+listMaterials[i].materialsName+'<br></td>'+
				                  '<td style="padding:0 !important;  width:25%;"><span class="green">'+TypeValue+'</span></td>'+
				                  '<td style="padding:0 !important;  width:25%;">' + listMaterials[i].createTime + '</td>'+
				                  		/*listMaterials[i].imgUrl +*/
								  '<td style="padding:0 !important;  width:25%;">' +
								  	'<span class="green"  onclick="javascript:openimage('+i+');">' +
								  			'查看' +
								  	'</span>' +
								  '</td>'+
				                '</tr>'+
				             '</tbody></table>'+
				    '</div>';
				}
				
				
				if (flag == false) {
					addTr="<div style='text-align: center;margin-top: 50%;" +
							"color: rgb(132, 131, 131);font-size: 1em; background:" + f3Color + ";'>暂无数据</div>";
				}
				var obj = this1.items.items[0];
				obj.setHtml(addTr);
				
		openimage=function(i){
			var i=i++;
			//sconsole.dir(listMaterials);
			var url= __ctxPath+"/"+listMaterials[i].imgUrl;
			localStorage.setItem("src",url);
			showImg(localStorage);
		}	
    }
});
