package com.jeecms.common.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class SendEmailTest
{
	public static boolean sendMail(String to, String code)
	{

		try
		{
			Properties props = new Properties();
			props.put("username", "xxxxx@163.com");
			props.put("password", "xxxxxx");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.163.com");
			props.put("mail.smtp.port", "25");

			Session mailSession = Session.getDefaultInstance(props);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress("13326349318@163.com"));
			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject("激活邮件");
			msg.setContent("<h1>此邮件为官方激活邮件</h1>", "text/html;charset=UTF-8");

			msg.saveChanges();

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(props.getProperty("mail.smtp.host"), props.getProperty("username"),
					props.getProperty("password"));
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception
	{

		Properties prop = new Properties();
		// 开启debug调试，以便在控制台查看
		prop.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		prop.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		prop.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		prop.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);

		// 创建session
		Session session = Session.getInstance(prop);
		// 通过session得到transport对象
		Transport ts = session.getTransport();
		// 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
		ts.connect("smtp.qq.com", "168060964", "ftqeworpcbumbjca");//后面的字符是授权码，用qq密码反正我是失败了（用自己的，别用我的，这个号是我瞎编的，为了。。。。）
		// 创建邮件
		Message message = createSimpleMail(session);
		// 发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	/**
	 * @Method: createSimpleMail
	 * @Description: 创建一封只包含文本的邮件
	 */
	public static MimeMessage createSimpleMail(Session session) throws Exception
	{
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("168060964@qq.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("674180639@qq.com"));
		// 邮件的标题
		message.setSubject("JavaMail测试");
		// 邮件的文本内容
		message.setContent("http://www.qq.com", "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}

	public static void mains(String[] args)
	{
		sendMail("xxxx@qq.com", "89");
	}
}
