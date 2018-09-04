package com.hurong.core.jms;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hurong.core.engine.MailEngine;
import com.hurong.core.model.MailModel;
import com.hurong.core.util.AppUtil;

/**
 * 从消息队列中读取对象，并且进行邮件发送
 * @author csx
 *
 */
public class MailMessageConsumer {
	
	private static final Log logger=LogFactory.getLog(MailMessageConsumer.class);
	
	@Resource
	private MailEngine mailEngine;
	public MailMessageConsumer(){
		if(mailEngine==null){
			mailEngine=(MailEngine)AppUtil.getBean("mailEngine");
			
		}
	}
	@SuppressWarnings("unchecked")
	public String  sendMail(MailModel mailModel){
		logger.debug("send mail now " + mailModel.getSubject());
		//来自
		mailModel.setFrom(AppUtil.getSysConfig().get("mail.from").toString());
		mailModel.setContent(mailModel.getContent());
		return mailEngine.sendTemplateMail(
				mailModel.getMailTemplate(),
				mailModel.getMailData(),
				mailModel.getSubject(),
			    null,
			    new String[]{mailModel.getTo()}, null, null, null, null, true);
	}
	
	public String  sendMail(String template ,MailModel mailModel){
		logger.debug("send mail now " + mailModel.getSubject());
		
			mailModel.setMailTemplate(template);
		
		//来自
		mailModel.setFrom(AppUtil.getSysConfig().get("mail.from").toString());
		mailModel.setContent("");
		return mailEngine.sendTemplateMail(
				mailModel.getMailTemplate(),
				mailModel.getMailData(),
				mailModel.getSubject(),
			    null,
			    new String[]{mailModel.getTo()}, null, null, null, null, true);
	}
}
