package com.sms;

import java.io.InputStream;
import java.util.Properties;




public class PropertiesUtil {
private static Properties props = null;
	
	private synchronized static void loadProperties(){
		props = new Properties();
		try{
			InputStream input = PropertiesUtil.class.getResourceAsStream("/com/sms/url.properties");
			props.load(input);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getProperty(String propName){
		loadProperties();
		return props.getProperty(propName);
	}
	
}
