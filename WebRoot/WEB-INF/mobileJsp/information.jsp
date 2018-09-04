<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent"/>
    <title>升升投 - 信息披露</title>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/reset.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/swiper.min.css"/>
    <link charset="utf-8" type="text/css" rel="stylesheet" href="${base}/mobileNew/css/information.css"/>
    <style>
        html {
            font-size: 100px;
        }
        .container {
            width: 100%;
            background: #FFFFFF;
           /* position: fixed;
            top: 0.88rem;
            left:0;*/
            margin-top: 0.88rem;
             overflow: hidden;
        }

        .swiper1 {
            width: 100%;
            height:0.88rem;
            /*  border-top:1px solid #ccc;*/
            box-shadow: 0 4px 4px 0 rgba(155,166,173,0.15);
            margin-bottom:0.3rem;
            position: fixed;
            background-color: #FFF;

        }
        .swiper1 .selected {
            color:  #409af6;
        }
        .swiper1 .swiper-slide {
            text-align: center;
            font-size: 0.28rem;
            width: auto!important;
            padding: 0 0.25rem!important;
            height:0.88rem!important;
            line-height:0.88rem;
            -webkit-tap-highlight-color:rgba(255,255,255,0);
        }
        .swiper1 .swiper-slide i{
            display:block;
            width:0.54rem;
            height:0.04rem;
            background: #6BA9FC;
            border-radius: 0.28rem;
            margin:-0.04rem auto 0;
            visibility: hidden;
        }
        .swiper1 .selected i{
            visibility: visible;
        }
        .swiper2 {
            width: 100%;
        }
        .swiper2 .swiper-slide {
            /*   !*height: 530px;*!
               height: calc(90vh - 50px);*/
            background-color: #fff;
            color: #1A1A1A;
            box-sizing: border-box;
            overflow-x: hidden;
            -webkit-overflow-scrolling: touch;
        }
        .changeSlide  .mySlide{
            display: none;
        }

       .changeSlide  .mySlide:nth-child(1){
            display: block;
        }

        .swiper-wrapper{
            z-index: 0;
        }


    .changeSlide .swiper-slide{
        box-sizing: border-box;
        overflow-x:hidden ;
    }
    </style>
</head>
<body>
<div class="nav_box">
    <div class="nav">
        <a href="javascript:history.go(-1);" class="back"></a>
        信息披露
    </div>
