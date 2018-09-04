<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>${systemConfig.metaTitle} - 联系我们</title>

    <meta name="description" content="${systemConfig.metaTitle} - 联系我们,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 联系我们,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
    <script type="text/javascript" src="${base}/js/user/repaymentrecords.js"></script>
    <script type="text/javascript" src="${base}/js/Calendar3.js"></script>
    <script type="text/javascript">var m1="关于我们",m2="",m3="联系我们";</script>


    <script type="text/javascript" src="https://api.map.baidu.com/api?key=tqbwrQq9P5iYWUsOKB8iGqlP4a3XL8qV&v=1.1&services=true&s=1"></script>
    <script type="text/javascript">
        $(function(){
        //创建和初始化地图函数：
        function initMap(){
            createMap();//创建地图
            setMapEvent();//设置地图事件
            addMapControl();//向地图添加控件
            addMarker();//向地图中添加marker
        }

        //创建地图函数：
        function createMap(){
            var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
            var point = new BMap.Point(116.466853,39.902411);//定义一个中心点坐标
            map.centerAndZoom(point,18);//设定地图的中心点和坐标并将地图显示在地图容器中
            window.map = map;//将map变量存储在全局
        }

        //地图事件设置函数：
        function setMapEvent(){
            map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
            map.enableScrollWheelZoom();//启用地图滚轮放大缩小
            map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
            map.enableKeyboard();//启用键盘上下左右键移动地图
        }

        //地图控件添加函数：
        function addMapControl(){
            //向地图中添加缩放控件
            var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
            map.addControl(ctrl_nav);
            //向地图中添加缩略图控件
            var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:0});
            map.addControl(ctrl_ove);
            //向地图中添加比例尺控件
            var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
            map.addControl(ctrl_sca);
        }

        //标注点数组
        var markerArr = [{title:"中发展信（北京）投资管理有限公司",content:"富力双子座A层27层中发展信",point:"116.466893|39.902376",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}
        ];
        //创建marker
        function addMarker(){
            for(var i=0;i<markerArr.length;i++){
                var json = markerArr[i];
                var p0 = json.point.split("|")[0];
                var p1 = json.point.split("|")[1];
                var point = new BMap.Point(p0,p1);
                var iconImg = createIcon(json.icon);
                var marker = new BMap.Marker(point,{icon:iconImg});
                var iw = createInfoWindow(i);
                var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
                marker.setLabel(label);
                map.addOverlay(marker);
                label.setStyle({
                    borderColor:"#808080",
                    color:"#333",
                    cursor:"pointer"
                });

                (function(){
                    var index = i;
                    var _iw = createInfoWindow(i);
                    var _marker = marker;
                    _marker.addEventListener("click",function(){
                        this.openInfoWindow(_iw);
                    });
                    _iw.addEventListener("open",function(){
                        _marker.getLabel().hide();
                    })
                    _iw.addEventListener("close",function(){
                        _marker.getLabel().show();
                    })
                    label.addEventListener("click",function(){
                        _marker.openInfoWindow(_iw);
                    })
                    if(!!json.isOpen){
                        label.hide();
                        _marker.openInfoWindow(_iw);
                    }
                })()
            }
        }
        //创建InfoWindow
        function createInfoWindow(i){
            var json = markerArr[i];
            var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
            return iw;
        }
        //创建一个Icon
        function createIcon(json){
            var icon = new BMap.Icon("https://api.map.baidu.com/lbsapi/creatmap/images/us_mk_icon.png&s=1", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
            return icon;
        }

        initMap();//创建和初始化地图
        });
    </script>
</head>
<style>
    .article-content {
        font-size: 14px;
        color: #6c6c6c;
        line-height: 40px;
        text-indent: 28px;
    }
    .sr-bdimgshare .bdimgshare-bg {
        position: absolute;
        width: 100%;
        height: 100%;
        overflow: hidden;
        filter: alpha(opacity=40);
        opacity: .4;
    }

    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}

</style>
<body >
<!-- header --><!-- navbar -->
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
 <#include "/WEB-INF/template/${systemConfig.theme}/layout/subnavbar.ftl">
