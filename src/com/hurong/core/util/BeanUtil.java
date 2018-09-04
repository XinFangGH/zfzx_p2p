package com.hurong.core.util;

/*
 *  北京金智万维软件有限公司 OA办公自动管理系统   -- http://www.credit-software.com
 *  Copyright (C) 2008-2011 JinZhi WanWei Software Company
 */
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.collection.PersistentBag;
import org.hibernate.proxy.map.MapProxy;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hurong.core.dao.impl.DynamicDaoImpl;
import com.hurong.core.model.DynaModel;
import com.hurong.core.service.DynamicService;
import com.hurong.core.service.impl.DynamicServiceImpl;
import com.hurong.core.struts.BeanDateConnverter;


public class BeanUtil {
	
	private static Log logger = LogFactory.getLog(BeanUtil.class);
	/**
	 * BeanUtil类型转换器
	 */
	public static ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
	
	static{
		convertUtilsBean.register(new BeanDateConnverter(), Date.class);
		convertUtilsBean.register(new LongConverter(null), Long.class);
	}
	
	/**
	 * 拷贝一个bean中的非空属性于另一个bean中
	 * 
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyNotNullProperties(Object dest, Object orig) throws IllegalAccessException, InvocationTargetException {
		BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("BeanUtils.copyProperties(" + dest + ", " + orig+ ")");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			DynaProperty[] origDescriptors = ((DynaBean) orig).getDynaClass()
					.getDynaProperties();
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				// Need to check isReadable() for WrapDynaBean
				// (see Jira issue# BEANUTILS-61)
				if (beanUtils.getPropertyUtils().isReadable(orig, name)
						&& beanUtils.getPropertyUtils().isWriteable(dest, name)) {
					Object value = ((DynaBean) orig).get(name);
					beanUtils.copyProperty(dest, name, value);
				}
			}
		} else if (orig instanceof Map) {
			Iterator entries = ((Map) orig).entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry entry = (Map.Entry) entries.next();
				String name = (String) entry.getKey();
				if (beanUtils.getPropertyUtils().isWriteable(dest, name)) {
					beanUtils.copyProperty(dest, name, entry.getValue());
				}
			}
		} else /* if (orig is a standard JavaBean) */{
			PropertyDescriptor[] origDescriptors = beanUtils.getPropertyUtils()
					.getPropertyDescriptors(orig);
			for (int i = 0; i < origDescriptors.length; i++) {
				String name = origDescriptors[i].getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (beanUtils.getPropertyUtils().isReadable(orig, name)
						&& beanUtils.getPropertyUtils().isWriteable(dest, name)) {
					try {
						Object value = beanUtils.getPropertyUtils()
								.getSimpleProperty(orig, name);
						if (value != null) {
							beanUtils.copyProperty(dest, name, value);
						}
					} catch (NoSuchMethodException e) {
						// Should not happen
					}
				}
			}
		}

	}
	
	/**
	 * Map to json string
	 * @param map
	 * @return
	 */
	public static String mapEntity2Html(Map<String,Object>mapData,String entityName){
		StringBuffer sb=new StringBuffer("<ul>");
		Iterator entryIt =mapData.entrySet().iterator();
		int i=0;
		sb.append("</ul>");
		return sb.toString();
	}

	/**
	 * 通过Http请求封装实体值
	 * @param request
	 * @param entity
	 * @return
	 */
	public static Map<String,Object> populateEntity(HttpServletRequest request,DynaModel dynaModel) {
		Map valuesMap=request.getParameterMap();
		return populateEntity(valuesMap,dynaModel);
	}
	
	/**
	 * 通过Map封装实体值
	 * @param valuesMap 格式为<String,String>
	 * @param dynaModel
	 * @return
	 */
	public static Map<String,Object> populateEntity(Map valuesMap,DynaModel dynaModel){
		
		Iterator typeIt=dynaModel.getTypes().entrySet().iterator();
		
		HashMap<String,Object> datas=new HashMap<String,Object>();
		
		while(typeIt.hasNext()){
			Map.Entry entry = (Map.Entry)typeIt.next();
			String fieldName=(String)entry.getKey();
			Class fieldType=(Class)entry.getValue();
			
			Object val=valuesMap.get(fieldName);
			if(val!=null){
				datas.put(fieldName,convertValue(convertUtilsBean, val, fieldType));
			}
		}
		return datas;
		
	}
	/**
	 * 通过Request构造实体
	 * @param request 请求对象
	 * @param entity 实体对象
	 * @param preName 请求中的前缀变量
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public static Object populateEntity(HttpServletRequest request,Object entity,String preName)
	throws IllegalAccessException, InvocationTargetException{
	    HashMap map = new HashMap();
	    Enumeration<String> names = request.getParameterNames();
	    while (names.hasMoreElements()) {
	      String name = (String) names.nextElement();
	      //属性名称
	      String propetyName=name;
	      if(StringUtils.isNotEmpty(preName)){
	    	  propetyName=propetyName.replace(preName+".", "");
	      }
	      map.put(propetyName, request.getParameterValues(name));
	    }
	    getBeanUtils().populate(entity, map);
		return entity;
	}

	// 取得动态DynamicService
	public static DynamicService getDynamicServiceBean(String entityName){
		org.springframework.orm.hibernate3.LocalSessionFactoryBean sessionFactory=(LocalSessionFactoryBean) AppUtil.getBean("&sessionFactory");
		DynamicDaoImpl dao = new DynamicDaoImpl(entityName);
		dao.setSessionFactory((SessionFactory)sessionFactory.getObject());
		dao.setEntityClassName(entityName);
		DynamicServiceImpl service = new DynamicServiceImpl(dao);
		return service;
	}

	/**
	 * 转化类型
	 * @param convertUtil
	 * @param value
	 * @param type
	 * @return
	 */
	public static Object convertValue(ConvertUtilsBean convertUtil,Object value,Class type){
		
		Converter converter = convertUtil.lookup(type);
		if(converter==null) return value;
		
		Object newValue=null;
		if (value instanceof String) {
		    newValue = converter.convert(type,(String) value);
		} else if (value instanceof String[]) {
		    newValue = converter.convert(type,((String[]) value)[0]);
		} else {
		    newValue = converter.convert(type,value);
		}
		return newValue;
	}

	/**
	 * 取得能转化类型的bean
	 * 
	 * @return
	 */
	public static BeanUtilsBean getBeanUtils() {
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean(convertUtilsBean,new PropertyUtilsBean());
		return beanUtilsBean;
	}
	
	/**
	 * 返回请求中的所有的对应的Map值
	 * @param request
	 * @return
	 */
	public static Map getMapFromRequest(HttpServletRequest request){
	    	Map reqMap=request.getParameterMap();
	   	
	    	HashMap<String,Object> datas=new HashMap<String,Object>();
		Iterator it = reqMap.entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			String key=(String)entry.getKey();
			String[] val=(String[])entry.getValue();
			if(val.length==1){
			    datas.put(key, val[0]);
			}else{
			    datas.put(key,val);
			}
		}
		return datas;
	}
	
	public  static String projectProjectNumber(Integer size)
	{
		Integer random=1;
		random+=size;
		String randow=String.valueOf(random);
		return randow;
	}
}
