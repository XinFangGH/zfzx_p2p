package com.hurong.credit.action.system;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.hurong.core.util.AppUtil;
import com.hurong.core.util.JsonUtil;
import com.hurong.core.util.PropertiesUtil;
import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.service.sms.MessageStrategyService;
import com.hurong.credit.util.freemarkerToWord.FileHelper;

@SuppressWarnings("unchecked")
public class SystemPropertiesAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private static String filePath = AppUtil.servletContext.getRealPath("/WEB-INF/classes/conf/config.properties");
	private static String sendPath = AppUtil.servletContext.getRealPath("/WEB-INF/classes/conf/sendmessage_config.properties");
	private static String sendPathExplain = AppUtil.servletContext.getRealPath("/WEB-INF/classes/conf/senduseExplain_config.properties");
	
	public String saveP2P() {
		Map map=AppUtil.getSysConfig();
		//同步更新config.properties属性文件和config对象
		resetProperties(this.getRequest(),filePath,map);
		return SUCCESS;
	}
	public String saveP2PExplain() {
		Map map=AppUtil.getSysConfig();
		//同步更新config.properties属性文件和config对象
		resetProperties(this.getRequest(),sendPathExplain,map);
		return SUCCESS;
	}
	/**
	 * 查看P2P系统基础配置参数
	 * @return
	 */
	public String getP2PBaseInfo(){
		StringBuffer sb=new StringBuffer("{\"success\":true,\"data\":{");
		try {
			readProperties(filePath,sb);
			readProperties(sendPath,sb);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		
		String data=sb.substring(0,sb.toString().length()-1)+"}}";
		setJsonString(data);
		return SUCCESS;
	}
	
	
	public String toGetP2pInfo(){
		int sms_count  =0;
		StringBuffer sms=new StringBuffer("");
		StringBuffer sb1=new StringBuffer("{\"success\":true,\"data\":{");
		try {
			Map<?,?> map=AppUtil.getConfigMap();
			Iterator<?> ite=map.keySet().iterator();
			while(ite.hasNext()){
				String key=ite.next().toString();
				if(key.startsWith("sms_") && !key.equals("sms_benname")){
					sms_count++;
					sms.append("{\"模板"+sms_count+"\":").append("\""+map.get(key)+"\"},");
				}
			}
			sb1.append("\"SMS_Address\":\"").append(map.get("smsUrl")).append("\",").
			append("\"SMS_UserName\":\"").append(map.get("smsAccountID")).append("\",").
			append("\"SMS_Pass\":\"").append(map.get("smsPassword")).append("\",").
			append("\"SMS_Count\":\"").append(sms_count).append("\"").
			append("}}");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		setJsonString(sb1.toString());
		JsonUtil.responseJsonString(sb1.toString());
		return SUCCESS;
	}
	
	/**
	 * 获取短信模板使用说明
	 * @return
	 */
	public String getP2PSendPathExplain(){
		StringBuffer sb=new StringBuffer("{\"success\":true,\"data\":{");
		try {
			readProperties(sendPathExplain,sb);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		String data=sb.substring(0,sb.toString().length()-1)+"}}";
		setJsonString(data);
		return SUCCESS;
	}
	
	
	
	public void readProperties(String path,StringBuffer sb){
		try {
			String temp="";
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(path);
			Reader r = new InputStreamReader(fis, "UTF-8");
			props.load(r);
			Iterator it = props.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				configMap.put(key, props.getProperty(key));
				if(!sb.toString().contains(key)){
					temp=props.getProperty(key).replace("\"","'");
					if(key.equals("attest")){//网站认证
						temp=temp.replace("^","\\n");
					}
					sb.append("\""+key+"\":").append("\""+temp+"\",");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}
	
	/**
	 * 重新配置config.properties属性文件
	 * @param request   请求对象
	 * @param filePath  属性文件路径
	 * @return
	 */
	public void resetProperties(HttpServletRequest request,String filePath,Map map) {
		// 参数Map
		Map<?, ?> properties = request.getParameterMap();
		Iterator<?> entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		try{
			while (entries.hasNext()) {
				entry = (Map.Entry) entries.next();
				name = (String) entry.getKey();
				Object valueObj = entry.getValue();
				if (null == valueObj) {
					value = "";
				} else if (valueObj instanceof String[]) {
					String[] values = (String[]) valueObj;
					for (int i = 0; i < values.length; i++) {
						value = values[i] + ",";
					}
					value = value.substring(0, value.length() - 1);
				} else {
					value = valueObj.toString();
				}
				if(!name.equals("attest")){//网站认证信息
					PropertiesUtil.writeKey(filePath, name, value);
					map.put(name,value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除后台ERP上传的文件
	 * @return
	 */
	public String deletFile(){
		try{
			Map map=AppUtil.getConfigMap();
			String realPath = super.getRequest().getRealPath("/");
			String remark=this.getRequest().getParameter("remark");
			String path=this.getRequest().getParameter("filePath");
			//1.从config对象中移除相关对象
			map.remove(remark);
			//2.重新配置属性文件
			PropertiesUtil.writeKey(filePath,remark,"");
			//3.删除文件
			File f = new File(realPath+path);
			if(f.exists()){
				FileHelper.deleteFile(f);
			}
			setJsonString("{success:true,msg:'删除成功!'}");
		}catch(Exception e){
			setJsonString("{success:true,msg:'删除失败!'}");
		}
		return SUCCESS;
	}
	
	
	/**  
	    * 根据主键key读取主键的值value  
	    * @param filePath 属性文件路径  
	    * @param key 键名  
	    */   
	    public static String readValue(String filePath, String key) {   
	        Properties props = new Properties();   
	        try {   
	            InputStream in = new BufferedInputStream(new FileInputStream(   
	                    filePath));   
	            props.load(in);   
	            String value = props.getProperty(key);   
	            System.out.println(key +"键的值是："+ value);   
	            return value;   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	            return null;   
	        }   
	    }   
	    
	    
	    private static Properties props = new Properties();   
	    static {   
	        try {   
	            props.load(new FileInputStream(sendPath));   
	        } catch (FileNotFoundException e) {   
	            e.printStackTrace();   
	            System.exit(-1);   
	        } catch (IOException e) {          
	            System.exit(-1);   
	        }   
	    } 
	
	    
		public void updateProperty(String keyname,String keyvalue){
	        try {   
	            props.load(new FileInputStream(sendPath));   
	            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
	            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。   
	            OutputStream fos = new FileOutputStream(sendPath);              
	            props.setProperty(keyname, keyvalue);   
	            // 以适合使用 load 方法加载到 Properties 表中的格式，   
	            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
	            props.store(fos, "Update '" + keyname + "' value");   
	            fos.close();
	        } catch (IOException e) {   
	            System.err.println("属性文件更新错误");   
	        }  
		}
		
		
		
		 public static void writeProperties(String keyname,String keyvalue) {          
		        try {   
		            // 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。   
		            // 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。   
		            OutputStream fos = new FileOutputStream(sendPath);   
		            props.setProperty(keyname, keyvalue);   
		            // 以适合使用 load 方法加载到 Properties 表中的格式，   
		            // 将此 Properties 表中的属性列表（键和元素对）写入输出流   
		            Map<String,String> map=AppUtil.getConfigMap();
		            map.put(keyname, keyvalue);
		            props.store(fos, "Update '" + keyname + "' value");   
		        } catch (IOException e) {   
		            System.err.println("属性文件更新错误");   
		        }   
		    } 
		 
		 
			public String testSMSConn(){
				String type="ERP";//平台类型用以区分是ERP还是P2P
				//获得短信提供上名称
				String sms_bean="";
				if("ERP".equals(type)){
					sms_bean=AppUtil.getConfigMap().get("sms_benname").toString();
				}
				MessageStrategyService messageStrategy = (MessageStrategyService) AppUtil.getBean(sms_bean);
				String phone= "13161939897";//this.getRequest().getParameter("telphone");
				String sms_test="";
				if("hXSmsManagerService".equals(sms_bean)){//华兴软通
					sms_test=AppUtil.getConfigMap().get("sms_test").toString().substring(0, AppUtil.getConfigMap().get("sms_test").toString().length()-4);
					
					//sms_test=sms_test.replace("${subject}", AppUtil.getConfigMap().get("subject")==null?"":AppUtil.getConfigMap().get("subject").toString());
				}else if("yXTessageService".equals(sms_bean)){//亿信通
					sms_test=AppUtil.getConfigMap().get("sms_yx_test").toString().trim().substring(0,AppUtil.getConfigMap().get("sms_yx_test").toString().length()-3);
				}
					String result=messageStrategy.sendMsg(phone,sms_test);
					if(result == null){
						JsonUtil.responseJsonString("{\"success\":false,\"id\":0,\"msg\":\"【短信接口调用失败】!\"}");
					}else{
						String[] reltArr = result.split("&");
						String[] resultArr = reltArr[0].split("=");
						if(resultArr[1].equals("28") || resultArr[1].equals("2") || resultArr[1].equals("3")){
							JsonUtil.responseJsonString("{\"success\":true,\"id\":0,\"msg\":\"【短信接口调用成功】!\"}");
						}else{
							JsonUtil.responseJsonString("{\"success\":false,\"id\":0,\"msg\":\"【短信接口调用失败，账号密码错误】!\"}");
						}
					}
				return SUCCESS;
			}

		 
		 /**
			 *更新短信配置文件 
			 */
			public String updateBaseInfo(){
				String smsUrl = this.getRequest().getParameter("smsUrl");
				String smsUserName = this.getRequest().getParameter("smsAccountID");
				String smsUserPass = this.getRequest().getParameter("smsPassword");
				if(smsUrl!=null && !"".equals(smsUrl)){
					readValue(sendPath, "smsUrl");   
					writeProperties("smsUrl", smsUrl);
				}
				
				if(smsUserName!=null && !"".equals(smsUserName)){
					readValue(sendPath, "smsAccountID");   
					writeProperties("smsAccountID", smsUserName);
				}
				    
				if(smsUserPass!=null && !"".equals(smsUserPass)){
					readValue(sendPath, "smsPassword");   
					updateProperty("smsPassword", smsUserPass);
				}
			    System.out.println("操作完成");  
			    //getBaseInfo();
			    StringBuffer sb = new StringBuffer("{\"success\":true,e:true,result:'保存成功！',\"data\":{");
				sb.append("\"smsUrl\":\"").append(smsUrl).append("\",").
				append("\"smsAccountID\":\"").append(smsUserName).append("\",").
				append("\"smsPassword\":\"").append(smsUserPass).append("\",").
				append("}}");
			    //setJsonString("{success:true,e:true,result:'保存成功！'}");
			    setJsonString(sb.toString());
				return SUCCESS;
			}
			
			
}