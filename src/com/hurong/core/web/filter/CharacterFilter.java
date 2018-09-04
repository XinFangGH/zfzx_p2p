package com.hurong.core.web.filter;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.JsonParser;
import com.hurong.core.util.StringUtil;

/**
 * 请求参数过滤器
 * @author LIUSL
 *
 */
public class CharacterFilter implements Filter {
	private  transient final static Log logger = LogFactory.getLog(CharacterFilter.class);
	public CharacterFilter() {
	}
	private static Properties urlFilterProperties =null;
	
	private static Map urlMap = null;
	
	/*
	 * 初始化配置文件
	 * 配置文件规则
	 * 		*没有配置的url默认为过滤系统中自带的特殊字符,配置的url可自定义符号
	 * 		配置文件格式：url地址 = 过滤符号
	 * 				 url地址为项目名称后的相对路径:如hurong_p2p_abc/credit/user/saveProject.do  --->>  /credit/user/saveProject.do
	 * 				   过滤符号以逗号相隔开  如 <,{   注：过滤符号没有的情况不配置=
	 * 		示例1:/credit/user/saveProject.do=<,{
	 * 		示例2:/credit/user/editProject.do
	 */
    public void init(FilterConfig fConfig) throws ServletException {
		 urlFilterProperties = new Properties();
		 urlMap = new HashMap();
		 try{
			 String src = CharacterFilter.class.getClassLoader().getResource("urlfilter.properties").getPath();
			 src = src.replace("%20", " ");
			 logger.error("***URL过滤配置文件路径:---"+src);
			 urlFilterProperties.load(new FileReader(src));
			 urlMap.putAll(urlFilterProperties);
			 logger.error("***正在加载URL过滤配置文件***");
			 Set set =  urlMap.keySet();
			 Iterator<String> iterator = set.iterator();
			 while (iterator.hasNext()) {
				String key = iterator.next();
				System.out.println(key+"="+urlMap.get(key));
			 }
			 logger.error("***URL过滤配置文件加载完成***");
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
			
	}
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//路径处理---------------------------------------
		//转为HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse)response;
		rep.addHeader("x-frame-options","SAMEORIGIN");
		//项目名称
		String contextPath = req.getContextPath();
		//带项目名称的相对路径
		String requestURI = req.getRequestURI();
		/*---------------------地址过滤开始--------------------------------*/
		StringBuffer requestURL = req.getRequestURL();
		//如果地址有非法符号侧转到404页面
		if(containsErrorStr(requestURI.toString())){
			request.getRequestDispatcher("/html/error_page_404.html").forward(request, response);
			logger.error("路径非法："+requestURL);
			logger.error("跳转到404");
			return;//方法结束
		}
		
		/*---------------------地址过滤结束--------------------------------*/
		
		requestURI = requestURI.replace(contextPath, "");
		//判断URL地址在不在配置文件中
		URLFilterResult urlFilterResult = findKeyByUrl(requestURI);
		if(urlFilterResult.isFlag()){
		//	logger.error("***自定义过滤开始***");
			
			//获得查找到的urkKey值查找是否配置了自定义标签
			if(!"NULL".equals(urlMap.get(urlFilterResult.getUrlKey()))){
				// 获得所有请求参数名
				Enumeration params = request.getParameterNames();
				while (params.hasMoreElements()) {
					// 得到参数名
					String name = params.nextElement().toString();
					// 得到参数对应值
					String[] value = request.getParameterValues(name);
					
					Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
					for (int i = 0; i < value.length; i++) {
						m.put(name, new String[] { replaceString(value[i], (String)urlMap.get(requestURI)) });
					}
					request = new ParameterRequestWrapper((HttpServletRequest) request,m);
				}
			}
			
			
		}else{
		//	logger.error("***默认过滤开始***");
			// 获得所有请求参数名
			Enumeration params = request.getParameterNames();
			while (params.hasMoreElements()) {
				// 得到参数名
				String name = params.nextElement().toString();
				// 得到参数对应值
				String[] value = request.getParameterValues(name);
				
				Map<String, String[]> m = new HashMap<String, String[]>(request.getParameterMap());
				for (int i = 0; i < value.length; i++) {
				//	m.put(name, new String[] { StringUtil.html2Text(value[i]) });
					m.put(name, new String[] { replaceSting(value[i]) });
				}
				request = new ParameterRequestWrapper((HttpServletRequest) request,m);
			}
		}
		chain.doFilter(request, response);
	}
	