</div>
<div class="container">
    <div class="swiper-container swiper1">
        <div class="swiper-wrapper">
            <div class="swiper-slide selected">承诺函<i></i></div>
            <div class="swiper-slide">合规进程<i></i></div>
            <div class="swiper-slide">公司简介<i></i></div>
            <div class="swiper-slide">组织架构<i></i></div>
            <div class="swiper-slide">大事记<i></i></div>
            <div class="swiper-slide">企业资质<i></i></div>
            <div class="swiper-slide">运营数据<i></i></div>
            <div class="swiper-slide">平台信息<i></i></div>
            <div class="swiper-slide">风控管理<i></i></div>
            <div class="swiper-slide">重大风险信息<i></i></div>
            <div class="swiper-slide">法律法规<i></i></div>
            <div class="swiper-slide">出借人教育<i></i></div>
            <div class="swiper-slide">联系我们<i></i></div>
        </div>
    </div>
    <!-- swiper2 -->
    <div class="swiper2 " style="width: 100%;margin-top:1.5rem;"><%--swiper-container swiper2--%>
        <div class="changeSlide"><%--swiper-wrapper--%>
            <div class="mySlide" >
                <img src="${base}/mobileNew/img/mypic/cnh.jpg" style="width:90%;margin:0 auto;display:block;" alt="">
            </div >
            <div class="mySlide" id="compliance">
                <div class="titleNav">
                    <span class="compliance"></span>
                    <span class="title">合规进程</span>
                </div>
                <div class="compliancePro">
                    <div class="to_greet">
                        <p>亲爱的升升投用户：</p>
                        <p>您好！<br>感谢您一直以来对升升投平台的关注和支持。</p>
                        <p>2016年8月24日，银监会发布《网络借贷信息中介机构业务活动管理暂行办法》。自此，互联网金融行业迎来首部规范性指导细则，也宣告互联网金融正式纳入国家金融管控体系。</p>
                        <p>随着互联网金融风险专项整治下一阶段工作展开，互金行业的环境“激浊扬清，端本清源”的步伐加快，《暂行办法》下发后，升升投平台积极迎合监管要求，依据《暂行办法》有关规定，有序推进平台合规化进程，希望为互联网金融行业健康发展贡献自己的一份力量。</p>
                        <p>升升投运营团队</p>
                    </div>
                    <div class="com_pub">《网络借贷暂行管理办法》共八章四十七条，其中涉及P2P平台的整改事项有20项，升升投平台目前已完成其中18项合规事宜，剩余2项也正在同步办理中，现将合规进程公示如下：</div>
                    <div class="com_tab">
                        <table>
                            <thead>
                            <tr><th>合规事项</th><th>《业务活动暂行办法》条款</th><th>状态</th></tr>
                            </thead>
                            <tbody>
                            <tr><td>网络借贷信息中介的定位</td><td>第一章第三条</td><td>已完成</td></tr>
                            <tr><td>申请ICP证和金融办备案</td><td>第二章第五条</td><td style="color:#409af6;">进行中</td></tr>
                            <tr><td>建立和完善信息管理制度</td><td>第三章第九条第四章第二十七条</td><td>已完成</td></tr>
                            <tr><td>下架禁令业务</td><td>第三章第十条</td><td>已完成</td></tr>
                            <tr><td>出借人和借款人实名</td><td>第三章第十一条</td><td>已完成</td></tr>
                            <tr><td>关于电子渠道以外经营环节的规定</td><td>第三章第十六条</td><td>已完成</td></tr>
                            <tr><td>借款额度限制：自然人借款不能超过20万，企业借款不能超过100万</td><td>第三章第十七条</td><td>已完成</td></tr>
                            <tr><td>信息系统定级备案、等级测试和建立应用级备灾系统设施</td><td>第三章第十八条</td><td>已完成</td></tr>
                            <tr><td>融资项目募集期限制不超过20个工作日</td><td>第三章第十九条</td><td>已完成</td></tr>
                            <tr><td>关于平台服务费的规定</td><td>第三章第二十条</td><td>已完成</td></tr>
                            <tr><td>接入征信系统提高授信质量</td><td>第三章第二十一条</td><td>已完成</td></tr>
                            <tr><td>
                                对出借人与借款人的基本信息和交易信息等使用电子签名，保障数据的真实性、完整性及电子签名、电子认证的法律效力</td><td>第三章第二十二条</td><td>已完成</td></tr>
                            <tr><td>交易数据保存和备份需超过5年</td><td>第三章第二十三条</td><td>已完成</td></tr>
                            <tr><td>不得干预出借人决策</td><td>第四章第二十五条</td><td>已完成</td></tr>
                            <tr><td>网络借贷风险及禁止性提示</td><td>第四章第二十六条</td><td>已完成</td></tr>
                            <tr><td>进行银行资金存管</td><td>第四章第二十八条</td><td>已完成</td></tr>
                            <tr><td>经营活动中涉及各方的信息披露</td><td>第五章第三十条</td><td>已完成</td></tr>
                            <tr><td>建立业务活动经营管理信息披露专栏</td><td>第五章第三十一条</td><td>已完成</td></tr>
                            <tr><td>保证披露的信息真实、准确、完整、及时、公平</td><td>第五章第三十二条</td><td>已完成</td></tr>
                            <tr><td>报送年度审计报告</td><td>第六章第三十八条</td><td style="color:#409af6;">进行中</td></tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="mySlide"  id="company">
                <div class="titleNav">
                    <span class="icon"></span>
                    <span class="title">公司简介</span>
                </div>
                <div class="companyContent">
                    <div  class="explain">
                        <p>升升投由中发展信（北京）投资管理有限公司倾力打造线上金融服务平台，公司于2016年4月在北京成立，企业实际缴纳注册资本金为1亿元。自监管细则出台以来，银行存管成为业内最为关注的话题。银行存管能有效的将用户的资金与平台自身运营资金进行隔离，避免出现资金池，同时需授权交易，银行全程监管，保障投资者的资金更安全。与中发展信（北京）投资管理有限公司对接资金存管的银行是云南的一家商业银行“富滇银行”。</p>
                        <p>富滇银行成立于2007年12月30日，是经中国银监会批准的，在对昆明市商业银行进行增资扩股和处置不良资产的基础上成立的 云南省第一家省级地方性股份制商业银行。2016年中国前100家银行排名在59名，富滇银行资金存管审核更为严格。目前与该行接触的平台有200多家，只有30家平台获得了准入，目前有9家平台已经正常上线。</p>
                        <p>升升投一直秉承”以人为本、高效专业“的核心理念，在互联网金融信息服务领域深耕细作，整合行业优势资源，基于互联网和大数据风控技术为用户提供简单便捷的金融信息服务。 未来，升升投将以为投资人提供多样化的投资产品、便捷的操作工具及优质的服务作为努力的方向，使投资人在获得资产稳健增值的同时享受到卓越的互联网金融信息服务所带来的优质体验，并将为全面构建、维护、推动中国金融创新与发展不遗余力！</p>
                        <p>升升投，您身边的财富好管家</p>
                    </div>
                    <div class="explain_1">
                        <p>经营理念</p>
                        <p>用品质、专业和效率构筑起我们的平台。唯有客户满意的笑容，方可成就展信人灿烂的明天。</p>
                        <p>在普惠金融的这条路上，可能会荆棘挡道，但我们展信人誓将秉承“以人为本、高效专业”的理念，一路前行！</p>
                    </div>
                    <div  class="explain_2">
                        <div>
                            <p>品质管理</p>
                            <p>用我们的诚实<br>筑您的幸福人生</p>
                            <p>用我们的专业<br>筑您的财富成长</p>
                        </div>
                        <div>
                            <p>企业文化</p>
                            <p>以人为本——尊重个人，重视沟通。专注专业——把严谨、真诚与热情，灌注于日常工作。精益求精——精细化的管理，人性化的服务，在通往目的地的路上我们追求卓越！</p>
                        </div>
                    </div>
                </div>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="licence"></span>
                    <span class="title">公司证照</span>
                </div>
                <div class="licenceModule">
                    <ul>
                        <li>
                            <p>名称</p> 中发展信（北京）投资管理有限公司
                        </li>
                        <li>
                            <p>类型</p>有限责任公司（自然人投资或控股）
                        </li>
                        <li>
                            <p>住所</p>北京市朝阳区东三环中路59号楼23层2702号
                        </li>
                        <li>
                            <p>法定代表人</p>李作林
                        </li>
                        <li>
                            <p>注册资本</p>10000万元
                        </li>
                        <li>
                            <p>成立日期</p>2016年04月13日
                        </li>
                        <li>
                            <p>经营期限</p>2016年04月13日 至 2036年04月12日
                        </li>
                        <li>
                            <p>经营范围</p> 投资管理；项目投资；资产管理；经济贸易咨询；（1、未经有关部门批准，不得以公开方式募集资金；2、不得公开开展证券类产品和金融衍生品交易活动；3、不得发放贷款；4、不得对所投资企业以外的其他企业提供担保；5、不得向投资者承诺投资本金不受损失或者承诺最低收益；企业依法自主选择经营项目开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）
                        </li>
                    </ul>
                </div>
            </div><!-- swiper-slide  slidePage swiper-no-swiping-->
            <div class="mySlide"  id="organizational">
                <div class="titleNav">
                    <span class="organizational"></span>
                    <span class="title">组织架构</span>
                </div>
                <div class="organizationalStructure"></div>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="practitioners"></span>
                    <span class="title">从业人员</span>
                </div>
                <div class="practitionersPic">
                    <div></div>
                    <div></div>
                </div>
            </div>
            <div class="mySlide"  id="honor">
               <%-- <div class="segmentingLine"></div>--%>
                <div class="titleNav">
                    <span class="history"></span>
                    <span class="title">发展历程</span>
                </div>
                <div class="developmentHistory">
                    <ul class="line">
                        <li>
                            <p>2018年03月</p>
                            <p>荣获“中国3.15诚信品牌企业”</p>
                        </li>
                        <li style=" height:1.8rem;">
                            <p>2018年01月</p>
                            <p>荣获“2017-2018年度中国互联网金融安全示范单位”</p>
                            <p>荣获“2017-2018年度中国互联网金融十大绿色创新企业”</p>
                        </li>
                        <li>
                            <p>2017年10月</p>
                            <p>公司线上平台升升投正式搭建</p>
                        </li>
                        <li>
                            <p>2017年08月</p>
                            <p>与富滇银行达成线上存管合作</p>
                        </li>
                        <li>
                            <p>2017年07月</p>
                            <p>划分全国三大业务区域</p>
                        </li>
                       <%-- <li>
                            <p>2017年01月</p>
                            <p>获工信部ICP备案号</p>
                        </li>--%>
                        <li>
                            <p>2016年10月</p>
                            <p>荣获“金融行业3A级诚信单位”</p>
                        </li>
                        <li>
                            <p>2016年08月</p>
                            <p>布局全国市场</p>
                        </li>
                        <li>
                            <p>2016年07月</p>
                            <p>升升投通联版上线</p>
                        </li>
                        <li>
                            <p>2016年04月</p>
                            <p>展信成立</p>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="mySlide" id="honor1">
                <div class="titleNav">
                    <span class="honor"></span>
                    <span class="title">荣誉资质</span>
                </div>
                <div class="License swiper5"  id="License">
                    <div class="swiper-wrapper ">
                        <%--<div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/01.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/01.jpg" alt="">
                        </div>--%>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/02.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/02.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/03.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/03.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/04.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/04.png" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/05.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/05.png" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/06.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/06.png" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/07.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/07.png" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/08.png" data-src="${base}/mobileNew/img/information/newHonor/newMax/08.png" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/15.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/15.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/16.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/16.jpg" alt="">
                        </div>
                        <div class="swiper-slide">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/17.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/17.jpg" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/09.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/09.png" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/10.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/10.png" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/11.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/11.jpg" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/12.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/12.jpg" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/13.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/13.jpg" alt="">
                        </div>
                        <div class="swiper-slide pic2">
                            <img src="${base}/mobileNew/img/information/newHonor/newMin/14.jpg" data-src="${base}/mobileNew/img/information/newHonor/newMax/14.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="mySlide"  id="operational_data">
                <a  class="eighteen_july" href="/webPhone/operationhomePage.do?end=2018-07-31"><img src="${base}/mobileNew/img/operate/eighteen_july.png" class="oper_data" alt="7月运营报告"></a>
                <a  class="eighteen_june" href="/webPhone/operationhomePage.do?end=2018-06-30"><img src="${base}/mobileNew/img/operate/eighteen_june.png" class="oper_data" alt="6月运营报告"></a>
                <a  class="eighteen_may" href="/webPhone/operationhomePage.do?end=2018-05-31"><img src="${base}/mobileNew/img/operate/eighteen_may.png" class="oper_data" alt="5月运营报告"></a>
                <a  class="eighteen_april" href="/webPhone/operationhomePage.do?end=2018-04-30"><img src="${base}/mobileNew/img/operate/eighteen_april.png" class="oper_data" alt="4月运营报告"></a>
            </div>
            <div class="mySlide"  id="platform">
                <div class="titleNav">
                    <span class="platform"></span>
                    <span class="title">平台信息</span>
                </div>
                <div class="platformMsg">
                    <ul>
                        <li><span>平台地址</span><span>https://www.zxzbol.com/</span></li>
                        <li><span>平台名称</span><span>升升投</span></li>
                        <li><span>隶属公司</span><span>中发展信（北京）投资管理有限公司</span></li>
                        <li><span>APP名称</span><span>升升投</span></li>
                        <li><span>微信公众号</span><span>升升投</span></li>
                        <li><span>官方微博</span><span>升升投</span></li>
                    </ul>
                </div>
            </div>
            <div class="mySlide"  id="winControl">
                <div class="titleNav">
                    <span class="blank"></span>
                    <span class="title">银行监管</span>
                </div>
                <div class="blankProcess"></div>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="transaction"></span>
                    <span class="title">交易资金</span>
                </div>
                <div class="transactionMoney swiper3">
                    <div class="swiper-wrapper " style="height:auto" >
                        <div class="swiper-slide">
                            <span class="division"></span>
                            <div>
                                <p>系统分账监管</p>
                                <p>接入富滇银行存管后，将由富滇银行对平台用户账户资金及平台自有运营资金进行分账监管，二者完全独立且相互隔离，平台无法接触用户账户资金。</p>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <span class="capital"></span>
                            <div>
                                <p>用户资金监管</p>
                                <p>用户进行充值、绑卡、提现等每一笔与资金有关的操作，均需要通过富滇银行资金存管账户，由富滇银行对用户资金信息进行管理，避免资金被挪用。</p>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <span class="power"></span>
                            <div>
                                <p>用户授权操作</p>
                                <p>用户需要开通富滇银行存管账户，并单独设立交易密码。在充值投资操作时，系统会验证密码，在得到用户的授权后，由银行根据交易指令进行。</p>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <span class="supervise"></span>
                            <div>
                                <p>银行监管交易流程</p>
                                <p>富滇银行对交易流程进行管理，并对所有的资金流水存档记录，确保借贷双方的资金流转和债权关系清晰明确。</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="transactionWind"></span>
                    <span class="title">风控流程</span>
                </div>
                <div class="winControlProcess"></div>
                <div class="segmentingLine"></div>
                <div class="titleNav">
                    <span class="system"></span>
                    <span class="title">风控体系</span>
                </div>
                <ul class="windPic">
                    <li><span></span><p>全程加密</p></li>
                    <li><span></span><p>系统隔离</p></li>
                    <li><span></span><p>异常检测</p></li>
                </ul>
                <div class="industry swiper4">
                    <div class="swiper-wrapper ">
                        <div class="swiper-slide"></div>
                        <div class="swiper-slide"></div>
                        <div class="swiper-slide"></div>
                    </div>
                </div>
            </div>
            <div class="mySlide"  id="majorRisk">
                <div class="titleNav">
                    <span class="risk"></span>
                    <span class="title">重大风险信息</span>
                </div>
                <div class="majorRisk">
                    <ul>
                        <li><span>重大事项</span><span>披露</span></li>
                        <li><span>合并、分立、解散或者申请破产</span><span>无</span></li>
                        <li><span>从业机构受到刑事处罚</span><span>无</span></li>
                        <li><span>公司被责令停业、整顿、关闭</span><span>无</span></li>
                        <li><span>重大诉讼或者仲裁事项</span><span>无</span></li>
                        <li><span>实际控制人与持股 5%以上的股东、董事、监事、高级管理人员涉及的重大诉讼、仲裁事项或重大行政处罚</span><span>无</span></li>
                        <li><span>不适用公司主要或者全部业务陷入停顿</span><span>无</span></li>
                        <li><span>存在欺诈、损害出借人利益等其他影响网络借贷信息中介机构经营活动的重大事项</span><span>无</span></li>
                        <li><span>公司涉及重大诉讼、仲裁，或涉嫌违法违规被有权机关调查，或受到刑事处罚、重大行政处罚</span><span>无</span></li>
                    </ul>
                </div>
            </div>
            <div class="mySlide" id="law">
                <div class="titleNav">
                    <span class="law"></span>
                    <span class="title">法律法规</span>
                </div>
                <div class="lawList" id="lawList">
                    <div class="lawLists">
                        <ul class="seeLaw">
                            <li><p>银监会《网络借贷资金存管业务指引》</p><a href="${base}/webPhone/law1homePage.do"><span>查看</span></a></li>
                            <li class="seeLawSketch">各银监局，各省、自治区、直辖市人民政府金融办（局），各大型银行、股份制银行，邮储银行，外资银行：为贯彻落实人民...</li>
                        </ul>
                        <div class="segmentingLine"></div>
                    </div>
                    <div class="lawLists">
                        <ul class="seeLaw">
                            <li><p>中国互联网金融协会《互联网金融信息 个体网络借贷》标准</p><a href="${base}/webPhone/law2homePage.do"><span>查看</span></a></li>
                            <li class="seeLawSketch">本标准提供了网络借贷信息中介机构（以下称“从业机构”）开展网络借贷信息中介业务活动信息披露的一般原则，以及信息...</li>
                        </ul>
                        <div class="segmentingLine"></div>
                    </div>
                    <div class="lawLists">
                        <ul class="seeLaw">
                            <li><p>银监会《网络借贷资金存管业务指引》</p><a href="${base}/webPhone/law3homePage.do"><span>查看</span></a></li>
                            <li class="seeLawSketch">《网络借贷信息中介备案登记管理指引》由银监会联合工信部、工商局联合发布的，为进一步规范网贷借贷信息中介机构的发...</li>
                        </ul>
                        <div class="segmentingLine"></div>
                    </div>
                    <div class="lawLists">
                        <ul class="seeLaw">
                            <li><p>保监会《互联网保险风险专项整治保险中介领域工作方案》</p><a href="${base}/webPhone/law4homePage.do"><span>查看</span></a></li>
                            <li class="seeLawSketch">为贯彻落实党中央、国务院决策部署，推动互联网保险风险专项整治工作有序开展，根据《关于促进互联网金融健康发展的指...</li>
                        </ul>
                        <div class="segmentingLine"></div>
                    </div>
                    <div class="lawLists">
                        <ul class="seeLaw">
                            <li><p>央行举头联合各金融监管部门《P2P网络借贷风险专项整治工作实施方案》</p><a href="${base}/webPhone/law5homePage.do"><span>查看</span></a></li>
                            <li class="seeLawSketch">各省、自治区、直辖市人民政府： 《P2P网络借贷风险专项整治工作实施方案》已经国务院同意，现印发给你们，请认真贯彻...</li>
                        </ul>
                        <div class="segmentingLine"></div>
                    </div>
                </div>
            </div>
            <div class="mySlide" id="cjrjy">
                <div class="titleNav">
                    <span class="fxts"></span>
                    <span class="title">风险提示</span>
                </div>
                <div class="con">
                    <p>您正在进行的是升升投平台提供网络借贷信息中介服务的网络借贷活动。升升投在此就网络借贷活动的风险及禁止性行为向您提示如下：</p>
                    <p>1、网络借贷是个体和个体之间通过互联网平台实现的直接借贷，您与借款人约定的且通过升升投平台展示的借款利率或参考年回报率不代表您最终实际取得的利息或回报，您出借的本金以及相应的利息存在不能够按期收回的风险；升升投不对您本金的收回、可获利息或回报金额作出任何承诺、保证。</p>
                    <p>2、您作为出借人，不得从事以下行为或存在以下情形：</p>
                    <p>（1）向网络借贷信息中介机构提供不真实、不准确、不完整的信息</p>
                    <p>（2）使用非法资金或非自有资金进行出借；</p>
                    <p>（3）不具备与进行网络借贷活动相适应的风险认知和承受能力，投资于与自身风险承受能力不匹配的融资项目；</p>
                    <p>（4）其他借贷合同及有关协议约定的禁止性行为。</p>
                    <p>3、您确认已经知悉网络借贷活动的风险，保证不存在从事网络借贷活动的禁止性行为，承诺具备与参与网络借贷活动相适应的投资风险意识、风险识别能力、拥有非保本类金融产品投资的经历并熟悉互联网，承诺自行承担借贷产生的本息损失。</p>
                </div>
            </div>
            <div class="mySlide" id="contact">
                <div class="titleNav">
                    <span class="contactUs"></span>
                    <span class="title">联系我们</span>
                </div>
                <div class="map">
                    <div></div>
                    <p>北京市朝阳区东三环中路59号富力双子座A座2702(室)</p>
                </div>
                <div class="segmentingLine"></div>
                <ul class="footerMsg">
                    <li><span>客服热线</span>400-9266-114</li>
                    <li><span>商务合作</span>400-9266-114</li>
                    <li><span>服务时间</span>周一到周五 9:00-18:00</li>
                    <li><span>温馨提示</span><p>如果您在使用升升投的过程中有任何疑问, 请您与升升投客服人员联系</p></li>
                </ul>
            </div>
        </div>
    </div>

    <%--弹框--%>
    <div id="outerdiv" style="position:fixed;/*top:0.88rem;*/top:0rem;left:0;background:rgba(100, 100, 100, 0.5);z-index:10;width:100%;height:100%;display:none;">
        <div id="innerdiv" style="position:absolute;">
            <i id="fork"></i>
            <img id="bigimg" style="border:1px solid #fff;" src="" />
        </div>
    </div>
