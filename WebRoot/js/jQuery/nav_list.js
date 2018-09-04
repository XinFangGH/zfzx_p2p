$("#xy-logo-nav>li>a").bind("click", function(){
    $(this).addClass("change").parent().siblings().children().removeClass("change");
});