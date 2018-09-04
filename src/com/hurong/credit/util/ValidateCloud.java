package com.hurong.credit.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.hurong.core.Constants;
import com.hurong.core.util.AppUtil;
import com.hurong.credit.model.user.BpCustMember;

/**
 * 目前只有个人客户
 * */
public class ValidateCloud {
	//验证用户
	public static String ValidateUser(BpCustMember bpCustMember,String operationType){
		String xml = createXml(bpCustMember,operationType);
		if(xml==null){
			return null;
		}
		String resultXML = sendMsg(xml);
		if(resultXML==null){
			return null;
		}
		String type  = parseXml(resultXML);
		return type;
	}
	//向云平台发送数据
	private static String sendMsg(String xml){
		String service_url = "http://empinterface.sdcloud.net/EMP2ISVInterface/EMP2ISVInterface.asmx";
        Service service = new Service();        
        try {			
			//String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><Msg><Head><Code>PUB10101</Code><ParkID>01</ParkID><SubmitTime>20120131173734884</SubmitTime><Version>1.0</Version><Priority>10</Priority></Head><Body><Account>scscnc</Account><UserType>0</UserType><UserEmail>2438</UserEmail><CompanyName>11</CompanyName><OrganizationCode>12345678-9</OrganizationCode><CompanyEmail>111@126.com</CompanyEmail></Body></Msg>";
			Call call = (Call)service.createCall(); 
			call.setTargetEndpointAddress(new URL(service_url));
			call.setOperationName(new QName("http://empinterface.sdcloud.net/EMP2ISVInterface/","EMPISVInterfaceXMLString")); 	
				
			call.addParameter("strXML",org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); 	
			call.setUseSOAPAction(true); 	
			call.setSOAPActionURI("http://empinterface.sdcloud.net/EMP2ISVInterface/EMP2ISVInterface.asmx?op=EMPISVInterfaceXMLString");
			String k = (String) call.invoke(new Object[]{xml});
			System.out.println(xml);
			System.out.println(k);
			return k;
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	//生成XML
	public static String createXml(BpCustMember bpCustMember,String operationType){
		String parkID = AppUtil.getTextByNodeName("/hurong/systemConfig/ParkID");
		String version = AppUtil.getTextByNodeName("/hurong/systemConfig/Version");
		String priority = AppUtil.getTextByNodeName("/hurong/systemConfig/Priority");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Document document = DocumentHelper.createDocument();
		Element Msg = document.addElement("Msg");//根节点
		Element Head = Msg.addElement("Head");//Head
		Element Code = Head.addElement("Code");
		Code.setText(operationType);
		Element ParkID = Head.addElement("ParkID");
		ParkID.setText(parkID);
		Element SubmitTime = Head.addElement("SubmitTime");
		SubmitTime.setText(sdf.format(bpCustMember.getRegistrationDate()));
		Element Version = Head.addElement("Version");
		Version.setText(version);
		Element Priority = Head.addElement("Priority");
		Priority.setText(priority);
		
		Element Body = Msg.addElement("Body");//Body
		Element Account = Body.addElement("Account");
		Account.setText(bpCustMember.getLoginname());
		Element UserType = Body.addElement("UserType");
		UserType.setText("0");
		Element UserEmail = Body.addElement("UserEmail");
		if(bpCustMember.getEmail()!=null){
			UserEmail.setText(bpCustMember.getEmail());
		}
		if(operationType.equals(Constants.PUB_ADD_USER)){
			Element OPType = Body.addElement("OPType");
			if(bpCustMember.getOPType() != null){
				
				OPType.setText(bpCustMember.getOPType());
			}else{
				OPType.setText("0");
			}
		
			Element SynType = Body.addElement("SynType");
			SynType.setText("01");
			
			Element Password = Body.addElement("Password");
			if(bpCustMember.getPassword()!=null){
				String pass= bpCustMember.getPassword();
				Password.setText(pass);
				
			}
			Element pId = Body.addElement("ParkID");
			pId.setText(parkID);
			Element UserName = Body.addElement("UserName");
			if(bpCustMember.getTruename()!=null){
				UserName.setText(bpCustMember.getTruename());
			}
			Element UserPhone = Body.addElement("UserPhone");
			if(bpCustMember.getHomePhone()!=null){
				UserPhone.setText(bpCustMember.getHomePhone());
			}
			Element Status = Body.addElement("Status");
			Status.setText("0");
		}
		return document.asXML();
	}
	//解析XML
	@SuppressWarnings("deprecation")
	public static String parseXml(String xml){
		SAXReader saxReader = new SAXReader();
		String resultType = null;
		try {
			Document doc = saxReader.read(new StringReader(xml));
			if(doc!=null){
				//
				Node node=null;
				node = doc.selectSingleNode("/Msg/Body/ResultCode");//实名验证使用
				/*if(sign != null){
					 node = doc.selectSingleNode("/Msg/Head/Code"); //投资客户使用
				}else{
					
				}*/
				
				if(node!=null){
					resultType = node.getText();
				}else{
					System.out.println("node==null");
				}
			}else{
				System.out.println("doc==null");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return resultType;
	}
}
