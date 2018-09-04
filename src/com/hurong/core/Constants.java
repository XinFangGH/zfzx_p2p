package com.hurong.core;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/

/**
 * 全应用程序的常量
 * @author csx
 *
 */
public class Constants {
	
	/**
	 * 项目状态:
	 * 0-表示进行中;
	 * 1-表示贷中(流程已正常结束:对应流程状态为2);
	 * 2-表示已完成(已正常还款);
	 * 3-表示已终止(对应流程状态为3);
	 * 
	 * 4-展期申请中(未审批-小贷特有);
	 * 5-通过展期申请(展期状态-小贷特有);
	 * 6-未通过展期申请(小贷特有)。
	 * 8-违约项目。
	 * 10-(已挂起项目：与多个不同的项目表以及和任务相关的表状态一致,都为10,避免不同的地方是不同的值,而本身所代表的意思一样。)
	 * 
	 * 在流程结束或终止流程需要改变项目的状态,在此定义全局常量。
	 * add by lu 2011.12.02 start
	 */
	public static final Short PROJECT_STATUS_ACTIVITY=0;
	
	public static final Short PROJECT_STATUS_MIDDLE=1;
	
	public static final Short PROJECT_STATUS_COMPLETE=2;
	
	public static final Short PROJECT_STATUS_STOP=3;
	
	public static final Short PROJECT_POSTPONED_STATUS_ACT=4;
	
	public static final Short PROJECT_POSTPONED_STATUS_PASS=5;
	
	public static final Short PROJECT_POSTPONED_STATUS_REFUSE=6;
	
//	public static final Short PROJECT_SUPERVISE_STATUS_MIDDLE=7;
	
	public static final Short PROJECT_STATUS_BREAKACONTRACT=8;
	
//	public static final Short PROJECT_SUPERVISE_STATUS_COMPLETE=9;
	
	public static final Short PROJECT_STATUS_SUSPENDED=10;
	
	
	/**
	 * add by lu 2011.12.02 end
	 */
	
	/**
	 * 网关直付
	 */
	public static final String THIRDPAY_FLG1="1";
	/**
	 * 托管账户
	 */
	public static final String THIRDPAY_FLG0="0";
	/**
	 * 代表禁用
	 */
	public static final Short FLAG_DISABLE = 0;
	/**
	 * 代表激活
	 */
	public static final Short FLAG_ACTIVATION = 1;
	/**
	 * 代表记录删除
	 */
	public static final Short FLAG_DELETED=1;
	/**
	 * 代表未删除记录
	 */
	public static final Short FLAG_UNDELETED=0;
	
	/**
	 * 应用程序的格式化符
	 */
	/**
	 * 通过webservices 生成静态页面 传输的数据格式
	 */
	public static final String BUILDHTML_FORMAT_JSON="application/json";
    public static final String BUILDHTML_FORMAT_XML = "application/xml";  
	public static final String DATE_FORMAT_FULL="yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD="yyyy-MM-dd"; 
	
	/**
	 * 流程启动者，可用于在流程定义使用
	 */
	//public static final String FLOW_START_USER="flowStartUser";
//	/**
//	 * 流程启动ID
//	 */
//	public static final String FLOW_START_USERID="flowStartUserId";
	
	/**
	 * 流程任务执行过程中，指定了某人执行该任务，该标识会存储于Variable变量的Map中，随流程进入下一步
	 */
	public static final String FLOW_ASSIGN_ID="flowAssignId";
	
	/**
	 * 为会签任务指定多个用户
	 */
	public static final String FLOW_SIGN_USERIDS="signUserIds";
	
	/**
	 * 若当前流程节点分配的节点为流程启动者，其userId则为以下值
	 */
	public static final String FLOW_START_ID="__start";
	/**
	 * 若当前流程分配置为当前启动者的上司，则其对应的ID为以下值
	 */
	public static final String FLOW_SUPER_ID="__super";
	
	/**
	 * 请假流程的key
	 */
	public static final String FLOW_LEAVE_KEY="ReqHolidayOut";
	
