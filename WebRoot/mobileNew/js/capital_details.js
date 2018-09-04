var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook');
bottomTip = document.querySelector('.loading-hook');
var reqData = {page: 1, limit: 8, isApp: 1};
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


    $('#checkBtn').click(function () {
        var arr = [], arr1;
        $('.screen_list .active').each(function () {
            if ($(this).attr("data-i")) {
                arr.push($(this).attr("data-i"));
            }
        });
        arr1 = $('.screen_list_1 .active_1').attr("data-i");
        if (arr.length) {
            reqData["transferType"]=JSON.stringify(arr);
        } else {
            reqData["transferType"]=0;
        }

        reqData["createDate"] = arr1;
        initAjax();
    });

    /*初始化数据*/
    function initAjax() {
        bottomTip.innerText = '查看更多';
        reqData["page"] = 1;
        $.ajax({
            type: 'GET',
            url: '/financePurchase/moneyDetailFinancePurchase.do',
            data: reqData,
            dataType: 'json',
            success: function (data) {
                var lists = data.data.List;
                if (lists.length > 0) {
                    var template = '';
                    $.each(lists, function (i, list) {
                        if(list.dealRecordStatus!=1){
                            template += ` <li>
                                <div>
                                    <span>` + list.transferTypeName + `</span><span class="msg success">` + list.msg + `</span>
                                </div>
                                <div>
                                    <p>` + list.createDate + `</p>
                                    <p><span class="fMoney">` + (list.incomMoney ? ("+" + list.incomMoney) : ("-" + list.payMoney)) + `</span>元</p>
                                </div>
                            </li>`;
                        }
                    });


                    // 向ul容器中添加内容
                    listContent.innerHTML = template;
                    console.log($(listContent).find('li').length);
                    $(".msg").each(function () {
                        if ($(this).html().indexOf("失败") != -1) {
                            $(this).removeClass('success').addClass("fail");
                        }
                    });
                    scrollFlag = 0;
                    reqData["page"] = 1;
                    bottomTip.parentElement.style.display = "none";
                    scroll.refresh();
                } else {
                    if (reqData["transferType"]) {
                        $('.tit').html("您筛选的明细不存在").show();
                        var t = setTimeout(function () {
                            $(".tit").hide();
                            clearTimeout(t);
                        }, 3000);
                    }
                }
            }

        });
    }

    initAjax();

    // 滑动中
    scroll.on('scroll', function (position) {
        scrollFlag = 1;
        bottomTip.parentElement.style.display = "block";
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
                    reqData["page"]++;
                    $.ajax({
                        type: 'GET',
                        url: '/financePurchase/moneyDetailFinancePurchase.do',
                        data: reqData,
                        dataType: 'json',
                        success: function (data) {
                            var lists = data.data.List;
                            if (lists.length > 0) {
                                var template = '';
                                $.each(lists, function (i, list) {
                                    if(list.dealRecordStatus!=1){
                                        template += ` <li>
                                                    <div>
                                                        <span>` + list.transferTypeName + `</span><span class="msg success">` + list.msg + `</span>
                                                    </div>
                                                    <div>
                                                        <p>` + list.createDate + `</p>
                                                        <p><span class="fMoney">` + (list.incomMoney ? ("+" + list.incomMoney) : ("-" + list.payMoney)) + `</span>元</p>
                                                    </div>
                                                </li>`;
                                    }
                                });
                                // 向ul容器中添加内容
                                listContent.innerHTML = listContent.innerHTML + template;
                                $(".msg").each(function () {
                                    if ($(this).html().indexOf("失败") != -1) {
                                        $(this).removeClass('success').addClass("fail");
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



