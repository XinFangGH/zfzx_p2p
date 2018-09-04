<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 -  银行卡</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/commen.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
    <script src="${base}/mobileNew/js/fontSize.js"></script>
    <script src="${base}/mobileNew/js/commen.js"></script>
    <script src="${base}/mobileNew/js/swiper.min.js"></script>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        银行卡
    </div>
</div>
<div class="bankCard bankCard1">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">快捷支付限额<i></i></div>
            <div class="swiper-slide">网关支付限额<i></i></div>
        </div>
    </div>
    <div class="swiper-container swiper2">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
                <table class="db">
                    <thead>
                    <tr>
                        <th>银行名称</th>
                        <th>单笔限额（元）</th>
                        <th>单日限额（元）</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><span class="bname gongshang"></span><span>工商银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname jianshe"></span><span>建设银行</span></td>
                        <td>200,000</td>
                        <td>200,000</td>
                    </tr>
                    <tr>
                        <td><span class="bname guangfa"></span><span>广发银行</span></td>
                        <td>200,000</td>
                        <td>500,000</td>
                    </tr>
                    <tr>
                        <td><span class="bname pingan"></span><span>平安银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname guangda"></span><span>光大银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname jiaotong"></span><span>交通银行</span></td>
                        <td>10000</td>
                        <td>20,000</td>
                    </tr>
                    <tr>
                        <td><span class="bname youchu"></span><span>邮储银行</span></td>
                        <td>5000</td>
                        <td>5000</td>
                    </tr>
                    <tr>
                        <td><span class="bname zhongguo"></span><span>中国银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname zheshang"></span><span>浙商银行</span></td>
                        <td>20000</td>
                        <td>20000</td>
                    </tr>
                    <tr>
                        <td><span class="bname pufa"></span><span>浦发银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname xingye"></span><span>兴业银行</span></td>
                        <td>50000</td>
                        <td>50000</td>
                    </tr>
                    <tr>
                        <td><span class="bname beijing"></span><span>北京银行</span></td>
                        <td>5000</td>
                        <td>5000</td>
                    </tr>
                    <tr>
                        <td><span class="bname zhongxin"></span><span>中信银行</span></td>
                        <td>5000</td>
                        <td>5000</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="swiper-slide">
                <p style="display: inline-block;width: 100%;height: 0.6rem;line-height: 0.6rem;font-size: 0.24rem;padding:0 0.5rem;margin-top: 0.2rem">
                    <i style="display: inline-block;height: 0.6rem;color:#FF7740;font-size: 0.24rem;">*</i>
                    网关支付请去电脑端进行操作</p>
                <div class="other_box">
                    <table class="other gongshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>电子口<br>令卡</td>
                            <td>0.05</td>
                            <td>0.1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>短信</td>
                            <td>0.2</td>
                            <td>0.5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>电子密码</td>
                            <td>50</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>U盾</td>
                            <td>100</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other jianshe">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>账号支付</td>
                            <td>0.1</td>
                            <td>0.1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>动态口令</td>
                            <td>0.5</td>
                            <td>0.5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>一代key</td>
                            <td>5</td>
                            <td>10</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>二代key</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other guangfa">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other pingan">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other guangda">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>动态密码</td>
                            <td>1</td>
                            <td>1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>U盾</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other jiaotong">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>短信</td>
                            <td>5</td>
                            <td>5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>key</td>
                            <td>100</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other youchu">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>手机短信</td>
                            <td>1</td>
                            <td>1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>电子令牌<br>+短信</td>
                            <td>20</td>
                            <td>20</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>Ukey<br>+短信</td>
                            <td>200</td>
                            <td>200</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other zhongguo">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>1</td>
                            <td>20</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other zheshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other pufa">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>动态密码</td>
                            <td>20</td>
                            <td>20</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>数字证书</td>
                            <td>无</td>
                            <td>无</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other xingye">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other beijing">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other zhongxin">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>动态密码</td>
                            <td>0.1</td>
                            <td>0.5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>U盾</td>
                            <td>100</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other zhaoshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>大众版</td>
                            <td>0.05</td>
                            <td>0.05</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>专业版</td>
                            <td>无</td>
                            <td>无</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other nongye">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>动态口令</td>
                            <td>0.1</td>
                            <td>0.3</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>一代key</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>二代key</td>
                            <td>100</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other minsheng">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>大众版</td>
                            <td>0.03</td>
                            <td>0.03</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>贵宾版</td>
                            <td>0.5</td>
                            <td>0.5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>U宝</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other huaxia">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>大众版</td>
                            <td>0.03</td>
                            <td>0.1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>手机动态</td>
                            <td>0.1</td>
                            <td>0.5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>数字证书</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other shanghai">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>E盾版网银</td>
                            <td>50</td>
                            <td>100</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>动态密码<br>网银</td>
                            <td>0.6</td>
                            <td>1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other tianjin">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other nanjing">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>5</td>
                            <td>5</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other hangzhou">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>卡密支付</td>
                            <td>300</td>
                            <td>300</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>证书支付</td>
                            <td>100</td>
                            <td>500</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other jiangxi">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other ningbo">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other qingdao">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <%--<div class="other_box">--%>
                    <%--<table class="other lanzhou">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th></th>--%>
                            <%--<th>单笔限额<br>(万元)</th>--%>
                            <%--<th>日累计<br>限额(万元)</th>--%>
                            <%--<th>原路<br>返回</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>手机短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>电子令牌<br>+短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>Ukey<br>+短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>备注</td>--%>
                            <%--<td colspan="3" class="text-l">需要开通网银支付功能</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <%--<div class="other_box">--%>
                    <%--<table class="other guilin">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th></th>--%>
                            <%--<th>单笔限额<br>(万元)</th>--%>
                            <%--<th>日累计<br>限额(万元)</th>--%>
                            <%--<th>原路<br>返回</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td>手机短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>电子令牌<br>+短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>Ukey<br>+短信</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>200</td>--%>
                            <%--<td>支持</td>--%>
                        <%--</tr>--%>
                        <%--<tr>--%>
                            <%--<td>备注</td>--%>
                            <%--<td colspan="3" class="text-l">需要开通网银支付功能</td>--%>
                        <%--</tr>--%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                <%--</div>--%>
                <div class="other_box">
                    <table class="other guizhou">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other huishang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>动态密码</td>
                            <td>0.1</td>
                            <td>0.1</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>USBKey</td>
                            <td>50</td>
                            <td>50</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other qilu">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other guangzhounongshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>300</td>
                            <td>300</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other huichunnongshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other yanbiannongshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="other_box">
                    <table class="other yinghuainongshang">
                        <thead>
                        <tr>
                            <th></th>
                            <th>单笔限额<br>(万元)</th>
                            <th>日累计<br>限额(万元)</th>
                            <th>原路<br>返回</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>网关支付</td>
                            <td>个人设置</td>
                            <td>个人设置</td>
                            <td>支持</td>
                        </tr>
                        <tr>
                            <td>备注</td>
                            <td colspan="3" class="text-l">需要开通网银支付功能</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        function setCurrentSlide(ele, index) {
            $(".swiper1 .swiper-slide").removeClass("selected");
            ele.addClass("selected");
            //swiper1.initialSlide=index;
        }

        var sl_swiper1 = new Swiper('.bankCard1 .swiper1', {
            //					设置slider容器能够同时显示的slides数量(carousel模式)。
            //					可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
            //					loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
            slidesPerView: 2,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 10,//slide之间的距离（单位px）。
            freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            observer: true,//修改swiper自己或子元素时，自动初始化swiper
            observeParents: true,//修改swiper的父元素时，自动初始化swiper
            onTab: function (swiper) {
                var n = sl_swiper1.clickedIndex;
            }
        });
        sl_swiper1.slides.each(function (index, val) {
            var ele = $(this);
            ele.on("click", function () {
                setCurrentSlide(ele, index);
                sl_swiper2.slideTo(index, 500, false);
                //mySwiper.initialSlide=index;
            });
        });

        var sl_swiper2 = new Swiper('.bankCard1 .swiper2', {
            //freeModeSticky  设置为true 滑动会自动贴合
            direction: 'horizontal',//Slides的滑动方向，可设置水平(horizontal)或垂直(vertical)。
            loop: false,
            //					effect : 'fade',//淡入
            //effect : 'cube',//方块
            //effect : 'coverflow',//3D流
            //					effect : 'flip',//3D翻转
            autoHeight: true,//自动高度。设置为true时，wrapper和container会随着当前slide的高度而发生变化。
            observer: true,//修改swiper自己或子元素时，自动初始化swiper
            observeParents: true,//修改swiper的父元素时，自动初始化swiper
            onSlideChangeEnd: function (swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                var n = swiper.activeIndex;
                setCurrentSlide($(".bankCard1 .swiper1 .swiper-slide").eq(n), n);
                sl_swiper1.slideTo(n, 500, false);
            }
        });
    });
</script>
</body>
</html>