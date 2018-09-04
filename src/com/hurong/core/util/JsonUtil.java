package com.hurong.core.util;
/*
 *  北京金智万维软件有限公司 OA办公管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.hibernate.collection.PersistentBag;
import org.hibernate.proxy.map.MapProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.json.SqlTimestampConverter;
import com.hurong.core.model.DynaModel;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class JsonUtil {
	private static Log logger=LogFactory.getLog(JsonUtil.class);
	private static String defaultContentType = "text/json; charset=utf-8" ;
	
	/**
	 * 取得json的格式化器，用JSONSerializer可以解决延迟加载的问题
	 * @param dateFields　日期字段
	 * @return
	 */
	public static JSONSerializer getJSONSerializer(String...dateFields){
		JSONSerializer serializer=new JSONSerializer();
		serializer.exclude("*.class");
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), dateFields);
		return serializer;
	}
	
	public static JSONSerializer getJSONSerializer(){
		JSONSerializer serializer=new JSONSerializer();
		serializer.exclude("*.class");
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), Date.class);
		return serializer;
	}
	
	/*
	 * 按照formate格式格式化json中的日期字段 formate like yyyy-MM-dd
	 */
	public static JSONSerializer getJSONSerializerDateByFormate(String formate){
		JSONSerializer serializer=new JSONSerializer();
		serializer.exclude("*.class");
		serializer.transform(new DateTransformer(formate), Date.class);
		return serializer;
	}
	/**
	 * List to json string
	 * @param List
	 * @return
	 */
	public static String listEntity2Json(List<Map<String,Object>> list,String entityName){
		
		StringBuffer sb=new StringBuffer("[");
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map m= list.get(i);
				String entStr=mapEntity2Json(m,entityName);
				sb.append(entStr).append(",");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		
		return sb.toString();
	}
	
	/**
	 * Map to json string
	 * @param map
	 * @return
	 */
	public static String mapEntity2Json(Map<String,Object>mapData,String entityName){

		StringBuffer sb=new StringBuffer("{");
		Gson gson=new GsonBuilder().serializeNulls().create();
		int i=0;
		if(i>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}");
		return sb.toString();
	}
	/**
	 * gson 格式化数据列表
	 * @param datas 数据列表
	 * @param gson Gson对象
	 * @param type 元数据类型
	 * @param fields 延迟加载的字段
	 * @return
	 */
	public static String jsonSerilize(List datas,Gson gson,Type type,String ...fields){
		//触发get方法以使得外键实体能获取以方便不需要的外键映射
		if(fields!=null){
			for(Object obj:datas){
				for(String field:fields){
					String methodName="get"+FunctionsUtil.makeFirstLetterUpperCase(field);
					try{
						Method getMethod=obj.getClass().getMethod(methodName,null);
						getMethod.invoke(obj, null);
					}catch(Exception ex){
						logger.error(ex.getMessage());
					}
				}
			}
		}
		return gson.toJson(datas, type);
	}
	
	/**
	 * 取得通用的Gson格式化，如日期格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Gson getGson(){
		GsonBuilder builder=new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss");
		builder.registerTypeAdapter(Timestamp.class, new SqlTimestampConverter());
		Gson gson=builder.create();
		return gson;
	}
	
	/**
	 * @author 刘俊
	 * response jsonString到客户端
	 * @param jsonString response到客户端的json字符串
	 * @param contentType response的contentType
	 */
	public static void responseJsonString(String jsonString, String contentType){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(contentType);
			PrintWriter pw = response.getWriter() ;
			pw.write(jsonString) ;
			pw.flush() ;
			pw.close() ;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author 刘俊
	 * 使用默认text/json contentType往客户端写json数据-----liujun
	 * @param jsonString response到客户端的json字符串
	 */
	public static void responseJsonString(String jsonString){
		responseJsonString(jsonString, defaultContentType) ;
	}
}