	/**
	 * 流程下一步跳转列表常量
	 */
	public static final String FLOW_NEXT_TRANS="nextTrans";
	/**
	 * 公文ID
	 */
	public static final String ARCHIES_ARCHIVESID="archives.archivesId";
	/**
	 * 公司LOGO路径
	 */
	public static final String COMPANY_LOGO="app.logoPath";
	/**
	 * 默认的LOGO
	 */
	public static final String DEFAULT_LOGO="/images/ht-logo-dynamic.png";
	/**
	 * 通过审核
	 */
	public static final Short FLAG_PASS=1;
	/**
	 * 不通过审核
	 */
	public static final Short FLAG_NOTPASS=2;
	
	/**
	 * 启用
	 */
	public static final Short ENABLED=1;
	/**
	 * 未启用
	 */
	public static final Short UNENABLED=0;
	
	/**
	 * 易生第三方支付（资金池第三方）
	 */
	public static final String EASYPAY="easypayConfig";

	/**
	 * 国付宝支付（资金池第三方）
	 */
	public static final String GOPAY="gopayConfig";
	/**
	 * 富有支付（富有金账户，富有资金池）
	 */
	public static final String FUIOU="fuiouConfig";
	/**
	 * 双乾支付 （资金账户托管模式）
	 */
	public static final String MONEYMOREMORE="moneyMoreMoreConfig";
	/**
	 * 汇付天下支付（资金账户托管模式）
	 */
	public static final String HUIFU="huifuConfig";

	
	/**
	 * 易宝第三方支付（资金账户托管模式）
	 */
	public static final String YEEPAY="yeepayConfig";
	/**

	 * 联动优势第三方支付（资金账户托管模式）
	 */
	public static final String UMPAY="umpayConfig"; 
	/**
	 * 新浪支付第三方支付（资金账户托管模式）
	 */
	public static final String SINAPAY="sinapayConfig"; 
	/**

	 * 通联
	 */
	public static final String ALLINPAY="allinpayConfig";

	/**

	 * 系统 默认 
	 */
	public static final String ZHIWEI="zhiweiConfig";
	/**
	 * 成功标识
	 */
	public static final String SUCCESSFLAG="8888";
	/**
	 * 失败标识
	 */
	public static final String FAILDFLAG="0000";
	
	public static final String CHAR_GBK="GBK";
	public static final String CHAR_UTF="UTF-8";
	
	
	//用户注册
	public static String PUB_REG = "PUB10101";
	public static String PUB_REG_NOT_EXIST = "0";
	public static String PUB_REG_EXITS="1";
	public static String PUB_REG_EMAIL="11010101";//PUB10102
	
	//添加用户
	public static String PUB_ADD_USER = "PUB10102";
	public static String PUB_ADD_SUC = "0";
	public static String PUB_ADD_FAIL = "1";
	
	//恒安致富金融
	public static String PROJ_SYS = "proj_hengancaifu";
	
