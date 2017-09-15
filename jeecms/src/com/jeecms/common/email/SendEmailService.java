package com.jeecms.common.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public final class SendEmailService
{

	public void send(String subject, String context, String email) throws GeneralSecurityException,
			FileNotFoundException, IOException, AddressException, MessagingException
	{
		Properties props = new Properties();
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);
		String dir = System.getProperty("user.dir");
		String dir2 = dir.substring(0, dir.length() - 4) + File.separator + "webapps" + File.separator + "ROOT"
				+ File.separator + "WEB-INF" + File.separator + "config" + File.separator + "Email.properties";
		props.load(new FileInputStream(dir2));

		Session session = Session.getInstance(props);

		Transport ts = session.getTransport();
		ts.connect(props.getProperty("mail.host"), props.getProperty("mail.username"),
				props.getProperty("mail.password"));
		MimeMessage message = createSimpleMail(session, subject, context, email, props);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	private static MimeMessage createSimpleMail(Session session, String subject, String context, String email,
			Properties props) throws AddressException, MessagingException
	{
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(props.getProperty("mail.email")));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		// 邮件的标题
		message.setSubject(subject);
		// 邮件的文本内容
		message.setContent(context, "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

}
