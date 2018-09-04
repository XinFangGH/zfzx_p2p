package com.hurong.core.web.filter;

/**
 * 
 * @author LIUSL
 *
 */
public class URLFilterResult {
	/**
	 * url配置项是否存在
	 * true 存在 
	 * false不存在
	 */
	private boolean flag = false;
	/**
	 * urlKEY 值
	 */
	private String urlKey;
	
	public URLFilterResult(boolean flag ,String urlKey){
		this.flag = flag ;
		this.urlKey = urlKey;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getUrlKey() {
		return urlKey;
	}
	public void setUrlKey(String urlKey) {
		this.urlKey = urlKey;
	}
	
}