	/**
	 * 中铭常规流程：(zmn:zmNormalFlow常规流程)
	 *节点中文名称			节点对应的顺序及key
	 *
	 *初步审核				10_zmnRudimentaryCheck
	 *项目尽调上报材料		20_zmnProjectMaterial
	 *业务主管审核上报材料	30_zmnCheckMaterial
	 *风险主管调配任务		40_zmnAllocateTask
	 *保前综合分析			50_zmnBefSyntheticalAnalyse
	 *风险主管审核			60_zmnRiskManagerCheck
	 *审保会集体决议			70_zmnSbhDraftResolution
	 *审保会决议确认			80_zmnSbhConfirm
	 *总裁审批				1000_zmnPresidentCheck
	 *合同制作				1010_zmnMakeContract
	 *法务审核合同			1020_zmnCheckContract
	 *合同签署确认			1030_zmnContractConfirm
	 *银行贷款信息登记		1040_zmnBankRegister
	 *项目保前归档			2000_zmnProjectEndConfirm
	 *保中监管及担保责任解除函获取		2000_zmnProjectInMiddle
	 *反担保措施登记			2000_zmnMortgageRegister
	 *违约处理				2010_zmnBreakAContractHandle

	 ************************************************************
	 *主管意见流程(sls:smallLoanSunFlow小贷子流程)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *主管意见			10_slsDirectorOpinion

	 ************************************************************
	 *小贷展期流程(sls:smallLoanSunFlow小贷子流程)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *项目展期审批		10_slsPostponed

	 ************************************************************
	 *企业经营型贷款担保常规流程(gln:guaranteeNormalFlow)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *业务经理尽职调查	10_glnProjectManagerSurveyed
	 *业务主管审核上报材料	20_glnDirectorCheckMaterial
	 *保前风险综合分析	30_glnBeforeSynthesizeAnalyse
	 *出具拟担保意向书	30_glnGuaranteeLetterOfIntent
	 *风险主管审核		40_glnRisksSupervisorApprove
	 *上传会议纪要		50_glnUploadMeettingSummary
	 *审保会决议			50_glnExaminationArrangement
	 *出具对外担保函		60_glnMakeOutOrientedIntent
	 *合同制作			70_glnMakeContract
	 *合同签署确认		80_glnSignContract
	 *业务经理登记		90_glnBusinessManagerRegister
	 *保中监管			2000_glnProjectSupervise
	 *反担保措施登记		2000_glnCounterGuaranteeRegister
	 *财务经理登记		2000_glnFinanceManagerRegister
	 *项目保前归档		2010_glnProjectBgEndConfirm
	 *获取担保责任解除函	2020_glnGetGuaranteeLiabilityUnchainLetter
	 *退还保证金			2030_glnHandBackCautionMoney
	 *解除反担保措施		2030_glnUnchainCounterGuarantee
	 *项目归档			2040_glnProjectEndConfirm

	 ************************************************************
	 *资金融资(flf:financingFlow小贷子流程)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *项目信息			10_flProjectInfo

	 ************************************************************
	 *小额贷款快速流程(slf:smallLoanFastFlow)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *项目信息			10_slfProjectInfo
	 *业务主管审批		20_slfBusinessDirectorAuditing
	 *风险主管审批		30_slfRiskDirectorAuditing
	 *董事审批			40_slfChairmanApprove
	 *合同制作与签署		1000_slfDraftContract
	 *款项计划确认		1010_slfMakeFundPlan
	 *归档确认			1020_slfConfirmProjectEnd
	 *贷中监管			2000_slfProjectSupervise
	 *项目完成			2010_slfProjectComplete

	 ************************************************************
	 *小额贷款常规流程(sln:smallLoanNormalFlow)
	 *节点中文名称		节点对应的顺序及key
	 *
	 *尽职调查			10_slnProjectManagerSurveyed
	 *业务主管审核		20_slnBusinessDirectorAuditing
	 *风险部综合分析		30_slnRiskManagerAnalyse
	 *上传会议纪要		40_slnUploadMeettingSummary
	 *审贷会决议			40_slnExaminationArrangement
	 *合同制作			50_slnDraftContract
	 *合同签署确认		1000_slnSignContract
	 *办理抵质押物手续	1010_slnCounterGuaranteeRegister
	 *款项计划确认		1020_slnMakeFundPlan
	 *归档确认			1030_slnConfirmProjectEnd
	 *贷中监管			2000_slnProjectSupervise
	 *项目完成			2010_slnProjectComplete
	 */
	
	
	/**
	 * 小额贷款-流程任务期限常量
	 */

