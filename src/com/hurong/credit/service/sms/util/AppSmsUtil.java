package com.hurong.credit.service.sms.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.util.validation;

/**
 * 发送短信专用Util，主要用于读取配置文件中的配置参数信息
 * 
 * @author dell
 * 
 */
public class AppSmsUtil {

	private static Log logger = LogFactory.getLog(AppSmsUtil.class);

	/**
	 * 应用程序全局对象
	 */
	private static ApplicationContext appContext;

	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		this.appContext = applicationContext;
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
	 * 应用程序启动时调用
	 * 
	 * @param servletContext
	 */
	@SuppressWarnings("unchecked")
	public static void init() {
		String appAbsolutePath = AppUtil.getAppAbsolutePath();
		// 读取来自公共的config.properties文件的配置,并且放入configMap内,应用程序共同使用
		String configFilePath = appAbsolutePath+ "WEB-INF/classes/conf/sendmessage_config.properties";
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(configFilePath);
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				System.out.println(key+"===="+props.get(key));
				AppUtil.getConfigMap().put(key, props.get(key));
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/*************************** 获取配置文件中的信息 ******************************/
	public static Document getSystemConfigXML() {
		try {
			Document document = null;
			File configFile = null;
			String configFilePath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+ "zhiwei.xml";
			configFile = new File(configFilePath);
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(configFile);
			return document;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * 项目 路径
	 */
	public static String getProjStr() {
		try {
			Document document = getSystemConfigXML();
			Node projStrNode = document.selectSingleNode("/zhiwei/systemConfig/projStr");
			return projStrNode.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return "zhiweiConfig";
		}
	}

	/**
	 * 用于验证手机验证码是否正确
	 * 
	 * @param telphone
	 *            手机号码
	 * @param phonecode
	 *            验证码
	 * @param sessioncode
	 *            session中存放的验证码
	 * @return
	 */
	public static String[] checkCode(String telphone, String phonecode,
			String sessioncode) {
		String[] codemeg = new String[2];
		String[] resultmeg = new String[2];
		codemeg = validation.phoneValidation(telphone);
		if (codemeg[0].equals(Constants.CODE_SUCCESS)) {   // 验证手机号号码准确性
			if (phonecode != null && !"".equals(phonecode)) {
				// TOOD 将来最好应有相应的算法存放验证码，目前的算法是手机号码加验证码来存放
				String code = telphone.concat(phonecode);
				if (sessioncode != null && !"".equals(sessioncode)) {
					if (sessioncode.equals(code)) { // 验证手机验证码是否正确
						resultmeg[0] = Constants.CODE_SUCCESS;
						resultmeg[1] = "验证成功";
					} else {
						resultmeg[0] = Constants.CODE_FAILED;
						resultmeg[1] = "验证码错误";
					}
				} else {
					resultmeg[0] = Constants.CODE_FAILED;
					resultmeg[1] = "验证码不存在";
				}
			} else {
				resultmeg[0] = Constants.CODE_FAILED;
				resultmeg[1] = "手机验证码不能为空";
			}
		}else{
			resultmeg[0] = "error";
			resultmeg[1] = codemeg[1];
		}
		return resultmeg;
	}
}