	/**
	 *  默认的过滤方法
	 * 	过滤的符号
	 */
	public static String replaceSting(String c){
	//	System.out.println("参数："+c);
		c = c.replace("&", "&amp;")
		 .replace("<", "&lt;")
		 .replace("%3C", "&lt;")
		 .replace(">", "&gt;")
		 .replace("%3E", "&gt;")
	//	 .replace("(", "")
	//	 .replace(")", "")
	//	 .replace("{", "")
		 .replace("%7B", "")
	//	 .replace("}", "")
		 .replace("%7D", "")
		 .replace("<xss", "")
		 ;
		/*
		 * 如果有{  或 }
		 * 判断是否同时有{ } @    这种情况应该是{}@{}数据串
		 * 再下步判断是不是{}单独的json串
		 * 如果不是侧替换为空
		 */
		if(c.contains("{")||c.contains("}")){
			if(c.contains("{")&&c.contains("}")&&c.contains("@")){  //  {}@{}@{}
				
			}else if(!isGoodJson(c)){
				c = c.replace("{", "")
					 .replace("}", "");
			}
		}
		
		//空格单独处理,如果不是日期侧替换
		if(!isDate(c)){
			c = c.replace(" ", "&nbsp;");
		}
		
		//大小括号单独处理(  )
		if(
			(c.contains("('")&&c.contains("')"))
			||(c.contains("(\"")&&c.contains("\""))
			||(c.contains("(")&&c.contains(",")&&c.contains(""))
		){//全是 where in中的参数不做处理
			
		}else{
			c = c.replace("(", "")
				 .replace(")", "");
		}
		
		return c;
	}
	
	/**
	 * 自定义符号过滤方法
	 * ---自定义的过滤全部替换为空
	 * @param c   --参数
	 * @param filterTag   --过滤的符号
	 * @return
	 */
	public static String replaceString(String c,String filterTag){
		String[] strArr = filterTag.split(",");
		for(int i = 0 ; i < strArr.length ; i++ ){
			c = c.replace(strArr[i], "");
		}
		
		return c;
	}
	
	/**
	 * URL地址过滤
	 * @param url
	 * @return
	 */
	public static boolean containsErrorStr(String url){
		if(url.contains("{")
		 ||url.contains("%7B")
		 ||url.contains("}")
		 ||url.contains("%7D")
		 ||url.contains("(")
		 ||url.contains(")")
		 ||url.contains("<")
		 ||url.contains("%3C")
		 ||url.contains(">")
		 ||url.contains("%3E")
		 ||url.contains("\"")
		 ||url.contains("%22")
		 ||url.contains("'")
		 ||url.contains(" ")
		){
			return true;
		}
		return false;
	}
	
	/*
	 * 查找URL是否存在配置文件中
	 */
	public static URLFilterResult findKeyByUrl(String url){
		URLFilterResult urlFilterResult = new URLFilterResult(false, "");
		/*-----------------*号通配符号查找------------------------*/
		//最一个"/"符号位置
		int index = url.lastIndexOf("/");
		//最一个"/"符号之前
		String urlStart = url.substring(0,index);
		//最一个"/"符号之后
		String urlEnd = url.substring(index+1);
		
		/*-----------------直接查找------------------------*/
		Set keySet = urlMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			if(url.equals(next)){//直接全部匹配查找
				urlFilterResult.setFlag(true);
				urlFilterResult.setUrlKey(next);
				return urlFilterResult;
			}
			//最一个"*"符号之后
			String nextEnd = next.substring(next.lastIndexOf("*")+1);
			if(next.contains(urlStart)&&urlEnd.contains(nextEnd)){//*号通配符号查找
				urlFilterResult.setFlag(true);
				urlFilterResult.setUrlKey(next);
				return urlFilterResult;
			}
		}
	
		
		return urlFilterResult;
	}
	
	/*
	 * 判断是不是json串
	 * 是返回true
	 * 不是返回false
	 */
	public static boolean isGoodJson(String json) {  
	        if (json==null||"".equals(json)) {  
	            return false;  
	        }  
	        try {  
	            new JsonParser().parse(json);  
	            logger.error("发现json: " + json);  
	            return true;  
	        } catch (Exception e) {  
	            logger.error("不是json: " + json);  
	            return false;  
	        }  
	}
	
	/*
	 * 判断是不是日期
	 * 是  返回true
	 * 不是 返回false
	 */
	public static boolean isDate(String str){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date parse2 = format.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	
	}
	
}
