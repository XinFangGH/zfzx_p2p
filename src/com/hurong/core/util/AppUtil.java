package com.hurong.core.util;

/*
 *  北京金智万维软件有限公司 OA办公自动管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
 */

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Node;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.menu.TopModule;
import com.hurong.core.model.OnlineUser;
import com.hurong.core.web.filter.SecurityInterceptorFilter;
import com.hurong.credit.model.system.IndexShow;
import com.hurong.credit.model.user.BpCustMember;
import com.hurong.credit.service.creditFlow.creditAssignment.bank.ObSystemAccountService;
import com.hurong.credit.service.system.IndexShowService;
import com.hurong.credit.service.system.SysConfigService;
import com.hurong.credit.util.MyUserSession;

/**
 * 方便取得Spring容器，取得其他服务实例，必须在Spring的配置文件里进行配置 如：<bean id="appUtil"
 * class="com.hurong.util.core.AppUtil"/> 也提供整个应用程序的相关配置获取方法
 * @author csx
 */
@SuppressWarnings("unchecked")
public class AppUtil implements ApplicationContextAware {

	private static Log logger = LogFactory.getLog(AppUtil.class);
	
	private static ApplicationContext appContext;

	private static SysConfigService sysConfigService=null;
	/**
	 * 存放应用程序的配置,如邮件服务器等
	 */
	private static Map configMap = new HashMap();
	/**
	 * 应用程序全局对象
	 */
	public static ServletContext servletContext;
	/**
	 * 存放在线用户,SessionId,OnlineUser
	 */
	private static Map<String, OnlineUser> onlineUsers = new LinkedHashMap<String, OnlineUser>();
	/**
	 * 在线用户的ID
	 */
	private static HashSet<Long> onlineUserIds = new HashSet<Long>();
	/**
	 * 系统的左边导航菜单文档，当系统启动时， 由系统去解析menu-all.xml，并放置系统，供权限菜单使用
	 */
	private static Map<String, Document> orgMenus = null;
	/**
	 * 去除了Function与url
	 */
	private static Map<String, Document> itemsMenus = null;
	/**
	 * 系统的所有头部菜单配置
	 */
	private static Map<String, TopModule> allTopModels = null;
	/**
	 * 系统的所有菜单功能
	 */
	private static Document menuDocument = null;
	/**
	 * 公共的顶部模块
	 */
	public static Map<String, TopModule> publicTopModules = null;

	public static Map<String, TopModule> getPublicTopModules() {
		return publicTopModules;
	}

	public static void setPublicTopModules(Map<String, TopModule> publicTopModules) {
		AppUtil.publicTopModules = publicTopModules;
	}

