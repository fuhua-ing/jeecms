package com.jeecms.core.entity.base;

import java.sql.Timestamp;

/**
 * AbstractJcMessageRecord entity provides the base persistence definition of
 * the JcMessageRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractJcMessageRecord implements java.io.Serializable
{

	// Fields    

	private Integer id;
	private String messageCode;
	private String messageType;
	private Timestamp messageApplyTime;
	private Timestamp messageEndTime;
	private String userName;
	private String reserveOne;
	private String reserveTwo;

	// Constructors

	/** default constructor */
	public AbstractJcMessageRecord()
	{
	}

	/** minimal constructor */
	public AbstractJcMessageRecord(String userName, String reserveOne, String reserveTwo)
	{
		this.userName = userName;
		this.reserveOne = reserveOne;
		this.reserveTwo = reserveTwo;
	}

	/** full constructor */
	public AbstractJcMessageRecord(String messageCode, String messageType, Timestamp messageApplyTime,
			Timestamp messageEndTime, String userName, String reserveOne, String reserveTwo)
	{
		this.messageCode = messageCode;
		this.messageType = messageType;
		this.messageApplyTime = messageApplyTime;
		this.messageEndTime = messageEndTime;
		this.userName = userName;
		this.reserveOne = reserveOne;
		this.reserveTwo = reserveTwo;
	}

	// Property accessors

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getMessageCode()
	{
		return this.messageCode;
	}

	public void setMessageCode(String messageCode)
	{
		this.messageCode = messageCode;
	}

	public String getMessageType()
	{
		return this.messageType;
	}

	public void setMessageType(String messageType)
	{
		this.messageType = messageType;
	}

	public Timestamp getMessageApplyTime()
	{
		return this.messageApplyTime;
	}

	public void setMessageApplyTime(Timestamp messageApplyTime)
	{
		this.messageApplyTime = messageApplyTime;
	}

	public Timestamp getMessageEndTime()
	{
		return this.messageEndTime;
	}

	public void setMessageEndTime(Timestamp messageEndTime)
	{
		this.messageEndTime = messageEndTime;
	}

	public String getUserName()
	{
		return this.userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getReserveOne()
	{
		return this.reserveOne;
	}

	public void setReserveOne(String reserveOne)
	{
		this.reserveOne = reserveOne;
	}

	public String getReserveTwo()
	{
		return this.reserveTwo;
	}

	public void setReserveTwo(String reserveTwo)
	{
		this.reserveTwo = reserveTwo;
	}

}