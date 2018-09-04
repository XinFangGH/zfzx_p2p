var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook'),
    bottomTip = document.querySelector('.loading-hook');
var p1=1,p2=1,p3=1,p4=1;
/*
 * 此处可优化,定义一个变量,记录用户滑动的状态,初始值为0,滑动中为1,滑动结束、数据请求成功重置为0
 * 防止用户刷新列表http请求过多
*/
var scrollFlag=0;
function  timeFn(i,time) {
    var t=setInterval(timeSub,60000);
    timeSub();
    if(time>0){
        var day = parseInt((time+60) / 60 / 60 / 24);
        var hour = parseInt((time+60) / 60 / 60 % 24);
        var minute = parseInt((time+60) / 60 % 60);
        //var seconds = parseInt(time / 1000 % 60);
        if(day<10){
            day='0'+day;
        }
        if(hour<10){
            hour='0'+hour;
        }
        if(minute<10){
            minute='0'+minute;
        }
        return (day + "天" + hour + "时" + minute + "分");
    }
    function timeSub(){
        if(time>0){
            var day = parseInt(time / 60 / 60 / 24);
            var hour = parseInt(time / 60 / 60 % 24);
            var minute = parseInt(time / 60 % 60);
            //var seconds = parseInt(time / 1000 % 60);
            if(day<10){
                day='0'+day;
            }
            if(hour<10){
                hour='0'+hour;
            }
            if(minute<10){
                minute='0'+minute;
            }
            $("#time"+i).html(day + "天" + hour + "时" + minute + "分");
            time-=60;
        }else{
            clearInterval(t);
            $("#time"+i).html("募集期结束");
        }
    }
}
function initScroll() {
    var scroll = new window.BScroll(listWrapper, {
        probeType: 1,
        click:true
    });
    /*出借中初始化数据*/
    function initAjax1(){
        $.ajax({
            type:'GET',
            url:'/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do',
            data:{planstate:1,isApp:1,limit:8,start:p1},
            dataType:'json',
            success:function(data){
                p1++;
                var lists=data.data.fundPayList;
                var plans=data.data.plBidPlans;
                var template='';
                if(lists.length){
                    $.each(lists,function(i,list){
                        template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.planId+`">
                                   <div class="lend_con">
                                <div class="dt clearfix">
                                    `+(list.novice == 1?"<span class=\"new\">新</span>":"")+`
                                    <span class="sd">`+list.proKeepType.substr(0,2)+`</span>
                                    <p class="mark_name">`+list.projectName+`</p>
                                    <p class="pr">投标时间：<span>`+list.bidtime.substr(0,10)+`</span></p>
                                </div>
                                <ul class="dc clearfix">
                                    <li>
                                        <p>
                                            `+(list.showRate?("<em>"+((list.interestRate-list.showRate).toFixed(1))+"</em>%+<em>"+list.showRate+"</em>%"):("<em>"+list.interestRate+"</em>%"))+`
                                        </p>
                                        <span>预期年化利率</span>
                                    </li>
                                    <li>
                                        <p>`+list.loanLife+`</p>
                                        <span>期限</span>
                                    </li>
                                    <li>
                                        <p>
                                            `+(list.userMoney >= 10000?('<em class="cor_org">'+(list.userMoney/10000).toFixed(2)+"</em>万元"):('<em class="cor_org">'+list.userMoney+"</em>元"))+`
                                        </p>
                                        <span>出借金额</span>
                                    </li>
                                </ul>
                                <div class="db clearfix">
                                    <p class="lf">剩余募集时间：<span id="time` + i + `">` + ((list.state == 7 || list.state == 2) ? "募集期结束" : timeFn(i, parseInt(list.remainingTime))) + `</span></p>
                                    <p class="rt">剩余&nbsp;
                                        `+(list.bAftermoney >= 10000?("<span>"+(list.bAftermoney/10000).toFixed(2)+"</span>万元"):("<span>"+list.bAftermoney+"</span>元"))+`
                                    </p>
                                </div>
                            </div>
                        </a>`;
                    });
                }else{
                    if(listContent.innerHTML){
                        bottomTip.innerText = '没有更多数据了';
                    }else{
                        template+=`<div class="default" style="display:block;">
				    		<img class="pic" src="../mobileNew/img/mypic/data.png" alt="" />
				    		<p>暂时没有相关数据哦~</p>
				    	</div>`;
                    }
                }


                // 向ul容器中添加内容
                listContent.innerHTML += template;
                scrollFlag=0;
                scroll.refresh();
            }
        });
    }

    /*回款中初始化数据*/
    function initAjax2(){
        $.ajax({
            type:'GET',
            url:'/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do',
            data:{planstate:7,isApp:1,limit:8,start:p2},
            dataType:'json',
            success:function(data){
                p2++;
                var lists=data.data.fundPayList;
                var template='';
                if(lists.length){
                    $.each(lists,function(i,list){
                        template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.planId+`">
                                <div class="hkz"><div class="lend_con">
                                <div class="dt clearfix">
                                    `+(list.novice == 1?"<span class=\"new\">新</span>":"")+`
                                    <span class="sd">`+list.proKeepType+`</span>
                                    <p class="mark_name">` + list.projectName + `</p> 
                                     <a class="contract" href="` + basepath + list.url + `" target="_blank">查看合同</a>
                                </div>
                                <ul class="dc clearfix">
                                    <li>
                                        <p>
                                            `+(list.showRate?("<em>"+(list.interestRate-list.showRate)+"</em>%+<em>"+list.showRate+"</em>%"):("<em>"+list.interestRate+"</em>%"))+`
                                        </p>
                                        <span>预期年化利率</span>
                                    </li>
                                    <li>
                                        <p>`+list.loanLife+`</p>
                                        <span>期限</span>
                                    </li>
                                    <li>
                                        <p>
                                            `+(list.userMoney >= 10000?('<em class="cor_org">'+(list.userMoney/10000).toFixed(2)+"</em>万元"):('<em class="cor_org">'+list.userMoney+"</em>元"))+`
                                        </p>
                                        <span>出借金额</span>
                                    </li>
                                </ul>
                                <div class="db clearfix">
                                    <p class="lf">投标时间：<span>`+list.bidtime.substr(0,10)+`</span></p>
                                    <p class="rt"><a href='/webPhone/mobilePaymentplanWebBpCustAction.do?planId=`+list.planId+`&orderNo=`+list.orderNo+`'>回款计划</a></p>
                                </div>
                            </div></div>
                        </a>`;
                    });
                }else{
                    if(listContent.innerHTML){
                        bottomTip.innerText = '没有更多数据了';
                    }else{
                        template+=`<div class="default" style="display:block;">
				    		<img class="pic" src="../mobileNew/img/mypic/data.png" alt="" />
				    		<p>暂时没有相关数据哦~</p>
				    	</div>`;
                    }
                }

                // 向ul容器中添加内容
                listContent.innerHTML += template;
                scrollFlag=0;
                scroll.refresh();
            }
        });
    }

    /*已结清初始化数据*/
    function initAjax3(){
        $.ajax({
            type:'GET',
            // url:'http://localhost:8080/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do',
            url:'/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do',
            data:{planstate:10,isApp:1,limit:8,start:p3},
            dataType:'json',
            success:function(data){
                p3++;
                var lists=data.data.fundPayList;
                var template='';
                if(lists.length){
                    $.each(lists,function(i,list){
                        template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.planId+`">
                    <div class="yjq"><div class="lend_con">
                                <div class="dt clearfix">
                                    `+(list.novice == 1?"<span class=\"new\">新</span>":"")+`
                                    <span class="sd">`+list.proKeepType+`</span>
                                    <p class="mark_name">`+list.projectName+`</p>
                                    <a class="contract" href="` + basepath + list.url + `" target="_blank">查看合同</a>
                                </div>
                                <ul class="dc clearfix">
                                    <li>
                                        <p>
                                            `+(list.showRate?("<em>"+(list.interestRate-list.showRate)+"</em>%+<em>"+list.showRate+"</em>%"):("<em>"+list.interestRate+"</em>%"))+`
                                        </p>
                                        <span>预期年化利率</span>
                                    </li>
                                    <li>
                                        <p>`+list.loanLife+`</p>
                                        <span>期限</span>
                                    </li>
                                    <li>
                                        <p>
                                            `+(list.userMoney >= 10000?('<em class="cor_org">'+(list.userMoney/10000).toFixed(2)+"</em>万元"):('<em class="cor_org">'+list.userMoney+"</em>元"))+`
                                        </p>
                                        <span>出借金额</span>
                                    </li>
                                </ul>
                                <div class="db clearfix">
                                    <p class="lf">投标时间：<span>`+list.bidtime.substr(0,10)+`</span></p>
                                    <p class="rt">结清时间：<span>`+list.finishDate.substr(0,10)+`</span></p>
                                </div>
                            </div></div>
                         </a>`;
                    });
                }else{
                    if(listContent.innerHTML){
                        bottomTip.innerText = '没有更多数据了';
                    }else{
                        template+=`<div class="default" style="display:block;">
				    		<img class="pic" src="../mobileNew/img/mypic/data.png" alt="" />
				    		<p>暂时没有相关数据哦~</p>
				    	</div>`;
                    }
                }

                // 向ul容器中添加内容
                listContent.innerHTML+= template;
                scrollFlag=0;
                scroll.refresh();
            }
        });
    }

    /*投标失败初始化数据*/
    function initAjax4(){
        $.ajax({
            type:'GET',
            url:'/webPhone/mobileMyManageMoneyWebFinancePurchaseAction.do',
            data:{planstate:-1,isApp:1,limit:8,start:p4},
            dataType:'json',
            success:function(data){
                p4++;
                var lists=data.data.fundPayList;
                var template='';
                if(lists.length){
                    $.each(lists,function(i,list){
                        template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.planId+`">
                    <div class="sbbd"><div class="lend_con">
                                <div class="dt clearfix">
                                    `+(list.novice == 1?"<span class=\"new\">新</span>":"")+`
                                    <span class="sd">`+list.proKeepType+`</span>
                                    <p class="mark_name">`+list.projectName+`</p>
                                    <p class="pr">投标时间：<span>`+list.bidtime.substr(0,10)+`</span></p>
                                </div>
                                <ul class="dc clearfix">
                                    <li>
                                        <p>
                                            `+(list.showRate?("<em>"+(list.interestRate-list.showRate)+"</em>%+<em>"+list.showRate+"</em>%"):("<em>"+list.interestRate+"</em>%"))+`
                                        </p>
                                        <span>预期年化利率</span>
                                    </li>
                                    <li>
                                        <p>`+list.loanLife+`</p>
                                        <span>期限</span>
                                    </li>
                                    <li>
                                        <p>
                                            `+(list.userMoney >= 10000?('<em>'+(list.userMoney/10000).toFixed(2)+"</em>万元"):('<em>'+list.userMoney+"</em>元"))+`
                                        </p>
                                        <span>出借金额</span>
                                    </li>
                                </ul>
                            </div></div>
                        </a>`;
                    });
                }else{
                    if(listContent.innerHTML){
                        bottomTip.innerText = '没有更多数据了';
                    }else{
                        template+=`<div class="default" style="display:block;">
				    		<img class="pic" src="../mobileNew/img/mypic/data.png" alt="" />
				    		<p>暂时没有相关数据哦~</p>
				    	</div>`;
                    }

                }

                // 向ul容器中添加内容
                listContent.innerHTML+= template;
                scrollFlag=0;
                scroll.refresh();
            }
        });
        // 滑动中
        scroll.on('scroll', function (position) {
            scrollFlag=1;
            bottomTip.parentElement.style.display="block";
            if(position.y > 30) {

            }
        });
    }
    initAjax1();
    function initAjax(i){
        switch (i){
            case 1:initAjax1();break;
            case 2:initAjax2();break;
            case 3:initAjax3();break;
            case 4:initAjax4();break;
        }
    }
    $('.swiper1 .swiper-slide').on('click',function(){
        if(!$(this).hasClass('selected')){
            var i=$(this).index()+1;
            $(this).addClass('selected').siblings().removeClass('selected');
            listContent.innerHTML="";
            p1=1;p2=1;p3=1;p4=1;
            initAjax(i);
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
            bottomTip.innerText = '加载中...';
            setTimeout(function () {
                // 恢复文本值
                bottomTip.innerText = '查看更多';
                // 向列表添加数据
                if(scrollFlag==0){
                    /*reloadData();*/
                    var index;
                   $('.swiper1 .swiper-slide').each(function(i,dom){
                       if($(dom).hasClass('selected')){
                           index=i+1;
                       }
                   });
                    initAjax(index);
                }

                // 加载更多后,重新计算滚动区域高度
                scroll.refresh();
                scrollFlag=0;
            }, 1000);
        }
    });
}

initScroll();




// 刷新成功提示方法
function refreshAlert(text) {
    text = text || '操作成功';
    alert.innerHtml = text;
    alert.style.display = 'block';
    setTimeout(function(){
        alert.style.display = 'none';
    },1000);
}


