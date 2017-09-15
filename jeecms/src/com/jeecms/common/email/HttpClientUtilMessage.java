package com.jeecms.common.email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtilMessage
{
	private static PoolingHttpClientConnectionManager cm = null;

	private static final Logger log = LoggerFactory.getLogger(HttpClientUtilMessage.class);
	static
	{
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", new PlainConnectionSocketFactory()).build();
		cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
	}

	public static CloseableHttpClient getHttpClient() throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}

	public static void sendMeassage(String phoneNum, String text)
	{
		try
		{

			CloseableHttpClient httpClient = getHttpClient();
			HttpPost post = new HttpPost(RequestUrlConstants.SEND_MESSAGE);
			post.setHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			BasicNameValuePair userid = new BasicNameValuePair("userid", "");
			BasicNameValuePair account = new BasicNameValuePair("account", RequestUrlConstants.getAccout());
			BasicNameValuePair password = new BasicNameValuePair("password", RequestUrlConstants.getPassword());
			BasicNameValuePair mobile = new BasicNameValuePair("mobile", phoneNum);
			BasicNameValuePair content = new BasicNameValuePair("content", StringUtils.replace(
					RequestUrlConstants.MESSAGE_TEMPALTE, "@", text));
			BasicNameValuePair sendTime = new BasicNameValuePair("sendTime", "");
			BasicNameValuePair extno = new BasicNameValuePair("extno", "");
			list.add(userid);
			list.add(account);
			list.add(password);
			list.add(mobile);
			list.add(content);
			list.add(sendTime);
			list.add(extno);

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
			post.setEntity(entity);
			CloseableHttpResponse execute = httpClient.execute(post);

			String string = EntityUtils.toString(execute.getEntity());
			System.out.println("HttpClientUtil.sendMeassage()");
			System.out.println("HttpClientUtil.sendMeassage()");
			System.out.println("HttpClientUtil.sendMeassage()");
			System.out.println("HttpClientUtil.sendMeassage()");

			System.out.println(string);
			execute.close();
		}
		catch (ClientProtocolException e)
		{

		}
		catch (IOException e)
		{

		}
	}

}