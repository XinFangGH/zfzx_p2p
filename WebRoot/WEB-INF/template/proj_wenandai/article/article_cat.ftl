$(document).ready(function() { 
$.ajax({
        type: "POST",
        url: "${base}/article/listByTypeArticleCategory.do",
        data: { //发送给数据库的数据
               "Q_type_N_EQ":1
               },
        dataType: 'json',
        beforeSend:function(){
           $("#catload").append('<img src="${base}/theme/${systemConfig.theme}/images/loading.gif"  />');
        },
       success: function(responseText, statusText) {
                if (statusText == "success") {
                var list=eval(responseText.result);
                var html="<li class=\"title\">新闻类别</li>";
                $.each(list,function(idx,item){
                //循环
                  html+="<li class=\"trititle\"><a href='${base}/article/listArticle.do?catId="+item.id+"'><span>"+item.name+"</span></a></li>";
                });
                 $("#articCat").append(html);
                } 
            },
            complete:function(XMLHttpRequest,textStatus){
             $("#catload").empty();
           },
            error: function() {
                  $("#catload").append("内容加载错误...");
            }
    })
}); 