package com.hurong.credit.action.pay.AllinPay.query;



import com.aipg.common.InfoReq;
import com.aipg.common.XStreamEx;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Q_Process {
	
	public XStream getXStream( ) {
		XStream xstream = new XStreamEx(new DomDriver());
		xstream.alias("AIPG", Q_AIPG.class);
		xstream.alias("INFO", InfoReq.class);
		xstream.alias("QUERY_TRANS", QUERY_TRANS.class);
		return xstream;
	}
	
	public Q_AIPG parseXML(String strData) {		
		return (Q_AIPG)getXStream().fromXML(strData);
	}
	
	public String formXML(Q_AIPG obj) {
		return getXStream().toXML(obj);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
