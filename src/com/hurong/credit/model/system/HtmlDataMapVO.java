package com.hurong.credit.model.system;


import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;  
  
@XmlRootElement(name = "HtmlDataMapVO")  
public class HtmlDataMapVO {  
  
    private String retJson;
    private Map<String, Object> data;
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public String getRetJson() {
		return retJson;
	}
	public void setRetJson(String retJson) {
		this.retJson = retJson;
	}  
   
} 