var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook');
/*
 * 此处可优化,定义一个变量,记录用户滑动的状态,初始值为0,滑动中为1,滑动结束、数据请求成功重置为0
 * 防止用户刷新列表http请求过多
*/
var scroll;
var scrollFlag=0;
function initScroll() {
     scroll = new window.BScroll(listWrapper, {
        probeType: 1,
        click:true,
        momentum:false
    });
    // 滑动中
    scroll.on('scroll', function (position) {
        scrollFlag=1;
        if(position.y > 30) {
            $('.top-tip .pic').addClass("ani_rote");
        }
    });
    /*
     * @ touchend:滑动结束的状态
     * @ maxScrollY:屏幕最大滚动高度
    */
    // 滑动结束
    scroll.on('touchend', function (position) {
        scrollFlag=0;
        scroll.refresh();
        if (position.y > 30) {
            if($('#page2').css('display')=="block"){
               /* setTimeout(function () {*/
                    // 恢复文本值
                    //bottomTip.innerText = '查看更多';
                    $('.top-tip .pic').removeClass("ani_rote");
                    // 向列表添加数据
                    if(scrollFlag==0){
                        /*reloadData();*/
                        $('#page1').show();
                        $('#page2').hide();
                        $('.list-wrapper-hook').css({"top":"0.9rem"});
                        $('.swiper1').hide();
                        scroll.refresh();
                        scrollFlag=0;
                    }

                    // 加载更多后,重新计算滚动区域高度
               /* }, 200);*/
            }
        }else if(position.y < (this.maxScrollY - 30)) {
            if($('#page1').css('display')=="block"){
               /* setTimeout(function () {*/
                    // 恢复文本值
                    //bottomTip.innerText = '查看更多';
                    // 向列表添加数据
                    if(scrollFlag==0){
                        /*reloadData();*/
                        $('#page2').show();
                        $('#page1').hide();
                        $('.list-wrapper-hook').css({"top":"2rem"});
                        $('.swiper1').show();
                        scroll.scrollTo(0,0);
                        scroll.refresh();
                        scrollFlag=0;
                    }

                    // 加载更多后,重新计算滚动区域高度
               /* }, 20);*/
            }
        }
    });
}

initScroll();


function setCurrentSlide(ele, index) {
    $(".swiper1 .swiper-slide").removeClass("selected");
    ele.addClass("selected");
    //swiper1.initialSlide=index;
}
var swiper1 = new Swiper('.swiper1', {
//					设置slider容器能够同时显示的slides数量(carousel模式)。
//					可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
//					loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
    slidesPerView: 3,
    paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
    spaceBetween: 10,//slide之间的距离（单位px）。
    freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
    loop: false,//是否可循环
    observer: true,//修改swiper自己或子元素时，自动初始化swiper
    observeParents: true,//修改swiper的父元素时，自动初始化swiper
    onTab: function (swiper) {
        var n = swiper1.clickedIndex;
    }
});
swiper1.slides.each(function (index, val) {
    var ele = $(this);
    ele.on("click", function () {
        scroll.scrollTo(0,0);
        setCurrentSlide(ele, index);
        $(".swiper2  .mySlide:eq(" + index + ")").show().siblings().hide();

        if(index == "2"){
            $(".swiper2  .mySlide:eq(1)").css("display","none");
            $("#Loan_record").css("display","block");
        }
        //swiper2.slideTo(index, 500, false);
        //mySwiper.initialSlide=index;
    });
});





