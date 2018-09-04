package com.thirdPayInterface.AllinPay.UnionPay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.unionpay.acp.sdk.LogUtil;


public class UnionPayUtil {
	
	private static KeyStore keyStore = null;
	private static Map<String, X509Certificate> certMap = new HashMap();
	 private static X509Certificate validateCert = null;
	/**
	 * 数据签名 
	 * @param data  实体对象
	 * @param signCertPath 证书路径
	 * @param signCertPwd  证书密码
	 * @param signCertType 
	 * @param encoding
	 * @return
	 */
	public static boolean sign(Map<String, String> data,String signCertPath,String signCertPwd,String signCertType, String encoding){
		//得到证书 ID
		 data.put("certId", getSignCertId(signCertPath,signCertPwd,signCertType));
		 String stringData = coverMap2String(data);
		 byte[] byteSign = null;
		 String stringSign = null;
		 try
		 {
		   byte[] signDigest = SecureUtil.sha1X16(stringData, encoding);
		   //获取签名字符串
		   byteSign = SecureUtil.base64Encode(signBySoft(getSignCertPrivateKey(signCertPwd), signDigest));
		   stringSign = new String(byteSign);
		   data.put("signature", stringSign);
		   return true;
		 } catch (Exception e) {
			 return false;
		 }
	}
		
	  public static String coverMap2String(Map<String, String> data){
	       TreeMap tree = new TreeMap();
	       Iterator it = data.entrySet().iterator();
	       while (it.hasNext()) {
	         Map.Entry en = (Map.Entry)it.next();
	         if ("signature".equals(((String)en.getKey()).trim())) {
			      continue;
			  }
	     tree.put(en.getKey(), en.getValue());
	  }
	   it = tree.entrySet().iterator();
	   StringBuffer sf = new StringBuffer();
	   while (it.hasNext()) {
	   Map.Entry en = (Map.Entry)it.next();
	   sf.append(new StringBuilder().append((String)en.getKey()).append("=").append((String)en.getValue()).append("&").toString());
	  }
	  return sf.substring(0, sf.length() - 1);
	}
	 /**
	  * 获取证书id编号
	  * @param signCertPath
	  * @param signCertPwd
	  * @param signCertType
	  * @return
	  */
	 public static String getSignCertId(String signCertPath,String signCertPwd,String signCertType){
		 String serialNumber="";
		 try {
			keyStore = getKeyInfo(signCertPath, signCertPwd, signCertType);
	       Enumeration aliasenum = keyStore.aliases();
	       String keyAlias = null;
	       if (aliasenum.hasMoreElements()) {
	         keyAlias = (String)aliasenum.nextElement();
	       }
	       X509Certificate cert = (X509Certificate)keyStore.getCertificate(keyAlias);
	       serialNumber= cert.getSerialNumber().toString();
	     } catch (Exception e) {
	      
	     }
	     return serialNumber;
	   }
	  
	  public static KeyStore getKeyInfo(String pfxkeyfile, String keypwd, String type)throws IOException{
	     FileInputStream fis = null;
	     try {
	       KeyStore ks = null;
	       if ("JKS".equals(type)) {
	         ks = KeyStore.getInstance(type);
	       } else if ("PKCS12".equals(type)) {
	         Security.insertProviderAt(new BouncyCastleProvider(), 1);
	         Security.addProvider(new BouncyCastleProvider());
	 
	         ks = KeyStore.getInstance(type);
	       }
	 
	       fis = new FileInputStream(pfxkeyfile);
	      char[] nPassword = (null == keypwd) || ("".equals(keypwd.trim())) ? null : keypwd.toCharArray();
	 
	       if (null != ks) {
	         ks.load(fis, nPassword);
	       }
	       KeyStore localKeyStore1 = ks;
	       return localKeyStore1;
	     }
	     catch (Exception e)
	     {
	    	 e.printStackTrace();
	      if (Security.getProvider("BC") == null) {
	       }
	       if (((e instanceof KeyStoreException)) && ("PKCS12".equals(type))) {
	         Security.removeProvider("BC");
	       }
	       KeyStore nPassword = null;
	       return nPassword;
	     }
	     finally
	     {
	       if (null != fis)
	         fis.close(); 
	     }
	   }
	  public static PrivateKey getSignCertPrivateKey(String signCertPwd){
	     try
	     {
	       Enumeration aliasenum = keyStore.aliases();
	       String keyAlias = null;
	       if (aliasenum.hasMoreElements()) {
	         keyAlias = (String)aliasenum.nextElement();
	       }
	       PrivateKey privateKey = (PrivateKey)keyStore.getKey(keyAlias, signCertPwd.toCharArray());
	 
	       return privateKey;
	     } catch (Exception e) {
	     }return null;
	   }
	  