<div class="main">
    <!-- 页面分为左右两个部分   -->
    <!-- 左侧页面 start，左侧页面用于盛放三级导航条，宽260px，并带有竖线分隔线 start -->
    <div class="abouts-nav fl">
        <!-- 以下内容为左侧三级导航条 -->
			<#include "/WEB-INF/template/${systemConfig.theme}/leftsidebar/leftsidebar_aboutus.ftl">
        <!-- 左侧三级导航结束 end-->
    </div>
    <!-- 左侧页面 end -->
    <div class="abouts-cont fr" <#--style="width:851px;"-->>
        <div class="abouts-border" style="height:800px;min-height: 800px;">
            <#--<div class="titie_h1">
                <span class="left_pic"></span><h1>联系我们</h1><span class="right_pic"></span>
            </div>-->
                <div class="company_title">
                    <span class="company_title_span"></span>
                    <span  class="company_title_JJ">联系我们</span><#--9265-->
                </div>
            <div class="profile" style="height:auto">
                <div  style="width:680px;height:350px;border:#ccc solid 1px;margin: 0 auto;" id="dituContent"></div>
                <div   style="width:680px;height:350px;margin: 60px auto;">
                    <ul style="width: 100%;">
                        <li style="width: 100%;text-align: center;font-size: 20px;color:#333333" >中发展信(北京)投资管理有限公司</li>
                        <li style="margin-left: 65px;font-size: 16px;color:#3333333;margin-top: 40px;"><i class="Phone"></i><span style="color:#808080">客服热线:</span>&nbsp;400-9266-114</li>
                        <li style="margin-left: 65px;font-size: 16px;color:#3333333;margin-top:20px;"><i class="Time"></i><span style="color:#808080">服务时间:</span>&nbsp;周一到周日&nbsp;9:00-21:00</li>
                        <li style="margin-left: 65px;font-size: 16px;color:#3333333;margin-top: 20px"><i class="Address"></i><span style="color:#808080">公司地址:</span>&nbsp;北京市朝阳区东三环中路59号富力双子座A座</li>
                        <li style="margin-left: 65px;font-size: 16px;color:#3333333;margin-top: 20px"><i class="Prompt"></i><span style="color:#808080">温馨提示:</span>&nbsp;如果您在使用升升投的过程中有任何疑问,&nbsp;请您与升升投客服人员联系。</li>
                        <li style="margin-left: 65px;font-size: 16px;color:#3333333;margin-top: 20px"><i class="Email"></i><span style="color:#808080">商务邮箱:</span>&nbsp;suxuan@zxzbol.com</li>
                    </ul>
                </div>
            </div>
        <#-- <div class="article">
         <span class="article-content">
             <p class="MsoNormal" align="left" style="background-color:white;">
                 <span style="color:#000000;font-size:14px;"><span style="color:#FF9900;font-family:'Microsoft YaHei';font-size:16px;"><strong></strong></span></span>
             </p>
         &lt;#&ndash;<p style="color:#6C6C6C;font-family:微软雅黑, 黑体, sans-serif;font-size:14px;text-indent:21pt;background-color:white;">
             <span style="font-size:32px;font-family:SimHei;color:#FF9900;">C</span><span style="color:#FF9900;font-family:'Microsoft YaHei';font-size:16px;line-height:24px;">ontact us</span><span style="color:#FF9900;font-size:18px;line-height:1.5;">联系我们</span>
         </p>&ndash;&gt;
             <p style="color:#6C6C6C;font-family:微软雅黑, 黑体, sans-serif;font-size:14px;text-indent:21pt;background-color:white;">
                 <span style="color:#FF9900;font-size:24px;"> </span>
             </p>

             <p>
                 <strong><span style="font-weight:normal;color:#FF9900;font-family:'Microsoft YaHei';font-size:16px;"><strong>集团公司总部及“升升投”运营中心</strong></span><span style="font-weight:normal;line-height:1.5;color:#999999;font-family:'Microsoft YaHei';"></span></strong>
             </p>
             <p>
                 <span style="color:#333333;font-size:14px;font-family:'Microsoft YaHei';">联系方式：</span><span style="color:#333333;font-size:14px;font-family:'Microsoft YaHei';">010-5714-4441</span>
             </p>
             <p class="MsoNormal" align="left" style="background-color:white;">
                 <span style="color:#333333;font-size:14px;font-family:'Microsoft YaHei';"><span style="color:#333333;font-size:14px;font-family:'Microsoft YaHei';">邮箱</span><span style="color:#333333;font-size:14px;font-family:'Microsoft YaHei';">：2931407238@qq.com</span></span>
             </p>
             <p>
                 <span style="font-size:14px;font-family:'Microsoft YaHei';"><br></span>
             </p>
             <p>
                 <br>
             </p>
             <p class="MsoNormal" align="left">
                 <br>
             </p>
         </span>
         </div>-->

        </div>
    </div>
</div>
<!--end: Container -->
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
<script>
/*    $(".about-navbar").css("height","1200px");*/
</script>

</html>