</div>
<script src="${base}/mobileNew/js/jquery-1.11.3.js"></script>
<script src="${base}/mobileNew/js/fontSize.js"></script>
<script src="${base}/mobileNew/js/swiper.min.js"></script>
<%--<script src="${base}/mobileNew/js/bscroll.js"></script>
<script src="${base}/mobileNew/js/infor_details.js"></script>--%>
<script>
    $(function() {
        function setCurrentSlide(ele, index) {
            $(".swiper1 .swiper-slide").removeClass("selected");
            ele.addClass("selected");
            //swiper1.initialSlide=index;
        }

        var swiper1 = new Swiper('.swiper1', {
//					设置slider容器能够同时显示的slides数量(carousel模式)。
//					可以设置为number或者 'auto'则自动根据slides的宽度来设定数量。
//					loop模式下如果设置为'auto'还需要设置另外一个参数loopedSlides。
            slidesPerView: 4.2,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 10,//slide之间的距离（单位px）。
            // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            onTab: function(swiper) {
                var n = swiper1.clickedIndex;
            }
        });
        swiper1.slides.each(function(index, val) {
            var ele = $(this);
            ele.on("click", function() {
                setCurrentSlide(ele, index);
                $(".changeSlide  .mySlide:eq(" + index + ")").show().siblings().hide();
               // swiper2.slideTo(index, 500, false);
            });
        });

/*        var swiper2 = new Swiper('.swiper2', {
            //freeModeSticky  设置为true 滑动会自动贴合
            //direction: 'horizontal',//Slides的滑动方向，可设置水平(horizontal)或垂直(vertical)。
            loop: false,
//					effect : 'fade',//淡入
            //effect : 'cube',//方块
            //effect : 'coverflow',//3D流
//					effect : 'flip',//3D翻转
            autoHeight: true,//自动高度。设置为true时，wrapper和container会随着当前slide的高度而发生变化。
            //scrollbar: '.swiper-scrollbar',
            onSlideChangeEnd: function(swiper) {  //回调函数，swiper从一个slide过渡到另一个slide结束时执行。
                var n = swiper.activeIndex;
                setCurrentSlide($(".swiper1 .swiper-slide").eq(n), n);
                swiper1.slideTo(n, 500, false);
            }
        });*/


        var swiper3 = new Swiper('.swiper3', {
            width: window.innerWidth,
            slidesPerView:1.15,
            freeModeSticky: true,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 15,//slide之间的距离（单位px）。
            // freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            slidesOffsetBefore:15,
            slidesOffsetAfter:15
        });

        var swiper4 = new Swiper('.swiper4', {
            width: window.innerWidth,
            slidesPerView:1.15,
            freeModeSticky: true,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 15,//slide之间的距离（单位px）。
            //freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            slidesOffsetBefore:15,
            slidesOffsetAfter:15
        });

        var swiper5 = new Swiper('.swiper5', {
            width: window.innerWidth,
            slidesPerView:2.6,
            freeModeSticky: true,
            paginationClickable: true,//此参数设置为true时，点击分页器的指示点分页器会控制Swiper切换。
            spaceBetween: 15,//slide之间的距离（单位px）。
            //freeMode: true,//默认为false，普通模式：slide滑动时只滑动一格，并自动贴合wrapper，设置为true则变为free模式，slide会根据惯性滑动且不会贴合。
            loop: false,//是否可循环
            slidesOffsetBefore:15,
            slidesOffsetAfter:15
        });
    });
