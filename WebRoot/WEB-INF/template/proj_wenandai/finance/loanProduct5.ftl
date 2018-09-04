<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${systemConfig.metaTitle} - 我要融资</title>
<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
<script type="text/javascript" src="${base}/js/user/register.js"></script>
<script type="text/javascript" src="${base}/js/user/check.js"></script>
<script type="text/javascript" src="${base}/js/user/changeMoney.js"></script>
<script type="text/javascript">var m1="我要融资",m2="",m3="";</script>
 <script type="text/javascript">
		$(function(){
			$("#showOrhidde").click(function(){				
				if($(this).parents(".info-list").find(".ui-list-disc").is(":visible")){
					$(this).parents(".info-list").find(".ui-list-disc").hide();
					$(this).text("点击加载可选材料说明").css("background","","color","#fff");
					$(this).parents(".worm").css("display","none");
				}else{	
					$(this).parents(".info-list").find(".ui-list-disc").show();
					$(this).text("点击收回>>>").css("background","","color","#fff").css("background","#CECECE","color","#fff");
				}
				
			});	
			
			$("#show").click(function(){				
				if($(this).parents(".info-list").find(".ui-list-disc").is(":visible")){
					$(this).parents(".info-list").find(".ui-list-disc").hide();
					$("#showOrhidde").text("点击加载可选材料说明").css("background","");
					$(this).parents(".worm").css("display","none");
				}else{	
					$(this).parents(".info-list").find(".ui-list-disc").show();
				}
				
			});	
		
		}); 
		function me(){
			$(".xy-common-tips-wrap").attr("style","display:none;");
		}
    </script>

</head>
<body >
<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">

<!-- 头部结束 -->
<!--start mian-->
<div style="background:#f2f2f2; width:100%;">
<div onclick="me();" class="xy-common-tips-wrap" style="position: fixed;top: 0;left: 0;width: 100%;background: rgba(0,0,0,0.4);height: 100%;+background: #f6f6f6;background: #f6f6f6\0;z-index: 1000;">
	<div class="xy-common-tips" style="width: 984px;height: 615px;position: fixed;top: 50%;left: 50%;/*background: #fff;*/margin-left: -522px;margin-top: -320px;padding: 20px;border-radius: 12px;+height:615px;height:615px\0;+margin-top:-300px;margin-top:-300px\0;+padding:0;padding:0\0;">
	    <img src="${base}/theme/${systemConfig.theme}/images/sea-bg.png">
	</div>
</div>

