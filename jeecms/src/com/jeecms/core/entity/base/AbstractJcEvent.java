package com.jeecms.core.entity.base;

import java.sql.Timestamp;

/**
 * AbstractJcEvent entity provides the base persistence definition of the
 * JcEvent entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractJcEvent implements java.io.Serializable
{

	// Fields    

	private Integer eventId;
	private String eventUuid;
	private String eventType;
	private Timestamp eventApplyTime;
	private Timestamp eventEndTime;
	private Integer eventUserId;

	// Constructors

	/** default constructor */
	public AbstractJcEvent()
	{
	}

	/** minimal constructor */
	public AbstractJcEvent(Integer eventUserId)
	{
		this.eventUserId = eventUserId;
	}

	/** full constructor */
	public AbstractJcEvent(String eventUuid, String eventType, Timestamp eventApplyTime, Timestamp eventEndTime,
			Integer eventUserId)
	{
		this.eventUuid = eventUuid;
		this.eventType = eventType;
		this.eventApplyTime = eventApplyTime;
		this.eventEndTime = eventEndTime;
		this.eventUserId = eventUserId;
	}

	// Property accessors

	public Integer getEventId()
	{
		return this.eventId;
	}

	public void setEventId(Integer eventId)
	{
		this.eventId = eventId;
	}

	public String getEventUuid()
	{
		return this.eventUuid;
	}

	public void setEventUuid(String eventUuid)
	{
		this.eventUuid = eventUuid;
	}

	public String getEventType()
	{
		return this.eventType;
	}

	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}

	public Timestamp getEventApplyTime()
	{
		return this.eventApplyTime;
	}

	public void setEventApplyTime(Timestamp eventApplyTime)
	{
		this.eventApplyTime = eventApplyTime;
	}

	public Timestamp getEventEndTime()
	{
		return this.eventEndTime;
	}

	public void setEventEndTime(Timestamp eventEndTime)
	{
		this.eventEndTime = eventEndTime;
	}

	public Integer getEventUserId()
	{
		return this.eventUserId;
	}

	public void setEventUserId(Integer eventUserId)
	{
		this.eventUserId = eventUserId;
	}

}