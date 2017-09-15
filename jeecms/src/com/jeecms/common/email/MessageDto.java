package com.jeecms.common.email;

import java.io.Serializable;

public class MessageDto implements Serializable
{
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 9145810662636698081L;

	private String userid;

	private String account;

	private String password;

	private String mobile;

	private String content;

	private String sendTime;

	private String action;

	private String extno;

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSendTime()
	{
		return sendTime;
	}

	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public String getExtno()
	{
		return extno;
	}

	public void setExtno(String extno)
	{
		this.extno = extno;
	}
}