	/*节点 时限【小额贷款-常规流程】*/
	/*public static final int L_PL_N01_PROJECTMANAGERSURVEYED = 5;//尽职调查
	public static final int L_PL_N02_RISKMANAGERANALYSE = 5;//风险部综合分析
	public static final int L_PL_N03_UPLOADMEETTINGSUMMARY = 5;//上传会议纪要
	public static final int L_PL_N04_EXAMINATIONARRANGEMENT = 5;//审贷会决议
	public static final int L_PL_N05_MAKEFUNDPLAN = 5;//款项计划确认
	public static final int L_PL_N06_DRAFTCONTRACT = 3;//合同制作
	public static final int L_PL_N07_SIGNCONTRACT = 3;//合同签署确认
	public static final int L_PL_N08_COUNTERGUARANTEEREGISTER = 3;//办理抵质押物手续
	public static final int L_PL_N09_CONFIRMPROJECTEND = 3;//归档确认
	//public static final int L_PL_N010_PROJECTLOANS = 3;//项目放款
	public static final int L_PL_N010_BUSINESSDIRECTORAUDITING = 3;//业务主管审核
	public static final int L_PL_N011_DIRECTOROPINION = 2;//主管意见
	public static final int L_PL_N012_PROJECTSUPERVISE = 5;//贷中监管
	public static final int L_PL_N013_PROJECTCOMPLETE = 3;//项目完成


	节点 时限【小额贷款-快速流程】
	public static final int L_FL_N01_GATHERPROJECTINFO = 5;//项目信息
	public static final int L_FL_N02_MAKEFUNDPLAN = 5;//款项计划确认
	public static final int L_FL_N03_DRAFTCONTRACT = 3;//合同制作
	public static final int L_FL_N04_CONFIRMPROJECTEND = 3;//归档确认
	public static final int L_FL_N05_PROJECTSUPERVISE = 5;//贷中监管
	public static final int L_FL_N06_PROJECTCOMPLETE = 3;//项目完成
	public static final int L_FL_N07_BUSINESSMANAGERAPPROVE = 3;//业务主管审批
	public static final int L_FL_N08_RISKMANAGERAPPROVE = 3;//风险主管审批
	public static final int L_FL_N09_CHAIRMANAPPROVE = 3;//董事审批
	

	节点 时限【小额贷款-融资流程】
	public static final int L_F_N01_FINANCINGPROJECT = 3;//融资项目
	
	
	节点 中英文对照【小额贷款-常规流程】
	public static final String CN_PL_N01_projectManagerSurveyed = "尽职调查";
	public static final String CN_PL_N02_riskManagerAnalyse = "风险部综合分析";
	public static final String CN_PL_N03_uploadMeettingSummary = "上传会议纪要";
	public static final String CN_PL_N04_examinationArrangement = "审贷会决议";
	public static final String CN_PL_N05_makeFundPlan = "款项计划确认";
	public static final String CN_PL_N06_draftContract = "合同制作";
	public static final String CN_PL_N07_signContract = "合同签署确认";
	public static final String CN_PL_N08_counterGuaranteeRegister = "办理抵质押物手续";
	public static final String CN_PL_N09_confirmProjectEnd = "归档确认";
	public static final String CN_PL_N010_businessDirectorAuditing = "业务主管审核";
	public static final String CN_PL_N011_directorOpinion = "主管意见";
	public static final String CN_PL_N012_projectSupervise = "贷中监管";
	public static final String CN_PL_N013_projectComplete = "项目完成";
	
	//public static final String CN_PL_N010_projectLoans = "项目放款";
	
	节点 中英文对照【小额贷款-快速流程】
	public static final String CN_FL_N01_gatherProjectInfo = "项目信息";
	public static final String CN_FL_N02_makeFundPlan = "款项计划确认";
	public static final String CN_FL_N03_draftContract = "合同制作";
	public static final String CN_FL_N04_confirmProjectEnd = "归档确认";
	public static final String CN_FL_N05_projectSupervise = "贷中监管";
	public static final String CN_FL_N06_projectComplete = "项目完成";
	public static final String CN_FL_N07_businessManagerApprove = "业务主管审批";
	public static final String CN_FL_N08_riskManagerApprove = "风险主管审批";
	public static final String CN_FL_N09_chairmanApprove = "董事审批";
	
	节点 中英文对照【小额贷款-融资流程】
	public static final String CN_F_N01_financingProject = "项目信息";
	
	
	*//**
	 * 担保贷款-流程任务期限常量
	 *//*
	节点 时限【担保贷款-常规流程】
	public static final int G_EB_N01_PROJECTMANAGERSURVEYED = 20;//业务经理尽职调查
	public static final int G_EB_N02_DIRECTORCHECKMATERIAL = 5;//业务主管审核上报材料
	public static final int G_EB_N03_BEFORESYNTHESIZEANALYSE = 10;//保前综合分析
	public static final int G_EB_N04_GUARANTEELETTEROFINTENT = 5;//出具拟担保意向书
	public static final int G_EB_N05_RISKSSUPERVISORAPPROVE = 5;//风险主管审核
	public static final int G_EB_N06_EXAMINATIONARRANGEMENT = 5;//审保会决议
	public static final int G_EB_N07_MAKEOUTORIENTEDINTENT = 5;//出具对外担保函
	public static final int G_EB_N08_MAKECONTRACTLEGALDEPAUDITING = 8;//生成合同-法务审核修改为合同制作
	public static final int G_EB_N09_BUSINESSMANAGERREGISTER = 10;//业务经理登记
	public static final int G_EB_N010_FINANCEMANAGERREGISTER = 10;//财务经理登记
	public static final int G_EB_N011_COUNTERGUARANTEEREGISTER = 10;//反担保措施登记
	public static final int G_EB_N012_MGSUPERVISE = 5;//保中监管
	public static final int G_EB_N013_PROJECTBGENDCONFIRM = 10;//项目保前归档
	public static final int G_EB_N014_GETGUARANTEELIABILITYUNCHAINLETTER = 10;//获取担保责任解除函
	public static final int G_EB_N015_HANDBACKCAUTIONMONEY = 5;//退还保证金
	public static final int G_EB_N016_UNCHAINCOUNTERGUARANTEE = 6;//解除反担保措施
	//public static final int G_EB_N017_CONFIRMAMOUNTOFMONEYHASRELEASE = 3;//确认授信额度已释放
	public static final int G_EB_N018_PROJECTENDCONFIRM = 5;//项目归档
	public static final int G_EB_N019_UPLOADMEETTINGSUMMARY = 5;//上传会议纪要
	
	节点 中英文对照【担保贷款-常规流程】
	public static final String CN_EB_N01_projectManagerSurveyed = "业务经理尽职调查";
	public static final String CN_EB_N02_directorCheckMaterial = "业务主管审核上报材料";
	public static final String CN_EB_N03_beforeSynthesizeAnalyse = "保前综合分析";
	public static final String CN_EB_N04_guaranteeLetterOfIntent = "出具拟担保意向书";
	public static final String CN_EB_N05_risksSupervisorApprove = "风险主管审核";
	public static final String CN_EB_N06_examinationArrangement = "审保会决议";
	public static final String CN_EB_N07_makeOutOrientedIntent = "出具对外担保函";
	public static final String CN_EB_N08_makeContractLegalDepAuditing = "合同制作";
	public static final String CN_EB_N09_businessManagerRegister = "业务经理登记";
	public static final String CN_EB_N010_financeManagerRegister = "财务经理登记";
	public static final String CN_EB_N011_counterGuaranteeRegister = "反担保措施登记";
	public static final String CN_EB_N012_mgSupervise = "保中监管";
	public static final String CN_EB_N013_projectBgEndConfirm = "项目保前归档";
	public static final String CN_EB_N014_getGuaranteeLiabilityUnchainLetter = "获取担保责任解除函";
	public static final String CN_EB_N015_handBackCautionMoney = "退还保证金";
	public static final String CN_EB_N016_unchainCounterGuarantee = "解除反担保措施";
	//public static final String CN_EB_N017_confirmAmountOfMoneyHasRelease = "确认授信额度已释放";
	public static final String CN_EB_N018_projectEndConfirm = "项目归档";
	public static final String CN_EB_N019_uploadMeettingSummary = "上传会议纪要";
	
	
	
	*//**
	 * 中铭常规流程-流程任务期限常量
	 *//*

	节点 时限【中铭常规流程】
	public static final int G_ZM_N01_PROJECTRESPONSIBLEINVESTIGATION = 20;//项目尽调材料上报
	public static final int G_ZM_N02_BUSINESSSUPERVISORAPPROVE = 5;//业务主管审核
	public static final int G_ZM_N03_GUARANTEELETTEROFINTENT = 5;//出具拟担保意向书
	public static final int G_ZM_N04_EXAMINATIONARRANGEMENT = 10;//审保会集体决议
	public static final int G_ZM_N05_EASYACCESS = 5;//绿色通道审保会决议
	public static final int G_ZM_N06_STOCKHOLDERRESOLUTION = 5;//出具对外担保承诺函及股东会决议
	public static final int G_ZM_N07_SUBMITPROJECTASSUREMATERIAL = 5;//提交项目担保材料
	public static final int G_ZM_N08_APPROVEASSUREMATERIAL = 8;//审核担保材料
	public static final int G_ZM_N09_GATHERINTERRELATEDCOST = 10;//收取相关费用
	public static final int G_ZM_N010_CONFIRMGATHERINTERRELATEDCOST = 10;//确认费用收取
	public static final int G_ZM_N011_MAKECONTRACT = 10;//保证合同制作
	public static final int G_ZM_N012_CONFIRMSIGNCONTRACT = 5;//保证合同签署确认
	public static final int G_ZM_N013_PAYBIDBONDTOBANK = 10;//向银行缴纳保证金
	public static final int G_ZM_N014_REGISTERLOANINFORMATION = 10;//银行贷款信息登记
	public static final int G_ZM_N015_CONFIRMGATHERPREMIUM = 5;//保费收取确认
	public static final int G_ZM_N016_PROJECTBEFOREEND = 6;//项目保前归档
	public static final int G_ZM_N017_COUNTERGUARANTEEREGISTER = 5;//反担保措施登记
	public static final int G_ZM_N018_MGSUPERVISE = 5;//保中监管
	public static final int G_ZM_N019_BREAKACONTRACTHANDLE = 5;//违约处理
	public static final int G_ZM_N020_GETGUARANTEELIABILITYUNCHAINLETTER = 5;//获取担保责任解除函
	public static final int G_ZM_N021_HANDBACKCAUTIONMONEY = 5;//退还保证金
	public static final int G_ZM_N022_UNCHAINCOUNTERGUARANTEE = 5;//解除反担保措施
	public static final int G_ZM_N023_PROJECTENDCONFIRM = 5;//项目归档
	
	节点 中英文对照【中铭常规流程】
	public static final String CN_ZM_N01_projectResponsibleInvestigation = "项目尽调材料上报";
	public static final String CN_ZM_N02_businessSupervisorApprove = "业务主管审核";
	public static final String CN_ZM_N03_guaranteeLetterOfIntent = "出具拟担保意向书";
	public static final String CN_ZM_N04_examinationArrangement = "审保会集体决议";
	public static final String CN_ZM_N05_easyAccess = "绿色通道审保会决议";
	public static final String CN_ZM_N06_stockholderResolution = "出具对外担保承诺函及股东会决议";
	public static final String CN_ZM_N07_submitProjectAssureMaterial = "提交项目担保材料";
	public static final String CN_ZM_N08_approveAssureMaterial = "审核担保材料";
	public static final String CN_ZM_N09_gatherInterrelatedCost = "收取相关费用";
	public static final String CN_ZM_N010_confirmGatherInterrelatedCost = "确认费用收取";
	public static final String CN_ZM_N011_makeContract = "保证合同制作";
	public static final String CN_ZM_N012_confirmSignContract = "保证合同签署确认";
	public static final String CN_ZM_N013_payBidBondToBank = "向银行缴纳保证金";
	public static final String CN_ZM_N014_registerLoanInformation = "银行贷款信息登记";
	public static final String CN_ZM_N015_confirmGatherPremium = "保费收取确认";
	public static final String CN_ZM_N016_projectBeforeEnd = "项目保前归档";
	public static final String CN_ZM_N017_counterGuaranteeRegister = "反担保措施登记";
	public static final String CN_ZM_N018_mgSupervise = "保中监管";
	public static final String CN_ZM_N019_breakAContractHandle = "违约处理";
	public static final String CN_ZM_N020_getGuaranteeLiabilityUnchainLetter = "获取担保责任解除函";
	public static final String CN_ZM_N021_handBackCautionMoney = "退还保证金";
	public static final String CN_ZM_N022_unchainCounterGuarantee = "解除反担保措施";
	public static final String CN_ZM_N023_projectEndConfirm = "项目归档";*/
	
	
	/**
	 * 接口调用成功编码
	 */
	public static final String CODE_SUCCESS="8888";
	
	/**
	 * 接口调用失败编码
	 */
	public static final String CODE_FAILED="0000";
	
	
	//通过第三方进行实名验证
	public static final String SYSTEM_AUTHENTICATION_THIRDPAY="thirdPayAuthentication";
	//通过ID5进行实名认证
	public static final String SYSTEM_AUTHENTICATION_ID5="ID5Authentication";
	//我们自己的实名认证方法
	public static final String SYSTEM_AUTHENTICATION_SYSTEM="systemAuthentication";
}
