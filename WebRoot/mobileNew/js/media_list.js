var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook');
bottomTip = document.querySelector('.loading-hook');
var p = 2;
/*
 * 此处可优化,定义一个变量,记录用户滑动的状态,初始值为0,滑动中为1,滑动结束、数据请求成功重置为0
 * 防止用户刷新列表http请求过多
*/
var scrollFlag = 0;

function initScroll() {
    var scroll = new window.BScroll(listWrapper, {
        probeType: 1,
        click: true
    });
    /*初始化数据*/
    $.ajax({
        type: 'GET',
        url: '/webPhone/getWebMediaListhomePage.do',
        data: {articleCategory_id: 34, isApp: 1, page: 1, limit: 5},
        dataType: 'json',
        success: function (data) {
            var lists = data.data.mediaList;
            var template = '';
            $.each(lists, function (i, media) {
                template += `<a href="/webPhone/getWebMediahomePage.do?mediaId=`+media.id+`">
                               <div class="list_con">
                                <div class="dt clearfix">
                                    <p class="pl">` + media.title + `</p>
                                    <p class="pr">查看</p>
                                </div>
                                <div class="dc">` + media.content + `</div>
                            </div>
                            </a>`;
            });


            // 向ul容器中添加内容
            listContent.innerHTML = template;
            $(".media_list .dc").each(function (i, dc) {
                var maxwidth = 58;

                if ($(this).text().length > maxwidth) {
                    $(this).html($(this).find('p').text().substring(0, maxwidth) + '...' + '<span ></span>');
                } else {
                    $(this).html($(this).find('p').text());
                }
            });
            scrollFlag = 0;
            p = 2;
            scroll.refresh();
        }
    });
    // 滑动中
    scroll.on('scroll', function (position) {
        scrollFlag = 1;
        if (position.y > 30) {
            //topTip.innerText = '释放立即刷新';
        }
    });
    /*
     * @ touchend:滑动结束的状态
     * @ maxScrollY:屏幕最大滚动高度
    */
    // 滑动结束
    scroll.on('touchend', function (position) {
        scrollFlag = 0;
        if (position.y > 30) {

        } else if (position.y < (this.maxScrollY - 30)) {
            bottomTip.parentElement.style.display = "block";
            if (bottomTip.innerText == '已无更多数据') {
                return;
            }
            bottomTip.innerText = '加载中...';
            setTimeout(function () {
                // 恢复文本值
                bottomTip.innerText = '查看更多';
                // 向列表添加数据
                if (scrollFlag == 0) {
                    /*reloadData();*/
                    $.ajax({
                        type: 'GET',
                        url: '/webPhone/getWebMediaListhomePage.do',
                        data: {articleCategory_id: 34, isApp: 1, page: p, limit: 5},
                        dataType: 'json',
                        success: function (data) {
                            p++;
                            var lists = data.data.mediaList;
                            if (lists.length > 0) {
                                var template = '';
                                $.each(lists, function (i, media) {
                                    template += `<a href="/webPhone/getWebMediahomePage.do?mediaId=` + media.id + `">
                                               <div class="list_con">
                                                <div class="dt clearfix">
                                                    <p class="pl">` + media.title + `</p>
                                                    <p class="pr">查看</p>
                                                </div>
                                                <div class="dc">` + media.content + `</div>
                                            </div>
                                           <a/>`;
                                });
                                // 向ul容器中添加内容
                                listContent.innerHTML = listContent.innerHTML + template;
                                $(".media_list .dc:has('p')").each(function (i, dc) {
                                    var maxwidth = 58;

                                    if ($(this).text().length > maxwidth) {
                                        $(this).html($(this).find('p').text().substring(0, maxwidth) + '...' + '<span ></span>');
                                    } else {
                                        $(this).html($(this).find('p').text());
                                    }
                                });
                                scroll.refresh();
                                scrollFlag = 0;
                            } else {
                                bottomTip.innerText = '已无更多数据';
                            }
                        }
                    });

                }

                // 加载更多后,重新计算滚动区域高度
                scroll.refresh();
                scrollFlag = 0;
            }, 1000);
        }
    });
}

initScroll();



