package com.jeecms.core.entity;

import java.sql.Timestamp;

import com.jeecms.core.entity.base.AbstractJcEvent;

/**
 * JcEvent entity. @author MyEclipse Persistence Tools
 */
public class JcEvent extends AbstractJcEvent implements java.io.Serializable
{

	// Constructors

	/** default constructor */
	public JcEvent()
	{
	}

	/** minimal constructor */
	public JcEvent(Integer eventUserId)
	{
		super(eventUserId);
	}

	/** full constructor */
	public JcEvent(String eventUuid, String eventType, Timestamp eventApplyTime, Timestamp eventEndTime,
			Integer eventUserId)
	{
		super(eventUuid, eventType, eventApplyTime, eventEndTime, eventUserId);
	}

}
