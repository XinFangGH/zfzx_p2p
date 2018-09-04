package com.hurong.credit.action.html;

import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.DynamicConfig;
import com.hurong.credit.config.Pager;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.creditFlow.fileForm.FileForm;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.article.ArticleCategoryService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.p2p.materials.PlWebShowMaterialsService;
import com.hurong.credit.util.MyUserSession;
import com.hurong.credit.util.TemplateConfigUtil;
import com.zhiwei.credit.model.p2p.article.Articlecategory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 *  北京金智万维软件有限公司   -- http://www.credit-software.com
 *	Copyright @ 2004 - 2010 Yuseen.com all rights reserved.京ICP备 05007290 号
 */
public class SingleAction  extends BaseAction{

	@Resource
	private PlWebShowMaterialsService plWebShowMaterialsService;

	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleCategoryService articleCategoryService;
    protected Pager pager;
    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
	/**
	 * 关于我们请求
	 * @return
	 */
	public String about(){
		//从session中取出当前用户
		/*BpCustMember mem = (BpCustMember) this.getSession().getAttribute(
				MyUserSession.MEMBEER_SESSION);
		if(mem!=null){*/
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.ABOUT).getTemplateFilePath());
		/*}else{
			this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOGIN).getTemplateFilePath());
		}*/
		return "freemarker";
	}
	/**
	 * 公司简介请求
	 * @return
	 */
	public String company(){
		//得到微博二维码
		List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
		this.getRequest().setAttribute("fileList", fileList);
		this.getSession().setAttribute("dh", 5);
		bpCustMember=(BpCustMember) this.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.COMPANY).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 公司资质
	 * @return
	 */
	public String ability(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.ABILITY).getTemplateFilePath());
		
		return "freemarker";
	}

	/**
	 * 组织架构
	 * @return
	 */
	public String structure(){

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.STRUCTURE).getTemplateFilePath());

		return "freemarker";
	}
	/**
	 * 大事记
	 * @return
	 */
	public String memorabilia(){

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MEMORABILIA).getTemplateFilePath());

		return "freemarker";
	}
	/**
	 * 企业资质
	 * @return
	 */
	public String enterprise(){

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.ENTERPRISE).getTemplateFilePath());

		return "freemarker";
	}


	/**
	 * 平台信息
	 * @return
	 */
	public String platform(){

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PLATFORM).getTemplateFilePath());

		return "freemarker";
	}




	/**
	 * 团队介绍
	 * @return
	 */
	public String team(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.TEAM).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 联系我们
	 * @return
	 */
	public String contact(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CONTACT).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 *理财攻略
	 * @return
	 */
	public String financialStrategy(){

		List<ArticleCategory> listParent = articleCategoryService.findByCateKey("hujin");
		List<ArticleCategory> listSencond = new ArrayList<>();
		List<Article> articleList = new ArrayList<>();
		for (ArticleCategory articleCategory : listParent) {
			List<ArticleCategory> list = articleCategoryService.findByParentId(articleCategory.getId());
			if (list != null && list.size()>0){
				listSencond.addAll(list);
			}
			List<Article> byCat5 = articleService.getByCat5(articleCategory);
			if (byCat5 != null && byCat5.size()>0){
				articleList.addAll(byCat5);
			}
		}


		this.getRequest().setAttribute("listParent",listParent);
		this.getRequest().setAttribute("listSencond",listSencond);
		this.getRequest().setAttribute("articleList",articleList);

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.FINANCIALSTRATEGY).getTemplateFilePath());

		return "freemarker";
	}

	/**
	 *互金理财
	 *
	 * @auther: XinFang
	 * @date: 2018/7/27 16:03
	 */
	public String mutualFinancial() {

		String id = this.getRequest().getParameter("id");
        List<ArticleCategory> listParent = articleCategoryService.findByCateKey("hujin");
		List<ArticleCategory> listThird = articleCategoryService.findByParentId(Long.valueOf(id));
		List<Article> articleList = new ArrayList<>();
		ArticleCategory parent = articleCategoryService.get(Long.valueOf(id));

		for (ArticleCategory articleCategory : listThird) {
			List<Article> list = articleService.getByCat(articleCategory);
			if (list != null && list.size() > 0) {
				articleList.addAll(list);
			}
		}
        Collections.sort(articleList,new Comparator<Article>(){
            public int compare(Article arg0, Article arg1) {
                return arg1.getCreateDate().compareTo(arg0.getCreateDate());
            }
        });

        if (pager == null) {
            pager = new Pager();
            pager.setPageSize(10);
        }
        pager.setTotalCount(articleList.size());

        int start = (pager.getPageNumber() - 1) * pager.getPageSize();

        List<Article> articleList1 = articleList.subList(start, pager.getTotalCount() - start > pager.getPageSize() ? start + pager.getPageSize() : pager.getTotalCount());

        pager.setList(articleList1);

		this.getRequest().setAttribute("lid", id);
		this.getRequest().setAttribute("listThird", listThird);
		this.getRequest().setAttribute("parent", parent);
		this.getRequest().setAttribute("listParent", listParent);

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.MUTUALFINANCIAL).getTemplateFilePath());

		return "freemarker";
	}


	/**
	 *三级分类跳转
	 *
	 * @auther: XinFang
	 * @date: 2018/7/30 9:30
	 */
	public String thirdFinancial() {

		String id = this.getRequest().getParameter("id");

		List<ArticleCategory> listParent = articleCategoryService.findByCateKey("hujin");
		ArticleCategory articleCategory = articleCategoryService.get(Long.valueOf(id));
		List<Article> articleList = articleService.getByCat(articleCategory);
		ArticleCategory parent = articleCategoryService.get(articleCategory.getParentId());
		List<ArticleCategory>	listThird = articleCategoryService.findByParentId(parent.getId());

        Collections.sort(articleList,new Comparator<Article>(){
            public int compare(Article arg0, Article arg1) {
                return arg1.getCreateDate().compareTo(arg0.getCreateDate());
            }
        });




        if (pager == null) {
            pager = new Pager();
            pager.setPageSize(10);
        }
        pager.setTotalCount(articleList.size());

        int start = (pager.getPageNumber() - 1) * pager.getPageSize();

        List<Article> articleList1 = articleList.subList(start, pager.getTotalCount() - start > pager.getPageSize() ? start + pager.getPageSize() : pager.getTotalCount());
        pager.setList(articleList1);

        this.getRequest().setAttribute("lid", id);


		this.getRequest().setAttribute("articleCategory", articleCategory);
		this.getRequest().setAttribute("listThird", listThird);
		this.getRequest().setAttribute("articleList", articleList);
		this.getRequest().setAttribute("parent", parent);
		this.getRequest().setAttribute("listParent", listParent);

		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.THIRDFINANCIAL).getTemplateFilePath());

		return "freemarker";
	}



	/**
	 * 安全保障
	 * @return
	 */
	public String secure(){
		//得到微博二维码
		List<FileForm> fileList = plWebShowMaterialsService.getImgUrl("system_p2p");
		this.getRequest().setAttribute("fileList", fileList);
		this.getSession().setAttribute("dh", "4");
		this.getSession().setAttribute("type", this.getRequest().getParameter("type"));
		this.getRequest().setAttribute("toWhere", this.getRequest().getParameter("toWhere"));
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SECURE).getTemplateFilePath());
		
		return "freemarker";
	}
	
	public String tttt(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SECURE).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 风险基金
	 * @return
	 */
	public String funds(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.FUNDS).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 交易安全
	 * @return
	 */
	public String billsafe(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.BILLSAFE).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 信用审核
	 * @return
	 */
	public String creditcheck(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CREDITCHECK).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 风险控制
	 * @return
	 */
	public String riskcontrol(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.RISKCONTROL).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * VIP服务
	 * @return
	 */
	public String vip(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.VIP).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 网站地图
	 * @return
	 */
	public String sitemap(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SITEMAP).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 常见问题
	 * @return
	 */
	public String faq(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.FAQ).getTemplateFilePath());
		return "freemarker";
	}

	/*2018/05/10 帮助  常见问题 刘艳南  start*/

	/**
	 * 登录注册
	 * @return
	 */
	public String loginreg(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.LOGINREG).getTemplateFilePath());
		return "freemarker";
	}



	/**
	 * 绑定银行卡
	 * @return
	 */
	public String bindcark(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.BINDCARK).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 充值提现
	 * @return
	 */
	public String recharges(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.RECHARGES).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 标的类
	 * @return
	 */
	public String standard(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.STANDARD).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 资金存管
	 * @return
	 */
	public String deposit(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.DEPOSIT).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 推荐浏览器
	 * @return
	 */
	public String recommend(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.RECOMMEND).getTemplateFilePath());
		return "freemarker";
	}

    /**
     * 平台操作说明
     * @return
     */
    public String description(){
        this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
                DynamicConfig.DESCRIPTION).getTemplateFilePath());
        return "freemarker";
    }




	/*2018/05/10 帮助  常见问题 刘艳南  end*/

	/**
	 * 合作机构
	 * @return
	 */
	public String partnerpic(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PARTNERPIC).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 友情链接
	 * @return
	 */
	public String partner_text(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.PARTNERTEXT).getTemplateFilePath());
		
		return "freemarker";
	}

	/**
	 * 投资流程
	 * 访问路径：
	 * @return
	 */
	public String newuserloan(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.NEWUSERlOAN).getTemplateFilePath());
	
	return "freemarker";
	}
	/**
	 * 借款流程
	 * 访问路径：
	 * @return
	 */
	public String newuserinvent(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.NEWUSERINVENT).getTemplateFilePath());
	
	return "freemarker";
	}
	

	/**
	 * 加入我们
	 * 访问路径：/html/joinusSingle.do
	 * @return
	 */
	public String joinus(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.JOINUS).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 安全理财
	 * 访问路径：/html/safeFinancingSingle.do
	 * @return
	 */
	public String safeFinancing(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SAFEFINANCING).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 媒体报道
	 * 访问路径：/html/mediaReportsSingle.do
	 * @return
	 */
	public String mediaReports(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MEDIAREPORTS).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 战略合作伙伴
	 * 访问路径：/html/strategySingle.do
	 * @return
	 */
	public String strategy(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.STRATEGY).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 账户问题
	 * @return
	 */
	public String account(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.ACCOUNT).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 投资问题
	 * @return
	 */
	public String inventProblem(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.INVENTPROBLEM).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 新手问题
	 * @return
	 */
	public String noriceask(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NORICEASK).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 安全问题
	 * @return
	 */
	public String safetyproblem(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SAFETYPROBLEM).getTemplateFilePath());
		
		return "freemarker";
	}
	
	/**
	 * 交易问题
	 * @return
	 */
	public String transaction(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.TRANSACTION).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 社会责任
	 * 访问路径：/html/socialdutySingle.do
	 * @return
	 */
	public String socialduty(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SOCIALDUTY).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 招贤纳士
	 * @return
	 */
	public String careers(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.CAREERS).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 借钱
	 * @return
	 */
	public String borrowmoney(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.BORROWMONEY).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 网上安全
	 * @return
	 */
	public String internetsecurity(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.INTERNETSECURITY).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 贷款文件
	 * @return
	 */
	public String loanfile(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOANFILE).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 贷款新手
	 * @return
	 */
	public String loannovice(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.LOANNOVICE).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 每月返款
	 * @return
	 */
	public String monthlyrepayment(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MONTHLYREPAYMENT).getTemplateFilePath());
		
		return "freemarker";
	}

	/**
	 * 团队管理
	 * @return
	 */
	public String managementteam(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.MANAGEMENTTEAM).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 专家顾问
	 * @return
	 */
	public String expertconsultant(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.EXPERTCONSULTANT).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * 监督报告
	 * @return
	 */
	public String supervisionreport(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.SUPERVISIONREPORT).getTemplateFilePath());
		
		return "freemarker";
	}
	
	public String newuserhelps(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWUSERHELPS).getTemplateFilePath());
		
		return "freemarker";
	}
	
	public String newuserloans(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
					DynamicConfig.NEWUSERLOANS).getTemplateFilePath());
		
		return "freemarker";
	}
	/**
	 * CEO致辞
	 * @return
	 */
	public String ceospeech(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.CEOSPEECH).getTemplateFilePath());
	
	return "freemarker";
	}
	/**
	 * 关于我们——借款产品
	 * @return
	 */
	public String loanProduct(){
		
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.LOANPRODUCT).getTemplateFilePath());
	
	return "freemarker";
	}
	
	/**
	 * ${base}/html/cpiSingle.do
	 * @return
	 */
	public String cpi(){
		this.setSuccessResultValue("/WEB-INF/template/common/computer/counter_Cpi.ftl");
		return "freemarker";
	}
	/**
	 * ${base}/html/incomeSingle.do
	 * @return
	 */
	public String income(){
		this.setSuccessResultValue("/WEB-INF/template/common/computer/counter_income.ftl");
		return "freemarker";
	}
	/**
	 * ${base}/html/housLoanSingle.do
	 * @return
	 */
	public String housLoan(){
		this.setSuccessResultValue("/WEB-INF/template/common/computer/counter_hous_loan.ftl");
		return "freemarker";
	}
	
	public String computer(){
		this.setSuccessResultValue("/WEB-INF/template/common/computer/computer.ftl");
		return "freemarker";
	}

	/**
	 * 风控管理
	 * @return
	 */
	public String sefity(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.SEFITY).getTemplateFilePath());
		return "freemarker";
	}


	/**
	 * 重大风险信息
	 * @return
	 */
	public String risk(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.RISK).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 承诺函
	 * @return
	 */
	public String commitment(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.COMMITMENT).getTemplateFilePath());
		return "freemarker";
	}
	/**
	 * 合规进程
	 * @return
	 */
	public String compliance(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.COMPLIANCE).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 运营数据
	 * @return
	 */
	public String operate(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.OPERATE).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 财务报告
	 * @return
	 */
	public String financial(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.FINANCIAL).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 法律法规
	 * @return
	 */
	public String regulations(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.REGULATIONS).getTemplateFilePath());
		return "freemarker";
	}

	/**
	 * 网站公告
	 * @return
	 */
	public String  bulletin(){
		this.setSuccessResultValue(TemplateConfigUtil.getDynamicConfig(
				DynamicConfig.BULLETIN).getTemplateFilePath());
		return "freemarker";
	}
}
