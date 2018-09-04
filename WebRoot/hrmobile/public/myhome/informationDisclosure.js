var flag=false,tel;
Ext.define('hrmobile.public.myhome.informationDisclosure', {
    extend: 'Ext.Container',
    name: 'informationDisclosure',
    constructor: function (config) {
    	config = config || {};
        tel=config.tel;
    	Ext.apply(config,{
			title:"<font color="+topColor+" style='font-size:"+topsize+"'>信息披露</font>",
    		width:"100%",
		    height:"100%",
            /*style:"margin-top:42px;",*/
		    fullscreen: true,
		    scrollable:{
		    	direction: 'vertical'
		    	//direction: 'false'
		    },
		    
		    items: [
                {
                    html:'<div class="infor_dis">' +
                            '<div class="header">' +
                                '<div class="swiper-wrapper">' +
                                    '<span class="swiper-slide on">公司简介</span><span class="swiper-slide">组织架构</span><span class="swiper-slide">大事记</span><span class="swiper-slide">平台信息</span>' +
                                    '<span class="swiper-slide">风控管理</span><span class="swiper-slide">重大风险信息</span><span class="swiper-slide">法律法规</span><span class="swiper-slide">联系我们</span>' +
                                '</div>'+
                            '</div>'+
                            '<div class="tabs-container swiper-container">' +
                                '<div class="swiper-wrapper">' +
                                    '<div class="swiper-slide gsjj">' +
                                        '<p class="tit">公司简介</p>'+
                                        '<div class="con">' +
                                            '<p>升升投由中发展信（北京）投资管理有限公司倾力打造线上金融服务平台，公司于2016年4月在北京成立，企业实际缴纳注册资本金为1亿元。自监管细则出台以来，银行存管成为业内最为关注的话题。银行存管能有效的将用户的资金与平台自身运营资金进行隔离，避免出现资金池，同时需授权交易，银行全程监管，保障投资者的资金更安全。与中发展信（北京）投资管理有限公司对接资金存管的银行是云南的一家商业银行“富滇银行”。</p>' +
                                            '<p>富滇银行成立于2007年12月30日，是经中国银监会批准的，在对昆明市商业银行进行增资扩股和处置不良资产的基础上成立的 云南省第一家省级地方性股份制商业银行。2016年中国前100家银行排名在59名，富滇银行资金存管审核更为严格。目前与该行接触的平台有200多家，只有30家平台获得了准入，目前有9家平台已经正常上线。</p>' +
                                            '<p>升升投一直秉承”以人为本、高效专业“的核心理念，在互联网金融信息服务领域深耕细作，整合行业优势资源，基于互联网和大数据风控技术为用户提供简单便捷的金融信息服务。 未来，升升投将以为投资人提供多样化的投资产品、便捷的操作工具及优质的服务作为努力的方向，使投资人在获得资产稳健增值的同时享受到卓越的互联网金融信息服务所带来的优质体验，并将为全面构建、维护、推动中国金融创新与发展不遗余力！</p>' +
                                            '<p>升升投，您身边的财富好管家</p>' +
                                        '</div>'+
                                        '<div class="dt">' +
                                             '<h2>经营理念</h2>' +
                                             '<p>用品质、专业和效率构筑起我们的平台。唯有客户满意的笑容，方可成就展信人灿烂的明天。<br><br>在普惠金融的这条路上，可能会荆棘挡道，但我们展信人誓将秉承“以人为本、高效专业”的理念，一路前行！</p>' +
                                        '</div>'+
                                        '<div class="db">' +
                                            '<div class="pl"><h2>品质管理</h2><p>用我们的诚实<br>祝您的幸福人生 <br><br>用我们的专业<br>祝您的财富成长</p></div>' +
                                            '<div class="pr"><h2>企业文化</h2><p>以人为本，尊重个人，重视沟通。专注专业 把严谨、真诚与热情，灌注于日常工作。精益求精 精细化的管理，人性化的服务，在通往目的地的路上我们追求卓越！</p></div>'+
                                        '</div>'+
                                        '<div class="line"></div>'+
                                         '<p class="tit tit2">公司证照</p>'+
                                        '<ul class="lists">' +
                                            '<li><span class="label">名称</span><span class="txt">中发展信（北京）投资管理有限公司</span></li>'+
                                            '<li><span class="label">类型</span><span class="txt">有限责任公司（自然人投资或控股）</span></li>'+
                                            '<li><span class="label">住所</span><span class="txt">北京市朝阳区酒仙桥路甲16号9层9603</span></li>'+
                                            '<li><span class="label">法定代表人</span><span class="txt">李作林</span></li>'+
                                            '<li><span class="label">注册资本</span><span class="txt">10000万元</span></li>'+
                                            '<li><span class="label">成立日期</span><span class="txt">2016年04月13日</span></li>'+
                                            '<li><span class="label">经营期限</span><span class="txt">2016年04月13日 至 2036年04月12日</span></li>'+
                                            '<li><span class="label">经营范围</span><span class="txt"> 投资管理；项目投资；资产管理；经济贸易咨询；（“1、未经有关部门批准，不得已公开方式募集资金；不得公开开展证券类产品和金融衍生品交易活动；3、不得发放贷款；4、不得对所投资企业以外的其他企业提供担保；5、不得向投资者承诺投资本金不受损失或者承诺最低收益”；企业依法自主选择经营项目开展经营活动；依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）</span></li>'+
                                        '</ul>'+
                                    '</div>'+
                                    '<div class="swiper-slide">组织架构</div>'+
                                    '<div class="swiper-slide">大事记</div>'+
                                    '<div class="swiper-slide">平台信息</div>'+
                                    '<div class="swiper-slide">风控管理</div>'+
                                    '<div class="swiper-slide">重大风险信息</div>'+
                                    '<div class="swiper-slide">法律法规</div>'+
                                    '<div class="swiper-slide">联系我们</div>'+
                                '</div>'+
                            '</div>'+
                         '</div>'
                }

                ],
		    	listeners :{
			        show:function(){

                    },
					painted:function(){
                        //头部tab切换
                        var navSwiper = new Swiper('.header', {
                            freeMode: true,
                            slidesPerView: 'auto',
                            freeModeSticky: true
                        });
                        var tabsSwiper = new Swiper('.tabs-container', {
                            speed: 500,
                            on: {
                                slideChangeTransitionStart: function() {
                                    $(".header .swiper-slide").removeClass('on');
                                    $(".header .swiper-slide").eq(this.activeIndex).addClass('on');
                                }
                            }
                        })
                        $(".header").on('click','.swiper-slide',function() {
                            $(".header .swiper-slide").removeClass('on');
                            $(this).addClass('on');
                            tabsSwiper.slideTo($(this).index())
                        })

					}
		    	}
	    })
    	this.callParent([config]);
    }
});