	public static Map<String, Document> getItemsMenus() {
		return itemsMenus;
	}

	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.appContext = applicationContext;
	}

	public static void setConfigMap(Map configMap) {
		AppUtil.configMap = configMap;
	}

	public static Map<String, Document> getOrgMenus() {
		return orgMenus;
	}

	public static Map getConfigMap() {
		return configMap;
	}
	
	public static Map<String, TopModule> getAllTopModels() {
		return allTopModels;
	}

	public static Document getMenuDocument() {
		return menuDocument;
	}
	
	public static HashSet<Long> getOnlineUserIds() {
		return onlineUserIds;
	}

	/**
	 * @param nodeName 节点名称
	 * */
	public static String getTextByNodeName(String nodeName) {
		if (nodeName == null || "".equals(nodeName) || nodeName.trim().length() == 0) {
			return null;
		}
		Document document = XmlUtil.getSystemConfigXML();
		if (document != null) {
			Node node = document.selectSingleNode(nodeName);
			if (node != null) {
				return node.getText();
			}
		}
		return null;
	}

	public static BpCustMember getBpCustMember(HttpServletRequest req) {
		BpCustMember mem = (BpCustMember) req.getSession().getAttribute(MyUserSession.MEMBEER_SESSION);
		mem = ((ObSystemAccountService) AppUtil.getBean("obSystemAccountService")).getAccountSumMoney(mem);
		return mem;
	}
	
	/**
	 * 取得Bean
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId) {
		return appContext.getBean(beanId);
	}

	/**
	 * 返回在线用户
	 * @return
	 */
	public static Map<String, OnlineUser> getOnlineUsers() {
		return onlineUsers;
	}

	/**
	 * 移除在线用户
	 * @param sessionId
	 */
	public static void removeOnlineUser(String sessionId) {
		OnlineUser user = onlineUsers.get(sessionId);
		if (user != null) {
			onlineUserIds.remove(user.getUserId());
		}
		onlineUsers.remove(sessionId);
	}

	/*
	 * 项目路径
	 */
	public static String getProjStr() {
		try {
			Document document = XmlUtil.getSystemConfigXML();
			Node projStrNode = document.selectSingleNode("/hurong/systemConfig/projStr");
			return projStrNode.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "zhiweiConfig";
		}
	}

	/**
	 * 取得应用程序的绝对路径
	 * @return
	 */
	public static String getAppAbsolutePath() {
		return servletContext.getRealPath("/");
	}

	/**
	 * 取得配置菜单的xml目录的绝对路径
	 * @return
	 */
	public static String getMenuAbDir() {
		return getAppAbsolutePath() + "/js/menu/xml/";
	}

	/**
	 * @return
	 */
	public static String getMenuXslDir() {
		return getAppAbsolutePath() + "/js/menu/";
	}

	/**
	 * 取得流程表单模板的目录的绝对路径
	 * @return
	 */
	public static String getFlowFormAbsolutePath() {
		String path = (String) configMap.get("app.flowFormPath");
		if (path == null){
			path = "/WEB-INF/FlowForm/";
		}
		return getAppAbsolutePath() + path;
	}

	public static String getMobileFlowFlowAbsPath() {
		return getAppAbsolutePath() + "/mobile/flow/FlowForm/";
	}

	/**
	 * 重新加载安全权限匹配的数据源
	 */
	public static void reloadSecurityDataSource() {
		SecurityInterceptorFilter securityInterceptorFilter = (SecurityInterceptorFilter) AppUtil.getBean("securityInterceptorFilter");
		securityInterceptorFilter.loadDataSource();
	}

	/**
	 * 应用程序启动时调用
	 * @param servletContext
	 */
	public static void init(ServletContext in_servletContext) {
		servletContext = in_servletContext;

		//1.TODO 读取来自公共的config.properties文件的配置,并且放入configMap内,应用程序共同使用
		String proj_filePath = servletContext.getRealPath("/WEB-INF/classes/conf/");
		String proj_configFilePath = proj_filePath + "/config.properties";
		try {
			commonPut(proj_configFilePath);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}

		//2.TODO 读取来自项目下config.properties文件的配置,并且放入configMap内,应用程序共同使用
		String filePath = servletContext.getRealPath("/WEB-INF/template/"+ getProjStr() + "/");
		String configFilePath = filePath + "/config.properties";
		try {
			commonPut(configFilePath);
			// 初始化网站参数设置 start
			IndexShowService indexShowService = (IndexShowService) AppUtil.getBean("indexShowService");
			QueryFilter filter = new QueryFilter();
			filter.addFilter("Q_type_S_EQ", "1");
			List<IndexShow> showList = indexShowService.getAll(filter);
			if (null != showList && !"".equals(showList)) {
				for (IndexShow indexShow : showList) {
					configMap.put(indexShow.getTwoLevelType(), indexShow.getDescription());
				}
			}
			// end
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	
	/**
	 * 公用方法:将属性文件中的内容添加到configMap中
	 * @param filePath  属性文件路径
	 */
	public static void commonPut(String filePath){
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(filePath);
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println("key="+key);
				configMap.put(key, props.getProperty(key));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 获取系统配置MAP
	 */
	public static Map getSysConfig() {
		return configMap;
	}
}