<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 出借查询</title>
    <link rel="stylesheet" type="text/css" href="${base}/mobileNew/css/FJL.picker.css" />
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <script src="${base}/mobileNew/js/FJL.min.js"></script>
    <script src="${base}/mobileNew/js/FJL.picker.min.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        出借查询
    </div>
</div>
<div class="lend_search">
    <div class="dt">
        <div class="pl">
            累计出借金额 <br/>
            <span id="money">35</span>万元
        </div>
        <div class="pr">
            累计出借次数<br/>
            <span id="num">30</span>次
        </div>
    </div>
    <div class="dc">
        <div class="lf">
            <p>起始日期</p>
            <span id='demo1' data-options='{"type":"date","beginYear":2000,"endYear":2020}' class="blue btn">2018-05-24</span>
        </div>
        <div class="rt">
            <p>结束日期</p>
            <span id='demo2' data-options='{"type":"date","beginYear":2000,"endYear":2020}' class="btn">选择日期</span>
        </div>
    </div>
    <div class="db">
        <div class="already_borrowed">
           <%-- <ul>
                <li><span class="userPic"></span><span>王小明</span><span class="more"></span><span>186****9227</span><span class="phonePic"></span></li>
                <li><span>融资租赁001</span><span>2000</span><span>2018-4-26</span></li>
                <li><span>项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
            </ul>
            <ul>
                <li><span class="userPic"></span><span>王小明</span><span class="more"></span><span>186****9227</span><span class="phonePic"></span></li>
                <li><span>融资租赁001</span><span>2000</span><span>2018-4-26</span></li>
                <li><span>项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
            </ul>--%>
        </div>
        <div class="no_loan">
            <ul>
                <li><span class="no_real_name"><i></i>未实名</span><span>186****9227</span><span>2018-4-26</span></li>
                <li><span>姓名</span><span>手机号</span><span>注册时间</span></li>
            </ul>
        </div>
    </div>
</div>
<script>
    function showContent(val1,val2){
        //调接口将两个值传人成功后，内容显示
        $.ajax({
            type:'GET',
            url:"/webPhone/selectInviteWebPhoneCustMember.do",
            data:{"starDate":val1,"endDate":val2,"isApp":1},
            dataType:'json',
            success:function(data){
                $('#money').html(data.data.sumMoney);
                $('#num').html(data.data.countNum);
                var lists=data.data.list;
                var flagment='';
                $.each(lists,function(i,list){
                    flagment+=`<ul>
                <li><span class="userPic"></span><span>`+list.truename+`</span><span class="more"></span><span>`+(list.telphone.substr(0,3)+'****'+list.telphone.substr(7))+`</span><span class="phonePic"></span></li>
                <li><span class="bidname">`+list.bidName+`</span><span>`+list.userMoney+`</span><span>`+list.bidtime+`</span></li>
                <li><span class="bidname">项目名称</span><span>出借金额（元）</span><span>出借时间</span></li>
            </ul>`;
                });
                $('.lend_search .db .already_borrowed').html(flagment);
            }
        });
        $('.lend_search .dt').show();
        $('.lend_search .db').show();
    }
    (function($) {
        $.init();
        //var result = $('#demo2')[0];
        var btns = $('.btn');
        var demo1=document.getElementById('demo1');
        var deom2=document.getElementById('demo2');
        btns.each(function(i, btn) {
            btn.addEventListener('tap', function() {
                var optionsJson = this.getAttribute('data-options') || '{}';
                var options = JSON.parse(optionsJson);
                var id = this.getAttribute('id');
                /*
                 * 首次显示时实例化组件
                 * 示例为了简洁，将 options 放在了按钮的 dom 上
                 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
                 */
                var picker = new $.DtPicker(options);
                picker.show(function(rs) {
                    /*
                     * rs.value 拼合后的 value
                     * rs.text 拼合后的 text
                     * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
                     * rs.m 月，用法同年
                     * rs.d 日，用法同年
                     * rs.h 时，用法同年
                     * rs.i 分（minutes 的第二个字母），用法同年
                     */
                    //result.innerText = rs.text;
                    btn.innerText = rs.text;
                    btn.setAttribute("class","blue btn")
                    if(demo1.innerHTML!="选择日期"&&demo2.innerHTML!="选择日期"){
                        /*搜索结果展示*/
                        showContent(demo1.innerHTML,demo2.innerHTML);
                    }
                    /*
                     * 返回 false 可以阻止选择框的关闭
                     * return false;
                     */
                    /*
                     * 释放组件资源，释放后将将不能再操作组件
                     * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
                     * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
                     * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
                     */
                    picker.dispose();
                });
            }, false);
        });
    })(mui);
</script>
</body>
</html>