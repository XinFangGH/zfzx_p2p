<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>${systemConfig.metaTitle} - 用户开始评估</title>
        <meta name="description" content="${systemConfig.metaTitle} - 用户开始评估,专业的互联网投融资平台,中发展信（北京）投资管理有限公司,展信资本">
        <meta name="keywords" content="${systemConfig.metaTitle} - 用户开始评估,投资 融资 债权转让,中发展信（北京）投资管理有限公司,展信资本">

		<#include "/WEB-INF/template/${systemConfig.theme}/layout/all.ftl">
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/topall.ftl">
		<link rel="stylesheet" type="text/css" href="${base}/theme/${systemConfig.theme}/css/funds.css?subversion='1.0'" />
		<script type="text/javascript" src="${base}/js/jQuery/jquery-1.8.2-min.js"></script>
		<script type="text/javascript" src="${base}/js/access/access.js"></script>
	</head>
	<body>
		<div> 
			<!--two-->
			  <div class="wrap mywrap">
			    <form action="${base}/user/saveAccessBpCustMember.do" method="post" id="survey">
			    	<input name="accessScore" id="accessScore" type="hidden" value="0">
			    	<input name="from" id="from" type="hidden" value="${from}">
			        <div class="stretchbox">
			        	<div class="out-survey">
			            	<dl class="surveybox">
			                	<dt>1、您的投资经验可以被概括为</dt>
			                    	<dd>
			                        	<ul class="list-surev">
			                            	<li>
			                                    <input name="radio_8" type="radio" class="radio" value="1">
			                                    <label> 有限：除银行活期账户和定期存款外，我基本没有其他投资经验</label>
			                                </li>
			                                <li>
			                                    <input name="radio_8" type="radio" class="radio" value="2">
			                                    <label> 一般：除银行活期账户和定期存款外，我购买过基金、保险等金融产品</label>
			                                </li> 
			                                <li>
			                                    <input name="radio_8" type="radio" class="radio" value="3">
			                                    <label> 较多：我在股票、基金等金融产品方面有一定的投资经验，但还需要进一步的指导</label>
			                                </li>
			                                <li>
			                                    <input name="radio_8" type="radio" class="radio" value="4">
			                                    <label> 丰富：我在股票、基金等金融产品方面有丰富的投资经验，倾向于自己做出投资决策</label>
			                                </li>
			                                <li>
			                                    <input name="radio_8" type="radio" class="radio" value="5">
			                                    <label> 非常丰富：我是一位非常有经验的投资者，参与过权证、期货或创业板等金融产品的交易或有5年以上股票、基金投资经验</label>
			                                </li> 
			                            </ul>
			                    	</dd>
			                </dl>
			            </div>
			            <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>2、您的金融知识可以被概括为</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	 <li>
			                                 <input name="radio_9" type="radio" class="radio" value="2">
			                                 <label> 现在或此前曾从事金融、经济或财会等与金融产品投资相关的工作超过两年；已取得金融、经济或财会等与金融产品投资相关专业学士以上学位；取得证券从业资格、期货从业资格、注册会计师证书（CPA）或注册金融分析师证书（CFA）中的一项及以上</label>
			                             </li> 
			                             <li>
			                                 <input name="radio_9" type="radio" class="radio" value="3">
			                                 <label> 掌握一定的金融知识和投资方法</label>
			                             </li>
			                             <li>
			                                 <input name="radio_9" type="radio" class="radio" value="4">
			                                 <label> 掌握丰富的金融知识和多种投资方法</label>
			                             </li>  
			                             <li>
			                                 <input name="radio_9" type="radio" class="radio" value="1">
			                                 <label> 不符合以上任何一项描述</label>
			                             </li> 
			                         </ul>
			                    </dd>
			                </dl>
						</div>
			            <div class="out-survey">
			            	<dl class="surveybox">
			                    <dt>3、您的财务状况可以被概括为</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                                <input name="radio_10" type="radio" class="radio" value="5">
			                                <label> 有较高收入，家庭收入大于支出，没有大额负债</label>
			                            </li>
			                            <li>
			                                <input name="radio_10" type="radio" class="radio" value="4">
			                                <label> 有稳定收入，家庭收支基本平衡，有消费信贷等短期信用债务</label>
			                            </li>
			                            <li>
			                                <input name="radio_10" type="radio" class="radio" value="3">
			                                <label> 有稳定收入，家庭收支平衡，有住房抵押贷款等长期定额债务</label>
			                            </li>
			                            <li>
			                                <input name="radio_10" type="radio" class="radio" value="2">
			                                <label> 有稳定收入，家庭支出大于收入，有造成生活压力的经济负担</label>
			                            </li>  
			                            <li>
			                                <input name="radio_10" type="radio" class="radio" value="1">
			                                <label> 无稳定收入，有亲朋之间借款</label>
			                            </li>  
			                       	</ul>
			                    </dd>
			                </dl>
			           </div>
			            <div class="out-survey">
			              <dl class="surveybox">
			                    <dt>4、您可用于理财投资的资产数额（包括银行存款等金融资产和非自住用途不动产）为</dt>
			                    <dd>
			                        <ul class="list-surev">
			                             <li>
			                                 <input name="radio_11" type="radio" class="radio" value="1">
			                                 <label> 不超过10万元人民币</label>
			                             </li>      
			                             <li>
			                                 <input name="radio_11" type="radio" class="radio" value="2">
			                                 <label> 10万-50万元（不含）人民币</label>
			                             </li>    
			                             <li>
			                                 <input name="radio_11" type="radio" class="radio" value="3">
			                                 <label> 50万-300万元（不含）人民币</label>
			                             </li>         
			                             <li>
			                                 <input name="radio_11" type="radio" class="radio" value="4">
			                                 <label> 300万元以上人民币</label>
			                             </li>     
			                    	</ul>
			                    </dd>
			                </dl>
			         
			          </div>
			           <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>5、最近您家庭预计进行金融投资的资金占家庭现有总资产(不含自住、自用房产及汽车等固定资产)的比例是</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                            	<input name="radio_12" type="radio" class="radio" value="1">
			                              	<label> 70%以上</label>
			                            </li>     
			                            <li>
			                                <input name="radio_12" type="radio" class="radio" value="2">
			                                <label> 50%-70%</label>
			                            </li>     
			                            <li>
			                                <input name="radio_12" type="radio" class="radio" value="3">
			                                <label> 30%－50%</label>
			                            </li>
			                            <li>
			                                <input name="radio_12" type="radio" class="radio" value="4">
			                                <label> 10%－30%</label>
			                            </li>
			                            <li>
			                                <input name="radio_12" type="radio" class="radio" value="5">
			                                <label> 10%以下</label>
			                            </li>
			                         </ul>
			                    </dd>
			                </dl>
			           </div>
			            <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>6、您用于金融投资的大部分资金不会用作其它用途的时间段为</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                                <input name="radio_13" type="radio" class="radio" value="1">
			                                <label> 短期——0到1年</label>
			                            </li>       
			                            <li>
			                                <input name="radio_13" type="radio" class="radio" value="2">
			                                <label> 中期——1到5年</label>
			                            </li>      
			                            <li>
			                                <input name="radio_13" type="radio" class="radio" value="3">
			                                <label> 长期——5年以上</label>
			                            </li>                           
			                    	</ul>
			                    </dd>
			                </dl>
			             </div>
			             <div class="out-survey">
			             	 <dl class="surveybox">
			                    <dt>7、您打算重点投资于哪些种类的投资品种 (可多选)</dt>
			                    <dd>
			                        <ul class="list-surev">
			                            <li>
			                                <input name="checkbox_14" type="checkbox" class="checkbox" value="1">
			                                <label> 债券、货币市场基金、债券基金、银行理财产品等固定收益类投资品种</label>
			                            </li>    
			                            <li>
			                                <input name="checkbox_14" type="checkbox" class="checkbox" value="1">
			                                <label> 股票、混合型基金、偏股型基金、股票型基金等权益类投资品种</label>
			                            </li>       
			                            <li>
			                               <input name="checkbox_14" type="checkbox" class="checkbox" value="1">
			                               <label> 融资融券、约定购回、股票质押回购</label>
			                            </li>         
			                            <li>
			                                <input name="checkbox_14" type="checkbox" class="checkbox" value="1">
			                                <label> 期货、期权、信托、基金专项计划</label>
			                            </li>       
			                            <li>
			                                <input name="checkbox_14" type="checkbox" class="checkbox" value="1">
			                                <label> 复杂或高风险金融产品、其他产品</label>
			                            </li>          
			                    	</ul>
			                    </dd>
			                </dl>
			            </div>
			            <div class="out-survey">
			               <dl class="surveybox">
			                    <dt>8、有一位投资者一个月内做了15笔交易（同一品种买卖各一次算一笔），您认为这样的交易频率</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                                <input name="radio_15" type="radio" class="radio" value="4">
			                                <label> 太高了</label>
			                            </li>    
			                            <li>
			                                <input name="radio_15" type="radio" class="radio" value="3">
			                                <label> 偏高</label>
			                            </li>     
			                            <li>
			                                <input name="radio_15" type="radio" class="radio" value="2">
			                                <label> 正常</label>
			                            </li>     
			                            <li>
			                                <input name="radio_15" type="radio" class="radio" value="1">
			                                <label> 偏低</label>
			                            </li>      
			                          </ul>
			                    </dd>
			                </dl>
			             </div>
			             <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>9、当您进行投资时，您的首要目标是</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                            	<input name="radio_16" type="radio" class="radio" value="1">
			                                <label> 资产保值，我不愿意承担任何投资风险</label>
			                            </li>    
			                            <li>
			                                <input name="radio_16" type="radio" class="radio" value="2">
			                                <label> 尽可能保证本金安全，不在乎收益率比较低</label>
			                            </li     
			                            ><li>
			                                <input name="radio_16" type="radio" class="radio" value="3">
			                                <label> 产生较多的收益，可以承担一定的投资风险</label>
			                            </li> 
			                            <li>
			                                <input name="radio_16" type="radio" class="radio" value="4">
			                                <label> 实现资产大幅增长，愿意承担很大的投资风险</label>
			                            </li>      
			                    	</ul>
			                    </dd>
			                </dl>
			              </div>
			            <div class="out-survey">
			                  <dl class="surveybox">
			                    <dt>10、您认为自己能承受的最大投资损失是多少</dt>
			                    <dd>
			                        <ul class="list-surev">
			                            <li>
			                                <input name="radio_17" type="radio" class="radio" value="1">
			                                <label> 10%以内</label>
			                            </li>          
			                            <li>
			                                <input name="radio_17" type="radio" class="radio" value="2">
			                                <label> 10%-20%</label>
			                            </li>            
			                            <li>
			                                <input name="radio_17" type="radio" class="radio" value="3">
			                                <label> 20%-30%</label>
			                            </li>            
			                            <li>
			                                 <input name="radio_17" type="radio" class="radio" value="4">
			                                 <label> 30%-50%</label>
			                            </li>             
			                            <li>
			                                <input name="radio_17" type="radio" class="radio" value="5">
			                                <label> 超过50%</label>
			                            </li>            
			                    	</ul>
			                    </dd>
			                </dl>
			            </div>
			            <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>11、您的最高学历是</dt>
			                    <dd>
			                        <ul class="list-surev">
			                        	<li>
			                            	<input name="radio_18" type="radio" class="radio" value="1">
			                                <label> 高中或以下</label>
			                            </li>    
			                            <li>
			                                <input name="radio_18" type="radio" class="radio" value="2">
			                                <label> 大学专科</label>
			                            </li>
			                            <li>
			                                <input name="radio_18" type="radio" class="radio" value="3">
			                                <label> 大学本科</label>
			                            </li>      
			                            <li>
			                               <input name="radio_18" type="radio" class="radio" value="4">
			                               <label> 硕士及以上</label>
			                            </li>          
			                   		</ul>
			                    </dd>
			                </dl>
			            </div>
			            <div class="out-survey">
			                <dl class="surveybox">
			                    <dt>12、您的年龄是</dt>
			                    <dd>
			                        <ul class="list-surev">
			                            <li>
			                            	<input name="radio_19" type="radio" class="radio" value="1">
			                                <label> 18-30岁</label>
			                            </li>
			                            <li>
			                                <input name="radio_19" type="radio" class="radio" value="2">
			                                <label> 31-40岁</label>
			                            </li>      
			                            <li>
			                                <input name="radio_19" type="radio" class="radio" value="3">
			                                <label> 41-50岁</label>
			                            </li>        
			                            <li>
			                                <input name="radio_19" type="radio" class="radio" value="4">
			                                <label> 51-60岁</label>
			                            </li>
			                            <li>
			                                <input name="radio_19" type="radio" class="radio" value="5">
			                                <label> 超过60岁</label>
			                            </li>
			                        </ul>
			                    </dd>
			                </dl>
			           </div>
			        </div>
			        <div class="btns ac pd-t30">
			            <a href="#" class="btn-c1 btn-h1" id="sb">提交问卷</a>
			        </div>
			    </form>
			</div>
		</div>
		<!-- copyright -->
		<#include "/WEB-INF/template/${systemConfig.theme}/layout/bottomall.ftl">
		</div>
	</body>
</html>