<div class="main">
<!--start 网商贷-->

	<div class="grid1" id="attestSlide">
    	 <h1 class="tit tit4">青春贷<span class="big">(适用于大学在校生 )</span></h1>
         <div class="info-list-condition">
             <h2 class="mb15">申请条件</h2>
             <ul class="ui-list-disc">
                  <li><i></i>全日制专本硕博在读学生</li>
                  <li><i></i>中国公民(除港澳台)</li>
                  <li><i></i>18岁以上</li>
             </ul>
             <div class="btn">
              <a href="${base}/financePurchase/creditInfoFinancePurchase.do?id=${id}" class="btnbg">申请借款</a>	
			</div>
    	</div>
         <div class="info-list-material">
			<h2 class="mb15">必要申请资料</h2>
			<div id="attestSlide">
            <ul class="ui-list-disc">
            	
            	<li>
 					<h5>1. 身份认证</h5>
 					
                    <div class="idcard-div">
                        <span data="1" class="card-icon" tabindex="-1">
                          
                     
                 <a title="身份证正反面" href="${base}/theme/${systemConfig.theme}/images/samples/idcardpic.png">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png"  title="身份证" alt="身份证"/></a><br />
                        
                         <a title="手持身份证原照" href="${base}/theme/${systemConfig.theme}/images/samples/beautifulidcard.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon1.png" style="display:none"  title="身份证" alt="身份证"/></a>
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">1、本人身份证正、反两面，配合写有“20XX年</b>  
	                        <b class="qxs">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;XX月XX日申请借款”字样纸条拍照</b>
	                        <b class="qxs">2、本人手持身份证及纸条原照</b>
	                        <b class="qxs">★拍照后请放大查看，确保身份证自己清晰</b>
                        </span>
                        <span class="bg">同时提供</span>
           	      	</div>
           	    
                </li>
                
                <li>
                    <h5>2. 学生认证</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="学生认证" href="${base}/theme/${systemConfig.theme}/images/samples/xueshengzheng.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon16.jpg" title="教师资格认证" alt="教师资格认证" /></a><br />
                      
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
                      	    <b class="qxs">1、学生证详情页照片</b>  
                      	    <b class="qxs">2、学信网在线查询码截图</b>  
                        </span>
                        <span class="bg">同时提供</span>
                    </div>
                </li>
               <!-- <li>
                    <h5>3. 校园一卡通认证</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="校园一卡通认证" href="${base}/theme/${systemConfig.theme}/images/samples/yikatong.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon2.png" title="收入证明" alt="收入证明" /></a><br />
                         <a title="网银明细1" href="${base}/theme/${systemConfig.theme}/images/samples/bankdetails1.png">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon2.png" style="display:none"  title="收入证明" alt="收入证明" /></a><br />
                         <a title="网银明细2" href="${base}/theme/${systemConfig.theme}/images/samples/bankdetails2.png">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon2.png" style="display:none"  title="收入证明" alt="收入证明" /></a>
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
                      	    <b class="qxs">1、校园一卡通的正、反两面照片</b>  
                        </span>
                    </div>
                </li>  -->                                        
       		 </ul>
       		 </div>
       		 <div class="worm-tips">
	           <span class="txt">温馨提示：除进行上述认证外，建议您进行房产、购车认证，有助于提高您的审核通过率与信用额度。</span>
	     	 </div>

           <div class="info-list-material info-list">
			<h2 class="mb15">可选认证资料</h2>         
       		 <div class="worm" id="showOrhidde">
	           <a href="javascript:void(0)">点击加载可选材料说明</a>
	     	 </div>
	     	   <ul class="ui-list-disc"  style="display:none;">
	     	   <li>
                    <h5>4. 信用报告</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="信用报告" href="${base}/theme/${systemConfig.theme}/images/samples/creditreport.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon3.png" title="信用报告" alt="信用报告" /></a><br />
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">★ 信用报告有效期为7天</b>  
                        </span>
                    </div>
                </li>  
            <li>
 					<h5>5. 工作认证</h5>
 					
                    <div class="idcard-div idcard-div1">
                        <span data="1" class="card-icon" tabindex="-1">
                        <a title="劳动合同" href="${base}/theme/${systemConfig.theme}/images/samples/laborcontract1.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon4.png"  title="工作认证" alt="工作认证"/></a><br />
                        <a title="劳动合同" href="${base}/theme/${systemConfig.theme}/images/samples/laborcontract2.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon4.png" style="display:none"  title="工作认证" alt="工作认证"/></a>
                        <br />
                        <a title="劳动合同" href="${base}/theme/${systemConfig.theme}/images/samples/laborcontract3.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon4.png" style="display:none"  title="工作认证" alt="工作认证"/></a>
                        <br />
                        <a title="在职证明" href="${base}/theme/${systemConfig.theme}/images/samples/proof.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon4.png" style="display:none"  title="工作认证" alt="工作认证"/></a>
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">1、劳动合同（需加盖单位公章）</b>  
	                        <b class="qxs">2、在职证明（需一个月内开具）</b>
                        </span>
                        <span class="bg">二选其一</span>
           	      	</div>
           	    
                </li> 
                <li>
                    <h5>6. 购车认证</h5>
                    <div class="idcard-div idcard-div1">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="行驶本照片" href="${base}/theme/${systemConfig.theme}/images/samples/runningcard1.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon5.png" title="购车认证" alt="购车认证" /></a><br />
                        <a title="行驶本照片" href="${base}/theme/${systemConfig.theme}/images/samples/runningcard2.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon5.png" style="display:none"  title="购车认证" alt="购车认证" /></a>
                        <br />
                        <a title="本人与所购车辆照片" href="${base}/theme/${systemConfig.theme}/images/samples/carandperson.png">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon5.png" style="display:none"  title="购车认证" alt="购车认证" /></a>
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
                      	    <b class="qxs">1、行驶本照片</b>  
	                        <b class="qxs">2、本人于所购车辆照片（需完整显示）</b>
                        </span>
                        <span class="bg">同时提供</span>
                    </div>
                </li>
                <li>
                    <h5>7. 房产认证</h5>
                    <div class="idcard-div idcard-div1">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="房产认证" href="${base}/theme/${systemConfig.theme}/images/samples/room1.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon6.png" title="房产认证" alt="房产认证" /></a><br />
                         <a title="房产认证" href="${base}/theme/${systemConfig.theme}/images/samples/room2.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon6.png" style="display:none"  title="房产认证" alt="房产认证" /></a>
                        <br />
                         <a title="房产认证" href="${base}/theme/${systemConfig.theme}/images/samples/room3.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon6.png" style="display:none"  title="房产认证" alt="房产认证" /></a>
                        <br />
                         <a title="房产认证" href="${base}/theme/${systemConfig.theme}/images/samples/room4.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon6.png" style="display:none"  title="房产认证" alt="房产认证" /></a>
                        <br />
                         <a title="房产认证" href="${base}/theme/${systemConfig.theme}/images/samples/room5.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon6.png" style="display:none"  title="房产认证" alt="房产认证" /></a>
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">1、房屋所有权证照片</b>  
	                        <b class="qxs">2、购房预售合同</b>
                        </span>
                         <span class="bg">二选其一</span>
                    </div>
                </li> 
                  <li>
                    <h5>8. 结婚认证</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="结婚认证" href="${base}/theme/${systemConfig.theme}/images/samples/merry.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon7.png" title="结婚认证" alt="结婚认证" /></a><br />
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">本人结婚照片</b>  
                        </span>
                    </div>
                </li>  
                <li>
                    <h5>9. 学历认证</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="学历认证" href="${base}/theme/${systemConfig.theme}/images/samples/education.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon8.png" title="学历认证" alt="学历认证" /></a><br />
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">★毕业证照片</b>  
                        </span>
                    </div>
                </li> 
                
                <li>
                    <h5>10. 居住认证</h5>
                    <div class="idcard-div idcard-div1">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="居住认证" href="${base}/theme/${systemConfig.theme}/images/samples/houes.jpg">
                        <img src="${base}/theme/${systemConfig.theme}/images/large-icon10.png" title="居住认证" alt="居住认证" /></a><br />
                         <a title="居住认证" href="${base}/theme/${systemConfig.theme}/images/samples/adress.jpg">
                        <img  src="${base}/theme/${systemConfig.theme}/images/large-icon10.png" style="display:none;" title="居住认证" alt="居住认证" /></a><br />
                        <a title="居住认证" href="${base}/theme/${systemConfig.theme}/images/samples/adress1.jpg">
                        <img  src="${base}/theme/${systemConfig.theme}/images/large-icon10.png" style="display:none;" title="居住认证" alt="居住认证" /></a><br />
                        <b class="qxs">点击图标查看样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs">1、本人姓名登记的水、电、气最近3个月缴费单</b>  
	                        <b class="qxs">2、本人父母的房产证明，及证明本人和父母关系的证明材料</b>
                        </span>
                         <span class="bg">二选其一</span>
                    </div>
                </li>  
                 <li>
                    <h5>11. 手机实名认证</h5>
                    <div class="idcard-div" style="">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="手机实名认证" href="javascript:void(0);">
                        <img id="shouji"src="${base}/theme/${systemConfig.theme}/images/large-icon11.png" title="手机实名认证" alt="手机实名认证" /></a><br />
                        <b class="qxs">无样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs" style="width:395px;">★手机实名认证需要用户上传手机流水单照片</b>  
	                        <b class="qxs" style="width:395px;">流水单打印方法：在手机运营商营业厅，提供手机服务密码或机主身份证明即可打印。</b>
	                        <b class="qxs" style="width:395px;">照片要求：绑定的手机号码近3个月的手机详单原件的照片。如详单数量较多可分月打印并上传每月前5日部分（每月详单均需清晰显示机主手机号码）。</b>
                        </span>
                    </div>
                </li> 
                 <li>
                    <h5>12. 微博认证</h5>
                    <div class="idcard-div">
                        <span data="1" class="card-icon" style="cursor:auto">
                        <a title="微博认证" href="javascript:void(0);">
                        <img id="weibo" src="${base}/theme/${systemConfig.theme}/images/large-icon12.png" title="微博认证" alt="微博认证" /></a><br />
                        <b class="qxs">无样例</b>
                        </span>
                        <span class="line" ></span>
                      	<span data="2" class="card-icon1" tabindex="-1">                
	                        <b class="qxs"  style="width:395px;">请点击升升投新浪微博，<a href="http://weibo.com/wenandai" style="color:#0697da" target="_blank" >http://weibo.com/wenandai</a>，并加为关注</b>
	                        <b class="qxs"  style="width:395px;">或点击升升投QQ微博，<a href="http://t.qq.com/wenandai" style="color:#0697da" target="_blank" >http://t.qq.com/wenandai</a>，并加为关注</b>
	                        <b class="qxs"  style="width:395px;">且在微博上，转发一条最新的升升投的微博，并截图上传给升升投</b>
                        </span>
                    </div>
                </li>   
                 
                <div class="worm1" id="show">
	           <a href="javascript:void(0)">点击收回>>></a>
	     	 </div>               
       		 </ul>	</div> 
         </div>       
    </div>
 <!--end 网商贷-->
 
 <!--start 借款方式-->
 
   	 <div class="info-list-way">
        <h2 class="mb15">借款方式</h2>
        <ul class="ui-list">
             <li><span>借&nbsp;款&nbsp;额&nbsp;度&nbsp;</span>100-10,000</li>
             <li><span>借款年利率</span>9%-13%</li>
             <li><span>借&nbsp;款&nbsp;期&nbsp;限</span>3、6、9、12、15、18、21、24个月</li>
             <li><span>审&nbsp;核&nbsp;时&nbsp;间</span>1-3个工作日</li>
             <li><span>还&nbsp;款&nbsp;方&nbsp;式</span>等额本息，每月还款</li>
        </ul>
    </div>
    
 <!--end 借款方式-->
 
 
