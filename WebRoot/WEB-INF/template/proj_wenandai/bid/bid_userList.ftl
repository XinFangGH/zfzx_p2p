$(document).ready(function() { 
$.ajax({
        type: "POST",
        url: "${base}/creditFlow/financingAgency/newBidListPlBidInfo.do",
        data: { //发送给数据库的数据
               start:0,
               limit:10
               },
        dataType: 'json',
        beforeSend:function(){
           $("#load").append('<img src="${base}/theme/${systemConfig.theme}/images/loading.gif"  />');
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
               
                var list=eval(responseText.result);
                var html="<li class=\"name\" style=\"font-weight:bold;\">投资人账号</li>	<li class=\"money\" style=\"font-weight:bold;\">投资金额</li>";
                $.each(list,function(idx,item){ 
                //循环
               html+="<li class=\"name\">"+item.userName+"</li><li class=\"money\">￥"+item.userMoney+"元</li>";  
                });
                 $("#userBidList").append(html);
                } 
            },
            complete:function(XMLHttpRequest,textStatus){
             $("#load").empty();
           },
            error: function() {
                  $("#load").append("内容加载错误...");
            }
    })
}); 