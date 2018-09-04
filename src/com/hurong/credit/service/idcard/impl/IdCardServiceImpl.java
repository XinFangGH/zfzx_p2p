package com.hurong.credit.service.idcard.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.axis.encoding.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.compass.core.util.reader.StringReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.thirdInterface.PlThirdInterfaceLog;
import com.hurong.credit.service.idcard.IdCardService;
import com.hurong.credit.service.thirdInterface.PlThirdInterfaceLogService;
import com.hurong.credit.util.UrlUtils;
import com.hurong.credit.util.WebClient;
import com.secret.QueryValidatorServices;
import com.secret.QueryValidatorServicesService;



public class IdCardServiceImpl implements IdCardService {
	protected transient final Log logger = LogFactory.getLog(getClass());
	@Resource
	PlThirdInterfaceLogService plThirdInterfaceLogService;
	
    private static final String KEY="c0e9664e585f099a3a1b641a32fcf875";
    private static final String URL="http://apis.juhe.cn/idcard/index";
    
	//得到config.properties读取的所有资源
	private static Map configMap = AppUtil.getConfigMap();
	
	@Override
	public String idCardQuery(String cardId) {
		Map<String, String> params=new HashMap<String, String>();
		params.put("key", KEY);
		params.put("cardno", cardId);
		String retdata="";
		try {
			String url=UrlUtils.generateUrl(params, URL,"utf-8");
			retdata=  WebClient.getWebContentByGet(url,"utf-8",36000000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retdata;
	}
	/*
	 * data:"刘丽萍,210204196501015829"
	 * queryType:"1A020201"--身份证
	 * returnType：singleType---单条数据查询
	 * */
	@Override
	public String idCardQuery(String returnType, String queryType, String data) {
		if(returnType!=null&&returnType.equals("singleQuery")){
			return querySingle(queryType,data);
		}
		return null;
	}
 
	public String querySingle(String queryType,String data){//查询单条记录
		
		return query(queryType,data);
	}
	
	public String queryBatch(String quertyType,String data){//查询多条记录
		
		return null;
	}
	
	public String query(String queryType,String data){
		String encodeKey = configMap.get("system_authentication_ID5_encodeKey").toString();
		String userName=configMap.get("system_authentication_ID5_userName").toString();
		String password=configMap.get("system_authentication_ID5_password").toString();
		QueryValidatorServicesService services = new QueryValidatorServicesService();
		QueryValidatorServices service = services.getQueryValidatorServices();
		String returnXml = service.querySingle(encode(encodeKey,userName), encode(encodeKey,password), encode(encodeKey,"1A020201"), encode(encodeKey,data));
		//String respCode,String msgExt,String plain,String buyerName
		addLog("8888","returnXml",returnXml,"---");
		if(returnXml!=null){
			return passeXML(decode(encodeKey,returnXml));
		}
		return null;
	}
	
	public String encode(String key,String data){//加密算法
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes()); 
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
			 //key的长度不能够小于8位字节 
			 Key secretKey = keyFactory.generateSecret(dks); 
			 Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding"); 
			 IvParameterSpec iv=new IvParameterSpec("12345678".getBytes());//向量 
			 AlgorithmParameterSpec paramSpec = iv; 
			 cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec); 
			 byte[] bytes = cipher.doFinal(data.getBytes()); 
			 return Base64.encode(bytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		return null;
	}
	public String decode(String key,String datas){//解密算法
		 try {
			 SecureRandom sr = new SecureRandom(); 
			 DESKeySpec dks = new DESKeySpec(key.getBytes()); 
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES"); 
			 //key 的长度不能够小于 8 位字节 
			 Key secretKey = keyFactory.generateSecret(dks);
			 Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			 IvParameterSpec iv=new IvParameterSpec("12345678".getBytes());//向量 
			 AlgorithmParameterSpec paramSpec = iv;
			 cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
			 return new String(cipher.doFinal(Base64.decode(datas)),"GB2312");
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String passeXML(String xml){//解析xml文档
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new StringReader(xml));
			Element element = document.getRootElement();
			Element dataStatus = (Element) element.selectSingleNode("/data/message/status");
			Element dataValue = (Element) element.selectSingleNode("/data/message/value");
			if(dataStatus==null){
				return null;
			}else{
				String value = dataStatus.getText();
				if(value==null){
					return null;
				}else if(!value.equals("0")){
					return "resultcode:\"-1\",resultvalue:\""+dataValue.getText()+"\"";
				}else {
					Element mStatus = (Element) element.selectSingleNode("/data/policeCheckInfos/policeCheckInfo/compStatus");
					Element mValue = (Element) element.selectSingleNode("/data/policeCheckInfos/policeCheckInfo/compResult");
					
					if(mStatus!=null){
						return "resultcode:\""+mStatus.getText()+"\",resultvalue:\""+mValue.getText()+"\"";
					}else{
						return "resultcode:\"2\",resultvalue:\"查询失败!\"";
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void addLog(String respCode,String msgExt,String plain,String buyerName){
		//respCode：0000失败，8888成功
		//msgExt:失败，成功
		//plain：发送信息
		//buyerName:使用者
		plThirdInterfaceLogService.saveLog(respCode, msgExt, plain, "ID5验证接口",
				null, PlThirdInterfaceLog.MEMTYPE1,
				PlThirdInterfaceLog.TYPE2, "身份证验证",buyerName,"","","");
	}
}
