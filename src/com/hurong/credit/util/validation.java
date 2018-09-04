/**
 * 
 */
package com.hurong.credit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hurong.core.Constants;

/**
 * 验证类：用来验证手机号码，邮箱，身份证，组织机构代码，Ip等号码准确性
 * @author linyan
 *
 */
public class validation {
	
	/**
	 * 验证手机号码准确性方法
	 * @param telphone
	 * @return
	 */
	public static String[] phoneValidation(String telphone){
		String [] codemeg=new String[2];
		if(telphone!=null&&!"".equals(telphone)){//手机号码不能空
			/**
			 * 根据实际开发于2009年9月7日最新统计：  
 			 * 中国电信发布中国3G号码段:中国联通185,186;中国移动188,187;中国电信189,180共6个号段。  
             * 3G业务专属的180-189号段已基本分配给各运营商使用, 其中180、189分配给中国电信,187、188归中国移动使用,185、186属于新联通。  
             * 中国移动拥有号码段：139、138、137、136、135、134、159、158、157（3G）、152、151、150、188（3G）、187（3G）;14个号段  
             * 中国联通拥有号码段：130、131、132、155、156（3G）、186（3G）、185（3G）;6个号段  
             * 中国电信拥有号码段：133、153、189（3G）、180（3G）;4个号码段  
             * 移动:  
             * 2G号段(GSM网络)有139,138,137,136,135,134(0-8),159,158,152,151,150  
             * 3G号段(TD-SCDMA网络)有157,188,187  
             * 147是移动TD上网卡专用号段.  
             * 联通:  
             * 2G号段(GSM网络)有130,131,132,155,156  
             * 3G号段(WCDMA网络)有186,185  
             * 电信:  
             * 2G号段(CDMA网络)有133,153  
             * 3G号段(CDMA网络)有189,180 
             * 
             * 2018年4月19日更新
             * 新增
             * 	联通166（0~9）号段
             * 	中国电信199（0~9）
             * 	移动 198（0~9）
			 */
//			Pattern p = Pattern.compile("^((13[0-9])|(14[5|7])|(15[^4,\\D])|(166)|(18[0-9])|(17[0-9]))|(19[8|9])\\d{8}$");       
			Pattern p = Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");       
	        Matcher m = p.matcher(telphone);
	        if(m.matches()){//手机号码验证正确
	        	codemeg[0]=Constants.CODE_SUCCESS;
				codemeg[1]="手机号码验证正确";
	        }else{
	        	codemeg[0]=Constants.CODE_FAILED;
				codemeg[1]="手机号码格式不正确";
	        }
		}else{
			codemeg[0]=Constants.CODE_FAILED;
			codemeg[1]="手机号码不能为空";
		}
		return codemeg;
	}

}
