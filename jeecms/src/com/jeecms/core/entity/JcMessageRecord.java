package com.jeecms.core.entity;

import java.sql.Timestamp;

import com.jeecms.core.entity.base.AbstractJcMessageRecord;

/**
 * JcMessageRecord entity. @author MyEclipse Persistence Tools
 */
public class JcMessageRecord extends AbstractJcMessageRecord implements java.io.Serializable
{

	// Constructors

	/** default constructor */
	public JcMessageRecord()
	{
	}

	/** minimal constructor */
	public JcMessageRecord(String userName, String reserveOne, String reserveTwo)
	{
		super(userName, reserveOne, reserveTwo);
	}

	/** full constructor */
	public JcMessageRecord(String messageCode, String messageType, Timestamp messageApplyTime,
			Timestamp messageEndTime, String userName, String reserveOne, String reserveTwo)
	{
		super(messageCode, messageType, messageApplyTime, messageEndTime, userName, reserveOne, reserveTwo);
	}

}
