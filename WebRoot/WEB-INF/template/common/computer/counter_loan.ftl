<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 
<title>${systemConfig.metaTitle} - 车贷计算器</title>
    <meta name="description" content="${systemConfig.metaTitle} - 车贷计算器,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
    <meta name="keywords" content="${systemConfig.metaTitle} - 车贷计算器,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript">var m1="",m2="",m3="",m4="";</script>
<link rel="stylesheet" type="text/css" href="${base}/js/computer/yiche_2014.css" media="all"> 
<script type="text/javascript" src="${base}/js/computer/dropdownlistnew.min.js"></script>
<script type="text/javascript" src="${base}/js/computer/CarCalculator.js"></script>
<script type="text/javascript" src="${base}/js/computer/carBase.js"></script>
<style type="text/css">
    #popBox { background: #000; position: absolute; z-index: 999; top: 0; left: 0; filter: alpha(opacity=30); opacity: 0.3; -moz-opacity: 0.3; }
    
    .iframe { width: 100%; border: none; position: absolute; z-index: 1; height: 100%; filter: alpha(opacity = 0); }
    
    .fixed-top { position: fixed; top: 0; z-index: 9999; }
    
.position{ padding:20px 0px 14px; color:#000; line-height:24px; text-align:left;  font-size:14px; width: 1000px; margin: 0 auto;}
</style>
</head>
<body onload="ResetToDefault();">
<#include "/WEB-INF/template/${systemConfig.theme}/registerCenter/layout/topall.ftl">
    <span id="yicheAnchor" name="yicheAnchor" style="display: block; height: 0; width: 0;
        line-height: 0; font-size: 0"></span>
    <div class="bt_pageBox"> 
</div>

    <div class="bt_page">
    <div class="position">您当前所在的位置： <a href="${base}">紫薇金融</a> &gt; 工具箱 &gt; 车贷计算器</div>
        <div class="line-box" style="margin:0px auto 0;">
            <div class="title-con">
                <input name="hidBsID" type="hidden" id="hidBsID" value="-1">
                <input name="hidCsID" type="hidden" id="hidCsID" value="-1">
                <input name="hidCarID" type="hidden" id="hidCarID" value="-1">
                <input name="hidSeatNum" type="hidden" id="hidSeatNum" value="7">
                <input name="hidCarPrice" type="hidden" id="hidCarPrice" value="0">
                <!-- <div class="title-box title-box2">
                    <h4>选择车款</h4>
                    <div class="tableHead_item">
                        <div class="jsq-tit-fbox">
                            <div id="master4" class="sel-item-box" style="z-index: 50; width: 150px;"><span class="sel-item"><span value="172" style="color: black;">巴博斯</span><em></em></span><div class="popup-box" style="display: none;"><div class="select-list"><a href="javascript:;" class="" bita-value="9" bita-text="奥迪"><em>A</em> 奥迪</a><a href="javascript:;" class="" bita-value="97" bita-text="阿斯顿·马丁"><em>A</em> 阿斯顿·马丁</a><a href="javascript:;" class="" bita-value="92" bita-text="阿尔法·罗密欧"><em>A</em> 阿尔法·罗密欧</a><a href="javascript:;" class="" bita-value="180" bita-text="AC Schnitzer"><em>A</em> AC Schnitzer</a><a href="javascript:;" class="" bita-value="228" bita-text="Artega"><em>A</em> Artega</a><a href="javascript:;" class="bg-blue" bita-value="26" bita-text="本田"><em>B</em> 本田</a><a href="javascript:;" class="bg-blue" bita-value="3" bita-text="宝马"><em>B</em> 宝马</a><a href="javascript:;" class="bg-blue" bita-value="2" bita-text="奔驰"><em>B</em> 奔驰</a><a href="javascript:;" class="bg-blue" bita-value="127" bita-text="别克"><em>B</em> 别克</a><a href="javascript:;" class="bg-blue" bita-value="15" bita-text="比亚迪"><em>B</em> 比亚迪</a><a href="javascript:;" class="bg-blue" bita-value="5" bita-text="标致"><em>B</em> 标致</a><a href="javascript:;" class="bg-blue" bita-value="82" bita-text="保时捷"><em>B</em> 保时捷</a><a href="javascript:;" class="bg-blue" bita-value="59" bita-text="奔腾"><em>B</em> 奔腾</a><a href="javascript:;" class="bg-blue" bita-value="211" bita-text="北汽幻速"><em>B</em> 北汽幻速</a><a href="javascript:;" class="bg-blue" bita-value="157" bita-text="宝骏"><em>B</em> 宝骏</a><a href="javascript:;" class="bg-blue" bita-value="195" bita-text="北汽绅宝"><em>B</em> 北汽绅宝</a><a href="javascript:;" class="bg-blue" bita-value="163" bita-text="北京"><em>B</em> 北京</a><a href="javascript:;" class="bg-blue" bita-value="14" bita-text="北汽制造"><em>B</em> 北汽制造</a><a href="javascript:;" class="bg-blue" bita-value="168" bita-text="北汽威旺"><em>B</em> 北汽威旺</a><a href="javascript:;" class="bg-blue" bita-value="85" bita-text="宾利"><em>B</em> 宾利</a><a href="javascript:;" class="bg-blue" bita-value="135" bita-text="布加迪"><em>B</em> 布加迪</a><a href="javascript:;" class="bg-blue" bita-value="172" bita-text="巴博斯"><em>B</em> 巴博斯</a><a href="javascript:;" class="bg-blue" bita-value="216" bita-text="北汽新能源"><em>B</em> 北汽新能源</a><a href="javascript:;" class="bg-blue" bita-value="184" bita-text="保斐利"><em>B</em> 保斐利</a><a href="javascript:;" class="bg-blue" bita-value="236" bita-text="宝沃"><em>B</em> 宝沃</a><a href="javascript:;" class="" bita-value="136" bita-text="长安轿车"><em>C</em> 长安轿车</a><a href="javascript:;" class="" bita-value="21" bita-text="长城"><em>C</em> 长城</a><a href="javascript:;" class="" bita-value="159" bita-text="长安商用"><em>C</em> 长安商用</a><a href="javascript:;" class="" bita-value="129" bita-text="昌河"><em>C</em> 昌河</a><a href="javascript:;" class="" bita-value="230" bita-text="长安跨越"><em>C</em> 长安跨越</a><a href="javascript:;" class="" bita-value="231" bita-text="长城华冠"><em>C</em> 长城华冠</a><a href="javascript:;" class="" bita-value="221" bita-text="成功"><em>C</em> 成功</a><a href="javascript:;" class="bg-blue" bita-value="8" bita-text="大众"><em>D</em> 大众</a><a href="javascript:;" class="bg-blue" bita-value="115" bita-text="东风风行"><em>D</em> 东风风行</a><a href="javascript:;" class="bg-blue" bita-value="141" bita-text="东风风神"><em>D</em> 东风风神</a><a href="javascript:;" class="bg-blue" bita-value="205" bita-text="东风小康"><em>D</em> 东风小康</a><a href="javascript:;" class="bg-blue" bita-value="29" bita-text="东南"><em>D</em> 东南</a><a href="javascript:;" class="bg-blue" bita-value="179" bita-text="DS"><em>D</em> DS</a><a href="javascript:;" class="bg-blue" bita-value="113" bita-text="道奇"><em>D</em> 道奇</a><a href="javascript:;" class="bg-blue" bita-value="235" bita-text="东风·郑州日产"><em>D</em> 东风·郑州日产</a><a href="javascript:;" class="bg-blue" bita-value="197" bita-text="东风风度"><em>D</em> 东风风度</a><a href="javascript:;" class="bg-blue" bita-value="215" bita-text="东风御风"><em>D</em> 东风御风</a><a href="javascript:;" class="" bita-value="7" bita-text="丰田"><em>F</em> 丰田</a><a href="javascript:;" class="" bita-value="17" bita-text="福特"><em>F</em> 福特</a><a href="javascript:;" class="" bita-value="128" bita-text="福田"><em>F</em> 福田</a><a href="javascript:;" class="" bita-value="40" bita-text="菲亚特"><em>F</em> 菲亚特</a><a href="javascript:;" class="" bita-value="91" bita-text="法拉利"><em>F</em> 法拉利</a><a href="javascript:;" class="" bita-value="67" bita-text="福迪"><em>F</em> 福迪</a><a href="javascript:;" class="" bita-value="199" bita-text="飞驰商务车"><em>F</em> 飞驰商务车</a><a href="javascript:;" class="" bita-value="208" bita-text="福汽启腾"><em>F</em> 福汽启腾</a><a href="javascript:;" class="" bita-value="187" bita-text="Faralli Mazzanti"><em>F</em> Faralli Mazzanti</a><a href="javascript:;" class="bg-blue" bita-value="147" bita-text="广汽"><em>G</em> 广汽</a><a href="javascript:;" class="bg-blue" bita-value="63" bita-text="广汽吉奥"><em>G</em> 广汽吉奥</a><a href="javascript:;" class="bg-blue" bita-value="182" bita-text="观致汽车"><em>G</em> 观致汽车</a><a href="javascript:;" class="bg-blue" bita-value="109" bita-text="GMC"><em>G</em> GMC</a><a href="javascript:;" class="bg-blue" bita-value="110" bita-text="光冈"><em>G</em> 光冈</a><a href="javascript:;" class="bg-blue" bita-value="237" bita-text="GTA"><em>G</em> GTA</a><a href="javascript:;" class="bg-blue" bita-value="133" bita-text="广汽日野"><em>G</em> 广汽日野</a><a href="javascript:;" class="" bita-value="196" bita-text="哈弗"><em>H</em> 哈弗</a><a href="javascript:;" class="" bita-value="32" bita-text="海马"><em>H</em> 海马</a><a href="javascript:;" class="" bita-value="112" bita-text="华泰"><em>H</em> 华泰</a><a href="javascript:;" class="" bita-value="58" bita-text="红旗"><em>H</em> 红旗</a><a href="javascript:;" class="" bita-value="52" bita-text="黄海"><em>H</em> 黄海</a><a href="javascript:;" class="" bita-value="31" bita-text="哈飞"><em>H</em> 哈飞</a><a href="javascript:;" class="" bita-value="149" bita-text="海马商用车"><em>H</em> 海马商用车</a><a href="javascript:;" class="" bita-value="225" bita-text="华颂"><em>H</em> 华颂</a><a href="javascript:;" class="" bita-value="181" bita-text="恒天汽车"><em>H</em> 恒天汽车</a><a href="javascript:;" class="" bita-value="170" bita-text="海格"><em>H</em> 海格</a><a href="javascript:;" class="" bita-value="45" bita-text="汇众"><em>H</em> 汇众</a><a href="javascript:;" class="bg-blue" bita-value="34" bita-text="吉利汽车"><em>J</em> 吉利汽车</a><a href="javascript:;" class="bg-blue" bita-value="35" bita-text="江淮"><em>J</em> 江淮</a><a href="javascript:;" class="bg-blue" bita-value="4" bita-text="Jeep"><em>J</em> Jeep</a><a href="javascript:;" class="bg-blue" bita-value="39" bita-text="金杯"><em>J</em> 金杯</a><a href="javascript:;" class="bg-blue" bita-value="98" bita-text="捷豹"><em>J</em> 捷豹</a><a href="javascript:;" class="bg-blue" bita-value="37" bita-text="江铃"><em>J</em> 江铃</a><a href="javascript:;" class="bg-blue" bita-value="152" bita-text="九龙"><em>J</em> 九龙</a><a href="javascript:;" class="bg-blue" bita-value="38" bita-text="江南"><em>J</em> 江南</a><a href="javascript:;" class="bg-blue" bita-value="57" bita-text="厦门金龙"><em>J</em> 厦门金龙</a><a href="javascript:;" class="bg-blue" bita-value="161" bita-text="金旅客车"><em>J</em> 金旅客车</a><a href="javascript:;" class="bg-blue" bita-value="224" bita-text="江铃集团轻汽"><em>J</em> 江铃集团轻汽</a><a href="javascript:;" class="" bita-value="107" bita-text="凯迪拉克"><em>K</em> 凯迪拉克</a><a href="javascript:;" class="" bita-value="150" bita-text="开瑞"><em>K</em> 开瑞</a><a href="javascript:;" class="" bita-value="220" bita-text="凯翼"><em>K</em> 凯翼</a><a href="javascript:;" class="" bita-value="51" bita-text="克莱斯勒"><em>K</em> 克莱斯勒</a><a href="javascript:;" class="" bita-value="213" bita-text="卡威"><em>K</em> 卡威</a><a href="javascript:;" class="" bita-value="218" bita-text="科瑞斯的"><em>K</em> 科瑞斯的</a><a href="javascript:;" class="" bita-value="145" bita-text="科尼赛克"><em>K</em> 科尼赛克</a><a href="javascript:;" class="" bita-value="212" bita-text="KTM"><em>K</em> KTM</a><a href="javascript:;" class="" bita-value="188" bita-text="卡尔森"><em>K</em> 卡尔森</a><a href="javascript:;" class="bg-blue" bita-value="16" bita-text="铃木"><em>L</em> 铃木</a><a href="javascript:;" class="bg-blue" bita-value="96" bita-text="路虎"><em>L</em> 路虎</a><a href="javascript:;" class="bg-blue" bita-value="94" bita-text="雷克萨斯"><em>L</em> 雷克萨斯</a><a href="javascript:;" class="bg-blue" bita-value="36" bita-text="陆风"><em>L</em> 陆风</a><a href="javascript:;" class="bg-blue" bita-value="76" bita-text="力帆"><em>L</em> 力帆</a><a href="javascript:;" class="bg-blue" bita-value="86" bita-text="兰博基尼"><em>L</em> 兰博基尼</a><a href="javascript:;" class="bg-blue" bita-value="95" bita-text="林肯"><em>L</em> 林肯</a><a href="javascript:;" class="bg-blue" bita-value="153" bita-text="猎豹汽车"><em>L</em> 猎豹汽车</a><a href="javascript:;" class="bg-blue" bita-value="99" bita-text="雷诺"><em>L</em> 雷诺</a><a href="javascript:;" class="bg-blue" bita-value="80" bita-text="劳斯莱斯"><em>L</em> 劳斯莱斯</a><a href="javascript:;" class="bg-blue" bita-value="146" bita-text="莲花"><em>L</em> 莲花</a><a href="javascript:;" class="bg-blue" bita-value="166" bita-text="理念"><em>L</em> 理念</a><a href="javascript:;" class="bg-blue" bita-value="83" bita-text="路特斯"><em>L</em> 路特斯</a><a href="javascript:;" class="bg-blue" bita-value="229" bita-text="雷丁电动"><em>L</em> 雷丁电动</a><a href="javascript:;" class="bg-blue" bita-value="200" bita-text="蓝海房车"><em>L</em> 蓝海房车</a><a href="javascript:;" class="bg-blue" bita-value="227" bita-text="朗世"><em>L</em> 朗世</a><a href="javascript:;" class="" bita-value="18" bita-text="马自达"><em>M</em> 马自达</a><a href="javascript:;" class="" bita-value="79" bita-text="MG"><em>M</em> MG</a><a href="javascript:;" class="" bita-value="93" bita-text="玛莎拉蒂"><em>M</em> 玛莎拉蒂</a><a href="javascript:;" class="" bita-value="81" bita-text="MINI"><em>M</em> MINI</a><a href="javascript:;" class="" bita-value="183" bita-text="迈凯伦"><em>M</em> 迈凯伦</a><a href="javascript:;" class="" bita-value="201" bita-text="摩根"><em>M</em> 摩根</a><a href="javascript:;" class="" bita-value="55" bita-text="美亚"><em>M</em> 美亚</a><a href="javascript:;" class="bg-blue" bita-value="155" bita-text="纳智捷"><em>N</em> 纳智捷</a><a href="javascript:;" class="bg-blue" bita-value="234" bita-text="Noble"><em>N</em> Noble</a><a href="javascript:;" class="" bita-value="84" bita-text="讴歌"><em>O</em> 讴歌</a><a href="javascript:;" class="" bita-value="104" bita-text="欧宝"><em>O</em> 欧宝</a><a href="javascript:;" class="" bita-value="171" bita-text="欧朗"><em>O</em> 欧朗</a><a href="javascript:;" class="bg-blue" bita-value="185" bita-text="帕加尼"><em>P</em> 帕加尼</a><a href="javascript:;" class="bg-blue" bita-value="191" bita-text="PGO"><em>P</em> PGO</a><a href="javascript:;" class="" bita-value="28" bita-text="起亚"><em>Q</em> 起亚</a><a href="javascript:;" class="" bita-value="42" bita-text="奇瑞"><em>Q</em> 奇瑞</a><a href="javascript:;" class="" bita-value="156" bita-text="启辰"><em>Q</em> 启辰</a><a href="javascript:;" class="" bita-value="43" bita-text="庆铃"><em>Q</em> 庆铃</a><a href="javascript:;" class="" bita-value="226" bita-text="乔治·巴顿"><em>Q</em> 乔治·巴顿</a><a href="javascript:;" class="bg-blue" bita-value="30" bita-text="日产"><em>R</em> 日产</a><a href="javascript:;" class="bg-blue" bita-value="78" bita-text="荣威"><em>R</em> 荣威</a><a href="javascript:;" class="bg-blue" bita-value="142" bita-text="瑞麒"><em>R</em> 瑞麒</a><a href="javascript:;" class="" bita-value="10" bita-text="斯柯达"><em>S</em> 斯柯达</a><a href="javascript:;" class="" bita-value="25" bita-text="三菱"><em>S</em> 三菱</a><a href="javascript:;" class="" bita-value="111" bita-text="斯巴鲁"><em>S</em> 斯巴鲁</a><a href="javascript:;" class="" bita-value="102" bita-text="双龙"><em>S</em> 双龙</a><a href="javascript:;" class="" bita-value="89" bita-text="smart"><em>S</em> smart</a><a href="javascript:;" class="" bita-value="165" bita-text="上汽大通MAXUS"><em>S</em> 上汽大通MAXUS</a><a href="javascript:;" class="" bita-value="209" bita-text="山姆"><em>S</em> 山姆</a><a href="javascript:;" class="" bita-value="137" bita-text="世爵"><em>S</em> 世爵</a><a href="javascript:;" class="" bita-value="169" bita-text="陕汽通家"><em>S</em> 陕汽通家</a><a href="javascript:;" class="bg-blue" bita-value="189" bita-text="特斯拉"><em>T</em> 特斯拉</a><a href="javascript:;" class="bg-blue" bita-value="202" bita-text="泰卡特"><em>T</em> 泰卡特</a><a href="javascript:;" class="bg-blue" bita-value="175" bita-text="腾势"><em>T</em> 腾势</a><a href="javascript:;" class="" bita-value="48" bita-text="五菱"><em>W</em> 五菱</a><a href="javascript:;" class="" bita-value="19" bita-text="沃尔沃"><em>W</em> 沃尔沃</a><a href="javascript:;" class="" bita-value="207" bita-text="潍柴英致"><em>W</em> 潍柴英致</a><a href="javascript:;" class="" bita-value="132" bita-text="五十铃"><em>W</em> 五十铃</a><a href="javascript:;" class="" bita-value="140" bita-text="威麟"><em>W</em> 威麟</a><a href="javascript:;" class="" bita-value="186" bita-text="威兹曼"><em>W</em> 威兹曼</a><a href="javascript:;" class="bg-blue" bita-value="13" bita-text="现代"><em>X</em> 现代</a><a href="javascript:;" class="bg-blue" bita-value="49" bita-text="雪佛兰"><em>X</em> 雪佛兰</a><a href="javascript:;" class="bg-blue" bita-value="6" bita-text="雪铁龙"><em>X</em> 雪铁龙</a><a href="javascript:;" class="bg-blue" bita-value="174" bita-text="星客特"><em>X</em> 星客特</a><a href="javascript:;" class="bg-blue" bita-value="87" bita-text="西雅特"><em>X</em> 西雅特</a><a href="javascript:;" class="bg-blue" bita-value="71" bita-text="新凯"><em>X</em> 新凯</a><a href="javascript:;" class="" bita-value="53" bita-text="一汽"><em>Y</em> 一汽</a><a href="javascript:;" class="" bita-value="100" bita-text="英菲尼迪"><em>Y</em> 英菲尼迪</a><a href="javascript:;" class="" bita-value="138" bita-text="野马汽车"><em>Y</em> 野马汽车</a><a href="javascript:;" class="" bita-value="41" bita-text="依维柯"><em>Y</em> 依维柯</a><a href="javascript:;" class="" bita-value="75" bita-text="永源"><em>Y</em> 永源</a><a href="javascript:;" class="" bita-value="238" bita-text="御捷"><em>Y</em> 御捷</a><a href="javascript:;" class="" bita-value="178" bita-text="宇通"><em>Y</em> 宇通</a><a href="javascript:;" class="" bita-value="194" bita-text="扬州亚星客车"><em>Y</em> 扬州亚星客车</a><a href="javascript:;" class="bg-blue" bita-value="77" bita-text="众泰"><em>Z</em> 众泰</a><a href="javascript:;" class="bg-blue" bita-value="60" bita-text="中华"><em>Z</em> 中华</a><a href="javascript:;" class="bg-blue" bita-value="33" bita-text="中兴"><em>Z</em> 中兴</a><a href="javascript:;" class="bg-blue" bita-value="167" bita-text="中欧奔驰房车"><em>Z</em> 中欧奔驰房车</a><a href="javascript:;" class="bg-blue" bita-value="233" bita-text="知豆"><em>Z</em> 知豆</a><a href="javascript:;" class="bg-blue" bita-value="214" bita-text="浙江卡尔森"><em>Z</em> 浙江卡尔森</a><a href="javascript:;" class="bg-blue" bita-value="203" bita-text="之诺"><em>Z</em> 之诺</a><a href="javascript:;" class="bg-blue" bita-value="204" bita-text="中通客车"><em>Z</em> 中通客车</a></div></div></div>
                        </div>
                        <div class="jsq-tit-fbox">
                            <div id="serial4" class="sel-item-box" style="z-index: 40; width: 150px;"><span class="sel-item"><span value="4438" style="color: black;">BRABUS巴博斯 GL级</span><em></em></span><div class="popup-box" style="height: 192px; display: none;"><div class="select-list"><div class="pop-sub-tt"><span>进口巴博斯</span></div><a href="javascript:;" class="" bita-value="3595" bita-text="BRABUS巴博斯 CLS级">BRABUS巴博斯 CLS级</a><a href="javascript:;" class="" bita-value="3598" bita-text="BRABUS巴博斯 G级">BRABUS巴博斯 G级</a><a href="javascript:;" class="" bita-value="4438" bita-text="BRABUS巴博斯 GL级">BRABUS巴博斯 GL级</a><a href="javascript:;" class="" bita-value="3594" bita-text="BRABUS巴博斯 M级">BRABUS巴博斯 M级</a><a href="javascript:;" class="" bita-value="3596" bita-text="BRABUS巴博斯 S级">BRABUS巴博斯 S级</a><a href="javascript:;" class="" bita-value="3961" bita-text="BRABUS巴博斯 SLK级">BRABUS巴博斯 SLK级</a></div></div></div>
                        </div>
                        <div class="jsq-tit-fbox w236">
                            <div id="cartype4" class="sel-item-box" style="z-index: 30; width: 200px;"><span class="sel-item"><span value="113862" style="color: black;">700GR</span><em></em></span><div class="popup-box" style="height: 72px; display: none;"><div class="select-list"><div class="pop-sub-tt"><span>2015 款</span></div><a href="javascript:;" class="" bita-value="113862" bita-text="700GR"><strong></strong>700GR</a></div></div></div>
                        </div>
                    </div>
                </div>-->
                <#-- <script language="javascript" type="text/javascript">
                    var hidBsId = document.getElementById("hidBsID");
                    var hidCsId = document.getElementById("hidCsID");
                    var hidCarId = document.getElementById("hidCarID");
                    var mdvalue = "0", sdvalue = "0", cdvalue = "0", statId = 104;
                    if (hidCarId && hidCarId.value > 0) {
                        mdvalue = (hidBsId || hidBsId.value != "") ? hidBsId.value : 0;
                        sdvalue = hidCsId ? hidCsId.value : 0;
                        cdvalue = hidCarId ? hidCarId.value : 0;
                        statId = 103;
                    }
                    
                    //绑定下拉选择框
                    BitA.DropDownListNew({
                        container: { master: "master4", serial: "serial4", cartype: "cartype4" },
                        include: { serial: "1", cartype: "1" },
                        dvalue: { master: mdvalue, serial: sdvalue, cartype: cdvalue },
                        callback: {
                            cartype: function (data) {
                            }
                        },
                        onchange: {
                            master: function (data) {
                                //初始化下拉组件(车系)文字颜色
                                var spanTag = $("#serial4").children("span").children("span");
                                spanTag[0].style.color = "black";
                            },
                            serial: function (data) {
                                //初始化下拉组件(车型)文字颜色
                                var spanTag = $("#cartype4").children("span").children("span");
                                spanTag[0].style.color = "black";
                            },
                            cartype: function (data) {
                                resetPrice(data.id, webSiteBaseUrl);
                                //初始化首付比例 否则从价格高选到底月供会出现负值
                                $('#r1').prop("checked", "checked");
                                InitCheChuanValue();
                                calcAutoLoanAll();
                                
                            }
                        }
                    });
                
                </script>-->
            </div>
            <div id="theanchor">
            </div>
            <div class="jisuanqi_box">
                <ul class="rela_ul">
                    <li>
                        <div class="l-box">
                            <span class="fl">请输入裸车价格</span> <span class="fonts1">
                                <input id="txtMoney" type="text" class="f-w100" maxlength="8" onfocus="if (value ==&#39;0&#39;){value =&#39;&#39;;}" onblur="if (value ==&#39;&#39;){value=&#39;0&#39;;}setCalcToolUrl(-1);calcAutoLoanAll();" onkeyup="InitPaiAndChuan(this.value)">
                                元</span>                        </div>
                        <div class="r-box">                        </div>
                       
                        <div class="clear">                        </div>
                    </li>
                    <li id="liDispaly1" class="bg" style="display: none;">
                        <div class="l-box padd_220">
                            <p class="fonts2">
                                <em>请选择车款或输入裸车价格</em></p>
                        </div>
                        <div class="clear">                        </div>
                    </li>
                    <li id="liDispaly2" style="background-color: rgb(247, 247, 247); background-position: initial initial; background-repeat: initial initial;" class="fixed-top">
                        <div class="l-box padd_220">
                            <p class="fonts2">
                                首付款：<em class="red16"><label id="lblShouFu">55,732,884</label>元</em></p>
                            <p class="suomin">首付金额+必要花费+商业保险</p>
                        </div>
                        <div class="l-box yuegong">
                            <p class="fonts2">
                                月供：<em class="red16"><label id="lblYueGong">751,362</label>元</em></p>
                            <p class="suomin"><label id="yueGongQi">48</label>期</p>
                        </div>
                        <div class="l-box zhonghuafei">
                            <p class="fonts2">
                                购车总花费：<em class="red16"><label id="lblTopTotal">91,798,260</label>元</em></p>
                            <p class="suomin">
                                比全款多花费<label id="lblDuoHuaFei">4,474,581</label>元</p>
                        </div>
                        <div class="clear">
                        </div>
                    </li>
                </ul>
                <p class="suomin txt_right">
                    此结果仅供参考，实际应缴费以当地为准</p>
            </div>
            <!------------------------贷款明细开始------------------------>
            <div class="jsq_com_box">
                <div class="titbox">
                    <h4>
                        贷款明细</h4>
                </div>
                <table id="loanDetail" width="100%" cellspacing="0" cellpadding="0" border="0">
                    <colgroup>
                        <col width="200px">
                        <col width="180px">
                        <col width="210px">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>
                                首付比例
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <input id="txtDownPayments" type="text" class="f-w100 f-curr" onfocus="if (value ==&#39;0&#39;){value =&#39;&#39;;}" onblur="if (value ==&#39;&#39;){value=&#39;0&#39;;}checkTxtDownPayments();calcAutoLoanAll();" disabled="disabled">
                                    元</div>
                            </td>
                            <td>
                                <input id="r1" name="rdoDownPayments" type="radio" checked="checked" value="0.3" onclick="calcAutoLoanAll()">
                                <label for="r1">
                                    30%</label>
                                <input id="r2" name="rdoDownPayments" type="radio" value="0.4" onclick="calcAutoLoanAll()">
                                <label for="r2">
                                    40%</label>
                                <input id="r3" name="rdoDownPayments" type="radio" value="0.5" onclick="calcAutoLoanAll()">
                                <label for="r3">
                                    50%</label>
                                <input id="r4" name="rdoDownPayments" type="radio" value="0.6" onclick="calcAutoLoanAll()">
                                <label for="r4">
                                    60%</label>
                                <br>
                                <input id="rdoDownPaymentsOfSelf" name="rdoDownPayments" onclick="resetTxtDownPayments()" type="radio">
                                <label for="rdoDownPaymentsOfSelf">
                                    自定义</label>
                            </td>
                            <td>
                                首付金额=首付比例*裸车价格
                            </td>
                        </tr>
                        <tr>
                            <th>
                                贷款期限
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em><label id="daikuanQi">48</label></em>期</div>
                            </td>
                            <td>
                                <input id="y1" name="rdoPaymentYears" type="radio" value="1" onclick="calcYearRate();calcAutoLoanAll();">
                                <label for="y1">
                                    1年</label>
                                <input id="y2" name="rdoPaymentYears" type="radio" value="2" onclick="calcYearRate();calcAutoLoanAll();">
                                <label for="y2">
                                    2年</label>
                                <input id="y3" name="rdoPaymentYears" type="radio" checked="checked" value="3" onclick="calcYearRate();calcAutoLoanAll();">
                                <label for="y3">
                                    3年</label>
                                <input id="y4" name="rdoPaymentYears" type="radio" value="4" onclick="calcYearRate();calcAutoLoanAll();">
                                <label for="y4">
                                    4年</label>
                                <br>
                                <input id="y5" name="rdoPaymentYears" type="radio" value="5" onclick="calcYearRate();calcAutoLoanAll();">
                                <label for="y5">
                                    5年</label>
                            </td>
                            <td>
                                银行贷款基准利率：1年期6.31%；2年期6.4%；3年期6.4%；4年期6.65%；5年期6.65%；
                            </td>
                        </tr>
                        <tr>
                            <th>
                                贷款利率
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <input id="loanRate" type="text" maxlength="5" class="f-w100 f-curr" value="6.4" onfocus="if (value ==&#39;0&#39;){value =&#39;&#39;;}" onblur="if (value ==&#39;&#39;){value=&#39;0&#39;;}calcAutoLoanAll();">
                                    %</div>
                            </td>
                            <td>&nbsp;
                                
                          </td>
                            <td>
                                可按照实际贷款套餐利率修改
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!------------------------贷款明细结束------------------------>
            <!------------------------必要花费开始------------------------>
            <div class="jsq_com_box">
                <div class="titbox">
                    <h4>
                        必要花费</h4>
                    <div class="red_num">
                        <label id="essentialCost">6,752,085</label>
                        元</div>
                </div>
                <table id="calEssentialCost" width="100%" cellspacing="0" cellpadding="0" border="0">
                    <colgroup>
                        <col width="200px">
                        <col width="180px">
                        <col width="210px">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>
                                <label>
                                    购置税</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblAcquisitionTax">6,750,170</label>
                                    </em>元</div>
                            </td>
                            <td>&nbsp;
                                
                          </td>
                            <td>
                                <span class="fl yiwen_box">购置附加税=裸车价格÷（1＋17％）× 10% 
                                    <a class="yiwenicon z30" desvalue="购置税解释说明" href="javascript:showjs('AcquisitionTax');">?
                                        <div id="AcquisitionTax" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>购置税</strong>
                                                <p>
                                                    车辆购置税是对在我国境内购置规定车辆的单位和个人征收的一种税，它由车辆购置附加费演变而来。现行车辆购置税法的基本规范，是从2001年1月1日起实施的《中华人民共和国车辆购置税暂行条例》。车辆购置税的纳税人为购置（包括购买、进口、自产、受赠、获奖或以其他方式取得并自用）应税车辆的单位和个人，征税范围为汽车、摩托车、电车、挂车、农用运输车，税率为10%，应纳税额的计算公式为：应纳税额=计税价格×税率。（2009年1月20日至12月31日，对1.6升及以下排量乘用车减按5%征收车辆购置税。自2010年1月1日至12月31日，对1.6升及以下排量乘用车减按7.5%征收车辆购置税。）</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <label>
                                    上牌费用</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <input maxlength="4" id="txtChePai" class="f-w100 f-curr" type="text" value="500" onkeyup="value=value.replace(/(\D)/g,&#39;&#39;);calcAutoLoanAll();" onfocus="if (value ==&#39;0&#39;){value =&#39;&#39;;}" onblur="if (value ==&#39;&#39;){value=&#39;0&#39;;}calcAutoLoanAll();">
                                    元</div>
                            </td>
                            <td>&nbsp;
                                
                          </td>
                            <td>
                                <span class="fl yiwen_box">可手动修改，不同地区费用不同 
                                    <a class="yiwenicon z30" desvalue="上牌费用解释说明" href="javascript:showjs('shangPai');">?
                                        <div id="shangPai" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>上牌费用</strong>
                                                <p>
                                                通常商家提供的一条龙服务收费约500元，个人办理约373元，其中工商验证、出库150元、移动证30元、环保卡3元、拓号费40元、行驶证相片20元、托盘费130元</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <label>
                                    车船使用税</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <input id="txtVehicleTax" class="f-w100 f-curr" type="text" maxlength="4" onkeyup="value=value.replace(/(\D)/g,&#39;&#39;);calcAutoLoanAll();" onfocus="if (value ==&#39;0&#39;){value =&#39;&#39;;}" onblur="if (value ==&#39;&#39;){value=&#39;0&#39;;}calcAutoLoanAll();">
                                    元</div>
                            </td>
                            <td id="divLoanVehicleAndVesselTaxMessage"><select id="vehicleTax" class="f-w150 f-curr" onblur="calcAutoLoanAll();"><option value="1">1.0L(含)以下</option><option value="2" selected="selected">1.0-1.6L(含)</option><option value="3">1.6-2.0L(含)</option><option value="4">2.0-2.5L(含)</option><option value="5">2.5-3.0L(含)</option><option value="6">3.0-4.0L(含)</option><option value="7">4.0L以上</option></select></td>
                            <td>
                                <span class="fl yiwen_box">按排量收取费用 
                                    <a class="yiwenicon z30" desvalue="车船使用税解释说明" href="javascript:showjs('cheChuanTax');">?
                                        <div id="cheChuanTax" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>车船使用税</strong>
                                                <p>
                                                各省不统一，以北京为例(单位/年)。1.0L(含)以下300元；1.0-1.6L(含)420元；1.6-2.0L(含)480元；2.0-2.5L(含)900元；2.5-3.0L(含)1920元；3.0-4.0L(含)3480元；4.0L以上5280元；不足一年按当年剩余月算。<br>
                                                车船使用税是对行驶于公共道路的车辆和航行于国内河流、湖泊或领海口岸的船舶,按照其种类(如机动车辆、非机动车辆、载人汽车、载货汽车等)、吨位和规定的税额计算征收的一种使用行为税。                                            </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <label>
                                    交强险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblCompulsory">1,100</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selCompulsory" class="f-w150 f-curr" onchange="calcAutoLoanAll();">
                                    <option value="950">家用6座以下</option>
                                    <option value="1100">家用6座及以上</option>
                                </select>
                            </td>
                            <td>
                                <span class="fl yiwen_box">国家强制保险 
                                    <a class="yiwenicon z30" desvalue="交强险解释说明" href="javascript:showjs('jiaoQiagnX');">?
                                        <div id="jiaoQiagnX" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>交强险</strong>
                                                <p>
                                                家用6座以下950元/年，家用6座及以上1100元/年<br>
                                                机动车交通事故责任强制保险是我国首个由国家法律规定实行的强制保险制度。《机动车交通事故责任强制保险条例》规定：交强险是由保险公司对被保险机动车发生道路交通事故造成受害人(不包括本车人员和被保险人)的人身伤亡、财产损失，在责任限额内予以赔偿的强制性责任保险。<br>
                                                交强险有责限额分为死亡伤残赔偿限额110000元、医疗费用赔偿限额10000元、财产损失赔偿限额2000元以及被保险人在道路交通事故中无责任的赔偿限额。无责的赔偿限额死亡伤残赔偿限额11000元、医疗费用赔偿限额1000元、财产损失赔偿限额100元。<br>
                                                责任限额是指被保险机动车发生道路交通事故，保险公司对每次保险事故所有受害人的人身伤亡和财产损失所承担的最高赔偿金额。                                            </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!------------------------必要花费结束------------------------>
            <!------------------------商业保险开始------------------------>
            <div class="jsq_com_box">
                <div class="titbox">
                    <h4>
                        商业保险</h4>
                    <div class="red_num">
                        <label id="lblCommonTotal">1,594,607</label>
                        元</div>
                    <div id="businessHeader" class="tab">
                        <a href="javascript:void(0);" onclick="JiBenBaoZ()">基本保障</a> | <a href="javascript:void(0);" onclick="GaoXingJ()">高性价比</a> | <a href="javascript:void(0);" onclick="calcBusinessTotalIncludeState()">
                                新车主全面保障</a>
                    </div>
                    <script type="text/javascript">
                        //车辆损失险 第三者责任险 不计免赔
                        function JiBenBaoZ() {
                            $('#chkTPL').prop("checked", true);
                            $('#chkCarDamage').prop("checked", true);
                            $('#chkAbatement').prop("checked", true);

                            $('#chkCarTheft').prop("checked", false);
                            $('#chkBreakageOfGlass').prop("checked", false);
                            $('#chkSelfignite').prop("checked", false);
                            $('#chkLimitofPassenger').prop("checked", false);
                            $('#chkLimitofDriver').prop("checked", false);
                            $('#chkCarDamageDW').prop("checked", false);
                            $('#chkEngine').prop("checked", false);

                            var commonTotal = 0;
                            if ($('#chkTPL').prop("checked")) {
                                commonTotal += parseFloat(jQuery('#lblTPL').html());
                            }
                            if ($('#chkCarDamage').prop("checked")) {
                                commonTotal += GetIntValue(jQuery('#lblCarDamage').html());
                            }
                            if ($('#chkAbatement').prop("checked")) {
                                commonTotal += GetIntValue(jQuery('#lblAbatement').html());
                            }
                            jQuery('#lblCommonTotal').html(Math.round(commonTotal));
                            calcAutoLoanAll();
                        }
                        //第三者责任险 车辆损失险 不计免赔 乘客坐位责任险 司机坐位责任险
                        function GaoXingJ() {
                            $('#chkTPL').prop("checked", true);
                            $('#chkCarDamage').prop("checked", true);
                            $('#chkAbatement').prop("checked", true);
                            $('#chkLimitofPassenger').prop("checked", true);
                            $('#chkLimitofDriver').prop("checked", true);

                            $('#chkCarTheft').prop("checked", false);
                            $('#chkBreakageOfGlass').prop("checked", false);
                            $('#chkSelfignite').prop("checked", false);
                            $('#chkCarDamageDW').prop("checked", false);
                            $('#chkEngine').prop("checked", false);

                            var commonTotal = 0;
                            if ($('#chkTPL').prop("checked")) {
                                commonTotal += parseFloat(jQuery('#lblTPL').html());
                            }
                            if ($('#chkCarDamage').prop("checked")) {
                                commonTotal += GetIntValue(jQuery('#lblCarDamage').html());
                            }
                            if ($('#chkAbatement').prop("checked")) {
                                commonTotal += GetIntValue(jQuery('#lblAbatement').html());
                            }
                            if ($('#chkLimitofPassenger').prop("checked")) {
                                commonTotal += parseFloat(jQuery('#lblLimitOfPassenger').html());
                            }
                            if ($('#chkLimitofDriver').prop("checked")) {
                                commonTotal += parseFloat(jQuery('#lblLimitOfDriver').html());
                            }
                            jQuery('#lblCommonTotal').html(Math.round(commonTotal));
                            calcAutoLoanAll();
                        }

                        function calcBusinessTotalIncludeState() {
                            $('#chkTPL').prop("checked", true);
                            $('#chkCarDamage').prop("checked", true);
                            $('#chkAbatement').prop("checked", true);
                            $('#chkLimitofPassenger').prop("checked", true);
                            $('#chkLimitofDriver').prop("checked", true);
                            $('#chkCarTheft').prop("checked", true);
                            $('#chkBreakageOfGlass').prop("checked", true);
                            $('#chkSelfignite').prop("checked", true);
                            $('#chkCarDamageDW').prop("checked", true);
                            $('#chkEngine').prop("checked", true);
                            calcBusinessTotal();
                            calcAutoLoanAll();
                        }
                    </script>
                </div>
                <table id="calBusiness" width="100%" cellspacing="0" cellpadding="0" border="0">
                    <colgroup>
                        <col width="200px">
                        <col width="180px">
                        <col width="210px">
                        <col>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>
                                <input id="chkTPL" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkTPL">
                                    第三者责任险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblTPL">1,131</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selTPL" class="f-w100 f-curr" onchange="calcAutoLoanAll();">
                                    <option value="50000">5万</option>
                                    <option value="100000">10万</option>
                                    <option value="200000" selected="selected">20万</option>
                                    <option value="500000">50万</option>
                                    <option value="1000000">100万</option>
                                </select>
                                赔附额度
                            </td>
                            <td>
                                <span class="fl yiwen_box">发生车险事故时，赔偿对第三方造成的人身及财产损失 
                                    <a class="yiwenicon z30" desvalue="第三者责任险解释说明" href="javascript:showjs('diSanFang');">?
                                        <div id="diSanFang" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 100px;  bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>第三方责任险</strong>
                                                <p>
                                                第三者责任险是指被保险人或其允许的驾驶人员在使用保险车辆过程中发生意外事故，致使第三者遭受人身伤亡或财产直接损毁，依法应当由被保险人承担的经济责任，保险公司负责赔偿。同时，若经保险公司书面同意，被保险人因此发生仲裁或诉讼费用的，保险公司在责任限额以外赔偿，但最高不超过责任限额的30％。因为交强险在对第三者的财产损失和医疗费用部分赔偿较低，可考虑购买第三者责任险作为交强险的补充。</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkCarDamage" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkCarDamage">
                                    车辆损失险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblCarDamage">711,135</label>
                                    </em>元</div>
                            </td>
                            <td>&nbsp;
                                
                          </td>
                            <td>
                                <span class="fl yiwen_box">车子发生碰撞，赔偿自己爱车损失的费用 
                                    <a class="yiwenicon z30" desvalue="车辆损失险解释说明" href="javascript:showjs('sunshi');">?
                                        <div id="sunshi" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px;  bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>车辆损失险</strong>
                                                <p>
                                                车辆损失险-裸车价格*费率+基础保费<br>
                                                车辆损失险是车辆保险中用途最广泛的险种，它负责赔偿由于自然灾害和意外事故造成的自己车辆的损失。无论是小剐小蹭，还是损坏严重，都可以由保险公司来支付修理费用。<br>
                                                被保险人或其允许的合格驾驶员在使用保险车辆过程中，因下列原因造成保险车辆的损失，保险公司负责赔偿：1．碰撞、倾覆；2．火灾、爆炸；3．外界物体倒塌、空中运行物体坠落、保险车辆行驶中平行坠落；4．雷击、暴风、龙卷风、暴雨、洪水、海啸、地陷、冰陷、崖崩、雪崩、雹灾、泥石流、滑坡；5.
                                                载运保险车辆的渡船遭受自然灾害（只限于有驾驶员随车照料者）。<br>
                                                发生保险事故时，被保险人或其允许的合格驾驶员对保险车辆采取施救、保护措施所支出的合理费用，保险公司负责赔偿。但此项费用的最高赔偿金额以责任限额为限。                                            </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkAbatement" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkAbatement">
                                    不计免赔特约险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblAbatement">142,453</label>
                                    </em>元</div>
                            </td>
                            <td>
                            </td>
                            <td>
                                <span class="fl yiwen_box">保险条款约定事故发生后被保险人要自己承担一定比例的损失金额，购买此险这部分损失费用保险公司将同样给予赔偿 
                                    <a class="yiwenicon z30" desvalue="不计免赔特约险解释说明" href="javascript:showjs('mianPei');">?
                                        <div id="mianPei" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>不计免赔特约险</strong>
                                                <p>
                                                    (车辆损失险+第三者责任险)×20%<br>
                                                    负责赔偿在车损险和第三者责任险中应由被保险人自己承担的免赔金额，即100%赔付。<br>
                                                    不计免赔特约险为附加险，必须在投保车损险和第三者责任险之后方可投保该险种。</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkCarTheft" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkCarTheft">
                                    全车盗抢险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblCarTheft">347,639</label>
                                    </em>元</div>
                            </td>
                            <td>
                            </td>
                            <td>
                                <span class="fl yiwen_box">赔偿全车被盗窃、抢劫、抢夺造成的车辆损失 
                                    <a class="yiwenicon z30" desvalue="全车盗抢险解释说明" href="javascript:showjs('daoQiang');">?
                                        <div id="daoQiang" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>全车盗抢险</strong>
                                                <p>
                                                    全车盗抢险=裸车价格*费率+基础保费<br>
                                                    指保险车辆全车被盗窃、被抢劫、被抢夺，经县级以上公安刑侦部门立案侦查证实满一定时间没有下落的，由保险公司在保险金额内予以赔偿。如果是车辆的某些零部件被盗抢，如轮胎被盗抢、车内财产被盗抢、后备箱内的物品丢失，保险公司均不负责赔偿。
                                                    但是，对于车辆被盗抢期间内，保险车辆上零部件的损坏、丢失，保险公司一般负责赔偿。<br>
                                                    全车盗抢险为附加险，必须在投保车辆损失险之后方可投保该险种。                                                </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkBreakageOfGlass" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkBreakageOfGlass">
                                    玻璃单独破碎险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblBreakageOfGlass">236,931</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selBreakageOfGlass" class="f-w100 f-curr" onchange="calcAutoLoanAll()">
                                    <option value="1">国产</option>
                                    <option value="0">进口</option>
                                </select>
                                赔附额度
                            </td>
                            <td>
                                <span class="fl yiwen_box">赔偿保险车辆在使用过程中，发生车窗、挡风玻璃的单独破碎损失 
                                    <a class="yiwenicon z30" desvalue="玻璃单独破碎险解释说明" href="javascript:showjs('boLi');">?
                                        <div id="boLi" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px;  bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>玻璃单独破碎险</strong>
                                                <p>
                                                    玻璃单独破碎险=裸车价格*费率<br>
                                                    负责赔偿保险车辆在使用过程中，发生本车玻璃发生单独破碎的保险公司按照保险合同进行赔偿。玻璃单独破碎险中的玻璃是指风档玻璃和车窗玻璃，如果车灯、车镜玻璃破碎及车辆维修过程中造成的破碎，保险公司不承担赔偿责任。<br>
                                                    玻璃单独破碎险为附加险，必须在投保车辆损失险之后方可投保该险种。                                                </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkSelfignite" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkSelfignite">
                                    自燃损失险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblSelfignite">118,465</label>
                                    </em>元</div>
                            </td>
                            <td>
                            </td>
                            <td>
                                <span class="fl yiwen_box">赔偿车子因电器、线路、运载货物等自身原因引发火灾造成的损失 
                                    <a class="yiwenicon z30" desvalue="自燃损失险解释说明" href="javascript:showjs('ziRan');">?
                                        <div id="ziRan" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px;  bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>自燃损失险</strong>
                                                <p>
                                                    自燃损失险=裸车价格×0.15%<br>
                                                    负责赔偿因本车电器、线路、供油系统发生故障及运载货物自身原因起火造成车辆本身的损失。当车辆发生部分损失，按照实际修复费用赔偿修理费。如果车辆自燃造成整体烧毁或已经失去修理价值，则按照出险时车辆的实际价值赔偿，但不超过责任限额。                                                </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkEngine" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkEngine">
                                    涉水险/发动机特别损失险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="engineDamage">35,557</label>
                                    </em>元</div>
                            </td>
                            <td>
                            </td>
                            <td>
                                <span class="fl yiwen_box">负责爱车被水淹导致改动机受损所造成的损失 
                                    <a class="yiwenicon z30" desvalue="发动机特别损失险解释说明" href="javascript:showjs('faDongJi');">?
                                        <div id="faDongJi" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>涉水险/发动机特别损失险</strong>
                                                <p>
                                                    发动机特别损失险=车损险*5%<br>
                                                    涉水险或称汽车损失保险、发动机特别损失险，各个保险公司叫法不一样但本质一致，这是一种新衍生的险种，均指车主为发动机购买的附加险。<br>
                                                    这个险种主要是指车主为发动机购买的附加险。它主要是保障车辆在积水路面涉水行驶或被水淹后致使发动机损坏可给予赔偿。即使被水淹后车主还强行启动发动机而造成了损害，保险公司仍然给予赔偿。当然保险公司不一样，条款就不大一样，投保时可以查阅下各个保险公司条款内容。                                                </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkCarDamageDW" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkCarDamageDW">
                                    车身划痕险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblCarDamageDW">1,100</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selCarDamageDW" class="f-w100 f-curr" onchange="calcAutoLoanAll()">
                                    <option value="2000">2千</option>
                                    <option value="5000" selected="selected">5千</option>
                                    <option value="10000">1万</option>
                                    <option value="20000">2万</option>
                                </select>
                                赔附额度
                            </td>
                            <td>
                                <span class="fl yiwen_box">负责无碰撞痕迹的车身表面油漆单独划伤的损失 
                                    <a class="yiwenicon z30" desvalue="车身划痕险解释说明" href="javascript:showjs('huaHen');">?
                                        <div id="huaHen" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>车身划痕险</strong>
                                                <p>
                                                    无明显碰撞痕迹的车身划痕损失，保险公司负责赔偿。<br>
                                                    车身划痕险为附加险，必须在投保车辆损失险之后方可投保该险种。                                                </p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkLimitofDriver" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkLimitofDriver">
                                    司机座位责任险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblLimitOfDriver">40</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selLimitofDriver" class="f-w100 f-curr" onchange="calcAutoLoanAll()">
                                    <option value="10000">1万</option>
                                    <option value="20000">2万</option>
                                    <option value="30000">3万</option>
                                    <option value="40000">4万</option>
                                    <option value="50000">5万</option>
                                </select>
                                赔附额度
                            </td>
                            <td>
                                <span class="fl yiwen_box">发生车险事故时，赔偿车内司机的伤亡和医疗赔偿费用 
                                    <a class="yiwenicon z30" desvalue="司机座位责任险解释说明" href="javascript:showjs('siJi');">?
                                        <div id="siJi" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px;  bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>司机坐位责任险</strong>
                                                <p>
                                                    司机责任险=保额*费率<br>
                                                    统称为车上责任险，包括司机座位和乘客座位，主要是指在发生意外情况下，保险公司对司机座位的人员和乘客的人身安全进行赔偿。<br>
                                                    严格来说，司机责任险并不是一个独立的险种，而是商业车险中车上人员责任险的一部分，除此之外，车主还可以为乘客座位投保，一般选择的赔偿限额为1-5万元</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                        <tr>
                            <th>
                                <input id="chkLimitofPassenger" type="checkbox" checked="checked" onclick="calcAutoLoanAll();">
                                <label for="chkLimitofPassenger">
                                    乘客座位责任险</label>
                            </th>
                            <td class="r_align">
                                <div class="jiage">
                                    <em>
                                        <label id="lblLimitOfPassenger">156</label>
                                    </em>元</div>
                            </td>
                            <td>
                                <select id="selLimitofPassenger" class="f-w100 f-curr" onchange="calcAutoLoanAll()">
                                    <option value="10000">1万</option>
                                    <option value="20000">2万</option>
                                    <option value="30000">3万</option>
                                    <option value="40000">4万</option>
                                    <option value="50000">5万</option>
                                </select>
                                赔附额度
                            </td>
                            <td>
                                <span class="fl yiwen_box">发生车险事故时，赔偿车内乘客的伤亡和医疗赔偿费用 
                                    <a class="yiwenicon z30" desvalue="乘客座位责任险解释说明" href="javascript:showjs('chengKe');">?
                                        <div id="chengKe" class="tc tc-jsq" style="display: none;">
                                            <iframe style="position: absolute; z-index: -1; width: 400px; bottom: 0;
                                                left: 0; scrolling: no;" frameborder="0" src="about:blank"></iframe>
                                            <div class="tc-box">
                                                <i></i><em class="close">关闭</em> <strong>乘客坐位责任险</strong>
                                                <p>
                                                    乘客责任险=保额*费率<br>
                                                    被保险人允许的合格驾驶员在使用保险车辆过程中发生保险事故，致使车内乘客人身伤亡，依法应由被保险人承担的赔偿责任，保险人依照保险合同的约定给予赔偿。</p>
                                            </div>
                                        </div>
                                    </a>                                </span>                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!------------------------商业保险结束------------------------>
        </div>
    </div>
    <script type="text/javascript">
        (function ($) {
            $(function () {
                $("#loanRate").bind("keyup", function () {
                    $(this).val($(this).val().replace(/([^0-9.])/g, ''));
                    if (parseFloat($(this).val()) > 99.99) {
                        $(this).val(99.99);
                        calcAutoLoanAll();
                        return;
                    }
                    calcAutoLoanAll();
                });

//                $("#loanRate").bind("keypress", function () {
//                    $(this).val($(this).val().replace(/([^0-9.])/g, ''));
//                    if (parseFloat($(this).val()) > parseFloat($("#txtMoney").val())) {
//                        $(this).val($("#txtMoney").val());
//                        calcAutoLoanAll();
//                        return;
//                    }
//                    calcAutoLoanAll();
//                });

                $("#txtDownPayments").bind("keyup", function () {
                    $(this).val($(this).val().replace(/([^0-9])/g, ''));
                    var luochePrice = $("#txtMoney").val();
                    if (parseFloat($(this).val()) > parseFloat(luochePrice)) {
                        $(this).val(luochePrice);
                        calcAutoLoanAll();
                        return;
                    }
                    calcAutoLoanAll();
                });



                //解释说明
                $("a.yiwenicon.z30").bind("click", function (e) {
                    $(".yiwenicon.z30").removeAttr("style"); //处理连续点击问号的情况
                    e.stopPropagation();
                    $(this).css("zIndex", 100);
                });
                $("p").bind("click", function (e) {
                    e.stopPropagation();
                });
                //------------------------必要花费--------------------------------------------

                $(document).on("change", "#vehicleTax", function () {
                    CalculateVehAndVesselTax();
                    calcAutoLoanAll();
                });

                //------------------------商业保险--------------------------------------------
                
            });
        })(jQuery)
	</script>
      <#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
</body>
</html>