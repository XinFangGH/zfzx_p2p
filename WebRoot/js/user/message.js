
//删除站内信（单个删除）

function deleteMessage(id){
    $.ajax({
        url: basepath+"message/isDeleteOaNewsMessage.do",
        data: {
            "ids":id,
            "tab":"news"
        },
        dataType:"JSON",
        success:function(responseText, statusText){
            if (statusText == "success"){
                showCommonTip(".common-tip",responseText.errMsg);
                // addPage(1,{start:0,limit:10});
                $("#newsMess").addClass("active");
                $("#acount").removeClass("active");
                $("#ol2").addClass("rent");
                $("#ol1").removeClass("rent");
                setInterval(function(){
                    $(".common-tip").hide();
                },3000);
                layer.msg('删除成功!',1,1);
                location.reload();
            }else{
                layer.msg('删除失败!',1,8);
                location.reload();
            }
        },
        error:function(){
            showCommonTip(".common-tip","操作失败");
            location.reload();
        }
    });
}
//标记为已读，多个删除
function updateMessage(state){
    var idAll ="";
    $("input[name='checkbox']:checked").each(function(){
        idAll+=$(this).val()+",";
    })
    if(idAll==""){
        layer.msg('请选择要操作的记录!',1,3);
    }else{
        $.ajax({
            url: basepath+"message/isUpdateOaNewsMessage.do",
            data: {
                "state":state,
                "ids":idAll,
                "tab":"tab"
            },
            dataType:"JSON",
            success:function(responseText, statusText){
                if (statusText == "success"){
                    showCommonTip(".common-tip",responseText.errMsg);
                    // addPage(1,{start:0,limit:10});
                    $("#newsMess").addClass("active");
                    $("#acount").removeClass("active");
                    $("#ol2").addClass("rent");
                    $("#ol1").removeClass("rent");
                    setInterval(function(){
                        $(".common-tip").hide();
                    },3000);
                    if(state==1){
                        layer.msg('标记为已读成功!',1,1);
                        location.reload();
                    }else{
                        layer.msg('批量删除成功!',1,1);
                        location.reload();
                    }
                }else{
                    layer.msg('操作失败!',1,8);
                    location.reload();
                }
            },
            error:function(){
                showCommonTip(".common-tip","操作失败");
                location.reload();
            }
        })
    }
}

//删除所选站内信（批量删除）
function deleteAllSelectMessage(){

    var obj=document.getElementsByName('checkbox'); //选择所有name="aihao"的对象，返回数组
    //取到对象数组后，我们来循环检测它是不是被选中
    var s='';
    for(var i=0; i<obj.length; i++){
        if(obj[i].checked) {
            if(i==(obj.length-1)){
                s+=obj[i].value;//如果选中，将value添加到变量s中,最后一次不加,
            }else{
                s+=obj[i].value+','; //如果选中，将value添加到变量s中
            }
        }
    }
    //那么现在来检测s的值就知道选中的复选框的值了
//	alert(s==''?'您还没有选择任何内容！':s);
    if(s==''){
        showCommonTip(".common-tip","请选择您要删除的记录！");
        //alert("请选择您要删除的记录！");
        return false;
    }
    var ids = s;
    if (confirm("确认要删除吗？")){

        $.ajax({
            url: basepath+"message/isDeleteOaNewsMessage.do",
            data: {
                "ids":ids
            },
            dataType:"JSON",
            success:function(responseText, statusText){
                if (statusText == "success"){
                    //alert("删除成功！");
                    showCommonTip(".common-tip",responseText.errMsg);
                    location.reload();
                }else{
                    showCommonTip(".common-tip","基本资料修改失败");
                }
            },
            error:function(){
                showCommonTip(".common-tip","基本资料修改失败");
            }
        });
    }else{
        return false;
    }
}

//全选和取消全选功能
var checkflag = "false";
function check(field) {
    if (checkflag == "false") {
        for (i = 0; i < field.length; i++) {
            field[i].checked = true;}
        checkflag = "true";
        return "Uncheck All";

    }else {
        for (i = 0; i < field.length; i++) {
            field[i].checked = false;
        }
        checkflag = "false";
        return "Check All";
    }
}

$(function(){
    $("#all").click(function(){
        if($("#all").attr("checked")=="checked"){
            $("table tr td [name='checkbox']").attr("checked",'true');//全选
            //$("#check").attr("checked",'true');
        }else{
            $("table tr td [name='checkbox']").removeAttr("checked");//反选
            //$("#check").removeAttr("checked");
        }

    })

    /*	$("#check").click(function(){
            if($("#check").attr("checked")=="checked"){
                $("table tr td [name='checkbox']").attr("checked",'true');//全选
                $("#all").attr("checked",'true');
            }else{
                $("table tr td [name='checkbox']").removeAttr("checked");//全选
                $("#all").removeAttr("checked");
            }

        }) */
});