</script>
<script>
    $(function(){
        $(".License .swiper-wrapper  .swiper-slide").click(function(){
            var _this = $(this).find("img");
            imgShow("#outerdiv", "#innerdiv", "#bigimg", _this);
        });



        function imgShow(outerdiv, innerdiv, bigimg, _this){
            var src = _this.attr("data-src");
            $(bigimg).attr("src", src);

            $("<img/>").attr("src", src).load(function(){
               /* var windowW = $(window).width();*/
                var windowW =screen.availWidth;
               /* var windowH = $(window).height();*/
                var windowH = screen.availHeight;
                var realWidth = this.width;
                var realHeight = this.height;
                var imgWidth, imgHeight;
                var scale = 0.8;

                if(realHeight>windowH*scale) {
                    imgHeight = windowH*scale;
                    imgWidth = imgHeight/realHeight*realWidth;
                    if(imgWidth>windowW*scale) {
                        imgWidth = windowW*scale;
                    }
                } else if(realWidth>windowW*scale) {
                    imgWidth = windowW*scale;
                    imgHeight = imgWidth/realWidth*realHeight;
                    if(imgHeight>windowH*scale) {
                        imgHeight= windowH*scale;
                    }
                } else {
                    imgWidth = realWidth;
                    imgHeight = realHeight;
                }
                $(bigimg).css("width",imgWidth);
                /*$(bigimg).css({"width":imgWidth,"height":imgHeight});*/
                var imgHeight1=$(bigimg).width()/realWidth*realHeight;
                var w = (windowW-imgWidth)/2;
                var h = (windowH-imgHeight1)/2;
                $(innerdiv).css({"top":h, "left":w});
                $(outerdiv).fadeIn("slow");//淡入显示#outerdiv及.pimg
            });
        }
    });

    var u = navigator.userAgent;
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
    /* alert('是否是Android：'+isAndroid);
     alert('是否是iOS：'+isiOS);*/

    if(isAndroid){
        $(".swiper2 .slidePage").css("height","calc(90vh - 50px)");
    }

    if(isiOS){
        $(".swiper2 .slidePage").css("height","10.6rem");
    }



    $("#bigimg").on('touchstart',function(){//再次点击淡出消失弹出层
        $(outerdiv).fadeOut("slow");
    });

    $('#outerdiv').on('touchstart',function(e){
        e.stopPropagation();
        e.preventDefault();
    },false);
   /* shield.addEventListener("touchstart",function(e){
        e.stopPropagation();
        e.preventDefault();
    },false);*/

</script>
<script>
/*    $(function(){
        $.ajax({
            url : "/webPhone/lawhomePage.do?lid="+32+"&isApp="+1,
            type:"get",
            dataType:"json",
            success : function(data) {
                console.log();
                var lawList = data.data.listArticle;
                $.each(function() {
                    //循环获取数据
                     $("#ul").append();
                });

            }
        });
    });*/

    /*文字显示两行，多余部分省略号代替*/
   /* $(".seeLawSketch").html($(".seeLawSketch").html().slice(0,60)+'...');*/
</script>
</body>
</html>