<!--start 费用说明-->
    <div class="info-list-fee">
                        <h2 class="mb15">费用说明</h2>
                        <ul class="ui-list">
                            <li class="range">
                               <h4>期初服务费<span style="display:inline-block;margin-left:23px;"><em class="wad_dknamemoney">收费列表如下</em>（由升升投平台收取）</span></h4>
                                <!-- <h5>由升升投平台收取</h5> -->
                                <!-- 期初服务费 开始 -->
                                    <div class="qcwrap wad_qcwrap">
                                       <p class="wad_qcmoney"><span class="spec">借款金额(元)</span><span class="spec1">500以下</span><span>500至1000(含)</span><span>1000至2000(含)</span><span>2000至3000(含)</span><span>3000至4000(含)</span><span>4000至5000(含)</span><span>5000至6000(含)</span><span>6000至10000(含)</span></p>
                                       <p class="wad_qcmoney1 wad_qcmoney"><span class="spec">手续费</span><span>￥30</span><span>￥70</span><span>￥160</span><span>￥210</span><span>￥280</span><span>￥370</span><span>￥480</span><span>￥680</span></p>
                                     </div>
                                <!-- 期初服务费 完
                                <table width="70%" class="table" border="0" cellspacing="0" cellpadding="0">
                                    <thead>
                                    <tr>
                                        <th>信用等级</th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s AA">AA</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s A">A</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s B">B</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s C">C</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s D">D</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s E">E</i></th>
                                        <th><i class="ui-creditlevel ui-creditlevel-s HR">HR</i></th>
                                    </tr>
                                    </thead>
                                    
                                </table> 
                                <p class="wad_dengjitishi">等级越高,有助于您快速获得理财人的资金支持<a href="${base}/article/helpcontentArticle.do?catId=264">如何提升信用等级？</a></p>-->
                            </li>
                            <li class="info">
                                <h4>每月还款额</h4>
                                <ul>
                                    <li><span>本金及利息</span>支付给理财人</li>
                                    <li><span>借款管理费</span>由升升投平台收取，借款本金的<em>0.5%</em></li>

                                </ul>
                            </li>
                        </ul>
                        <div class="review-button">
                          <a href="${base}/financePurchase/creditInfoFinancePurchase.do?id=${id}" class="ui-button ui-button1">申请借款</a>
                        </div>
                    </div>
                </div>
            </div>
      </div> 
 
 
<!--end 费用说明-->  
</div>
</div>
 
<!--start mian-->
 

<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">

<div id="blueimp-gallery" class="blueimp-gallery" style="display: none;">
        <div class="slides" style="width: 5464px;"></div>
        <h3 class="title"></h3>
        <a class="prev">‹</a>
        <a class="next">›</a>
        <a class="close">×</a>
        <a class="play-pause"></a>
        <ol class="indicator"></ol>
    </div>
    
	<script type="text/javascript">
		var attestSlide = document.getElementById('attestSlide');
		attestSlide.onclick = function (event) {
            event = event || window.event;
            
            var target = event.target || event.srcElement,
                link = target.src ? target.parentNode : target,
                options = {index: link, event: event},
                links = this.getElementsByTagName('a');
                if(target.id!="weibo"&&target.id!="shouji"){
                	if(target.tagName=='IMG'){
		            	blueimp.Gallery(links, options);
		            }
                }
               
        };
	</script>
</body>
</html>