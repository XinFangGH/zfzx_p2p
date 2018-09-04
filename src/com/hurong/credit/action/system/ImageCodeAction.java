package com.hurong.credit.action.system;

import com.hurong.core.web.action.BaseAction;
import com.hurong.credit.config.RandomValidateCode;

public class ImageCodeAction extends BaseAction{
	
	
	public String get(){
	
		  this.getResponse().setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
		  this.getResponse().setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
		  this.getResponse().setHeader("Cache-Control", "no-cache");
		  this.getResponse().setDateHeader("Expire", 0);
	      RandomValidateCode randomValidateCode = new RandomValidateCode();
	      try {
	          randomValidateCode.getRandcode(this.getRequest(), this.getResponse());//输出图片方法
	      } catch (Exception e) {
	          e.printStackTrace();
	      }
	      
	      return null;
	}	
}
