var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook');
    bottomTip = document.querySelector('.loading-hook');
var p=2;
/*
 * 此处可优化,定义一个变量,记录用户滑动的状态,初始值为0,滑动中为1,滑动结束、数据请求成功重置为0
 * 防止用户刷新列表http请求过多
*/
var scrollFlag=0;
function initScroll() {
    var scroll = new window.BScroll(listWrapper, {
        probeType: 1,
        click:true
    });
    /*初始化数据*/
    $.ajax({
        type:'GET',
        url:'/message/getUserMesAllOaNewsMessage.do',
        data:{isApp:1,page:1,limit:10},
        dataType:'json',
        success:function(data){
            var lists=data.data.list;
            var template='';
            $.each(lists,function(i,list){
                template+= ` <li>
                                <a href="/message/getMesOaNewsMessage.do?id=` + list.id + `">
                                    <h3><b class="h_text">`+list.title+`</b>`+(list.readStatus?"":"<span ></span>")+`</h3>
                                    <span>`+list.sendTime+`</span>
                                    <div class="h_con">`+list.content+`</div>
                                </a>
                            </li>`;
            });


            // 向ul容器中添加内容
            listContent.innerHTML = template;
            $(".h_text").each(function(){
                var str=$(this).text().trim();
                var maxwidth=12;
                if(str.length > maxwidth){
                    $(this).html(str.substring(0,maxwidth)+'...');
                }else{
                    $(this).html(str);
                }
            });
            $(".h_con").each(function(i,dom){
                var str=$(this).text().trim();
                var maxwidth=26;
                if(str.length > maxwidth){
                    $(this).html(str.substring(0,maxwidth)+'...');
                }else{
                    $(this).html(str);
                }
            });
            scrollFlag=0;
            p=2;
            scroll.refresh();
        }
    });
    // 滑动中
    scroll.on('scroll', function (position) {
        scrollFlag=1;
        if(position.y > 30) {
            topTip.innerText = '释放立即刷新';
        }
    });
    /*
     * @ touchend:滑动结束的状态
     * @ maxScrollY:屏幕最大滚动高度
    */
    // 滑动结束
    scroll.on('touchend', function (position) {
        scrollFlag=0;
        if (position.y > 30) {

        }else if(position.y < (this.maxScrollY - 30)) {
            bottomTip.parentElement.style.display="block";
            if(bottomTip.innerText=='已无更多数据'){
                return;
            }
            bottomTip.innerText = '加载中...';
            setTimeout(function () {
                // 恢复文本值
                bottomTip.innerText = '查看更多';
                // 向列表添加数据
                if(scrollFlag==0){
                    /*reloadData();*/
                    $.ajax({
                        type:'GET',
                        url:'/message/getUserMesAllOaNewsMessage.do',
                        data:{isApp:1,page:p,limit:5},
                        dataType:'json',
                        success:function(data){
                            p++;
                            var lists=data.data.list;
                            if(lists.length>0){
                                var template='';
                                $.each(lists,function(i,list){
                                    template+= ` <li>
                                                    <a href="/message/getMesOaNewsMessage.do?id=` + list.id + `">
                                                        <h3><b class="h_text">`+list.title+`</b>`+(list.readStatus?"":"<span ></span>")+`</h3>
                                                        <span>`+list.sendTime+`</span>
                                                        <div class="h_con">`+list.content+`</div>
                                                    </a>
                                                </li>`;
                                });
                                // 向ul容器中添加内容
                                listContent.innerHTML = listContent.innerHTML + template;
                                $(".h_text").each(function(){
                                    var str=$(this).text().trim();
                                    var maxwidth=12;
                                    if(str.length > maxwidth){
                                        $(this).html(str.substring(0,maxwidth)+'...');
                                    }else{
                                        $(this).html(str);
                                    }
                                });
                                $(".h_con").each(function(i,dom){
                                    var str=$(this).text().trim();
                                    var maxwidth=26;
                                    if(str.length > maxwidth){
                                        $(this).html(str.substring(0,maxwidth)+'...');
                                    }else{
                                        $(this).html(str);
                                    }
                                });
                                scroll.refresh();
                                scrollFlag=0;
                            }else{
                                bottomTip.innerText = '已无更多数据';
                            }
                        }
                    });

                }

                // 加载更多后,重新计算滚动区域高度
                scroll.refresh();
                scrollFlag=0;
            }, 1000);
        }
    });
}

initScroll();



