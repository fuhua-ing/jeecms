package com.jeecms.common.email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestUrlConstants
{

	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUrlConstants.class);

	public static final String SEND_MESSAGE = "http://sh2.cshxsp.com/smsJson.aspx?action=send";

	public static final String MESSAGE_TEMPALTE = "您的验证码为@，有效期3分钟，请速去填写。【创新金融中心】";

	private static final Map<String, String> THREAD_LOCAL = new HashMap<>();

	static
	{
		try
		{
			LOGGER.info("did it init MessagePropties?");
			String dir = System.getProperty("user.dir");
			String dir2 = dir.substring(0, dir.length() - 4) + File.separator + "webapps" + File.separator + "ROOT"
					+ File.separator + "WEB-INF" + File.separator + "config" + File.separator + "Message.properties";
			Properties properties = new Properties();
			properties.load(new FileInputStream(dir2));
			Enumeration<?> propertyNames = properties.propertyNames();
			while (propertyNames.hasMoreElements())
			{
				String key = (String) propertyNames.nextElement();
				String value = properties.getProperty(key);
				THREAD_LOCAL.put(key, value);
			}
			for (Entry<String, String> entry : THREAD_LOCAL.entrySet())
			{
				LOGGER.info("I did get the {}: and the {}", entry.getKey(), entry.getValue());
			}
		}
		catch (FileNotFoundException e)
		{
		}
		catch (IOException e)
		{
		}

	}

	public static String getPassword()
	{
		String password = THREAD_LOCAL.get("password");

		return MD5Util.MD5Encode(password, "UTF-8");

	}

	public static String getAccout()
	{
		return THREAD_LOCAL.get("account");
	}
}
