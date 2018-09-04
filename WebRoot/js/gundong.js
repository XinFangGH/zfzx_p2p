// JavaScript Document
//主要功能 bybeihua

$(document).ready(function () {
    
    //首页他发布切换
    tab_he(); 
});


//首页他发布 他借出切换效果 begin
function tab_he() {

    if ($('.islend').length > 0) {
        var dota = $("#publiclist");
        var ta_tc = dota.find("ul");
        var dota2 = $("#publiclist2");
        var ta_tc2 = dota2.find("ul");
        var scroll = function(uls) {
            /*复制一次内部的li*/
            var li_h = uls.eq(0).find("li").outerHeight();

            this.copy = function() {
                uls.each(function() {
                    var lis = $(this).find("li");
                    $(this).append(lis.clone());
                    $(this).attr("li_s", lis.size() * 2);
                    $(this).attr("cur_li", lis.size() * 2 - 1);
                });
            };
            this.copy();
            /*移动到最下边的li*/
            this.toLast = function() {
                uls.each(function() {
                    $(this).scrollTop(10000);
                });
            };
            this.toLast();

            var timer = null;
            /*滚动的超时时间*/
            var i = 0;
            /*开始滚动ul数组*/
            function start(i) {
                var cur_ul = uls.eq(i);
                if (cur_ul) {
                    cur_ul.animate({
                        scrollTop: cur_ul.scrollTop() - li_h - 5
                    },
                    function() {
                        var cur_li = parseInt(cur_ul.attr("cur_li"));
                        var li_s = parseInt(cur_ul.attr("li_s"));
                        cur_li--;
                        if ((li_s / 2 - 1) == cur_li) {
                            cur_ul.attr("cur_li", li_s - 1);
                            $(this).scrollTop(10000);
                        } else {
                            cur_ul.attr("cur_li", cur_li);
                        }
                        start(++i);
                    });
                };
            };
            timer = setInterval(function() {
                start(i);
            },
            3000);
        };
        scroll(ta_tc);
        scroll(ta_tc2);
    }
}
//首页他发布 他借出切换效果 end
