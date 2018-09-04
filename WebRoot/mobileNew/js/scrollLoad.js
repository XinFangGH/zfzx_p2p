
var listWrapper = document.querySelector('.list-wrapper-hook'),
    listContent = document.querySelector('.list-content-hook'),
    /*alert = document.querySelector('.alert-hook'),*/
    topTip = document.querySelector('.refresh-hook'),
    bottomTip = document.querySelector('.loading-hook');
var flag=false;
var url='/webPhone/planListhomePage.do';
var reqData={page:1,limit:10,isApp:1};
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
    $("#slide_into").on('keypress',function(e) {
        var keycode = e.keyCode;
        var searchName = $(this).val();
        if(keycode=='13') {
            e.preventDefault();
            document.activeElement.blur();
            //请求搜索接口
            url="/webPhone/checkPlanListlist.do";
            reqData["bidProName"]=$(this).val();
            initAjax();

        }
    });
    function initAjax(){
        bottomTip.innerText = '查看更多'
        reqData["page"]=1;
        $.ajax({
            type:'GET',
            url:url,
            data:reqData,
            dataType:'json',
            success:function(data){
                var lists=data.data.indexPlanList;
                if(lists.length>0){
                    var template='';
                    $.each(lists,function(i,list){
                        template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.bidId+`">
                                <div class="financing `+(list.state == 10||list.state==3||list.state==4?'yhk ':" ")+(list.state==2?'ymb':(list.state==3?"ylb":(list.state==4?"ygq":"")))+`">
                                    <div class="financingTitle">
                                        `+(list.novice != 0?'<span class="newMark">新</span>':"")+`
                                        <span class="newStyle">${list.proKeepType.substring(0,2)}</span>
                                        <p class="markTitle">`+list.bidProName+`</p>
                                        `+(list.showRate != null?'<span class="markBg">加息</span>':"")+`
                                    </div>
                                    <div  class="financingSubject">
                                        <ul>
                                            <li class="changeLi">
                                                <div class="redRate" style="text-align: left">
                                                    <span>`+(list.showRate != null?((list.yearInterestRate-list.showRate).toFixed(1)):list.yearInterestRate)+`</span>
                                                    `+(list.showRate != null?'<span>+'+list.showRate+'</span>':"")+`
                                                    <span>%</span>
                                                </div>
                                                <p  style="text-align: left">预期年化利率</p>
                                            </li>
                                            <li>
                                                <div><span>
                                                `+(list.raiseRate != null?list.raiseRate+'%':'0.0%')+`
                                                </span></div>
                                                <p>募集期利率</p>
                                            </li>
                                            <li>
                                                <div  style="text-align: right"><span>`+list.startMoney+`元</span></div>
                                                <p  style="text-align: right">起投金额</p>
                                            </li>
                                        </ul>
                                        <div class="progressRate">
                                            <div class="progressBar">
                                                <span style="width:`+list.progress+`%"></span>
                                            </div>
                                            <p>`+list.progress+`%</p>
                                        </div>
                                        <div class="progressBottom">
                                            <span class="lf">`+list.theWayBack+`</span>
                                            <span>剩余
                                <p>
                                `+(list.afterMoney >= 10000?((list.afterMoney/10000).toFixed(2)+"<span>万元</span>"):(list.afterMoney+"<span>元</span>"))+`
                                </p>
                            </span>
                                        </div>
                                        `+(list.state == 7||list.state == 10||list.state == 2||list.state == 3||list.state == 4?'<span class="Seal"></span>':"")+`
                                    </div>
                                </div>
                            </a>`;
                    });
                    // 向ul容器中添加内容
                    listContent.innerHTML = template;
                    scrollFlag=0;
                    reqData["page"]=1;
                    scroll.refresh();
                    bottomTip.parentElement.style.display="none";
                }else{
                    if(url=="/webPhone/checkPlanListlist.do"){
                        $('.tit').html("您搜索的标的不存在").show();
                        var t =setTimeout(function () {
                            $(".tit").hide();
                            clearTimeout(t);
                        },3000);
                    }
                }

            }
        });
    }
    initAjax()

  // 滑动中
  scroll.on('scroll', function (position) {
      scrollFlag=1;
      bottomTip.parentElement.style.display="block";
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
      
      setTimeout(function () {
        /*
         * 这里发送ajax刷新数据
         * 刷新后,后台只返回第1页的数据,无论用户是否已经上拉加载了更多
        */
          initAjax();
        // 恢复文本值
        topTip.innerText = '下拉刷新';
        // 刷新成功后的提示

          /*refreshAlert('刷新成功');*/



        // 刷新列表后,重新计算滚动区域高度
        scroll.refresh();

      }, 1000);
    }else if(position.y < (this.maxScrollY - 30)) {
        if(bottomTip.innerText == '已无更多数据'){
            return;
        }
      bottomTip.innerText = '加载中...';
      setTimeout(function () {
        // 恢复文本值 
        bottomTip.innerText = '查看更多';
        // 向列表添加数据
          if(scrollFlag==0){
              /*reloadData();*/
              reqData["page"]++;
              $.ajax({
                  type:'GET',
                  url:url,
                  data:reqData,
                  dataType:'json',
                  success:function(data){
                      var lists=data.data.indexPlanList;
                      if(lists.length>0){
                          var template='';
                          $.each(lists,function(i,list){
                              template+=`<a href="/webPhone/mobilePlanDetailWebBidPlanAction.do?bidId=`+list.bidId+`">
                                <div class="financing `+(list.state == 10||list.state==3||list.state==4?'yhk ':" ")+(list.state==2?'ymb':(list.state==3?"ylb":(list.state==4?"ygq":"")))+`">
                                    <div class="financingTitle">
                                        `+(list.novice != 0?'<span class="newMark">新</span>':"")+`
                                        <span class="newStyle">${list.proKeepType.substring(0,2)}</span>
                                        <p class="markTitle">`+list.bidProName+`</p>
                                        `+(list.showRate != null?'<span class="markBg">加息</span>':"")+`
                                    </div>
                                    <div  class="financingSubject">
                                        <ul>
                                            <li class="changeLi">
                                                <div class="redRate" style="text-align: left">
                                                    <span>`+(list.showRate != null?((list.yearInterestRate-list.showRate).toFixed(1)):list.yearInterestRate)+`</span>
                                                    `+(list.showRate != null?'<span>+'+list.showRate+'</span>':"")+`
                                                    <span>%</span>
                                                </div>
                                                <p  style="text-align: left">预期年化利率</p>
                                            </li>
                                            <li>
                                                <div><span>
                                                `+(list.raiseRate != null?list.raiseRate+'%':'0.0%')+`
                                                </span></div>
                                                <p>募集期利率</p>
                                            </li>
                                            <li>
                                                <div  style="text-align: right"><span>`+list.startMoney+`元</span></div>
                                                <p  style="text-align: right">起投金额</p>
                                            </li>
                                            
                                        </ul>
                                        <div class="progressRate">
                                            <div class="progressBar">
                                                <span style="width:`+list.progress+`%"></span>
                                            </div>
                                            <p>`+list.progress+`%</p>
                                        </div>
                                        <div class="progressBottom">
                                            <span class="lf">`+list.theWayBack+`</span>
                                            <span>剩余
                                <p>
                                `+(list.afterMoney >= 10000?((list.afterMoney/10000).toFixed(2)+"万元"):(list.afterMoney+"元"))+`
                                </p>
                            </span>
                                        </div>
                                        `+(list.state == 7||list.state == 10||list.state == 2||list.state == 3||list.state == 4?'<span class="Seal"></span>':"")+`
                                    </div>
                                </div>
                            </a>`;
                          });
                          // 向ul容器中添加内容
                          listContent.innerHTML = listContent.innerHTML + template;
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





// 刷新成功提示方法
function refreshAlert(text) {
  text = text || '操作成功';
  alert.innerHtml = text;
  alert.style.display = 'block';
  setTimeout(function(){
    alert.style.display = 'none';
  },1000);
}


