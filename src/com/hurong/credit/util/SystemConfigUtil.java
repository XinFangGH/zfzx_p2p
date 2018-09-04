package com.hurong.credit.util;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hurong.core.command.QueryFilter;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.article.Article;
import com.hurong.credit.model.article.ArticleCategory;
import com.hurong.credit.model.article.P2pFriendlink;
import com.hurong.credit.model.system.SysConfig;
import com.hurong.credit.model.system.SystemConfig;
import com.hurong.credit.service.article.ArticleCategoryService;
import com.hurong.credit.service.article.ArticleService;
import com.hurong.credit.service.article.P2pFriendlinkService;
import com.hurong.credit.service.system.SysConfigService;


public class SystemConfigUtil {
	
	public static final String SYSTEM_CONFIG_CACHE_KEY = "systemConfig";// systemConfig缓存Key
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	/**
	 * 获取系统配置信息
	 * 
	 * @return SystemConfig对象
	 */
	public static SystemConfig getSystemConfig() {
		SystemConfig systemConfig=null;
		try{
			systemConfig = new SystemConfig();
			systemConfig.setFileURL(configMap.get("fileURL").toString());
			systemConfig.setErpURL(configMap.get("erpURL").toString());
 
			systemConfig.setMetaAuthor(MetaUtil.META_AUTHOR_VALUE);//网站作者
			systemConfig.setMetaLanguage(MetaUtil.META_LANGUAGE_VALUE);//网站语言
			systemConfig.setMetaGenerator(MetaUtil.META_GENERATOR_VALUE);//头部generator
			systemConfig.setTheme(AppUtil.getProjStr());//模版路径
			//System.out.println("======================="+AppUtil.getProjStr());
			systemConfig.setThirdPay(configMap.get("thirdPayConfig").toString());
			systemConfig.setThirdPayType(configMap.get("thirdPayType").toString());
			systemConfig.setSystem_authentication_type(configMap.get("system_authentication_type").toString());
			systemConfig.setSystem_authentication_show(configMap.get("system_authentication_show").toString());
			// 手续费
			if(configMap.containsKey("poundage")){
				systemConfig.setPoundage(configMap.get("poundage").toString());
			}
			if(configMap.containsKey("poundage")){
				systemConfig.setIsOpenPayMentCode(configMap.get("poundage").toString());
			}
		
			systemConfig.setMetaSubject(configMap.get("subject").toString());//头部subject
			systemConfig.setMetaDescription(configMap.get("description").toString());//头部description
			systemConfig.setMetaKeywords(configMap.get("keywords").toString());//头部keywords
			systemConfig.setMetaRobots(configMap.get("robots").toString());//头部robots
			systemConfig.setMetaTitle(configMap.get("metaname").toString());//头部title
			systemConfig.setRegDeal(configMap.get("regDeal").toString());
			systemConfig.setPhone4(configMap.get("phone").toString());
			
			systemConfig.setCopyRight(null==configMap.get("copyRight")?null:configMap.get("copyRight").toString());//站点版权
			systemConfig.setWorkTime(null==configMap.get("workTime")?null:configMap.get("workTime").toString());//工作时间
			systemConfig.setCompanyAddress(null==configMap.get("companyAddress")?null:configMap.get("companyAddress").toString());//公司地址
			systemConfig.setCustomerEmail(null==configMap.get("customerEmail")?null:configMap.get("customerEmail").toString());//客服邮箱
			systemConfig.setConsumerHotline(null==configMap.get("consumerHotline")?null:configMap.get("consumerHotline").toString());//客服电话
			systemConfig.setConsumerQQ(null==configMap.get("consumerQQ")?null:configMap.get("consumerQQ").toString());//客服QQ
			systemConfig.setP2pLogoFile(null==configMap.get("p2p_logoFile")?null:configMap.get("p2p_logoFile").toString());//P2P_logo
			systemConfig.setP2pIconFile(null==configMap.get("p2pIconFile")?null:configMap.get("p2pIconFile").toString());//P2P_icon
			systemConfig.setWeibo(null==configMap.get("weibo")?null:configMap.get("weibo").toString());
			systemConfig.setWeixin(null==configMap.get("weixin")?null:configMap.get("weixin").toString());
			systemConfig.setBeianInfo(null==configMap.get("beianInfo")?null:configMap.get("beianInfo").toString());//网站备案信息
			systemConfig.setBaiduMapKey(null==configMap.get("baiduMap")?null:configMap.get("baiduMap").toString());//百度地图appkey
			systemConfig.setBaiduMapMarkers(null==configMap.get("baiduMapMarker")?null:configMap.get("baiduMapMarker").toString());//百度地图标记
			systemConfig.setCountCode(null==configMap.get("countCode")?null:configMap.get("countCode").toString());//站长统计
			
			//查询网站认证
			QueryFilter filter=new QueryFilter();
			filter.addFilter("Q_configKey_S_EQ","attest");
			SysConfigService sysConfigService=(SysConfigService)AppUtil.getBean("sysConfigService");
			List<SysConfig> list = sysConfigService.getAll(filter);
			if(null!=list && list.size()>0){
				systemConfig.setAttest(list.get(0).getDataValue());
			}
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			String host =inetAddress.getHostName(); //获取本地的主机名
			systemConfig.setHost(host);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return systemConfig;
	}
	
	/**
	 * 获取友情链接
	 * 
	 * @return 对象
	 */
	public static List<P2pFriendlink> getFriendLink(int typ) {
		
		P2pFriendlinkService service=((P2pFriendlinkService) AppUtil.getBean("p2pFriendlinkService"));
		List<P2pFriendlink> list=null;
		try{
		 list= service.getListByType(typ);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return list;
	}
	/**
	 * 获取友情链接(根据网站类别)
	 * 
	 * @return 对象
	 */
	public static List<P2pFriendlink> getFriendLinkByWebKey(String webKey) {
		
		P2pFriendlinkService service=((P2pFriendlinkService) AppUtil.getBean("p2pFriendlinkService"));
		List<P2pFriendlink> list=null;
		try{
		 list= service.getListByWebKey(webKey);
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return list;
	}

	public static List<Article> getArticleByCat(int i) {
		ArticleCategoryService catServer=null;
		ArticleCategory articleCategory=null;
		ArticleService server=null;
		try{
		 catServer=((ArticleCategoryService) AppUtil.getBean("articlecategoryService"));
		 articleCategory=catServer.get(Long.valueOf(i));
		 server=((ArticleService) AppUtil.getBean("articleService"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return server.getByCat(articleCategory);
	}
	

}