	  public static byte[] signBySoft(PrivateKey privateKey, byte[] data)throws Exception{
	     byte[] result = null;
	     Signature st = Signature.getInstance("SHA1withRSA");
	     st.initSign(privateKey);
	     st.update(data);
	     result = st.sign();
	     return result;
	   }
	  /**
	   * 回调验证签名
	   * @param resData
	   * @param encoding
	   * @return
	   */
	  public static boolean validate(Map<String, String> resData, String encoding)
	   {
	       String stringSign = (String)resData.get("signature");
	       String certId = (String)resData.get("certId");
	       String stringData = coverMap2String(resData);
	       try
	       {
	    	 PublicKey key = getValidateKey(certId,resData.get("certDir").toString());
	         return SecureUtil.validateSignBySoft(key, SecureUtil.base64Decode(stringSign.getBytes(encoding)), SecureUtil.sha1X16(stringData, encoding));
	       } catch (Exception e) {
	    	   e.printStackTrace();
	    	   return false;
	       }
	    }
	  public static PublicKey getValidateKey(String certId,String certDir){
		  X509Certificate cf = null;
	     initValidateCertFromDir(certDir);
	     if (certMap.containsKey(certId))
	     {
	       cf = (X509Certificate)certMap.get(certId);
	       return cf.getPublicKey();
	     }else{
	    	 return null;
	     }
	  }
	  public static void initValidateCertFromDir(String certDir)
	   {
	     certMap.clear();
	     String filePath = certDir;
	     if ((null == filePath) || ("".equals(filePath))) {
	       return;
	     }
	     CertificateFactory cf = null;
	     FileInputStream in = null;
	     try
	     {
	       cf = CertificateFactory.getInstance("X.509");
	       File fileDir = new File(filePath);
	       File[] files = fileDir.listFiles(new CerFilter());
	       for (int i = 0; i < files.length; i++) {
	         File file = files[i];
	         in = new FileInputStream(file.getAbsolutePath());
	         validateCert = (X509Certificate)cf.generateCertificate(in);
	         certMap.put(validateCert.getSerialNumber().toString(), validateCert);
	       }
	       if (null != in)
	         try {
	           in.close();
	         } catch (IOException e) {
	           LogUtil.writeErrorLog(e.toString());
	         }
	     }catch (Exception e)
	     {
	    	 e.printStackTrace();
	     } finally
	     {
	      if (null != in) {
	         try {
	           in.close();
	         } catch (IOException e) {
	           LogUtil.writeErrorLog(e.toString());
	         }
	       }
	     }
	   }

	  static class CerFilter implements FilenameFilter
	   {
	     public boolean isCer(String name)
	     {
	       return name.toLowerCase().endsWith(".cer");
	     }
	 
	     public boolean accept(File dir, String name)
	     {
	       return isCer(name);
	     }
	   }
	public static Map<String, X509Certificate> getCertMap() {
		return certMap;
	}

	public static void setCertMap(Map<String, X509Certificate> certMap) {
		UnionPayUtil.certMap = certMap;
	}
	  
}
